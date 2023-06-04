package com.ybuse.schoolbackend.exam_score.mapper;
import java.sql.Timestamp;

import com.ybuse.schoolbackend.exam_score.domain.po.ExamScorePo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ExamScoreMapperTest {

    @Resource
    private ExamScoreMapper examScoreMapper;
    @Test
    public void add() {
        ExamScorePo examScorePo = new ExamScorePo();
        examScorePo.setEsScore(99);
        examScorePo.setEsTitle("第一学期第一次");
        examScorePo.setCreateTime(new Timestamp(new java.util.Date().getTime()));
        examScoreMapper.insert(examScorePo);

    }

}