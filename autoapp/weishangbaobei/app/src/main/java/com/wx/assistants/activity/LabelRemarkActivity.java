package com.wx.assistants.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.stub.StubApp;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxTagEvent;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.LabelAutoBatchLayout;
import com.wx.assistants.view.LabelRemarkSelectLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class LabelRemarkActivity extends BaseActivity {
    @BindView(2131296647)
    ExecuteTimeSpaceLayout executeTimeSpaceLayout;
    @BindView(2131296717)
    LabelRemarkSelectLayout friendSendModeLayout;
    /* access modifiers changed from: private */
    public int groupNumber = SubsamplingScaleImageView.ORIENTATION_180;
    /* access modifiers changed from: private */
    public boolean isAutoGroup = false;
    /* access modifiers changed from: private */
    public String jumpLabelNames = "";
    /* access modifiers changed from: private */
    public LinkedHashSet jumpPersons = new LinkedHashSet();
    @BindView(2131296883)
    LabelAutoBatchLayout labelAutoBatchLayout;
    @BindView(2131296884)
    LinearLayout labelLayout;
    @BindView(2131296885)
    EditText labelName;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> labels = new LinkedHashSet<>();
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
    @BindView(2131297231)
    EditText remarkName;
    /* access modifiers changed from: private */
    public int sendCardType = 0;
    @BindView(2131297379)
    ImageView showImg;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6737);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.LabelRemarkActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.LabelRemarkActivity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        initData();
        try {
            this.groupNumber = ((Integer) SPUtils.get(this, "label_remark_group_num", Integer.valueOf(this.groupNumber))).intValue();
            this.isAutoGroup = ((Boolean) SPUtils.get(this, "label_remark_group_open", Boolean.valueOf(this.isAutoGroup))).booleanValue();
            this.labelAutoBatchLayout.setDefaultGroupNum(this.groupNumber, this.isAutoGroup);
        } catch (Exception unused) {
        }
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("智能分组");
        this.startWx.setText("启动微信开始分组");
        this.executeTimeSpaceLayout.setExecuteTimeTitle("群发间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = LabelRemarkActivity.this.spaceTime = i;
            }
        });
        this.friendSendModeLayout.setFriendSendTypeListener(new LabelRemarkSelectLayout.FriendSendTypeListener() {
            public void sendType(int i) {
                int unused = LabelRemarkActivity.this.sendCardType = i;
                if (LabelRemarkActivity.this.sendCardType == 3) {
                    LabelRemarkActivity.this.labelLayout.setVisibility(8);
                } else {
                    LabelRemarkActivity.this.labelLayout.setVisibility(0);
                }
            }

            public void sendStartIndex(int i) {
                int unused = LabelRemarkActivity.this.startIndex = i;
            }

            public void sendMembers(LinkedHashSet<String> linkedHashSet) {
                LinkedHashSet unused = LabelRemarkActivity.this.jumpPersons = linkedHashSet;
            }

            public void sendLabels(String str) {
                String unused = LabelRemarkActivity.this.jumpLabelNames = str;
            }
        });
        this.labelAutoBatchLayout.setDefaultGroupNum(this.groupNumber, this.isAutoGroup);
        this.labelAutoBatchLayout.setOnAutoBatchModelListener(new LabelAutoBatchLayout.OnAutoBatchModelListener() {
            /* JADX WARNING: type inference failed for: r3v1, types: [android.content.Context, com.wx.assistants.activity.LabelRemarkActivity] */
            public void resultGroupNum(int i) {
                int unused = LabelRemarkActivity.this.groupNumber = i;
                SPUtils.put(LabelRemarkActivity.this, "label_remark_group_num", Integer.valueOf(LabelRemarkActivity.this.groupNumber));
            }

            /* JADX WARNING: type inference failed for: r3v1, types: [android.content.Context, com.wx.assistants.activity.LabelRemarkActivity] */
            public void isOpen(boolean z) {
                boolean unused = LabelRemarkActivity.this.isAutoGroup = z;
                SPUtils.put(LabelRemarkActivity.this, "label_remark_group_open", Boolean.valueOf(LabelRemarkActivity.this.isAutoGroup));
            }
        });
    }

    public void initData() {
        try {
            int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "label_remark_num_all", 1)).intValue();
            int intValue2 = ((Integer) SPUtils.get(MyApplication.getConText(), "label_remark_num_un_label", 1)).intValue();
            int intValue3 = ((Integer) SPUtils.get(MyApplication.getConText(), "label_remark_num_un_remark", 1)).intValue();
            int intValue4 = ((Integer) SPUtils.get(MyApplication.getConText(), "label_remark_num_part", 1)).intValue();
            int intValue5 = ((Integer) SPUtils.get(MyApplication.getConText(), "label_remark_num_shield", 1)).intValue();
            this.friendSendModeLayout.initSendIndexStrAll("label_remark_num_all", intValue);
            this.friendSendModeLayout.initSendIndexStrUnLabel("label_remark_num_un_label", intValue2);
            this.friendSendModeLayout.initSendIndexStrUnRemark("label_remark_num_un_remark", intValue3);
            this.friendSendModeLayout.initSendIndexStrPart("label_remark_num_part", intValue4);
            this.friendSendModeLayout.initSendIndexStrShield("label_remark_num_shield", intValue5);
            this.friendSendModeLayout.initSendLabelStrAll("label_remark_label_all", (String) SPUtils.get(MyApplication.getConText(), "label_remark_label_all", ""));
            this.friendSendModeLayout.initSendLabelStrUnLabel("label_remark_label_un_label", (String) SPUtils.get(MyApplication.getConText(), "label_remark_label_un_label", ""));
            this.friendSendModeLayout.initSendLabelStrUnRemark("label_remark_label_un_remark", (String) SPUtils.get(MyApplication.getConText(), "label_remark_label_un_remark", ""));
            this.friendSendModeLayout.initSendLabelStrPart("label_remark_label_part", (String) SPUtils.get(MyApplication.getConText(), "label_remark_label_part", ""));
            this.friendSendModeLayout.initSendLabelStrShield("label_remark_label_shield", (String) SPUtils.get(MyApplication.getConText(), "label_remark_label_shield", ""));
            this.friendSendModeLayout.initSendLabelMemberPart("label_remark_member_part", (String) SPUtils.get(MyApplication.getConText(), "label_remark_label_member_part", ""));
            this.friendSendModeLayout.initSendLabelMemberShield("label_remark_label_member_shield", (String) SPUtils.get(MyApplication.getConText(), "label_remark_label_member_shield", ""));
            String str = (String) SPUtils.get(MyApplication.getConText(), "label_remark_remark", "");
            if (str != null && !"".equals(str)) {
                this.remarkName.setText(str);
            }
            String str2 = (String) SPUtils.get(MyApplication.getConText(), "label_remark_label", "");
            if (str2 != null && !"".equals(str2)) {
                this.labelName.setText(str2);
            }
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_37");
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onEventMainThread(WxTagEvent wxTagEvent) {
        ArrayList<String> selectedTagList = wxTagEvent.getSelectedTagList();
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        if (selectedTagList == null || selectedTagList.size() <= 0) {
            this.jumpLabelNames = "";
            this.friendSendModeLayout.setSendStr("");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < selectedTagList.size(); i++) {
                sb.append(selectedTagList.get(i) + ";");
                linkedHashSet.add(selectedTagList.get(i));
            }
            this.labels = linkedHashSet;
            String sb2 = sb.toString();
            this.jumpLabelNames = sb2;
            this.friendSendModeLayout.setSendStr(sb2);
        }
        if (wxTagEvent.getSelectedPeopleList() != null) {
            this.jumpPersons = new LinkedHashSet();
            this.jumpPersons.addAll(wxTagEvent.getSelectedPeopleList());
        } else {
            this.jumpPersons = new LinkedHashSet();
        }
        this.friendSendModeLayout.setSendMember(this.jumpPersons);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.LabelRemarkActivity] */
    @OnClick({2131297049, 2131297425, 2131297636, 2131297052})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.该功能可将大量的好友进行标签分组，还可以批量给好友昵称添加备注，方便对大量的好友进行管理，可精细化推广广告。\n2.添加备注：给指定的好友昵称前自动添加备注。\n3.添加标签：给指定的好友，自动添加标签。\n4.选择好友可以选择全部、部分、屏蔽部分以外的所有好友、没有添加过标签的好友、没有备注过的好友。");
        } else if (id == 2131297425) {
            final String obj = this.labelName.getText().toString();
            final String obj2 = this.remarkName.getText().toString();
            if (this.sendCardType == 3 && (obj2 == null || "".equals(obj2))) {
                ToastUtils.showToast(this, "请设置要添加的备注");
            } else if ((obj2 == null || "".equals(obj2)) && (obj == null || "".equals(obj))) {
                ToastUtils.showToast(this, "请设置要添加的备注或标签");
            } else {
                if (this.jumpLabelNames == null || this.jumpLabelNames.length() == 0) {
                    if (this.sendCardType == 3) {
                        ToastUtils.showToast(this, "请设置部分好友标签");
                        return;
                    } else if (this.sendCardType == 4) {
                        ToastUtils.showToast(this, "请设置您要屏蔽的标签");
                        return;
                    }
                }
                startCheck("56", true, new BaseActivity.OnStartCheckListener() {
                    public void checkEnd() {
                        OperationParameterModel operationParameterModel = new OperationParameterModel();
                        operationParameterModel.setTaskNum("56");
                        operationParameterModel.setSendCardType(LabelRemarkActivity.this.sendCardType);
                        operationParameterModel.setStartIndex(LabelRemarkActivity.this.startIndex);
                        operationParameterModel.setTagNamesStr(LabelRemarkActivity.this.jumpLabelNames);
                        operationParameterModel.setTagListNames(LabelRemarkActivity.this.labels);
                        operationParameterModel.setSpaceTime(LabelRemarkActivity.this.spaceTime);
                        operationParameterModel.setIsDND(0);
                        operationParameterModel.setRemarkPrefix(obj2);
                        operationParameterModel.setSingLabelStr(obj);
                        operationParameterModel.setMaxOperaNum(5000);
                        operationParameterModel.setAutoGroup(LabelRemarkActivity.this.isAutoGroup);
                        operationParameterModel.setGroupNumber(LabelRemarkActivity.this.groupNumber);
                        if (obj2 != null && !"".equals(obj2) && obj != null && !"".equals(obj)) {
                            operationParameterModel.setIntimateMode(2);
                        } else if (obj2 == null || "".equals(obj2)) {
                            operationParameterModel.setIntimateMode(1);
                        } else {
                            operationParameterModel.setIntimateMode(0);
                        }
                        MyApplication.instance.setOperationParameterModel(operationParameterModel);
                        LabelRemarkActivity.this.startWX(operationParameterModel);
                    }
                });
            }
        } else if (id == 2131297636) {
            WebViewActivity.startActivity(this, "智能分组视频教程", QBangApis.VIDEO_SPLIT_GROUPS);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
