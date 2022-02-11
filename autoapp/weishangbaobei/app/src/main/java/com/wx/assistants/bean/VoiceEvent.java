package com.wx.assistants.bean;

public class VoiceEvent {
    private int voiceFragmentTag;

    public int getVoiceFragmentTag() {
        return this.voiceFragmentTag;
    }

    public void setVoiceFragmentTag(int i) {
        this.voiceFragmentTag = i;
    }

    public VoiceEvent(int i) {
        this.voiceFragmentTag = i;
    }
}
