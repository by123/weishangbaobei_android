package com.umeng.commonsdk.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.stub.StubApp;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONObject;

public class c implements UMLogDataProtocol {
    private static final String b = "info";
    private static final String c = "stat";
    private Context a;

    public c(Context context) {
        if (context != null) {
            this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
    }

    public void removeCacheData(Object obj) {
    }

    public JSONObject setupReportData(long j) {
        return null;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public void workEvent(Object obj, int i) {
        ULog.i("walle", "[internal] workEvent");
        switch (i) {
            case a.e:
                ULog.i("walle", "[internal] workEvent send envelope");
                Class<?> cls = Class.forName("com.umeng.commonsdk.internal.UMInternalManagerAgent");
                if (cls != null) {
                    cls.getMethod("sendInternalEnvelopeByStateful2", new Class[]{Context.class}).invoke(cls, new Object[]{this.a});
                    return;
                }
                return;
            case a.g:
                ULog.i("walle", "[internal] workEvent cache battery, event is " + obj.toString());
                Class<?> cls2 = Class.forName("com.umeng.commonsdk.internal.utils.UMInternalUtilsAgent");
                if (cls2 != null) {
                    cls2.getMethod("saveBattery", new Class[]{Context.class, String.class}).invoke(cls2, new Object[]{this.a, (String) obj});
                    return;
                }
                return;
            case a.h:
                ULog.i("walle", "[internal] workEvent cache station, event is " + obj.toString());
                Class<?> cls3 = Class.forName("com.umeng.commonsdk.internal.utils.UMInternalUtilsAgent");
                if (cls3 != null) {
                    cls3.getMethod("saveBaseStationStrength", new Class[]{Context.class, String.class}).invoke(cls3, new Object[]{this.a, (String) obj});
                    return;
                }
                return;
            case a.i:
                Class<?> cls4 = Class.forName("com.umeng.commonsdk.internal.utils.InfoPreferenceAgent");
                if (cls4 != null) {
                    cls4.getMethod("saveBluetoothInfo", new Class[]{Context.class, Object.class}).invoke(cls4, new Object[]{this.a, obj});
                    return;
                }
                return;
            case a.j:
                Class<?> cls5 = Class.forName("com.umeng.commonsdk.internal.utils.ApplicationLayerUtilAgent");
                if (cls5 != null) {
                    cls5.getMethod("wifiChange", new Class[]{Context.class}).invoke(cls5, new Object[]{this.a});
                    return;
                }
                return;
            case a.k:
                try {
                    Class<?> cls6 = Class.forName("com.umeng.commonsdk.internal.utils.InfoPreferenceAgent");
                    if (cls6 != null) {
                        cls6.getMethod("saveUA", new Class[]{Context.class, String.class}).invoke(cls6, new Object[]{this.a, (String) obj});
                        return;
                    }
                    return;
                } catch (ClassNotFoundException e) {
                    return;
                } catch (Throwable th) {
                    return;
                }
            case a.l:
                SharedPreferences sharedPreferences = StubApp.getOrigApplicationContext(this.a.getApplicationContext()).getSharedPreferences(b, 0);
                if (sharedPreferences != null) {
                    sharedPreferences.edit().putString(c, (String) obj).commit();
                    return;
                }
                return;
            case a.m:
                try {
                    ULog.i("walle", "[internal] workEvent send envelope");
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(e.aw, a.d);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(e.ak, new JSONObject());
                    JSONObject buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(this.a, jSONObject, jSONObject2);
                    if (buildEnvelopeWithExtHeader != null && !buildEnvelopeWithExtHeader.has("exception")) {
                        ULog.i("walle", "[internal] workEvent send envelope back, result is ok");
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    return;
                }
            default:
                return;
        }
    }
}
