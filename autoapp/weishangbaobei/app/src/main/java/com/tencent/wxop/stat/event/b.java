package com.tencent.wxop.stat.event;

import android.content.Context;
import com.tencent.wxop.stat.StatServiceImpl;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import java.util.Map;
import java.util.Properties;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends e {
    protected c a = new c();
    private long m = -1;

    public b(Context context, int i, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.a.a = str;
    }

    private void h() {
        Properties commonKeyValueForKVEvent;
        if (this.a.a != null && (commonKeyValueForKVEvent = StatServiceImpl.getCommonKeyValueForKVEvent(this.a.a)) != null && commonKeyValueForKVEvent.size() > 0) {
            if (this.a.c == null || this.a.c.length() == 0) {
                this.a.c = new JSONObject(commonKeyValueForKVEvent);
                return;
            }
            for (Map.Entry entry : commonKeyValueForKVEvent.entrySet()) {
                try {
                    this.a.c.put(entry.getKey().toString(), entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public EventType a() {
        return EventType.CUSTOM;
    }

    public void a(long j) {
        this.m = j;
    }

    public boolean a(JSONObject jSONObject) {
        String str;
        Object obj;
        jSONObject.put("ei", this.a.a);
        if (this.m > 0) {
            jSONObject.put(com.umeng.analytics.pro.b.V, this.m);
        }
        if (this.a.b == null) {
            h();
            str = "kv";
            obj = this.a.c;
        } else {
            str = "ar";
            obj = this.a.b;
        }
        jSONObject.put(str, obj);
        return true;
    }

    public c b() {
        return this.a;
    }
}
