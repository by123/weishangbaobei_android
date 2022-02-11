package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.text.TextUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class a {
    private static Object a = new Object();
    private static String b;

    public static void a(Context context) {
        Constructor<?> constructor;
        Object newInstance;
        try {
            Class<?> cls = Class.forName("com.wireless.security.securityenv.sdk.SecurityEnvSDK");
            if (cls != null && (constructor = cls.getConstructor(new Class[]{Context.class})) != null && (newInstance = constructor.newInstance(new Object[]{context})) != null) {
                Method declaredMethod = cls.getDeclaredMethod("initSync", new Class[0]);
                if (declaredMethod != null) {
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(newInstance, new Object[0]);
                }
                Method declaredMethod2 = cls.getDeclaredMethod("getToken", new Class[0]);
                if (declaredMethod2 != null) {
                    declaredMethod2.setAccessible(true);
                    String str = (String) declaredMethod2.invoke(newInstance, new Object[0]);
                    if (!TextUtils.isEmpty(str)) {
                        synchronized (a) {
                            b = str;
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public static String b(Context context) {
        String str;
        synchronized (a) {
            str = b;
        }
        return str;
    }
}
