package com.ybuse.schoolbackend.api_remote.utils;

import com.qiniu.util.IOUtils;

import java.io.InputStream;
import java.util.Base64;

/**
 * @Author: hyj
 * @Date: 2023/6/3
 * @Title: 图片转换工具
 * ---------
 * Description:
 */
public class ImagesConvert {

    /**
     * 图片流转换为base64
     * @param inputStream 图片流
     * @return base64
     */
    public static String inputStreamToBase64(InputStream inputStream) {
        try {
            //转换为base64
            byte[] bytes = IOUtils.toByteArray(inputStream);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }
}