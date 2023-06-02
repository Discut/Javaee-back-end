package com.ybuse.schoolbackend.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybuse.schoolbackend.user.domain.po.UserAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserAccountDao extends BaseMapper<UserAccount> {

}
