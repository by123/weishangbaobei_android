package com.umeng.socialize.sina.params;

import android.graphics.Bitmap;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Set;

public class WeiboParameters {
    private static final String DEFAULT_CHARSET = "UTF-8";
    private String mAppKey;
    private LinkedHashMap<String, Object> mParams = new LinkedHashMap<>();

    public WeiboParameters(String str) {
        this.mAppKey = str;
    }

    @Deprecated
    public void add(String str, int i) {
        this.mParams.put(str, String.valueOf(i));
    }

    @Deprecated
    public void add(String str, long j) {
        this.mParams.put(str, String.valueOf(j));
    }

    @Deprecated
    public void add(String str, Object obj) {
        this.mParams.put(str, obj.toString());
    }

    @Deprecated
    public void add(String str, String str2) {
        this.mParams.put(str, str2);
    }

    public boolean containsKey(String str) {
        return this.mParams.containsKey(str);
    }

    public boolean containsValue(String str) {
        return this.mParams.containsValue(str);
    }

    public String encodeUrl() {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String next : this.mParams.keySet()) {
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            Object obj = this.mParams.get(next);
            if (obj instanceof String) {
                String str = (String) obj;
                if (!TextUtils.isEmpty(str)) {
                    try {
                        sb.append(URLEncoder.encode(next, DEFAULT_CHARSET) + "=" + URLEncoder.encode(str, DEFAULT_CHARSET));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sb.toString();
    }

    public Object get(String str) {
        return this.mParams.get(str);
    }

    public String getAppKey() {
        return this.mAppKey;
    }

    public LinkedHashMap<String, Object> getParams() {
        return this.mParams;
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x0012  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasBinaryData() {
        /*
            r3 = this;
            java.util.LinkedHashMap<java.lang.String, java.lang.Object> r0 = r3.mParams
            java.util.Set r0 = r0.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x000a:
            boolean r1 = r0.hasNext()
            if (r1 != 0) goto L_0x0012
            r0 = 0
            return r0
        L_0x0012:
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.util.LinkedHashMap<java.lang.String, java.lang.Object> r2 = r3.mParams
            java.lang.Object r1 = r2.get(r1)
            boolean r2 = r1 instanceof java.io.ByteArrayOutputStream
            if (r2 != 0) goto L_0x0026
            boolean r1 = r1 instanceof android.graphics.Bitmap
            if (r1 == 0) goto L_0x000a
        L_0x0026:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.sina.params.WeiboParameters.hasBinaryData():boolean");
    }

    public Set<String> keySet() {
        return this.mParams.keySet();
    }

    public void put(String str, int i) {
        this.mParams.put(str, String.valueOf(i));
    }

    public void put(String str, long j) {
        this.mParams.put(str, String.valueOf(j));
    }

    public void put(String str, Bitmap bitmap) {
        this.mParams.put(str, bitmap);
    }

    public void put(String str, Object obj) {
        this.mParams.put(str, obj.toString());
    }

    public void put(String str, String str2) {
        this.mParams.put(str, str2);
    }

    public void remove(String str) {
        if (this.mParams.containsKey(str)) {
            this.mParams.remove(str);
            this.mParams.remove(this.mParams.get(str));
        }
    }

    public void setParams(LinkedHashMap<String, Object> linkedHashMap) {
        this.mParams = linkedHashMap;
    }

    public int size() {
        return this.mParams.size();
    }
}
