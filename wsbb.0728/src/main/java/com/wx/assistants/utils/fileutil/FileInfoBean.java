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

    public int compareTo(FileInfoBean fileInfoBean) {
        return this.fileName.compareTo(fileInfoBean.fileName);
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

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public void setFileSize(long j) {
        this.fileSize = j;
    }

    public String toString() {
        return "FileInfoBean{fileName='" + this.fileName + '\'' + ", filePath='" + this.filePath + '\'' + ", fileSize=" + this.fileSize + '}';
    }
}
