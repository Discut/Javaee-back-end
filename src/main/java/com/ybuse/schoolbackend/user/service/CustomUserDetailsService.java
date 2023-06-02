package com.ybuse.schoolbackend.user.service;

import com.ybuse.schoolbackend.user.dao.UserRoleDao;
import com.ybuse.schoolbackend.user.domain.dto.CustomUserDetails;
import com.ybuse.schoolbackend.user.domain.dto.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//@Service("customUserDetailsService")
@Deprecated
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRoleDao userRoleDao;

    public CustomUserDetailsService(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRoleDao.findRolesByUsername(username);
        if (user == null) {
            return new CustomUserDetails(username, null, null);
        }


/*        user.setRoles(
                user.getRoles().stream()
                        .peek(role -> {
                            if (role.getCode().equals("ACTIVITI_USER")) {
                                role.setCode("ROLE_ACTIVITI_USER");
                            }
                        }).collect(Collectors.toList()));*/

        return new CustomUserDetails(user.getUsername(),
                user.getPassword(),
                user.getEnable() == 1,
                true, true, true,
                user.getRoles());
    }
}
