package com.ybuse.schoolbackend.account_and_class.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.account_and_class.domain.po.AccountAndClassPo;
import com.ybuse.schoolbackend.account_and_class.mapper.AccountAndClassMapper;
import com.ybuse.schoolbackend.account_and_class.service.IAccountAndClassService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 30277
* @description 针对表【account_and_class】的数据库操作Service实现
* @createDate 2023-06-03 17:08:53
*/
@Service
public class AccountAndClassServiceImpl extends ServiceImpl<AccountAndClassMapper, AccountAndClassPo>
    implements IAccountAndClassService {

    @Resource
    private AccountAndClassMapper accountAndClassMapper;

    @Override
    public int add(AccountAndClassPo accountAndClassPo) {
        return accountAndClassMapper.insert(accountAndClassPo);
    }
}




