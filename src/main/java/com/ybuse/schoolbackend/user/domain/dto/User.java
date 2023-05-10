package com.ybuse.schoolbackend.user.domain.dto;

import com.ybuse.schoolbackend.user.domain.po.Role;
import com.ybuse.schoolbackend.utils.ITokenGetter;
import com.ybuse.schoolbackend.utils.UserUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements ITokenGetter {

    private org.springframework.security.core.userdetails.User user;
    private String username;
    private String password;
    private String title;
    private List<Role> roles;
    private int enable;

    @Override
    public Map<String, Object> getTokenContains() {
        Map<String, Object> map = new HashMap<>();
        map.put(UserUtil.USER_NAME, user.getUsername());
        map.put(UserUtil.USER_ROLES, user.getAuthorities());
        map.put(UserUtil.USER_ENABLE, user.isEnabled());
        return map;
    }
}
