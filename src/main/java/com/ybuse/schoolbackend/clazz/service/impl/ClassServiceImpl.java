package com.ybuse.schoolbackend.clazz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.clazz.dao.IClassMapper;
import com.ybuse.schoolbackend.clazz.domain.po.ClassName;
import com.ybuse.schoolbackend.clazz.service.IClassService;
import com.ybuse.schoolbackend.user.domain.po.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: hyj
 * @Date: 2023/6/3
 * @Title: ---------
 * Description:
 */
@Service
public class ClassServiceImpl extends ServiceImpl<IClassMapper, ClassName> implements IClassService {


    @Override
    public List<ClassName> findAll() {
        return this.list();
    }

    @Override
    public List<ClassName> findAllByClass(String className) {
        return null;
    }


    @Override
    public boolean addClass(ClassName className) {
        return this.save(className);
    }


}
