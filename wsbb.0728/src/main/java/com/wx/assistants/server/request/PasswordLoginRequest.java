package com.wx.assistants.server.request;

import com.google.gson.annotations.SerializedName;

public final class PasswordLoginRequest {
    @SerializedName("password")
    private String password;
    @SerializedName("phoneNumber")
    private String phoneNumber;

    public PasswordLoginRequest(String str, String str2) {
        this.phoneNumber = str;
        this.password = str2;
    }
}
