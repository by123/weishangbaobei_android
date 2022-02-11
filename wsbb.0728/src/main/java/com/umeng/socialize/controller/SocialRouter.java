package com.umeng.socialize.controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import com.stub.StubApp;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.SocializeException;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.handler.UMMoreHandler;
import com.umeng.socialize.handler.UMSSOHandler;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.net.analytics.SocialAnalytics;
import com.umeng.socialize.net.utils.SocializeNetUtils;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.utils.UmengText;
import com.umeng.socialize.utils.UrlUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SocialRouter {
    private static final String BUNDLE_KEY_ACTION = "share_action";
    private static final String BUNDLE_KEY_AUTH_PLATFORM = "umeng_share_platform";
    private String APIVERSION = "6.4.5";
    private ParamsGuard guard;
    private SparseArray<UMAuthListener> mAuthListenerContainer;
    private SHARE_MEDIA mAuthPlatform;
    private Context mContext;
    private SparseArray<UMAuthListener> mFetchUserInfoListenerContainer;
    private SparseArray<UMShareListener> mShareListenerContainer;
    private final Map<SHARE_MEDIA, UMSSOHandler> platformHandlers = new HashMap();
    private final List<Pair<SHARE_MEDIA, String>> supportedPlatform = new ArrayList();

    static class ParamsGuard {
        private Map<SHARE_MEDIA, UMSSOHandler> handlers;

        public ParamsGuard(Map<SHARE_MEDIA, UMSSOHandler> map) {
            this.handlers = map;
        }

        private boolean checkContext(Context context) {
            if (context != null) {
                return true;
            }
            Log.e("Context is null");
            return false;
        }

        private boolean checkPlatformConfig(SHARE_MEDIA share_media) {
            PlatformConfig.Platform platform = PlatformConfig.configs.get(share_media);
            if (this.handlers.get(share_media) != null) {
                return true;
            }
            Log.url(UmengText.noJar(share_media), UrlUtil.ALL_NO_JAR);
            return false;
        }

        public boolean auth(Context context, SHARE_MEDIA share_media) {
            if (!checkContext(context)) {
                return false;
            }
            if (!checkPlatformConfig(share_media)) {
                return false;
            }
            if (this.handlers.get(share_media).isSupportAuth()) {
                return true;
            }
            Log.w(share_media.toString() + UmengText.NOT_SUPPORT_PLATFROM);
            return false;
        }

        public boolean share(ShareAction shareAction) {
            SHARE_MEDIA platform = shareAction.getPlatform();
            if (platform == null) {
                return false;
            }
            if ((platform != SHARE_MEDIA.SINA && platform != SHARE_MEDIA.QQ && platform != SHARE_MEDIA.WEIXIN) || PlatformConfig.configs.get(platform).isConfigured()) {
                return checkPlatformConfig(platform);
            }
            Log.um(UmengText.errorWithUrl(UmengText.noKey(platform), UrlUtil.ALL_NO_CONFIG));
            return false;
        }
    }

    public SocialRouter(Context context) {
        List<Pair<SHARE_MEDIA, String>> list = this.supportedPlatform;
        list.add(new Pair(SHARE_MEDIA.LAIWANG, "com.umeng.socialize.handler.UMLWHandler"));
        list.add(new Pair(SHARE_MEDIA.LAIWANG_DYNAMIC, "com.umeng.socialize.handler.UMLWHandler"));
        list.add(new Pair(SHARE_MEDIA.SINA, "com.umeng.socialize.handler.SinaSimplyHandler"));
        list.add(new Pair(SHARE_MEDIA.PINTEREST, "com.umeng.socialize.handler.UMPinterestHandler"));
        list.add(new Pair(SHARE_MEDIA.QZONE, "com.umeng.qq.handler.UmengQZoneHandler"));
        list.add(new Pair(SHARE_MEDIA.QQ, "com.umeng.qq.handler.UmengQQHandler"));
        list.add(new Pair(SHARE_MEDIA.RENREN, "com.umeng.socialize.handler.RenrenSsoHandler"));
        list.add(new Pair(SHARE_MEDIA.TENCENT, "com.umeng.socialize.handler.TencentWBSsoHandler"));
        list.add(new Pair(SHARE_MEDIA.WEIXIN, "com.umeng.weixin.handler.UmengWXHandler"));
        list.add(new Pair(SHARE_MEDIA.WEIXIN_CIRCLE, "com.umeng.weixin.handler.UmengWXHandler"));
        list.add(new Pair(SHARE_MEDIA.WEIXIN_FAVORITE, "com.umeng.weixin.handler.UmengWXHandler"));
        list.add(new Pair(SHARE_MEDIA.YIXIN, "com.umeng.socialize.handler.UMYXHandler"));
        list.add(new Pair(SHARE_MEDIA.YIXIN_CIRCLE, "com.umeng.socialize.handler.UMYXHandler"));
        list.add(new Pair(SHARE_MEDIA.EMAIL, "com.umeng.socialize.handler.EmailHandler"));
        list.add(new Pair(SHARE_MEDIA.EVERNOTE, "com.umeng.socialize.handler.UMEvernoteHandler"));
        list.add(new Pair(SHARE_MEDIA.FACEBOOK, "com.umeng.socialize.handler.UMFacebookHandler"));
        list.add(new Pair(SHARE_MEDIA.FACEBOOK_MESSAGER, "com.umeng.socialize.handler.UMFacebookHandler"));
        list.add(new Pair(SHARE_MEDIA.FLICKR, "com.umeng.socialize.handler.UMFlickrHandler"));
        list.add(new Pair(SHARE_MEDIA.FOURSQUARE, "com.umeng.socialize.handler.UMFourSquareHandler"));
        list.add(new Pair(SHARE_MEDIA.GOOGLEPLUS, "com.umeng.socialize.handler.UMGooglePlusHandler"));
        list.add(new Pair(SHARE_MEDIA.INSTAGRAM, "com.umeng.socialize.handler.UMInstagramHandler"));
        list.add(new Pair(SHARE_MEDIA.KAKAO, "com.umeng.socialize.handler.UMKakaoHandler"));
        list.add(new Pair(SHARE_MEDIA.LINE, "com.umeng.socialize.handler.UMLineHandler"));
        list.add(new Pair(SHARE_MEDIA.LINKEDIN, "com.umeng.socialize.handler.UMLinkedInHandler"));
        list.add(new Pair(SHARE_MEDIA.POCKET, "com.umeng.socialize.handler.UMPocketHandler"));
        list.add(new Pair(SHARE_MEDIA.WHATSAPP, "com.umeng.socialize.handler.UMWhatsAppHandler"));
        list.add(new Pair(SHARE_MEDIA.YNOTE, "com.umeng.socialize.handler.UMYNoteHandler"));
        list.add(new Pair(SHARE_MEDIA.SMS, "com.umeng.socialize.handler.SmsHandler"));
        list.add(new Pair(SHARE_MEDIA.DOUBAN, "com.umeng.socialize.handler.DoubanHandler"));
        list.add(new Pair(SHARE_MEDIA.TUMBLR, "com.umeng.socialize.handler.UMTumblrHandler"));
        list.add(new Pair(SHARE_MEDIA.TWITTER, "com.umeng.socialize.handler.TwitterHandler"));
        list.add(new Pair(SHARE_MEDIA.ALIPAY, "com.umeng.socialize.handler.AlipayHandler"));
        list.add(new Pair(SHARE_MEDIA.MORE, "com.umeng.socialize.handler.UMMoreHandler"));
        list.add(new Pair(SHARE_MEDIA.DINGTALK, "com.umeng.socialize.handler.UMDingSSoHandler"));
        list.add(new Pair(SHARE_MEDIA.VKONTAKTE, "com.umeng.socialize.handler.UMVKHandler"));
        list.add(new Pair(SHARE_MEDIA.DROPBOX, "com.umeng.socialize.handler.UMDropBoxHandler"));
        this.guard = new ParamsGuard(this.platformHandlers);
        this.mContext = null;
        this.mAuthListenerContainer = new SparseArray<>();
        this.mShareListenerContainer = new SparseArray<>();
        this.mFetchUserInfoListenerContainer = new SparseArray<>();
        this.mContext = context;
        init();
    }

    private void checkAppkey(Context context) {
        String appkey = SocializeUtils.getAppkey(context);
        if (TextUtils.isEmpty(appkey)) {
            throw new SocializeException(UmengText.errorWithUrl(UmengText.APPKEY_NOT_FOUND, UrlUtil.ALL_NO_APPKEY));
        } else if (SocializeNetUtils.isConSpeCharacters(appkey)) {
            throw new SocializeException(UmengText.errorWithUrl(UmengText.APPKEY_NOT_FOUND, UrlUtil.ALL_ERROR_APPKEY));
        } else if (SocializeNetUtils.isSelfAppkey(appkey)) {
            throw new SocializeException(UmengText.errorWithUrl(UmengText.APPKEY_NOT_FOUND, UrlUtil.ALL_ERROR_APPKEY));
        }
    }

    private void clearListener() {
        synchronized (this) {
            this.mAuthListenerContainer.clear();
            this.mShareListenerContainer.clear();
            this.mFetchUserInfoListenerContainer.clear();
        }
    }

    /* access modifiers changed from: private */
    public UMAuthListener getAndRemoveAuthListener(int i) {
        UMAuthListener uMAuthListener;
        synchronized (this) {
            this.mAuthPlatform = null;
            uMAuthListener = this.mAuthListenerContainer.get(i, (Object) null);
            if (uMAuthListener != null) {
                this.mAuthListenerContainer.remove(i);
            }
        }
        return uMAuthListener;
    }

    /* access modifiers changed from: private */
    public UMAuthListener getAndRemoveFetchUserInfoListener(int i) {
        UMAuthListener uMAuthListener;
        synchronized (this) {
            uMAuthListener = this.mFetchUserInfoListenerContainer.get(i, (Object) null);
            if (uMAuthListener != null) {
                this.mFetchUserInfoListenerContainer.remove(i);
            }
        }
        return uMAuthListener;
    }

    /* access modifiers changed from: private */
    public UMShareListener getAndRemoveShareListener(int i) {
        UMShareListener uMShareListener;
        synchronized (this) {
            uMShareListener = this.mShareListenerContainer.get(i, (Object) null);
            if (uMShareListener != null) {
                this.mShareListenerContainer.remove(i);
            }
        }
        return uMShareListener;
    }

    private UMAuthListener getAuthListener(final int i, final String str) {
        return new UMAuthListener() {
            public void onCancel(SHARE_MEDIA share_media, int i) {
                UMAuthListener access$100 = SocialRouter.this.getAndRemoveAuthListener(i);
                if (access$100 != null) {
                    access$100.onCancel(share_media, i);
                }
                if (ContextUtil.getContext() != null) {
                    SocialAnalytics.authendt(ContextUtil.getContext(), share_media.toString().toLowerCase(), "cancel", "", str);
                }
            }

            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                UMAuthListener access$100 = SocialRouter.this.getAndRemoveAuthListener(i);
                if (access$100 != null) {
                    access$100.onComplete(share_media, i, map);
                }
                if (ContextUtil.getContext() != null) {
                    SocialAnalytics.authendt(ContextUtil.getContext(), share_media.toString().toLowerCase(), "success", "", str);
                }
            }

            public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
                UMAuthListener access$100 = SocialRouter.this.getAndRemoveAuthListener(i);
                if (access$100 != null) {
                    access$100.onError(share_media, i, th);
                }
                if (th != null) {
                    Log.um("error:" + th.getMessage());
                } else {
                    Log.um("error:null");
                }
                if (ContextUtil.getContext() != null && th != null) {
                    SocialAnalytics.authendt(ContextUtil.getContext(), share_media.toString().toLowerCase(), "fail", th.getMessage(), str);
                }
            }

            public void onStart(SHARE_MEDIA share_media) {
                UMAuthListener access$100 = SocialRouter.this.getAndRemoveAuthListener(i);
                if (access$100 != null) {
                    access$100.onStart(share_media);
                }
            }
        };
    }

    private UMSSOHandler getHandler(int i) {
        int i2 = 10103;
        if (!(i == 10103 || i == 11101)) {
            i2 = i;
        }
        if (i == 64207 || i == 64206 || i == 64208) {
            i2 = 64206;
        }
        if (i == 32973 || i == 765) {
            i2 = 5659;
        }
        if (i == 5650) {
            i2 = 5659;
        }
        for (UMSSOHandler next : this.platformHandlers.values()) {
            if (next != null && i2 == next.getRequestCode()) {
                return next;
            }
        }
        return null;
    }

    private void init() {
        for (Pair next : this.supportedPlatform) {
            this.platformHandlers.put(next.first, (next.first == SHARE_MEDIA.WEIXIN_CIRCLE || next.first == SHARE_MEDIA.WEIXIN_FAVORITE) ? this.platformHandlers.get(SHARE_MEDIA.WEIXIN) : next.first == SHARE_MEDIA.FACEBOOK_MESSAGER ? this.platformHandlers.get(SHARE_MEDIA.FACEBOOK) : next.first == SHARE_MEDIA.YIXIN_CIRCLE ? this.platformHandlers.get(SHARE_MEDIA.YIXIN) : next.first == SHARE_MEDIA.LAIWANG_DYNAMIC ? this.platformHandlers.get(SHARE_MEDIA.LAIWANG) : next.first == SHARE_MEDIA.TENCENT ? newHandler((String) next.second) : next.first == SHARE_MEDIA.MORE ? new UMMoreHandler() : next.first == SHARE_MEDIA.SINA ? Config.isUmengSina.booleanValue() ? newHandler((String) next.second) : newHandler("com.umeng.socialize.handler.SinaSsoHandler") : next.first == SHARE_MEDIA.WEIXIN ? Config.isUmengWx.booleanValue() ? newHandler((String) next.second) : newHandler("com.umeng.socialize.handler.UMWXHandler") : next.first == SHARE_MEDIA.QQ ? Config.isUmengQQ.booleanValue() ? newHandler((String) next.second) : newHandler("com.umeng.socialize.handler.UMQQSsoHandler") : next.first == SHARE_MEDIA.QZONE ? Config.isUmengQQ.booleanValue() ? newHandler((String) next.second) : newHandler("com.umeng.socialize.handler.QZoneSsoHandler") : newHandler((String) next.second));
        }
    }

    private UMSSOHandler newHandler(String str) {
        UMSSOHandler uMSSOHandler;
        try {
            uMSSOHandler = (UMSSOHandler) Class.forName(str).newInstance();
        } catch (Exception e) {
            uMSSOHandler = null;
        }
        if (uMSSOHandler != null) {
            return uMSSOHandler;
        }
        if (str.contains("SinaSimplyHandler")) {
            Config.isUmengSina = false;
            return newHandler("com.umeng.socialize.handler.SinaSsoHandler");
        } else if (str.contains("UmengQQHandler")) {
            Config.isUmengQQ = false;
            return newHandler("com.umeng.socialize.handler.UMQQSsoHandler");
        } else if (str.contains("UmengQZoneHandler")) {
            Config.isUmengQQ = false;
            return newHandler("com.umeng.socialize.handler.QZoneSsoHandler");
        } else if (!str.contains("UmengWXHandler")) {
            return uMSSOHandler;
        } else {
            Config.isUmengWx = false;
            return newHandler("com.umeng.socialize.handler.UMWXHandler");
        }
    }

    private void parseShareContent(ShareContent shareContent) {
        Log.umd("sharetext=" + shareContent.mText);
        if (shareContent.mMedia != null) {
            if (shareContent.mMedia instanceof UMImage) {
                UMImage uMImage = (UMImage) shareContent.mMedia;
                if (uMImage.isUrlMedia()) {
                    Log.umd("urlimage=" + uMImage.asUrlImage() + " compressStyle=" + uMImage.compressStyle + " isLoadImgByCompress=" + uMImage.isLoadImgByCompress + "  compressFormat=" + uMImage.compressFormat);
                } else {
                    byte[] asBinImage = uMImage.asBinImage();
                    StringBuilder sb = new StringBuilder();
                    sb.append("localimage=");
                    sb.append(asBinImage == null ? 0 : asBinImage.length);
                    sb.append(" compressStyle=");
                    sb.append(uMImage.compressStyle);
                    sb.append(" isLoadImgByCompress=");
                    sb.append(uMImage.isLoadImgByCompress);
                    sb.append("  compressFormat=");
                    sb.append(uMImage.compressFormat);
                    Log.umd(sb.toString());
                }
                if (uMImage.getThumbImage() != null) {
                    UMImage thumbImage = uMImage.getThumbImage();
                    if (thumbImage.isUrlMedia()) {
                        Log.umd("urlthumbimage=" + thumbImage.asUrlImage());
                    } else {
                        Log.umd("localthumbimage=" + thumbImage.asBinImage().length);
                    }
                }
            }
            if (shareContent.mMedia instanceof UMVideo) {
                UMVideo uMVideo = (UMVideo) shareContent.mMedia;
                Log.umd("video=" + uMVideo.toUrl());
                Log.umd("video title=" + uMVideo.getTitle());
                Log.umd("video desc=" + uMVideo.getDescription());
                if (TextUtils.isEmpty(uMVideo.toUrl())) {
                    Log.um(UmengText.urlEmpty(0));
                }
                if (uMVideo.getThumbImage() != null) {
                    if (uMVideo.getThumbImage().isUrlMedia()) {
                        Log.umd("urlthumbimage=" + uMVideo.getThumbImage().asUrlImage());
                    } else {
                        Log.umd("localthumbimage=" + uMVideo.getThumbImage().asBinImage());
                    }
                }
            }
            if (shareContent.mMedia instanceof UMusic) {
                UMusic uMusic = (UMusic) shareContent.mMedia;
                Log.umd("music=" + uMusic.toUrl());
                Log.umd("music title=" + uMusic.getTitle());
                Log.umd("music desc=" + uMusic.getDescription());
                Log.umd("music target=" + uMusic.getmTargetUrl());
                if (TextUtils.isEmpty(uMusic.toUrl())) {
                    Log.um(UmengText.urlEmpty(1));
                }
                if (uMusic.getThumbImage() != null) {
                    if (uMusic.getThumbImage().isUrlMedia()) {
                        Log.umd("urlthumbimage=" + uMusic.getThumbImage().asUrlImage());
                    } else {
                        Log.umd("localthumbimage=" + uMusic.getThumbImage().asBinImage());
                    }
                }
            }
            if (shareContent.mMedia instanceof UMWeb) {
                UMWeb uMWeb = (UMWeb) shareContent.mMedia;
                Log.umd("web=" + uMWeb.toUrl());
                Log.umd("web title=" + uMWeb.getTitle());
                Log.umd("web desc=" + uMWeb.getDescription());
                if (uMWeb.getThumbImage() != null) {
                    if (uMWeb.getThumbImage().isUrlMedia()) {
                        Log.umd("urlthumbimage=" + uMWeb.getThumbImage().asUrlImage());
                    } else {
                        Log.umd("localthumbimage=" + uMWeb.getThumbImage().asBinImage());
                    }
                }
                if (TextUtils.isEmpty(uMWeb.toUrl())) {
                    Log.um(UmengText.urlEmpty(2));
                }
            }
        }
        if (shareContent.file != null) {
            Log.umd("file=" + shareContent.file.getName());
        }
    }

    private void safelyCloseDialog(Dialog dialog) {
        if (dialog != null) {
            try {
                dialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveAuthListener(int i, UMAuthListener uMAuthListener) {
        synchronized (this) {
            this.mAuthListenerContainer.put(i, uMAuthListener);
        }
    }

    private void saveFetchUserInfoListener(int i, UMAuthListener uMAuthListener) {
        synchronized (this) {
            this.mFetchUserInfoListenerContainer.put(i, uMAuthListener);
        }
    }

    private void saveShareListener(int i, UMShareListener uMShareListener) {
        synchronized (this) {
            this.mShareListenerContainer.put(i, uMShareListener);
        }
    }

    private void setAuthListener(SHARE_MEDIA share_media, UMAuthListener uMAuthListener, UMSSOHandler uMSSOHandler, String str) {
        if (!uMSSOHandler.isHasAuthListener()) {
            int ordinal = share_media.ordinal();
            saveAuthListener(ordinal, uMAuthListener);
            uMSSOHandler.setAuthListener(getAuthListener(ordinal, str));
        }
    }

    public void deleteOauth(Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        if (this.guard.auth(activity, share_media)) {
            if (uMAuthListener == null) {
                uMAuthListener = new UMAuthListener() {
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                    }

                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                    }

                    public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
                    }

                    public void onStart(SHARE_MEDIA share_media) {
                    }
                };
            }
            this.platformHandlers.get(share_media).onCreate(activity, PlatformConfig.getPlatform(share_media));
            this.platformHandlers.get(share_media).deleteAuth(uMAuthListener);
        }
    }

    public void doOauthVerify(Activity activity, final SHARE_MEDIA share_media, final UMAuthListener uMAuthListener) {
        if (this.guard.auth(activity, share_media)) {
            UMSSOHandler uMSSOHandler = this.platformHandlers.get(share_media);
            uMSSOHandler.onCreate(activity, PlatformConfig.getPlatform(share_media));
            String valueOf = String.valueOf(System.currentTimeMillis());
            if (ContextUtil.getContext() != null) {
                SocialAnalytics.authstart(ContextUtil.getContext(), share_media, uMSSOHandler.getSDKVersion(), uMSSOHandler.isInstall(), valueOf);
            }
            int ordinal = share_media.ordinal();
            saveAuthListener(ordinal, uMAuthListener);
            UMAuthListener authListener = getAuthListener(ordinal, valueOf);
            QueuedWork.runInMain(new Runnable() {
                public void run() {
                    uMAuthListener.onStart(share_media);
                }
            });
            uMSSOHandler.authorize(authListener);
            this.mAuthPlatform = share_media;
        }
    }

    public void fetchAuthResultWithBundle(Activity activity, Bundle bundle, UMAuthListener uMAuthListener) {
        SHARE_MEDIA convertToEmun;
        UMSSOHandler handler;
        if (bundle != null && uMAuthListener != null) {
            String string = bundle.getString(BUNDLE_KEY_AUTH_PLATFORM, (String) null);
            if (bundle.getInt(BUNDLE_KEY_ACTION, -1) == 0 && !TextUtils.isEmpty(string) && (convertToEmun = SHARE_MEDIA.convertToEmun(string)) != null) {
                if (convertToEmun == SHARE_MEDIA.QQ) {
                    handler = this.platformHandlers.get(convertToEmun);
                    handler.onCreate(activity, PlatformConfig.getPlatform(convertToEmun));
                } else {
                    handler = getHandler(convertToEmun);
                }
                if (handler != null) {
                    setAuthListener(convertToEmun, uMAuthListener, handler, String.valueOf(System.currentTimeMillis()));
                }
            }
        }
    }

    public UMSSOHandler getHandler(SHARE_MEDIA share_media) {
        UMSSOHandler uMSSOHandler = this.platformHandlers.get(share_media);
        if (uMSSOHandler != null) {
            uMSSOHandler.onCreate(this.mContext, PlatformConfig.getPlatform(share_media));
        }
        return uMSSOHandler;
    }

    public void getPlatformInfo(final Activity activity, final SHARE_MEDIA share_media, final UMAuthListener uMAuthListener) {
        if (this.guard.auth(activity, share_media)) {
            UMSSOHandler uMSSOHandler = this.platformHandlers.get(share_media);
            uMSSOHandler.onCreate(activity, PlatformConfig.getPlatform(share_media));
            final String valueOf = String.valueOf(System.currentTimeMillis());
            if (ContextUtil.getContext() != null) {
                SocialAnalytics.getInfostart(ContextUtil.getContext(), share_media, uMSSOHandler.getSDKVersion(), valueOf);
            }
            final int ordinal = share_media.ordinal();
            saveFetchUserInfoListener(ordinal, uMAuthListener);
            AnonymousClass2 r3 = new UMAuthListener() {
                public void onCancel(SHARE_MEDIA share_media, int i) {
                    UMAuthListener access$000 = SocialRouter.this.getAndRemoveFetchUserInfoListener(ordinal);
                    if (access$000 != null) {
                        access$000.onCancel(share_media, i);
                    }
                    if (ContextUtil.getContext() != null) {
                        SocialAnalytics.getInfoendt(ContextUtil.getContext(), share_media.toString().toLowerCase(), "cancel", "", valueOf);
                    }
                }

                public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                    UMAuthListener access$000 = SocialRouter.this.getAndRemoveFetchUserInfoListener(ordinal);
                    if (access$000 != null) {
                        access$000.onComplete(share_media, i, map);
                    }
                    if (ContextUtil.getContext() != null) {
                        SocialAnalytics.getInfoendt(ContextUtil.getContext(), share_media.toString().toLowerCase(), "success", "", valueOf);
                    }
                }

                public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
                    UMAuthListener access$000 = SocialRouter.this.getAndRemoveFetchUserInfoListener(ordinal);
                    if (access$000 != null) {
                        access$000.onError(share_media, i, th);
                    }
                    if (th != null) {
                        Log.toast(activity, UmengText.AUTH_FAIL_LOG);
                        Log.um(th.getMessage());
                        Log.um(UmengText.SOLVE + UrlUtil.ALL_AUTHFAIL);
                    } else {
                        Log.um("null");
                    }
                    if (ContextUtil.getContext() != null && th != null) {
                        SocialAnalytics.getInfoendt(ContextUtil.getContext(), share_media.toString().toLowerCase(), "fail", th.getMessage(), valueOf);
                    }
                }

                public void onStart(SHARE_MEDIA share_media) {
                    UMAuthListener access$000 = SocialRouter.this.getAndRemoveFetchUserInfoListener(ordinal);
                    if (access$000 != null) {
                        access$000.onStart(share_media);
                    }
                }
            };
            QueuedWork.runInMain(new Runnable() {
                public void run() {
                    uMAuthListener.onStart(share_media);
                }
            });
            uMSSOHandler.getPlatformInfo(r3);
        }
    }

    public String getSDKVersion(Activity activity, SHARE_MEDIA share_media) {
        if (!this.guard.auth(activity, share_media)) {
            return "";
        }
        this.platformHandlers.get(share_media).onCreate(activity, PlatformConfig.getPlatform(share_media));
        return this.platformHandlers.get(share_media).getSDKVersion();
    }

    public SHARE_MEDIA getShareMediaByrequestCode(int i) {
        return (i == 10103 || i == 11101) ? SHARE_MEDIA.QQ : (i == 32973 || i == 765) ? SHARE_MEDIA.SINA : SHARE_MEDIA.QQ;
    }

    public boolean isAuthorize(Activity activity, SHARE_MEDIA share_media) {
        if (!this.guard.auth(activity, share_media)) {
            return false;
        }
        this.platformHandlers.get(share_media).onCreate(activity, PlatformConfig.getPlatform(share_media));
        return this.platformHandlers.get(share_media).isAuthorize();
    }

    public boolean isInstall(Activity activity, SHARE_MEDIA share_media) {
        if (!this.guard.auth(activity, share_media)) {
            return false;
        }
        this.platformHandlers.get(share_media).onCreate(activity, PlatformConfig.getPlatform(share_media));
        return this.platformHandlers.get(share_media).isInstall();
    }

    public boolean isSupport(Activity activity, SHARE_MEDIA share_media) {
        if (!this.guard.auth(activity, share_media)) {
            return false;
        }
        this.platformHandlers.get(share_media).onCreate(activity, PlatformConfig.getPlatform(share_media));
        return this.platformHandlers.get(share_media).isSupport();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        UMSSOHandler handler = getHandler(i);
        if (handler != null) {
            handler.onActivityResult(i, i2, intent);
        }
    }

    @Deprecated
    public void onCreate(Activity activity, int i, UMAuthListener uMAuthListener) {
        UMSSOHandler handler = getHandler(i);
        if (handler == null) {
            return;
        }
        if (i == 10103 || i == 11101) {
            handler.onCreate(activity, PlatformConfig.getPlatform(getShareMediaByrequestCode(i)));
            setAuthListener(SHARE_MEDIA.QQ, uMAuthListener, handler, String.valueOf(System.currentTimeMillis()));
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        int i;
        String str = "";
        if (this.mAuthPlatform == null || !(this.mAuthPlatform == SHARE_MEDIA.WEIXIN || this.mAuthPlatform == SHARE_MEDIA.QQ || this.mAuthPlatform == SHARE_MEDIA.SINA)) {
            i = -1;
        } else {
            str = this.mAuthPlatform.toString();
            i = 0;
        }
        bundle.putString(BUNDLE_KEY_AUTH_PLATFORM, str);
        bundle.putInt(BUNDLE_KEY_ACTION, i);
        this.mAuthPlatform = null;
    }

    public void release() {
        clearListener();
        UMSSOHandler uMSSOHandler = this.platformHandlers.get(SHARE_MEDIA.SINA);
        if (uMSSOHandler != null) {
            uMSSOHandler.release();
        }
        UMSSOHandler uMSSOHandler2 = this.platformHandlers.get(SHARE_MEDIA.MORE);
        if (uMSSOHandler2 != null) {
            uMSSOHandler2.release();
        }
        UMSSOHandler uMSSOHandler3 = this.platformHandlers.get(SHARE_MEDIA.DINGTALK);
        if (uMSSOHandler3 != null) {
            uMSSOHandler3.release();
        }
        UMSSOHandler uMSSOHandler4 = this.platformHandlers.get(SHARE_MEDIA.WEIXIN);
        if (uMSSOHandler4 != null) {
            uMSSOHandler4.release();
        }
        UMSSOHandler uMSSOHandler5 = this.platformHandlers.get(SHARE_MEDIA.QQ);
        if (uMSSOHandler5 != null) {
            uMSSOHandler5.release();
        }
        this.mAuthPlatform = null;
    }

    public void setShareConfig(UMShareConfig uMShareConfig) {
        if (this.platformHandlers != null && !this.platformHandlers.isEmpty()) {
            for (Map.Entry<SHARE_MEDIA, UMSSOHandler> value : this.platformHandlers.entrySet()) {
                UMSSOHandler uMSSOHandler = (UMSSOHandler) value.getValue();
                if (uMSSOHandler != null) {
                    uMSSOHandler.setShareConfig(uMShareConfig);
                }
            }
        }
    }

    public void setmContext(Context context) {
        this.mContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
    }

    public void share(Activity activity, final ShareAction shareAction, final UMShareListener uMShareListener) {
        checkAppkey(activity);
        WeakReference weakReference = new WeakReference(activity);
        if (this.guard.share(shareAction)) {
            if (Config.DEBUG) {
                Log.umd("api version:" + this.APIVERSION);
                Log.umd("sharemedia=" + shareAction.getPlatform().toString());
                Log.umd(UmengText.SHARE_STYLE + shareAction.getShareContent().getShareType());
                parseShareContent(shareAction.getShareContent());
            }
            SHARE_MEDIA platform = shareAction.getPlatform();
            UMSSOHandler uMSSOHandler = this.platformHandlers.get(platform);
            uMSSOHandler.onCreate((Context) weakReference.get(), PlatformConfig.getPlatform(platform));
            if (!platform.toString().equals("TENCENT") && !platform.toString().equals("RENREN") && !platform.toString().equals("DOUBAN")) {
                if (platform.toString().equals("WEIXIN")) {
                    SocialAnalytics.log((Context) weakReference.get(), "wxsession", shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
                } else if (platform.toString().equals("WEIXIN_CIRCLE")) {
                    SocialAnalytics.log((Context) weakReference.get(), "wxtimeline", shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
                } else if (platform.toString().equals("WEIXIN_FAVORITE")) {
                    SocialAnalytics.log((Context) weakReference.get(), "wxfavorite", shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
                } else {
                    SocialAnalytics.log((Context) weakReference.get(), platform.toString().toLowerCase(), shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
                }
            }
            final String valueOf = String.valueOf(System.currentTimeMillis());
            if (ContextUtil.getContext() != null) {
                SocialAnalytics.sharestart(ContextUtil.getContext(), shareAction.getPlatform(), uMSSOHandler.getSDKVersion(), uMSSOHandler.isInstall(), shareAction.getShareContent().getShareType(), valueOf, shareAction.getShareContent().mMedia instanceof UMImage ? ((UMImage) shareAction.getShareContent().mMedia).isHasWaterMark() : false);
            }
            final int ordinal = platform.ordinal();
            saveShareListener(ordinal, uMShareListener);
            final AnonymousClass6 r1 = new UMShareListener() {
                public void onCancel(SHARE_MEDIA share_media) {
                    if (ContextUtil.getContext() != null) {
                        SocialAnalytics.shareend(ContextUtil.getContext(), share_media.toString().toLowerCase(), "cancel", "", valueOf);
                    }
                    UMShareListener access$200 = SocialRouter.this.getAndRemoveShareListener(ordinal);
                    if (access$200 != null) {
                        access$200.onCancel(share_media);
                    }
                }

                public void onError(SHARE_MEDIA share_media, Throwable th) {
                    if (!(ContextUtil.getContext() == null || th == null)) {
                        SocialAnalytics.shareend(ContextUtil.getContext(), share_media.toString().toLowerCase(), "fail", th.getMessage(), valueOf);
                    }
                    UMShareListener access$200 = SocialRouter.this.getAndRemoveShareListener(ordinal);
                    if (access$200 != null) {
                        access$200.onError(share_media, th);
                    }
                    if (th != null) {
                        Log.um("error:" + th.getMessage());
                        Log.um(UmengText.SOLVE + UrlUtil.ALL_SHAREFAIL);
                        return;
                    }
                    Log.um("error:null");
                }

                public void onResult(SHARE_MEDIA share_media) {
                    if (ContextUtil.getContext() != null) {
                        SocialAnalytics.shareend(ContextUtil.getContext(), share_media.toString().toLowerCase(), "success", "", valueOf);
                    }
                    UMShareListener access$200 = SocialRouter.this.getAndRemoveShareListener(ordinal);
                    if (access$200 != null) {
                        access$200.onResult(share_media);
                    }
                }

                public void onStart(SHARE_MEDIA share_media) {
                    UMShareListener access$200 = SocialRouter.this.getAndRemoveShareListener(ordinal);
                    if (access$200 != null) {
                        access$200.onStart(share_media);
                    }
                }
            };
            if (!shareAction.getUrlValid()) {
                QueuedWork.runInMain(new Runnable() {
                    public void run() {
                        UMShareListener uMShareListener = r1;
                        SHARE_MEDIA platform = shareAction.getPlatform();
                        uMShareListener.onError(platform, new Throwable(UmengErrorCode.NotInstall.getMessage() + UmengText.WEB_HTTP));
                    }
                });
                return;
            }
            QueuedWork.runInMain(new Runnable() {
                public void run() {
                    if (uMShareListener != null) {
                        uMShareListener.onStart(shareAction.getPlatform());
                    }
                }
            });
            uMSSOHandler.share(shareAction.getShareContent(), r1);
        }
    }
}
