package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import com.stub.StubApp;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.socialize.common.SocializeConstants;

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

    public void a() {
        synchronized (this) {
            ULog.i(a, "destroy");
            try {
                if (this.b != null) {
                    this.b = null;
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(this.d, th);
            }
        }
        return;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        return;
     */
    public void a(d dVar) {
        boolean isProviderEnabled;
        Location location = null;
        boolean z = false;
        synchronized (this) {
            ULog.i(a, "getSystemLocation");
            if (dVar != null && this.d != null) {
                this.e = dVar;
                boolean checkPermission = UMUtils.checkPermission(this.d, "android.permission.ACCESS_COARSE_LOCATION");
                boolean checkPermission2 = UMUtils.checkPermission(this.d, "android.permission.ACCESS_FINE_LOCATION");
                if (checkPermission || checkPermission2) {
                    try {
                        if (this.b != null) {
                            if (Build.VERSION.SDK_INT >= 21) {
                                isProviderEnabled = this.b.isProviderEnabled("gps");
                                z = this.b.isProviderEnabled("network");
                            } else {
                                isProviderEnabled = checkPermission2 ? this.b.isProviderEnabled("gps") : false;
                                if (checkPermission) {
                                    z = this.b.isProviderEnabled("network");
                                }
                            }
                            if (isProviderEnabled || z) {
                                ULog.i(a, "getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)");
                                if (checkPermission2) {
                                    location = this.b.getLastKnownLocation("passive");
                                } else if (checkPermission) {
                                    location = this.b.getLastKnownLocation("network");
                                }
                            }
                            this.e.a(location);
                        }
                    } catch (Throwable th) {
                        UMCrashManager.reportCrash(this.d, th);
                    }
                } else if (this.e != null) {
                    this.e.a((Location) null);
                }
            }
        }
        UMCrashManager.reportCrash(this.d, th);
        return;
    }
}
