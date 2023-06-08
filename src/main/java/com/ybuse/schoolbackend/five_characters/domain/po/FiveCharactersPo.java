package com.ybuse.schoolbackend.five_characters.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("five_characters")
public class FiveCharactersPo {

  @TableId(type = IdType.AUTO)
  private long id;
  private long fcType;
  private String fcComment;
  private long fcScore;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getFcType() {
    return fcType;
  }

  public void setFcType(long fcType) {
    this.fcType = fcType;
  }


  public String getFcComment() {
    return fcComment;
  }

  public void setFcComment(String fcComment) {
    this.fcComment = fcComment;
  }


  public long getFcScore() {
    return fcScore;
  }

  public void setFcScore(long fcScore) {
    this.fcScore = fcScore;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
