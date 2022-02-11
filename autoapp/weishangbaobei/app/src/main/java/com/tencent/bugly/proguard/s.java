package com.tencent.bugly.proguard;

import android.content.Context;
import com.tencent.bugly.BuglyStrategy;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: BUGLY */
public final class s {
    private static s b;
    public Map<String, String> a = null;
    private Context c;

    private s(Context context) {
        this.c = context;
    }

    public static s a(Context context) {
        if (b == null) {
            b = new s(context);
        }
        return b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:96:0x0174 A[Catch:{ all -> 0x016a, Throwable -> 0x018f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] a(java.lang.String r19, byte[] r20, com.tencent.bugly.proguard.v r21, java.util.Map<java.lang.String, java.lang.String> r22) {
        /*
            r18 = this;
            r1 = r18
            r2 = r20
            r3 = r21
            r4 = 0
            r5 = 0
            if (r19 != 0) goto L_0x0012
            java.lang.String r0 = "Failed for no URL."
            java.lang.Object[] r2 = new java.lang.Object[r5]
            com.tencent.bugly.proguard.x.e(r0, r2)
            return r4
        L_0x0012:
            if (r2 != 0) goto L_0x0017
            r8 = 0
            goto L_0x0019
        L_0x0017:
            int r8 = r2.length
            long r8 = (long) r8
        L_0x0019:
            java.lang.String r10 = "request: %s, send: %d (pid=%d | tid=%d)"
            r11 = 4
            java.lang.Object[] r11 = new java.lang.Object[r11]
            r11[r5] = r19
            java.lang.Long r12 = java.lang.Long.valueOf(r8)
            r13 = 1
            r11[r13] = r12
            int r12 = android.os.Process.myPid()
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r14 = 2
            r11[r14] = r12
            r12 = 3
            int r15 = android.os.Process.myTid()
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)
            r11[r12] = r15
            com.tencent.bugly.proguard.x.c(r10, r11)
            r12 = r19
            r0 = 0
            r10 = 0
            r11 = 0
        L_0x0045:
            if (r0 > 0) goto L_0x01ad
            if (r10 > 0) goto L_0x01ad
            if (r11 == 0) goto L_0x004e
            r6 = r0
            r11 = 0
            goto L_0x007c
        L_0x004e:
            int r0 = r0 + 1
            if (r0 <= r13) goto L_0x007b
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            java.lang.String r6 = "try time: "
            r15.<init>(r6)
            r15.append(r0)
            java.lang.String r6 = r15.toString()
            java.lang.Object[] r7 = new java.lang.Object[r5]
            com.tencent.bugly.proguard.x.c(r6, r7)
            java.util.Random r6 = new java.util.Random
            long r13 = java.lang.System.currentTimeMillis()
            r6.<init>(r13)
            r7 = 10000(0x2710, float:1.4013E-41)
            int r6 = r6.nextInt(r7)
            long r6 = (long) r6
            r13 = 10000(0x2710, double:4.9407E-320)
            long r6 = r6 + r13
            android.os.SystemClock.sleep(r6)
        L_0x007b:
            r6 = r0
        L_0x007c:
            android.content.Context r0 = r1.c
            java.lang.String r0 = com.tencent.bugly.crashreport.common.info.b.c(r0)
            if (r0 != 0) goto L_0x008f
            java.lang.String r0 = "Failed to request for network not avail"
            java.lang.Object[] r7 = new java.lang.Object[r5]
            com.tencent.bugly.proguard.x.d(r0, r7)
            r0 = r6
        L_0x008c:
            r13 = 1
            r14 = 2
            goto L_0x0045
        L_0x008f:
            r3.a((long) r8)
            r7 = r22
            java.net.HttpURLConnection r13 = r1.a((java.lang.String) r12, (byte[]) r2, (java.lang.String) r0, (java.util.Map<java.lang.String, java.lang.String>) r7)
            if (r13 == 0) goto L_0x019b
            int r0 = r13.getResponseCode()     // Catch:{ IOException -> 0x016d }
            r14 = 200(0xc8, float:2.8E-43)
            if (r0 != r14) goto L_0x00c6
            java.util.Map r0 = a((java.net.HttpURLConnection) r13)     // Catch:{ IOException -> 0x016d }
            r1.a = r0     // Catch:{ IOException -> 0x016d }
            byte[] r14 = b(r13)     // Catch:{ IOException -> 0x016d }
            if (r14 != 0) goto L_0x00b1
            r4 = 0
            goto L_0x00b3
        L_0x00b1:
            int r0 = r14.length     // Catch:{ IOException -> 0x016d }
            long r4 = (long) r0     // Catch:{ IOException -> 0x016d }
        L_0x00b3:
            r3.b(r4)     // Catch:{ IOException -> 0x016d }
            r13.disconnect()     // Catch:{ Throwable -> 0x00ba }
            goto L_0x00c5
        L_0x00ba:
            r0 = move-exception
            r2 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r2)
            if (r0 != 0) goto L_0x00c5
            r2.printStackTrace()
        L_0x00c5:
            return r14
        L_0x00c6:
            r4 = 301(0x12d, float:4.22E-43)
            if (r0 == r4) goto L_0x00d9
            r4 = 302(0x12e, float:4.23E-43)
            if (r0 == r4) goto L_0x00d9
            r4 = 303(0x12f, float:4.25E-43)
            if (r0 == r4) goto L_0x00d9
            r4 = 307(0x133, float:4.3E-43)
            if (r0 != r4) goto L_0x00d7
            goto L_0x00d9
        L_0x00d7:
            r4 = 0
            goto L_0x00da
        L_0x00d9:
            r4 = 1
        L_0x00da:
            if (r4 == 0) goto L_0x012f
            java.lang.String r4 = "Location"
            java.lang.String r4 = r13.getHeaderField(r4)     // Catch:{ IOException -> 0x012b }
            if (r4 != 0) goto L_0x010c
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x010a }
            java.lang.String r5 = "Failed to redirect: %d"
            r4.<init>(r5)     // Catch:{ IOException -> 0x010a }
            r4.append(r0)     // Catch:{ IOException -> 0x010a }
            java.lang.String r0 = r4.toString()     // Catch:{ IOException -> 0x010a }
            r4 = 0
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x010a }
            com.tencent.bugly.proguard.x.e(r0, r5)     // Catch:{ IOException -> 0x010a }
            r13.disconnect()     // Catch:{ Throwable -> 0x00fd }
        L_0x00fb:
            r2 = 0
            goto L_0x0109
        L_0x00fd:
            r0 = move-exception
            r2 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r2)
            if (r0 != 0) goto L_0x00fb
            r2.printStackTrace()
            goto L_0x00fb
        L_0x0109:
            return r2
        L_0x010a:
            r0 = move-exception
            goto L_0x012d
        L_0x010c:
            int r10 = r10 + 1
            java.lang.String r5 = "redirect code: %d ,to:%s"
            r14 = 2
            java.lang.Object[] r6 = new java.lang.Object[r14]     // Catch:{ IOException -> 0x0126 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r0)     // Catch:{ IOException -> 0x0126 }
            r12 = 0
            r6[r12] = r11     // Catch:{ IOException -> 0x0126 }
            r15 = 1
            r6[r15] = r4     // Catch:{ IOException -> 0x0124 }
            com.tencent.bugly.proguard.x.c(r5, r6)     // Catch:{ IOException -> 0x0124 }
            r12 = r4
            r5 = 0
            r11 = 1
            goto L_0x0132
        L_0x0124:
            r0 = move-exception
            goto L_0x0128
        L_0x0126:
            r0 = move-exception
            r15 = 1
        L_0x0128:
            r12 = r4
            r6 = 0
            goto L_0x012d
        L_0x012b:
            r0 = move-exception
            r15 = 1
        L_0x012d:
            r11 = 1
            goto L_0x016e
        L_0x012f:
            r14 = 2
            r15 = 1
            r5 = r6
        L_0x0132:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0167 }
            java.lang.String r6 = "response code "
            r4.<init>(r6)     // Catch:{ IOException -> 0x0167 }
            r4.append(r0)     // Catch:{ IOException -> 0x0167 }
            java.lang.String r0 = r4.toString()     // Catch:{ IOException -> 0x0167 }
            r4 = 0
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x0167 }
            com.tencent.bugly.proguard.x.d(r0, r6)     // Catch:{ IOException -> 0x0167 }
            int r0 = r13.getContentLength()     // Catch:{ IOException -> 0x0167 }
            long r14 = (long) r0     // Catch:{ IOException -> 0x0167 }
            r16 = 0
            int r0 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r0 >= 0) goto L_0x0153
            r14 = 0
        L_0x0153:
            r3.b(r14)     // Catch:{ IOException -> 0x0167 }
            r13.disconnect()     // Catch:{ Throwable -> 0x015a }
            goto L_0x0165
        L_0x015a:
            r0 = move-exception
            r4 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r4)
            if (r0 != 0) goto L_0x0165
            r4.printStackTrace()
        L_0x0165:
            r0 = r5
            goto L_0x0187
        L_0x0167:
            r0 = move-exception
            r6 = r5
            goto L_0x016e
        L_0x016a:
            r0 = move-exception
            r2 = r0
            goto L_0x018b
        L_0x016d:
            r0 = move-exception
        L_0x016e:
            boolean r4 = com.tencent.bugly.proguard.x.a(r0)     // Catch:{ all -> 0x016a }
            if (r4 != 0) goto L_0x0177
            r0.printStackTrace()     // Catch:{ all -> 0x016a }
        L_0x0177:
            r13.disconnect()     // Catch:{ Throwable -> 0x017b }
            goto L_0x0186
        L_0x017b:
            r0 = move-exception
            r4 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r4)
            if (r0 != 0) goto L_0x0186
            r4.printStackTrace()
        L_0x0186:
            r0 = r6
        L_0x0187:
            r4 = 0
            r13 = 0
            goto L_0x01a9
        L_0x018b:
            r13.disconnect()     // Catch:{ Throwable -> 0x018f }
            goto L_0x019a
        L_0x018f:
            r0 = move-exception
            r3 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r3)
            if (r0 != 0) goto L_0x019a
            r3.printStackTrace()
        L_0x019a:
            throw r2
        L_0x019b:
            java.lang.String r0 = "Failed to execute post."
            r4 = 0
            java.lang.Object[] r5 = new java.lang.Object[r4]
            com.tencent.bugly.proguard.x.c(r0, r5)
            r13 = 0
            r3.b(r13)
            r0 = r6
        L_0x01a9:
            r4 = 0
            r5 = 0
            goto L_0x008c
        L_0x01ad:
            r2 = r4
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.s.a(java.lang.String, byte[], com.tencent.bugly.proguard.v, java.util.Map):byte[]");
    }

    private static Map<String, String> a(HttpURLConnection httpURLConnection) {
        HashMap hashMap = new HashMap();
        Map headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null || headerFields.size() == 0) {
            return null;
        }
        for (String str : headerFields.keySet()) {
            List list = (List) headerFields.get(str);
            if (list.size() > 0) {
                hashMap.put(str, list.get(0));
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x003e A[Catch:{ all -> 0x0054 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004b A[SYNTHETIC, Splitter:B:27:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0057 A[SYNTHETIC, Splitter:B:34:0x0057] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] b(java.net.HttpURLConnection r5) {
        /*
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0036, all -> 0x0033 }
            java.io.InputStream r5 = r5.getInputStream()     // Catch:{ Throwable -> 0x0036, all -> 0x0033 }
            r1.<init>(r5)     // Catch:{ Throwable -> 0x0036, all -> 0x0033 }
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x0031 }
            r5.<init>()     // Catch:{ Throwable -> 0x0031 }
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch:{ Throwable -> 0x0031 }
        L_0x0016:
            int r3 = r1.read(r2)     // Catch:{ Throwable -> 0x0031 }
            if (r3 <= 0) goto L_0x0021
            r4 = 0
            r5.write(r2, r4, r3)     // Catch:{ Throwable -> 0x0031 }
            goto L_0x0016
        L_0x0021:
            r5.flush()     // Catch:{ Throwable -> 0x0031 }
            byte[] r5 = r5.toByteArray()     // Catch:{ Throwable -> 0x0031 }
            r1.close()     // Catch:{ Throwable -> 0x002c }
            goto L_0x0030
        L_0x002c:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0030:
            return r5
        L_0x0031:
            r5 = move-exception
            goto L_0x0038
        L_0x0033:
            r5 = move-exception
            r1 = r0
            goto L_0x0055
        L_0x0036:
            r5 = move-exception
            r1 = r0
        L_0x0038:
            boolean r2 = com.tencent.bugly.proguard.x.a(r5)     // Catch:{ all -> 0x0054 }
            if (r2 != 0) goto L_0x0041
            r5.printStackTrace()     // Catch:{ all -> 0x0054 }
        L_0x0041:
            com.tencent.bugly.proguard.u r5 = com.tencent.bugly.proguard.u.a()     // Catch:{ all -> 0x0054 }
            r2 = 1
            r5.b((boolean) r2)     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x0053
            r1.close()     // Catch:{ Throwable -> 0x004f }
            goto L_0x0053
        L_0x004f:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0053:
            return r0
        L_0x0054:
            r5 = move-exception
        L_0x0055:
            if (r1 == 0) goto L_0x005f
            r1.close()     // Catch:{ Throwable -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r0 = move-exception
            r0.printStackTrace()
        L_0x005f:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.s.b(java.net.HttpURLConnection):byte[]");
    }

    private HttpURLConnection a(String str, byte[] bArr, String str2, Map<String, String> map) {
        if (str == null) {
            x.e("destUrl is null.", new Object[0]);
            return null;
        }
        HttpURLConnection a2 = a(str2, str);
        if (a2 == null) {
            x.e("Failed to get HttpURLConnection object.", new Object[0]);
            return null;
        }
        try {
            a2.setRequestProperty("wup_version", "3.0");
            if (map != null && map.size() > 0) {
                for (Map.Entry next : map.entrySet()) {
                    a2.setRequestProperty((String) next.getKey(), URLEncoder.encode((String) next.getValue(), "utf-8"));
                }
            }
            a2.setRequestProperty("A37", URLEncoder.encode(str2, "utf-8"));
            a2.setRequestProperty("A38", URLEncoder.encode(str2, "utf-8"));
            OutputStream outputStream = a2.getOutputStream();
            if (bArr == null) {
                outputStream.write(0);
            } else {
                outputStream.write(bArr);
            }
            return a2;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            x.e("Failed to upload, please check your network.", new Object[0]);
            return null;
        }
    }

    private static HttpURLConnection a(String str, String str2) {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(str2);
            if (a.b() != null) {
                httpURLConnection = (HttpURLConnection) url.openConnection(a.b());
            } else if (str == null || !str.toLowerCase(Locale.US).contains("wap")) {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(System.getProperty("http.proxyHost"), Integer.parseInt(System.getProperty("http.proxyPort")))));
            }
            httpURLConnection.setConnectTimeout(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(false);
            return httpURLConnection;
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
