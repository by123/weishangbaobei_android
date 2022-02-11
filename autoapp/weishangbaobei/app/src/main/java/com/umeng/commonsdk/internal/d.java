package com.umeng.commonsdk.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import com.stub.StubApp;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.internal.utils.a;
import com.umeng.commonsdk.internal.utils.d;
import com.umeng.commonsdk.internal.utils.j;
import com.umeng.commonsdk.internal.utils.k;
import com.umeng.commonsdk.internal.utils.l;
import com.umeng.commonsdk.proguard.c;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.proguard.s;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.stateless.f;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UMInternalManager */
public class d {
    public static void a(Context context) {
        try {
            ULog.i("walle", "[internal] workEvent send envelope");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(e.aw, a.d);
            JSONObject buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(context, jSONObject, e(context));
            if (buildEnvelopeWithExtHeader != null && !buildEnvelopeWithExtHeader.has("exception")) {
                ULog.i("walle", "[internal] workEvent send envelope back, result is ok");
                a.f(context);
                j.d(context);
                c.c(context);
            }
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
        }
    }

    public static void b(Context context) {
        ULog.i("walle", "[internal] begin by stateful--->>>");
        if (context != null) {
            try {
                if (UMEnvelopeBuild.isReadyBuild(context, UMLogDataProtocol.UMBusinessType.U_INTERNAL)) {
                    UMWorkDispatch.sendEvent(context, a.e, b.a(context).a(), (Object) null);
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        }
    }

    public static void c(Context context) {
        Throwable th;
        Context context2;
        if (context != null) {
            try {
                ULog.i("walle", "[internal] begin by not stateful--->>>");
                context2 = StubApp.getOrigApplicationContext(context.getApplicationContext());
                try {
                    f.a(context2, context2.getFilesDir() + "/" + com.umeng.commonsdk.stateless.a.e + "/" + Base64.encodeToString(a.a.getBytes(), 0), 10);
                    UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                    JSONObject buildSLBaseHeader = uMSLEnvelopeBuild.buildSLBaseHeader(context2);
                    if (buildSLBaseHeader != null && buildSLBaseHeader.has("header")) {
                        try {
                            JSONObject jSONObject = (JSONObject) buildSLBaseHeader.opt("header");
                            if (jSONObject != null) {
                                jSONObject.put(e.aw, a.d);
                            }
                        } catch (Exception unused) {
                        }
                    }
                    ULog.i("walle", "[internal] header is " + buildSLBaseHeader.toString());
                    JSONObject d = d(context2);
                    ULog.i("walle", "[internal] body is " + d.toString());
                    ULog.i("walle", uMSLEnvelopeBuild.buildSLEnvelope(context2, buildSLBaseHeader, d, a.a).toString());
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                context2 = context;
                th = th4;
                UMCrashManager.reportCrash(context2, th);
            }
        }
    }

    public static JSONObject d(Context context) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (context != null) {
            Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
            try {
                JSONArray p = p(origApplicationContext);
                if (p != null && p.length() > 0) {
                    jSONObject2.put("run_server", p);
                }
            } catch (Exception e) {
                try {
                    UMCrashManager.reportCrash(origApplicationContext, e);
                } catch (Exception unused) {
                }
            }
            try {
                String k = a.k(origApplicationContext);
                if (!TextUtils.isEmpty(k)) {
                    jSONObject2.put("imsi", k);
                }
            } catch (Exception e2) {
                UMCrashManager.reportCrash(origApplicationContext, e2);
            }
            try {
                String l = a.l(origApplicationContext);
                if (!TextUtils.isEmpty(l)) {
                    jSONObject2.put("meid", l);
                }
            } catch (Exception e3) {
                UMCrashManager.reportCrash(origApplicationContext, e3);
            }
            try {
                jSONObject.put("internal", jSONObject2);
            } catch (JSONException e4) {
                UMCrashManager.reportCrash(origApplicationContext, e4);
            }
        }
        return jSONObject;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:165:0x01b5 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x00a8 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:97:0x0105 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject e(android.content.Context r5) {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            if (r5 == 0) goto L_0x01d0
            android.content.Context r5 = r5.getApplicationContext()
            android.content.Context r5 = com.stub.StubApp.getOrigApplicationContext(r5)
            org.json.JSONArray r2 = p(r5)     // Catch:{ Exception -> 0x0026 }
            if (r2 == 0) goto L_0x002a
            int r3 = r2.length()     // Catch:{ Exception -> 0x0026 }
            if (r3 <= 0) goto L_0x002a
            java.lang.String r3 = "rs"
            r1.put(r3, r2)     // Catch:{ Exception -> 0x0026 }
            goto L_0x002a
        L_0x0026:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r2)
        L_0x002a:
            org.json.JSONArray r2 = q(r5)     // Catch:{ Exception -> 0x003c }
            if (r2 == 0) goto L_0x0040
            int r3 = r2.length()     // Catch:{ Exception -> 0x003c }
            if (r3 <= 0) goto L_0x0040
            java.lang.String r3 = "bstn"
            r1.put(r3, r2)     // Catch:{ Exception -> 0x003c }
            goto L_0x0040
        L_0x003c:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r2)
        L_0x0040:
            org.json.JSONArray r2 = r(r5)     // Catch:{ Exception -> 0x0052 }
            if (r2 == 0) goto L_0x0056
            int r3 = r2.length()     // Catch:{ Exception -> 0x0052 }
            if (r3 <= 0) goto L_0x0056
            java.lang.String r3 = "by"
            r1.put(r3, r2)     // Catch:{ Exception -> 0x0052 }
            goto L_0x0056
        L_0x0052:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r2)
        L_0x0056:
            a(r5, r1)     // Catch:{ Exception -> 0x005a }
            goto L_0x005e
        L_0x005a:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r2)
        L_0x005e:
            b(r5, r1)     // Catch:{ Exception -> 0x0062 }
            goto L_0x0066
        L_0x0062:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r2)
        L_0x0066:
            org.json.JSONObject r2 = a()     // Catch:{ Exception -> 0x0078 }
            if (r2 == 0) goto L_0x007c
            int r3 = r2.length()     // Catch:{ Exception -> 0x0078 }
            if (r3 <= 0) goto L_0x007c
            java.lang.String r3 = "sd"
            r1.put(r3, r2)     // Catch:{ Exception -> 0x0078 }
            goto L_0x007c
        L_0x0078:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r2)
        L_0x007c:
            org.json.JSONObject r2 = b()     // Catch:{ Exception -> 0x008e }
            if (r2 == 0) goto L_0x0092
            int r3 = r2.length()     // Catch:{ Exception -> 0x008e }
            if (r3 <= 0) goto L_0x0092
            java.lang.String r3 = "build"
            r1.put(r3, r2)     // Catch:{ Exception -> 0x008e }
            goto L_0x0092
        L_0x008e:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r2)
        L_0x0092:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x00bf }
            r2.<init>()     // Catch:{ Exception -> 0x00bf }
            org.json.JSONArray r3 = g(r5)     // Catch:{ Exception -> 0x00bf }
            if (r3 == 0) goto L_0x00a8
            int r4 = r3.length()     // Catch:{ Exception -> 0x00bf }
            if (r4 <= 0) goto L_0x00a8
            java.lang.String r4 = "a_sr"
            r2.put(r4, r3)     // Catch:{ JSONException -> 0x00a8 }
        L_0x00a8:
            org.json.JSONArray r3 = com.umeng.commonsdk.internal.utils.j.c(r5)     // Catch:{ Exception -> 0x00bf }
            if (r3 == 0) goto L_0x00b9
            int r4 = r3.length()     // Catch:{ Exception -> 0x00bf }
            if (r4 <= 0) goto L_0x00b9
            java.lang.String r4 = "stat"
            r2.put(r4, r3)     // Catch:{ JSONException -> 0x00b9 }
        L_0x00b9:
            java.lang.String r3 = "sr"
            r1.put(r3, r2)     // Catch:{ Exception -> 0x00bf }
            goto L_0x00c3
        L_0x00bf:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r2)
        L_0x00c3:
            org.json.JSONObject r2 = h(r5)     // Catch:{ Exception -> 0x00d5 }
            if (r2 == 0) goto L_0x00d9
            int r3 = r2.length()     // Catch:{ Exception -> 0x00d5 }
            if (r3 <= 0) goto L_0x00d9
            java.lang.String r3 = "scr"
            r1.put(r3, r2)     // Catch:{ Exception -> 0x00d5 }
            goto L_0x00d9
        L_0x00d5:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r2)
        L_0x00d9:
            org.json.JSONObject r2 = i(r5)     // Catch:{ Exception -> 0x00eb }
            if (r2 == 0) goto L_0x00ef
            int r3 = r2.length()     // Catch:{ Exception -> 0x00eb }
            if (r3 <= 0) goto L_0x00ef
            java.lang.String r3 = "sinfo"
            r1.put(r3, r2)     // Catch:{ Exception -> 0x00eb }
            goto L_0x00ef
        L_0x00eb:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r2)
        L_0x00ef:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x011c }
            r2.<init>()     // Catch:{ Exception -> 0x011c }
            org.json.JSONArray r3 = com.umeng.commonsdk.internal.utils.a.e(r5)     // Catch:{ Exception -> 0x011c }
            if (r3 == 0) goto L_0x0105
            int r4 = r3.length()     // Catch:{ Exception -> 0x011c }
            if (r4 <= 0) goto L_0x0105
            java.lang.String r4 = "wl"
            r2.put(r4, r3)     // Catch:{ JSONException -> 0x0105 }
        L_0x0105:
            org.json.JSONArray r3 = j(r5)     // Catch:{ Exception -> 0x011c }
            if (r3 == 0) goto L_0x0116
            int r4 = r3.length()     // Catch:{ Exception -> 0x011c }
            if (r4 <= 0) goto L_0x0116
            java.lang.String r4 = "a_wls"
            r2.put(r4, r3)     // Catch:{ JSONException -> 0x0116 }
        L_0x0116:
            java.lang.String r3 = "winfo"
            r1.put(r3, r2)     // Catch:{ Exception -> 0x011c }
            goto L_0x0120
        L_0x011c:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r2)
        L_0x0120:
            org.json.JSONArray r2 = k(r5)     // Catch:{ Exception -> 0x0132 }
            if (r2 == 0) goto L_0x0136
            int r3 = r2.length()     // Catch:{ Exception -> 0x0132 }
            if (r3 <= 0) goto L_0x0136
            java.lang.String r3 = "input"
            r1.put(r3, r2)     // Catch:{ Exception -> 0x0132 }
            goto L_0x0136
        L_0x0132:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r2)
        L_0x0136:
            org.json.JSONObject r2 = com.umeng.commonsdk.internal.utils.a.o(r5)     // Catch:{ Exception -> 0x0148 }
            if (r2 == 0) goto L_0x014c
            int r3 = r2.length()     // Catch:{ Exception -> 0x0148 }
            if (r3 <= 0) goto L_0x014c
            java.lang.String r3 = "bt"
            r1.put(r3, r2)     // Catch:{ Exception -> 0x0148 }
            goto L_0x014c
        L_0x0148:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r2)
        L_0x014c:
            org.json.JSONArray r2 = l(r5)     // Catch:{ Exception -> 0x015e }
            if (r2 == 0) goto L_0x0162
            int r3 = r2.length()     // Catch:{ Exception -> 0x015e }
            if (r3 <= 0) goto L_0x0162
            java.lang.String r3 = "cam"
            r1.put(r3, r2)     // Catch:{ Exception -> 0x015e }
            goto L_0x0162
        L_0x015e:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r2)
        L_0x0162:
            org.json.JSONArray r2 = m(r5)     // Catch:{ Exception -> 0x0174 }
            if (r2 == 0) goto L_0x0178
            int r3 = r2.length()     // Catch:{ Exception -> 0x0174 }
            if (r3 <= 0) goto L_0x0178
            java.lang.String r3 = "appls"
            r1.put(r3, r2)     // Catch:{ Exception -> 0x0174 }
            goto L_0x0178
        L_0x0174:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r2)
        L_0x0178:
            org.json.JSONObject r2 = n(r5)     // Catch:{ Exception -> 0x018a }
            if (r2 == 0) goto L_0x018e
            int r3 = r2.length()     // Catch:{ Exception -> 0x018a }
            if (r3 <= 0) goto L_0x018e
            java.lang.String r3 = "mem"
            r1.put(r3, r2)     // Catch:{ Exception -> 0x018a }
            goto L_0x018e
        L_0x018a:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r2)
        L_0x018e:
            org.json.JSONArray r2 = o(r5)     // Catch:{ Exception -> 0x01a0 }
            if (r2 == 0) goto L_0x01a4
            int r3 = r2.length()     // Catch:{ Exception -> 0x01a0 }
            if (r3 <= 0) goto L_0x01a4
            java.lang.String r3 = "lbs"
            r1.put(r3, r2)     // Catch:{ Exception -> 0x01a0 }
            goto L_0x01a4
        L_0x01a0:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r2)
        L_0x01a4:
            org.json.JSONObject r2 = d()     // Catch:{ Exception -> 0x01b5 }
            if (r2 == 0) goto L_0x01b5
            int r3 = r2.length()     // Catch:{ Exception -> 0x01b5 }
            if (r3 <= 0) goto L_0x01b5
            java.lang.String r3 = "cpu"
            r1.put(r3, r2)     // Catch:{ Exception -> 0x01b5 }
        L_0x01b5:
            org.json.JSONObject r2 = c()     // Catch:{ Exception -> 0x01c6 }
            if (r2 == 0) goto L_0x01c6
            int r3 = r2.length()     // Catch:{ Exception -> 0x01c6 }
            if (r3 <= 0) goto L_0x01c6
            java.lang.String r3 = "rom"
            r1.put(r3, r2)     // Catch:{ Exception -> 0x01c6 }
        L_0x01c6:
            java.lang.String r2 = "inner"
            r0.put(r2, r1)     // Catch:{ JSONException -> 0x01cc }
            goto L_0x01d0
        L_0x01cc:
            r1 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r1)
        L_0x01d0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.d.e(android.content.Context):org.json.JSONObject");
    }

    private static JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("tot_s", a.h());
            jSONObject.put("ava_s", a.i());
            jSONObject.put("ts", System.currentTimeMillis());
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    private static JSONObject d() {
        try {
            d.a a = com.umeng.commonsdk.internal.utils.d.a();
            if (a == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("pro", a.a);
                jSONObject.put("pla", a.b);
                jSONObject.put("cpus", a.c);
                jSONObject.put("fea", a.d);
                jSONObject.put("imp", a.e);
                jSONObject.put("arc", a.f);
                jSONObject.put("var", a.g);
                jSONObject.put("par", a.h);
                jSONObject.put("rev", a.i);
                jSONObject.put("har", a.j);
                jSONObject.put("rev", a.k);
                jSONObject.put("ser", a.l);
                jSONObject.put("cur_cpu", com.umeng.commonsdk.internal.utils.d.d());
                jSONObject.put("max_cpu", com.umeng.commonsdk.internal.utils.d.b());
                jSONObject.put("min_cpu", com.umeng.commonsdk.internal.utils.d.c());
                jSONObject.put("ts", System.currentTimeMillis());
            } catch (Exception unused) {
            }
            return jSONObject;
        } catch (Exception unused2) {
            return null;
        }
    }

    private static JSONArray o(Context context) {
        if (context != null) {
            return c.b(StubApp.getOrigApplicationContext(context.getApplicationContext()));
        }
        return null;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00a9 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:38:0x00b3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.json.JSONArray p(android.content.Context r7) {
        /*
            r0 = 0
            if (r7 == 0) goto L_0x00c6
            android.content.Context r1 = r7.getApplicationContext()     // Catch:{ Throwable -> 0x00c2 }
            android.content.Context r1 = com.stub.StubApp.getOrigApplicationContext(r1)     // Catch:{ Throwable -> 0x00c2 }
            java.lang.String r2 = "activity"
            java.lang.Object r1 = r1.getSystemService(r2)     // Catch:{ Throwable -> 0x00c2 }
            android.app.ActivityManager r1 = (android.app.ActivityManager) r1     // Catch:{ Throwable -> 0x00c2 }
            if (r1 == 0) goto L_0x00c6
            r2 = 2147483647(0x7fffffff, float:NaN)
            java.util.List r1 = r1.getRunningServices(r2)     // Catch:{ Throwable -> 0x00c2 }
            if (r1 == 0) goto L_0x00c6
            boolean r2 = r1.isEmpty()     // Catch:{ Throwable -> 0x00c2 }
            if (r2 != 0) goto L_0x00c6
            r2 = 0
        L_0x0025:
            int r3 = r1.size()     // Catch:{ Throwable -> 0x00c2 }
            if (r2 >= r3) goto L_0x0094
            java.lang.Object r3 = r1.get(r2)     // Catch:{ Throwable -> 0x00c2 }
            if (r3 == 0) goto L_0x0091
            java.lang.Object r3 = r1.get(r2)     // Catch:{ Throwable -> 0x00c2 }
            android.app.ActivityManager$RunningServiceInfo r3 = (android.app.ActivityManager.RunningServiceInfo) r3     // Catch:{ Throwable -> 0x00c2 }
            android.content.ComponentName r3 = r3.service     // Catch:{ Throwable -> 0x00c2 }
            if (r3 == 0) goto L_0x0091
            java.lang.Object r3 = r1.get(r2)     // Catch:{ Throwable -> 0x00c2 }
            android.app.ActivityManager$RunningServiceInfo r3 = (android.app.ActivityManager.RunningServiceInfo) r3     // Catch:{ Throwable -> 0x00c2 }
            android.content.ComponentName r3 = r3.service     // Catch:{ Throwable -> 0x00c2 }
            java.lang.String r3 = r3.getClassName()     // Catch:{ Throwable -> 0x00c2 }
            if (r3 == 0) goto L_0x0091
            java.lang.Object r3 = r1.get(r2)     // Catch:{ Throwable -> 0x00c2 }
            android.app.ActivityManager$RunningServiceInfo r3 = (android.app.ActivityManager.RunningServiceInfo) r3     // Catch:{ Throwable -> 0x00c2 }
            android.content.ComponentName r3 = r3.service     // Catch:{ Throwable -> 0x00c2 }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ Throwable -> 0x00c2 }
            if (r3 == 0) goto L_0x0091
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0091 }
            r3.<init>()     // Catch:{ JSONException -> 0x0091 }
            java.lang.String r4 = "sn"
            java.lang.Object r5 = r1.get(r2)     // Catch:{ JSONException -> 0x0091 }
            android.app.ActivityManager$RunningServiceInfo r5 = (android.app.ActivityManager.RunningServiceInfo) r5     // Catch:{ JSONException -> 0x0091 }
            android.content.ComponentName r5 = r5.service     // Catch:{ JSONException -> 0x0091 }
            java.lang.String r5 = r5.getClassName()     // Catch:{ JSONException -> 0x0091 }
            java.lang.String r5 = r5.toString()     // Catch:{ JSONException -> 0x0091 }
            r3.put(r4, r5)     // Catch:{ JSONException -> 0x0091 }
            java.lang.String r4 = "pn"
            java.lang.Object r5 = r1.get(r2)     // Catch:{ JSONException -> 0x0091 }
            android.app.ActivityManager$RunningServiceInfo r5 = (android.app.ActivityManager.RunningServiceInfo) r5     // Catch:{ JSONException -> 0x0091 }
            android.content.ComponentName r5 = r5.service     // Catch:{ JSONException -> 0x0091 }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ JSONException -> 0x0091 }
            java.lang.String r5 = r5.toString()     // Catch:{ JSONException -> 0x0091 }
            r3.put(r4, r5)     // Catch:{ JSONException -> 0x0091 }
            if (r0 != 0) goto L_0x008e
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0091 }
            r4.<init>()     // Catch:{ JSONException -> 0x0091 }
            r0 = r4
        L_0x008e:
            r0.put(r3)     // Catch:{ JSONException -> 0x0091 }
        L_0x0091:
            int r2 = r2 + 1
            goto L_0x0025
        L_0x0094:
            if (r0 == 0) goto L_0x00c6
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Throwable -> 0x00c2 }
            r1.<init>()     // Catch:{ Throwable -> 0x00c2 }
            java.lang.String r2 = "ts"
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x00a9 }
            r1.put(r2, r3)     // Catch:{ JSONException -> 0x00a9 }
            java.lang.String r2 = "ls"
            r1.put(r2, r0)     // Catch:{ JSONException -> 0x00a9 }
        L_0x00a9:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Throwable -> 0x00c2 }
            r2.<init>()     // Catch:{ Throwable -> 0x00c2 }
            java.lang.String r3 = "sers"
            r2.put(r3, r1)     // Catch:{ JSONException -> 0x00b3 }
        L_0x00b3:
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ Throwable -> 0x00c2 }
            r1.<init>()     // Catch:{ Throwable -> 0x00c2 }
            r1.put(r2)     // Catch:{ Throwable -> 0x00bd }
            r0 = r1
            goto L_0x00c6
        L_0x00bd:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x00c3
        L_0x00c2:
            r1 = move-exception
        L_0x00c3:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r7, r1)
        L_0x00c6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.d.p(android.content.Context):org.json.JSONArray");
    }

    private static JSONArray q(Context context) {
        JSONArray jSONArray = new JSONArray();
        JSONObject d = k.d(context);
        if (d != null) {
            try {
                String e = k.e(context);
                if (!TextUtils.isEmpty(e)) {
                    d.put("sig", e);
                }
                jSONArray.put(d);
            } catch (Exception unused) {
            }
        }
        return jSONArray;
    }

    private static JSONArray r(Context context) {
        JSONArray jSONArray = new JSONArray();
        String f = k.f(context);
        if (!TextUtils.isEmpty(f)) {
            try {
                jSONArray.put(new JSONObject(f));
            } catch (Exception unused) {
            }
        }
        return jSONArray;
    }

    private static JSONArray s(Context context) {
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
            String a = k.a(origApplicationContext);
            JSONObject jSONObject = null;
            if (!TextUtils.isEmpty(a)) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put(e.X, a);
                    } catch (Exception unused) {
                    }
                    jSONObject = jSONObject2;
                } catch (Exception unused2) {
                }
            }
            String b = k.b(origApplicationContext);
            if (!TextUtils.isEmpty(b)) {
                if (jSONObject == null) {
                    try {
                        jSONObject = new JSONObject();
                    } catch (Exception unused3) {
                    }
                }
                jSONObject.put(e.Y, b);
            }
            String c = k.c(origApplicationContext);
            if (!TextUtils.isEmpty(c)) {
                if (jSONObject == null) {
                    try {
                        jSONObject = new JSONObject();
                    } catch (Exception unused4) {
                    }
                }
                jSONObject.put(e.Z, c);
            }
            JSONObject d = k.d(origApplicationContext);
            if (d != null) {
                try {
                    String e = k.e(origApplicationContext);
                    if (!TextUtils.isEmpty(e)) {
                        d.put("signalscale", e);
                    }
                    if (jSONObject == null) {
                        jSONObject = new JSONObject();
                    }
                    jSONObject.put(e.ab, d);
                } catch (Exception unused5) {
                }
            }
            String f = k.f(origApplicationContext);
            if (!TextUtils.isEmpty(f)) {
                if (jSONObject == null) {
                    try {
                        jSONObject = new JSONObject();
                    } catch (Exception unused6) {
                    }
                }
                jSONObject.put(e.W, new JSONObject(f));
            }
            if (jSONObject != null) {
                jSONArray.put(jSONObject);
            }
        }
        return jSONArray;
    }

    private static void a(Context context, JSONObject jSONObject) {
        PackageManager packageManager;
        if (context != null && (packageManager = StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageManager()) != null) {
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            a(jSONObject, "gp", packageManager.hasSystemFeature("android.hardware.location.gps"));
            a(jSONObject, "to", packageManager.hasSystemFeature("android.hardware.touchscreen"));
            a(jSONObject, "mo", packageManager.hasSystemFeature("android.hardware.telephony"));
            a(jSONObject, "ca", packageManager.hasSystemFeature("android.hardware.camera"));
            a(jSONObject, "fl", packageManager.hasSystemFeature("android.hardware.camera.flash"));
        }
    }

    private static void a(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject != null && !TextUtils.isEmpty(str)) {
            if (z) {
                try {
                    jSONObject.put(str, 1);
                } catch (Exception unused) {
                }
            } else {
                jSONObject.put(str, 0);
            }
        }
    }

    private static void b(Context context, JSONObject jSONObject) {
        if (context != null) {
            String a = l.a(context);
            if (!TextUtils.isEmpty(a)) {
                try {
                    JSONObject jSONObject2 = new JSONObject(a);
                    if (jSONObject == null) {
                        jSONObject = new JSONObject();
                    }
                    if (jSONObject2.has(l.d)) {
                        jSONObject.put(l.d, jSONObject2.opt(l.d));
                    }
                    if (jSONObject2.has(l.c)) {
                        jSONObject.put(l.c, jSONObject2.opt(l.c));
                    }
                    if (jSONObject2.has(l.b)) {
                        jSONObject.put(l.b, jSONObject2.opt(l.b));
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    public static String f(Context context) {
        try {
            com.umeng.commonsdk.statistics.idtracking.e a = com.umeng.commonsdk.statistics.idtracking.e.a(context);
            if (a == null) {
                return null;
            }
            a.a();
            String encodeToString = Base64.encodeToString(new s().a(a.b()), 0);
            if (!TextUtils.isEmpty(encodeToString)) {
                return encodeToString;
            }
            return null;
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
            return null;
        }
    }

    public static JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("f", a.c());
            jSONObject.put(e.ar, a.d());
            jSONObject.put("ts", System.currentTimeMillis());
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    public static JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("a_pr", Build.PRODUCT);
            jSONObject.put("a_bl", Build.BOOTLOADER);
            if (Build.VERSION.SDK_INT >= 14) {
                jSONObject.put("a_rv", Build.getRadioVersion());
            }
            jSONObject.put("a_fp", Build.FINGERPRINT);
            jSONObject.put("a_hw", Build.HARDWARE);
            jSONObject.put("a_host", Build.HOST);
            if (Build.VERSION.SDK_INT >= 21) {
                JSONArray jSONArray = new JSONArray();
                for (String put : Build.SUPPORTED_32_BIT_ABIS) {
                    jSONArray.put(put);
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("a_s32", jSONArray);
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                JSONArray jSONArray2 = new JSONArray();
                for (String put2 : Build.SUPPORTED_64_BIT_ABIS) {
                    jSONArray2.put(put2);
                }
                if (jSONArray2.length() > 0) {
                    jSONObject.put("a_s64", jSONArray2);
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                JSONArray jSONArray3 = new JSONArray();
                for (String put3 : Build.SUPPORTED_ABIS) {
                    jSONArray3.put(put3);
                }
                if (jSONArray3.length() > 0) {
                    jSONObject.put("a_sa", jSONArray3);
                }
            }
            jSONObject.put("a_ta", Build.TAGS);
            jSONObject.put("a_uk", "unknown");
            jSONObject.put("a_user", Build.USER);
            jSONObject.put("a_cpu1", Build.CPU_ABI);
            jSONObject.put("a_cpu2", Build.CPU_ABI2);
            jSONObject.put("a_ra", Build.RADIO);
            if (Build.VERSION.SDK_INT >= 23) {
                jSONObject.put("a_bos", Build.VERSION.BASE_OS);
                jSONObject.put("a_pre", Build.VERSION.PREVIEW_SDK_INT);
                jSONObject.put("a_sp", Build.VERSION.SECURITY_PATCH);
            }
            jSONObject.put("a_cn", Build.VERSION.CODENAME);
            jSONObject.put("a_intl", Build.VERSION.INCREMENTAL);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    public static JSONArray g(Context context) {
        if (context != null) {
            return k.g(StubApp.getOrigApplicationContext(context.getApplicationContext()));
        }
        return null;
    }

    public static JSONObject h(Context context) {
        DisplayMetrics displayMetrics;
        JSONObject jSONObject = new JSONObject();
        if (context != null) {
            try {
                jSONObject.put("a_st_h", a.h(context));
                jSONObject.put("a_nav_h", a.i(context));
                if (!(context.getResources() == null || (displayMetrics = context.getResources().getDisplayMetrics()) == null)) {
                    jSONObject.put("a_den", (double) displayMetrics.density);
                    jSONObject.put("a_dpi", displayMetrics.densityDpi);
                }
            } catch (Exception e) {
                UMCrashManager.reportCrash(context, e);
            }
        }
        return jSONObject;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:3|4|(1:6)(1:7)|8|(3:10|11|12)|13|14|(3:16|17|18)|19|21|22) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0087 */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0091  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject i(android.content.Context r5) {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            if (r5 == 0) goto L_0x00ad
            android.content.Context r5 = r5.getApplicationContext()
            android.content.Context r5 = com.stub.StubApp.getOrigApplicationContext(r5)
            java.lang.String r1 = r5.getPackageName()
            java.lang.String r2 = "a_fit"
            long r3 = com.umeng.commonsdk.internal.utils.a.a(r5, r1)     // Catch:{ Exception -> 0x00a9 }
            r0.put(r2, r3)     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r2 = "a_alut"
            long r3 = com.umeng.commonsdk.internal.utils.a.b(r5, r1)     // Catch:{ Exception -> 0x00a9 }
            r0.put(r2, r3)     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r2 = "a_c"
            java.lang.String r3 = com.umeng.commonsdk.internal.utils.a.c(r5, r1)     // Catch:{ Exception -> 0x00a9 }
            r0.put(r2, r3)     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r2 = "a_uid"
            int r1 = com.umeng.commonsdk.internal.utils.a.d(r5, r1)     // Catch:{ Exception -> 0x00a9 }
            r0.put(r2, r1)     // Catch:{ Exception -> 0x00a9 }
            boolean r1 = com.umeng.commonsdk.internal.utils.a.a()     // Catch:{ Exception -> 0x00a9 }
            if (r1 == 0) goto L_0x0044
            java.lang.String r1 = "a_root"
            r2 = 1
            r0.put(r1, r2)     // Catch:{ Exception -> 0x00a9 }
            goto L_0x004a
        L_0x0044:
            java.lang.String r1 = "a_root"
            r2 = 0
            r0.put(r1, r2)     // Catch:{ Exception -> 0x00a9 }
        L_0x004a:
            java.lang.String r1 = "tf"
            java.lang.String r2 = com.umeng.commonsdk.internal.utils.a.b()     // Catch:{ Exception -> 0x00a9 }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r1 = "s_fs"
            float r2 = com.umeng.commonsdk.internal.utils.a.a((android.content.Context) r5)     // Catch:{ Exception -> 0x00a9 }
            double r2 = (double) r2     // Catch:{ Exception -> 0x00a9 }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r1 = "a_meid"
            java.lang.String r2 = com.umeng.commonsdk.internal.utils.a.l(r5)     // Catch:{ Exception -> 0x00a9 }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r1 = "a_imsi"
            java.lang.String r2 = com.umeng.commonsdk.internal.utils.a.k(r5)     // Catch:{ Exception -> 0x00a9 }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r1 = "st"
            long r2 = com.umeng.commonsdk.internal.utils.a.f()     // Catch:{ Exception -> 0x00a9 }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r1 = com.umeng.commonsdk.internal.utils.k.b(r5)     // Catch:{ Exception -> 0x00a9 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x00a9 }
            if (r2 != 0) goto L_0x0087
            java.lang.String r2 = "a_iccid"
            r0.put(r2, r1)     // Catch:{ Exception -> 0x0087 }
        L_0x0087:
            java.lang.String r1 = com.umeng.commonsdk.internal.utils.k.c(r5)     // Catch:{ Exception -> 0x00a9 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x00a9 }
            if (r2 != 0) goto L_0x0096
            java.lang.String r2 = "a_simei"
            r0.put(r2, r1)     // Catch:{ Exception -> 0x0096 }
        L_0x0096:
            java.lang.String r1 = "hn"
            java.lang.String r2 = com.umeng.commonsdk.internal.utils.a.g()     // Catch:{ Exception -> 0x00a9 }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r1 = "ts"
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00a9 }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x00a9 }
            goto L_0x00ad
        L_0x00a9:
            r1 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r1)
        L_0x00ad:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.d.i(android.content.Context):org.json.JSONObject");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r7 = com.stub.StubApp.getOrigApplicationContext(r7.getApplicationContext());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONArray j(android.content.Context r7) {
        /*
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            if (r7 == 0) goto L_0x00b6
            android.content.Context r7 = r7.getApplicationContext()
            android.content.Context r7 = com.stub.StubApp.getOrigApplicationContext(r7)
            java.util.List r1 = com.umeng.commonsdk.internal.utils.a.b(r7)
            if (r1 == 0) goto L_0x00b6
            int r2 = r1.size()
            if (r2 <= 0) goto L_0x00b6
            java.util.Iterator r1 = r1.iterator()
        L_0x001f:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00b6
            java.lang.Object r2 = r1.next()
            android.net.wifi.ScanResult r2 = (android.net.wifi.ScanResult) r2
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x00b0 }
            r3.<init>()     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r4 = "a_bssid"
            java.lang.String r5 = r2.BSSID     // Catch:{ Exception -> 0x00b0 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r4 = "a_ssid"
            java.lang.String r5 = r2.SSID     // Catch:{ Exception -> 0x00b0 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r4 = "a_cap"
            java.lang.String r5 = r2.capabilities     // Catch:{ Exception -> 0x00b0 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r4 = "a_fcy"
            int r5 = r2.frequency     // Catch:{ Exception -> 0x00b0 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r4 = "ts"
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00b0 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x00b0 }
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x00b0 }
            r5 = 23
            if (r4 < r5) goto L_0x00a2
            java.lang.String r4 = "a_c0"
            int r5 = r2.centerFreq0     // Catch:{ Exception -> 0x00b0 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r4 = "a_c1"
            int r5 = r2.centerFreq1     // Catch:{ Exception -> 0x00b0 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r4 = "a_cw"
            int r5 = r2.channelWidth     // Catch:{ Exception -> 0x00b0 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x00b0 }
            boolean r4 = r2.is80211mcResponder()     // Catch:{ Exception -> 0x00b0 }
            r5 = 0
            r6 = 1
            if (r4 == 0) goto L_0x007e
            java.lang.String r4 = "a_is80211"
            r3.put(r4, r6)     // Catch:{ Exception -> 0x00b0 }
            goto L_0x0083
        L_0x007e:
            java.lang.String r4 = "a_is80211"
            r3.put(r4, r5)     // Catch:{ Exception -> 0x00b0 }
        L_0x0083:
            boolean r4 = r2.isPasspointNetwork()     // Catch:{ Exception -> 0x00b0 }
            if (r4 == 0) goto L_0x008f
            java.lang.String r4 = "a_isppn"
            r3.put(r4, r6)     // Catch:{ Exception -> 0x00b0 }
            goto L_0x0094
        L_0x008f:
            java.lang.String r4 = "a_isppn"
            r3.put(r4, r5)     // Catch:{ Exception -> 0x00b0 }
        L_0x0094:
            java.lang.String r4 = "a_ofn"
            java.lang.CharSequence r5 = r2.operatorFriendlyName     // Catch:{ Exception -> 0x00b0 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r4 = "a_vn"
            java.lang.CharSequence r5 = r2.venueName     // Catch:{ Exception -> 0x00b0 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x00b0 }
        L_0x00a2:
            java.lang.String r4 = "a_dc"
            int r2 = r2.describeContents()     // Catch:{ Exception -> 0x00b0 }
            r3.put(r4, r2)     // Catch:{ Exception -> 0x00b0 }
            r0.put(r3)     // Catch:{ Exception -> 0x00b0 }
            goto L_0x001f
        L_0x00b0:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r7, r2)
            goto L_0x001f
        L_0x00b6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.d.j(android.content.Context):org.json.JSONArray");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r7 = com.stub.StubApp.getOrigApplicationContext(r7.getApplicationContext());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONArray k(android.content.Context r7) {
        /*
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            if (r7 == 0) goto L_0x0052
            android.content.Context r7 = r7.getApplicationContext()
            android.content.Context r7 = com.stub.StubApp.getOrigApplicationContext(r7)
            java.util.List r1 = com.umeng.commonsdk.internal.utils.a.m(r7)
            if (r1 == 0) goto L_0x0052
            java.util.Iterator r1 = r1.iterator()
        L_0x0019:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0052
            java.lang.Object r2 = r1.next()
            android.view.inputmethod.InputMethodInfo r2 = (android.view.inputmethod.InputMethodInfo) r2
            android.content.pm.PackageManager r3 = r7.getPackageManager()     // Catch:{ Exception -> 0x004d }
            java.lang.CharSequence r3 = r2.loadLabel(r3)     // Catch:{ Exception -> 0x004d }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ Exception -> 0x004d }
            r4.<init>()     // Catch:{ Exception -> 0x004d }
            java.lang.String r5 = "a_la"
            r4.put(r5, r3)     // Catch:{ Exception -> 0x004d }
            java.lang.String r3 = "a_pn"
            java.lang.String r2 = r2.getPackageName()     // Catch:{ Exception -> 0x004d }
            r4.put(r3, r2)     // Catch:{ Exception -> 0x004d }
            java.lang.String r2 = "ts"
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x004d }
            r4.put(r2, r5)     // Catch:{ Exception -> 0x004d }
            r0.put(r4)     // Catch:{ Exception -> 0x004d }
            goto L_0x0019
        L_0x004d:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r7, r2)
            goto L_0x0019
        L_0x0052:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.d.k(android.content.Context):org.json.JSONArray");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r6 = com.stub.StubApp.getOrigApplicationContext(r6.getApplicationContext());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONArray l(android.content.Context r6) {
        /*
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            if (r6 == 0) goto L_0x0052
            android.content.Context r6 = r6.getApplicationContext()
            android.content.Context r6 = com.stub.StubApp.getOrigApplicationContext(r6)
            java.util.List r1 = com.umeng.commonsdk.internal.utils.j.e(r6)
            if (r1 == 0) goto L_0x0052
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L_0x0052
            java.util.Iterator r1 = r1.iterator()
        L_0x001f:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0052
            java.lang.Object r2 = r1.next()
            com.umeng.commonsdk.internal.utils.j$a r2 = (com.umeng.commonsdk.internal.utils.j.a) r2
            if (r2 == 0) goto L_0x001f
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x004d }
            r3.<init>()     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = "a_w"
            int r5 = r2.a     // Catch:{ Exception -> 0x004d }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = "a_h"
            int r2 = r2.b     // Catch:{ Exception -> 0x004d }
            r3.put(r4, r2)     // Catch:{ Exception -> 0x004d }
            java.lang.String r2 = "ts"
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x004d }
            r3.put(r2, r4)     // Catch:{ Exception -> 0x004d }
            r0.put(r3)     // Catch:{ Exception -> 0x004d }
            goto L_0x001f
        L_0x004d:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r2)
            goto L_0x001f
        L_0x0052:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.d.l(android.content.Context):org.json.JSONArray");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r6 = com.stub.StubApp.getOrigApplicationContext(r6.getApplicationContext());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONArray m(android.content.Context r6) {
        /*
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            if (r6 == 0) goto L_0x0052
            android.content.Context r6 = r6.getApplicationContext()
            android.content.Context r6 = com.stub.StubApp.getOrigApplicationContext(r6)
            java.util.List r1 = com.umeng.commonsdk.internal.utils.a.p(r6)
            if (r1 == 0) goto L_0x0052
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L_0x0052
            java.util.Iterator r1 = r1.iterator()
        L_0x001f:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0052
            java.lang.Object r2 = r1.next()
            com.umeng.commonsdk.internal.utils.a$a r2 = (com.umeng.commonsdk.internal.utils.a.C0007a) r2
            if (r2 == 0) goto L_0x001f
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x004d }
            r3.<init>()     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = "a_pn"
            java.lang.String r5 = r2.a     // Catch:{ Exception -> 0x004d }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = "a_la"
            java.lang.String r2 = r2.b     // Catch:{ Exception -> 0x004d }
            r3.put(r4, r2)     // Catch:{ Exception -> 0x004d }
            java.lang.String r2 = "ts"
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x004d }
            r3.put(r2, r4)     // Catch:{ Exception -> 0x004d }
            r0.put(r3)     // Catch:{ Exception -> 0x004d }
            goto L_0x001f
        L_0x004d:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r2)
            goto L_0x001f
        L_0x0052:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.d.m(android.content.Context):org.json.JSONArray");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r5 = com.stub.StubApp.getOrigApplicationContext(r5.getApplicationContext());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject n(android.content.Context r5) {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            if (r5 == 0) goto L_0x0037
            android.content.Context r5 = r5.getApplicationContext()
            android.content.Context r5 = com.stub.StubApp.getOrigApplicationContext(r5)
            android.app.ActivityManager$MemoryInfo r1 = com.umeng.commonsdk.internal.utils.a.q(r5)
            if (r1 == 0) goto L_0x0037
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0033 }
            r3 = 16
            if (r2 < r3) goto L_0x0022
            java.lang.String r2 = "t"
            long r3 = r1.totalMem     // Catch:{ Exception -> 0x0033 }
            r0.put(r2, r3)     // Catch:{ Exception -> 0x0033 }
        L_0x0022:
            java.lang.String r2 = "f"
            long r3 = r1.availMem     // Catch:{ Exception -> 0x0033 }
            r0.put(r2, r3)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r1 = "ts"
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0033 }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0037
        L_0x0033:
            r1 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r1)
        L_0x0037:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.d.n(android.content.Context):org.json.JSONObject");
    }
}
