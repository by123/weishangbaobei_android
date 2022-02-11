package com.umeng.socialize.sina.params;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MusicObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoObject;
import com.sina.weibo.sdk.api.VoiceObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.HandlerRequestCode;
import com.umeng.socialize.sina.helper.Base64;
import com.umeng.socialize.sina.helper.MD5;
import com.umeng.socialize.sina.message.BaseRequest;
import com.umeng.socialize.sina.util.Utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class ShareRequestParam extends BrowserRequestParamBase {
    public static final String REQ_PARAM_AID = "aid";
    public static final String REQ_PARAM_KEY_HASH = "key_hash";
    public static final String REQ_PARAM_PACKAGENAME = "packagename";
    public static final String REQ_PARAM_PICINFO = "picinfo";
    public static final String REQ_PARAM_SOURCE = "source";
    public static final String REQ_PARAM_TITLE = "title";
    public static final String REQ_PARAM_TOKEN = "access_token";
    public static final String REQ_PARAM_VERSION = "version";
    public static final String REQ_UPLOAD_PIC_PARAM_IMG = "img";
    public static final String RESP_UPLOAD_PIC_PARAM_CODE = "code";
    public static final String RESP_UPLOAD_PIC_PARAM_DATA = "data";
    public static final int RESP_UPLOAD_PIC_SUCC_CODE = 1;
    private static final String SHARE_URL = "http://service.weibo.com/share/mobilesdk.php";
    public static final String UPLOAD_PIC_URL = "http://service.weibo.com/share/mobilesdk_uppic.php";
    private String mAppKey;
    private String mAppPackage;
    private String mAuthListenerKey;
    private byte[] mBase64ImgData;
    private BaseRequest mBaseRequest;
    private String mHashKey;
    private String mShareContent;
    private String mToken;
    private UMShareListener shareListener;

    public static class UploadPicResult {
        private int code = -2;
        private String picId;

        private UploadPicResult() {
        }

        public static UploadPicResult parse(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            UploadPicResult uploadPicResult = new UploadPicResult();
            try {
                JSONObject jSONObject = new JSONObject(str);
                uploadPicResult.code = jSONObject.optInt(ShareRequestParam.RESP_UPLOAD_PIC_PARAM_CODE, -2);
                uploadPicResult.picId = jSONObject.optString("data", "");
                return uploadPicResult;
            } catch (JSONException e) {
                return uploadPicResult;
            }
        }

        public int getCode() {
            return this.code;
        }

        public String getPicId() {
            return this.picId;
        }
    }

    public ShareRequestParam(Context context) {
        super(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f A[SYNTHETIC, Splitter:B:20:0x003f] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0054 A[SYNTHETIC, Splitter:B:32:0x0054] */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    private void handleMblogPic(String str, byte[] bArr) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        try {
            if (!TextUtils.isEmpty(str)) {
                File file = new File(str);
                if (file.exists() && file.canRead() && file.length() > 0) {
                    byte[] bArr2 = new byte[((int) file.length())];
                    try {
                        fileInputStream2 = new FileInputStream(file);
                        try {
                            fileInputStream2.read(bArr2);
                            this.mBase64ImgData = Base64.encodebyte(bArr2);
                            try {
                                fileInputStream2.close();
                                return;
                            } catch (Exception e) {
                                return;
                            }
                        } catch (IOException e2) {
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e3) {
                                }
                            }
                            if (bArr != null || bArr.length > 0) {
                            }
                        } catch (Throwable th) {
                            th = th;
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (Exception e4) {
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e5) {
                        fileInputStream = null;
                        if (fileInputStream != null) {
                        }
                        if (bArr != null || bArr.length > 0) {
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream2 = null;
                        if (fileInputStream2 != null) {
                        }
                        throw th;
                    }
                }
            }
        } catch (SecurityException e6) {
        }
        if (bArr != null && bArr.length > 0) {
            this.mBase64ImgData = Base64.encodebyte(bArr);
        }
    }

    private void handleSharedMessage(Bundle bundle) {
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        weiboMultiMessage.toObject(bundle);
        StringBuilder sb = new StringBuilder();
        if (weiboMultiMessage.textObject instanceof TextObject) {
            sb.append(weiboMultiMessage.textObject.text);
        }
        if (weiboMultiMessage.imageObject instanceof ImageObject) {
            ImageObject imageObject = weiboMultiMessage.imageObject;
            handleMblogPic(imageObject.imagePath, imageObject.imageData);
        }
        if (weiboMultiMessage.mediaObject instanceof TextObject) {
            sb.append(((TextObject) weiboMultiMessage.mediaObject).text);
        }
        if (weiboMultiMessage.mediaObject instanceof ImageObject) {
            ImageObject imageObject2 = (ImageObject) weiboMultiMessage.mediaObject;
            handleMblogPic(imageObject2.imagePath, imageObject2.imageData);
        }
        if (weiboMultiMessage.mediaObject instanceof WebpageObject) {
            sb.append(" ");
            sb.append(((WebpageObject) weiboMultiMessage.mediaObject).actionUrl);
        }
        if (weiboMultiMessage.mediaObject instanceof MusicObject) {
            sb.append(" ");
            sb.append(((MusicObject) weiboMultiMessage.mediaObject).actionUrl);
        }
        if (weiboMultiMessage.mediaObject instanceof VideoObject) {
            sb.append(" ");
            sb.append(((VideoObject) weiboMultiMessage.mediaObject).actionUrl);
        }
        if (weiboMultiMessage.mediaObject instanceof VoiceObject) {
            sb.append(" ");
            sb.append(((VoiceObject) weiboMultiMessage.mediaObject).actionUrl);
        }
        this.mShareContent = sb.toString();
    }

    private void sendSdkResponse(Activity activity, int i, String str) {
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) {
            Intent intent = new Intent("com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY");
            intent.setFlags(WXMediaMessage.MINI_PROGRAM__THUMB_LENGHT);
            intent.setPackage(extras.getString("_weibo_appPackage"));
            intent.putExtras(extras);
            intent.putExtra("_weibo_appPackage", activity.getPackageName());
            intent.putExtra("_weibo_resp_errcode", i);
            intent.putExtra("_weibo_resp_errstr", str);
            try {
                activity.startActivityForResult(intent, HandlerRequestCode.SINA_SHARE_REQUEST_CODE);
            } catch (ActivityNotFoundException e) {
            }
        }
    }

    public WeiboParameters buildUploadPicParam(WeiboParameters weiboParameters) {
        if (hasImage()) {
            weiboParameters.put(REQ_UPLOAD_PIC_PARAM_IMG, new String(this.mBase64ImgData));
        }
        return weiboParameters;
    }

    public String buildUrl(String str) {
        Uri.Builder buildUpon = Uri.parse(SHARE_URL).buildUpon();
        buildUpon.appendQueryParameter("title", this.mShareContent);
        buildUpon.appendQueryParameter("version", "0031205000");
        if (!TextUtils.isEmpty(this.mAppKey)) {
            buildUpon.appendQueryParameter(REQ_PARAM_SOURCE, this.mAppKey);
        }
        if (!TextUtils.isEmpty(this.mToken)) {
            buildUpon.appendQueryParameter("access_token", this.mToken);
        }
        String aid = Utility.getAid(this.mContext, this.mAppKey);
        if (!TextUtils.isEmpty(aid)) {
            buildUpon.appendQueryParameter(REQ_PARAM_AID, aid);
        }
        if (!TextUtils.isEmpty(this.mAppPackage)) {
            buildUpon.appendQueryParameter(REQ_PARAM_PACKAGENAME, this.mAppPackage);
        }
        if (!TextUtils.isEmpty(this.mHashKey)) {
            buildUpon.appendQueryParameter(REQ_PARAM_KEY_HASH, this.mHashKey);
        }
        if (!TextUtils.isEmpty(str)) {
            buildUpon.appendQueryParameter(REQ_PARAM_PICINFO, str);
        }
        return buildUpon.build().toString();
    }

    public void execRequest(Activity activity, int i) {
        if (i == 3) {
            sendSdkCancleResponse(activity);
        }
    }

    public String getAppKey() {
        return this.mAppKey;
    }

    public String getAppPackage() {
        return this.mAppPackage;
    }

    public String getAuthListenerKey() {
        return this.mAuthListenerKey;
    }

    public byte[] getBase64ImgData() {
        return this.mBase64ImgData;
    }

    public String getHashKey() {
        return this.mHashKey;
    }

    public String getShareContent() {
        return this.mShareContent;
    }

    public String getToken() {
        return this.mToken;
    }

    public boolean hasImage() {
        return this.mBase64ImgData != null && this.mBase64ImgData.length > 0;
    }

    public void onCreateRequestParamBundle(Bundle bundle) {
        if (this.mBaseRequest != null) {
            this.mBaseRequest.toBundle(bundle);
        }
        if (!TextUtils.isEmpty(this.mAppPackage)) {
            this.mHashKey = MD5.hexdigest(Utility.getSign(this.mContext, this.mAppPackage));
        }
        bundle.putString("access_token", this.mToken);
        bundle.putString(REQ_PARAM_SOURCE, this.mAppKey);
        bundle.putString(REQ_PARAM_PACKAGENAME, this.mAppPackage);
        bundle.putString(REQ_PARAM_KEY_HASH, this.mHashKey);
        bundle.putString("_weibo_appPackage", this.mAppPackage);
        bundle.putString("_weibo_appKey", this.mAppKey);
        bundle.putInt("_weibo_flag", 538116905);
        bundle.putString("_weibo_sign", this.mHashKey);
    }

    /* access modifiers changed from: protected */
    public void onSetupRequestParam(Bundle bundle) {
        this.mAppKey = bundle.getString(REQ_PARAM_SOURCE);
        this.mAppPackage = bundle.getString(REQ_PARAM_PACKAGENAME);
        this.mHashKey = bundle.getString(REQ_PARAM_KEY_HASH);
        this.mToken = bundle.getString("access_token");
        this.mAuthListenerKey = bundle.getString("key_listener");
        handleSharedMessage(bundle);
        this.mUrl = buildUrl("");
    }

    public void sendSdkCancleResponse(Activity activity) {
        sendSdkResponse(activity, 1, "send cancel!!!");
    }

    public void sendSdkErrorResponse(Activity activity, String str) {
        sendSdkResponse(activity, 2, str);
    }

    public void sendSdkOkResponse(Activity activity) {
        sendSdkResponse(activity, 0, "send ok!!!");
    }

    public void setAppKey(String str) {
        this.mAppKey = str;
    }

    public void setAppPackage(String str) {
        this.mAppPackage = str;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        this.mBaseRequest = baseRequest;
    }

    public void setToken(String str) {
        this.mToken = str;
    }
}
