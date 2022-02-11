package com.umeng.commonsdk.proguard;

import java.lang.reflect.InvocationTargetException;

public class o {
    public static n a(Class<? extends n> cls, int i) {
        try {
            return (n) cls.getMethod("findByValue", new Class[]{Integer.TYPE}).invoke((Object) null, new Object[]{Integer.valueOf(i)});
        } catch (NoSuchMethodException e) {
            return null;
        } catch (IllegalAccessException e2) {
            return null;
        } catch (InvocationTargetException e3) {
            return null;
        }
    }
}
