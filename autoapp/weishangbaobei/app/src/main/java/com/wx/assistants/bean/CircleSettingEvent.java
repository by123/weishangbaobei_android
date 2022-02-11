package com.wx.assistants.bean;

public class CircleSettingEvent {
    private String circleModel;
    private String selectGroups;
    private String selectTags;

    public String getCircleModel() {
        return this.circleModel;
    }

    public void setCircleModel(String str) {
        this.circleModel = str;
    }

    public String getSelectTags() {
        return this.selectTags;
    }

    public void setSelectTags(String str) {
        this.selectTags = str;
    }

    public String getSelectGroups() {
        return this.selectGroups;
    }

    public void setSelectGroups(String str) {
        this.selectGroups = str;
    }

    public CircleSettingEvent(String str, String str2, String str3) {
        this.circleModel = str;
        this.selectTags = str2;
        this.selectGroups = str3;
    }
}
