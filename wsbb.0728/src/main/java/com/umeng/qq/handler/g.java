package com.umeng.qq.handler;

import com.umeng.qq.tencent.j;
import com.umeng.qq.tencent.r;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork;

class g implements j {
    final /* synthetic */ UMShareListener a;
    final /* synthetic */ UmengQQHandler b;

    g(UmengQQHandler umengQQHandler, UMShareListener uMShareListener) {
        this.b = umengQQHandler;
        this.a = uMShareListener;
    }

    public void a(r rVar) {
        QueuedWork.runInMain(new h(this, rVar));
    }

    public void a(Object obj) {
        this.a.onResult(SHARE_MEDIA.QQ);
    }

    public void onCancel() {
        this.a.onCancel(SHARE_MEDIA.QQ);
    }
}
