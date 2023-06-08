package com.ybuse.schoolbackend.clazz.domain.po;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author: hyj
 * @Date: 2023/6/3
 * @Title: ---------
 * Description:
 */
@Data
public class ClassName {

    @TableId
    private long id;
    private String className;
    private String qrUuid;
    private java.sql.Timestamp createTime;


}
