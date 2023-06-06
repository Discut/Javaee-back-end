package com.ybuse.schoolbackend.class_and_other.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.active_manager.domain.po.ActiveManagerPo;
import com.ybuse.schoolbackend.class_and_other.domain.po.ClassAndOtherPo;
import com.ybuse.schoolbackend.class_and_other.service.IClassAndOtherService;
import com.ybuse.schoolbackend.class_and_other.mapper.ClassAndOtherMapper;
import com.ybuse.schoolbackend.core.logger.MethodType;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 30277
* @description 针对表【class_and_other】的数据库操作Service实现
* @createDate 2023-06-03 17:11:21
*/
@Service
@PrintLog(
        methodType = MethodType.SERVICE
)
public class ClassAndOtherServiceImpl extends ServiceImpl<ClassAndOtherMapper, ClassAndOtherPo>
    implements IClassAndOtherService {

    @Resource
    private ClassAndOtherMapper classAndOtherMapper;

    @Override
    public int add(ClassAndOtherPo classAndOtherPo) {
        return classAndOtherMapper.insert(classAndOtherPo);
    }

    @Override
    public int deleteById(int activeId) {
        return classAndOtherMapper.deleteByClassNo(activeId);
    }

    @Override
    public List<ActiveManagerPo> queryAllActiveByClassId(int classId) {
        return classAndOtherMapper.findActiveManagersByClassNo(classId);
    }
}




