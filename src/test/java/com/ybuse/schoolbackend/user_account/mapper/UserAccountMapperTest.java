package com.ybuse.schoolbackend.user_account.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ybuse.schoolbackend.Application;
import com.ybuse.schoolbackend.user_account.domain.po.UserAccountPo;
import com.ybuse.schoolbackend.user_account.service.UserAccountService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;


@SpringBootTest(classes = Application.class)
class UserAccountMapperTest {


    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private UserAccountMapper userAccountMapper;

    @Test
    public void add() {
        UserAccountPo userAccountPo = new UserAccountPo();
        userAccountPo.setUaName("xh");
        userAccountPo.setUaAccount("200104111");

        String encode = bCryptPasswordEncoder.encode("123");
        userAccountPo.setUaPassword(encode);
        userAccountPo.setUaType(4);
        userAccountPo.setUaClassId(4);
        userAccountPo.setUaGroup("yi");
        userAccountPo.setCreateTime(new Timestamp(new java.util.Date().getTime()));
        userAccountPo.setLastLogin(new Timestamp(new java.util.Date().getTime()));
        userAccountMapper.insert(userAccountPo);
    }

    @Test
    public void show() {
        QueryWrapper<UserAccountPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ua_type", 1);
        UserAccountPo userAccountPo = userAccountMapper.selectOne(queryWrapper);
        System.out.println(userAccountPo);
    }

}