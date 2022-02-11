package com.umeng.socialize.net.base;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.socialize.Config;
import com.umeng.socialize.SocializeException;
import com.umeng.socialize.a.a.a;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.media.BaseMediaObject;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.net.utils.AesHelper;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.net.utils.URequest;
import com.umeng.socialize.utils.DeviceConfig;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public abstract class SocializeRequest extends URequest {
    private static final String BASE_URL = "http://log.umsns.com/";
    public static final int REQUEST_ANALYTIC = 1;
    public static final int REQUEST_API = 2;
    public static final int REQUEST_SOCIAL = 0;
    private static final String TAG = "SocializeRequest";
    private Map<String, URequest.FilePair> mFileMap = new HashMap();
    public int mOpId;
    private int mReqType = 1;

    /* renamed from: com.umeng.socialize.net.base.SocializeRequest$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$umeng$socialize$net$utils$URequest$RequestMethod = new int[URequest.RequestMethod.values().length];

        static {
            try {
                $SwitchMap$com$umeng$socialize$net$utils$URequest$RequestMethod[URequest.RequestMethod.POST.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$umeng$socialize$net$utils$URequest$RequestMethod[URequest.RequestMethod.GET.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum FILE_TYPE {
        IMAGE,
        VEDIO
    }

    public SocializeRequest(Context context, String str, Class<? extends SocializeReseponse> cls, int i, URequest.RequestMethod requestMethod) {
        super("");
        this.mResponseClz = cls;
        this.mOpId = i;
        this.mContext = context;
        this.mMethod = requestMethod;
        setBaseUrl(BASE_URL);
        AesHelper.setPassword(SocializeUtils.getAppkey(context));
    }

    public static Map<String, Object> getBaseQuery(Context context) {
        HashMap hashMap = new HashMap();
        String deviceId = DeviceConfig.getDeviceId(context);
        if (!TextUtils.isEmpty(deviceId)) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_IMEI, deviceId);
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MD5IMEI, AesHelper.md5(deviceId));
        }
        String mac = DeviceConfig.getMac(context);
        if (TextUtils.isEmpty(mac)) {
            Log.w(TAG, "Get MacAddress failed. Check permission android.permission.ACCESS_WIFI_STATE [" + DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE") + "]");
        } else {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, mac);
        }
        if (!TextUtils.isEmpty(SocializeConstants.UID)) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_UID, SocializeConstants.UID);
        }
        try {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_EN, DeviceConfig.getNetworkAccessMode(context)[0]);
        } catch (Exception e) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_EN, "Unknown");
        }
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_DE, Build.MODEL);
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_VERSION, "6.4.5");
        hashMap.put("os", "Android");
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID, DeviceConfig.getAndroidID(context));
        hashMap.put("sn", DeviceConfig.getDeviceSN());
        hashMap.put("os_version", DeviceConfig.getOsVersion());
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_DT, Long.valueOf(System.currentTimeMillis()));
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_AK, SocializeUtils.getAppkey(context));
        hashMap.put(SocializeProtocolConstants.PROTOCOL_VERSION, SocializeConstants.PROTOCOL_VERSON);
        hashMap.put(SocializeConstants.USHARETYPE, Config.shareType);
        if (!TextUtils.isEmpty(Config.EntityKey)) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_ENTITY_KEY, Config.EntityKey);
        }
        if (!TextUtils.isEmpty(Config.SessionId)) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_SID, Config.SessionId);
        }
        try {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_REQUEST_TYPE, 0);
        } catch (Exception e2) {
        }
        return hashMap;
    }

    private String mapTostring(Map<String, Object> map) {
        if (this.mParams.isEmpty()) {
            return null;
        }
        try {
            return new JSONObject(map).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addFileParams(byte[] bArr, FILE_TYPE file_type, String str) {
        if (FILE_TYPE.IMAGE == file_type) {
            String d = a.d(bArr);
            if (TextUtils.isEmpty(d)) {
                d = "png";
            }
            if (TextUtils.isEmpty(str)) {
                str = String.valueOf(System.currentTimeMillis());
            }
            this.mFileMap.put(SocializeProtocolConstants.PROTOCOL_KEY_IMAGE, new URequest.FilePair(str + "." + d, bArr));
        }
    }

    public void addMediaParams(UMediaObject uMediaObject) {
        if (uMediaObject != null) {
            if (uMediaObject instanceof BaseMediaObject) {
                addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_TITLE, ((BaseMediaObject) uMediaObject).getTitle());
            }
            if (uMediaObject.isUrlMedia()) {
                for (Map.Entry next : uMediaObject.toUrlExtraParams().entrySet()) {
                    addStringParams((String) next.getKey(), next.getValue().toString());
                }
                return;
            }
            byte[] bArr = uMediaObject.toByte();
            if (bArr != null) {
                addFileParams(bArr, FILE_TYPE.IMAGE, (String) null);
            }
        }
    }

    public Map<String, Object> buildParams() {
        Map<String, Object> baseQuery = getBaseQuery(this.mContext);
        if (!TextUtils.isEmpty(Config.EntityKey)) {
            baseQuery.put(SocializeProtocolConstants.PROTOCOL_KEY_ENTITY_KEY, Config.EntityKey);
        }
        if (!TextUtils.isEmpty(Config.SessionId)) {
            baseQuery.put(SocializeProtocolConstants.PROTOCOL_KEY_SID, Config.SessionId);
        }
        baseQuery.put(SocializeProtocolConstants.PROTOCOL_KEY_REQUEST_TYPE, Integer.valueOf(this.mReqType));
        baseQuery.put(SocializeProtocolConstants.PROTOCOL_KEY_OPID, Integer.valueOf(this.mOpId));
        baseQuery.put(SocializeProtocolConstants.PROTOCOL_KEY_UID, Config.UID);
        baseQuery.putAll(this.mParams);
        return baseQuery;
    }

    public Map<String, Object> getBodyPair() {
        Map<String, Object> buildParams = buildParams();
        String mapTostring = mapTostring(buildParams);
        if (mapTostring != null) {
            try {
                Log.net("SocializeRequest body=" + mapTostring);
                String encryptNoPadding = AesHelper.encryptNoPadding(URLEncoder.encode(mapTostring, "UTF-8"), "UTF-8");
                buildParams.clear();
                buildParams.put("ud_post", encryptNoPadding);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return buildParams;
    }

    public String getDecryptString(String str) {
        try {
            return AesHelper.decryptNoPadding(str, "UTF-8").trim();
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public String getEcryptString(String str) {
        try {
            return "ud_get=" + AesHelper.encryptNoPadding(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "ud_get=" + str;
        }
    }

    public Map<String, URequest.FilePair> getFilePair() {
        return this.mFileMap;
    }

    /* access modifiers changed from: protected */
    public String getHttpMethod() {
        return AnonymousClass1.$SwitchMap$com$umeng$socialize$net$utils$URequest$RequestMethod[this.mMethod.ordinal()] != 1 ? GET : POST;
    }

    /* access modifiers changed from: protected */
    public abstract String getPath();

    public void onPrepareRequest() {
        addStringParams("pcv", SocializeConstants.PROTOCOL_VERSON);
        addStringParams(SocializeConstants.USHARETYPE, Config.shareType);
        String deviceId = DeviceConfig.getDeviceId(this.mContext);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_IMEI, deviceId);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_MD5IMEI, AesHelper.md5(deviceId));
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_DE, Build.MODEL);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_MAC, DeviceConfig.getMac(this.mContext));
        addStringParams("os", "Android");
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_EN, DeviceConfig.getNetworkAccessMode(this.mContext)[0]);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_UID, (String) null);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_VERSION, "6.4.5");
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_DT, String.valueOf(System.currentTimeMillis()));
    }

    public void setBaseUrl(String str) {
        String str2 = "";
        try {
            if (!TextUtils.isEmpty(getPath())) {
                str2 = new URL(new URL(str), getPath()).toString();
            }
            super.setBaseUrl(str2);
        } catch (Exception e) {
            throw new SocializeException("Can not generate correct url in SocializeRequest [" + str + "]", (Throwable) e);
        }
    }

    public void setReqType(int i) {
        this.mReqType = i;
    }

    public String toGetUrl() {
        return generateGetURL(getBaseUrl(), buildParams());
    }

    public JSONObject toJson() {
        return null;
    }
}
