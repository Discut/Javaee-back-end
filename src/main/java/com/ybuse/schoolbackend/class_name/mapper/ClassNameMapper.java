package com.ybuse.schoolbackend.class_name.mapper;

import com.ybuse.schoolbackend.class_name.domain.po.ClassNamePo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author 30277
* @description 针对表【class_name】的数据库操作Mapper
* @createDate 2023-06-03 17:12:00
* @Entity generator.domain.ClassName
*/
@Mapper
public interface ClassNameMapper extends BaseMapper<ClassNamePo> {
    /**
     * 根据班级名称查询id
     * @param className 班级名称
     * @return long
     */
    @Select("SELECT cn.id FROM class_name as cn where cn.class_name = #{className}")
    long queryByClassName(String className);



}




