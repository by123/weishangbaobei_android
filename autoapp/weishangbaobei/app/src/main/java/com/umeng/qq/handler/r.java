package com.umeng.qq.handler;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import java.util.Map;

class r implements UMAuthListener {
    final /* synthetic */ UMAuthListener a;
    final /* synthetic */ UmengQQHandler b;

    r(UmengQQHandler umengQQHandler, UMAuthListener uMAuthListener) {
        this.b = umengQQHandler;
        this.a = uMAuthListener;
    }

    public void onCancel(SHARE_MEDIA share_media, int i) {
        this.a.onCancel(SHARE_MEDIA.QQ, 2);
    }

    public void onComplete(SHARE_MEDIA share_media, int i, Map map) {
        this.b.b(this.a);
    }

    public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
        this.a.onError(SHARE_MEDIA.QQ, 2, th);
    }

    public void onStart(SHARE_MEDIA share_media) {
    }
}
