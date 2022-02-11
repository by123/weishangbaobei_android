package com.maning.updatelibrary.http;

public abstract class AbsFileProgressCallback {
    public abstract void onCancle();

    public abstract void onFailed(String str);

    public abstract void onProgress(long j, long j2, boolean z);

    public abstract void onStart();

    public abstract void onSuccess(String str);
}
