package com.ybuse.schoolbackend.public_enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConductScoreEnum {

    X_B("1", "学生干部-班级卫生"),
    X_L("2", "学生干部-两操"),
    J_W("3", "教师-午间托管"),
    J_K("4", "教师-课后服务");


    private final String type;
    private final String name;

}
