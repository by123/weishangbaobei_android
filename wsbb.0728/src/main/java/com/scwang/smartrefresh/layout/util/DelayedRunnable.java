package com.scwang.smartrefresh.layout.util;

public class DelayedRunnable implements Runnable {
    public long delayMillis;
    private Runnable runnable;

    public DelayedRunnable(Runnable runnable2, long j) {
        this.runnable = runnable2;
        this.delayMillis = j;
    }

    public void run() {
        try {
            if (this.runnable != null) {
                this.runnable.run();
                this.runnable = null;
            }
        } catch (Throwable th) {
            if (!(th instanceof NoClassDefFoundError)) {
                th.printStackTrace();
            }
        }
    }
}
