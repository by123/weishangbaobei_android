package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.stub.StubApp;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.ConmdListBean;
import com.wx.assistants.bean.ExtractRecordBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.UserInfoBean;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.server.request.UserExtractRequest;
import com.wx.assistants.server.request.ZFBAccountRequest;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.MoneyUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import java.util.ArrayList;
import java.util.Date;

public class AlipayWithdrawalsActivity extends BaseActivity {
    private String accountName;
    private String accountNo;
    @BindView(2131296574)
    TextView dateText;
    @BindView(2131296621)
    EditText editTextWithdrawMoney;
    @BindView(2131296622)
    EditText editTextZFBAccount;
    @BindView(2131296623)
    EditText editTextZFBName;
    @BindView(2131296654)
    TextView extractMoneyText;
    @BindView(2131296655)
    TextView extractResult;
    @BindView(2131296893)
    LinearLayout lastRecordLayout;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297489)
    TextView textViewAllWithdraw;
    @BindView(2131297491)
    TextView textViewMoney;
    @BindView(2131297494)
    TextView textViewWithdrawRecord;
    @BindView(2131297508)
    TextView timeText;
    private UserInfoBean.UserBean userBean;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(8236);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.videoIntroduceLayout.setVisibility(4);
        this.navTitle.setText("提现到支付宝");
        this.startWx.setText("立即提现");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.AlipayWithdrawalsActivity] */
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

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getUser();
        initRecordData();
    }

    public void getUser() {
        ApiWrapper.getInstance().getUser(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: type inference failed for: r3v6, types: [com.wx.assistants.activity.AlipayWithdrawalsActivity, android.app.Activity] */
            /* JADX WARNING: Can't wrap try/catch for region: R(6:14|15|16|17|18|26) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004f */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onFinish(com.wx.assistants.bean.ConmdBean r3) {
                /*
                    r2 = this;
                    com.wx.assistants.activity.AlipayWithdrawalsActivity r0 = com.wx.assistants.activity.AlipayWithdrawalsActivity.this
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout
                    if (r0 == 0) goto L_0x000d
                    com.wx.assistants.activity.AlipayWithdrawalsActivity r0 = com.wx.assistants.activity.AlipayWithdrawalsActivity.this
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout
                    r0.finishRefresh()
                L_0x000d:
                    if (r3 == 0) goto L_0x0059
                    int r0 = r3.getCode()     // Catch:{ Exception -> 0x0055 }
                    r1 = 1
                    if (r0 != r1) goto L_0x0033
                    java.lang.String r0 = r3.getMessage()     // Catch:{ Exception -> 0x0055 }
                    if (r0 == 0) goto L_0x0033
                    java.lang.String r3 = r3.getMessage()     // Catch:{ Exception -> 0x0055 }
                    java.lang.String r0 = "请重新登录"
                    boolean r3 = r3.contains(r0)     // Catch:{ Exception -> 0x0055 }
                    if (r3 == 0) goto L_0x0059
                    com.wx.assistants.activity.AlipayWithdrawalsActivity r3 = com.wx.assistants.activity.AlipayWithdrawalsActivity.this     // Catch:{ Exception -> 0x0055 }
                    com.wx.assistants.activity.AlipayWithdrawalsActivity$1$1 r0 = new com.wx.assistants.activity.AlipayWithdrawalsActivity$1$1     // Catch:{ Exception -> 0x0055 }
                    r0.<init>()     // Catch:{ Exception -> 0x0055 }
                    com.wx.assistants.utils.DialogUIUtils.goLogin(r3, r0, r1)     // Catch:{ Exception -> 0x0055 }
                    goto L_0x0059
                L_0x0033:
                    int r0 = r3.getCode()     // Catch:{ Exception -> 0x0055 }
                    if (r0 != 0) goto L_0x0059
                    com.google.gson.Gson r0 = new com.google.gson.Gson     // Catch:{ Exception -> 0x0055 }
                    r0.<init>()     // Catch:{ Exception -> 0x0055 }
                    java.lang.Object r3 = r3.getData()     // Catch:{ Exception -> 0x0055 }
                    java.lang.String r3 = r0.toJson(r3)     // Catch:{ Exception -> 0x0055 }
                    android.content.Context r0 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x004f }
                    java.lang.String r1 = "ws_baby_user_info"
                    com.wx.assistants.utils.SPUtils.put(r0, r1, r3)     // Catch:{ Exception -> 0x004f }
                L_0x004f:
                    com.wx.assistants.activity.AlipayWithdrawalsActivity r0 = com.wx.assistants.activity.AlipayWithdrawalsActivity.this     // Catch:{ Exception -> 0x0055 }
                    r0.setUserInfo(r3)     // Catch:{ Exception -> 0x0055 }
                    goto L_0x0059
                L_0x0055:
                    r3 = move-exception
                    r3.printStackTrace()
                L_0x0059:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.AlipayWithdrawalsActivity.AnonymousClass1.onFinish(com.wx.assistants.bean.ConmdBean):void");
            }

            public void onFailure(FailureModel failureModel) {
                if (AlipayWithdrawalsActivity.this.refreshLayout != null) {
                    AlipayWithdrawalsActivity.this.refreshLayout.finishRefresh(false);
                }
            }
        });
    }

    public void setUserInfo(String str) {
        this.userBean = (UserInfoBean.UserBean) new Gson().fromJson(str, UserInfoBean.UserBean.class);
        if (this.userBean != null) {
            try {
                String userId = this.userBean.getUserId();
                if (userId != null && !"".equals(userId)) {
                    SPUtils.put(MyApplication.getConText(), "ws_baby_user_id", userId);
                }
            } catch (Exception unused) {
            }
            this.accountNo = this.userBean.getAccountNo();
            this.accountName = this.userBean.getAccountName();
            this.editTextZFBAccount.setText(this.accountNo);
            this.editTextZFBName.setText(this.accountName);
            TextView textView = this.textViewMoney;
            textView.setText(this.userBean.getWithdrawBalance() + "");
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, com.wx.assistants.activity.AlipayWithdrawalsActivity] */
    @OnClick({2131297052, 2131297049, 2131297425, 2131297491, 2131296622, 2131296623, 2131297494, 2131296621, 2131297489})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131297049:
                back();
                return;
            case 2131297052:
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.满1元即可提现\n\n2.每次提现只能提现整数\n\n3.提现申请将于48小时内到账、双休日、法定节假日顺延\n\n4.如发现作弊行为，直接进行封号处理，概不结算\n\n");
                return;
            case 2131297425:
                final String obj = this.editTextZFBAccount.getText().toString();
                final String obj2 = this.editTextZFBName.getText().toString();
                final String obj3 = this.editTextWithdrawMoney.getText().toString();
                if (obj == null || "".equals(obj)) {
                    ToastUtils.showToast(this, "请输入支付宝账户");
                    return;
                } else if (obj2 == null || "".equals(obj2)) {
                    ToastUtils.showToast(this, "请输入支付宝账户姓名");
                    return;
                } else if (obj3 == null || "".equals(obj3)) {
                    ToastUtils.showToast(this, "请输入支付宝金额");
                    return;
                } else {
                    try {
                        if (Integer.valueOf((int) Double.parseDouble(obj3)).intValue() <= 0) {
                            ToastUtils.showToast(this, "提现金额至少1元");
                            return;
                        }
                    } catch (Exception unused) {
                    }
                    if (obj.equals(this.accountNo) && obj2.equals(this.accountName)) {
                        applyExtract(obj, obj2, obj3);
                        return;
                    } else if (this.userBean != null) {
                        ApiWrapper.getInstance().updateZFBAccount(new ZFBAccountRequest(obj2, obj, this.userBean.getGovtIdNo(), this.userBean.getGovtIdType()), new ApiWrapper.CallbackListener<ConmdBean>() {
                            /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.wx.assistants.activity.AlipayWithdrawalsActivity] */
                            public void onFinish(ConmdBean conmdBean) {
                                if (conmdBean != null) {
                                    try {
                                        if (conmdBean.getCode() == 0) {
                                            AlipayWithdrawalsActivity.this.applyExtract(obj, obj2, obj3);
                                            return;
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        return;
                                    }
                                }
                                ToastUtils.showToast(AlipayWithdrawalsActivity.this, conmdBean.getMessage());
                            }

                            /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.wx.assistants.activity.AlipayWithdrawalsActivity] */
                            public void onFailure(FailureModel failureModel) {
                                if (failureModel != null) {
                                    ToastUtils.showToast(AlipayWithdrawalsActivity.this, failureModel.getErrorMessage());
                                }
                            }
                        });
                        return;
                    } else {
                        return;
                    }
                }
            case 2131297489:
                this.editTextWithdrawMoney.setText(this.textViewMoney.getText().toString());
                return;
            case 2131297494:
                startActivity(new Intent(this, WithdrawalsRecordActivity.class));
                return;
            default:
                return;
        }
    }

    public void applyExtract(String str, String str2, String str3) {
        ApiWrapper.getInstance().applyExtract(new UserExtractRequest(str, str2, MoneyUtils.changeY2F(Double.parseDouble(str3))), new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.AlipayWithdrawalsActivity] */
            /* JADX WARNING: type inference failed for: r3v2, types: [android.content.Context, com.wx.assistants.activity.AlipayWithdrawalsActivity] */
            public void onFinish(ConmdBean conmdBean) {
                if (conmdBean == null) {
                    return;
                }
                if (conmdBean.getCode() == 0) {
                    DialogUIUtils.dialogNilTile(AlipayWithdrawalsActivity.this, "提交成功，请您耐心等待审核", new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                } else {
                    ToastUtils.showToast(AlipayWithdrawalsActivity.this, conmdBean.getMessage());
                }
            }

            /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.wx.assistants.activity.AlipayWithdrawalsActivity] */
            public void onFailure(FailureModel failureModel) {
                if (failureModel != null) {
                    ToastUtils.showToast(AlipayWithdrawalsActivity.this, failureModel.getErrorMessage());
                }
            }
        });
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [android.content.Context, com.wx.assistants.activity.AlipayWithdrawalsActivity] */
    public void initRecordData() {
        ApiWrapper.getInstance().myExtractList(0, 1, MacAddressUtils.getMacAddress(MyApplication.mContext), (String) SPUtils.get(this, "user_token", ""), new ApiWrapper.CallbackListener<ConmdListBean>() {
            public void onFailure(FailureModel failureModel) {
            }

            public void onFinish(ConmdListBean conmdListBean) {
                if (conmdListBean != null) {
                    try {
                        if (conmdListBean.getCode() == 0) {
                            ConmdListBean.LimitPageBean data = conmdListBean.getData();
                            if (data.getSize() > 0) {
                                if (data.getSize() < data.getPageSize()) {
                                    AlipayWithdrawalsActivity.this.refreshLayout.setEnableLoadMore(false);
                                }
                                Gson gson = new Gson();
                                ArrayList arrayList = (ArrayList) gson.fromJson(gson.toJson(data.getList()), new TypeToken<ArrayList<ExtractRecordBean>>() {
                                }.getType());
                                if (arrayList == null || arrayList.size() != 1) {
                                    AlipayWithdrawalsActivity.this.lastRecordLayout.setVisibility(8);
                                    return;
                                }
                                AlipayWithdrawalsActivity.this.lastRecordLayout.setVisibility(0);
                                ExtractRecordBean extractRecordBean = (ExtractRecordBean) arrayList.get(0);
                                Date createTime = extractRecordBean.getCreateTime();
                                String withdrawAmount = extractRecordBean.getWithdrawAmount();
                                String state = extractRecordBean.getState();
                                String result = extractRecordBean.getResult();
                                String convertDate2String = DateUtils.convertDate2String(createTime, "yyyy-MM-dd");
                                String convertDate2String2 = DateUtils.convertDate2String(createTime, "HH:mm:ss");
                                AlipayWithdrawalsActivity.this.dateText.setText(convertDate2String);
                                AlipayWithdrawalsActivity.this.timeText.setText(convertDate2String2);
                                TextView textView = AlipayWithdrawalsActivity.this.extractMoneyText;
                                textView.setText("提现" + withdrawAmount + "元");
                                if (!"1.0".equals(state)) {
                                    if (!"1".equals(state)) {
                                        if (!SocializeConstants.PROTOCOL_VERSON.equals(state)) {
                                            if (!"2".equals(state)) {
                                                if (!"3.0".equals(state)) {
                                                    if (!"3".equals(state)) {
                                                        if ("4.0".equals(state) || "4".equals(state)) {
                                                            TextView textView2 = AlipayWithdrawalsActivity.this.extractResult;
                                                            if (result == null) {
                                                                result = "支付失败";
                                                            }
                                                            textView2.setText(result);
                                                            AlipayWithdrawalsActivity.this.extractResult.setTextColor(AlipayWithdrawalsActivity.this.getResources().getColor(2131099759));
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                }
                                                AlipayWithdrawalsActivity.this.extractResult.setText("已完成");
                                                AlipayWithdrawalsActivity.this.extractResult.setTextColor(AlipayWithdrawalsActivity.this.getResources().getColor(2131099740));
                                                return;
                                            }
                                        }
                                        AlipayWithdrawalsActivity.this.extractResult.setText("取消");
                                        AlipayWithdrawalsActivity.this.extractResult.setTextColor(AlipayWithdrawalsActivity.this.getResources().getColor(2131099759));
                                        return;
                                    }
                                }
                                AlipayWithdrawalsActivity.this.extractResult.setText("申请中");
                                AlipayWithdrawalsActivity.this.extractResult.setTextColor(AlipayWithdrawalsActivity.this.getResources().getColor(2131099768));
                                return;
                            }
                            AlipayWithdrawalsActivity.this.lastRecordLayout.setVisibility(8);
                            return;
                        }
                        AlipayWithdrawalsActivity.this.lastRecordLayout.setVisibility(8);
                    } catch (Exception unused) {
                    }
                }
            }
        });
    }
}
