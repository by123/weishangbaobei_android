package com.wx.assistants.Enity;

public class MusicBean {
    private Long duration;
    private Long id;
    private String title;

    public MusicBean() {
    }

    public MusicBean(Long l, Long l2, String str) {
        this.id = l;
        this.duration = l2;
        this.title = str;
    }

    public Long getDuration() {
        return this.duration;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDuration(Long l) {
        this.duration = l;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
