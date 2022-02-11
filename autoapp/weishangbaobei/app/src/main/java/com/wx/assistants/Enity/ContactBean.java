package com.wx.assistants.Enity;

import java.io.Serializable;

public class ContactBean implements Serializable {
    public boolean checked;
    private String extra;
    private Long id;
    private boolean isAdded;
    private String nickName;
    private String number;

    public boolean isAdded() {
        return this.isAdded;
    }

    public void setAdded(boolean z) {
        this.isAdded = z;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public ContactBean() {
    }

    public ContactBean(String str, boolean z) {
        this.number = str;
        this.checked = z;
    }

    public ContactBean(String str, String str2, boolean z) {
        this.nickName = str;
        this.number = str2;
        this.checked = z;
    }

    public ContactBean(Long l, String str, String str2, boolean z, boolean z2, String str3) {
        this.id = l;
        this.number = str;
        this.nickName = str2;
        this.checked = z;
        this.isAdded = z2;
        this.extra = str3;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean z) {
        this.checked = z;
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
}
