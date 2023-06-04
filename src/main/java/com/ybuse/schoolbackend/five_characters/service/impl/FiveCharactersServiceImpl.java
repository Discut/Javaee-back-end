package com.ybuse.schoolbackend.five_characters.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.five_characters.domain.po.FiveCharactersPo;
import com.ybuse.schoolbackend.five_characters.service.FiveCharactersService;
import com.ybuse.schoolbackend.five_characters.mapper.FiveCharactersMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 30277
* @description 针对表【five_characters】的数据库操作Service实现
* @createDate 2023-06-03 17:13:43
*/
@Service
public class FiveCharactersServiceImpl extends ServiceImpl<FiveCharactersMapper, FiveCharactersPo>
    implements FiveCharactersService {

    @Resource
    private FiveCharactersMapper fiveCharactersMapper;

    @Override
    public void add(FiveCharactersPo fiveCharactersPo) {
        fiveCharactersMapper.insert(fiveCharactersPo);
    }
}




