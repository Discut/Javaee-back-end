package com.ybuse.schoolbackend.clazz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybuse.schoolbackend.clazz.domain.po.ClassName;
import com.ybuse.schoolbackend.user.domain.po.Role;

import java.util.List;

/**
 @Author: hyj
 @Date: 2023/6/3
 @Title:
 * ---------
 *  Description:
 *
 */
public interface IClassService extends IService<ClassName> {

    /**
     * 查询所有班级
     * @return 班级列表
     */
    List<ClassName> findAll();

    /**
     * 根据班级查询学生成绩
     * 管理员全部 ，教师自己的班级
     * @param className 班级
     * @return 班级列表
     */
    List<ClassName> findAllByClass(String className);

    /**
     * 添加班级
     * @param className 班级名称
     * @return 是否添加成功
     */
    boolean addClass(ClassName className);

}
