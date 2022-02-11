package com.wx.assistants.Enity;

public class GroupMemberBeanAll {
    private Long id;
    private String wxGroups;
    private String wxName;
    private String wxNum;

    public GroupMemberBeanAll() {
    }

    public GroupMemberBeanAll(Long l, String str, String str2, String str3) {
        this.id = l;
        this.wxName = str;
        this.wxNum = str2;
        this.wxGroups = str3;
    }

    public Long getId() {
        return this.id;
    }

    public String getWxGroups() {
        return this.wxGroups;
    }

    public String getWxName() {
        return this.wxName;
    }

    public String getWxNum() {
        return this.wxNum;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setWxGroups(String str) {
        this.wxGroups = str;
    }

    public void setWxName(String str) {
        this.wxName = str;
    }

    public void setWxNum(String str) {
        this.wxNum = str;
    }
}
