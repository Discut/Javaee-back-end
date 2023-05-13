package com.ybuse.schoolbackend.core.controller.security.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 子系统配置类
 */
@Data
@SpringBootConfiguration
@ConfigurationProperties(prefix = "system-config.api-key")
public class ApiKeyProperties {

    private String secret = "qazxswedcvfrtgbnhyuj";

    private Long expireTime = 30 * 60 * 1000L;

    private String headerName = "X-Api-Key";

}
