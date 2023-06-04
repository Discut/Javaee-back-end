package com.ybuse.schoolbackend.active_manager.domain.vo;

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
public class ActiveManagerVo {

    private String title;
    private String startTime;
    private String endTime;
    private String content;
    /**
     * 班级
     */
    private String subject;

}

