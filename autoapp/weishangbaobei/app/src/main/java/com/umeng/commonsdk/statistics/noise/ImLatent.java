package com.umeng.commonsdk.statistics.noise;

import android.content.Context;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.statistics.a;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.internal.d;

public class ImLatent implements d {
    private static ImLatent instanse;
    private final int LATENT_MAX = 1800000;
    private final int LATENT_WINDOW = 10;
    private final long _360HOURS_IN_MS = 1296000000;
    private final long _36HOURS_IN_MS = 129600000;
    private final int _DEFAULT_HOURS = 360;
    private final int _DEFAULT_MAX_LATENT = 1800;
    private final int _DEFAULT_MIN_HOURS = 36;
    private final int _DEFAULT_MIN_LATENT = 1;
    private final long _ONE_HOURS_IN_MS = 3600000;
    private Context context;
    private long latentHour = 1296000000;
    private int latentWindow = 10;
    private long mDelay = 0;
    private long mElapsed = 0;
    private boolean mLatentActivite = false;
    private Object mLatentLock = new Object();
    private StatTracer statTracer;
    private com.umeng.commonsdk.statistics.common.d storeHelper;

    public static synchronized ImLatent getService(Context context2, StatTracer statTracer2) {
        ImLatent imLatent;
        synchronized (ImLatent.class) {
            if (instanse == null) {
                instanse = new ImLatent(context2, statTracer2);
                instanse.onImprintChanged(ImprintHandler.getImprintService(context2).c());
            }
            imLatent = instanse;
        }
        return imLatent;
    }

    private ImLatent(Context context2, StatTracer statTracer2) {
        this.context = context2;
        this.storeHelper = com.umeng.commonsdk.statistics.common.d.a(context2);
        this.statTracer = statTracer2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        r4 = java.lang.System.currentTimeMillis() - r8.statTracer.getLastReqTime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        if (r4 <= r8.latentHour) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        r1 = com.umeng.commonsdk.statistics.idtracking.Envelope.getSignature(r8.context);
        r2 = r8.mLatentLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0037, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r8.mDelay = (long) com.umeng.commonsdk.statistics.common.DataHelper.random(r8.latentWindow, r1);
        r8.mElapsed = r4;
        r8.mLatentActivite = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0045, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0046, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004f, code lost:
        if (r4 <= 129600000) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0051, code lost:
        r2 = r8.mLatentLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0053, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r8.mDelay = 0;
        r8.mElapsed = r4;
        r8.mLatentActivite = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005c, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005d, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0061, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean shouldStartLatency() {
        /*
            r8 = this;
            com.umeng.commonsdk.statistics.common.d r0 = r8.storeHelper
            boolean r0 = r0.c()
            r1 = 0
            if (r0 == 0) goto L_0x000a
            return r1
        L_0x000a:
            com.umeng.commonsdk.statistics.internal.StatTracer r0 = r8.statTracer
            boolean r0 = r0.isFirstRequest()
            if (r0 == 0) goto L_0x0013
            return r1
        L_0x0013:
            java.lang.Object r0 = r8.mLatentLock
            monitor-enter(r0)
            boolean r2 = r8.mLatentActivite     // Catch:{ all -> 0x0062 }
            if (r2 == 0) goto L_0x001c
            monitor-exit(r0)     // Catch:{ all -> 0x0062 }
            return r1
        L_0x001c:
            monitor-exit(r0)     // Catch:{ all -> 0x0062 }
            com.umeng.commonsdk.statistics.internal.StatTracer r0 = r8.statTracer
            long r2 = r0.getLastReqTime()
            long r4 = java.lang.System.currentTimeMillis()
            long r4 = r4 - r2
            long r2 = r8.latentHour
            r0 = 1
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 <= 0) goto L_0x004a
            android.content.Context r1 = r8.context
            java.lang.String r1 = com.umeng.commonsdk.statistics.idtracking.Envelope.getSignature(r1)
            java.lang.Object r2 = r8.mLatentLock
            monitor-enter(r2)
            int r3 = r8.latentWindow     // Catch:{ all -> 0x0047 }
            int r1 = com.umeng.commonsdk.statistics.common.DataHelper.random(r3, r1)     // Catch:{ all -> 0x0047 }
            long r6 = (long) r1     // Catch:{ all -> 0x0047 }
            r8.mDelay = r6     // Catch:{ all -> 0x0047 }
            r8.mElapsed = r4     // Catch:{ all -> 0x0047 }
            r8.mLatentActivite = r0     // Catch:{ all -> 0x0047 }
            monitor-exit(r2)     // Catch:{ all -> 0x0047 }
            return r0
        L_0x0047:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0047 }
            throw r0
        L_0x004a:
            r2 = 129600000(0x7b98a00, double:6.40309077E-316)
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 <= 0) goto L_0x0061
            java.lang.Object r2 = r8.mLatentLock
            monitor-enter(r2)
            r6 = 0
            r8.mDelay = r6     // Catch:{ all -> 0x005e }
            r8.mElapsed = r4     // Catch:{ all -> 0x005e }
            r8.mLatentActivite = r0     // Catch:{ all -> 0x005e }
            monitor-exit(r2)     // Catch:{ all -> 0x005e }
            return r0
        L_0x005e:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x005e }
            throw r0
        L_0x0061:
            return r1
        L_0x0062:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0062 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.noise.ImLatent.shouldStartLatency():boolean");
    }

    public boolean isLatentActivite() {
        boolean z;
        synchronized (this.mLatentLock) {
            z = this.mLatentActivite;
        }
        return z;
    }

    public void latentDeactivite() {
        synchronized (this.mLatentLock) {
            this.mLatentActivite = false;
        }
    }

    public long getDelayTime() {
        long j;
        synchronized (this.mLatentLock) {
            j = this.mDelay;
        }
        return j;
    }

    public long getElapsedTime() {
        return this.mElapsed;
    }

    public void onImprintChanged(ImprintHandler.a aVar) {
        int intValue = Integer.valueOf(aVar.a("latent_hours", String.valueOf(360))).intValue();
        if (intValue <= 36) {
            intValue = 360;
        }
        this.latentHour = ((long) intValue) * 3600000;
        int intValue2 = Integer.valueOf(aVar.a(e.az, "0")).intValue();
        if (intValue2 < 1 || intValue2 > 1800) {
            intValue2 = 0;
        }
        if (intValue2 != 0) {
            this.latentWindow = intValue2;
        } else if (a.c <= 0 || a.c > 1800000) {
            this.latentWindow = 10;
        } else {
            this.latentWindow = a.c;
        }
    }
}
