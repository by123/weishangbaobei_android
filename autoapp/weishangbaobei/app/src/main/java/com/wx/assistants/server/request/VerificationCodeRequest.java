package com.wx.assistants.server.request;

import com.google.gson.annotations.SerializedName;

public final class VerificationCodeRequest {
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("type")
    private int type;

    public VerificationCodeRequest(String str, int i) {
        this.phoneNumber = str;
        this.type = i;
    }
}
