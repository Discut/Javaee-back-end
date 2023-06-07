package com.ybuse.schoolbackend.prize_manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import com.ybuse.schoolbackend.prize_manager.domain.po.PrizeManagerPo;
import com.ybuse.schoolbackend.prize_manager.service.IPrizeManagerService;
import com.ybuse.schoolbackend.prize_manager.mapper.PrizeManagerMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 30277
* @description 针对表【prize_manager】的数据库操作Service实现
* @createDate 2023-06-03 17:15:15
*/
@Service
@PrintLog(
        methodType = MethodType.SERVICE
)
public class PrizeManagerServiceImpl extends ServiceImpl<PrizeManagerMapper, PrizeManagerPo>
    implements IPrizeManagerService {

    @Resource
    private PrizeManagerMapper prizeManagerMapper;

    @Override
    public int add(PrizeManagerPo prizeManagerPo) {
        return prizeManagerMapper.insert(prizeManagerPo);
    }

    @Override
    public List<PrizeManagerPo> findAll() {
        return this.list();
    }
}




