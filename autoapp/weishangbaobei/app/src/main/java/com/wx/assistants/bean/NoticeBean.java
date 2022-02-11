package com.wx.assistants.bean;

import java.util.Date;

public class NoticeBean {
    Date createTime;
    String effectiveEnd;
    String effectiveStart;
    String info;
    String isRead;
    String noticeDesc;
    String noticeId;
    String noticeStatus;
    String noticeTitle;
    String noticeType;
    String noticeUrl;
    String targetPk;

    public String getNoticeId() {
        return this.noticeId;
    }

    public void setNoticeId(String str) {
        this.noticeId = str;
    }

    public String getNoticeTitle() {
        return this.noticeTitle;
    }

    public void setNoticeTitle(String str) {
        this.noticeTitle = str;
    }

    public String getNoticeDesc() {
        return this.noticeDesc;
    }

    public void setNoticeDesc(String str) {
        this.noticeDesc = str;
    }

    public String getNoticeUrl() {
        return this.noticeUrl;
    }

    public void setNoticeUrl(String str) {
        this.noticeUrl = str;
    }

    public String getNoticeStatus() {
        return this.noticeStatus;
    }

    public void setNoticeStatus(String str) {
        this.noticeStatus = str;
    }

    public String getNoticeType() {
        return this.noticeType;
    }

    public void setNoticeType(String str) {
        this.noticeType = str;
    }

    public String getTargetPk() {
        return this.targetPk;
    }

    public void setTargetPk(String str) {
        this.targetPk = str;
    }

    public String getEffectiveStart() {
        return this.effectiveStart;
    }

    public void setEffectiveStart(String str) {
        this.effectiveStart = str;
    }

    public String getEffectiveEnd() {
        return this.effectiveEnd;
    }

    public void setEffectiveEnd(String str) {
        this.effectiveEnd = str;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date date) {
        this.createTime = date;
    }

    public String getIsRead() {
        return this.isRead;
    }

    public void setIsRead(String str) {
        this.isRead = str;
    }
}
