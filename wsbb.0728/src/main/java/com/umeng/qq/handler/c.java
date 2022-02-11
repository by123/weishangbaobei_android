package com.umeng.qq.handler;

import com.umeng.socialize.Config;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;
import com.umeng.socialize.utils.UmengText;

class c implements Runnable {
    final /* synthetic */ UMShareListener a;
    final /* synthetic */ UmengQQHandler b;

    c(UmengQQHandler umengQQHandler, UMShareListener uMShareListener) {
        this.b = umengQQHandler;
        this.a = uMShareListener;
    }

    public void run() {
        UMShareListener uMShareListener = this.a;
        SHARE_MEDIA share_media = SHARE_MEDIA.QQ;
        uMShareListener.onError(share_media, new Throwable(UmengErrorCode.ShareFailed.getMessage() + UmengText.tencentEmpty(Config.isUmengQQ.booleanValue())));
    }
}
