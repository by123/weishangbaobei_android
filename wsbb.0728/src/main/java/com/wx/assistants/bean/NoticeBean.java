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

    public Date getCreateTime() {
        return this.createTime;
    }

    public String getEffectiveEnd() {
        return this.effectiveEnd;
    }

    public String getEffectiveStart() {
        return this.effectiveStart;
    }

    public String getInfo() {
        return this.info;
    }

    public String getIsRead() {
        return this.isRead;
    }

    public String getNoticeDesc() {
        return this.noticeDesc;
    }

    public String getNoticeId() {
        return this.noticeId;
    }

    public String getNoticeStatus() {
        return this.noticeStatus;
    }

    public String getNoticeTitle() {
        return this.noticeTitle;
    }

    public String getNoticeType() {
        return this.noticeType;
    }

    public String getNoticeUrl() {
        return this.noticeUrl;
    }

    public String getTargetPk() {
        return this.targetPk;
    }

    public void setCreateTime(Date date) {
        this.createTime = date;
    }

    public void setEffectiveEnd(String str) {
        this.effectiveEnd = str;
    }

    public void setEffectiveStart(String str) {
        this.effectiveStart = str;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public void setIsRead(String str) {
        this.isRead = str;
    }

    public void setNoticeDesc(String str) {
        this.noticeDesc = str;
    }

    public void setNoticeId(String str) {
        this.noticeId = str;
    }

    public void setNoticeStatus(String str) {
        this.noticeStatus = str;
    }

    public void setNoticeTitle(String str) {
        this.noticeTitle = str;
    }

    public void setNoticeType(String str) {
        this.noticeType = str;
    }

    public void setNoticeUrl(String str) {
        this.noticeUrl = str;
    }

    public void setTargetPk(String str) {
        this.targetPk = str;
    }
}
