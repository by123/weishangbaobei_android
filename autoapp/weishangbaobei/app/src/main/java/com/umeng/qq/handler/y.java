package com.umeng.qq.handler;

import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.media.SimpleShareContent;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.utils.UmengText;
import java.util.ArrayList;

public class y extends SimpleShareContent {
    public ArrayList a = new ArrayList();

    public y(ShareContent shareContent) {
        super(shareContent);
    }

    private Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("summary", getText());
        return bundle;
    }

    private Bundle b() {
        String str;
        UMVideo video = getVideo();
        String str2 = null;
        if (video.getThumbImage() == null) {
            str = null;
        } else if (video.getThumbImage().asFileImage() != null) {
            if (getUMImageScale(video.getThumbImage()) <= 0) {
                str2 = UmengText.SHARECONTENT_IMAGE_ERROR;
            }
            String str3 = str2;
            str2 = video.getThumbImage().asFileImage().toString();
            str = str3;
        } else {
            str = UmengText.QQ_PERMISSION;
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", subString(objectSetTitle(video), 200));
        bundle.putString("summary", subString(objectSetDescription(video), 600));
        bundle.putString(a.b, str2);
        if (!TextUtils.isEmpty(str2)) {
            this.a.clear();
            this.a.add(str2);
        }
        bundle.putStringArrayList("imageUrl", this.a);
        bundle.putString(a.h, video.toUrl());
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("error", str);
        }
        return bundle;
    }

    private Bundle c() {
        String str;
        UMWeb umWeb = getUmWeb();
        String str2 = null;
        if (umWeb.getThumbImage() == null) {
            str = null;
        } else if (umWeb.getThumbImage().asFileImage() != null) {
            if (getUMImageScale(umWeb.getThumbImage()) <= 0) {
                str2 = UmengText.SHARECONTENT_IMAGE_ERROR;
            }
            String str3 = str2;
            str2 = umWeb.getThumbImage().asFileImage().toString();
            str = str3;
        } else {
            str = UmengText.QQ_PERMISSION;
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", subString(objectSetTitle(umWeb), 200));
        bundle.putString("summary", subString(objectSetDescription(umWeb), 600));
        bundle.putString(a.b, str2);
        if (!TextUtils.isEmpty(str2)) {
            this.a.clear();
            this.a.add(str2);
        }
        bundle.putStringArrayList("imageUrl", this.a);
        bundle.putString(a.h, umWeb.toUrl());
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("error", str);
        }
        return bundle;
    }

    private Bundle d() {
        String str;
        UMusic music = getMusic();
        String str2 = null;
        if (music.getThumbImage() == null) {
            str = null;
        } else if (music.getThumbImage().asFileImage() != null) {
            if (getUMImageScale(music.getThumbImage()) <= 0) {
                str2 = UmengText.SHARECONTENT_IMAGE_ERROR;
            }
            String str3 = str2;
            str2 = music.getThumbImage().asFileImage().toString();
            str = str3;
        } else {
            str = UmengText.QQ_PERMISSION;
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", subString(objectSetTitle(music), 200));
        bundle.putString("summary", subString(objectSetDescription(music), 600));
        bundle.putString(a.b, str2);
        if (!TextUtils.isEmpty(str2)) {
            this.a.clear();
            this.a.add(str2);
        }
        bundle.putStringArrayList("imageUrl", this.a);
        bundle.putString(a.h, music.getmTargetUrl());
        bundle.putString(a.j, music.toUrl());
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("error", str);
        }
        return bundle;
    }

    private Bundle e() {
        String str;
        Bundle bundle = new Bundle();
        String str2 = null;
        if (getImage().asFileImage() != null) {
            if (getUMImageScale(getImage()) <= 0) {
                str2 = UmengText.SHARECONTENT_IMAGE_ERROR;
            }
            str = getImage().asFileImage().toString();
        } else {
            str2 = UmengText.QQ_PERMISSION;
            str = null;
        }
        bundle.putString("summary", getText());
        bundle.putString(a.b, str);
        if (!TextUtils.isEmpty(str)) {
            this.a.clear();
            this.a.add(str);
        }
        bundle.putStringArrayList("imageUrl", this.a);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("error", str2);
        }
        return bundle;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.Bundle a(java.lang.String r4) {
        /*
            r3 = this;
            int r0 = r3.getmStyle()
            r1 = 2
            if (r0 == r1) goto L_0x003e
            int r0 = r3.getmStyle()
            r1 = 3
            if (r0 != r1) goto L_0x000f
            goto L_0x003e
        L_0x000f:
            int r0 = r3.getmStyle()
            r1 = 4
            if (r0 != r1) goto L_0x001f
            android.os.Bundle r0 = r3.d()
        L_0x001a:
            java.lang.String r1 = "umeng_type"
            java.lang.String r2 = "qzone"
            goto L_0x0046
        L_0x001f:
            int r0 = r3.getmStyle()
            r1 = 16
            if (r0 != r1) goto L_0x002c
            android.os.Bundle r0 = r3.c()
            goto L_0x001a
        L_0x002c:
            int r0 = r3.getmStyle()
            r1 = 8
            if (r0 != r1) goto L_0x0039
            android.os.Bundle r0 = r3.b()
            goto L_0x001a
        L_0x0039:
            android.os.Bundle r0 = r3.a()
            goto L_0x0042
        L_0x003e:
            android.os.Bundle r0 = r3.e()
        L_0x0042:
            java.lang.String r1 = "umeng_type"
            java.lang.String r2 = "shuoshuo"
        L_0x0046:
            r0.putString(r1, r2)
            boolean r1 = android.text.TextUtils.isEmpty(r4)
            if (r1 != 0) goto L_0x0054
            java.lang.String r1 = "appName"
            r0.putString(r1, r4)
        L_0x0054:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.qq.handler.y.a(java.lang.String):android.os.Bundle");
    }
}
