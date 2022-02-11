package com.wx.assistants.Enity;

public class TagMemberBeanCompany {
    private Long id;
    private String wxCompany;
    private String wxName;
    private String wxTags;

    public TagMemberBeanCompany() {
    }

    public TagMemberBeanCompany(Long l, String str, String str2, String str3) {
        this.id = l;
        this.wxName = str;
        this.wxCompany = str2;
        this.wxTags = str3;
    }

    public Long getId() {
        return this.id;
    }

    public String getWxCompany() {
        return this.wxCompany;
    }

    public String getWxName() {
        return this.wxName;
    }

    public String getWxTags() {
        return this.wxTags;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setWxCompany(String str) {
        this.wxCompany = str;
    }

    public void setWxName(String str) {
        this.wxName = str;
    }

    public void setWxTags(String str) {
        this.wxTags = str;
    }
}
