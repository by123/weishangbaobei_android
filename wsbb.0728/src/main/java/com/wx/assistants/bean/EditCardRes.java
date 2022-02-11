package com.wx.assistants.bean;

public class EditCardRes {
    private Integer id;
    private String wechatNo;

    public EditCardRes(Integer num, String str) {
        this.id = num;
        this.wechatNo = str;
    }

    public Integer getId() {
        return this.id;
    }

    public String getWechatNo() {
        return this.wechatNo;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public void setWechatNo(String str) {
        this.wechatNo = str;
    }
}
