package de.greenrobot.event.util;

import android.util.Log;
import de.greenrobot.event.EventBus;
import java.util.HashMap;
import java.util.Map;

public class ExceptionToResourceMapping {
    public final Map<Class<? extends Throwable>, Integer> throwableToMsgIdMap = new HashMap();

    public ExceptionToResourceMapping addMapping(Class<? extends Throwable> cls, int i) {
        this.throwableToMsgIdMap.put(cls, Integer.valueOf(i));
        return this;
    }

    public Integer mapThrowable(Throwable th) {
        int i = 20;
        Throwable th2 = th;
        do {
            Integer mapThrowableFlat = mapThrowableFlat(th2);
            if (mapThrowableFlat != null) {
                return mapThrowableFlat;
            }
            th2 = th2.getCause();
            i--;
            if (i <= 0 || th2 == th) {
                Log.d(EventBus.TAG, "No specific message ressource ID found for " + th);
            }
        } while (th2 != null);
        Log.d(EventBus.TAG, "No specific message ressource ID found for " + th);
        return null;
    }

    /* access modifiers changed from: protected */
    public Integer mapThrowableFlat(Throwable th) {
        Class<?> cls = th.getClass();
        Integer num = this.throwableToMsgIdMap.get(cls);
        if (num != null) {
            return num;
        }
        Class cls2 = null;
        Integer num2 = num;
        for (Map.Entry next : this.throwableToMsgIdMap.entrySet()) {
            Class cls3 = (Class) next.getKey();
            if (cls3.isAssignableFrom(cls) && (cls2 == null || cls2.isAssignableFrom(cls3))) {
                cls2 = cls3;
                num2 = (Integer) next.getValue();
            }
        }
        return num2;
    }
}
