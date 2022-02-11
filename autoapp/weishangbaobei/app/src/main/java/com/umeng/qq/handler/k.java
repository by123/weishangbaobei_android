package com.umeng.qq.handler;

import com.umeng.socialize.bean.SHARE_MEDIA;
import java.util.Map;

class k implements Runnable {
    final /* synthetic */ Map a;
    final /* synthetic */ j b;

    k(j jVar, Map map) {
        this.b = jVar;
        this.a = map;
    }

    public void run() {
        this.b.c.a.onComplete(SHARE_MEDIA.QQ, 0, this.a);
    }
}
