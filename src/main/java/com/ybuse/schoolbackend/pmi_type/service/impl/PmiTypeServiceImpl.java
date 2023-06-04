package com.ybuse.schoolbackend.pmi_type.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.pmi_type.domain.po.PmiTypePo;
import com.ybuse.schoolbackend.pmi_type.service.PmiTypeService;
import com.ybuse.schoolbackend.pmi_type.mapper.PmiTypeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 30277
* @description 针对表【pmi_type】的数据库操作Service实现
* @createDate 2023-06-03 17:14:43
*/
@Service
public class PmiTypeServiceImpl extends ServiceImpl<PmiTypeMapper, PmiTypePo>
    implements PmiTypeService{

    @Resource
    private PmiTypeMapper pmiTypeMapper;

    @Override
    public void add(PmiTypePo pmiTypePo) {
        pmiTypeMapper.insert(pmiTypePo);
    }
}




