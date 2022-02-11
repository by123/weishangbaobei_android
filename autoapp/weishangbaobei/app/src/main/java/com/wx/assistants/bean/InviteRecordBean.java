package com.wx.assistants.bean;

import java.util.Date;

public class InviteRecordBean {
    private String activationCode;
    private Date createTime;
    private String equipment;
    private String id;
    private String inviteCode;
    private String inviteUserId;
    private String level;
    private String levelName;
    private String memberStatus;
    private String nickName;
    private String password;
    private String phoneNumber;
    private String picUrl;
    private String region;
    private String token;
    private String username;

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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(String str) {
        this.picUrl = str;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String str) {
        this.region = str;
    }

    public String getEquipment() {
        return this.equipment;
    }

    public void setEquipment(String str) {
        this.equipment = str;
    }

    public String getActivationCode() {
        return this.activationCode;
    }

    public void setActivationCode(String str) {
        this.activationCode = str;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date date) {
        this.createTime = date;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String str) {
        this.level = str;
    }

    public String getLevelName() {
        return this.levelName;
    }

    public void setLevelName(String str) {
        this.levelName = str;
    }

    public String getMemberStatus() {
        return this.memberStatus;
    }

    public void setMemberStatus(String str) {
        this.memberStatus = str;
    }

    public String getInviteCode() {
        return this.inviteCode;
    }

    public void setInviteCode(String str) {
        this.inviteCode = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getInviteUserId() {
        return this.inviteUserId;
    }

    public void setInviteUserId(String str) {
        this.inviteUserId = str;
    }
}
