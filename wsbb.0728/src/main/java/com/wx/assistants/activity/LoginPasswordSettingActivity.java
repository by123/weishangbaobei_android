package com.wx.assistants.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.blankj.utilcode.util.RegexUtils;
import com.stub.StubApp;
import com.umeng.analytics.MobclickAgent;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.server.request.PasswordSettingRequest;
import com.wx.assistants.server.request.VerificationCodeRequest;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class LoginPasswordSettingActivity extends BaseActivity {
    @BindView(2131296656)
    ImageView eyeImg;
    @BindView(2131296657)
    LinearLayout eyeLayout;
    private boolean isHide = true;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297084)
    TextView numberText;
    @BindView(2131297116)
    EditText passwordEditText;
    @BindView(2131297363)
    ShadowLinearLayout shadowLinearLayout;
    @BindView(2131297437)
    Button submitSetting;
    @BindView(2131297625)
    TextView verificationBtn;
    @BindView(2131297626)
    EditText verificationEt;

    static {
        StubApp.interface11(8957);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.wx.assistants.activity.LoginPasswordSettingActivity, android.content.Context] */
    /* access modifiers changed from: private */
    public void DelaySend() {
        String trim = this.numberText.getText().toString().trim();
        if (trim != null && !"".equals(trim)) {
            SPUtils.put(this, "ws_baby_phone", trim);
        }
        new CountDownTimer(60000, 1000) {
            public void onFinish() {
                LoginPasswordSettingActivity.this.verificationBtn.setEnabled(true);
                LoginPasswordSettingActivity.this.verificationBtn.setText("发送验证码");
            }

            public void onTick(long j) {
                LoginPasswordSettingActivity.this.verificationBtn.setEnabled(false);
                TextView textView = LoginPasswordSettingActivity.this.verificationBtn;
                textView.setText((j / 1000) + "秒");
            }
        }.start();
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.activity.LoginPasswordSettingActivity, com.wx.assistants.base.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.verificationBtn.setEnabled(true);
        String str = (String) SPUtils.get(this, "ws_baby_phone", "");
        if (str != null && !"".equals(str) && this.numberText != null) {
            this.numberText.setText(str);
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.wx.assistants.activity.LoginPasswordSettingActivity, android.content.Context] */
    @OnClick({2131297437, 2131297049, 2131297625, 2131296657})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131296657) {
            if (this.isHide) {
                this.isHide = false;
                this.eyeImg.setBackground(getResources().getDrawable(2131558627));
                this.passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                this.isHide = true;
                this.eyeImg.setBackground(getResources().getDrawable(2131558628));
                this.passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            if (this.passwordEditText != null && this.passwordEditText.getText().toString().trim().length() > 0) {
                this.passwordEditText.setSelection(this.passwordEditText.getText().toString().trim().length());
            }
        } else if (id == 2131297049) {
            back();
        } else if (id != 2131297437) {
            if (id == 2131297625) {
                if (RegexUtils.isMobileExact(this.numberText.getText().toString().trim()) || this.numberText.getText().toString().trim().length() == 11) {
                    showLoadingDialog(".正在获取.");
                    ApiWrapper.getInstance().getMessageVerificationCode(new VerificationCodeRequest(this.numberText.getText().toString().trim(), 1), new ApiWrapper.CallbackListener<ConmdBean>() {
                        /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.activity.LoginPasswordSettingActivity, android.content.Context] */
                        public void onFailure(FailureModel failureModel) {
                            LoginPasswordSettingActivity.this.dismissLoadingDialog();
                            ToastUtils.showToast(LoginPasswordSettingActivity.this, failureModel.getErrorMessage());
                        }

                        /* JADX WARNING: type inference failed for: r0v3, types: [com.wx.assistants.activity.LoginPasswordSettingActivity, android.content.Context] */
                        public void onFinish(ConmdBean conmdBean) {
                            try {
                                LoginPasswordSettingActivity.this.dismissLoadingDialog();
                                if (conmdBean == null || conmdBean.getCode() != 0) {
                                    ToastUtils.showToast(LoginPasswordSettingActivity.this, conmdBean.getMessage());
                                } else {
                                    LoginPasswordSettingActivity.this.DelaySend();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                LogUtils.log("WS_BABY_Catch_10");
                            }
                        }
                    });
                    return;
                }
                Toast.makeText(this, "请输入正确手机号", 0).show();
            }
        } else if (RegexUtils.isMobileExact(this.numberText.getText().toString().trim()) || this.numberText.getText().toString().trim().length() == 11) {
            String trim = this.verificationEt.getText().toString().trim();
            if (trim == null || "".equals(trim)) {
                Toast.makeText(this, "请输入短信验证码", 0).show();
                return;
            }
            String trim2 = this.passwordEditText.getText().toString().trim();
            if (trim2 == null || "".equals(trim2)) {
                Toast.makeText(this, "请设置登录密码", 0).show();
                return;
            }
            ApiWrapper.getInstance().loginPasswordSetting(new PasswordSettingRequest(this.numberText.getText().toString().trim(), trim2, this.verificationEt.getText().toString().trim()), new ApiWrapper.CallbackListener<ConmdBean>() {
                /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.activity.LoginPasswordSettingActivity, android.content.Context] */
                public void onFailure(FailureModel failureModel) {
                    ToastUtils.showToast(LoginPasswordSettingActivity.this, failureModel.getErrorMessage());
                }

                /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.activity.LoginPasswordSettingActivity, android.content.Context] */
                /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.LoginPasswordSettingActivity, android.content.Context] */
                /* JADX WARNING: type inference failed for: r0v5, types: [com.wx.assistants.activity.LoginPasswordSettingActivity, android.content.Context] */
                /* JADX WARNING: type inference failed for: r0v10, types: [com.wx.assistants.activity.LoginPasswordSettingActivity, android.content.Context] */
                public void onFinish(ConmdBean conmdBean) {
                    if (conmdBean == null || conmdBean.getCode() != 0) {
                        ToastUtils.showToast(LoginPasswordSettingActivity.this, conmdBean.getMessage());
                        return;
                    }
                    try {
                        SPUtils.put(LoginPasswordSettingActivity.this, "ws_baby_phone", LoginPasswordSettingActivity.this.numberText.getText().toString());
                        SPUtils.put(LoginPasswordSettingActivity.this, "user_token", conmdBean.getData());
                        MobclickAgent.onProfileSignIn(LoginPasswordSettingActivity.this.numberText.getText().toString());
                        ToastUtils.showToast(LoginPasswordSettingActivity.this, "登录密码设置成功");
                        LoginPasswordSettingActivity.this.finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                        LogUtils.log("WS_BABY_Catch_11");
                    }
                }
            });
        } else {
            Toast.makeText(this, "请输入正确手机号", 0).show();
        }
    }
}
