package com.ybuse.schoolbackend.class_and_other.domain.vo;

import lombok.Data;

/**
 @Author: hyj
 @Date: 2023/6/4
 @Title:
 * ---------
 *  Description:
 *
 */
@Data
public class AmidAndClassVo {

    private long amId;
    private long csId;
    private String classNo;
    private java.sql.Timestamp createTime;

}
