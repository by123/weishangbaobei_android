package com.wx.assistants.server.request;

import com.google.gson.annotations.SerializedName;

public final class CleanWaterMarkRequest {
    @SerializedName("client")
    private String client;
    @SerializedName("link")
    private String link;
    @SerializedName("sign")
    private String sign;
    @SerializedName("timestamp")
    private String timestamp;

    public CleanWaterMarkRequest(String str, String str2, String str3, String str4) {
        this.link = str;
        this.timestamp = str2;
        this.sign = str3;
        this.client = str4;
    }
}
