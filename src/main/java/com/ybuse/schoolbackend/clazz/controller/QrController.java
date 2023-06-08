package com.ybuse.schoolbackend.clazz.controller;

import com.ybuse.schoolbackend.clazz.domain.vo.QrImageVo;
import com.ybuse.schoolbackend.clazz.service.QrGenerate;
import com.ybuse.schoolbackend.core.ApiV1Controller;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @Author: hyj
 * @Date: 2023/6/3
 * @Title: QrController
 * ---------
 * Description:
 * 二维码生成
 * http://127.0.0.1:8080/api/v1/class/file/qrcode?title=123&footer=456
 */
@PrintLog(
        methodType = MethodType.HTTP_UP
)
@Tag(name = "ClassController", description = "用于操作class的api", externalDocs = @ExternalDocumentation(description = "Swagger3(OpenAPI)常用注解参考", url = "https://blog.csdn.net/qq_35425070/article/details/105347336"))
@ApiV1Controller("/class")
public class QrController {

    private final QrGenerate generate;

    public QrController(QrGenerate generate) {
        this.generate = generate;
    }


    @PostMapping("/file/qrcode")
    @Operation(summary = "生成二维码")
    public CommonResult<Object> generateQrController(@RequestBody QrImageVo qrImageVo) {
        generate.paresImage(qrImageVo);
        return CommonResult.success(null).setMessage("获取二维码生成任务");
    }
}
