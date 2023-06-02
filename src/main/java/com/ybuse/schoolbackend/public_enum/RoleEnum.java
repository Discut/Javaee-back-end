package com.ybuse.schoolbackend.public_enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    ADMIN(1, "管理员"),
    OFFICIAL(2, "新增"),
    TEACHER(3, "教师"),
    STUDENT(4, "学生");

    private final Integer code;
    private final String desc;

    public static RoleEnum valueOf(Integer code) {
        for (RoleEnum roleEnum : values()) {
            if (roleEnum.getCode().equals(code)) {
                return roleEnum;
            }
        }
        return null;
    }


}
