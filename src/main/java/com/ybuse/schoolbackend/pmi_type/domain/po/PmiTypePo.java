package com.ybuse.schoolbackend.pmi_type.domain.po;


import com.baomidou.mybatisplus.annotation.TableName;

@TableName("pmi_type")
public class PmiTypePo {

  private long id;
  private String typeName;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
