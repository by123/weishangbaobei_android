package com.wx.assistants.bean;

public class HomeBannerBean {
    private String createTime;
    private String id;
    private String picUrl;
    private String scrollBar;
    private String title;
    private String type;
    private String url;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getScrollBar() {
        return this.scrollBar;
    }

    public void setScrollBar(String str) {
        this.scrollBar = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(String str) {
        this.picUrl = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }
}
