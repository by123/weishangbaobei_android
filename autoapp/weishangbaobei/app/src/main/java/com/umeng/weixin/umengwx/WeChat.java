package com.umeng.weixin.umengwx;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.constants.ConstantsAPI;

public class WeChat {
    private Context a;
    private String b;
    private boolean c = false;

    public WeChat(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public final boolean handleIntent(Intent intent, e eVar) {
        try {
            switch (intent.getIntExtra("_wxapi_command_type", 0)) {
                case 1:
                    eVar.a((b) new i(intent.getExtras()));
                    return true;
                case 2:
                    eVar.a((b) new k(intent.getExtras()));
                    return true;
                case 3:
                case 4:
                case 5:
                    return true;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public final boolean isWXAppInstalled() {
        try {
            return this.a.getPackageManager().getPackageInfo("com.tencent.mm", 64) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public final boolean isWXAppSupportAPI() {
        return true;
    }

    public final boolean launchShare(Bundle bundle) {
        if (this.a == null) {
            return false;
        }
        Intent intent = new Intent();
        intent.setClassName("com.tencent.mm", "com.tencent.mm.plugin.base.stub.WXEntryActivity");
        String packageName = this.a.getPackageName();
        intent.putExtras(bundle);
        intent.putExtra(ConstantsAPI.SDK_VERSION, Build.MINIPROGRAM_SUPPORTED_SDK_INT);
        intent.putExtra(ConstantsAPI.APP_PACKAGE, packageName);
        intent.putExtra(ConstantsAPI.CONTENT, "weixin://sendreq?appid=" + this.b);
        intent.putExtra(ConstantsAPI.CHECK_SUM, j.a("weixin://sendreq?appid=" + this.b, Build.MINIPROGRAM_SUPPORTED_SDK_INT, packageName));
        intent.addFlags(268435456).addFlags(134217728);
        try {
            this.a.startActivity(intent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public final void pushare(Bundle bundle) {
        launchShare(bundle);
    }

    public final boolean registerApp(String str) {
        if (!this.c) {
            if (str != null) {
                this.b = str;
            }
            if (this.a == null) {
                return false;
            }
            Intent intent = new Intent(ConstantsAPI.ACTION_HANDLE_APP_REGISTER);
            String packageName = this.a.getPackageName();
            intent.putExtra(ConstantsAPI.SDK_VERSION, Build.SCAN_QRCODE_AUTH_SUPPORTED_SDK_INT);
            intent.putExtra(ConstantsAPI.APP_PACKAGE, packageName);
            intent.putExtra(ConstantsAPI.CONTENT, "weixin://registerapp?appid=" + this.b);
            intent.putExtra(ConstantsAPI.CHECK_SUM, j.a("weixin://registerapp?appid=" + this.b, Build.SCAN_QRCODE_AUTH_SUPPORTED_SDK_INT, packageName));
            this.a.sendBroadcast(intent, ConstantsAPI.WXApp.WXAPP_BROADCAST_PERMISSION);
            return true;
        }
        throw new IllegalStateException("registerApp fail, WXMsgImpl has been detached");
    }

    public final boolean sendReq(a aVar) {
        if (!aVar.b()) {
            return false;
        }
        Bundle bundle = new Bundle();
        aVar.a(bundle);
        launchShare(bundle);
        return true;
    }
}
