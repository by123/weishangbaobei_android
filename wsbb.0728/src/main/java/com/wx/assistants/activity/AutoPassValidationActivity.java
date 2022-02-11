package com.wx.assistants.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.LabelBean;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.RemarkFriendLayout;
import com.wx.assistants.view.SingleLabelSelectLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AutoPassValidationActivity extends BaseActivity {
    @BindView(2131296646)
    RemarkFriendLayout executeRemarkLayout;
    /* access modifiers changed from: private */
    public boolean isRemark = false;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297054)
    TextView navTitle;
    /* access modifiers changed from: private */
    public String remarkStr = "";
    /* access modifiers changed from: private */
    public String selectLabel = "";
    @BindView(2131297386)
    SingleLabelSelectLayout singleSelectLabelLayout;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6709);
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("自动接受好友请求");
        this.startWx.setText("启动微信接受好友请求");
        this.executeRemarkLayout.setOnRemarkListener(new RemarkFriendLayout.OnRemarkListener() {
            public void remark(boolean z, String str) {
                boolean unused = AutoPassValidationActivity.this.isRemark = z;
                String unused2 = AutoPassValidationActivity.this.remarkStr = str;
            }
        });
        this.singleSelectLabelLayout.setOnSelectSingleLabelListener(new SingleLabelSelectLayout.OnSelectSingleLabelListener() {
            public void selectLabel(LabelBean labelBean) {
                LogUtils.log("WS_BABY_selectLabel");
                if (labelBean == null) {
                    String unused = AutoPassValidationActivity.this.selectLabel = null;
                } else {
                    String unused2 = AutoPassValidationActivity.this.selectLabel = labelBean.getLabelName();
                }
            }

            public void selectedPeopleList(LinkedHashSet<String> linkedHashSet) {
                LogUtils.log("WS_BABY_selectedPeopleList" + linkedHashSet);
            }

            public void selectedPeopleString(String str) {
                LogUtils.log("WS_BABY_selectedPeopleString" + str);
            }
        });
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.activity.AutoPassValidationActivity, android.content.Context] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.activity.AutoPassValidationActivity, android.content.Context] */
    @OnClick({2131297049, 2131297425, 2131297636, 2131297052})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.很多人加你，手动点击通过很麻烦，该功能可以实现自动接受好友请求\n\n2.该功能是在开启好友验证的情况下可以使用\n\n3.如果被加好友很多，建议一次性接受在50个以内，即可批次接受\n");
        } else if (id != 2131297425) {
            if (id == 2131297636) {
                WebViewActivity.startActivity(this, "自动接受好友请求", QBangApis.VIDEO_AUTO_PASS_VALIDATION);
            }
        } else if (!this.isRemark || (this.remarkStr != null && !"".equals(this.remarkStr))) {
            startCheck("23", true, new BaseActivity.OnStartCheckListener() {
                public void checkEnd() {
                    OperationParameterModel operationParameterModel = new OperationParameterModel();
                    operationParameterModel.setTaskNum("23");
                    if (AutoPassValidationActivity.this.isRemark) {
                        operationParameterModel.setSayContent(AutoPassValidationActivity.this.remarkStr + "_");
                    } else {
                        operationParameterModel.setSayContent("");
                    }
                    operationParameterModel.setSingLabelStr(AutoPassValidationActivity.this.selectLabel);
                    MyApplication.instance.setOperationParameterModel(operationParameterModel);
                    AutoPassValidationActivity.this.startWX(operationParameterModel);
                }
            });
        } else {
            ToastUtils.showToast(this, "请设置备注前缀");
        }
    }
}
