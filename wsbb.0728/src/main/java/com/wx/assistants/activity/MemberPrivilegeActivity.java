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

    /* JADX WARNING: type inference failed for: r7v0, types: [com.wx.assistants.activity.MemberPrivilegeActivity, android.app.Activity] */
    private void initView() {
        this.navTitle.setText("会员特权");
        this.webViewUtil2 = new WebViewUtil2();
        this.webViewUtil2.initWebView(this, this.safeWebView, this.navTitle, this.progressBar, this.errorLayout, isUseCache);
        this.webViewUtil2.loadUrl(this, QBangApis.INVITE_AWARD_URL);
    }

    public void getUser() {
        ApiWrapper.getInstance().getUser(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFailure(FailureModel failureModel) {
            }

            public void onFinish(ConmdBean conmdBean) {
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() != 1 || conmdBean.getMessage() == null) {
                            if (conmdBean.getCode() == 0) {
                                String json = new Gson().toJson(conmdBean.getData());
                                try {
                                    SPUtils.put(MyApplication.getConText(), "ws_baby_user_info", json);
                                } catch (Exception e) {
                                }
                                MemberPrivilegeActivity.this.setUserInfo(json);
                            }
                        } else if (conmdBean.getMessage().contains("请重新登录")) {
                            MemberPrivilegeActivity.this.diamondImage.setVisibility(8);
                            MemberPrivilegeActivity.this.memberLever.setVisibility(8);
                            MemberPrivilegeActivity.this.setUserName.setText("未知用户");
                            MemberPrivilegeActivity.this.setExpireTime.setText("尚未登录，请登录");
                            MemberPrivilegeActivity.this.operationBtn.setVisibility(0);
                            MemberPrivilegeActivity.this.operationBtn.setText("登录");
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

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
        } catch (Exception e) {
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.MemberPrivilegeActivity] */
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

    public void setUserInfo(String str) {
        UserInfoBean.UserBean userBean = (UserInfoBean.UserBean) new Gson().fromJson(str, UserInfoBean.UserBean.class);
        if (userBean != null) {
            try {
                String userId = userBean.getUserId();
                if (userId != null && !"".equals(userId)) {
                    SPUtils.put(MyApplication.getConText(), "ws_baby_user_id", userId);
                }
            } catch (Exception e) {
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
}
