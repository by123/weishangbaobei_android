package com.wx.assistants.Enity;

public class TagBeanCompay {
    private Long id;
    private String wxCompany;
    private String wxName;

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

    public TagBeanCompay(Long l, String str, String str2) {
        this.id = l;
        this.wxName = str;
        this.wxCompany = str2;
    }

    public TagBeanCompay() {
    }
}
