package com.ybuse.schoolbackend.user_and_other.service;

import com.ybuse.schoolbackend.user_and_other.domain.po.UserAndOtherPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 30277
* @description 针对表【user_and_other】的数据库操作Service
* @createDate 2023-06-03 17:16:57
*/
public interface UserAndOtherService extends IService<UserAndOtherPo> {
    void add(UserAndOtherPo userAndOtherPo);
}
