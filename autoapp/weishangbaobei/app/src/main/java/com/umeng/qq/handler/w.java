package com.umeng.qq.handler;

import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;

class w implements Runnable {
    final /* synthetic */ UMShareListener a;
    final /* synthetic */ String b;
    final /* synthetic */ UmengQZoneHandler c;

    w(UmengQZoneHandler umengQZoneHandler, UMShareListener uMShareListener, String str) {
        this.c = umengQZoneHandler;
        this.a = uMShareListener;
        this.b = str;
    }

    public void run() {
        UMShareListener uMShareListener = this.a;
        SHARE_MEDIA share_media = SHARE_MEDIA.QZONE;
        uMShareListener.onError(share_media, new Throwable(UmengErrorCode.ShareFailed.getMessage() + this.b));
    }
}
