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
import com.wx.assistants.bean.LabelBean;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.service_utils.Gender;
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

public class AutoAddNearbyPeoplesActivity extends BaseActivity {
    @BindView(2131296507)
    LinearLayout cleanEditText;
    /* access modifiers changed from: private */
    public int executeNum = 100;
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
    private int maxNum = 200;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
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
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6706);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddNearbyPeoplesActivity] */
    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("自动加附近人");
        this.startWx.setText("启动微信加附近人");
        final ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(getResources().getStringArray(2130903043)));
        this.flowViewGroup.setAdapter(new TagSelectAdapter(this, arrayList));
        this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                AutoAddNearbyPeoplesActivity.this.sayContent.setText((CharSequence) arrayList.get(i));
            }
        });
        this.executeTimeSpaceLayout.setExecuteTimeTitle("加人间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = AutoAddNearbyPeoplesActivity.this.spaceTime = i;
            }
        });
        this.executeRemarkLayout.addData(0, "附近人");
        this.executeRemarkLayout.setOnRemarkListener(new RemarkFriendLayout.OnRemarkListener() {
            public void remark(boolean z, String str) {
                boolean unused = AutoAddNearbyPeoplesActivity.this.isRemark = z;
                String unused2 = AutoAddNearbyPeoplesActivity.this.remarkStr = str;
            }
        });
        this.singleSelectLabelLayout.setOnSelectSingleLabelListener(new SingleLabelSelectLayout.OnSelectSingleLabelListener() {
            public void selectLabel(LabelBean labelBean) {
                LogUtils.log("WS_BABY_selectLabel");
                if (labelBean == null) {
                    String unused = AutoAddNearbyPeoplesActivity.this.selectLabel = null;
                } else {
                    String unused2 = AutoAddNearbyPeoplesActivity.this.selectLabel = labelBean.getLabelName();
                }
            }

            public void selectedPeopleList(LinkedHashSet<String> linkedHashSet) {
                LogUtils.log("WS_BABY_selectedPeopleList" + linkedHashSet);
            }

            public void selectedPeopleString(String str) {
                LogUtils.log("WS_BABY_selectedPeopleString" + str);
            }
        });
        this.ffModelLayout.setDefaultTime(this.ffModelStartTime, this.ffModelEndTime);
        this.ffModelLayout.setOnFFModelListener(new FFModelLayout.OnFFModelListener() {
            public void ffMode(int i) {
                int unused = AutoAddNearbyPeoplesActivity.this.ffModel = i;
                if (AutoAddNearbyPeoplesActivity.this.ffModel == 0) {
                    AutoAddNearbyPeoplesActivity.this.executeTimeSpaceLayout.setVisibility(0);
                } else {
                    AutoAddNearbyPeoplesActivity.this.executeTimeSpaceLayout.setVisibility(8);
                }
            }

            public void ffModeTime(int i, int i2) {
                int unused = AutoAddNearbyPeoplesActivity.this.ffModelStartTime = i;
                int unused2 = AutoAddNearbyPeoplesActivity.this.ffModelEndTime = i2;
            }
        });
        this.numberSettingLayout.setNum(this.executeNum, this.maxNum, "加人数量");
        this.startIndex = ((Integer) SPUtils.get(this, "auto_add_nearly_num", 1)).intValue();
        this.numberSettingLayout.setStartPointIndex(this.startIndex, "加人起点");
        this.numberSettingLayout.setOnResultListener(new NumSettingLayout.OnResultListener() {
            public void result(int i, int i2) {
                int unused = AutoAddNearbyPeoplesActivity.this.executeNum = i;
                int unused2 = AutoAddNearbyPeoplesActivity.this.startIndex = i2;
                SPUtils.put(MyApplication.getConText(), "auto_add_nearly_num", Integer.valueOf(AutoAddNearbyPeoplesActivity.this.startIndex));
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddNearbyPeoplesActivity] */
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
        String str = (String) SPUtils.get(MyApplication.getConText(), "auto_add_nearby_text", "");
        if (str != null && !"".equals(str)) {
            this.sayContent.setText(str);
        }
        this.sayContent.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "auto_add_nearby_text", editable.toString());
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddNearbyPeoplesActivity] */
    @OnClick({2131296507, 2131297425, 2131297049, 2131297278, 2131296681, 2131297359, 2131297360, 2131297361, 2131297362, 2131297636, 2131297052})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296507:
                this.sayContent.setText("");
                return;
            case 2131297049:
                back();
                return;
            case 2131297052:
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.根据微信规则，加附件的人，一次最多加40个好友\n\n2.使用自动加附近人，不耽误其他加粉功能\n\n3.如果有多个微信号，都可登陆微信使用自动加附近的人\n\n4.为了保证不出现微商加粉频繁，同一微信号3小时内使用加附近人，最多40个，建议一天不超过两次；若要再次使用加附近人，需要间隔三个小时，或者切换其他微信号，继续使用加附近人\n\n");
                return;
            case 2131297359:
                this.sex = Gender.ALL;
                return;
            case 2131297360:
                this.sex = Gender.MAN;
                return;
            case 2131297361:
                this.sex = Gender.WOMEN;
                return;
            case 2131297425:
                if (!this.isRemark || (this.remarkStr != null && !"".equals(this.remarkStr))) {
                    startCheck("2", true, new BaseActivity.OnStartCheckListener() {
                        public void checkEnd() {
                            OperationParameterModel operationParameterModel = new OperationParameterModel();
                            operationParameterModel.setTaskNum("2");
                            operationParameterModel.setSex(AutoAddNearbyPeoplesActivity.this.sex);
                            operationParameterModel.setDistance(AutoAddNearbyPeoplesActivity.this.startIndex);
                            operationParameterModel.setMaxOperaNum(AutoAddNearbyPeoplesActivity.this.executeNum);
                            operationParameterModel.setFfModel(AutoAddNearbyPeoplesActivity.this.ffModel);
                            operationParameterModel.setFfModelStartTime(AutoAddNearbyPeoplesActivity.this.ffModelStartTime);
                            operationParameterModel.setFfModelEndTime(AutoAddNearbyPeoplesActivity.this.ffModelEndTime);
                            operationParameterModel.setSayContent(AutoAddNearbyPeoplesActivity.this.sayContent.getText().toString());
                            operationParameterModel.setSpaceTime(AutoAddNearbyPeoplesActivity.this.spaceTime);
                            if (AutoAddNearbyPeoplesActivity.this.isRemark) {
                                operationParameterModel.setRemarkPrefix(AutoAddNearbyPeoplesActivity.this.remarkStr + "_");
                            } else {
                                operationParameterModel.setRemarkPrefix("");
                            }
                            operationParameterModel.setSingLabelStr(AutoAddNearbyPeoplesActivity.this.selectLabel);
                            MyApplication.instance.setOperationParameterModel(operationParameterModel);
                            AutoAddNearbyPeoplesActivity.this.startWX(operationParameterModel);
                        }
                    });
                    return;
                } else {
                    ToastUtils.showToast(this, "请设置备注前缀");
                    return;
                }
            case 2131297636:
                WebViewActivity.startActivity(this, "自动加附近人视频讲解", QBangApis.VIDEO_NEARBY_PEOPLES);
                return;
            default:
                return;
        }
    }
}
