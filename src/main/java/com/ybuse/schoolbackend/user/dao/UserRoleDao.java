package com.ybuse.schoolbackend.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.ybuse.schoolbackend.user.domain.dto.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRoleDao extends BaseMapper<User> {
    @Select("SELECT * FROM user")
    @Results({
            @Result(id = true, column = "username", property = "username"),
            @Result(column = "title", property = "title"),
            @Result(column = "password", property = "password"),
            @Result(column = "enable", property = "enable"),
            @Result(column = "username", property = "roles",
                    many = @Many(select = "com.ybuse.schoolbackend.user.user.dao.RoleDao.findRolesByAccount")),
    })
    List<User> queryAll();

    @Select("SELECT * FROM user WHERE username=#{username}")
    @Results({
            @Result(id = true, column = "username", property = "username"),
            @Result(column = "title", property = "title"),
            @Result(column = "password", property = "password"),
            @Result(column = "enable", property = "enable"),
            @Result(column = "username", property = "roles",
                    many = @Many(select = "com.ybuse.schoolbackend.user.dao.RoleDao.findRolesByAccount")),
    })
    User findRolesByUsername(String username);
}

