package com.wx.assistants.server.request;

import com.google.gson.annotations.SerializedName;

public final class UserExtractRequest {
    @SerializedName("accountName")
    private String accountName;
    @SerializedName("accountNo")
    private String accountNo;
    @SerializedName("money")
    private int money;

    public UserExtractRequest(String str, String str2, int i) {
        this.money = i;
        this.accountNo = str;
        this.accountName = str2;
    }
}
