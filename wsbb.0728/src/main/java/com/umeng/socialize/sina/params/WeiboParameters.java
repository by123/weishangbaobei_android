package com.umeng.socialize.sina.params;

import android.graphics.Bitmap;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
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
        boolean z;
        StringBuilder sb = new StringBuilder();
        boolean z2 = true;
        for (String next : this.mParams.keySet()) {
            if (z2) {
                z = false;
            } else {
                sb.append("&");
                z = z2;
            }
            Object obj = this.mParams.get(next);
            if (obj instanceof String) {
                String str = (String) obj;
                if (!TextUtils.isEmpty(str)) {
                    try {
                        sb.append(URLEncoder.encode(next, DEFAULT_CHARSET) + "=" + URLEncoder.encode(str, DEFAULT_CHARSET));
                        z2 = z;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        z2 = z;
                    }
                }
            }
            z2 = z;
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
    public boolean hasBinaryData() {
        for (String str : this.mParams.keySet()) {
            Object obj = this.mParams.get(str);
            if ((obj instanceof ByteArrayOutputStream) || (obj instanceof Bitmap)) {
                return true;
            }
            while (r1.hasNext()) {
            }
        }
        return false;
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
