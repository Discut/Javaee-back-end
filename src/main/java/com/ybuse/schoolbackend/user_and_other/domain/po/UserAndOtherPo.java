package com.ybuse.schoolbackend.user_and_other.domain.po;


import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_and_other")
public class UserAndOtherPo {

  private long id;
  private long uid;
  private long esId;
  private long pmId;
  private long fcId;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public long getEsId() {
    return esId;
  }

  public void setEsId(long esId) {
    this.esId = esId;
  }


  public long getPmId() {
    return pmId;
  }

  public void setPmId(long pmId) {
    this.pmId = pmId;
  }


  public long getFcId() {
    return fcId;
  }

  public void setFcId(long fcId) {
    this.fcId = fcId;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
