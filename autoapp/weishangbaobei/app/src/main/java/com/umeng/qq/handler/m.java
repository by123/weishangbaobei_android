package com.umeng.qq.handler;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;

class m implements Runnable {
    final /* synthetic */ UmengQQHandler a;

    m(UmengQQHandler umengQQHandler) {
        this.a = umengQQHandler;
    }

    public void run() {
        this.a.c.onError(SHARE_MEDIA.QQ, 0, new Throwable(UmengErrorCode.NotInstall.getMessage()));
    }
}
