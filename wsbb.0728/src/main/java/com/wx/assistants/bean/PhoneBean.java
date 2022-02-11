package com.wx.assistants.bean;

import java.io.Serializable;

public class PhoneBean implements Serializable {
    public boolean checked;
    private String nickName;
    private String number;

    public PhoneBean() {
    }

    public PhoneBean(String str, String str2, boolean z) {
        this.nickName = str;
        this.number = str2;
        this.checked = z;
    }

    public PhoneBean(String str, boolean z) {
        this.number = str;
        this.checked = z;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getNumber() {
        return this.number;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean z) {
        this.checked = z;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public void setNumber(String str) {
        this.number = str;
    }
}
