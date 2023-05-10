package com.ybuse.schoolbackend.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo {
    private String name;
    private List<String> roles;
    private String avatar;
    private String introduction;
}
