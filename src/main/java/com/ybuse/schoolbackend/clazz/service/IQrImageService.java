package com.ybuse.schoolbackend.clazz.service;

import com.ybuse.schoolbackend.clazz.domain.po.QrImagePo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ybuse.schoolbackend.clazz.domain.vo.QrImageVo;

/**
* @author Administrator
* @description 针对表【qr_image】的数据库操作Service
* @createDate 2023-06-03 11:39:47
*/
public interface IQrImageService extends IService<QrImagePo> {

    /**
     * 生成二维码
     * @param qrImageVo 二维码信息对象
     * @return 远程地址 - 字符串
     */
    String generateQr(QrImageVo qrImageVo);
}
