package com.umeng.qq.handler;

import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;

class v implements Runnable {
    final /* synthetic */ UMShareListener a;
    final /* synthetic */ UmengQZoneHandler b;

    v(UmengQZoneHandler umengQZoneHandler, UMShareListener uMShareListener) {
        this.b = umengQZoneHandler;
        this.a = uMShareListener;
    }

    public void run() {
        this.a.onError(SHARE_MEDIA.QZONE, new Throwable(UmengErrorCode.NotInstall.getMessage()));
    }
}
