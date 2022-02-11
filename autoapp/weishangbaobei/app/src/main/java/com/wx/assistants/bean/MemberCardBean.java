package com.wx.assistants.bean;

import java.util.Date;

public class MemberCardBean {
    private String activationCode;
    private Date activationTime;
    private String createTime;
    private String typeName;

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getActivationCode() {
        return this.activationCode;
    }

    public void setActivationCode(String str) {
        this.activationCode = str;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String str) {
        this.typeName = str;
    }

    public Date getActivationTime() {
        return this.activationTime;
    }

    public void setActivationTime(Date date) {
        this.activationTime = date;
    }
}
