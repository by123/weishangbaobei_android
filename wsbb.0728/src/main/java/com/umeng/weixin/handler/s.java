package com.umeng.weixin.handler;

import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.media.SimpleShareContent;
import com.umeng.socialize.media.UMEmoji;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMMin;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.UmengText;

public class s extends SimpleShareContent {
    public s(ShareContent shareContent) {
        super(shareContent);
    }

    private Bundle b() {
        Bundle bundle = new Bundle();
        bundle.putInt("_wxobject_sdkVer", 0);
        bundle.putInt("_wxapi_sendmessagetowx_req_media_type", 1);
        bundle.putString("_wxobject_description", objectSetText(getText()));
        bundle.putByteArray("_wxobject_thumbdata", (byte[]) null);
        bundle.putInt("_wxapi_command_type", 2);
        bundle.putString("_wxobject_title", (String) null);
        bundle.putString("_wxapi_basereq_openid", (String) null);
        bundle.putString("_wxtextobject_text", getText());
        bundle.putString(WXMediaMessage.Builder.KEY_IDENTIFIER, "com.tencent.mm.sdk.openapi.WXTextObject");
        if (TextUtils.isEmpty(getText())) {
            bundle.putString("error", UmengText.EMPTY_TEXT);
        }
        if (getText().length() > 10240) {
            bundle.putString("error", UmengText.LONG_TEXT);
        }
        return bundle;
    }

    private Bundle c() {
        UMEmoji umEmoji = getUmEmoji();
        String str = "";
        if (!(umEmoji == null || umEmoji.asFileImage() == null)) {
            str = umEmoji.asFileImage().toString();
        }
        byte[] objectSetThumb = objectSetThumb(umEmoji);
        Bundle bundle = new Bundle();
        bundle.putInt("_wxobject_sdkVer", 0);
        bundle.putInt("_wxapi_sendmessagetowx_req_media_type", 8);
        bundle.putString("_wxobject_description", getText());
        bundle.putByteArray("_wxobject_thumbdata", objectSetThumb);
        bundle.putString("_wxemojiobject_emojiPath", str);
        bundle.putInt("_wxapi_command_type", 2);
        bundle.putString("_wxobject_title", (String) null);
        bundle.putString("_wxapi_basereq_openid", (String) null);
        bundle.putString(WXMediaMessage.Builder.KEY_IDENTIFIER, "com.tencent.mm.sdk.openapi.WXEmojiObject");
        if (!TextUtils.isEmpty("")) {
            bundle.putString("error", "");
        }
        return bundle;
    }

    private Bundle d() {
        UMusic music = getMusic();
        String str = "";
        String url = TextUtils.isEmpty(music.getmTargetUrl()) ? music.toUrl() : music.getmTargetUrl();
        String url2 = music.toUrl();
        String lowBandDataUrl = !TextUtils.isEmpty(music.getLowBandDataUrl()) ? music.getLowBandDataUrl() : null;
        String lowBandUrl = !TextUtils.isEmpty(music.getLowBandUrl()) ? music.getLowBandUrl() : null;
        String objectSetTitle = objectSetTitle(music);
        String objectSetDescription = objectSetDescription(music);
        byte[] objectSetThumb = objectSetThumb(music);
        if (objectSetThumb == null || objectSetThumb.length <= 0) {
            str = UmengText.SHARECONTENT_THUMB_ERROR;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("_wxobject_sdkVer", 0);
        bundle.putInt("_wxapi_sendmessagetowx_req_media_type", 3);
        bundle.putString("_wxobject_description", objectSetDescription);
        bundle.putByteArray("_wxobject_thumbdata", objectSetThumb);
        bundle.putInt("_wxapi_command_type", 2);
        bundle.putString("_wxobject_title", objectSetTitle);
        bundle.putString("_wxmusicobject_musicUrl", url);
        bundle.putString("_wxmusicobject_musicLowBandUrl", lowBandUrl);
        bundle.putString("_wxmusicobject_musicDataUrl", url2);
        bundle.putString("_wxmusicobject_musicLowBandDataUrl", lowBandDataUrl);
        bundle.putString("_wxapi_basereq_openid", (String) null);
        bundle.putString("_wxtextobject_text", objectSetDescription);
        bundle.putString(WXMediaMessage.Builder.KEY_IDENTIFIER, "com.tencent.mm.sdk.openapi.WXMusicObject");
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("error", str);
        }
        return bundle;
    }

    private Bundle e() {
        UMVideo video = getVideo();
        String str = "";
        String url = video.toUrl();
        String lowBandUrl = !TextUtils.isEmpty(video.getLowBandUrl()) ? video.getLowBandUrl() : null;
        String objectSetTitle = objectSetTitle(video);
        String objectSetDescription = objectSetDescription(video);
        byte[] objectSetThumb = objectSetThumb(video);
        if (objectSetThumb == null || objectSetThumb.length <= 0) {
            str = UmengText.SHARECONTENT_THUMB_ERROR;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("_wxobject_sdkVer", 0);
        bundle.putInt("_wxapi_sendmessagetowx_req_media_type", 4);
        bundle.putString("_wxobject_description", objectSetDescription);
        bundle.putByteArray("_wxobject_thumbdata", objectSetThumb);
        bundle.putInt("_wxapi_command_type", 2);
        bundle.putString("_wxobject_title", objectSetTitle);
        bundle.putString("_wxvideoobject_videoUrl", url);
        bundle.putString("_wxvideoobject_videoLowBandUrl", lowBandUrl);
        bundle.putString("_wxapi_basereq_openid", (String) null);
        bundle.putString("_wxtextobject_text", objectSetDescription);
        bundle.putString(WXMediaMessage.Builder.KEY_IDENTIFIER, "com.tencent.mm.sdk.openapi.WXVideoObject");
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("error", str);
        }
        return bundle;
    }

    private Bundle f() {
        UMImage image = getImage();
        byte[] asBinImage = image.asBinImage();
        String str = "";
        if (canFileValid(image)) {
            str = image.asFileImage().toString();
        } else {
            asBinImage = getStrictImageData(image);
        }
        byte[] imageThumb = getImageThumb(image);
        Bundle bundle = new Bundle();
        bundle.putInt("_wxobject_sdkVer", 0);
        bundle.putInt("_wxapi_sendmessagetowx_req_media_type", 2);
        bundle.putString("_wxobject_description", getText());
        bundle.putByteArray("_wxobject_thumbdata", imageThumb);
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("_wximageobject_imagePath", str);
            bundle.putByteArray("_wximageobject_imageData", (byte[]) null);
        } else {
            bundle.putByteArray("_wximageobject_imageData", asBinImage);
            bundle.putString("_wximageobject_imagePath", str);
        }
        bundle.putInt("_wxapi_command_type", 2);
        bundle.putString("_wxobject_title", (String) null);
        bundle.putString("_wxapi_basereq_openid", (String) null);
        bundle.putString(WXMediaMessage.Builder.KEY_IDENTIFIER, "com.tencent.mm.sdk.openapi.WXImageObject");
        if (!TextUtils.isEmpty("")) {
            bundle.putString("error", "");
        }
        return bundle;
    }

    private Bundle g() {
        UMWeb umWeb = getUmWeb();
        String objectSetTitle = objectSetTitle(umWeb);
        byte[] objectSetThumb = objectSetThumb(umWeb);
        if (objectSetThumb == null || objectSetThumb.length <= 0) {
            Log.um(UmengText.SHARECONTENT_THUMB_ERROR);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("_wxobject_sdkVer", 0);
        bundle.putInt("_wxapi_sendmessagetowx_req_media_type", 5);
        bundle.putString("_wxobject_description", objectSetDescription(umWeb));
        bundle.putByteArray("_wxobject_thumbdata", objectSetThumb);
        bundle.putInt("_wxapi_command_type", 2);
        bundle.putString("_wxobject_title", objectSetTitle);
        bundle.putString("_wxwebpageobject_webpageUrl", umWeb.toUrl());
        bundle.putString("_wxapi_basereq_openid", (String) null);
        bundle.putString("_wxtextobject_text", objectSetDescription(umWeb));
        bundle.putString("_wxobject_description", objectSetDescription(umWeb));
        bundle.putString(WXMediaMessage.Builder.KEY_IDENTIFIER, "com.tencent.mm.sdk.openapi.WXWebpageObject");
        bundle.putString("_wxwebpageobject_extInfo", (String) null);
        bundle.putString("_wxwebpageobject_canvaspagexml", (String) null);
        if (TextUtils.isEmpty(umWeb.toUrl())) {
            bundle.putString("error", UmengText.EMPTY_WEB_URL);
        }
        if (umWeb.toUrl().length() > 10240) {
            bundle.putString("error", UmengText.LONG_URL);
        }
        if (!TextUtils.isEmpty("")) {
            bundle.putString("error", "");
        }
        return bundle;
    }

    private Bundle h() {
        StringBuilder sb;
        String str;
        UMMin umMin = getUmMin();
        String objectSetTitle = objectSetTitle(umMin);
        byte[] objectSetThumb = objectSetThumb(umMin);
        if (objectSetThumb == null || objectSetThumb.length <= 0) {
            Log.um(UmengText.SHARECONTENT_THUMB_ERROR);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("_wxobject_sdkVer", 0);
        bundle.putInt("_wxapi_sendmessagetowx_req_media_type", 36);
        String path = umMin.getPath();
        if (!TextUtils.isEmpty(path)) {
            String[] split = path.split("\\?");
            if (split.length > 1) {
                sb = new StringBuilder();
                sb.append(split[0]);
                sb.append(".html?");
                str = split[1];
            } else {
                sb = new StringBuilder();
                sb.append(split[0]);
                str = ".html";
            }
            sb.append(str);
            bundle.putString("_wxminiprogram_path", sb.toString());
        }
        bundle.putString("_wxobject_description", objectSetDescription(umMin));
        bundle.putByteArray("_wxobject_thumbdata", objectSetThumb);
        bundle.putInt("_wxapi_command_type", 2);
        bundle.putString("_wxminiprogram_username", umMin.getUserName() + "@app");
        bundle.putString("_wxobject_title", objectSetTitle);
        bundle.putString("_wxminiprogram_webpageurl", umMin.toUrl());
        bundle.putString("_wxapi_basereq_openid", (String) null);
        bundle.putString(WXMediaMessage.Builder.KEY_IDENTIFIER, "com.tencent.mm.sdk.openapi.WXMiniProgramObject");
        if (TextUtils.isEmpty(umMin.toUrl())) {
            bundle.putString("error", UmengText.EMPTY_WEB_URL);
        }
        if (umMin.toUrl().length() > 10240) {
            bundle.putString("error", UmengText.LONG_URL);
        }
        if (!TextUtils.isEmpty("")) {
            bundle.putString("error", "");
        }
        if (TextUtils.isEmpty(umMin.getPath())) {
            bundle.putString("error", "UMMin path is null");
        }
        if (TextUtils.isEmpty(umMin.toUrl())) {
            bundle.putString("error", "UMMin url is null");
        }
        return bundle;
    }

    public Bundle a() {
        Bundle f = (getmStyle() == 2 || getmStyle() == 3) ? f() : getmStyle() == 16 ? g() : getmStyle() == 4 ? d() : getmStyle() == 8 ? e() : getmStyle() == 64 ? c() : getmStyle() == 128 ? h() : b();
        f.putString("_wxobject_message_action", (String) null);
        f.putString("_wxobject_message_ext", (String) null);
        f.putString("_wxobject_mediatagname", (String) null);
        return f;
    }
}
