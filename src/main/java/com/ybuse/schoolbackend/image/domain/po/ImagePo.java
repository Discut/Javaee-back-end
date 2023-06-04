package com.ybuse.schoolbackend.image.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("image")
public class ImagePo {

  private long id;
  private String imName;
  private String imPath;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getImName() {
    return imName;
  }

  public void setImName(String imName) {
    this.imName = imName;
  }


  public String getImPath() {
    return imPath;
  }

  public void setImPath(String imPath) {
    this.imPath = imPath;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
