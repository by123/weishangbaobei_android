package com.meiqia.meiqiasdk.util;

import android.os.AsyncTask;

public abstract class MQAsyncTask<Params, Result> extends AsyncTask<Params, Void, Result> {
    private Callback<Result> mCallback;

    public interface Callback<T> {
        void onPostExecute(T t);

        void onTaskCancelled();
    }

    public MQAsyncTask(Callback<Result> callback) {
        this.mCallback = callback;
    }

    public final void cancelTask() {
        if (getStatus() != AsyncTask.Status.FINISHED) {
            cancel(true);
        }
    }

    /* access modifiers changed from: protected */
    public final void onPostExecute(Result result) {
        super.onPostExecute(result);
        if (this.mCallback != null) {
            this.mCallback.onPostExecute(result);
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        super.onCancelled();
        if (this.mCallback != null) {
            this.mCallback.onTaskCancelled();
        }
        this.mCallback = null;
    }
}
