package com.ybuse.schoolbackend.clazz.domain.po;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 @Author: hyj
 @Date: 2023/6/3
 @Title:
 * ---------
 *  Description:
 *
 */
@TableName("qr_image")
@Data
public class QrImagePo {

  private long id;
  private String qrUuid;
  private String qrTitle;
  private String qrContent;
  private String qrFooter;
  private String qrImageUrl;
  private java.sql.Timestamp createTime;

}
