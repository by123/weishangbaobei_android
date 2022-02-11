package com.wx.assistants.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.NumSettingLayout;
import com.wx.assistants.webview.WebViewActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AutoPassNilValidationActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public int executeNum = 200;
    /* access modifiers changed from: private */
    public String filterContent = "";
    /* access modifiers changed from: private */
    public boolean isFilter = false;
    /* access modifiers changed from: private */
    public boolean isLeave = false;
    /* access modifiers changed from: private */
    public String leaveMsg = "";
    private int maxNum = 5000;
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
    @BindView(2131297082)
    NumSettingLayout numberSettingLayout;
    @BindView(2131297234)
    Switch remarkSwitchBtn;
    @BindView(2131297235)
    Switch remarkSwitchBtn2;
    @BindView(2131297278)
    EditText sayContent;
    @BindView(2131297279)
    EditText sayContent2;
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6708);
    }

    private void initView() {
        this.navTitle.setText("自动通过免验证好友");
        this.startWx.setText("启动微信开始通过");
        this.navRightImg.setVisibility(0);
        this.remarkSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                boolean unused = AutoPassNilValidationActivity.this.isFilter = z;
                if (AutoPassNilValidationActivity.this.isFilter) {
                    AutoPassNilValidationActivity.this.sayContent.setVisibility(0);
                } else {
                    AutoPassNilValidationActivity.this.sayContent.setVisibility(8);
                }
            }
        });
        this.remarkSwitchBtn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                boolean unused = AutoPassNilValidationActivity.this.isLeave = z;
                if (AutoPassNilValidationActivity.this.isLeave) {
                    AutoPassNilValidationActivity.this.sayContent2.setVisibility(0);
                } else {
                    AutoPassNilValidationActivity.this.sayContent2.setVisibility(8);
                }
            }
        });
        String str = (String) SPUtils.get(MyApplication.getConText(), "auto_pass_nil_validation_text", "");
        if (str != null && !"".equals(str)) {
            this.sayContent.setText(str);
        }
        String str2 = (String) SPUtils.get(MyApplication.getConText(), "auto_pass_nil_validation_text2", "");
        if (str2 != null && !"".equals(str2)) {
            this.sayContent2.setText(str2);
        }
        this.sayContent.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "auto_pass_nil_validation_text", editable.toString());
                } else {
                    SPUtils.put(MyApplication.getConText(), "auto_pass_nil_validation_text", "");
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.sayContent2.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "auto_pass_nil_validation_text2", editable.toString());
                } else {
                    SPUtils.put(MyApplication.getConText(), "auto_pass_nil_validation_text2", "");
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoPassNilValidationActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.AutoPassNilValidationActivity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.numberSettingLayout.setNum(this.executeNum, this.maxNum, "加人数量");
        this.startIndex = ((Integer) SPUtils.get(this, "auto_pass_nil_validation_num", 1)).intValue();
        this.numberSettingLayout.setStartPointIndex(this.startIndex, "加人起点");
        this.numberSettingLayout.setOnResultListener(new NumSettingLayout.OnResultListener() {
            public void result(int i, int i2) {
                int unused = AutoPassNilValidationActivity.this.executeNum = i;
                int unused2 = AutoPassNilValidationActivity.this.startIndex = i2;
                SPUtils.put(MyApplication.getConText(), "auto_pass_nil_validation_num", Integer.valueOf(AutoPassNilValidationActivity.this.startIndex));
            }
        });
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.AutoPassNilValidationActivity] */
    @OnClick({2131297049, 2131297425, 2131297636, 2131297052})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.如果您设置了免验证，别人加你为好友之后会出现在微信消息列表。\n 2.该功能是逐条检测您的消息列表，把加你的好友自动添加到通讯录。");
        } else if (id == 2131297425) {
            this.filterContent = this.sayContent.getText().toString();
            if (!this.isFilter || (this.filterContent != null && !"".equals(this.filterContent))) {
                this.leaveMsg = this.sayContent2.getText().toString();
                if (!this.isLeave || (this.leaveMsg != null && !"".equals(this.leaveMsg))) {
                    startCheck("12", true, new BaseActivity.OnStartCheckListener() {
                        public void checkEnd() {
                            OperationParameterModel operationParameterModel = new OperationParameterModel();
                            operationParameterModel.setTaskNum("60");
                            operationParameterModel.setStartIndex(AutoPassNilValidationActivity.this.startIndex);
                            operationParameterModel.setMaxOperaNum(AutoPassNilValidationActivity.this.executeNum);
                            if (AutoPassNilValidationActivity.this.isFilter) {
                                operationParameterModel.setSayContent(AutoPassNilValidationActivity.this.filterContent);
                            } else {
                                operationParameterModel.setSayContent("");
                            }
                            if (AutoPassNilValidationActivity.this.isLeave) {
                                operationParameterModel.setMaterialText(AutoPassNilValidationActivity.this.leaveMsg);
                            } else {
                                operationParameterModel.setMaterialText("");
                            }
                            MyApplication.instance.setOperationParameterModel(operationParameterModel);
                            AutoPassNilValidationActivity.this.startWX(operationParameterModel);
                        }
                    });
                } else {
                    ToastUtils.showToast(this, "请输入留言信息！");
                }
            } else {
                ToastUtils.showToast(this, "请输入过滤内容！");
            }
        } else if (id == 2131297636) {
            WebViewActivity.startActivity(this, "通过免验证视频教程", QBangApis.VIDEO_PASS_NIL_VALIDATION);
        }
    }
}
