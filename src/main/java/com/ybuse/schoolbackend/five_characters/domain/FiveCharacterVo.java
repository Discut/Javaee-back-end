package com.ybuse.schoolbackend.five_characters.domain;

import lombok.Data;

@Data
public class FiveCharacterVo {
    private String type;
    private String comment;
    private String score;
    private String student;
    private String clazz;
}
