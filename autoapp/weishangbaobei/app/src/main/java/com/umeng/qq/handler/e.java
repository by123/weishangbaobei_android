package com.umeng.qq.handler;

import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;

class e implements Runnable {
    final /* synthetic */ UMShareListener a;
    final /* synthetic */ UmengQQHandler b;

    e(UmengQQHandler umengQQHandler, UMShareListener uMShareListener) {
        this.b = umengQQHandler;
        this.a = uMShareListener;
    }

    public void run() {
        this.a.onError(SHARE_MEDIA.QQ, new Throwable(UmengErrorCode.NotInstall.getMessage()));
    }
}
