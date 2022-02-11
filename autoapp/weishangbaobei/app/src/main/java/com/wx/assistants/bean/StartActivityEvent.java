package com.wx.assistants.bean;

public class StartActivityEvent {
    private String className;

    public StartActivityEvent() {
    }

    public StartActivityEvent(String str) {
        this.className = str;
    }
}
