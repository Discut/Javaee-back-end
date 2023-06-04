package com.ybuse.schoolbackend.pmi_type.service;

import com.ybuse.schoolbackend.pmi_type.domain.po.PmiTypePo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 30277
* @description 针对表【pmi_type】的数据库操作Service
* @createDate 2023-06-03 17:14:43
*/
public interface PmiTypeService extends IService<PmiTypePo> {

    void add(PmiTypePo pmiTypePo);

}
