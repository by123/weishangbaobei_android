package com.umeng.socialize.media;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class TencentWBSharepreference {
    private static final String KEY_ACCESS_KEY = "access_key";
    private static final String KEY_ACCESS_SECRET = "access_secret";
    private static final String KEY_TTL = "expires_in";
    private static final String KEY_UID = "uid";
    private String mAccessKey = null;
    private String mAccessSecret = null;
    private long mTTL = 0;
    private String mUID = null;
    private SharedPreferences sharedPreferences = null;

    public TencentWBSharepreference(Context context, String str) {
        this.sharedPreferences = context.getSharedPreferences(str, 0);
        this.mAccessKey = this.sharedPreferences.getString(KEY_ACCESS_KEY, (String) null);
        this.mAccessSecret = this.sharedPreferences.getString(KEY_ACCESS_SECRET, (String) null);
        this.mUID = this.sharedPreferences.getString("uid", (String) null);
        this.mTTL = this.sharedPreferences.getLong("expires_in", 0);
    }

    public TencentWBSharepreference setAuthData(Map<String, String> map) {
        this.mAccessKey = map.get(KEY_ACCESS_KEY);
        this.mAccessSecret = map.get(KEY_ACCESS_SECRET);
        this.mUID = map.get("uid");
        if (!TextUtils.isEmpty(map.get("expires_in"))) {
            this.mTTL = (Long.valueOf(map.get("expires_in")).longValue() * 1000) + System.currentTimeMillis();
        }
        return this;
    }

    public Map<String, String> getAuthData() {
        HashMap hashMap = new HashMap();
        hashMap.put(KEY_ACCESS_KEY, this.mAccessKey);
        hashMap.put(KEY_ACCESS_SECRET, this.mAccessSecret);
        hashMap.put("uid", this.mUID);
        hashMap.put("expires_in", String.valueOf(this.mTTL));
        return hashMap;
    }

    public String getUID() {
        return this.mUID;
    }

    public boolean isAuthorized() {
        return !TextUtils.isEmpty(this.mUID);
    }

    public boolean isAuthValid() {
        return isAuthorized() && !(((this.mTTL - System.currentTimeMillis()) > 0 ? 1 : ((this.mTTL - System.currentTimeMillis()) == 0 ? 0 : -1)) <= 0);
    }

    public void commit() {
        this.sharedPreferences.edit().putString(KEY_ACCESS_KEY, this.mAccessKey).putString(KEY_ACCESS_SECRET, this.mAccessSecret).putString("uid", this.mUID).putLong("expires_in", this.mTTL).commit();
    }

    public void delete() {
        this.sharedPreferences.edit().clear().commit();
    }
}
