package com.ybuse.schoolbackend.pmi_type.service;

import cn.hutool.core.lang.Opt;
import com.ybuse.schoolbackend.pmi_type.domain.po.PmiTypePo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 30277
* @description 针对表【pmi_type】的数据库操作Service
* @createDate 2023-06-03 17:14:43
*/
public interface IPmiTypeService extends IService<PmiTypePo> {

    int add(PmiTypePo pmiTypePo);

    List<PmiTypePo> findAll();
}
