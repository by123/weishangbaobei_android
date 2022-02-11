package com.wx.assistants.activity;

import android.content.Intent;
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
import com.google.gson.Gson;
import com.stub.StubApp;
import com.umeng.analytics.MobclickAgent;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.server.request.LoginRequest;
import com.wx.assistants.server.request.PasswordLoginRequest;
import com.wx.assistants.server.request.VerificationCodeRequest;
import com.wx.assistants.utils.Code;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;

public class LoginActivity extends BaseActivity {
    @BindView(2131296656)
    ImageView eyeImg;
    @BindView(2131296657)
    LinearLayout eyeLayout;
    @BindView(2131296782)
    EditText imgVerificationEditText;
    private boolean isHide = true;
    /* access modifiers changed from: private */
    public boolean isNeedBack = false;
    private boolean isSmsLogin = true;
    @BindView(2131297081)
    EditText numberEditText;
    @BindView(2131297116)
    EditText passwordEditText;
    @BindView(2131297117)
    LinearLayout passwordLoginLayout;
    @BindView(2131297118)
    TextView passwordLoginPoint;
    @BindView(2131297392)
    LinearLayout smsLoginLayout;
    @BindView(2131297393)
    EditText smsVerificationEditText;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297448)
    TextView switchLogin;
    @BindView(2131297627)
    TextView verification_btn;
    @BindView(2131297628)
    ImageView verification_img;

    static {
        StubApp.interface11(8947);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
    /* access modifiers changed from: private */
    public void DelaySend() {
        String trim = this.numberEditText.getText().toString().trim();
        if (trim != null && !"".equals(trim)) {
            SPUtils.put(this, "ws_baby_phone", trim);
        }
        new CountDownTimer(60000, 1000) {
            public void onFinish() {
                LoginActivity.this.verification_btn.setEnabled(true);
                LoginActivity.this.verification_btn.setText("发送验证码");
            }

            public void onTick(long j) {
                LoginActivity.this.verification_btn.setEnabled(false);
                TextView textView = LoginActivity.this.verification_btn;
                textView.setText((j / 1000) + "秒");
            }
        }.start();
    }

    public void getUserInfo() {
        ApiWrapper.getInstance().getUser(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFailure(FailureModel failureModel) {
                LoginActivity.this.dismissLoadingDialog();
            }

            /* JADX WARNING: type inference failed for: r0v11, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
            /* JADX WARNING: type inference failed for: r1v3, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
            /* JADX WARNING: type inference failed for: r0v18, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
            public void onFinish(ConmdBean conmdBean) {
                LoginActivity.this.dismissLoadingDialog();
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() == 1 && conmdBean.getMessage() != null) {
                            ToastUtils.showToast(LoginActivity.this, "获取用户数据失败");
                        } else if (conmdBean.getCode() == 0) {
                            try {
                                SPUtils.put(MyApplication.getConText(), "ws_baby_user_info", new Gson().toJson(conmdBean.getData()));
                            } catch (Exception e) {
                            }
                            MobclickAgent.onProfileSignIn(LoginActivity.this.numberEditText.getText().toString());
                            ToastUtils.showToast(LoginActivity.this, "登录成功");
                            if (LoginActivity.this.isNeedBack) {
                                LoginActivity.this.finish();
                                return;
                            }
                            LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            LoginActivity.this.finish();
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

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.activity.LoginActivity, com.wx.assistants.base.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.verification_btn.setEnabled(true);
        String str = (String) SPUtils.get(this, "ws_baby_phone", "");
        if (str != null && !"".equals(str) && this.numberEditText != null) {
            this.numberEditText.setText(str);
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
    @OnClick({2131297628, 2131297627, 2131297448, 2131297425, 2131296657})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296657:
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
                    return;
                }
                return;
            case 2131297425:
                if (!RegexUtils.isMobileExact(this.numberEditText.getText().toString().trim()) && this.numberEditText.getText().toString().trim().length() != 11) {
                    Toast.makeText(this, "请输入正确手机号", 0).show();
                    return;
                } else if (!this.isSmsLogin) {
                    String trim = this.passwordEditText.getText().toString().trim();
                    String trim2 = this.numberEditText.getText().toString().trim();
                    if (trim == null || "".equals(trim)) {
                        Toast.makeText(this, "请设置登录密码", 0).show();
                        return;
                    }
                    showLoadingDialog(".正在登录.");
                    ApiWrapper.getInstance().userLogin(new PasswordLoginRequest(trim2, trim), (ApiWrapper.CallbackListener<ConmdBean>) new ApiWrapper.CallbackListener<ConmdBean>() {
                        /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
                        public void onFailure(FailureModel failureModel) {
                            LoginActivity.this.dismissLoadingDialog();
                            ToastUtils.showToast(LoginActivity.this, failureModel.getErrorMessage());
                        }

                        /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
                        /* JADX WARNING: type inference failed for: r0v5, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
                        /* JADX WARNING: type inference failed for: r0v6, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
                        public void onFinish(ConmdBean conmdBean) {
                            if (conmdBean == null || conmdBean.getCode() != 0) {
                                LoginActivity.this.dismissLoadingDialog();
                                ToastUtils.showToast(LoginActivity.this, conmdBean.getMessage());
                                return;
                            }
                            try {
                                SPUtils.put(LoginActivity.this, "ws_baby_phone", LoginActivity.this.numberEditText.getText().toString());
                                SPUtils.put(LoginActivity.this, "user_token", conmdBean.getData());
                                LoginActivity.this.getUserInfo();
                            } catch (Exception e) {
                                e.printStackTrace();
                                LogUtils.log("WS_BABY_Catch_11");
                            }
                        }
                    });
                    return;
                } else if (!Code.getInstance().getCode().toLowerCase().equals(this.imgVerificationEditText.getText().toString().trim().toLowerCase())) {
                    Toast.makeText(this, "图片验证码不正确", 0).show();
                    return;
                } else {
                    String trim3 = this.smsVerificationEditText.getText().toString().trim();
                    if (trim3 == null || "".equals(trim3)) {
                        Toast.makeText(this, "请输入短信验证码", 0).show();
                        return;
                    }
                    showLoadingDialog(".正在登录.");
                    ApiWrapper.getInstance().userLogin(new LoginRequest(this.numberEditText.getText().toString().trim(), MacAddressUtils.getMacAddress(MyApplication.mContext), this.smsVerificationEditText.getText().toString().trim()), (ApiWrapper.CallbackListener<ConmdBean>) new ApiWrapper.CallbackListener<ConmdBean>() {
                        /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
                        public void onFailure(FailureModel failureModel) {
                            LoginActivity.this.dismissLoadingDialog();
                            ToastUtils.showToast(LoginActivity.this, failureModel.getErrorMessage());
                        }

                        /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
                        /* JADX WARNING: type inference failed for: r0v5, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
                        /* JADX WARNING: type inference failed for: r0v6, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
                        public void onFinish(ConmdBean conmdBean) {
                            if (conmdBean == null || conmdBean.getCode() != 0) {
                                LoginActivity.this.dismissLoadingDialog();
                                ToastUtils.showToast(LoginActivity.this, conmdBean.getMessage());
                                return;
                            }
                            try {
                                SPUtils.put(LoginActivity.this, "ws_baby_phone", LoginActivity.this.numberEditText.getText().toString());
                                SPUtils.put(LoginActivity.this, "user_token", conmdBean.getData());
                                LoginActivity.this.getUserInfo();
                            } catch (Exception e) {
                                e.printStackTrace();
                                LogUtils.log("WS_BABY_Catch_11");
                            }
                        }
                    });
                    return;
                }
            case 2131297448:
                if (this.isSmsLogin) {
                    this.isSmsLogin = false;
                    this.switchLogin.setText("使用短信验证码登录");
                    this.smsLoginLayout.setVisibility(8);
                    this.passwordLoginLayout.setVisibility(0);
                    this.passwordLoginPoint.setVisibility(0);
                    return;
                }
                this.isSmsLogin = true;
                this.switchLogin.setText("使用密码登录");
                this.smsLoginLayout.setVisibility(0);
                this.passwordLoginLayout.setVisibility(8);
                this.passwordLoginPoint.setVisibility(8);
                return;
            case 2131297627:
                if (!RegexUtils.isMobileExact(this.numberEditText.getText().toString().trim()) && this.numberEditText.getText().toString().trim().length() != 11) {
                    Toast.makeText(this, "请输入正确手机号", 0).show();
                    return;
                } else if (!Code.getInstance().getCode().toLowerCase().equals(this.imgVerificationEditText.getText().toString().trim().toLowerCase())) {
                    Toast.makeText(this, "图片验证码不正确", 0).show();
                    return;
                } else {
                    showLoadingDialog(".正在获取.");
                    ApiWrapper.getInstance().getMessageVerificationCode(new VerificationCodeRequest(this.numberEditText.getText().toString().trim(), 0), new ApiWrapper.CallbackListener<ConmdBean>() {
                        /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
                        public void onFailure(FailureModel failureModel) {
                            LoginActivity.this.dismissLoadingDialog();
                            ToastUtils.showToast(LoginActivity.this, failureModel.getErrorMessage());
                        }

                        /* JADX WARNING: type inference failed for: r0v3, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
                        public void onFinish(ConmdBean conmdBean) {
                            try {
                                LoginActivity.this.dismissLoadingDialog();
                                if (conmdBean == null || conmdBean.getCode() != 0) {
                                    ToastUtils.showToast(LoginActivity.this, conmdBean.getMessage());
                                } else {
                                    LoginActivity.this.DelaySend();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                LogUtils.log("WS_BABY_Catch_10");
                            }
                        }
                    });
                    return;
                }
            case 2131297628:
                this.verification_img.setImageBitmap(Code.getInstance().createBitmap());
                return;
            default:
                return;
        }
    }
}
