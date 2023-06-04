package com.ybuse.schoolbackend.image.mapper;

import com.ybuse.schoolbackend.image.domain.po.ImagePo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 30277
* @description 针对表【image】的数据库操作Mapper
* @createDate 2023-06-03 17:14:09
* @Entity generator.domain.Image
*/
@Mapper
public interface ImageMapper extends BaseMapper<ImagePo> {

}




