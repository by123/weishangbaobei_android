package com.wx.assistants.Enity;

public class TagPeoplesBean {
    private Long id;
    private String wxName;
    private String wxNum;
    private String wxPeoples;
    private String wxTagName;

    public TagPeoplesBean() {
    }

    public TagPeoplesBean(Long l, String str, String str2, String str3, String str4) {
        this.id = l;
        this.wxName = str;
        this.wxNum = str2;
        this.wxTagName = str3;
        this.wxPeoples = str4;
    }

    public Long getId() {
        return this.id;
    }

    public String getWxName() {
        return this.wxName;
    }

    public String getWxNum() {
        return this.wxNum;
    }

    public String getWxPeoples() {
        return this.wxPeoples;
    }

    public String getWxTagName() {
        return this.wxTagName;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setWxName(String str) {
        this.wxName = str;
    }

    public void setWxNum(String str) {
        this.wxNum = str;
    }

    public void setWxPeoples(String str) {
        this.wxPeoples = str;
    }

    public void setWxTagName(String str) {
        this.wxTagName = str;
    }
}
