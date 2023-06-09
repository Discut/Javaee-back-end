package com.ybuse.schoolbackend.api_remote.controller;

import com.ybuse.schoolbackend.api_remote.service.IAiService;
import com.ybuse.schoolbackend.core.ApiV1Controller;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import io.swagger.v3.oas.annotations.Operation;
import lombok.val;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: hyj
 * @Date: 2023/6/3
 * @Title: ---------
 * Description:
 */
@PrintLog(
        methodType = MethodType.HTTP_UP,
        tags = {"图片上传Controller"}
)
@ApiV1Controller("/remote")
public class AiController {

    private final IAiService aiService;

    @PostMapping("/resolution")
    @Operation(summary = "超分辨重构并上云及落盘")
    public CommonResult<Object> resolution( @RequestParam("wangeditor-uploaded-image") MultipartFile wangeditorUploadedImage) {

        Map<String, Object> result = new HashMap<>(16);
        List<String> urls = aiService.superResolutionReconstruction(wangeditorUploadedImage);
        // 未清晰化图片地址
        //  清晰化图片地址
        result.put("originalUrl", urls.get(0));
        result.put("resolutionUrl", urls.get(1));
        return CommonResult.success(result);
    }

    public AiController(IAiService aiService) {
        this.aiService = aiService;
    }
}
