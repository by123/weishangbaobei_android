package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.google.gson.Gson;
import com.stub.StubApp;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.UserInfoBean;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.webview.WebViewUtil2;

public class MemberPrivilegeActivity extends BaseActivity {
    private static boolean isUseCache;
    @BindView(2131296597)
    ImageView diamondImage;
    @BindView(2131296640)
    LinearLayout errorLayout;
    @BindView(2131297004)
    TextView memberLever;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297091)
    Button operationBtn;
    @BindView(2131297162)
    ProgressBar progressBar;
    @BindView(2131297272)
    WebView safeWebView;
    @BindView(2131297349)
    TextView setExpireTime;
    @BindView(2131297353)
    ImageView setUserHead;
    @BindView(2131297354)
    TextView setUserName;
    private WebViewUtil2 webViewUtil2;

    static {
        StubApp.interface11(9032);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r8v0, types: [com.wx.assistants.activity.MemberPrivilegeActivity, android.app.Activity] */
    private void initView() {
        this.navTitle.setText("会员特权");
        this.webViewUtil2 = new WebViewUtil2();
        this.webViewUtil2.initWebView(this, this.safeWebView, this.navTitle, this.progressBar, this.errorLayout, isUseCache);
        this.webViewUtil2.loadUrl(this, QBangApis.INVITE_AWARD_URL);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getUser();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.MemberPrivilegeActivity] */
    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        try {
            String str = (String) SPUtils.get(this, "ws_baby_user_info", "");
            if (str != null && !"".equals(str)) {
                setUserInfo(str);
            }
        } catch (Exception unused) {
        }
    }

    public void setUserInfo(String str) {
        UserInfoBean.UserBean userBean = (UserInfoBean.UserBean) new Gson().fromJson(str, UserInfoBean.UserBean.class);
        if (userBean != null) {
            try {
                String userId = userBean.getUserId();
                if (userId != null && !"".equals(userId)) {
                    SPUtils.put(MyApplication.getConText(), "ws_baby_user_id", userId);
                }
            } catch (Exception unused) {
            }
            String memberStatus = userBean.getMemberStatus();
            if ("1".equals(memberStatus) || "1.0".equals(memberStatus)) {
                String str2 = userBean.getPhone_number() + "";
                String codeEndTime = userBean.getCodeEndTime();
                if (codeEndTime == null) {
                    codeEndTime = "";
                } else if (codeEndTime.contains(" ")) {
                    codeEndTime = codeEndTime.split(" ")[0];
                }
                this.setUserName.setText(str2);
                this.setExpireTime.setText("会员到期时间：" + codeEndTime);
                this.operationBtn.setVisibility(8);
                this.diamondImage.setVisibility(0);
                String level = userBean.getLevel();
                if ("1".equals(level) || "1.0".equals(level)) {
                    this.memberLever.setText("月");
                } else if ("2".equals(level) || SocializeConstants.PROTOCOL_VERSON.equals(level)) {
                    this.memberLever.setText("年");
                } else if ("3".equals(level) || "3.0".equals(level)) {
                    this.memberLever.setText("三");
                } else if ("4".equals(level) || "4.0".equals(level)) {
                    this.memberLever.setText("半");
                } else if ("5".equals(level) || "5.0".equals(level)) {
                    this.memberLever.setText("年");
                } else if ("10".equals(level) || "10.0".equals(level)) {
                    this.memberLever.setText("永");
                } else if ("11".equals(level) || "11.0".equals(level)) {
                    this.memberLever.setText("永");
                } else {
                    this.memberLever.setText("体");
                }
            } else {
                this.memberLever.setVisibility(8);
                this.diamondImage.setVisibility(8);
                this.setUserName.setText(userBean.getPhone_number() + "");
                this.setExpireTime.setText("开通会员，尊享更多功能特权");
                this.operationBtn.setVisibility(0);
                this.operationBtn.setText("升级会员");
            }
        }
    }

    public void getUser() {
        ApiWrapper.getInstance().getUser(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFailure(FailureModel failureModel) {
            }

            /* JADX WARNING: Can't wrap try/catch for region: R(6:11|12|13|14|15|23) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x006b */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onFinish(com.wx.assistants.bean.ConmdBean r3) {
                /*
                    r2 = this;
                    if (r3 == 0) goto L_0x0075
                    int r0 = r3.getCode()     // Catch:{ Exception -> 0x0071 }
                    r1 = 1
                    if (r0 != r1) goto L_0x004f
                    java.lang.String r0 = r3.getMessage()     // Catch:{ Exception -> 0x0071 }
                    if (r0 == 0) goto L_0x004f
                    java.lang.String r3 = r3.getMessage()     // Catch:{ Exception -> 0x0071 }
                    java.lang.String r0 = "请重新登录"
                    boolean r3 = r3.contains(r0)     // Catch:{ Exception -> 0x0071 }
                    if (r3 == 0) goto L_0x0075
                    com.wx.assistants.activity.MemberPrivilegeActivity r3 = com.wx.assistants.activity.MemberPrivilegeActivity.this     // Catch:{ Exception -> 0x0071 }
                    android.widget.ImageView r3 = r3.diamondImage     // Catch:{ Exception -> 0x0071 }
                    r0 = 8
                    r3.setVisibility(r0)     // Catch:{ Exception -> 0x0071 }
                    com.wx.assistants.activity.MemberPrivilegeActivity r3 = com.wx.assistants.activity.MemberPrivilegeActivity.this     // Catch:{ Exception -> 0x0071 }
                    android.widget.TextView r3 = r3.memberLever     // Catch:{ Exception -> 0x0071 }
                    r3.setVisibility(r0)     // Catch:{ Exception -> 0x0071 }
                    com.wx.assistants.activity.MemberPrivilegeActivity r3 = com.wx.assistants.activity.MemberPrivilegeActivity.this     // Catch:{ Exception -> 0x0071 }
                    android.widget.TextView r3 = r3.setUserName     // Catch:{ Exception -> 0x0071 }
                    java.lang.String r0 = "未知用户"
                    r3.setText(r0)     // Catch:{ Exception -> 0x0071 }
                    com.wx.assistants.activity.MemberPrivilegeActivity r3 = com.wx.assistants.activity.MemberPrivilegeActivity.this     // Catch:{ Exception -> 0x0071 }
                    android.widget.TextView r3 = r3.setExpireTime     // Catch:{ Exception -> 0x0071 }
                    java.lang.String r0 = "尚未登录，请登录"
                    r3.setText(r0)     // Catch:{ Exception -> 0x0071 }
                    com.wx.assistants.activity.MemberPrivilegeActivity r3 = com.wx.assistants.activity.MemberPrivilegeActivity.this     // Catch:{ Exception -> 0x0071 }
                    android.widget.Button r3 = r3.operationBtn     // Catch:{ Exception -> 0x0071 }
                    r0 = 0
                    r3.setVisibility(r0)     // Catch:{ Exception -> 0x0071 }
                    com.wx.assistants.activity.MemberPrivilegeActivity r3 = com.wx.assistants.activity.MemberPrivilegeActivity.this     // Catch:{ Exception -> 0x0071 }
                    android.widget.Button r3 = r3.operationBtn     // Catch:{ Exception -> 0x0071 }
                    java.lang.String r0 = "登录"
                    r3.setText(r0)     // Catch:{ Exception -> 0x0071 }
                    goto L_0x0075
                L_0x004f:
                    int r0 = r3.getCode()     // Catch:{ Exception -> 0x0071 }
                    if (r0 != 0) goto L_0x0075
                    com.google.gson.Gson r0 = new com.google.gson.Gson     // Catch:{ Exception -> 0x0071 }
                    r0.<init>()     // Catch:{ Exception -> 0x0071 }
                    java.lang.Object r3 = r3.getData()     // Catch:{ Exception -> 0x0071 }
                    java.lang.String r3 = r0.toJson(r3)     // Catch:{ Exception -> 0x0071 }
                    android.content.Context r0 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x006b }
                    java.lang.String r1 = "ws_baby_user_info"
                    com.wx.assistants.utils.SPUtils.put(r0, r1, r3)     // Catch:{ Exception -> 0x006b }
                L_0x006b:
                    com.wx.assistants.activity.MemberPrivilegeActivity r0 = com.wx.assistants.activity.MemberPrivilegeActivity.this     // Catch:{ Exception -> 0x0071 }
                    r0.setUserInfo(r3)     // Catch:{ Exception -> 0x0071 }
                    goto L_0x0075
                L_0x0071:
                    r3 = move-exception
                    r3.printStackTrace()
                L_0x0075:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.MemberPrivilegeActivity.AnonymousClass1.onFinish(com.wx.assistants.bean.ConmdBean):void");
            }
        });
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.MemberPrivilegeActivity] */
    @OnClick({2131297049, 2131297091})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297091) {
            if ("登录".equals(this.operationBtn.getText())) {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("isNeedBack", true);
                startActivity(intent);
            } else if ("升级会员".equals(this.operationBtn.getText())) {
                startActivity(new Intent(this, MemberCenterListActivity.class));
            }
        }
    }
}
