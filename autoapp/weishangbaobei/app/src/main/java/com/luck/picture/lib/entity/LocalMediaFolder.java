package com.luck.picture.lib.entity;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class LocalMediaFolder implements Parcelable {
    public static final Parcelable.Creator<LocalMediaFolder> CREATOR = new Parcelable.Creator<LocalMediaFolder>() {
        public LocalMediaFolder createFromParcel(Parcel parcel) {
            return new LocalMediaFolder(parcel);
        }

        public LocalMediaFolder[] newArray(int i) {
            return new LocalMediaFolder[i];
        }
    };
    private int checkedNum;
    private String firstImagePath;
    private int imageNum;
    private List<LocalMedia> images = new ArrayList();
    private boolean isChecked;
    private String name;
    private String path;

    public int describeContents() {
        return 0;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

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
        if (this.images == null) {
            this.images = new ArrayList();
        }
        return this.images;
    }

    public void setImages(List<LocalMedia> list) {
        this.images = list;
    }

    public int getCheckedNum() {
        return this.checkedNum;
    }

    public void setCheckedNum(int i) {
        this.checkedNum = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.path);
        parcel.writeString(this.firstImagePath);
        parcel.writeInt(this.imageNum);
        parcel.writeInt(this.checkedNum);
        parcel.writeByte(this.isChecked ? (byte) 1 : 0);
        parcel.writeTypedList(this.images);
    }

    public LocalMediaFolder() {
    }

    protected LocalMediaFolder(Parcel parcel) {
        this.name = parcel.readString();
        this.path = parcel.readString();
        this.firstImagePath = parcel.readString();
        this.imageNum = parcel.readInt();
        this.checkedNum = parcel.readInt();
        this.isChecked = parcel.readByte() != 0;
        this.images = parcel.createTypedArrayList(LocalMedia.CREATOR);
    }
}
