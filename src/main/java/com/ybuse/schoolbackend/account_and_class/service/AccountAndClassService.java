package com.ybuse.schoolbackend.account_and_class.service;

import com.ybuse.schoolbackend.account_and_class.domain.po.AccountAndClassPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 30277
* @description 针对表【account_and_class】的数据库操作Service
* @createDate 2023-06-03 17:08:53
*/
public interface AccountAndClassService extends IService<AccountAndClassPo> {
    void add(AccountAndClassPo accountAndClassPo);
}
