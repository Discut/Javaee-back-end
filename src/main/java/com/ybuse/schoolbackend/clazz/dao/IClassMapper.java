package com.ybuse.schoolbackend.clazz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybuse.schoolbackend.clazz.domain.po.ClassName;
import org.apache.ibatis.annotations.Mapper;

/**
 @Author: hyj
 @Date: 2023/6/3
 @Title:
 * ---------
 *  Description:
 *
 */
@Mapper
public interface IClassMapper extends BaseMapper<ClassName> {
}
