package com.yalantis.ucrop.model;

public class ExifInfo {
    private int mExifDegrees;
    private int mExifOrientation;
    private int mExifTranslation;

    public ExifInfo(int i, int i2, int i3) {
        this.mExifOrientation = i;
        this.mExifDegrees = i2;
        this.mExifTranslation = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ExifInfo exifInfo = (ExifInfo) obj;
        if (this.mExifOrientation != exifInfo.mExifOrientation) {
            return false;
        }
        if (this.mExifDegrees != exifInfo.mExifDegrees) {
            return false;
        }
        return this.mExifTranslation == exifInfo.mExifTranslation;
    }

    public int getExifDegrees() {
        return this.mExifDegrees;
    }

    public int getExifOrientation() {
        return this.mExifOrientation;
    }

    public int getExifTranslation() {
        return this.mExifTranslation;
    }

    public int hashCode() {
        return (((this.mExifOrientation * 31) + this.mExifDegrees) * 31) + this.mExifTranslation;
    }

    public void setExifDegrees(int i) {
        this.mExifDegrees = i;
    }

    public void setExifOrientation(int i) {
        this.mExifOrientation = i;
    }

    public void setExifTranslation(int i) {
        this.mExifTranslation = i;
    }
}
