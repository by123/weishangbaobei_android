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
import com.wx.assistants.view.NumSettingOnlyLayout;
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

public class AutoAddGroupFansActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(2131296507)
    LinearLayout cleanEditText;
    @BindView(2131296569)
    CustomRadioSwitchLayout customRadioSwitchLayout;
    @BindView(2131296620)
    EditText editTextAddName;
    /* access modifiers changed from: private */
    public int executeNum = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
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
    @BindView(2131296669)
    EditText filterNickName;
    @BindView(2131296681)
    TagCloudLayout flowViewGroup;
    /* access modifiers changed from: private */
    public boolean isRemark = false;
    /* access modifiers changed from: private */
    public ArrayList<String> mList;
    /* access modifiers changed from: private */
    public ArrayList<String> mRemark;
    /* access modifiers changed from: private */
    public int maxNum = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
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
    @BindView(2131297083)
    NumSettingOnlyLayout numberSettingLayout0;
    @BindView(2131297234)
    Switch remarkSwitchBtn;
    @BindView(2131297237)
    TagCloudLayout remarkViewGroup;
    @BindView(2131297238)
    LinearLayout remarksLayout;
    @BindView(2131297278)
    EditText sayContent;
    /* access modifiers changed from: private */
    public int sayHelloNum = 20;
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
    @BindView(2131297363)
    ShadowLinearLayout shadowLinearLayout;
    @BindView(2131297386)
    SingleLabelSelectLayout singleSelectLabelLayout;
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
        StubApp.interface11(6703);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.widget.RadioGroup$OnCheckedChangeListener, android.content.Context, com.wx.assistants.activity.AutoAddGroupFansActivity] */
    private void initData() {
        this.sexRadioGroup.setOnCheckedChangeListener(this);
        this.sexRadioButtonAll.setChecked(true);
        this.mList = new ArrayList<>();
        this.mList.addAll(Arrays.asList(getResources().getStringArray(2130903043)));
        this.tagSelectAdapter = new TagSelectAdapter(this, this.mList);
        this.flowViewGroup.setAdapter(this.tagSelectAdapter);
        this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                AutoAddGroupFansActivity.this.sayContent.setText((CharSequence) AutoAddGroupFansActivity.this.mList.get(i));
            }
        });
        this.mRemark = new ArrayList<>();
        this.mRemark.addAll(Arrays.asList(getResources().getStringArray(2130903048)));
        this.tagRemarkAdapter = new TagRemarkAdapter(this, this.mRemark);
        this.remarkViewGroup.setAdapter(this.tagRemarkAdapter);
        this.remarkViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                AutoAddGroupFansActivity.this.editTextAddName.setText((String) AutoAddGroupFansActivity.this.mRemark.get(i));
            }
        });
        this.singleSelectLabelLayout.setOnSelectSingleLabelListener(new SingleLabelSelectLayout.OnSelectSingleLabelListener() {
            public void selectLabel(LabelBean labelBean) {
                LogUtils.log("WS_BABY_selectLabel");
                if (labelBean == null) {
                    String unused = AutoAddGroupFansActivity.this.selectLabel = null;
                } else {
                    String unused2 = AutoAddGroupFansActivity.this.selectLabel = labelBean.getLabelName();
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

    /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddGroupFansActivity] */
    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("自动群聊加粉");
        this.startWx.setText("启动微信加粉");
        this.executeTimeSpaceLayout.setExecuteTimeTitle("加人间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = AutoAddGroupFansActivity.this.spaceTime = i;
            }
        });
        this.sayContent.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "group_add_friend_content", editable.toString());
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.filterNickName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "group_add_filter_nickname", editable.toString());
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.ffModelLayout.setDefaultTime(this.ffModelStartTime, this.ffModelEndTime);
        this.ffModelLayout.setOnFFModelListener(new FFModelLayout.OnFFModelListener() {
            public void ffMode(int i) {
                int unused = AutoAddGroupFansActivity.this.ffModel = i;
                if (AutoAddGroupFansActivity.this.ffModel == 0) {
                    AutoAddGroupFansActivity.this.executeTimeSpaceLayout.setVisibility(0);
                } else {
                    AutoAddGroupFansActivity.this.executeTimeSpaceLayout.setVisibility(8);
                }
            }

            public void ffModeTime(int i, int i2) {
                int unused = AutoAddGroupFansActivity.this.ffModelStartTime = i;
                int unused2 = AutoAddGroupFansActivity.this.ffModelEndTime = i2;
            }
        });
        this.customRadioSwitchLayout.setText("只加免验证的好友");
        this.customRadioSwitchLayout.setDefaultSelect(1);
        this.customRadioSwitchLayout.setOnModelSelectListener(new CustomRadioSwitchLayout.OnModelSelectListener() {
            public void mode(int i) {
                int unused = AutoAddGroupFansActivity.this.verifyCode = i;
                if (i == 1) {
                    AutoAddGroupFansActivity.this.sayLayout.setVisibility(0);
                    AutoAddGroupFansActivity.this.numberSettingLayout0.setVisibility(0);
                    int unused2 = AutoAddGroupFansActivity.this.executeNum = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
                    AutoAddGroupFansActivity.this.numberSettingLayout.setNum(AutoAddGroupFansActivity.this.executeNum, AutoAddGroupFansActivity.this.maxNum, "执行数量");
                    AutoAddGroupFansActivity.this.numberSettingLayout.setNumberGone(true);
                    int unused3 = AutoAddGroupFansActivity.this.sayHelloNum = 20;
                    AutoAddGroupFansActivity.this.numberSettingLayout0.setNum(AutoAddGroupFansActivity.this.sayHelloNum, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, "打招呼数量");
                    return;
                }
                AutoAddGroupFansActivity.this.sayLayout.setVisibility(8);
                AutoAddGroupFansActivity.this.numberSettingLayout0.setVisibility(8);
                int unused4 = AutoAddGroupFansActivity.this.executeNum = 100;
                AutoAddGroupFansActivity.this.numberSettingLayout.setNum(AutoAddGroupFansActivity.this.executeNum, AutoAddGroupFansActivity.this.maxNum, "执行数量");
                AutoAddGroupFansActivity.this.numberSettingLayout.setNumberGone(false);
                int unused5 = AutoAddGroupFansActivity.this.sayHelloNum = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
                AutoAddGroupFansActivity.this.numberSettingLayout0.setNum(AutoAddGroupFansActivity.this.sayHelloNum, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, "打招呼数量");
            }
        });
        this.remarkSwitchBtn.setChecked(false);
        this.remarkSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    boolean unused = AutoAddGroupFansActivity.this.isRemark = true;
                    AutoAddGroupFansActivity.this.remarksLayout.setVisibility(0);
                    return;
                }
                boolean unused2 = AutoAddGroupFansActivity.this.isRemark = false;
                AutoAddGroupFansActivity.this.remarksLayout.setVisibility(8);
            }
        });
        this.numberSettingLayout.setNum(this.executeNum, this.maxNum, "执行数量");
        this.startIndex = ((Integer) SPUtils.get(this, "group_inner_add_fans_num", 1)).intValue();
        this.numberSettingLayout.setStartPointIndex(this.startIndex, "执行起点");
        this.numberSettingLayout.setOnResultListener(new NumSettingLayout.OnResultListener() {
            public void result(int i, int i2) {
                int unused = AutoAddGroupFansActivity.this.executeNum = i;
                int unused2 = AutoAddGroupFansActivity.this.startIndex = i2;
                SPUtils.put(MyApplication.getConText(), "group_inner_add_fans_num", Integer.valueOf(AutoAddGroupFansActivity.this.startIndex));
            }
        });
        this.numberSettingLayout.setNumberGone(true);
        this.numberSettingLayout0.setNum(this.sayHelloNum, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, "打招呼数量");
        this.numberSettingLayout0.setOnResultListener(new NumSettingOnlyLayout.OnResultListener() {
            public void result(int i) {
                int unused = AutoAddGroupFansActivity.this.sayHelloNum = i;
            }
        });
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
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddGroupFansActivity] */
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
        int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "group_inner_add_fans_num", 1)).intValue();
        if (this.numberSettingLayout != null) {
            this.startIndex = intValue;
            this.numberSettingLayout.setStartPointIndex(this.startIndex, "执行起点");
        }
        String str = (String) SPUtils.get(MyApplication.getConText(), "group_add_friend_content", "");
        if (str != null && !"".equals(str)) {
            this.sayContent.setText(str);
        }
        String str2 = (String) SPUtils.get(MyApplication.getConText(), "group_add_filter_nickname", "");
        if (str2 != null && !"".equals(str2)) {
            this.filterNickName.setText(str2);
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddGroupFansActivity] */
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
                DialogUIUtils.dialogFunctionalSpecification(this, "自动群聊加粉", "1.加粉起点默认为1，从群聊的第1个好友开始加。\n\n2.建议每次添加好友40个左右。每次使用间隔三小时以上。\n\n3.如果您有多个微信号，每个微信号都可以使用群加好友。\n\n4.为了保证不出现微信加粉频繁,打招呼数量默认20，加人会自动停止，您的微信号质量好的话，也可以调整这个数量。\n\n5.为了保证不出现微信加粉频繁，且好友能收到好友验证请求，建议每次添加好友，间隔时间10秒（具体根据个人微信号的质量来定），或者切换其他微信号，继续使用群聊加好友的功能。\n\n");
                return;
            case 2131297425:
                if (validate()) {
                    startCheck("12", true, new BaseActivity.OnStartCheckListener() {
                        public void checkEnd() {
                            String str;
                            String obj = AutoAddGroupFansActivity.this.filterNickName.getText().toString();
                            LinkedHashSet linkedHashSet = new LinkedHashSet();
                            if (obj != null && !"".equals(obj)) {
                                if (obj.contains("#")) {
                                    String[] split = obj.split("#");
                                    for (String add : split) {
                                        linkedHashSet.add(add);
                                    }
                                } else {
                                    linkedHashSet.add(obj);
                                }
                            }
                            String trim = AutoAddGroupFansActivity.this.editTextAddName.getText().toString().trim();
                            if (trim == null || "".equals(trim)) {
                                str = "";
                            } else {
                                str = trim + ": ";
                            }
                            OperationParameterModel operationParameterModel = new OperationParameterModel();
                            operationParameterModel.setTaskNum("12");
                            operationParameterModel.setSayContent(AutoAddGroupFansActivity.this.sayContent.getText().toString());
                            operationParameterModel.setStartIndex(AutoAddGroupFansActivity.this.startIndex);
                            operationParameterModel.setMaxOperaNum(AutoAddGroupFansActivity.this.executeNum);
                            operationParameterModel.setMaxPraiseNum(AutoAddGroupFansActivity.this.sayHelloNum);
                            operationParameterModel.setSex(AutoAddGroupFansActivity.this.sex);
                            operationParameterModel.setFfModel(AutoAddGroupFansActivity.this.ffModel);
                            operationParameterModel.setVerifyCode(AutoAddGroupFansActivity.this.verifyCode);
                            operationParameterModel.setFfModelStartTime(AutoAddGroupFansActivity.this.ffModelStartTime);
                            operationParameterModel.setFfModelEndTime(AutoAddGroupFansActivity.this.ffModelEndTime);
                            operationParameterModel.setSpaceTime(AutoAddGroupFansActivity.this.spaceTime);
                            operationParameterModel.setRemarkPrefix(str);
                            operationParameterModel.setSingLabelStr(AutoAddGroupFansActivity.this.selectLabel);
                            operationParameterModel.setFilterNickNames(linkedHashSet);
                            MyApplication.instance.setOperationParameterModel(operationParameterModel);
                            AutoAddGroupFansActivity.this.startWX(operationParameterModel);
                        }
                    });
                    return;
                }
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "自动群聊加粉视频教程", QBangApis.VIDEO_GROUP_ADD_FANS);
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddGroupFansActivity] */
    public boolean validate() {
        if (this.isRemark) {
            String trim = this.editTextAddName.getText().toString().trim();
            if (trim == null || "".equals(trim)) {
                ToastUtils.showToast(this, "请选择备注标签");
                return false;
            }
        } else {
            this.editTextAddName.setText("");
        }
        return true;
    }
}
