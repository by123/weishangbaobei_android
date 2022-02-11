package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.umeng.analytics.MobclickAgent;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.webview.WebViewActivity;
import com.xw.repo.BubbleSeekBar;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class SettingActivity extends BaseActivity {
    @BindView(2131296452)
    BubbleSeekBar bubbleSeekBar;
    @BindView(2131296561)
    LinearLayout currentDeviceLayout;
    @BindView(2131296601)
    LinearLayout disclaimerLayout;
    @BindView(2131296649)
    Button exitLogin;
    @BindView(2131296666)
    LinearLayout fillInInvitationRelationshipLayout;
    @BindView(2131296984)
    TextView macAddress;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297350)
    LinearLayout setLoginPasswordLayout;
    @BindView(2131297363)
    ShadowLinearLayout shadowLinearLayout;
    @BindView(2131297446)
    Switch switchBtn;
    @BindView(2131297622)
    LinearLayout userPrivacyPolicy;
    @BindView(2131297623)
    LinearLayout userUsageProtocolLayout;
    @BindView(2131297693)
    LinearLayout wxVersionLayout;

    static {
        StubApp.interface11(9369);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.SettingActivity] */
    public void initData() {
        this.navTitle.setText("我的设置");
        this.bubbleSeekBar.setProgress((float) ((Integer) SPUtils.get(this, "seek_bar_speed", 0)).intValue());
        this.bubbleSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int i, float f) {
            }

            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z) {
            }

            /* JADX WARNING: type inference failed for: r1v1, types: [android.content.Context, com.wx.assistants.activity.SettingActivity] */
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z) {
                SPUtils.put(SettingActivity.this, "seek_bar_speed", Integer.valueOf(i));
            }
        });
        this.switchBtn.setChecked(((Boolean) SPUtils.get(this, "isCanShowVerDialog", true)).booleanValue());
        this.switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /* JADX WARNING: type inference failed for: r2v1, types: [android.content.Context, com.wx.assistants.activity.SettingActivity] */
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SPUtils.put(SettingActivity.this, "isCanShowVerDialog", Boolean.valueOf(z));
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getUser();
    }

    public void getUser() {
        String macAddress2 = MacAddressUtils.getMacAddress(MyApplication.mContext);
        this.macAddress.setText(macAddress2);
        ApiWrapper.getInstance().getUser(macAddress2, new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFailure(FailureModel failureModel) {
            }

            public void onFinish(ConmdBean conmdBean) {
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() != 1 || conmdBean.getMessage() == null) {
                            SettingActivity.this.exitLogin.setText("退出登录");
                        } else if (conmdBean.getMessage().contains("请重新登录")) {
                            SettingActivity.this.exitLogin.setText("登 录");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, android.app.Activity, com.wx.assistants.activity.SettingActivity] */
    @OnClick({2131297622, 2131297049, 2131296561, 2131296649, 2131297363, 2131297693, 2131297623, 2131296601, 2131296666, 2131297350})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296601:
                PublicTextActivity.start(this, "免责声明", getResources().getString(2131689571));
                return;
            case 2131296649:
                if ("登 录".equals(this.exitLogin.getText().toString())) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.putExtra("isNeedBack", true);
                    startActivity(intent);
                    finish();
                    return;
                } else if ("退出登录".equals(this.exitLogin.getText().toString())) {
                    SPUtils.put(this, "ws_baby_phone", "");
                    SPUtils.put(this, "user_token", "");
                    SPUtils.put(this, "ws_baby_user_info", "");
                    MobclickAgent.onProfileSignOff();
                    finish();
                    return;
                } else {
                    return;
                }
            case 2131296666:
                startActivity(InvitationBindActivity.class, true, true);
                return;
            case 2131297049:
                back();
                return;
            case 2131297350:
                startActivity(LoginPasswordSettingActivity.class, true, true);
                return;
            case 2131297622:
                WebViewActivity.startActivity(this, "微商宝贝隐私政策", "file:///android_asset/use_need_know.html");
                return;
            case 2131297623:
                WebViewActivity.startActivity(this, "微商宝贝用户服务协议", "file:///android_asset/use_need_know_1.html");
                return;
            case 2131297693:
                PublicTextActivity.start(this, "支持微信版本说明", getResources().getString(2131689879));
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getcode(java.lang.String r8) {
        /*
            java.lang.String r0 = "GB2312"
            java.lang.String r1 = "ISO-8859-1"
            java.lang.String r2 = "UTF-8"
            java.lang.String r3 = "GBK"
            java.lang.String r4 = "Big5"
            java.lang.String r5 = "UTF-16LE"
            java.lang.String r6 = "Shift_JIS"
            java.lang.String r7 = "EUC-JP"
            java.lang.String[] r0 = new java.lang.String[]{r0, r1, r2, r3, r4, r5, r6, r7}
            r1 = 0
        L_0x0015:
            int r2 = r0.length
            if (r1 >= r2) goto L_0x0033
            java.lang.String r2 = new java.lang.String     // Catch:{ Exception -> 0x0030, all -> 0x002e }
            r3 = r0[r1]     // Catch:{ Exception -> 0x0030, all -> 0x002e }
            byte[] r3 = r8.getBytes(r3)     // Catch:{ Exception -> 0x0030, all -> 0x002e }
            r4 = r0[r1]     // Catch:{ Exception -> 0x0030, all -> 0x002e }
            r2.<init>(r3, r4)     // Catch:{ Exception -> 0x0030, all -> 0x002e }
            boolean r2 = r8.equals(r2)     // Catch:{ Exception -> 0x0030, all -> 0x002e }
            if (r2 == 0) goto L_0x0030
            r2 = r0[r1]     // Catch:{ Exception -> 0x0030, all -> 0x002e }
            return r2
        L_0x002e:
            r8 = move-exception
            throw r8
        L_0x0030:
            int r1 = r1 + 1
            goto L_0x0015
        L_0x0033:
            java.lang.String r8 = ""
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.SettingActivity.getcode(java.lang.String):java.lang.String");
    }
}
