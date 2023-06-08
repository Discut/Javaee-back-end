package com.ybuse.schoolbackend.user_account.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import com.ybuse.schoolbackend.user_account.domain.po.UserAccountPo;
import com.ybuse.schoolbackend.user_account.service.IUserAccountService;
import com.ybuse.schoolbackend.user_account.mapper.UserAccountMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 30277
* @description 针对表【user_account】的数据库操作Service实现
* @createDate 2023-06-03 17:16:23
*/
@Service
@PrintLog(
        methodType = MethodType.SERVICE
)
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccountPo>
    implements IUserAccountService {

    @Resource
    private UserAccountMapper userAccountMapper;

    @Override
    public int insert(UserAccountPo userAccountPo) {
        return userAccountMapper.insert(userAccountPo);
    }

    @Override
    public int queryIdByAccount(String account) {
        return (int)userAccountMapper.getUserIdByUaccountId(account);
    }

}




