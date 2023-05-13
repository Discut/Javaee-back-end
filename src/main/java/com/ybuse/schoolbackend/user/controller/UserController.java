package com.ybuse.schoolbackend.user.controller;

import com.ybuse.schoolbackend.core.ApiV1Controller;
import com.ybuse.schoolbackend.core.apisecurity.annotation.EnableApiSecurity;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import com.ybuse.schoolbackend.user.domain.dto.LoginDto;
import com.ybuse.schoolbackend.user.domain.vo.UserInfoVo;
import com.ybuse.schoolbackend.user.service.itfc.IUserService;
import com.ybuse.schoolbackend.utils.TokenUtil;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@PrintLog
@Tag(name = "用户主体操作API", description = "用于用户操作的API", externalDocs = @ExternalDocumentation(description = "Swagger3(OpenAPI)常用注解参考", url = "https://blog.csdn.net/qq_35425070/article/details/105347336"))
@ApiV1Controller("/user")
public class UserController {

    private IUserService service;

    @Autowired
    public void setService(IUserService service) {
        this.service = service;
    }


    @PostMapping("/logout")
    public CommonResult<Object> logout(HttpServletRequest request) {
        String token = TokenUtil.getTokenFor(request);
        return service.logout(token);
    }


    @EnableApiSecurity
    @PostMapping("/info")
    public CommonResult<UserInfoVo> info(@RequestBody Map<String, Object> json) {
        Object token = json.get("token");
        return service.getUserInfo(token.toString());
    }


    @PostMapping("/test")
    @Operation(summary = "测试")
    public CommonResult<LoginDto> test(@RequestBody LoginDto loginDto) {
        return CommonResult.success(loginDto);
    }

}
