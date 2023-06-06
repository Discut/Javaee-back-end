package com.ybuse.schoolbackend.class_name.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.class_name.domain.po.ClassNamePo;
import com.ybuse.schoolbackend.class_name.service.IClassNameService;
import com.ybuse.schoolbackend.class_name.mapper.ClassNameMapper;
import com.ybuse.schoolbackend.clazz.domain.po.ClassName;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 30277
* @description 针对表【class_name】的数据库操作Service实现
* @createDate 2023-06-03 17:12:00
*/
@Service
@PrintLog(
        methodType = MethodType.SERVICE
)
public class ClassNameServiceImpl extends ServiceImpl<ClassNameMapper, ClassNamePo>
    implements IClassNameService {

    @Resource
    private ClassNameMapper classNameMapper;

    @Override
    public int add(ClassNamePo classNamePo) {
        return classNameMapper.insert(classNamePo);
    }

    @Override
    public int queryByClassName(String className) {
        return (int)classNameMapper.queryByClassName(className);
    }

    @Override
    public List<ClassNamePo> findAll() {
        return this.list();
    }
}




