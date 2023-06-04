package com.ybuse.schoolbackend.account_and_class.domain.po;


import com.baomidou.mybatisplus.annotation.TableName;

@TableName("account_and_class")
public class AccountAndClassPo {

  private long id;
  private String account;
  private long classId;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }


  public long getClassId() {
    return classId;
  }

  public void setClassId(long classId) {
    this.classId = classId;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "AccountAndClassPo{" +
            "id=" + id +
            ", account='" + account + '\'' +
            ", classId=" + classId +
            ", createTime=" + createTime +
            '}';
  }
}
