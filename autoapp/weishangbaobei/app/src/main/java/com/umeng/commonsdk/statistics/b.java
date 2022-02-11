package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.idtracking.Envelope;
import com.umeng.commonsdk.utils.UMUtils;
import org.json.JSONObject;

/* compiled from: EnvelopeManager */
public class b {
    public static String a = null;
    public static String b = "";
    private static final String c = "EnvelopeManager";
    private static String d;
    private static boolean f;
    private int e = 0;

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

    private JSONObject a(int i, JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                jSONObject.put("exception", i);
            } catch (Exception unused) {
            }
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("exception", i);
        } catch (Exception unused2) {
        }
        return jSONObject2;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x0073: MOVE  (r4v12 java.lang.String) = (r4v11 java.lang.String)
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
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:31)
        */
    public org.json.JSONObject a(android.content.Context r10, org.json.JSONObject r11, org.json.JSONObject r12) {
        /*
            r9 = this;
            boolean r0 = com.umeng.commonsdk.statistics.common.ULog.DEBUG
            if (r0 == 0) goto L_0x0046
            if (r11 == 0) goto L_0x0046
            if (r12 == 0) goto L_0x0046
            java.lang.String r0 = "EnvelopeManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "headerJSONObject size is "
            r1.append(r2)
            java.lang.String r2 = r11.toString()
            byte[] r2 = r2.getBytes()
            int r2 = r2.length
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r0, r1)
            java.lang.String r0 = "EnvelopeManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "bodyJSONObject size is "
            r1.append(r2)
            java.lang.String r2 = r12.toString()
            byte[] r2 = r2.getBytes()
            int r2 = r2.length
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r0, r1)
        L_0x0046:
            r0 = 110(0x6e, float:1.54E-43)
            r1 = 0
            if (r10 == 0) goto L_0x0328
            if (r12 != 0) goto L_0x004f
            goto L_0x0328
        L_0x004f:
            org.json.JSONObject r2 = b(r10)     // Catch:{ Throwable -> 0x02d6 }
            if (r2 == 0) goto L_0x005b
            if (r11 == 0) goto L_0x005b
            org.json.JSONObject r2 = r9.a((org.json.JSONObject) r2, (org.json.JSONObject) r11)     // Catch:{ Throwable -> 0x02d6 }
        L_0x005b:
            if (r2 == 0) goto L_0x0085
            if (r12 == 0) goto L_0x0085
            java.util.Iterator r3 = r12.keys()     // Catch:{ Throwable -> 0x02d6 }
        L_0x0063:
            boolean r4 = r3.hasNext()     // Catch:{ Throwable -> 0x02d6 }
            if (r4 == 0) goto L_0x0085
            java.lang.Object r4 = r3.next()     // Catch:{ Throwable -> 0x02d6 }
            if (r4 == 0) goto L_0x0063
            boolean r5 = r4 instanceof java.lang.String     // Catch:{ Throwable -> 0x02d6 }
            if (r5 == 0) goto L_0x0063
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Throwable -> 0x02d6 }
            if (r4 == 0) goto L_0x0063
            java.lang.Object r5 = r12.opt(r4)     // Catch:{ Throwable -> 0x02d6 }
            if (r5 == 0) goto L_0x0063
            java.lang.Object r5 = r12.opt(r4)     // Catch:{ Exception -> 0x0063 }
            r2.put(r4, r5)     // Catch:{ Exception -> 0x0063 }
            goto L_0x0063
        L_0x0085:
            r3 = 0
            if (r2 == 0) goto L_0x01ff
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02d6 }
            r4.<init>()     // Catch:{ Throwable -> 0x02d6 }
            int r5 = r2.length()     // Catch:{ Throwable -> 0x02d6 }
            if (r5 <= 0) goto L_0x01db
            java.lang.String r5 = "push"
            boolean r5 = r2.has(r5)     // Catch:{ Throwable -> 0x02d6 }
            if (r5 == 0) goto L_0x00c5
            java.lang.String r5 = "p"
            java.lang.String r6 = "header"
            org.json.JSONObject r6 = r2.optJSONObject(r6)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r7 = "p_sdk_v"
            java.lang.String r6 = r6.optString(r7)     // Catch:{ Throwable -> 0x02d6 }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x02d6 }
            if (r7 != 0) goto L_0x00c5
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Throwable -> 0x02d6 }
            if (r7 != 0) goto L_0x00c5
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r5 = "=="
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
            r4.append(r6)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r5 = "&="
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
        L_0x00c5:
            java.lang.String r5 = "share"
            boolean r5 = r2.has(r5)     // Catch:{ Throwable -> 0x02d6 }
            if (r5 == 0) goto L_0x00f7
            java.lang.String r5 = "s"
            java.lang.String r6 = "header"
            org.json.JSONObject r6 = r2.optJSONObject(r6)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r7 = "s_sdk_v"
            java.lang.String r6 = r6.optString(r7)     // Catch:{ Throwable -> 0x02d6 }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x02d6 }
            if (r7 != 0) goto L_0x00f7
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Throwable -> 0x02d6 }
            if (r7 != 0) goto L_0x00f7
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r5 = "=="
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
            r4.append(r6)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r5 = "&="
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
        L_0x00f7:
            java.lang.String r5 = "analytics"
            boolean r5 = r2.has(r5)     // Catch:{ Throwable -> 0x02d6 }
            if (r5 == 0) goto L_0x0144
            java.lang.String r5 = "dplus"
            boolean r5 = r2.has(r5)     // Catch:{ Throwable -> 0x02d6 }
            if (r5 == 0) goto L_0x010a
            java.lang.String r5 = "ad"
            goto L_0x011c
        L_0x010a:
            java.lang.String r5 = "a"
            java.lang.String r6 = "header"
            org.json.JSONObject r6 = r2.optJSONObject(r6)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r7 = "st"
            boolean r6 = r6.has(r7)     // Catch:{ Throwable -> 0x02d6 }
            if (r6 == 0) goto L_0x011c
            java.lang.String r5 = "t"
        L_0x011c:
            java.lang.String r6 = "header"
            org.json.JSONObject r6 = r2.optJSONObject(r6)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r7 = "sdk_version"
            java.lang.String r6 = r6.optString(r7)     // Catch:{ Throwable -> 0x02d6 }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x02d6 }
            if (r7 != 0) goto L_0x0144
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Throwable -> 0x02d6 }
            if (r7 != 0) goto L_0x0144
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r5 = "=="
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
            r4.append(r6)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r5 = "&="
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
        L_0x0144:
            java.lang.String r5 = "dplus"
            boolean r5 = r2.has(r5)     // Catch:{ Throwable -> 0x02d6 }
            if (r5 == 0) goto L_0x01a9
            java.lang.String r5 = "header"
            org.json.JSONObject r5 = r2.optJSONObject(r5)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r6 = "sdk_version"
            java.lang.String r5 = r5.optString(r6)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r6 = "analytics"
            boolean r6 = r2.has(r6)     // Catch:{ Throwable -> 0x02d6 }
            if (r6 == 0) goto L_0x018b
            java.lang.String r6 = "ad"
            java.lang.String r7 = r4.toString()     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r8 = "ad"
            boolean r7 = r7.contains(r8)     // Catch:{ Throwable -> 0x02d6 }
            if (r7 != 0) goto L_0x01a9
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Throwable -> 0x02d6 }
            if (r7 != 0) goto L_0x01a9
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x02d6 }
            if (r7 != 0) goto L_0x01a9
            r4.append(r6)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r6 = "=="
            r4.append(r6)     // Catch:{ Throwable -> 0x02d6 }
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r5 = "&="
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
            goto L_0x01a9
        L_0x018b:
            java.lang.String r6 = "d"
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Throwable -> 0x02d6 }
            if (r7 != 0) goto L_0x01a9
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x02d6 }
            if (r7 != 0) goto L_0x01a9
            r4.append(r6)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r6 = "=="
            r4.append(r6)     // Catch:{ Throwable -> 0x02d6 }
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r5 = "&="
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
        L_0x01a9:
            java.lang.String r5 = "inner"
            boolean r5 = r2.has(r5)     // Catch:{ Throwable -> 0x02d6 }
            if (r5 == 0) goto L_0x01db
            java.lang.String r5 = "i"
            java.lang.String r6 = "header"
            org.json.JSONObject r6 = r2.optJSONObject(r6)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r7 = "i_sdk_v"
            java.lang.String r6 = r6.optString(r7)     // Catch:{ Throwable -> 0x02d6 }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x02d6 }
            if (r7 != 0) goto L_0x01db
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Throwable -> 0x02d6 }
            if (r7 != 0) goto L_0x01db
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r5 = "=="
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
            r4.append(r6)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r5 = "&="
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
        L_0x01db:
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x02d6 }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x02d6 }
            if (r5 == 0) goto L_0x01ec
            r3 = 101(0x65, float:1.42E-43)
            org.json.JSONObject r2 = r9.a((int) r3, (org.json.JSONObject) r2)     // Catch:{ Throwable -> 0x02d6 }
            return r2
        L_0x01ec:
            java.lang.String r5 = "&="
            boolean r5 = r4.endsWith(r5)     // Catch:{ Throwable -> 0x02d6 }
            if (r5 == 0) goto L_0x0200
            int r5 = r4.length()     // Catch:{ Throwable -> 0x02d6 }
            int r5 = r5 + -2
            java.lang.String r4 = r4.substring(r3, r5)     // Catch:{ Throwable -> 0x02d6 }
            goto L_0x0200
        L_0x01ff:
            r4 = r1
        L_0x0200:
            if (r2 == 0) goto L_0x0232
            com.umeng.commonsdk.statistics.idtracking.e r5 = com.umeng.commonsdk.statistics.idtracking.e.a((android.content.Context) r10)     // Catch:{ Exception -> 0x0232 }
            if (r5 == 0) goto L_0x0232
            r5.a()     // Catch:{ Exception -> 0x0232 }
            com.umeng.commonsdk.statistics.proto.c r5 = r5.b()     // Catch:{ Exception -> 0x0232 }
            com.umeng.commonsdk.proguard.s r6 = new com.umeng.commonsdk.proguard.s     // Catch:{ Exception -> 0x0232 }
            r6.<init>()     // Catch:{ Exception -> 0x0232 }
            byte[] r5 = r6.a(r5)     // Catch:{ Exception -> 0x0232 }
            java.lang.String r3 = android.util.Base64.encodeToString(r5, r3)     // Catch:{ Exception -> 0x0232 }
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x0232 }
            if (r5 != 0) goto L_0x0232
            java.lang.String r5 = "header"
            org.json.JSONObject r5 = r2.getJSONObject(r5)     // Catch:{ Exception -> 0x0232 }
            java.lang.String r6 = "id_tracking"
            r5.put(r6, r3)     // Catch:{ Exception -> 0x0232 }
            java.lang.String r3 = "header"
            r2.put(r3, r5)     // Catch:{ Exception -> 0x0232 }
        L_0x0232:
            if (r2 == 0) goto L_0x0268
            java.lang.String r3 = r2.toString()     // Catch:{ Throwable -> 0x02d6 }
            byte[] r3 = r3.getBytes()     // Catch:{ Throwable -> 0x02d6 }
            int r3 = r3.length     // Catch:{ Throwable -> 0x02d6 }
            long r5 = (long) r3     // Catch:{ Throwable -> 0x02d6 }
            long r7 = com.umeng.commonsdk.statistics.common.DataHelper.ENVELOPE_ENTITY_RAW_LENGTH_MAX     // Catch:{ Throwable -> 0x02d6 }
            boolean r3 = com.umeng.commonsdk.statistics.common.DataHelper.largeThanMaxSize(r5, r7)     // Catch:{ Throwable -> 0x02d6 }
            if (r3 == 0) goto L_0x0268
            android.content.SharedPreferences r3 = com.umeng.commonsdk.statistics.internal.PreferenceWrapper.getDefault(r10)     // Catch:{ Throwable -> 0x02d6 }
            if (r3 == 0) goto L_0x0261
            java.lang.String r4 = "serial"
            r5 = 1
            int r4 = r3.getInt(r4, r5)     // Catch:{ Throwable -> 0x02d6 }
            android.content.SharedPreferences$Editor r3 = r3.edit()     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r6 = "serial"
            int r4 = r4 + r5
            android.content.SharedPreferences$Editor r3 = r3.putInt(r6, r4)     // Catch:{ Throwable -> 0x02d6 }
            r3.commit()     // Catch:{ Throwable -> 0x02d6 }
        L_0x0261:
            r3 = 113(0x71, float:1.58E-43)
            org.json.JSONObject r2 = r9.a((int) r3, (org.json.JSONObject) r2)     // Catch:{ Throwable -> 0x02d6 }
            return r2
        L_0x0268:
            if (r2 == 0) goto L_0x027f
            java.lang.String r3 = r2.toString()     // Catch:{ Throwable -> 0x02d6 }
            byte[] r3 = r3.getBytes()     // Catch:{ Throwable -> 0x02d6 }
            com.umeng.commonsdk.statistics.idtracking.Envelope r3 = r9.a((android.content.Context) r10, (byte[]) r3)     // Catch:{ Throwable -> 0x02d6 }
            if (r3 != 0) goto L_0x0280
            r3 = 111(0x6f, float:1.56E-43)
            org.json.JSONObject r2 = r9.a((int) r3, (org.json.JSONObject) r2)     // Catch:{ Throwable -> 0x02d6 }
            return r2
        L_0x027f:
            r3 = r1
        L_0x0280:
            if (r3 == 0) goto L_0x0297
            byte[] r5 = r3.toBinary()     // Catch:{ Throwable -> 0x02d6 }
            int r5 = r5.length     // Catch:{ Throwable -> 0x02d6 }
            long r5 = (long) r5     // Catch:{ Throwable -> 0x02d6 }
            long r7 = com.umeng.commonsdk.statistics.common.DataHelper.ENVELOPE_LENGTH_MAX     // Catch:{ Throwable -> 0x02d6 }
            boolean r5 = com.umeng.commonsdk.statistics.common.DataHelper.largeThanMaxSize(r5, r7)     // Catch:{ Throwable -> 0x02d6 }
            if (r5 == 0) goto L_0x0297
            r3 = 114(0x72, float:1.6E-43)
            org.json.JSONObject r2 = r9.a((int) r3, (org.json.JSONObject) r2)     // Catch:{ Throwable -> 0x02d6 }
            return r2
        L_0x0297:
            if (r2 == 0) goto L_0x02a6
            java.lang.String r5 = "header"
            org.json.JSONObject r5 = r2.optJSONObject(r5)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r6 = "app_version"
            java.lang.String r5 = r5.optString(r6)     // Catch:{ Throwable -> 0x02d6 }
            goto L_0x02a7
        L_0x02a6:
            r5 = r1
        L_0x02a7:
            int r3 = r9.a(r10, r3, r4, r5)     // Catch:{ Throwable -> 0x02d6 }
            if (r3 == 0) goto L_0x02b2
            org.json.JSONObject r2 = r9.a((int) r3, (org.json.JSONObject) r2)     // Catch:{ Throwable -> 0x02d6 }
            return r2
        L_0x02b2:
            boolean r3 = com.umeng.commonsdk.statistics.common.ULog.DEBUG     // Catch:{ Throwable -> 0x02d6 }
            if (r3 == 0) goto L_0x02d5
            java.lang.String r3 = "EnvelopeManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02d6 }
            r4.<init>()     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r5 = "constructHeader size is "
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r5 = r2.toString()     // Catch:{ Throwable -> 0x02d6 }
            byte[] r5 = r5.getBytes()     // Catch:{ Throwable -> 0x02d6 }
            int r5 = r5.length     // Catch:{ Throwable -> 0x02d6 }
            r4.append(r5)     // Catch:{ Throwable -> 0x02d6 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x02d6 }
            android.util.Log.i(r3, r4)     // Catch:{ Throwable -> 0x02d6 }
        L_0x02d5:
            return r2
        L_0x02d6:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r10, r2)
            if (r11 == 0) goto L_0x02ee
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x02ec }
            r2.<init>()     // Catch:{ Exception -> 0x02ec }
            java.lang.String r1 = "header"
            r2.put(r1, r11)     // Catch:{ JSONException -> 0x02ea, Exception -> 0x02e7 }
            goto L_0x02ea
        L_0x02e7:
            r11 = move-exception
            r1 = r2
            goto L_0x0320
        L_0x02ea:
            r1 = r2
            goto L_0x02ee
        L_0x02ec:
            r11 = move-exception
            goto L_0x0320
        L_0x02ee:
            if (r12 == 0) goto L_0x0323
            if (r1 != 0) goto L_0x02f8
            org.json.JSONObject r11 = new org.json.JSONObject     // Catch:{ Exception -> 0x02ec }
            r11.<init>()     // Catch:{ Exception -> 0x02ec }
            r1 = r11
        L_0x02f8:
            if (r12 == 0) goto L_0x0323
            java.util.Iterator r11 = r12.keys()     // Catch:{ Exception -> 0x02ec }
        L_0x02fe:
            boolean r2 = r11.hasNext()     // Catch:{ Exception -> 0x02ec }
            if (r2 == 0) goto L_0x0323
            java.lang.Object r2 = r11.next()     // Catch:{ Exception -> 0x02ec }
            if (r2 == 0) goto L_0x02fe
            boolean r3 = r2 instanceof java.lang.String     // Catch:{ Exception -> 0x02ec }
            if (r3 == 0) goto L_0x02fe
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x02ec }
            if (r2 == 0) goto L_0x02fe
            java.lang.Object r3 = r12.opt(r2)     // Catch:{ Exception -> 0x02ec }
            if (r3 == 0) goto L_0x02fe
            java.lang.Object r3 = r12.opt(r2)     // Catch:{ Exception -> 0x02fe }
            r1.put(r2, r3)     // Catch:{ Exception -> 0x02fe }
            goto L_0x02fe
        L_0x0320:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r10, r11)
        L_0x0323:
            org.json.JSONObject r10 = r9.a((int) r0, (org.json.JSONObject) r1)
            return r10
        L_0x0328:
            org.json.JSONObject r10 = r9.a((int) r0, (org.json.JSONObject) r1)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.b.a(android.content.Context, org.json.JSONObject, org.json.JSONObject):org.json.JSONObject");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(25:42|43|44|45|47|48|49|50|(1:52)|56|57|(8:59|60|61|(4:63|66|67|(1:69))|64|66|67|(0))|73|74|75|(1:77)|(3:81|82|(1:86))|87|89|90|91|92|(3:96|97|98)|(2:105|106)|109) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:91:0x0272 */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0231 A[Catch:{ Exception -> 0x0237 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.json.JSONObject b(android.content.Context r9) {
        /*
            r0 = 0
            android.content.SharedPreferences r1 = com.umeng.commonsdk.statistics.internal.PreferenceWrapper.getDefault(r9)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r2 = d     // Catch:{ Throwable -> 0x02a1 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x02a1 }
            r3 = 1
            r4 = 0
            if (r2 != 0) goto L_0x001b
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x0018 }
            java.lang.String r5 = d     // Catch:{ Exception -> 0x0018 }
            r2.<init>(r5)     // Catch:{ Exception -> 0x0018 }
            goto L_0x01bd
        L_0x0018:
            r2 = r0
            goto L_0x01bd
        L_0x001b:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Throwable -> 0x02a1 }
            r2.<init>()     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "app_signature"
            java.lang.String r6 = com.umeng.commonsdk.statistics.common.DeviceConfig.getAppMD5Signature(r9)     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "app_sig_sha1"
            java.lang.String r6 = com.umeng.commonsdk.statistics.common.DeviceConfig.getAppSHA1Key(r9)     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "app_sig_sha"
            java.lang.String r6 = com.umeng.commonsdk.statistics.common.DeviceConfig.getAppHashKey(r9)     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "app_version"
            java.lang.String r6 = com.umeng.commonsdk.statistics.common.DeviceConfig.getAppVersionName(r9)     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "version_code"
            java.lang.String r6 = com.umeng.commonsdk.statistics.common.DeviceConfig.getAppVersionCode(r9)     // Catch:{ Throwable -> 0x02a1 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "idmd5"
            java.lang.String r6 = com.umeng.commonsdk.statistics.common.DeviceConfig.getDeviceIdUmengMD5(r9)     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "cpu"
            java.lang.String r6 = com.umeng.commonsdk.statistics.common.DeviceConfig.getCPU()     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getMCCMNC(r9)     // Catch:{ Throwable -> 0x02a1 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x02a1 }
            if (r6 != 0) goto L_0x0075
            java.lang.String r6 = "mccmnc"
            r2.put(r6, r5)     // Catch:{ Throwable -> 0x02a1 }
            b = r5     // Catch:{ Throwable -> 0x02a1 }
            goto L_0x007c
        L_0x0075:
            java.lang.String r5 = "mccmnc"
            java.lang.String r6 = ""
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
        L_0x007c:
            java.lang.String r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getSubOSName(r9)     // Catch:{ Throwable -> 0x02a1 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x02a1 }
            if (r6 != 0) goto L_0x008b
            java.lang.String r6 = "sub_os_name"
            r2.put(r6, r5)     // Catch:{ Throwable -> 0x02a1 }
        L_0x008b:
            java.lang.String r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getSubOSVersion(r9)     // Catch:{ Throwable -> 0x02a1 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x02a1 }
            if (r6 != 0) goto L_0x009a
            java.lang.String r6 = "sub_os_version"
            r2.put(r6, r5)     // Catch:{ Throwable -> 0x02a1 }
        L_0x009a:
            java.lang.String r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getDeviceType(r9)     // Catch:{ Throwable -> 0x02a1 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x02a1 }
            if (r6 != 0) goto L_0x00a9
            java.lang.String r6 = "device_type"
            r2.put(r6, r5)     // Catch:{ Throwable -> 0x02a1 }
        L_0x00a9:
            java.lang.String r5 = "package_name"
            java.lang.String r6 = com.umeng.commonsdk.statistics.common.DeviceConfig.getPackageName(r9)     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "sdk_type"
            java.lang.String r6 = "Android"
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "device_id"
            java.lang.String r6 = com.umeng.commonsdk.statistics.common.DeviceConfig.getDeviceId(r9)     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "device_model"
            java.lang.String r6 = android.os.Build.MODEL     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "device_board"
            java.lang.String r6 = android.os.Build.BOARD     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "device_brand"
            java.lang.String r6 = android.os.Build.BRAND     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "device_manutime"
            long r6 = android.os.Build.TIME     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "device_manufacturer"
            java.lang.String r6 = android.os.Build.MANUFACTURER     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "device_manuid"
            java.lang.String r6 = android.os.Build.ID     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "device_name"
            java.lang.String r6 = android.os.Build.DEVICE     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "os"
            java.lang.String r6 = "Android"
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "os_version"
            java.lang.String r6 = android.os.Build.VERSION.RELEASE     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            int[] r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getResolutionArray(r9)     // Catch:{ Throwable -> 0x02a1 }
            if (r5 == 0) goto L_0x0124
            java.lang.String r6 = "resolution"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02a1 }
            r7.<init>()     // Catch:{ Throwable -> 0x02a1 }
            r8 = r5[r3]     // Catch:{ Throwable -> 0x02a1 }
            r7.append(r8)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r8 = "*"
            r7.append(r8)     // Catch:{ Throwable -> 0x02a1 }
            r5 = r5[r4]     // Catch:{ Throwable -> 0x02a1 }
            r7.append(r5)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = r7.toString()     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r6, r5)     // Catch:{ Throwable -> 0x02a1 }
        L_0x0124:
            java.lang.String r5 = "mc"
            java.lang.String r6 = com.umeng.commonsdk.statistics.common.DeviceConfig.getMac(r9)     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "timezone"
            int r6 = com.umeng.commonsdk.statistics.common.DeviceConfig.getTimeZone(r9)     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String[] r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getLocaleInfo(r9)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r6 = "country"
            r7 = r5[r4]     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r6, r7)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r6 = "language"
            r5 = r5[r3]     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r6, r5)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "carrier"
            java.lang.String r6 = com.umeng.commonsdk.statistics.common.DeviceConfig.getNetworkOperatorName(r9)     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "display_name"
            java.lang.String r6 = com.umeng.commonsdk.statistics.common.DeviceConfig.getAppName(r9)     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String[] r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getNetworkAccessMode(r9)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r6 = "Wi-Fi"
            r7 = r5[r4]     // Catch:{ Throwable -> 0x02a1 }
            boolean r6 = r6.equals(r7)     // Catch:{ Throwable -> 0x02a1 }
            if (r6 == 0) goto L_0x0170
            java.lang.String r6 = "access"
            java.lang.String r7 = "wifi"
            r2.put(r6, r7)     // Catch:{ Throwable -> 0x02a1 }
            goto L_0x0189
        L_0x0170:
            java.lang.String r6 = "2G/3G"
            r7 = r5[r4]     // Catch:{ Throwable -> 0x02a1 }
            boolean r6 = r6.equals(r7)     // Catch:{ Throwable -> 0x02a1 }
            if (r6 == 0) goto L_0x0182
            java.lang.String r6 = "access"
            java.lang.String r7 = "2G/3G"
            r2.put(r6, r7)     // Catch:{ Throwable -> 0x02a1 }
            goto L_0x0189
        L_0x0182:
            java.lang.String r6 = "access"
            java.lang.String r7 = "unknow"
            r2.put(r6, r7)     // Catch:{ Throwable -> 0x02a1 }
        L_0x0189:
            java.lang.String r6 = ""
            r7 = r5[r3]     // Catch:{ Throwable -> 0x02a1 }
            boolean r6 = r6.equals(r7)     // Catch:{ Throwable -> 0x02a1 }
            if (r6 != 0) goto L_0x019a
            java.lang.String r6 = "access_subtype"
            r5 = r5[r3]     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r6, r5)     // Catch:{ Throwable -> 0x02a1 }
        L_0x019a:
            java.lang.String r5 = "com_ver"
            java.lang.String r6 = "2.0.0"
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = "com_type"
            int r6 = com.umeng.commonsdk.statistics.SdkVersion.SDK_TYPE     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r5 = a     // Catch:{ Throwable -> 0x02a1 }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x02a1 }
            if (r5 != 0) goto L_0x01b7
            java.lang.String r5 = "module"
            java.lang.String r6 = a     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x02a1 }
        L_0x01b7:
            java.lang.String r5 = r2.toString()     // Catch:{ Throwable -> 0x02a1 }
            d = r5     // Catch:{ Throwable -> 0x02a1 }
        L_0x01bd:
            if (r2 != 0) goto L_0x01c0
            return r0
        L_0x01c0:
            java.lang.String r5 = "successful_requests"
            java.lang.String r6 = "successful_request"
            int r6 = r1.getInt(r6, r4)     // Catch:{ Exception -> 0x01e1 }
            r2.put(r5, r6)     // Catch:{ Exception -> 0x01e1 }
            java.lang.String r5 = "failed_requests"
            java.lang.String r6 = "failed_requests"
            int r6 = r1.getInt(r6, r4)     // Catch:{ Exception -> 0x01e1 }
            r2.put(r5, r6)     // Catch:{ Exception -> 0x01e1 }
            java.lang.String r5 = "req_time"
            java.lang.String r6 = "last_request_spent_ms"
            int r1 = r1.getInt(r6, r4)     // Catch:{ Exception -> 0x01e1 }
            r2.put(r5, r1)     // Catch:{ Exception -> 0x01e1 }
        L_0x01e1:
            java.lang.String r1 = "channel"
            java.lang.String r5 = com.umeng.commonsdk.utils.UMUtils.getChannel(r9)     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r1, r5)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r1 = "appkey"
            java.lang.String r5 = com.umeng.commonsdk.utils.UMUtils.getAppkey(r9)     // Catch:{ Throwable -> 0x02a1 }
            r2.put(r1, r5)     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r1 = com.umeng.commonsdk.utils.UMUtils.getDeviceToken(r9)     // Catch:{ Exception -> 0x0203 }
            boolean r5 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0203 }
            if (r5 != 0) goto L_0x0207
            java.lang.String r5 = "devicetoken"
            r2.put(r5, r1)     // Catch:{ Exception -> 0x0203 }
            goto L_0x0207
        L_0x0203:
            r1 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r1)     // Catch:{ Throwable -> 0x02a1 }
        L_0x0207:
            int r1 = com.umeng.commonsdk.statistics.SdkVersion.SDK_TYPE     // Catch:{ Exception -> 0x0237 }
            if (r1 == r3) goto L_0x023b
            java.lang.String r1 = "com.umeng.commonsdk.internal.utils.SDStorageAgent"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException | Throwable -> 0x022a }
            if (r1 == 0) goto L_0x022a
            java.lang.String r5 = "getUmtt"
            java.lang.Class[] r6 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException | Throwable -> 0x022a }
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r6[r4] = r7     // Catch:{ ClassNotFoundException | Throwable -> 0x022a }
            java.lang.reflect.Method r5 = r1.getMethod(r5, r6)     // Catch:{ ClassNotFoundException | Throwable -> 0x022a }
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ ClassNotFoundException | Throwable -> 0x022a }
            r6[r4] = r9     // Catch:{ ClassNotFoundException | Throwable -> 0x022a }
            java.lang.Object r1 = r5.invoke(r1, r6)     // Catch:{ ClassNotFoundException | Throwable -> 0x022a }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ ClassNotFoundException | Throwable -> 0x022a }
            goto L_0x022b
        L_0x022a:
            r1 = r0
        L_0x022b:
            boolean r5 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0237 }
            if (r5 != 0) goto L_0x023b
            java.lang.String r5 = "umtt"
            r2.put(r5, r1)     // Catch:{ Exception -> 0x0237 }
            goto L_0x023b
        L_0x0237:
            r1 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r1)     // Catch:{ Throwable -> 0x02a1 }
        L_0x023b:
            java.lang.String r1 = "umid"
            java.lang.String r1 = com.umeng.commonsdk.framework.UMEnvelopeBuild.imprintProperty(r9, r1, r0)     // Catch:{ Exception -> 0x024d }
            boolean r5 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x024d }
            if (r5 != 0) goto L_0x0251
            java.lang.String r5 = "umid"
            r2.put(r5, r1)     // Catch:{ Exception -> 0x024d }
            goto L_0x0251
        L_0x024d:
            r1 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r1)     // Catch:{ Throwable -> 0x02a1 }
        L_0x0251:
            int r1 = com.umeng.commonsdk.statistics.SdkVersion.SDK_TYPE     // Catch:{ Exception -> 0x0264 }
            if (r1 == r3) goto L_0x0264
            java.lang.String r1 = com.umeng.commonsdk.proguard.a.b(r9)     // Catch:{ Exception -> 0x0264 }
            if (r1 == 0) goto L_0x0264
            java.lang.String r1 = "utoken"
            java.lang.String r3 = com.umeng.commonsdk.proguard.a.b(r9)     // Catch:{ Exception -> 0x0264 }
            r2.put(r1, r3)     // Catch:{ Exception -> 0x0264 }
        L_0x0264:
            java.lang.String r1 = "wrapper_type"
            java.lang.String r3 = com.umeng.commonsdk.statistics.a.a     // Catch:{ Exception -> 0x0272 }
            r2.put(r1, r3)     // Catch:{ Exception -> 0x0272 }
            java.lang.String r1 = "wrapper_version"
            java.lang.String r3 = com.umeng.commonsdk.statistics.a.b     // Catch:{ Exception -> 0x0272 }
            r2.put(r1, r3)     // Catch:{ Exception -> 0x0272 }
        L_0x0272:
            com.umeng.commonsdk.statistics.idtracking.ImprintHandler r1 = com.umeng.commonsdk.statistics.idtracking.ImprintHandler.getImprintService(r9)     // Catch:{ Throwable -> 0x02a1 }
            byte[] r1 = r1.a()     // Catch:{ Throwable -> 0x02a1 }
            if (r1 == 0) goto L_0x028d
            int r3 = r1.length     // Catch:{ Throwable -> 0x02a1 }
            if (r3 <= 0) goto L_0x028d
            java.lang.String r3 = "imprint"
            java.lang.String r1 = android.util.Base64.encodeToString(r1, r4)     // Catch:{ JSONException -> 0x0289 }
            r2.put(r3, r1)     // Catch:{ JSONException -> 0x0289 }
            goto L_0x028d
        L_0x0289:
            r1 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r1)     // Catch:{ Throwable -> 0x02a1 }
        L_0x028d:
            if (r2 == 0) goto L_0x02a5
            int r1 = r2.length()     // Catch:{ Throwable -> 0x02a1 }
            if (r1 <= 0) goto L_0x02a5
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Throwable -> 0x02a1 }
            r1.<init>()     // Catch:{ Throwable -> 0x02a1 }
            java.lang.String r3 = "header"
            org.json.JSONObject r1 = r1.put(r3, r2)     // Catch:{ Throwable -> 0x02a1 }
            return r1
        L_0x02a1:
            r1 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r1)
        L_0x02a5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.b.b(android.content.Context):org.json.JSONObject");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CodeShrinkVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x0032: MOVE  (r2v2 java.lang.String) = (r2v1 java.lang.String)
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
            if (r5 == 0) goto L_0x0060
            if (r6 == 0) goto L_0x0060
            java.lang.String r0 = "header"
            java.lang.Object r0 = r5.opt(r0)
            if (r0 == 0) goto L_0x0060
            java.lang.String r0 = "header"
            java.lang.Object r0 = r5.opt(r0)
            boolean r0 = r0 instanceof org.json.JSONObject
            if (r0 == 0) goto L_0x0060
            java.lang.String r0 = "header"
            java.lang.Object r0 = r5.opt(r0)
            org.json.JSONObject r0 = (org.json.JSONObject) r0
            java.util.Iterator r1 = r6.keys()
        L_0x0022:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0060
            java.lang.Object r2 = r1.next()
            if (r2 == 0) goto L_0x0022
            boolean r3 = r2 instanceof java.lang.String
            if (r3 == 0) goto L_0x0022
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto L_0x0022
            java.lang.Object r3 = r6.opt(r2)
            if (r3 == 0) goto L_0x0022
            java.lang.Object r3 = r6.opt(r2)     // Catch:{ Exception -> 0x0022 }
            r0.put(r2, r3)     // Catch:{ Exception -> 0x0022 }
            java.lang.String r3 = "vertical_type"
            boolean r3 = r2.equals(r3)     // Catch:{ Exception -> 0x0022 }
            if (r3 == 0) goto L_0x0022
            java.lang.Object r3 = r6.opt(r2)     // Catch:{ Exception -> 0x0022 }
            boolean r3 = r3 instanceof java.lang.Integer     // Catch:{ Exception -> 0x0022 }
            if (r3 == 0) goto L_0x0022
            java.lang.Object r2 = r6.opt(r2)     // Catch:{ Exception -> 0x0022 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Exception -> 0x0022 }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x0022 }
            r4.e = r2     // Catch:{ Exception -> 0x0022 }
            goto L_0x0022
        L_0x0060:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.b.a(org.json.JSONObject, org.json.JSONObject):org.json.JSONObject");
    }

    private Envelope a(Context context, byte[] bArr) {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(context, "codex", (String) null);
        int i = -1;
        try {
            if (!TextUtils.isEmpty(imprintProperty)) {
                i = Integer.valueOf(imprintProperty).intValue();
            }
        } catch (NumberFormatException e2) {
            UMCrashManager.reportCrash(context, e2);
        }
        if (i == 0) {
            return Envelope.genEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        if (i == 1) {
            return Envelope.genEncryptEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        if (f) {
            return Envelope.genEncryptEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        return Envelope.genEnvelope(context, UMUtils.getAppkey(context), bArr);
    }

    private int a(Context context, Envelope envelope, String str, String str2) {
        if (context == null || envelope == null || TextUtils.isEmpty(str)) {
            return 101;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = DeviceConfig.getAppVersionName(context);
        }
        return UMFrUtils.saveEnvelopeFile(context, str + "&&" + str2 + "_" + System.currentTimeMillis() + "_envelope.log", envelope.toBinary());
    }

    public static void a(boolean z) {
        f = z;
    }
}
