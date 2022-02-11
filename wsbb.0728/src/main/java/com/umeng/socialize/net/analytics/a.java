package com.umeng.socialize.net.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.socialize.Config;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.net.utils.URequest;
import com.umeng.socialize.utils.SocializeUtils;

public class a extends SocializeRequest {
    private static final String a = "/share/multi_add/";
    private static final int b = 9;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private UMediaObject i;

    public a(Context context, String str, String str2) {
        super(context, "", b.class, 9, URequest.RequestMethod.POST);
        this.mContext = context;
        this.c = str;
        this.h = str2;
        setReqType(1);
    }

    public void a(UMediaObject uMediaObject) {
        if (uMediaObject instanceof UMImage) {
            this.i = uMediaObject;
        } else if (uMediaObject instanceof UMusic) {
            UMusic uMusic = (UMusic) uMediaObject;
            this.f = uMusic.getTitle();
            this.g = uMusic.toUrl();
            this.h = uMusic.getDescription();
            this.i = uMusic.getThumbImage();
        } else if (uMediaObject instanceof UMVideo) {
            UMVideo uMVideo = (UMVideo) uMediaObject;
            this.f = uMVideo.getTitle();
            this.g = uMVideo.toUrl();
            this.h = uMVideo.getDescription();
            this.i = uMVideo.getThumbImage();
        } else if (uMediaObject instanceof UMWeb) {
            UMWeb uMWeb = (UMWeb) uMediaObject;
            this.f = uMWeb.getTitle();
            this.g = uMWeb.toUrl();
            this.h = uMWeb.getDescription();
            this.i = uMWeb.getThumbImage();
        }
    }

    public void a(String str) {
        this.c = str;
    }

    public void b(String str) {
        this.d = str;
    }

    public void c(String str) {
        this.e = str;
    }

    public void d(String str) {
        this.h = str;
    }

    /* access modifiers changed from: protected */
    public String getPath() {
        return a + SocializeUtils.getAppkey(this.mContext) + "/" + Config.EntityKey + "/";
    }

    public void onPrepareRequest() {
        super.onPrepareRequest();
        String format = String.format("{\"%s\":\"%s\"}", new Object[]{this.c, this.d == null ? "" : this.d});
        String appkey = SocializeUtils.getAppkey(this.mContext);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_DESCRIPTOR, Config.Descriptor);
        addStringParams("to", format);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_SHARE_SNS, format);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_AK, appkey);
        addStringParams("type", this.e);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_COMMENT_TEXT, this.h);
        if (!TextUtils.isEmpty(this.g)) {
            addStringParams("url", this.g);
        }
        if (!TextUtils.isEmpty(this.f)) {
            addStringParams("title", this.f);
        }
        addMediaParams(this.i);
    }
}
