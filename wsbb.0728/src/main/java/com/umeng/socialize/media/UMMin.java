package com.umeng.socialize.media;

import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.HashMap;
import java.util.Map;

public class UMMin extends BaseMediaObject {
    private String f;
    private String g;

    public UMMin(String str) {
        super(str);
    }

    public UMediaObject.MediaType getMediaType() {
        return UMediaObject.MediaType.WEBPAGE;
    }

    public String getPath() {
        return this.g;
    }

    public String getUserName() {
        return this.f;
    }

    public void setPath(String str) {
        this.g = str;
    }

    public void setUserName(String str) {
        this.f = str;
    }

    public byte[] toByte() {
        if (this.e != null) {
            return this.e.toByte();
        }
        return null;
    }

    public Map<String, Object> toUrlExtraParams() {
        HashMap hashMap = new HashMap();
        if (isUrlMedia()) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FURL, this.a);
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FTYPE, getMediaType());
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_TITLE, this.b);
        }
        return hashMap;
    }
}
