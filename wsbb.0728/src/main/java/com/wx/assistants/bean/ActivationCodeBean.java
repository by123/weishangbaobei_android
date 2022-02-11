package com.wx.assistants.bean;

import java.util.Date;

public class ActivationCodeBean {
    private String activationCode;
    private Date createTime;
    private String id;
    private String phoneNumber;
    private int state;

    public String getActivationCode() {
        return this.activationCode;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public String getId() {
        return this.id;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public int getState() {
        return this.state;
    }

    public void setActivationCode(String str) {
        this.activationCode = str;
    }

    public void setCreateTime(Date date) {
        this.createTime = date;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public void setState(int i) {
        this.state = i;
    }
}
