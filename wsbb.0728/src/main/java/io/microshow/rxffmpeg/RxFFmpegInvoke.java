package io.microshow.rxffmpeg;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxFFmpegInvoke {
    public static final String TAG = "RxFFmpegInvoke";
    private static volatile RxFFmpegInvoke instance;
    private IFFmpegListener ffmpegListener;

    static {
        System.loadLibrary("rxffmpeg-core");
        System.loadLibrary("rxffmpeg-invoke");
    }

    private RxFFmpegInvoke() {
    }

    public static RxFFmpegInvoke getInstance() {
        if (instance == null) {
            synchronized (RxFFmpegInvoke.class) {
                try {
                    if (instance == null) {
                        instance = new RxFFmpegInvoke();
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<RxFFmpegInvoke> cls = RxFFmpegInvoke.class;
                        throw th;
                    }
                }
            }
        }
        return instance;
    }

    public native void exit();

    public IFFmpegListener getFFmpegListener() {
        return this.ffmpegListener;
    }

    public native String getMediaInfo(String str);

    public void onCancel() {
        if (this.ffmpegListener != null) {
            this.ffmpegListener.onCancel();
        }
    }

    public void onClean() {
        if (this.ffmpegListener != null) {
            this.ffmpegListener = null;
        }
    }

    public void onDestroy() {
        if (this.ffmpegListener != null) {
            this.ffmpegListener = null;
        }
        if (instance != null) {
            instance = null;
        }
    }

    public void onError(String str) {
        if (this.ffmpegListener != null) {
            this.ffmpegListener.onError(str);
        }
    }

    public void onFinish() {
        if (this.ffmpegListener != null) {
            this.ffmpegListener.onFinish();
        }
    }

    public void onProgress(int i, long j) {
        if (this.ffmpegListener != null) {
            this.ffmpegListener.onProgress(i, j);
        }
    }

    public int runCommand(String[] strArr, IFFmpegListener iFFmpegListener) {
        int runFFmpegCmd;
        setFFmpegListener(iFFmpegListener);
        synchronized (RxFFmpegInvoke.class) {
            try {
                runFFmpegCmd = runFFmpegCmd(strArr);
                onClean();
            } catch (Throwable th) {
                Class<RxFFmpegInvoke> cls = RxFFmpegInvoke.class;
                throw th;
            }
        }
        return runFFmpegCmd;
    }

    public void runCommandAsync(String[] strArr, IFFmpegListener iFFmpegListener) {
        setFFmpegListener(iFFmpegListener);
        synchronized (RxFFmpegInvoke.class) {
            try {
                new Thread(new 1(this, strArr)).start();
            } catch (Throwable th) {
                Class<RxFFmpegInvoke> cls = RxFFmpegInvoke.class;
                throw th;
            }
        }
    }

    public Flowable<RxFFmpegProgress> runCommandRxJava(String[] strArr) {
        return Flowable.create(new 2(this, strArr), BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public native int runFFmpegCmd(String[] strArr);

    public native void setDebug(boolean z);

    public void setFFmpegListener(IFFmpegListener iFFmpegListener) {
        this.ffmpegListener = iFFmpegListener;
    }
}
