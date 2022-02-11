package com.umeng.socialize.media;

import android.os.Parcel;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.HashMap;
import java.util.Map;

public class UMusic extends BaseMediaObject {
    private String f;
    private String g;
    private String h;
    private String i;
    private int j;
    private String k;

    protected UMusic(Parcel parcel) {
        super(parcel);
    }

    public UMusic(String str) {
        super(str);
    }

    public int getDuration() {
        return this.j;
    }

    public String getH5Url() {
        return this.h;
    }

    public String getHighBandDataUrl() {
        return this.g;
    }

    public String getLowBandDataUrl() {
        return this.f;
    }

    public String getLowBandUrl() {
        return this.i;
    }

    public UMediaObject.MediaType getMediaType() {
        return UMediaObject.MediaType.MUSIC;
    }

    public UMImage getThumbImage() {
        return this.e;
    }

    public String getmTargetUrl() {
        return this.k;
    }

    public void setDuration(int i2) {
        this.j = i2;
    }

    public void setH5Url(String str) {
        this.h = str;
    }

    public void setHighBandDataUrl(String str) {
        this.g = str;
    }

    public void setLowBandDataUrl(String str) {
        this.f = str;
    }

    public void setLowBandUrl(String str) {
        this.i = str;
    }

    public void setmTargetUrl(String str) {
        this.k = str;
    }

    public byte[] toByte() {
        if (this.e != null) {
            return this.e.toByte();
        }
        return null;
    }

    public String toString() {
        return "UMusic [title=" + this.b + "media_url=" + this.a + ", qzone_title=" + this.b + ", qzone_thumb=" + "]";
    }

    public final Map<String, Object> toUrlExtraParams() {
        HashMap hashMap = new HashMap();
        if (isUrlMedia()) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FURL, this.a);
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FTYPE, getMediaType());
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_TITLE, this.b);
        }
        return hashMap;
    }
}
