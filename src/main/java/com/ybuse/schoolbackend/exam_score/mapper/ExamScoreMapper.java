package com.ybuse.schoolbackend.exam_score.mapper;

import com.ybuse.schoolbackend.exam_score.domain.po.ExamScorePo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 30277
* @description 针对表【exam_score】的数据库操作Mapper
* @createDate 2023-06-03 17:13:15
* @Entity generator.domain.ExamScore
*/
@Mapper
public interface ExamScoreMapper extends BaseMapper<ExamScorePo> {

}




