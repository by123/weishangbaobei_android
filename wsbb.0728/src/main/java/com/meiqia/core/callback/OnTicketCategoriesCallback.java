package com.meiqia.core.callback;

import org.json.JSONArray;

public interface OnTicketCategoriesCallback extends OnFailureCallBack {
    void onSuccess(JSONArray jSONArray);
}
