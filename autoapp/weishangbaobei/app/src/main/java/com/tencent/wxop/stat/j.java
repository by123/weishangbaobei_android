package com.tencent.wxop.stat;

import com.umeng.commonsdk.proguard.c;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

class j extends DefaultConnectionKeepAliveStrategy {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        long keepAliveDuration = j.super.getKeepAliveDuration(httpResponse, httpContext);
        return keepAliveDuration == -1 ? c.d : keepAliveDuration;
    }
}
