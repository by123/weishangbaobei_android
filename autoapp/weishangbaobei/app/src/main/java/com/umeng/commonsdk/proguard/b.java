package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.location.LocationManager;
import com.stub.StubApp;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.socialize.common.SocializeConstants;

/* compiled from: UMSysLocation */
public class b {
    private static final String a = "UMSysLocation";
    private static final int c = 10000;
    private LocationManager b;
    private Context d;
    private d e;

    private b() {
    }

    public b(Context context) {
        if (context == null) {
            MLog.e("Context参数不能为null");
            return;
        }
        this.d = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.b = (LocationManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService(SocializeConstants.KEY_LOCATION);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0038, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00c7, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.umeng.commonsdk.proguard.d r9) {
        /*
            r8 = this;
            monitor-enter(r8)
            java.lang.String r0 = "UMSysLocation"
            r1 = 1
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x00c8 }
            java.lang.String r3 = "getSystemLocation"
            r4 = 0
            r2[r4] = r3     // Catch:{ all -> 0x00c8 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r0, (java.lang.Object[]) r2)     // Catch:{ all -> 0x00c8 }
            if (r9 == 0) goto L_0x00c6
            android.content.Context r0 = r8.d     // Catch:{ all -> 0x00c8 }
            if (r0 != 0) goto L_0x0016
            goto L_0x00c6
        L_0x0016:
            r8.e = r9     // Catch:{ all -> 0x00c8 }
            android.content.Context r0 = r8.d     // Catch:{ all -> 0x00c8 }
            java.lang.String r2 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r0 = com.umeng.commonsdk.utils.UMUtils.checkPermission(r0, r2)     // Catch:{ all -> 0x00c8 }
            android.content.Context r2 = r8.d     // Catch:{ all -> 0x00c8 }
            java.lang.String r3 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r2 = com.umeng.commonsdk.utils.UMUtils.checkPermission(r2, r3)     // Catch:{ all -> 0x00c8 }
            r3 = 0
            if (r0 != 0) goto L_0x0039
            if (r2 == 0) goto L_0x002e
            goto L_0x0039
        L_0x002e:
            com.umeng.commonsdk.proguard.d r9 = r8.e     // Catch:{ all -> 0x00c8 }
            if (r9 == 0) goto L_0x0037
            com.umeng.commonsdk.proguard.d r9 = r8.e     // Catch:{ all -> 0x00c8 }
            r9.a(r3)     // Catch:{ all -> 0x00c8 }
        L_0x0037:
            monitor-exit(r8)
            return
        L_0x0039:
            android.location.LocationManager r5 = r8.b     // Catch:{ Throwable -> 0x0098 }
            if (r5 == 0) goto L_0x00c4
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x0098 }
            r6 = 21
            if (r5 < r6) goto L_0x0054
            android.location.LocationManager r5 = r8.b     // Catch:{ Throwable -> 0x0098 }
            java.lang.String r6 = "gps"
            boolean r5 = r5.isProviderEnabled(r6)     // Catch:{ Throwable -> 0x0098 }
            android.location.LocationManager r6 = r8.b     // Catch:{ Throwable -> 0x0098 }
            java.lang.String r7 = "network"
            boolean r6 = r6.isProviderEnabled(r7)     // Catch:{ Throwable -> 0x0098 }
            goto L_0x006c
        L_0x0054:
            if (r2 == 0) goto L_0x005f
            android.location.LocationManager r5 = r8.b     // Catch:{ Throwable -> 0x0098 }
            java.lang.String r6 = "gps"
            boolean r5 = r5.isProviderEnabled(r6)     // Catch:{ Throwable -> 0x0098 }
            goto L_0x0060
        L_0x005f:
            r5 = 0
        L_0x0060:
            if (r0 == 0) goto L_0x006b
            android.location.LocationManager r6 = r8.b     // Catch:{ Throwable -> 0x0098 }
            java.lang.String r7 = "network"
            boolean r6 = r6.isProviderEnabled(r7)     // Catch:{ Throwable -> 0x0098 }
            goto L_0x006c
        L_0x006b:
            r6 = 0
        L_0x006c:
            if (r5 != 0) goto L_0x0070
            if (r6 == 0) goto L_0x0091
        L_0x0070:
            java.lang.String r5 = "UMSysLocation"
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0098 }
            java.lang.String r7 = "getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)"
            r6[r4] = r7     // Catch:{ Throwable -> 0x0098 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r5, (java.lang.Object[]) r6)     // Catch:{ Throwable -> 0x0098 }
            if (r2 == 0) goto L_0x0086
            android.location.LocationManager r0 = r8.b     // Catch:{ Throwable -> 0x0098 }
            java.lang.String r2 = "passive"
            android.location.Location r0 = r0.getLastKnownLocation(r2)     // Catch:{ Throwable -> 0x0098 }
            goto L_0x0092
        L_0x0086:
            if (r0 == 0) goto L_0x0091
            android.location.LocationManager r0 = r8.b     // Catch:{ Throwable -> 0x0098 }
            java.lang.String r2 = "network"
            android.location.Location r0 = r0.getLastKnownLocation(r2)     // Catch:{ Throwable -> 0x0098 }
            goto L_0x0092
        L_0x0091:
            r0 = r3
        L_0x0092:
            com.umeng.commonsdk.proguard.d r2 = r8.e     // Catch:{ Throwable -> 0x0098 }
            r2.a(r0)     // Catch:{ Throwable -> 0x0098 }
            goto L_0x00c4
        L_0x0098:
            r0 = move-exception
            java.lang.String r2 = "UMSysLocation"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x00c8 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c8 }
            r5.<init>()     // Catch:{ all -> 0x00c8 }
            java.lang.String r6 = "e is "
            r5.append(r6)     // Catch:{ all -> 0x00c8 }
            r5.append(r0)     // Catch:{ all -> 0x00c8 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00c8 }
            r1[r4] = r5     // Catch:{ all -> 0x00c8 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r2, (java.lang.Object[]) r1)     // Catch:{ all -> 0x00c8 }
            if (r9 == 0) goto L_0x00bf
            r9.a(r3)     // Catch:{ Throwable -> 0x00b9 }
            goto L_0x00bf
        L_0x00b9:
            r9 = move-exception
            android.content.Context r1 = r8.d     // Catch:{ all -> 0x00c8 }
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r1, r9)     // Catch:{ all -> 0x00c8 }
        L_0x00bf:
            android.content.Context r9 = r8.d     // Catch:{ all -> 0x00c8 }
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r0)     // Catch:{ all -> 0x00c8 }
        L_0x00c4:
            monitor-exit(r8)
            return
        L_0x00c6:
            monitor-exit(r8)
            return
        L_0x00c8:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.proguard.b.a(com.umeng.commonsdk.proguard.d):void");
    }

    public synchronized void a() {
        ULog.i(a, "destroy");
        try {
            if (this.b != null) {
                this.b = null;
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.d, th);
        }
        return;
    }
}
