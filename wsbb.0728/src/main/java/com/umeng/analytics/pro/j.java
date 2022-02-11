package com.umeng.analytics.pro;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.b;
import com.umeng.analytics.pro.c;
import com.umeng.analytics.pro.g;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class j {
    public static String a;
    private static JSONArray e = new JSONArray();
    private static Object f = new Object();
    boolean b = false;
    Application.ActivityLifecycleCallbacks c = new Application.ActivityLifecycleCallbacks() {
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
            if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION == MobclickAgent.PageMode.AUTO) {
                j.this.b(activity);
                b.a().i();
                j.this.b = false;
                try {
                    if (UMConfigure.isDebugLog()) {
                        String name = activity.getClass().getName();
                        if (!name.equals(b.a().f())) {
                            UMLog.aq(h.r, 0, "\\|", new String[]{"@"}, new String[]{name}, (String[]) null, (String[]) null);
                        }
                    }
                } catch (Throwable th) {
                }
            }
        }

        public void onActivityResumed(Activity activity) {
            if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.AUTO || activity == null) {
                return;
            }
            if (j.this.b) {
                j.this.b = false;
                if (!TextUtils.isEmpty(j.a)) {
                    String str = j.a;
                    if (!str.equals(activity.getPackageName() + "." + activity.getLocalClassName())) {
                        j.this.a(activity);
                        b.a().h();
                        return;
                    }
                    return;
                }
                j.a = activity.getPackageName() + "." + activity.getLocalClassName();
                return;
            }
            j.this.a(activity);
            b.a().h();
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
            if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION == MobclickAgent.PageMode.AUTO) {
                try {
                    if (UMConfigure.isDebugLog()) {
                        String name = activity.getClass().getName();
                        if (!name.equals(b.a().g())) {
                            String substring = name.substring(0, name.length() - 1);
                            UMLog.aq(h.s, 0, "\\|", new String[]{"@"}, new String[]{substring}, (String[]) null, (String[]) null);
                        }
                    }
                } catch (Throwable th) {
                }
            }
        }
    };
    private final Map<String, Long> d = new HashMap();
    private Application g = null;
    private boolean h = false;

    public j(Context context) {
        synchronized (this) {
            if (this.g == null && context != null) {
                if (context instanceof Activity) {
                    this.g = ((Activity) context).getApplication();
                } else if (context instanceof Application) {
                    this.g = (Application) context;
                }
                if (this.g != null) {
                    b(context);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(Activity activity) {
        a = activity.getPackageName() + "." + activity.getLocalClassName();
        synchronized (this.d) {
            this.d.put(a, Long.valueOf(System.currentTimeMillis()));
        }
    }

    public static void a(Context context) {
        String jSONArray;
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                synchronized (f) {
                    jSONArray = e.toString();
                    e = new JSONArray();
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put(c.d.a.c, new JSONArray(jSONArray));
                    g.a(context).a(q.a().c(), jSONObject, g.a.AUTOPAGE);
                }
            } catch (Throwable th) {
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public void b(Activity activity) {
        long j;
        long j2;
        try {
            synchronized (this.d) {
                if (a == null && activity != null) {
                    a = activity.getPackageName() + "." + activity.getLocalClassName();
                }
                if (TextUtils.isEmpty(a) || !this.d.containsKey(a)) {
                    j = 0;
                    j2 = 0;
                } else {
                    j = this.d.get(a).longValue();
                    long currentTimeMillis = System.currentTimeMillis();
                    this.d.remove(a);
                    j2 = currentTimeMillis - j;
                }
            }
            synchronized (f) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(b.u, a);
                    jSONObject.put("duration", j2);
                    jSONObject.put(b.w, j);
                    jSONObject.put("type", 0);
                    e.put(jSONObject);
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
    }

    private void b(Context context) {
        if (!this.h) {
            this.h = true;
            if (this.g != null && Build.VERSION.SDK_INT >= 14) {
                this.g.registerActivityLifecycleCallbacks(this.c);
            }
        }
    }

    public boolean a() {
        return this.h;
    }

    public void b() {
        this.h = false;
        if (this.g != null) {
            if (Build.VERSION.SDK_INT >= 14) {
                this.g.unregisterActivityLifecycleCallbacks(this.c);
            }
            this.g = null;
        }
    }

    public void c() {
        b((Activity) null);
        b();
    }
}
