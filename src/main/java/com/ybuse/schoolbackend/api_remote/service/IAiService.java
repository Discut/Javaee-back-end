package com.ybuse.schoolbackend.api_remote.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybuse.schoolbackend.api_remote.po.ImagePo;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 @Author: hyj
 @Date: 2023/6/3
 @Title:
 * ---------
 *  Description:
 *
 */
@PrintLog(
        methodType = MethodType.DB
)
public interface IAiService extends IService<ImagePo> {



    /**
     * 超分辨率重构
     * @param photo 图片
     * @return 图片流
     */
    List<String> superResolutionReconstruction(MultipartFile photo);

    /**
     * @param photo
     * @return
     */
    String uploadOriginImage(MultipartFile photo);

    /**
     * 学生识别
     * @param photo 图片
     * @return 上云地址
     */
    String personIdentification(InputStream photo);

}
