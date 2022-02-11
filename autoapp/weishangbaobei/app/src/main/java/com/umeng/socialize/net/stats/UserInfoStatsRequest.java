package com.umeng.socialize.net.stats;

import android.content.Context;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.net.utils.URequest;

public class UserInfoStatsRequest extends StatsRequest {

    protected enum GetUserInfoLifecycle {
        START("getinfostart"),
        END("getinfoend");
        
        private String value;

        private GetUserInfoLifecycle(String str) {
            this.value = str;
        }

        public String toString() {
            return this.value;
        }
    }

    public UserInfoStatsRequest(Context context, Class<? extends SocializeReseponse> cls) {
        super(context, "", cls, 0, URequest.RequestMethod.GET);
    }

    /* access modifiers changed from: protected */
    public String getPath() {
        return this.mRequestPath;
    }

    /* access modifiers changed from: protected */
    public void setRequestPath(GetUserInfoLifecycle getUserInfoLifecycle) {
        addStringParams(PARAMS_STATS_TYPE, getUserInfoLifecycle.toString());
    }
}
