package com.umeng.qq.handler;

import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.media.SimpleShareContent;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.utils.UmengText;

public class t extends SimpleShareContent {
    public t(ShareContent shareContent) {
        super(shareContent);
    }

    private Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("summary", getText());
        bundle.putInt(a.k, 1);
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
                str2 = UmengText.SHARECONTENT_THUMB_ERROR;
            }
            String str3 = str2;
            str2 = video.getThumbImage().asFileImage().toString();
            str = str3;
        } else {
            str = UmengText.QQ_PERMISSION;
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", subString(objectSetTitle(video), 45));
        bundle.putString("summary", subString(objectSetDescription(video), 60));
        bundle.putString("imageUrl", "");
        bundle.putString(a.b, str2);
        bundle.putInt(a.k, 1);
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
                str2 = UmengText.SHARECONTENT_THUMB_ERROR;
            }
            String str3 = str2;
            str2 = umWeb.getThumbImage().asFileImage().toString();
            str = str3;
        } else {
            str = UmengText.QQ_PERMISSION;
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", subString(objectSetTitle(umWeb), 45));
        bundle.putString("summary", subString(objectSetDescription(umWeb), 60));
        bundle.putString("imageUrl", "");
        bundle.putString(a.b, str2);
        bundle.putInt(a.k, 1);
        bundle.putString(a.h, umWeb.toUrl());
        if (TextUtils.isEmpty(getUmWeb().toUrl())) {
            bundle.putString("error", UmengText.EMPTY_WEB_URL);
        }
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
                str2 = UmengText.SHARECONTENT_THUMB_ERROR;
            }
            String str3 = str2;
            str2 = music.getThumbImage().asFileImage().toString();
            str = str3;
        } else {
            str = UmengText.QQ_PERMISSION;
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", subString(objectSetTitle(music), 45));
        bundle.putString("summary", subString(objectSetDescription(music), 60));
        bundle.putString("imageUrl", "");
        bundle.putString(a.b, str2);
        bundle.putInt(a.k, 2);
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
        if (getImage() == null || getImage().asFileImage() == null) {
            str2 = UmengText.QQ_PERMISSION;
            str = null;
        } else {
            if (getUMImageScale(getImage()) <= 0) {
                str2 = UmengText.SHARECONTENT_IMAGE_ERROR;
            }
            str = getImage().asFileImage().toString();
        }
        bundle.putString("summary", getText());
        bundle.putString(a.b, str);
        bundle.putInt(a.k, 5);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("error", str2);
        }
        return bundle;
    }

    public Bundle a(boolean z, String str) {
        Bundle bundle;
        String str2;
        int i = 2;
        if (getmStyle() == 2 || getmStyle() == 3) {
            bundle = e();
        } else if (getmStyle() == 4) {
            bundle = d();
        } else if (getmStyle() == 16) {
            bundle = c();
        } else if (getmStyle() == 8) {
            bundle = b();
        } else {
            bundle = a();
            bundle.putString("error", UmengText.supportStyle(false, "text"));
        }
        if (z) {
            str2 = a.n;
        } else {
            str2 = a.n;
            i = 1;
        }
        bundle.putInt(str2, i);
        if (!TextUtils.isEmpty(str)) {
            bundle.putString(a.i, str);
        }
        return bundle;
    }
}
