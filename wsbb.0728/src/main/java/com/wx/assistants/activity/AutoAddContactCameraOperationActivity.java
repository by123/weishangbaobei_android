package com.wx.assistants.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.adapter.TagSelectAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.LabelBean;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FFModelLayout;
import com.wx.assistants.view.RemarkFriendLayout;
import com.wx.assistants.view.SingleLabelSelectLayout;
import com.wx.assistants.view.TagCloudLayout;
import com.wx.assistants.webview.WebViewActivity;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class AutoAddContactCameraOperationActivity extends BaseActivity {
    @BindView(2131296507)
    LinearLayout cleanEditText;
    private int executeNum = 50;
    @BindView(2131296646)
    RemarkFriendLayout executeRemarkLayout;
    @BindView(2131296647)
    ExecuteTimeSpaceLayout executeTimeSpaceLayout;
    /* access modifiers changed from: private */
    public int ffModel = 0;
    /* access modifiers changed from: private */
    public int ffModelEndTime = 10;
    @BindView(2131296660)
    FFModelLayout ffModelLayout;
    /* access modifiers changed from: private */
    public int ffModelStartTime = 0;
    @BindView(2131296681)
    TagCloudLayout flowViewGroup;
    /* access modifiers changed from: private */
    public boolean isRemark = false;
    /* access modifiers changed from: private */
    public ArrayList<String> mList;
    private int maxNum = 50;
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
    /* access modifiers changed from: private */
    public String remarkStr = "";
    @BindView(2131297278)
    EditText sayContent;
    /* access modifiers changed from: private */
    public String selectLabel = "";
    @BindView(2131297363)
    ShadowLinearLayout shadowLinearLayout;
    @BindView(2131297386)
    SingleLabelSelectLayout singleSelectLabelLayout;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    @BindView(2131297425)
    Button startWx;
    private TagSelectAdapter tagSelectAdapter;
    @BindView(2131297636)
    LinearLayout videoIntroduce;

    static {
        StubApp.interface11(6699);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContactCameraOperationActivity] */
    private void initData() {
        this.mList = new ArrayList<>();
        this.mList.addAll(Arrays.asList(getResources().getStringArray(2130903043)));
        this.tagSelectAdapter = new TagSelectAdapter(this, this.mList);
        this.flowViewGroup.setAdapter(this.tagSelectAdapter);
        this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                AutoAddContactCameraOperationActivity.this.sayContent.setText((CharSequence) AutoAddContactCameraOperationActivity.this.mList.get(i));
            }
        });
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("拍照加人附加信息");
        this.startWx.setText("启动微信开始加人");
        this.executeTimeSpaceLayout.setExecuteTimeTitle("加人间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = AutoAddContactCameraOperationActivity.this.spaceTime = i;
            }
        });
        this.ffModelLayout.setDefaultTime(this.ffModelStartTime, this.ffModelEndTime);
        this.ffModelLayout.setOnFFModelListener(new FFModelLayout.OnFFModelListener() {
            public void ffMode(int i) {
                int unused = AutoAddContactCameraOperationActivity.this.ffModel = i;
                if (AutoAddContactCameraOperationActivity.this.ffModel == 0) {
                    AutoAddContactCameraOperationActivity.this.executeTimeSpaceLayout.setVisibility(8);
                } else {
                    AutoAddContactCameraOperationActivity.this.executeTimeSpaceLayout.setVisibility(8);
                }
            }

            public void ffModeTime(int i, int i2) {
                int unused = AutoAddContactCameraOperationActivity.this.ffModelStartTime = i;
                int unused2 = AutoAddContactCameraOperationActivity.this.ffModelEndTime = i2;
            }
        });
        this.executeRemarkLayout.setOnRemarkListener(new RemarkFriendLayout.OnRemarkListener() {
            public void remark(boolean z, String str) {
                boolean unused = AutoAddContactCameraOperationActivity.this.isRemark = z;
                String unused2 = AutoAddContactCameraOperationActivity.this.remarkStr = str;
            }
        });
        this.singleSelectLabelLayout.setOnSelectSingleLabelListener(new SingleLabelSelectLayout.OnSelectSingleLabelListener() {
            public void selectLabel(LabelBean labelBean) {
                LogUtils.log("WS_BABY_selectLabel");
                if (labelBean == null) {
                    String unused = AutoAddContactCameraOperationActivity.this.selectLabel = null;
                } else {
                    String unused2 = AutoAddContactCameraOperationActivity.this.selectLabel = labelBean.getLabelName();
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContactCameraOperationActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        String str = (String) SPUtils.get(MyApplication.getConText(), "auto_add_customer_text_1", "");
        if (str != null && !"".equals(str)) {
            this.sayContent.setText(str);
        }
        this.sayContent.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "auto_add_customer_text_1", editable.toString());
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContactCameraOperationActivity] */
    @OnClick({2131296507, 2131297049, 2131297425, 2131297636, 2131297052})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296507:
                this.sayContent.setText("");
                return;
            case 2131297049:
                back();
                return;
            case 2131297052:
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.根据微信规则，使用此功能一次最多加50个好友。\n2.如果有多个微信号，每个微信号都可以使用此功能加人。\n3.为了保证不出现微信加人频繁，同一微信号建议每隔3小时使用一次加人功能。\n4.此功能为会员用户提供支持。");
                return;
            case 2131297425:
                startCheck("48", true, new BaseActivity.OnStartCheckListener() {
                    public void checkEnd() {
                        OperationParameterModel operationParameterModel = new OperationParameterModel();
                        operationParameterModel.setTaskNum("48");
                        operationParameterModel.setMaxOperaNum(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                        operationParameterModel.setScanPhones(AutoAddContactCameraAddressActivity.phones);
                        operationParameterModel.setStartIndex(1);
                        operationParameterModel.setBreakAdd(0);
                        operationParameterModel.setAutoRemarkContactsName(false);
                        operationParameterModel.setSpaceTime(0);
                        operationParameterModel.setFfModel(AutoAddContactCameraOperationActivity.this.ffModel);
                        operationParameterModel.setFfModelStartTime(AutoAddContactCameraOperationActivity.this.ffModelStartTime);
                        operationParameterModel.setFfModelEndTime(AutoAddContactCameraOperationActivity.this.ffModelEndTime);
                        operationParameterModel.setSayContent(AutoAddContactCameraOperationActivity.this.sayContent.getText().toString());
                        if (AutoAddContactCameraOperationActivity.this.isRemark) {
                            operationParameterModel.setRemarkPrefix(AutoAddContactCameraOperationActivity.this.remarkStr + "_");
                        } else {
                            operationParameterModel.setRemarkPrefix("");
                        }
                        operationParameterModel.setSingLabelStr(AutoAddContactCameraOperationActivity.this.selectLabel);
                        MyApplication.instance.setOperationParameterModel(operationParameterModel);
                        AutoAddContactCameraOperationActivity.this.startWX(operationParameterModel);
                    }
                });
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "手动输入加人视频教程", QBangApis.AUTO_ADD_CUSTOMER);
                return;
            default:
                return;
        }
    }
}
