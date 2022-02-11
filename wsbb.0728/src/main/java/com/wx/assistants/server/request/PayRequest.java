package com.wx.assistants.server.request;

import com.google.gson.annotations.SerializedName;

public final class PayRequest {
    @SerializedName("equipment")
    private String equipment;
    @SerializedName("info")
    private String info;
    @SerializedName("member")
    private String member;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("price")
    private int price;

    public PayRequest(String str, String str2, int i, String str3, String str4) {
        this.equipment = str;
        this.phoneNumber = str2;
        this.price = i;
        this.member = str3;
        this.info = str4;
    }
}
