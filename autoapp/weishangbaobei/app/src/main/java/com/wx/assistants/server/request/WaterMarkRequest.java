package com.wx.assistants.server.request;

import com.google.gson.annotations.SerializedName;

public final class WaterMarkRequest {
    @SerializedName("url")
    private String url;

    public WaterMarkRequest(String str) {
        this.url = str;
    }
}
