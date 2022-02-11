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

public class AutoAddContacts1Activity extends BaseActivity {
    @BindView(2131296507)
    LinearLayout cleanEditText;
    /* access modifiers changed from: private */
    public int executeNum = 50;
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
    private int maxNum = 50;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297082)
    NumSettingLayout numberSettingLayout;
    @BindView(2131297278)
    EditText sayContent;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297447)
    Switch switchButton;
    private TagSelectAdapter tagSelectAdapter;
    @BindView(2131297636)
    LinearLayout videoIntroduce;

    static {
        StubApp.interface11(6700);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContacts1Activity] */
    private void initData() {
        this.mList = new ArrayList<>();
        this.mList.addAll(Arrays.asList(getResources().getStringArray(2130903043)));
        this.tagSelectAdapter = new TagSelectAdapter(this, this.mList);
        this.flowViewGroup.setAdapter(this.tagSelectAdapter);
        this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                AutoAddContacts1Activity.this.sayContent.setText((CharSequence) AutoAddContacts1Activity.this.mList.get(i));
            }
        });
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("自动添加手机联系人");
        this.executeTimeSpaceLayout.setExecuteTimeTitle("加人间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = AutoAddContacts1Activity.this.spaceTime = i;
            }
        });
        this.ffModelLayout.setDefaultTime(this.ffModelStartTime, this.ffModelEndTime);
        this.ffModelLayout.setOnFFModelListener(new FFModelLayout.OnFFModelListener() {
            public void ffMode(int i) {
                int unused = AutoAddContacts1Activity.this.ffModel = i;
                if (AutoAddContacts1Activity.this.ffModel == 0) {
                    AutoAddContacts1Activity.this.executeTimeSpaceLayout.setVisibility(0);
                } else {
                    AutoAddContacts1Activity.this.executeTimeSpaceLayout.setVisibility(8);
                }
            }

            public void ffModeTime(int i, int i2) {
                int unused = AutoAddContacts1Activity.this.ffModelStartTime = i;
                int unused2 = AutoAddContacts1Activity.this.ffModelEndTime = i2;
            }
        });
        this.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    boolean unused = AutoAddContacts1Activity.this.isRemark = true;
                } else {
                    boolean unused2 = AutoAddContacts1Activity.this.isRemark = false;
                }
            }
        });
        this.numberSettingLayout.setNum(this.executeNum, this.maxNum, "加人数量");
        this.numberSettingLayout.setOnResultListener(new NumSettingLayout.OnResultListener() {
            public void result(int i, int i2) {
                int unused = AutoAddContacts1Activity.this.executeNum = i;
                int unused2 = AutoAddContacts1Activity.this.startIndex = i2;
                SPUtils.put(MyApplication.getConText(), "auto_add_contact_num", Integer.valueOf(AutoAddContacts1Activity.this.startIndex));
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContacts1Activity] */
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

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.AutoAddContacts1Activity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        String str = (String) SPUtils.get(MyApplication.getConText(), "auto_add_contacts_text_1", "");
        if (str != null && !"".equals(str)) {
            this.sayContent.setText(str);
        }
        this.sayContent.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "auto_add_contacts_text_1", editable.toString());
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.startIndex = ((Integer) SPUtils.get(this, "auto_add_contact_num", 1)).intValue();
        this.numberSettingLayout.setStartPointIndex(this.startIndex, "加人起点");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContacts1Activity] */
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
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.根据微信规则，使用手机联系人一次最多加50个好友。\n2.使用手机联系人加粉儿，不耽误其他加粉儿功能。\n\n3.如果有多个微信号，每个微信号都可以使用手机联系人加粉。\n\n4.为了保证不出现微信加粉频繁，同一微信号建议每隔3小时使用一次加粉功能。\n\n5.此功能为会员用户提供支持。\n");
                return;
            case 2131297425:
                startCheck("16", true, new BaseActivity.OnStartCheckListener() {
                    public void checkEnd() {
                        OperationParameterModel operationParameterModel = new OperationParameterModel();
                        operationParameterModel.setTaskNum("16");
                        operationParameterModel.setSayContent(AutoAddContacts1Activity.this.sayContent.getText().toString());
                        operationParameterModel.setStartIndex(AutoAddContacts1Activity.this.startIndex);
                        operationParameterModel.setMaxOperaNum(AutoAddContacts1Activity.this.executeNum);
                        operationParameterModel.setFfModel(AutoAddContacts1Activity.this.ffModel);
                        operationParameterModel.setFfModelStartTime(AutoAddContacts1Activity.this.ffModelStartTime);
                        operationParameterModel.setFfModelEndTime(AutoAddContacts1Activity.this.ffModelEndTime);
                        operationParameterModel.setAutoRemarkContactsName(AutoAddContacts1Activity.this.isRemark);
                        operationParameterModel.setSpaceTime(AutoAddContacts1Activity.this.spaceTime);
                        MyApplication.instance.setOperationParameterModel(operationParameterModel);
                        AutoAddContacts1Activity.this.startWX(operationParameterModel);
                    }
                });
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "自动加手机联系人视频教程", QBangApis.VIDEO_AUTO_ADD_CONTACT);
                return;
            default:
                return;
        }
    }
}
