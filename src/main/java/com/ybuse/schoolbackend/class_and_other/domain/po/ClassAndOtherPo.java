package com.ybuse.schoolbackend.class_and_other.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("class_and_other")
public class ClassAndOtherPo {

  private long id;
  private long amId;
  private long csId;
  private String classNo;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getAmId() {
    return amId;
  }

  public void setAmId(long amId) {
    this.amId = amId;
  }


  public long getCsId() {
    return csId;
  }

  public void setCsId(long csId) {
    this.csId = csId;
  }


  public String getClassNo() {
    return classNo;
  }

  public void setClassNo(String classNo) {
    this.classNo = classNo;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
