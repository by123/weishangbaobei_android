package com.umeng.socialize.common;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.umeng.socialize.utils.SocializeUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QueuedWork {
    public static boolean isUseThreadPool;
    private static ExecutorService mLogicExecutor = Executors.newFixedThreadPool(5);
    private static ExecutorService mNetExecutor = Executors.newFixedThreadPool(5);
    private static Handler uiHandler;

    public static void runInMain(Runnable runnable) {
        if (uiHandler == null) {
            uiHandler = new Handler(Looper.getMainLooper());
        }
        uiHandler.post(runnable);
    }

    public static void runInBack(Runnable runnable, boolean z) {
        if (!isUseThreadPool) {
            new Thread(runnable).start();
        } else if (z) {
            mNetExecutor.execute(runnable);
        } else {
            mLogicExecutor.execute(runnable);
        }
    }

    public static abstract class DialogThread<T> extends UMAsyncTask {
        Dialog dialog = null;

        public DialogThread(Context context) {
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            SocializeUtils.safeCloseDialog(this.dialog);
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            SocializeUtils.safeShowDialog(this.dialog);
        }
    }

    public static abstract class UMAsyncTask<Result> {
        protected Runnable thread;

        /* access modifiers changed from: protected */
        public abstract Result doInBackground();

        /* access modifiers changed from: protected */
        public void onPostExecute(Result result) {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
        }

        public final UMAsyncTask<Result> execute() {
            this.thread = new Runnable() {
                public void run() {
                    final Object doInBackground = UMAsyncTask.this.doInBackground();
                    QueuedWork.runInMain(new Runnable() {
                        public void run() {
                            UMAsyncTask.this.onPostExecute(doInBackground);
                        }
                    });
                }
            };
            QueuedWork.runInMain(new Runnable() {
                public void run() {
                    UMAsyncTask.this.onPreExecute();
                }
            });
            QueuedWork.runInBack(this.thread, false);
            return this;
        }
    }
}
