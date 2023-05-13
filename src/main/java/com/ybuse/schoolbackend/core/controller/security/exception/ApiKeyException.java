package com.ybuse.schoolbackend.core.controller.security.exception;

import com.ybuse.schoolbackend.core.CustomException;

/**
 * 错误类型
 */
public class ApiKeyException extends CustomException {
    public ApiKeyException(String apiKeyIsEmpty) {
        super(apiKeyIsEmpty);
    }
}
