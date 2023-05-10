package com.ybuse.backend;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BackEndApplicationTests {

    @Test
    public void contextLoads() {
        LoggerFactory.getLogger(BackEndApplicationTests.class).info("hello");
    }

}
