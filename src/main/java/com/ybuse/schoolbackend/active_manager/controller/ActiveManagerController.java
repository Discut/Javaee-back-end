package com.ybuse.schoolbackend.active_manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ybuse.schoolbackend.active_manager.ActiveStatusEnum;
import com.ybuse.schoolbackend.active_manager.domain.dto.ActiveDto;
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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: hyj
 * @Date: 2023/6/4
 * @Title: ---------
 * Description:
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
        try {
            ActiveManagerPo activeManagerPo = new ActiveManagerPo();
            activeManagerPo.setAmName(activeManagerVo.getTitle());
            activeManagerPo.setAmContent(activeManagerVo.getContent());
            activeManagerPo.setAmEndContent("");
            val interval = activeManagerVo.getStartTime() + "|" + activeManagerVo.getEndTime();
            activeManagerPo.setAmTimeInterval(interval);
            activeManagerPo.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));

            int amId = activeManagerService.add(activeManagerPo);
            if (amId == 0) {
                throw new CustomException("添加失败");
            }
            ClassAndOtherPo classAndOtherPo = new ClassAndOtherPo();
            classAndOtherPo.setAmId(activeManagerPo.getId());
            classAndOtherPo.setClassNo(String.valueOf(classNameService.queryByClassName(activeManagerVo.getSubject())));
            classAndOtherPo.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));

            // 返回中间表主键
            int id = classAndOtherService.add(classAndOtherPo);
            return CommonResult.success(id);

        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{activeId}")
    @Operation(summary = "delete active")
    public CommonResult<Object> deleteActive(@PathVariable("activeId") int[] activeId) {

        ExceptionUtil.isTrue(!activeManagerService.removeById(activeId), "删除失败");
        Map<String, Object> result = new HashMap<>();
        for (int id : activeId) {
            classAndOtherService.deleteById(id);
            result.put(String.valueOf(id), classAndOtherService.deleteById(id) > 0 ? "success" : "fail");
        }
        return CommonResult.success(result);
    }

    @PutMapping("/modify/{activeId}")
    @Operation(summary = "update active")
    public CommonResult<Object> updateActive(@RequestBody ActiveManagerVo activeManagerVo, @PathVariable("activeId") int activeId) {

        ActiveManagerPo activeManagerPo = new ActiveManagerPo();
        activeManagerPo.setId(activeId);
        activeManagerPo.setAmContent(activeManagerVo.getContent());
        activeManagerPo.setAmEndContent(activeManagerVo.getEndContent());
        val interval = activeManagerVo.getStartTime() + "|" + activeManagerVo.getEndTime();
        activeManagerPo.setAmTimeInterval(interval);
        activeManagerPo.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));

        ExceptionUtil.isTrue(!activeManagerService.updateById(activeManagerPo), "修改失败");

        return CommonResult.success("ok");
    }

    @GetMapping("/list/{className}")
    @Operation(summary = "query active by (classname)")
    public CommonResult<Object> queryActive(@PathVariable("className") String className) {

        int classId = classNameService.queryByClassName(className);
        List<ActiveManagerPo> allActiveByClassId = classAndOtherService.queryAllActiveByClassId(classId);
        val list = allActiveByClassId.stream().map(activeManagerPo -> {
            val startTimeString = activeManagerPo.getAmTimeInterval().split("\\|")[0];
            val endTimeString = activeManagerPo.getAmTimeInterval().split("\\|")[1];
            val startTime = LocalDate.parse(startTimeString);
            val endTime = LocalDate.parse(endTimeString);
            ActiveDto activeDto = new ActiveDto(activeManagerPo);
            activeDto.setId(String.valueOf(activeManagerPo.getId()));
            activeDto.setTitle(activeManagerPo.getAmName());
            activeDto.setContent(activeManagerPo.getAmContent());
            activeDto.setEndContent(activeManagerPo.getAmEndContent());
            activeDto.setStartTime(activeManagerPo.getAmTimeInterval().split("\\|")[0]);
            activeDto.setEndTime(activeManagerPo.getAmTimeInterval().split("\\|")[1]);
            if (endTime.isBefore(startTime)) {
                activeDto.setStatus(ActiveStatusEnum.FINISHED);
            } else if (startTime.isBefore(LocalDate.now()) && endTime.isAfter(LocalDate.now())) {
                activeDto.setStatus(ActiveStatusEnum.ACTING);
            } else if (endTime.isBefore(LocalDate.now())) {
                activeDto.setStatus(ActiveStatusEnum.FINISHED);
            } else {
                activeDto.setStatus(ActiveStatusEnum.RUNNABLE);
            }
            return activeDto;
        }).toList();

        Map<String, Object> result = new HashMap<>(16);
        result.put("classActive", list);

        return CommonResult.success(result);

    }

    @GetMapping("/info/{activeId}")
    @Operation(summary = "query active by (activeId)")
    public CommonResult<ActiveDto> queryActiveById(@PathVariable("activeId") String activeId) {
        if (StringUtils.isBlank(activeId)) {
            CommonResult.error("activeId不能为空");
        }
        ActiveManagerPo activeManagerPo = activeManagerService.getById(Integer.parseInt(activeId));
        val activeDto = new ActiveDto(activeManagerPo);
        return CommonResult.success(activeDto);
    }

}
