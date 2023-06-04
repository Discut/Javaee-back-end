package com.ybuse.schoolbackend.active_manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.active_manager.domain.po.ActiveManagerPo;
import com.ybuse.schoolbackend.active_manager.service.ActiveManagerService;
import com.ybuse.schoolbackend.active_manager.mapper.ActiveManagerMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 30277
* @description 针对表【active_manager】的数据库操作Service实现
* @createDate 2023-06-03 17:10:33
*/
@Service
public class ActiveManagerServiceImpl extends ServiceImpl<ActiveManagerMapper, ActiveManagerPo>
    implements ActiveManagerService {

    @Resource
    private ActiveManagerMapper activeManagerMapper;

    @Override
    public void add(ActiveManagerPo activeManagerPo) {
        activeManagerMapper.insert(activeManagerPo);
    }
}




