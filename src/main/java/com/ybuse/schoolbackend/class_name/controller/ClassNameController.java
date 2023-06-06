package com.ybuse.schoolbackend.class_name.controller;

import com.ybuse.schoolbackend.class_name.domain.po.ClassNamePo;
import com.ybuse.schoolbackend.class_name.service.IClassNameService;
import com.ybuse.schoolbackend.core.ApiV1Controller;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 @Author: hyj
 @Date: 2023/6/6
 @Title:
 * ---------
 *  Description:
 *
 */
@PrintLog(
        methodType = MethodType.HTTP_UP
)
@Tag(name = "ActiveManagerController", description = "ActiveManagerController", externalDocs = @ExternalDocumentation(description = "Swagger3(OpenAPI)常用注解参考", url = "https://blog.csdn.net/qq_35425070/article/details/105347336"))
@ApiV1Controller("/class")
public class ClassNameController {
    /**
     * 服务对象
     */
    private final IClassNameService classNameService;

    public ClassNameController(IClassNameService classNameService) {
        this.classNameService = classNameService;
    }

    @PostMapping("/post")
    @Operation(summary = "create class")
    public CommonResult<Object> addClass(@RequestBody ClassNamePo classNamePo) {
        classNameService.add(classNamePo);
        return CommonResult.success("ok");
    }

    @DeleteMapping("/delete")
    @Operation(summary = "delete active")
    public CommonResult<Object> deleteClass(@RequestBody int id) {
        classNameService.removeById(id);
        return CommonResult.success("ok");
    }

    @PutMapping("/put")
    @Operation(summary = "put active")
    public CommonResult<Object> putClass(@RequestBody ClassNamePo classNamePo) {
        classNameService.updateById(classNamePo);
        return CommonResult.success("ok");
    }

    @GetMapping("/get")
    @Operation(summary = "get active")
    public CommonResult<Object> getAllClass() {
        val maps = classNameService.findAll().stream().map(classNamePo -> {
            Map<String, Object> map = new HashMap<>(16);
            map.put("id", classNamePo.getId());
            map.put("name", classNamePo.getClassName());
            return map;
        }).toList();
        System.out.println(maps);
        return CommonResult.success(maps);
    }
}

