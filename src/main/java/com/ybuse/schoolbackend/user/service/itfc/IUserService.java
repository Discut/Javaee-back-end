package com.ybuse.schoolbackend.user.service.itfc;


import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.user.domain.dto.LoginResponseDto;
import com.ybuse.schoolbackend.user.domain.vo.UserInfoVo;

public interface IUserService {
    CommonResult<LoginResponseDto> login(String username, String password);

    CommonResult<Object> logout(String token);

    CommonResult<UserInfoVo> getUserInfo(String token);
}
