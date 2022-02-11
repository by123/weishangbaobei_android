package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.a;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.idtracking.Envelope;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    public static String a = null;
    public static String b = "";
    private static final String c = "EnvelopeManager";
    private static String d;
    private static boolean f;
    private int e = 0;

    private int a(Context context, Envelope envelope, String str, String str2) {
        if (context == null || envelope == null || TextUtils.isEmpty(str)) {
            return 101;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = DeviceConfig.getAppVersionName(context);
        }
        return UMFrUtils.saveEnvelopeFile(context, str + "&&" + str2 + "_" + System.currentTimeMillis() + "_envelope.log", envelope.toBinary());
    }

    public static long a(Context context) {
        long j = DataHelper.ENVELOPE_ENTITY_RAW_LENGTH_MAX - DataHelper.ENVELOPE_EXTRA_LENGTH;
        JSONObject b2 = b(context);
        if (!(b2 == null || b2.toString() == null || b2.toString().getBytes() == null)) {
            long length = (long) b2.toString().getBytes().length;
            if (ULog.DEBUG) {
                Log.i(c, "headerLen size is " + length);
            }
            j -= length;
        }
        if (ULog.DEBUG) {
            Log.i(c, "free size is " + j);
        }
        return j;
    }

    private Envelope a(Context context, byte[] bArr) {
        int i;
        String imprintProperty = UMEnvelopeBuild.imprintProperty(context, "codex", (String) null);
        try {
            i = !TextUtils.isEmpty(imprintProperty) ? Integer.valueOf(imprintProperty).intValue() : -1;
        } catch (NumberFormatException e2) {
            UMCrashManager.reportCrash(context, e2);
            i = -1;
        }
        return i == 0 ? Envelope.genEnvelope(context, UMUtils.getAppkey(context), bArr) : i == 1 ? Envelope.genEncryptEnvelope(context, UMUtils.getAppkey(context), bArr) : f ? Envelope.genEncryptEnvelope(context, UMUtils.getAppkey(context), bArr) : Envelope.genEnvelope(context, UMUtils.getAppkey(context), bArr);
    }

    private JSONObject a(int i, JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                jSONObject.put("exception", i);
            } catch (Exception e2) {
            }
        } else {
            jSONObject = new JSONObject();
            try {
                jSONObject.put("exception", i);
            } catch (Exception e3) {
            }
        }
        return jSONObject;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CodeShrinkVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x0032: MOVE  (r1v2 java.lang.String) = (r1v1 java.lang.String)
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.visit(CodeShrinkVisitor.java:35)
        */
    private org.json.JSONObject a(org.json.JSONObject r5, org.json.JSONObject r6) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x0062
            if (r6 == 0) goto L_0x0062
            java.lang.String r0 = "header"
            java.lang.Object r0 = r5.opt(r0)
            if (r0 == 0) goto L_0x0062
            java.lang.String r0 = "header"
            java.lang.Object r0 = r5.opt(r0)
            boolean r0 = r0 instanceof org.json.JSONObject
            if (r0 == 0) goto L_0x0062
            java.lang.String r0 = "header"
            java.lang.Object r0 = r5.opt(r0)
            org.json.JSONObject r0 = (org.json.JSONObject) r0
            java.util.Iterator r2 = r6.keys()
        L_0x0022:
            boolean r1 = r2.hasNext()
            if (r1 == 0) goto L_0x0062
            java.lang.Object r1 = r2.next()
            if (r1 == 0) goto L_0x0022
            boolean r3 = r1 instanceof java.lang.String
            if (r3 == 0) goto L_0x0022
            java.lang.String r1 = (java.lang.String) r1
            if (r1 == 0) goto L_0x0022
            java.lang.Object r3 = r6.opt(r1)
            if (r3 == 0) goto L_0x0022
            java.lang.Object r3 = r6.opt(r1)     // Catch:{ Exception -> 0x0060 }
            r0.put(r1, r3)     // Catch:{ Exception -> 0x0060 }
            java.lang.String r3 = "vertical_type"
            boolean r3 = r1.equals(r3)     // Catch:{ Exception -> 0x0060 }
            if (r3 == 0) goto L_0x0022
            java.lang.Object r3 = r6.opt(r1)     // Catch:{ Exception -> 0x0060 }
            boolean r3 = r3 instanceof java.lang.Integer     // Catch:{ Exception -> 0x0060 }
            if (r3 == 0) goto L_0x0022
            java.lang.Object r1 = r6.opt(r1)     // Catch:{ Exception -> 0x0060 }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ Exception -> 0x0060 }
            int r1 = r1.intValue()     // Catch:{ Exception -> 0x0060 }
            r4.e = r1     // Catch:{ Exception -> 0x0060 }
            goto L_0x0022
        L_0x0060:
            r1 = move-exception
            goto L_0x0022
        L_0x0062:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.b.a(org.json.JSONObject, org.json.JSONObject):org.json.JSONObject");
    }

    public static void a(boolean z) {
        f = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x0243 A[Catch:{ Exception -> 0x02b0 }] */
    private static JSONObject b(Context context) {
        JSONObject jSONObject;
        String str;
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
        if (!TextUtils.isEmpty(d)) {
            try {
                jSONObject = new JSONObject(d);
            } catch (Exception e2) {
                jSONObject = null;
            }
        } else {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(e.o, DeviceConfig.getAppMD5Signature(context));
            jSONObject2.put(e.p, DeviceConfig.getAppSHA1Key(context));
            jSONObject2.put(e.q, DeviceConfig.getAppHashKey(context));
            jSONObject2.put("app_version", DeviceConfig.getAppVersionName(context));
            jSONObject2.put("version_code", Integer.parseInt(DeviceConfig.getAppVersionCode(context)));
            jSONObject2.put(e.u, DeviceConfig.getDeviceIdUmengMD5(context));
            jSONObject2.put(e.v, DeviceConfig.getCPU());
            String mccmnc = DeviceConfig.getMCCMNC(context);
            if (!TextUtils.isEmpty(mccmnc)) {
                jSONObject2.put(e.A, mccmnc);
                b = mccmnc;
            } else {
                jSONObject2.put(e.A, "");
            }
            String subOSName = DeviceConfig.getSubOSName(context);
            if (!TextUtils.isEmpty(subOSName)) {
                jSONObject2.put(e.J, subOSName);
            }
            String subOSVersion = DeviceConfig.getSubOSVersion(context);
            if (!TextUtils.isEmpty(subOSVersion)) {
                jSONObject2.put(e.K, subOSVersion);
            }
            String deviceType = DeviceConfig.getDeviceType(context);
            if (!TextUtils.isEmpty(deviceType)) {
                jSONObject2.put(e.af, deviceType);
            }
            jSONObject2.put(e.n, DeviceConfig.getPackageName(context));
            jSONObject2.put(e.t, "Android");
            jSONObject2.put("device_id", DeviceConfig.getDeviceId(context));
            jSONObject2.put("device_model", Build.MODEL);
            jSONObject2.put(e.D, Build.BOARD);
            jSONObject2.put(e.E, Build.BRAND);
            jSONObject2.put(e.F, Build.TIME);
            jSONObject2.put(e.G, Build.MANUFACTURER);
            jSONObject2.put(e.H, Build.ID);
            jSONObject2.put(e.I, Build.DEVICE);
            jSONObject2.put("os", "Android");
            jSONObject2.put("os_version", Build.VERSION.RELEASE);
            int[] resolutionArray = DeviceConfig.getResolutionArray(context);
            if (resolutionArray != null) {
                jSONObject2.put(e.y, resolutionArray[1] + "*" + resolutionArray[0]);
            }
            jSONObject2.put(e.z, DeviceConfig.getMac(context));
            jSONObject2.put(e.L, DeviceConfig.getTimeZone(context));
            String[] localeInfo = DeviceConfig.getLocaleInfo(context);
            jSONObject2.put(e.N, localeInfo[0]);
            jSONObject2.put(e.M, localeInfo[1]);
            jSONObject2.put(e.O, DeviceConfig.getNetworkOperatorName(context));
            jSONObject2.put("display_name", DeviceConfig.getAppName(context));
            String[] networkAccessMode = DeviceConfig.getNetworkAccessMode(context);
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
            if (!TextUtils.isEmpty(a)) {
                jSONObject2.put(e.d, a);
            }
            d = jSONObject2.toString();
            jSONObject = jSONObject2;
        }
        if (jSONObject == null) {
            return null;
        }
        try {
            jSONObject.put(e.R, sharedPreferences.getInt("successful_request", 0));
            jSONObject.put(e.S, sharedPreferences.getInt(e.S, 0));
            jSONObject.put(e.T, sharedPreferences.getInt("last_request_spent_ms", 0));
        } catch (Exception e3) {
        }
        jSONObject.put("channel", UMUtils.getChannel(context));
        jSONObject.put("appkey", UMUtils.getAppkey(context));
        try {
            String deviceToken = UMUtils.getDeviceToken(context);
            if (!TextUtils.isEmpty(deviceToken)) {
                jSONObject.put(e.a, deviceToken);
            }
        } catch (Exception e4) {
            UMCrashManager.reportCrash(context, e4);
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
        try {
            if (SdkVersion.SDK_TYPE != 1) {
                try {
                    Class<?> cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
                    if (cls != null) {
                        str = (String) cls.getMethod("getUmtt", new Class[]{Context.class}).invoke(cls, new Object[]{context});
                        if (!TextUtils.isEmpty(str)) {
                            jSONObject.put(e.e, str);
                        }
                    }
                } catch (Throwable th2) {
                }
                str = null;
                if (!TextUtils.isEmpty(str)) {
                }
            }
        } catch (Exception e5) {
            UMCrashManager.reportCrash(context, e5);
        }
        try {
            String imprintProperty = UMEnvelopeBuild.imprintProperty(context, e.f, (String) null);
            if (!TextUtils.isEmpty(imprintProperty)) {
                jSONObject.put(e.f, imprintProperty);
            }
        } catch (Exception e6) {
            UMCrashManager.reportCrash(context, e6);
        }
        try {
            if (!(SdkVersion.SDK_TYPE == 1 || a.b(context) == null)) {
                jSONObject.put(e.g, a.b(context));
            }
        } catch (Exception e7) {
        }
        try {
            jSONObject.put("wrapper_type", a.a);
            jSONObject.put("wrapper_version", a.b);
        } catch (Exception e8) {
        }
        byte[] a2 = ImprintHandler.getImprintService(context).a();
        if (a2 != null && a2.length > 0) {
            try {
                jSONObject.put(e.U, Base64.encodeToString(a2, 0));
            } catch (JSONException e9) {
                UMCrashManager.reportCrash(context, e9);
            }
        }
        if (jSONObject != null) {
            if (jSONObject.length() > 0) {
                return new JSONObject().put("header", jSONObject);
            }
        }
        return null;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x0076: MOVE  (r0v88 java.lang.String) = (r0v87 java.lang.String)
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:122)
        	at jadx.core.dex.visitors.regions.TernaryMod.visitRegion(TernaryMod.java:34)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:73)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:31)
        */
    public org.json.JSONObject a(android.content.Context r12, org.json.JSONObject r13, org.json.JSONObject r14) {
        /*
            r11 = this;
            r10 = 110(0x6e, float:1.54E-43)
            r2 = 0
            boolean r0 = com.umeng.commonsdk.statistics.common.ULog.DEBUG
            if (r0 == 0) goto L_0x0049
            if (r13 == 0) goto L_0x0049
            if (r14 == 0) goto L_0x0049
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "headerJSONObject size is "
            r0.append(r1)
            java.lang.String r1 = r13.toString()
            byte[] r1 = r1.getBytes()
            int r1 = r1.length
            r0.append(r1)
            java.lang.String r1 = "EnvelopeManager"
            java.lang.String r0 = r0.toString()
            android.util.Log.i(r1, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "bodyJSONObject size is "
            r0.append(r1)
            java.lang.String r1 = r14.toString()
            byte[] r1 = r1.getBytes()
            int r1 = r1.length
            r0.append(r1)
            java.lang.String r1 = "EnvelopeManager"
            java.lang.String r0 = r0.toString()
            android.util.Log.i(r1, r0)
        L_0x0049:
            if (r12 == 0) goto L_0x004d
            if (r14 != 0) goto L_0x0052
        L_0x004d:
            org.json.JSONObject r0 = r11.a((int) r10, (org.json.JSONObject) r2)
        L_0x0051:
            return r0
        L_0x0052:
            org.json.JSONObject r1 = b(r12)     // Catch:{ Throwable -> 0x01fc }
            if (r1 == 0) goto L_0x005e
            if (r13 == 0) goto L_0x005e
            org.json.JSONObject r1 = r11.a((org.json.JSONObject) r1, (org.json.JSONObject) r13)     // Catch:{ Throwable -> 0x01fc }
        L_0x005e:
            if (r1 == 0) goto L_0x008a
            if (r14 == 0) goto L_0x008a
            java.util.Iterator r3 = r14.keys()     // Catch:{ Throwable -> 0x01fc }
        L_0x0066:
            boolean r0 = r3.hasNext()     // Catch:{ Throwable -> 0x01fc }
            if (r0 == 0) goto L_0x008a
            java.lang.Object r0 = r3.next()     // Catch:{ Throwable -> 0x01fc }
            if (r0 == 0) goto L_0x0066
            boolean r4 = r0 instanceof java.lang.String     // Catch:{ Throwable -> 0x01fc }
            if (r4 == 0) goto L_0x0066
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x01fc }
            if (r0 == 0) goto L_0x0066
            java.lang.Object r4 = r14.opt(r0)     // Catch:{ Throwable -> 0x01fc }
            if (r4 == 0) goto L_0x0066
            java.lang.Object r4 = r14.opt(r0)     // Catch:{ Exception -> 0x0088 }
            r1.put(r0, r4)     // Catch:{ Exception -> 0x0088 }
            goto L_0x0066
        L_0x0088:
            r0 = move-exception
            goto L_0x0066
        L_0x008a:
            if (r1 == 0) goto L_0x02be
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01fc }
            r3.<init>()     // Catch:{ Throwable -> 0x01fc }
            int r0 = r1.length()     // Catch:{ Throwable -> 0x01fc }
            if (r0 <= 0) goto L_0x01b5
            java.lang.String r0 = "push"
            boolean r0 = r1.has(r0)     // Catch:{ Throwable -> 0x01fc }
            if (r0 == 0) goto L_0x00cb
            java.lang.String r0 = "header"
            org.json.JSONObject r0 = r1.optJSONObject(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r4 = "p_sdk_v"
            java.lang.String r0 = r0.optString(r4)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r4 = "p"
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x01fc }
            if (r4 != 0) goto L_0x00cb
            boolean r4 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x01fc }
            if (r4 != 0) goto L_0x00cb
            java.lang.String r4 = "p"
            r3.append(r4)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r4 = "=="
            r3.append(r4)     // Catch:{ Throwable -> 0x01fc }
            r3.append(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r0 = "&="
            r3.append(r0)     // Catch:{ Throwable -> 0x01fc }
        L_0x00cb:
            java.lang.String r0 = "share"
            boolean r0 = r1.has(r0)     // Catch:{ Throwable -> 0x01fc }
            if (r0 == 0) goto L_0x00ff
            java.lang.String r0 = "header"
            org.json.JSONObject r0 = r1.optJSONObject(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r4 = "s_sdk_v"
            java.lang.String r0 = r0.optString(r4)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r4 = "s"
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x01fc }
            if (r4 != 0) goto L_0x00ff
            boolean r4 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x01fc }
            if (r4 != 0) goto L_0x00ff
            java.lang.String r4 = "s"
            r3.append(r4)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r4 = "=="
            r3.append(r4)     // Catch:{ Throwable -> 0x01fc }
            r3.append(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r0 = "&="
            r3.append(r0)     // Catch:{ Throwable -> 0x01fc }
        L_0x00ff:
            java.lang.String r0 = "analytics"
            boolean r0 = r1.has(r0)     // Catch:{ Throwable -> 0x01fc }
            if (r0 == 0) goto L_0x0139
            java.lang.String r0 = "dplus"
            boolean r0 = r1.has(r0)     // Catch:{ Throwable -> 0x01fc }
            if (r0 == 0) goto L_0x01c7
            java.lang.String r0 = "ad"
        L_0x0111:
            java.lang.String r4 = "header"
            org.json.JSONObject r4 = r1.optJSONObject(r4)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r5 = "sdk_version"
            java.lang.String r4 = r4.optString(r5)     // Catch:{ Throwable -> 0x01fc }
            boolean r5 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x01fc }
            if (r5 != 0) goto L_0x0139
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x01fc }
            if (r5 != 0) goto L_0x0139
            r3.append(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r0 = "=="
            r3.append(r0)     // Catch:{ Throwable -> 0x01fc }
            r3.append(r4)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r0 = "&="
            r3.append(r0)     // Catch:{ Throwable -> 0x01fc }
        L_0x0139:
            java.lang.String r0 = "dplus"
            boolean r0 = r1.has(r0)     // Catch:{ Throwable -> 0x01fc }
            if (r0 == 0) goto L_0x0181
            java.lang.String r0 = "header"
            org.json.JSONObject r0 = r1.optJSONObject(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r4 = "sdk_version"
            java.lang.String r0 = r0.optString(r4)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r4 = "analytics"
            boolean r4 = r1.has(r4)     // Catch:{ Throwable -> 0x01fc }
            if (r4 == 0) goto L_0x01db
            java.lang.String r4 = r3.toString()     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r5 = "ad"
            boolean r4 = r4.contains(r5)     // Catch:{ Throwable -> 0x01fc }
            if (r4 != 0) goto L_0x0181
            java.lang.String r4 = "ad"
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x01fc }
            if (r4 != 0) goto L_0x0181
            boolean r4 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x01fc }
            if (r4 != 0) goto L_0x0181
            java.lang.String r4 = "ad"
            r3.append(r4)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r4 = "=="
            r3.append(r4)     // Catch:{ Throwable -> 0x01fc }
            r3.append(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r0 = "&="
            r3.append(r0)     // Catch:{ Throwable -> 0x01fc }
        L_0x0181:
            java.lang.String r0 = "inner"
            boolean r0 = r1.has(r0)     // Catch:{ Throwable -> 0x01fc }
            if (r0 == 0) goto L_0x01b5
            java.lang.String r0 = "header"
            org.json.JSONObject r0 = r1.optJSONObject(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r4 = "i_sdk_v"
            java.lang.String r0 = r0.optString(r4)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r4 = "i"
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x01fc }
            if (r4 != 0) goto L_0x01b5
            boolean r4 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x01fc }
            if (r4 != 0) goto L_0x01b5
            java.lang.String r4 = "i"
            r3.append(r4)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r4 = "=="
            r3.append(r4)     // Catch:{ Throwable -> 0x01fc }
            r3.append(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r0 = "&="
            r3.append(r0)     // Catch:{ Throwable -> 0x01fc }
        L_0x01b5:
            java.lang.String r0 = r3.toString()     // Catch:{ Throwable -> 0x01fc }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x01fc }
            if (r3 == 0) goto L_0x023f
            r0 = 101(0x65, float:1.42E-43)
            org.json.JSONObject r0 = r11.a((int) r0, (org.json.JSONObject) r1)     // Catch:{ Throwable -> 0x01fc }
            goto L_0x0051
        L_0x01c7:
            java.lang.String r0 = "a"
            java.lang.String r4 = "header"
            org.json.JSONObject r4 = r1.optJSONObject(r4)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r5 = "st"
            boolean r4 = r4.has(r5)     // Catch:{ Throwable -> 0x01fc }
            if (r4 == 0) goto L_0x0111
            java.lang.String r0 = "t"
            goto L_0x0111
        L_0x01db:
            java.lang.String r4 = "d"
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x01fc }
            if (r4 != 0) goto L_0x0181
            boolean r4 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x01fc }
            if (r4 != 0) goto L_0x0181
            java.lang.String r4 = "d"
            r3.append(r4)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r4 = "=="
            r3.append(r4)     // Catch:{ Throwable -> 0x01fc }
            r3.append(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r0 = "&="
            r3.append(r0)     // Catch:{ Throwable -> 0x01fc }
            goto L_0x0181
        L_0x01fc:
            r0 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r12, r0)
            if (r13 == 0) goto L_0x0350
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x0331 }
            r0.<init>()     // Catch:{ Exception -> 0x0331 }
            java.lang.String r1 = "header"
            r0.put(r1, r13)     // Catch:{ JSONException -> 0x033e, Exception -> 0x0349 }
        L_0x020c:
            if (r14 == 0) goto L_0x0336
            if (r0 != 0) goto L_0x034b
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x0344 }
            r2.<init>()     // Catch:{ Exception -> 0x0344 }
        L_0x0215:
            if (r14 == 0) goto L_0x034e
            java.util.Iterator r1 = r14.keys()     // Catch:{ Exception -> 0x0346 }
        L_0x021b:
            boolean r0 = r1.hasNext()     // Catch:{ Exception -> 0x0346 }
            if (r0 == 0) goto L_0x034e
            java.lang.Object r0 = r1.next()     // Catch:{ Exception -> 0x0346 }
            if (r0 == 0) goto L_0x021b
            boolean r3 = r0 instanceof java.lang.String     // Catch:{ Exception -> 0x0346 }
            if (r3 == 0) goto L_0x021b
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x0346 }
            if (r0 == 0) goto L_0x021b
            java.lang.Object r3 = r14.opt(r0)     // Catch:{ Exception -> 0x0346 }
            if (r3 == 0) goto L_0x021b
            java.lang.Object r3 = r14.opt(r0)     // Catch:{ Exception -> 0x023d }
            r2.put(r0, r3)     // Catch:{ Exception -> 0x023d }
            goto L_0x021b
        L_0x023d:
            r0 = move-exception
            goto L_0x021b
        L_0x023f:
            java.lang.String r3 = "&="
            boolean r3 = r0.endsWith(r3)     // Catch:{ Throwable -> 0x01fc }
            if (r3 == 0) goto L_0x0353
            r3 = 0
            int r4 = r0.length()     // Catch:{ Throwable -> 0x01fc }
            int r4 = r4 + -2
            java.lang.String r0 = r0.substring(r3, r4)     // Catch:{ Throwable -> 0x01fc }
            r4 = r0
        L_0x0253:
            if (r1 == 0) goto L_0x0286
            com.umeng.commonsdk.statistics.idtracking.e r0 = com.umeng.commonsdk.statistics.idtracking.e.a((android.content.Context) r12)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x0286
            r0.a()     // Catch:{ Exception -> 0x0341 }
            com.umeng.commonsdk.statistics.proto.c r0 = r0.b()     // Catch:{ Exception -> 0x0341 }
            com.umeng.commonsdk.proguard.s r3 = new com.umeng.commonsdk.proguard.s     // Catch:{ Exception -> 0x0341 }
            r3.<init>()     // Catch:{ Exception -> 0x0341 }
            byte[] r0 = r3.a(r0)     // Catch:{ Exception -> 0x0341 }
            r3 = 0
            java.lang.String r0 = android.util.Base64.encodeToString(r0, r3)     // Catch:{ Exception -> 0x0341 }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x0341 }
            if (r3 != 0) goto L_0x0286
            java.lang.String r3 = "header"
            org.json.JSONObject r3 = r1.getJSONObject(r3)     // Catch:{ Exception -> 0x0341 }
            java.lang.String r5 = "id_tracking"
            r3.put(r5, r0)     // Catch:{ Exception -> 0x0341 }
            java.lang.String r0 = "header"
            r1.put(r0, r3)     // Catch:{ Exception -> 0x0341 }
        L_0x0286:
            if (r1 == 0) goto L_0x02c0
            java.lang.String r0 = r1.toString()     // Catch:{ Throwable -> 0x01fc }
            byte[] r0 = r0.getBytes()     // Catch:{ Throwable -> 0x01fc }
            int r0 = r0.length     // Catch:{ Throwable -> 0x01fc }
            long r6 = (long) r0     // Catch:{ Throwable -> 0x01fc }
            long r8 = com.umeng.commonsdk.statistics.common.DataHelper.ENVELOPE_ENTITY_RAW_LENGTH_MAX     // Catch:{ Throwable -> 0x01fc }
            boolean r0 = com.umeng.commonsdk.statistics.common.DataHelper.largeThanMaxSize(r6, r8)     // Catch:{ Throwable -> 0x01fc }
            if (r0 == 0) goto L_0x02c0
            android.content.SharedPreferences r0 = com.umeng.commonsdk.statistics.internal.PreferenceWrapper.getDefault(r12)     // Catch:{ Throwable -> 0x01fc }
            if (r0 == 0) goto L_0x02b6
            java.lang.String r3 = "serial"
            r4 = 1
            int r3 = r0.getInt(r3, r4)     // Catch:{ Throwable -> 0x01fc }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r4 = "serial"
            int r3 = r3 + 1
            android.content.SharedPreferences$Editor r0 = r0.putInt(r4, r3)     // Catch:{ Throwable -> 0x01fc }
            r0.commit()     // Catch:{ Throwable -> 0x01fc }
        L_0x02b6:
            r0 = 113(0x71, float:1.58E-43)
            org.json.JSONObject r0 = r11.a((int) r0, (org.json.JSONObject) r1)     // Catch:{ Throwable -> 0x01fc }
            goto L_0x0051
        L_0x02be:
            r4 = r2
            goto L_0x0253
        L_0x02c0:
            if (r1 == 0) goto L_0x02d8
            java.lang.String r0 = r1.toString()     // Catch:{ Throwable -> 0x01fc }
            byte[] r0 = r0.getBytes()     // Catch:{ Throwable -> 0x01fc }
            com.umeng.commonsdk.statistics.idtracking.Envelope r0 = r11.a((android.content.Context) r12, (byte[]) r0)     // Catch:{ Throwable -> 0x01fc }
            if (r0 != 0) goto L_0x02d9
            r0 = 111(0x6f, float:1.56E-43)
            org.json.JSONObject r0 = r11.a((int) r0, (org.json.JSONObject) r1)     // Catch:{ Throwable -> 0x01fc }
            goto L_0x0051
        L_0x02d8:
            r0 = r2
        L_0x02d9:
            if (r0 == 0) goto L_0x02f1
            byte[] r3 = r0.toBinary()     // Catch:{ Throwable -> 0x01fc }
            int r3 = r3.length     // Catch:{ Throwable -> 0x01fc }
            long r6 = (long) r3     // Catch:{ Throwable -> 0x01fc }
            long r8 = com.umeng.commonsdk.statistics.common.DataHelper.ENVELOPE_LENGTH_MAX     // Catch:{ Throwable -> 0x01fc }
            boolean r3 = com.umeng.commonsdk.statistics.common.DataHelper.largeThanMaxSize(r6, r8)     // Catch:{ Throwable -> 0x01fc }
            if (r3 == 0) goto L_0x02f1
            r0 = 114(0x72, float:1.6E-43)
            org.json.JSONObject r0 = r11.a((int) r0, (org.json.JSONObject) r1)     // Catch:{ Throwable -> 0x01fc }
            goto L_0x0051
        L_0x02f1:
            if (r1 == 0) goto L_0x033c
            java.lang.String r3 = "header"
            org.json.JSONObject r3 = r1.optJSONObject(r3)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r5 = "app_version"
            java.lang.String r3 = r3.optString(r5)     // Catch:{ Throwable -> 0x01fc }
        L_0x02ff:
            int r0 = r11.a(r12, r0, r4, r3)     // Catch:{ Throwable -> 0x01fc }
            if (r0 == 0) goto L_0x030b
            org.json.JSONObject r0 = r11.a((int) r0, (org.json.JSONObject) r1)     // Catch:{ Throwable -> 0x01fc }
            goto L_0x0051
        L_0x030b:
            boolean r0 = com.umeng.commonsdk.statistics.common.ULog.DEBUG     // Catch:{ Throwable -> 0x01fc }
            if (r0 == 0) goto L_0x032e
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01fc }
            r0.<init>()     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r3 = "constructHeader size is "
            r0.append(r3)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r3 = r1.toString()     // Catch:{ Throwable -> 0x01fc }
            byte[] r3 = r3.getBytes()     // Catch:{ Throwable -> 0x01fc }
            int r3 = r3.length     // Catch:{ Throwable -> 0x01fc }
            r0.append(r3)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r3 = "EnvelopeManager"
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x01fc }
            android.util.Log.i(r3, r0)     // Catch:{ Throwable -> 0x01fc }
        L_0x032e:
            r0 = r1
            goto L_0x0051
        L_0x0331:
            r1 = move-exception
            r0 = r2
        L_0x0333:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r12, r1)
        L_0x0336:
            org.json.JSONObject r0 = r11.a((int) r10, (org.json.JSONObject) r0)
            goto L_0x0051
        L_0x033c:
            r3 = r2
            goto L_0x02ff
        L_0x033e:
            r1 = move-exception
            goto L_0x020c
        L_0x0341:
            r0 = move-exception
            goto L_0x0286
        L_0x0344:
            r1 = move-exception
            goto L_0x0333
        L_0x0346:
            r1 = move-exception
            r0 = r2
            goto L_0x0333
        L_0x0349:
            r1 = move-exception
            goto L_0x0333
        L_0x034b:
            r2 = r0
            goto L_0x0215
        L_0x034e:
            r0 = r2
            goto L_0x0336
        L_0x0350:
            r0 = r2
            goto L_0x020c
        L_0x0353:
            r4 = r0
            goto L_0x0253
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.b.a(android.content.Context, org.json.JSONObject, org.json.JSONObject):org.json.JSONObject");
    }
}
