package com.wx.assistants.server.request;

import com.google.gson.annotations.SerializedName;

public final class LoginRequest {
    @SerializedName("equipment")
    private String equipment;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("verificationCode")
    private String verificationCode;

    public LoginRequest(String str, String str2, String str3) {
        this.phoneNumber = str;
        this.equipment = str2;
        this.verificationCode = str3;
    }
}
