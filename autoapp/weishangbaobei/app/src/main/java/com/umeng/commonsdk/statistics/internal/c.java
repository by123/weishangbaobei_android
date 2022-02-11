package com.umeng.commonsdk.statistics.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.commonsdk.statistics.b;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

/* compiled from: NetworkHelper */
public class c {
    private static boolean e;
    private String a = "10.0.0.172";
    private int b = 80;
    private Context c;
    private b d;

    public c(Context context) {
        this.c = context;
    }

    public void a(b bVar) {
        this.d = bVar;
    }

    private void a() {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(this.c, "domain_p", "");
        String imprintProperty2 = UMEnvelopeBuild.imprintProperty(this.c, "domain_s", "");
        if (!TextUtils.isEmpty(imprintProperty)) {
            UMServerURL.DEFAULT_URL = DataHelper.assembleURL(imprintProperty);
        }
        if (!TextUtils.isEmpty(imprintProperty2)) {
            UMServerURL.SECONDARY_URL = DataHelper.assembleURL(imprintProperty2);
        }
        AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
    }

    private void b() {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(this.c, "domain_p", "");
        String imprintProperty2 = UMEnvelopeBuild.imprintProperty(this.c, "domain_s", "");
        if (!TextUtils.isEmpty(imprintProperty)) {
            UMServerURL.DEFAULT_URL = DataHelper.assembleURL(imprintProperty);
        }
        if (!TextUtils.isEmpty(imprintProperty2)) {
            UMServerURL.SECONDARY_URL = DataHelper.assembleURL(imprintProperty2);
        }
        String imprintProperty3 = UMEnvelopeBuild.imprintProperty(this.c, "oversea_domain_p", "");
        String imprintProperty4 = UMEnvelopeBuild.imprintProperty(this.c, "oversea_domain_s", "");
        if (!TextUtils.isEmpty(imprintProperty3)) {
            UMServerURL.OVERSEA_DEFAULT_URL = DataHelper.assembleURL(imprintProperty3);
        }
        if (!TextUtils.isEmpty(imprintProperty4)) {
            UMServerURL.OVERSEA_SECONDARY_URL = DataHelper.assembleURL(imprintProperty4);
        }
        AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.OVERSEA_DEFAULT_URL, UMServerURL.OVERSEA_SECONDARY_URL};
        if (TextUtils.isEmpty(b.b)) {
            return;
        }
        if (b.b.startsWith("460") || b.b.startsWith("461")) {
            AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
        }
    }

    public byte[] a(byte[] bArr, boolean z) {
        if (SdkVersion.SDK_TYPE == 0) {
            a();
        } else {
            UMServerURL.DEFAULT_URL = UMServerURL.OVERSEA_DEFAULT_URL;
            UMServerURL.SECONDARY_URL = UMServerURL.OVERSEA_SECONDARY_URL;
            b();
        }
        int i = 0;
        byte[] bArr2 = null;
        while (true) {
            if (i >= AnalyticsConstants.APPLOG_URL_LIST.length) {
                break;
            }
            bArr2 = b(bArr, AnalyticsConstants.APPLOG_URL_LIST[i]);
            if (bArr2 == null) {
                if (this.d != null) {
                    this.d.onRequestFailed();
                }
                i++;
            } else if (this.d != null) {
                this.d.onRequestSucceed(z);
            }
        }
        return bArr2;
    }

    private boolean c() {
        NetworkInfo activeNetworkInfo;
        String extraInfo;
        if (this.c.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.c.getPackageName()) != 0) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.c.getSystemService("connectivity");
            if (!DeviceConfig.checkPermission(this.c, "android.permission.ACCESS_NETWORK_STATE") || connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getType() == 1 || (extraInfo = activeNetworkInfo.getExtraInfo()) == null || (!extraInfo.equals("cmwap") && !extraInfo.equals("3gwap") && !extraInfo.equals("uniwap"))) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.c, th);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0132  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] a(byte[] r7, java.lang.String r8) {
        /*
            r6 = this;
            r0 = 0
            com.umeng.commonsdk.statistics.internal.b r1 = r6.d     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
            if (r1 == 0) goto L_0x000a
            com.umeng.commonsdk.statistics.internal.b r1 = r6.d     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
            r1.onRequestStart()     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
        L_0x000a:
            boolean r1 = r6.c()     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
            if (r1 == 0) goto L_0x002c
            java.net.Proxy r1 = new java.net.Proxy     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
            java.net.Proxy$Type r2 = java.net.Proxy.Type.HTTP     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
            java.net.InetSocketAddress r3 = new java.net.InetSocketAddress     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
            java.lang.String r4 = r6.a     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
            int r5 = r6.b     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
            r3.<init>(r4, r5)     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
            r1.<init>(r2, r3)     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
            java.net.URL r2 = new java.net.URL     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
            r2.<init>(r8)     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
            java.net.URLConnection r1 = r2.openConnection(r1)     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
            goto L_0x0037
        L_0x002c:
            java.net.URL r1 = new java.net.URL     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
            r1.<init>(r8)     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
            java.net.URLConnection r1 = r1.openConnection()     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Throwable -> 0x0122, all -> 0x011f }
        L_0x0037:
            java.lang.String r2 = "X-Umeng-UTC"
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x011d }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ Throwable -> 0x011d }
            r1.setRequestProperty(r2, r3)     // Catch:{ Throwable -> 0x011d }
            java.lang.String r2 = "X-Umeng-Sdk"
            android.content.Context r3 = r6.c     // Catch:{ Throwable -> 0x011d }
            com.umeng.commonsdk.statistics.internal.a r3 = com.umeng.commonsdk.statistics.internal.a.a((android.content.Context) r3)     // Catch:{ Throwable -> 0x011d }
            java.lang.String r3 = r3.b()     // Catch:{ Throwable -> 0x011d }
            r1.setRequestProperty(r2, r3)     // Catch:{ Throwable -> 0x011d }
            java.lang.String r2 = "Msg-Type"
            java.lang.String r3 = "envelope/json"
            r1.setRequestProperty(r2, r3)     // Catch:{ Throwable -> 0x011d }
            java.lang.String r2 = "Content-Type"
            android.content.Context r3 = r6.c     // Catch:{ Throwable -> 0x011d }
            com.umeng.commonsdk.statistics.internal.a r3 = com.umeng.commonsdk.statistics.internal.a.a((android.content.Context) r3)     // Catch:{ Throwable -> 0x011d }
            java.lang.String r3 = r3.a()     // Catch:{ Throwable -> 0x011d }
            r1.setRequestProperty(r2, r3)     // Catch:{ Throwable -> 0x011d }
            java.lang.String r2 = "X-Umeng-Pro-Ver"
            java.lang.String r3 = "1.0.0"
            r1.setRequestProperty(r2, r3)     // Catch:{ Throwable -> 0x011d }
            r2 = 10000(0x2710, float:1.4013E-41)
            r1.setConnectTimeout(r2)     // Catch:{ Throwable -> 0x011d }
            r2 = 30000(0x7530, float:4.2039E-41)
            r1.setReadTimeout(r2)     // Catch:{ Throwable -> 0x011d }
            java.lang.String r2 = "POST"
            r1.setRequestMethod(r2)     // Catch:{ Throwable -> 0x011d }
            r2 = 1
            r1.setDoOutput(r2)     // Catch:{ Throwable -> 0x011d }
            r1.setDoInput(r2)     // Catch:{ Throwable -> 0x011d }
            r3 = 0
            r1.setUseCaches(r3)     // Catch:{ Throwable -> 0x011d }
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x011d }
            r5 = 8
            if (r4 >= r5) goto L_0x0097
            java.lang.String r4 = "http.keepAlive"
            java.lang.String r5 = "false"
            java.lang.System.setProperty(r4, r5)     // Catch:{ Throwable -> 0x011d }
        L_0x0097:
            java.io.OutputStream r4 = r1.getOutputStream()     // Catch:{ Throwable -> 0x011d }
            r4.write(r7)     // Catch:{ Throwable -> 0x011d }
            r4.flush()     // Catch:{ Throwable -> 0x011d }
            r4.close()     // Catch:{ Throwable -> 0x011d }
            com.umeng.commonsdk.statistics.internal.b r7 = r6.d     // Catch:{ Throwable -> 0x011d }
            if (r7 == 0) goto L_0x00ad
            com.umeng.commonsdk.statistics.internal.b r7 = r6.d     // Catch:{ Throwable -> 0x011d }
            r7.onRequestEnd()     // Catch:{ Throwable -> 0x011d }
        L_0x00ad:
            int r7 = r1.getResponseCode()     // Catch:{ Throwable -> 0x011d }
            java.lang.String r4 = "Content-Type"
            java.lang.String r4 = r1.getHeaderField(r4)     // Catch:{ Throwable -> 0x011d }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x011d }
            if (r5 != 0) goto L_0x00c6
            java.lang.String r5 = "application/thrift"
            boolean r4 = r4.equalsIgnoreCase(r5)     // Catch:{ Throwable -> 0x011d }
            if (r4 == 0) goto L_0x00c6
            goto L_0x00c7
        L_0x00c6:
            r2 = 0
        L_0x00c7:
            boolean r3 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG     // Catch:{ Throwable -> 0x011d }
            if (r3 == 0) goto L_0x00e7
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x011d }
            r3.<init>()     // Catch:{ Throwable -> 0x011d }
            java.lang.String r4 = "status code : "
            r3.append(r4)     // Catch:{ Throwable -> 0x011d }
            r3.append(r7)     // Catch:{ Throwable -> 0x011d }
            java.lang.String r4 = "; isThrifit:"
            r3.append(r4)     // Catch:{ Throwable -> 0x011d }
            r3.append(r2)     // Catch:{ Throwable -> 0x011d }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x011d }
            com.umeng.commonsdk.statistics.common.MLog.d((java.lang.String) r3)     // Catch:{ Throwable -> 0x011d }
        L_0x00e7:
            r3 = 200(0xc8, float:2.8E-43)
            if (r7 != r3) goto L_0x0117
            if (r2 == 0) goto L_0x0117
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x011d }
            r7.<init>()     // Catch:{ Throwable -> 0x011d }
            java.lang.String r2 = "Send message to "
            r7.append(r2)     // Catch:{ Throwable -> 0x011d }
            r7.append(r8)     // Catch:{ Throwable -> 0x011d }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x011d }
            com.umeng.commonsdk.statistics.common.MLog.i((java.lang.String) r7)     // Catch:{ Throwable -> 0x011d }
            java.io.InputStream r7 = r1.getInputStream()     // Catch:{ Throwable -> 0x011d }
            byte[] r8 = com.umeng.commonsdk.statistics.common.HelperUtils.readStreamToByteArray(r7)     // Catch:{ all -> 0x0112 }
            com.umeng.commonsdk.statistics.common.HelperUtils.safeClose((java.io.InputStream) r7)     // Catch:{ Throwable -> 0x011d }
            if (r1 == 0) goto L_0x0111
            r1.disconnect()
        L_0x0111:
            return r8
        L_0x0112:
            r8 = move-exception
            com.umeng.commonsdk.statistics.common.HelperUtils.safeClose((java.io.InputStream) r7)     // Catch:{ Throwable -> 0x011d }
            throw r8     // Catch:{ Throwable -> 0x011d }
        L_0x0117:
            if (r1 == 0) goto L_0x011c
            r1.disconnect()
        L_0x011c:
            return r0
        L_0x011d:
            r7 = move-exception
            goto L_0x0124
        L_0x011f:
            r7 = move-exception
            r1 = r0
            goto L_0x0130
        L_0x0122:
            r7 = move-exception
            r1 = r0
        L_0x0124:
            java.lang.String r8 = "IOException,Failed to send message."
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r8, (java.lang.Throwable) r7)     // Catch:{ all -> 0x012f }
            if (r1 == 0) goto L_0x012e
            r1.disconnect()
        L_0x012e:
            return r0
        L_0x012f:
            r7 = move-exception
        L_0x0130:
            if (r1 == 0) goto L_0x0135
            r1.disconnect()
        L_0x0135:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.internal.c.a(byte[], java.lang.String):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:107:0x01c8 A[SYNTHETIC, Splitter:B:107:0x01c8] */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x01d4 A[SYNTHETIC, Splitter:B:112:0x01d4] */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x01e3 A[SYNTHETIC, Splitter:B:120:0x01e3] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x01ef A[SYNTHETIC, Splitter:B:125:0x01ef] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0184 A[SYNTHETIC, Splitter:B:73:0x0184] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0190 A[SYNTHETIC, Splitter:B:78:0x0190] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01a6 A[SYNTHETIC, Splitter:B:90:0x01a6] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01b2 A[SYNTHETIC, Splitter:B:95:0x01b2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] b(byte[] r9, java.lang.String r10) {
        /*
            r8 = this;
            r0 = 2
            r1 = 0
            com.umeng.commonsdk.statistics.internal.b r2 = r8.d     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
            if (r2 == 0) goto L_0x000b
            com.umeng.commonsdk.statistics.internal.b r2 = r8.d     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
            r2.onRequestStart()     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
        L_0x000b:
            boolean r2 = r8.c()     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
            if (r2 == 0) goto L_0x002d
            java.net.Proxy r2 = new java.net.Proxy     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
            java.net.Proxy$Type r3 = java.net.Proxy.Type.HTTP     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
            java.net.InetSocketAddress r4 = new java.net.InetSocketAddress     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
            java.lang.String r5 = r8.a     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
            int r6 = r8.b     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
            r4.<init>(r5, r6)     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
            r2.<init>(r3, r4)     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
            java.net.URL r3 = new java.net.URL     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
            r3.<init>(r10)     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
            java.net.URLConnection r2 = r3.openConnection(r2)     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
            javax.net.ssl.HttpsURLConnection r2 = (javax.net.ssl.HttpsURLConnection) r2     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
            goto L_0x0038
        L_0x002d:
            java.net.URL r2 = new java.net.URL     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
            r2.<init>(r10)     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
            java.net.URLConnection r2 = r2.openConnection()     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
            javax.net.ssl.HttpsURLConnection r2 = (javax.net.ssl.HttpsURLConnection) r2     // Catch:{ SSLHandshakeException -> 0x01bd, UnknownHostException -> 0x019b, Throwable -> 0x0180, all -> 0x017d }
        L_0x0038:
            boolean r3 = e     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            r4 = 1
            if (r3 != 0) goto L_0x0059
            org.apache.http.conn.ssl.X509HostnameVerifier r3 = org.apache.http.conn.ssl.SSLSocketFactory.STRICT_HOSTNAME_VERIFIER     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            r2.setHostnameVerifier(r3)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            java.lang.String r3 = "TLS"
            javax.net.ssl.SSLContext r3 = javax.net.ssl.SSLContext.getInstance(r3)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            java.security.SecureRandom r5 = new java.security.SecureRandom     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            r5.<init>()     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            r3.init(r1, r1, r5)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            javax.net.ssl.SSLSocketFactory r3 = r3.getSocketFactory()     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            r2.setSSLSocketFactory(r3)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            e = r4     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
        L_0x0059:
            java.lang.String r3 = "X-Umeng-UTC"
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            r2.setRequestProperty(r3, r5)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            java.lang.String r3 = "X-Umeng-Sdk"
            android.content.Context r5 = r8.c     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            com.umeng.commonsdk.statistics.internal.a r5 = com.umeng.commonsdk.statistics.internal.a.a((android.content.Context) r5)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            java.lang.String r5 = r5.b()     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            r2.setRequestProperty(r3, r5)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            java.lang.String r3 = "Content-Type"
            android.content.Context r5 = r8.c     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            com.umeng.commonsdk.statistics.internal.a r5 = com.umeng.commonsdk.statistics.internal.a.a((android.content.Context) r5)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            java.lang.String r5 = r5.a()     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            r2.setRequestProperty(r3, r5)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            java.lang.String r3 = "Msg-Type"
            java.lang.String r5 = "envelope/json"
            r2.setRequestProperty(r3, r5)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            java.lang.String r3 = "X-Umeng-Pro-Ver"
            java.lang.String r5 = "1.0.0"
            r2.setRequestProperty(r3, r5)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            r3 = 30000(0x7530, float:4.2039E-41)
            r2.setConnectTimeout(r3)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            r2.setReadTimeout(r3)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            java.lang.String r3 = "POST"
            r2.setRequestMethod(r3)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            r2.setDoOutput(r4)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            r2.setDoInput(r4)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            r3 = 0
            r2.setUseCaches(r3)     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            java.io.OutputStream r5 = r2.getOutputStream()     // Catch:{ SSLHandshakeException -> 0x017b, UnknownHostException -> 0x0179, Throwable -> 0x0177, all -> 0x0174 }
            r5.write(r9)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            r5.flush()     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            r2.connect()     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            com.umeng.commonsdk.statistics.internal.b r9 = r8.d     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            if (r9 == 0) goto L_0x00bf
            com.umeng.commonsdk.statistics.internal.b r9 = r8.d     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            r9.onRequestEnd()     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
        L_0x00bf:
            int r9 = r2.getResponseCode()     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            java.lang.String r6 = "Content-Type"
            java.lang.String r6 = r2.getHeaderField(r6)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            if (r7 != 0) goto L_0x00d8
            java.lang.String r7 = "application/thrift"
            boolean r6 = r6.equalsIgnoreCase(r7)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            if (r6 == 0) goto L_0x00d8
            r3 = 1
        L_0x00d8:
            boolean r4 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            if (r4 == 0) goto L_0x00f8
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            r4.<init>()     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            java.lang.String r6 = "status code : "
            r4.append(r6)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            r4.append(r9)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            java.lang.String r6 = "; isThrifit:"
            r4.append(r6)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            r4.append(r3)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            java.lang.String r4 = r4.toString()     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            com.umeng.commonsdk.statistics.common.MLog.i((java.lang.String) r4)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
        L_0x00f8:
            r4 = 200(0xc8, float:2.8E-43)
            if (r9 != r4) goto L_0x0159
            if (r3 == 0) goto L_0x0159
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            r3.<init>()     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            java.lang.String r4 = "Send message to server. status code is: "
            r3.append(r4)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            r3.append(r9)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            java.lang.String r3 = r3.toString()     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            com.umeng.commonsdk.statistics.common.MLog.i((java.lang.String) r3)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            java.lang.String r3 = "MobclickRT"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            r4.<init>()     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            java.lang.String r6 = "Send message to server. status code is: "
            r4.append(r6)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            r4.append(r9)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            java.lang.String r9 = "; url = "
            r4.append(r9)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            r4.append(r10)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            java.lang.String r9 = r4.toString()     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            com.umeng.commonsdk.debug.UMRTLog.i(r3, r9)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            java.io.InputStream r9 = r2.getInputStream()     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            byte[] r10 = com.umeng.commonsdk.statistics.common.HelperUtils.readStreamToByteArray(r9)     // Catch:{ all -> 0x0154 }
            com.umeng.commonsdk.statistics.common.HelperUtils.safeClose((java.io.InputStream) r9)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            if (r5 == 0) goto L_0x0147
            r5.close()     // Catch:{ Exception -> 0x0141 }
            goto L_0x0147
        L_0x0141:
            r9 = move-exception
            android.content.Context r0 = r8.c
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r0, r9)
        L_0x0147:
            if (r2 == 0) goto L_0x0153
            java.io.InputStream r9 = r2.getInputStream()     // Catch:{ IOException -> 0x0150 }
            r9.close()     // Catch:{ IOException -> 0x0150 }
        L_0x0150:
            r2.disconnect()
        L_0x0153:
            return r10
        L_0x0154:
            r10 = move-exception
            com.umeng.commonsdk.statistics.common.HelperUtils.safeClose((java.io.InputStream) r9)     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
            throw r10     // Catch:{ SSLHandshakeException -> 0x01bf, UnknownHostException -> 0x019d, Throwable -> 0x0172 }
        L_0x0159:
            if (r5 == 0) goto L_0x0165
            r5.close()     // Catch:{ Exception -> 0x015f }
            goto L_0x0165
        L_0x015f:
            r9 = move-exception
            android.content.Context r10 = r8.c
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r10, r9)
        L_0x0165:
            if (r2 == 0) goto L_0x0171
            java.io.InputStream r9 = r2.getInputStream()     // Catch:{ IOException -> 0x016e }
            r9.close()     // Catch:{ IOException -> 0x016e }
        L_0x016e:
            r2.disconnect()
        L_0x0171:
            return r1
        L_0x0172:
            goto L_0x0182
        L_0x0174:
            r9 = move-exception
            goto L_0x01e1
        L_0x0177:
            r5 = r1
            goto L_0x0182
        L_0x0179:
            r5 = r1
            goto L_0x019d
        L_0x017b:
            r5 = r1
            goto L_0x01bf
        L_0x017d:
            r9 = move-exception
            r2 = r1
            goto L_0x01e1
        L_0x0180:
            r2 = r1
            r5 = r2
        L_0x0182:
            if (r5 == 0) goto L_0x018e
            r5.close()     // Catch:{ Exception -> 0x0188 }
            goto L_0x018e
        L_0x0188:
            r9 = move-exception
            android.content.Context r10 = r8.c
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r10, r9)
        L_0x018e:
            if (r2 == 0) goto L_0x019a
            java.io.InputStream r9 = r2.getInputStream()     // Catch:{ IOException -> 0x0197 }
            r9.close()     // Catch:{ IOException -> 0x0197 }
        L_0x0197:
            r2.disconnect()
        L_0x019a:
            return r1
        L_0x019b:
            r2 = r1
            r5 = r2
        L_0x019d:
            java.lang.String r9 = "A_10200"
            java.lang.String r10 = "\\|"
            com.umeng.commonsdk.debug.UMLog.aq((java.lang.String) r9, (int) r0, (java.lang.String) r10)     // Catch:{ all -> 0x01df }
            if (r5 == 0) goto L_0x01b0
            r5.close()     // Catch:{ Exception -> 0x01aa }
            goto L_0x01b0
        L_0x01aa:
            r9 = move-exception
            android.content.Context r10 = r8.c
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r10, r9)
        L_0x01b0:
            if (r2 == 0) goto L_0x01bc
            java.io.InputStream r9 = r2.getInputStream()     // Catch:{ IOException -> 0x01b9 }
            r9.close()     // Catch:{ IOException -> 0x01b9 }
        L_0x01b9:
            r2.disconnect()
        L_0x01bc:
            return r1
        L_0x01bd:
            r2 = r1
            r5 = r2
        L_0x01bf:
            java.lang.String r9 = "A_10201"
            java.lang.String r10 = "\\|"
            com.umeng.commonsdk.debug.UMLog.aq((java.lang.String) r9, (int) r0, (java.lang.String) r10)     // Catch:{ all -> 0x01df }
            if (r5 == 0) goto L_0x01d2
            r5.close()     // Catch:{ Exception -> 0x01cc }
            goto L_0x01d2
        L_0x01cc:
            r9 = move-exception
            android.content.Context r10 = r8.c
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r10, r9)
        L_0x01d2:
            if (r2 == 0) goto L_0x01de
            java.io.InputStream r9 = r2.getInputStream()     // Catch:{ IOException -> 0x01db }
            r9.close()     // Catch:{ IOException -> 0x01db }
        L_0x01db:
            r2.disconnect()
        L_0x01de:
            return r1
        L_0x01df:
            r9 = move-exception
            r1 = r5
        L_0x01e1:
            if (r1 == 0) goto L_0x01ed
            r1.close()     // Catch:{ Exception -> 0x01e7 }
            goto L_0x01ed
        L_0x01e7:
            r10 = move-exception
            android.content.Context r0 = r8.c
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r0, r10)
        L_0x01ed:
            if (r2 == 0) goto L_0x01f9
            java.io.InputStream r10 = r2.getInputStream()     // Catch:{ IOException -> 0x01f6 }
            r10.close()     // Catch:{ IOException -> 0x01f6 }
        L_0x01f6:
            r2.disconnect()
        L_0x01f9:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.internal.c.b(byte[], java.lang.String):byte[]");
    }
}
