package com.wx.assistants.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
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
import com.wx.assistants.view.NumSettingLayout;
import com.wx.assistants.view.TagCloudLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import java.util.Arrays;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AutoAddContacts3Activity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(2131296507)
    LinearLayout cleanEditText;
    /* access modifiers changed from: private */
    public int defaultNum = 50;
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
    public boolean isRemark = true;
    /* access modifiers changed from: private */
    public ArrayList<String> mList;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297078)
    NumSettingLayout numSettingOnlyLayout;
    @BindView(2131297232)
    RadioButton remarkNo;
    @BindView(2131297233)
    RadioGroup remarkRadioGroupId;
    @BindView(2131297236)
    RadioButton remarkYes;
    @BindView(2131297278)
    EditText sayContent;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    private TagSelectAdapter tagSelectAdapter;
    @BindView(2131297636)
    LinearLayout videoIntroduce;

    static {
        StubApp.interface11(6702);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContacts3Activity] */
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

    /* JADX WARNING: type inference failed for: r3v0, types: [android.widget.RadioGroup$OnCheckedChangeListener, android.content.Context, com.wx.assistants.activity.AutoAddContacts3Activity] */
    private void initData() {
        this.remarkRadioGroupId.setOnCheckedChangeListener(this);
        this.remarkYes.setChecked(true);
        this.mList = new ArrayList<>();
        this.mList.addAll(Arrays.asList(getResources().getStringArray(2130903043)));
        this.tagSelectAdapter = new TagSelectAdapter(this, this.mList);
        this.flowViewGroup.setAdapter(this.tagSelectAdapter);
        this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                AutoAddContacts3Activity.this.sayContent.setText((CharSequence) AutoAddContacts3Activity.this.mList.get(i));
            }
        });
        this.ffModelLayout.setDefaultTime(this.ffModelStartTime, this.ffModelEndTime);
        this.ffModelLayout.setOnFFModelListener(new FFModelLayout.OnFFModelListener() {
            public void ffMode(int i) {
                int unused = AutoAddContacts3Activity.this.ffModel = i;
                if (AutoAddContacts3Activity.this.ffModel == 0) {
                    AutoAddContacts3Activity.this.executeTimeSpaceLayout.setVisibility(0);
                } else {
                    AutoAddContacts3Activity.this.executeTimeSpaceLayout.setVisibility(8);
                }
            }

            public void ffModeTime(int i, int i2) {
                int unused = AutoAddContacts3Activity.this.ffModelStartTime = i;
                int unused2 = AutoAddContacts3Activity.this.ffModelEndTime = i2;
            }
        });
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("新朋友推荐");
        this.startWx.setText("启动微信加新朋友推荐");
        this.numSettingOnlyLayout.setNum(50, 50, "加人数量");
        this.numSettingOnlyLayout.setStartPointIndex(this.startIndex, "加人起点");
        this.numSettingOnlyLayout.setOnResultListener(new NumSettingLayout.OnResultListener() {
            public void result(int i, int i2) {
                int unused = AutoAddContacts3Activity.this.defaultNum = i;
                int unused2 = AutoAddContacts3Activity.this.startIndex = i2;
            }
        });
        this.executeTimeSpaceLayout.setExecuteTimeTitle("加人间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = AutoAddContacts3Activity.this.spaceTime = i;
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.startIndex = ((Integer) SPUtils.get(MyApplication.getConText(), "auto_add_contact_num", 1)).intValue();
        this.numSettingOnlyLayout.setStartPointIndex(this.startIndex, "加人起点");
        String str = (String) SPUtils.get(MyApplication.getConText(), "auto_add_contacts_text_3", "");
        if (str != null && !"".equals(str)) {
            this.sayContent.setText(str);
        }
        this.sayContent.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "auto_add_contacts_text_3", editable.toString());
                }
            }
        });
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContacts3Activity] */
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
                DialogUIUtils.dialogFunctionalSpecification(this, "新朋友推荐", "1.根据微信规则，使用新朋友推荐一次最多加50个好友。\n\n2.进入新的朋友页面,此功能会对带有 '添加' 按钮的通讯录联系人进行自动添加操作。\n\n3.如果有多个微信号，每个微信号都可以使用新朋友推荐加人。\n\n4.为了保证不出现微信加粉频繁，同一微信号建议每隔3小时使用一次加人功能。\n\n5.此功能为会员用户提供支持。");
                return;
            case 2131297425:
                startCheck("35", true, new BaseActivity.OnStartCheckListener() {
                    public void checkEnd() {
                        OperationParameterModel operationParameterModel = new OperationParameterModel();
                        operationParameterModel.setTaskNum("35");
                        operationParameterModel.setSayContent(AutoAddContacts3Activity.this.sayContent.getText().toString());
                        operationParameterModel.setStartIndex(AutoAddContacts3Activity.this.startIndex);
                        operationParameterModel.setMaxOperaNum(AutoAddContacts3Activity.this.defaultNum);
                        operationParameterModel.setFfModel(AutoAddContacts3Activity.this.ffModel);
                        operationParameterModel.setFfModelStartTime(AutoAddContacts3Activity.this.ffModelStartTime);
                        operationParameterModel.setFfModelEndTime(AutoAddContacts3Activity.this.ffModelEndTime);
                        operationParameterModel.setAutoRemarkContactsName(AutoAddContacts3Activity.this.isRemark);
                        operationParameterModel.setSpaceTime(AutoAddContacts3Activity.this.spaceTime);
                        MyApplication.instance.setOperationParameterModel(operationParameterModel);
                        AutoAddContacts3Activity.this.startWX(operationParameterModel);
                    }
                });
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "新朋友推荐视频教程", QBangApis.VIDEO_AUTO_ADD_ADDRESS_BOOK);
                return;
            default:
                return;
        }
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId == 2131297232) {
            this.isRemark = false;
        } else if (checkedRadioButtonId == 2131297236) {
            this.isRemark = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
