package com.ybuse.schoolbackend.user.domain.po;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_account")
public class UserAccount {

    @TableId
    private long id;
    private String uaAccount;
    private String uaPassword;
    private long uaType;
    private long uaClassId;
    private String uaGroup;
    private java.sql.Timestamp createTime;
    private java.sql.Timestamp lastLogin;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;

    }


    public String getUaAccount() {
        return uaAccount;
    }

    public void setUaAccount(String uaAccount) {
        this.uaAccount = uaAccount;
    }


    public String getUaPassword() {
        return uaPassword;
    }

    public void setUaPassword(String uaPassword) {
        this.uaPassword = uaPassword;
    }


    public long getUaType() {
        return uaType;
    }

    public void setUaType(long uaType) {
        this.uaType = uaType;
    }


    public long getUaClassId() {
        return uaClassId;
    }

    public void setUaClassId(long uaClassId) {
        this.uaClassId = uaClassId;
    }


    public String getUaGroup() {
        return uaGroup;
    }

    public void setUaGroup(String uaGroup) {
        this.uaGroup = uaGroup;
    }


    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }


    public java.sql.Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(java.sql.Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

}
