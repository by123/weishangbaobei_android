package com.umeng.socialize.media;

import android.text.TextUtils;
import com.sina.weibo.sdk.api.BaseMediaObject;
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

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0021, code lost:
        if (android.text.TextUtils.isEmpty(getText()) == false) goto L_0x0023;
     */
    public WeiboMultiMessage a() {
        BaseMediaObject f;
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        if (getmStyle() == 2 || getmStyle() == 3) {
            weiboMultiMessage.imageObject = c();
        } else {
            if (getmStyle() == 16) {
                f = d();
            } else if (getmStyle() == 4) {
                f = e();
            } else if (getmStyle() == 8) {
                f = f();
            }
            weiboMultiMessage.mediaObject = f;
            a(weiboMultiMessage);
            return weiboMultiMessage;
        }
        weiboMultiMessage.textObject = b();
        return weiboMultiMessage;
    }
}
