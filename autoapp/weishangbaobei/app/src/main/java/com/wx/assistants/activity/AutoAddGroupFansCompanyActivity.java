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
import com.wx.assistants.view.NumSettingLayout;
import com.wx.assistants.view.SingleLabelSelectLayoutCompany;
import com.wx.assistants.view.TagCloudLayout;
import com.wx.assistants.webview.WebViewActivity;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Arrays;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AutoAddGroupFansCompanyActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(2131296507)
    LinearLayout cleanEditText;
    @BindView(2131296569)
    CustomRadioSwitchLayout customRadioSwitchLayout;
    @BindView(2131296620)
    EditText editTextAddName;
    /* access modifiers changed from: private */
    public int executeNum = 100;
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
    @BindView(2131297082)
    NumSettingLayout numberSettingLayout;
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
    SingleLabelSelectLayoutCompany singleSelectLabelLayout;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    private TagRemarkAdapter tagRemarkAdapter;
    private TagSelectAdapter tagSelectAdapter;
    /* access modifiers changed from: private */
    public int verifyCode = 1;
    @BindView(2131297636)
    LinearLayout videoIntroduce;

    static {
        StubApp.interface11(6704);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddGroupFansCompanyActivity] */
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

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddGroupFansCompanyActivity] */
    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("自动群聊加粉");
        this.startWx.setText("启动微信加粉");
        this.executeTimeSpaceLayout.setExecuteTimeTitle("加人间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = AutoAddGroupFansCompanyActivity.this.spaceTime = i;
            }
        });
        this.sayContent.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "group_add_friend_content_company", editable.toString());
                }
            }
        });
        this.ffModelLayout.setDefaultTime(this.ffModelStartTime, this.ffModelEndTime);
        this.ffModelLayout.setOnFFModelListener(new FFModelLayout.OnFFModelListener() {
            public void ffMode(int i) {
                int unused = AutoAddGroupFansCompanyActivity.this.ffModel = i;
                if (AutoAddGroupFansCompanyActivity.this.ffModel == 0) {
                    AutoAddGroupFansCompanyActivity.this.executeTimeSpaceLayout.setVisibility(0);
                } else {
                    AutoAddGroupFansCompanyActivity.this.executeTimeSpaceLayout.setVisibility(8);
                }
            }

            public void ffModeTime(int i, int i2) {
                int unused = AutoAddGroupFansCompanyActivity.this.ffModelStartTime = i;
                int unused2 = AutoAddGroupFansCompanyActivity.this.ffModelEndTime = i2;
            }
        });
        this.remarkSwitchBtn.setChecked(false);
        this.remarkSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    boolean unused = AutoAddGroupFansCompanyActivity.this.isRemark = true;
                    AutoAddGroupFansCompanyActivity.this.remarksLayout.setVisibility(0);
                    return;
                }
                boolean unused2 = AutoAddGroupFansCompanyActivity.this.isRemark = false;
                AutoAddGroupFansCompanyActivity.this.remarksLayout.setVisibility(8);
            }
        });
        this.numberSettingLayout.setNum(this.executeNum, this.maxNum, "加人数量");
        this.startIndex = ((Integer) SPUtils.get(this, "auto_group_add_num_company", 1)).intValue();
        this.numberSettingLayout.setStartPointIndex(this.startIndex, "加人起点");
        this.numberSettingLayout.setOnResultListener(new NumSettingLayout.OnResultListener() {
            public void result(int i, int i2) {
                int unused = AutoAddGroupFansCompanyActivity.this.executeNum = i;
                int unused2 = AutoAddGroupFansCompanyActivity.this.startIndex = i2;
                SPUtils.put(MyApplication.getConText(), "auto_group_add_num_company", Integer.valueOf(AutoAddGroupFansCompanyActivity.this.startIndex));
            }
        });
        this.customRadioSwitchLayout.setText("只加免验证的好友");
        this.customRadioSwitchLayout.setDefaultSelect(1);
        this.customRadioSwitchLayout.setOnModelSelectListener(new CustomRadioSwitchLayout.OnModelSelectListener() {
            public void mode(int i) {
                int unused = AutoAddGroupFansCompanyActivity.this.verifyCode = i;
                if (i == 1) {
                    AutoAddGroupFansCompanyActivity.this.sayLayout.setVisibility(0);
                } else {
                    AutoAddGroupFansCompanyActivity.this.sayLayout.setVisibility(8);
                }
            }
        });
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.widget.RadioGroup$OnCheckedChangeListener, android.content.Context, com.wx.assistants.activity.AutoAddGroupFansCompanyActivity] */
    private void initData() {
        this.sexRadioGroup.setOnCheckedChangeListener(this);
        this.sexRadioButtonAll.setChecked(true);
        this.mList = new ArrayList<>();
        this.mList.addAll(Arrays.asList(getResources().getStringArray(2130903043)));
        this.tagSelectAdapter = new TagSelectAdapter(this, this.mList);
        this.flowViewGroup.setAdapter(this.tagSelectAdapter);
        this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                AutoAddGroupFansCompanyActivity.this.sayContent.setText((CharSequence) AutoAddGroupFansCompanyActivity.this.mList.get(i));
            }
        });
        this.mRemark = new ArrayList<>();
        this.mRemark.addAll(Arrays.asList(getResources().getStringArray(2130903048)));
        this.tagRemarkAdapter = new TagRemarkAdapter(this, this.mRemark);
        this.remarkViewGroup.setAdapter(this.tagRemarkAdapter);
        this.remarkViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                AutoAddGroupFansCompanyActivity.this.editTextAddName.setText((String) AutoAddGroupFansCompanyActivity.this.mRemark.get(i));
            }
        });
        this.singleSelectLabelLayout.setOnSelectSingleLabelListener(new SingleLabelSelectLayoutCompany.OnSelectSingleLabelListener() {
            public void selectLabel(LabelBean labelBean) {
                LogUtils.log("WS_BABY_selectLabel");
                if (labelBean == null) {
                    String unused = AutoAddGroupFansCompanyActivity.this.selectLabel = null;
                } else {
                    String unused2 = AutoAddGroupFansCompanyActivity.this.selectLabel = labelBean.getLabelName();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "auto_group_add_num_company", 1)).intValue();
        if (this.numberSettingLayout != null) {
            this.startIndex = intValue;
            this.numberSettingLayout.setStartPointIndex(this.startIndex, "加人起点");
        }
        String str = (String) SPUtils.get(MyApplication.getConText(), "group_add_friend_content_company", "");
        if (str != null && !"".equals(str)) {
            this.sayContent.setText(str);
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddGroupFansCompanyActivity] */
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
                DialogUIUtils.dialogFunctionalSpecification(this, "企业外部群加粉", "1.请先进入到企业外部群聊页后，再点开始。\n\n2.加粉起点默认为1，从群聊的第1个好友开始加。\n\n3.建议一天添加好友100个左右。\n\n4.如果您有多个微信号，每个微信号都可以使用群加好友。\n\n5.为了保证不出现微信加粉频繁，且好友能收到好友验证请求，建议每次添加好友，间隔时间10秒（具体根据个人微信号的质量来定），每次添加50个左右，下次添加建议间隔三小时，或者切换其他微信号，继续使用群聊加好友的功能。\n\n");
                return;
            case 2131297425:
                if (validate()) {
                    if (!checkCompanyWxApp(this)) {
                        ToastUtils.showToast(this, "请先安装企业微信！");
                        return;
                    } else {
                        startCheck("12", true, new BaseActivity.OnStartCheckListener() {
                            public void checkEnd() {
                                String str;
                                String trim = AutoAddGroupFansCompanyActivity.this.editTextAddName.getText().toString().trim();
                                if (trim == null || "".equals(trim)) {
                                    str = "";
                                } else {
                                    str = trim + ": ";
                                }
                                OperationParameterModel operationParameterModel = new OperationParameterModel();
                                operationParameterModel.setTaskNum("10012");
                                operationParameterModel.setSayContent(AutoAddGroupFansCompanyActivity.this.sayContent.getText().toString());
                                operationParameterModel.setStartIndex(AutoAddGroupFansCompanyActivity.this.startIndex);
                                operationParameterModel.setMaxOperaNum(AutoAddGroupFansCompanyActivity.this.executeNum);
                                operationParameterModel.setSex(AutoAddGroupFansCompanyActivity.this.sex);
                                operationParameterModel.setFfModel(AutoAddGroupFansCompanyActivity.this.ffModel);
                                operationParameterModel.setVerifyCode(AutoAddGroupFansCompanyActivity.this.verifyCode);
                                operationParameterModel.setFfModelStartTime(AutoAddGroupFansCompanyActivity.this.ffModelStartTime);
                                operationParameterModel.setFfModelEndTime(AutoAddGroupFansCompanyActivity.this.ffModelEndTime);
                                operationParameterModel.setSpaceTime(AutoAddGroupFansCompanyActivity.this.spaceTime);
                                operationParameterModel.setRemarkPrefix(str);
                                operationParameterModel.setSingLabelStr(AutoAddGroupFansCompanyActivity.this.selectLabel);
                                MyApplication.instance.setOperationParameterModel(operationParameterModel);
                                AutoAddGroupFansCompanyActivity.this.startWXCompany(operationParameterModel);
                            }
                        });
                        return;
                    }
                } else {
                    return;
                }
            case 2131297636:
                WebViewActivity.startActivity(this, "企业外部群加粉视频教程", QBangApis.VIDEO_COMPANY_GROUP_ADD);
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddGroupFansCompanyActivity] */
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
