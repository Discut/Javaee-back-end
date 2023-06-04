package com.ybuse.schoolbackend.active_manager.service;

import com.ybuse.schoolbackend.active_manager.domain.po.ActiveManagerPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 30277
* @description 针对表【active_manager】的数据库操作Service
* @createDate 2023-06-03 17:10:33
*/
public interface ActiveManagerService extends IService<ActiveManagerPo> {
    void add(ActiveManagerPo activeManagerPo);
}
