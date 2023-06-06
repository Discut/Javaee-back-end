package com.ybuse.schoolbackend.class_and_other.mapper;

import com.ybuse.schoolbackend.active_manager.domain.po.ActiveManagerPo;
import com.ybuse.schoolbackend.class_and_other.domain.po.ClassAndOtherPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 30277
* @description 针对表【class_and_other】的数据库操作Mapper
* @createDate 2023-06-03 17:11:21
* @Entity generator.domain.ClassAndOther
*/
@Mapper
public interface ClassAndOtherMapper extends BaseMapper<ClassAndOtherPo> {

    /**
     * 根据班级id查询所有活动
     * @param classNo 班级id
     * @return List<ActiveManagerPo>
     */
    @Select("SELECT am.id, am.am_name, am.am_time_interval, am.am_content, am.am_end_content, am.create_time FROM class_name as cn " +
            "JOIN class_and_other as cao ON cn.id = cao.class_no " +
            "JOIN active_manager as am ON cao.am_id = am.id " +
            "where cn.id = #{classNo}")
    List<ActiveManagerPo> findActiveManagersByClassNo(int classNo);

    /**
     * 在中间表中根据activeId删除当条所在数据
     * @param activeId 活动id
     * @return int
     */
    @Delete("delete from class_and_other where am_id=#{activeId}")
    int deleteByClassNo(int activeId);


}




