package com.umeng.commonsdk.stateless;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.stub.StubApp;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;

/* compiled from: UMSLNetWorkSender */
public class d {
    public static final int a = 273;
    /* access modifiers changed from: private */
    public static Context b = null;
    private static HandlerThread c = null;
    private static Handler d = null;
    private static Object e = new Object();
    private static final int f = 512;
    private static IntentFilter g;
    /* access modifiers changed from: private */
    public static boolean h = false;
    private static BroadcastReceiver i = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager;
            if (context != null && intent != null) {
                try {
                    if (intent.getAction() != null && intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                        Context unused = d.b = StubApp.getOrigApplicationContext(context.getApplicationContext());
                        if (d.b != null && (connectivityManager = (ConnectivityManager) d.b.getSystemService("connectivity")) != null) {
                            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                                ULog.i("walle", "[stateless] net reveiver disconnected --->>>");
                                boolean unused2 = d.h = false;
                                return;
                            }
                            boolean unused3 = d.h = true;
                            ULog.i("walle", "[stateless] net reveiver ok --->>>");
                            d.b(273);
                        }
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(context, th);
                }
            }
        }
    };

    public d(Context context) {
        synchronized (e) {
            if (context != null) {
                try {
                    b = StubApp.getOrigApplicationContext(context.getApplicationContext());
                    if (b != null && c == null) {
                        c = new HandlerThread("SL-NetWorkSender");
                        c.start();
                        if (d == null) {
                            d = new Handler(c.getLooper()) {
                                public void handleMessage(Message message) {
                                    int i = message.what;
                                    if (i == 273) {
                                        d.e();
                                    } else if (i == 512) {
                                        d.f();
                                    }
                                }
                            };
                        }
                        if (DeviceConfig.checkPermission(b, "android.permission.ACCESS_NETWORK_STATE")) {
                            ULog.i("walle", "[stateless] begin register receiver");
                            if (g == null) {
                                g = new IntentFilter();
                                g.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                                if (i != null) {
                                    ULog.i("walle", "[stateless] register receiver ok");
                                    b.registerReceiver(i, g);
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(context, th);
                }
            }
        }
    }

    public static void a(int i2) {
        if (h && d != null) {
            Message obtainMessage = d.obtainMessage();
            obtainMessage.what = i2;
            d.sendMessage(obtainMessage);
        }
    }

    public static void b(int i2) {
        try {
            if (h && d != null && !d.hasMessages(i2)) {
                ULog.i("walle", "[stateless] sendMsgOnce !!!!");
                Message obtainMessage = d.obtainMessage();
                obtainMessage.what = i2;
                d.sendMessage(obtainMessage);
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(b, th);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:11|12|13|14|15|16|17|(4:19|(1:21)|22|31)(2:23|24)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0064 */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006a A[Catch:{ Throwable -> 0x00a4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0098 A[Catch:{ Throwable -> 0x00a4 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void e() {
        /*
            boolean r0 = h
            if (r0 == 0) goto L_0x00ab
            android.content.Context r0 = b
            if (r0 != 0) goto L_0x000a
            goto L_0x00ab
        L_0x000a:
            android.content.Context r0 = b     // Catch:{ Throwable -> 0x00a4 }
            java.io.File r0 = com.umeng.commonsdk.stateless.f.a((android.content.Context) r0)     // Catch:{ Throwable -> 0x00a4 }
            if (r0 == 0) goto L_0x00aa
            java.io.File r1 = r0.getParentFile()     // Catch:{ Throwable -> 0x00a4 }
            if (r1 == 0) goto L_0x00aa
            java.io.File r1 = r0.getParentFile()     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r1 = r1.getName()     // Catch:{ Throwable -> 0x00a4 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x00a4 }
            if (r1 != 0) goto L_0x00aa
            com.umeng.commonsdk.stateless.e r1 = new com.umeng.commonsdk.stateless.e     // Catch:{ Throwable -> 0x00a4 }
            android.content.Context r2 = b     // Catch:{ Throwable -> 0x00a4 }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r2 = new java.lang.String     // Catch:{ Throwable -> 0x00a4 }
            java.io.File r3 = r0.getParentFile()     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r3 = r3.getName()     // Catch:{ Throwable -> 0x00a4 }
            r4 = 0
            byte[] r3 = android.util.Base64.decode(r3, r4)     // Catch:{ Throwable -> 0x00a4 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r3 = "walle"
            r5 = 1
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x00a4 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00a4 }
            r7.<init>()     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r8 = "[stateless] handleProcessNext, pathUrl is "
            r7.append(r8)     // Catch:{ Throwable -> 0x00a4 }
            r7.append(r2)     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x00a4 }
            r6[r4] = r7     // Catch:{ Throwable -> 0x00a4 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r3, (java.lang.Object[]) r6)     // Catch:{ Throwable -> 0x00a4 }
            r3 = 0
            java.lang.String r6 = r0.getAbsolutePath()     // Catch:{ Exception -> 0x0064 }
            byte[] r6 = com.umeng.commonsdk.stateless.f.a((java.lang.String) r6)     // Catch:{ Exception -> 0x0064 }
            r3 = r6
        L_0x0064:
            boolean r1 = r1.a(r3, r2)     // Catch:{ Throwable -> 0x00a4 }
            if (r1 == 0) goto L_0x0098
            java.lang.String r1 = "walle"
            java.lang.Object[] r2 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r3 = "[stateless] Send envelope file success, delete it."
            r2[r4] = r3     // Catch:{ Throwable -> 0x00a4 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r1, (java.lang.Object[]) r2)     // Catch:{ Throwable -> 0x00a4 }
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ Throwable -> 0x00a4 }
            r1.<init>(r0)     // Catch:{ Throwable -> 0x00a4 }
            boolean r0 = r1.delete()     // Catch:{ Throwable -> 0x00a4 }
            if (r0 != 0) goto L_0x0092
            java.lang.String r0 = "walle"
            java.lang.Object[] r2 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r3 = "[stateless] Failed to delete already processed file. We try again after delete failed."
            r2[r4] = r3     // Catch:{ Throwable -> 0x00a4 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r0, (java.lang.Object[]) r2)     // Catch:{ Throwable -> 0x00a4 }
            r1.delete()     // Catch:{ Throwable -> 0x00a4 }
        L_0x0092:
            r0 = 273(0x111, float:3.83E-43)
            b(r0)     // Catch:{ Throwable -> 0x00a4 }
            goto L_0x00aa
        L_0x0098:
            java.lang.String r0 = "walle"
            java.lang.Object[] r1 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r2 = "[stateless] Send envelope file failed, abandon and wait next trigger!"
            r1[r4] = r2     // Catch:{ Throwable -> 0x00a4 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r0, (java.lang.Object[]) r1)     // Catch:{ Throwable -> 0x00a4 }
            return
        L_0x00a4:
            r0 = move-exception
            android.content.Context r1 = b
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r1, r0)
        L_0x00aa:
            return
        L_0x00ab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.d.e():void");
    }

    public static void a() {
        b(512);
    }

    /* access modifiers changed from: private */
    public static void f() {
        if (g != null) {
            if (i != null) {
                if (b != null) {
                    b.unregisterReceiver(i);
                }
                i = null;
            }
            g = null;
        }
        if (c != null) {
            c.quit();
            if (c != null) {
                c = null;
            }
            if (d != null) {
                d = null;
            }
        }
    }
}
