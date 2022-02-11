package com.wx.assistants.Enity;

public class GroupMemberBean {
    private Long id;
    private String wxGroups;
    private String wxName;
    private String wxNum;

    public String getWxGroups() {
        return this.wxGroups;
    }

    public void setWxGroups(String str) {
        this.wxGroups = str;
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

    public GroupMemberBean(Long l, String str, String str2, String str3) {
        this.id = l;
        this.wxName = str;
        this.wxNum = str2;
        this.wxGroups = str3;
    }

    public GroupMemberBean() {
    }
}
