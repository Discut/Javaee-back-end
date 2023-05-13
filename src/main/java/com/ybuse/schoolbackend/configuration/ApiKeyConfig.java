package com.ybuse.schoolbackend.configuration;

import com.ybuse.schoolbackend.core.apisecurity.ISubjectProvider;
import com.ybuse.schoolbackend.utils.UserUtil;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * api-key 子系统配置类
 */
@SpringBootConfiguration
public class ApiKeyConfig {
    @Bean
    public ISubjectProvider subjectProvider() {
        return new ISubjectProvider() {
            @Override
            public String getSubject() {
                return UserUtil.getUserName();
            }
        };
    }
}
