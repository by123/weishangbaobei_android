package com.umeng.commonsdk.statistics.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.BuglyStrategy;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.commonsdk.statistics.b;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class c {
    private static boolean e;
    private String a = "10.0.0.172";
    private int b = 80;
    private Context c;
    private b d;

    public c(Context context) {
        this.c = context;
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

    private boolean c() {
        String extraInfo;
        if (this.c.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.c.getPackageName()) != 0) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.c.getSystemService("connectivity");
            if (!DeviceConfig.checkPermission(this.c, "android.permission.ACCESS_NETWORK_STATE")) {
                return false;
            }
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (!(activeNetworkInfo == null || activeNetworkInfo.getType() == 1 || (extraInfo = activeNetworkInfo.getExtraInfo()) == null || (!extraInfo.equals("cmwap") && !extraInfo.equals("3gwap") && !extraInfo.equals("uniwap")))) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.c, th);
        }
    }

    public void a(b bVar) {
        this.d = bVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x0130  */
    public byte[] a(byte[] bArr, String str) {
        HttpURLConnection httpURLConnection;
        Throwable th;
        InputStream inputStream;
        boolean z = true;
        HttpURLConnection httpURLConnection2 = null;
        try {
            if (this.d != null) {
                this.d.onRequestStart();
            }
            if (c()) {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.a, this.b)));
            } else {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            }
            try {
                httpURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                httpURLConnection.setRequestProperty("X-Umeng-Sdk", a.a(this.c).b());
                httpURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                httpURLConnection.setRequestProperty("Content-Type", a.a(this.c).a());
                httpURLConnection.setRequestProperty("X-Umeng-Pro-Ver", "1.0.0");
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                if (Build.VERSION.SDK_INT < 8) {
                    System.setProperty("http.keepAlive", Bugly.SDK_IS_DEV);
                }
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
                outputStream.close();
                if (this.d != null) {
                    this.d.onRequestEnd();
                }
                int responseCode = httpURLConnection.getResponseCode();
                String headerField = httpURLConnection.getHeaderField("Content-Type");
                if (TextUtils.isEmpty(headerField) || !headerField.equalsIgnoreCase("application/thrift")) {
                    z = false;
                }
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.d("status code : " + responseCode + "; isThrifit:" + z);
                }
                if (responseCode == 200 && z) {
                    MLog.i("Send message to " + str);
                    inputStream = httpURLConnection.getInputStream();
                    byte[] readStreamToByteArray = HelperUtils.readStreamToByteArray(inputStream);
                    HelperUtils.safeClose(inputStream);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return readStreamToByteArray;
                } else if (httpURLConnection == null) {
                    return null;
                } else {
                    httpURLConnection.disconnect();
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    MLog.e("IOException,Failed to send message.", th);
                    if (httpURLConnection == null) {
                        return null;
                    }
                    httpURLConnection.disconnect();
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    httpURLConnection2 = httpURLConnection;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            if (httpURLConnection2 != null) {
            }
            throw th;
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

    /* JADX WARNING: Removed duplicated region for block: B:100:0x01d3 A[SYNTHETIC, Splitter:B:100:0x01d3] */
    /* JADX WARNING: Removed duplicated region for block: B:136:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:140:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:142:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0171 A[SYNTHETIC, Splitter:B:57:0x0171] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0176 A[SYNTHETIC, Splitter:B:60:0x0176] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x019e A[SYNTHETIC, Splitter:B:75:0x019e] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01a3 A[SYNTHETIC, Splitter:B:78:0x01a3] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01b2 A[SYNTHETIC, Splitter:B:85:0x01b2] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01b7 A[SYNTHETIC, Splitter:B:88:0x01b7] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01ce A[SYNTHETIC, Splitter:B:97:0x01ce] */
    public byte[] b(byte[] bArr, String str) {
        HttpsURLConnection httpsURLConnection;
        OutputStream outputStream;
        Throwable th;
        Throwable th2;
        OutputStream outputStream2;
        HttpsURLConnection httpsURLConnection2;
        InputStream inputStream;
        try {
            if (this.d != null) {
                this.d.onRequestStart();
            }
            if (c()) {
                httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.a, this.b)));
            } else {
                httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
            }
            try {
                if (!e) {
                    httpsURLConnection.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                    SSLContext instance = SSLContext.getInstance("TLS");
                    instance.init((KeyManager[]) null, (TrustManager[]) null, new SecureRandom());
                    httpsURLConnection.setSSLSocketFactory(instance.getSocketFactory());
                    e = true;
                }
                httpsURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                httpsURLConnection.setRequestProperty("X-Umeng-Sdk", a.a(this.c).b());
                httpsURLConnection.setRequestProperty("Content-Type", a.a(this.c).a());
                httpsURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                httpsURLConnection.setRequestProperty("X-Umeng-Pro-Ver", "1.0.0");
                httpsURLConnection.setConnectTimeout(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
                httpsURLConnection.setReadTimeout(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setUseCaches(false);
                outputStream = httpsURLConnection.getOutputStream();
            } catch (SSLHandshakeException e2) {
                outputStream2 = null;
                httpsURLConnection2 = httpsURLConnection;
                try {
                    UMLog.aq("A_10201", 2, "\\|");
                    if (outputStream2 != null) {
                        try {
                            outputStream2.close();
                        } catch (Exception e3) {
                            UMCrashManager.reportCrash(this.c, e3);
                        }
                    }
                    if (httpsURLConnection2 == null) {
                        return null;
                    }
                    try {
                        httpsURLConnection2.getInputStream().close();
                    } catch (IOException e4) {
                    }
                    httpsURLConnection2.disconnect();
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    outputStream = outputStream2;
                    httpsURLConnection = httpsURLConnection2;
                    th2 = th;
                    if (outputStream != null) {
                    }
                    if (httpsURLConnection != null) {
                    }
                    throw th2;
                }
            } catch (UnknownHostException e5) {
                outputStream = null;
                try {
                    UMLog.aq("A_10200", 2, "\\|");
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception e6) {
                            UMCrashManager.reportCrash(this.c, e6);
                        }
                    }
                    if (httpsURLConnection == null) {
                        return null;
                    }
                    try {
                        httpsURLConnection.getInputStream().close();
                    } catch (IOException e7) {
                    }
                    httpsURLConnection.disconnect();
                    return null;
                } catch (Throwable th4) {
                    th = th4;
                    th2 = th;
                    if (outputStream != null) {
                    }
                    if (httpsURLConnection != null) {
                    }
                    throw th2;
                }
            } catch (Throwable th5) {
                th2 = th5;
                outputStream = null;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception e8) {
                        UMCrashManager.reportCrash(this.c, e8);
                    }
                }
                if (httpsURLConnection != null) {
                    try {
                        httpsURLConnection.getInputStream().close();
                    } catch (IOException e9) {
                    }
                    httpsURLConnection.disconnect();
                }
                throw th2;
            }
            try {
                outputStream.write(bArr);
                outputStream.flush();
                httpsURLConnection.connect();
                if (this.d != null) {
                    this.d.onRequestEnd();
                }
                int responseCode = httpsURLConnection.getResponseCode();
                String headerField = httpsURLConnection.getHeaderField("Content-Type");
                boolean z = !TextUtils.isEmpty(headerField) && headerField.equalsIgnoreCase("application/thrift");
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.i("status code : " + responseCode + "; isThrifit:" + z);
                }
                if (responseCode != 200 || !z) {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception e10) {
                            UMCrashManager.reportCrash(this.c, e10);
                        }
                    }
                    if (httpsURLConnection == null) {
                        return null;
                    }
                    try {
                        httpsURLConnection.getInputStream().close();
                    } catch (IOException e11) {
                    }
                    httpsURLConnection.disconnect();
                    return null;
                }
                MLog.i("Send message to server. status code is: " + responseCode);
                UMRTLog.i(UMRTLog.RTLOG_TAG, "Send message to server. status code is: " + responseCode + "; url = " + str);
                inputStream = httpsURLConnection.getInputStream();
                byte[] readStreamToByteArray = HelperUtils.readStreamToByteArray(inputStream);
                HelperUtils.safeClose(inputStream);
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception e12) {
                        UMCrashManager.reportCrash(this.c, e12);
                    }
                }
                if (httpsURLConnection != null) {
                    try {
                        httpsURLConnection.getInputStream().close();
                    } catch (IOException e13) {
                    }
                    httpsURLConnection.disconnect();
                }
                return readStreamToByteArray;
            } catch (SSLHandshakeException e14) {
                outputStream2 = outputStream;
                httpsURLConnection2 = httpsURLConnection;
                UMLog.aq("A_10201", 2, "\\|");
                if (outputStream2 != null) {
                }
                if (httpsURLConnection2 == null) {
                }
            } catch (UnknownHostException e15) {
                UMLog.aq("A_10200", 2, "\\|");
                if (outputStream != null) {
                }
                if (httpsURLConnection == null) {
                }
            } catch (Throwable th6) {
                if (outputStream != null) {
                }
                if (httpsURLConnection == null) {
                }
            }
        } catch (SSLHandshakeException e16) {
            outputStream2 = null;
            httpsURLConnection2 = null;
            UMLog.aq("A_10201", 2, "\\|");
            if (outputStream2 != null) {
            }
            if (httpsURLConnection2 == null) {
            }
        } catch (UnknownHostException e17) {
            outputStream = null;
            httpsURLConnection = null;
            UMLog.aq("A_10200", 2, "\\|");
            if (outputStream != null) {
            }
            if (httpsURLConnection == null) {
            }
        } catch (Throwable th7) {
            th2 = th7;
            httpsURLConnection = null;
            outputStream = null;
            if (outputStream != null) {
            }
            if (httpsURLConnection != null) {
            }
            throw th2;
        }
    }
}
