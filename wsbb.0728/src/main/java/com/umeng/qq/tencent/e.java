package com.umeng.qq.tencent;

import android.app.Dialog;
import android.view.View;

class e extends g {
    final /* synthetic */ j a;
    final /* synthetic */ Object b;
    final /* synthetic */ c c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    e(c cVar, Dialog dialog, j jVar, Object obj) {
        super(cVar, dialog);
        this.c = cVar;
        this.a = jVar;
        this.b = obj;
    }

    public void onClick(View view) {
        if (this.d != null && this.d.isShowing()) {
            this.d.dismiss();
        }
        if (this.a != null) {
            this.a.a(this.b);
        }
    }
}
