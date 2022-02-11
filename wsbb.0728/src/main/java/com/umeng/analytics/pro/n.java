package com.umeng.analytics.pro;

import android.content.Context;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.service.UMGlobalContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class n {
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private final long e;

    private static class a {
        public static final n a = new n();

        private a() {
        }
    }

    private n() {
        this.e = 60000;
    }

    public static n a() {
        return a.a;
    }

    private void a(JSONObject jSONObject, boolean z) {
        if (!z && jSONObject.has(b.n)) {
            jSONObject.remove(b.n);
        }
        if (jSONObject.has(b.K)) {
            jSONObject.remove(b.K);
        }
        if (jSONObject.has("error")) {
            jSONObject.remove("error");
        }
        if (jSONObject.has(b.R)) {
            jSONObject.remove(b.R);
        }
        if (jSONObject.has(b.S)) {
            jSONObject.remove(b.S);
        }
        if (jSONObject.has(b.K)) {
            jSONObject.remove(b.K);
        }
        if (jSONObject.has("userlevel")) {
            jSONObject.remove("userlevel");
        }
    }

    private JSONArray b() {
        JSONArray jSONArray = new JSONArray();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", u.a().a(UMGlobalContext.getAppContext((Context) null)));
            jSONObject.put(b.p, currentTimeMillis);
            jSONObject.put(b.q, currentTimeMillis + 60000);
            jSONObject.put("duration", 60000);
            jSONArray.put(jSONObject);
        } catch (JSONException e2) {
        }
        return jSONArray;
    }

    private JSONArray c() {
        JSONArray jSONArray = new JSONArray();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", u.a().d(UMGlobalContext.getAppContext((Context) null)));
            jSONObject.put(b.p, currentTimeMillis);
            jSONArray.put(jSONObject);
        } catch (JSONException e2) {
        }
        return jSONArray;
    }

    public int a(Context context) {
        return Integer.valueOf(UMEnvelopeBuild.imprintProperty(context, "defcon", String.valueOf(0))).intValue();
    }

    public void a(JSONObject jSONObject, Context context) {
        int a2 = a(context);
        if (a2 == 1) {
            a(jSONObject, true);
            g.a(context).b(false, true);
        } else if (a2 == 2) {
            jSONObject.remove(b.n);
            try {
                jSONObject.put(b.n, b());
            } catch (Exception e2) {
            }
            a(jSONObject, true);
            g.a(context).b(false, true);
        } else if (a2 == 3) {
            a(jSONObject, false);
            g.a(context).b(false, true);
        }
    }

    public void b(JSONObject jSONObject, Context context) {
        int a2 = a(context);
        if (a2 == 1) {
            if (jSONObject.has(b.K)) {
                jSONObject.remove(b.K);
            }
            if (jSONObject.has(b.n)) {
                try {
                    JSONArray jSONArray = jSONObject.getJSONArray(b.n);
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        if (jSONObject2.has(b.ar)) {
                            jSONObject2.remove(b.ar);
                        }
                        if (jSONObject2.has(b.as)) {
                            jSONObject2.remove(b.as);
                        }
                    }
                } catch (JSONException e2) {
                }
            }
            g.a(context).a(false, true);
        } else if (a2 == 2) {
            if (jSONObject.has(b.K)) {
                jSONObject.remove(b.K);
            }
            if (jSONObject.has(b.n)) {
                jSONObject.remove(b.n);
            }
            try {
                jSONObject.put(b.n, c());
            } catch (Exception e3) {
            }
            g.a(context).a(false, true);
        } else if (a2 == 3) {
            if (jSONObject.has(b.K)) {
                jSONObject.remove(b.K);
            }
            jSONObject.remove(b.n);
            g.a(context).a(false, true);
        }
    }
}