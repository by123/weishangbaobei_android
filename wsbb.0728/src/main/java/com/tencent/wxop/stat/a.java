package com.tencent.wxop.stat;

import android.content.Context;
import android.content.IntentFilter;
import com.stub.StubApp;
import com.tencent.wxop.stat.common.StatConstants;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.common.e;
import com.tencent.wxop.stat.common.l;
import com.tencent.wxop.stat.common.r;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import org.apache.http.HttpHost;
import org.json.JSONObject;

public class a {
    private static a g;
    private List<String> a = null;
    private volatile int b = 2;
    private volatile String c = "";
    private volatile HttpHost d = null;
    /* access modifiers changed from: private */
    public e e = null;
    private int f = 0;
    private Context h = null;
    private StatLogger i = null;

    private a(Context context) {
        this.h = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.e = new e();
        i.a(context);
        this.i = l.b();
        l();
        i();
        g();
    }

    public static a a(Context context) {
        if (g == null) {
            synchronized (a.class) {
                try {
                    if (g == null) {
                        g = new a(context);
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<a> cls = a.class;
                        throw th;
                    }
                }
            }
        }
        return g;
    }

    private boolean b(String str) {
        return Pattern.compile("(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})").matcher(str).matches();
    }

    private void i() {
        this.a = new ArrayList(10);
        this.a.add("117.135.169.101");
        this.a.add("140.207.54.125");
        this.a.add("180.153.8.53");
        this.a.add("120.198.203.175");
        this.a.add("14.17.43.18");
        this.a.add("163.177.71.186");
        this.a.add("111.30.131.31");
        this.a.add("123.126.121.167");
        this.a.add("123.151.152.111");
        this.a.add("113.142.45.79");
        this.a.add("123.138.162.90");
        this.a.add("103.7.30.94");
    }

    private String j() {
        try {
            if (!b(StatConstants.MTA_SERVER_HOST)) {
                return InetAddress.getByName(StatConstants.MTA_SERVER_HOST).getHostAddress();
            }
        } catch (Exception e2) {
            this.i.e((Throwable) e2);
        }
        return "";
    }

    private void k() {
        String str;
        String j = j();
        if (StatConfig.isDebugEnable()) {
            StatLogger statLogger = this.i;
            statLogger.i("remoteIp ip is " + j);
        }
        if (l.c(j)) {
            if (this.a.contains(j)) {
                str = j;
            } else {
                str = this.a.get(this.f);
                if (StatConfig.isDebugEnable()) {
                    StatLogger statLogger2 = this.i;
                    statLogger2.w(j + " not in ip list, change to:" + str);
                }
            }
            StatConfig.setStatReportUrl("http://" + str + ":80/mstat/report");
        }
    }

    private void l() {
        this.b = 0;
        this.d = null;
        this.c = null;
    }

    public HttpHost a() {
        return this.d;
    }

    public void a(String str) {
        if (StatConfig.isDebugEnable()) {
            this.i.i("updateIpList " + str);
        }
        try {
            if (l.c(str)) {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.length() > 0) {
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String string = jSONObject.getString(keys.next());
                        if (l.c(string)) {
                            for (String str2 : string.split(";")) {
                                if (l.c(str2)) {
                                    String[] split = str2.split(":");
                                    if (split.length > 1) {
                                        String str3 = split[0];
                                        if (b(str3) && !this.a.contains(str3)) {
                                            if (StatConfig.isDebugEnable()) {
                                                this.i.i("add new ip:" + str3);
                                            }
                                            this.a.add(str3);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
            this.i.e((Throwable) e2);
        }
        this.f = new Random().nextInt(this.a.size());
    }

    public String b() {
        return this.c;
    }

    public int c() {
        return this.b;
    }

    public void d() {
        this.f = (this.f + 1) % this.a.size();
    }

    public boolean e() {
        return this.b == 1;
    }

    public boolean f() {
        return this.b != 0;
    }

    /* access modifiers changed from: package-private */
    public void g() {
        if (r.f(this.h)) {
            if (StatConfig.g) {
                k();
            }
            this.c = l.j(this.h);
            if (StatConfig.isDebugEnable()) {
                StatLogger statLogger = this.i;
                statLogger.i("NETWORK name:" + this.c);
            }
            if (l.c(this.c)) {
                this.b = "WIFI".equalsIgnoreCase(this.c) ? 1 : 2;
                this.d = l.a(this.h);
            }
            if (StatServiceImpl.a()) {
                StatServiceImpl.d(this.h);
                return;
            }
            return;
        }
        if (StatConfig.isDebugEnable()) {
            this.i.i("NETWORK TYPE: network is close.");
        }
        l();
    }

    public void h() {
        StubApp.getOrigApplicationContext(this.h.getApplicationContext()).registerReceiver(new b(this), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }
}
