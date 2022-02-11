package com.wx.assistants.bean;

public class DeviceBean {
    private String equipment;
    private boolean isSelected = false;
    private String member;
    private String userId;

    public String getEquipment() {
        return this.equipment;
    }

    public String getMember() {
        return this.member;
    }

    public String getUserId() {
        return this.userId;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setEquipment(String str) {
        this.equipment = str;
    }

    public void setMember(String str) {
        this.member = str;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public void setUserId(String str) {
        this.userId = str;
    }
}
