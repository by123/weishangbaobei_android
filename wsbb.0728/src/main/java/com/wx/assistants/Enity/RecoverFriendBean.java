package com.wx.assistants.Enity;

public class RecoverFriendBean {
    public boolean checked;
    private String deleteTime;
    private Long id;
    private boolean isAdded = false;
    private String wxNickName;
    private String wxNum;

    public RecoverFriendBean() {
    }

    public RecoverFriendBean(Long l, boolean z, String str, String str2, String str3, boolean z2) {
        this.id = l;
        this.isAdded = z;
        this.wxNum = str;
        this.wxNickName = str2;
        this.deleteTime = str3;
        this.checked = z2;
    }

    public RecoverFriendBean(String str, String str2, String str3, boolean z) {
        this.wxNum = str;
        this.wxNickName = str2;
        this.deleteTime = str3;
        this.checked = z;
    }

    public boolean getChecked() {
        return this.checked;
    }

    public String getDeleteTime() {
        return this.deleteTime;
    }

    public Long getId() {
        return this.id;
    }

    public boolean getIsAdded() {
        return this.isAdded;
    }

    public String getWxNickName() {
        return this.wxNickName;
    }

    public String getWxNum() {
        return this.wxNum;
    }

    public boolean isAdded() {
        return this.isAdded;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean z) {
        this.checked = z;
    }

    public void setDeleteTime(String str) {
        this.deleteTime = str;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setIsAdded(boolean z) {
        this.isAdded = z;
    }

    public void setWxNickName(String str) {
        this.wxNickName = str;
    }

    public void setWxNum(String str) {
        this.wxNum = str;
    }
}
