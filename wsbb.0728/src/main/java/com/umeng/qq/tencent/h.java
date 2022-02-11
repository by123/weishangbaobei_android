package com.umeng.qq.tencent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import com.stub.StubApp;
import com.umeng.commonsdk.proguard.e;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.ContextUtil;

public class h {
    public static String c;
    public static String d;
    public static String e;
    public static boolean f;
    protected k a;
    protected n b;

    public h(k kVar, n nVar) {
        this.a = kVar;
        this.b = nVar;
    }

    public h(n nVar) {
        this((k) null, nVar);
    }

    private Intent a(Activity activity, Intent intent) {
        Intent intent2 = new Intent(StubApp.getOrigApplicationContext(activity.getApplicationContext()), AssistActivity.class);
        intent2.putExtra("is_login", true);
        intent2.putExtra(AssistActivity.EXTRA_INTENT, intent);
        return intent2;
    }

    /* access modifiers changed from: protected */
    public Intent a(String str) {
        Intent intent = new Intent();
        if (l.c(ContextUtil.getContext())) {
            intent.setClassName("com.tencent.minihd.qq", str);
            if (s.a(ContextUtil.getContext(), intent)) {
                return intent;
            }
        }
        intent.setClassName("com.tencent.mobileqq", str);
        if (s.a(ContextUtil.getContext(), intent)) {
            return intent;
        }
        intent.setClassName("com.tencent.tim", str);
        if (!s.a(ContextUtil.getContext(), intent)) {
            return null;
        }
        return intent;
    }

    /* access modifiers changed from: protected */
    public Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("format", "json");
        bundle.putString("status_os", Build.VERSION.RELEASE);
        bundle.putString("status_machine", Build.MODEL);
        bundle.putString("status_version", Build.VERSION.SDK);
        bundle.putString(SocializeProtocolConstants.PROTOCOL_KEY_VERSION, "3.1.0.lite");
        bundle.putString("sdkp", e.al);
        if (this.b != null && this.b.a()) {
            bundle.putString("access_token", this.b.c());
            bundle.putString("oauth_consumer_key", this.b.b());
            bundle.putString("openid", this.b.d());
            bundle.putString("appid_for_getting_config", this.b.b());
        }
        SharedPreferences sharedPreferences = ContextUtil.getContext().getSharedPreferences("pfStore", 0);
        if (f) {
            bundle.putString("pf", "desktop_m_qq-" + d + "-" + "android" + "-" + c + "-" + e);
        } else {
            bundle.putString("pf", sharedPreferences.getString("pf", "openmobile_android"));
        }
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void a(Activity activity, int i, Intent intent, Boolean bool) {
        Intent intent2 = new Intent(StubApp.getOrigApplicationContext(activity.getApplicationContext()), AssistActivity.class);
        if (bool.booleanValue()) {
            intent2.putExtra("is_qq_mobile_share", true);
        }
        intent2.putExtra(AssistActivity.EXTRA_INTENT, intent);
        activity.startActivityForResult(intent2, i);
    }

    /* access modifiers changed from: protected */
    public void a(Activity activity, Intent intent, int i) {
        intent.putExtra("key_request_code", i);
        activity.startActivityForResult(a(activity, intent), i);
    }

    /* access modifiers changed from: protected */
    public boolean a(Intent intent) {
        if (intent != null) {
            return s.a(ContextUtil.getContext(), intent);
        }
        return false;
    }
}
