package com.umeng.socialize.media;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.stub.StubApp;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.handler.SinaSimplyHandler;
import com.umeng.socialize.utils.Log;

public class WBShareCallBackActivity extends Activity implements WbShareCallback {
    protected SinaSimplyHandler a = null;
    private final String b = WBShareCallBackActivity.class.getSimpleName();

    static {
        StubApp.interface11(7909);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        UMShareAPI uMShareAPI = UMShareAPI.get(StubApp.getOrigApplicationContext(getApplicationContext()));
        String str = this.b;
        Log.e(str, "handleid=" + this.a);
        this.a = (SinaSimplyHandler) uMShareAPI.getHandler(SHARE_MEDIA.SINA);
        this.a.onCreate(this, PlatformConfig.getPlatform(SHARE_MEDIA.SINA));
        if (this.a.getmWeiboShareAPI() != null) {
            this.a.getmWeiboShareAPI().doResultIntent(intent, this);
        }
        finish();
    }

    public void onWbShareCancel() {
        if (this.a != null) {
            this.a.onCancel();
        }
    }

    public void onWbShareFail() {
        if (this.a != null) {
            this.a.onError();
        }
    }

    public void onWbShareSuccess() {
        if (this.a != null) {
            this.a.onSuccess();
        }
    }
}
