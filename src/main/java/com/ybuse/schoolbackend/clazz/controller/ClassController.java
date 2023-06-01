package com.ybuse.schoolbackend.clazz.controller;

import com.ybuse.schoolbackend.clazz.service.IClassService;
import com.ybuse.schoolbackend.core.ApiV1Controller;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@PrintLog(
        methodType = MethodType.HTTP_UP
)
@Tag(name = "ClassController", description = "用于操作class的api", externalDocs = @ExternalDocumentation(description = "Swagger3(OpenAPI)常用注解参考", url = "https://blog.csdn.net/qq_35425070/article/details/105347336"))
@ApiV1Controller("/class")
public class ClassController {

    private IClassService classService;

    @GetMapping("/list")
    @Operation(summary = "查询所有class")
    public CommonResult<Object> list() {
        val maps = classService.findAll().stream().map(clazz -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", clazz.getId());
            map.put("name", clazz.getClassName());
            return map;
        }).toList();
        return CommonResult.success(maps);
    }

    @Autowired
    public void setClassService(IClassService classService) {
        this.classService = classService;
    }
}
