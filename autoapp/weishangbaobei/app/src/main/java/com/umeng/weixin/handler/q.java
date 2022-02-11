package com.umeng.weixin.handler;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class q {
    private static final String a = "access_token";
    private static final String b = "expires_in";
    private static final String c = "refresh_token";
    private static final String d = "rt_expires_in";
    private static final String e = "openid";
    private static final String f = "unionid";
    private static final String g = "expires_in";
    private SharedPreferences h = null;
    private String i;
    private String j;
    private String k;
    private long l;
    private String m;
    private long n;

    public q(Context context, String str) {
        this.h = context.getSharedPreferences(str + "simple", 0);
        this.i = this.h.getString(f, (String) null);
        this.j = this.h.getString("openid", (String) null);
        this.k = this.h.getString("access_token", (String) null);
        this.l = this.h.getLong("expires_in", 0);
        this.m = this.h.getString(c, (String) null);
        this.n = this.h.getLong(d, 0);
    }

    public q a(Bundle bundle) {
        this.i = bundle.getString(f);
        this.j = bundle.getString("openid");
        this.k = bundle.getString("access_token");
        this.m = bundle.getString(c);
        String string = bundle.getString("expires_in");
        if (!TextUtils.isEmpty(string)) {
            this.l = (Long.valueOf(string).longValue() * 1000) + System.currentTimeMillis();
        }
        long j2 = bundle.getLong("refresh_token_expires");
        if (j2 != 0) {
            this.n = (j2 * 1000) + System.currentTimeMillis();
        }
        k();
        return this;
    }

    public String a() {
        return this.i;
    }

    public String b() {
        return this.j;
    }

    public String c() {
        return this.m;
    }

    public Map d() {
        HashMap hashMap = new HashMap();
        hashMap.put("access_token", this.k);
        hashMap.put(f, this.i);
        hashMap.put("openid", this.j);
        hashMap.put(c, this.m);
        hashMap.put("expires_in", String.valueOf(this.l));
        return hashMap;
    }

    public boolean e() {
        return !TextUtils.isEmpty(this.k) && !(((this.l - System.currentTimeMillis()) > 0 ? 1 : ((this.l - System.currentTimeMillis()) == 0 ? 0 : -1)) <= 0);
    }

    public String f() {
        return this.k;
    }

    public long g() {
        return this.l;
    }

    public boolean h() {
        return !TextUtils.isEmpty(this.m) && !(((this.n - System.currentTimeMillis()) > 0 ? 1 : ((this.n - System.currentTimeMillis()) == 0 ? 0 : -1)) <= 0);
    }

    public boolean i() {
        return !TextUtils.isEmpty(f());
    }

    public void j() {
        this.h.edit().clear().commit();
        this.m = "";
        this.k = "";
    }

    public void k() {
        this.h.edit().putString(f, this.i).putString("openid", this.j).putString("access_token", this.k).putString(c, this.m).putLong(d, this.n).putLong("expires_in", this.l).commit();
    }
}
