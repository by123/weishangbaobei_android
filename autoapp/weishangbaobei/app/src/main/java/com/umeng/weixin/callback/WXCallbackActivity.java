package com.umeng.weixin.callback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.stub.StubApp;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;
import com.umeng.weixin.handler.UmengWXHandler;
import com.umeng.weixin.umengwx.a;
import com.umeng.weixin.umengwx.b;
import com.umeng.weixin.umengwx.e;

public abstract class WXCallbackActivity extends Activity implements e {
    protected UmengWXHandler a = null;
    private final String b = WXCallbackActivity.class.getSimpleName();

    /* access modifiers changed from: protected */
    public void a(Intent intent) {
        this.a.getWXApi().handleIntent(intent, this);
    }

    public void a(a aVar) {
        if (this.a != null) {
            this.a.getWXEventHandler().a(aVar);
        }
        finish();
    }

    public void a(b bVar) {
        if (!(this.a == null || bVar == null)) {
            try {
                this.a.getWXEventHandler().a(bVar);
            } catch (Exception unused) {
            }
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        UMShareAPI uMShareAPI = UMShareAPI.get(StubApp.getOrigApplicationContext(getApplicationContext()));
        Log.um("WXCallbackActivity");
        this.a = (UmengWXHandler) uMShareAPI.getHandler(SHARE_MEDIA.WEIXIN);
        this.a.onCreate(StubApp.getOrigApplicationContext(getApplicationContext()), PlatformConfig.getPlatform(SHARE_MEDIA.WEIXIN));
        a(getIntent());
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.a = (UmengWXHandler) UMShareAPI.get(StubApp.getOrigApplicationContext(getApplicationContext())).getHandler(SHARE_MEDIA.WEIXIN);
        String str = this.b;
        Log.e(str, "handleid=" + this.a);
        this.a.onCreate(StubApp.getOrigApplicationContext(getApplicationContext()), PlatformConfig.getPlatform(SHARE_MEDIA.WEIXIN));
        a(intent);
    }
}
