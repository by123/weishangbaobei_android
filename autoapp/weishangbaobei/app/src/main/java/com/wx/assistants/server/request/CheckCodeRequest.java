package com.wx.assistants.server.request;

import com.google.gson.annotations.SerializedName;

public final class CheckCodeRequest {
    @SerializedName("code")
    private String code;

    public CheckCodeRequest(String str) {
        this.code = str;
    }
}
