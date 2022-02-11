package com.tencent.wxop.stat;

import java.util.List;

class k implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ h b;
    final /* synthetic */ i c;

    k(i iVar, List list, h hVar) {
        this.c = iVar;
        this.a = list;
        this.b = hVar;
    }

    public void run() {
        this.c.a((List<?>) this.a, this.b);
    }
}
