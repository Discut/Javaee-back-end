package com.ybuse.schoolbackend.user_and_other.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import com.ybuse.schoolbackend.user_and_other.domain.po.UserAndOtherPo;
import com.ybuse.schoolbackend.user_and_other.service.IUserAndOtherService;
import com.ybuse.schoolbackend.user_and_other.mapper.UserAndOtherMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 30277
* @description 针对表【user_and_other】的数据库操作Service实现
* @createDate 2023-06-03 17:16:57
*/
@Service
@PrintLog(
        methodType = MethodType.SERVICE
)
public class UserAndOtherServiceImpl extends ServiceImpl<UserAndOtherMapper, UserAndOtherPo>
    implements IUserAndOtherService {

    @Resource
    private UserAndOtherMapper userAndOtherMapper;

    @Override
    public int add(UserAndOtherPo userAndOtherPo) {
        return userAndOtherMapper.insert(userAndOtherPo);
    }
}




