package com.umeng.qq.tencent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.socialize.utils.DeviceConfig;

public class AuthActivity extends Activity {
    public static final String ACTION_KEY = "action";
    public static final String ACTION_SHARE_PRIZE = "sharePrize";
    private static int a;

    static {
        StubApp.interface11(7772);
    }

    private void a(Uri uri) {
        String string;
        Intent intent;
        String str;
        if (!(uri == null || uri.toString() == null || uri.toString().equals(""))) {
            String uri2 = uri.toString();
            Bundle a2 = l.a(uri2.substring(uri2.indexOf("#") + 1));
            if (!(a2 == null || (string = a2.getString(ACTION_KEY)) == null)) {
                if (string.equals("shareToQQ") || string.equals("shareToQzone") || string.equals("sendToMyComputer") || string.equals("shareToTroopBar")) {
                    if (string.equals("shareToQzone") && DeviceConfig.getAppVersion("com.tencent.mobileqq", this) != null && s.a((Context) this, "5.2.0") < 0) {
                        a++;
                        if (a == 2) {
                            a = 0;
                            finish();
                            return;
                        }
                    }
                    intent = new Intent(this, AssistActivity.class);
                    intent.putExtras(a2);
                    intent.setFlags(603979776);
                } else if (string.equals("addToQQFavorites")) {
                    Intent intent2 = getIntent();
                    intent2.putExtras(a2);
                    intent2.putExtra("key_action", "action_share");
                    j a3 = p.a().a(string);
                    if (a3 != null) {
                        p.a().a(intent2, a3);
                    }
                } else if (string.equals(ACTION_SHARE_PRIZE)) {
                    intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
                    try {
                        str = l.b(a2.getString("response")).getString("activityid");
                    } catch (Exception unused) {
                        str = "";
                    }
                    if (!TextUtils.isEmpty(str)) {
                        intent.putExtra(ACTION_SHARE_PRIZE, true);
                        Bundle bundle = new Bundle();
                        bundle.putString("activityid", str);
                        intent.putExtras(bundle);
                    }
                }
                startActivity(intent);
            }
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);
}
