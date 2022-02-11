package com.wx.assistants.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stub.StubApp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.proguard.ap;
import com.wx.assistants.adapter.ChangeDeviceAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.DeviceBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.PayBean;
import com.wx.assistants.bean.UpdateMemberEvent;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.server.request.PayRequest;
import com.wx.assistants.server.request.WxPayRequest;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.MoneyUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.zfbapi.PayResult;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.greenrobot.eventbus.EventBus;

public class DeviceChangeActivity extends BaseActivity {
    private static final int SDK_PAY_FLAG = 1;
    private String alipayMoney = "5";
    /* access modifiers changed from: private */
    public ChangeDeviceAdapter changeDeviceAdapter;
    /* access modifiers changed from: private */
    public List<DeviceBean> deviceBeans;
    /* access modifiers changed from: private */
    public ListView deviceListView;
    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    public Handler mHandler = new Handler() {
        /* JADX WARNING: type inference failed for: r0v5, types: [android.content.Context, com.wx.assistants.activity.DeviceChangeActivity] */
        /* JADX WARNING: type inference failed for: r0v6, types: [android.content.Context, com.wx.assistants.activity.DeviceChangeActivity] */
        public void handleMessage(Message message) {
            if (message.what == 1) {
                PayResult payResult = new PayResult((Map) message.obj);
                payResult.getResult();
                if (TextUtils.equals(payResult.getResultStatus(), "9000")) {
                    ToastUtils.showToast(DeviceChangeActivity.this, "设备更换成功");
                    EventBus.getDefault().post(new UpdateMemberEvent(0));
                    DeviceChangeActivity.this.back();
                    return;
                }
                ToastUtils.showToast(DeviceChangeActivity.this, "支付失败");
            }
        }
    };
    /* access modifiers changed from: private */
    public IWXAPI msgApi;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297087)
    Button onLineSuggestServiceBtn;
    private PayBean payBean;
    private String payId = "10001";
    private String phoneName = "";
    /* access modifiers changed from: private */
    public DeviceBean selectBean;
    @BindView(2131297347)
    LinearLayout setAgreement;
    @BindView(2131297348)
    TextView setAmountPrice;
    @BindView(2131297352)
    TextView setPayment;
    @BindView(2131297355)
    RelativeLayout setWxPayment;
    @BindView(2131297356)
    CheckBox setWxRadioButton;
    @BindView(2131297357)
    RelativeLayout setZfbPayment;
    @BindView(2131297358)
    CheckBox setZfbRadioButton;
    private String wxMoney = "5";

    static {
        StubApp.interface11(8611);
    }

    private void WXPay() {
        showLoadingDialog("正在操作");
        ApiWrapper.getInstance().wechatPay(new WxPayRequest(MacAddressUtils.getMacAddress(MyApplication.mContext), this.payBean.getPhoneNumber().toString().trim(), MoneyUtils.changeY2F(Double.parseDouble(this.payBean.getWxPrice().toString().trim())), this.payId, this.selectBean.getUserId()), new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.DeviceChangeActivity] */
            public void onFailure(FailureModel failureModel) {
                DeviceChangeActivity.this.dismissLoadingDialog();
                ToastUtils.showToast(DeviceChangeActivity.this, failureModel.getErrorMessage());
            }

            /* JADX WARNING: type inference failed for: r0v5, types: [android.content.Context, com.wx.assistants.activity.DeviceChangeActivity] */
            /* JADX WARNING: type inference failed for: r2v2, types: [android.content.Context, com.wx.assistants.activity.DeviceChangeActivity] */
            public void onFinish(ConmdBean conmdBean) {
                DeviceChangeActivity.this.dismissLoadingDialog();
                if (conmdBean != null) {
                    try {
                        PayBean payBean = (PayBean) new Gson().fromJson(conmdBean.getData().toString(), PayBean.class);
                        if (conmdBean.getCode() == 1000) {
                            IWXAPI unused = DeviceChangeActivity.this.msgApi = WXAPIFactory.createWXAPI(DeviceChangeActivity.this, MyApplication.WX_APP_ID, true);
                            DeviceChangeActivity.this.msgApi.registerApp(MyApplication.WX_APP_ID);
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
                            payReq.sign = DeviceChangeActivity.this.genPackageSign(treeMap, "IlIlIlIlIlweishang2018iLiLiLiLiL");
                            DeviceChangeActivity.this.msgApi.registerApp(MyApplication.WX_APP_ID);
                            DeviceChangeActivity.this.msgApi.sendReq(payReq);
                            Log.e(BaseActivity.TAG, "checkArgs===============" + payReq.checkArgs());
                            return;
                        }
                        ToastUtils.showToast(DeviceChangeActivity.this, conmdBean.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void ZFBPay() {
        ApiWrapper.getInstance().alipay(new PayRequest(MacAddressUtils.getMacAddress(MyApplication.mContext), this.payBean.getPhoneNumber().toString().trim(), MoneyUtils.changeY2F(Double.parseDouble(this.payBean.getAliPrice().toString().trim())), this.payId, this.selectBean.getUserId()), new ApiWrapper.CallbackListener<ConmdBean<String>>() {
            /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.wx.assistants.activity.DeviceChangeActivity] */
            public void onFailure(FailureModel failureModel) {
                ToastUtils.showToast(DeviceChangeActivity.this, failureModel.getErrorMessage());
            }

            /* JADX WARNING: type inference failed for: r0v2, types: [android.content.Context, com.wx.assistants.activity.DeviceChangeActivity] */
            public void onFinish(ConmdBean conmdBean) {
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() == 1000) {
                            final String str = (String) conmdBean.getData();
                            new Thread(new Runnable() {
                                /* JADX WARNING: type inference failed for: r1v1, types: [com.wx.assistants.activity.DeviceChangeActivity, android.app.Activity] */
                                public void run() {
                                    Map payV2 = new PayTask(DeviceChangeActivity.this).payV2(str, true);
                                    Log.i("msp", payV2.toString());
                                    Message message = new Message();
                                    message.what = 1;
                                    message.obj = payV2;
                                    DeviceChangeActivity.this.mHandler.sendMessage(message);
                                }
                            }).start();
                            return;
                        }
                        ToastUtils.showToast(DeviceChangeActivity.this, conmdBean.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public String genPackageSign(SortedMap<Object, Object> sortedMap, String str) {
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
        return getMessageDigest(stringBuffer.toString().getBytes()).toUpperCase();
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

    private void initView() {
        this.navTitle.setText("更换设备增值服务");
        this.deviceListView = (ListView) findViewById(2131296592);
    }

    private void lighton() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 1.0f;
        getWindow().setAttributes(attributes);
    }

    private static void showAlert(Context context, String str) {
        showAlert(context, str, (DialogInterface.OnDismissListener) null);
    }

    private static void showAlert(Context context, String str, DialogInterface.OnDismissListener onDismissListener) {
        if (Build.VERSION.SDK_INT >= 17) {
            new AlertDialog.Builder(context).setMessage(str).setPositiveButton("", (DialogInterface.OnClickListener) null).setOnDismissListener(onDismissListener).show();
        }
    }

    public void initData() {
        ApiWrapper.getInstance().myUsersByPhone(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFailure(FailureModel failureModel) {
                PrintStream printStream = System.out;
                printStream.println("" + failureModel);
            }

            /* JADX WARNING: type inference failed for: r2v3, types: [android.content.Context, com.wx.assistants.activity.DeviceChangeActivity] */
            public void onFinish(ConmdBean conmdBean) {
                try {
                    PrintStream printStream = System.out;
                    printStream.println("" + conmdBean);
                    List unused = DeviceChangeActivity.this.deviceBeans = new ArrayList();
                    Gson gson = new Gson();
                    List unused2 = DeviceChangeActivity.this.deviceBeans = (List) gson.fromJson(gson.toJson(conmdBean.getData()), new TypeToken<List<DeviceBean>>() {
                    }.getType());
                    DeviceBean unused3 = DeviceChangeActivity.this.selectBean = (DeviceBean) DeviceChangeActivity.this.deviceBeans.get(0);
                    ChangeDeviceAdapter unused4 = DeviceChangeActivity.this.changeDeviceAdapter = new ChangeDeviceAdapter(DeviceChangeActivity.this, DeviceChangeActivity.this.deviceBeans);
                    DeviceChangeActivity.this.changeDeviceAdapter.setOnSelectDeviceListener(new ChangeDeviceAdapter.OnSelectDeviceListener() {
                        public void select(DeviceBean deviceBean) {
                            DeviceBean unused = DeviceChangeActivity.this.selectBean = deviceBean;
                            LogUtils.log("WS_BABY_CHANGE_DEVICE" + DeviceChangeActivity.this.selectBean.getEquipment());
                        }
                    });
                    DeviceChangeActivity.this.deviceListView.setAdapter(DeviceChangeActivity.this.changeDeviceAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.DeviceChangeActivity] */
    @OnClick({2131297049, 2131297087, 2131297356, 2131297355, 2131297358, 2131297357, 2131297347, 2131297352})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297087) {
            startActivity(new Intent(this, SuggestServerActivity.class));
        } else if (id == 2131297347) {
            startActivity(new Intent(this, PaymentProtocolActivity.class));
        } else if (id != 2131297352) {
            switch (id) {
                case 2131297355:
                    this.setWxRadioButton.setChecked(true);
                    this.setZfbRadioButton.setChecked(false);
                    this.payBean = new PayBean();
                    if (!(this.wxMoney == null || this.wxMoney.length() == 0)) {
                        this.payBean.setWxPrice(this.wxMoney);
                        this.payBean.setPhoneNumber(this.phoneName);
                    }
                    this.setAmountPrice.setText(this.wxMoney);
                    return;
                case 2131297356:
                    this.setWxRadioButton.setChecked(true);
                    this.setZfbRadioButton.setChecked(false);
                    this.payBean = new PayBean();
                    if (!(this.wxMoney == null || this.wxMoney.length() == 0)) {
                        this.payBean.setWxPrice(this.wxMoney);
                        this.payBean.setPhoneNumber(this.phoneName);
                    }
                    this.setAmountPrice.setText(this.wxMoney);
                    return;
                case 2131297357:
                    this.setWxRadioButton.setChecked(false);
                    this.setZfbRadioButton.setChecked(true);
                    lighton();
                    this.payBean = new PayBean();
                    if (!(this.alipayMoney == null || this.alipayMoney.length() == 0)) {
                        this.payBean.setAliPrice(this.alipayMoney);
                        this.payBean.setPhoneNumber(this.phoneName);
                    }
                    this.setAmountPrice.setText(this.alipayMoney);
                    return;
                case 2131297358:
                    this.setWxRadioButton.setChecked(false);
                    this.setZfbRadioButton.setChecked(true);
                    this.payBean = new PayBean();
                    if (!(this.alipayMoney == null || this.alipayMoney.length() == 0)) {
                        this.payBean.setAliPrice(this.alipayMoney);
                        this.payBean.setPhoneNumber(this.phoneName);
                    }
                    this.setAmountPrice.setText(this.alipayMoney);
                    return;
                default:
                    return;
            }
        } else if (this.selectBean == null || this.selectBean.getUserId() == null || "".equals(this.selectBean.getUserId())) {
            ToastUtils.showToast(this, "请选择要更换的设备！！！");
        } else {
            String str = (String) SPUtils.get(this, "ws_baby_phone", "");
            if (!"".equals(str)) {
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
