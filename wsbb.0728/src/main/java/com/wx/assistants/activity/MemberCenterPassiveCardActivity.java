package com.wx.assistants.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stub.StubApp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.proguard.ap;
import com.wx.assistants.adapter.MemberPassiveCardAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.MemberPriceAdapterBean;
import com.wx.assistants.bean.PassiveCardEvent;
import com.wx.assistants.bean.PayBean;
import com.wx.assistants.bean.UserInfoBean;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.server.request.PayRequest;
import com.wx.assistants.server.request.WxPayRequest;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.MoneyUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.GridItemDecoration;
import com.wx.assistants.zfbapi.PayResult;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.greenrobot.eventbus.EventBus;

public class MemberCenterPassiveCardActivity extends BaseActivity implements MemberPassiveCardAdapter.OnClick, BaseActivity.OnUserInfoListener {
    private static final int SDK_PAY_FLAG = 1;
    /* access modifiers changed from: private */
    public String alipayMoney = "";
    private String code = "";
    private long endTime;
    /* access modifiers changed from: private */
    public long endTime1;
    @BindView(2131296659)
    TextView favourable;
    private int id = -1;
    @BindView(2131296814)
    LinearLayout introduceLayout;
    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    public Handler mHandler = new Handler() {
        /* JADX WARNING: type inference failed for: r0v5, types: [android.content.Context, com.wx.assistants.activity.MemberCenterPassiveCardActivity] */
        /* JADX WARNING: type inference failed for: r0v6, types: [android.content.Context, com.wx.assistants.activity.MemberCenterPassiveCardActivity] */
        public void handleMessage(Message message) {
            if (message.what == 1) {
                PayResult payResult = new PayResult((Map) message.obj);
                payResult.getResult();
                if (TextUtils.equals(payResult.getResultStatus(), "9000")) {
                    ToastUtils.showToast(MemberCenterPassiveCardActivity.this, "支付成功");
                    EventBus.getDefault().post(new PassiveCardEvent());
                    MemberCenterPassiveCardActivity.this.finish();
                    return;
                }
                ToastUtils.showToast(MemberCenterPassiveCardActivity.this, "支付失败");
            }
        }
    };
    /* access modifiers changed from: private */
    public String memberId;
    /* access modifiers changed from: private */
    public MemberPassiveCardAdapter memberPriceRecylerviewAdapter;
    /* access modifiers changed from: private */
    public List<MemberPriceAdapterBean> memberlist = new ArrayList();
    /* access modifiers changed from: private */
    public IWXAPI msgApi;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;
    /* access modifiers changed from: private */
    public PayBean payBean;
    /* access modifiers changed from: private */
    public String phonename;
    /* access modifiers changed from: private */
    public int position = 0;
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    @BindView(2131297265)
    RecyclerView rv;
    @BindView(2131297348)
    TextView setAmountPrice;
    @BindView(2131297355)
    RelativeLayout setWxPayment;
    @BindView(2131297356)
    RadioButton setWxRadioButton;
    @BindView(2131297357)
    RelativeLayout setZfbPayment;
    @BindView(2131297358)
    CheckBox setZfbRadioButton;
    @BindView(2131297414)
    Button startPayBtn;
    private long startTime;
    /* access modifiers changed from: private */
    public long startTime1;
    /* access modifiers changed from: private */
    public String wxMoney = "";

    static {
        StubApp.interface11(9022);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.MemberCenterPassiveCardActivity] */
    private void WXPay() {
        showLoadingDialog("正在操作");
        String macAddress = MacAddressUtils.getMacAddress(MyApplication.mContext);
        if (this.payBean == null || this.payBean.getPhoneNumber() == null || this.payBean.getWxPrice() == null) {
            dismissLoadingDialog();
            ToastUtils.showToast(this, "请先选择会员");
            return;
        }
        String trim = this.payBean.getPhoneNumber().toString().trim();
        int changeY2F = MoneyUtils.changeY2F(Double.parseDouble(this.payBean.getWxPrice().toString().trim()));
        String str = this.memberId;
        ApiWrapper.getInstance().wechatPay(new WxPayRequest(macAddress, trim, changeY2F, str, this.id + ""), new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.MemberCenterPassiveCardActivity] */
            public void onFailure(FailureModel failureModel) {
                MemberCenterPassiveCardActivity.this.dismissLoadingDialog();
                ToastUtils.showToast(MemberCenterPassiveCardActivity.this, failureModel.getErrorMessage());
            }

            /* JADX WARNING: type inference failed for: r0v5, types: [android.content.Context, com.wx.assistants.activity.MemberCenterPassiveCardActivity] */
            /* JADX WARNING: type inference failed for: r2v3, types: [android.content.Context, com.wx.assistants.activity.MemberCenterPassiveCardActivity] */
            public void onFinish(ConmdBean conmdBean) {
                MemberCenterPassiveCardActivity.this.dismissLoadingDialog();
                if (conmdBean != null) {
                    try {
                        PayBean payBean = (PayBean) new Gson().fromJson(conmdBean.getData().toString(), PayBean.class);
                        if (conmdBean.getCode() == 1000) {
                            LogUtils.log("WS_BABY_000000");
                            long unused = MemberCenterPassiveCardActivity.this.startTime1 = System.currentTimeMillis();
                            IWXAPI unused2 = MemberCenterPassiveCardActivity.this.msgApi = WXAPIFactory.createWXAPI(MemberCenterPassiveCardActivity.this, MyApplication.WX_APP_ID, true);
                            MemberCenterPassiveCardActivity.this.msgApi.registerApp(MyApplication.WX_APP_ID);
                            long unused3 = MemberCenterPassiveCardActivity.this.endTime1 = System.currentTimeMillis();
                            LogUtils.log("WS_BABY_SIGN_TIME1 ## " + (MemberCenterPassiveCardActivity.this.endTime1 - MemberCenterPassiveCardActivity.this.startTime1));
                            PayReq payReq = new PayReq();
                            payReq.appId = MyApplication.WX_APP_ID;
                            payReq.partnerId = MyApplication.WX_Merchant_NUMBER;
                            payReq.prepayId = payBean.getPrepayid();
                            payReq.nonceStr = payBean.getNoncestr();
                            payReq.timeStamp = payBean.getTimestamp();
                            payReq.packageValue = "Sign=WXPay";
                            TreeMap treeMap = new TreeMap();
                            treeMap.put("appid", payReq.appId);
                            treeMap.put("noncestr", payReq.nonceStr);
                            treeMap.put("package", payReq.packageValue);
                            treeMap.put("partnerid", payReq.partnerId);
                            treeMap.put("prepayid", payReq.prepayId);
                            treeMap.put("timestamp", payReq.timeStamp);
                            payReq.sign = MemberCenterPassiveCardActivity.this.genPackageSign(treeMap, "IlIlIlIlIlweishang2018iLiLiLiLiL");
                            MemberCenterPassiveCardActivity.this.msgApi.registerApp(MyApplication.WX_APP_ID);
                            MemberCenterPassiveCardActivity.this.msgApi.sendReq(payReq);
                            Log.e(BaseActivity.TAG, "checkArgs===============" + payReq.checkArgs());
                            return;
                        }
                        ToastUtils.showToast(MemberCenterPassiveCardActivity.this, conmdBean.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.MemberCenterPassiveCardActivity] */
    private void ZFBPay() {
        String macAddress = MacAddressUtils.getMacAddress(MyApplication.mContext);
        if (this.payBean == null || this.payBean.getPhoneNumber() == null || this.payBean.getAliPrice() == null) {
            ToastUtils.showToast(this, "请先选择会员");
            return;
        }
        String trim = this.payBean.getPhoneNumber().toString().trim();
        int changeY2F = MoneyUtils.changeY2F(Double.parseDouble(this.payBean.getAliPrice().toString().trim()));
        String str = this.memberId;
        ApiWrapper.getInstance().alipay(new PayRequest(macAddress, trim, changeY2F, str, this.id + ""), new ApiWrapper.CallbackListener<ConmdBean<String>>() {
            /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.wx.assistants.activity.MemberCenterPassiveCardActivity] */
            public void onFailure(FailureModel failureModel) {
                ToastUtils.showToast(MemberCenterPassiveCardActivity.this, failureModel.getErrorMessage());
            }

            /* JADX WARNING: type inference failed for: r0v2, types: [android.content.Context, com.wx.assistants.activity.MemberCenterPassiveCardActivity] */
            public void onFinish(ConmdBean conmdBean) {
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() == 1000) {
                            final String str = (String) conmdBean.getData();
                            new Thread(new Runnable() {
                                /* JADX WARNING: type inference failed for: r1v1, types: [com.wx.assistants.activity.MemberCenterPassiveCardActivity, android.app.Activity] */
                                public void run() {
                                    Map payV2 = new PayTask(MemberCenterPassiveCardActivity.this).payV2(str, true);
                                    Log.i("msp", payV2.toString());
                                    Message message = new Message();
                                    message.what = 1;
                                    message.obj = payV2;
                                    MemberCenterPassiveCardActivity.this.mHandler.sendMessage(message);
                                }
                            }).start();
                            return;
                        }
                        ToastUtils.showToast(MemberCenterPassiveCardActivity.this, conmdBean.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public String genPackageSign(SortedMap<Object, Object> sortedMap, String str) {
        this.startTime = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry next : sortedMap.entrySet()) {
            String str2 = (String) next.getKey();
            Object value = next.getValue();
            if (value != null && !"".equals(value) && !"sign".equals(str2) && !"key".equals(str2)) {
                stringBuffer.append(str2 + "=" + value + "&");
            }
        }
        stringBuffer.append("key=" + str);
        Log.d("sb------------", stringBuffer.toString());
        String upperCase = getMessageDigest(stringBuffer.toString().getBytes()).toUpperCase();
        this.endTime = System.currentTimeMillis();
        LogUtils.log("WS_BABY_SIGN_TIME ## " + (this.endTime - this.startTime));
        return upperCase;
    }

    private String getMessageDigest(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            char[] cArr2 = new char[(r5 * 2)];
            int i = 0;
            for (byte b : instance.digest()) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b & ap.m];
            }
            return new String(cArr2);
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.adapter.MemberPassiveCardAdapter$OnClick, android.content.Context, com.wx.assistants.activity.MemberCenterPassiveCardActivity] */
    private void initView() {
        this.navTitle.setText("会员中心");
        this.memberPriceRecylerviewAdapter = new MemberPassiveCardAdapter(this.memberlist, this);
        this.rv.setAdapter(this.memberPriceRecylerviewAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        this.rv.addItemDecoration(new GridItemDecoration.Builder(this).setHorizontalSpan(2131165354).setVerticalSpan(2131165317).setColorResource(2131100063).setShowLastLine(false).build());
        this.rv.setLayoutManager(gridLayoutManager);
        this.memberPriceRecylerviewAdapter.setOnClick(this);
    }

    private void lighton() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 1.0f;
        getWindow().setAttributes(attributes);
    }

    public void click(int i) {
        this.memberPriceRecylerviewAdapter.setThisPosotion(i);
        this.memberPriceRecylerviewAdapter.notifyDataSetChanged();
        this.memberId = this.memberlist.get(i).getId() + "";
        this.wxMoney = this.memberlist.get(i).getWechatAmount().toString();
        this.alipayMoney = this.memberlist.get(i).getAliAmount().toString();
        this.wxMoney = MoneyUtils.removeDecimalPoints(this.wxMoney);
        this.alipayMoney = MoneyUtils.removeDecimalPoints(this.alipayMoney);
        if (this.setZfbRadioButton.isChecked()) {
            this.setAmountPrice.setText(this.alipayMoney);
        } else {
            this.setAmountPrice.setText(this.wxMoney);
        }
        this.payBean = new PayBean();
        if (!(this.alipayMoney == null || this.alipayMoney.length() == 0)) {
            this.payBean.setAliPrice(this.alipayMoney);
            this.payBean.setPhoneNumber(this.phonename);
        }
        if (this.wxMoney != null && this.wxMoney.length() != 0) {
            this.payBean.setWxPrice(this.wxMoney);
            this.payBean.setPhoneNumber(this.phonename);
        }
    }

    public void initData() {
        ApiWrapper.getInstance().getFee(new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: type inference failed for: r0v2, types: [android.content.Context, com.wx.assistants.activity.MemberCenterPassiveCardActivity] */
            public void onFailure(FailureModel failureModel) {
                if (MemberCenterPassiveCardActivity.this.refreshLayout != null) {
                    MemberCenterPassiveCardActivity.this.refreshLayout.finishRefresh(false);
                }
                ToastUtils.showToast(MemberCenterPassiveCardActivity.this, failureModel.getErrorMessage());
            }

            /* JADX WARNING: type inference failed for: r0v4, types: [android.content.Context, com.wx.assistants.activity.MemberCenterPassiveCardActivity] */
            public void onFinish(ConmdBean conmdBean) {
                int i;
                try {
                    if (MemberCenterPassiveCardActivity.this.refreshLayout != null) {
                        MemberCenterPassiveCardActivity.this.refreshLayout.finishRefresh();
                    }
                    if (conmdBean == null) {
                        return;
                    }
                    if (conmdBean.getCode() == 0) {
                        MemberCenterPassiveCardActivity.this.memberlist.clear();
                        Gson gson = new Gson();
                        MemberCenterPassiveCardActivity.this.memberlist.addAll((List) gson.fromJson(gson.toJson(conmdBean.getData()), new TypeToken<List<MemberPriceAdapterBean>>() {
                        }.getType()));
                        if (MemberCenterPassiveCardActivity.this.memberlist != null && MemberCenterPassiveCardActivity.this.memberlist.size() > 0) {
                            int i2 = 0;
                            while (true) {
                                i = i2;
                                if (i >= MemberCenterPassiveCardActivity.this.memberlist.size()) {
                                    break;
                                }
                                String isCumulative = ((MemberPriceAdapterBean) MemberCenterPassiveCardActivity.this.memberlist.get(i)).getIsCumulative();
                                if (isCumulative == null || "".equals(isCumulative) || (!"1.0".equals(isCumulative) && !"1".equals(isCumulative))) {
                                    i2 = i + 1;
                                }
                            }
                            int unused = MemberCenterPassiveCardActivity.this.position = i;
                            MemberCenterPassiveCardActivity memberCenterPassiveCardActivity = MemberCenterPassiveCardActivity.this;
                            String unused2 = memberCenterPassiveCardActivity.memberId = ((MemberPriceAdapterBean) MemberCenterPassiveCardActivity.this.memberlist.get(MemberCenterPassiveCardActivity.this.position)).getId() + "";
                            String unused3 = MemberCenterPassiveCardActivity.this.wxMoney = ((MemberPriceAdapterBean) MemberCenterPassiveCardActivity.this.memberlist.get(MemberCenterPassiveCardActivity.this.position)).getWechatAmount().toString();
                            String unused4 = MemberCenterPassiveCardActivity.this.alipayMoney = ((MemberPriceAdapterBean) MemberCenterPassiveCardActivity.this.memberlist.get(MemberCenterPassiveCardActivity.this.position)).getAliAmount().toString();
                            String unused5 = MemberCenterPassiveCardActivity.this.wxMoney = MoneyUtils.removeDecimalPoints(MemberCenterPassiveCardActivity.this.wxMoney);
                            MemberCenterPassiveCardActivity.this.setAmountPrice.setText(MemberCenterPassiveCardActivity.this.wxMoney);
                            PayBean unused6 = MemberCenterPassiveCardActivity.this.payBean = new PayBean();
                            if (!(MemberCenterPassiveCardActivity.this.wxMoney == null || MemberCenterPassiveCardActivity.this.wxMoney.length() == 0)) {
                                MemberCenterPassiveCardActivity.this.payBean.setWxPrice(MemberCenterPassiveCardActivity.this.wxMoney);
                            }
                            if (!(MemberCenterPassiveCardActivity.this.alipayMoney == null || MemberCenterPassiveCardActivity.this.alipayMoney.length() == 0)) {
                                MemberCenterPassiveCardActivity.this.payBean.setAliPrice(MemberCenterPassiveCardActivity.this.alipayMoney);
                            }
                            MemberCenterPassiveCardActivity.this.payBean.setPhoneNumber(MemberCenterPassiveCardActivity.this.phonename);
                            MemberCenterPassiveCardActivity.this.memberPriceRecylerviewAdapter.setThisPosotion(MemberCenterPassiveCardActivity.this.position);
                            MemberCenterPassiveCardActivity.this.memberPriceRecylerviewAdapter.notifyDataSetChanged();
                            return;
                        }
                        return;
                    }
                    ToastUtils.showToast(MemberCenterPassiveCardActivity.this, conmdBean.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void initViewListener() {
        setOnUserInfoListener(this);
        this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                MemberCenterPassiveCardActivity.this.initData();
                MemberCenterPassiveCardActivity.this.getUserInfoData();
            }
        });
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.MemberCenterPassiveCardActivity] */
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

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.MemberCenterPassiveCardActivity] */
    @OnClick({2131297049, 2131297356, 2131297355, 2131297358, 2131297357, 2131296814, 2131297414})
    public void onViewClicked(View view) {
        int id2 = view.getId();
        if (id2 == 2131296814) {
            startActivity(new Intent(this, PaymentProtocolActivity.class));
        } else if (id2 == 2131297049) {
            back();
        } else if (id2 == 2131297348) {
        } else {
            if (id2 != 2131297414) {
                switch (id2) {
                    case 2131297355:
                        this.setWxRadioButton.setChecked(true);
                        this.setZfbRadioButton.setChecked(false);
                        this.payBean = new PayBean();
                        if (!(this.wxMoney == null || this.wxMoney.length() == 0)) {
                            this.payBean.setWxPrice(this.wxMoney);
                            this.payBean.setPhoneNumber(this.phonename);
                        }
                        this.wxMoney = MoneyUtils.removeDecimalPoints(this.wxMoney);
                        this.setAmountPrice.setText(this.wxMoney);
                        return;
                    case 2131297356:
                        this.setWxRadioButton.setChecked(true);
                        this.setZfbRadioButton.setChecked(false);
                        this.payBean = new PayBean();
                        if (!(this.wxMoney == null || this.wxMoney.length() == 0)) {
                            this.payBean.setWxPrice(this.wxMoney);
                            this.payBean.setPhoneNumber(this.phonename);
                        }
                        this.wxMoney = MoneyUtils.removeDecimalPoints(this.wxMoney);
                        this.setAmountPrice.setText(this.wxMoney);
                        return;
                    case 2131297357:
                        this.setWxRadioButton.setChecked(false);
                        this.setZfbRadioButton.setChecked(true);
                        lighton();
                        this.payBean = new PayBean();
                        if (!(this.alipayMoney == null || this.alipayMoney.length() == 0)) {
                            this.payBean.setAliPrice(this.alipayMoney);
                            this.payBean.setPhoneNumber(this.phonename);
                        }
                        this.alipayMoney = MoneyUtils.removeDecimalPoints(this.alipayMoney);
                        this.setAmountPrice.setText(this.alipayMoney);
                        return;
                    case 2131297358:
                        this.setWxRadioButton.setChecked(false);
                        this.setZfbRadioButton.setChecked(true);
                        this.payBean = new PayBean();
                        if (!(this.alipayMoney == null || this.alipayMoney.length() == 0)) {
                            this.payBean.setAliPrice(this.alipayMoney);
                            this.payBean.setPhoneNumber(this.phonename);
                        }
                        this.alipayMoney = MoneyUtils.removeDecimalPoints(this.alipayMoney);
                        this.setAmountPrice.setText(this.alipayMoney);
                        return;
                    default:
                        return;
                }
            } else {
                String str = (String) SPUtils.get(this, "ws_baby_phone", "");
                if (!"".equals(str)) {
                    if (this.payBean == null) {
                        this.payBean = new PayBean();
                    }
                    this.payBean.setPhoneNumber(str);
                    if (this.setWxRadioButton.isChecked()) {
                        WXPay();
                    }
                    if (this.setZfbRadioButton.isChecked()) {
                        ZFBPay();
                        return;
                    }
                    return;
                }
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("isNeedBack", true);
                startActivity(intent);
            }
        }
    }

    public void setUserInfo(UserInfoBean.UserBean userBean) {
        if (userBean != null) {
            try {
                String userId = userBean.getUserId();
                if (userId != null && !"".equals(userId)) {
                    SPUtils.put(MyApplication.getConText(), "ws_baby_user_id", userId);
                }
            } catch (Exception e) {
            }
        }
    }
}
