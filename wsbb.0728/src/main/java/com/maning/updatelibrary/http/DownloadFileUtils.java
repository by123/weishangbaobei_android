package com.maning.updatelibrary.http;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import com.umeng.commonsdk.proguard.c;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

    public static void cancle(Object obj) {
        Call call;
        try {
            if (mCallHashMap != null && mCallHashMap.size() > 0 && mCallHashMap.containsKey(obj) && (call = mCallHashMap.get(obj)) != null) {
                if (!call.isCanceled()) {
                    call.cancel();
                }
                mCallHashMap.remove(obj);
            }
        } catch (Exception e) {
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
        } catch (Exception e) {
        }
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
        SSLContext sSLContext = null;
        AnonymousClass5 r2 = new X509TrustManager() {
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
                TrustManager[] trustManagerArr = {r2};
                sSLContext.init((KeyManager[]) null, trustManagerArr, new SecureRandom());
            } catch (NoSuchAlgorithmException e) {
                e = e;
                e.printStackTrace();
                SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
                AnonymousClass6 r1 = new HostnameVerifier() {
                    public boolean verify(String str, SSLSession sSLSession) {
                        return true;
                    }
                };
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.connectTimeout(c.d, TimeUnit.MILLISECONDS);
                builder.readTimeout(c.d, TimeUnit.MILLISECONDS);
                builder.writeTimeout(c.d, TimeUnit.MILLISECONDS);
                builder.sslSocketFactory(socketFactory, r2);
                builder.hostnameVerifier(r1);
                return builder;
            } catch (KeyManagementException e2) {
                e = e2;
                e.printStackTrace();
                SSLSocketFactory socketFactory2 = sSLContext.getSocketFactory();
                AnonymousClass6 r12 = new HostnameVerifier() {
                    public boolean verify(String str, SSLSession sSLSession) {
                        return true;
                    }
                };
                OkHttpClient.Builder builder2 = new OkHttpClient.Builder();
                builder2.connectTimeout(c.d, TimeUnit.MILLISECONDS);
                builder2.readTimeout(c.d, TimeUnit.MILLISECONDS);
                builder2.writeTimeout(c.d, TimeUnit.MILLISECONDS);
                builder2.sslSocketFactory(socketFactory2, r2);
                builder2.hostnameVerifier(r12);
                return builder2;
            }
        } catch (NoSuchAlgorithmException e3) {
            e = e3;
        } catch (KeyManagementException e4) {
            e = e4;
            e.printStackTrace();
            SSLSocketFactory socketFactory22 = sSLContext.getSocketFactory();
            AnonymousClass6 r122 = new HostnameVerifier() {
                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            };
            OkHttpClient.Builder builder22 = new OkHttpClient.Builder();
            builder22.connectTimeout(c.d, TimeUnit.MILLISECONDS);
            builder22.readTimeout(c.d, TimeUnit.MILLISECONDS);
            builder22.writeTimeout(c.d, TimeUnit.MILLISECONDS);
            builder22.sslSocketFactory(socketFactory22, r2);
            builder22.hostnameVerifier(r122);
            return builder22;
        }
        SSLSocketFactory socketFactory222 = sSLContext.getSocketFactory();
        AnonymousClass6 r1222 = new HostnameVerifier() {
            public boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        };
        OkHttpClient.Builder builder222 = new OkHttpClient.Builder();
        builder222.connectTimeout(c.d, TimeUnit.MILLISECONDS);
        builder222.readTimeout(c.d, TimeUnit.MILLISECONDS);
        builder222.writeTimeout(c.d, TimeUnit.MILLISECONDS);
        builder222.sslSocketFactory(socketFactory222, r2);
        builder222.hostnameVerifier(r1222);
        return builder222;
    }

    private void startDonwload() {
        if (this.downloadModel != null) {
            String httpUrl = this.downloadModel.getHttpUrl();
            Object tag = this.downloadModel.getTag();
            Object obj = tag == null ? httpUrl : tag;
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
            mCallHashMap.put(obj, newCall);
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

                /* JADX WARNING: Removed duplicated region for block: B:13:0x0040 A[SYNTHETIC, Splitter:B:13:0x0040] */
                /* JADX WARNING: Removed duplicated region for block: B:27:0x0064 A[SYNTHETIC, Splitter:B:27:0x0064] */
                /* JADX WARNING: Removed duplicated region for block: B:30:0x0069 A[SYNTHETIC, Splitter:B:30:0x0069] */
                /* JADX WARNING: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
                public void onResponse(Call call, Response response) throws IOException {
                    InputStream inputStream;
                    FileOutputStream fileOutputStream;
                    byte[] bArr = new byte[2048];
                    DownloadFileUtils.checkDownloadFilePath(downloadPath);
                    try {
                        inputStream = response.body().byteStream();
                        try {
                            response.body().contentLength();
                            fileOutputStream = new FileOutputStream(new File(downloadPath));
                            while (true) {
                                try {
                                    int read = inputStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    fileOutputStream.write(bArr, 0, read);
                                } catch (Exception e) {
                                    e = e;
                                    try {
                                        DownloadFileUtils.mUIHandler.post(new Runnable() {
                                            public void run() {
                                                String access$000 = DownloadFileUtils.TAG;
                                                Log.e(access$000, "onFailure:" + e.getMessage());
                                                if (e.getMessage().equals("Socket closed")) {
                                                    fileProgressCallback.onCancle();
                                                } else {
                                                    fileProgressCallback.onFailed(e.toString());
                                                }
                                            }
                                        });
                                        if (inputStream != null) {
                                            try {
                                                inputStream.close();
                                            } catch (IOException e2) {
                                            }
                                        }
                                        if (fileOutputStream == null) {
                                            return;
                                        }
                                        fileOutputStream.close();
                                    } catch (Throwable th) {
                                        th = th;
                                        if (inputStream != null) {
                                            try {
                                                inputStream.close();
                                            } catch (IOException e3) {
                                            }
                                        }
                                        if (fileOutputStream != null) {
                                            try {
                                                fileOutputStream.close();
                                            } catch (IOException e4) {
                                            }
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (inputStream != null) {
                                    }
                                    if (fileOutputStream != null) {
                                    }
                                    throw th;
                                }
                            }
                            fileOutputStream.flush();
                            DownloadFileUtils.mUIHandler.post(new Runnable() {
                                public void run() {
                                    fileProgressCallback.onSuccess("");
                                }
                            });
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e5) {
                                }
                            }
                        } catch (Exception e6) {
                            e = e6;
                            fileOutputStream = null;
                            DownloadFileUtils.mUIHandler.post(new Runnable() {
                                public void run() {
                                    String access$000 = DownloadFileUtils.TAG;
                                    Log.e(access$000, "onFailure:" + e.getMessage());
                                    if (e.getMessage().equals("Socket closed")) {
                                        fileProgressCallback.onCancle();
                                    } else {
                                        fileProgressCallback.onFailed(e.toString());
                                    }
                                }
                            });
                            if (inputStream != null) {
                            }
                            if (fileOutputStream == null) {
                            }
                            fileOutputStream.close();
                        } catch (Throwable th3) {
                            th = th3;
                            fileOutputStream = null;
                            if (inputStream != null) {
                            }
                            if (fileOutputStream != null) {
                            }
                            throw th;
                        }
                    } catch (Exception e7) {
                        e = e7;
                        inputStream = null;
                        fileOutputStream = null;
                    } catch (Throwable th4) {
                        th = th4;
                        inputStream = null;
                        fileOutputStream = null;
                        if (inputStream != null) {
                        }
                        if (fileOutputStream != null) {
                        }
                        throw th;
                    }
                    try {
                        fileOutputStream.close();
                    } catch (IOException e8) {
                    }
                }
            });
            return;
        }
        throw new NullPointerException("OkhttpRequestModel初始化失败");
    }

    public static DownloadFileUtils with() {
        instance = new DownloadFileUtils();
        return instance;
    }

    public DownloadFileUtils addHeader(String str, String str2) {
        this.downloadModel.getHeadersMap().put(str, str2);
        return instance;
    }

    public DownloadFileUtils downloadPath(String str) {
        this.downloadModel.setDownloadPath(str);
        return instance;
    }

    public void execute(AbsFileProgressCallback absFileProgressCallback) {
        if (absFileProgressCallback == null) {
            absFileProgressCallback = this.defaultFileProgressCallback;
        }
        this.downloadModel.setFileProgressCallback(absFileProgressCallback);
        startDonwload();
    }

    public DownloadFileUtils headers(Map<String, String> map) {
        this.downloadModel.setHeadersMap(map);
        return instance;
    }

    public DownloadFileUtils tag(Object obj) {
        this.downloadModel.setTag(obj);
        return instance;
    }

    public DownloadFileUtils url(String str) {
        this.downloadModel.setHttpUrl(str);
        return instance;
    }
}
