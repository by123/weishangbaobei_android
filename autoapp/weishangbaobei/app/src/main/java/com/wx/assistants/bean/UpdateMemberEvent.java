package com.wx.assistants.bean;

public class UpdateMemberEvent {
    private int tag = 0;

    public UpdateMemberEvent(int i) {
        setTag(i);
    }

    public int getTag() {
        return this.tag;
    }

    public void setTag(int i) {
        this.tag = i;
    }
}
