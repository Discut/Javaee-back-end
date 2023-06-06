package com.ybuse.schoolbackend.active_manager.controller;

import com.ybuse.schoolbackend.active_manager.domain.po.ActiveManagerPo;
import com.ybuse.schoolbackend.active_manager.domain.vo.ActiveManagerVo;
import com.ybuse.schoolbackend.active_manager.service.IActiveManagerService;
import com.ybuse.schoolbackend.class_and_other.domain.po.ClassAndOtherPo;
import com.ybuse.schoolbackend.class_and_other.service.IClassAndOtherService;
import com.ybuse.schoolbackend.class_name.service.IClassNameService;
import com.ybuse.schoolbackend.core.ApiV1Controller;
import com.ybuse.schoolbackend.core.CustomException;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import com.ybuse.schoolbackend.utils.ExceptionUtil;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    private final IClassNameService classNameService;

    public ActiveManagerController(IActiveManagerService activeManagerService, IClassAndOtherService classAndOtherService, IClassNameService classNameService) {
        this.activeManagerService = activeManagerService;
        this.classAndOtherService = classAndOtherService;
        this.classNameService = classNameService;
    }


    @PostMapping("/put")
    @Operation(summary = "create active")
    public CommonResult<Object> addActive(@RequestBody ActiveManagerVo activeManagerVo) {
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

    @DeleteMapping("/delete/{activeId}")
    @Operation(summary = "delete active")
    public CommonResult<Object> deleteActive(@PathVariable("activeId") int activeId) {

        ExceptionUtil.isTrue(!activeManagerService.removeById(activeId),"删除失败");
        int i = classAndOtherService.deleteById(activeId);
        return CommonResult.success("ok-"+i);

    }

    @PutMapping("/modify")
    @Operation(summary = "update active")
    public CommonResult<Object> updateActive(@RequestBody ActiveManagerVo activeManagerVo) {

        ActiveManagerPo activeManagerPo = new ActiveManagerPo();
        activeManagerPo.setAmContent(activeManagerVo.getContent());
        activeManagerPo.setAmEndContent(activeManagerVo.getEndContent());
        val interval = activeManagerVo.getStartTime() + "|" + activeManagerVo.getEndTime();
        activeManagerPo.setAmTimeInterval(interval);
        activeManagerPo.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));

        ExceptionUtil.isTrue(!activeManagerService.updateById(activeManagerPo),"删除失败");

        return CommonResult.success("ok");
    }

    @GetMapping("/list/{className}")
    @Operation(summary = "query active by (classname)")
    public CommonResult<Object> queryActive(@PathVariable("className") String className) {

        int classId = classNameService.queryByClassName(className);
        List<ActiveManagerPo> allActiveByClassId = classAndOtherService.queryAllActiveByClassId(classId);

        Map<String, Object> result = new HashMap<>(16);
        result.put("classActive", allActiveByClassId);

        return CommonResult.success(result);
    }

}
