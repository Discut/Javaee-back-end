package com.ybuse.schoolbackend.exam_score.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import com.ybuse.schoolbackend.exam_score.domain.po.ExamScorePo;
import com.ybuse.schoolbackend.exam_score.service.IExamScoreService;
import com.ybuse.schoolbackend.exam_score.mapper.ExamScoreMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 30277
* @description 针对表【exam_score】的数据库操作Service实现
* @createDate 2023-06-03 17:13:15
*/
@Service
@PrintLog(
        methodType = MethodType.SERVICE
)
public class ExamScoreServiceImpl extends ServiceImpl<ExamScoreMapper, ExamScorePo>
    implements IExamScoreService {

    @Resource
    private ExamScoreMapper examScoreMapper;

    @Override
    public int add(ExamScorePo examScorePo) {
        return examScoreMapper.insert(examScorePo);
    }
}




