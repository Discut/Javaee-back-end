package com.ybuse.schoolbackend.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ybuse.schoolbackend.core.domain.vo.ResponseVo;
import com.ybuse.schoolbackend.user.domain.vo.LoginResponseVo;
import com.ybuse.schoolbackend.user.domain.vo.UserInfoVo;
import com.ybuse.schoolbackend.user.service.itfc.IUserService;
import com.ybuse.schoolbackend.utils.TokenBlacklistUtil;
import com.ybuse.schoolbackend.utils.TokenUtil;
import com.ybuse.schoolbackend.utils.UserUtil;
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

@Service
public class UserService implements IUserService {
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // 登录
    public ResponseVo<LoginResponseVo> login(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        Object userObject = authenticate.getPrincipal();
        User user = userObject instanceof User ? (User) userObject : null;
        ResponseVo<LoginResponseVo> objectResponseVo = new ResponseVo<>();
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
            LoginResponseVo loginResponseVo = new LoginResponseVo(token);
            return ResponseVo.success(loginResponseVo).setMessage("登录成功");
        }
        return objectResponseVo;
    }

    @Override
    public ResponseVo<Object> logout(String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            User user = authentication.getPrincipal() instanceof User ? (User) authentication.getPrincipal() : null;
            if (user != null) {
                SecurityContextHolder.getContext().setAuthentication(null);
                long expirationInterval = TokenUtil.getExpirationInterval(token);
                TokenBlacklistUtil.addBlacklist(user.getUsername(), token, expirationInterval > 0 ? expirationInterval : 0);
                return ResponseVo.success(null).setMessage("退出成功");
            }
        }
        return ResponseVo.error("意外的错误");
    }

    @Override
    public ResponseVo<UserInfoVo> getUserInfo(String token) {
        Map<String, Object> json = TokenUtil.parseToken(token);
        String username = String.valueOf(json.get(UserUtil.USER_NAME));
        JSONArray rolesJson = null;
        if (json.get(UserUtil.USER_ROLES) instanceof cn.hutool.json.JSONArray roles) {
            rolesJson = (JSONArray) JSON.parse(roles.toString());
        }else {
            rolesJson = (JSONArray) json.get(UserUtil.USER_ROLES);
        }
        ResponseVo<UserInfoVo> objectResponseVo = new ResponseVo<>();
        List<String> roles = new ArrayList<>();
        for (Object role : rolesJson) {
            JSONObject r = (JSONObject) role;
            roles.add(String.valueOf(r.get("authority")));
        }
        UserInfoVo userInfoVo = new UserInfoVo(username, roles, null, null);
        objectResponseVo.setData(userInfoVo);
        objectResponseVo.setStatus(200);
        objectResponseVo.setMessage("获取用户信息成功");
        return objectResponseVo;
    }
}
