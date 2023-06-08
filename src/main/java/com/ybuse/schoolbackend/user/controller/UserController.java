package com.ybuse.schoolbackend.user.controller;

import com.ybuse.schoolbackend.core.ApiV1Controller;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import com.ybuse.schoolbackend.scoresys.domain.dto.User;
import com.ybuse.schoolbackend.user.domain.dto.LoginDto;
import com.ybuse.schoolbackend.user.domain.vo.UserInfoVo;
import com.ybuse.schoolbackend.user.service.itfc.IUserService;
import com.ybuse.schoolbackend.utils.TokenUtil;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PrintLog(
        methodType = MethodType.HTTP_UP
)
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


    @PostMapping("/me")
    public CommonResult<UserInfoVo> me(@RequestBody TokenVo tokenVo) {
        return service.getUserInfo(tokenVo.getToken());
    }

    @GetMapping("/info/{accountId}")
    @Operation(summary = "获取用户信息")
    public CommonResult<UserInfoDto> info(@PathVariable String accountId) {
        return service.getOtherUserInfo(accountId);
    }

    @PostMapping("/test")
    @Operation(summary = "测试")
    public CommonResult<LoginDto> test(@RequestBody LoginDto loginDto) {
        return CommonResult.success(loginDto);
    }

    @GetMapping("/list/{type}")
    @Operation(summary = "获取不同类型的用户")
    public CommonResult<List<User>> list(@PathVariable String type, @RequestParam String key, @RequestParam String classId) {
        return service.getStudentList(key, classId);
    }

    @Data
    public static class UserInfoDto {
        private String id;
        private String name;
        private String subtitle;
        private List<String> roles;
        private String avatar;
        private String des;
    }

    @Data
    public static class TokenVo {
        private String token;
    }

}
