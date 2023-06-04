package com.ybuse.schoolbackend.active_manager.controller;

import com.ybuse.schoolbackend.active_manager.domain.po.ActiveManagerPo;
import com.ybuse.schoolbackend.active_manager.domain.vo.ActiveManagerVo;
import com.ybuse.schoolbackend.active_manager.service.IActiveManagerService;
import com.ybuse.schoolbackend.class_and_other.domain.po.ClassAndOtherPo;
import com.ybuse.schoolbackend.class_and_other.service.IClassAndOtherService;
import com.ybuse.schoolbackend.core.ApiV1Controller;
import com.ybuse.schoolbackend.core.CustomException;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
@Tag(name = "ActiveManagerController", description = "ActiveManagerController", externalDocs = @ExternalDocumentation(description = "Swagger3(OpenAPI)常用注解参考", url = "https://blog.csdn.net/qq_35425070/article/details/105347336"))
@ApiV1Controller("/activity")
public class ActiveManagerController {

    private final IActiveManagerService activeManagerService;
    private final IClassAndOtherService classAndOtherService;

    public ActiveManagerController(IActiveManagerService activeManagerService, IClassAndOtherService classAndOtherService) {
        this.activeManagerService = activeManagerService;
        this.classAndOtherService = classAndOtherService;
    }


    @PostMapping("/put")
    @Operation(summary = "添加活动")
    public CommonResult<Object> addPo(@RequestBody ActiveManagerVo activeManagerVo) {
        try{
            ActiveManagerPo activeManagerPo = new ActiveManagerPo();
            activeManagerPo.setAmName(activeManagerVo.getTitle());
            activeManagerPo.setAmContent(activeManagerVo.getContent());
            activeManagerPo.setAmEndContent("");
            val interval = activeManagerVo.getStartTime() + "|" + activeManagerVo.getEndTime();
            activeManagerPo.setAmTimeInterval(interval);
            activeManagerPo.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));

            int amId = activeManagerService.add(activeManagerPo);

            ClassAndOtherPo classAndOtherPo = new ClassAndOtherPo();
            classAndOtherPo.setAmId(amId);
            classAndOtherPo.setClassNo(activeManagerVo.getSubject());
            classAndOtherPo.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));

            // 返回中间表主键
            int id = classAndOtherService.add(classAndOtherPo);
            return CommonResult.success(id);

        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }
}
