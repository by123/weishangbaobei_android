package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;

/* compiled from: UUIDTracker */
public class s extends a {
    private static final String a = "uuid";
    private static final String e = "yosuid";
    private static final String f = "23346339";
    private Context b = null;
    private String c = null;
    private String d = null;

    public s(Context context) {
        super(a);
        this.b = context;
        this.c = null;
        this.d = null;
    }

    public String f() {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor edit;
        try {
            if (TextUtils.isEmpty(a("ro.yunos.version", "")) || this.b == null || (sharedPreferences = PreferenceWrapper.getDefault(this.b)) == null) {
                return null;
            }
            String string = sharedPreferences.getString(e, "");
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            this.d = b(f);
            if (!(TextUtils.isEmpty(this.d) || this.b == null || sharedPreferences == null || (edit = sharedPreferences.edit()) == null)) {
                edit.putString(e, this.d).commit();
            }
            return this.d;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v13, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: java.io.InputStream} */
    /* JADX WARNING: type inference failed for: r7v12 */
    /* JADX WARNING: type inference failed for: r0v5, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r7v15 */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r7v16 */
    /* JADX WARNING: type inference failed for: r7v17 */
    /* JADX WARNING: type inference failed for: r7v18 */
    /* JADX WARNING: type inference failed for: r7v19, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r7v20 */
    /* JADX WARNING: type inference failed for: r7v30 */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f6, code lost:
        if (r0 != null) goto L_0x012d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f9, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00fa, code lost:
        r3 = null;
        r7 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x012b, code lost:
        if (r0 == null) goto L_0x0130;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x012d, code lost:
        r0.disconnect();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ea A[SYNTHETIC, Splitter:B:43:0x00ea] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00f1 A[SYNTHETIC, Splitter:B:47:0x00f1] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00f9 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:15:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x010c A[SYNTHETIC, Splitter:B:63:0x010c] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0113 A[SYNTHETIC, Splitter:B:67:0x0113] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x011a A[SYNTHETIC, Splitter:B:71:0x011a] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0128 A[SYNTHETIC, Splitter:B:80:0x0128] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String b(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r7 = "ro.yunos.openuuid"
            java.lang.String r0 = ""
            java.lang.String r7 = a(r7, r0)
            r6.d = r7
            java.lang.String r7 = r6.d
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 != 0) goto L_0x0015
            java.lang.String r7 = r6.d
            return r7
        L_0x0015:
            java.lang.String r7 = "ro.aliyun.clouduuid"
            java.lang.String r0 = ""
            java.lang.String r7 = a(r7, r0)
            r6.c = r7
            java.lang.String r7 = r6.c
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 == 0) goto L_0x0031
            java.lang.String r7 = "ro.sys.aliyun.clouduuid"
            java.lang.String r0 = ""
            java.lang.String r7 = a(r7, r0)
            r6.c = r7
        L_0x0031:
            java.lang.String r7 = r6.c
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 != 0) goto L_0x0130
            r7 = 0
            java.net.URL r0 = new java.net.URL     // Catch:{ Exception -> 0x0125, all -> 0x0105 }
            java.lang.String r1 = "https://cmnsguider.yunos.com:443/genDeviceToken"
            r0.<init>(r1)     // Catch:{ Exception -> 0x0125, all -> 0x0105 }
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ Exception -> 0x0125, all -> 0x0105 }
            javax.net.ssl.HttpsURLConnection r0 = (javax.net.ssl.HttpsURLConnection) r0     // Catch:{ Exception -> 0x0125, all -> 0x0105 }
            r1 = 30000(0x7530, float:4.2039E-41)
            r0.setConnectTimeout(r1)     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            r0.setReadTimeout(r1)     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            java.lang.String r1 = "POST"
            r0.setRequestMethod(r1)     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            r1 = 1
            r0.setDoInput(r1)     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            r0.setDoOutput(r1)     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            r1 = 0
            r0.setUseCaches(r1)     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            java.lang.String r1 = "Content-Type"
            java.lang.String r2 = "application/x-www-form-urlencoded"
            r0.setRequestProperty(r1, r2)     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            com.umeng.commonsdk.statistics.idtracking.s$1 r1 = new com.umeng.commonsdk.statistics.idtracking.s$1     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            r1.<init>()     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            r0.setHostnameVerifier(r1)     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            r1.<init>()     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            java.lang.String r2 = "appKey="
            r1.append(r2)     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            java.lang.String r2 = "23338940"
            java.lang.String r3 = "UTF-8"
            java.lang.String r2 = java.net.URLEncoder.encode(r2, r3)     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            r1.append(r2)     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            java.lang.String r2 = "&uuid="
            r1.append(r2)     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            java.lang.String r2 = "FC1FE84794417B1BEF276234F6FB4E63"
            java.lang.String r3 = "UTF-8"
            java.lang.String r2 = java.net.URLEncoder.encode(r2, r3)     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            r1.append(r2)     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            java.io.DataOutputStream r2 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            java.io.OutputStream r3 = r0.getOutputStream()     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0103, all -> 0x00ff }
            r2.writeBytes(r1)     // Catch:{ Exception -> 0x00fd, all -> 0x00f9 }
            r2.flush()     // Catch:{ Exception -> 0x00fd, all -> 0x00f9 }
            int r1 = r0.getResponseCode()     // Catch:{ Exception -> 0x00fd, all -> 0x00f9 }
            r3 = 200(0xc8, float:2.8E-43)
            if (r1 != r3) goto L_0x00e4
            java.io.InputStream r1 = r0.getInputStream()     // Catch:{ Exception -> 0x00e4, all -> 0x00f9 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00e1, all -> 0x00d9 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00e1, all -> 0x00d9 }
            r4.<init>(r1)     // Catch:{ Exception -> 0x00e1, all -> 0x00d9 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x00e1, all -> 0x00d9 }
            java.lang.StringBuffer r7 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
            r7.<init>()     // Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
        L_0x00c1:
            java.lang.String r4 = r3.readLine()     // Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
            if (r4 == 0) goto L_0x00cb
            r7.append(r4)     // Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
            goto L_0x00c1
        L_0x00cb:
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
            r6.d = r7     // Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
            goto L_0x00e2
        L_0x00d2:
            r7 = move-exception
            r5 = r1
            r1 = r7
            r7 = r2
            r2 = r0
            r0 = r5
            goto L_0x010a
        L_0x00d9:
            r3 = move-exception
            r5 = r3
            r3 = r7
            r7 = r2
            r2 = r0
            r0 = r1
            r1 = r5
            goto L_0x010a
        L_0x00e1:
            r3 = r7
        L_0x00e2:
            r7 = r1
            goto L_0x00e5
        L_0x00e4:
            r3 = r7
        L_0x00e5:
            r2.close()     // Catch:{ Exception -> 0x00e8 }
        L_0x00e8:
            if (r3 == 0) goto L_0x00ef
            r3.close()     // Catch:{ Exception -> 0x00ee }
            goto L_0x00ef
        L_0x00ee:
        L_0x00ef:
            if (r7 == 0) goto L_0x00f6
            r7.close()     // Catch:{ Exception -> 0x00f5 }
            goto L_0x00f6
        L_0x00f5:
        L_0x00f6:
            if (r0 == 0) goto L_0x0130
            goto L_0x012d
        L_0x00f9:
            r1 = move-exception
            r3 = r7
            r7 = r2
            goto L_0x0101
        L_0x00fd:
            r7 = r2
            goto L_0x0126
        L_0x00ff:
            r1 = move-exception
            r3 = r7
        L_0x0101:
            r2 = r0
            goto L_0x0109
        L_0x0103:
            goto L_0x0126
        L_0x0105:
            r0 = move-exception
            r2 = r7
            r3 = r2
            r1 = r0
        L_0x0109:
            r0 = r3
        L_0x010a:
            if (r7 == 0) goto L_0x0111
            r7.close()     // Catch:{ Exception -> 0x0110 }
            goto L_0x0111
        L_0x0110:
        L_0x0111:
            if (r3 == 0) goto L_0x0118
            r3.close()     // Catch:{ Exception -> 0x0117 }
            goto L_0x0118
        L_0x0117:
        L_0x0118:
            if (r0 == 0) goto L_0x011f
            r0.close()     // Catch:{ Exception -> 0x011e }
            goto L_0x011f
        L_0x011e:
        L_0x011f:
            if (r2 == 0) goto L_0x0124
            r2.disconnect()
        L_0x0124:
            throw r1
        L_0x0125:
            r0 = r7
        L_0x0126:
            if (r7 == 0) goto L_0x012b
            r7.close()     // Catch:{ Exception -> 0x012b }
        L_0x012b:
            if (r0 == 0) goto L_0x0130
        L_0x012d:
            r0.disconnect()
        L_0x0130:
            java.lang.String r7 = r6.d
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.idtracking.s.b(java.lang.String):java.lang.String");
    }

    public static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke((Object) null, new Object[]{str, str2});
        } catch (Exception unused) {
            return str2;
        }
    }
}
