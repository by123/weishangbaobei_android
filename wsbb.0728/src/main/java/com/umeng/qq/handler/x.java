package com.umeng.qq.handler;

import com.umeng.qq.tencent.j;
import com.umeng.qq.tencent.r;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;

class x implements j {
    final /* synthetic */ UMShareListener a;
    final /* synthetic */ UmengQZoneHandler b;

    x(UmengQZoneHandler umengQZoneHandler, UMShareListener uMShareListener) {
        this.b = umengQZoneHandler;
        this.a = uMShareListener;
    }

    public void a(r rVar) {
        if (this.a != null) {
            UMShareListener uMShareListener = this.a;
            SHARE_MEDIA share_media = SHARE_MEDIA.QZONE;
            uMShareListener.onError(share_media, new Throwable(UmengErrorCode.ShareFailed.getMessage() + rVar.b));
        }
    }

    public void a(Object obj) {
        if (this.a != null) {
            this.a.onResult(SHARE_MEDIA.QZONE);
        }
    }

    public void onCancel() {
        if (this.a != null) {
            this.a.onCancel(SHARE_MEDIA.QZONE);
        }
    }
}
