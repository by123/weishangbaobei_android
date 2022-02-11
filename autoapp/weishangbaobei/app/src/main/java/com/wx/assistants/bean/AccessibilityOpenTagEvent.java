package com.wx.assistants.bean;

public class AccessibilityOpenTagEvent {
    private int tag = 0;

    public AccessibilityOpenTagEvent(int i) {
        setTag(i);
    }

    public int getTag() {
        return this.tag;
    }

    public void setTag(int i) {
        this.tag = i;
    }
}
