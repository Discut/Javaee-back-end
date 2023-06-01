package com.ybuse.schoolbackend.clazz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.clazz.dao.IClassMapper;
import com.ybuse.schoolbackend.clazz.domain.po.ClassName;
import com.ybuse.schoolbackend.clazz.service.IClassService;
import com.ybuse.schoolbackend.user.domain.po.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService extends ServiceImpl<IClassMapper, ClassName> implements IClassService {

    @Override
    public List<ClassName> findAll() {
        return this.list();
    }

    @Override
    public List<ClassName> findAllByRole(Role role) {
        return this.list();
    }
}
