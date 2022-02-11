package com.umeng.socialize.net;

import android.content.Context;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.utils.URequest;
import com.umeng.socialize.utils.SocializeUtils;

public class UrlRequest extends SocializeRequest {
    private static final String a = "/link/add/";
    private static final int d = 26;
    private String b;
    private String c;

    public UrlRequest(Context context, String str, String str2) {
        super(context, "", UrlResponse.class, 26, URequest.RequestMethod.POST);
        this.mContext = context;
        this.b = str2;
        this.c = str;
    }

    /* access modifiers changed from: protected */
    public String getPath() {
        return a + SocializeUtils.getAppkey(this.mContext) + "/";
    }

    public void onPrepareRequest() {
        super.onPrepareRequest();
        addStringParams("url", this.b);
        addStringParams("to", this.c);
    }
}
