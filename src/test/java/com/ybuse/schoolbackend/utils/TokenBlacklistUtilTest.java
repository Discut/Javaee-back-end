package com.ybuse.schoolbackend.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.Assert.*;

@SpringBootTest
public class TokenBlacklistUtilTest {

    @Autowired
    private RedisTemplate<String, Object> template;

    @Test
    public void isBlacklist() {
        boolean blacklist = TokenBlacklistUtil.isBlacklist("1", "1");
        assertTrue(blacklist);
    }

    @Test
    public void testRedis(){
        template.opsForValue().set("1", "1");
    }
}