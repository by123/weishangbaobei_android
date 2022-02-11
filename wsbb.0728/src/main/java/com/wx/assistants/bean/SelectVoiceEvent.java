package com.wx.assistants.bean;

public class SelectVoiceEvent {
    private String voicePath;

    public SelectVoiceEvent() {
    }

    public SelectVoiceEvent(String str) {
        this.voicePath = str;
    }

    public String getVoicePath() {
        return this.voicePath;
    }

    public void setVoicePath(String str) {
        this.voicePath = str;
    }
}
