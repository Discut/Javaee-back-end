package com.ybuse.schoolbackend.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.ybuse.schoolbackend.core.annotation.aop.LogMethod;
import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serial;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * token黑名单工具类
 *
 * @author Discut
 */
public class TokenBlacklistUtil {
    private static final String BLACKLIST_KEY = "blacklist:";

    /**
     * token是否存在于黑名单
     *
     * @param key   用户名
     * @param token token
     * @return 是否存在于黑名单
     */
    @LogMethod
    public static boolean isBlacklist(String key, String token) {
        RedisTemplate<String, Object> bean = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        if (Objects.isNull(bean))
            return false;
        ValueOperations<String, Object> stringObjectValueOperations = bean.opsForValue();
        String s = BLACKLIST_KEY + key;
        if (stringObjectValueOperations.get(s) instanceof UnusedToken unusedToken)
            return unusedToken.token.equals(TokenUtil.spliceToken(token));
        else
            return false;
    }

    /**
     * 添加token到黑名单
     *
     * @param key     用户名
     * @param token   token
     * @param timeout token过期时间
     */
    public static void addBlacklist(String key, String token, long timeout) {
        RedisTemplate<String, Object> bean = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        UnusedToken unusedToken = new UnusedToken();
        unusedToken.date = SimpleDateFormat.getDateTimeInstance().format(System.currentTimeMillis());
        unusedToken.token = token;
        //RedisTemplate<String, Object> bean = BeanGetterUtil.getBean("redisTemplate", RedisTemplate.class);
        if (Objects.isNull(bean)) {
            return;
        }
        ValueOperations<String, Object> stringObjectValueOperations = bean.opsForValue();
        String s = BLACKLIST_KEY + key;
        Object o = stringObjectValueOperations.get(s);
        if (Objects.isNull(o)) {
            stringObjectValueOperations.set(s, unusedToken, timeout, TimeUnit.MILLISECONDS);
        }
    }

    @Data
    public static class UnusedToken implements java.io.Serializable {
        @Serial
        private static final long serialVersionUID = 1L;


        private String token;
        private String date;
    }
}
