package com.wx.assistants.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class AppVersionModel implements Parcelable {
    public static final Parcelable.Creator<AppVersionModel> CREATOR = new Parcelable.Creator<AppVersionModel>() {
        public AppVersionModel createFromParcel(Parcel parcel) {
            return new AppVersionModel(parcel);
        }

        public AppVersionModel[] newArray(int i) {
            return new AppVersionModel[i];
        }
    };
    private String shareUrl;
    private BaseVersion version;

    public static class BaseVersion implements Parcelable {
        public static final Parcelable.Creator<BaseVersion> CREATOR = new Parcelable.Creator<BaseVersion>() {
            public BaseVersion createFromParcel(Parcel parcel) {
                return new BaseVersion(parcel);
            }

            public BaseVersion[] newArray(int i) {
                return new BaseVersion[i];
            }
        };
        private String appPlatform;
        private String createTime;
        private String downloadUrl;
        private int isForce = 0;
        private String marketCode;
        private String shieldChannel;
        private int versionCode;
        private String versionInstructions;
        private String versionName;

        public BaseVersion() {
        }

        protected BaseVersion(Parcel parcel) {
            this.versionCode = parcel.readInt();
            this.versionName = parcel.readString();
            this.versionInstructions = parcel.readString();
            this.marketCode = parcel.readString();
            this.createTime = parcel.readString();
            this.isForce = parcel.readInt();
            this.downloadUrl = parcel.readString();
            this.appPlatform = parcel.readString();
            this.shieldChannel = parcel.readString();
        }

        public int describeContents() {
            return 0;
        }

        public String getAppPlatform() {
            return this.appPlatform;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public String getDownloadUrl() {
            return this.downloadUrl;
        }

        public int getIsForce() {
            return this.isForce;
        }

        public String getMarketCode() {
            return this.marketCode;
        }

        public String getShieldChannel() {
            return this.shieldChannel;
        }

        public int getVersionCode() {
            return this.versionCode;
        }

        public String getVersionInstructions() {
            return this.versionInstructions;
        }

        public String getVersionName() {
            return this.versionName;
        }

        public void setAppPlatform(String str) {
            this.appPlatform = str;
        }

        public void setCreateTime(String str) {
            this.createTime = str;
        }

        public void setDownloadUrl(String str) {
            this.downloadUrl = str;
        }

        public void setIsForce(int i) {
            this.isForce = i;
        }

        public void setMarketCode(String str) {
            this.marketCode = str;
        }

        public void setShieldChannel(String str) {
            this.shieldChannel = str;
        }

        public void setVersionCode(int i) {
            this.versionCode = i;
        }

        public void setVersionInstructions(String str) {
            this.versionInstructions = str;
        }

        public void setVersionName(String str) {
            this.versionName = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.versionCode);
            parcel.writeString(this.versionName);
            parcel.writeString(this.versionInstructions);
            parcel.writeString(this.marketCode);
            parcel.writeString(this.createTime);
            parcel.writeInt(this.isForce);
            parcel.writeString(this.downloadUrl);
            parcel.writeString(this.appPlatform);
            parcel.writeString(this.shieldChannel);
        }
    }

    public AppVersionModel() {
    }

    protected AppVersionModel(Parcel parcel) {
        this.version = (BaseVersion) parcel.readParcelable(BaseVersion.class.getClassLoader());
        this.shareUrl = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getShareUrl() {
        return this.shareUrl;
    }

    public BaseVersion getVersion() {
        return this.version;
    }

    public void setShareUrl(String str) {
        this.shareUrl = str;
    }

    public void setVersion(BaseVersion baseVersion) {
        this.version = baseVersion;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.version, i);
        parcel.writeString(this.shareUrl);
    }
}
