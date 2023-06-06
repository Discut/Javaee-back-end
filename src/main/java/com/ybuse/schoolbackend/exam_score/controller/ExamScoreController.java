package com.ybuse.schoolbackend.exam_score.controller;

import com.ybuse.schoolbackend.class_name.domain.po.ClassNamePo;
import com.ybuse.schoolbackend.core.ApiV1Controller;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import com.ybuse.schoolbackend.exam_score.domain.po.ExamScorePo;
import com.ybuse.schoolbackend.exam_score.service.IExamScoreService;
import com.ybuse.schoolbackend.exam_score.service.impl.ExamScoreServiceImpl;
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
@Tag(name = "ActiveManagerController", description = "ActiveManagerController", externalDocs = @ExternalDocumentation(description = "Swagger3(OpenAPI)常用注解参考", url = "https://blog.csdn.net/qq_35425070/article/details/105347336"))
@ApiV1Controller("/score")
public class ExamScoreController {

    @Autowired
    IExamScoreService examScoreService;

    @PostMapping("/post")
    @Operation(summary = "create examScore")
    public CommonResult<Object> addClass(@RequestBody ExamScorePo examScorePo) {
        examScoreService.add(examScorePo);
        return CommonResult.success("ok");
    }

    @DeleteMapping("/delete")
    @Operation(summary = "delete examScore")
    public CommonResult<Object> deleteClass(@RequestBody int id) {
        examScoreService.removeById(id);
        return CommonResult.success("ok");
    }

    @PutMapping("/put")
    @Operation(summary = "put examScore")
    public CommonResult<Object> putClass(@RequestBody ExamScorePo examScorePo) {
        examScoreService.updateById(examScorePo);
        return CommonResult.success("ok");
    }

    /**
     * TODO 操行分管理查询
     * 根据班级查询考试+here (多表联查) 传入 --> 班级名称
     * 根据学生姓名查询考试成绩+here (多表联查) 传入 --> 学生姓名
     * 查出后进行以下模糊查询tip(子查询)
     * here：模糊查询（第几次/科目/第几学期） -- like解决
     */
    @GetMapping("/get/student/{studentName}")
    @Operation(summary = "get examScore")
    public CommonResult<Object> getExamScoreByStudentName(@PathVariable String studentName) {

        // 1. 根据学生姓名查询学生id
        // 2. 根据中间表查询

//        val maps = classNameService.findAll().stream().map(classNamePo -> {
//            Map<String, Object> map = new HashMap<>(16);
//            map.put("id", classNamePo.getId());
//            map.put("name", classNamePo.getClassName());
//            return map;
//        }).toList();
//        System.out.println(maps);
        return CommonResult.success("maps");
    }

    @GetMapping("/get/class/{className}")
    @Operation(summary = "get examScore")
    public CommonResult<Object> getExamScoreByClassName(@PathVariable String className) {


//        val maps = classNameService.findAll().stream().map(classNamePo -> {
//            Map<String, Object> map = new HashMap<>(16);
//            map.put("id", classNamePo.getId());
//            map.put("name", classNamePo.getClassName());
//            return map;
//        }).toList();
//        System.out.println(maps);
        return CommonResult.success("maps");
    }

    /**
     * 查询所有分数
     * @return
     */
    @GetMapping("/get")
    @Operation(summary = "get examScore")
    public CommonResult<Object> geAllExamScore() {
        val maps = examScoreService.findAll().stream().map(examScorePo -> {
            Map<String, Object> map = new HashMap<>(16);
            map.put("id", examScorePo.getId());
            map.put("name", examScorePo.getEsScore());
            map.put("title", examScorePo.getEsTitle());
            return map;
        }).toList();
        System.out.println(maps);
        return CommonResult.success("maps");
    }





}
