package com.maning.updatelibrary.http;

import java.util.Map;

public class DownloadModel {
    private String downloadPath;
    private AbsFileProgressCallback fileProgressCallback;
    private Map<String, String> headersMap;
    private String httpUrl;
    private Object tag;

    public String getHttpUrl() {
        return this.httpUrl;
    }

    public void setHttpUrl(String str) {
        this.httpUrl = str;
    }

    public Map<String, String> getHeadersMap() {
        return this.headersMap;
    }

    public void setHeadersMap(Map<String, String> map) {
        this.headersMap = map;
    }

    public Object getTag() {
        return this.tag;
    }

    public void setTag(Object obj) {
        this.tag = obj;
    }

    public String getDownloadPath() {
        return this.downloadPath;
    }

    public void setDownloadPath(String str) {
        this.downloadPath = str;
    }

    public AbsFileProgressCallback getFileProgressCallback() {
        return this.fileProgressCallback;
    }

    public void setFileProgressCallback(AbsFileProgressCallback absFileProgressCallback) {
        this.fileProgressCallback = absFileProgressCallback;
    }
}
