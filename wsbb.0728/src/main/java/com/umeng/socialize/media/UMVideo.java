package com.umeng.socialize.media;

import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.HashMap;
import java.util.Map;

public class UMVideo extends BaseMediaObject {
    private String f;
    private String g;
    private String h;
    private String i;
    private int j;

    public UMVideo(String str) {
        super(str);
    }

    public int getDuration() {
        return this.j;
    }

    public String getH5Url() {
        return this.i;
    }

    public String getHighBandDataUrl() {
        return this.h;
    }

    public String getLowBandDataUrl() {
        return this.g;
    }

    public String getLowBandUrl() {
        return this.f;
    }

    public UMediaObject.MediaType getMediaType() {
        return UMediaObject.MediaType.VEDIO;
    }

    public void setDuration(int i2) {
        this.j = i2;
    }

    public void setH5Url(String str) {
        this.i = str;
    }

    public void setHighBandDataUrl(String str) {
        this.h = str;
    }

    public void setLowBandDataUrl(String str) {
        this.g = str;
    }

    public void setLowBandUrl(String str) {
        this.f = str;
    }

    public byte[] toByte() {
        if (this.e != null) {
            return this.e.toByte();
        }
        return null;
    }

    public String toString() {
        return "UMVedio [media_url=" + this.a + ", qzone_title=" + this.b + ", qzone_thumb=" + "media_url=" + this.a + ", qzone_title=" + this.b + ", qzone_thumb=" + "]";
    }

    public final Map<String, Object> toUrlExtraParams() {
        HashMap hashMap = new HashMap();
        if (isUrlMedia()) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FURL, this.a);
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FTYPE, getMediaType());
        }
        return hashMap;
    }
}
