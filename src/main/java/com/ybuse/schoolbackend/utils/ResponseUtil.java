package com.ybuse.schoolbackend.utils;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ResponseUtil {

    public static void returnJson(HttpServletResponse response, String json) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
        response.getWriter().flush();
    }

    public static void returnJson(HttpServletResponse response, Object object) throws IOException {
        String json = JSON.toJSONString(object);
        returnJson(response, json);
    }
}
