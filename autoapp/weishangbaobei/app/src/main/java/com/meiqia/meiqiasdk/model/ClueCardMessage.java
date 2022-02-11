package com.meiqia.meiqiasdk.model;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ClueCardMessage extends BaseMessage {
    private JSONObject attrs = new JSONObject();
    private Map<String, Boolean> enableStateMap = new HashMap();

    public ClueCardMessage() {
        setItemViewType(11);
    }

    public JSONObject getAttrs() {
        return this.attrs;
    }

    public void setAttrs(JSONObject jSONObject) {
        this.attrs = jSONObject;
    }

    public void setEnable(String str, boolean z) {
        this.enableStateMap.put(str, Boolean.valueOf(z));
    }

    public boolean isEnable(String str) {
        Boolean bool = this.enableStateMap.get(str);
        return bool != null && bool.booleanValue();
    }

    public boolean isAllEnable() {
        for (Boolean booleanValue : this.enableStateMap.values()) {
            if (booleanValue.booleanValue()) {
                return true;
            }
        }
        return false;
    }
}
