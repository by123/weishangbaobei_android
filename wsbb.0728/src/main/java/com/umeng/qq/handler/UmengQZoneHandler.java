package com.umeng.qq.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.qq.tencent.Tencent;
import com.umeng.qq.tencent.j;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.common.SocializeConstants;

public class UmengQZoneHandler extends b {
    private s o;

    public int getRequestCode() {
        return 10104;
    }

    public j getmShareListener(UMShareListener uMShareListener) {
        return new x(this, uMShareListener);
    }

    public boolean isInstall() {
        return this.mWeakAct.get() == null || ((Activity) this.mWeakAct.get()).isFinishing() || this.d.isSupportSSOLogin((Activity) this.mWeakAct.get());
    }

    public boolean isSupportAuth() {
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 10104) {
            Tencent.onActivityResultData(i, i2, intent, getmShareListener(this.e));
        }
    }

    public void onCreate(Context context, PlatformConfig.Platform platform) {
        super.onCreate(context, platform);
        this.o = new s(context, SHARE_MEDIA.QQ.toString());
    }

    public boolean share(ShareContent shareContent, UMShareListener uMShareListener) {
        Runnable wVar;
        y yVar = new y(shareContent);
        if (uMShareListener != null) {
            this.e = uMShareListener;
        }
        if (this.d == null) {
            wVar = new u(this, uMShareListener);
        } else {
            if (!isInstall()) {
                if (Config.isJumptoAppStore) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(SocializeConstants.DOWN_URL_QQ));
                    ((Activity) this.mWeakAct.get()).startActivity(intent);
                }
                QueuedWork.runInMain(new v(this, uMShareListener));
            }
            Bundle a = yVar.a(getShareConfig().getAppName());
            String string = a.getString("error");
            if (!TextUtils.isEmpty(string)) {
                wVar = new w(this, uMShareListener, string);
            } else {
                if (this.mWeakAct.get() != null && !((Activity) this.mWeakAct.get()).isFinishing()) {
                    this.d.shareToQzone((Activity) this.mWeakAct.get(), a, getmShareListener(this.e));
                }
                return false;
            }
        }
        QueuedWork.runInMain(wVar);
        return false;
    }
}
