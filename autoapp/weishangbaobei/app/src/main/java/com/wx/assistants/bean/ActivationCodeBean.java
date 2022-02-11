package com.wx.assistants.bean;

import java.util.Date;

public class ActivationCodeBean {
    private String activationCode;
    private Date createTime;
    private String id;
    private String phoneNumber;
    private int state;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getActivationCode() {
        return this.activationCode;
    }

    public void setActivationCode(String str) {
        this.activationCode = str;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date date) {
        this.createTime = date;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }
}
