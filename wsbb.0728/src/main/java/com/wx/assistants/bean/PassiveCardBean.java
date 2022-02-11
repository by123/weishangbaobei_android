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

    public String getCreatTime() {
        return this.creatTime;
    }

    public int getCurrentPassiveNum() {
        return this.currentPassiveNum;
    }

    public String getEstimateFanceNum() {
        return this.estimateFanceNum;
    }

    public String getFanceEndTime() {
        return this.fanceEndTime;
    }

    public String getFanceStartTime() {
        return this.fanceStartTime;
    }

    public int getId() {
        return this.id;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public int getTotalPassive() {
        return this.totalPassive;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getWechatNo() {
        return this.wechatNo;
    }

    public void setCreatTime(String str) {
        this.creatTime = str;
    }

    public void setCurrentPassiveNum(int i) {
        this.currentPassiveNum = i;
    }

    public void setEstimateFanceNum(String str) {
        this.estimateFanceNum = str;
    }

    public void setFanceEndTime(String str) {
        this.fanceEndTime = str;
    }

    public void setFanceStartTime(String str) {
        this.fanceStartTime = str;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setMemberId(String str) {
        this.memberId = str;
    }

    public void setTotalPassive(int i) {
        this.totalPassive = i;
    }

    public void setUpdateTime(String str) {
        this.updateTime = str;
    }

    public void setUserId(int i) {
        this.userId = i;
    }

    public void setWechatNo(String str) {
        this.wechatNo = str;
    }
}
