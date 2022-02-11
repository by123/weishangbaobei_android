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
import com.wx.assistants.bean.HotMaterialTextBean;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxTagEvent;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.CirculateNumLayout;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FriendSendModeLayout;
import com.wx.assistants.view.IntimateModelLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class GroupSendCollectionToFriendsActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public int circulateMode = 0;
    @BindView(2131296493)
    CirculateNumLayout circulateNum;
    /* access modifiers changed from: private */
    public int circulateSize = 1;
    @BindView(2131296507)
    LinearLayout cleanEditText;
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
    @BindView(2131296950)
    LinearLayout linearLayoutTemplate;
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
        StubApp.interface11(6726);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendCollectionToFriendsActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("群发收藏到好友");
        this.startWx.setText("启动微信开始群发收藏");
        this.executeTimeSpaceLayout.setExecuteTimeTitle("群发间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = GroupSendCollectionToFriendsActivity.this.spaceTime = i;
            }
        });
        this.friendSendModeLayout.setFriendSendTypeListener(new FriendSendModeLayout.FriendSendTypeListener() {
            public void sendType(int i) {
                int unused = GroupSendCollectionToFriendsActivity.this.sendCardType = i;
            }

            public void sendStartIndex(int i) {
                int unused = GroupSendCollectionToFriendsActivity.this.startIndex = i;
            }

            public void sendMembers(LinkedHashSet<String> linkedHashSet) {
                LinkedHashSet unused = GroupSendCollectionToFriendsActivity.this.jumpPersons = linkedHashSet;
            }
        });
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

    @Subscribe
    public void onEventMainThread(final HotMaterialTextBean hotMaterialTextBean) {
        runOnUiThread(new Runnable() {
            public void run() {
                String materialStr = hotMaterialTextBean.getMaterialStr();
                if (materialStr != null && !"".equals(materialStr)) {
                    GroupSendCollectionToFriendsActivity.this.editLeavingMessage.setText(materialStr);
                    if (materialStr != null && !"".equals(materialStr)) {
                        SPUtils.put(MyApplication.getConText(), "collection_friend_say_content", materialStr);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            String str = (String) SPUtils.get(MyApplication.getConText(), "collection_friend_say_content", "");
            if (str != null && !"".equals(str)) {
                this.editLeavingMessage.setText(str);
            }
            int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "collection_to_friends_num_all", 1)).intValue();
            int intValue2 = ((Integer) SPUtils.get(MyApplication.getConText(), "collection_to_friends_num_part", 1)).intValue();
            int intValue3 = ((Integer) SPUtils.get(MyApplication.getConText(), "collection_to_friends_num_shield", 1)).intValue();
            this.friendSendModeLayout.initSendIndexStrAll(true, "collection_to_friends_num_all", intValue);
            this.friendSendModeLayout.initSendIndexStrPart(true, "collection_to_friends_num_part", intValue2);
            this.friendSendModeLayout.initSendIndexStrShield(true, "collection_to_friends_num_shield", intValue3);
            this.friendSendModeLayout.initSendLabelStrAll(true, "collection_to_friends_label_all", (String) SPUtils.get(MyApplication.getConText(), "collection_to_friends_label_all", ""));
            this.friendSendModeLayout.initSendLabelStrPart(true, "collection_to_friends_label_part", (String) SPUtils.get(MyApplication.getConText(), "collection_to_friends_label_part", ""));
            this.friendSendModeLayout.initSendLabelStrShield(true, "collection_to_friends_label_shield", (String) SPUtils.get(MyApplication.getConText(), "collection_to_friends_label_shield", ""));
            this.friendSendModeLayout.initSendLabelMemberPart(true, "collection_to_friends_label_member_part", (String) SPUtils.get(MyApplication.getConText(), "collection_to_friends_label_member_part", ""));
            this.friendSendModeLayout.initSendLabelMemberShield(true, "collection_to_friends_label_member_shield", (String) SPUtils.get(MyApplication.getConText(), "collection_to_friends_label_member_shield", ""));
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("WS_BABY_Catch_12");
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendCollectionToFriendsActivity] */
    @OnClick({2131296507, 2131297049, 2131297425, 2131297636, 2131297052, 2131296950})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296507:
                this.editLeavingMessage.setText("");
                return;
            case 2131296950:
                WebViewActivity.startActivity(this, "热门群发模版", QBangApis.HOT_MASTER_PLATE_URL, false);
                return;
            case 2131297049:
                back();
                return;
            case 2131297052:
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.群发收藏给所有好友，可选择设置只想发送的标签好友或者不想发送的标签好友。\n\n2.只群发收藏的第一条内容，可以留言文字。\n\n3.此功能是会员用户专享");
                return;
            case 2131297425:
                final String obj = this.editLeavingMessage.getText().toString();
                if (obj != null && !"".equals(obj)) {
                    SPUtils.put(MyApplication.getConText(), "collection_friend_say_content", obj);
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
                startCheck("19", true, new BaseActivity.OnStartCheckListener() {
                    public void checkEnd() {
                        OperationParameterModel operationParameterModel = new OperationParameterModel();
                        operationParameterModel.setTaskNum("19");
                        operationParameterModel.setSendCardType(GroupSendCollectionToFriendsActivity.this.sendCardType);
                        operationParameterModel.setStartIndex(GroupSendCollectionToFriendsActivity.this.startIndex);
                        operationParameterModel.setSayContent(obj + GroupSendCollectionToFriendsActivity.this.getAppendSign());
                        operationParameterModel.setSpaceTime(GroupSendCollectionToFriendsActivity.this.spaceTime);
                        operationParameterModel.setCirculateMode(GroupSendCollectionToFriendsActivity.this.circulateMode);
                        if (GroupSendCollectionToFriendsActivity.this.circulateMode == 0) {
                            operationParameterModel.setCirculateOutSize(GroupSendCollectionToFriendsActivity.this.circulateSize);
                            operationParameterModel.setCirculateInnerSize(1);
                        } else {
                            operationParameterModel.setCirculateOutSize(1);
                            operationParameterModel.setCirculateInnerSize(GroupSendCollectionToFriendsActivity.this.circulateSize);
                        }
                        operationParameterModel.setTagListPeoples(GroupSendCollectionToFriendsActivity.this.jumpPersons);
                        operationParameterModel.setIntimateMode(GroupSendCollectionToFriendsActivity.this.intimateMode);
                        MyApplication.instance.setOperationParameterModel(operationParameterModel);
                        GroupSendCollectionToFriendsActivity.this.startWX(operationParameterModel);
                    }
                });
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "群发收藏到好友视频教程", QBangApis.VIDEO_COLLECTION_TO_FRIENDS);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
