package com.ybuse.schoolbackend.core.controller.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "存储apikey返回值")
public class ApiKeyDto {
    @Schema(description = "api key，使用JWT构建")
    private String apiKey;
}
