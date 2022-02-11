package com.umeng.qq.tencent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.qq.handler.a;
import java.net.URLEncoder;
import java.util.ArrayList;

public class o extends h {
    public o(Context context, n nVar) {
        super(nVar);
    }

    private void b(Activity activity, Bundle bundle, j jVar) {
        StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_qzone?src_type=app&version=1&file_type=news");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("imageUrl");
        String string = bundle.getString("title");
        String string2 = bundle.getString("summary");
        String string3 = bundle.getString(a.h);
        String string4 = bundle.getString(a.j);
        int i = bundle.getInt(a.k, 1);
        String string5 = bundle.getString(a.i);
        int i2 = bundle.getInt(a.n, 0);
        String string6 = bundle.getString("share_qq_ext_str");
        String b = this.b.b();
        String d = this.b.d();
        if (stringArrayList != null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            int size = stringArrayList.size() > 9 ? 9 : stringArrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                stringBuffer2.append(URLEncoder.encode(stringArrayList.get(i3)));
                if (i3 != size - 1) {
                    stringBuffer2.append(";");
                }
            }
            stringBuffer.append("&image_url=" + Base64.encodeToString(l.e(stringBuffer2.toString()), 2));
        }
        if (!TextUtils.isEmpty(string)) {
            stringBuffer.append("&title=" + Base64.encodeToString(l.e(string), 2));
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append("&description=" + Base64.encodeToString(l.e(string2), 2));
        }
        if (!TextUtils.isEmpty(b)) {
            stringBuffer.append("&share_id=" + b);
        }
        if (!TextUtils.isEmpty(string3)) {
            stringBuffer.append("&url=" + Base64.encodeToString(l.e(string3), 2));
        }
        if (!TextUtils.isEmpty(string5)) {
            stringBuffer.append("&app_name=" + Base64.encodeToString(l.e(string5), 2));
        }
        if (!l.c(d)) {
            stringBuffer.append("&open_id=" + Base64.encodeToString(l.e(d), 2));
        }
        if (!l.c(string4)) {
            stringBuffer.append("&audioUrl=" + Base64.encodeToString(l.e(string4), 2));
        }
        stringBuffer.append("&req_type=" + Base64.encodeToString(l.e(String.valueOf(i)), 2));
        if (!l.c(string6)) {
            stringBuffer.append("&share_qq_ext_str=" + Base64.encodeToString(l.e(string6), 2));
        }
        stringBuffer.append("&cflag=" + Base64.encodeToString(l.e(String.valueOf(i2)), 2));
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringBuffer.toString()));
        intent.putExtra("pkg_name", activity.getPackageName());
        if (!l.a(activity, "4.6.0")) {
            p.a().a("shareToQzone", jVar);
            if (a(intent)) {
                a(activity, 10104, intent, false);
            }
        } else if (a(intent)) {
            p.a().a(11104, jVar);
            a(activity, intent, 11104);
        }
    }

    private void c(Activity activity, Bundle bundle, j jVar) {
        StringBuffer stringBuffer = new StringBuffer("mqqapi://qzone/publish?src_type=app&version=1&file_type=news");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("imageUrl");
        String string = bundle.getString("summary");
        int i = bundle.getInt(a.k, 3);
        String string2 = bundle.getString(a.i);
        String string3 = bundle.getString("videoPath");
        int i2 = bundle.getInt("videoDuration");
        long j = bundle.getLong("videoSize");
        String b = this.b.b();
        String d = this.b.d();
        if (3 == i && stringArrayList != null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            int size = stringArrayList.size();
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 >= size) {
                    break;
                }
                stringBuffer2.append(URLEncoder.encode(stringArrayList.get(i4)));
                if (i4 != size - 1) {
                    stringBuffer2.append(";");
                }
                i3 = i4 + 1;
            }
            stringBuffer.append("&image_url=" + Base64.encodeToString(l.e(stringBuffer2.toString()), 2));
        }
        if (4 == i) {
            stringBuffer.append("&videoPath=" + Base64.encodeToString(l.e(string3), 2));
            stringBuffer.append("&videoDuration=" + Base64.encodeToString(l.e(String.valueOf(i2)), 2));
            stringBuffer.append("&videoSize=" + Base64.encodeToString(l.e(String.valueOf(j)), 2));
        }
        if (!TextUtils.isEmpty(string)) {
            stringBuffer.append("&description=" + Base64.encodeToString(l.e(string), 2));
        }
        if (!TextUtils.isEmpty(b)) {
            stringBuffer.append("&share_id=" + b);
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append("&app_name=" + Base64.encodeToString(l.e(string2), 2));
        }
        if (!l.c(d)) {
            stringBuffer.append("&open_id=" + Base64.encodeToString(l.e(d), 2));
        }
        stringBuffer.append("&req_type=" + Base64.encodeToString(l.e(String.valueOf(i)), 2));
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringBuffer.toString()));
        intent.putExtra("pkg_name", activity.getPackageName());
        if (a(intent)) {
            a(activity, 10104, intent, false);
        }
    }

    public void a(Activity activity, Bundle bundle, j jVar) {
        String string = bundle.getString(a.q);
        if (string == null || !string.equals(a.r)) {
            b(activity, bundle, jVar);
        } else {
            c(activity, bundle, jVar);
        }
    }
}
