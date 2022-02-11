package com.wx.assistants.bean;

import java.util.Date;

public class MaterialBean {
    private String content;
    private Date createTime;
    private int forwardNum;
    private int id;
    private String picUrlJson;

    public String getContent() {
        return this.content;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public int getForwardNum() {
        return this.forwardNum;
    }

    public int getId() {
        return this.id;
    }

    public String getPicUrlJson() {
        return this.picUrlJson;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setCreateTime(Date date) {
        this.createTime = date;
    }

    public void setForwardNum(int i) {
        this.forwardNum = i;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setPicUrlJson(String str) {
        this.picUrlJson = str;
    }
}
