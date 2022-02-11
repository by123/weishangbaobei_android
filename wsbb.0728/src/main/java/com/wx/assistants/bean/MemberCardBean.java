package com.wx.assistants.bean;

import java.util.Date;

public class MemberCardBean {
    private String activationCode;
    private Date activationTime;
    private String createTime;
    private String typeName;

    public String getActivationCode() {
        return this.activationCode;
    }

    public Date getActivationTime() {
        return this.activationTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setActivationCode(String str) {
        this.activationCode = str;
    }

    public void setActivationTime(Date date) {
        this.activationTime = date;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public void setTypeName(String str) {
        this.typeName = str;
    }
}
