package com.wx.assistants.bean;

public class MemberItemBean {
    private boolean isChecked;
    private int type;
    private String type_aomnt;
    private String type_desc;
    private String type_name;

    public int getType() {
        return this.type;
    }

    public String getType_aomnt() {
        return this.type_aomnt;
    }

    public String getType_desc() {
        return this.type_desc;
    }

    public String getType_name() {
        return this.type_name;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setType_aomnt(String str) {
        this.type_aomnt = str;
    }

    public void setType_desc(String str) {
        this.type_desc = str;
    }

    public void setType_name(String str) {
        this.type_name = str;
    }
}
