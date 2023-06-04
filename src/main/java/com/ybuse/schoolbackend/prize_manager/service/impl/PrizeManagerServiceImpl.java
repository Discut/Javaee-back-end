package com.ybuse.schoolbackend.prize_manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.prize_manager.domain.po.PrizeManagerPo;
import com.ybuse.schoolbackend.prize_manager.service.PrizeManagerService;
import com.ybuse.schoolbackend.prize_manager.mapper.PrizeManagerMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 30277
* @description 针对表【prize_manager】的数据库操作Service实现
* @createDate 2023-06-03 17:15:15
*/
@Service
public class PrizeManagerServiceImpl extends ServiceImpl<PrizeManagerMapper, PrizeManagerPo>
    implements PrizeManagerService{

    @Resource
    private PrizeManagerMapper prizeManagerMapper;

    @Override
    public void add(PrizeManagerPo prizeManagerPo) {
        prizeManagerMapper.insert(prizeManagerPo);
    }
}




