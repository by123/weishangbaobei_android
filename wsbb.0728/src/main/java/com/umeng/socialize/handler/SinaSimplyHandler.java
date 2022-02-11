package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.stub.StubApp;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.HandlerRequestCode;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.media.AppInfo;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.WBShareCallBackActivity;
import com.umeng.socialize.net.base.SocializeClient;
import com.umeng.socialize.net.c;
import com.umeng.socialize.net.d;
import com.umeng.socialize.net.h;
import com.umeng.socialize.net.i;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.sina.SinaAPI;
import com.umeng.socialize.sina.auth.AuthInfo;
import com.umeng.socialize.sina.helper.MD5;
import com.umeng.socialize.sina.message.BaseResponse;
import com.umeng.socialize.sina.message.SendMultiMessageToWeiboRequest;
import com.umeng.socialize.sina.params.ShareRequestParam;
import com.umeng.socialize.sina.params.WeiboParameters;
import com.umeng.socialize.sina.util.Utility;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.utils.UmengText;
import com.umeng.socialize.utils.UrlUtil;
import com.umeng.socialize.view.OauthDialog;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class SinaSimplyHandler extends UMSSOHandler {
    public static final String SCOPE = "email,direct_messages_read,direct_messages_write,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog,invitation_write";
    private static final Uri a = Uri.parse("content://com.sina.weibo.sdkProvider/query/package");
    private static final Uri b = Uri.parse("content://com.sina.weibo.sdkProvider/query/package");
    private static final String c = "com.sina.weibo.action.sdkidentity";
    private static final String d = "weibo_for_sdk.json";
    private static final String e = "sina2/main?uid";
    private static String j = "";
    private static String k = "";
    public static String keyHash = "";
    private static final String p = "com.sina.weibo.business.RemoteSSOService";
    private static final String q = "userName";
    private static final String r = "id";
    public final int ERR_CANCEL = 1;
    public final int ERR_FAIL = 2;
    public final int ERR_OK = 0;
    private Context f = null;
    /* access modifiers changed from: private */
    public a g = null;
    private WeiboMultiMessage h;
    private String i = "6.4.5";
    private UMAuthListener l;
    private SHARE_MEDIA m = SHARE_MEDIA.SINA;
    private String n = "";
    /* access modifiers changed from: private */
    public String o = "";
    private AuthInfo s;
    private AppInfo t;
    private SinaAPI u;
    private UMShareListener v;

    class a implements UMAuthListener {
        private UMAuthListener b = null;

        a(UMAuthListener uMAuthListener) {
            this.b = uMAuthListener;
        }

        public void onCancel(SHARE_MEDIA share_media, int i) {
            if (this.b != null) {
                this.b.onCancel(share_media, i);
            }
        }

        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            if (SinaSimplyHandler.this.g != null) {
                SinaSimplyHandler.this.g.a(map).g();
            }
            SinaSimplyHandler.this.uploadAuthData(SocializeUtils.mapToBundle(map));
            if (this.b != null) {
                this.b.onComplete(share_media, i, map);
            }
        }

        public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
            if (this.b != null) {
                this.b.onError(share_media, i, th);
            }
        }

        public void onStart(SHARE_MEDIA share_media) {
        }
    }

    private AppInfo a(Context context) {
        boolean z = true;
        AppInfo b2 = b(context);
        AppInfo c2 = c(context);
        boolean z2 = b2 != null;
        if (c2 == null) {
            z = false;
        }
        if (!z2 || !z) {
            if (z2) {
                return b2;
            }
            if (!z) {
                return null;
            }
        } else if (b2.c() >= c2.c()) {
            return b2;
        }
        return c2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b0 A[SYNTHETIC, Splitter:B:40:0x00b0] */
    private AppInfo a(String str) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byte[] bArr = new byte[4096];
            inputStream = this.f.createPackageContext(str, 2).getAssets().open(d);
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    int read = inputStream.read(bArr, 0, 4096);
                    if (read == -1) {
                        break;
                    }
                    sb.append(new String(bArr, 0, read));
                }
                if (!TextUtils.isEmpty(sb.toString()) && validateWeiboSign(this.f, str)) {
                    JSONObject jSONObject = new JSONObject(sb.toString());
                    int optInt = jSONObject.optInt("support_api", -1);
                    AppInfo appInfo = new AppInfo();
                    appInfo.a(str);
                    appInfo.a(optInt);
                    appInfo.b(jSONObject.optString("authActivityName", "com.sina.weibo.SSOActivity"));
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                            return appInfo;
                        } catch (IOException e2) {
                            Log.um(e2.getMessage());
                        }
                    }
                    return appInfo;
                } else if (inputStream == null) {
                    return null;
                } else {
                    try {
                        inputStream.close();
                        return null;
                    } catch (IOException e3) {
                        Log.um(e3.getMessage());
                        return null;
                    }
                }
            } catch (Exception e4) {
                e = e4;
                try {
                    Log.um(e.getMessage());
                    if (inputStream == null) {
                        return null;
                    }
                    try {
                        inputStream.close();
                        return null;
                    } catch (IOException e5) {
                        Log.um(e5.getMessage());
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    inputStream2 = inputStream;
                    if (inputStream2 != null) {
                    }
                    throw th;
                }
            }
        } catch (Exception e6) {
            e = e6;
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            if (inputStream2 != null) {
                try {
                    inputStream2.close();
                } catch (IOException e7) {
                    Log.um(e7.getMessage());
                }
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public void a(final UMAuthListener uMAuthListener) {
        final WeiboParameters weiboParameters = new WeiboParameters(this.n);
        weiboParameters.put("client_id", this.n);
        weiboParameters.put("redirect_uri", this.o);
        weiboParameters.put("scope", SCOPE);
        weiboParameters.put("response_type", ShareRequestParam.RESP_UPLOAD_PIC_PARAM_CODE);
        weiboParameters.put("version", "0031405000");
        weiboParameters.put("luicode", "10000360");
        weiboParameters.put("lfid", "OP_" + this.n);
        final String aid = Utility.getAid((Context) this.mWeakAct.get(), this.n);
        QueuedWork.runInMain(new Runnable() {
            public void run() {
                if (!TextUtils.isEmpty(aid)) {
                    weiboParameters.put(ShareRequestParam.REQ_PARAM_AID, aid);
                }
                weiboParameters.put(ShareRequestParam.REQ_PARAM_PACKAGENAME, ContextUtil.getPackageName());
                weiboParameters.put(ShareRequestParam.REQ_PARAM_KEY_HASH, SinaSimplyHandler.keyHash);
                String str = "https://open.weibo.cn/oauth2/authorize?" + weiboParameters.encodeUrl();
                if (SinaSimplyHandler.this.mWeakAct.get() != null && !((Activity) SinaSimplyHandler.this.mWeakAct.get()).isFinishing()) {
                    OauthDialog oauthDialog = new OauthDialog((Activity) SinaSimplyHandler.this.mWeakAct.get(), SHARE_MEDIA.SINA, new a(uMAuthListener));
                    oauthDialog.setWaitUrl(str);
                    oauthDialog.setmRedirectUri(SinaSimplyHandler.this.o);
                    oauthDialog.show();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean a() {
        try {
            AppInfo a2 = a(this.f);
            Intent intent = new Intent();
            intent.setClassName(a2.a(), a2.b());
            intent.putExtras(getAuthBundle());
            intent.putExtra("_weibo_command_type", 3);
            intent.putExtra("_weibo_transaction", String.valueOf(System.currentTimeMillis()));
            intent.putExtra(ShareRequestParam.REQ_PARAM_AID, Utility.getAid((Context) this.mWeakAct.get(), this.s.getAppKey()));
            if (!a((Context) this.mWeakAct.get(), intent)) {
                return false;
            }
            String aid = Utility.getAid((Context) this.mWeakAct.get(), this.s.getAppKey());
            if (!TextUtils.isEmpty(aid)) {
                intent.putExtra(ShareRequestParam.REQ_PARAM_AID, aid);
            }
            try {
                ((Activity) this.mWeakAct.get()).startActivityForResult(intent, HandlerRequestCode.SINASSO_REQUEST_CODE);
                return true;
            } catch (ActivityNotFoundException e2) {
                return false;
            }
        } catch (Exception e3) {
            Log.um(e3.getMessage());
            return false;
        }
    }

    private static boolean a(Context context, Intent intent) {
        ResolveInfo resolveActivity;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null || (resolveActivity = packageManager.resolveActivity(intent, 0)) == null) {
            return false;
        }
        try {
            return a(packageManager.getPackageInfo(resolveActivity.activityInfo.packageName, 64).signatures, "18da2bf10352443a00a5e046d9fca6bd");
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return false;
        } catch (Exception e3) {
            e3.printStackTrace();
            return false;
        }
    }

    private static boolean a(Signature[] signatureArr, String str) {
        if (signatureArr == null || str == null) {
            return false;
        }
        for (Signature byteArray : signatureArr) {
            if (str.equals(MD5.hexdigest(byteArray.toByteArray()))) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x00af  */
    private AppInfo b(Context context) {
        Cursor cursor;
        Throwable th;
        Throwable th2;
        Cursor cursor2;
        int i2;
        ContentResolver contentResolver = context.getContentResolver();
        try {
            cursor = contentResolver.query(a, (String[]) null, (String) null, (String[]) null, (String) null);
            if (cursor == null) {
                try {
                    Cursor query = contentResolver.query(b, (String[]) null, (String) null, (String[]) null, (String) null);
                    if (query == null) {
                        try {
                            AppInfo appInfo = null;
                            if (query != null) {
                                query.close();
                            }
                            return appInfo;
                        } catch (Exception e2) {
                            e = e2;
                            cursor = query;
                            e = e;
                            try {
                                Log.um(e.getMessage());
                                if (cursor == null) {
                                    return null;
                                }
                                cursor.close();
                                return null;
                            } catch (Throwable th3) {
                                th = th3;
                                th2 = th;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                throw th2;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            cursor = query;
                            th2 = th;
                            if (cursor != null) {
                            }
                            throw th2;
                        }
                    } else {
                        cursor2 = query;
                    }
                } catch (Exception e3) {
                    e = e3;
                } catch (Throwable th5) {
                    th2 = th5;
                    if (cursor != null) {
                    }
                    throw th2;
                }
            } else {
                cursor2 = cursor;
            }
            try {
                int columnIndex = cursor2.getColumnIndex("support_api");
                int columnIndex2 = cursor2.getColumnIndex("package");
                int columnIndex3 = cursor2.getColumnIndex("sso_activity");
                if (cursor2.moveToFirst()) {
                    try {
                        i2 = Integer.parseInt(cursor2.getString(columnIndex));
                    } catch (NumberFormatException e4) {
                        e4.printStackTrace();
                        i2 = -1;
                    }
                    String string = cursor2.getString(columnIndex2);
                    String string2 = columnIndex3 > 0 ? cursor2.getString(columnIndex3) : null;
                    if (!TextUtils.isEmpty(string) && validateWeiboSign(context, string)) {
                        AppInfo appInfo2 = new AppInfo();
                        appInfo2.a(string);
                        appInfo2.a(i2);
                        if (!TextUtils.isEmpty(string2)) {
                            appInfo2.b(string2);
                        }
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        return appInfo2;
                    } else if (cursor2 == null) {
                        return null;
                    } else {
                        cursor2.close();
                        return null;
                    }
                } else if (cursor2 == null) {
                    return null;
                } else {
                    cursor2.close();
                    return null;
                }
            } catch (Exception e5) {
                e = e5;
                cursor = cursor2;
            } catch (Throwable th6) {
                th = th6;
                cursor = cursor2;
                th2 = th;
                if (cursor != null) {
                }
                throw th2;
            }
        } catch (Exception e6) {
            e = e6;
            cursor = null;
        } catch (Throwable th7) {
            th2 = th7;
            cursor = null;
            if (cursor != null) {
            }
            throw th2;
        }
    }

    private String b() {
        return this.g != null ? this.g.a() : "";
    }

    private String b(String str) {
        try {
            return ((Activity) this.mWeakAct.get()).getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "0";
        }
    }

    /* access modifiers changed from: private */
    public void b(final UMAuthListener uMAuthListener) {
        Runnable r0;
        i iVar = (i) new SocializeClient().execute(new h(getUID(), b(), this.n, Utility.getAid((Context) this.mWeakAct.get(), this.n)));
        if (iVar != null) {
            final Map<String, String> map = iVar.a;
            if (map != null && !map.containsKey("error")) {
                map.put("iconurl", map.get("profile_image_url"));
                map.put("name", map.get("screen_name"));
                map.put("gender", getGender(map.get("gender")));
                if (this.g != null) {
                    map.put(SocializeProtocolConstants.PROTOCOL_KEY_UID, this.g.d());
                    map.put("access_token", this.g.a());
                    map.put("refreshToken", this.g.b());
                    map.put("expires_in", String.valueOf(this.g.c()));
                    map.put("accessToken", this.g.a());
                    map.put("refreshToken", this.g.b());
                    map.put("expiration", String.valueOf(this.g.c()));
                    r0 = new Runnable() {
                        public void run() {
                            uMAuthListener.onComplete(SHARE_MEDIA.SINA, 2, map);
                        }
                    };
                } else {
                    return;
                }
            } else if (map != null) {
                if (this.g != null) {
                    this.g.h();
                }
                r0 = new Runnable() {
                    public void run() {
                        UMAuthListener uMAuthListener = uMAuthListener;
                        SHARE_MEDIA share_media = SHARE_MEDIA.SINA;
                        uMAuthListener.onError(share_media, 2, new Throwable(UmengErrorCode.RequestForUserProfileFailed + ((String) map.get("error")).toString()));
                    }
                };
            } else {
                QueuedWork.runInMain(new Runnable() {
                    public void run() {
                        UMAuthListener uMAuthListener = uMAuthListener;
                        SHARE_MEDIA share_media = SHARE_MEDIA.SINA;
                        uMAuthListener.onError(share_media, 2, new Throwable(UmengErrorCode.RequestForUserProfileFailed + UmengText.DATA_EMPTY));
                    }
                });
                return;
            }
            QueuedWork.runInMain(r0);
            return;
        }
        QueuedWork.runInMain(new Runnable() {
            public void run() {
                UMAuthListener uMAuthListener = uMAuthListener;
                SHARE_MEDIA share_media = SHARE_MEDIA.SINA;
                uMAuthListener.onError(share_media, 2, new Throwable(UmengErrorCode.RequestForUserProfileFailed + UmengText.SINA_GET_ERROR));
            }
        });
    }

    private AppInfo c(Context context) {
        AppInfo a2;
        Intent intent = new Intent(c);
        intent.addCategory("android.intent.category.DEFAULT");
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            return null;
        }
        AppInfo appInfo = null;
        for (ResolveInfo next : queryIntentServices) {
            if (!(next.serviceInfo == null || next.serviceInfo.applicationInfo == null || TextUtils.isEmpty(next.serviceInfo.packageName) || (a2 = a(next.serviceInfo.packageName)) == null)) {
                appInfo = a2;
            }
        }
        return appInfo;
    }

    private void c(final UMAuthListener uMAuthListener) {
        authorize(new UMAuthListener() {
            public void onCancel(SHARE_MEDIA share_media, int i) {
                uMAuthListener.onCancel(share_media, i);
            }

            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                QueuedWork.runInBack(new Runnable() {
                    public void run() {
                        SinaSimplyHandler.this.b(uMAuthListener);
                    }
                }, true);
            }

            public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
                uMAuthListener.onError(share_media, i, th);
            }

            public void onStart(SHARE_MEDIA share_media) {
            }
        });
    }

    public static boolean validateWeiboSign(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return a(context.getPackageManager().getPackageInfo(str, 64).signatures, "18da2bf10352443a00a5e046d9fca6bd");
        } catch (PackageManager.NameNotFoundException e2) {
            return false;
        }
    }

    public void authorize(final UMAuthListener uMAuthListener) {
        this.l = uMAuthListener;
        if (!getShareConfig().isSinaAuthWithWebView() && isInstall()) {
            QueuedWork.runInBack(new Runnable() {
                public void run() {
                    if (!SinaSimplyHandler.this.a()) {
                        SinaSimplyHandler.this.a(uMAuthListener);
                    }
                }
            }, true);
        } else {
            a(uMAuthListener);
        }
    }

    public void deleteAuth() {
        if (this.g != null) {
            this.g.h();
        }
    }

    public void deleteAuth(final UMAuthListener uMAuthListener) {
        c cVar = new c(this.n, b(), Utility.getAid((Context) this.mWeakAct.get(), this.n));
        if (this.g != null) {
            this.g.h();
        }
        d dVar = (d) new SocializeClient().execute(cVar);
        QueuedWork.runInMain(new Runnable() {
            public void run() {
                uMAuthListener.onComplete(SinaSimplyHandler.this.getConfig().getName(), 1, (Map<String, String>) null);
            }
        });
    }

    public Bundle getAuthBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("appKey", this.n);
        bundle.putString("redirectUri", this.o);
        bundle.putString("scope", SCOPE);
        bundle.putString(ShareRequestParam.REQ_PARAM_PACKAGENAME, ContextUtil.getPackageName());
        bundle.putString(ShareRequestParam.REQ_PARAM_KEY_HASH, Utility.getSign((Context) this.mWeakAct.get(), ContextUtil.getPackageName()));
        return bundle;
    }

    public AppInfo getInfo() {
        return this.t;
    }

    public WeiboMultiMessage getMessage() {
        return this.h;
    }

    public void getPlatformInfo(UMAuthListener uMAuthListener) {
        if (getShareConfig().isNeedAuthOnGetUserInfo() || !this.g.f()) {
            c(uMAuthListener);
        } else {
            b(uMAuthListener);
        }
    }

    public int getRequestCode() {
        return HandlerRequestCode.SINA_REQUEST_CODE;
    }

    /* access modifiers changed from: protected */
    public String getToName() {
        return "sina";
    }

    public String getUID() {
        return this.g != null ? this.g.d() : "";
    }

    public AppInfo getWbAppInfo() {
        AppInfo appInfo;
        synchronized (this) {
            this.t = a(this.f);
            appInfo = this.t;
        }
        return appInfo;
    }

    public SinaAPI getmWeiboShareAPI() {
        return this.u;
    }

    public boolean isAuthorize() {
        if (this.g == null) {
            return false;
        }
        return this.g.f();
    }

    public boolean isHasAuthListener() {
        return this.l != null;
    }

    public boolean isInstall() {
        AppInfo wbAppInfo = getWbAppInfo();
        return wbAppInfo != null && wbAppInfo.d();
    }

    public boolean isSupportAuth() {
        return true;
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i2 == 5650) {
            if (i3 == -1) {
                String stringExtra = intent.getStringExtra("error");
                if (stringExtra == null) {
                    stringExtra = intent.getStringExtra("error_type");
                }
                if (stringExtra != null) {
                    if (!stringExtra.equals("access_denied") && !stringExtra.equals("OAuthAccessDeniedException")) {
                        String stringExtra2 = intent.getStringExtra("error_description");
                        if (stringExtra2 != null) {
                            stringExtra = stringExtra + ":" + stringExtra2;
                        }
                        this.l.onError(SHARE_MEDIA.SINA, 0, new Throwable(UmengErrorCode.AuthorizeFailed + stringExtra));
                        return;
                    }
                } else if (this.l != null) {
                    Bundle extras = intent.getExtras();
                    uploadAuthData(extras);
                    extras.keySet();
                    HashMap hashMap = new HashMap();
                    hashMap.put("name", extras.getString(q));
                    hashMap.put("accessToken", extras.getString("access_token"));
                    hashMap.put("refreshToken", extras.getString("refresh_token"));
                    hashMap.put("expiration", extras.getString("expires_in"));
                    hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_UID, extras.getString(SocializeProtocolConstants.PROTOCOL_KEY_UID));
                    if (this.g != null) {
                        this.g.a(extras).g();
                    }
                    this.l.onComplete(SHARE_MEDIA.SINA, 0, hashMap);
                    return;
                } else {
                    return;
                }
            } else if (i3 != 0) {
                return;
            } else {
                if (intent == null) {
                    if (this.l != null) {
                        this.l.onCancel(SHARE_MEDIA.SINA, 0);
                        Log.d("Weibo-authorize", "Login canceled by user.");
                        return;
                    }
                    return;
                }
            }
            this.l.onCancel(SHARE_MEDIA.SINA, 0);
            return;
        }
        super.onActivityResult(i2, i3, intent);
    }

    public void onCancel() {
        if (this.v != null) {
            this.v.onCancel(SHARE_MEDIA.SINA);
        }
    }

    public void onCreate(Context context, PlatformConfig.Platform platform) {
        super.onCreate(context, platform);
        Log.um("sina simplify version:" + this.i);
        this.f = StubApp.getOrigApplicationContext(context.getApplicationContext());
        PlatformConfig.APPIDPlatform aPPIDPlatform = (PlatformConfig.APPIDPlatform) platform;
        this.n = aPPIDPlatform.appId;
        this.o = aPPIDPlatform.redirectUrl;
        this.s = new AuthInfo(context, aPPIDPlatform.appId, ((PlatformConfig.APPIDPlatform) getConfig()).redirectUrl, SCOPE);
        keyHash = Utility.getSign(context, ContextUtil.getPackageName());
        this.g = new a(context, SHARE_MEDIA.SINA.toString());
        this.u = new SinaAPI(StubApp.getOrigApplicationContext(context.getApplicationContext()), this.n, false);
        this.u.registerApp();
    }

    public void onError() {
        if (this.v != null) {
            this.v.onError(SHARE_MEDIA.SINA, new Throwable(UmengErrorCode.ShareFailed.getMessage()));
        }
    }

    public void onResponse(BaseResponse baseResponse) {
        switch (baseResponse.errCode) {
            case 0:
                if (this.v != null) {
                    this.v.onResult(SHARE_MEDIA.SINA);
                }
                baseResponse.toBundle(new Bundle());
                return;
            case 1:
                if (this.v != null) {
                    this.v.onCancel(SHARE_MEDIA.SINA);
                    return;
                }
                return;
            case 2:
                String str = baseResponse.errMsg;
                if (str.contains("auth faild")) {
                    str = UmengText.errorWithUrl(UmengText.SINA_SIGN_ERROR, UrlUtil.SINA_ERROR_SIGN);
                }
                if (this.v != null) {
                    UMShareListener uMShareListener = this.v;
                    SHARE_MEDIA share_media = SHARE_MEDIA.SINA;
                    uMShareListener.onError(share_media, new Throwable(UmengErrorCode.ShareFailed.getMessage() + str));
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onSuccess() {
        if (this.v != null) {
            this.v.onResult(SHARE_MEDIA.SINA);
        }
    }

    public void release() {
        this.l = null;
    }

    public void setAuthListener(UMAuthListener uMAuthListener) {
        super.setAuthListener(uMAuthListener);
        this.l = uMAuthListener;
    }

    public boolean share(ShareContent shareContent, UMShareListener uMShareListener) {
        SinaShareContent sinaShareContent = new SinaShareContent(shareContent);
        SendMultiMessageToWeiboRequest sendMultiMessageToWeiboRequest = new SendMultiMessageToWeiboRequest();
        sendMultiMessageToWeiboRequest.transaction = String.valueOf(System.currentTimeMillis());
        sendMultiMessageToWeiboRequest.multiMessage = sinaShareContent.a();
        this.h = sendMultiMessageToWeiboRequest.multiMessage;
        new AuthInfo(getContext(), this.n, ((PlatformConfig.APPIDPlatform) getConfig()).redirectUrl, SCOPE);
        String b2 = b();
        this.v = uMShareListener;
        if (!isInstall()) {
            this.u.startShareWeiboActivity((Activity) this.mWeakAct.get(), b2, sendMultiMessageToWeiboRequest, uMShareListener);
            return true;
        } else if (this.mWeakAct.get() == null || ((Activity) this.mWeakAct.get()).isFinishing()) {
            return true;
        } else {
            ((Activity) this.mWeakAct.get()).startActivity(new Intent((Context) this.mWeakAct.get(), WBShareCallBackActivity.class));
            return true;
        }
    }
}
