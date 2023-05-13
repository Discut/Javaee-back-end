package com.ybuse.schoolbackend.core.apisecurity.util;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.ybuse.schoolbackend.core.apisecurity.ISubjectProvider;
import com.ybuse.schoolbackend.core.apisecurity.exception.ApiKeyException;
import com.ybuse.schoolbackend.core.apisecurity.properties.ApiKeyProperties;
import lombok.NonNull;
import lombok.val;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 工具类<br>
 * 验证、生成 api key
 */
public class ApiKeyUtil {

    /**
     * 消费签发的api key
     *
     * @param apiKey api key
     */
    public static void consumeApikey(String apiKey) {
        RedisTemplate<String, Object> redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        if (isValid(apiKey)) {
            redisTemplate.delete(getKeyName());
        }
    }

    /**
     * 验证api key是否被消费
     *
     * @param apiKey api key
     * @return 是否被消费
     */
    public static boolean isValid(String apiKey) {
        RedisTemplate<String, Object> redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
        // 存在则未被消费
        return Objects.equals(stringObjectValueOperations.get(getKeyName()), apiKey);
    }

    /**
     * 生成api key
     *
     * @param path key对应的路径
     * @return api key
     */
    public static String generateAPIKey(@NonNull String path, @NonNull String subject) {
        RedisTemplate<String, Object> redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        ApiKeyProperties bean = SpringUtil.getBean(ApiKeyProperties.class);
        JWT jwt = JWT.create();
        jwt.setIssuedAt(new Date());
        jwt.setExpiresAt(new Date(System.currentTimeMillis() + bean.getExpireTime()));
        jwt.setIssuer("systemApiAdmin");
        jwt.setPayload("user", subject);
        jwt.setPayload("path", path);
        jwt.setSigner(JWTSignerUtil.hs256(bean.getSecret().getBytes()));
        String apikey = jwt.sign();
        val stringObjectValueOperations = redisTemplate.opsForValue();
        stringObjectValueOperations.set(getKeyName(), apikey, bean.getExpireTime(), TimeUnit.MILLISECONDS);
        return apikey;
    }

    /**
     * 验证apikey
     *
     * @param apiKey api key
     * @throws RuntimeException api key不合法
     */
    public static void validateApiKey(String apiKey, String subject) throws RuntimeException {
        try {
            ApiKeyProperties bean = SpringUtil.getBean(ApiKeyProperties.class);
            JWTUtil.verify(apiKey, JWTSignerUtil.hs256(bean.getSecret().getBytes()));
            JWT jwt = JWTUtil.parseToken(apiKey);
            String user = jwt.getPayload("user").toString();
            if (user == null) {
                throw new ApiKeyException("api key不合法");
            }
            if (!user.equals(subject)) {
                throw new ApiKeyException("错误的使用主体");
            }
            JWTValidator.of(apiKey).validateDate(new Date());
        } catch (Exception e) {
            throw new ApiKeyException(e.getMessage());
        }
    }

    /**
     * 获取在redis中存储键值对时的key值
     *
     * @return key name
     */

    private static String getKeyName() {
        ISubjectProvider subjectProvider = SpringUtil.getBean(ISubjectProvider.class);
        if (subjectProvider == null) {
            throw new RuntimeException("subjectProvider is null");
        }
        return subjectProvider.getSubject() + ".apikey";
    }

    public enum Subject {
        ANONYMITY

    }
}
