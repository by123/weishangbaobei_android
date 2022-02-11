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
import com.wx.assistants.bean.WxTagEvent;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.CirculateModelLayout;
import com.wx.assistants.view.CirculateNumLayout;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FriendSendModeLayout;
import com.wx.assistants.view.IntimateModelLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class GroupSendCardToFriendsActivity extends BaseActivity {
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
    @BindView(2131296717)
    FriendSendModeLayout friendSendModeLayout;
    /* access modifiers changed from: private */
    public int intimateMode = 1;
    @BindView(2131296813)
    IntimateModelLayout intimateModelLayout;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> jumpPersons = new LinkedHashSet<>();
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
    public int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6724);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendCardToFriendsActivity] */
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
        this.navTitle.setText("群发名片到好友");
        this.startWx.setText("启动微信开始群发名片");
        this.executeTimeSpaceLayout.setExecuteTimeTitle("群发间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = GroupSendCardToFriendsActivity.this.spaceTime = i;
            }
        });
        this.friendSendModeLayout.setFriendSendTypeListener(new FriendSendModeLayout.FriendSendTypeListener() {
            public void sendType(int i) {
                int unused = GroupSendCardToFriendsActivity.this.sendCardType = i;
            }

            public void sendStartIndex(int i) {
                int unused = GroupSendCardToFriendsActivity.this.startIndex = i;
            }

            public void sendMembers(LinkedHashSet<String> linkedHashSet) {
                LinkedHashSet unused = GroupSendCardToFriendsActivity.this.jumpPersons = linkedHashSet;
            }
        });
    }

    public void initData() {
        try {
            int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "card_to_friend_num_all", 1)).intValue();
            int intValue2 = ((Integer) SPUtils.get(MyApplication.getConText(), "card_to_friend_num_part", 1)).intValue();
            int intValue3 = ((Integer) SPUtils.get(MyApplication.getConText(), "card_to_friend_num_shield", 1)).intValue();
            this.friendSendModeLayout.initSendIndexStrAll(true, "card_to_friend_num_all", intValue);
            this.friendSendModeLayout.initSendIndexStrPart(true, "card_to_friend_num_part", intValue2);
            this.friendSendModeLayout.initSendIndexStrShield(true, "card_to_friend_num_shield", intValue3);
            this.friendSendModeLayout.initSendLabelStrAll(true, "card_to_friend_label_all", (String) SPUtils.get(MyApplication.getConText(), "card_to_friend_label_all", ""));
            this.friendSendModeLayout.initSendLabelStrPart(true, "card_to_friend_label_part", (String) SPUtils.get(MyApplication.getConText(), "card_to_friend_label_part", ""));
            this.friendSendModeLayout.initSendLabelStrShield(true, "card_to_friend_label_shield", (String) SPUtils.get(MyApplication.getConText(), "card_to_friend_label_shield", ""));
            this.friendSendModeLayout.initSendLabelMemberPart(true, "card_to_friend_label_member_part", (String) SPUtils.get(MyApplication.getConText(), "card_to_friend_label_member_part", ""));
            this.friendSendModeLayout.initSendLabelMemberShield(true, "card_to_friend_label_member_shield", (String) SPUtils.get(MyApplication.getConText(), "card_to_friend_label_member_shield", ""));
            String str = (String) SPUtils.get(MyApplication.getConText(), "card_name_friend", "");
            if (str != null && !"".equals(str)) {
                this.editCardNickName.setText(str);
            }
            String str2 = (String) SPUtils.get(MyApplication.getConText(), "card_friend_say_content", "");
            if (str2 != null && !"".equals(str2)) {
                this.editLeavingMessage.setText(str2);
            }
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_37");
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onEventMainThread(WxTagEvent wxTagEvent) {
        ArrayList<String> selectedTagList = wxTagEvent.getSelectedTagList();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (selectedTagList == null || selectedTagList.size() <= 0) {
            this.friendSendModeLayout.setSendStr("");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < selectedTagList.size(); i++) {
                sb.append(selectedTagList.get(i) + ";");
                linkedHashSet.add(selectedTagList.get(i));
            }
            this.friendSendModeLayout.setSendStr(sb.toString());
        }
        if (wxTagEvent.getSelectedPeopleList() != null) {
            this.jumpPersons = new LinkedHashSet<>();
            this.jumpPersons.addAll(wxTagEvent.getSelectedPeopleList());
        } else {
            this.jumpPersons = new LinkedHashSet<>();
        }
        this.friendSendModeLayout.setSendMember(this.jumpPersons);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendCardToFriendsActivity] */
    @OnClick({2131297049, 2131297425, 2131297636, 2131297052})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.名片推送，设置的昵称一定要唯一，避免选择名片错误的情况。\n\n2.可设置不想发送的好友，请提前把不想发送的好友放入到屏蔽标签中。\n\n3.如果不太懂的话，可以看一下视频介绍。");
        } else if (id == 2131297425) {
            final String obj = this.editLeavingMessage.getText().toString();
            if (obj != null && !"".equals(obj)) {
                SPUtils.put(MyApplication.getConText(), "card_friend_say_content", obj);
            }
            final String obj2 = this.editCardNickName.getText().toString();
            if (obj2 == null || "".equals(obj2)) {
                ToastUtils.showToast(this, "请设置要转发的名片昵称");
                return;
            }
            if (this.jumpPersons == null || this.jumpPersons.size() <= 0) {
                if (this.sendCardType == 1) {
                    ToastUtils.showToast(this, "请设置您要群发的标签");
                    return;
                } else if (this.sendCardType == 2) {
                    ToastUtils.showToast(this, "请设置您要屏蔽的标签");
                    return;
                }
            }
            startCheck("36", true, new BaseActivity.OnStartCheckListener() {
                public void checkEnd() {
                    if (obj2 != null && !"".equals(obj2)) {
                        SPUtils.put(MyApplication.getConText(), "card_name_friend", obj2);
                    }
                    OperationParameterModel operationParameterModel = new OperationParameterModel();
                    operationParameterModel.setTaskNum("36");
                    operationParameterModel.setSendCardType(GroupSendCardToFriendsActivity.this.sendCardType);
                    operationParameterModel.setSayContent(obj + GroupSendCardToFriendsActivity.this.getAppendSign());
                    operationParameterModel.setStartIndex(GroupSendCardToFriendsActivity.this.startIndex);
                    operationParameterModel.setTagListPeoples(GroupSendCardToFriendsActivity.this.jumpPersons);
                    operationParameterModel.setSpaceTime(GroupSendCardToFriendsActivity.this.spaceTime);
                    operationParameterModel.setCirculateMode(GroupSendCardToFriendsActivity.this.circulateMode);
                    if (GroupSendCardToFriendsActivity.this.circulateMode == 0) {
                        operationParameterModel.setCirculateOutSize(GroupSendCardToFriendsActivity.this.circulateSize);
                        operationParameterModel.setCirculateInnerSize(1);
                    } else {
                        operationParameterModel.setCirculateOutSize(1);
                        operationParameterModel.setCirculateInnerSize(GroupSendCardToFriendsActivity.this.circulateSize);
                    }
                    operationParameterModel.setCardName(obj2);
                    operationParameterModel.setIntimateMode(GroupSendCardToFriendsActivity.this.intimateMode);
                    MyApplication.instance.setOperationParameterModel(operationParameterModel);
                    GroupSendCardToFriendsActivity.this.startWX(operationParameterModel);
                }
            });
        } else if (id == 2131297636) {
            WebViewActivity.startActivity(this, "群发名片到微信好友", QBangApis.VIDEO_GROUP_SEND_CARD_FRIENDS);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
