package com.umeng.weixin.handler;

import android.os.Bundle;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.utils.SocializeUtils;
import java.util.Map;

class m implements Runnable {
    final /* synthetic */ StringBuilder a;
    final /* synthetic */ UMAuthListener b;
    final /* synthetic */ UmengWXHandler c;

    m(UmengWXHandler umengWXHandler, StringBuilder sb, UMAuthListener uMAuthListener) {
        this.c = umengWXHandler;
        this.a = sb;
        this.b = uMAuthListener;
    }

    public void run() {
        String a2 = r.a(this.a.toString());
        try {
            Map<String, String> jsonToMap = SocializeUtils.jsonToMap(a2);
            if (jsonToMap == null || jsonToMap.size() == 0) {
                Map unused = this.c.e();
            }
            Bundle a3 = this.c.d(a2);
            this.c.a(a3);
            this.c.uploadAuthData(a3);
            QueuedWork.runInMain(new n(this, jsonToMap));
        } catch (Exception unused2) {
        }
    }
}
