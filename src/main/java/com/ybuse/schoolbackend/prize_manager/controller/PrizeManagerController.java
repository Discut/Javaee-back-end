/*
package com.ybuse.schoolbackend.prize_manager.controller;

import com.ybuse.schoolbackend.core.ApiV1Controller;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import com.ybuse.schoolbackend.prize_manager.domain.po.PrizeManagerPo;
import com.ybuse.schoolbackend.prize_manager.service.IPrizeManagerService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

*/
/**
 @Author: hyj
 @Date: 2023/6/6
 @Title:
 * ---------
 *  Description:
 *
 *//*

@PrintLog(
        methodType = MethodType.HTTP_UP
)
@Tag(name = "PrizeManagerController", description = "用于操作class的api", externalDocs = @ExternalDocumentation(description = "Swagger3(OpenAPI)常用注解参考", url = "https://blog.csdn.net/qq_35425070/article/details/105347336"))

@ApiV1Controller("/prize_manager")
public class PrizeManagerController {


    private final IPrizeManagerService prizeManagerService;

    public PrizeManagerController(IPrizeManagerService prizeManagerService) {
        this.prizeManagerService = prizeManagerService;
    }


    @PostMapping("/post")
    @Operation(summary = "create class")
    public CommonResult<Object> addClass(@RequestBody PrizeManagerPo classNamePo) {
        prizeManagerService.add(classNamePo);
        return CommonResult.success("ok");
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete active")
    public CommonResult<Object> deleteClass(@PathVariable("id") int id) {
        prizeManagerService.removeById(id);
        return CommonResult.success("ok");
    }

    @PutMapping("/put")
    @Operation(summary = "put active")
    public CommonResult<Object> putClass(@RequestBody PrizeManagerPo prizeManagerPo) {
        prizeManagerService.updateById(prizeManagerPo);

        return CommonResult.success("ok");
    }

    @GetMapping("/get")
    @Operation(summary = "get active")
    public CommonResult<Object> getAllClass() {
        val maps = prizeManagerService.findAll().stream().map(classNamePo -> {
            Map<String, Object> map = new HashMap<>(16);
            map.put("id", classNamePo.getId());
            map.put("prizeName", classNamePo.getPmPrizeName());
            return map;
        }).toList();
        System.out.println(maps);
        return CommonResult.success(maps);
    }


}
*/
