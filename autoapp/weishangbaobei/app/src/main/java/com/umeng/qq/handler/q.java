package com.umeng.qq.handler;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;
import org.json.JSONException;

class q implements Runnable {
    final /* synthetic */ JSONException a;
    final /* synthetic */ n b;

    q(n nVar, JSONException jSONException) {
        this.b = nVar;
        this.a = jSONException;
    }

    public void run() {
        UMAuthListener uMAuthListener = this.b.a;
        SHARE_MEDIA share_media = SHARE_MEDIA.QQ;
        uMAuthListener.onError(share_media, 2, new Throwable(UmengErrorCode.RequestForUserProfileFailed.getMessage() + this.a.getMessage()));
    }
}
