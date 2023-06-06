package com.ybuse.schoolbackend.common_score.controller;

import com.ybuse.schoolbackend.class_and_other.domain.po.ClassAndOtherPo;
import com.ybuse.schoolbackend.class_and_other.service.IClassAndOtherService;
import com.ybuse.schoolbackend.common_score.domain.po.CommonScorePo;
import com.ybuse.schoolbackend.common_score.domain.vo.CommonScoreVo;
import com.ybuse.schoolbackend.common_score.service.ICommonScoreService;
import com.ybuse.schoolbackend.core.ApiV1Controller;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Tag(name = "CommonScoreController", description = "CommonScoreController", externalDocs = @ExternalDocumentation(description = "Swagger3(OpenAPI)常用注解参考", url = "https://blog.csdn.net/qq_35425070/article/details/105347336"))
@ApiV1Controller("/common_score")
public class CommonScoreController {

    private final ICommonScoreService commonScoreService;

    private final IClassAndOtherService classAndOtherService;

    public CommonScoreController(ICommonScoreService commonScoreService, IClassAndOtherService classAndOtherService) {
        this.commonScoreService = commonScoreService;
        this.classAndOtherService = classAndOtherService;
    }

    @PostMapping("/add")
    @Operation(summary = "添加操行分")
    public CommonResult<Object> addScore(@RequestBody CommonScoreVo commonScoreVo) {

        System.out.println("here");
        CommonScorePo commonScorePo = new CommonScorePo();
        commonScorePo.setCsScore(commonScoreVo.getScore());
        commonScorePo.setCsName(commonScoreVo.getStudentName());
        commonScorePo.setCsTypeId(commonScoreVo.getType());

        int csId = commonScoreService.add(commonScorePo);

        ClassAndOtherPo classAndOtherPo = new ClassAndOtherPo();
        classAndOtherPo.setCsId(csId);
        classAndOtherPo.setClassNo(commonScoreVo.getClassName());

        int returnId = classAndOtherService.add(classAndOtherPo);

        return CommonResult.success(returnId);
    }





}
