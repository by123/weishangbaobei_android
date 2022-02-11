package com.wx.assistants.server.request;

import com.google.gson.annotations.SerializedName;

public final class MaterialForwardRequest {
    @SerializedName("id")
    private String id;

    public MaterialForwardRequest(String str) {
        this.id = str;
    }
}
