package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.pro.g;
import com.umeng.analytics.pro.k;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.json.JSONArray;
import org.json.JSONObject;

public class r {
    private static final int b = 5;
    private static JSONArray c = new JSONArray();
    private static Object d = new Object();
    Stack<String> a = new Stack<>();
    private final Map<String, Long> e = new HashMap();

    public static void a(Context context) {
        String jSONArray;
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                synchronized (d) {
                    jSONArray = c.toString();
                    c = new JSONArray();
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("__a", new JSONArray(jSONArray));
                    if (jSONObject.length() > 0) {
                        g.a(context).a(q.a().c(), jSONObject, g.a.PAGE);
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public int a() {
        return 2;
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (UMConfigure.isDebugLog() && this.a.size() != 0) {
                UMLog.aq(h.F, 0, "\\|", new String[]{"@"}, new String[]{this.a.peek()}, (String[]) null, (String[]) null);
            }
            synchronized (this.e) {
                this.e.put(str, Long.valueOf(System.currentTimeMillis()));
                if (UMConfigure.isDebugLog()) {
                    this.a.push(str);
                }
            }
        }
    }

    public void b() {
        String str;
        synchronized (this.e) {
            long j = 0;
            str = null;
            for (Map.Entry next : this.e.entrySet()) {
                if (((Long) next.getValue()).longValue() > j) {
                    j = ((Long) next.getValue()).longValue();
                    str = (String) next.getKey();
                }
            }
        }
        if (str != null) {
            b(str);
        }
    }

    public void b(String str) {
        Long l;
        Context appContext;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.e.containsKey(str)) {
            synchronized (this.e) {
                l = this.e.get(str);
            }
            if (l != null) {
                if (UMConfigure.isDebugLog() && this.a.size() > 0 && str.equals(this.a.peek())) {
                    this.a.pop();
                }
                long currentTimeMillis = System.currentTimeMillis();
                long longValue = l.longValue();
                synchronized (d) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(b.u, str);
                        jSONObject.put("duration", currentTimeMillis - longValue);
                        jSONObject.put(b.w, l);
                        jSONObject.put("type", a());
                        c.put(jSONObject);
                        if (c.length() >= 5 && (appContext = UMGlobalContext.getAppContext((Context) null)) != null) {
                            UMWorkDispatch.sendEvent(appContext, k.a.c, CoreProtocol.getInstance(appContext), (Object) null);
                        }
                    } catch (Throwable th) {
                    }
                }
                if (UMConfigure.isDebugLog() && this.a.size() != 0) {
                    UMLog.aq(h.E, 0, "\\|", new String[]{"@"}, new String[]{str}, (String[]) null, (String[]) null);
                }
            }
        } else if (UMConfigure.isDebugLog() && this.a.size() == 0) {
            UMLog.aq(h.G, 0, "\\|", new String[]{"@"}, new String[]{str}, (String[]) null, (String[]) null);
        }
    }
}
