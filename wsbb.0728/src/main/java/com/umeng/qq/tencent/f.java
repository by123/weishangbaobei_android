package com.umeng.qq.tencent;

import android.content.DialogInterface;

class f implements DialogInterface.OnCancelListener {
    final /* synthetic */ j a;
    final /* synthetic */ Object b;
    final /* synthetic */ c c;

    f(c cVar, j jVar, Object obj) {
        this.c = cVar;
        this.a = jVar;
        this.b = obj;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.a != null) {
            this.a.a(this.b);
        }
    }
}
