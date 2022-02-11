package com.wx.assistants.Enity;

import java.io.Serializable;

public class ContactScanBean implements Serializable {
    private int addResultType;
    public boolean checked;
    private long createTime;
    private String extra;
    private Long id;
    private boolean isAdded;
    private String nickName;
    private String number;

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public int getAddResultType() {
        return this.addResultType;
    }

    public void setAddResultType(int i) {
        this.addResultType = i;
    }

    public boolean getIsAdded() {
        return this.isAdded;
    }

    public void setIsAdded(boolean z) {
        this.isAdded = z;
    }

    public boolean getChecked() {
        return this.checked;
    }

    public void setChecked(boolean z) {
        this.checked = z;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public ContactScanBean(Long l, String str, String str2, boolean z, boolean z2, int i, String str3, long j) {
        this.id = l;
        this.number = str;
        this.nickName = str2;
        this.checked = z;
        this.isAdded = z2;
        this.addResultType = i;
        this.extra = str3;
        this.createTime = j;
    }

    public ContactScanBean() {
    }
}
