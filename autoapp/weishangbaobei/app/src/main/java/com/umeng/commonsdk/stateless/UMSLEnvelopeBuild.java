package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.utils.UMUtils;
import org.json.JSONObject;

public class UMSLEnvelopeBuild {
    private static final String TAG = "UMSLEnvelopeBuild";
    private static String cacheSystemheader;
    private static boolean isEncryptEnabled;
    public static Context mContext;
    public static String module;

    /* JADX WARNING: Can't wrap try/catch for region: R(20:51|52|53|54|55|(8:57|58|59|(4:61|64|65|(1:67))|62|64|65|(0))|68|70|71|(1:73)|74|75|(1:79)|80|82|83|(3:86|87|(3:89|90|91))|95|96|97) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:74:0x0238 */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0222 A[Catch:{ Exception -> 0x0227 }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x025b A[SYNTHETIC, Splitter:B:86:0x025b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized org.json.JSONObject buildSLBaseHeader(android.content.Context r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            java.lang.String r0 = "walle"
            r1 = 1
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x02bc }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x02bc }
            r3.<init>()     // Catch:{ all -> 0x02bc }
            java.lang.String r4 = "[stateless] begin build hader, thread is "
            r3.append(r4)     // Catch:{ all -> 0x02bc }
            java.lang.Thread r4 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x02bc }
            r3.append(r4)     // Catch:{ all -> 0x02bc }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x02bc }
            r4 = 0
            r2[r4] = r3     // Catch:{ all -> 0x02bc }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r0, (java.lang.Object[]) r2)     // Catch:{ all -> 0x02bc }
            r0 = 0
            if (r10 != 0) goto L_0x0026
            monitor-exit(r9)
            return r0
        L_0x0026:
            android.content.Context r10 = r10.getApplicationContext()     // Catch:{ all -> 0x02bc }
            android.content.Context r10 = com.stub.StubApp.getOrigApplicationContext(r10)     // Catch:{ all -> 0x02bc }
            java.lang.String r2 = cacheSystemheader     // Catch:{ Throwable -> 0x0298 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x0298 }
            if (r2 != 0) goto L_0x0042
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x003f }
            java.lang.String r3 = cacheSystemheader     // Catch:{ Exception -> 0x003f }
            r2.<init>(r3)     // Catch:{ Exception -> 0x003f }
            goto L_0x01e2
        L_0x003f:
            r2 = r0
            goto L_0x01e2
        L_0x0042:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0298 }
            r2.<init>()     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "app_signature"
            java.lang.String r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getAppMD5Signature(r10)     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "app_sig_sha1"
            java.lang.String r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getAppSHA1Key(r10)     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "app_sig_sha"
            java.lang.String r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getAppHashKey(r10)     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "app_version"
            java.lang.String r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getAppVersionName(r10)     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "version_code"
            java.lang.String r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getAppVersionCode(r10)     // Catch:{ Throwable -> 0x0298 }
            int r5 = java.lang.Integer.parseInt(r5)     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "idmd5"
            java.lang.String r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getDeviceIdUmengMD5(r10)     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "cpu"
            java.lang.String r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getCPU()     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = com.umeng.commonsdk.statistics.common.DeviceConfig.getMCCMNC(r10)     // Catch:{ Throwable -> 0x0298 }
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x0298 }
            if (r5 != 0) goto L_0x009a
            java.lang.String r5 = "mccmnc"
            r2.put(r5, r3)     // Catch:{ Throwable -> 0x0298 }
            goto L_0x00a1
        L_0x009a:
            java.lang.String r3 = "mccmnc"
            java.lang.String r5 = ""
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
        L_0x00a1:
            java.lang.String r3 = com.umeng.commonsdk.statistics.common.DeviceConfig.getSubOSName(r10)     // Catch:{ Throwable -> 0x0298 }
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x0298 }
            if (r5 != 0) goto L_0x00b0
            java.lang.String r5 = "sub_os_name"
            r2.put(r5, r3)     // Catch:{ Throwable -> 0x0298 }
        L_0x00b0:
            java.lang.String r3 = com.umeng.commonsdk.statistics.common.DeviceConfig.getSubOSVersion(r10)     // Catch:{ Throwable -> 0x0298 }
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x0298 }
            if (r5 != 0) goto L_0x00bf
            java.lang.String r5 = "sub_os_version"
            r2.put(r5, r3)     // Catch:{ Throwable -> 0x0298 }
        L_0x00bf:
            java.lang.String r3 = com.umeng.commonsdk.statistics.common.DeviceConfig.getDeviceType(r10)     // Catch:{ Throwable -> 0x0298 }
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x0298 }
            if (r5 != 0) goto L_0x00ce
            java.lang.String r5 = "device_type"
            r2.put(r5, r3)     // Catch:{ Throwable -> 0x0298 }
        L_0x00ce:
            java.lang.String r3 = "package_name"
            java.lang.String r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getPackageName(r10)     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "sdk_type"
            java.lang.String r5 = "Android"
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "device_id"
            java.lang.String r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getDeviceId(r10)     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "device_model"
            java.lang.String r5 = android.os.Build.MODEL     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "device_board"
            java.lang.String r5 = android.os.Build.BOARD     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "device_brand"
            java.lang.String r5 = android.os.Build.BRAND     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "device_manutime"
            long r5 = android.os.Build.TIME     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "device_manufacturer"
            java.lang.String r5 = android.os.Build.MANUFACTURER     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "device_manuid"
            java.lang.String r5 = android.os.Build.ID     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "device_name"
            java.lang.String r5 = android.os.Build.DEVICE     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "os"
            java.lang.String r5 = "Android"
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "os_version"
            java.lang.String r5 = android.os.Build.VERSION.RELEASE     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            int[] r3 = com.umeng.commonsdk.statistics.common.DeviceConfig.getResolutionArray(r10)     // Catch:{ Throwable -> 0x0298 }
            if (r3 == 0) goto L_0x0149
            java.lang.String r5 = "resolution"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0298 }
            r6.<init>()     // Catch:{ Throwable -> 0x0298 }
            r7 = r3[r1]     // Catch:{ Throwable -> 0x0298 }
            r6.append(r7)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r7 = "*"
            r6.append(r7)     // Catch:{ Throwable -> 0x0298 }
            r3 = r3[r4]     // Catch:{ Throwable -> 0x0298 }
            r6.append(r3)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = r6.toString()     // Catch:{ Throwable -> 0x0298 }
            r2.put(r5, r3)     // Catch:{ Throwable -> 0x0298 }
        L_0x0149:
            java.lang.String r3 = "mc"
            java.lang.String r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getMac(r10)     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "timezone"
            int r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getTimeZone(r10)     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String[] r3 = com.umeng.commonsdk.statistics.common.DeviceConfig.getLocaleInfo(r10)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r5 = "country"
            r6 = r3[r4]     // Catch:{ Throwable -> 0x0298 }
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r5 = "language"
            r3 = r3[r1]     // Catch:{ Throwable -> 0x0298 }
            r2.put(r5, r3)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "carrier"
            java.lang.String r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getNetworkOperatorName(r10)     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "display_name"
            java.lang.String r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getAppName(r10)     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String[] r3 = com.umeng.commonsdk.statistics.common.DeviceConfig.getNetworkAccessMode(r10)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r5 = "Wi-Fi"
            r6 = r3[r4]     // Catch:{ Throwable -> 0x0298 }
            boolean r5 = r5.equals(r6)     // Catch:{ Throwable -> 0x0298 }
            if (r5 == 0) goto L_0x0195
            java.lang.String r5 = "access"
            java.lang.String r6 = "wifi"
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x0298 }
            goto L_0x01ae
        L_0x0195:
            java.lang.String r5 = "2G/3G"
            r6 = r3[r4]     // Catch:{ Throwable -> 0x0298 }
            boolean r5 = r5.equals(r6)     // Catch:{ Throwable -> 0x0298 }
            if (r5 == 0) goto L_0x01a7
            java.lang.String r5 = "access"
            java.lang.String r6 = "2G/3G"
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x0298 }
            goto L_0x01ae
        L_0x01a7:
            java.lang.String r5 = "access"
            java.lang.String r6 = "unknow"
            r2.put(r5, r6)     // Catch:{ Throwable -> 0x0298 }
        L_0x01ae:
            java.lang.String r5 = ""
            r6 = r3[r1]     // Catch:{ Throwable -> 0x0298 }
            boolean r5 = r5.equals(r6)     // Catch:{ Throwable -> 0x0298 }
            if (r5 != 0) goto L_0x01bf
            java.lang.String r5 = "access_subtype"
            r3 = r3[r1]     // Catch:{ Throwable -> 0x0298 }
            r2.put(r5, r3)     // Catch:{ Throwable -> 0x0298 }
        L_0x01bf:
            java.lang.String r3 = "com_ver"
            java.lang.String r5 = "2.0.0"
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "com_type"
            int r5 = com.umeng.commonsdk.statistics.SdkVersion.SDK_TYPE     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = module     // Catch:{ Throwable -> 0x0298 }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x0298 }
            if (r3 != 0) goto L_0x01dc
            java.lang.String r3 = "module"
            java.lang.String r5 = module     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
        L_0x01dc:
            java.lang.String r3 = r2.toString()     // Catch:{ Throwable -> 0x0298 }
            cacheSystemheader = r3     // Catch:{ Throwable -> 0x0298 }
        L_0x01e2:
            if (r2 != 0) goto L_0x01e6
            monitor-exit(r9)
            return r0
        L_0x01e6:
            java.lang.String r3 = "channel"
            java.lang.String r5 = com.umeng.commonsdk.utils.UMUtils.getChannel(r10)     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r3 = "appkey"
            java.lang.String r5 = com.umeng.commonsdk.utils.UMUtils.getAppkey(r10)     // Catch:{ Throwable -> 0x0298 }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x0298 }
            int r3 = com.umeng.commonsdk.statistics.SdkVersion.SDK_TYPE     // Catch:{ Exception -> 0x0227 }
            if (r3 == r1) goto L_0x0227
            java.lang.String r3 = "com.umeng.commonsdk.internal.utils.SDStorageAgent"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch:{ ClassNotFoundException | Throwable -> 0x021b }
            if (r3 == 0) goto L_0x021b
            java.lang.String r5 = "getUmtt"
            java.lang.Class[] r6 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException | Throwable -> 0x021b }
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r6[r4] = r7     // Catch:{ ClassNotFoundException | Throwable -> 0x021b }
            java.lang.reflect.Method r5 = r3.getMethod(r5, r6)     // Catch:{ ClassNotFoundException | Throwable -> 0x021b }
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ ClassNotFoundException | Throwable -> 0x021b }
            r6[r4] = r10     // Catch:{ ClassNotFoundException | Throwable -> 0x021b }
            java.lang.Object r3 = r5.invoke(r3, r6)     // Catch:{ ClassNotFoundException | Throwable -> 0x021b }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ ClassNotFoundException | Throwable -> 0x021b }
            goto L_0x021c
        L_0x021b:
            r3 = r0
        L_0x021c:
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x0227 }
            if (r5 != 0) goto L_0x0227
            java.lang.String r5 = "umtt"
            r2.put(r5, r3)     // Catch:{ Exception -> 0x0227 }
        L_0x0227:
            java.lang.String r3 = "umid"
            java.lang.String r3 = com.umeng.commonsdk.framework.UMEnvelopeBuild.imprintProperty(r10, r3, r0)     // Catch:{ Exception -> 0x0238 }
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x0238 }
            if (r5 != 0) goto L_0x0238
            java.lang.String r5 = "umid"
            r2.put(r5, r3)     // Catch:{ Exception -> 0x0238 }
        L_0x0238:
            int r3 = com.umeng.commonsdk.statistics.SdkVersion.SDK_TYPE     // Catch:{ Exception -> 0x024b }
            if (r3 == r1) goto L_0x024b
            java.lang.String r3 = com.umeng.commonsdk.proguard.a.b(r10)     // Catch:{ Exception -> 0x024b }
            if (r3 == 0) goto L_0x024b
            java.lang.String r3 = "utoken"
            java.lang.String r5 = com.umeng.commonsdk.proguard.a.b(r10)     // Catch:{ Exception -> 0x024b }
            r2.put(r3, r5)     // Catch:{ Exception -> 0x024b }
        L_0x024b:
            java.lang.String r3 = "wrapper_type"
            java.lang.String r5 = com.umeng.commonsdk.stateless.a.a     // Catch:{ Exception -> 0x0259 }
            r2.put(r3, r5)     // Catch:{ Exception -> 0x0259 }
            java.lang.String r3 = "wrapper_version"
            java.lang.String r5 = com.umeng.commonsdk.stateless.a.b     // Catch:{ Exception -> 0x0259 }
            r2.put(r3, r5)     // Catch:{ Exception -> 0x0259 }
        L_0x0259:
            if (r2 == 0) goto L_0x029c
            int r3 = r2.length()     // Catch:{ Throwable -> 0x0298 }
            if (r3 <= 0) goto L_0x029c
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0298 }
            r3.<init>()     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r5 = "walle"
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0298 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0298 }
            r7.<init>()     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r8 = "[stateless] build header end , header is "
            r7.append(r8)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r8 = r2.toString()     // Catch:{ Throwable -> 0x0298 }
            r7.append(r8)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r8 = ", thread is "
            r7.append(r8)     // Catch:{ Throwable -> 0x0298 }
            java.lang.Thread r8 = java.lang.Thread.currentThread()     // Catch:{ Throwable -> 0x0298 }
            r7.append(r8)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x0298 }
            r6[r4] = r7     // Catch:{ Throwable -> 0x0298 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r5, (java.lang.Object[]) r6)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r5 = "header"
            org.json.JSONObject r2 = r3.put(r5, r2)     // Catch:{ Throwable -> 0x0298 }
            monitor-exit(r9)
            return r2
        L_0x0298:
            r2 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r10, r2)     // Catch:{ all -> 0x02bc }
        L_0x029c:
            java.lang.String r10 = "walle"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x02bc }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x02bc }
            r2.<init>()     // Catch:{ all -> 0x02bc }
            java.lang.String r3 = "[stateless] build header end , header is null !!! thread is "
            r2.append(r3)     // Catch:{ all -> 0x02bc }
            java.lang.Thread r3 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x02bc }
            r2.append(r3)     // Catch:{ all -> 0x02bc }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x02bc }
            r1[r4] = r2     // Catch:{ all -> 0x02bc }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r10, (java.lang.Object[]) r1)     // Catch:{ all -> 0x02bc }
            monitor-exit(r9)
            return r0
        L_0x02bc:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.buildSLBaseHeader(android.content.Context):org.json.JSONObject");
    }

    private synchronized JSONObject makeErrorResult(int i, JSONObject jSONObject) {
        JSONObject jSONObject2;
        if (jSONObject != null) {
            try {
                jSONObject.put("exception", i);
            } catch (Exception unused) {
            }
        } else {
            jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("exception", i);
            } catch (Exception unused2) {
            }
        }
        return jSONObject2;
        return jSONObject;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CodeShrinkVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x008a: MOVE  (r5v23 java.lang.String) = (r5v22 java.lang.String)
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.visit(CodeShrinkVisitor.java:35)
        */
    public synchronized org.json.JSONObject buildSLEnvelope(android.content.Context r10, org.json.JSONObject r11, org.json.JSONObject r12, java.lang.String r13) {
        /*
            r9 = this;
            monitor-enter(r9)
            java.lang.String r0 = "walle"
            r1 = 1
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x0266 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0266 }
            r3.<init>()     // Catch:{ all -> 0x0266 }
            java.lang.String r4 = "[stateless] build envelope, heade is "
            r3.append(r4)     // Catch:{ all -> 0x0266 }
            java.lang.String r4 = r11.toString()     // Catch:{ all -> 0x0266 }
            r3.append(r4)     // Catch:{ all -> 0x0266 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0266 }
            r4 = 0
            r2[r4] = r3     // Catch:{ all -> 0x0266 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r0, (java.lang.Object[]) r2)     // Catch:{ all -> 0x0266 }
            java.lang.String r0 = "walle"
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x0266 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0266 }
            r3.<init>()     // Catch:{ all -> 0x0266 }
            java.lang.String r5 = "[stateless] build envelope, body is "
            r3.append(r5)     // Catch:{ all -> 0x0266 }
            java.lang.String r5 = r12.toString()     // Catch:{ all -> 0x0266 }
            r3.append(r5)     // Catch:{ all -> 0x0266 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0266 }
            r2[r4] = r3     // Catch:{ all -> 0x0266 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r0, (java.lang.Object[]) r2)     // Catch:{ all -> 0x0266 }
            java.lang.String r0 = "walle"
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x0266 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0266 }
            r3.<init>()     // Catch:{ all -> 0x0266 }
            java.lang.String r5 = "[stateless] build envelope, thread is "
            r3.append(r5)     // Catch:{ all -> 0x0266 }
            java.lang.Thread r5 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0266 }
            r3.append(r5)     // Catch:{ all -> 0x0266 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0266 }
            r2[r4] = r3     // Catch:{ all -> 0x0266 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r0, (java.lang.Object[]) r2)     // Catch:{ all -> 0x0266 }
            r0 = 110(0x6e, float:1.54E-43)
            r2 = 0
            if (r10 == 0) goto L_0x0255
            if (r11 == 0) goto L_0x0255
            if (r12 == 0) goto L_0x0255
            if (r13 != 0) goto L_0x006a
            goto L_0x0255
        L_0x006a:
            android.content.Context r3 = r10.getApplicationContext()     // Catch:{ Throwable -> 0x022b }
            android.content.Context r3 = com.stub.StubApp.getOrigApplicationContext(r3)     // Catch:{ Throwable -> 0x022b }
            if (r11 == 0) goto L_0x009f
            if (r12 == 0) goto L_0x009f
            java.util.Iterator r10 = r12.keys()     // Catch:{ Throwable -> 0x009c }
        L_0x007a:
            boolean r5 = r10.hasNext()     // Catch:{ Throwable -> 0x009c }
            if (r5 == 0) goto L_0x009f
            java.lang.Object r5 = r10.next()     // Catch:{ Throwable -> 0x009c }
            if (r5 == 0) goto L_0x007a
            boolean r6 = r5 instanceof java.lang.String     // Catch:{ Throwable -> 0x009c }
            if (r6 == 0) goto L_0x007a
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Throwable -> 0x009c }
            if (r5 == 0) goto L_0x007a
            java.lang.Object r6 = r12.opt(r5)     // Catch:{ Throwable -> 0x009c }
            if (r6 == 0) goto L_0x007a
            java.lang.Object r6 = r12.opt(r5)     // Catch:{ Exception -> 0x007a }
            r11.put(r5, r6)     // Catch:{ Exception -> 0x007a }
            goto L_0x007a
        L_0x009c:
            r10 = move-exception
            goto L_0x022e
        L_0x009f:
            if (r11 == 0) goto L_0x00d1
            com.umeng.commonsdk.statistics.idtracking.e r10 = com.umeng.commonsdk.statistics.idtracking.e.a((android.content.Context) r3)     // Catch:{ Exception -> 0x00d1 }
            if (r10 == 0) goto L_0x00d1
            r10.a()     // Catch:{ Exception -> 0x00d1 }
            com.umeng.commonsdk.statistics.proto.c r10 = r10.b()     // Catch:{ Exception -> 0x00d1 }
            com.umeng.commonsdk.proguard.s r12 = new com.umeng.commonsdk.proguard.s     // Catch:{ Exception -> 0x00d1 }
            r12.<init>()     // Catch:{ Exception -> 0x00d1 }
            byte[] r10 = r12.a(r10)     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r10 = android.util.Base64.encodeToString(r10, r4)     // Catch:{ Exception -> 0x00d1 }
            boolean r12 = android.text.TextUtils.isEmpty(r10)     // Catch:{ Exception -> 0x00d1 }
            if (r12 != 0) goto L_0x00d1
            java.lang.String r12 = "header"
            org.json.JSONObject r12 = r11.getJSONObject(r12)     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r5 = "id_tracking"
            r12.put(r5, r10)     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r10 = "header"
            r11.put(r10, r12)     // Catch:{ Exception -> 0x00d1 }
        L_0x00d1:
            if (r11 == 0) goto L_0x0110
            java.lang.String r10 = r11.toString()     // Catch:{ Throwable -> 0x009c }
            byte[] r10 = r10.getBytes()     // Catch:{ Throwable -> 0x009c }
            int r10 = r10.length     // Catch:{ Throwable -> 0x009c }
            long r5 = (long) r10     // Catch:{ Throwable -> 0x009c }
            long r7 = com.umeng.commonsdk.stateless.a.c     // Catch:{ Throwable -> 0x009c }
            boolean r10 = com.umeng.commonsdk.stateless.f.a((long) r5, (long) r7)     // Catch:{ Throwable -> 0x009c }
            if (r10 == 0) goto L_0x0110
            java.lang.String r10 = "walle"
            java.lang.Object[] r12 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x009c }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x009c }
            r13.<init>()     // Catch:{ Throwable -> 0x009c }
            java.lang.String r5 = "[stateless] build envelope, json overstep!!!! size is "
            r13.append(r5)     // Catch:{ Throwable -> 0x009c }
            java.lang.String r5 = r11.toString()     // Catch:{ Throwable -> 0x009c }
            byte[] r5 = r5.getBytes()     // Catch:{ Throwable -> 0x009c }
            int r5 = r5.length     // Catch:{ Throwable -> 0x009c }
            r13.append(r5)     // Catch:{ Throwable -> 0x009c }
            java.lang.String r13 = r13.toString()     // Catch:{ Throwable -> 0x009c }
            r12[r4] = r13     // Catch:{ Throwable -> 0x009c }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r10, (java.lang.Object[]) r12)     // Catch:{ Throwable -> 0x009c }
            r10 = 113(0x71, float:1.58E-43)
            org.json.JSONObject r10 = r9.makeErrorResult(r10, r11)     // Catch:{ Throwable -> 0x009c }
            monitor-exit(r9)
            return r10
        L_0x0110:
            java.lang.String r10 = "walle"
            java.lang.Object[] r12 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x009c }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x009c }
            r5.<init>()     // Catch:{ Throwable -> 0x009c }
            java.lang.String r6 = "[stateless] build envelope, json size is "
            r5.append(r6)     // Catch:{ Throwable -> 0x009c }
            java.lang.String r6 = r11.toString()     // Catch:{ Throwable -> 0x009c }
            byte[] r6 = r6.getBytes()     // Catch:{ Throwable -> 0x009c }
            int r6 = r6.length     // Catch:{ Throwable -> 0x009c }
            r5.append(r6)     // Catch:{ Throwable -> 0x009c }
            java.lang.String r5 = r5.toString()     // Catch:{ Throwable -> 0x009c }
            r12[r4] = r5     // Catch:{ Throwable -> 0x009c }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r10, (java.lang.Object[]) r12)     // Catch:{ Throwable -> 0x009c }
            if (r11 == 0) goto L_0x0156
            java.lang.String r10 = r11.toString()     // Catch:{ Throwable -> 0x009c }
            byte[] r10 = r10.getBytes()     // Catch:{ Throwable -> 0x009c }
            com.umeng.commonsdk.stateless.c r10 = r9.constructEnvelope(r3, r10)     // Catch:{ Throwable -> 0x009c }
            if (r10 != 0) goto L_0x0157
            java.lang.String r10 = "walle"
            java.lang.Object[] r12 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x009c }
            java.lang.String r13 = "[stateless] build envelope, envelope is null !!!!"
            r12[r4] = r13     // Catch:{ Throwable -> 0x009c }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r10, (java.lang.Object[]) r12)     // Catch:{ Throwable -> 0x009c }
            r10 = 111(0x6f, float:1.56E-43)
            org.json.JSONObject r10 = r9.makeErrorResult(r10, r11)     // Catch:{ Throwable -> 0x009c }
            monitor-exit(r9)
            return r10
        L_0x0156:
            r10 = r2
        L_0x0157:
            if (r10 == 0) goto L_0x018e
            byte[] r12 = r10.b()     // Catch:{ Throwable -> 0x009c }
            int r12 = r12.length     // Catch:{ Throwable -> 0x009c }
            long r5 = (long) r12     // Catch:{ Throwable -> 0x009c }
            long r7 = com.umeng.commonsdk.stateless.a.d     // Catch:{ Throwable -> 0x009c }
            boolean r12 = com.umeng.commonsdk.stateless.f.a((long) r5, (long) r7)     // Catch:{ Throwable -> 0x009c }
            if (r12 == 0) goto L_0x018e
            java.lang.String r12 = "walle"
            java.lang.Object[] r13 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x009c }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x009c }
            r5.<init>()     // Catch:{ Throwable -> 0x009c }
            java.lang.String r6 = "[stateless] build envelope, envelope overstep!!!! size is "
            r5.append(r6)     // Catch:{ Throwable -> 0x009c }
            byte[] r10 = r10.b()     // Catch:{ Throwable -> 0x009c }
            int r10 = r10.length     // Catch:{ Throwable -> 0x009c }
            r5.append(r10)     // Catch:{ Throwable -> 0x009c }
            java.lang.String r10 = r5.toString()     // Catch:{ Throwable -> 0x009c }
            r13[r4] = r10     // Catch:{ Throwable -> 0x009c }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r12, (java.lang.Object[]) r13)     // Catch:{ Throwable -> 0x009c }
            r10 = 114(0x72, float:1.6E-43)
            org.json.JSONObject r10 = r9.makeErrorResult(r10, r11)     // Catch:{ Throwable -> 0x009c }
            monitor-exit(r9)
            return r10
        L_0x018e:
            byte[] r12 = r13.getBytes()     // Catch:{ Throwable -> 0x009c }
            java.lang.String r12 = android.util.Base64.encodeToString(r12, r4)     // Catch:{ Throwable -> 0x009c }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x009c }
            r5.<init>()     // Catch:{ Throwable -> 0x009c }
            r5.append(r13)     // Catch:{ Throwable -> 0x009c }
            java.lang.String r13 = "_"
            r5.append(r13)     // Catch:{ Throwable -> 0x009c }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x009c }
            r5.append(r6)     // Catch:{ Throwable -> 0x009c }
            java.lang.String r13 = r5.toString()     // Catch:{ Throwable -> 0x009c }
            byte[] r13 = r13.getBytes()     // Catch:{ Throwable -> 0x009c }
            java.lang.String r13 = android.util.Base64.encodeToString(r13, r4)     // Catch:{ Throwable -> 0x009c }
            byte[] r10 = r10.b()     // Catch:{ Throwable -> 0x009c }
            boolean r10 = com.umeng.commonsdk.stateless.f.a(r3, r12, r13, r10)     // Catch:{ Throwable -> 0x009c }
            if (r10 != 0) goto L_0x01d3
            java.lang.String r10 = "walle"
            java.lang.Object[] r12 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x009c }
            java.lang.String r13 = "[stateless] build envelope, save fail ----->>>>>"
            r12[r4] = r13     // Catch:{ Throwable -> 0x009c }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r10, (java.lang.Object[]) r12)     // Catch:{ Throwable -> 0x009c }
            r10 = 101(0x65, float:1.42E-43)
            org.json.JSONObject r10 = r9.makeErrorResult(r10, r11)     // Catch:{ Throwable -> 0x009c }
            monitor-exit(r9)
            return r10
        L_0x01d3:
            java.lang.String r10 = "walle"
            java.lang.Object[] r12 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x009c }
            java.lang.String r13 = "[stateless] build envelope, save ok ----->>>>>"
            r12[r4] = r13     // Catch:{ Throwable -> 0x009c }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r10, (java.lang.Object[]) r12)     // Catch:{ Throwable -> 0x009c }
            java.lang.String r10 = "walle"
            java.lang.Object[] r12 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x009c }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x009c }
            r13.<init>()     // Catch:{ Throwable -> 0x009c }
            java.lang.String r5 = "[stateless] envelope file size is "
            r13.append(r5)     // Catch:{ Throwable -> 0x009c }
            java.lang.String r5 = r11.toString()     // Catch:{ Throwable -> 0x009c }
            byte[] r5 = r5.getBytes()     // Catch:{ Throwable -> 0x009c }
            int r5 = r5.length     // Catch:{ Throwable -> 0x009c }
            r13.append(r5)     // Catch:{ Throwable -> 0x009c }
            java.lang.String r13 = r13.toString()     // Catch:{ Throwable -> 0x009c }
            r12[r4] = r13     // Catch:{ Throwable -> 0x009c }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r10, (java.lang.Object[]) r12)     // Catch:{ Throwable -> 0x009c }
            com.umeng.commonsdk.stateless.d r10 = new com.umeng.commonsdk.stateless.d     // Catch:{ Throwable -> 0x009c }
            r10.<init>(r3)     // Catch:{ Throwable -> 0x009c }
            r10 = 273(0x111, float:3.83E-43)
            com.umeng.commonsdk.stateless.d.b(r10)     // Catch:{ Throwable -> 0x009c }
            java.lang.String r10 = "walle"
            java.lang.Object[] r12 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x009c }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x009c }
            r13.<init>()     // Catch:{ Throwable -> 0x009c }
            java.lang.String r5 = "[stateless] build envelope end, thread is "
            r13.append(r5)     // Catch:{ Throwable -> 0x009c }
            java.lang.Thread r5 = java.lang.Thread.currentThread()     // Catch:{ Throwable -> 0x009c }
            r13.append(r5)     // Catch:{ Throwable -> 0x009c }
            java.lang.String r13 = r13.toString()     // Catch:{ Throwable -> 0x009c }
            r12[r4] = r13     // Catch:{ Throwable -> 0x009c }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r10, (java.lang.Object[]) r12)     // Catch:{ Throwable -> 0x009c }
            monitor-exit(r9)
            return r11
        L_0x022b:
            r11 = move-exception
            r3 = r10
            r10 = r11
        L_0x022e:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r3, r10)     // Catch:{ all -> 0x0266 }
            java.lang.String r10 = "walle"
            java.lang.Object[] r11 = new java.lang.Object[r1]     // Catch:{ all -> 0x0266 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0266 }
            r12.<init>()     // Catch:{ all -> 0x0266 }
            java.lang.String r13 = "build envelope end, thread is "
            r12.append(r13)     // Catch:{ all -> 0x0266 }
            java.lang.Thread r13 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0266 }
            r12.append(r13)     // Catch:{ all -> 0x0266 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0266 }
            r11[r4] = r12     // Catch:{ all -> 0x0266 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r10, (java.lang.Object[]) r11)     // Catch:{ all -> 0x0266 }
            org.json.JSONObject r10 = r9.makeErrorResult(r0, r2)     // Catch:{ all -> 0x0266 }
            monitor-exit(r9)
            return r10
        L_0x0255:
            java.lang.String r10 = "walle"
            java.lang.Object[] r11 = new java.lang.Object[r1]     // Catch:{ all -> 0x0266 }
            java.lang.String r12 = "[stateless] build envelope, context is null or header is null or body is null"
            r11[r4] = r12     // Catch:{ all -> 0x0266 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r10, (java.lang.Object[]) r11)     // Catch:{ all -> 0x0266 }
            org.json.JSONObject r10 = r9.makeErrorResult(r0, r2)     // Catch:{ all -> 0x0266 }
            monitor-exit(r9)
            return r10
        L_0x0266:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.buildSLEnvelope(android.content.Context, org.json.JSONObject, org.json.JSONObject, java.lang.String):org.json.JSONObject");
    }

    private synchronized c constructEnvelope(Context context, byte[] bArr) {
        c cVar;
        int i = -1;
        String imprintProperty = UMEnvelopeBuild.imprintProperty(context, "slcodex", (String) null);
        ULog.i("walle", "[stateless] build envelope, codexStr is " + imprintProperty);
        try {
            if (!TextUtils.isEmpty(imprintProperty)) {
                i = Integer.valueOf(imprintProperty).intValue();
            }
        } catch (NumberFormatException e) {
            UMCrashManager.reportCrash(context, e);
        }
        if (i == 0) {
            ULog.i("walle", "[stateless] build envelope, codexValue is 0");
            cVar = c.a(context, UMUtils.getAppkey(context), bArr);
        } else if (i == 1) {
            ULog.i("walle", "[stateless] build envelope, codexValue is 1");
            cVar = c.b(context, UMUtils.getAppkey(context), bArr);
        } else if (isEncryptEnabled) {
            ULog.i("walle", "[stateless] build envelope, isEncryptEnabled is true");
            cVar = c.b(context, UMUtils.getAppkey(context), bArr);
        } else {
            ULog.i("walle", "[stateless] build envelope, isEncryptEnabled is false");
            cVar = c.a(context, UMUtils.getAppkey(context), bArr);
        }
        return cVar;
    }

    public static void setEncryptEnabled(boolean z) {
        isEncryptEnabled = z;
    }
}
