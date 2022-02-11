package com.wx.assistants.server.request;

import com.google.gson.annotations.SerializedName;

public final class ZFBAccountRequest {
    @SerializedName("accountName")
    private String accountName;
    @SerializedName("accountNo")
    private String accountNo;
    @SerializedName("govtIdNo")
    private String govtIdNo;
    @SerializedName("govtIdType")
    private String govtIdType;

    public ZFBAccountRequest(String str, String str2, String str3, String str4) {
        this.accountName = str;
        this.accountNo = str2;
        this.govtIdNo = str3;
        this.govtIdNo = str3;
        this.govtIdType = str4;
    }
}
