package com.umeng.commonsdk.proguard;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class v implements Serializable {
    private static Map<Class<? extends j>, Map<? extends q, v>> d = new HashMap();
    public final String a;
    public final byte b;
    public final w c;

    public v(String str, byte b2, w wVar) {
        this.a = str;
        this.b = b2;
        this.c = wVar;
    }

    public static Map<? extends q, v> a(Class<? extends j> cls) {
        if (!d.containsKey(cls)) {
            try {
                cls.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("InstantiationException for TBase class: " + cls.getName() + ", message: " + e.getMessage());
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + cls.getName() + ", message: " + e2.getMessage());
            }
        }
        return d.get(cls);
    }

    public static void a(Class<? extends j> cls, Map<? extends q, v> map) {
        d.put(cls, map);
    }
}
