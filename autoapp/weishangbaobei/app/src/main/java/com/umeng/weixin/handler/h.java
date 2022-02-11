package com.umeng.weixin.handler;

import android.os.Bundle;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;

class h implements Runnable {
    final /* synthetic */ Bundle a;
    final /* synthetic */ UmengWXHandler b;

    h(UmengWXHandler umengWXHandler, Bundle bundle) {
        this.b = umengWXHandler;
        this.a = bundle;
    }

    public void run() {
        UMShareListener b2 = this.b.k;
        SHARE_MEDIA a2 = this.b.f;
        b2.onError(a2, new Throwable(UmengErrorCode.UnKnowCode.getMessage() + this.a.getString("error")));
    }
}
