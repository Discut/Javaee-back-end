package com.ybuse.schoolbackend.user.controller;

import com.ybuse.schoolbackend.core.ApiV1Controller;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.user.domain.dto.LoginDto;
import com.ybuse.schoolbackend.user.domain.dto.LoginResponseDto;
import com.ybuse.schoolbackend.user.service.itfc.IUserService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "LoginAndRegisterController", description = "公开的用于用户操作的API", externalDocs = @ExternalDocumentation(description = "Swagger3(OpenAPI)常用注解参考", url = "https://blog.csdn.net/qq_35425070/article/details/105347336"))
@ApiV1Controller("/user")
public class LoginAndRegisterController {

    private IUserService service;

    @Autowired
    public void setService(IUserService service) {
        this.service = service;
    }

    @PrintLog
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public CommonResult<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        return service.login(loginDto.getUsername(), loginDto.getPassword());
    }
}
