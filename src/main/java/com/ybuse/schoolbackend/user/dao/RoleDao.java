package com.ybuse.schoolbackend.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybuse.schoolbackend.user.domain.po.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleDao extends BaseMapper<Role> {
    @Select("SELECT * FROM role WHERE id IN (SELECT role_id FROM rl_user_role WHERE username = #{username})")
    List<Role> findRolesByAccount(String username);
}
