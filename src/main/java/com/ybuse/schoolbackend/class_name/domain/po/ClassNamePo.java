package com.ybuse.schoolbackend.class_name.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("class_name")
public class ClassNamePo {

  private long id;
  private String className;
  private String qrUuid;
  private String classTeacher;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }


  public String getQrUuid() {
    return qrUuid;
  }

  public void setQrUuid(String qrUuid) {
    this.qrUuid = qrUuid;
  }


  public String getClassTeacher() {
    return classTeacher;
  }

  public void setClassTeacher(String classTeacher) {
    this.classTeacher = classTeacher;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
