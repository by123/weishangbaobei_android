package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.tencent.bugly.BuglyStrategy;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.b;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class e {
    private String a = "10.0.0.172";
    private int b = 80;
    private Context c;

    public e(Context context) {
        this.c = context;
    }

    private void a() {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(this.c, "sl_domain_p", "");
        if (!TextUtils.isEmpty(imprintProperty)) {
            a.g = DataHelper.assembleStatelessURL(imprintProperty);
        }
    }

    private void b() {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(this.c, "sl_domain_p", "");
        String imprintProperty2 = UMEnvelopeBuild.imprintProperty(this.c, "oversea_sl_domain_p", "");
        if (!TextUtils.isEmpty(imprintProperty)) {
            a.f = DataHelper.assembleStatelessURL(imprintProperty);
        }
        if (!TextUtils.isEmpty(imprintProperty2)) {
            a.h = DataHelper.assembleStatelessURL(imprintProperty2);
        }
        a.g = a.h;
        if (TextUtils.isEmpty(b.b)) {
            return;
        }
        if (b.b.startsWith("460") || b.b.startsWith("461")) {
            a.g = a.f;
        }
    }

    private boolean c() {
        String extraInfo;
        if (this.c != null) {
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
            } catch (Throwable th) {
                UMCrashManager.reportCrash(this.c, th);
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0117 A[SYNTHETIC, Splitter:B:32:0x0117] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x011c A[SYNTHETIC, Splitter:B:35:0x011c] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0130 A[SYNTHETIC, Splitter:B:45:0x0130] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0135 A[SYNTHETIC, Splitter:B:48:0x0135] */
    /* JADX WARNING: Removed duplicated region for block: B:90:? A[RETURN, SYNTHETIC] */
    public boolean a(byte[] bArr, String str) {
        OutputStream outputStream;
        HttpsURLConnection httpsURLConnection;
        Throwable th;
        boolean z;
        HttpsURLConnection httpsURLConnection2;
        InputStream inputStream;
        if (bArr == null || str == null) {
            ULog.i("walle", "[stateless] sendMessage, envelopeByte == null || path == null ");
            return false;
        }
        if (SdkVersion.SDK_TYPE == 0) {
            a();
        } else {
            a.f = a.h;
            b();
        }
        try {
            if (c()) {
                httpsURLConnection = (HttpsURLConnection) new URL(a.g + "/" + str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.a, this.b)));
            } else {
                httpsURLConnection = (HttpsURLConnection) new URL(a.g + "/" + str).openConnection();
            }
            try {
                httpsURLConnection.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init((KeyManager[]) null, (TrustManager[]) null, new SecureRandom());
                httpsURLConnection.setSSLSocketFactory(instance.getSocketFactory());
                httpsURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                httpsURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                httpsURLConnection.setConnectTimeout(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
                httpsURLConnection.setReadTimeout(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setUseCaches(false);
                outputStream = httpsURLConnection.getOutputStream();
            } catch (SSLHandshakeException e) {
                e = e;
                outputStream = null;
            } catch (Throwable th2) {
                th = th2;
                outputStream = null;
                httpsURLConnection2 = httpsURLConnection;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception e2) {
                    }
                }
                if (httpsURLConnection2 != null) {
                    try {
                        httpsURLConnection2.getInputStream().close();
                    } catch (IOException e3) {
                    }
                    httpsURLConnection2.disconnect();
                }
                throw th;
            }
            try {
                outputStream.write(bArr);
                outputStream.flush();
                httpsURLConnection.connect();
                if (httpsURLConnection.getResponseCode() == 200) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> send stateless message success : " + a.g + "/" + str);
                    z = true;
                } else {
                    z = false;
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception e4) {
                    }
                }
                if (httpsURLConnection == null) {
                    return z;
                }
                try {
                    inputStream = httpsURLConnection.getInputStream();
                    inputStream.close();
                } catch (IOException e5) {
                }
                httpsURLConnection.disconnect();
                return z;
            } catch (SSLHandshakeException e6) {
                e = e6;
                MLog.e("SSLHandshakeException, Failed to send message.", (Throwable) e);
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception e7) {
                    }
                }
                if (httpsURLConnection == null) {
                    return false;
                }
                inputStream = httpsURLConnection.getInputStream();
                z = false;
                inputStream.close();
                httpsURLConnection.disconnect();
                return z;
            } catch (Throwable th3) {
                httpsURLConnection2 = httpsURLConnection;
                th = th3;
                if (outputStream != null) {
                }
                if (httpsURLConnection2 != null) {
                }
                throw th;
            }
        } catch (SSLHandshakeException e8) {
            e = e8;
            httpsURLConnection = null;
            outputStream = null;
        } catch (Throwable th4) {
            outputStream = null;
            httpsURLConnection2 = null;
            th = th4;
            if (outputStream != null) {
            }
            if (httpsURLConnection2 != null) {
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0088, code lost:
        if (r0 == null) goto L_0x00e1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b7 A[SYNTHETIC, Splitter:B:24:0x00b7] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00bc  */
    public boolean b(byte[] bArr, String str) {
        HttpURLConnection httpURLConnection;
        Throwable th;
        OutputStream outputStream;
        Throwable th2;
        OutputStream outputStream2;
        HttpURLConnection httpURLConnection2;
        boolean z = true;
        OutputStream outputStream3 = null;
        if (bArr == null || str == null) {
            return false;
        }
        try {
            if (c()) {
                httpURLConnection2 = (HttpURLConnection) new URL(a.g + "/" + str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.a, this.b)));
            } else {
                httpURLConnection2 = (HttpURLConnection) new URL(a.g + "/" + str).openConnection();
            }
            try {
                httpURLConnection2.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                httpURLConnection2.setRequestProperty("Msg-Type", "envelope/json");
                httpURLConnection2.setConnectTimeout(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
                httpURLConnection2.setReadTimeout(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
                httpURLConnection2.setRequestMethod("POST");
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setDoInput(true);
                httpURLConnection2.setUseCaches(false);
                OutputStream outputStream4 = httpURLConnection2.getOutputStream();
                try {
                    outputStream4.write(bArr);
                    outputStream4.flush();
                    httpURLConnection2.connect();
                    if (httpURLConnection2.getResponseCode() != 200) {
                        z = false;
                    }
                    if (outputStream4 != null) {
                        try {
                            outputStream4.close();
                        } catch (Exception e) {
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    outputStream = outputStream4;
                    httpURLConnection = httpURLConnection2;
                    if (outputStream != null) {
                    }
                    if (httpURLConnection != null) {
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                httpURLConnection = httpURLConnection2;
                outputStream = outputStream3;
                if (outputStream != null) {
                }
                if (httpURLConnection != null) {
                }
                throw th;
            }
        } catch (Throwable th5) {
            outputStream = null;
            httpURLConnection = null;
            th = th5;
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e2) {
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }
}
