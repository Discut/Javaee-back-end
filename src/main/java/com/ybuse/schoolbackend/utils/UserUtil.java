package com.ybuse.schoolbackend.utils;

import org.springframework.security.core.userdetails.User;

import java.util.HashMap;
import java.util.Map;

public class UserUtil {
    public static final String USER_NAME = "username";
    public static final String USER_ROLES = "roles";
    public static final String USER_ENABLE = "enable";
    public static final String USER_VALID_TIME = "exp";


    @Deprecated
    public static String getToken(User user, String secret, long expMillis) {
        expMillis = expMillis > 0 ? expMillis : 24L * 60 * 60 * 1000;
        if (user != null) {
            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put(USER_NAME, user.getUsername());
            claims.put(USER_ROLES, user.getAuthorities());
            claims.put(USER_ENABLE, user.isEnabled());
            claims.put(USER_VALID_TIME, System.currentTimeMillis() + expMillis);

            // 生成token
            return TokenUtil.getAccessToken( claims);
        }
        return null;
    }

}
