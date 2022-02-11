package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.a;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.utils.UMUtils;
import org.json.JSONObject;

public class UMSLEnvelopeBuild {
    private static final String TAG = "UMSLEnvelopeBuild";
    private static String cacheSystemheader;
    private static boolean isEncryptEnabled;
    public static Context mContext;
    public static String module;

    private c constructEnvelope(Context context, byte[] bArr) {
        int i;
        c a;
        synchronized (this) {
            String imprintProperty = UMEnvelopeBuild.imprintProperty(context, "slcodex", (String) null);
            ULog.i("walle", "[stateless] build envelope, codexStr is " + imprintProperty);
            try {
                i = !TextUtils.isEmpty(imprintProperty) ? Integer.valueOf(imprintProperty).intValue() : -1;
            } catch (NumberFormatException e) {
                UMCrashManager.reportCrash(context, e);
                i = -1;
            }
            if (i == 0) {
                ULog.i("walle", "[stateless] build envelope, codexValue is 0");
                a = c.a(context, UMUtils.getAppkey(context), bArr);
            } else if (i == 1) {
                ULog.i("walle", "[stateless] build envelope, codexValue is 1");
                a = c.b(context, UMUtils.getAppkey(context), bArr);
            } else if (isEncryptEnabled) {
                ULog.i("walle", "[stateless] build envelope, isEncryptEnabled is true");
                a = c.b(context, UMUtils.getAppkey(context), bArr);
            } else {
                ULog.i("walle", "[stateless] build envelope, isEncryptEnabled is false");
                a = c.a(context, UMUtils.getAppkey(context), bArr);
            }
        }
        return a;
    }

    private JSONObject makeErrorResult(int i, JSONObject jSONObject) {
        synchronized (this) {
            if (jSONObject != null) {
                try {
                    jSONObject.put("exception", i);
                } catch (Exception e) {
                }
            } else {
                jSONObject = new JSONObject();
                try {
                    jSONObject.put("exception", i);
                } catch (Exception e2) {
                }
            }
        }
        return jSONObject;
    }

    public static void setEncryptEnabled(boolean z) {
        isEncryptEnabled = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:76:0x025e A[Catch:{ Exception -> 0x02e4 }] */
    public JSONObject buildSLBaseHeader(Context context) {
        JSONObject jSONObject;
        String str;
        synchronized (this) {
            ULog.i("walle", "[stateless] begin build hader, thread is " + Thread.currentThread());
            if (context == null) {
                return null;
            }
            Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
            try {
                if (!TextUtils.isEmpty(cacheSystemheader)) {
                    try {
                        jSONObject = new JSONObject(cacheSystemheader);
                    } catch (Exception e) {
                        jSONObject = null;
                    }
                } else {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(e.o, DeviceConfig.getAppMD5Signature(origApplicationContext));
                    jSONObject2.put(e.p, DeviceConfig.getAppSHA1Key(origApplicationContext));
                    jSONObject2.put(e.q, DeviceConfig.getAppHashKey(origApplicationContext));
                    jSONObject2.put("app_version", DeviceConfig.getAppVersionName(origApplicationContext));
                    jSONObject2.put("version_code", Integer.parseInt(DeviceConfig.getAppVersionCode(origApplicationContext)));
                    jSONObject2.put(e.u, DeviceConfig.getDeviceIdUmengMD5(origApplicationContext));
                    jSONObject2.put(e.v, DeviceConfig.getCPU());
                    String mccmnc = DeviceConfig.getMCCMNC(origApplicationContext);
                    if (!TextUtils.isEmpty(mccmnc)) {
                        jSONObject2.put(e.A, mccmnc);
                    } else {
                        jSONObject2.put(e.A, "");
                    }
                    String subOSName = DeviceConfig.getSubOSName(origApplicationContext);
                    if (!TextUtils.isEmpty(subOSName)) {
                        jSONObject2.put(e.J, subOSName);
                    }
                    String subOSVersion = DeviceConfig.getSubOSVersion(origApplicationContext);
                    if (!TextUtils.isEmpty(subOSVersion)) {
                        jSONObject2.put(e.K, subOSVersion);
                    }
                    String deviceType = DeviceConfig.getDeviceType(origApplicationContext);
                    if (!TextUtils.isEmpty(deviceType)) {
                        jSONObject2.put(e.af, deviceType);
                    }
                    jSONObject2.put(e.n, DeviceConfig.getPackageName(origApplicationContext));
                    jSONObject2.put(e.t, "Android");
                    jSONObject2.put("device_id", DeviceConfig.getDeviceId(origApplicationContext));
                    jSONObject2.put("device_model", Build.MODEL);
                    jSONObject2.put(e.D, Build.BOARD);
                    jSONObject2.put(e.E, Build.BRAND);
                    jSONObject2.put(e.F, Build.TIME);
                    jSONObject2.put(e.G, Build.MANUFACTURER);
                    jSONObject2.put(e.H, Build.ID);
                    jSONObject2.put(e.I, Build.DEVICE);
                    jSONObject2.put("os", "Android");
                    jSONObject2.put("os_version", Build.VERSION.RELEASE);
                    int[] resolutionArray = DeviceConfig.getResolutionArray(origApplicationContext);
                    if (resolutionArray != null) {
                        jSONObject2.put(e.y, resolutionArray[1] + "*" + resolutionArray[0]);
                    }
                    jSONObject2.put(e.z, DeviceConfig.getMac(origApplicationContext));
                    jSONObject2.put(e.L, DeviceConfig.getTimeZone(origApplicationContext));
                    String[] localeInfo = DeviceConfig.getLocaleInfo(origApplicationContext);
                    jSONObject2.put(e.N, localeInfo[0]);
                    jSONObject2.put(e.M, localeInfo[1]);
                    jSONObject2.put(e.O, DeviceConfig.getNetworkOperatorName(origApplicationContext));
                    jSONObject2.put("display_name", DeviceConfig.getAppName(origApplicationContext));
                    String[] networkAccessMode = DeviceConfig.getNetworkAccessMode(origApplicationContext);
                    if ("Wi-Fi".equals(networkAccessMode[0])) {
                        jSONObject2.put(e.P, "wifi");
                    } else if ("2G/3G".equals(networkAccessMode[0])) {
                        jSONObject2.put(e.P, "2G/3G");
                    } else {
                        jSONObject2.put(e.P, "unknow");
                    }
                    if (!"".equals(networkAccessMode[1])) {
                        jSONObject2.put(e.Q, networkAccessMode[1]);
                    }
                    jSONObject2.put(e.b, "2.0.0");
                    jSONObject2.put(e.c, SdkVersion.SDK_TYPE);
                    if (!TextUtils.isEmpty(module)) {
                        jSONObject2.put(e.d, module);
                    }
                    cacheSystemheader = jSONObject2.toString();
                    jSONObject = jSONObject2;
                }
                if (jSONObject == null) {
                    return null;
                }
                jSONObject.put("channel", UMUtils.getChannel(origApplicationContext));
                jSONObject.put("appkey", UMUtils.getAppkey(origApplicationContext));
                try {
                    if (SdkVersion.SDK_TYPE != 1) {
                        try {
                            Class<?> cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
                            if (cls != null) {
                                str = (String) cls.getMethod("getUmtt", new Class[]{Context.class}).invoke(cls, new Object[]{origApplicationContext});
                                if (!TextUtils.isEmpty(str)) {
                                    jSONObject.put(e.e, str);
                                }
                            }
                        } catch (Throwable th) {
                        }
                        str = null;
                        if (!TextUtils.isEmpty(str)) {
                        }
                    }
                } catch (Exception e2) {
                }
                try {
                    String imprintProperty = UMEnvelopeBuild.imprintProperty(origApplicationContext, e.f, (String) null);
                    if (!TextUtils.isEmpty(imprintProperty)) {
                        jSONObject.put(e.f, imprintProperty);
                    }
                } catch (Exception e3) {
                }
                try {
                    if (!(SdkVersion.SDK_TYPE == 1 || a.b(origApplicationContext) == null)) {
                        jSONObject.put(e.g, a.b(origApplicationContext));
                    }
                } catch (Exception e4) {
                }
                try {
                    jSONObject.put("wrapper_type", a.a);
                    jSONObject.put("wrapper_version", a.b);
                } catch (Exception e5) {
                }
                if (jSONObject != null) {
                    if (jSONObject.length() > 0) {
                        JSONObject jSONObject3 = new JSONObject();
                        ULog.i("walle", "[stateless] build header end , header is " + jSONObject.toString() + ", thread is " + Thread.currentThread());
                        JSONObject put = jSONObject3.put("header", jSONObject);
                        return put;
                    }
                }
                ULog.i("walle", "[stateless] build header end , header is null !!! thread is " + Thread.currentThread());
                return null;
            } catch (Throwable th2) {
                UMCrashManager.reportCrash(origApplicationContext, th2);
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CodeShrinkVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x00a0: MOVE  (r0v55 java.lang.String) = (r0v54 java.lang.String)
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.visit(CodeShrinkVisitor.java:35)
        */
    public org.json.JSONObject buildSLEnvelope(android.content.Context r7, org.json.JSONObject r8, org.json.JSONObject r9, java.lang.String r10) {
        /*
            r6 = this;
            r1 = 0
            monitor-enter(r6)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0286 }
            r0.<init>()     // Catch:{ all -> 0x0286 }
            java.lang.String r2 = "[stateless] build envelope, heade is "
            r0.append(r2)     // Catch:{ all -> 0x0286 }
            java.lang.String r2 = r8.toString()     // Catch:{ all -> 0x0286 }
            r0.append(r2)     // Catch:{ all -> 0x0286 }
            java.lang.String r2 = "walle"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0286 }
            r4 = 0
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0286 }
            r3[r4] = r0     // Catch:{ all -> 0x0286 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ all -> 0x0286 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0286 }
            r0.<init>()     // Catch:{ all -> 0x0286 }
            java.lang.String r2 = "[stateless] build envelope, body is "
            r0.append(r2)     // Catch:{ all -> 0x0286 }
            java.lang.String r2 = r9.toString()     // Catch:{ all -> 0x0286 }
            r0.append(r2)     // Catch:{ all -> 0x0286 }
            java.lang.String r2 = "walle"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0286 }
            r4 = 0
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0286 }
            r3[r4] = r0     // Catch:{ all -> 0x0286 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ all -> 0x0286 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0286 }
            r0.<init>()     // Catch:{ all -> 0x0286 }
            java.lang.String r2 = "[stateless] build envelope, thread is "
            r0.append(r2)     // Catch:{ all -> 0x0286 }
            java.lang.Thread r2 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0286 }
            r0.append(r2)     // Catch:{ all -> 0x0286 }
            java.lang.String r2 = "walle"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0286 }
            r4 = 0
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0286 }
            r3[r4] = r0     // Catch:{ all -> 0x0286 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ all -> 0x0286 }
            if (r7 == 0) goto L_0x006a
            if (r8 == 0) goto L_0x006a
            if (r9 == 0) goto L_0x006a
            if (r10 != 0) goto L_0x0080
        L_0x006a:
            java.lang.String r0 = "walle"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0286 }
            r2 = 0
            java.lang.String r3 = "[stateless] build envelope, context is null or header is null or body is null"
            r1[r2] = r3     // Catch:{ all -> 0x0286 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r0, (java.lang.Object[]) r1)     // Catch:{ all -> 0x0286 }
            r0 = 110(0x6e, float:1.54E-43)
            r1 = 0
            org.json.JSONObject r8 = r6.makeErrorResult(r0, r1)     // Catch:{ all -> 0x0286 }
            monitor-exit(r6)
        L_0x007f:
            return r8
        L_0x0080:
            android.content.Context r0 = r7.getApplicationContext()     // Catch:{ Throwable -> 0x0258 }
            android.content.Context r7 = com.stub.StubApp.getOrigApplicationContext(r0)     // Catch:{ Throwable -> 0x0258 }
            if (r8 == 0) goto L_0x00b4
            if (r9 == 0) goto L_0x00b4
            java.util.Iterator r2 = r9.keys()     // Catch:{ Throwable -> 0x028c }
        L_0x0090:
            boolean r0 = r2.hasNext()     // Catch:{ Throwable -> 0x028c }
            if (r0 == 0) goto L_0x00b4
            java.lang.Object r0 = r2.next()     // Catch:{ Throwable -> 0x028c }
            if (r0 == 0) goto L_0x0090
            boolean r3 = r0 instanceof java.lang.String     // Catch:{ Throwable -> 0x028c }
            if (r3 == 0) goto L_0x0090
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x028c }
            if (r0 == 0) goto L_0x0090
            java.lang.Object r3 = r9.opt(r0)     // Catch:{ Throwable -> 0x028c }
            if (r3 == 0) goto L_0x0090
            java.lang.Object r3 = r9.opt(r0)     // Catch:{ Exception -> 0x00b2 }
            r8.put(r0, r3)     // Catch:{ Exception -> 0x00b2 }
            goto L_0x0090
        L_0x00b2:
            r0 = move-exception
            goto L_0x0090
        L_0x00b4:
            if (r8 == 0) goto L_0x00e7
            com.umeng.commonsdk.statistics.idtracking.e r0 = com.umeng.commonsdk.statistics.idtracking.e.a((android.content.Context) r7)     // Catch:{ Exception -> 0x0289 }
            if (r0 == 0) goto L_0x00e7
            r0.a()     // Catch:{ Exception -> 0x0289 }
            com.umeng.commonsdk.statistics.proto.c r0 = r0.b()     // Catch:{ Exception -> 0x0289 }
            com.umeng.commonsdk.proguard.s r2 = new com.umeng.commonsdk.proguard.s     // Catch:{ Exception -> 0x0289 }
            r2.<init>()     // Catch:{ Exception -> 0x0289 }
            byte[] r0 = r2.a(r0)     // Catch:{ Exception -> 0x0289 }
            r2 = 0
            java.lang.String r0 = android.util.Base64.encodeToString(r0, r2)     // Catch:{ Exception -> 0x0289 }
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x0289 }
            if (r2 != 0) goto L_0x00e7
            java.lang.String r2 = "header"
            org.json.JSONObject r2 = r8.getJSONObject(r2)     // Catch:{ Exception -> 0x0289 }
            java.lang.String r3 = "id_tracking"
            r2.put(r3, r0)     // Catch:{ Exception -> 0x0289 }
            java.lang.String r0 = "header"
            r8.put(r0, r2)     // Catch:{ Exception -> 0x0289 }
        L_0x00e7:
            if (r8 == 0) goto L_0x0129
            java.lang.String r0 = r8.toString()     // Catch:{ Throwable -> 0x028c }
            byte[] r0 = r0.getBytes()     // Catch:{ Throwable -> 0x028c }
            int r0 = r0.length     // Catch:{ Throwable -> 0x028c }
            long r2 = (long) r0     // Catch:{ Throwable -> 0x028c }
            long r4 = com.umeng.commonsdk.stateless.a.c     // Catch:{ Throwable -> 0x028c }
            boolean r0 = com.umeng.commonsdk.stateless.f.a((long) r2, (long) r4)     // Catch:{ Throwable -> 0x028c }
            if (r0 == 0) goto L_0x0129
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x028c }
            r0.<init>()     // Catch:{ Throwable -> 0x028c }
            java.lang.String r1 = "[stateless] build envelope, json overstep!!!! size is "
            r0.append(r1)     // Catch:{ Throwable -> 0x028c }
            java.lang.String r1 = r8.toString()     // Catch:{ Throwable -> 0x028c }
            byte[] r1 = r1.getBytes()     // Catch:{ Throwable -> 0x028c }
            int r1 = r1.length     // Catch:{ Throwable -> 0x028c }
            r0.append(r1)     // Catch:{ Throwable -> 0x028c }
            java.lang.String r1 = "walle"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x028c }
            r3 = 0
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x028c }
            r2[r3] = r0     // Catch:{ Throwable -> 0x028c }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r1, (java.lang.Object[]) r2)     // Catch:{ Throwable -> 0x028c }
            r0 = 113(0x71, float:1.58E-43)
            org.json.JSONObject r8 = r6.makeErrorResult(r0, r8)     // Catch:{ Throwable -> 0x028c }
            monitor-exit(r6)
            goto L_0x007f
        L_0x0129:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x028c }
            r0.<init>()     // Catch:{ Throwable -> 0x028c }
            java.lang.String r2 = "[stateless] build envelope, json size is "
            r0.append(r2)     // Catch:{ Throwable -> 0x028c }
            java.lang.String r2 = r8.toString()     // Catch:{ Throwable -> 0x028c }
            byte[] r2 = r2.getBytes()     // Catch:{ Throwable -> 0x028c }
            int r2 = r2.length     // Catch:{ Throwable -> 0x028c }
            r0.append(r2)     // Catch:{ Throwable -> 0x028c }
            java.lang.String r2 = "walle"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x028c }
            r4 = 0
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x028c }
            r3[r4] = r0     // Catch:{ Throwable -> 0x028c }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ Throwable -> 0x028c }
            if (r8 == 0) goto L_0x0174
            java.lang.String r0 = r8.toString()     // Catch:{ Throwable -> 0x028c }
            byte[] r0 = r0.getBytes()     // Catch:{ Throwable -> 0x028c }
            com.umeng.commonsdk.stateless.c r0 = r6.constructEnvelope(r7, r0)     // Catch:{ Throwable -> 0x028c }
            if (r0 != 0) goto L_0x0175
            java.lang.String r0 = "walle"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x028c }
            r2 = 0
            java.lang.String r3 = "[stateless] build envelope, envelope is null !!!!"
            r1[r2] = r3     // Catch:{ Throwable -> 0x028c }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r0, (java.lang.Object[]) r1)     // Catch:{ Throwable -> 0x028c }
            r0 = 111(0x6f, float:1.56E-43)
            org.json.JSONObject r8 = r6.makeErrorResult(r0, r8)     // Catch:{ Throwable -> 0x028c }
            monitor-exit(r6)
            goto L_0x007f
        L_0x0174:
            r0 = r1
        L_0x0175:
            if (r0 == 0) goto L_0x01af
            byte[] r1 = r0.b()     // Catch:{ Throwable -> 0x028c }
            int r1 = r1.length     // Catch:{ Throwable -> 0x028c }
            long r2 = (long) r1     // Catch:{ Throwable -> 0x028c }
            long r4 = com.umeng.commonsdk.stateless.a.d     // Catch:{ Throwable -> 0x028c }
            boolean r1 = com.umeng.commonsdk.stateless.f.a((long) r2, (long) r4)     // Catch:{ Throwable -> 0x028c }
            if (r1 == 0) goto L_0x01af
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x028c }
            r1.<init>()     // Catch:{ Throwable -> 0x028c }
            java.lang.String r2 = "[stateless] build envelope, envelope overstep!!!! size is "
            r1.append(r2)     // Catch:{ Throwable -> 0x028c }
            byte[] r0 = r0.b()     // Catch:{ Throwable -> 0x028c }
            int r0 = r0.length     // Catch:{ Throwable -> 0x028c }
            r1.append(r0)     // Catch:{ Throwable -> 0x028c }
            java.lang.String r0 = "walle"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x028c }
            r3 = 0
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x028c }
            r2[r3] = r1     // Catch:{ Throwable -> 0x028c }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r0, (java.lang.Object[]) r2)     // Catch:{ Throwable -> 0x028c }
            r0 = 114(0x72, float:1.6E-43)
            org.json.JSONObject r8 = r6.makeErrorResult(r0, r8)     // Catch:{ Throwable -> 0x028c }
            monitor-exit(r6)
            goto L_0x007f
        L_0x01af:
            byte[] r1 = r10.getBytes()     // Catch:{ Throwable -> 0x028c }
            r2 = 0
            java.lang.String r1 = android.util.Base64.encodeToString(r1, r2)     // Catch:{ Throwable -> 0x028c }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x028c }
            r2.<init>()     // Catch:{ Throwable -> 0x028c }
            r2.append(r10)     // Catch:{ Throwable -> 0x028c }
            java.lang.String r3 = "_"
            r2.append(r3)     // Catch:{ Throwable -> 0x028c }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x028c }
            r2.append(r4)     // Catch:{ Throwable -> 0x028c }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x028c }
            byte[] r2 = r2.getBytes()     // Catch:{ Throwable -> 0x028c }
            r3 = 0
            java.lang.String r2 = android.util.Base64.encodeToString(r2, r3)     // Catch:{ Throwable -> 0x028c }
            byte[] r0 = r0.b()     // Catch:{ Throwable -> 0x028c }
            boolean r0 = com.umeng.commonsdk.stateless.f.a(r7, r1, r2, r0)     // Catch:{ Throwable -> 0x028c }
            if (r0 != 0) goto L_0x01f9
            java.lang.String r0 = "walle"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x028c }
            r2 = 0
            java.lang.String r3 = "[stateless] build envelope, save fail ----->>>>>"
            r1[r2] = r3     // Catch:{ Throwable -> 0x028c }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r0, (java.lang.Object[]) r1)     // Catch:{ Throwable -> 0x028c }
            r0 = 101(0x65, float:1.42E-43)
            org.json.JSONObject r8 = r6.makeErrorResult(r0, r8)     // Catch:{ Throwable -> 0x028c }
            monitor-exit(r6)
            goto L_0x007f
        L_0x01f9:
            java.lang.String r0 = "walle"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x028c }
            r2 = 0
            java.lang.String r3 = "[stateless] build envelope, save ok ----->>>>>"
            r1[r2] = r3     // Catch:{ Throwable -> 0x028c }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r0, (java.lang.Object[]) r1)     // Catch:{ Throwable -> 0x028c }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x028c }
            r0.<init>()     // Catch:{ Throwable -> 0x028c }
            java.lang.String r1 = "[stateless] envelope file size is "
            r0.append(r1)     // Catch:{ Throwable -> 0x028c }
            java.lang.String r1 = r8.toString()     // Catch:{ Throwable -> 0x028c }
            byte[] r1 = r1.getBytes()     // Catch:{ Throwable -> 0x028c }
            int r1 = r1.length     // Catch:{ Throwable -> 0x028c }
            r0.append(r1)     // Catch:{ Throwable -> 0x028c }
            java.lang.String r1 = "walle"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x028c }
            r3 = 0
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x028c }
            r2[r3] = r0     // Catch:{ Throwable -> 0x028c }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r1, (java.lang.Object[]) r2)     // Catch:{ Throwable -> 0x028c }
            com.umeng.commonsdk.stateless.d r0 = new com.umeng.commonsdk.stateless.d     // Catch:{ Throwable -> 0x028c }
            r0.<init>(r7)     // Catch:{ Throwable -> 0x028c }
            r0 = 273(0x111, float:3.83E-43)
            com.umeng.commonsdk.stateless.d.b(r0)     // Catch:{ Throwable -> 0x028c }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x028c }
            r0.<init>()     // Catch:{ Throwable -> 0x028c }
            java.lang.String r1 = "[stateless] build envelope end, thread is "
            r0.append(r1)     // Catch:{ Throwable -> 0x028c }
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ Throwable -> 0x028c }
            r0.append(r1)     // Catch:{ Throwable -> 0x028c }
            java.lang.String r1 = "walle"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x028c }
            r3 = 0
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x028c }
            r2[r3] = r0     // Catch:{ Throwable -> 0x028c }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r1, (java.lang.Object[]) r2)     // Catch:{ Throwable -> 0x028c }
            monitor-exit(r6)
            goto L_0x007f
        L_0x0258:
            r0 = move-exception
        L_0x0259:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r7, r0)     // Catch:{ all -> 0x0286 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0286 }
            r0.<init>()     // Catch:{ all -> 0x0286 }
            java.lang.String r1 = "build envelope end, thread is "
            r0.append(r1)     // Catch:{ all -> 0x0286 }
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0286 }
            r0.append(r1)     // Catch:{ all -> 0x0286 }
            java.lang.String r1 = "walle"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0286 }
            r3 = 0
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0286 }
            r2[r3] = r0     // Catch:{ all -> 0x0286 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r1, (java.lang.Object[]) r2)     // Catch:{ all -> 0x0286 }
            r0 = 110(0x6e, float:1.54E-43)
            r1 = 0
            org.json.JSONObject r8 = r6.makeErrorResult(r0, r1)     // Catch:{ all -> 0x0286 }
            monitor-exit(r6)
            goto L_0x007f
        L_0x0286:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        L_0x0289:
            r0 = move-exception
            goto L_0x00e7
        L_0x028c:
            r0 = move-exception
            goto L_0x0259
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.buildSLEnvelope(android.content.Context, org.json.JSONObject, org.json.JSONObject, java.lang.String):org.json.JSONObject");
    }
}
