package com.tencent.bugly.proguard;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;

/* compiled from: BUGLY */
public final class ab extends Thread {
    private boolean a = false;
    private List<aa> b = new ArrayList();
    private List<ac> c = new ArrayList();
    private ArrayList<aa> d = new ArrayList<>();

    public final void a() {
        a(new Handler(Looper.getMainLooper()), 5000);
    }

    public final void b() {
        int i = 0;
        while (i < this.b.size()) {
            try {
                if (this.b.get(i).d().equals(Looper.getMainLooper().getThread().getName())) {
                    x.c("remove handler::%s", this.b.get(i));
                    this.b.remove(i);
                }
                i++;
            } catch (Exception e) {
                x.b(e);
                return;
            }
        }
    }

    private void a(Handler handler, long j) {
        if (handler == null) {
            x.e("addThread handler should not be null", new Object[0]);
            return;
        }
        String name = handler.getLooper().getThread().getName();
        int i = 0;
        while (i < this.b.size()) {
            try {
                if (this.b.get(i).d().equals(handler.getLooper().getThread().getName())) {
                    x.e("addThread fail ,this thread has been added in monitor queue", new Object[0]);
                    return;
                }
                i++;
            } catch (Exception e) {
                x.b(e);
            }
        }
        this.b.add(new aa(handler, name, 5000));
    }

    public final boolean c() {
        this.a = true;
        if (!isAlive()) {
            return false;
        }
        try {
            interrupt();
        } catch (Exception e) {
            x.b(e);
        }
        return true;
    }

    public final boolean d() {
        if (isAlive()) {
            return false;
        }
        try {
            start();
            return true;
        } catch (Exception e) {
            x.b(e);
            return false;
        }
    }

    private int e() {
        int i = 0;
        int i2 = 0;
        while (i < this.b.size()) {
            try {
                int max = Math.max(i2, this.b.get(i).c());
                i++;
                i2 = max;
            } catch (Exception e) {
                x.b(e);
            }
        }
        return i2;
    }

    public final void a(ac acVar) {
        if (this.c.contains(acVar)) {
            x.c("addThreadMonitorListeners fail ,this threadMonitorListener has been added in monitor queue", new Object[0]);
        } else {
            this.c.add(acVar);
        }
    }

    public final void b(ac acVar) {
        this.c.remove(acVar);
    }

    public final void run() {
        while (!this.a) {
            int i = 0;
            while (i < this.b.size()) {
                try {
                    this.b.get(i).a();
                    i++;
                } catch (Exception e) {
                    x.b(e);
                } catch (OutOfMemoryError e2) {
                    x.b(e2);
                }
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            for (long j = 2000; j > 0 && !isInterrupted(); j = 2000 - (SystemClock.uptimeMillis() - uptimeMillis)) {
                sleep(j);
            }
            int e3 = e();
            if (e3 != 0) {
                if (e3 != 1) {
                    this.d.clear();
                    for (int i2 = 0; i2 < this.b.size(); i2++) {
                        aa aaVar = this.b.get(i2);
                        if (aaVar.b()) {
                            this.d.add(aaVar);
                            aaVar.a(Long.MAX_VALUE);
                        }
                    }
                    int i3 = 0;
                    boolean z = false;
                    while (i3 < this.d.size()) {
                        aa aaVar2 = this.d.get(i3);
                        boolean z2 = z;
                        for (int i4 = 0; i4 < this.c.size(); i4++) {
                            if (this.c.get(i4).a(aaVar2)) {
                                z2 = true;
                            }
                        }
                        if (!z2) {
                            aaVar2.f();
                            x.c("although thread is blocked ,may not be anr error,so restore handler check wait time and restart check main thread", new Object[0]);
                        }
                        i3++;
                        z = z2;
                    }
                }
            }
        }
    }
}
