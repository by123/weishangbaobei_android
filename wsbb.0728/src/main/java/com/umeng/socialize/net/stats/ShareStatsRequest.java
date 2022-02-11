package com.umeng.socialize.net.stats;

import android.content.Context;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.net.utils.URequest;

public class ShareStatsRequest extends StatsRequest {

    protected enum ShareLifecycle {
        START("sharestart"),
        END("shareend");
        
        private String value;

        private ShareLifecycle(String str) {
            this.value = str;
        }

        public String toString() {
            return this.value;
        }
    }

    public ShareStatsRequest(Context context, Class<? extends SocializeReseponse> cls) {
        super(context, "", cls, 0, URequest.RequestMethod.GET);
    }

    /* access modifiers changed from: protected */
    public String getPath() {
        return this.mRequestPath;
    }

    /* access modifiers changed from: protected */
    public void setRequestPath(ShareLifecycle shareLifecycle) {
        addStringParams(PARAMS_STATS_TYPE, shareLifecycle.toString());
    }
}
