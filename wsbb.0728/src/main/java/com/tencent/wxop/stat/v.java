package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.event.c;

final class v implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ c b;
    final /* synthetic */ Context c;

    v(String str, c cVar, Context context) {
        this.a = str;
        this.b = cVar;
        this.c = context;
    }

    public final void run() {
        try {
            if (StatServiceImpl.a(this.a)) {
                StatServiceImpl.q.error((Object) "The event_id of StatService.trackCustomBeginEvent() can not be null or empty.");
                return;
            }
            if (StatConfig.isDebugEnable()) {
                StatLogger f = StatServiceImpl.q;
                f.i("add begin key:" + this.b.toString());
            }
            if (StatServiceImpl.e.containsKey(this.b)) {
                StatLogger f2 = StatServiceImpl.q;
                f2.error((Object) "Duplicate CustomEvent key: " + this.b.toString() + ", trackCustomBeginEvent() repeated?");
            } else if (StatServiceImpl.e.size() <= StatConfig.getMaxParallelTimmingEvents()) {
                StatServiceImpl.e.put(this.b, Long.valueOf(System.currentTimeMillis()));
            } else {
                StatLogger f3 = StatServiceImpl.q;
                f3.error((Object) "The number of timedEvent exceeds the maximum value " + Integer.toString(StatConfig.getMaxParallelTimmingEvents()));
            }
        } catch (Throwable th) {
            StatServiceImpl.q.e(th);
            StatServiceImpl.a(this.c, th);
        }
    }
}
