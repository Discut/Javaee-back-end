package com.ybuse.schoolbackend.api_remote.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybuse.schoolbackend.api_remote.po.ImagePo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 @Author: hyj
 @Date: 2023/6/3
 @Title:
 * ---------
 *  Description:
 *
 */
public interface IAiService extends IService<ImagePo> {



    /**
     * 超分辨率重构
     * @param photo 图片
     * @return 图片流
     */
    String superResolutionReconstruction(MultipartFile photo);

    /**
     * 学生识别
     * @param photo 图片
     * @return 上云地址
     */
    String personIdentification(InputStream photo);

}
