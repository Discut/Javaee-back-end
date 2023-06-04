package com.ybuse.schoolbackend.prize_manager.service;

import com.ybuse.schoolbackend.prize_manager.domain.po.PrizeManagerPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 30277
* @description 针对表【prize_manager】的数据库操作Service
* @createDate 2023-06-03 17:15:15
*/
public interface PrizeManagerService extends IService<PrizeManagerPo> {
    void add(PrizeManagerPo prizeManagerPo);
}
