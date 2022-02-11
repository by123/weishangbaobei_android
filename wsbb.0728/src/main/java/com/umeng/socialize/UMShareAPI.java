package com.umeng.socialize;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.controller.SocialRouter;
import com.umeng.socialize.handler.UMSSOHandler;
import com.umeng.socialize.net.ActionBarRequest;
import com.umeng.socialize.net.ActionBarResponse;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.net.analytics.SocialAnalytics;
import com.umeng.socialize.uploadlog.UMLog;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeSpUtils;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.utils.UrlUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

public class UMShareAPI {
    private static UMShareAPI singleton;
    private UMShareConfig mDefaultShareConfig = new UMShareConfig();
    /* access modifiers changed from: private */
    public SocialRouter router;

    private static class InitThread extends QueuedWork.UMAsyncTask<Void> {
        private boolean isToday = false;
        private Context mContext;

        public InitThread(Context context) {
            this.mContext = context;
            String uMId = SocializeSpUtils.getUMId(context);
            if (!TextUtils.isEmpty(uMId)) {
                Config.UID = uMId;
            }
            String uMEk = SocializeSpUtils.getUMEk(context);
            if (!TextUtils.isEmpty(uMEk)) {
                Config.EntityKey = uMEk;
            }
            this.isToday = SocializeUtils.isToday(SocializeSpUtils.getTime(context));
        }

        private boolean isNewInstall() {
            return this.mContext.getSharedPreferences(SocializeConstants.SOCIAL_PREFERENCE_NAME, 0).getBoolean("newinstall", false);
        }

        /* access modifiers changed from: protected */
        public Void doInBackground() {
            ActionBarResponse queryShareId;
            boolean isNewInstall = isNewInstall();
            Log.y("----sdkversion:6.4.5---\n 如有任何错误，请开启debug模式:在设置各平台APPID的地方添加代码：Config.DEBUG = true\n所有编译问题或者设置问题，请参考文档：https://at.umeng.com/0fqeCy?cid=476");
            if ((TextUtils.isEmpty(Config.EntityKey) || TextUtils.isEmpty(Config.UID) || !this.isToday) && (queryShareId = RestAPI.queryShareId(new ActionBarRequest(this.mContext, isNewInstall))) != null && queryShareId.isOk()) {
                setInstalled();
                Config.EntityKey = queryShareId.mEntityKey;
                Config.SessionId = queryShareId.mSid;
                Config.UID = queryShareId.mUid;
                SocializeSpUtils.putUMId(this.mContext, Config.UID);
                SocializeSpUtils.putUMEk(this.mContext, Config.EntityKey);
                SocializeSpUtils.putTime(this.mContext);
            }
            SocialAnalytics.dauStats(this.mContext, isNewInstall);
            return null;
        }

        public void setInstalled() {
            SharedPreferences.Editor edit = this.mContext.getSharedPreferences(SocializeConstants.SOCIAL_PREFERENCE_NAME, 0).edit();
            edit.putBoolean("newinstall", true);
            edit.commit();
        }
    }

    private UMShareAPI(Context context) {
        ContextUtil.setContext(StubApp.getOrigApplicationContext(context.getApplicationContext()));
        this.router = new SocialRouter(StubApp.getOrigApplicationContext(context.getApplicationContext()));
        new InitThread(StubApp.getOrigApplicationContext(context.getApplicationContext())).execute();
    }

    public static UMShareAPI get(Context context) {
        if (singleton == null || singleton.router == null) {
            singleton = new UMShareAPI(context);
        }
        singleton.router.setmContext(context);
        return singleton;
    }

    public static void init(Context context, String str) {
        SocializeConstants.APPKEY = str;
        get(context);
    }

    private boolean judgePlatform(Activity activity, SHARE_MEDIA share_media) {
        boolean z = false;
        for (Method name : activity.getClass().getDeclaredMethods()) {
            if (name.getName().equals("onActivityResult")) {
                z = true;
            }
        }
        if (!z) {
            Log.url("您的activity中没有重写onActivityResult方法", UrlUtil.ALL_NO_ONACTIVITY);
        }
        if (share_media == SHARE_MEDIA.QQ) {
            String checkQQByself = UmengTool.checkQQByself(activity);
            if (!checkQQByself.contains("没有")) {
                Log.um(UmengTool.checkQQByself(activity));
                return true;
            } else if (checkQQByself.contains("没有在AndroidManifest.xml中检测到")) {
                UmengTool.showDialogWithURl(activity, checkQQByself, UrlUtil.QQ_TENCENT_INITFAIL);
                return false;
            } else if (checkQQByself.contains("android.permission.WRITE_EXTERNAL_STORAGE")) {
                UmengTool.showDialogWithURl(activity, checkQQByself, UrlUtil.QQ_SHARE_FAIL);
                return false;
            } else if (checkQQByself.contains("qq应用id")) {
                UmengTool.showDialogWithURl(activity, checkQQByself, UrlUtil.QQ_SHARESUCCESS_CANCEL);
                return false;
            } else if (checkQQByself.contains("qq的id配置")) {
                UmengTool.showDialogWithURl(activity, checkQQByself, UrlUtil.ALL_NO_CONFIG);
                return false;
            } else {
                UmengTool.showDialog(activity, checkQQByself);
                return false;
            }
        } else if (share_media == SHARE_MEDIA.WEIXIN) {
            String checkWxBySelf = UmengTool.checkWxBySelf(activity);
            if (checkWxBySelf.contains("不正确")) {
                if (checkWxBySelf.contains("WXEntryActivity配置不正确")) {
                    UmengTool.showDialogWithURl(activity, checkWxBySelf, UrlUtil.WX_NO_CALLBACK);
                } else {
                    UmengTool.showDialog(activity, checkWxBySelf);
                }
                UmengTool.checkWx(activity);
                return false;
            }
            Log.um(UmengTool.checkWxBySelf(activity));
            return true;
        } else if (share_media == SHARE_MEDIA.SINA) {
            if (UmengTool.checkSinaBySelf(activity).contains("不正确")) {
                UmengTool.checkSina(activity);
                return false;
            }
            Log.um(UmengTool.checkSinaBySelf(activity));
            return true;
        } else if (share_media != SHARE_MEDIA.FACEBOOK) {
            if (share_media == SHARE_MEDIA.VKONTAKTE) {
                Log.um(UmengTool.checkVKByself(activity));
            }
            if (share_media == SHARE_MEDIA.LINKEDIN) {
                Log.um(UmengTool.checkLinkin(activity));
            }
            if (share_media != SHARE_MEDIA.KAKAO) {
                return true;
            }
            Log.um(UmengTool.checkKakao(activity));
            return true;
        } else if (UmengTool.checkFBByself(activity).contains("没有")) {
            UmengTool.checkFacebook(activity);
            return false;
        } else {
            Log.um(UmengTool.checkFBByself(activity));
            return true;
        }
    }

    public void deleteOauth(Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        if (activity != null) {
            singleton.router.setmContext(activity);
            final Activity activity2 = activity;
            final SHARE_MEDIA share_media2 = share_media;
            final UMAuthListener uMAuthListener2 = uMAuthListener;
            new QueuedWork.DialogThread<Void>(activity) {
                /* access modifiers changed from: protected */
                public Object doInBackground() {
                    if (UMShareAPI.this.router == null) {
                        return null;
                    }
                    UMShareAPI.this.router.deleteOauth(activity2, share_media2, uMAuthListener2);
                    return null;
                }
            }.execute();
            return;
        }
        Log.d("UMerror", "deleteOauth activity is null");
    }

    public void doOauthVerify(Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        UMLog.putAuth();
        singleton.router.setmContext(activity);
        if (Config.DEBUG && !judgePlatform(activity, share_media)) {
            return;
        }
        if (activity != null) {
            final Activity activity2 = activity;
            final SHARE_MEDIA share_media2 = share_media;
            final UMAuthListener uMAuthListener2 = uMAuthListener;
            new QueuedWork.DialogThread<Void>(activity) {
                /* access modifiers changed from: protected */
                public Void doInBackground() {
                    if (UMShareAPI.this.router == null) {
                        SocialRouter unused = UMShareAPI.this.router = new SocialRouter(activity2);
                    }
                    UMShareAPI.this.router.doOauthVerify(activity2, share_media2, uMAuthListener2);
                    return null;
                }
            }.execute();
            return;
        }
        Log.d("UMerror", "doOauthVerify activity is null");
    }

    public void doShare(Activity activity, ShareAction shareAction, UMShareListener uMShareListener) {
        UMLog.putShare();
        final WeakReference weakReference = new WeakReference(activity);
        if (Config.DEBUG) {
            if (judgePlatform(activity, shareAction.getPlatform())) {
                UrlUtil.sharePrint(shareAction.getPlatform());
            } else {
                return;
            }
        }
        if (weakReference.get() == null || ((Activity) weakReference.get()).isFinishing()) {
            Log.d("UMerror", "Share activity is null");
            return;
        }
        singleton.router.setmContext(activity);
        final ShareAction shareAction2 = shareAction;
        final UMShareListener uMShareListener2 = uMShareListener;
        new QueuedWork.DialogThread<Void>((Context) weakReference.get()) {
            /* access modifiers changed from: protected */
            public Void doInBackground() {
                if (weakReference.get() != null && !((Activity) weakReference.get()).isFinishing()) {
                    if (UMShareAPI.this.router != null) {
                        UMShareAPI.this.router.share((Activity) weakReference.get(), shareAction2, uMShareListener2);
                    } else {
                        SocialRouter unused = UMShareAPI.this.router = new SocialRouter((Context) weakReference.get());
                        UMShareAPI.this.router.share((Activity) weakReference.get(), shareAction2, uMShareListener2);
                    }
                }
                return null;
            }
        }.execute();
    }

    public void fetchAuthResultWithBundle(Activity activity, Bundle bundle, UMAuthListener uMAuthListener) {
        this.router.fetchAuthResultWithBundle(activity, bundle, uMAuthListener);
    }

    public UMSSOHandler getHandler(SHARE_MEDIA share_media) {
        if (this.router != null) {
            return this.router.getHandler(share_media);
        }
        return null;
    }

    public void getPlatformInfo(Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        if (activity != null) {
            UMLog.putAuth();
            if (Config.DEBUG) {
                if (judgePlatform(activity, share_media)) {
                    UrlUtil.getInfoPrint(share_media);
                } else {
                    return;
                }
            }
            singleton.router.setmContext(activity);
            final Activity activity2 = activity;
            final SHARE_MEDIA share_media2 = share_media;
            final UMAuthListener uMAuthListener2 = uMAuthListener;
            new QueuedWork.DialogThread<Void>(activity) {
                /* access modifiers changed from: protected */
                public Object doInBackground() {
                    if (UMShareAPI.this.router == null) {
                        return null;
                    }
                    UMShareAPI.this.router.getPlatformInfo(activity2, share_media2, uMAuthListener2);
                    return null;
                }
            }.execute();
            return;
        }
        Log.d("UMerror", "getPlatformInfo activity argument is null");
    }

    public String getversion(Activity activity, SHARE_MEDIA share_media) {
        if (this.router != null) {
            return this.router.getSDKVersion(activity, share_media);
        }
        this.router = new SocialRouter(activity);
        return this.router.getSDKVersion(activity, share_media);
    }

    public boolean isAuthorize(Activity activity, SHARE_MEDIA share_media) {
        if (this.router != null) {
            return this.router.isAuthorize(activity, share_media);
        }
        this.router = new SocialRouter(activity);
        return this.router.isAuthorize(activity, share_media);
    }

    public boolean isInstall(Activity activity, SHARE_MEDIA share_media) {
        if (this.router != null) {
            return this.router.isInstall(activity, share_media);
        }
        this.router = new SocialRouter(activity);
        return this.router.isInstall(activity, share_media);
    }

    public boolean isSupport(Activity activity, SHARE_MEDIA share_media) {
        if (this.router != null) {
            return this.router.isSupport(activity, share_media);
        }
        this.router = new SocialRouter(activity);
        return this.router.isSupport(activity, share_media);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.router != null) {
            this.router.onActivityResult(i, i2, intent);
        } else {
            Log.v("auth fail", "router=null");
        }
        Log.um("onActivityResult =" + i + "  resultCode=" + i2);
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.router.onSaveInstanceState(bundle);
    }

    public void release() {
        this.router.release();
    }

    public void setShareConfig(UMShareConfig uMShareConfig) {
        this.router.setShareConfig(uMShareConfig);
    }
}
