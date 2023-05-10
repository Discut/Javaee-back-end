package com.ybuse.schoolbackend.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseVo {
    private String token;
}
