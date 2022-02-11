package com.wx.assistants.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
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
import com.wx.assistants.Enity.ContactBean;
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
import com.wx.assistants.view.NumSettingLayout;
import com.wx.assistants.view.RemarkFriendLayout;
import com.wx.assistants.view.SingleLabelSelectLayout;
import com.wx.assistants.view.TagCloudLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class AutoAddMyCustomerActivity extends BaseActivity {
    @BindView(2131296506)
    LinearLayout cleanCustomer;
    @BindView(2131296507)
    LinearLayout cleanEditText;
    @BindView(2131296573)
    EditText customerEdit;
    /* access modifiers changed from: private */
    public int executeNum = 50;
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
    @BindView(2131297082)
    NumSettingLayout numberSettingLayout;
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
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    private TagSelectAdapter tagSelectAdapter;
    @BindView(2131297636)
    LinearLayout videoIntroduce;

    static {
        StubApp.interface11(6705);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddMyCustomerActivity] */
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddMyCustomerActivity] */
    private void initData() {
        this.mList = new ArrayList<>();
        this.mList.addAll(Arrays.asList(getResources().getStringArray(2130903043)));
        this.tagSelectAdapter = new TagSelectAdapter(this, this.mList);
        this.flowViewGroup.setAdapter(this.tagSelectAdapter);
        this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                AutoAddMyCustomerActivity.this.sayContent.setText((CharSequence) AutoAddMyCustomerActivity.this.mList.get(i));
            }
        });
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("精准客源加人");
        this.startWx.setText("启动微信开始加人");
        this.executeTimeSpaceLayout.setExecuteTimeTitle("加人间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = AutoAddMyCustomerActivity.this.spaceTime = i;
            }
        });
        this.ffModelLayout.setDefaultTime(this.ffModelStartTime, this.ffModelEndTime);
        this.ffModelLayout.setOnFFModelListener(new FFModelLayout.OnFFModelListener() {
            public void ffMode(int i) {
                int unused = AutoAddMyCustomerActivity.this.ffModel = i;
                if (AutoAddMyCustomerActivity.this.ffModel == 0) {
                    AutoAddMyCustomerActivity.this.executeTimeSpaceLayout.setVisibility(8);
                } else {
                    AutoAddMyCustomerActivity.this.executeTimeSpaceLayout.setVisibility(8);
                }
            }

            public void ffModeTime(int i, int i2) {
                int unused = AutoAddMyCustomerActivity.this.ffModelStartTime = i;
                int unused2 = AutoAddMyCustomerActivity.this.ffModelEndTime = i2;
            }
        });
        this.numberSettingLayout.setNum(this.executeNum, this.maxNum, "加人数量");
        this.numberSettingLayout.setOnResultListener(new NumSettingLayout.OnResultListener() {
            public void result(int i, int i2) {
                int unused = AutoAddMyCustomerActivity.this.executeNum = i;
                int unused2 = AutoAddMyCustomerActivity.this.startIndex = i2;
                SPUtils.put(MyApplication.getConText(), "auto_add_customer_num", Integer.valueOf(AutoAddMyCustomerActivity.this.startIndex));
            }
        });
        this.executeRemarkLayout.setOnRemarkListener(new RemarkFriendLayout.OnRemarkListener() {
            public void remark(boolean z, String str) {
                boolean unused = AutoAddMyCustomerActivity.this.isRemark = z;
                String unused2 = AutoAddMyCustomerActivity.this.remarkStr = str;
            }
        });
        this.singleSelectLabelLayout.setOnSelectSingleLabelListener(new SingleLabelSelectLayout.OnSelectSingleLabelListener() {
            public void selectLabel(LabelBean labelBean) {
                LogUtils.log("WS_BABY_selectLabel");
                if (labelBean == null) {
                    String unused = AutoAddMyCustomerActivity.this.selectLabel = null;
                } else {
                    String unused2 = AutoAddMyCustomerActivity.this.selectLabel = labelBean.getLabelName();
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

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.AutoAddMyCustomerActivity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        String str = (String) SPUtils.get(MyApplication.getConText(), "auto_add_customer_text_0", "");
        if (str != null && !"".equals(str)) {
            this.customerEdit.setText(str);
        }
        this.customerEdit.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "auto_add_customer_text_0", editable.toString());
                }
            }
        });
        String str2 = (String) SPUtils.get(MyApplication.getConText(), "auto_add_customer_text_1", "");
        if (str2 != null && !"".equals(str2)) {
            this.sayContent.setText(str2);
        }
        this.sayContent.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "auto_add_customer_text_1", editable.toString());
                }
            }
        });
        this.startIndex = ((Integer) SPUtils.get(this, "auto_add_customer_num", 1)).intValue();
        this.numberSettingLayout.setStartPointIndex(this.startIndex, "加人起点");
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddMyCustomerActivity] */
    @OnClick({2131296507, 2131296506, 2131297049, 2131297425, 2131297636, 2131297052})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296506:
                this.customerEdit.setText("");
                return;
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
                final ArrayList<ContactBean> changeData = changeData();
                if (changeData == null) {
                    ToastUtils.showToast(this, "请输入您的客源信息！");
                    return;
                } else if (this.startIndex > changeData.size()) {
                    ToastUtils.showToast(this, "设置的起点过高！");
                    return;
                } else {
                    if (this.startIndex > 1) {
                        for (int i = 0; i < this.startIndex - 1; i++) {
                            changeData.remove(0);
                        }
                    }
                    startCheck("67", true, new BaseActivity.OnStartCheckListener() {
                        /* JADX WARNING: type inference failed for: r0v5, types: [android.content.Context, com.wx.assistants.activity.AutoAddMyCustomerActivity] */
                        public void checkEnd() {
                            if (!AutoAddMyCustomerActivity.this.isRemark || (AutoAddMyCustomerActivity.this.remarkStr != null && !"".equals(AutoAddMyCustomerActivity.this.remarkStr))) {
                                OperationParameterModel operationParameterModel = new OperationParameterModel();
                                operationParameterModel.setTaskNum("67");
                                operationParameterModel.setSayContent(AutoAddMyCustomerActivity.this.sayContent.getText().toString());
                                operationParameterModel.setStartIndex(AutoAddMyCustomerActivity.this.startIndex);
                                operationParameterModel.setMaxOperaNum(AutoAddMyCustomerActivity.this.executeNum);
                                operationParameterModel.setFfModel(AutoAddMyCustomerActivity.this.ffModel);
                                operationParameterModel.setFfModelStartTime(AutoAddMyCustomerActivity.this.ffModelStartTime);
                                operationParameterModel.setFfModelEndTime(AutoAddMyCustomerActivity.this.ffModelEndTime);
                                operationParameterModel.setAutoRemarkContactsName(false);
                                operationParameterModel.setSpaceTime(AutoAddMyCustomerActivity.this.spaceTime);
                                operationParameterModel.setAddressBookPhones(changeData);
                                if (AutoAddMyCustomerActivity.this.isRemark) {
                                    operationParameterModel.setRemarkPrefix(AutoAddMyCustomerActivity.this.remarkStr + "_");
                                } else {
                                    operationParameterModel.setRemarkPrefix("");
                                }
                                operationParameterModel.setSingLabelStr(AutoAddMyCustomerActivity.this.selectLabel);
                                MyApplication.instance.setOperationParameterModel(operationParameterModel);
                                AutoAddMyCustomerActivity.this.startWX(operationParameterModel);
                                return;
                            }
                            ToastUtils.showToast(AutoAddMyCustomerActivity.this, "请设置备注前缀");
                        }
                    });
                    return;
                }
            case 2131297636:
                WebViewActivity.startActivity(this, "精准客源加人视频教程", QBangApis.AUTO_ADD_CUSTOMER);
                return;
            default:
                return;
        }
    }

    public ArrayList<ContactBean> changeData() {
        String[] split;
        String obj = this.customerEdit.getText().toString();
        if (obj == null || "".equals(obj) || (split = obj.split("\\n")) == null || split.length == 0) {
            return null;
        }
        ArrayList<ContactBean> arrayList = new ArrayList<>();
        for (String str : split) {
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(new ContactBean(str, false));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
