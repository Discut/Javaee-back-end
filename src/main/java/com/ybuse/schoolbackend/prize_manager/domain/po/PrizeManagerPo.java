package com.ybuse.schoolbackend.prize_manager.domain.po;


import com.baomidou.mybatisplus.annotation.TableName;

@TableName("prize_manager")
public class PrizeManagerPo {

  private long id;
  private String pmPrizeName;
  private String pmTypeId;
  private String pmScore;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getPmPrizeName() {
    return pmPrizeName;
  }

  public void setPmPrizeName(String pmPrizeName) {
    this.pmPrizeName = pmPrizeName;
  }


  public String getPmTypeId() {
    return pmTypeId;
  }

  public void setPmTypeId(String pmTypeId) {
    this.pmTypeId = pmTypeId;
  }


  public String getPmScore() {
    return pmScore;
  }

  public void setPmScore(String pmScore) {
    this.pmScore = pmScore;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
