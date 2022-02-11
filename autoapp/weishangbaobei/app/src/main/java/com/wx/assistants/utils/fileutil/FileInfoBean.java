package com.wx.assistants.utils.fileutil;

public class FileInfoBean implements Comparable<FileInfoBean> {
    private String fileName;
    private String filePath;
    private long fileSize;

    public FileInfoBean() {
    }

    public FileInfoBean(String str, String str2, long j) {
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

    public int compareTo(FileInfoBean fileInfoBean) {
        return this.fileName.compareTo(fileInfoBean.fileName);
    }
}
