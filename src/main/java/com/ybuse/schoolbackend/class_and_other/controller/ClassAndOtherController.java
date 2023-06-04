package com.ybuse.schoolbackend.class_and_other.controller;

import com.ybuse.schoolbackend.class_and_other.domain.po.ClassAndOtherPo;
import com.ybuse.schoolbackend.class_and_other.domain.vo.AmidAndActiveVo;
import com.ybuse.schoolbackend.class_and_other.service.IClassAndOtherService;
import com.ybuse.schoolbackend.core.ApiV1Controller;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


/**
 @Author: hyj
 @Date: 2023/6/4
 @Title:
 * ---------
 *  Description:
 *
 */
@PrintLog(
        methodType = MethodType.HTTP_UP
)
@Tag(name = "ClassController", description = "用于操作class的api", externalDocs = @ExternalDocumentation(description = "Swagger3(OpenAPI)常用注解参考", url = "https://blog.csdn.net/qq_35425070/article/details/105347336"))
@ApiV1Controller("/class_and_other")
public class ClassAndOtherController {

//    @Autowired
//    private IClassAndOtherService classAndOtherService;
//
//    @GetMapping("/add")
//    @Operation(summary = "查询所有class")
//    public CommonResult<Object> addPo(AmidAndActiveVo amidAndActiveVo) {
//
////        ClassAndOtherPo classAndOtherPo = new ClassAndOtherPo();
////        classAndOtherPo.setAmId(amidAndActiveVo.);
////
////        classAndOtherService.add(amidAndActiveVo);
////        return CommonResult.success("ok");
//    }
//
}
