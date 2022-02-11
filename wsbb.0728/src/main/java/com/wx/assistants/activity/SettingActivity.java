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

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    private static String getcode(String str) {
        String[] strArr = {"GB2312", "ISO-8859-1", "UTF-8", "GBK", "Big5", "UTF-16LE", "Shift_JIS", "EUC-JP"};
        for (int i = 0; i < strArr.length; i++) {
            if (str.equals(new String(str.getBytes(strArr[i]), strArr[i]))) {
                return strArr[i];
            }
        }
        return "";
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.SettingActivity] */
    public void initData() {
        this.navTitle.setText("我的设置");
        this.bubbleSeekBar.setProgress((float) ((Integer) SPUtils.get(this, "seek_bar_speed", 0)).intValue());
        this.bubbleSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int i, float f) {
            }

            /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.wx.assistants.activity.SettingActivity] */
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z) {
                SPUtils.put(SettingActivity.this, "seek_bar_speed", Integer.valueOf(i));
            }

            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z) {
            }
        });
        this.switchBtn.setChecked(((Boolean) SPUtils.get(this, "isCanShowVerDialog", true)).booleanValue());
        this.switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.wx.assistants.activity.SettingActivity] */
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SPUtils.put(SettingActivity.this, "isCanShowVerDialog", Boolean.valueOf(z));
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

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, android.app.Activity, com.wx.assistants.activity.SettingActivity] */
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
}
