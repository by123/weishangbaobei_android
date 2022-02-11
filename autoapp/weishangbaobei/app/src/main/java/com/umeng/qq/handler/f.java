package com.umeng.qq.handler;

import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

class f implements Runnable {
    final /* synthetic */ UMShareListener a;
    final /* synthetic */ String b;
    final /* synthetic */ UmengQQHandler c;

    f(UmengQQHandler umengQQHandler, UMShareListener uMShareListener, String str) {
        this.c = umengQQHandler;
        this.a = uMShareListener;
        this.b = str;
    }

    public void run() {
        this.a.onError(SHARE_MEDIA.QQ, new Throwable(this.b));
    }
}
