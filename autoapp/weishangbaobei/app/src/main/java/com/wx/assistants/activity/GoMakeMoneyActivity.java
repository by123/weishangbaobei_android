package com.wx.assistants.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.google.gson.internal.LinkedTreeMap;
import com.stub.StubApp;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.dialog.AlertActionSheetSharedDialog;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.ToastUtils;
import gdut.bsx.share2.ShareContentType;

public class GoMakeMoneyActivity extends BaseActivity {
    @BindView(2131296366)
    LinearLayout backLayout;
    @BindView(2131296480)
    Button checkRule;
    @BindView(2131296549)
    Button copyBtn;
    @BindView(2131296687)
    Button forwardCircle;
    @BindView(2131296819)
    Button inviteNow;
    @BindView(2131296827)
    TextView inviteUrl;
    /* access modifiers changed from: private */
    public String inviteUrlStr = "";
    @BindView(2131297044)
    Button myAward;

    static {
        StubApp.interface11(8629);
    }

    public void initView() {
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public void initInviteData() {
        LogUtils.log("WS_BABY_InComeFragment.initInviteData");
        ApiWrapper.getInstance().getInviteData(new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFinish(ConmdBean conmdBean) {
                if (conmdBean != null) {
                    try {
                        String unused = GoMakeMoneyActivity.this.inviteUrlStr = (String) ((LinkedTreeMap) conmdBean.getData()).get("inviteUrl");
                        MyApplication.inviteUrlStr = GoMakeMoneyActivity.this.inviteUrlStr;
                        GoMakeMoneyActivity.this.inviteUrl.setText(GoMakeMoneyActivity.this.inviteUrlStr);
                    } catch (Exception unused2) {
                        LogUtils.log("WS_BABY_Catch_34");
                    }
                }
            }

            /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.wx.assistants.activity.GoMakeMoneyActivity] */
            public void onFailure(FailureModel failureModel) {
                if (failureModel != null) {
                    ToastUtils.showToast(GoMakeMoneyActivity.this, failureModel.getErrorMessage());
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.GoMakeMoneyActivity] */
    @OnClick({2131296549, 2131296687, 2131296819, 2131297044, 2131296480})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296480:
                startActivity(InviteAwardActivity.class, true, true);
                return;
            case 2131296549:
                if (this.inviteUrlStr != null && !"".equals(this.inviteUrlStr)) {
                    ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence) null, this.inviteUrlStr));
                    ToastUtils.showToast(this, "复制成功");
                    return;
                }
                return;
            case 2131296687:
                startActivity(OneKeyForwardMaterialActivity.class, true, true);
                return;
            case 2131296819:
                if (this.inviteUrlStr == null || "".equals(this.inviteUrlStr)) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.putExtra("isNeedBack", true);
                    startActivity(intent);
                    return;
                }
                new AlertActionSheetSharedDialog(this, new AlertActionSheetSharedDialog.OnSheetItemClickListener() {
                    /* JADX WARNING: type inference failed for: r2v0, types: [android.app.Activity, com.wx.assistants.activity.GoMakeMoneyActivity] */
                    /* JADX WARNING: type inference failed for: r2v1, types: [android.app.Activity, com.wx.assistants.activity.GoMakeMoneyActivity] */
                    /* JADX WARNING: type inference failed for: r1v5, types: [android.content.Context, com.wx.assistants.activity.GoMakeMoneyActivity] */
                    /* JADX WARNING: type inference failed for: r2v2, types: [android.app.Activity, com.wx.assistants.activity.GoMakeMoneyActivity] */
                    public void onClick(int i) {
                        String str = "下载<<微商宝贝>>在“个人中心”（功能建议于客服）中领取激活码即可立享24小时免费VIP特权。让您体验快速找出删除你的好友、24小时群发群、群聊爆粉、一键点赞评论等超多酷炫功能。详戳【 " + GoMakeMoneyActivity.this.inviteUrlStr + " 】";
                        switch (i) {
                            case 0:
                                new ShareAction(GoMakeMoneyActivity.this).withText(str).setPlatform(SHARE_MEDIA.WEIXIN.toSnsPlatform().mPlatform).setCallback((UMShareListener) null).share();
                                return;
                            case 1:
                                new ShareAction(GoMakeMoneyActivity.this).withText(str).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform().mPlatform).setCallback((UMShareListener) null).share();
                                return;
                            case 2:
                                GoMakeMoneyActivity.this.shareQQ(GoMakeMoneyActivity.this, str);
                                return;
                            case 3:
                                new ShareAction(GoMakeMoneyActivity.this).withText(str).setPlatform(SHARE_MEDIA.QZONE.toSnsPlatform().mPlatform).setCallback((UMShareListener) null).share();
                                return;
                            default:
                                return;
                        }
                    }
                }).builder().show();
                return;
            case 2131297044:
                startActivity(MyInviteRecordMainActivity.class, true, true);
                return;
            default:
                return;
        }
    }

    public void shareQQ(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.setType(ShareContentType.TEXT);
        try {
            intent.setClassName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity");
            Intent createChooser = Intent.createChooser(intent, "选择分享途径");
            if (createChooser != null) {
                context.startActivity(createChooser);
            }
        } catch (Exception unused) {
            context.startActivity(intent);
        }
    }

    @OnClick({2131296366})
    public void onViewClicked() {
        back();
    }
}
