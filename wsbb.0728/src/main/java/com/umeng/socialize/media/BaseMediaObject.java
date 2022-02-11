package com.umeng.socialize.media;

import android.os.Parcel;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseMediaObject implements UMediaObject {
    protected String a = "";
    protected String b = "";
    protected Map<String, Object> c = new HashMap();
    protected String d = "";
    protected UMImage e;
    public String mText = null;

    public BaseMediaObject() {
    }

    protected BaseMediaObject(Parcel parcel) {
        if (parcel != null) {
            this.a = parcel.readString();
            this.b = parcel.readString();
        }
    }

    public BaseMediaObject(String str) {
        this.a = str;
    }

    public String getDescription() {
        return this.d;
    }

    public UMImage getThumbImage() {
        return this.e;
    }

    public String getTitle() {
        return this.b;
    }

    public Map<String, Object> getmExtra() {
        return this.c;
    }

    public boolean isUrlMedia() {
        return !TextUtils.isEmpty(this.a);
    }

    public void setDescription(String str) {
        this.d = str;
    }

    public void setThumb(UMImage uMImage) {
        this.e = uMImage;
    }

    public void setTitle(String str) {
        this.b = str;
    }

    public void setmExtra(String str, Object obj) {
        this.c.put(str, obj);
    }

    public String toString() {
        return "BaseMediaObject [media_url=" + this.a + ", qzone_title=" + this.b + ", qzone_thumb=" + "]";
    }

    public String toUrl() {
        return this.a;
    }
}
