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

/* compiled from: AutoViewPageTracker */
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
                            String[] strArr = {name.substring(0, name.length() - 1)};
                            UMLog.aq(h.s, 0, "\\|", new String[]{"@"}, strArr, (String[]) null, (String[]) null);
                        }
                    }
                } catch (Throwable unused) {
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

        public void onActivityPaused(Activity activity) {
            if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION == MobclickAgent.PageMode.AUTO) {
                j.this.b(activity);
                b.a().i();
                j.this.b = false;
                try {
                    if (UMConfigure.isDebugLog()) {
                        String name = activity.getClass().getName();
                        if (!name.equals(b.a().f())) {
                            String str = h.r;
                            UMLog.aq(str, 0, "\\|", new String[]{"@"}, new String[]{name}, (String[]) null, (String[]) null);
                        }
                    }
                } catch (Throwable unused) {
                }
            }
        }
    };
    private final Map<String, Long> d = new HashMap();
    private Application g = null;
    private boolean h = false;

    public boolean a() {
        return this.h;
    }

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

    private void b(Context context) {
        if (!this.h) {
            this.h = true;
            if (this.g != null && Build.VERSION.SDK_INT >= 14) {
                this.g.registerActivityLifecycleCallbacks(this.c);
            }
        }
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
            } catch (Throwable unused) {
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

    /* access modifiers changed from: private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0083 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(android.app.Activity r10) {
        /*
            r9 = this;
            java.util.Map<java.lang.String, java.lang.Long> r0 = r9.d     // Catch:{ Throwable -> 0x008a }
            monitor-enter(r0)     // Catch:{ Throwable -> 0x008a }
            java.lang.String r1 = a     // Catch:{ all -> 0x0087 }
            if (r1 != 0) goto L_0x0027
            if (r10 == 0) goto L_0x0027
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0087 }
            r1.<init>()     // Catch:{ all -> 0x0087 }
            java.lang.String r2 = r10.getPackageName()     // Catch:{ all -> 0x0087 }
            r1.append(r2)     // Catch:{ all -> 0x0087 }
            java.lang.String r2 = "."
            r1.append(r2)     // Catch:{ all -> 0x0087 }
            java.lang.String r10 = r10.getLocalClassName()     // Catch:{ all -> 0x0087 }
            r1.append(r10)     // Catch:{ all -> 0x0087 }
            java.lang.String r10 = r1.toString()     // Catch:{ all -> 0x0087 }
            a = r10     // Catch:{ all -> 0x0087 }
        L_0x0027:
            java.lang.String r10 = a     // Catch:{ all -> 0x0087 }
            boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x0087 }
            r1 = 0
            if (r10 != 0) goto L_0x005a
            java.util.Map<java.lang.String, java.lang.Long> r10 = r9.d     // Catch:{ all -> 0x0087 }
            java.lang.String r3 = a     // Catch:{ all -> 0x0087 }
            boolean r10 = r10.containsKey(r3)     // Catch:{ all -> 0x0087 }
            if (r10 == 0) goto L_0x005a
            java.util.Map<java.lang.String, java.lang.Long> r10 = r9.d     // Catch:{ all -> 0x0087 }
            java.lang.String r1 = a     // Catch:{ all -> 0x0087 }
            java.lang.Object r10 = r10.get(r1)     // Catch:{ all -> 0x0087 }
            java.lang.Long r10 = (java.lang.Long) r10     // Catch:{ all -> 0x0087 }
            long r1 = r10.longValue()     // Catch:{ all -> 0x0087 }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0087 }
            r10 = 0
            long r3 = r3 - r1
            java.util.Map<java.lang.String, java.lang.Long> r10 = r9.d     // Catch:{ all -> 0x0087 }
            java.lang.String r5 = a     // Catch:{ all -> 0x0087 }
            r10.remove(r5)     // Catch:{ all -> 0x0087 }
            r7 = r1
            r1 = r3
            r3 = r7
            goto L_0x005b
        L_0x005a:
            r3 = r1
        L_0x005b:
            monitor-exit(r0)     // Catch:{ all -> 0x0087 }
            java.lang.Object r10 = f     // Catch:{ Throwable -> 0x008a }
            monitor-enter(r10)     // Catch:{ Throwable -> 0x008a }
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0083 }
            r0.<init>()     // Catch:{ Throwable -> 0x0083 }
            java.lang.String r5 = "page_name"
            java.lang.String r6 = a     // Catch:{ Throwable -> 0x0083 }
            r0.put(r5, r6)     // Catch:{ Throwable -> 0x0083 }
            java.lang.String r5 = "duration"
            r0.put(r5, r1)     // Catch:{ Throwable -> 0x0083 }
            java.lang.String r1 = "page_start"
            r0.put(r1, r3)     // Catch:{ Throwable -> 0x0083 }
            java.lang.String r1 = "type"
            r2 = 0
            r0.put(r1, r2)     // Catch:{ Throwable -> 0x0083 }
            org.json.JSONArray r1 = e     // Catch:{ Throwable -> 0x0083 }
            r1.put(r0)     // Catch:{ Throwable -> 0x0083 }
            goto L_0x0083
        L_0x0081:
            r0 = move-exception
            goto L_0x0085
        L_0x0083:
            monitor-exit(r10)     // Catch:{ all -> 0x0081 }
            goto L_0x008a
        L_0x0085:
            monitor-exit(r10)     // Catch:{ all -> 0x0081 }
            throw r0     // Catch:{ Throwable -> 0x008a }
        L_0x0087:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0087 }
            throw r10     // Catch:{ Throwable -> 0x008a }
        L_0x008a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.j.b(android.app.Activity):void");
    }
}
