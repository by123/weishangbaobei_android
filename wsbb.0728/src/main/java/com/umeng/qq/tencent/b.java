package com.umeng.qq.tencent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.CookieSyncManager;
import com.umeng.socialize.utils.ContextUtil;
import java.lang.ref.WeakReference;

public class b extends h {
    private String g;
    /* access modifiers changed from: private */
    public WeakReference h;
    private j i;

    public b(n nVar) {
        super(nVar);
    }

    private int a(boolean z, j jVar) {
        CookieSyncManager.createInstance(ContextUtil.getContext());
        return 2;
    }

    private boolean a(Activity activity, boolean z) {
        String str;
        Intent a = a("com.tencent.open.agent.AgentActivity");
        if (a != null) {
            Bundle a2 = a();
            if (z) {
                a2.putString("isadd", "1");
            }
            a2.putString("scope", this.g);
            a2.putString("client_id", this.b.b());
            if (f) {
                str = "desktop_m_qq-" + d + "-" + "android" + "-" + c + "-" + e;
            } else {
                str = "openmobile_android";
            }
            a2.putString("pf", str);
            a2.putString("need_pay", "1");
            a2.putString("oauth_app_name", s.a(ContextUtil.getContext()));
            a.putExtra("key_action", "action_login");
            a.putExtra("key_params", a2);
            if (a(a)) {
                this.i = new c(this, this.i);
                p.a().a(11101, this.i);
                a(activity, a, 11101);
                return true;
            }
        }
        return false;
    }

    public int a(Activity activity, String str, j jVar, boolean z) {
        this.g = str;
        this.h = new WeakReference(activity);
        this.i = jVar;
        if (a(activity, z)) {
            return 1;
        }
        this.i = new c(this, this.i);
        return a(z, this.i);
    }
}
