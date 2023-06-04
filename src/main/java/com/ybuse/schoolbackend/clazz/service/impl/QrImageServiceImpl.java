package com.ybuse.schoolbackend.clazz.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.clazz.dao.IQrImageMapper;
import com.ybuse.schoolbackend.clazz.domain.po.QrImagePo;
import com.ybuse.schoolbackend.clazz.domain.vo.QrImageVo;
import com.ybuse.schoolbackend.clazz.service.IQrImageService;
import com.ybuse.schoolbackend.utils.ExceptionUtil;
import com.ybuse.schoolbackend.utils.UuidGenerate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @description 针对表【qr_image】的数据库操作Service实现
 * @createDate 2023-06-03 11:39:47
 */
@Service
public class QrImageServiceImpl extends ServiceImpl<IQrImageMapper, QrImagePo> implements IQrImageService {

    @Value("${file-save-path}")
    private String fileSavePath;

    @Value("${client-url}")
    private String clientUrl;

    @Value("${qrLogo-path}")
    private String qrLogo;

    /**
     * 桶域名
     */
    @Value("${kodo_pre_url}")
    private String kodoPreUrl;

    private final IQrImageMapper qrImageMapper;


    /**
     * 生成二维码
     *
     * @param qrImageVo 二维码信息对象
     * @return 是否成功
     */
    @Override
    public String generateQr(QrImageVo qrImageVo) {
        String simpleUuid = IdUtil.simpleUUID();
        QrImagePo qrImagePo = new QrImagePo();
        qrImagePo.setQrTitle(qrImageVo.getTitle());
        qrImagePo.setQrFooter(qrImageVo.getFooter());
        qrImagePo.setQrUuid(simpleUuid);

        List<String> kodoQrImagesUrl;
        kodoQrImagesUrl = UuidGenerate.qrCodeByInfo(qrImagePo, qrLogo, clientUrl, simpleUuid, fileSavePath, kodoPreUrl);
        qrImagePo.setQrContent(kodoQrImagesUrl.get(0));
        qrImagePo.setQrImageUrl(kodoQrImagesUrl.get(1));
        qrImagePo.setCreateTime((new Timestamp(System.currentTimeMillis())));

        System.out.println("here");
        ExceptionUtil.isTrue(qrImageMapper.insert(qrImagePo) < 1, "生成二维码失败");

        return qrImagePo.getQrImageUrl();
    }


    public QrImageServiceImpl(IQrImageMapper qrImageMapper) {
        this.qrImageMapper = qrImageMapper;
    }
}


