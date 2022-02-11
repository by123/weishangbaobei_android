package com.wx.assistants.bean;

public class AddCardRes {
    private String wechatNo;

    public String getWechatNo() {
        return this.wechatNo;
    }

    public void setWechatNo(String str) {
        this.wechatNo = str;
    }

    public AddCardRes(String str) {
        this.wechatNo = str;
    }
}
