package com.wx.assistants.Enity;

public class TagMemberBeanCompany {
    private Long id;
    private String wxCompany;
    private String wxName;
    private String wxTags;

    public String getWxTags() {
        return this.wxTags;
    }

    public void setWxTags(String str) {
        this.wxTags = str;
    }

    public String getWxCompany() {
        return this.wxCompany;
    }

    public void setWxCompany(String str) {
        this.wxCompany = str;
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

    public TagMemberBeanCompany(Long l, String str, String str2, String str3) {
        this.id = l;
        this.wxName = str;
        this.wxCompany = str2;
        this.wxTags = str3;
    }

    public TagMemberBeanCompany() {
    }
}
