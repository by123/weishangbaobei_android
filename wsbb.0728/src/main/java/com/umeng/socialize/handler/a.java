package com.umeng.socialize.handler;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.Map;

public class a {
    private static final String a = "access_key";
    private static final String b = "access_secret";
    private static final String c = "uid";
    private static final String d = "expires_in";
    private static final String e = "access_token";
    private static final String f = "refresh_token";
    private static final String g = "expire_in";
    private static final String h = "expires_in";
    private static final String i = "userName";
    private static final String j = "uid";
    private static final String k = "isfollow";
    private String l = null;
    private String m = null;
    private String n = null;
    private long o = 0;
    private String p = null;
    private String q = null;
    private String r = null;
    private boolean s = false;
    private SharedPreferences t = null;

    public a(Context context, String str) {
        this.t = context.getSharedPreferences(str, 0);
        this.l = this.t.getString(a, (String) null);
        this.q = this.t.getString(f, (String) null);
        this.m = this.t.getString(b, (String) null);
        this.p = this.t.getString("access_token", (String) null);
        this.n = this.t.getString(SocializeProtocolConstants.PROTOCOL_KEY_UID, (String) null);
        this.o = this.t.getLong("expires_in", 0);
        this.s = this.t.getBoolean(k, false);
    }

    public a a(Bundle bundle) {
        this.p = bundle.getString("access_token");
        this.q = bundle.getString(f);
        this.n = bundle.getString(SocializeProtocolConstants.PROTOCOL_KEY_UID);
        if (!TextUtils.isEmpty(bundle.getString(g))) {
            this.o = (Long.valueOf(bundle.getString(g)).longValue() * 1000) + System.currentTimeMillis();
        }
        if (!TextUtils.isEmpty(bundle.getString("expires_in"))) {
            this.o = (Long.valueOf(bundle.getString("expires_in")).longValue() * 1000) + System.currentTimeMillis();
        }
        return this;
    }

    public a a(Map<String, String> map) {
        this.l = map.get(a);
        this.m = map.get(b);
        this.p = map.get("access_token");
        this.q = map.get(f);
        this.n = map.get(SocializeProtocolConstants.PROTOCOL_KEY_UID);
        if (!TextUtils.isEmpty(map.get("expires_in"))) {
            this.o = (Long.valueOf(map.get("expires_in")).longValue() * 1000) + System.currentTimeMillis();
        }
        return this;
    }

    public String a() {
        return TextUtils.isEmpty(this.p) ? this.l : this.p;
    }

    public String b() {
        return this.q;
    }

    public long c() {
        return this.o;
    }

    public String d() {
        return this.n;
    }

    public boolean e() {
        return !TextUtils.isEmpty(this.p);
    }

    public boolean f() {
        return e() && !(((this.o - System.currentTimeMillis()) > 0 ? 1 : ((this.o - System.currentTimeMillis()) == 0 ? 0 : -1)) <= 0);
    }

    public void g() {
        this.t.edit().putString(a, this.l).putString(b, this.m).putString("access_token", this.p).putString(f, this.q).putString(SocializeProtocolConstants.PROTOCOL_KEY_UID, this.n).putLong("expires_in", this.o).commit();
    }

    public void h() {
        this.l = null;
        this.m = null;
        this.p = null;
        this.n = null;
        this.o = 0;
        this.t.edit().clear().commit();
    }
}
