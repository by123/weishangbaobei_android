package com.umeng.qq.handler;

import android.os.Bundle;
import com.umeng.qq.tencent.j;
import com.umeng.qq.tencent.r;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.utils.SocializeUtils;

class i implements j {
    final /* synthetic */ UMAuthListener a;
    final /* synthetic */ UmengQQHandler b;

    i(UmengQQHandler umengQQHandler, UMAuthListener uMAuthListener) {
        this.b = umengQQHandler;
        this.a = uMAuthListener;
    }

    public void a(r rVar) {
        if (this.a != null) {
            UMAuthListener uMAuthListener = this.a;
            SHARE_MEDIA share_media = SHARE_MEDIA.QQ;
            uMAuthListener.onError(share_media, 0, new Throwable(UmengErrorCode.AuthorizeFailed.getMessage() + "==> errorCode = " + rVar.a + ", errorMsg = " + rVar.b + ", detail = " + rVar.c));
        }
    }

    public void a(Object obj) {
        SocializeUtils.safeCloseDialog(this.b.a);
        Bundle a2 = this.b.a(obj);
        if (this.b.p == null && this.b.getContext() != null) {
            s unused = this.b.p = new s(this.b.getContext(), SHARE_MEDIA.QQ.toString());
        }
        if (this.b.p != null) {
            this.b.p.a(a2).f();
        }
        QueuedWork.runInBack(new j(this, obj, a2), true);
    }

    public void onCancel() {
        if (this.a != null) {
            this.a.onCancel(SHARE_MEDIA.QQ, 0);
        }
    }
}
