package com.tencent.mm.opensdk.openapi;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.tencent.mm.opensdk.channel.MMessageActV2;
import com.tencent.mm.opensdk.channel.a.a;
import com.tencent.mm.opensdk.channel.a.b;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbiz.AddCardToWXCardPackage;
import com.tencent.mm.opensdk.modelbiz.ChooseCardFromWXCardPackage;
import com.tencent.mm.opensdk.modelbiz.CreateChatroom;
import com.tencent.mm.opensdk.modelbiz.HandleScanResult;
import com.tencent.mm.opensdk.modelbiz.JoinChatroom;
import com.tencent.mm.opensdk.modelbiz.OpenWebview;
import com.tencent.mm.opensdk.modelbiz.SubscribeMessage;
import com.tencent.mm.opensdk.modelbiz.SubscribeMiniProgramMsg;
import com.tencent.mm.opensdk.modelbiz.WXInvoiceAuthInsert;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.modelbiz.WXNontaxPay;
import com.tencent.mm.opensdk.modelbiz.WXOpenBusinessView;
import com.tencent.mm.opensdk.modelbiz.WXOpenBusinessWebview;
import com.tencent.mm.opensdk.modelbiz.WXPayInsurance;
import com.tencent.mm.opensdk.modelmsg.GetMessageFromWX;
import com.tencent.mm.opensdk.modelmsg.LaunchFromWX;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.modelpay.JumpToOfflinePay;
import com.tencent.mm.opensdk.modelpay.PayResp;
import com.tencent.mm.opensdk.utils.ILog;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.d;
import com.umeng.qq.tencent.AuthActivity;
import com.umeng.socialize.ShareContent;
import java.net.URLEncoder;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

class BaseWXApiImplV10 implements IWXAPI {
    protected static final String TAG = "MicroMsg.SDK.WXApiImplV10";
    private static String wxappPayEntryClassname;
    protected String appId;
    protected boolean checkSignature = false;
    protected Context context;
    protected boolean detached = false;
    /* access modifiers changed from: private */
    public int wxSdkVersion;

    BaseWXApiImplV10(Context context2, String str, boolean z) {
        Log.d(TAG, "<init>, appId = " + str + ", checkSignature = " + z);
        this.context = context2;
        this.appId = str;
        this.checkSignature = z;
    }

    private boolean checkSumConsistent(byte[] bArr, byte[] bArr2) {
        String str;
        String str2;
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            str = TAG;
            str2 = "checkSumConsistent fail, invalid arguments";
        } else if (bArr.length != bArr2.length) {
            str = TAG;
            str2 = "checkSumConsistent fail, length is different";
        } else {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    return false;
                }
            }
            return true;
        }
        Log.e(str, str2);
        return false;
    }

    private boolean createChatroom(Context context2, Bundle bundle) {
        Cursor query = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/createChatroom"), (String[]) null, (String) null, new String[]{this.appId, bundle.getString("_wxapi_basereq_transaction", ""), bundle.getString("_wxapi_create_chatroom_group_id", ""), bundle.getString("_wxapi_create_chatroom_chatroom_name", ""), bundle.getString("_wxapi_create_chatroom_chatroom_nickname", ""), bundle.getString("_wxapi_create_chatroom_ext_msg", ""), bundle.getString("_wxapi_basereq_openid", "")}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private String getTokenFromWX(Context context2) {
        Cursor query = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/genTokenForOpenSdk"), (String[]) null, (String) null, new String[]{this.appId, "621019136"}, (String) null);
        if (query == null || !query.moveToFirst()) {
            return null;
        }
        String string = query.getString(0);
        Log.i(TAG, "getTokenFromWX token is " + string);
        query.close();
        return string;
    }

    private boolean handleWxInternalRespType(String str, IWXAPIEventHandler iWXAPIEventHandler) {
        Log.i(TAG, "handleWxInternalRespType, extInfo = " + str);
        try {
            Uri parse = Uri.parse(str);
            String queryParameter = parse.getQueryParameter("wx_internal_resptype");
            Log.i(TAG, "handleWxInternalRespType, respType = " + queryParameter);
            if (d.i(queryParameter)) {
                Log.e(TAG, "handleWxInternalRespType fail, respType is null");
                return false;
            } else if (queryParameter.equals("subscribemessage")) {
                SubscribeMessage.Resp resp = new SubscribeMessage.Resp();
                String queryParameter2 = parse.getQueryParameter("ret");
                if (queryParameter2 != null && queryParameter2.length() > 0) {
                    resp.errCode = d.j(queryParameter2);
                }
                resp.openId = parse.getQueryParameter("openid");
                resp.templateID = parse.getQueryParameter("template_id");
                resp.scene = d.j(parse.getQueryParameter("scene"));
                resp.action = parse.getQueryParameter(AuthActivity.ACTION_KEY);
                resp.reserved = parse.getQueryParameter("reserved");
                iWXAPIEventHandler.onResp(resp);
                return true;
            } else if (queryParameter.contains("invoice_auth_insert")) {
                WXInvoiceAuthInsert.Resp resp2 = new WXInvoiceAuthInsert.Resp();
                String queryParameter3 = parse.getQueryParameter("ret");
                if (queryParameter3 != null && queryParameter3.length() > 0) {
                    resp2.errCode = d.j(queryParameter3);
                }
                resp2.wxOrderId = parse.getQueryParameter("wx_order_id");
                iWXAPIEventHandler.onResp(resp2);
                return true;
            } else if (queryParameter.contains("payinsurance")) {
                WXPayInsurance.Resp resp3 = new WXPayInsurance.Resp();
                String queryParameter4 = parse.getQueryParameter("ret");
                if (queryParameter4 != null && queryParameter4.length() > 0) {
                    resp3.errCode = d.j(queryParameter4);
                }
                resp3.wxOrderId = parse.getQueryParameter("wx_order_id");
                iWXAPIEventHandler.onResp(resp3);
                return true;
            } else if (queryParameter.contains("nontaxpay")) {
                WXNontaxPay.Resp resp4 = new WXNontaxPay.Resp();
                String queryParameter5 = parse.getQueryParameter("ret");
                if (queryParameter5 != null && queryParameter5.length() > 0) {
                    resp4.errCode = d.j(queryParameter5);
                }
                resp4.wxOrderId = parse.getQueryParameter("wx_order_id");
                iWXAPIEventHandler.onResp(resp4);
                return true;
            } else {
                if (!"subscribeminiprogrammsg".equals(queryParameter)) {
                    if (!"5".equals(queryParameter)) {
                        Log.e(TAG, "this open sdk version not support the request type");
                        return false;
                    }
                }
                SubscribeMiniProgramMsg.Resp resp5 = new SubscribeMiniProgramMsg.Resp();
                String queryParameter6 = parse.getQueryParameter("ret");
                if (queryParameter6 != null && queryParameter6.length() > 0) {
                    resp5.errCode = d.j(queryParameter6);
                }
                resp5.openId = parse.getQueryParameter("openid");
                resp5.unionId = parse.getQueryParameter("unionid");
                resp5.nickname = parse.getQueryParameter("nickname");
                resp5.errStr = parse.getQueryParameter("errmsg");
                iWXAPIEventHandler.onResp(resp5);
                return true;
            }
        } catch (Exception e) {
            Log.e(TAG, "handleWxInternalRespType fail, ex = " + e.getMessage());
        }
    }

    private boolean joinChatroom(Context context2, Bundle bundle) {
        Cursor query = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/joinChatroom"), (String[]) null, (String) null, new String[]{this.appId, bundle.getString("_wxapi_basereq_transaction", ""), bundle.getString("_wxapi_join_chatroom_group_id", ""), bundle.getString("_wxapi_join_chatroom_chatroom_nickname", ""), bundle.getString("_wxapi_join_chatroom_ext_msg", ""), bundle.getString("_wxapi_basereq_openid", "")}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendAddCardToWX(Context context2, Bundle bundle) {
        Cursor query = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/addCardToWX"), (String[]) null, (String) null, new String[]{this.appId, bundle.getString("_wxapi_add_card_to_wx_card_list"), bundle.getString("_wxapi_basereq_transaction")}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendChooseCardFromWX(Context context2, Bundle bundle) {
        Cursor query = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/chooseCardFromWX"), (String[]) null, (String) null, new String[]{bundle.getString("_wxapi_choose_card_from_wx_card_app_id"), bundle.getString("_wxapi_choose_card_from_wx_card_location_id"), bundle.getString("_wxapi_choose_card_from_wx_card_sign_type"), bundle.getString("_wxapi_choose_card_from_wx_card_card_sign"), bundle.getString("_wxapi_choose_card_from_wx_card_time_stamp"), bundle.getString("_wxapi_choose_card_from_wx_card_nonce_str"), bundle.getString("_wxapi_choose_card_from_wx_card_card_id"), bundle.getString("_wxapi_choose_card_from_wx_card_card_type"), bundle.getString("_wxapi_choose_card_from_wx_card_can_multi_select"), bundle.getString("_wxapi_basereq_transaction")}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendHandleScanResult(Context context2, Bundle bundle) {
        Cursor query = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/handleScanResult"), (String[]) null, (String) null, new String[]{this.appId, bundle.getString("_wxapi_scan_qrcode_result")}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendInvoiceAuthInsert(Context context2, BaseReq baseReq) {
        Cursor query = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openTypeWebview"), (String[]) null, (String) null, new String[]{this.appId, "2", URLEncoder.encode(String.format("url=%s", new Object[]{URLEncoder.encode(((WXInvoiceAuthInsert.Req) baseReq).url)}))}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendJumpToBizProfileReq(Context context2, Bundle bundle) {
        ContentResolver contentResolver = context2.getContentResolver();
        Uri parse = Uri.parse("content://com.tencent.mm.sdk.comm.provider/jumpToBizProfile");
        StringBuilder sb = new StringBuilder();
        sb.append(bundle.getInt("_wxapi_jump_to_biz_profile_req_scene"));
        StringBuilder sb2 = new StringBuilder();
        sb2.append(bundle.getInt("_wxapi_jump_to_biz_profile_req_profile_type"));
        Cursor query = contentResolver.query(parse, (String[]) null, (String) null, new String[]{this.appId, bundle.getString("_wxapi_jump_to_biz_profile_req_to_user_name"), bundle.getString("_wxapi_jump_to_biz_profile_req_ext_msg"), sb.toString(), sb2.toString()}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendJumpToBizTempSessionReq(Context context2, Bundle bundle) {
        ContentResolver contentResolver = context2.getContentResolver();
        Uri parse = Uri.parse("content://com.tencent.mm.sdk.comm.provider/jumpToBizTempSession");
        StringBuilder sb = new StringBuilder();
        sb.append(bundle.getInt("_wxapi_jump_to_biz_webview_req_show_type"));
        Cursor query = contentResolver.query(parse, (String[]) null, (String) null, new String[]{this.appId, bundle.getString("_wxapi_jump_to_biz_webview_req_to_user_name"), bundle.getString("_wxapi_jump_to_biz_webview_req_session_from"), sb.toString()}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendJumpToBizWebviewReq(Context context2, Bundle bundle) {
        ContentResolver contentResolver = context2.getContentResolver();
        Uri parse = Uri.parse("content://com.tencent.mm.sdk.comm.provider/jumpToBizProfile");
        StringBuilder sb = new StringBuilder();
        sb.append(bundle.getInt("_wxapi_jump_to_biz_webview_req_scene"));
        Cursor query = contentResolver.query(parse, (String[]) null, (String) null, new String[]{this.appId, bundle.getString("_wxapi_jump_to_biz_webview_req_to_user_name"), bundle.getString("_wxapi_jump_to_biz_webview_req_ext_msg"), sb.toString()}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendJumpToOfflinePayReq(Context context2, Bundle bundle) {
        Cursor query = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/jumpToOfflinePay"), (String[]) null, (String) null, new String[]{this.appId}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendLaunchWXMiniprogram(Context context2, BaseReq baseReq) {
        WXLaunchMiniProgram.Req req = (WXLaunchMiniProgram.Req) baseReq;
        ContentResolver contentResolver = context2.getContentResolver();
        Uri parse = Uri.parse("content://com.tencent.mm.sdk.comm.provider/launchWXMiniprogram");
        StringBuilder sb = new StringBuilder();
        sb.append(req.miniprogramType);
        Cursor query = contentResolver.query(parse, (String[]) null, (String) null, new String[]{this.appId, req.userName, req.path, sb.toString(), req.extData}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendNonTaxPay(Context context2, BaseReq baseReq) {
        Cursor query = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openTypeWebview"), (String[]) null, (String) null, new String[]{this.appId, "3", URLEncoder.encode(String.format("url=%s", new Object[]{URLEncoder.encode(((WXNontaxPay.Req) baseReq).url)}))}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendOpenBusiLuckyMoney(Context context2, Bundle bundle) {
        Cursor query = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openBusiLuckyMoney"), (String[]) null, (String) null, new String[]{this.appId, bundle.getString("_wxapi_open_busi_lucky_money_timeStamp"), bundle.getString("_wxapi_open_busi_lucky_money_nonceStr"), bundle.getString("_wxapi_open_busi_lucky_money_signType"), bundle.getString("_wxapi_open_busi_lucky_money_signature"), bundle.getString("_wxapi_open_busi_lucky_money_package")}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendOpenBusinessView(Context context2, BaseReq baseReq) {
        WXOpenBusinessView.Req req = (WXOpenBusinessView.Req) baseReq;
        Cursor query = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openBusinessView"), (String[]) null, (String) null, new String[]{this.appId, req.businessType, req.query, req.extInfo}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendOpenBusinessWebview(Context context2, BaseReq baseReq) {
        WXOpenBusinessWebview.Req req = (WXOpenBusinessWebview.Req) baseReq;
        ContentResolver contentResolver = context2.getContentResolver();
        Uri parse = Uri.parse("content://com.tencent.mm.sdk.comm.provider/openBusinessWebview");
        String str = "";
        if (req.queryInfo != null && req.queryInfo.size() > 0) {
            str = new JSONObject(req.queryInfo).toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(req.businessType);
        Cursor query = contentResolver.query(parse, (String[]) null, (String) null, new String[]{this.appId, sb.toString(), str}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendOpenRankListReq(Context context2, Bundle bundle) {
        Cursor query = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openRankList"), (String[]) null, (String) null, new String[0], (String) null);
        if (query == null) {
            return true;
        }
        query.close();
        return true;
    }

    private boolean sendOpenWebview(Context context2, Bundle bundle) {
        Cursor query = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openWebview"), (String[]) null, (String) null, new String[]{this.appId, bundle.getString("_wxapi_jump_to_webview_url"), bundle.getString("_wxapi_basereq_transaction")}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendPayInSurance(Context context2, BaseReq baseReq) {
        Cursor query = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openTypeWebview"), (String[]) null, (String) null, new String[]{this.appId, "4", URLEncoder.encode(String.format("url=%s", new Object[]{URLEncoder.encode(((WXPayInsurance.Req) baseReq).url)}))}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendPayReq(Context context2, Bundle bundle) {
        if (wxappPayEntryClassname == null) {
            wxappPayEntryClassname = new MMSharedPreferences(context2).getString("_wxapp_pay_entry_classname_", (String) null);
            Log.d(TAG, "pay, set wxappPayEntryClassname = " + wxappPayEntryClassname);
            if (wxappPayEntryClassname == null) {
                try {
                    wxappPayEntryClassname = context2.getPackageManager().getApplicationInfo("com.tencent.mm", ShareContent.MINAPP_STYLE).metaData.getString("com.tencent.mm.BuildInfo.OPEN_SDK_PAY_ENTRY_CLASSNAME", (String) null);
                } catch (Exception e) {
                    Log.e(TAG, "get from metaData failed : " + e.getMessage());
                }
            }
            if (wxappPayEntryClassname == null) {
                Log.e(TAG, "pay fail, wxappPayEntryClassname is null");
                return false;
            }
        }
        MMessageActV2.Args args = new MMessageActV2.Args();
        args.bundle = bundle;
        args.targetPkgName = "com.tencent.mm";
        args.targetClassName = wxappPayEntryClassname;
        return MMessageActV2.send(context2, args);
    }

    private boolean sendSubscribeMessage(Context context2, BaseReq baseReq) {
        SubscribeMessage.Req req = (SubscribeMessage.Req) baseReq;
        Cursor query = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openTypeWebview"), (String[]) null, (String) null, new String[]{this.appId, "1", String.valueOf(req.scene), req.templateID, req.reserved}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendSubscribeMiniProgramMsg(Context context2, BaseReq baseReq) {
        Cursor query = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openTypeWebview"), (String[]) null, (String) null, new String[]{this.appId, "5", ((SubscribeMiniProgramMsg.Req) baseReq).miniProgramAppId}, (String) null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    public void detach() {
        Log.d(TAG, "detach");
        this.detached = true;
        this.context = null;
    }

    public int getWXAppSupportAPI() {
        if (this.detached) {
            throw new IllegalStateException("getWXAppSupportAPI fail, WXMsgImpl has been detached");
        } else if (!isWXAppInstalled()) {
            Log.e(TAG, "open wx app failed, not installed or signature check failed");
            return 0;
        } else {
            this.wxSdkVersion = 0;
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        int unused = BaseWXApiImplV10.this.wxSdkVersion = new MMSharedPreferences(BaseWXApiImplV10.this.context).getInt("_build_info_sdk_int_", 0);
                    } catch (Exception e) {
                        Log.w(BaseWXApiImplV10.TAG, e.getMessage());
                    }
                    countDownLatch.countDown();
                }
            }).run();
            try {
                countDownLatch.await(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Log.w(TAG, e.getMessage());
            }
            Log.d(TAG, "_build_info_sdk_int_ = " + this.wxSdkVersion);
            if (this.wxSdkVersion == 0) {
                try {
                    this.wxSdkVersion = this.context.getPackageManager().getApplicationInfo("com.tencent.mm", ShareContent.MINAPP_STYLE).metaData.getInt("com.tencent.mm.BuildInfo.OPEN_SDK_VERSION", 0);
                    Log.d(TAG, "OPEN_SDK_VERSION = " + this.wxSdkVersion);
                } catch (Exception e2) {
                    Log.e(TAG, "get from metaData failed : " + e2.getMessage());
                }
            }
            return this.wxSdkVersion;
        }
    }

    public boolean handleIntent(Intent intent, IWXAPIEventHandler iWXAPIEventHandler) {
        try {
            if (!WXApiImplComm.isIntentFromWx(intent, ConstantsAPI.Token.WX_TOKEN_VALUE_MSG)) {
                Log.i(TAG, "handleIntent fail, intent not from weixin msg");
                return false;
            } else if (!this.detached) {
                String stringExtra = intent.getStringExtra(ConstantsAPI.CONTENT);
                int intExtra = intent.getIntExtra(ConstantsAPI.SDK_VERSION, 0);
                String stringExtra2 = intent.getStringExtra(ConstantsAPI.APP_PACKAGE);
                if (stringExtra2 != null) {
                    if (stringExtra2.length() != 0) {
                        if (!checkSumConsistent(intent.getByteArrayExtra(ConstantsAPI.CHECK_SUM), b.a(stringExtra, intExtra, stringExtra2))) {
                            Log.e(TAG, "checksum fail");
                            return false;
                        }
                        int intExtra2 = intent.getIntExtra("_wxapi_command_type", 0);
                        Log.i(TAG, "handleIntent, cmd = " + intExtra2);
                        switch (intExtra2) {
                            case 1:
                                iWXAPIEventHandler.onResp(new SendAuth.Resp(intent.getExtras()));
                                return true;
                            case 2:
                                iWXAPIEventHandler.onResp(new SendMessageToWX.Resp(intent.getExtras()));
                                return true;
                            case 3:
                                iWXAPIEventHandler.onReq(new GetMessageFromWX.Req(intent.getExtras()));
                                return true;
                            case 4:
                                ShowMessageFromWX.Req req = new ShowMessageFromWX.Req(intent.getExtras());
                                String str = req.message.messageExt;
                                if (str == null || !str.contains("wx_internal_resptype")) {
                                    if (str != null && str.contains("openbusinesswebview")) {
                                        try {
                                            Uri parse = Uri.parse(str);
                                            if (parse == null || !"openbusinesswebview".equals(parse.getHost())) {
                                                Log.d(TAG, "not openbusinesswebview %" + str);
                                            } else {
                                                WXOpenBusinessWebview.Resp resp = new WXOpenBusinessWebview.Resp();
                                                String queryParameter = parse.getQueryParameter("ret");
                                                if (queryParameter != null && queryParameter.length() > 0) {
                                                    resp.errCode = d.j(queryParameter);
                                                }
                                                resp.resultInfo = parse.getQueryParameter("resultInfo");
                                                resp.errStr = parse.getQueryParameter("errmsg");
                                                String queryParameter2 = parse.getQueryParameter("type");
                                                if (queryParameter2 != null && queryParameter2.length() > 0) {
                                                    resp.businessType = d.j(queryParameter2);
                                                }
                                                iWXAPIEventHandler.onResp(resp);
                                                return true;
                                            }
                                        } catch (Exception e) {
                                            Log.e(TAG, "parse fail, ex = " + e.getMessage());
                                        }
                                    }
                                    iWXAPIEventHandler.onReq(req);
                                    return true;
                                }
                                boolean handleWxInternalRespType = handleWxInternalRespType(str, iWXAPIEventHandler);
                                Log.i(TAG, "handleIntent, extInfo contains wx_internal_resptype, ret = " + handleWxInternalRespType);
                                return handleWxInternalRespType;
                            case 5:
                                iWXAPIEventHandler.onResp(new PayResp(intent.getExtras()));
                                return true;
                            case 6:
                                iWXAPIEventHandler.onReq(new LaunchFromWX.Req(intent.getExtras()));
                                return true;
                            case 9:
                                iWXAPIEventHandler.onResp(new AddCardToWXCardPackage.Resp(intent.getExtras()));
                                return true;
                            case 12:
                                iWXAPIEventHandler.onResp(new OpenWebview.Resp(intent.getExtras()));
                                return true;
                            case 14:
                                iWXAPIEventHandler.onResp(new CreateChatroom.Resp(intent.getExtras()));
                                return true;
                            case 15:
                                iWXAPIEventHandler.onResp(new JoinChatroom.Resp(intent.getExtras()));
                                return true;
                            case 16:
                                iWXAPIEventHandler.onResp(new ChooseCardFromWXCardPackage.Resp(intent.getExtras()));
                                return true;
                            case 17:
                                iWXAPIEventHandler.onResp(new HandleScanResult.Resp(intent.getExtras()));
                                return true;
                            case 19:
                                iWXAPIEventHandler.onResp(new WXLaunchMiniProgram.Resp(intent.getExtras()));
                                return true;
                            case 24:
                                iWXAPIEventHandler.onResp(new JumpToOfflinePay.Resp(intent.getExtras()));
                                return true;
                            case 25:
                                iWXAPIEventHandler.onResp(new WXOpenBusinessWebview.Resp(intent.getExtras()));
                                return true;
                            case 26:
                                iWXAPIEventHandler.onResp(new WXOpenBusinessView.Resp(intent.getExtras()));
                                return true;
                            default:
                                Log.e(TAG, "unknown cmd = " + intExtra2);
                                break;
                        }
                        return false;
                    }
                }
                Log.e(TAG, "invalid argument");
                return false;
            } else {
                throw new IllegalStateException("handleIntent fail, WXMsgImpl has been detached");
            }
        } catch (Exception e2) {
            Log.e(TAG, "handleIntent fail, ex = " + e2.getMessage());
        }
    }

    public boolean isWXAppInstalled() {
        if (!this.detached) {
            try {
                PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo("com.tencent.mm", 64);
                if (packageInfo == null) {
                    return false;
                }
                return WXApiImplComm.validateAppSignature(this.context, packageInfo.signatures, this.checkSignature);
            } catch (PackageManager.NameNotFoundException unused) {
                return false;
            }
        } else {
            throw new IllegalStateException("isWXAppInstalled fail, WXMsgImpl has been detached");
        }
    }

    public boolean openWXApp() {
        if (this.detached) {
            throw new IllegalStateException("openWXApp fail, WXMsgImpl has been detached");
        } else if (!isWXAppInstalled()) {
            Log.e(TAG, "open wx app failed, not installed or signature check failed");
            return false;
        } else {
            try {
                this.context.startActivity(this.context.getPackageManager().getLaunchIntentForPackage("com.tencent.mm"));
                return true;
            } catch (Exception e) {
                Log.e(TAG, "startActivity fail, exception = " + e.getMessage());
                return false;
            }
        }
    }

    public boolean registerApp(String str) {
        return registerApp(str, 0);
    }

    public boolean registerApp(String str, long j) {
        if (this.detached) {
            throw new IllegalStateException("registerApp fail, WXMsgImpl has been detached");
        } else if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            Log.e(TAG, "register app failed for wechat app signature check failed");
            return false;
        } else {
            Log.d(TAG, "registerApp, appId = " + str);
            if (str != null) {
                this.appId = str;
            }
            Log.d(TAG, "registerApp, appId = " + str);
            if (str != null) {
                this.appId = str;
            }
            Log.d(TAG, "register app " + this.context.getPackageName());
            a.C0002a aVar = new a.C0002a();
            aVar.W = "com.tencent.mm";
            aVar.action = ConstantsAPI.ACTION_HANDLE_APP_REGISTER;
            aVar.content = "weixin://registerapp?appid=" + this.appId;
            aVar.X = j;
            return a.a(this.context, aVar);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:117:0x021a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean sendReq(com.tencent.mm.opensdk.modelbase.BaseReq r10) {
        /*
            r9 = this;
            boolean r0 = r9.detached
            if (r0 != 0) goto L_0x026e
            android.content.Context r0 = r9.context
            java.lang.String r1 = "com.tencent.mm"
            boolean r2 = r9.checkSignature
            boolean r0 = com.tencent.mm.opensdk.openapi.WXApiImplComm.validateAppSignatureForPackage(r0, r1, r2)
            r1 = 0
            if (r0 != 0) goto L_0x0019
            java.lang.String r10 = "MicroMsg.SDK.WXApiImplV10"
            java.lang.String r0 = "sendReq failed for wechat app signature check failed"
        L_0x0015:
            com.tencent.mm.opensdk.utils.Log.e(r10, r0)
            return r1
        L_0x0019:
            boolean r0 = r10.checkArgs()
            if (r0 != 0) goto L_0x0024
            java.lang.String r10 = "MicroMsg.SDK.WXApiImplV10"
            java.lang.String r0 = "sendReq checkArgs fail"
            goto L_0x0015
        L_0x0024:
            java.lang.String r0 = "MicroMsg.SDK.WXApiImplV10"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "sendReq, req type = "
            r2.<init>(r3)
            int r3 = r10.getType()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.tencent.mm.opensdk.utils.Log.i(r0, r2)
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            r10.toBundle(r0)
            int r2 = r10.getType()
            r3 = 5
            if (r2 != r3) goto L_0x0051
            android.content.Context r10 = r9.context
            boolean r10 = r9.sendPayReq(r10, r0)
            return r10
        L_0x0051:
            int r2 = r10.getType()
            r3 = 7
            if (r2 != r3) goto L_0x005f
            android.content.Context r10 = r9.context
            boolean r10 = r9.sendJumpToBizProfileReq(r10, r0)
            return r10
        L_0x005f:
            int r2 = r10.getType()
            r3 = 8
            if (r2 != r3) goto L_0x006e
            android.content.Context r10 = r9.context
            boolean r10 = r9.sendJumpToBizWebviewReq(r10, r0)
            return r10
        L_0x006e:
            int r2 = r10.getType()
            r3 = 10
            if (r2 != r3) goto L_0x007d
            android.content.Context r10 = r9.context
            boolean r10 = r9.sendJumpToBizTempSessionReq(r10, r0)
            return r10
        L_0x007d:
            int r2 = r10.getType()
            r3 = 9
            if (r2 != r3) goto L_0x008c
            android.content.Context r10 = r9.context
            boolean r10 = r9.sendAddCardToWX(r10, r0)
            return r10
        L_0x008c:
            int r2 = r10.getType()
            r3 = 16
            if (r2 != r3) goto L_0x009b
            android.content.Context r10 = r9.context
            boolean r10 = r9.sendChooseCardFromWX(r10, r0)
            return r10
        L_0x009b:
            int r2 = r10.getType()
            r3 = 11
            if (r2 != r3) goto L_0x00aa
            android.content.Context r10 = r9.context
            boolean r10 = r9.sendOpenRankListReq(r10, r0)
            return r10
        L_0x00aa:
            int r2 = r10.getType()
            r3 = 12
            if (r2 != r3) goto L_0x00b9
            android.content.Context r10 = r9.context
            boolean r10 = r9.sendOpenWebview(r10, r0)
            return r10
        L_0x00b9:
            int r2 = r10.getType()
            r3 = 25
            if (r2 != r3) goto L_0x00c8
            android.content.Context r0 = r9.context
            boolean r10 = r9.sendOpenBusinessWebview(r0, r10)
            return r10
        L_0x00c8:
            int r2 = r10.getType()
            r3 = 13
            if (r2 != r3) goto L_0x00d7
            android.content.Context r10 = r9.context
            boolean r10 = r9.sendOpenBusiLuckyMoney(r10, r0)
            return r10
        L_0x00d7:
            int r2 = r10.getType()
            r3 = 14
            if (r2 != r3) goto L_0x00e6
            android.content.Context r10 = r9.context
            boolean r10 = r9.createChatroom(r10, r0)
            return r10
        L_0x00e6:
            int r2 = r10.getType()
            r3 = 15
            if (r2 != r3) goto L_0x00f5
            android.content.Context r10 = r9.context
            boolean r10 = r9.joinChatroom(r10, r0)
            return r10
        L_0x00f5:
            int r2 = r10.getType()
            r3 = 17
            if (r2 != r3) goto L_0x0104
            android.content.Context r10 = r9.context
            boolean r10 = r9.sendHandleScanResult(r10, r0)
            return r10
        L_0x0104:
            int r2 = r10.getType()
            r3 = 18
            if (r2 != r3) goto L_0x0113
            android.content.Context r0 = r9.context
            boolean r10 = r9.sendSubscribeMessage(r0, r10)
            return r10
        L_0x0113:
            int r2 = r10.getType()
            r3 = 23
            if (r2 != r3) goto L_0x0122
            android.content.Context r0 = r9.context
            boolean r10 = r9.sendSubscribeMiniProgramMsg(r0, r10)
            return r10
        L_0x0122:
            int r2 = r10.getType()
            r3 = 19
            if (r2 != r3) goto L_0x0131
            android.content.Context r0 = r9.context
            boolean r10 = r9.sendLaunchWXMiniprogram(r0, r10)
            return r10
        L_0x0131:
            int r2 = r10.getType()
            r3 = 26
            if (r2 != r3) goto L_0x0140
            android.content.Context r0 = r9.context
            boolean r10 = r9.sendOpenBusinessView(r0, r10)
            return r10
        L_0x0140:
            int r2 = r10.getType()
            r3 = 20
            if (r2 != r3) goto L_0x014f
            android.content.Context r0 = r9.context
            boolean r10 = r9.sendInvoiceAuthInsert(r0, r10)
            return r10
        L_0x014f:
            int r2 = r10.getType()
            r3 = 21
            if (r2 != r3) goto L_0x015e
            android.content.Context r0 = r9.context
            boolean r10 = r9.sendNonTaxPay(r0, r10)
            return r10
        L_0x015e:
            int r2 = r10.getType()
            r3 = 22
            if (r2 != r3) goto L_0x016d
            android.content.Context r0 = r9.context
            boolean r10 = r9.sendPayInSurance(r0, r10)
            return r10
        L_0x016d:
            int r2 = r10.getType()
            r3 = 24
            if (r2 != r3) goto L_0x017c
            android.content.Context r10 = r9.context
            boolean r10 = r9.sendJumpToOfflinePayReq(r10, r0)
            return r10
        L_0x017c:
            int r2 = r10.getType()
            r3 = 2
            if (r2 != r3) goto L_0x021f
            r2 = r10
            com.tencent.mm.opensdk.modelmsg.SendMessageToWX$Req r2 = (com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req) r2
            com.tencent.mm.opensdk.modelmsg.WXMediaMessage r4 = r2.message
            int r4 = r4.getType()
            boolean r5 = com.tencent.mm.opensdk.utils.d.c(r4)
            if (r5 == 0) goto L_0x021f
            int r5 = r9.getWXAppSupportAPI()
            r6 = 620756993(0x25000001, float:1.1102232E-16)
            if (r5 >= r6) goto L_0x01ad
            com.tencent.mm.opensdk.modelmsg.WXWebpageObject r4 = new com.tencent.mm.opensdk.modelmsg.WXWebpageObject
            r4.<init>()
        L_0x01a0:
            java.lang.String r5 = "_wxminiprogram_webpageurl"
            java.lang.String r5 = r0.getString(r5)
            r4.webpageUrl = r5
            com.tencent.mm.opensdk.modelmsg.WXMediaMessage r5 = r2.message
            r5.mediaObject = r4
            goto L_0x0215
        L_0x01ad:
            r5 = 46
            if (r4 != r5) goto L_0x01c0
            int r4 = r9.getWXAppSupportAPI()
            r5 = 620953856(0x25030100, float:1.1362778E-16)
            if (r4 >= r5) goto L_0x01c0
            com.tencent.mm.opensdk.modelmsg.WXWebpageObject r4 = new com.tencent.mm.opensdk.modelmsg.WXWebpageObject
            r4.<init>()
            goto L_0x01a0
        L_0x01c0:
            com.tencent.mm.opensdk.modelmsg.WXMediaMessage r4 = r2.message
            com.tencent.mm.opensdk.modelmsg.WXMediaMessage$IMediaObject r4 = r4.mediaObject
            com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject r4 = (com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject) r4
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r4.userName
            r5.append(r6)
            java.lang.String r6 = "@app"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.userName = r5
            java.lang.String r5 = r4.path
            boolean r6 = com.tencent.mm.opensdk.utils.d.i(r5)
            if (r6 != 0) goto L_0x0215
            java.lang.String r6 = "\\?"
            java.lang.String[] r5 = r5.split(r6)
            int r6 = r5.length
            r7 = 1
            if (r6 <= r7) goto L_0x0206
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r8 = r5[r1]
            r6.append(r8)
            java.lang.String r8 = ".html?"
            r6.append(r8)
            r5 = r5[r7]
        L_0x01fe:
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            goto L_0x0213
        L_0x0206:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r5 = r5[r1]
            r6.append(r5)
            java.lang.String r5 = ".html"
            goto L_0x01fe
        L_0x0213:
            r4.path = r5
        L_0x0215:
            int r4 = r2.scene
            r5 = 3
            if (r4 == r5) goto L_0x021c
            r2.scene = r1
        L_0x021c:
            r10.toBundle(r0)
        L_0x021f:
            com.tencent.mm.opensdk.channel.MMessageActV2$Args r1 = new com.tencent.mm.opensdk.channel.MMessageActV2$Args
            r1.<init>()
            r1.bundle = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "weixin://sendreq?appid="
            r0.<init>(r2)
            java.lang.String r2 = r9.appId
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r1.content = r0
            java.lang.String r0 = "com.tencent.mm"
            r1.targetPkgName = r0
            java.lang.String r0 = "com.tencent.mm.plugin.base.stub.WXEntryActivity"
            r1.targetClassName = r0
            int r10 = r10.getType()
            if (r10 != r3) goto L_0x0267
            android.content.Context r10 = r9.context     // Catch:{ Exception -> 0x024f }
            java.lang.String r10 = r9.getTokenFromWX(r10)     // Catch:{ Exception -> 0x024f }
            r1.token = r10     // Catch:{ Exception -> 0x024f }
            goto L_0x0267
        L_0x024f:
            r10 = move-exception
            java.lang.String r0 = "MicroMsg.SDK.WXApiImplV10"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "getTokenFromWX fail, exception = "
            r2.<init>(r3)
            java.lang.String r10 = r10.getMessage()
            r2.append(r10)
            java.lang.String r10 = r2.toString()
            com.tencent.mm.opensdk.utils.Log.e(r0, r10)
        L_0x0267:
            android.content.Context r10 = r9.context
            boolean r10 = com.tencent.mm.opensdk.channel.MMessageActV2.send(r10, r1)
            return r10
        L_0x026e:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "sendReq fail, WXMsgImpl has been detached"
            r10.<init>(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mm.opensdk.openapi.BaseWXApiImplV10.sendReq(com.tencent.mm.opensdk.modelbase.BaseReq):boolean");
    }

    public boolean sendResp(BaseResp baseResp) {
        String str;
        String str2;
        if (!this.detached) {
            if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
                str = TAG;
                str2 = "sendResp failed for wechat app signature check failed";
            } else if (!baseResp.checkArgs()) {
                str = TAG;
                str2 = "sendResp checkArgs fail";
            } else {
                Bundle bundle = new Bundle();
                baseResp.toBundle(bundle);
                MMessageActV2.Args args = new MMessageActV2.Args();
                args.bundle = bundle;
                args.content = "weixin://sendresp?appid=" + this.appId;
                args.targetPkgName = "com.tencent.mm";
                args.targetClassName = "com.tencent.mm.plugin.base.stub.WXEntryActivity";
                return MMessageActV2.send(this.context, args);
            }
            Log.e(str, str2);
            return false;
        }
        throw new IllegalStateException("sendResp fail, WXMsgImpl has been detached");
    }

    public void setLogImpl(ILog iLog) {
        Log.setLogImpl(iLog);
    }

    public void unregisterApp() {
        if (this.detached) {
            throw new IllegalStateException("unregisterApp fail, WXMsgImpl has been detached");
        } else if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            Log.e(TAG, "unregister app failed for wechat app signature check failed");
        } else {
            Log.d(TAG, "unregisterApp, appId = " + this.appId);
            if (this.appId == null || this.appId.length() == 0) {
                Log.e(TAG, "unregisterApp fail, appId is empty");
                return;
            }
            Log.d(TAG, "unregister app " + this.context.getPackageName());
            a.C0002a aVar = new a.C0002a();
            aVar.W = "com.tencent.mm";
            aVar.action = ConstantsAPI.ACTION_HANDLE_APP_UNREGISTER;
            aVar.content = "weixin://unregisterapp?appid=" + this.appId;
            a.a(this.context, aVar);
        }
    }
}
