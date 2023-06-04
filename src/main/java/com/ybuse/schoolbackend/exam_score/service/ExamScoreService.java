package com.ybuse.schoolbackend.exam_score.service;

import com.ybuse.schoolbackend.exam_score.domain.po.ExamScorePo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 30277
* @description 针对表【exam_score】的数据库操作Service
* @createDate 2023-06-03 17:13:15
*/
public interface ExamScoreService extends IService<ExamScorePo> {
    void add(ExamScorePo examScorePo);
}
