package com.umeng.weixin.handler;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;
import java.util.Map;

class d implements Runnable {
    final /* synthetic */ UMAuthListener a;
    final /* synthetic */ Map b;
    final /* synthetic */ UmengWXHandler c;

    d(UmengWXHandler umengWXHandler, UMAuthListener uMAuthListener, Map map) {
        this.c = umengWXHandler;
        this.a = uMAuthListener;
        this.b = map;
    }

    public void run() {
        UMAuthListener uMAuthListener = this.a;
        SHARE_MEDIA share_media = SHARE_MEDIA.WEIXIN;
        uMAuthListener.onError(share_media, 2, new Throwable(UmengErrorCode.RequestForUserProfileFailed.getMessage() + ((String) this.b.get("errcode"))));
    }
}
