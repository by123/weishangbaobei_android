package com.umeng.socialize.net.stats;

import android.content.Context;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.net.utils.URequest;

public class AuthStatsRequest extends StatsRequest {

    protected enum AuthLifecycle {
        START("authstart"),
        END("authend");
        
        private String value;

        private AuthLifecycle(String str) {
            this.value = str;
        }

        public String toString() {
            return this.value;
        }
    }

    public AuthStatsRequest(Context context, Class<? extends SocializeReseponse> cls) {
        super(context, "", cls, 0, URequest.RequestMethod.GET);
    }

    /* access modifiers changed from: protected */
    public String getPath() {
        return this.mRequestPath;
    }

    /* access modifiers changed from: protected */
    public void setRequestPath(AuthLifecycle authLifecycle) {
        addStringParams(PARAMS_STATS_TYPE, authLifecycle.toString());
    }
}
