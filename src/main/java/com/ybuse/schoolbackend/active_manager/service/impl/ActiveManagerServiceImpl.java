package com.ybuse.schoolbackend.active_manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.active_manager.domain.po.ActiveManagerPo;
import com.ybuse.schoolbackend.active_manager.mapper.ActiveManagerMapper;
import com.ybuse.schoolbackend.active_manager.service.IActiveManagerService;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 30277
* @description 针对表【active_manager】的数据库操作Service实现
* @createDate 2023-06-03 17:10:33
*/
@Service
@PrintLog(
        methodType = MethodType.SERVICE
)
public class ActiveManagerServiceImpl extends ServiceImpl<ActiveManagerMapper, ActiveManagerPo>
    implements IActiveManagerService {

    @Resource
    private ActiveManagerMapper activeManagerMapper;

    @Override
    public int add(ActiveManagerPo activeManagerPo) {
        return activeManagerMapper.insert(activeManagerPo);
    }
}




