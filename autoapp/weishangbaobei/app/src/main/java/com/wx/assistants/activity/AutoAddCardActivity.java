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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.adapter.TagRemarkAdapter;
import com.wx.assistants.adapter.TagSelectAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.LabelBean;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.service_utils.Gender;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.CustomRadioSwitchLayout;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FFModelLayout;
import com.wx.assistants.view.SingleLabelSelectLayout;
import com.wx.assistants.view.TagCloudLayout;
import com.wx.assistants.webview.WebViewActivity;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AutoAddCardActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(2131296507)
    LinearLayout cleanEditText;
    @BindView(2131296569)
    CustomRadioSwitchLayout customRadioSwitchLayout;
    @BindView(2131296620)
    EditText editTextAddName;
    private int executeNum = 100;
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
    /* access modifiers changed from: private */
    public ArrayList<String> mRemark;
    private int maxNum = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297234)
    Switch remarkSwitchBtn;
    @BindView(2131297237)
    TagCloudLayout remarkViewGroup;
    @BindView(2131297238)
    LinearLayout remarksLayout;
    @BindView(2131297278)
    EditText sayContent;
    @BindView(2131297280)
    LinearLayout sayLayout;
    /* access modifiers changed from: private */
    public String selectLabel = "";
    /* access modifiers changed from: private */
    public Gender sex = Gender.ALL;
    @BindView(2131297359)
    RadioButton sexRadioButtonAll;
    @BindView(2131297360)
    RadioButton sexRadioButtonMan;
    @BindView(2131297361)
    RadioButton sexRadioButtonWoman;
    @BindView(2131297362)
    RadioGroup sexRadioGroup;
    @BindView(2131297386)
    SingleLabelSelectLayout singleSelectLabelLayout;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    @BindView(2131297425)
    Button startWx;
    private TagRemarkAdapter tagRemarkAdapter;
    private TagSelectAdapter tagSelectAdapter;
    /* access modifiers changed from: private */
    public int verifyCode = 1;
    @BindView(2131297636)
    LinearLayout videoIntroduce;

    static {
        StubApp.interface11(6696);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddCardActivity] */
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

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("自动添加名片");
        this.startWx.setText("启动微信加名片");
        this.executeTimeSpaceLayout.setExecuteTimeTitle("加人间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = AutoAddCardActivity.this.spaceTime = i;
            }
        });
        this.sayContent.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "group_add_card_content", editable.toString());
                }
            }
        });
        this.ffModelLayout.setDefaultTime(this.ffModelStartTime, this.ffModelEndTime);
        this.ffModelLayout.setOnFFModelListener(new FFModelLayout.OnFFModelListener() {
            public void ffMode(int i) {
                int unused = AutoAddCardActivity.this.ffModel = i;
                if (AutoAddCardActivity.this.ffModel == 0) {
                    AutoAddCardActivity.this.executeTimeSpaceLayout.setVisibility(0);
                } else {
                    AutoAddCardActivity.this.executeTimeSpaceLayout.setVisibility(8);
                }
            }

            public void ffModeTime(int i, int i2) {
                int unused = AutoAddCardActivity.this.ffModelStartTime = i;
                int unused2 = AutoAddCardActivity.this.ffModelEndTime = i2;
            }
        });
        this.customRadioSwitchLayout.setText("只加免验证的好友");
        this.customRadioSwitchLayout.setDefaultSelect(1);
        this.customRadioSwitchLayout.setOnModelSelectListener(new CustomRadioSwitchLayout.OnModelSelectListener() {
            public void mode(int i) {
                int unused = AutoAddCardActivity.this.verifyCode = i;
                if (i == 1) {
                    AutoAddCardActivity.this.sayLayout.setVisibility(0);
                } else {
                    AutoAddCardActivity.this.sayLayout.setVisibility(8);
                }
            }
        });
        this.remarkSwitchBtn.setChecked(false);
        this.remarkSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    boolean unused = AutoAddCardActivity.this.isRemark = true;
                    AutoAddCardActivity.this.remarksLayout.setVisibility(0);
                    return;
                }
                boolean unused2 = AutoAddCardActivity.this.isRemark = false;
                AutoAddCardActivity.this.remarksLayout.setVisibility(8);
            }
        });
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.widget.RadioGroup$OnCheckedChangeListener, android.content.Context, com.wx.assistants.activity.AutoAddCardActivity] */
    private void initData() {
        this.sexRadioGroup.setOnCheckedChangeListener(this);
        this.sexRadioButtonAll.setChecked(true);
        this.mList = new ArrayList<>();
        this.mList.addAll(Arrays.asList(getResources().getStringArray(2130903043)));
        this.tagSelectAdapter = new TagSelectAdapter(this, this.mList);
        this.flowViewGroup.setAdapter(this.tagSelectAdapter);
        this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                AutoAddCardActivity.this.sayContent.setText((CharSequence) AutoAddCardActivity.this.mList.get(i));
            }
        });
        this.mRemark = new ArrayList<>();
        this.mRemark.addAll(Arrays.asList(getResources().getStringArray(2130903048)));
        this.tagRemarkAdapter = new TagRemarkAdapter(this, this.mRemark);
        this.remarkViewGroup.setAdapter(this.tagRemarkAdapter);
        this.remarkViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                AutoAddCardActivity.this.editTextAddName.setText((String) AutoAddCardActivity.this.mRemark.get(i));
            }
        });
        this.singleSelectLabelLayout.setOnSelectSingleLabelListener(new SingleLabelSelectLayout.OnSelectSingleLabelListener() {
            public void selectLabel(LabelBean labelBean) {
                LogUtils.log("WS_BABY_selectLabel");
                if (labelBean == null) {
                    String unused = AutoAddCardActivity.this.selectLabel = null;
                } else {
                    String unused2 = AutoAddCardActivity.this.selectLabel = labelBean.getLabelName();
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
    public void onResume() {
        super.onResume();
        String str = (String) SPUtils.get(MyApplication.getConText(), "group_add_card_content", "");
        if (str != null && !"".equals(str)) {
            this.sayContent.setText(str);
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddCardActivity] */
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
                DialogUIUtils.dialogFunctionalSpecification(this, "批量加名片", "1.当群内有大量名片，你需要加这些名片为好友的时候适合用此功能。\n2.进入一个带有大量名片的群聊页，先手动滚动到打算开始加名片的位置，点击开始\n3.为了保证不出现微信加名片频繁，且好友能收到好友验证请求，建议每次添加50个左右，下次添加建议间隔三小时。\n");
                return;
            case 2131297425:
                if (validate()) {
                    startCheck("57", true, new BaseActivity.OnStartCheckListener() {
                        public void checkEnd() {
                            String str;
                            String trim = AutoAddCardActivity.this.editTextAddName.getText().toString().trim();
                            if (trim == null || "".equals(trim)) {
                                str = "";
                            } else {
                                str = trim + ": ";
                            }
                            OperationParameterModel operationParameterModel = new OperationParameterModel();
                            operationParameterModel.setTaskNum("57");
                            operationParameterModel.setSayContent(AutoAddCardActivity.this.sayContent.getText().toString());
                            operationParameterModel.setStartIndex(1);
                            operationParameterModel.setMaxOperaNum(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                            operationParameterModel.setSex(AutoAddCardActivity.this.sex);
                            operationParameterModel.setFfModel(AutoAddCardActivity.this.ffModel);
                            operationParameterModel.setVerifyCode(AutoAddCardActivity.this.verifyCode);
                            operationParameterModel.setFfModelStartTime(AutoAddCardActivity.this.ffModelStartTime);
                            operationParameterModel.setFfModelEndTime(AutoAddCardActivity.this.ffModelEndTime);
                            operationParameterModel.setSpaceTime(AutoAddCardActivity.this.spaceTime);
                            operationParameterModel.setRemarkPrefix(str);
                            operationParameterModel.setSingLabelStr(AutoAddCardActivity.this.selectLabel);
                            MyApplication.instance.setOperationParameterModel(operationParameterModel);
                            AutoAddCardActivity.this.startWX(operationParameterModel);
                        }
                    });
                    return;
                }
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "批量加名片视频教程", QBangApis.VIDEO_GROUP_ADD_CARD);
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddCardActivity] */
    public boolean validate() {
        if (this.isRemark) {
            String trim = this.editTextAddName.getText().toString().trim();
            if (trim != null && !"".equals(trim)) {
                return true;
            }
            ToastUtils.showToast(this, "请选择备注标签");
            return false;
        }
        this.editTextAddName.setText("");
        return true;
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case 2131297359:
                this.sex = Gender.ALL;
                return;
            case 2131297360:
                this.sex = Gender.MAN;
                return;
            case 2131297361:
                this.sex = Gender.WOMEN;
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
