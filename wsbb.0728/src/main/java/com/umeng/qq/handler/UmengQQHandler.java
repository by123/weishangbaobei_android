package com.umeng.qq.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.qq.tencent.Tencent;
import com.umeng.qq.tencent.j;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.utils.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.json.JSONObject;

public class UmengQQHandler extends b {
    private j o;
    /* access modifiers changed from: private */
    public s p;
    private final String q = "https://graph.qq.com/oauth2.0/me?access_token=";
    private final String r = "&unionid=1";

    private j a(UMAuthListener uMAuthListener) {
        return new i(this, uMAuthListener);
    }

    private j a(UMShareListener uMShareListener) {
        return new g(this, uMShareListener);
    }

    private String a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine + "/n");
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
        }
        inputStream.close();
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public String a(String str) {
        try {
            URLConnection openConnection = new URL(str).openConnection();
            if (openConnection == null) {
                return "";
            }
            openConnection.connect();
            InputStream inputStream = openConnection.getInputStream();
            return inputStream == null ? "" : a(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void a() {
        if (!isInstall()) {
            if (Config.isJumptoAppStore) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(SocializeConstants.DOWN_URL_QQ));
                ((Activity) this.mWeakAct.get()).startActivity(intent);
            }
            QueuedWork.runInMain(new m(this));
        } else if (this.mWeakAct.get() != null && !((Activity) this.mWeakAct.get()).isFinishing()) {
            this.d.login((Activity) this.mWeakAct.get(), "all", a(this.c));
        }
    }

    /* access modifiers changed from: private */
    public String b() {
        return this.p != null ? this.p.a() : "";
    }

    /* access modifiers changed from: private */
    public void b(UMAuthListener uMAuthListener) {
        QueuedWork.runInBack(new n(this, uMAuthListener), false);
    }

    /* access modifiers changed from: private */
    public String c() {
        return this.p != null ? this.p.b() : "";
    }

    /* access modifiers changed from: private */
    public void c(UMAuthListener uMAuthListener) {
        authorize(new r(this, uMAuthListener));
    }

    /* access modifiers changed from: private */
    public long d() {
        if (this.p != null) {
            return this.p.e();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public String e() {
        return this.p != null ? this.p.c() : "";
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.p != null) {
            this.p.g();
        }
    }

    /* access modifiers changed from: private */
    public JSONObject g() {
        StringBuilder sb = new StringBuilder();
        sb.append("https://openmobile.qq.com/user/get_simple_userinfo?status_os=" + Build.VERSION.RELEASE);
        sb.append("&");
        sb.append("access_token=" + b());
        sb.append("&oauth_consumer_key=" + this.config.appId);
        sb.append("&format=json&openid=" + e());
        sb.append("&status_version=" + Build.VERSION.SDK);
        sb.append("&status_machine=" + h());
        sb.append("&pf=openmobile_android&sdkp=a&sdkv=3.1.0.lite");
        return new JSONObject(request(sb.toString()).replace("/n", ""));
    }

    private String h() {
        try {
            return URLEncoder.encode(Build.MODEL.replace(" ", "+"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "sm801";
        }
    }

    public void authorize(UMAuthListener uMAuthListener) {
        this.c = uMAuthListener;
        if (this.d == null) {
            QueuedWork.runInMain(new l(this, uMAuthListener));
        }
        a();
    }

    public void deleteAuth(UMAuthListener uMAuthListener) {
        this.d.logout();
        f();
        QueuedWork.runInMain(new d(this, uMAuthListener));
    }

    public void getPlatformInfo(UMAuthListener uMAuthListener) {
        if (!this.p.d() || getShareConfig().isNeedAuthOnGetUserInfo()) {
            c(uMAuthListener);
        } else {
            b(uMAuthListener);
        }
    }

    public int getRequestCode() {
        return 10103;
    }

    /* access modifiers changed from: protected */
    public String getToName() {
        return "qq";
    }

    public void initOpenidAndToken(JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("access_token");
            String string2 = jSONObject.getString("expires_in");
            String string3 = jSONObject.getString("openid");
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                this.d.setAccessToken(string, string2);
                this.d.setOpenId(string3);
            }
        } catch (Exception e) {
        }
    }

    public boolean isAuthorize() {
        if (this.p != null) {
            return this.p.d();
        }
        return false;
    }

    public boolean isHasAuthListener() {
        return this.c != null;
    }

    public boolean isInstall() {
        return this.d.isSupportSSOLogin((Activity) this.mWeakAct.get());
    }

    public boolean isSupportAuth() {
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 10103) {
            Tencent.onActivityResultData(i, i2, intent, this.o);
        }
        if (i == 11101) {
            Tencent.onActivityResultData(i, i2, intent, a(this.c));
        }
    }

    public void onCreate(Context context, PlatformConfig.Platform platform) {
        super.onCreate(context, platform);
        if (context != null) {
            this.p = new s(getContext(), SHARE_MEDIA.QQ.toString());
        }
    }

    public void release() {
        if (this.d != null) {
            this.d.release();
        }
        this.d = null;
        this.c = null;
    }

    public String request(String str) {
        try {
            URLConnection openConnection = new URL(str).openConnection();
            if (openConnection == null) {
                return "";
            }
            openConnection.connect();
            InputStream inputStream = openConnection.getInputStream();
            return inputStream == null ? "" : a(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void setAuthListener(UMAuthListener uMAuthListener) {
        this.c = uMAuthListener;
    }

    public boolean share(ShareContent shareContent, UMShareListener uMShareListener) {
        Runnable fVar;
        t tVar = new t(shareContent);
        if (this.d == null) {
            fVar = new c(this, uMShareListener);
        } else {
            this.o = a(uMShareListener);
            if (this.o == null) {
                Log.d("listen", "listener is null");
            }
            if (!isInstall()) {
                if (Config.isJumptoAppStore) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(SocializeConstants.DOWN_URL_QQ));
                    ((Activity) this.mWeakAct.get()).startActivity(intent);
                }
                QueuedWork.runInMain(new e(this, uMShareListener));
            }
            Bundle a = tVar.a(getShareConfig().isHideQzoneOnQQFriendList(), getShareConfig().getAppName());
            String string = a.getString("error");
            if (!TextUtils.isEmpty(string)) {
                fVar = new f(this, uMShareListener, string);
            } else {
                if (this.mWeakAct.get() != null && !((Activity) this.mWeakAct.get()).isFinishing()) {
                    this.d.shareToQQ((Activity) this.mWeakAct.get(), a, this.o);
                }
                return true;
            }
        }
        QueuedWork.runInMain(fVar);
        return false;
    }
}
