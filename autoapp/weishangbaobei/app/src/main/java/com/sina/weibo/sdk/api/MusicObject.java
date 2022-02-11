package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class MusicObject extends BaseMediaObject {
    public static final Parcelable.Creator<MusicObject> CREATOR = new Parcelable.Creator() {
        public MusicObject createFromParcel(Parcel parcel) {
            return new MusicObject(parcel);
        }

        public MusicObject[] newArray(int i) {
            return new MusicObject[i];
        }
    };
    public static final String EXTRA_KEY_DEFAULTTEXT = "extra_key_defaulttext";
    public String dataHdUrl;
    public String dataUrl;
    public String defaultText;
    public int duration;
    public String h5Url;

    public MusicObject() {
    }

    public MusicObject(Parcel parcel) {
        super(parcel);
        this.h5Url = parcel.readString();
        this.dataUrl = parcel.readString();
        this.dataHdUrl = parcel.readString();
        this.duration = parcel.readInt();
    }

    public boolean checkArgs() {
        if (!super.checkArgs()) {
            return false;
        }
        if (this.dataUrl == null || this.dataUrl.length() <= 512) {
            return (this.dataHdUrl == null || this.dataHdUrl.length() <= 512) && this.duration > 0;
        }
        return false;
    }

    public int getObjType() {
        return 3;
    }

    /* access modifiers changed from: protected */
    public BaseMediaObject toExtraMediaObject(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                this.defaultText = new JSONObject(str).optString("extra_key_defaulttext");
            } catch (JSONException unused) {
            }
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public String toExtraMediaString() {
        try {
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(this.defaultText)) {
                jSONObject.put("extra_key_defaulttext", this.defaultText);
            }
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.h5Url);
        parcel.writeString(this.dataUrl);
        parcel.writeString(this.dataHdUrl);
        parcel.writeInt(this.duration);
    }
}
