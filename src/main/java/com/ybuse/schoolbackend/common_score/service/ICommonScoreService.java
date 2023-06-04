package com.ybuse.schoolbackend.common_score.service;

import com.ybuse.schoolbackend.common_score.domain.po.CommonScorePo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 30277
* @description 针对表【common_score】的数据库操作Service
* @createDate 2023-06-03 17:12:36
*/
public interface ICommonScoreService extends IService<CommonScorePo> {
    int add(CommonScorePo commonScorePo);
}
