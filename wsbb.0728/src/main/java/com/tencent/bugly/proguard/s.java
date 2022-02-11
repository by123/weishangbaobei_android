package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.b;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import java.util.Random;

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
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
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
                return a2;
            }
            outputStream.write(bArr);
            return a2;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            x.e("Failed to upload, please check your network.", new Object[0]);
            return null;
        }
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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: byte[]} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0055 A[SYNTHETIC, Splitter:B:32:0x0055] */
    private static byte[] b(HttpURLConnection httpURLConnection) {
        BufferedInputStream bufferedInputStream;
        ? r2 = 0;
        if (httpURLConnection != null) {
            try {
                bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[WXMediaMessage.DESCRIPTION_LENGTH_LIMIT];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    byteArrayOutputStream.flush();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        bufferedInputStream.close();
                        r2 = byteArray;
                    } catch (Throwable th) {
                        th.printStackTrace();
                        r2 = byteArray;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                if (r2 != 0) {
                }
                throw th;
            }
        }
        return r2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:80:0x016f A[Catch:{ all -> 0x019c, Throwable -> 0x01a1 }] */
    public final byte[] a(String str, byte[] bArr, v vVar, Map<String, String> map) {
        boolean z;
        int i;
        IOException iOException;
        boolean z2;
        int i2;
        int i3;
        int i4;
        byte[] bArr2 = null;
        int i5 = 0;
        if (str == null) {
            x.e("Failed for no URL.", new Object[0]);
            return null;
        }
        long length = bArr == null ? 0 : (long) bArr.length;
        x.c("request: %s, send: %d (pid=%d | tid=%d)", str, Long.valueOf(length), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        int i6 = 0;
        int i7 = 0;
        boolean z3 = false;
        String str2 = str;
        while (i6 <= 0 && i7 <= 0) {
            if (z3) {
                z3 = false;
            } else {
                i6++;
                if (i6 > 1) {
                    x.c("try time: " + i6, new Object[i5]);
                    SystemClock.sleep(((long) new Random(System.currentTimeMillis()).nextInt(10000)) + 10000);
                }
            }
            String c2 = b.c(this.c);
            if (c2 == null) {
                x.d("Failed to request for network not avail", new Object[i5]);
                z = z3;
                i = i6;
            } else {
                vVar.a(length);
                HttpURLConnection a2 = a(str2, bArr, c2, map);
                if (a2 != null) {
                    try {
                        int responseCode = a2.getResponseCode();
                        if (responseCode == 200) {
                            this.a = a(a2);
                            byte[] b2 = b(a2);
                            vVar.b(b2 == null ? 0 : (long) b2.length);
                            try {
                                a2.disconnect();
                                return b2;
                            } catch (Throwable th) {
                                if (x.a(th)) {
                                    return b2;
                                }
                                th.printStackTrace();
                                return b2;
                            }
                        } else {
                            if (responseCode == 301 || responseCode == 302 || responseCode == 303 || responseCode == 307) {
                                try {
                                    String headerField = a2.getHeaderField("Location");
                                    if (headerField == null) {
                                        try {
                                            x.e("Failed to redirect: %d" + responseCode, new Object[0]);
                                            try {
                                                a2.disconnect();
                                            } catch (Throwable th2) {
                                                if (!x.a(th2)) {
                                                    th2.printStackTrace();
                                                }
                                            }
                                            return null;
                                        } catch (IOException e) {
                                            e = e;
                                            z2 = true;
                                            iOException = e;
                                            i2 = i7;
                                            i3 = i6;
                                            try {
                                                if (!x.a(iOException)) {
                                                }
                                                try {
                                                    a2.disconnect();
                                                    z3 = z2;
                                                    i4 = i2;
                                                    i = i3;
                                                } catch (Throwable th3) {
                                                    if (!x.a(th3)) {
                                                        th3.printStackTrace();
                                                    }
                                                    z3 = z2;
                                                    i4 = i2;
                                                    i = i3;
                                                }
                                                z = z3;
                                                i7 = i4;
                                                bArr2 = null;
                                                i5 = 0;
                                                z3 = z;
                                                i6 = i;
                                            } catch (Throwable th4) {
                                                if (!x.a(th4)) {
                                                    th4.printStackTrace();
                                                }
                                            }
                                        }
                                    } else {
                                        i7++;
                                        try {
                                            x.c("redirect code: %d ,to:%s", Integer.valueOf(responseCode), headerField);
                                            i3 = 0;
                                            z3 = true;
                                            i2 = i7;
                                            str2 = headerField;
                                        } catch (IOException e2) {
                                            e = e2;
                                            i6 = 0;
                                            str2 = headerField;
                                            z2 = true;
                                            iOException = e;
                                            i2 = i7;
                                            i3 = i6;
                                            if (!x.a(iOException)) {
                                            }
                                            a2.disconnect();
                                            z3 = z2;
                                            i4 = i2;
                                            i = i3;
                                            z = z3;
                                            i7 = i4;
                                            bArr2 = null;
                                            i5 = 0;
                                            z3 = z;
                                            i6 = i;
                                        }
                                    }
                                } catch (IOException e3) {
                                    e = e3;
                                }
                            } else {
                                i2 = i7;
                                i3 = i6;
                            }
                            try {
                                x.d("response code " + responseCode, new Object[0]);
                                long contentLength = (long) a2.getContentLength();
                                if (contentLength < 0) {
                                    contentLength = 0;
                                }
                                vVar.b(contentLength);
                                try {
                                    a2.disconnect();
                                } catch (Throwable th5) {
                                    if (!x.a(th5)) {
                                        th5.printStackTrace();
                                    }
                                }
                                i4 = i2;
                                i = i3;
                            } catch (IOException e4) {
                                iOException = e4;
                                z2 = z3;
                                if (!x.a(iOException)) {
                                    iOException.printStackTrace();
                                }
                                a2.disconnect();
                                z3 = z2;
                                i4 = i2;
                                i = i3;
                                z = z3;
                                i7 = i4;
                                bArr2 = null;
                                i5 = 0;
                                z3 = z;
                                i6 = i;
                            }
                            z = z3;
                            i7 = i4;
                        }
                    } catch (IOException e5) {
                        iOException = e5;
                        z2 = z3;
                        i2 = i7;
                        i3 = i6;
                    }
                } else {
                    x.c("Failed to execute post.", new Object[0]);
                    vVar.b(0);
                    z = z3;
                    i = i6;
                }
                bArr2 = null;
                i5 = 0;
            }
            z3 = z;
            i6 = i;
        }
        return bArr2;
        throw th;
    }
}
