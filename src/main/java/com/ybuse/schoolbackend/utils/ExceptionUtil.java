package com.ybuse.schoolbackend.utils;

import com.ybuse.schoolbackend.core.CustomException;

public class ExceptionUtil extends CustomException {
    public ExceptionUtil(String message) {
        super(message);
    }
    public  static void isTrue(Boolean flag,String msg){
        if(flag){
            throw  new CustomException(msg);
        }
    }
}
