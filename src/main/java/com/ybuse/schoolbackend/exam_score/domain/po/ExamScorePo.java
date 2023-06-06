package com.ybuse.schoolbackend.exam_score.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 @Author: hyj
 @Date: 2023/6/6
 @Title:
 * ---------
 *  Description:
 *
 */
@TableName("exam_score")
@Data
public class ExamScorePo {
  private long id;
  private long esScore;
  private String esTitle;
  private java.sql.Timestamp createTime;
}

