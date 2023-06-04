package com.ybuse.schoolbackend.class_name.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.class_name.domain.po.ClassNamePo;
import com.ybuse.schoolbackend.class_name.service.ClassNameService;
import com.ybuse.schoolbackend.class_name.mapper.ClassNameMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 30277
* @description 针对表【class_name】的数据库操作Service实现
* @createDate 2023-06-03 17:12:00
*/
@Service
public class ClassNameServiceImpl extends ServiceImpl<ClassNameMapper, ClassNamePo>
    implements ClassNameService {

    @Resource
    private ClassNameMapper classNameMapper;

    @Override
    public void add(ClassNamePo classNamePo) {
        classNameMapper.insert(classNamePo);
    }
}




