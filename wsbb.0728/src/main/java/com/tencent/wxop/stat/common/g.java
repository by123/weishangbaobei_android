package com.tencent.wxop.stat.common;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import com.stub.StubApp;
import java.lang.reflect.Method;

public class g {
    private static volatile g c;
    private int a = 10;
    private int b = 0;
    private Context d = null;
    private boolean e = false;

    private g(Context context) {
        this.d = StubApp.getOrigApplicationContext(context.getApplicationContext());
        try {
            this.e = r.a(this.d, "android.permission.WRITE_SETTINGS");
            if (this.e && Build.VERSION.SDK_INT >= 23) {
                Method declaredMethod = Settings.System.class.getDeclaredMethod("canWrite", new Class[]{Context.class});
                declaredMethod.setAccessible(true);
                this.e = ((Boolean) declaredMethod.invoke((Object) null, new Object[]{this.d})).booleanValue();
            }
        } catch (Throwable th) {
            int i = this.b;
            this.b = i + 1;
            if (i < this.a) {
                th.printStackTrace();
            }
        }
    }

    public static g a(Context context) {
        if (c == null) {
            synchronized (g.class) {
                try {
                    if (c == null) {
                        c = new g(context);
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<g> cls = g.class;
                        throw th;
                    }
                }
            }
        }
        return c;
    }

    public String a(String str) {
        try {
            return Settings.System.getString(this.d.getContentResolver(), str);
        } catch (Throwable th) {
            int i = this.b;
            this.b = i + 1;
            if (i < this.a) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public boolean a(String str, String str2) {
        if (this.e) {
            try {
                return Settings.System.putString(this.d.getContentResolver(), str, str2);
            } catch (Throwable th) {
                int i = this.b;
                this.b = i + 1;
                if (i < this.a) {
                    th.printStackTrace();
                }
            }
        }
        return false;
    }
}
