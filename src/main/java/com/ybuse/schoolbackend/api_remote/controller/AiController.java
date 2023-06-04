package com.ybuse.schoolbackend.api_remote.controller;

import com.ybuse.schoolbackend.api_remote.service.IAiService;
import com.ybuse.schoolbackend.core.ApiV1Controller;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
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
    public CommonResult<Object> resolution(MultipartFile file) {

        Map<String, Object> result = new HashMap<>(16);
        result.put("url", aiService.superResolutionReconstruction(file));
        return CommonResult.success(result);
    }

    public AiController(IAiService aiService) {
        this.aiService = aiService;
    }
}
