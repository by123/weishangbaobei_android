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

    private ImLatent(Context context2, StatTracer statTracer2) {
        this.context = context2;
        this.storeHelper = com.umeng.commonsdk.statistics.common.d.a(context2);
        this.statTracer = statTracer2;
    }

    public static ImLatent getService(Context context2, StatTracer statTracer2) {
        ImLatent imLatent;
        synchronized (ImLatent.class) {
            try {
                if (instanse == null) {
                    instanse = new ImLatent(context2, statTracer2);
                    instanse.onImprintChanged(ImprintHandler.getImprintService(context2).c());
                }
                imLatent = instanse;
            } catch (Throwable th) {
                Class<ImLatent> cls = ImLatent.class;
                throw th;
            }
        }
        return imLatent;
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

    public void onImprintChanged(ImprintHandler.a aVar) {
        int i = 360;
        int intValue = Integer.valueOf(aVar.a("latent_hours", String.valueOf(360))).intValue();
        if (intValue > 36) {
            i = intValue;
        }
        this.latentHour = ((long) i) * 3600000;
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

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0020, code lost:
        r2 = java.lang.System.currentTimeMillis() - r8.statTracer.getLastReqTime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0030, code lost:
        if (r2 <= r8.latentHour) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0032, code lost:
        r0 = com.umeng.commonsdk.statistics.idtracking.Envelope.getSignature(r8.context);
        r4 = r8.mLatentLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003a, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r8.mDelay = (long) com.umeng.commonsdk.statistics.common.DataHelper.random(r8.latentWindow, r0);
        r8.mElapsed = r2;
        r8.mLatentActivite = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0049, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0054, code lost:
        if (r2 <= 129600000) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0056, code lost:
        r4 = r8.mLatentLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0058, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r8.mDelay = 0;
        r8.mElapsed = r2;
        r8.mLatentActivite = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0062, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        return true;
     */
    public boolean shouldStartLatency() {
        if (this.storeHelper.c() || this.statTracer.isFirstRequest()) {
            return false;
        }
        synchronized (this.mLatentLock) {
            if (this.mLatentActivite) {
                return false;
            }
        }
    }
}
