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
        String str2;
        String str3 = null;
        UMVideo video = getVideo();
        if (video.getThumbImage() == null) {
            str = null;
            str2 = null;
        } else if (video.getThumbImage().asFileImage() != null) {
            if (getUMImageScale(video.getThumbImage()) <= 0) {
                str3 = UmengText.SHARECONTENT_IMAGE_ERROR;
            }
            str = video.getThumbImage().asFileImage().toString();
            str2 = str3;
        } else {
            str2 = UmengText.QQ_PERMISSION;
            str = null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", subString(objectSetTitle(video), 200));
        bundle.putString("summary", subString(objectSetDescription(video), 600));
        bundle.putString(a.b, str);
        if (!TextUtils.isEmpty(str)) {
            this.a.clear();
            this.a.add(str);
        }
        bundle.putStringArrayList("imageUrl", this.a);
        bundle.putString(a.h, video.toUrl());
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("error", str2);
        }
        return bundle;
    }

    private Bundle c() {
        String str;
        String str2;
        String str3 = null;
        UMWeb umWeb = getUmWeb();
        if (umWeb.getThumbImage() == null) {
            str = null;
            str2 = null;
        } else if (umWeb.getThumbImage().asFileImage() != null) {
            if (getUMImageScale(umWeb.getThumbImage()) <= 0) {
                str3 = UmengText.SHARECONTENT_IMAGE_ERROR;
            }
            str = umWeb.getThumbImage().asFileImage().toString();
            str2 = str3;
        } else {
            str2 = UmengText.QQ_PERMISSION;
            str = null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", subString(objectSetTitle(umWeb), 200));
        bundle.putString("summary", subString(objectSetDescription(umWeb), 600));
        bundle.putString(a.b, str);
        if (!TextUtils.isEmpty(str)) {
            this.a.clear();
            this.a.add(str);
        }
        bundle.putStringArrayList("imageUrl", this.a);
        bundle.putString(a.h, umWeb.toUrl());
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("error", str2);
        }
        return bundle;
    }

    private Bundle d() {
        String str;
        String str2;
        String str3 = null;
        UMusic music = getMusic();
        if (music.getThumbImage() == null) {
            str = null;
            str2 = null;
        } else if (music.getThumbImage().asFileImage() != null) {
            if (getUMImageScale(music.getThumbImage()) <= 0) {
                str3 = UmengText.SHARECONTENT_IMAGE_ERROR;
            }
            str = music.getThumbImage().asFileImage().toString();
            str2 = str3;
        } else {
            str2 = UmengText.QQ_PERMISSION;
            str = null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", subString(objectSetTitle(music), 200));
        bundle.putString("summary", subString(objectSetDescription(music), 600));
        bundle.putString(a.b, str);
        if (!TextUtils.isEmpty(str)) {
            this.a.clear();
            this.a.add(str);
        }
        bundle.putStringArrayList("imageUrl", this.a);
        bundle.putString(a.h, music.getmTargetUrl());
        bundle.putString(a.j, music.toUrl());
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("error", str2);
        }
        return bundle;
    }

    private Bundle e() {
        String str;
        String str2;
        String str3 = null;
        Bundle bundle = new Bundle();
        if (getImage().asFileImage() != null) {
            if (getUMImageScale(getImage()) <= 0) {
                str3 = UmengText.SHARECONTENT_IMAGE_ERROR;
            }
            str2 = getImage().asFileImage().toString();
            str = str3;
        } else {
            str = UmengText.QQ_PERMISSION;
            str2 = null;
        }
        bundle.putString("summary", getText());
        bundle.putString(a.b, str2);
        if (!TextUtils.isEmpty(str2)) {
            this.a.clear();
            this.a.add(str2);
        }
        bundle.putStringArrayList("imageUrl", this.a);
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("error", str);
        }
        return bundle;
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x001f  */
    public Bundle a(String str) {
        String str2;
        Bundle bundle;
        Bundle e;
        if (getmStyle() == 2 || getmStyle() == 3) {
            e = e();
        } else {
            if (getmStyle() == 4) {
                bundle = d();
            } else if (getmStyle() == 16) {
                bundle = c();
            } else if (getmStyle() == 8) {
                bundle = b();
            } else {
                e = a();
            }
            str2 = a.s;
            bundle.putString(a.q, str2);
            if (!TextUtils.isEmpty(str)) {
                bundle.putString(a.i, str);
            }
            return bundle;
        }
        str2 = a.r;
        bundle.putString(a.q, str2);
        if (!TextUtils.isEmpty(str)) {
        }
        return bundle;
    }
}
