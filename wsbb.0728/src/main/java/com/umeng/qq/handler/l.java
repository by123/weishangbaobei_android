package com.umeng.qq.handler;

import com.umeng.socialize.Config;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;
import com.umeng.socialize.utils.UmengText;

class l implements Runnable {
    final /* synthetic */ UMAuthListener a;
    final /* synthetic */ UmengQQHandler b;

    l(UmengQQHandler umengQQHandler, UMAuthListener uMAuthListener) {
        this.b = umengQQHandler;
        this.a = uMAuthListener;
    }

    public void run() {
        UMAuthListener uMAuthListener = this.a;
        SHARE_MEDIA share_media = SHARE_MEDIA.QQ;
        uMAuthListener.onError(share_media, 0, new Throwable(UmengErrorCode.AuthorizeFailed.getMessage() + UmengText.tencentEmpty(Config.isUmengQQ.booleanValue())));
    }
}
