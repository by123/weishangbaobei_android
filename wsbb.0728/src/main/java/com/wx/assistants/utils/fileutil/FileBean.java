package com.wx.assistants.utils.fileutil;

public class FileBean implements Comparable<FileBean> {
    private String createTime;
    private String fileName;
    private String filePath;
    private long fileSize;
    private boolean selected;

    public FileBean() {
    }

    public FileBean(String str, String str2, long j) {
        this.fileName = str;
        this.filePath = str2;
        this.fileSize = j;
    }

    public int compareTo(FileBean fileBean) {
        return this.fileName.compareTo(fileBean.fileName);
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public void setFileSize(long j) {
        this.fileSize = j;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public String toString() {
        return "FileInfoBean{fileName='" + this.fileName + '\'' + ", filePath='" + this.filePath + '\'' + ", fileSize=" + this.fileSize + '}';
    }
}
