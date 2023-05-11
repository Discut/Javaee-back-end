package com.ybuse.schoolbackend.user.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户登录参数")
public class LoginDto {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "密码")
    private String password;
}
