package com.ybuse.schoolbackend.five_characters.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import com.ybuse.schoolbackend.five_characters.domain.po.FiveCharactersPo;
import com.ybuse.schoolbackend.five_characters.service.IFiveCharactersService;
import com.ybuse.schoolbackend.five_characters.mapper.FiveCharactersMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 30277
* @description 针对表【five_characters】的数据库操作Service实现
* @createDate 2023-06-03 17:13:43
*/
@Service
@PrintLog(
        methodType = MethodType.SERVICE
)
public class FiveCharactersServiceImpl extends ServiceImpl<FiveCharactersMapper, FiveCharactersPo>
    implements IFiveCharactersService {

    @Resource
    private FiveCharactersMapper fiveCharactersMapper;

    @Override
    public int add(FiveCharactersPo fiveCharactersPo) {
        return fiveCharactersMapper.insert(fiveCharactersPo);
    }

    @Override
    public List<FiveCharactersPo> findAll() {
        return this.list();
    }
}




