package com.umeng.analytics;

import android.content.Context;
import com.stub.StubApp;
import com.umeng.analytics.pro.k;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMSenderStateNotify;
import org.json.JSONObject;

public class CoreProtocol implements UMLogDataProtocol, UMSenderStateNotify {
    private static Context a;

    private static class a {
        /* access modifiers changed from: private */
        public static final CoreProtocol a = new CoreProtocol();

        private a() {
        }
    }

    private CoreProtocol() {
    }

    public static CoreProtocol getInstance(Context context) {
        if (a == null && context != null) {
            a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        return a.a;
    }

    public void onConnectionAvailable() {
        k.a(a).a();
    }

    public void onSenderIdle() {
        k.a(a).b();
    }

    public void removeCacheData(Object obj) {
        k.a(a).a(obj);
    }

    public JSONObject setupReportData(long j) {
        return k.a(a).a(j);
    }

    public void workEvent(Object obj, int i) {
        k.a(a).a(obj, i);
    }
}
