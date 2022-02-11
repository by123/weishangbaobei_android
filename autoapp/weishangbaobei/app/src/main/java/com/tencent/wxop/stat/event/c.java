package com.tencent.wxop.stat.event;

import com.wx.assistants.utils.fileutil.ListUtils;
import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONObject;

public class c {
    public String a;
    public JSONArray b;
    public JSONObject c = null;

    public c() {
    }

    public c(String str, String[] strArr, Properties properties) {
        JSONObject jSONObject;
        this.a = str;
        if (properties != null) {
            jSONObject = new JSONObject(properties);
        } else if (strArr != null) {
            this.b = new JSONArray();
            for (String put : strArr) {
                this.b.put(put);
            }
            return;
        } else {
            jSONObject = new JSONObject();
        }
        this.c = jSONObject;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            return toString().equals(((c) obj).toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.a);
        sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        if (this.b != null) {
            sb.append(this.b.toString());
        }
        if (this.c != null) {
            sb.append(this.c.toString());
        }
        return sb.toString();
    }
}
