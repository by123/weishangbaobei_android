package com.meiqia.core.bean;

import com.meiqia.core.a.f;
import org.json.JSONException;
import org.json.JSONObject;

public class MQClientEvent {
    private JSONObject metadata = new JSONObject();
    private String name;

    public JSONObject getEvent() {
        return this.metadata;
    }

    public String getName() {
        return this.name;
    }

    public void setEvent(String str, String str2) {
        try {
            this.metadata.put(str, str2);
        } catch (JSONException e) {
            f.a("set event error " + e.toString());
        }
    }

    public void setName(String str) {
        this.name = str;
    }
}
