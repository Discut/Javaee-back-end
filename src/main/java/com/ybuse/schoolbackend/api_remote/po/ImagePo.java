package com.ybuse.schoolbackend.api_remote.po;

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
@TableName("image")
@Data
public class ImagePo {

  private long id;
  private String imName;
  private String imPath;
  private java.sql.Timestamp createTime;

}
