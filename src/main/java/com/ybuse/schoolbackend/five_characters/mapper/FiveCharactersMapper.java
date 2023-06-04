package com.ybuse.schoolbackend.five_characters.mapper;

import com.ybuse.schoolbackend.five_characters.domain.po.FiveCharactersPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 30277
* @description 针对表【five_characters】的数据库操作Mapper
* @createDate 2023-06-03 17:13:43
* @Entity generator.domain.FiveCharacters
*/
@Mapper
public interface FiveCharactersMapper extends BaseMapper<FiveCharactersPo> {

}




