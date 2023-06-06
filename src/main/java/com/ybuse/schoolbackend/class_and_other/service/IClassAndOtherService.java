package com.ybuse.schoolbackend.class_and_other.service;

import com.ybuse.schoolbackend.active_manager.domain.po.ActiveManagerPo;
import com.ybuse.schoolbackend.class_and_other.domain.po.ClassAndOtherPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 30277
* @description 针对表【class_and_other】的数据库操作Service
* @createDate 2023-06-03 17:11:21
*/
public interface IClassAndOtherService extends IService<ClassAndOtherPo> {

    /**
     * 添加活动-班级绑定
     * @param classAndOtherPo classAndOtherPo
     * @return id
     */
    int add(ClassAndOtherPo classAndOtherPo);

    /**
     * 根据活动id删除当条记录
     * @param activeId activeId
     * @return int
     */
    int deleteById(int activeId);

    /**
     * 根据班级id查询其所有的活动信息
     * @param classId 班级id
     * @return List
     */
    List<ActiveManagerPo> queryAllActiveByClassId(int classId);
}
