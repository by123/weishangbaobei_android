package com.wx.assistants.server.request;

import com.google.gson.annotations.SerializedName;

public final class PermissionRequest {
    @SerializedName("equipment")
    private String equipment;

    public PermissionRequest(String str) {
        this.equipment = str;
    }
}
