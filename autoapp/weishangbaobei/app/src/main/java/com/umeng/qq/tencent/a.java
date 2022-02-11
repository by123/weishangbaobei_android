package com.umeng.qq.tencent;

import android.os.Handler;
import android.os.Message;

class a extends Handler {
    final /* synthetic */ AssistActivity a;

    a(AssistActivity assistActivity) {
        this.a = assistActivity;
    }

    public void handleMessage(Message message) {
        if (message.what == 0 && !this.a.isFinishing()) {
            this.a.finish();
        }
    }
}
