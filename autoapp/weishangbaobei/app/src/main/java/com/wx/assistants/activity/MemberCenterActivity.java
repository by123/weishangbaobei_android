package com.wx.assistants.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.google.gson.Gson;
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
import com.wx.assistants.bean.MemberPriceAdapterBean;
import com.wx.assistants.bean.PayBean;
import com.wx.assistants.bean.UserInfoBean;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.server.request.PayRequest;
import com.wx.assistants.server.request.WxPayRequest;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.MoneyUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
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
        /* JADX WARNING: type inference failed for: r3v5, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
        /* JADX WARNING: type inference failed for: r3v6, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
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

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getUser();
    }

    public void initViewListener() {
        this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                MemberCenterActivity.this.initData();
                MemberCenterActivity.this.getUser();
            }
        });
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity, com.wx.assistants.adapter.MemberPriceRecylerviewAdapter$OnClick] */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0062, code lost:
        r0 = com.wx.assistants.application.MyApplication.bannerPayBean;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initView() {
        /*
            r5 = this;
            android.widget.TextView r0 = r5.navTitle
            java.lang.String r1 = "会员中心"
            r0.setText(r1)
            com.wx.assistants.adapter.MemberPriceRecylerviewAdapter r0 = new com.wx.assistants.adapter.MemberPriceRecylerviewAdapter
            java.util.List<com.wx.assistants.bean.MemberPriceAdapterBean> r1 = r5.memberlist
            r0.<init>(r1, r5)
            r5.memberPriceRecylerviewAdapter = r0
            android.support.v7.widget.RecyclerView r0 = r5.rv
            com.wx.assistants.adapter.MemberPriceRecylerviewAdapter r1 = r5.memberPriceRecylerviewAdapter
            r0.setAdapter(r1)
            android.support.v7.widget.RecyclerView r0 = r5.rv
            com.wx.assistants.view.RecyclerViewDivider r1 = new com.wx.assistants.view.RecyclerViewDivider
            r2 = 2131099799(0x7f060097, float:1.7811961E38)
            int r2 = android.support.v4.content.ContextCompat.getColor(r5, r2)
            r3 = 0
            r4 = 1
            r1.<init>(r5, r4, r3, r2)
            r0.addItemDecoration(r1)
            android.support.v7.widget.GridLayoutManager r0 = new android.support.v7.widget.GridLayoutManager
            r1 = 3
            r0.<init>(r5, r1)
            com.wx.assistants.view.GridItemDecoration$Builder r1 = new com.wx.assistants.view.GridItemDecoration$Builder
            r1.<init>(r5)
            r2 = 2131165672(0x7f0701e8, float:1.7945568E38)
            com.wx.assistants.view.GridItemDecoration$Builder r1 = r1.setHorizontalSpan((int) r2)
            com.wx.assistants.view.GridItemDecoration$Builder r1 = r1.setVerticalSpan((int) r2)
            r2 = 2131100063(0x7f06019f, float:1.7812497E38)
            com.wx.assistants.view.GridItemDecoration$Builder r1 = r1.setColorResource(r2)
            com.wx.assistants.view.GridItemDecoration$Builder r1 = r1.setShowLastLine(r3)
            com.wx.assistants.view.GridItemDecoration r1 = r1.build()
            android.support.v7.widget.RecyclerView r2 = r5.rv
            r2.addItemDecoration(r1)
            android.support.v7.widget.RecyclerView r1 = r5.rv
            r1.setLayoutManager(r0)
            com.wx.assistants.adapter.MemberPriceRecylerviewAdapter r0 = r5.memberPriceRecylerviewAdapter
            r0.setOnClick(r5)
            com.wx.assistants.bean.HomeBannerBean r0 = com.wx.assistants.application.MyApplication.bannerPayBean
            if (r0 == 0) goto L_0x00a3
            com.wx.assistants.bean.HomeBannerBean r0 = com.wx.assistants.application.MyApplication.bannerPayBean
            java.lang.String r1 = r0.getPicUrl()
            if (r1 == 0) goto L_0x00a3
            java.lang.String r2 = ""
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L_0x00a3
            android.widget.LinearLayout r2 = r5.singleAdvertLayout
            r2.setVisibility(r3)
            android.content.Context r2 = com.wx.assistants.application.MyApplication.getConText()
            com.bumptech.glide.RequestManager r2 = com.bumptech.glide.Glide.with(r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "http://image.abcvabc.com/"
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            com.bumptech.glide.DrawableTypeRequest r1 = r2.load(r1)
            android.widget.ImageView r2 = r5.singleAdvertImg
            r1.into(r2)
            android.widget.LinearLayout r1 = r5.singleAdvertLayout
            com.wx.assistants.activity.MemberCenterActivity$2 r2 = new com.wx.assistants.activity.MemberCenterActivity$2
            r2.<init>(r0)
            r1.setOnClickListener(r2)
        L_0x00a3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.MemberCenterActivity.initView():void");
    }

    public void initData() {
        ApiWrapper.getInstance().getMemberRatingList(new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x016d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onFinish(com.wx.assistants.bean.ConmdBean r4) {
                /*
                    r3 = this;
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout     // Catch:{ Exception -> 0x01ef }
                    if (r0 == 0) goto L_0x000d
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout     // Catch:{ Exception -> 0x01ef }
                    r0.finishRefresh()     // Catch:{ Exception -> 0x01ef }
                L_0x000d:
                    if (r4 == 0) goto L_0x01f3
                    int r0 = r4.getCode()     // Catch:{ Exception -> 0x01ef }
                    if (r0 != 0) goto L_0x01e5
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.util.List r0 = r0.memberlist     // Catch:{ Exception -> 0x01ef }
                    r0.clear()     // Catch:{ Exception -> 0x01ef }
                    com.google.gson.Gson r0 = new com.google.gson.Gson     // Catch:{ Exception -> 0x01ef }
                    r0.<init>()     // Catch:{ Exception -> 0x01ef }
                    java.lang.Object r4 = r4.getData()     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r4 = r0.toJson(r4)     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity$3$1 r1 = new com.wx.assistants.activity.MemberCenterActivity$3$1     // Catch:{ Exception -> 0x01ef }
                    r1.<init>()     // Catch:{ Exception -> 0x01ef }
                    java.lang.reflect.Type r1 = r1.getType()     // Catch:{ Exception -> 0x01ef }
                    java.lang.Object r4 = r0.fromJson(r4, r1)     // Catch:{ Exception -> 0x01ef }
                    java.util.List r4 = (java.util.List) r4     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.util.List r0 = r0.memberlist     // Catch:{ Exception -> 0x01ef }
                    r0.addAll(r4)     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.util.List r4 = r4.memberlist     // Catch:{ Exception -> 0x01ef }
                    if (r4 == 0) goto L_0x01f3
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.util.List r4 = r4.memberlist     // Catch:{ Exception -> 0x01ef }
                    int r4 = r4.size()     // Catch:{ Exception -> 0x01ef }
                    if (r4 <= 0) goto L_0x01f3
                    r4 = 0
                L_0x0058:
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.util.List r0 = r0.memberlist     // Catch:{ Exception -> 0x01ef }
                    int r0 = r0.size()     // Catch:{ Exception -> 0x01ef }
                    if (r4 >= r0) goto L_0x0097
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.util.List r0 = r0.memberlist     // Catch:{ Exception -> 0x01ef }
                    java.lang.Object r0 = r0.get(r4)     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.bean.MemberPriceAdapterBean r0 = (com.wx.assistants.bean.MemberPriceAdapterBean) r0     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r0 = r0.getIsCumulative()     // Catch:{ Exception -> 0x01ef }
                    if (r0 == 0) goto L_0x0094
                    java.lang.String r1 = ""
                    boolean r1 = r1.equals(r0)     // Catch:{ Exception -> 0x01ef }
                    if (r1 != 0) goto L_0x0094
                    java.lang.String r1 = "1.0"
                    boolean r1 = r1.equals(r0)     // Catch:{ Exception -> 0x01ef }
                    if (r1 != 0) goto L_0x008e
                    java.lang.String r1 = "1"
                    boolean r0 = r1.equals(r0)     // Catch:{ Exception -> 0x01ef }
                    if (r0 == 0) goto L_0x0094
                L_0x008e:
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    int unused = r0.position = r4     // Catch:{ Exception -> 0x01ef }
                    goto L_0x0097
                L_0x0094:
                    int r4 = r4 + 1
                    goto L_0x0058
                L_0x0097:
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01ef }
                    r0.<init>()     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r1 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.util.List r1 = r1.memberlist     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r2 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    int r2 = r2.position     // Catch:{ Exception -> 0x01ef }
                    java.lang.Object r1 = r1.get(r2)     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.bean.MemberPriceAdapterBean r1 = (com.wx.assistants.bean.MemberPriceAdapterBean) r1     // Catch:{ Exception -> 0x01ef }
                    int r1 = r1.getId()     // Catch:{ Exception -> 0x01ef }
                    r0.append(r1)     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r1 = ""
                    r0.append(r1)     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x01ef }
                    java.lang.String unused = r4.memberId = r0     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.util.List r0 = r0.memberlist     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r1 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    int r1 = r1.position     // Catch:{ Exception -> 0x01ef }
                    java.lang.Object r0 = r0.get(r1)     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.bean.MemberPriceAdapterBean r0 = (com.wx.assistants.bean.MemberPriceAdapterBean) r0     // Catch:{ Exception -> 0x01ef }
                    java.math.BigDecimal r0 = r0.getWechatAmount()     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x01ef }
                    java.lang.String unused = r4.wxMoney = r0     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.util.List r0 = r0.memberlist     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r1 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    int r1 = r1.position     // Catch:{ Exception -> 0x01ef }
                    java.lang.Object r0 = r0.get(r1)     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.bean.MemberPriceAdapterBean r0 = (com.wx.assistants.bean.MemberPriceAdapterBean) r0     // Catch:{ Exception -> 0x01ef }
                    java.math.BigDecimal r0 = r0.getAliAmount()     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x01ef }
                    java.lang.String unused = r4.alipayMoney = r0     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r0 = r0.wxMoney     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r0 = com.wx.assistants.utils.MoneyUtils.removeDecimalPoints(r0)     // Catch:{ Exception -> 0x01ef }
                    java.lang.String unused = r4.wxMoney = r0     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.util.List r0 = r0.memberlist     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r1 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    int r1 = r1.position     // Catch:{ Exception -> 0x01ef }
                    java.lang.Object r0 = r0.get(r1)     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.bean.MemberPriceAdapterBean r0 = (com.wx.assistants.bean.MemberPriceAdapterBean) r0     // Catch:{ Exception -> 0x01ef }
                    java.math.BigDecimal r0 = r0.getAmount()     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x01ef }
                    java.lang.String unused = r4.originPrice = r0     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    android.widget.TextView r4 = r4.setAmountPrice     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r0 = r0.wxMoney     // Catch:{ Exception -> 0x01ef }
                    r4.setText(r0)     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x016d }
                    java.lang.String r4 = r4.originPrice     // Catch:{ Exception -> 0x016d }
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x016d }
                    java.lang.String r0 = r0.alipayMoney     // Catch:{ Exception -> 0x016d }
                    java.lang.String r4 = com.wx.assistants.utils.MoneyUtils.differenceComputation(r4, r0)     // Catch:{ Exception -> 0x016d }
                    java.lang.String r4 = com.wx.assistants.utils.MoneyUtils.removeDecimalPoints(r4)     // Catch:{ Exception -> 0x016d }
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x016d }
                    android.widget.TextView r0 = r0.favourable     // Catch:{ Exception -> 0x016d }
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x016d }
                    r1.<init>()     // Catch:{ Exception -> 0x016d }
                    java.lang.String r2 = "(优惠了"
                    r1.append(r2)     // Catch:{ Exception -> 0x016d }
                    r1.append(r4)     // Catch:{ Exception -> 0x016d }
                    java.lang.String r4 = "元)"
                    r1.append(r4)     // Catch:{ Exception -> 0x016d }
                    java.lang.String r4 = r1.toString()     // Catch:{ Exception -> 0x016d }
                    r0.setText(r4)     // Catch:{ Exception -> 0x016d }
                L_0x016d:
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.bean.PayBean r0 = new com.wx.assistants.bean.PayBean     // Catch:{ Exception -> 0x01ef }
                    r0.<init>()     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.bean.PayBean unused = r4.payBean = r0     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r4 = r4.wxMoney     // Catch:{ Exception -> 0x01ef }
                    if (r4 == 0) goto L_0x019a
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r4 = r4.wxMoney     // Catch:{ Exception -> 0x01ef }
                    int r4 = r4.length()     // Catch:{ Exception -> 0x01ef }
                    if (r4 == 0) goto L_0x019a
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.bean.PayBean r4 = r4.payBean     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r0 = r0.wxMoney     // Catch:{ Exception -> 0x01ef }
                    r4.setWxPrice(r0)     // Catch:{ Exception -> 0x01ef }
                L_0x019a:
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r4 = r4.alipayMoney     // Catch:{ Exception -> 0x01ef }
                    if (r4 == 0) goto L_0x01bd
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r4 = r4.alipayMoney     // Catch:{ Exception -> 0x01ef }
                    int r4 = r4.length()     // Catch:{ Exception -> 0x01ef }
                    if (r4 == 0) goto L_0x01bd
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.bean.PayBean r4 = r4.payBean     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r0 = r0.alipayMoney     // Catch:{ Exception -> 0x01ef }
                    r4.setAliPrice(r0)     // Catch:{ Exception -> 0x01ef }
                L_0x01bd:
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.bean.PayBean r4 = r4.payBean     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r0 = r0.phonename     // Catch:{ Exception -> 0x01ef }
                    r4.setPhoneNumber(r0)     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.adapter.MemberPriceRecylerviewAdapter r4 = r4.memberPriceRecylerviewAdapter     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    int r0 = r0.position     // Catch:{ Exception -> 0x01ef }
                    r4.setThisPosotion(r0)     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.adapter.MemberPriceRecylerviewAdapter r4 = r4.memberPriceRecylerviewAdapter     // Catch:{ Exception -> 0x01ef }
                    r4.notifyDataSetChanged()     // Catch:{ Exception -> 0x01ef }
                    goto L_0x01f3
                L_0x01e5:
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x01ef }
                    java.lang.String r4 = r4.getMessage()     // Catch:{ Exception -> 0x01ef }
                    com.wx.assistants.utils.ToastUtils.showToast(r0, r4)     // Catch:{ Exception -> 0x01ef }
                    goto L_0x01f3
                L_0x01ef:
                    r4 = move-exception
                    r4.printStackTrace()
                L_0x01f3:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.MemberCenterActivity.AnonymousClass3.onFinish(com.wx.assistants.bean.ConmdBean):void");
            }

            /* JADX WARNING: type inference failed for: r0v2, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
            public void onFailure(FailureModel failureModel) {
                if (MemberCenterActivity.this.refreshLayout != null) {
                    MemberCenterActivity.this.refreshLayout.finishRefresh(false);
                }
                ToastUtils.showToast(MemberCenterActivity.this, failureModel.getErrorMessage());
            }
        });
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
        } catch (Exception unused) {
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
            } catch (Exception unused) {
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

    public void getUser() {
        ApiWrapper.getInstance().getUser(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFailure(FailureModel failureModel) {
            }

            /* JADX WARNING: Can't wrap try/catch for region: R(6:12|13|14|15|16|24) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0077 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onFinish(com.wx.assistants.bean.ConmdBean r4) {
                /*
                    r3 = this;
                    if (r4 == 0) goto L_0x008d
                    r0 = 0
                    int r1 = r4.getCode()     // Catch:{ Exception -> 0x007d }
                    r2 = 1
                    if (r1 != r2) goto L_0x005b
                    java.lang.String r1 = r4.getMessage()     // Catch:{ Exception -> 0x007d }
                    if (r1 == 0) goto L_0x005b
                    com.wx.assistants.activity.MemberCenterActivity r1 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x007d }
                    boolean unused = r1.isVip = r0     // Catch:{ Exception -> 0x007d }
                    com.wx.assistants.activity.MemberCenterActivity r1 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x007d }
                    java.lang.String r2 = ""
                    java.lang.String unused = r1.memberType = r2     // Catch:{ Exception -> 0x007d }
                    java.lang.String r4 = r4.getMessage()     // Catch:{ Exception -> 0x007d }
                    java.lang.String r1 = "请重新登录"
                    boolean r4 = r4.contains(r1)     // Catch:{ Exception -> 0x007d }
                    if (r4 == 0) goto L_0x008d
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x007d }
                    android.widget.TextView r4 = r4.memberLever     // Catch:{ Exception -> 0x007d }
                    r1 = 8
                    r4.setVisibility(r1)     // Catch:{ Exception -> 0x007d }
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x007d }
                    android.widget.ImageView r4 = r4.diamondImage     // Catch:{ Exception -> 0x007d }
                    r4.setVisibility(r1)     // Catch:{ Exception -> 0x007d }
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x007d }
                    android.widget.TextView r4 = r4.setUserName     // Catch:{ Exception -> 0x007d }
                    java.lang.String r1 = "未知用户"
                    r4.setText(r1)     // Catch:{ Exception -> 0x007d }
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x007d }
                    android.widget.TextView r4 = r4.setExpireTime     // Catch:{ Exception -> 0x007d }
                    java.lang.String r1 = "尚未登录，请登录"
                    r4.setText(r1)     // Catch:{ Exception -> 0x007d }
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x007d }
                    android.widget.Button r4 = r4.operationBtn     // Catch:{ Exception -> 0x007d }
                    r4.setVisibility(r0)     // Catch:{ Exception -> 0x007d }
                    com.wx.assistants.activity.MemberCenterActivity r4 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x007d }
                    android.widget.Button r4 = r4.operationBtn     // Catch:{ Exception -> 0x007d }
                    java.lang.String r1 = "登录"
                    r4.setText(r1)     // Catch:{ Exception -> 0x007d }
                    goto L_0x008d
                L_0x005b:
                    int r1 = r4.getCode()     // Catch:{ Exception -> 0x007d }
                    if (r1 != 0) goto L_0x008d
                    com.google.gson.Gson r1 = new com.google.gson.Gson     // Catch:{ Exception -> 0x007d }
                    r1.<init>()     // Catch:{ Exception -> 0x007d }
                    java.lang.Object r4 = r4.getData()     // Catch:{ Exception -> 0x007d }
                    java.lang.String r4 = r1.toJson(r4)     // Catch:{ Exception -> 0x007d }
                    android.content.Context r1 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x0077 }
                    java.lang.String r2 = "ws_baby_user_info"
                    com.wx.assistants.utils.SPUtils.put(r1, r2, r4)     // Catch:{ Exception -> 0x0077 }
                L_0x0077:
                    com.wx.assistants.activity.MemberCenterActivity r1 = com.wx.assistants.activity.MemberCenterActivity.this     // Catch:{ Exception -> 0x007d }
                    r1.setUserInfo(r4)     // Catch:{ Exception -> 0x007d }
                    goto L_0x008d
                L_0x007d:
                    r4 = move-exception
                    com.wx.assistants.activity.MemberCenterActivity r1 = com.wx.assistants.activity.MemberCenterActivity.this
                    boolean unused = r1.isVip = r0
                    com.wx.assistants.activity.MemberCenterActivity r0 = com.wx.assistants.activity.MemberCenterActivity.this
                    java.lang.String r1 = ""
                    java.lang.String unused = r0.memberType = r1
                    r4.printStackTrace()
                L_0x008d:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.MemberCenterActivity.AnonymousClass4.onFinish(com.wx.assistants.bean.ConmdBean):void");
            }
        });
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
                            } catch (Exception unused) {
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

    private void lighton() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 1.0f;
        getWindow().setAttributes(attributes);
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
            char[] cArr2 = new char[(r1 * 2)];
            int i = 0;
            for (byte b : instance.digest()) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b & ap.m];
            }
            return new String(cArr2);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
    /* access modifiers changed from: private */
    public void ZFBPay() {
        String macAddress = MacAddressUtils.getMacAddress(MyApplication.mContext);
        if (this.payBean == null || this.payBean.getPhoneNumber() == null || this.payBean.getAliPrice() == null) {
            ToastUtils.showToast(this, "请先选择会员");
            return;
        }
        ApiWrapper.getInstance().alipay(new PayRequest(macAddress, this.payBean.getPhoneNumber().toString().trim(), MoneyUtils.changeY2F(Double.parseDouble(this.payBean.getAliPrice().toString().trim())), this.memberId, (String) null), new ApiWrapper.CallbackListener<ConmdBean<String>>() {
            /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
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

            /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
            public void onFailure(FailureModel failureModel) {
                ToastUtils.showToast(MemberCenterActivity.this, failureModel.getErrorMessage());
            }
        });
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
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
            /* JADX WARNING: type inference failed for: r0v4, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
            /* JADX WARNING: type inference failed for: r1v4, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
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

            /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.MemberCenterActivity] */
            public void onFailure(FailureModel failureModel) {
                MemberCenterActivity.this.dismissLoadingDialog();
                ToastUtils.showToast(MemberCenterActivity.this, failureModel.getErrorMessage());
            }
        });
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
            } catch (Exception unused) {
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
}
