package com.ybuse.schoolbackend.user.service.itfc;


import com.ybuse.schoolbackend.core.domain.vo.ResponseVo;
import com.ybuse.schoolbackend.user.domain.vo.LoginResponseVo;
import com.ybuse.schoolbackend.user.domain.vo.UserInfoVo;

public interface IUserService {
    ResponseVo<LoginResponseVo> login(String username, String password);

    ResponseVo<Object> logout(String token);

    ResponseVo<UserInfoVo> getUserInfo(String token);
}
