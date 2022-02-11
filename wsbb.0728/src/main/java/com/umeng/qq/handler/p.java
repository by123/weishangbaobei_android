package com.umeng.qq.handler;

import android.text.TextUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.Map;

class p implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Map b;
    final /* synthetic */ n c;

    p(n nVar, String str, Map map) {
        this.c = nVar;
        this.a = str;
        this.b = map;
    }

    public void run() {
        if (TextUtils.isEmpty(this.a) || !this.a.equals("100030")) {
            UMAuthListener uMAuthListener = this.c.a;
            SHARE_MEDIA share_media = SHARE_MEDIA.QQ;
            uMAuthListener.onError(share_media, 2, new Throwable(UmengErrorCode.AuthorizeFailed + ((String) this.b.get(SocializeProtocolConstants.PROTOCOL_KEY_MSG))));
            return;
        }
        this.c.b.f();
        this.c.b.c(this.c.a);
    }
}
