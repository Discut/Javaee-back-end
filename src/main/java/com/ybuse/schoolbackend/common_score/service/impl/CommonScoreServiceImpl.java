package com.ybuse.schoolbackend.common_score.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.common_score.domain.po.CommonScorePo;
import com.ybuse.schoolbackend.common_score.service.ICommonScoreService;
import com.ybuse.schoolbackend.common_score.mapper.NewCommonScoreMapper;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 30277
* @description 针对表【common_score】的数据库操作Service实现
* @createDate 2023-06-03 17:12:36
*/
@Service
@PrintLog(
        methodType = MethodType.SERVICE
)
public class CommonScoreServiceImpl extends ServiceImpl<NewCommonScoreMapper, CommonScorePo>
    implements ICommonScoreService {

    @Resource
    private NewCommonScoreMapper newCommonScoreMapper;

    @Override
    public int add(CommonScorePo commonScorePo) {
        return newCommonScoreMapper.insert(commonScorePo);
    }
}




