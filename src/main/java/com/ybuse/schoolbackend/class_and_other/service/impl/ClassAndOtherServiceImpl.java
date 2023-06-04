package com.ybuse.schoolbackend.class_and_other.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.class_and_other.domain.po.ClassAndOtherPo;
import com.ybuse.schoolbackend.class_and_other.service.ClassAndOtherService;
import com.ybuse.schoolbackend.class_and_other.mapper.ClassAndOtherMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 30277
* @description 针对表【class_and_other】的数据库操作Service实现
* @createDate 2023-06-03 17:11:21
*/
@Service
public class ClassAndOtherServiceImpl extends ServiceImpl<ClassAndOtherMapper, ClassAndOtherPo>
    implements ClassAndOtherService{

    @Resource
    private ClassAndOtherMapper classAndOtherMapper;

    @Override
    public void add(ClassAndOtherPo classAndOtherPo) {
        classAndOtherMapper.insert(classAndOtherPo);
    }
}




