package com.wx.assistants.Enity;

public class TagPeoplesBean {
    private Long id;
    private String wxName;
    private String wxNum;
    private String wxPeoples;
    private String wxTagName;

    public String getWxPeoples() {
        return this.wxPeoples;
    }

    public void setWxPeoples(String str) {
        this.wxPeoples = str;
    }

    public String getWxTagName() {
        return this.wxTagName;
    }

    public void setWxTagName(String str) {
        this.wxTagName = str;
    }

    public String getWxNum() {
        return this.wxNum;
    }

    public void setWxNum(String str) {
        this.wxNum = str;
    }

    public String getWxName() {
        return this.wxName;
    }

    public void setWxName(String str) {
        this.wxName = str;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public TagPeoplesBean(Long l, String str, String str2, String str3, String str4) {
        this.id = l;
        this.wxName = str;
        this.wxNum = str2;
        this.wxTagName = str3;
        this.wxPeoples = str4;
    }

    public TagPeoplesBean() {
    }
}
