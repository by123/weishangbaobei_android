package com.umeng.qq.tencent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.qq.handler.a;

public class m extends h {
    public m(Context context, n nVar) {
        super(nVar);
    }

    private void b(Activity activity, Bundle bundle, j jVar) {
        if (!TextUtils.isEmpty(bundle.getString("imageUrl"))) {
            jVar.a(new r(-1, "分享类型解析出问题，不能有url图片，请联系技术人员", ""));
        } else {
            c(activity, bundle, jVar);
        }
    }

    private void c(Activity activity, Bundle bundle, j jVar) {
        StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_fri?src_type=app&version=1&file_type=news");
        String string = bundle.getString("imageUrl");
        String string2 = bundle.getString("title");
        String string3 = bundle.getString("summary");
        String string4 = bundle.getString(a.h);
        String string5 = bundle.getString(a.j);
        int i = bundle.getInt(a.k, 1);
        int i2 = bundle.getInt(a.n, 0);
        String string6 = bundle.getString("share_qq_ext_str");
        String b = l.b((Context) activity);
        if (b == null) {
            b = bundle.getString(a.i);
        }
        String string7 = bundle.getString(a.b);
        String b2 = this.b.b();
        String d = this.b.d();
        if (!TextUtils.isEmpty(string)) {
            stringBuffer.append("&image_url=" + Base64.encodeToString(l.e(string), 2));
        }
        if (!TextUtils.isEmpty(string7)) {
            stringBuffer.append("&file_data=" + Base64.encodeToString(l.e(string7), 2));
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append("&title=" + Base64.encodeToString(l.e(string2), 2));
        }
        if (!TextUtils.isEmpty(string3)) {
            stringBuffer.append("&description=" + Base64.encodeToString(l.e(string3), 2));
        }
        if (!TextUtils.isEmpty(b2)) {
            stringBuffer.append("&share_id=" + b2);
        }
        if (!TextUtils.isEmpty(string4)) {
            stringBuffer.append("&url=" + Base64.encodeToString(l.e(string4), 2));
        }
        if (!TextUtils.isEmpty(b)) {
            if (b.length() > 20) {
                b = b.substring(0, 20) + "...";
            }
            stringBuffer.append("&app_name=" + Base64.encodeToString(l.e(b), 2));
        }
        if (!TextUtils.isEmpty(d)) {
            stringBuffer.append("&open_id=" + Base64.encodeToString(l.e(d), 2));
        }
        if (!TextUtils.isEmpty(string5)) {
            stringBuffer.append("&audioUrl=" + Base64.encodeToString(l.e(string5), 2));
        }
        stringBuffer.append("&req_type=" + Base64.encodeToString(l.e(String.valueOf(i)), 2));
        if (!TextUtils.isEmpty(string6)) {
            stringBuffer.append("&share_qq_ext_str=" + Base64.encodeToString(l.e(string6), 2));
        }
        stringBuffer.append("&cflag=" + Base64.encodeToString(l.e(String.valueOf(i2)), 2));
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringBuffer.toString()));
        intent.putExtra("pkg_name", activity.getPackageName());
        if (!l.a(activity, "4.6.0")) {
            p.a().a("shareToQQ", jVar);
            if (a(intent)) {
                a(activity, 10103, intent, true);
            }
        } else if (a(intent)) {
            p.a().a(11103, jVar);
            a(activity, intent, 11103);
        }
    }

    public void a(Activity activity, Bundle bundle, j jVar) {
        if (l.a(activity, "4.5.0")) {
            jVar.a(new r(-6, "低版本手Q不支持该项功能!", (String) null));
        } else if (l.a((Context) activity)) {
            b(activity, bundle, jVar);
        }
    }
}
