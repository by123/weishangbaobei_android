package com.meiqia.meiqiasdk.model;

public class FileMessage extends BaseMessage {
    public static final int FILE_STATE_DOWNLOADING = 1;
    public static final int FILE_STATE_EXPIRED = 4;
    public static final int FILE_STATE_FAILED = 3;
    public static final int FILE_STATE_FINISH = 0;
    public static final int FILE_STATE_NOT_EXIST = 2;
    private String extra;
    private int fileState;
    private String localPath;
    private int progress;
    private String url;

    public FileMessage() {
        setItemViewType(0);
        setContentType("file");
        this.fileState = 2;
    }

    public FileMessage(String str) {
        this();
        this.url = str;
    }

    public String getExtra() {
        return this.extra;
    }

    public int getFileState() {
        return this.fileState;
    }

    public String getLocalPath() {
        return this.localPath;
    }

    public int getProgress() {
        return this.progress;
    }

    public String getUrl() {
        return this.url;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public void setFileState(int i) {
        this.fileState = i;
    }

    public void setLocalPath(String str) {
        this.localPath = str;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
