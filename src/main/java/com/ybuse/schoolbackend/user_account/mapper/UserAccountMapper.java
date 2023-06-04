package com.ybuse.schoolbackend.user_account.mapper;

import com.ybuse.schoolbackend.user_account.domain.po.UserAccountPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 30277
* @description 针对表【user_account】的数据库操作Mapper
* @createDate 2023-06-03 17:16:23
* @Entity generator.domain.UserAccount
*/
@Mapper
@Repository
public interface UserAccountMapper extends BaseMapper<UserAccountPo> {

}




