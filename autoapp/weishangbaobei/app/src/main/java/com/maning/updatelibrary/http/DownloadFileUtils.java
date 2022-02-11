package com.maning.updatelibrary.http;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import com.umeng.commonsdk.proguard.c;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.Interceptor$Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadFileUtils {
    /* access modifiers changed from: private */
    public static final String TAG = "DownloadFileUtils";
    private static DownloadFileUtils instance;
    private static HashMap<Object, Call> mCallHashMap = new HashMap<>();
    /* access modifiers changed from: private */
    public static Handler mUIHandler = new Handler(Looper.getMainLooper());
    private AbsFileProgressCallback defaultFileProgressCallback = new AbsFileProgressCallback() {
        public void onCancle() {
        }

        public void onFailed(String str) {
        }

        public void onProgress(long j, long j2, boolean z) {
        }

        public void onStart() {
        }

        public void onSuccess(String str) {
        }
    };
    private DownloadModel downloadModel = new DownloadModel();

    private DownloadFileUtils() {
    }

    public static DownloadFileUtils with() {
        instance = new DownloadFileUtils();
        return instance;
    }

    public DownloadFileUtils url(String str) {
        this.downloadModel.setHttpUrl(str);
        return instance;
    }

    public DownloadFileUtils downloadPath(String str) {
        this.downloadModel.setDownloadPath(str);
        return instance;
    }

    public DownloadFileUtils tag(Object obj) {
        this.downloadModel.setTag(obj);
        return instance;
    }

    public DownloadFileUtils headers(Map<String, String> map) {
        this.downloadModel.setHeadersMap(map);
        return instance;
    }

    public DownloadFileUtils addHeader(String str, String str2) {
        this.downloadModel.getHeadersMap().put(str, str2);
        return instance;
    }

    public void execute(AbsFileProgressCallback absFileProgressCallback) {
        if (absFileProgressCallback == null) {
            absFileProgressCallback = this.defaultFileProgressCallback;
        }
        this.downloadModel.setFileProgressCallback(absFileProgressCallback);
        startDonwload();
    }

    private void startDonwload() {
        if (this.downloadModel != null) {
            String httpUrl = this.downloadModel.getHttpUrl();
            Object tag = this.downloadModel.getTag();
            if (tag == null) {
                tag = httpUrl;
            }
            Map<String, String> headersMap = this.downloadModel.getHeadersMap();
            final String downloadPath = this.downloadModel.getDownloadPath();
            final AbsFileProgressCallback fileProgressCallback = this.downloadModel.getFileProgressCallback();
            OkHttpClient.Builder okhttpDefaultBuilder = getOkhttpDefaultBuilder();
            Request.Builder builder = new Request.Builder();
            builder.url(httpUrl);
            if (headersMap != null && headersMap.size() > 0) {
                for (String next : headersMap.keySet()) {
                    builder.addHeader(next, headersMap.get(next));
                }
            }
            okhttpDefaultBuilder.addNetworkInterceptor(new Interceptor() {
                public Response intercept(Interceptor$Chain interceptor$Chain) throws IOException {
                    Response proceed = interceptor$Chain.proceed(interceptor$Chain.request());
                    return proceed.newBuilder().body(new ProgressResponseBody(proceed.body(), fileProgressCallback)).build();
                }
            });
            mUIHandler.post(new Runnable() {
                public void run() {
                    fileProgressCallback.onStart();
                }
            });
            Call newCall = okhttpDefaultBuilder.build().newCall(builder.get().build());
            mCallHashMap.put(tag, newCall);
            newCall.enqueue(new Callback() {
                public void onFailure(final Call call, final IOException iOException) {
                    String access$000 = DownloadFileUtils.TAG;
                    Log.e(access$000, "onFailure:" + iOException.toString());
                    DownloadFileUtils.mUIHandler.post(new Runnable() {
                        public void run() {
                            if (call.isCanceled()) {
                                fileProgressCallback.onCancle();
                            } else {
                                fileProgressCallback.onFailed(iOException.toString());
                            }
                        }
                    });
                }

                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0045 */
                /* JADX WARNING: Removed duplicated region for block: B:29:0x0068 A[SYNTHETIC, Splitter:B:29:0x0068] */
                /* JADX WARNING: Removed duplicated region for block: B:37:0x0073 A[SYNTHETIC, Splitter:B:37:0x0073] */
                /* JADX WARNING: Removed duplicated region for block: B:41:0x0078 A[SYNTHETIC, Splitter:B:41:0x0078] */
                /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onResponse(okhttp3.Call r4, okhttp3.Response r5) throws java.io.IOException {
                    /*
                        r3 = this;
                        r4 = 2048(0x800, float:2.87E-42)
                        byte[] r4 = new byte[r4]
                        java.lang.String r0 = r3
                        com.maning.updatelibrary.http.DownloadFileUtils.checkDownloadFilePath(r0)
                        r0 = 0
                        okhttp3.ResponseBody r1 = r5.body()     // Catch:{ Exception -> 0x0058, all -> 0x0054 }
                        java.io.InputStream r1 = r1.byteStream()     // Catch:{ Exception -> 0x0058, all -> 0x0054 }
                        okhttp3.ResponseBody r5 = r5.body()     // Catch:{ Exception -> 0x0050, all -> 0x004d }
                        r5.contentLength()     // Catch:{ Exception -> 0x0050, all -> 0x004d }
                        java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x0050, all -> 0x004d }
                        java.lang.String r2 = r3     // Catch:{ Exception -> 0x0050, all -> 0x004d }
                        r5.<init>(r2)     // Catch:{ Exception -> 0x0050, all -> 0x004d }
                        java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0050, all -> 0x004d }
                        r2.<init>(r5)     // Catch:{ Exception -> 0x0050, all -> 0x004d }
                    L_0x0025:
                        int r5 = r1.read(r4)     // Catch:{ Exception -> 0x004b, all -> 0x0049 }
                        r0 = -1
                        if (r5 == r0) goto L_0x0031
                        r0 = 0
                        r2.write(r4, r0, r5)     // Catch:{ Exception -> 0x004b, all -> 0x0049 }
                        goto L_0x0025
                    L_0x0031:
                        r2.flush()     // Catch:{ Exception -> 0x004b, all -> 0x0049 }
                        android.os.Handler r4 = com.maning.updatelibrary.http.DownloadFileUtils.mUIHandler     // Catch:{ Exception -> 0x004b, all -> 0x0049 }
                        com.maning.updatelibrary.http.DownloadFileUtils$4$2 r5 = new com.maning.updatelibrary.http.DownloadFileUtils$4$2     // Catch:{ Exception -> 0x004b, all -> 0x0049 }
                        r5.<init>()     // Catch:{ Exception -> 0x004b, all -> 0x0049 }
                        r4.post(r5)     // Catch:{ Exception -> 0x004b, all -> 0x0049 }
                        if (r1 == 0) goto L_0x0045
                        r1.close()     // Catch:{ IOException -> 0x0045 }
                    L_0x0045:
                        r2.close()     // Catch:{ IOException -> 0x006e }
                        goto L_0x006e
                    L_0x0049:
                        r4 = move-exception
                        goto L_0x0071
                    L_0x004b:
                        r4 = move-exception
                        goto L_0x0052
                    L_0x004d:
                        r4 = move-exception
                        r2 = r0
                        goto L_0x0071
                    L_0x0050:
                        r4 = move-exception
                        r2 = r0
                    L_0x0052:
                        r0 = r1
                        goto L_0x005a
                    L_0x0054:
                        r4 = move-exception
                        r1 = r0
                        r2 = r1
                        goto L_0x0071
                    L_0x0058:
                        r4 = move-exception
                        r2 = r0
                    L_0x005a:
                        android.os.Handler r5 = com.maning.updatelibrary.http.DownloadFileUtils.mUIHandler     // Catch:{ all -> 0x006f }
                        com.maning.updatelibrary.http.DownloadFileUtils$4$3 r1 = new com.maning.updatelibrary.http.DownloadFileUtils$4$3     // Catch:{ all -> 0x006f }
                        r1.<init>(r4)     // Catch:{ all -> 0x006f }
                        r5.post(r1)     // Catch:{ all -> 0x006f }
                        if (r0 == 0) goto L_0x006b
                        r0.close()     // Catch:{ IOException -> 0x006b }
                    L_0x006b:
                        if (r2 == 0) goto L_0x006e
                        goto L_0x0045
                    L_0x006e:
                        return
                    L_0x006f:
                        r4 = move-exception
                        r1 = r0
                    L_0x0071:
                        if (r1 == 0) goto L_0x0076
                        r1.close()     // Catch:{ IOException -> 0x0076 }
                    L_0x0076:
                        if (r2 == 0) goto L_0x007b
                        r2.close()     // Catch:{ IOException -> 0x007b }
                    L_0x007b:
                        throw r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.maning.updatelibrary.http.DownloadFileUtils.AnonymousClass4.onResponse(okhttp3.Call, okhttp3.Response):void");
                }
            });
            return;
        }
        throw new NullPointerException("OkhttpRequestModel初始化失败");
    }

    /* access modifiers changed from: private */
    public static void checkDownloadFilePath(String str) {
        File file = new File(str.substring(0, str.lastIndexOf("/") + 1));
        File file2 = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @NonNull
    public static OkHttpClient.Builder getOkhttpDefaultBuilder() {
        SSLContext sSLContext;
        NoSuchAlgorithmException e;
        KeyManagementException e2;
        AnonymousClass5 r0 = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        try {
            sSLContext = SSLContext.getInstance("SSL");
            try {
                sSLContext.init((KeyManager[]) null, new TrustManager[]{r0}, new SecureRandom());
            } catch (NoSuchAlgorithmException e3) {
                e = e3;
            } catch (KeyManagementException e4) {
                e2 = e4;
                e2.printStackTrace();
                SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
                AnonymousClass6 r2 = new HostnameVerifier() {
                    public boolean verify(String str, SSLSession sSLSession) {
                        return true;
                    }
                };
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.connectTimeout(c.d, TimeUnit.MILLISECONDS);
                builder.readTimeout(c.d, TimeUnit.MILLISECONDS);
                builder.writeTimeout(c.d, TimeUnit.MILLISECONDS);
                builder.sslSocketFactory(socketFactory, r0);
                builder.hostnameVerifier(r2);
                return builder;
            }
        } catch (NoSuchAlgorithmException e5) {
            NoSuchAlgorithmException noSuchAlgorithmException = e5;
            sSLContext = null;
            e = noSuchAlgorithmException;
            e.printStackTrace();
            SSLSocketFactory socketFactory2 = sSLContext.getSocketFactory();
            AnonymousClass6 r22 = new HostnameVerifier() {
                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            };
            OkHttpClient.Builder builder2 = new OkHttpClient.Builder();
            builder2.connectTimeout(c.d, TimeUnit.MILLISECONDS);
            builder2.readTimeout(c.d, TimeUnit.MILLISECONDS);
            builder2.writeTimeout(c.d, TimeUnit.MILLISECONDS);
            builder2.sslSocketFactory(socketFactory2, r0);
            builder2.hostnameVerifier(r22);
            return builder2;
        } catch (KeyManagementException e6) {
            KeyManagementException keyManagementException = e6;
            sSLContext = null;
            e2 = keyManagementException;
            e2.printStackTrace();
            SSLSocketFactory socketFactory22 = sSLContext.getSocketFactory();
            AnonymousClass6 r222 = new HostnameVerifier() {
                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            };
            OkHttpClient.Builder builder22 = new OkHttpClient.Builder();
            builder22.connectTimeout(c.d, TimeUnit.MILLISECONDS);
            builder22.readTimeout(c.d, TimeUnit.MILLISECONDS);
            builder22.writeTimeout(c.d, TimeUnit.MILLISECONDS);
            builder22.sslSocketFactory(socketFactory22, r0);
            builder22.hostnameVerifier(r222);
            return builder22;
        }
        SSLSocketFactory socketFactory222 = sSLContext.getSocketFactory();
        AnonymousClass6 r2222 = new HostnameVerifier() {
            public boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        };
        OkHttpClient.Builder builder222 = new OkHttpClient.Builder();
        builder222.connectTimeout(c.d, TimeUnit.MILLISECONDS);
        builder222.readTimeout(c.d, TimeUnit.MILLISECONDS);
        builder222.writeTimeout(c.d, TimeUnit.MILLISECONDS);
        builder222.sslSocketFactory(socketFactory222, r0);
        builder222.hostnameVerifier(r2222);
        return builder222;
    }

    public static void cancle(Object obj) {
        Call call;
        try {
            if (mCallHashMap != null && mCallHashMap.size() > 0 && mCallHashMap.containsKey(obj) && (call = mCallHashMap.get(obj)) != null) {
                if (!call.isCanceled()) {
                    call.cancel();
                }
                mCallHashMap.remove(obj);
            }
        } catch (Exception unused) {
        }
    }

    public static void cancleAll() {
        try {
            if (mCallHashMap != null && mCallHashMap.size() > 0) {
                for (Map.Entry next : mCallHashMap.entrySet()) {
                    Object key = next.getKey();
                    Call call = (Call) next.getValue();
                    if (call != null) {
                        if (!call.isCanceled()) {
                            call.cancel();
                        }
                        mCallHashMap.remove(key);
                    }
                }
            }
        } catch (Exception unused) {
        }
    }
}
