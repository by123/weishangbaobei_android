package com.wx.assistants.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class FailureModel implements Parcelable {
    public static final Parcelable.Creator<FailureModel> CREATOR = new Parcelable.Creator<FailureModel>() {
        public FailureModel createFromParcel(Parcel parcel) {
            return new FailureModel(parcel);
        }

        public FailureModel[] newArray(int i) {
            return new FailureModel[i];
        }
    };
    private int code;
    private String errorBody;
    private String errorMessage;

    public int describeContents() {
        return 0;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public String getErrorBody() {
        return this.errorBody;
    }

    public void setErrorBody(String str) {
        this.errorBody = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.code);
        parcel.writeString(this.errorMessage);
        parcel.writeString(this.errorBody);
    }

    public FailureModel() {
    }

    protected FailureModel(Parcel parcel) {
        this.code = parcel.readInt();
        this.errorMessage = parcel.readString();
        this.errorBody = parcel.readString();
    }
}
