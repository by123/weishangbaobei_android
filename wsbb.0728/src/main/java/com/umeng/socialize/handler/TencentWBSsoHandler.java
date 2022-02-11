package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.luck.picture.lib.config.PictureConfig;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.HandlerRequestCode;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.media.TencentWBSharepreference;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.UmengText;
import com.umeng.socialize.view.OauthDialog;
import java.io.File;
import java.util.Map;

public class TencentWBSsoHandler extends UMAPIShareHandler {
    private static final String waiturl = "tenc2/main?uid";
    protected String VERSION = "6.4.5";
    /* access modifiers changed from: private */
    public TencentWBSharepreference tencentWBSharepreference;

    class AuthListenerWrapper implements UMAuthListener {
        private UMAuthListener mListener = null;

        AuthListenerWrapper(UMAuthListener uMAuthListener) {
            this.mListener = uMAuthListener;
        }

        public void onCancel(SHARE_MEDIA share_media, int i) {
            if (this.mListener != null) {
                this.mListener.onCancel(share_media, i);
            }
        }

        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            TencentWBSsoHandler.this.tencentWBSharepreference.setAuthData(map).commit();
            if (this.mListener != null) {
                this.mListener.onComplete(share_media, i, map);
            }
        }

        public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
            if (this.mListener != null) {
                this.mListener.onError(share_media, i, th);
            }
        }

        public void onStart(SHARE_MEDIA share_media) {
        }
    }

    public void authorize(final UMAuthListener uMAuthListener) {
        if (isAuthorize()) {
            final Map<String, String> authData = getAuthData();
            QueuedWork.runInMain(new Runnable() {
                public void run() {
                    uMAuthListener.onComplete(SHARE_MEDIA.TENCENT, 0, authData);
                }
            });
            return;
        }
        QueuedWork.runInMain(new Runnable() {
            public void run() {
                if (TencentWBSsoHandler.this.mWeakAct.get() != null && !((Activity) TencentWBSsoHandler.this.mWeakAct.get()).isFinishing()) {
                    OauthDialog oauthDialog = new OauthDialog((Activity) TencentWBSsoHandler.this.mWeakAct.get(), SHARE_MEDIA.TENCENT, new AuthListenerWrapper(uMAuthListener));
                    oauthDialog.setWaitUrl(TencentWBSsoHandler.waiturl);
                    oauthDialog.show();
                }
            }
        });
    }

    public void authorizeCallBack(int i, int i2, Intent intent) {
    }

    public void deleteAuth() {
        if (this.tencentWBSharepreference != null) {
            this.tencentWBSharepreference.delete();
        }
    }

    public void deleteAuth(final UMAuthListener uMAuthListener) {
        this.tencentWBSharepreference.delete();
        if (uMAuthListener != null) {
            QueuedWork.runInMain(new Runnable() {
                public void run() {
                    uMAuthListener.onComplete(TencentWBSsoHandler.this.getConfig().getName(), 1, (Map<String, String>) null);
                }
            });
        }
    }

    public Map<String, String> getAuthData() {
        if (this.tencentWBSharepreference != null) {
            return this.tencentWBSharepreference.getAuthData();
        }
        return null;
    }

    public Bundle getEditable(ShareContent shareContent) {
        Bundle bundle = new Bundle();
        bundle.putString("media", SHARE_MEDIA.TENCENT.toString());
        bundle.putString("title", UmengText.SHARE_TO + UmengText.TENCENT);
        bundle.putString(SocializeConstants.KEY_TEXT, shareContent.mText);
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMImage)) {
            File asFileImage = ((UMImage) shareContent.mMedia).asFileImage();
            if (asFileImage != null) {
                bundle.putString(SocializeConstants.KEY_PIC, asFileImage.getAbsolutePath());
                return bundle;
            }
        } else if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMusic)) {
            bundle.putString(SocializeConstants.KEY_PIC, "music");
            bundle.putString(SocializeConstants.KEY_TEXT, ((UMusic) shareContent.mMedia).getDescription());
            return bundle;
        } else if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMVideo)) {
            bundle.putString(SocializeConstants.KEY_PIC, PictureConfig.VIDEO);
            bundle.putString(SocializeConstants.KEY_TEXT, ((UMVideo) shareContent.mMedia).getDescription());
            return bundle;
        } else if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMWeb)) {
            bundle.putString(SocializeConstants.KEY_PIC, "web");
            bundle.putString(SocializeConstants.KEY_TEXT, ((UMWeb) shareContent.mMedia).getDescription());
        }
        return bundle;
    }

    public SHARE_MEDIA getPlatform() {
        return SHARE_MEDIA.TENCENT;
    }

    public int getRequestCode() {
        return HandlerRequestCode.TENCENT_WB_REQUEST_CODE;
    }

    public ShareContent getResult(ShareContent shareContent, Bundle bundle) {
        shareContent.mText = bundle.getString(SocializeConstants.KEY_TEXT);
        if (bundle.getString(SocializeConstants.KEY_PIC) == null) {
            shareContent.mMedia = null;
        }
        return shareContent;
    }

    public String getUID() {
        return this.tencentWBSharepreference.getUID();
    }

    public boolean isAuthorize() {
        return this.tencentWBSharepreference != null && this.tencentWBSharepreference.isAuthorized();
    }

    public boolean isSupportAuth() {
        return true;
    }

    public void onCreate(Context context, PlatformConfig.Platform platform) {
        super.onCreate(context, platform);
        Log.um(platform.getName() + " version:" + this.VERSION);
        this.tencentWBSharepreference = new TencentWBSharepreference(context, SHARE_MEDIA.TENCENT.toString());
    }
}
