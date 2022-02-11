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
import com.wx.assistants.view.GroupSendModeLayoutH;
import com.wx.assistants.webview.WebViewActivity;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class GroupSendCardToGroupActivity extends BaseActivity {
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
    GroupSendModeLayoutH groupSendModeLayout;
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
    public int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6725);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendCardToGroupActivity] */
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
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("群发名片到微信群");
        this.startWx.setText("启动微信开始群发名片");
        this.executeTimeSpaceLayout.setExecuteTimeTitle("群发间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = GroupSendCardToGroupActivity.this.spaceTime = i;
            }
        });
        this.sendGroupMethodLayout.setText("找群方式", "翻页找", 60, "搜索找", 60);
        this.sendGroupMethodLayout.setDefaultSelect(0);
        this.sendGroupMethodLayout.setOnModelSelectListener(new CustomRadioLayout.OnModelSelectListener() {
            public void mode(int i) {
                int unused = GroupSendCardToGroupActivity.this.sendGroupMethod = i;
            }
        });
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendCardToGroupActivity] */
    public void initData() {
        try {
            ((Integer) SPUtils.get(this, "card_to_group_start_index", 1)).intValue();
            int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "card_to_group_num_all", 1)).intValue();
            int intValue2 = ((Integer) SPUtils.get(MyApplication.getConText(), "card_to_group_num_part", 1)).intValue();
            int intValue3 = ((Integer) SPUtils.get(MyApplication.getConText(), "card_to_group_num_shield", 1)).intValue();
            this.groupSendModeLayout.initSendIndexStrAll(true, "card_to_group_num_all", intValue);
            this.groupSendModeLayout.initSendIndexStrPart(true, "card_to_group_num_part", intValue2);
            this.groupSendModeLayout.initSendIndexStrShield(true, "card_to_group_num_shield", intValue3);
            this.groupSendModeLayout.initSendLabelStrAll(true, "card_to_group_label_all", (String) SPUtils.get(MyApplication.getConText(), "card_to_group_label_all", ""));
            this.groupSendModeLayout.initSendLabelStrPart(true, "card_to_group_label_part", (String) SPUtils.get(MyApplication.getConText(), "card_to_group_label_part", ""));
            this.groupSendModeLayout.initSendLabelStrShield(true, "card_to_group_label_shield", (String) SPUtils.get(MyApplication.getConText(), "card_to_group_label_shield", ""));
            String str = (String) SPUtils.get(MyApplication.getConText(), "card_name_group", "");
            if (str != null && !"".equals(str)) {
                this.editCardNickName.setText(str);
            }
            String str2 = (String) SPUtils.get(MyApplication.getConText(), "card_group_say_content", "");
            if (str2 != null && !"".equals(str2)) {
                this.editLeavingMessage.setText(str2);
            }
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_37");
            e.printStackTrace();
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

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendCardToGroupActivity] */
    @OnClick({2131297049, 2131297425, 2131297636, 2131297052})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.名片推送，设置的昵称一定要唯一，避免选择名片错误的情况。\n\n2.使用此功能前，需要将要操作的群，提前保存在通讯录中，即（微信->通讯录->群聊）中存在此群\n\n3.为了防止漏群情况，使用此功能前，群名必须保证唯一\n\n4.可设置循环方式和循环次数。假如选择A,B两个群发送，循环次数2次为例。外循环：AB两群发完一遍，AB两群在发一遍。内循环：A群内连发2遍后，B群内也连发两遍。\n\n5.设置不想发送的群名称时，群名称一定是唯一的，这样可以避免发送错群的情况。\n\n6.如果不太懂的话，可以看一下视频介绍。");
        } else if (id == 2131297425) {
            final String obj = this.editLeavingMessage.getText().toString();
            if (obj != null && !"".equals(obj)) {
                SPUtils.put(MyApplication.getConText(), "card_group_say_content", obj);
            }
            final String obj2 = this.editCardNickName.getText().toString();
            if (obj2 == null || "".equals(obj2)) {
                ToastUtils.showToast(this, "请设置要转发的名片昵称");
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
            startCheck("10", true, new BaseActivity.OnStartCheckListener() {
                public void checkEnd() {
                    if (obj2 != null && !"".equals(obj2)) {
                        SPUtils.put(MyApplication.getConText(), "card_name_group", obj2);
                    }
                    OperationParameterModel operationParameterModel = new OperationParameterModel();
                    operationParameterModel.setTaskNum("10");
                    operationParameterModel.setStartIndex(GroupSendCardToGroupActivity.this.startIndex);
                    operationParameterModel.setSendGroupMethod(GroupSendCardToGroupActivity.this.sendGroupMethod);
                    operationParameterModel.setSendCardType(GroupSendCardToGroupActivity.this.sendCardType);
                    operationParameterModel.setSayContent(obj + GroupSendCardToGroupActivity.this.getAppendSign());
                    operationParameterModel.setJumpGroupNames(GroupSendCardToGroupActivity.this.groupNames);
                    operationParameterModel.setSpaceTime(GroupSendCardToGroupActivity.this.spaceTime);
                    operationParameterModel.setCirculateMode(GroupSendCardToGroupActivity.this.circulateMode);
                    if (GroupSendCardToGroupActivity.this.circulateMode == 0) {
                        operationParameterModel.setCirculateOutSize(GroupSendCardToGroupActivity.this.circulateSize);
                        operationParameterModel.setCirculateInnerSize(1);
                    } else {
                        operationParameterModel.setCirculateOutSize(1);
                        operationParameterModel.setCirculateInnerSize(GroupSendCardToGroupActivity.this.circulateSize);
                    }
                    operationParameterModel.setCardName(obj2);
                    MyApplication.instance.setOperationParameterModel(operationParameterModel);
                    GroupSendCardToGroupActivity.this.startWX(operationParameterModel);
                }
            });
        } else if (id == 2131297636) {
            WebViewActivity.startActivity(this, "群发名片到微信群", QBangApis.VIDEO_GROUP_SEND_CARD_TO_GROUP);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
