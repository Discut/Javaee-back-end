package com.ybuse.schoolbackend.five_characters.controller;

import com.ybuse.schoolbackend.core.ApiV1Controller;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import com.ybuse.schoolbackend.five_characters.domain.FiveCharacterVo;
import com.ybuse.schoolbackend.five_characters.domain.po.FiveCharactersPo;
import com.ybuse.schoolbackend.five_characters.service.IFiveCharactersService;
import com.ybuse.schoolbackend.public_enum.FCenum;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: hyj
 * @Date: 2023/6/6
 * @Title: ---------
 * Description:
 */
@PrintLog(
        methodType = MethodType.HTTP_UP
)
@Tag(name = "CommonScoreController", description = "CommonScoreController", externalDocs = @ExternalDocumentation(description = "Swagger3(OpenAPI)常用注解参考", url = "https://blog.csdn.net/qq_35425070/article/details/105347336"))
@ApiV1Controller("/score/dztml")
public class FiveCharactersController {

    private final IFiveCharactersService fiveCharactersService;

    public FiveCharactersController(IFiveCharactersService fiveCharactersService) {
        this.fiveCharactersService = fiveCharactersService;
    }

    @PostMapping("/post")
    @Operation(summary = "create class")
    public CommonResult<Object> addClass(@RequestBody FiveCharacterVo fiveCharactersPo) {
        FiveCharactersPo po = new FiveCharactersPo();
        po.setFcComment(fiveCharactersPo.getComment());
        po.setFcScore(Long.parseLong(fiveCharactersPo.getScore()));
        po.setFcType(Long.parseLong(Objects.requireNonNull(FCenum.valueOfType(fiveCharactersPo.getType())).getType()));


        fiveCharactersService.add(po);
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

    @GetMapping("/type")
    @Operation(summary = "获取打分类型")
    public CommonResult<List<FiveCharactersType>> getType() {
        List<FiveCharactersType> list = Arrays.stream(FCenum.values()).map(el -> {
            FiveCharactersType fiveCharactersType = new FiveCharactersType();
            fiveCharactersType.setLabel(el.getName());
            fiveCharactersType.setValue(el.getType());
            return fiveCharactersType;
        }).toList();
        return CommonResult.success(list);
    }


    @Data
    public static class FiveCharactersType {
        private String value;
        private String label;
    }
}
