package com.wx.assistants.bean;

public class BindInvitationModel {
    private String phoneNumber;

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public BindInvitationModel(String str) {
        this.phoneNumber = str;
    }
}
