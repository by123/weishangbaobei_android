package com.umeng.commonsdk.internal.crash;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.stateless.a;
import com.umeng.commonsdk.stateless.f;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class UMCrashManager {
    /* access modifiers changed from: private */
    public static boolean isReportCrash;
    /* access modifiers changed from: private */
    public static Object mObject = new Object();

    public static void reportCrash(final Context context, final Throwable th) {
        if (!isReportCrash) {
            ULog.i("walle-crash", "report is " + isReportCrash);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        synchronized (UMCrashManager.mObject) {
                            if (!(context == null || th == null || UMCrashManager.isReportCrash)) {
                                boolean unused = UMCrashManager.isReportCrash = true;
                                ULog.i("walle-crash", "report thread is " + UMCrashManager.isReportCrash);
                                String a2 = a.a(th);
                                if (!TextUtils.isEmpty(a2)) {
                                    f.a(context, context.getFilesDir() + "/" + a.e + "/" + Base64.encodeToString(com.umeng.commonsdk.internal.a.a.getBytes(), 0), 10);
                                    UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                                    JSONObject buildSLBaseHeader = uMSLEnvelopeBuild.buildSLBaseHeader(context);
                                    try {
                                        JSONObject jSONObject = new JSONObject();
                                        jSONObject.put("content", a2);
                                        jSONObject.put("ts", System.currentTimeMillis());
                                        JSONObject jSONObject2 = new JSONObject();
                                        jSONObject2.put("crash", jSONObject);
                                        JSONObject jSONObject3 = new JSONObject();
                                        jSONObject3.put(SocializeProtocolConstants.PROTOCOL_KEY_REQUEST_TYPE, jSONObject2);
                                        JSONObject buildSLEnvelope = uMSLEnvelopeBuild.buildSLEnvelope(context, buildSLBaseHeader, jSONObject3, com.umeng.commonsdk.internal.a.a);
                                        if (buildSLEnvelope != null) {
                                            buildSLEnvelope.has("exception");
                                        }
                                    } catch (JSONException e) {
                                    }
                                }
                            }
                        }
                    } catch (Throwable th) {
                    }
                }
            }).start();
        }
    }
}
