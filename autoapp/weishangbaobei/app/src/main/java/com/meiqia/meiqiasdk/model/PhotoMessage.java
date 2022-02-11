package com.meiqia.meiqiasdk.model;

public class PhotoMessage extends BaseMessage {
    private String localPath;
    private String url;

    public PhotoMessage() {
        setItemViewType(0);
        setContentType("photo");
    }

    public PhotoMessage(String str) {
        this();
        this.url = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getLocalPath() {
        return this.localPath;
    }

    public void setLocalPath(String str) {
        this.localPath = str;
    }
}
