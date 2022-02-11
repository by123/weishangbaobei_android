package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.editorpage.IEditor;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.net.SharePostRequest;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.utils.Log;
import java.util.Map;
import java.util.Stack;

public abstract class UMAPIShareHandler extends UMSSOHandler implements IEditor {
    private Stack<StatHolder> mStatStack = new Stack<>();

    private static class StatHolder {
        public ShareContent Content;
        /* access modifiers changed from: private */
        public UMShareListener Listener;

        private StatHolder() {
        }
    }

    public abstract void authorizeCallBack(int i, int i2, Intent intent);

    public abstract void deleteAuth();

    /* access modifiers changed from: protected */
    public void doShare(ShareContent shareContent, UMShareListener uMShareListener) {
        if (getShareConfig().isOpenShareEditActivity()) {
            StatHolder statHolder = new StatHolder();
            statHolder.Content = shareContent;
            UMShareListener unused = statHolder.Listener = uMShareListener;
            this.mStatStack.push(statHolder);
            if (this.mWeakAct.get() != null && !((Activity) this.mWeakAct.get()).isFinishing()) {
                try {
                    Intent intent = new Intent((Context) this.mWeakAct.get(), Class.forName("com.umeng.socialize.editorpage.ShareActivity"));
                    intent.putExtras(getEditable(shareContent));
                    ((Activity) this.mWeakAct.get()).startActivityForResult(intent, getRequestCode());
                } catch (ClassNotFoundException e) {
                    sendShareRequest(shareContent, uMShareListener);
                    Log.e("没有加入界面jar");
                    e.printStackTrace();
                }
            }
        } else {
            sendShareRequest(shareContent, uMShareListener);
        }
    }

    public abstract SHARE_MEDIA getPlatform();

    public abstract String getUID();

    public void onActivityResult(int i, int i2, Intent intent) {
        StatHolder pop;
        if (i == getRequestCode()) {
            if (i2 == 1000) {
                if (!this.mStatStack.isEmpty() && (pop = this.mStatStack.pop()) != null) {
                    pop.Listener.onCancel(getPlatform());
                }
            } else if (intent == null || !intent.hasExtra(SocializeConstants.KEY_TEXT)) {
                authorizeCallBack(i, i2, intent);
            } else if (!this.mStatStack.empty()) {
                final StatHolder pop2 = this.mStatStack.pop();
                final Bundle extras = intent.getExtras();
                if (i2 == -1) {
                    QueuedWork.runInBack(new Runnable() {
                        public void run() {
                            UMAPIShareHandler.this.sendShareRequest(UMAPIShareHandler.this.getResult(pop2.Content, extras), pop2.Listener);
                            Log.d("act", "sent share request");
                        }
                    }, true);
                } else if (pop2.Listener != null) {
                    pop2.Listener.onCancel(getPlatform());
                }
            }
        }
    }

    public void onCreate(Context context, PlatformConfig.Platform platform) {
        super.onCreate(context, platform);
    }

    public void sendShareRequest(ShareContent shareContent, final UMShareListener uMShareListener) {
        final SHARE_MEDIA platform = getPlatform();
        SharePostRequest sharePostRequest = new SharePostRequest(getContext(), platform.toString().toLowerCase(), getUID(), shareContent);
        sharePostRequest.setReqType(0);
        final SocializeReseponse doShare = RestAPI.doShare(sharePostRequest);
        if (doShare == null) {
            QueuedWork.runInMain(new Runnable() {
                public void run() {
                    UMShareListener uMShareListener = uMShareListener;
                    SHARE_MEDIA share_media = platform;
                    uMShareListener.onError(share_media, new Throwable(UmengErrorCode.ShareFailed.getMessage() + "response is null"));
                }
            });
        } else if (!doShare.isOk()) {
            final ShareContent shareContent2 = shareContent;
            final UMShareListener uMShareListener2 = uMShareListener;
            final SocializeReseponse socializeReseponse = doShare;
            QueuedWork.runInMain(new Runnable() {
                public void run() {
                    if (doShare.mStCode == 5027) {
                        UMAPIShareHandler.this.deleteAuth();
                        UMAPIShareHandler.this.share(shareContent2, uMShareListener2);
                        return;
                    }
                    UMShareListener uMShareListener = uMShareListener2;
                    SHARE_MEDIA share_media = platform;
                    uMShareListener.onError(share_media, new Throwable(UmengErrorCode.ShareFailed.getMessage() + socializeReseponse.mMsg));
                }
            });
        } else {
            QueuedWork.runInMain(new Runnable() {
                public void run() {
                    uMShareListener.onResult(platform);
                }
            });
        }
    }

    public boolean share(final ShareContent shareContent, final UMShareListener uMShareListener) {
        if (isAuthorize()) {
            doShare(shareContent, uMShareListener);
            return false;
        }
        authorize(new UMAuthListener() {
            public void onCancel(SHARE_MEDIA share_media, int i) {
                uMShareListener.onCancel(share_media);
            }

            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                QueuedWork.runInBack(new Runnable() {
                    public void run() {
                        UMAPIShareHandler.this.doShare(shareContent, uMShareListener);
                    }
                }, true);
            }

            public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
                uMShareListener.onError(share_media, th);
            }

            public void onStart(SHARE_MEDIA share_media) {
                uMShareListener.onStart(share_media);
            }
        });
        return false;
    }
}
