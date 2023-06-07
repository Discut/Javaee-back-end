package com.ybuse.schoolbackend.exam_score.service;

import com.ybuse.schoolbackend.exam_score.domain.po.ExamScorePo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.PropertyValues;

import java.util.List;

/**
* @author 30277
* @description 针对表【exam_score】的数据库操作Service
* @createDate 2023-06-03 17:13:15
*/
public interface IExamScoreService extends IService<ExamScorePo> {
    int add(ExamScorePo examScorePo);

    /**
     * 查询所有成绩
     * @return List<ExamScorePo>
     */
    List<ExamScorePo> findAll();

    /**
     * 根据班级名称 、 模糊查询 查询所有信息
     * @param className 班级名称
     * @param content 模糊查询内容
     * @return List<ExamScorePo>
     */
    List<ExamScorePo> findAllByClassName(String className,String content);

    /**
     * 根据学生名称 、 模糊查询 查询所有信息
     * @param studentName 学生名称
     * @param content 模糊查询内容
     * @return List<ExamScorePo>
     */
    List<ExamScorePo> findAllByStudentName(String studentName, String content);
}
