package com.wx.assistants.bean;

import java.util.Date;

public class ExtractRecordBean {
    private String balance;
    private Date createTime;
    private String equipment;
    private String id;
    private String phoneNumber;
    private String result;
    private String state;
    private String userid;
    private String username;
    private String withdrawAmount;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public String getEquipment() {
        return this.equipment;
    }

    public void setEquipment(String str) {
        this.equipment = str;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String str) {
        this.userid = str;
    }

    public String getWithdrawAmount() {
        return this.withdrawAmount;
    }

    public void setWithdrawAmount(String str) {
        this.withdrawAmount = str;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setBalance(String str) {
        this.balance = str;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date date) {
        this.createTime = date;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String str) {
        this.result = str;
    }
}
