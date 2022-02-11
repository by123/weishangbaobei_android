package com.tencent.a.a.a.a;

import android.content.Context;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class g {
    private static g V;
    private Map<Integer, f> U = null;
    private int b = 0;
    private Context c = null;

    private g(Context context) {
        this.c = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.U = new HashMap(3);
        this.U.put(1, new e(context));
        this.U.put(2, new b(context));
        this.U.put(4, new d(context));
    }

    public static synchronized g C(Context context) {
        g gVar;
        synchronized (g.class) {
            if (V == null) {
                V = new g(context);
            }
            gVar = V;
        }
        return gVar;
    }

    private c b(List<Integer> list) {
        c o;
        if (list.size() >= 0) {
            for (Integer num : list) {
                f fVar = this.U.get(num);
                if (fVar != null && (o = fVar.o()) != null && h.c(o.c)) {
                    return o;
                }
            }
        }
        return new c();
    }

    public final void a(String str) {
        c p = p();
        p.c = str;
        if (!h.b(p.a)) {
            p.a = h.a(this.c);
        }
        if (!h.b(p.b)) {
            p.b = h.b(this.c);
        }
        p.T = System.currentTimeMillis();
        for (Map.Entry<Integer, f> value : this.U.entrySet()) {
            ((f) value.getValue()).a(p);
        }
    }

    public final c p() {
        return b(new ArrayList(Arrays.asList(new Integer[]{1, 2, 4})));
    }
}
