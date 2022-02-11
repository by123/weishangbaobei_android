package com.wx.assistants.Enity;

public classTagMemberBean {
    private Long id;
    private String wxName;
    private String wxNum;
    private String wxTags;

    public String getWxTags() {
        return this.wxTags;
    }

    public void setWxTags(String str) {
        this.wxTags = str;
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

    public TagMemberBean(Long l, String str, String str2, String str3) {
        this.id = l;
        this.wxName = str;
        this.wxNum = str2;
        this.wxTags = str3;
    }

    public TagMemberBean() {
    }
}
