package com.meiqia.core.bean;

public class MQClient {
    private String AESKey;
    private String appKey;
    private String bindUserId;
    private String browserId;
    private String enterpriseId = "1";
    private String trackId;
    private String visitId;
    private String visitPageId;

    public MQClient() {
    }

    public MQClient(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.appKey = str;
        this.bindUserId = str2;
        this.trackId = str3;
        this.AESKey = str4;
        this.enterpriseId = str5;
        this.browserId = str6;
        this.visitPageId = str7;
        this.visitId = str8;
    }

    public String getAESKey() {
        return this.AESKey;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public String getBindUserId() {
        return this.bindUserId;
    }

    public String getBrowserId() {
        return this.browserId;
    }

    public String getEnterpriseId() {
        return this.enterpriseId;
    }

    public String getTrackId() {
        return this.trackId;
    }

    public String getVisitId() {
        return this.visitId;
    }

    public String getVisitPageId() {
        return this.visitPageId;
    }

    public void setAESKey(String str) {
        this.AESKey = str;
    }

    public void setAppKey(String str) {
        this.appKey = str;
    }

    public void setBindUserId(String str) {
        this.bindUserId = str;
    }

    public void setBrowserId(String str) {
        this.browserId = str;
    }

    public void setEnterpriseId(String str) {
        this.enterpriseId = str;
    }

    public void setTrackId(String str) {
        this.trackId = str;
    }

    public void setVisitId(String str) {
        this.visitId = str;
    }

    public void setVisitPageId(String str) {
        this.visitPageId = str;
    }
}
