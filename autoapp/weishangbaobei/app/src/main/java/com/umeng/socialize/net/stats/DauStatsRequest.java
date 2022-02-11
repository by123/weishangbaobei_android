package com.umeng.socialize.net.stats;

import android.content.Context;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.net.utils.URequest;

public class DauStatsRequest extends StatsRequest {
    public DauStatsRequest(Context context, Class<? extends SocializeReseponse> cls) {
        super(context, "", cls, 0, URequest.RequestMethod.GET);
        addStringParams(PARAMS_STATS_TYPE, "dau");
    }

    /* access modifiers changed from: protected */
    public String getPath() {
        return this.mRequestPath;
    }
}
