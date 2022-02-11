package com.umeng.qq.handler;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class s {
    private static final String a = "access_token";
    private static final String b = "openid";
    private static final String c = "uid";
    private static final String d = "unionid";
    private static final String e = "expires_in";
    private static long g;
    private String f = null;
    private String h = null;
    private String i = null;
    private String j = null;
    private SharedPreferences k = null;

    public s(Context context, String str) {
        this.k = context.getSharedPreferences(str + "simplify", 0);
        this.f = this.k.getString("access_token", (String) null);
        this.h = this.k.getString("uid", (String) null);
        g = this.k.getLong("expires_in", 0);
        this.j = this.k.getString("openid", (String) null);
        this.i = this.k.getString(d, (String) null);
    }

    public s a(Bundle bundle) {
        this.f = bundle.getString("access_token");
        g = (Long.valueOf(bundle.getString("expires_in")).longValue() * 1000) + System.currentTimeMillis();
        this.j = bundle.getString("openid");
        this.h = bundle.getString("openid");
        this.i = bundle.getString(d);
        return this;
    }

    public String a() {
        return this.f;
    }

    public void a(String str) {
        this.h = str;
    }

    public String b() {
        return this.i;
    }

    public void b(String str) {
        this.i = str;
    }

    public String c() {
        return this.h;
    }

    public void c(String str) {
        this.j = str;
    }

    public boolean d() {
        return this.f != null && !(((g - System.currentTimeMillis()) > 0 ? 1 : ((g - System.currentTimeMillis()) == 0 ? 0 : -1)) <= 0);
    }

    public long e() {
        return g;
    }

    public void f() {
        this.k.edit().putString("access_token", this.f).putLong("expires_in", g).putString("uid", this.h).putString("openid", this.j).putString(d, this.i).commit();
    }

    public void g() {
        this.k.edit().clear().commit();
        this.f = null;
        g = 0;
        this.h = null;
    }
}
