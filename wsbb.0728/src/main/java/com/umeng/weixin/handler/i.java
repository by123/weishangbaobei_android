package com.umeng.weixin.handler;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import java.util.Map;

class i implements Runnable {
    final /* synthetic */ UMAuthListener a;
    final /* synthetic */ UmengWXHandler b;

    i(UmengWXHandler umengWXHandler, UMAuthListener uMAuthListener) {
        this.b = umengWXHandler;
        this.a = uMAuthListener;
    }

    public void run() {
        this.a.onComplete(SHARE_MEDIA.WEIXIN, 1, (Map<String, String>) null);
    }
}
