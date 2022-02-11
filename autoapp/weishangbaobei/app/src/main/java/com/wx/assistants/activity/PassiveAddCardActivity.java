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

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0021 */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initView() {
        /*
            r3 = this;
            android.widget.ImageView r0 = r3.navRightImg
            r1 = 0
            r0.setVisibility(r1)
            android.widget.TextView r0 = r3.navTitle
            java.lang.String r1 = "添加被加号"
            r0.setText(r1)
            android.widget.Button r0 = r3.startWx
            java.lang.String r1 = "确认添加"
            r0.setText(r1)
            r0 = -1
            android.content.Intent r1 = r3.getIntent()     // Catch:{ Exception -> 0x0021 }
            java.lang.String r2 = "id"
            int r1 = r1.getIntExtra(r2, r0)     // Catch:{ Exception -> 0x0021 }
            r3.id = r1     // Catch:{ Exception -> 0x0021 }
        L_0x0021:
            android.content.Intent r1 = r3.getIntent()     // Catch:{ Exception -> 0x002d }
            java.lang.String r2 = "code"
            java.lang.String r1 = r1.getStringExtra(r2)     // Catch:{ Exception -> 0x002d }
            r3.code = r1     // Catch:{ Exception -> 0x002d }
        L_0x002d:
            int r1 = r3.id
            if (r1 != r0) goto L_0x0040
            android.widget.TextView r0 = r3.navTitle
            java.lang.String r1 = "添加被加号"
            r0.setText(r1)
            android.widget.Button r0 = r3.startWx
            java.lang.String r1 = "确认添加"
            r0.setText(r1)
            goto L_0x004e
        L_0x0040:
            android.widget.TextView r0 = r3.navTitle
            java.lang.String r1 = "修改被加号"
            r0.setText(r1)
            android.widget.Button r0 = r3.startWx
            java.lang.String r1 = "确认修改"
            r0.setText(r1)
        L_0x004e:
            java.lang.String r0 = r3.code
            if (r0 == 0) goto L_0x0063
            java.lang.String r0 = ""
            java.lang.String r1 = r3.code
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0063
            android.widget.EditText r0 = r3.editLeavingMessage
            java.lang.String r1 = r3.code
            r0.setText(r1)
        L_0x0063:
            com.wx.assistants.view.AutoLinkStyleTextView r0 = r3.autoLinkStyleTextView
            com.wx.assistants.activity.PassiveAddCardActivity$1 r1 = new com.wx.assistants.activity.PassiveAddCardActivity$1
            r1.<init>()
            r0.setOnClickCallBack(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.PassiveAddCardActivity.initView():void");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.activity.PassiveAddCardActivity, android.content.Context] */
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

                    /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.activity.PassiveAddCardActivity, android.content.Context] */
                    public void onFailure(FailureModel failureModel) {
                        PassiveAddCardActivity.this.dismissLoadingDialog();
                        ? r0 = PassiveAddCardActivity.this;
                        ToastUtils.showToast(r0, failureModel.getCode() + ":" + failureModel.getErrorMessage());
                    }
                });
            } else {
                showLoadingDialog("正在修改");
                ApiWrapper.getInstance().editCardInfo(new EditCardRes(Integer.valueOf(this.id), obj), new ApiWrapper.CallbackListener<ConmdBean>() {
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

                    /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.activity.PassiveAddCardActivity, android.content.Context] */
                    public void onFailure(FailureModel failureModel) {
                        PassiveAddCardActivity.this.dismissLoadingDialog();
                        ? r0 = PassiveAddCardActivity.this;
                        ToastUtils.showToast(r0, failureModel.getCode() + ":" + failureModel.getErrorMessage());
                    }
                });
            }
        } else if (id2 == 2131297636) {
            WebViewActivity.startActivity(this, "坐等被加", QBangApis.VIDEO_PASSIVE_CARD);
        }
    }
}
