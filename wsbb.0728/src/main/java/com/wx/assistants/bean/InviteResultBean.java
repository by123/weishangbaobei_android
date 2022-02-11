package com.wx.assistants.bean;

public class InviteResultBean {
    private String inviteUrl;
    private String qrcode;

    public String getInviteUrl() {
        return this.inviteUrl;
    }

    public String getQrcode() {
        return this.qrcode;
    }

    public void setInviteUrl(String str) {
        this.inviteUrl = str;
    }

    public void setQrcode(String str) {
        this.qrcode = str;
    }
}
