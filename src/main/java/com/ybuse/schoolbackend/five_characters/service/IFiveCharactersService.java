package com.ybuse.schoolbackend.five_characters.service;

import com.ybuse.schoolbackend.five_characters.domain.po.FiveCharactersPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 30277
* @description 针对表【five_characters】的数据库操作Service
* @createDate 2023-06-03 17:13:43
*/
public interface IFiveCharactersService extends IService<FiveCharactersPo> {
    int add(FiveCharactersPo fiveCharactersPo);
}
