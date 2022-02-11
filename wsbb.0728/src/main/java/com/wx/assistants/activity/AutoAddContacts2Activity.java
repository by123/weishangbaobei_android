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
import com.wx.assistants.Enity.ContactBean;
import com.wx.assistants.adapter.TagSelectAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FFModelLayout;
import com.wx.assistants.view.TagCloudLayout;
import com.wx.assistants.webview.WebViewActivity;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Arrays;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AutoAddContacts2Activity extends BaseActivity {
    @BindView(2131296414)
    Switch breakSwitchBtn;
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
    public int isBreak = 0;
    @BindView(2131296829)
    TextView isOpenBreakText;
    /* access modifiers changed from: private */
    public boolean isRemark = true;
    /* access modifiers changed from: private */
    public ArrayList<String> mList;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297234)
    Switch remarkSwitchBtn;
    @BindView(2131297278)
    EditText sayContent;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    @BindView(2131297425)
    Button startWx;
    private TagSelectAdapter tagSelectAdapter;
    @BindView(2131297636)
    LinearLayout videoIntroduce;

    static {
        StubApp.interface11(6701);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContacts2Activity] */
    private void initData() {
        this.mList = new ArrayList<>();
        this.mList.addAll(Arrays.asList(getResources().getStringArray(2130903043)));
        this.tagSelectAdapter = new TagSelectAdapter(this, this.mList);
        this.flowViewGroup.setAdapter(this.tagSelectAdapter);
        this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                AutoAddContacts2Activity.this.sayContent.setText((CharSequence) AutoAddContacts2Activity.this.mList.get(i));
            }
        });
        this.remarkSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                boolean unused = AutoAddContacts2Activity.this.isRemark = z;
            }
        });
        this.breakSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    int unused = AutoAddContacts2Activity.this.isBreak = 1;
                } else {
                    int unused2 = AutoAddContacts2Activity.this.isBreak = 0;
                }
            }
        });
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("自选添加手机联系人");
        this.startWx.setText("启动微信加手机联系人");
        this.executeTimeSpaceLayout.setExecuteTimeTitle("加人间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = AutoAddContacts2Activity.this.spaceTime = i;
            }
        });
        this.ffModelLayout.setDefaultTime(this.ffModelStartTime, this.ffModelEndTime);
        this.ffModelLayout.setOnFFModelListener(new FFModelLayout.OnFFModelListener() {
            public void ffMode(int i) {
                int unused = AutoAddContacts2Activity.this.ffModel = i;
                if (AutoAddContacts2Activity.this.ffModel == 0) {
                    AutoAddContacts2Activity.this.executeTimeSpaceLayout.setVisibility(0);
                } else {
                    AutoAddContacts2Activity.this.executeTimeSpaceLayout.setVisibility(8);
                }
            }

            public void ffModeTime(int i, int i2) {
                int unused = AutoAddContacts2Activity.this.ffModelStartTime = i;
                int unused2 = AutoAddContacts2Activity.this.ffModelEndTime = i2;
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContacts2Activity] */
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
        String str = (String) SPUtils.get(MyApplication.getConText(), "auto_add_contacts_text_2", "");
        if (str != null && !"".equals(str)) {
            this.sayContent.setText(str);
        }
        this.sayContent.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "auto_add_contacts_text_2", editable.toString());
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContacts2Activity] */
    @OnClick({2131297049, 2131297425, 2131297052, 2131297636})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.根据微信规则，使用手机联系人一次最多加20个好友。\n\n2.使用手机联系人加粉儿，不耽误其他加粉儿功能。\n\n3.如果有多个微信号，每个微信号都可以使用手机联系人加粉。\n\n4.为了保证不出现微信加粉频繁，同一微信号建议每隔3小时使用一次加粉功能。\n\n5.此功能为会员用户提供支持。");
        } else if (id != 2131297425) {
            if (id == 2131297636) {
                WebViewActivity.startActivity(this, "自选加手机联系人视频教程", QBangApis.VIDEO_MANUAL_ADD_CONTACT);
            }
        } else if (validate()) {
            startCheck("26", true, new BaseActivity.OnStartCheckListener() {
                public void checkEnd() {
                    OperationParameterModel operationParameterModel = MyApplication.instance.getOperationParameterModel();
                    operationParameterModel.setTaskNum("26");
                    operationParameterModel.setSayContent(AutoAddContacts2Activity.this.sayContent.getText().toString());
                    operationParameterModel.setStartIndex(1);
                    operationParameterModel.setMaxOperaNum(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                    operationParameterModel.setBreakAdd(AutoAddContacts2Activity.this.isBreak);
                    operationParameterModel.setFfModel(AutoAddContacts2Activity.this.ffModel);
                    operationParameterModel.setFfModelStartTime(AutoAddContacts2Activity.this.ffModelStartTime);
                    operationParameterModel.setFfModelEndTime(AutoAddContacts2Activity.this.ffModelEndTime);
                    operationParameterModel.setAutoRemarkContactsName(AutoAddContacts2Activity.this.isRemark);
                    operationParameterModel.setSpaceTime(AutoAddContacts2Activity.this.spaceTime);
                    MyApplication.instance.setOperationParameterModel(operationParameterModel);
                    AutoAddContacts2Activity.this.startWX(operationParameterModel);
                }
            });
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContacts2Activity] */
    public boolean validate() {
        ArrayList<ContactBean> addressBookPhones = MyApplication.instance.getOperationParameterModel().getAddressBookPhones();
        if (addressBookPhones != null && addressBookPhones.size() > 0) {
            return true;
        }
        ToastUtils.showToast(this, "请返回，先选择要添加的通讯录");
        return false;
    }
}
