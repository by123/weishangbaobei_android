package com.wx.assistants.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
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
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.adapter.MemberPriceRecylerviewAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.HomeBannerBean;
import com.wx.assistants.bean.MemberPriceAdapterBean;
import com.wx.assistants.bean.PayBean;
import com.wx.assistants.bean.UserInfoBean;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.server.request.PayRequest;
import com.wx.assistants.server.request.WxPayRequest;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.MoneyUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.GridItemDecoration;
import com.wx.assistants.view.RecyclerViewDivider;
import com.wx.assistants.webview.WebViewActivity;
import com.wx.assistants.zfbapi.PayResult;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class MemberCenterActivity extends BaseActivity implements MemberPriceRecylerviewAdapter.OnClick {
    private static final int SDK_PAY_FLAG = 1;
    /* access modifiers changed from: private */
    public String alipayMoney = "";
    @BindView(2131296341)
    ImageView anchor1;
    @BindView(2131296348)
    ImageView anchor2;
    @BindView(2131296349)
    ImageView anchor3;
    private String currentSelectMemberType = "";
    @BindView(2131296597)
    ImageView diamondImage;
    private long endTime;
    /* access modifiers changed from: private */
    public long endTime1;
    @BindView(2131296659)
    TextView favourable;
    /* access modifiers changed from: private */
    public boolean isVip = false;
    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    public Handler mHandler = new Handler() {
        /* JADX WARNING: type inference failed for: r0v5, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
        /* JADX WARNING: type inference failed for: r0v6, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
        public void handleMessage(Message message) {
            if (message.what == 1) {
                PayResult payResult = new PayResult((Map) message.obj);
                payResult.getResult();
                if (TextUtils.equals(payResult.getResultStatus(), "9000")) {
                    ToastUtils.showToast(MemberCenterActivity.this, "支付成功");
                    MemberCenterActivity.this.finish();
                    return;
                }
                ToastUtils.showToast(MemberCenterActivity.this, "支付失败");
            }
        }
    };
    /* access modifiers changed from: private */
    public String memberId;
    @BindView(2131297004)
    TextView memberLever;
    /* access modifiers changed from: private */
    public MemberPriceRecylerviewAdapter memberPriceRecylerviewAdapter;
    /* access modifiers changed from: private */
    public String memberType = "";
    /* access modifiers changed from: private */
    public List<MemberPriceAdapterBean> memberlist = new ArrayList();
    private String money = "";
    /* access modifiers changed from: private */
    public IWXAPI msgApi;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297091)
    Button operationBtn;
    /* access modifiers changed from: private */
    public String originPrice = "";
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
    @BindView(2131297347)
    LinearLayout setAgreement;
    @BindView(2131297348)
    TextView setAmountPrice;
    @BindView(2131297349)
    TextView setExpireTime;
    @BindView(2131297352)
    TextView setPayment;
    @BindView(2131297353)
    ImageView setUserHead;
    @BindView(2131297354)
    TextView setUserName;
    @BindView(2131297355)
    RelativeLayout setWxPayment;
    @BindView(2131297356)
    RadioButton setWxRadioButton;
    @BindView(2131297357)
    RelativeLayout setZfbPayment;
    @BindView(2131297358)
    CheckBox setZfbRadioButton;
    @BindView(2131297383)
    ImageView singleAdvertImg;
    @BindView(2131297384)
    LinearLayout singleAdvertLayout;
    private long startTime;
    /* access modifiers changed from: private */
    public long startTime1;
    /* access modifiers changed from: private */
    public String wxMoney = "";

    static {
        StubApp.interface11(8979);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
    /* access modifiers changed from: private */
    public void WXPay() {
        showLoadingDialog("正在操作");
        String macAddress = MacAddressUtils.getMacAddress(MyApplication.mContext);
        if (this.payBean == null || this.payBean.getPhoneNumber() == null || this.payBean.getWxPrice() == null) {
            dismissLoadingDialog();
            ToastUtils.showToast(this, "请先选择会员");
            return;
        }
        ApiWrapper.getInstance().wechatPay(new WxPayRequest(macAddress, this.payBean.getPhoneNumber().toString().trim(), MoneyUtils.changeY2F(Double.parseDouble(this.payBean.getWxPrice().toString().trim())), this.memberId, (String) null), new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
            public void onFailure(FailureModel failureModel) {
                MemberCenterActivity.this.dismissLoadingDialog();
                ToastUtils.showToast(MemberCenterActivity.this, failureModel.getErrorMessage());
            }

            /* JADX WARNING: type inference failed for: r0v5, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
            /* JADX WARNING: type inference failed for: r2v3, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
            public void onFinish(ConmdBean conmdBean) {
                MemberCenterActivity.this.dismissLoadingDialog();
                if (conmdBean != null) {
                    try {
                        PayBean payBean = (PayBean) new Gson().fromJson(conmdBean.getData().toString(), PayBean.class);
                        if (conmdBean.getCode() == 1000) {
                            LogUtils.log("WS_BABY_000000");
                            long unused = MemberCenterActivity.this.startTime1 = System.currentTimeMillis();
                            IWXAPI unused2 = MemberCenterActivity.this.msgApi = WXAPIFactory.createWXAPI(MemberCenterActivity.this, MyApplication.WX_APP_ID, true);
                            MemberCenterActivity.this.msgApi.registerApp(MyApplication.WX_APP_ID);
                            long unused3 = MemberCenterActivity.this.endTime1 = System.currentTimeMillis();
                            LogUtils.log("WS_BABY_SIGN_TIME1 ## " + (MemberCenterActivity.this.endTime1 - MemberCenterActivity.this.startTime1));
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
                            payReq.sign = MemberCenterActivity.this.genPackageSign(treeMap, "IlIlIlIlIlweishang2018iLiLiLiLiL");
                            MemberCenterActivity.this.msgApi.registerApp(MyApplication.WX_APP_ID);
                            MemberCenterActivity.this.msgApi.sendReq(payReq);
                            Log.e(BaseActivity.TAG, "checkArgs===============" + payReq.checkArgs());
                            return;
                        }
                        ToastUtils.showToast(MemberCenterActivity.this, conmdBean.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
    /* access modifiers changed from: private */
    public void ZFBPay() {
        String macAddress = MacAddressUtils.getMacAddress(MyApplication.mContext);
        if (this.payBean == null || this.payBean.getPhoneNumber() == null || this.payBean.getAliPrice() == null) {
            ToastUtils.showToast(this, "请先选择会员");
            return;
        }
        ApiWrapper.getInstance().alipay(new PayRequest(macAddress, this.payBean.getPhoneNumber().toString().trim(), MoneyUtils.changeY2F(Double.parseDouble(this.payBean.getAliPrice().toString().trim())), this.memberId, (String) null), new ApiWrapper.CallbackListener<ConmdBean<String>>() {
            /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
            public void onFailure(FailureModel failureModel) {
                ToastUtils.showToast(MemberCenterActivity.this, failureModel.getErrorMessage());
            }

            /* JADX WARNING: type inference failed for: r0v2, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
            public void onFinish(ConmdBean conmdBean) {
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() == 1000) {
                            final String str = (String) conmdBean.getData();
                            new Thread(new Runnable() {
                                /* JADX WARNING: type inference failed for: r1v1, types: [com.wx.assistants.activity.MemberCenterActivity, android.app.Activity] */
                                public void run() {
                                    Map payV2 = new PayTask(MemberCenterActivity.this).payV2(str, true);
                                    Log.i("msp", payV2.toString());
                                    Message message = new Message();
                                    message.what = 1;
                                    message.obj = payV2;
                                    MemberCenterActivity.this.mHandler.sendMessage(message);
                                }
                            }).start();
                            return;
                        }
                        ToastUtils.showToast(MemberCenterActivity.this, conmdBean.getMessage());
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

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity, com.wx.assistants.adapter.MemberPriceRecylerviewAdapter$OnClick] */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0062, code lost:
        r0 = com.wx.assistants.application.MyApplication.bannerPayBean;
     */
    private void initView() {
        final HomeBannerBean homeBannerBean;
        String picUrl;
        this.navTitle.setText("会员中心");
        this.memberPriceRecylerviewAdapter = new MemberPriceRecylerviewAdapter(this.memberlist, this);
        this.rv.setAdapter(this.memberPriceRecylerviewAdapter);
        this.rv.addItemDecoration(new RecyclerViewDivider(this, 1, 0, ContextCompat.getColor(this, 2131099799)));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        this.rv.addItemDecoration(new GridItemDecoration.Builder(this).setHorizontalSpan(2131165672).setVerticalSpan(2131165672).setColorResource(2131100063).setShowLastLine(false).build());
        this.rv.setLayoutManager(gridLayoutManager);
        this.memberPriceRecylerviewAdapter.setOnClick(this);
        if (MyApplication.bannerPayBean != null && (picUrl = homeBannerBean.getPicUrl()) != null && !"".equals(picUrl)) {
            this.singleAdvertLayout.setVisibility(0);
            RequestManager with = Glide.with(MyApplication.getConText());
            with.load(QBangApis.IMG_PREFIX_URL + picUrl).into(this.singleAdvertImg);
            this.singleAdvertLayout.setOnClickListener(new View.OnClickListener() {
                /* JADX WARNING: type inference failed for: r1v2, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
                public void onClick(View view) {
                    String url = homeBannerBean.getUrl();
                    if (url != null && !"".equals(url)) {
                        WebViewActivity.startActivity(MemberCenterActivity.this, "", url);
                    }
                }
            });
        }
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
        this.currentSelectMemberType = this.memberlist.get(i).getTypeName();
        this.originPrice = this.memberlist.get(i).getAmount().toString();
        this.wxMoney = this.memberlist.get(i).getWechatAmount().toString();
        this.alipayMoney = this.memberlist.get(i).getAliAmount().toString();
        this.wxMoney = MoneyUtils.removeDecimalPoints(this.wxMoney);
        this.alipayMoney = MoneyUtils.removeDecimalPoints(this.alipayMoney);
        if (this.setZfbRadioButton.isChecked()) {
            this.setAmountPrice.setText(this.alipayMoney);
            try {
                String removeDecimalPoints = MoneyUtils.removeDecimalPoints(MoneyUtils.differenceComputation(this.originPrice, this.alipayMoney));
                TextView textView = this.favourable;
                textView.setText("(优惠了" + removeDecimalPoints + "元)");
            } catch (Exception e) {
            }
        } else {
            this.setAmountPrice.setText(this.wxMoney);
            String removeDecimalPoints2 = MoneyUtils.removeDecimalPoints(MoneyUtils.differenceComputation(this.originPrice, this.wxMoney));
            TextView textView2 = this.favourable;
            textView2.setText("(优惠了" + removeDecimalPoints2 + "元)");
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

    public void getUser() {
        ApiWrapper.getInstance().getUser(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFailure(FailureModel failureModel) {
            }

            public void onFinish(ConmdBean conmdBean) {
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() == 1 && conmdBean.getMessage() != null) {
                            boolean unused = MemberCenterActivity.this.isVip = false;
                            String unused2 = MemberCenterActivity.this.memberType = "";
                            if (conmdBean.getMessage().contains("请重新登录")) {
                                MemberCenterActivity.this.memberLever.setVisibility(8);
                                MemberCenterActivity.this.diamondImage.setVisibility(8);
                                MemberCenterActivity.this.setUserName.setText("未知用户");
                                MemberCenterActivity.this.setExpireTime.setText("尚未登录，请登录");
                                MemberCenterActivity.this.operationBtn.setVisibility(0);
                                MemberCenterActivity.this.operationBtn.setText("登录");
                            }
                        } else if (conmdBean.getCode() == 0) {
                            String json = new Gson().toJson(conmdBean.getData());
                            try {
                                SPUtils.put(MyApplication.getConText(), "ws_baby_user_info", json);
                            } catch (Exception e) {
                            }
                            MemberCenterActivity.this.setUserInfo(json);
                        }
                    } catch (Exception e2) {
                        boolean unused3 = MemberCenterActivity.this.isVip = false;
                        String unused4 = MemberCenterActivity.this.memberType = "";
                        e2.printStackTrace();
                    }
                }
            }
        });
    }

    public void initData() {
        ApiWrapper.getInstance().getMemberRatingList(new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: type inference failed for: r0v2, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
            public void onFailure(FailureModel failureModel) {
                if (MemberCenterActivity.this.refreshLayout != null) {
                    MemberCenterActivity.this.refreshLayout.finishRefresh(false);
                }
                ToastUtils.showToast(MemberCenterActivity.this, failureModel.getErrorMessage());
            }

            /* JADX WARNING: type inference failed for: r0v4, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
            public void onFinish(ConmdBean conmdBean) {
                int i;
                try {
                    if (MemberCenterActivity.this.refreshLayout != null) {
                        MemberCenterActivity.this.refreshLayout.finishRefresh();
                    }
                    if (conmdBean == null) {
                        return;
                    }
                    if (conmdBean.getCode() == 0) {
                        MemberCenterActivity.this.memberlist.clear();
                        Gson gson = new Gson();
                        MemberCenterActivity.this.memberlist.addAll((List) gson.fromJson(gson.toJson(conmdBean.getData()), new TypeToken<List<MemberPriceAdapterBean>>() {
                        }.getType()));
                        if (MemberCenterActivity.this.memberlist != null && MemberCenterActivity.this.memberlist.size() > 0) {
                            int i2 = 0;
                            while (true) {
                                i = i2;
                                if (i >= MemberCenterActivity.this.memberlist.size()) {
                                    break;
                                }
                                String isCumulative = ((MemberPriceAdapterBean) MemberCenterActivity.this.memberlist.get(i)).getIsCumulative();
                                if (isCumulative == null || "".equals(isCumulative) || (!"1.0".equals(isCumulative) && !"1".equals(isCumulative))) {
                                    i2 = i + 1;
                                }
                            }
                            int unused = MemberCenterActivity.this.position = i;
                            MemberCenterActivity memberCenterActivity = MemberCenterActivity.this;
                            String unused2 = memberCenterActivity.memberId = ((MemberPriceAdapterBean) MemberCenterActivity.this.memberlist.get(MemberCenterActivity.this.position)).getId() + "";
                            String unused3 = MemberCenterActivity.this.wxMoney = ((MemberPriceAdapterBean) MemberCenterActivity.this.memberlist.get(MemberCenterActivity.this.position)).getWechatAmount().toString();
                            String unused4 = MemberCenterActivity.this.alipayMoney = ((MemberPriceAdapterBean) MemberCenterActivity.this.memberlist.get(MemberCenterActivity.this.position)).getAliAmount().toString();
                            String unused5 = MemberCenterActivity.this.wxMoney = MoneyUtils.removeDecimalPoints(MemberCenterActivity.this.wxMoney);
                            String unused6 = MemberCenterActivity.this.originPrice = ((MemberPriceAdapterBean) MemberCenterActivity.this.memberlist.get(MemberCenterActivity.this.position)).getAmount().toString();
                            MemberCenterActivity.this.setAmountPrice.setText(MemberCenterActivity.this.wxMoney);
                            try {
                                String removeDecimalPoints = MoneyUtils.removeDecimalPoints(MoneyUtils.differenceComputation(MemberCenterActivity.this.originPrice, MemberCenterActivity.this.alipayMoney));
                                TextView textView = MemberCenterActivity.this.favourable;
                                textView.setText("(优惠了" + removeDecimalPoints + "元)");
                            } catch (Exception e) {
                            }
                            PayBean unused7 = MemberCenterActivity.this.payBean = new PayBean();
                            if (!(MemberCenterActivity.this.wxMoney == null || MemberCenterActivity.this.wxMoney.length() == 0)) {
                                MemberCenterActivity.this.payBean.setWxPrice(MemberCenterActivity.this.wxMoney);
                            }
                            if (!(MemberCenterActivity.this.alipayMoney == null || MemberCenterActivity.this.alipayMoney.length() == 0)) {
                                MemberCenterActivity.this.payBean.setAliPrice(MemberCenterActivity.this.alipayMoney);
                            }
                            MemberCenterActivity.this.payBean.setPhoneNumber(MemberCenterActivity.this.phonename);
                            MemberCenterActivity.this.memberPriceRecylerviewAdapter.setThisPosotion(MemberCenterActivity.this.position);
                            MemberCenterActivity.this.memberPriceRecylerviewAdapter.notifyDataSetChanged();
                            return;
                        }
                        return;
                    }
                    ToastUtils.showToast(MemberCenterActivity.this, conmdBean.getMessage());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public void initViewListener() {
        this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                MemberCenterActivity.this.initData();
                MemberCenterActivity.this.getUser();
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

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
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

    /* JADX WARNING: type inference failed for: r7v0, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
    @OnClick({2131297049, 2131297353, 2131297091, 2131297354, 2131297349, 2131297356, 2131297355, 2131297358, 2131297357, 2131297347, 2131297352})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id != 2131297091) {
            switch (id) {
                case 2131297347:
                    startActivity(new Intent(this, PaymentProtocolActivity.class));
                    return;
                case 2131297348:
                case 2131297349:
                    return;
                default:
                    switch (id) {
                        case 2131297352:
                            String str = (String) SPUtils.get(this, "ws_baby_phone", "");
                            if (!"".equals(str)) {
                                if (this.payBean == null) {
                                    this.payBean = new PayBean();
                                }
                                this.payBean.setPhoneNumber(str);
                                if (this.isVip) {
                                    DialogUIUtils.dialogDefault(this, "付款提醒", "您目前已经是" + this.memberType + "了，是否确定购买" + this.currentSelectMemberType + "？", "取消购买", "确定购买", (View.OnClickListener) null, new View.OnClickListener() {
                                        public void onClick(View view) {
                                            if (MemberCenterActivity.this.setWxRadioButton.isChecked()) {
                                                MemberCenterActivity.this.WXPay();
                                            }
                                            if (MemberCenterActivity.this.setZfbRadioButton.isChecked()) {
                                                MemberCenterActivity.this.ZFBPay();
                                            }
                                        }
                                    });
                                    return;
                                }
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
                            return;
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
                            String removeDecimalPoints = MoneyUtils.removeDecimalPoints(MoneyUtils.differenceComputation(this.originPrice, this.wxMoney));
                            TextView textView = this.favourable;
                            textView.setText("(优惠了" + removeDecimalPoints + "元)");
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
                            String removeDecimalPoints2 = MoneyUtils.removeDecimalPoints(MoneyUtils.differenceComputation(this.originPrice, this.wxMoney));
                            TextView textView2 = this.favourable;
                            textView2.setText("(优惠了" + removeDecimalPoints2 + "元)");
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
                            String removeDecimalPoints3 = MoneyUtils.removeDecimalPoints(MoneyUtils.differenceComputation(this.originPrice, this.alipayMoney));
                            TextView textView3 = this.favourable;
                            textView3.setText("(优惠了" + removeDecimalPoints3 + "元)");
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
                            try {
                                String removeDecimalPoints4 = MoneyUtils.removeDecimalPoints(MoneyUtils.differenceComputation(this.originPrice, this.alipayMoney));
                                TextView textView4 = this.favourable;
                                textView4.setText("(优惠了" + removeDecimalPoints4 + "元)");
                                return;
                            } catch (Exception e) {
                                return;
                            }
                        default:
                            return;
                    }
                    return;
            }
        } else if ("登录".equals(this.operationBtn.getText())) {
            Intent intent2 = new Intent(this, LoginActivity.class);
            intent2.putExtra("isNeedBack", true);
            startActivity(intent2);
        } else if ("会员特权".equals(this.operationBtn.getText())) {
            startActivity(new Intent(this, MemberPrivilegeActivity.class));
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
                this.isVip = true;
                String str2 = userBean.getPhone_number() + "";
                userBean.getLevelName() + "";
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
                    this.memberType = "月度会员";
                    this.memberLever.setText("月");
                } else if ("2".equals(level) || SocializeConstants.PROTOCOL_VERSON.equals(level)) {
                    this.memberLever.setText("年");
                    this.memberType = "年度会员";
                } else if ("3".equals(level) || "3.0".equals(level)) {
                    this.memberLever.setText("三");
                    this.memberType = "三年会员";
                } else if ("4".equals(level) || "4.0".equals(level)) {
                    this.memberLever.setText("半");
                    this.memberType = "半年会员";
                } else if ("5".equals(level) || "5.0".equals(level)) {
                    this.memberLever.setText("年");
                    this.memberType = "年度会员";
                } else if ("10".equals(level) || "10.0".equals(level)) {
                    this.memberLever.setText("永");
                    this.memberType = "永久会员";
                } else if ("11".equals(level) || "11.0".equals(level)) {
                    this.memberLever.setText("永");
                    this.memberType = "永久会员";
                } else {
                    this.isVip = false;
                    this.memberType = "";
                    this.memberLever.setText("体");
                }
            } else {
                this.isVip = false;
                this.memberType = "";
                this.memberLever.setVisibility(8);
                this.diamondImage.setVisibility(8);
                this.setUserName.setText(userBean.getPhone_number() + "");
                this.setExpireTime.setText("开通会员，尊享更多功能特权");
                this.operationBtn.setVisibility(0);
                this.operationBtn.setText("会员特权");
            }
        }
    }
}
