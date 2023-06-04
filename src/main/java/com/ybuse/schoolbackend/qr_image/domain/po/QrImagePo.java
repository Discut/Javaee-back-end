package com.ybuse.schoolbackend.qr_image.domain.po;


import com.baomidou.mybatisplus.annotation.TableName;

@TableName("qr_image")
public class QrImagePo {

  private long id;
  private String qrUuid;
  private String qrTitle;
  private String qrContent;
  private String qrFooter;
  private String qrImageUrl;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getQrUuid() {
    return qrUuid;
  }

  public void setQrUuid(String qrUuid) {
    this.qrUuid = qrUuid;
  }


  public String getQrTitle() {
    return qrTitle;
  }

  public void setQrTitle(String qrTitle) {
    this.qrTitle = qrTitle;
  }


  public String getQrContent() {
    return qrContent;
  }

  public void setQrContent(String qrContent) {
    this.qrContent = qrContent;
  }


  public String getQrFooter() {
    return qrFooter;
  }

  public void setQrFooter(String qrFooter) {
    this.qrFooter = qrFooter;
  }


  public String getQrImageUrl() {
    return qrImageUrl;
  }

  public void setQrImageUrl(String qrImageUrl) {
    this.qrImageUrl = qrImageUrl;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
