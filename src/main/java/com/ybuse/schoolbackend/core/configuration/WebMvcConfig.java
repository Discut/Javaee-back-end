package com.ybuse.schoolbackend.core.configuration;

import com.ybuse.schoolbackend.core.annotation.controller.ApiV1Controller;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * Help with configuring {@link HandlerMapping} path matching options such as
     * whether to use parsed {@code PathPatterns} or String pattern matching
     * with {@code PathMatcher}, whether to match trailing slashes, and more.
     *
     * @see PathMatchConfigurer
     * @since 4.0.3
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 将扫描到改Annotation的controller路径设置为/api/v1
        configurer.addPathPrefix("/api/v1", method -> method.isAnnotationPresent(ApiV1Controller.class));
        WebMvcConfigurer.super.configurePathMatch(configurer);
    }
}
