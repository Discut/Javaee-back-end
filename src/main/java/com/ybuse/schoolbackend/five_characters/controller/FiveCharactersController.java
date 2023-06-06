package com.ybuse.schoolbackend.five_characters.controller;

import com.ybuse.schoolbackend.core.ApiV1Controller;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import com.ybuse.schoolbackend.five_characters.domain.po.FiveCharactersPo;
import com.ybuse.schoolbackend.five_characters.service.IFiveCharactersService;
import com.ybuse.schoolbackend.prize_manager.domain.po.PrizeManagerPo;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
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
@Tag(name = "CommonScoreController", description = "CommonScoreController", externalDocs = @ExternalDocumentation(description = "Swagger3(OpenAPI)常用注解参考", url = "https://blog.csdn.net/qq_35425070/article/details/105347336"))
@ApiV1Controller("/five_characters")
public class FiveCharactersController {

    private final IFiveCharactersService fiveCharactersService;

    public FiveCharactersController(IFiveCharactersService fiveCharactersService) {
        this.fiveCharactersService = fiveCharactersService;
    }

    @PostMapping("/post")
    @Operation(summary = "create class")
    public CommonResult<Object> addClass(@RequestBody FiveCharactersPo fiveCharactersPo) {
        fiveCharactersService.add(fiveCharactersPo);
        return CommonResult.success("ok");
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete active")
    public CommonResult<Object> deleteClass(@PathVariable("id") int id) {
        fiveCharactersService.removeById(id);
        return CommonResult.success("ok");
    }

    @PutMapping("/put")
    @Operation(summary = "put active")
    public CommonResult<Object> putClass(@RequestBody FiveCharactersPo fiveCharactersPo) {
        fiveCharactersService.updateById(fiveCharactersPo);
        return CommonResult.success("ok");
    }

    @GetMapping("/get")
    @Operation(summary = "get active")
    public CommonResult<Object> getAllClass() {
        val maps = fiveCharactersService.findAll().stream().map(classNamePo -> {
            Map<String, Object> map = new HashMap<>(16);
            map.put("id", classNamePo.getId());
            map.put("fcType", classNamePo.getFcType());
            map.put("fcScore", classNamePo.getFcScore());
            map.put("fcComment", classNamePo.getFcComment());
            return map;
        }).toList();
        System.out.println(maps);
        return CommonResult.success(maps);
    }
}
