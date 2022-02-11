package com.wx.assistants.Enity;

public class TagMemberBean {
    private Long id;
    private String wxName;
    private String wxNum;
    private String wxTags;

    public TagMemberBean() {
    }

    public TagMemberBean(Long l, String str, String str2, String str3) {
        this.id = l;
        this.wxName = str;
        this.wxNum = str2;
        this.wxTags = str3;
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

    public String getWxTags() {
        return this.wxTags;
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

    public void setWxTags(String str) {
        this.wxTags = str;
    }
}
