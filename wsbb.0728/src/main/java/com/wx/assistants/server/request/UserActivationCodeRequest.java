package com.wx.assistants.server.request;

import com.google.gson.annotations.SerializedName;

public final class UserActivationCodeRequest {
    @SerializedName("activationCode")
    private String activationCode;
    @SerializedName("equipment")
    private String equipment;
    @SerializedName("phoneNumber")
    private String phoneNumber;

    public UserActivationCodeRequest(String str, String str2, String str3) {
        this.phoneNumber = str;
        this.activationCode = str2;
        this.equipment = str3;
    }
}
