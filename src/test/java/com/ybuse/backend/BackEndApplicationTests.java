package com.ybuse.backend;

import cn.hutool.extra.spring.SpringUtil;
import com.ybuse.schoolbackend.Application;
import jakarta.annotation.Resource;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(classes = Application.class)
public class BackEndApplicationTests {

    @Test
    public void contextLoads() {
        LoggerFactory.getLogger(BackEndApplicationTests.class).info("hello");
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Test
    public void passwordTest(){

        //SpringUtil.getBean(BCryptPasswordEncoder.class)
        val encode = passwordEncoder.encode("1");

        System.out.println(encode);
    }

}
