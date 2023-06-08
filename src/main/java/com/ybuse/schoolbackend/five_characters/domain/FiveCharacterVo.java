package com.ybuse.schoolbackend.five_characters.domain;

import lombok.Data;

/**
 @Author: hyj
 @Date: 2023/6/8
 @Title:
 * ---------
 *  Description:
 *
 */
@Data
public class FiveCharacterVo {
    private String type;
    private String comment;
    private String score;
    private String account;
    private String clazz;
}
