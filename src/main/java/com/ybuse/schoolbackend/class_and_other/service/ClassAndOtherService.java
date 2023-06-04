package com.ybuse.schoolbackend.class_and_other.service;

import com.ybuse.schoolbackend.class_and_other.domain.po.ClassAndOtherPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 30277
* @description 针对表【class_and_other】的数据库操作Service
* @createDate 2023-06-03 17:11:21
*/
public interface ClassAndOtherService extends IService<ClassAndOtherPo> {
    void add(ClassAndOtherPo classAndOtherPo);
}
