package com.ybuse.schoolbackend.clazz.service;

import com.ybuse.schoolbackend.class_name.domain.po.ClassNamePo;
import com.ybuse.schoolbackend.class_name.service.IClassNameService;
import com.ybuse.schoolbackend.clazz.domain.vo.QrImageVo;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 29628
 */
@PrintLog(
        methodType = MethodType.SERVICE
)
@Service
public class QrGenerate {
    private final IQrImageService qrImageService;

    private final IClassNameService classNameService;

    public QrGenerate(IQrImageService qrImageService, IClassNameService classNameService) {
        this.qrImageService = qrImageService;
        this.classNameService = classNameService;
    }

    @Async("asyncIoExecutor")
    public void paresImage(QrImageVo qrImageVo) {
        Map<String, Object> result = new HashMap<>();
        String url = qrImageService.generateQr(qrImageVo);
        result.put("url", url);
        updateDao(url, qrImageVo.getClassId());
    }

    @PrintLog(
            methodType = MethodType.SERVICE,
            tags = "QrCode 异步操作"
    )
    @Async("asyncIoExecutor")
    public void updateDao(String url, String className) {
        int classId = classNameService.queryByClassName(className);
        ClassNamePo classNamePo = new ClassNamePo();
        classNamePo.setId(classId);
        classNamePo.setQrUuid(url);
        classNameService.updateById(classNamePo);
    }
}
