package com.umeng.qq.handler;

import com.umeng.socialize.Config;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;
import com.umeng.socialize.utils.UmengText;

class u implements Runnable {
    final /* synthetic */ UMShareListener a;
    final /* synthetic */ UmengQZoneHandler b;

    u(UmengQZoneHandler umengQZoneHandler, UMShareListener uMShareListener) {
        this.b = umengQZoneHandler;
        this.a = uMShareListener;
    }

    public void run() {
        UMShareListener uMShareListener = this.a;
        SHARE_MEDIA share_media = SHARE_MEDIA.QZONE;
        uMShareListener.onError(share_media, new Throwable(UmengErrorCode.ShareFailed.getMessage() + UmengText.tencentEmpty(Config.isUmengQQ.booleanValue())));
    }
}
