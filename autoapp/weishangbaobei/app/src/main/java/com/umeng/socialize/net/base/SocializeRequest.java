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

    public enum FILE_TYPE {
        IMAGE,
        VEDIO
    }

    /* access modifiers changed from: protected */
    public abstract String getPath();

    public JSONObject toJson() {
        return null;
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

    public void setReqType(int i) {
        this.mReqType = i;
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

    public Map<String, URequest.FilePair> getFilePair() {
        return this.mFileMap;
    }

    public String toGetUrl() {
        return generateGetURL(getBaseUrl(), buildParams());
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

    /* renamed from: com.umeng.socialize.net.base.SocializeRequest$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$umeng$socialize$net$utils$URequest$RequestMethod = new int[URequest.RequestMethod.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.umeng.socialize.net.utils.URequest$RequestMethod[] r0 = com.umeng.socialize.net.utils.URequest.RequestMethod.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$umeng$socialize$net$utils$URequest$RequestMethod = r0
                int[] r0 = $SwitchMap$com$umeng$socialize$net$utils$URequest$RequestMethod     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.umeng.socialize.net.utils.URequest$RequestMethod r1 = com.umeng.socialize.net.utils.URequest.RequestMethod.POST     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$umeng$socialize$net$utils$URequest$RequestMethod     // Catch:{ NoSuchFieldError -> 0x001f }
                com.umeng.socialize.net.utils.URequest$RequestMethod r1 = com.umeng.socialize.net.utils.URequest.RequestMethod.GET     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.base.SocializeRequest.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public String getHttpMethod() {
        if (AnonymousClass1.$SwitchMap$com$umeng$socialize$net$utils$URequest$RequestMethod[this.mMethod.ordinal()] != 1) {
            return GET;
        }
        return POST;
    }

    public String getEcryptString(String str) {
        try {
            return "ud_get=" + AesHelper.encryptNoPadding(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "ud_get=" + str;
        }
    }

    public String getDecryptString(String str) {
        try {
            return AesHelper.decryptNoPadding(str, "UTF-8").trim();
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
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
        } catch (Exception unused) {
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
        } catch (Exception unused2) {
        }
        return hashMap;
    }
}
