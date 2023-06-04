package com.ybuse.schoolbackend.class_name.service;

import com.ybuse.schoolbackend.class_name.domain.po.ClassNamePo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 30277
* @description 针对表【class_name】的数据库操作Service
* @createDate 2023-06-03 17:12:00
*/
public interface ClassNameService extends IService<ClassNamePo> {

    void add(ClassNamePo classNamePo);

}
