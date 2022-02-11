package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        AppInfo b2 = b(context);
        AppInfo c2 = c(context);
        boolean z = false;
        boolean z2 = b2 != null;
        if (c2 != null) {
            z = true;
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

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0098 A[SYNTHETIC, Splitter:B:35:0x0098] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a8 A[SYNTHETIC, Splitter:B:42:0x00a8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.umeng.socialize.media.AppInfo a(java.lang.String r9) {
        /*
            r8 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            android.content.Context r0 = r8.f     // Catch:{ Exception -> 0x008d, all -> 0x008a }
            r2 = 2
            android.content.Context r0 = r0.createPackageContext(r9, r2)     // Catch:{ Exception -> 0x008d, all -> 0x008a }
            r2 = 4096(0x1000, float:5.74E-42)
            byte[] r3 = new byte[r2]     // Catch:{ Exception -> 0x008d, all -> 0x008a }
            android.content.res.AssetManager r0 = r0.getAssets()     // Catch:{ Exception -> 0x008d, all -> 0x008a }
            java.lang.String r4 = "weibo_for_sdk.json"
            java.io.InputStream r0 = r0.open(r4)     // Catch:{ Exception -> 0x008d, all -> 0x008a }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0088 }
            r4.<init>()     // Catch:{ Exception -> 0x0088 }
        L_0x0022:
            r5 = 0
            int r6 = r0.read(r3, r5, r2)     // Catch:{ Exception -> 0x0088 }
            r7 = -1
            if (r6 == r7) goto L_0x0033
            java.lang.String r7 = new java.lang.String     // Catch:{ Exception -> 0x0088 }
            r7.<init>(r3, r5, r6)     // Catch:{ Exception -> 0x0088 }
            r4.append(r7)     // Catch:{ Exception -> 0x0088 }
            goto L_0x0022
        L_0x0033:
            java.lang.String r2 = r4.toString()     // Catch:{ Exception -> 0x0088 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x0088 }
            if (r2 != 0) goto L_0x0079
            android.content.Context r2 = r8.f     // Catch:{ Exception -> 0x0088 }
            boolean r2 = validateWeiboSign(r2, r9)     // Catch:{ Exception -> 0x0088 }
            if (r2 == 0) goto L_0x0079
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x0088 }
            java.lang.String r3 = r4.toString()     // Catch:{ Exception -> 0x0088 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0088 }
            java.lang.String r3 = "support_api"
            int r3 = r2.optInt(r3, r7)     // Catch:{ Exception -> 0x0088 }
            com.umeng.socialize.media.AppInfo r4 = new com.umeng.socialize.media.AppInfo     // Catch:{ Exception -> 0x0088 }
            r4.<init>()     // Catch:{ Exception -> 0x0088 }
            r4.a((java.lang.String) r9)     // Catch:{ Exception -> 0x0088 }
            r4.a((int) r3)     // Catch:{ Exception -> 0x0088 }
            java.lang.String r9 = "authActivityName"
            java.lang.String r3 = "com.sina.weibo.SSOActivity"
            java.lang.String r9 = r2.optString(r9, r3)     // Catch:{ Exception -> 0x0088 }
            r4.b(r9)     // Catch:{ Exception -> 0x0088 }
            if (r0 == 0) goto L_0x0078
            r0.close()     // Catch:{ IOException -> 0x0070 }
            goto L_0x0078
        L_0x0070:
            r9 = move-exception
            java.lang.String r9 = r9.getMessage()
            com.umeng.socialize.utils.Log.um(r9)
        L_0x0078:
            return r4
        L_0x0079:
            if (r0 == 0) goto L_0x0087
            r0.close()     // Catch:{ IOException -> 0x007f }
            goto L_0x0087
        L_0x007f:
            r9 = move-exception
            java.lang.String r9 = r9.getMessage()
            com.umeng.socialize.utils.Log.um(r9)
        L_0x0087:
            return r1
        L_0x0088:
            r9 = move-exception
            goto L_0x008f
        L_0x008a:
            r9 = move-exception
            r0 = r1
            goto L_0x00a6
        L_0x008d:
            r9 = move-exception
            r0 = r1
        L_0x008f:
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x00a5 }
            com.umeng.socialize.utils.Log.um(r9)     // Catch:{ all -> 0x00a5 }
            if (r0 == 0) goto L_0x00a4
            r0.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x00a4
        L_0x009c:
            r9 = move-exception
            java.lang.String r9 = r9.getMessage()
            com.umeng.socialize.utils.Log.um(r9)
        L_0x00a4:
            return r1
        L_0x00a5:
            r9 = move-exception
        L_0x00a6:
            if (r0 == 0) goto L_0x00b4
            r0.close()     // Catch:{ IOException -> 0x00ac }
            goto L_0x00b4
        L_0x00ac:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()
            com.umeng.socialize.utils.Log.um(r0)
        L_0x00b4:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.handler.SinaSimplyHandler.a(java.lang.String):com.umeng.socialize.media.AppInfo");
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
            } catch (ActivityNotFoundException unused) {
                return false;
            }
        } catch (Exception e2) {
            Log.um(e2.getMessage());
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
        if (!(signatureArr == null || str == null)) {
            for (Signature byteArray : signatureArr) {
                if (str.equals(MD5.hexdigest(byteArray.toByteArray()))) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.umeng.socialize.media.AppInfo b(android.content.Context r10) {
        /*
            r9 = this;
            android.content.ContentResolver r6 = r10.getContentResolver()
            r7 = 0
            android.net.Uri r1 = a     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r2 = r7
            java.lang.String[] r2 = (java.lang.String[]) r2     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r3 = r7
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r4 = r7
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r5 = r7
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r0 = r6
            android.database.Cursor r8 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            if (r8 != 0) goto L_0x0040
            android.net.Uri r1 = b     // Catch:{ Exception -> 0x003c, all -> 0x0038 }
            r2 = r7
            java.lang.String[] r2 = (java.lang.String[]) r2     // Catch:{ Exception -> 0x003c, all -> 0x0038 }
            r3 = r7
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x003c, all -> 0x0038 }
            r4 = r7
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ Exception -> 0x003c, all -> 0x0038 }
            r5 = r7
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x003c, all -> 0x0038 }
            r0 = r6
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x003c, all -> 0x0038 }
            if (r0 != 0) goto L_0x0041
            r10 = r7
            com.umeng.socialize.media.AppInfo r10 = (com.umeng.socialize.media.AppInfo) r10     // Catch:{ Exception -> 0x00a7 }
            if (r0 == 0) goto L_0x0037
            r0.close()
        L_0x0037:
            return r10
        L_0x0038:
            r10 = move-exception
            r0 = r8
            goto L_0x00bc
        L_0x003c:
            r10 = move-exception
            r0 = r8
            goto L_0x00ae
        L_0x0040:
            r0 = r8
        L_0x0041:
            java.lang.String r1 = "support_api"
            int r1 = r0.getColumnIndex(r1)     // Catch:{ Exception -> 0x00a7 }
            java.lang.String r2 = "package"
            int r2 = r0.getColumnIndex(r2)     // Catch:{ Exception -> 0x00a7 }
            java.lang.String r3 = "sso_activity"
            int r3 = r0.getColumnIndex(r3)     // Catch:{ Exception -> 0x00a7 }
            boolean r4 = r0.moveToFirst()     // Catch:{ Exception -> 0x00a7 }
            if (r4 != 0) goto L_0x005f
            if (r0 == 0) goto L_0x005e
            r0.close()
        L_0x005e:
            return r7
        L_0x005f:
            r4 = -1
            java.lang.String r1 = r0.getString(r1)     // Catch:{ Exception -> 0x00a7 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x0069 }
            goto L_0x006e
        L_0x0069:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ Exception -> 0x00a7 }
            r1 = -1
        L_0x006e:
            java.lang.String r2 = r0.getString(r2)     // Catch:{ Exception -> 0x00a7 }
            if (r3 <= 0) goto L_0x0079
            java.lang.String r3 = r0.getString(r3)     // Catch:{ Exception -> 0x00a7 }
            goto L_0x007a
        L_0x0079:
            r3 = r7
        L_0x007a:
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x00a7 }
            if (r4 != 0) goto L_0x00a1
            boolean r10 = validateWeiboSign(r10, r2)     // Catch:{ Exception -> 0x00a7 }
            if (r10 != 0) goto L_0x0087
            goto L_0x00a1
        L_0x0087:
            com.umeng.socialize.media.AppInfo r10 = new com.umeng.socialize.media.AppInfo     // Catch:{ Exception -> 0x00a7 }
            r10.<init>()     // Catch:{ Exception -> 0x00a7 }
            r10.a((java.lang.String) r2)     // Catch:{ Exception -> 0x00a7 }
            r10.a((int) r1)     // Catch:{ Exception -> 0x00a7 }
            boolean r1 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x00a7 }
            if (r1 != 0) goto L_0x009b
            r10.b(r3)     // Catch:{ Exception -> 0x00a7 }
        L_0x009b:
            if (r0 == 0) goto L_0x00a0
            r0.close()
        L_0x00a0:
            return r10
        L_0x00a1:
            if (r0 == 0) goto L_0x00a6
            r0.close()
        L_0x00a6:
            return r7
        L_0x00a7:
            r10 = move-exception
            goto L_0x00ae
        L_0x00a9:
            r10 = move-exception
            r0 = r7
            goto L_0x00bc
        L_0x00ac:
            r10 = move-exception
            r0 = r7
        L_0x00ae:
            java.lang.String r10 = r10.getMessage()     // Catch:{ all -> 0x00bb }
            com.umeng.socialize.utils.Log.um(r10)     // Catch:{ all -> 0x00bb }
            if (r0 == 0) goto L_0x00ba
            r0.close()
        L_0x00ba:
            return r7
        L_0x00bb:
            r10 = move-exception
        L_0x00bc:
            if (r0 == 0) goto L_0x00c1
            r0.close()
        L_0x00c1:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.handler.SinaSimplyHandler.b(android.content.Context):com.umeng.socialize.media.AppInfo");
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
        Runnable r1;
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
                    r1 = new Runnable() {
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
                r1 = new Runnable() {
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
            QueuedWork.runInMain(r1);
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
        AppInfo appInfo = null;
        if (queryIntentServices != null && !queryIntentServices.isEmpty()) {
            for (ResolveInfo next : queryIntentServices) {
                if (!(next.serviceInfo == null || next.serviceInfo.applicationInfo == null || TextUtils.isEmpty(next.serviceInfo.packageName) || (a2 = a(next.serviceInfo.packageName)) == null)) {
                    appInfo = a2;
                }
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
        } catch (PackageManager.NameNotFoundException unused) {
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

    public synchronized AppInfo getWbAppInfo() {
        this.t = a(this.f);
        return this.t;
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
