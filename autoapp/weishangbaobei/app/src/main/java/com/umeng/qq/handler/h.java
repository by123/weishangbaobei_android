package com.umeng.qq.handler;

import com.umeng.qq.tencent.r;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;

class h implements Runnable {
    final /* synthetic */ r a;
    final /* synthetic */ g b;

    h(g gVar, r rVar) {
        this.b = gVar;
        this.a = rVar;
    }

    public void run() {
        UMShareListener uMShareListener = this.b.a;
        SHARE_MEDIA share_media = SHARE_MEDIA.QQ;
        StringBuilder sb = new StringBuilder();
        sb.append(UmengErrorCode.ShareFailed.getMessage());
        sb.append(this.a == null ? "" : this.a.b);
        uMShareListener.onError(share_media, new Throwable(sb.toString()));
    }
}
