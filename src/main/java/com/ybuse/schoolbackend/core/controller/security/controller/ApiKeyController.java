package com.ybuse.schoolbackend.core.controller.security.controller;


import com.ybuse.schoolbackend.core.controller.annotation.ApiV1Controller;
import com.ybuse.schoolbackend.core.controller.security.ISubjectProvider;
import com.ybuse.schoolbackend.core.controller.security.dto.ApiKeyDto;
import com.ybuse.schoolbackend.core.controller.security.util.ApiKeyUtil;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@PrintLog({"api key 获取"})
@Tag(description = "客户端访问重要接口时，传入该接口路径到此链接请求一个apikey。当调用该重要接口时，携带apikey。", name = "获取apiKey")
@ApiV1Controller("/apiKey")
public class ApiKeyController {

    private ISubjectProvider usernameGetter;

    @Operation(summary = "获取apiKey", description = "使用此接口获取apikey")
    @Parameter(name = "path", description = "重要接口路径。注意：该路径请不要携带路径版本。")
    @GetMapping("/one")
    public CommonResult<ApiKeyDto> getApiKey(@RequestParam("path") String path) {
        return CommonResult.success(new ApiKeyDto(ApiKeyUtil.generateAPIKey(path, usernameGetter.getSubject())));
    }

    @Autowired
    public void setUsernameGetter(ISubjectProvider usernameGetter) {
        this.usernameGetter = usernameGetter;
    }
}
