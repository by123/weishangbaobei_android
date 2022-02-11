package com.janedler.V2;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.lang.ref.WeakReference;

public class JToastManager {
    private static final int LONG_DURATION_MS = 2750;
    private static final int MSG_TIMEOUT = 0;
    private static final int SHORT_DURATION_MS = 1500;
    private static JToastManager sSnackbarManager;
    private SnackbarRecord mCurrentSnackbar;
    private final Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        public boolean handleMessage(Message message) {
            Log.e("TAG", "hHH");
            if (message.what != 0) {
                return false;
            }
            JToastManager.this.handleTimeout((SnackbarRecord) message.obj);
            return true;
        }
    });
    private final Object mLock = new Object();
    private SnackbarRecord mNextSnackbar;

    interface Callback {
        void dismiss(int i);

        void show();
    }

    static JToastManager getInstance() {
        if (sSnackbarManager == null) {
            sSnackbarManager = new JToastManager();
        }
        return sSnackbarManager;
    }

    private JToastManager() {
    }

    public void show(int i, Callback callback) {
        synchronized (this.mLock) {
            if (isCurrentSnackbar(callback)) {
                int unused = this.mCurrentSnackbar.duration = i;
                this.mHandler.removeCallbacksAndMessages(this.mCurrentSnackbar);
                scheduleTimeoutLocked(this.mCurrentSnackbar);
                return;
            }
            if (isNextSnackbar(callback)) {
                int unused2 = this.mNextSnackbar.duration = i;
            } else {
                this.mNextSnackbar = new SnackbarRecord(i, callback);
            }
            if (this.mCurrentSnackbar == null || !cancelSnackbarLocked(this.mCurrentSnackbar, 4)) {
                this.mCurrentSnackbar = null;
                showNextSnackbarLocked();
            }
        }
    }

    private boolean isCurrentSnackbar(Callback callback) {
        return this.mCurrentSnackbar != null && this.mCurrentSnackbar.isSnackbar(callback);
    }

    private boolean isNextSnackbar(Callback callback) {
        return this.mNextSnackbar != null && this.mNextSnackbar.isSnackbar(callback);
    }

    public void dismiss(Callback callback, int i) {
        synchronized (this.mLock) {
            if (isCurrentSnackbar(callback)) {
                cancelSnackbarLocked(this.mCurrentSnackbar, i);
            } else if (isNextSnackbar(callback)) {
                cancelSnackbarLocked(this.mNextSnackbar, i);
            }
        }
    }

    public void onDismissed(Callback callback) {
        synchronized (this.mLock) {
            if (isCurrentSnackbar(callback)) {
                this.mCurrentSnackbar = null;
                if (this.mNextSnackbar != null) {
                    showNextSnackbarLocked();
                }
            }
        }
    }

    public void onShown(Callback callback) {
        synchronized (this.mLock) {
            if (isCurrentSnackbar(callback)) {
                scheduleTimeoutLocked(this.mCurrentSnackbar);
            }
        }
    }

    public void cancelTimeout(Callback callback) {
        synchronized (this.mLock) {
            if (isCurrentSnackbar(callback)) {
                this.mHandler.removeCallbacksAndMessages(this.mCurrentSnackbar);
            }
        }
    }

    public void restoreTimeout(Callback callback) {
        synchronized (this.mLock) {
            if (isCurrentSnackbar(callback)) {
                scheduleTimeoutLocked(this.mCurrentSnackbar);
            }
        }
    }

    private static class SnackbarRecord {
        /* access modifiers changed from: private */
        public final WeakReference<Callback> callback;
        /* access modifiers changed from: private */
        public int duration;

        SnackbarRecord(int i, Callback callback2) {
            this.callback = new WeakReference<>(callback2);
            this.duration = i;
        }

        /* access modifiers changed from: package-private */
        public boolean isSnackbar(Callback callback2) {
            return callback2 != null && this.callback.get() == callback2;
        }
    }

    private void showNextSnackbarLocked() {
        if (this.mNextSnackbar != null) {
            this.mCurrentSnackbar = this.mNextSnackbar;
            this.mNextSnackbar = null;
            Callback callback = (Callback) this.mCurrentSnackbar.callback.get();
            if (callback != null) {
                callback.show();
            } else {
                this.mCurrentSnackbar = null;
            }
        }
    }

    private void scheduleTimeoutLocked(SnackbarRecord snackbarRecord) {
        if (snackbarRecord.duration != -2) {
            if (snackbarRecord.duration > 0) {
                int unused = snackbarRecord.duration;
            } else {
                int access$100 = snackbarRecord.duration;
            }
            this.mHandler.removeCallbacksAndMessages(snackbarRecord);
            this.mHandler.sendMessageDelayed(Message.obtain(this.mHandler, 0, snackbarRecord), 2000);
        }
    }

    /* access modifiers changed from: private */
    public void handleTimeout(SnackbarRecord snackbarRecord) {
        synchronized (this.mLock) {
            if (this.mCurrentSnackbar == snackbarRecord || this.mNextSnackbar == snackbarRecord) {
                cancelSnackbarLocked(snackbarRecord, 2);
            }
        }
    }

    private boolean cancelSnackbarLocked(SnackbarRecord snackbarRecord, int i) {
        Callback callback = (Callback) snackbarRecord.callback.get();
        if (callback == null) {
            return false;
        }
        callback.dismiss(i);
        return true;
    }
}
