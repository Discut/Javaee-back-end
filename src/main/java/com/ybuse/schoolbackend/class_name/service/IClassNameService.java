package com.ybuse.schoolbackend.class_name.service;

import com.ybuse.schoolbackend.class_name.domain.po.ClassNamePo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ybuse.schoolbackend.clazz.domain.po.ClassName;

import java.util.List;

/**
* @author 30277
* @description 针对表【class_name】的数据库操作Service
* @createDate 2023-06-03 17:12:00
*/
public interface IClassNameService extends IService<ClassNamePo> {


    int add(ClassNamePo classNamePo);

    /**
     * 根据班级名称查询班级id
     * @param className 班级名称
     * @return 班级id
     */
    int queryByClassName(String className);

    /**
     * 查询所有班级
     * @return List
     */
    List<ClassNamePo> findAll();
}
