package com.umeng.qq.handler;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import java.util.Map;

class d implements Runnable {
    final /* synthetic */ UMAuthListener a;
    final /* synthetic */ UmengQQHandler b;

    d(UmengQQHandler umengQQHandler, UMAuthListener uMAuthListener) {
        this.b = umengQQHandler;
        this.a = uMAuthListener;
    }

    public void run() {
        this.a.onComplete(SHARE_MEDIA.QQ, 1, (Map<String, String>) null);
    }
}
