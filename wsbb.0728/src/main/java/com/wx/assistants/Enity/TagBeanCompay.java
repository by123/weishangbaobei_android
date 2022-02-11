package com.wx.assistants.Enity;

public class TagBeanCompay {
    private Long id;
    private String wxCompany;
    private String wxName;

    public TagBeanCompay() {
    }

    public TagBeanCompay(Long l, String str, String str2) {
        this.id = l;
        this.wxName = str;
        this.wxCompany = str2;
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

    public void setId(Long l) {
        this.id = l;
    }

    public void setWxCompany(String str) {
        this.wxCompany = str;
    }

    public void setWxName(String str) {
        this.wxName = str;
    }
}
