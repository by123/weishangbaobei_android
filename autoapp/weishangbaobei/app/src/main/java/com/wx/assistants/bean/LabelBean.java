package com.wx.assistants.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class LabelBean implements Parcelable {
    public static final Parcelable.Creator<LabelBean> CREATOR = new Parcelable.Creator<LabelBean>() {
        public LabelBean createFromParcel(Parcel parcel) {
            return new LabelBean(parcel);
        }

        public LabelBean[] newArray(int i) {
            return new LabelBean[i];
        }
    };
    private boolean isSelected = false;
    private String labelName;
    private String labelNameText;

    public int describeContents() {
        return 0;
    }

    public static Parcelable.Creator<LabelBean> getCREATOR() {
        return CREATOR;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public LabelBean(String str, String str2) {
        this.labelName = str;
        this.labelNameText = str2;
    }

    public String toString() {
        return "LabelBean{labelName='" + this.labelName + '\'' + ", labelNameText='" + this.labelNameText + '\'' + '}';
    }

    public LabelBean() {
    }

    public String getLabelName() {
        return this.labelName;
    }

    public void setLabelName(String str) {
        this.labelName = str;
    }

    public String getLabelNameText() {
        return this.labelNameText;
    }

    public void setLabelNameText(String str) {
        this.labelNameText = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.labelName);
        parcel.writeString(this.labelNameText);
        parcel.writeByte(this.isSelected ? (byte) 1 : 0);
    }

    protected LabelBean(Parcel parcel) {
        boolean z = false;
        this.labelName = parcel.readString();
        this.labelNameText = parcel.readString();
        this.isSelected = parcel.readByte() != 0 ? true : z;
    }
}
