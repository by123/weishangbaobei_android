package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.stub.StubApp;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.internal.d;
import com.umeng.commonsdk.internal.utils.b;
import com.umeng.commonsdk.internal.utils.j;
import com.umeng.commonsdk.internal.utils.l;
import com.umeng.commonsdk.proguard.c;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.stateless.a;
import com.umeng.commonsdk.stateless.f;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import org.json.JSONArray;
import org.json.JSONObject;

public class UMConfigureImpl {
    private static boolean a;
    private static boolean b;

    private static void a(final Context context) {
        synchronized (UMConfigureImpl.class) {
            if (context != null) {
                try {
                    if (!b) {
                        String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                        String packageName = context.getPackageName();
                        if (!TextUtils.isEmpty(currentProcessName) && !TextUtils.isEmpty(packageName) && currentProcessName.equals(packageName)) {
                            new Thread(new Runnable() {
                                public void run() {
                                    try {
                                        JSONArray b = c.b(context);
                                        if (b != null && b.length() > 0) {
                                            f.a(context, context.getFilesDir() + "/" + a.e + "/" + Base64.encodeToString(com.umeng.commonsdk.internal.a.n.getBytes(), 0), 10);
                                            JSONObject jSONObject = new JSONObject();
                                            jSONObject.put("lbs", b);
                                            JSONObject jSONObject2 = new JSONObject();
                                            jSONObject2.put(SocializeProtocolConstants.PROTOCOL_KEY_REQUEST_TYPE, jSONObject);
                                            UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                                            uMSLEnvelopeBuild.buildSLEnvelope(context, uMSLEnvelopeBuild.buildSLBaseHeader(context), jSONObject2, com.umeng.commonsdk.internal.a.n);
                                        }
                                    } catch (Exception e) {
                                        UMCrashManager.reportCrash(context, e);
                                    }
                                }
                            }).start();
                        }
                        b = true;
                    }
                } catch (Throwable th) {
                    Class<UMConfigureImpl> cls = UMConfigureImpl.class;
                    throw th;
                }
            }
        }
    }

    private static void b(final Context context) {
        synchronized (UMConfigureImpl.class) {
            if (context != null) {
                try {
                    if (!a) {
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                                    String packageName = context.getPackageName();
                                    if (!TextUtils.isEmpty(currentProcessName) && !TextUtils.isEmpty(packageName) && currentProcessName.equals(packageName)) {
                                        com.umeng.commonsdk.proguard.a.a(context);
                                        try {
                                            c.a(context);
                                        } catch (Throwable th) {
                                            ULog.e("internal", "e is " + th);
                                        }
                                        try {
                                            if (!com.umeng.commonsdk.internal.utils.c.a(context).a()) {
                                                com.umeng.commonsdk.internal.utils.c.a(context).b();
                                            }
                                        } catch (Throwable th2) {
                                            ULog.e("internal", "e is " + th2);
                                        }
                                        try {
                                            l.b(context);
                                        } catch (Throwable th3) {
                                            ULog.e("internal", "e is " + th3);
                                        }
                                        try {
                                            com.umeng.commonsdk.internal.utils.a.n(context);
                                        } catch (Throwable th4) {
                                            ULog.e("internal", "e is " + th4);
                                        }
                                        try {
                                            com.umeng.commonsdk.internal.utils.a.d(context);
                                        } catch (Throwable th5) {
                                            ULog.e("internal", "e is " + th5);
                                        }
                                        try {
                                            j.b(context);
                                        } catch (Throwable th6) {
                                            ULog.e("internal", "e is " + th6);
                                        }
                                        try {
                                            d.b(context);
                                        } catch (Throwable th7) {
                                            ULog.e("internal", "e is " + th7);
                                        }
                                        try {
                                            d.c(context);
                                        } catch (Throwable th8) {
                                        }
                                    }
                                } catch (Throwable th9) {
                                    UMCrashManager.reportCrash(context, th9);
                                }
                            }
                        }).start();
                        if (!b.a(context).a()) {
                            b.a(context).b();
                        }
                        a = true;
                    }
                } catch (Throwable th) {
                    try {
                        ULog.e("internal", "e is " + th.getMessage());
                        UMCrashManager.reportCrash(context, th);
                    } catch (Throwable th2) {
                        Class<UMConfigureImpl> cls = UMConfigureImpl.class;
                        throw th2;
                    }
                }
            }
        }
    }

    public static void init(Context context) {
        if (context != null) {
            Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
            b(origApplicationContext);
            a(origApplicationContext);
        }
    }
}
