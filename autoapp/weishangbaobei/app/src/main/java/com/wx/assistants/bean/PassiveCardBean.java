package com.wx.assistants.bean;

public class PassiveCardBean {
    private String creatTime;
    private int currentPassiveNum;
    private String estimateFanceNum;
    private String fanceEndTime;
    private String fanceStartTime;
    private int id;
    private String memberId;
    private int totalPassive;
    private String updateTime;
    private int userId;
    private String wechatNo;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int i) {
        this.userId = i;
    }

    public String getWechatNo() {
        return this.wechatNo;
    }

    public void setWechatNo(String str) {
        this.wechatNo = str;
    }

    public String getFanceStartTime() {
        return this.fanceStartTime;
    }

    public void setFanceStartTime(String str) {
        this.fanceStartTime = str;
    }

    public String getFanceEndTime() {
        return this.fanceEndTime;
    }

    public void setFanceEndTime(String str) {
        this.fanceEndTime = str;
    }

    public String getCreatTime() {
        return this.creatTime;
    }

    public void setCreatTime(String str) {
        this.creatTime = str;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String str) {
        this.updateTime = str;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String str) {
        this.memberId = str;
    }

    public int getCurrentPassiveNum() {
        return this.currentPassiveNum;
    }

    public void setCurrentPassiveNum(int i) {
        this.currentPassiveNum = i;
    }

    public int getTotalPassive() {
        return this.totalPassive;
    }

    public void setTotalPassive(int i) {
        this.totalPassive = i;
    }

    public String getEstimateFanceNum() {
        return this.estimateFanceNum;
    }

    public void setEstimateFanceNum(String str) {
        this.estimateFanceNum = str;
    }
}
