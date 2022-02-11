package com.wx.assistants.Enity;

public class RecordBean {
    private long createTimeLong;
    private String createTimeString;
    private String h5Url;
    private Long id;
    private boolean isCollection;
    private boolean isFromWx;
    public boolean isPlayed;
    public boolean isPlaying;
    private String mp3LocPath;
    private String mp3SharedPath;
    private String path;
    private int second;
    private int voiceTag;

    public RecordBean() {
    }

    public RecordBean(Long l, String str, String str2, String str3, String str4, int i, boolean z, boolean z2, boolean z3, long j, String str5, int i2, boolean z4) {
        this.id = l;
        this.path = str;
        this.mp3LocPath = str2;
        this.h5Url = str3;
        this.mp3SharedPath = str4;
        this.second = i;
        this.isPlayed = z;
        this.isPlaying = z2;
        this.isCollection = z3;
        this.createTimeLong = j;
        this.createTimeString = str5;
        this.voiceTag = i2;
        this.isFromWx = z4;
    }

    public long getCreateTimeLong() {
        return this.createTimeLong;
    }

    public String getCreateTimeString() {
        return this.createTimeString;
    }

    public String getH5Url() {
        return this.h5Url;
    }

    public Long getId() {
        return this.id;
    }

    public boolean getIsCollection() {
        return this.isCollection;
    }

    public boolean getIsFromWx() {
        return this.isFromWx;
    }

    public boolean getIsPlayed() {
        return this.isPlayed;
    }

    public boolean getIsPlaying() {
        return this.isPlaying;
    }

    public String getMp3LocPath() {
        return this.mp3LocPath;
    }

    public String getMp3SharedPath() {
        return this.mp3SharedPath;
    }

    public String getPath() {
        return this.path;
    }

    public int getSecond() {
        return this.second;
    }

    public int getVoiceTag() {
        return this.voiceTag;
    }

    public void setCreateTimeLong(long j) {
        this.createTimeLong = j;
    }

    public void setCreateTimeString(String str) {
        this.createTimeString = str;
    }

    public void setH5Url(String str) {
        this.h5Url = str;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setIsCollection(boolean z) {
        this.isCollection = z;
    }

    public void setIsFromWx(boolean z) {
        this.isFromWx = z;
    }

    public void setIsPlayed(boolean z) {
        this.isPlayed = z;
    }

    public void setIsPlaying(boolean z) {
        this.isPlaying = z;
    }

    public void setMp3LocPath(String str) {
        this.mp3LocPath = str;
    }

    public void setMp3SharedPath(String str) {
        this.mp3SharedPath = str;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setSecond(int i) {
        this.second = i;
    }

    public void setVoiceTag(int i) {
        this.voiceTag = i;
    }
}
