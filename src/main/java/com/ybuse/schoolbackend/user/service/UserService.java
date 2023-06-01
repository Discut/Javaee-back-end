package com.ybuse.schoolbackend.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ybuse.schoolbackend.clazz.dao.IClassMapper;
import com.ybuse.schoolbackend.clazz.domain.po.ClassName;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.public_enum.RoleEnum;
import com.ybuse.schoolbackend.user.dao.IUserAccountDao;
import com.ybuse.schoolbackend.user.domain.dto.LoginResponseDto;
import com.ybuse.schoolbackend.user.domain.po.UserAccount;
import com.ybuse.schoolbackend.user.domain.vo.UserInfoVo;
import com.ybuse.schoolbackend.user.service.itfc.IUserService;
import com.ybuse.schoolbackend.utils.TokenBlacklistUtil;
import com.ybuse.schoolbackend.utils.TokenUtil;
import com.ybuse.schoolbackend.utils.UserUtil;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService implements IUserService {
    private AuthenticationManager authenticationManager;

    private IUserAccountDao userAccountDao;

    private IClassMapper classMapper;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 公共返回
     */
    @Override
    public CommonResult<LoginResponseDto> login(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        Object userObject = authenticate.getPrincipal();
        User user = userObject instanceof User ? (User) userObject : null;
        CommonResult<LoginResponseDto> objectCommonResult = new CommonResult<>();
        if (authenticate.isAuthenticated()) {
           /* String token = UserUtil.getToken(user, secret, expMillis);
            LoginResponseVo loginResponseVo = new LoginResponseVo(token);
            objectResponseVo.setData(loginResponseVo);
            objectResponseVo.setCode(200);
            objectResponseVo.setMessage("登录成功");
            return objectResponseVo;*/
            com.ybuse.schoolbackend.user.domain.dto.User user1 = new com.ybuse.schoolbackend.user.domain.dto.User();
            user1.setUser(user);
            String token = TokenUtil.getAccessToken(user1);
            LoginResponseDto loginResponseDto = new LoginResponseDto(token);
            return CommonResult.success(loginResponseDto).setMessage("登录成功");
        }
        return objectCommonResult;
    }

    @Override
    public CommonResult<Object> logout(String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            User user = authentication.getPrincipal() instanceof User ? (User) authentication.getPrincipal() : null;
            if (user != null) {
                SecurityContextHolder.getContext().setAuthentication(null);
                long expirationInterval = TokenUtil.getExpirationInterval(token);
                TokenBlacklistUtil.addBlacklist(user.getUsername(), token, expirationInterval > 0 ? expirationInterval : 0);
                return CommonResult.success(null).setMessage("退出成功");
            }
        }
        return CommonResult.error("意外的错误");
    }

    @Override
    public CommonResult<UserInfoVo> getUserInfo(String token) {
        Map<String, Object> json = TokenUtil.parseToken(token);
        String username = String.valueOf(json.get(UserUtil.USER_NAME));
        JSONArray rolesJson;
        if (json.get(UserUtil.USER_ROLES) instanceof cn.hutool.json.JSONArray roles) {
            rolesJson = (JSONArray) JSON.parse(roles.toString());
        } else {
            rolesJson = (JSONArray) json.get(UserUtil.USER_ROLES);
        }
        CommonResult<UserInfoVo> objectCommonResult = new CommonResult<>();
        List<String> roles = new ArrayList<>();
        for (Object role : rolesJson) {
            JSONObject r = (JSONObject) role;
            /*roles.add(String.valueOf(r.get("authority")));*/
            // 适配新role枚举
            val code = RoleEnum.valueOf(Integer.parseInt(r.get("code").toString()));
            if (Objects.nonNull(code)) {
                roles.add("ROLE_" + code.name());
            }
        }
        UserInfoVo userInfoVo = new UserInfoVo(username, roles, null, null);
        objectCommonResult.setData(userInfoVo);
        objectCommonResult.setStatus(200);
        objectCommonResult.setMessage("获取用户信息成功");
        return objectCommonResult;
    }

    @Override
    public CommonResult<List<com.ybuse.schoolbackend.scoresys.domain.dto.User>> getStudentList(String key, String classId) {
        LambdaQueryWrapper<UserAccount> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();

        val classNameLambdaQueryWrapper = new LambdaQueryWrapper<ClassName>();
        classNameLambdaQueryWrapper.eq(StringUtils.isNotBlank(classId), ClassName::getClassName, classId);
        ClassName className = classMapper.selectOne(classNameLambdaQueryWrapper);
        objectLambdaQueryWrapper.eq(UserAccount::getId, className.getId())
                .like(StringUtils.isNotBlank(key), UserAccount::getUaAccount, key);
        val list = userAccountDao.selectList(objectLambdaQueryWrapper).stream().map(u -> {
            val user = new com.ybuse.schoolbackend.scoresys.domain.dto.User();
            user.setType(Objects.requireNonNull(RoleEnum.valueOf((int) u.getUaType())).name());
            user.setUsername(u.getUaAccount());
            return user;
        }).toList();
        return CommonResult.success(list);
    }

    @Autowired
    public void setUserAccountDao(IUserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }

    @Autowired
    public void setClassMapper(IClassMapper classMapper) {
        this.classMapper = classMapper;
    }
}
