package com.ybuse.schoolbackend.clazz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybuse.schoolbackend.clazz.domain.po.ClassName;
import com.ybuse.schoolbackend.user.domain.po.Role;

import java.util.List;

public interface IClassService extends IService<ClassName> {

    List<ClassName> findAll();

    List<ClassName> findAllByRole(Role role);

}
