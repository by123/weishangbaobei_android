package com.tencent.bugly.crashreport.crash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tencent.bugly.crashreport.common.info.b;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import com.umeng.commonsdk.proguard.c;

public class BuglyBroadcastReceiver extends BroadcastReceiver {
    /* access modifiers changed from: private */
    public static BuglyBroadcastReceiver d;
    /* access modifiers changed from: private */
    public IntentFilter a = new IntentFilter();
    /* access modifiers changed from: private */
    public Context b;
    private String c;
    private boolean e = true;

    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        return true;
     */
    private boolean a(Context context, Intent intent) {
        synchronized (this) {
            if (!(context == null || intent == null)) {
                if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    if (this.e) {
                        this.e = false;
                        return true;
                    }
                    String c2 = b.c(this.b);
                    x.c("is Connect BC " + c2, new Object[0]);
                    x.a("network %s changed to %s", this.c, c2);
                    if (c2 == null) {
                        this.c = null;
                        return true;
                    }
                    String str = this.c;
                    this.c = c2;
                    long currentTimeMillis = System.currentTimeMillis();
                    a a2 = a.a();
                    u a3 = u.a();
                    com.tencent.bugly.crashreport.common.info.a a4 = com.tencent.bugly.crashreport.common.info.a.a(context);
                    if (a2 == null || a3 == null || a4 == null) {
                        x.d("not inited BC not work", new Object[0]);
                        return true;
                    } else if (!c2.equals(str)) {
                        if (currentTimeMillis - a3.a(c.a) > c.d) {
                            x.a("try to upload crash on network changed.", new Object[0]);
                            c a5 = c.a();
                            if (a5 != null) {
                                a5.a(0);
                            }
                        }
                        if (currentTimeMillis - a3.a(1001) > c.d) {
                            x.a("try to upload userinfo on network changed.", new Object[0]);
                            com.tencent.bugly.crashreport.biz.b.a.b();
                        }
                    }
                }
            }
        }
    }

    public static BuglyBroadcastReceiver getInstance() {
        BuglyBroadcastReceiver buglyBroadcastReceiver;
        synchronized (BuglyBroadcastReceiver.class) {
            try {
                if (d == null) {
                    d = new BuglyBroadcastReceiver();
                }
                buglyBroadcastReceiver = d;
            } catch (Throwable th) {
                Class<BuglyBroadcastReceiver> cls = BuglyBroadcastReceiver.class;
                throw th;
            }
        }
        return buglyBroadcastReceiver;
    }

    public void addFilter(String str) {
        synchronized (this) {
            if (!this.a.hasAction(str)) {
                this.a.addAction(str);
            }
            x.c("add action %s", str);
        }
    }

    public final void onReceive(Context context, Intent intent) {
        try {
            a(context, intent);
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
    }

    public void register(Context context) {
        synchronized (this) {
            this.b = context;
            z.a((Runnable) new Runnable() {
                public final void run() {
                    try {
                        x.a((Class) BuglyBroadcastReceiver.d.getClass(), "Register broadcast receiver of Bugly.", new Object[0]);
                        synchronized (this) {
                            BuglyBroadcastReceiver.this.b.registerReceiver(BuglyBroadcastReceiver.d, BuglyBroadcastReceiver.this.a);
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    public void unregister(Context context) {
        synchronized (this) {
            try {
                x.a((Class) getClass(), "Unregister broadcast receiver of Bugly.", new Object[0]);
                context.unregisterReceiver(this);
                this.b = context;
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }
}
