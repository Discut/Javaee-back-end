package com.ybuse.schoolbackend.utils;

import java.util.Map;

/**
 * token内容获取器
 */
public interface ITokenGetter {
    Map<String, Object> getTokenContains();
}