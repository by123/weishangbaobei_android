package com.wx.assistants.bean;

public class FriendLabelBean {
    private String friendlable;
    private String lable;
    private Boolean select;

    public FriendLabelBean() {
    }

    public FriendLabelBean(String str, String str2, Boolean bool) {
        this.lable = str;
        this.friendlable = str2;
        this.select = bool;
    }

    public String getFriendlable() {
        return this.friendlable;
    }

    public String getLable() {
        return this.lable;
    }

    public Boolean getSelect() {
        return this.select;
    }

    public void setFriendlable(String str) {
        this.friendlable = str;
    }

    public void setLable(String str) {
        this.lable = str;
    }

    public void setSelect(Boolean bool) {
        this.select = bool;
    }
}
