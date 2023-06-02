package com.ybuse.schoolbackend.core.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MethodType {
    UNKNOWN("未知"),
    DB("数据库操作"),
    SERVICE("服务调用"),
    HTTP_UP("上游调用当前服务器api"),
    HTTP_DOWN("当前服务器调用下游api"),
    ;
    private final String dec;
}
