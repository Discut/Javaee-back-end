package com.ybuse.schoolbackend.active_manager.service;

import com.ybuse.schoolbackend.active_manager.domain.po.ActiveManagerPo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.SelectKey;

/**
* @author 30277
* @description 针对表【active_manager】的数据库操作Service
* @createDate 2023-06-03 17:10:33
*/
public interface IActiveManagerService extends IService<ActiveManagerPo> {
//    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = long.class)
    int add(ActiveManagerPo activeManagerPo);
}
