package com.wx.assistants.bean;

public class EditCardRes {
    private Integer id;
    private String wechatNo;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public String getWechatNo() {
        return this.wechatNo;
    }

    public void setWechatNo(String str) {
        this.wechatNo = str;
    }

    public EditCardRes(Integer num, String str) {
        this.id = num;
        this.wechatNo = str;
    }
}
