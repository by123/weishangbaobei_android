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

    public ContactScanBean() {
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

    public int getAddResultType() {
        return this.addResultType;
    }

    public boolean getChecked() {
        return this.checked;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public String getExtra() {
        return this.extra;
    }

    public Long getId() {
        return this.id;
    }

    public boolean getIsAdded() {
        return this.isAdded;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getNumber() {
        return this.number;
    }

    public void setAddResultType(int i) {
        this.addResultType = i;
    }

    public void setChecked(boolean z) {
        this.checked = z;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setIsAdded(boolean z) {
        this.isAdded = z;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public void setNumber(String str) {
        this.number = str;
    }
}
