package com.umeng.socialize.media;

import android.text.TextUtils;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MusicObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.net.LinkCardResponse;
import com.umeng.socialize.net.LinkcardRequest;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.sina.util.Utility;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.UmengText;

public class SinaShareContent extends SimpleShareContent {
    public SinaShareContent(ShareContent shareContent) {
        super(shareContent);
    }

    private WeiboMultiMessage a(WeiboMultiMessage weiboMultiMessage) {
        TextObject textObject;
        if (!TextUtils.isEmpty(getText())) {
            textObject = b();
        } else {
            textObject = new TextObject();
            if (getBaseMediaObject() != null && !TextUtils.isEmpty(getBaseMediaObject().getDescription())) {
                textObject.text = getBaseMediaObject().getDescription();
            }
        }
        weiboMultiMessage.textObject = textObject;
        return weiboMultiMessage;
    }

    private TextObject b() {
        TextObject textObject = new TextObject();
        textObject.text = getText();
        return textObject;
    }

    private WeiboMultiMessage b(WeiboMultiMessage weiboMultiMessage) {
        if (!(getBaseMediaObject() == null || getBaseMediaObject().getThumbImage() == null)) {
            ImageObject imageObject = new ImageObject();
            if (canFileValid(getBaseMediaObject().getThumbImage())) {
                imageObject.imagePath = getBaseMediaObject().getThumbImage().asFileImage().toString();
            } else {
                imageObject.imageData = getImageData(getBaseMediaObject().getThumbImage());
            }
            weiboMultiMessage.imageObject = imageObject;
        }
        return weiboMultiMessage;
    }

    private ImageObject c() {
        ImageObject imageObject = new ImageObject();
        if (canFileValid(getImage())) {
            imageObject.imagePath = getImage().asFileImage().toString();
        } else {
            imageObject.imageData = getImageData(getImage());
        }
        imageObject.thumbData = objectSetThumb(getImage());
        imageObject.description = getText();
        return imageObject;
    }

    private WebpageObject d() {
        LinkcardRequest linkcardRequest = new LinkcardRequest(ContextUtil.getContext());
        linkcardRequest.setMedia(getUmWeb());
        LinkCardResponse convertLinkCard = RestAPI.convertLinkCard(linkcardRequest);
        WebpageObject webpageObject = new WebpageObject();
        webpageObject.identify = Utility.generateGUID();
        webpageObject.title = objectSetTitle(getUmWeb());
        webpageObject.description = objectSetDescription(getUmWeb());
        if (getUmWeb().getThumbImage() != null) {
            webpageObject.thumbData = objectSetThumb(getUmWeb());
        } else {
            Log.um(UmengText.SINA_THUMB_ERROR);
        }
        webpageObject.actionUrl = (convertLinkCard == null || TextUtils.isEmpty(convertLinkCard.url)) ? getUmWeb().toUrl() : convertLinkCard.url;
        webpageObject.defaultText = getText();
        return webpageObject;
    }

    private MusicObject e() {
        MusicObject musicObject = new MusicObject();
        musicObject.identify = Utility.generateGUID();
        musicObject.title = objectSetTitle(getMusic());
        musicObject.description = objectSetDescription(getMusic());
        if (getMusic().getThumbImage() != null) {
            musicObject.thumbData = objectSetThumb(getMusic());
        } else {
            Log.um(UmengText.SINA_THUMB_ERROR);
        }
        musicObject.actionUrl = getMusic().getmTargetUrl();
        if (!TextUtils.isEmpty(getMusic().getLowBandDataUrl())) {
            musicObject.dataUrl = getMusic().getLowBandDataUrl();
        }
        if (!TextUtils.isEmpty(getMusic().getHighBandDataUrl())) {
            musicObject.dataHdUrl = getMusic().getHighBandDataUrl();
        }
        if (!TextUtils.isEmpty(getMusic().getH5Url())) {
            musicObject.h5Url = getMusic().getH5Url();
        }
        musicObject.duration = getMusic().getDuration() > 0 ? getMusic().getDuration() : 10;
        if (!TextUtils.isEmpty(getText())) {
            musicObject.defaultText = getText();
        }
        return musicObject;
    }

    private VideoObject f() {
        VideoObject videoObject = new VideoObject();
        videoObject.identify = Utility.generateGUID();
        videoObject.title = objectSetTitle(getVideo());
        videoObject.description = objectSetDescription(getVideo());
        if (getVideo().getThumbImage() != null) {
            videoObject.thumbData = objectSetThumb(getVideo());
        } else {
            Log.um(UmengText.SINA_THUMB_ERROR);
        }
        videoObject.actionUrl = getVideo().toUrl();
        if (!TextUtils.isEmpty(getVideo().getLowBandDataUrl())) {
            videoObject.dataUrl = getVideo().getLowBandDataUrl();
        }
        if (!TextUtils.isEmpty(getVideo().getHighBandDataUrl())) {
            videoObject.dataHdUrl = getVideo().getHighBandDataUrl();
        }
        if (!TextUtils.isEmpty(getVideo().getH5Url())) {
            videoObject.h5Url = getVideo().getH5Url();
        }
        videoObject.duration = getVideo().getDuration() > 0 ? getVideo().getDuration() : 10;
        if (!TextUtils.isEmpty(getVideo().getDescription())) {
            videoObject.description = getVideo().getDescription();
        }
        videoObject.defaultText = getText();
        return videoObject;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004d, code lost:
        if (android.text.TextUtils.isEmpty(getText()) == false) goto L_0x004f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.sina.weibo.sdk.api.WeiboMultiMessage a() {
        /*
            r3 = this;
            com.sina.weibo.sdk.api.WeiboMultiMessage r0 = new com.sina.weibo.sdk.api.WeiboMultiMessage
            r0.<init>()
            int r1 = r3.getmStyle()
            r2 = 2
            if (r1 == r2) goto L_0x003f
            int r1 = r3.getmStyle()
            r2 = 3
            if (r1 != r2) goto L_0x0014
            goto L_0x003f
        L_0x0014:
            int r1 = r3.getmStyle()
            r2 = 16
            if (r1 != r2) goto L_0x0026
            com.sina.weibo.sdk.api.WebpageObject r1 = r3.d()
        L_0x0020:
            r0.mediaObject = r1
            r3.a(r0)
            goto L_0x0055
        L_0x0026:
            int r1 = r3.getmStyle()
            r2 = 4
            if (r1 != r2) goto L_0x0032
            com.sina.weibo.sdk.api.MusicObject r1 = r3.e()
            goto L_0x0020
        L_0x0032:
            int r1 = r3.getmStyle()
            r2 = 8
            if (r1 != r2) goto L_0x004f
            com.sina.weibo.sdk.api.VideoObject r1 = r3.f()
            goto L_0x0020
        L_0x003f:
            com.sina.weibo.sdk.api.ImageObject r1 = r3.c()
            r0.imageObject = r1
            java.lang.String r1 = r3.getText()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0055
        L_0x004f:
            com.sina.weibo.sdk.api.TextObject r1 = r3.b()
            r0.textObject = r1
        L_0x0055:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.media.SinaShareContent.a():com.sina.weibo.sdk.api.WeiboMultiMessage");
    }
}
