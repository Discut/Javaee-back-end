package com.ybuse.schoolbackend.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ybuse.schoolbackend.public_enum.RoleEnum;
import com.ybuse.schoolbackend.user.dao.IUserAccountDao;
import com.ybuse.schoolbackend.user.domain.dto.CustomUserDetails;
import com.ybuse.schoolbackend.user.domain.po.Role;
import com.ybuse.schoolbackend.user.domain.po.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("customUserDetailsService")
public class NewCustomUserDetailsService implements UserDetailsService {

    private IUserAccountDao userAccountDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<UserAccount> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(username), UserAccount::getUaAccount, username);
        UserAccount userAccount = userAccountDao.selectOne(wrapper);
        if (Objects.isNull(userAccount)) {
            return new CustomUserDetails(username, null, null);
        }
        RoleEnum roleEnum = RoleEnum.valueOf((int) userAccount.getUaType());
        if (Objects.isNull(roleEnum)) {
            return new CustomUserDetails(username, null, null);
        }

        List<Role> roles = new ArrayList<>(1);
        Role role = new Role();
        role.setCode(String.valueOf(roleEnum.getCode()));
        role.setTitle(roleEnum.getDesc());
        role.setId(roleEnum.getCode());
        roles.add(role);

        return new CustomUserDetails(userAccount.getUaAccount(),
                userAccount.getUaPassword(),
                true,
                true, true, true,
                roles);
    }

    @Autowired
    public void setUserAccountDao(IUserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }
}
