package com.ybuse.schoolbackend.exam_score.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("exam_score")
public class ExamScorePo {

  private long id;
  private long esScore;
  private String esTitle;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getEsScore() {
    return esScore;
  }

  public void setEsScore(long esScore) {
    this.esScore = esScore;
  }


  public String getEsTitle() {
    return esTitle;
  }

  public void setEsTitle(String esTitle) {
    this.esTitle = esTitle;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
