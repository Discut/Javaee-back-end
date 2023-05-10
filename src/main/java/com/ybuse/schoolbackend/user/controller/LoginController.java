package com.ybuse.schoolbackend.user.controller;

import com.ybuse.schoolbackend.core.annotation.ApiV1Controller;
import com.ybuse.schoolbackend.core.aop.annotation.LogMethod;
import com.ybuse.schoolbackend.core.domain.vo.ResponseVo;
import com.ybuse.schoolbackend.user.domain.vo.LoginVo;
import com.ybuse.schoolbackend.user.domain.vo.LoginResponseVo;
import com.ybuse.schoolbackend.user.domain.vo.UserInfoVo;
import com.ybuse.schoolbackend.user.service.itfc.IUserService;
import com.ybuse.schoolbackend.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@ApiV1Controller("/user")
public class LoginController {

    private IUserService service;

    @Autowired
    public void setService(IUserService service) {
        this.service = service;
    }

    @LogMethod
    @PostMapping("/login")
    public ResponseVo<LoginResponseVo> login(@RequestBody LoginVo loginVo) {
        return service.login(loginVo.getUsername(), loginVo.getPassword());
    }

    @LogMethod
    @PostMapping("/logout")
    public ResponseVo<Object> logout(HttpServletRequest request) {
        String token = TokenUtil.getTokenFor(request);
        return service.logout(token);
    }

    @LogMethod
    @PostMapping("/info")
    public ResponseVo<UserInfoVo> info(@RequestBody Map<String, Object> json) {
        Object token = json.get("token");
        return service.getUserInfo(token.toString());
    }

    @LogMethod
    @PostMapping("/test")
    public ResponseVo<LoginVo> test(@RequestBody LoginVo loginVo) {
        return ResponseVo.success(loginVo);
    }
}
