package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.Map;

public class Bugly {
    public static final String SDK_IS_DEV = "false";
    private static boolean a = false;
    public static Context applicationContext = null;
    private static String[] b = {"BuglyCrashModule", "BuglyRqdModule", "BuglyBetaModule"};
    private static String[] c = {"BuglyRqdModule", "BuglyCrashModule", "BuglyBetaModule"};
    public static boolean enable = true;
    public static Boolean isDev;

    public static String getAppChannel() {
        byte[] bArr;
        synchronized (Bugly.class) {
            try {
                a b2 = a.b();
                if (b2 == null) {
                    return null;
                }
                if (TextUtils.isEmpty(b2.m)) {
                    p a2 = p.a();
                    if (a2 == null) {
                        String str = b2.m;
                        return str;
                    }
                    Map<String, byte[]> a3 = a2.a(556, (o) null, true);
                    if (!(a3 == null || (bArr = a3.get("app_channel")) == null)) {
                        String str2 = new String(bArr);
                        return str2;
                    }
                }
                String str3 = b2.m;
                return str3;
            } catch (Throwable th) {
                Class<Bugly> cls = Bugly.class;
                throw th;
            }
        }
    }

    public static void init(Context context, String str, boolean z) {
        init(context, str, z, (BuglyStrategy) null);
    }

    public static void init(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        synchronized (Bugly.class) {
            try {
                if (!a) {
                    a = true;
                    Context a2 = z.a(context);
                    applicationContext = a2;
                    if (a2 == null) {
                        Log.e(x.a, "init arg 'context' should not be null!");
                        return;
                    }
                    if (isDev()) {
                        b = c;
                    }
                    for (String str2 : b) {
                        if (str2.equals("BuglyCrashModule")) {
                            b.a((a) CrashModule.getInstance());
                        } else if (!str2.equals("BuglyBetaModule") && !str2.equals("BuglyRqdModule")) {
                            str2.equals("BuglyFeedbackModule");
                        }
                    }
                    b.a = enable;
                    b.a(applicationContext, str, z, buglyStrategy);
                }
            } catch (Throwable th) {
                Class<Bugly> cls = Bugly.class;
                throw th;
            }
        }
    }

    public static boolean isDev() {
        if (isDev == null) {
            isDev = Boolean.valueOf(Boolean.parseBoolean(SDK_IS_DEV.replace("@", "")));
        }
        return isDev.booleanValue();
    }
}
