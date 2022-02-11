package com.wx.assistants.Enity;

public class CopyFriendBean {
    public boolean checked;
    private String deleteTime;
    private Long id;
    private boolean isAdded = false;
    private String wxNickName;
    private String wxNum;
    private String wxRemarkName;

    public CopyFriendBean() {
    }

    public CopyFriendBean(Long l, boolean z, String str, String str2, String str3, String str4, boolean z2) {
        this.id = l;
        this.isAdded = z;
        this.wxNum = str;
        this.wxNickName = str2;
        this.wxRemarkName = str3;
        this.deleteTime = str4;
        this.checked = z2;
    }

    public CopyFriendBean(String str, String str2, String str3, String str4, boolean z) {
        this.wxNum = str;
        this.wxNickName = str2;
        this.wxRemarkName = str3;
        this.deleteTime = str4;
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

    public String getWxRemarkName() {
        return this.wxRemarkName;
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

    public void setWxRemarkName(String str) {
        this.wxRemarkName = str;
    }
}
