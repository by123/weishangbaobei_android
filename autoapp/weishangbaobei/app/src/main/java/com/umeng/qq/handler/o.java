package com.umeng.qq.handler;

import com.umeng.socialize.bean.SHARE_MEDIA;
import java.util.Map;

class o implements Runnable {
    final /* synthetic */ Map a;
    final /* synthetic */ n b;

    o(n nVar, Map map) {
        this.b = nVar;
        this.a = map;
    }

    public void run() {
        this.b.a.onComplete(SHARE_MEDIA.QQ, 2, this.a);
    }
}
