package com.ybuse.schoolbackend.common_score.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.common_score.domain.po.CommonScorePo;
import com.ybuse.schoolbackend.common_score.service.CommonScoreService;
import com.ybuse.schoolbackend.common_score.mapper.NewCommonScoreMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 30277
* @description 针对表【common_score】的数据库操作Service实现
* @createDate 2023-06-03 17:12:36
*/
@Service
public class CommonScoreServiceImpl extends ServiceImpl<NewCommonScoreMapper, CommonScorePo>
    implements CommonScoreService {

    @Resource
    private NewCommonScoreMapper newCommonScoreMapper;

    @Override
    public void add(CommonScorePo commonScorePo) {
        newCommonScoreMapper.insert(commonScorePo);
    }
}




