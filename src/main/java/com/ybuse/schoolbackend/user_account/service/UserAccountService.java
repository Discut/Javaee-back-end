package com.ybuse.schoolbackend.user_account.service;

import com.ybuse.schoolbackend.user_account.domain.po.UserAccountPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 30277
* @description 针对表【user_account】的数据库操作Service
* @createDate 2023-06-03 17:16:23
*/
public interface UserAccountService extends IService<UserAccountPo> {
    void insert(UserAccountPo userAccountPo);
}
