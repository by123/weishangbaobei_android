package com.maning.updatelibrary.http;

import java.util.Map;

public class DownloadModel {
    private String downloadPath;
    private AbsFileProgressCallback fileProgressCallback;
    private Map<String, String> headersMap;
    private String httpUrl;
    private Object tag;

    public String getDownloadPath() {
        return this.downloadPath;
    }

    public AbsFileProgressCallback getFileProgressCallback() {
        return this.fileProgressCallback;
    }

    public Map<String, String> getHeadersMap() {
        return this.headersMap;
    }

    public String getHttpUrl() {
        return this.httpUrl;
    }

    public Object getTag() {
        return this.tag;
    }

    public void setDownloadPath(String str) {
        this.downloadPath = str;
    }

    public void setFileProgressCallback(AbsFileProgressCallback absFileProgressCallback) {
        this.fileProgressCallback = absFileProgressCallback;
    }

    public void setHeadersMap(Map<String, String> map) {
        this.headersMap = map;
    }

    public void setHttpUrl(String str) {
        this.httpUrl = str;
    }

    public void setTag(Object obj) {
        this.tag = obj;
    }
}
