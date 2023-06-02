package com.ybuse.schoolbackend.clazz.domain.po;


import com.baomidou.mybatisplus.annotation.TableId;

public class ClassName {

    @TableId
    private long id;
    private String className;
    private java.sql.Timestamp createTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }

}
