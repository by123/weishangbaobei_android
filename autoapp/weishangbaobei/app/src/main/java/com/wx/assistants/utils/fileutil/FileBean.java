package com.wx.assistants.utils.fileutil;

public class FileBean implements Comparable<FileBean> {
    private String createTime;
    private String fileName;
    private String filePath;
    private long fileSize;
    private boolean selected;

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public FileBean() {
    }

    public FileBean(String str, String str2, long j) {
        this.fileName = str;
        this.filePath = str2;
        this.fileSize = j;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(long j) {
        this.fileSize = j;
    }

    public String toString() {
        return "FileInfoBean{fileName='" + this.fileName + '\'' + ", filePath='" + this.filePath + '\'' + ", fileSize=" + this.fileSize + '}';
    }

    public int compareTo(FileBean fileBean) {
        return this.fileName.compareTo(fileBean.fileName);
    }
}
