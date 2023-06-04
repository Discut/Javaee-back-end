package com.ybuse.schoolbackend.common_score.domain.vo;

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
public class CommonScoreVo {

    String studentName;
    String className;
    int score;
    byte type;

}
