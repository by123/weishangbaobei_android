package com.meiqia.meiqiasdk.util;

import android.content.Context;
import android.text.TextUtils;
import com.stub.StubApp;
import java.io.File;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;

public class MQDownloadManager {
    private static MQDownloadManager sInstance;
    /* access modifiers changed from: private */
    public Context mContext;
    private OkHttpClient mOkHttpClient = new OkHttpClient();

    public interface Callback {
        void onFailure();

        void onSuccess(File file);
    }

    private MQDownloadManager(Context context) {
        this.mContext = context;
    }

    public static MQDownloadManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (MQDownloadManager.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new MQDownloadManager(StubApp.getOrigApplicationContext(context.getApplicationContext()));
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<MQDownloadManager> cls = MQDownloadManager.class;
                        throw th;
                    }
                }
            }
        }
        return sInstance;
    }

    public void downloadVoice(final String str, final Callback callback) {
        if (!TextUtils.isEmpty(str) && str.startsWith("http")) {
            this.mOkHttpClient.newCall(new Request.Builder().url(str).build()).enqueue(new okhttp3.Callback() {
                public void onFailure(Call call, IOException iOException) {
                    if (callback != null) {
                        callback.onFailure();
                    }
                }

                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        try {
                            File cachedVoiceFileByUrl = MQAudioRecorderManager.getCachedVoiceFileByUrl(MQDownloadManager.this.mContext, str);
                            BufferedSink buffer = Okio.buffer(Okio.sink(cachedVoiceFileByUrl));
                            buffer.writeAll(response.body().source());
                            buffer.close();
                            if (callback != null) {
                                callback.onSuccess(cachedVoiceFileByUrl);
                            }
                        } catch (IOException e) {
                            if (callback != null) {
                                callback.onFailure();
                            }
                        }
                    } else if (callback != null) {
                        callback.onFailure();
                    }
                }
            });
        } else if (callback != null) {
            callback.onFailure();
        }
    }
}
