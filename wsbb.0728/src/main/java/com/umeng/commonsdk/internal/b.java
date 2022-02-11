package com.umeng.commonsdk.internal;

import android.content.Context;
import com.stub.StubApp;

public class b {
    private static b b;
    private Context a;
    private c c;

    private b(Context context) {
        this.a = context;
        this.c = new c(context);
    }

    public static b a(Context context) {
        b bVar;
        synchronized (b.class) {
            try {
                if (b == null) {
                    b = new b(StubApp.getOrigApplicationContext(context.getApplicationContext()));
                }
                bVar = b;
            } catch (Throwable th) {
                Class<b> cls = b.class;
                throw th;
            }
        }
        return bVar;
    }

    public c a() {
        return this.c;
    }
}
