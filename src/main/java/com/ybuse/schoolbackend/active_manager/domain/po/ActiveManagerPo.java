package com.ybuse.schoolbackend.active_manager.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 @Author: hyj
 @Date: 2023/6/8
 @Title:
 * ---------
 *  Description:
 *
 */
@TableName("active_manager")
public class ActiveManagerPo {

    @TableId(value = "id", type = IdType.AUTO)
    private long id;
    private String amName;
    private String amTimeInterval;
    private String amContent;
    private String amEndContent;
    private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getAmName() {
    return amName;
  }

  public void setAmName(String amName) {
    this.amName = amName;
  }


  public String getAmTimeInterval() {
    return amTimeInterval;
  }

  public void setAmTimeInterval(String amTimeInterval) {
    this.amTimeInterval = amTimeInterval;
  }


  public String getAmContent() {
    return amContent;
  }

  public void setAmContent(String amContent) {
    this.amContent = amContent;
  }


  public String getAmEndContent() {
    return amEndContent;
  }

  public void setAmEndContent(String amEndContent) {
    this.amEndContent = amEndContent;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
