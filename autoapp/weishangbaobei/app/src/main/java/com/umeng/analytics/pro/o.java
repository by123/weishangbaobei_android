package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import com.stub.StubApp;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.pro.k;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.socialize.ShareContent;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: EventTracker */
public class o {
    private static final String a = "fs_lc_tl_uapp";
    private static final String f = "-1";
    private static Context g;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private JSONObject h;

    private o() {
        this.b = ShareContent.MINAPP_STYLE;
        this.c = 256;
        this.d = WXMediaMessage.DESCRIPTION_LENGTH_LIMIT;
        this.e = 10;
        this.h = null;
        try {
            if (this.h == null) {
                b(g);
            }
        } catch (Throwable unused) {
        }
    }

    /* compiled from: EventTracker */
    private static class a {
        /* access modifiers changed from: private */
        public static final o a = new o();

        private a() {
        }
    }

    public static o a(Context context) {
        if (g == null && context != null) {
            g = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        return a.a;
    }

    public void a(String str, String str2, long j, int i, String str3) {
        String str4;
        try {
            if (a(str)) {
                if (b(str2)) {
                    if (Arrays.asList(b.aD).contains(str)) {
                        MLog.e("key is " + str + ", please check key, illegal");
                        UMLog.aq(h.m, 0, "\\|");
                        return;
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("id", str);
                    jSONObject.put("ts", currentTimeMillis);
                    if (j > 0) {
                        jSONObject.put(b.V, j);
                    }
                    jSONObject.put("__t", g.a);
                    if (str2 == null) {
                        str2 = "";
                    }
                    jSONObject.put(str, str2);
                    if (UMGlobalContext.getInstance().isMainProcess(g)) {
                        str4 = u.a().d(UMGlobalContext.getAppContext(g));
                    } else {
                        str4 = u.a().a(UMGlobalContext.getAppContext(g), currentTimeMillis);
                    }
                    if (TextUtils.isEmpty(str4)) {
                        str4 = f;
                    }
                    jSONObject.put("__i", str4);
                    if (!TextUtils.isEmpty(str3)) {
                        try {
                            JSONObject jSONObject2 = new JSONObject(str3);
                            if (jSONObject2.length() > 0) {
                                jSONObject.put(b.ar, jSONObject2);
                            }
                        } catch (JSONException unused) {
                        }
                    }
                    jSONObject.put(b.ac, 0);
                    jSONObject.put(b.ad, UMGlobalContext.getInstance().getProcessName(g));
                    a();
                    if (this.h != null && this.h.has(str) && !((Boolean) this.h.get(str)).booleanValue()) {
                        jSONObject.put(b.X, 1);
                        this.h.put(str, true);
                        c(g);
                    }
                    UMWorkDispatch.sendEvent(g, k.a.a, CoreProtocol.getInstance(g), jSONObject);
                    return;
                }
            }
            UMLog.aq(h.l, 0, "\\|");
        } catch (Throwable unused2) {
        }
    }

    public void a(String str, Map<String, Object> map, long j, String str2) {
        String str3;
        try {
            if (!a(str)) {
                UMLog.aq(h.f, 0, "\\|");
            } else if (b(map)) {
                if (map.size() > 10) {
                    MLog.e("map size is " + map.size() + ", please check");
                } else if (Arrays.asList(b.aD).contains(str)) {
                    MLog.e("key is " + str + ", please check key, illegal");
                    UMLog.aq(h.b, 0, "\\|");
                } else {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("id", str);
                    jSONObject.put("ts", System.currentTimeMillis());
                    if (j > 0) {
                        jSONObject.put(b.V, j);
                    }
                    jSONObject.put("__t", g.a);
                    ULog.i("befort ekv map, event is " + jSONObject.toString());
                    for (Map.Entry next : map.entrySet()) {
                        if (!Arrays.asList(b.aD).contains(next.getKey())) {
                            Object value = next.getValue();
                            if (!(value instanceof String) && !(value instanceof Integer) && !(value instanceof Long) && !(value instanceof Short) && !(value instanceof Float)) {
                                if (!(value instanceof Double)) {
                                    if (!value.getClass().isArray()) {
                                        MLog.e("please check key or value, illegal type!");
                                        return;
                                    } else if (value instanceof int[]) {
                                        int[] iArr = (int[]) value;
                                        if (iArr.length > 10) {
                                            MLog.e("please check key or value, size overlength!");
                                            return;
                                        }
                                        JSONArray jSONArray = new JSONArray();
                                        for (int put : iArr) {
                                            jSONArray.put(put);
                                        }
                                        jSONObject.put((String) next.getKey(), jSONArray);
                                    } else if (value instanceof double[]) {
                                        double[] dArr = (double[]) value;
                                        if (dArr.length > 10) {
                                            MLog.e("please check key or value, size overlength!");
                                            return;
                                        }
                                        JSONArray jSONArray2 = new JSONArray();
                                        for (double put2 : dArr) {
                                            jSONArray2.put(put2);
                                        }
                                        jSONObject.put((String) next.getKey(), jSONArray2);
                                    } else if (value instanceof long[]) {
                                        long[] jArr = (long[]) value;
                                        if (jArr.length > 10) {
                                            MLog.e("please check key or value, size overlength!");
                                            return;
                                        }
                                        JSONArray jSONArray3 = new JSONArray();
                                        for (long put3 : jArr) {
                                            jSONArray3.put(put3);
                                        }
                                        jSONObject.put((String) next.getKey(), jSONArray3);
                                    } else if (value instanceof float[]) {
                                        float[] fArr = (float[]) value;
                                        if (fArr.length > 10) {
                                            MLog.e("please check key or value, size overlength!");
                                            return;
                                        }
                                        JSONArray jSONArray4 = new JSONArray();
                                        for (float f2 : fArr) {
                                            jSONArray4.put((double) f2);
                                        }
                                        jSONObject.put((String) next.getKey(), jSONArray4);
                                    } else if (value instanceof short[]) {
                                        short[] sArr = (short[]) value;
                                        if (sArr.length > 10) {
                                            MLog.e("please check key or value, size overlength!");
                                            return;
                                        }
                                        JSONArray jSONArray5 = new JSONArray();
                                        for (short put4 : sArr) {
                                            jSONArray5.put(put4);
                                        }
                                        jSONObject.put((String) next.getKey(), jSONArray5);
                                    } else if (value instanceof String[]) {
                                        String[] strArr = (String[]) value;
                                        if (strArr.length > 10) {
                                            MLog.e("please check key or value, size overlength!");
                                            return;
                                        }
                                        JSONArray jSONArray6 = new JSONArray();
                                        int i = 0;
                                        while (i < strArr.length) {
                                            if (strArr[i] == null) {
                                                MLog.e("please check array, null item!");
                                                return;
                                            } else if (b(strArr[i])) {
                                                jSONArray6.put(strArr[i]);
                                                i++;
                                            } else {
                                                return;
                                            }
                                        }
                                        jSONObject.put((String) next.getKey(), jSONArray6);
                                    } else {
                                        MLog.e("please check key or value, illegal type!");
                                        return;
                                    }
                                }
                            }
                            jSONObject.put((String) next.getKey(), value);
                        } else {
                            UMLog.aq(h.e, 0, "\\|");
                            return;
                        }
                    }
                    if (UMGlobalContext.getInstance().isMainProcess(g)) {
                        str3 = u.a().d(UMGlobalContext.getAppContext(g));
                    } else {
                        str3 = u.a().a(UMGlobalContext.getAppContext(g), jSONObject.getLong("ts"));
                    }
                    if (TextUtils.isEmpty(str3)) {
                        str3 = f;
                    }
                    jSONObject.put("__i", str3);
                    if (!TextUtils.isEmpty(str2)) {
                        try {
                            JSONObject jSONObject2 = new JSONObject(str2);
                            if (jSONObject2.length() > 0) {
                                jSONObject.put(b.ar, jSONObject2);
                            }
                        } catch (JSONException unused) {
                        }
                    }
                    jSONObject.put(b.ac, 0);
                    jSONObject.put(b.ad, UMGlobalContext.getInstance().getProcessName(g));
                    a();
                    if (this.h != null && this.h.has(str) && !((Boolean) this.h.get(str)).booleanValue()) {
                        jSONObject.put(b.X, 1);
                        this.h.put(str, true);
                        c(g);
                    }
                    ULog.i("----->>>>>ekv event json is " + jSONObject.toString());
                    UMWorkDispatch.sendEvent(g, k.a.a, CoreProtocol.getInstance(g), jSONObject);
                }
            }
        } catch (Throwable unused2) {
        }
    }

    public void a(String str, Map<String, Object> map, String str2) {
        try {
            if (a(str)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", str);
                jSONObject.put("ts", System.currentTimeMillis());
                jSONObject.put(b.V, 0);
                jSONObject.put("__t", g.b);
                ULog.i("befort gkv map, event is " + jSONObject.toString());
                Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
                for (int i = 0; i < 10 && it.hasNext(); i++) {
                    Map.Entry next = it.next();
                    if (!b.X.equals(next.getKey()) && !b.V.equals(next.getKey()) && !"id".equals(next.getKey()) && !"ts".equals(next.getKey())) {
                        Object value = next.getValue();
                        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
                            jSONObject.put((String) next.getKey(), value);
                        }
                    }
                }
                String d2 = u.a().d(UMGlobalContext.getAppContext(g));
                if (TextUtils.isEmpty(d2)) {
                    d2 = f;
                }
                jSONObject.put("__i", d2);
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(str2);
                        if (jSONObject2.length() > 0) {
                            jSONObject.put(b.ar, jSONObject2);
                        }
                    } catch (JSONException unused) {
                    }
                }
                jSONObject.put(b.ac, 0);
                jSONObject.put(b.ad, UMGlobalContext.getInstance().getProcessName(g));
                ULog.i("----->>>>>gkv event json is " + jSONObject.toString());
                UMWorkDispatch.sendEvent(g, k.a.b, CoreProtocol.getInstance(g), jSONObject);
            }
        } catch (Throwable unused2) {
        }
    }

    private void b(Context context) {
        try {
            String string = PreferenceWrapper.getDefault(context).getString(a, (String) null);
            if (!TextUtils.isEmpty(string)) {
                this.h = new JSONObject(string);
            }
            a();
        } catch (Exception unused) {
        }
    }

    private void a() {
        try {
            String imprintProperty = UMEnvelopeBuild.imprintProperty(g, "track_list", "");
            if (!TextUtils.isEmpty(imprintProperty)) {
                String[] split = imprintProperty.split("!");
                JSONObject jSONObject = new JSONObject();
                int i = 0;
                if (this.h != null) {
                    for (String subStr : split) {
                        String subStr2 = HelperUtils.subStr(subStr, ShareContent.MINAPP_STYLE);
                        if (this.h.has(subStr2)) {
                            jSONObject.put(subStr2, this.h.get(subStr2));
                        }
                    }
                }
                this.h = new JSONObject();
                if (split.length >= 10) {
                    while (i < 10) {
                        a(split[i], jSONObject);
                        i++;
                    }
                } else {
                    while (i < split.length) {
                        a(split[i], jSONObject);
                        i++;
                    }
                }
                c(g);
            }
        } catch (Exception unused) {
        }
    }

    private void a(String str, JSONObject jSONObject) throws JSONException {
        String subStr = HelperUtils.subStr(str, ShareContent.MINAPP_STYLE);
        if (jSONObject.has(subStr)) {
            a(subStr, ((Boolean) jSONObject.get(subStr)).booleanValue());
        } else {
            a(subStr, false);
        }
    }

    private void a(String str, boolean z) {
        try {
            if (!b.X.equals(str) && !b.V.equals(str) && !"id".equals(str) && !"ts".equals(str) && !this.h.has(str)) {
                this.h.put(str, z);
            }
        } catch (Exception unused) {
        }
    }

    private void c(Context context) {
        try {
            if (this.h != null) {
                PreferenceWrapper.getDefault(g).edit().putString(a, this.h.toString()).commit();
            }
        } catch (Throwable unused) {
        }
    }

    public void a(List<String> list) {
        if (list != null) {
            try {
                if (list.size() > 0) {
                    a();
                    if (this.h == null) {
                        this.h = new JSONObject();
                        int size = list.size();
                        int i = 0;
                        while (true) {
                            if (i >= size) {
                                break;
                            }
                            if (this.h != null) {
                                if (this.h.length() >= 5) {
                                    break;
                                }
                            } else {
                                this.h = new JSONObject();
                            }
                            String str = list.get(i);
                            if (!TextUtils.isEmpty(str)) {
                                a(HelperUtils.subStr(str, ShareContent.MINAPP_STYLE), false);
                            }
                            i++;
                        }
                        c(g);
                        return;
                    } else if (this.h.length() >= 5) {
                        MLog.d("already setFistLaunchEvent, igone.");
                        return;
                    } else {
                        int i2 = 0;
                        while (i2 < list.size()) {
                            if (this.h.length() >= 5) {
                                MLog.d(" add setFistLaunchEvent over.");
                                return;
                            } else {
                                a(HelperUtils.subStr(list.get(i2), ShareContent.MINAPP_STYLE), false);
                                i2++;
                            }
                        }
                        c(g);
                        return;
                    }
                }
            } catch (Exception unused) {
                return;
            }
        }
        UMLog.aq(h.aj, 0, "\\|");
    }

    private JSONObject a(Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry next : map.entrySet()) {
                try {
                    String str = (String) next.getKey();
                    if (str != null) {
                        String subStr = HelperUtils.subStr(str, ShareContent.MINAPP_STYLE);
                        Object value = next.getValue();
                        if (value != null) {
                            int i = 0;
                            if (value.getClass().isArray()) {
                                if (value instanceof int[]) {
                                    int[] iArr = (int[]) value;
                                    JSONArray jSONArray = new JSONArray();
                                    while (i < iArr.length) {
                                        jSONArray.put(iArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(subStr, jSONArray);
                                } else if (value instanceof double[]) {
                                    double[] dArr = (double[]) value;
                                    JSONArray jSONArray2 = new JSONArray();
                                    while (i < dArr.length) {
                                        jSONArray2.put(dArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(subStr, jSONArray2);
                                } else if (value instanceof long[]) {
                                    long[] jArr = (long[]) value;
                                    JSONArray jSONArray3 = new JSONArray();
                                    while (i < jArr.length) {
                                        jSONArray3.put(jArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(subStr, jSONArray3);
                                } else if (value instanceof float[]) {
                                    float[] fArr = (float[]) value;
                                    JSONArray jSONArray4 = new JSONArray();
                                    while (i < fArr.length) {
                                        jSONArray4.put((double) fArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(subStr, jSONArray4);
                                } else if (value instanceof short[]) {
                                    short[] sArr = (short[]) value;
                                    JSONArray jSONArray5 = new JSONArray();
                                    while (i < sArr.length) {
                                        jSONArray5.put(sArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(subStr, jSONArray5);
                                }
                            } else if (value instanceof List) {
                                List list = (List) value;
                                JSONArray jSONArray6 = new JSONArray();
                                while (i < list.size()) {
                                    Object obj = list.get(i);
                                    if ((obj instanceof String) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Short)) {
                                        jSONArray6.put(list.get(i));
                                    }
                                    i++;
                                }
                                if (jSONArray6.length() > 0) {
                                    jSONObject.put(subStr, jSONArray6);
                                }
                            } else if (value instanceof String) {
                                jSONObject.put(subStr, HelperUtils.subStr(value.toString(), 256));
                            } else {
                                if (!(value instanceof Long) && !(value instanceof Integer) && !(value instanceof Float) && !(value instanceof Double)) {
                                    if (!(value instanceof Short)) {
                                        MLog.e("The param has not support type. please check !");
                                    }
                                }
                                jSONObject.put(subStr, value);
                            }
                        }
                    }
                } catch (Exception e2) {
                    MLog.e((Throwable) e2);
                }
            }
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    private boolean a(String str) {
        if (str != null) {
            try {
                int length = str.trim().getBytes().length;
                if (length > 0 && length <= 128) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        MLog.e("key is " + str + ", please check key, illegal");
        return false;
    }

    private boolean b(String str) {
        if (str == null) {
            return true;
        }
        try {
            if (str.trim().getBytes().length <= 256) {
                return true;
            }
        } catch (Exception unused) {
        }
        MLog.e("value is " + str + ", please check value, illegal");
        return false;
    }

    private boolean c(String str) {
        if (str == null) {
            return true;
        }
        try {
            return str.trim().getBytes().length <= 1024;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean b(Map<String, Object> map) {
        if (map != null) {
            try {
                if (!map.isEmpty()) {
                    for (Map.Entry next : map.entrySet()) {
                        if (!a((String) next.getKey())) {
                            UMLog.aq(h.h, 0, "\\|");
                            return false;
                        } else if (next.getValue() == null) {
                            UMLog.aq(h.i, 0, "\\|");
                            return false;
                        } else if (next.getValue() instanceof String) {
                            if (b.aB.equals(next.getKey())) {
                                if (!c(next.getValue().toString())) {
                                    UMLog.aq(h.O, 0, "\\|");
                                    return false;
                                }
                            } else if (!b(next.getValue().toString())) {
                                UMLog.aq(h.j, 0, "\\|");
                                return false;
                            }
                        }
                    }
                    return true;
                }
            } catch (Exception unused) {
                return true;
            }
        }
        UMLog.aq(h.g, 0, "\\|");
        return false;
    }
}
