package com.wx.assistants.bean;

public class MemberItemBean {
    private boolean isChecked;
    private int type;
    private String type_aomnt;
    private String type_desc;
    private String type_name;

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getType_name() {
        return this.type_name;
    }

    public void setType_name(String str) {
        this.type_name = str;
    }

    public String getType_desc() {
        return this.type_desc;
    }

    public void setType_desc(String str) {
        this.type_desc = str;
    }

    public String getType_aomnt() {
        return this.type_aomnt;
    }

    public void setType_aomnt(String str) {
        this.type_aomnt = str;
    }
}
