package com.ybuse.schoolbackend.user.controller;

import com.ybuse.schoolbackend.core.aop.annotation.LogMethod;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.user.domain.dto.LoginDto;
import com.ybuse.schoolbackend.user.domain.vo.UserInfoVo;
import com.ybuse.schoolbackend.user.service.itfc.IUserService;
import com.ybuse.schoolbackend.utils.TokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    private IUserService service;

    @Autowired
    public void setService(IUserService service) {
        this.service = service;
    }

    @LogMethod
    @PostMapping("/logout")
    public CommonResult<Object> logout(HttpServletRequest request) {
        String token = TokenUtil.getTokenFor(request);
        return service.logout(token);
    }

    @LogMethod
    @PostMapping("/info")
    public CommonResult<UserInfoVo> info(@RequestBody Map<String, Object> json) {
        Object token = json.get("token");
        return service.getUserInfo(token.toString());
    }

    @LogMethod
    @PostMapping("/test")
    @Operation(summary = "测试")
    public CommonResult<LoginDto> test(@RequestBody LoginDto loginDto) {
        return CommonResult.success(loginDto);
    }

}
