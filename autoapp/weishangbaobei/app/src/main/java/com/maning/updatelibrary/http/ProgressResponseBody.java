package com.maning.updatelibrary.http;

import android.os.Handler;
import android.os.Looper;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class ProgressResponseBody extends ResponseBody {
    private BufferedSource bufferedSource;
    /* access modifiers changed from: private */
    public Handler mUIHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final AbsFileProgressCallback progressListener;
    /* access modifiers changed from: private */
    public final ResponseBody responseBody;

    public interface ProgressListener {
        void update(long j, long j2, boolean z);
    }

    public ProgressResponseBody(ResponseBody responseBody2, AbsFileProgressCallback absFileProgressCallback) {
        this.responseBody = responseBody2;
        this.progressListener = absFileProgressCallback;
    }

    public MediaType contentType() {
        return this.responseBody.contentType();
    }

    public long contentLength() {
        return this.responseBody.contentLength();
    }

    public BufferedSource source() {
        if (this.bufferedSource == null) {
            this.bufferedSource = Okio.buffer(source(this.responseBody.source()));
        }
        return this.bufferedSource;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [okio.Source, com.maning.updatelibrary.http.ProgressResponseBody$1] */
    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0;

            public long read(Buffer buffer, long j) throws IOException {
                final long read = ProgressResponseBody.super.read(buffer, j);
                this.totalBytesRead += read != -1 ? read : 0;
                ProgressResponseBody.this.mUIHandler.post(new Runnable() {
                    public void run() {
                        ProgressResponseBody.this.progressListener.onProgress(AnonymousClass1.this.totalBytesRead, ProgressResponseBody.this.responseBody.contentLength(), read == -1);
                    }
                });
                return read;
            }
        };
    }
}
