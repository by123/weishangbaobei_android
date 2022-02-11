package com.wx.assistants.bean;

public class HomeItemBean {
    private Class className;
    private String descName;
    private int drawableBgId;
    private int drawableId;
    private boolean isFree;
    private String name;

    public HomeItemBean(Class cls, int i, int i2, String str, String str2, boolean z) {
        this.className = cls;
        this.drawableId = i2;
        this.drawableBgId = i;
        this.name = str;
        this.descName = str2;
        this.isFree = z;
    }

    public Class getClassName() {
        return this.className;
    }

    public String getDescName() {
        return this.descName;
    }

    public int getDrawableBgId() {
        return this.drawableBgId;
    }

    public int getDrawableId() {
        return this.drawableId;
    }

    public String getName() {
        return this.name;
    }

    public boolean isFree() {
        return this.isFree;
    }

    public void setClassName(Class cls) {
        this.className = cls;
    }

    public void setDescName(String str) {
        this.descName = str;
    }

    public void setDrawableBgId(int i) {
        this.drawableBgId = i;
    }

    public void setDrawableId(int i) {
        this.drawableId = i;
    }

    public void setFree(boolean z) {
        this.isFree = z;
    }

    public void setName(String str) {
        this.name = str;
    }
}
