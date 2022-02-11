package com.yongchun.library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LocalMediaFolder implements Serializable {
    private String firstImagePath;
    private int imageNum;
    private List<LocalMedia> images = new ArrayList();
    private String name;
    private String path;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getFirstImagePath() {
        return this.firstImagePath;
    }

    public void setFirstImagePath(String str) {
        this.firstImagePath = str;
    }

    public int getImageNum() {
        return this.imageNum;
    }

    public void setImageNum(int i) {
        this.imageNum = i;
    }

    public List<LocalMedia> getImages() {
        return this.images;
    }

    public void setImages(List<LocalMedia> list) {
        this.images = list;
    }
}
