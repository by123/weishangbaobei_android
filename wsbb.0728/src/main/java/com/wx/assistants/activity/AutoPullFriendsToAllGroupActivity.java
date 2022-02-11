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
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxGroupEvent;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.CirculateModelLayout;
import com.wx.assistants.view.CirculateNumLayout;
import com.wx.assistants.view.CustomRadioLayout;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.GroupSendModeLayoutH2;
import com.wx.assistants.webview.WebViewActivity;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AutoPullFriendsToAllGroupActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public int circulateMode = 0;
    @BindView(2131296492)
    CirculateModelLayout circulateModeLayout;
    @BindView(2131296493)
    CirculateNumLayout circulateNum;
    /* access modifiers changed from: private */
    public int circulateSize = 1;
    @BindView(2131296613)
    EditText editCardNickName;
    @BindView(2131296616)
    EditText editLeavingMessage;
    @BindView(2131296647)
    ExecuteTimeSpaceLayout executeTimeSpaceLayout;
    /* access modifiers changed from: private */
    public String groupNames = "";
    @BindView(2131296736)
    GroupSendModeLayoutH2 groupSendModeLayout;
    private int minSpaceTime = 5;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297054)
    TextView navTitle;
    /* access modifiers changed from: private */
    public int sendCardType = 0;
    /* access modifiers changed from: private */
    public int sendGroupMethod = 0;
    @BindView(2131297334)
    CustomRadioLayout sendGroupMethodLayout;
    /* access modifiers changed from: private */
    public int spaceTime = 5;
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6710);
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("换群专用");
        this.startWx.setText("启动微信开始拉人进群");
        this.executeTimeSpaceLayout.setExecuteTimeTitle("拉人间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = AutoPullFriendsToAllGroupActivity.this.spaceTime = i;
            }
        });
        this.executeTimeSpaceLayout.setMinTime(this.minSpaceTime);
        this.editLeavingMessage.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_say_content", editable.toString());
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.editCardNickName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_name", editable.toString());
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.circulateNum.setCirculateNumListener(new CirculateNumLayout.CirculateNumListener() {
            public void circulateNum(int i) {
                int unused = AutoPullFriendsToAllGroupActivity.this.circulateSize = i;
            }
        });
        this.circulateModeLayout.setCirculateModelListener(new CirculateModelLayout.CirculateModelListener() {
            public void circulateMode(int i) {
                int unused = AutoPullFriendsToAllGroupActivity.this.circulateMode = i;
            }
        });
        this.groupSendModeLayout.setGroupSendTypeListener(new GroupSendModeLayoutH2.GroupSendTypeListener() {
            public void sendMembers(String str) {
                String unused = AutoPullFriendsToAllGroupActivity.this.groupNames = str;
            }

            public void sendStartIndex(int i) {
                int unused = AutoPullFriendsToAllGroupActivity.this.startIndex = i;
            }

            public void sendType(int i) {
                int unused = AutoPullFriendsToAllGroupActivity.this.sendCardType = i;
            }
        });
        this.sendGroupMethodLayout.setText("找群方式", "翻页找", 60, "搜索找", 60);
        this.sendGroupMethodLayout.setDefaultSelect(0);
        this.sendGroupMethodLayout.setOnModelSelectListener(new CustomRadioLayout.OnModelSelectListener() {
            public void mode(int i) {
                int unused = AutoPullFriendsToAllGroupActivity.this.sendGroupMethod = i;
            }
        });
    }

    public void initData() {
        try {
            int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "pull_friend_to_all_group_num_all", 1)).intValue();
            int intValue2 = ((Integer) SPUtils.get(MyApplication.getConText(), "pull_friend_to_all_group_num_part", 1)).intValue();
            int intValue3 = ((Integer) SPUtils.get(MyApplication.getConText(), "pull_friend_to_all_group_num_shield", 1)).intValue();
            this.groupSendModeLayout.initSendIndexStrAll(true, "pull_friend_to_all_group_num_all", intValue);
            this.groupSendModeLayout.initSendIndexStrPart(true, "pull_friend_to_all_group_num_part", intValue2);
            this.groupSendModeLayout.initSendIndexStrShield(true, "pull_friend_to_all_group_num_shield", intValue3);
            this.groupSendModeLayout.initSendLabelStrAll(true, "pull_friend_to_all_group_label_all", (String) SPUtils.get(MyApplication.getConText(), "pull_friend_to_all_group_label_all", ""));
            this.groupSendModeLayout.initSendLabelStrPart(true, "pull_friend_to_all_group_label_part", (String) SPUtils.get(MyApplication.getConText(), "pull_friend_to_all_group_label_part", ""));
            this.groupSendModeLayout.initSendLabelStrShield(true, "pull_friend_to_all_group_label_shield", (String) SPUtils.get(MyApplication.getConText(), "pull_friend_to_all_group_label_shield", ""));
            String str = (String) SPUtils.get(MyApplication.getConText(), "pull_friend_to_all_group_name", "");
            if (str != null && !"".equals(str)) {
                this.editCardNickName.setText(str);
            }
            String str2 = (String) SPUtils.get(MyApplication.getConText(), "pull_friend_to_all_group_say_content", "");
            if (str2 != null && !"".equals(str2)) {
                this.editLeavingMessage.setText(str2);
            }
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_37");
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoPullFriendsToAllGroupActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    @Subscribe
    public void onEventMainThread(WxGroupEvent wxGroupEvent) {
        LinkedHashSet<String> selectedGroupList = wxGroupEvent.getSelectedGroupList();
        if (selectedGroupList == null || selectedGroupList.size() <= 0) {
            this.groupSendModeLayout.setSendStr("");
            return;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = selectedGroupList.iterator();
        while (it.hasNext()) {
            sb.append(((String) it.next()) + ";");
        }
        this.groupNames = sb.toString();
        this.groupSendModeLayout.setSendStr(this.groupNames);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        initData();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.AutoPullFriendsToAllGroupActivity] */
    @OnClick({2131297049, 2131297425, 2131297636, 2131297052})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.拉人进群，设置的昵称/微信号一定要唯一，避免选择好友昵称/微信号错误的情况。\n\n2.使用此功能前，需要将要操作的群，提前保存在通讯录中，即（微信->通讯录->群聊）中存在此群\n\n3.为了防止漏群情况，使用此功能前，群名必须保证唯一\n\n4.设置不想发送的群名称时，群名称一定是唯一的，这样可以避免发送错群的情况。\n\n5.如果不太懂的话，可以看一下视频介绍。");
        } else if (id == 2131297425) {
            final String obj = this.editCardNickName.getText().toString();
            try {
                this.spaceTime = Integer.parseInt(this.executeTimeSpaceLayout.getEditText().getText().toString());
            } catch (Exception e) {
            }
            if (obj == null || "".equals(obj)) {
                ToastUtils.showToast(this, "请设置要拉人的昵称或微信号");
                return;
            }
            if (this.groupNames == null || "".equals(this.groupNames)) {
                if (this.sendCardType == 1) {
                    ToastUtils.showToast(this, "请设置您要群发的群");
                    return;
                } else if (this.sendCardType == 2) {
                    ToastUtils.showToast(this, "请设置您要屏蔽的群");
                    return;
                }
            }
            if (this.spaceTime < this.minSpaceTime) {
                ToastUtils.showToast(this, "拉人间隔时间最小设置: " + this.minSpaceTime + " 秒");
                return;
            }
            startCheck("31", true, new BaseActivity.OnStartCheckListener() {
                public void checkEnd() {
                    String obj = AutoPullFriendsToAllGroupActivity.this.editLeavingMessage.getText().toString();
                    if (obj != null && !"".equals(obj)) {
                        SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_say_content", obj);
                    }
                    if (obj != null && !"".equals(obj)) {
                        SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_name", obj);
                    }
                    OperationParameterModel operationParameterModel = new OperationParameterModel();
                    operationParameterModel.setTaskNum("31");
                    operationParameterModel.setSendGroupMethod(AutoPullFriendsToAllGroupActivity.this.sendGroupMethod);
                    operationParameterModel.setSendCardType(AutoPullFriendsToAllGroupActivity.this.sendCardType);
                    operationParameterModel.setSayContent(obj);
                    operationParameterModel.setStartIndex(AutoPullFriendsToAllGroupActivity.this.startIndex);
                    operationParameterModel.setJumpGroupNames(AutoPullFriendsToAllGroupActivity.this.groupNames);
                    operationParameterModel.setSpaceTime(AutoPullFriendsToAllGroupActivity.this.spaceTime);
                    operationParameterModel.setCirculateMode(AutoPullFriendsToAllGroupActivity.this.circulateMode);
                    if (AutoPullFriendsToAllGroupActivity.this.circulateMode == 0) {
                        operationParameterModel.setCirculateOutSize(AutoPullFriendsToAllGroupActivity.this.circulateSize);
                        operationParameterModel.setCirculateInnerSize(1);
                    } else {
                        operationParameterModel.setCirculateOutSize(1);
                        operationParameterModel.setCirculateInnerSize(AutoPullFriendsToAllGroupActivity.this.circulateSize);
                    }
                    operationParameterModel.setCardName(obj);
                    MyApplication.instance.setOperationParameterModel(operationParameterModel);
                    AutoPullFriendsToAllGroupActivity.this.startWX(operationParameterModel);
                }
            });
        } else if (id == 2131297636) {
            WebViewActivity.startActivity(this, "换群专用视频教程", QBangApis.VIDEO_PULL_SINGLE_FRIEND_TO_GROUPS);
        }
    }
}
