package com.umeng.socialize.utils;

import android.content.Context;
import android.os.Build;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.utils.AesHelper;

public class URLBuilder {
    private String idmd5 = null;
    private String imei = null;
    private String mAppkey = null;
    private String mEntityKey = null;
    private String mHost = null;
    private String mOpId = null;
    private String mPath = null;
    private String mPlatfrom = null;
    private String mReqType = "0";
    private String mSessionId = null;
    private String mUID = null;
    private String mac = null;
    private String model = null;
    private String network = null;
    private String os = null;
    private String protoversion = null;
    private String sdkversion = null;
    private String ts = null;

    public URLBuilder(Context context) {
        this.imei = DeviceConfig.getDeviceId(context);
        if (this.imei != null) {
            this.idmd5 = AesHelper.md5(this.imei);
        }
        this.mac = DeviceConfig.getMac(context);
        this.network = DeviceConfig.getNetworkAccessMode(context)[0];
        this.model = Build.MODEL;
        this.sdkversion = "6.4.5";
        this.os = "Android";
        this.ts = String.valueOf(System.currentTimeMillis());
        this.protoversion = SocializeConstants.PROTOCOL_VERSON;
    }

    public URLBuilder setHost(String str) {
        this.mHost = str;
        return this;
    }

    public URLBuilder setPath(String str) {
        this.mPath = str;
        return this;
    }

    public URLBuilder setAppkey(String str) {
        this.mAppkey = str;
        return this;
    }

    public URLBuilder setEntityKey(String str) {
        this.mEntityKey = str;
        return this;
    }

    public URLBuilder withMedia(SHARE_MEDIA share_media) {
        this.mPlatfrom = share_media.toString();
        return this;
    }

    public URLBuilder withOpId(String str) {
        this.mOpId = str;
        return this;
    }

    public URLBuilder withSessionId(String str) {
        this.mSessionId = str;
        return this;
    }

    public URLBuilder withUID(String str) {
        this.mUID = str;
        return this;
    }

    public String to() {
        return this.mHost + this.mPath + this.mAppkey + "/" + this.mEntityKey + "/?" + buildParams();
    }

    public String toEncript() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mHost);
        sb.append(this.mPath);
        sb.append(this.mAppkey);
        sb.append("/");
        sb.append(this.mEntityKey);
        sb.append("/?");
        String buildParams = buildParams();
        Log.net("base url: " + sb.toString());
        Log.net("params: " + buildParams);
        AesHelper.setPassword(this.mAppkey);
        try {
            Log.net("URLBuilder url=" + buildParams);
            String encryptNoPadding = AesHelper.encryptNoPadding(buildParams, "UTF-8");
            sb.append("ud_get=");
            sb.append(encryptNoPadding);
        } catch (Exception unused) {
            Log.w("fail to encrypt query string");
            sb.append(buildParams);
        }
        return sb.toString();
    }

    private String buildParams() {
        StringBuilder sb = new StringBuilder();
        sb.append("via=");
        sb.append(this.mPlatfrom.toLowerCase());
        sb.append("&opid=");
        sb.append(this.mOpId);
        sb.append("&ak=");
        sb.append(this.mAppkey);
        sb.append("&pcv=");
        sb.append(this.protoversion);
        sb.append("&tp=");
        sb.append(this.mReqType);
        if (this.imei != null) {
            sb.append("&imei=");
            sb.append(this.imei);
        }
        if (this.idmd5 != null) {
            sb.append("&md5imei=");
            sb.append(this.idmd5);
        }
        if (this.mac != null) {
            sb.append("&mac=");
            sb.append(this.mac);
        }
        if (this.network != null) {
            sb.append("&en=");
            sb.append(this.network);
        }
        if (this.model != null) {
            sb.append("&de=");
            sb.append(this.model);
        }
        if (this.sdkversion != null) {
            sb.append("&sdkv=");
            sb.append(this.sdkversion);
        }
        if (this.os != null) {
            sb.append("&os=");
            sb.append(this.os);
        }
        if (this.ts != null) {
            sb.append("&dt=");
            sb.append(this.ts);
        }
        if (this.mUID != null) {
            sb.append("&uid=");
            sb.append(this.mUID);
        }
        if (this.mEntityKey != null) {
            sb.append("&ek=");
            sb.append(this.mEntityKey);
        }
        if (this.mSessionId != null) {
            sb.append("&sid=");
            sb.append(this.mSessionId);
        }
        return sb.toString();
    }
}
