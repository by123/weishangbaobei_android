package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.SocializeUtils;
import gdut.bsx.share2.ShareContentType;

public class UMMoreHandler extends UMSSOHandler {
    private Activity mAct;

    public void onCreate(Context context, PlatformConfig.Platform platform) {
        super.onCreate(context, platform);
        this.mAct = (Activity) context;
    }

    public void release() {
        super.release();
        this.mAct = null;
    }

    public boolean share(ShareContent shareContent, UMShareListener uMShareListener) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        if (shareContent.mMedia == null || !(shareContent.mMedia instanceof UMImage)) {
            intent.setType(ShareContentType.TEXT);
        } else {
            intent.setType(ShareContentType.IMAGE);
            UMImage uMImage = (UMImage) shareContent.mMedia;
            if (uMImage.asFileImage().getPath() != null) {
                intent.putExtra("android.intent.extra.STREAM", SocializeUtils.insertImage(getContext(), uMImage.asFileImage().getPath()));
            }
        }
        intent.putExtra("android.intent.extra.SUBJECT", shareContent.subject);
        intent.putExtra("android.intent.extra.TEXT", shareContent.mText);
        Intent createChooser = Intent.createChooser(intent, Config.MORE_TITLE);
        createChooser.addFlags(268435456);
        try {
            if (this.mAct != null && !this.mAct.isFinishing()) {
                this.mAct.startActivity(createChooser);
            }
            uMShareListener.onResult(SHARE_MEDIA.MORE);
            return true;
        } catch (Exception e) {
            uMShareListener.onError(SHARE_MEDIA.MORE, e);
            return true;
        }
    }
}
