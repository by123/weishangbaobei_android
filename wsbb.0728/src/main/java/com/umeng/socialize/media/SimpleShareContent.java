package com.umeng.socialize.media;

import android.text.TextUtils;
import com.luck.picture.lib.config.PictureConfig;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.a.a.a;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.UmengText;
import java.io.File;

public class SimpleShareContent {
    public final String DEFAULT_DESCRIPTION = "这里是描述";
    public final String DEFAULT_TITLE = "这里是标题";
    public final int IMAGE_LIMIT = 491520;
    public final int THUMB_LIMIT = 24576;
    public final int WX_THUMB_LIMIT = 18432;
    private UMImage a;
    private String b;
    private UMVideo c;
    private UMEmoji d;
    private UMusic e;
    private UMMin f;
    private UMWeb g;
    private File h;
    private BaseMediaObject i;
    private int j;
    private String k;
    private String l;

    public SimpleShareContent(ShareContent shareContent) {
        this.b = shareContent.mText;
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMImage)) {
            this.a = (UMImage) shareContent.mMedia;
            this.i = this.a;
        }
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMusic)) {
            this.e = (UMusic) shareContent.mMedia;
            this.i = this.e;
        }
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMVideo)) {
            this.c = (UMVideo) shareContent.mMedia;
            this.i = this.c;
        }
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMEmoji)) {
            this.d = (UMEmoji) shareContent.mMedia;
            this.i = this.d;
        }
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMWeb)) {
            this.g = (UMWeb) shareContent.mMedia;
            this.i = this.g;
        }
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMMin)) {
            this.f = (UMMin) shareContent.mMedia;
            this.i = this.g;
        }
        if (shareContent.file != null) {
            this.h = shareContent.file;
        }
        this.l = shareContent.subject;
        this.j = shareContent.getShareType();
        this.k = a();
    }

    private String a() {
        int i2 = this.j;
        if (i2 == 8) {
            return PictureConfig.VIDEO;
        }
        if (i2 == 16) {
            return "web";
        }
        if (i2 == 32) {
            return "file";
        }
        if (i2 == 64) {
            return "emoji";
        }
        if (i2 == 128) {
            return "minapp";
        }
        switch (i2) {
            case 1:
                return "text";
            case 2:
                return "image";
            case 3:
                return "textandimage";
            case 4:
                return "music";
            default:
                return "error";
        }
    }

    public boolean canFileValid(UMImage uMImage) {
        return uMImage.asFileImage() != null;
    }

    public String getAssertSubject() {
        return TextUtils.isEmpty(this.l) ? "umengshare" : this.l;
    }

    public BaseMediaObject getBaseMediaObject() {
        return this.i;
    }

    public File getFile() {
        return this.h;
    }

    public UMImage getImage() {
        return this.a;
    }

    public byte[] getImageData(UMImage uMImage) {
        return uMImage.asBinImage();
    }

    public byte[] getImageThumb(UMImage uMImage) {
        byte[] a2;
        if (uMImage.getThumbImage() != null) {
            a2 = a.a(uMImage.getThumbImage(), 18432);
            if (a2 == null || a2.length <= 0) {
                Log.um(UmengText.SHARECONTENT_THUMB_ERROR);
            }
        } else {
            a2 = a.a(uMImage, 18432);
            if (a2 == null || a2.length <= 0) {
                Log.um(UmengText.SHARECONTENT_THUMB_ERROR);
            }
        }
        return a2;
    }

    public UMusic getMusic() {
        return this.e;
    }

    public String getMusicTargetUrl(UMusic uMusic) {
        return TextUtils.isEmpty(uMusic.getmTargetUrl()) ? uMusic.toUrl() : uMusic.getmTargetUrl();
    }

    public String getStrStyle() {
        return this.k;
    }

    public byte[] getStrictImageData(UMImage uMImage) {
        if (getUMImageScale(uMImage) <= 491520) {
            return getImageData(uMImage);
        }
        byte[] a2 = a.a(getImage(), 491520);
        if (a2 != null && a2.length > 0) {
            return a2;
        }
        Log.um(UmengText.SHARECONTENT_THUMB_ERROR);
        return null;
    }

    public String getSubject() {
        return this.l;
    }

    public String getText() {
        return this.b;
    }

    public int getUMImageScale(UMImage uMImage) {
        return a.a(uMImage);
    }

    public UMEmoji getUmEmoji() {
        return this.d;
    }

    public UMMin getUmMin() {
        return this.f;
    }

    public UMWeb getUmWeb() {
        return this.g;
    }

    public UMVideo getVideo() {
        return this.c;
    }

    public int getmStyle() {
        return this.j;
    }

    public String objectSetDescription(BaseMediaObject baseMediaObject) {
        if (TextUtils.isEmpty(baseMediaObject.getDescription())) {
            return "这里是描述";
        }
        String description = baseMediaObject.getDescription();
        return description.length() > 1024 ? description.substring(0, WXMediaMessage.DESCRIPTION_LENGTH_LIMIT) : description;
    }

    public String objectSetText(String str) {
        return TextUtils.isEmpty(str) ? "这里是描述" : str.length() > 10240 ? str.substring(0, 10240) : str;
    }

    public byte[] objectSetThumb(BaseMediaObject baseMediaObject) {
        if (baseMediaObject.getThumbImage() == null) {
            return null;
        }
        byte[] a2 = a.a(baseMediaObject.getThumbImage(), 24576);
        if (a2 != null && a2.length > 0) {
            return a2;
        }
        Log.um(UmengText.SHARECONTENT_THUMB_ERROR);
        return a2;
    }

    public String objectSetTitle(BaseMediaObject baseMediaObject) {
        if (TextUtils.isEmpty(baseMediaObject.getTitle())) {
            return "这里是标题";
        }
        String title = baseMediaObject.getTitle();
        return title.length() > 512 ? title.substring(0, WXMediaMessage.TITLE_LENGTH_LIMIT) : title;
    }

    public void setImage(UMImage uMImage) {
        this.a = uMImage;
    }

    public void setMusic(UMusic uMusic) {
        this.e = uMusic;
    }

    public void setText(String str) {
        this.b = str;
    }

    public void setVideo(UMVideo uMVideo) {
        this.c = uMVideo;
    }

    public String subString(String str, int i2) {
        return (!TextUtils.isEmpty(str) || str.length() <= i2) ? str : str.substring(0, i2);
    }
}
