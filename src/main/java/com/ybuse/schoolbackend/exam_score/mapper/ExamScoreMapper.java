package com.ybuse.schoolbackend.exam_score.mapper;

import com.ybuse.schoolbackend.exam_score.domain.po.ExamScorePo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 30277
* @description 针对表【exam_score】的数据库操作Mapper
* @createDate 2023-06-03 17:13:15
* @Entity generator.domain.ExamScore
*/
@Mapper
public interface ExamScoreMapper extends BaseMapper<ExamScorePo> {

    /**
     * 根据班级名称 模糊查询 查询所有成绩
     * @param className 班级名称
     * @param content 模糊查询内容
     * @return List<ExamScorePo>
     */
    @Select("SELECT a.* FROM (SELECT es.id, es.es_score, es.es_title, es.create_time FROM user_account as ua " +
            "JOIN user_and_other as uao ON ua.id = uao.uid " +
            "JOIN exam_score as es ON uao.es_id = es.id " +
            "JOIN class_name as cn ON cn.id = ua.ua_class_id " +
            "where ua.ua_type = 4 and cn.class_name = #{className}) as a " +
            "where a.es_title like CONCAT('%', #{content}, '%')")
    List<ExamScorePo> findAllByClassName(String className, String content);

    /**
     * 根据学生名称 模糊查询 查询所有成绩
     * @param studentName 学生名称
     * @param content 模糊查询内容
     * @return List<ExamScorePo>
     */
    @Select("SELECT a.* FROM (SELECT es.id, es.es_score, es.es_title, es.create_time FROM user_account as ua " +
            "JOIN user_and_other as uao ON ua.id = uao.uid " +
            "JOIN exam_score as es ON uao.es_id = es.id " +
            "where ua.ua_type = 4 and ua.ua_name = #{studentName}) as a " +
            "where a.es_title like CONCAT('%', #{content}, '%')")
    List<ExamScorePo> findAllByStudentName(String studentName, String content);
}




