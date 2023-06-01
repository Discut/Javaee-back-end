package com.ybuse.schoolbackend.scoresys.domain.po;


public class CommonScore {

  private long id;
  private long csTypeId;
  private String csName;
  private long csScore;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getCsTypeId() {
    return csTypeId;
  }

  public void setCsTypeId(long csTypeId) {
    this.csTypeId = csTypeId;
  }


  public String getCsName() {
    return csName;
  }

  public void setCsName(String csName) {
    this.csName = csName;
  }


  public long getCsScore() {
    return csScore;
  }

  public void setCsScore(long csScore) {
    this.csScore = csScore;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
