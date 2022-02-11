package com.wx.assistants.Enity;

import java.io.Serializable;

public class GroupBean implements Serializable {
    private Long id;
    private String wxName;
    private String wxNum;

    public GroupBean() {
    }

    public GroupBean(Long l, String str, String str2) {
        this.id = l;
        this.wxName = str;
        this.wxNum = str2;
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

    public void setId(Long l) {
        this.id = l;
    }

    public void setWxName(String str) {
        this.wxName = str;
    }

    public void setWxNum(String str) {
        this.wxNum = str;
    }
}
