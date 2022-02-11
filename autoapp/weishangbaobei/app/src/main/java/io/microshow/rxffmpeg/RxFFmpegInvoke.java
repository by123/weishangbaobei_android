package io.microshow.rxffmpeg;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxFFmpegInvoke {
    public static final String TAG = "RxFFmpegInvoke";
    private static volatile RxFFmpegInvoke instance;
    private IFFmpegListener ffmpegListener;

    public native void exit();

    public native String getMediaInfo(String str);

    public native int runFFmpegCmd(String[] strArr);

    public native void setDebug(boolean z);

    static {
        System.loadLibrary("rxffmpeg-core");
        System.loadLibrary("rxffmpeg-invoke");
    }

    private RxFFmpegInvoke() {
    }

    public static RxFFmpegInvoke getInstance() {
        if (instance == null) {
            synchronized (RxFFmpegInvoke.class) {
                if (instance == null) {
                    instance = new RxFFmpegInvoke();
                }
            }
        }
        return instance;
    }

    public void runCommandAsync(String[] strArr, IFFmpegListener iFFmpegListener) {
        setFFmpegListener(iFFmpegListener);
        synchronized (RxFFmpegInvoke.class) {
            new Thread(new 1(this, strArr)).start();
        }
    }

    public int runCommand(String[] strArr, IFFmpegListener iFFmpegListener) {
        int runFFmpegCmd;
        setFFmpegListener(iFFmpegListener);
        synchronized (RxFFmpegInvoke.class) {
            runFFmpegCmd = runFFmpegCmd(strArr);
            onClean();
        }
        return runFFmpegCmd;
    }

    public Flowable<RxFFmpegProgress> runCommandRxJava(String[] strArr) {
        return Flowable.create(new 2(this, strArr), BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public void onProgress(int i, long j) {
        if (this.ffmpegListener != null) {
            this.ffmpegListener.onProgress(i, j);
        }
    }

    public void onFinish() {
        if (this.ffmpegListener != null) {
            this.ffmpegListener.onFinish();
        }
    }

    public void onCancel() {
        if (this.ffmpegListener != null) {
            this.ffmpegListener.onCancel();
        }
    }

    public void onError(String str) {
        if (this.ffmpegListener != null) {
            this.ffmpegListener.onError(str);
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

    public IFFmpegListener getFFmpegListener() {
        return this.ffmpegListener;
    }

    public void setFFmpegListener(IFFmpegListener iFFmpegListener) {
        this.ffmpegListener = iFFmpegListener;
    }
}
