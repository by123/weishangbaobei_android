package com.umeng.weixin.handler;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.UmengErrorCode;

class j implements Runnable {
    final /* synthetic */ UMAuthListener a;
    final /* synthetic */ UmengWXHandler b;

    j(UmengWXHandler umengWXHandler, UMAuthListener uMAuthListener) {
        this.b = umengWXHandler;
        this.a = uMAuthListener;
    }

    public void run() {
        this.a.onError(this.b.f, 0, new Throwable(UmengErrorCode.NotInstall.getMessage()));
    }
}
