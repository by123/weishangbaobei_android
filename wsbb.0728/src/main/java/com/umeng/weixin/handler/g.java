package com.umeng.weixin.handler;

import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;
import com.umeng.socialize.utils.UmengText;

class g implements Runnable {
    final /* synthetic */ UMShareListener a;
    final /* synthetic */ UmengWXHandler b;

    g(UmengWXHandler umengWXHandler, UMShareListener uMShareListener) {
        this.b = umengWXHandler;
        this.a = uMShareListener;
    }

    public void run() {
        UMShareListener uMShareListener = this.a;
        SHARE_MEDIA a2 = this.b.f;
        uMShareListener.onError(a2, new Throwable(UmengErrorCode.ShareDataTypeIllegal.getMessage() + UmengText.WX_CIRCLE_NOT_SUPPORT_EMOJ));
    }
}
