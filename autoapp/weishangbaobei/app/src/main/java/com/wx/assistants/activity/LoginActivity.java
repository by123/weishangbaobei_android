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

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
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
                        /* JADX WARNING: type inference failed for: r0v3, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
                        /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
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

                        /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
                        public void onFailure(FailureModel failureModel) {
                            LoginActivity.this.dismissLoadingDialog();
                            ToastUtils.showToast(LoginActivity.this, failureModel.getErrorMessage());
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
                        /* JADX WARNING: type inference failed for: r0v3, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
                        /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
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

                        /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
                        public void onFailure(FailureModel failureModel) {
                            LoginActivity.this.dismissLoadingDialog();
                            ToastUtils.showToast(LoginActivity.this, failureModel.getErrorMessage());
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

                        /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
                        public void onFailure(FailureModel failureModel) {
                            LoginActivity.this.dismissLoadingDialog();
                            ToastUtils.showToast(LoginActivity.this, failureModel.getErrorMessage());
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

    /* JADX WARNING: type inference failed for: r8v0, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
    /* access modifiers changed from: private */
    public void DelaySend() {
        String trim = this.numberEditText.getText().toString().trim();
        if (trim != null && !"".equals(trim)) {
            SPUtils.put(this, "ws_baby_phone", trim);
        }
        new CountDownTimer(60000, 1000) {
            public void onTick(long j) {
                LoginActivity.this.verification_btn.setEnabled(false);
                TextView textView = LoginActivity.this.verification_btn;
                textView.setText((j / 1000) + "秒");
            }

            public void onFinish() {
                LoginActivity.this.verification_btn.setEnabled(true);
                LoginActivity.this.verification_btn.setText("发送验证码");
            }
        }.start();
    }

    public void getUserInfo() {
        ApiWrapper.getInstance().getUser(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: type inference failed for: r3v8, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
            /* JADX WARNING: type inference failed for: r0v5, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
            /* JADX WARNING: type inference failed for: r3v14, types: [com.wx.assistants.activity.LoginActivity, android.content.Context] */
            /* JADX WARNING: Can't wrap try/catch for region: R(6:10|11|12|13|14|(2:16|24)(2:17|25)) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0038 */
            /* JADX WARNING: Removed duplicated region for block: B:16:0x0056 A[Catch:{ Exception -> 0x0070 }] */
            /* JADX WARNING: Removed duplicated region for block: B:17:0x005c A[Catch:{ Exception -> 0x0070 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onFinish(com.wx.assistants.bean.ConmdBean r3) {
                /*
                    r2 = this;
                    com.wx.assistants.activity.LoginActivity r0 = com.wx.assistants.activity.LoginActivity.this
                    r0.dismissLoadingDialog()
                    if (r3 == 0) goto L_0x0074
                    int r0 = r3.getCode()     // Catch:{ Exception -> 0x0070 }
                    r1 = 1
                    if (r0 != r1) goto L_0x001c
                    java.lang.String r0 = r3.getMessage()     // Catch:{ Exception -> 0x0070 }
                    if (r0 == 0) goto L_0x001c
                    com.wx.assistants.activity.LoginActivity r3 = com.wx.assistants.activity.LoginActivity.this     // Catch:{ Exception -> 0x0070 }
                    java.lang.String r0 = "获取用户数据失败"
                    com.wx.assistants.utils.ToastUtils.showToast(r3, r0)     // Catch:{ Exception -> 0x0070 }
                    goto L_0x0074
                L_0x001c:
                    int r0 = r3.getCode()     // Catch:{ Exception -> 0x0070 }
                    if (r0 != 0) goto L_0x0074
                    com.google.gson.Gson r0 = new com.google.gson.Gson     // Catch:{ Exception -> 0x0070 }
                    r0.<init>()     // Catch:{ Exception -> 0x0070 }
                    java.lang.Object r3 = r3.getData()     // Catch:{ Exception -> 0x0070 }
                    java.lang.String r3 = r0.toJson(r3)     // Catch:{ Exception -> 0x0070 }
                    android.content.Context r0 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x0038 }
                    java.lang.String r1 = "ws_baby_user_info"
                    com.wx.assistants.utils.SPUtils.put(r0, r1, r3)     // Catch:{ Exception -> 0x0038 }
                L_0x0038:
                    com.wx.assistants.activity.LoginActivity r3 = com.wx.assistants.activity.LoginActivity.this     // Catch:{ Exception -> 0x0070 }
                    android.widget.EditText r3 = r3.numberEditText     // Catch:{ Exception -> 0x0070 }
                    android.text.Editable r3 = r3.getText()     // Catch:{ Exception -> 0x0070 }
                    java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0070 }
                    com.umeng.analytics.MobclickAgent.onProfileSignIn(r3)     // Catch:{ Exception -> 0x0070 }
                    com.wx.assistants.activity.LoginActivity r3 = com.wx.assistants.activity.LoginActivity.this     // Catch:{ Exception -> 0x0070 }
                    java.lang.String r0 = "登录成功"
                    com.wx.assistants.utils.ToastUtils.showToast(r3, r0)     // Catch:{ Exception -> 0x0070 }
                    com.wx.assistants.activity.LoginActivity r3 = com.wx.assistants.activity.LoginActivity.this     // Catch:{ Exception -> 0x0070 }
                    boolean r3 = r3.isNeedBack     // Catch:{ Exception -> 0x0070 }
                    if (r3 == 0) goto L_0x005c
                    com.wx.assistants.activity.LoginActivity r3 = com.wx.assistants.activity.LoginActivity.this     // Catch:{ Exception -> 0x0070 }
                    r3.finish()     // Catch:{ Exception -> 0x0070 }
                    goto L_0x0074
                L_0x005c:
                    android.content.Intent r3 = new android.content.Intent     // Catch:{ Exception -> 0x0070 }
                    com.wx.assistants.activity.LoginActivity r0 = com.wx.assistants.activity.LoginActivity.this     // Catch:{ Exception -> 0x0070 }
                    java.lang.Class<com.wx.assistants.activity.MainActivity> r1 = com.wx.assistants.activity.MainActivity.class
                    r3.<init>(r0, r1)     // Catch:{ Exception -> 0x0070 }
                    com.wx.assistants.activity.LoginActivity r0 = com.wx.assistants.activity.LoginActivity.this     // Catch:{ Exception -> 0x0070 }
                    r0.startActivity(r3)     // Catch:{ Exception -> 0x0070 }
                    com.wx.assistants.activity.LoginActivity r3 = com.wx.assistants.activity.LoginActivity.this     // Catch:{ Exception -> 0x0070 }
                    r3.finish()     // Catch:{ Exception -> 0x0070 }
                    goto L_0x0074
                L_0x0070:
                    r3 = move-exception
                    r3.printStackTrace()
                L_0x0074:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.LoginActivity.AnonymousClass5.onFinish(com.wx.assistants.bean.ConmdBean):void");
            }

            public void onFailure(FailureModel failureModel) {
                LoginActivity.this.dismissLoadingDialog();
            }
        });
    }
}
