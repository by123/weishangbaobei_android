package com.luck.picture.lib.entity;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class EventEntity implements Parcelable {
    public static final Parcelable.Creator<EventEntity> CREATOR = new Parcelable.Creator<EventEntity>() {
        public EventEntity createFromParcel(Parcel parcel) {
            return new EventEntity(parcel);
        }

        public EventEntity[] newArray(int i) {
            return new EventEntity[i];
        }
    };
    public List<LocalMedia> medias = new ArrayList();
    public int position;
    public int what;

    public int describeContents() {
        return 0;
    }

    public EventEntity() {
    }

    public EventEntity(int i) {
        this.what = i;
    }

    public EventEntity(int i, List<LocalMedia> list) {
        this.what = i;
        this.medias = list;
    }

    public EventEntity(int i, int i2) {
        this.what = i;
        this.position = i2;
    }

    public EventEntity(int i, List<LocalMedia> list, int i2) {
        this.what = i;
        this.position = i2;
        this.medias = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.what);
        parcel.writeInt(this.position);
        parcel.writeTypedList(this.medias);
    }

    protected EventEntity(Parcel parcel) {
        this.what = parcel.readInt();
        this.position = parcel.readInt();
        this.medias = parcel.createTypedArrayList(LocalMedia.CREATOR);
    }
}
