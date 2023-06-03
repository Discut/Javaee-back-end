package com.ybuse.schoolbackend.scoresys.controller;

import com.ybuse.schoolbackend.core.ApiV1Controller;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import com.ybuse.schoolbackend.public_enum.RoleEnum;
import com.ybuse.schoolbackend.scoresys.domain.vo.DeductScoreVo;
import com.ybuse.schoolbackend.scoresys.service.DeductScoreFactory;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Discut
 */
@PrintLog(
        methodType = MethodType.HTTP_UP
)
@Tag(name = "SubjectScoreController", description = "用于操作分数的api", externalDocs = @ExternalDocumentation(description = "Swagger3(OpenAPI)常用注解参考", url = "https://blog.csdn.net/qq_35425070/article/details/105347336"))
@ApiV1Controller("/score")
public class SubjectScoreController {
    private String teacher = RoleEnum.TEACHER.getCode().toString();


    @Operation(description = "扣分")
//    @PreAuthorize("hasAuthority(T(com.ybuse.schoolbackend.public_enum.RoleEnum).TEACHER.code) or " +
//            "hasAuthority(T(com.ybuse.schoolbackend.public_enum.RoleEnum).ADMIN.code) or " +
//            "hasAuthority(T(com.ybuse.schoolbackend.public_enum.RoleEnum).STUDENT.code)")// 权限验证
    @PostMapping("/deduct/{type}")
    public CommonResult<Object> deduct(@PathVariable String type, @RequestBody DeductScoreVo deductScoreVo) {
        DeductScoreFactory.getInstance().operation(type, deductScoreVo);
        return CommonResult.success(null);
    }

}
