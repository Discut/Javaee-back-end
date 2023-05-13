package com.ybuse.schoolbackend.core.controller.security;

import org.springframework.context.annotation.Bean;

/**
 * api key 系统配置类适配器
 */
public interface ApiKeyConfigAdapter {
    @Bean
    ISubjectProvider subjectProvider();
}
