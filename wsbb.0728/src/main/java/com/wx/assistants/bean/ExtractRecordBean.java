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

    public String getBalance() {
        return this.balance;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public String getEquipment() {
        return this.equipment;
    }

    public String getId() {
        return this.id;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getResult() {
        return this.result;
    }

    public String getState() {
        return this.state;
    }

    public String getUserid() {
        return this.userid;
    }

    public String getUsername() {
        return this.username;
    }

    public String getWithdrawAmount() {
        return this.withdrawAmount;
    }

    public void setBalance(String str) {
        this.balance = str;
    }

    public void setCreateTime(Date date) {
        this.createTime = date;
    }

    public void setEquipment(String str) {
        this.equipment = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public void setState(String str) {
        this.state = str;
    }

    public void setUserid(String str) {
        this.userid = str;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public void setWithdrawAmount(String str) {
        this.withdrawAmount = str;
    }
}
