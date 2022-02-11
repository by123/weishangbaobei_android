package com.wx.assistants.server.request;

import com.google.gson.annotations.SerializedName;

public final class PasswordSettingRequest {
    @SerializedName("password")
    private String password;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("verificationCode")
    private String verificationCode;

    public PasswordSettingRequest(String str, String str2, String str3) {
        this.phoneNumber = str;
        this.password = str2;
        this.verificationCode = str3;
    }
}
