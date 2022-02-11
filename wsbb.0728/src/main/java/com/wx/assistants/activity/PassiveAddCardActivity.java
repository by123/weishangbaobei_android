package com.wx.assistants.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.umeng.socialize.sina.params.ShareRequestParam;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AddCardRes;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.EditCardRes;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.PassiveCardEvent;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.AutoLinkStyleTextView;
import com.wx.assistants.webview.WebViewActivity;
import org.greenrobot.eventbus.EventBus;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class PassiveAddCardActivity extends BaseActivity {
    @BindView(2131296363)
    AutoLinkStyleTextView autoLinkStyleTextView;
    private String code = "";
    @BindView(2131296616)
    EditText editLeavingMessage;
    private int id = -1;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297079)
    TextView numText;
    @BindView(2131297363)
    ShadowLinearLayout shadowLinearLayout;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(9342);
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("添加被加号");
        this.startWx.setText("确认添加");
        try {
            this.id = getIntent().getIntExtra("id", -1);
        } catch (Exception e) {
        }
        try {
            this.code = getIntent().getStringExtra(ShareRequestParam.RESP_UPLOAD_PIC_PARAM_CODE);
        } catch (Exception e2) {
        }
        if (this.id == -1) {
            this.navTitle.setText("添加被加号");
            this.startWx.setText("确认添加");
        } else {
            this.navTitle.setText("修改被加号");
            this.startWx.setText("确认修改");
        }
        if (this.code != null && !"".equals(this.code)) {
            this.editLeavingMessage.setText(this.code);
        }
        this.autoLinkStyleTextView.setOnClickCallBack(new AutoLinkStyleTextView.ClickCallBack() {
            /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.activity.PassiveAddCardActivity, android.content.Context] */
            /* JADX WARNING: type inference failed for: r0v2, types: [com.wx.assistants.activity.PassiveAddCardActivity, android.content.Context] */
            public void onClick(int i) {
                if (i == 0) {
                    WebViewActivity.startActivity(PassiveAddCardActivity.this, "微商宝贝用户服务协议", "file:///android_asset/use_need_know_1.html");
                } else if (i == 1) {
                    WebViewActivity.startActivity(PassiveAddCardActivity.this, "微商宝贝隐私政策", "file:///android_asset/use_need_know.html");
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.activity.PassiveAddCardActivity, android.content.Context] */
    @OnClick({2131297425, 2131297049, 2131297636, 2131297052})
    public void onViewClicked(View view) {
        int id2 = view.getId();
        if (id2 == 2131297049) {
            back();
        } else if (id2 == 2131297052) {
            DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.添加的被加号，要确认能收到该好友。\n\n2.被加号可以是您绑定微信的手机号、微信号、QQ号\n\n3.大量被加需关闭好友验证：「设置-隐私-加我为朋友时需要验证」关闭该项。\n\n4.开启被加后，将会有大量的好友加你，加你的好友会出现在消息列表页，只需通过即可。\n");
        } else if (id2 == 2131297425) {
            String obj = this.editLeavingMessage.getText().toString();
            if (obj == null || "".equals(obj)) {
                ToastUtils.showToast(this, "请输入被加号");
            } else if (this.id == -1) {
                showLoadingDialog("正在添加");
                ApiWrapper.getInstance().addCardInfo(new AddCardRes(obj), new ApiWrapper.CallbackListener<ConmdBean>() {
                    /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.activity.PassiveAddCardActivity, android.content.Context] */
                    public void onFailure(FailureModel failureModel) {
                        PassiveAddCardActivity.this.dismissLoadingDialog();
                        ? r0 = PassiveAddCardActivity.this;
                        ToastUtils.showToast(r0, failureModel.getCode() + ":" + failureModel.getErrorMessage());
                    }

                    /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.activity.PassiveAddCardActivity, android.content.Context] */
                    public void onFinish(ConmdBean conmdBean) {
                        PassiveAddCardActivity.this.dismissLoadingDialog();
                        if (conmdBean != null) {
                            ToastUtils.showToast(PassiveAddCardActivity.this, conmdBean.getMessage());
                            if (conmdBean.getCode() == 0) {
                                EventBus.getDefault().post(new PassiveCardEvent());
                                PassiveAddCardActivity.this.back();
                            }
                        }
                    }
                });
            } else {
                showLoadingDialog("正在修改");
                ApiWrapper.getInstance().editCardInfo(new EditCardRes(Integer.valueOf(this.id), obj), new ApiWrapper.CallbackListener<ConmdBean>() {
                    /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.activity.PassiveAddCardActivity, android.content.Context] */
                    public void onFailure(FailureModel failureModel) {
                        PassiveAddCardActivity.this.dismissLoadingDialog();
                        ? r0 = PassiveAddCardActivity.this;
                        ToastUtils.showToast(r0, failureModel.getCode() + ":" + failureModel.getErrorMessage());
                    }

                    /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.activity.PassiveAddCardActivity, android.content.Context] */
                    public void onFinish(ConmdBean conmdBean) {
                        PassiveAddCardActivity.this.dismissLoadingDialog();
                        if (conmdBean != null) {
                            ToastUtils.showToast(PassiveAddCardActivity.this, conmdBean.getMessage());
                            if (conmdBean.getCode() == 0) {
                                EventBus.getDefault().post(new PassiveCardEvent());
                                PassiveAddCardActivity.this.back();
                            }
                        }
                    }
                });
            }
        } else if (id2 == 2131297636) {
            WebViewActivity.startActivity(this, "坐等被加", QBangApis.VIDEO_PASSIVE_CARD);
        }
    }
}
