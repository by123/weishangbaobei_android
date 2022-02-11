package com.meiqia.meiqiasdk.model;

public class VoiceMessage extends BaseMessage {
    public static final int NO_DURATION = -1;
    private int duration;
    private String localPath;
    private String url;

    public VoiceMessage() {
        this.duration = -1;
        setItemViewType(0);
        setContentType("audio");
    }

    public VoiceMessage(String str) {
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

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }
}
