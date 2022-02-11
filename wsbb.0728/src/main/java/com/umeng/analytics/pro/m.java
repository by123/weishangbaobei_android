package com.umeng.analytics.pro;

import android.content.Context;
import com.umeng.commonsdk.debug.UMRTLog;
import org.json.JSONArray;
import org.json.JSONObject;

public class m {
    public static long a(JSONArray jSONArray) {
        return (long) jSONArray.toString().getBytes().length;
    }

    public static long a(JSONObject jSONObject) {
        return (long) jSONObject.toString().getBytes().length;
    }

    public static JSONObject a(Context context, long j, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (jSONObject.has("content")) {
                JSONObject jSONObject3 = jSONObject.getJSONObject("content");
                if (jSONObject3.has("analytics")) {
                    JSONObject jSONObject4 = jSONObject3.getJSONObject("analytics");
                    if (jSONObject4.has(b.R)) {
                        jSONObject4.remove(b.R);
                    }
                    if (jSONObject4.has(b.S)) {
                        jSONObject4.remove(b.S);
                    }
                    if (jSONObject4.has("error")) {
                        jSONObject4.remove("error");
                    }
                    jSONObject3.put("analytics", jSONObject4);
                }
                jSONObject2.put("content", jSONObject3);
                if (jSONObject.has("header")) {
                    jSONObject2.put("header", jSONObject.getJSONObject("header"));
                }
                if (a(jSONObject2) > j) {
                    try {
                        g.a(context).i();
                        g.a(context).h();
                        g.a(context).b(true, false);
                        g.a(context).a();
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> u-app packet overload !!! ");
                        return null;
                    } catch (Throwable th) {
                        return null;
                    }
                }
            }
            return jSONObject2;
        } catch (Throwable th2) {
            return jSONObject2;
        }
    }
}