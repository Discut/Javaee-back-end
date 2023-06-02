package com.ybuse.schoolbackend.scoresys.service.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ybuse.schoolbackend.core.CustomException;
import com.ybuse.schoolbackend.public_enum.ConductScoreEnum;
import com.ybuse.schoolbackend.scoresys.dao.CommonScoreMapper;
import com.ybuse.schoolbackend.scoresys.domain.po.CommonScore;
import com.ybuse.schoolbackend.scoresys.domain.vo.DeductScoreVo;
import com.ybuse.schoolbackend.scoresys.service.IDeductScoreOperation;
import lombok.val;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Objects;

/**
 * 教师-课后服务
 * @author Discut
 */
public class JKDeductScore implements IDeductScoreOperation<DeductScoreVo> {

    @PreAuthorize("hasAuthority(T(com.ybuse.schoolbackend.public_enum.RoleEnum).TEACHER.code)")
    @Override
    public void operation(DeductScoreVo data) {
        val bean = SpringUtil.getBean(CommonScoreMapper.class);
        if (Objects.isNull(bean)) {
            throw new CustomException("Dao is null");
        }
        CommonScore commonScore = new CommonScore();
        if (StringUtils.isNotBlank(data.getStudentId())) {
            commonScore.setCsName("student:" + data.getStudentId());
        } else {
            commonScore.setCsName("class:" + data.getClassId());
        }
        commonScore.setCsTypeId(Long.parseLong(ConductScoreEnum.J_K.getType()));
        commonScore.setCsScore(data.getScore());
        bean.insert(commonScore);
    }
}
