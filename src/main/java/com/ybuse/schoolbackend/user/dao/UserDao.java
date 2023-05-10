package com.ybuse.schoolbackend.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.ybuse.schoolbackend.user.domain.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {

}
