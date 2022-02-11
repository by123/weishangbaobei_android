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
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FriendSendModeLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class GroupSendPublicNumberToFriendsActivity extends BaseActivity {
    @BindView(2131296616)
    EditText editLeavingMessage;
    @BindView(2131296647)
    ExecuteTimeSpaceLayout executeTimeSpaceLayout;
    @BindView(2131296717)
    FriendSendModeLayout friendSendModeLayout;
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
        StubApp.interface11(6730);
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("群发公众号到好友");
        this.startWx.setText("启动微信开始群发公众号");
        this.executeTimeSpaceLayout.setExecuteTimeTitle("群发间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = GroupSendPublicNumberToFriendsActivity.this.spaceTime = i;
            }
        });
        this.friendSendModeLayout.setFriendSendTypeListener(new FriendSendModeLayout.FriendSendTypeListener() {
            public void sendMembers(LinkedHashSet<String> linkedHashSet) {
                LinkedHashSet unused = GroupSendPublicNumberToFriendsActivity.this.jumpPersons = linkedHashSet;
            }

            public void sendStartIndex(int i) {
                int unused = GroupSendPublicNumberToFriendsActivity.this.startIndex = i;
            }

            public void sendType(int i) {
                int unused = GroupSendPublicNumberToFriendsActivity.this.sendCardType = i;
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendPublicNumberToFriendsActivity] */
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
    public void onEventMainThread(final HotMaterialTextBean hotMaterialTextBean) {
        runOnUiThread(new Runnable() {
            public void run() {
                String materialStr = hotMaterialTextBean.getMaterialStr();
                if (materialStr != null && !"".equals(materialStr)) {
                    GroupSendPublicNumberToFriendsActivity.this.editLeavingMessage.setText(materialStr);
                    if (materialStr != null && !"".equals(materialStr)) {
                        SPUtils.put(MyApplication.getConText(), "public_number_friend_say_content", materialStr);
                    }
                }
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
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= selectedTagList.size()) {
                    break;
                }
                sb.append(selectedTagList.get(i2) + ";");
                linkedHashSet.add(selectedTagList.get(i2));
                i = i2 + 1;
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

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            String str = (String) SPUtils.get(MyApplication.getConText(), "public_number_friend_say_content", "");
            if (str != null && !"".equals(str)) {
                this.editLeavingMessage.setText(str);
            }
            int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "public_number_to_friend_num_all", 1)).intValue();
            int intValue2 = ((Integer) SPUtils.get(MyApplication.getConText(), "public_number_to_friend_num_part", 1)).intValue();
            int intValue3 = ((Integer) SPUtils.get(MyApplication.getConText(), "public_number_to_friend_num_shield", 1)).intValue();
            this.friendSendModeLayout.initSendIndexStrAll(true, "public_number_to_friend_num_all", intValue);
            this.friendSendModeLayout.initSendIndexStrPart(true, "public_number_to_friend_num_part", intValue2);
            this.friendSendModeLayout.initSendIndexStrShield(true, "public_number_to_friend_num_shield", intValue3);
            this.friendSendModeLayout.initSendLabelStrAll(true, "public_number_to_friend_label_all", (String) SPUtils.get(MyApplication.getConText(), "public_number_to_friend_label_all", ""));
            this.friendSendModeLayout.initSendLabelStrPart(true, "public_number_to_friend_label_part", (String) SPUtils.get(MyApplication.getConText(), "public_number_to_friend_label_part", ""));
            this.friendSendModeLayout.initSendLabelStrShield(true, "public_number_to_friend_label_shield", (String) SPUtils.get(MyApplication.getConText(), "public_number_to_friend_label_shield", ""));
            this.friendSendModeLayout.initSendLabelMemberPart(true, "public_number_to_friend_label_member_part", (String) SPUtils.get(MyApplication.getConText(), "public_number_to_friend_label_member_part", ""));
            this.friendSendModeLayout.initSendLabelMemberShield(true, "public_number_to_friend_label_member_shield", (String) SPUtils.get(MyApplication.getConText(), "public_number_to_friend_label_member_shield", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendPublicNumberToFriendsActivity] */
    @OnClick({2131297049, 2131297425, 2131297636, 2131297052, 2131296950})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296950:
                WebViewActivity.startActivity(this, "热门群发模版", QBangApis.HOT_MASTER_PLATE_URL, false);
                return;
            case 2131297049:
                back();
                return;
            case 2131297052:
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.请将要转发的公众号，提前放在会话聊天页的最后一条。\n\n2.群发公众号默认群发所有好友，可选择设置只想发送的标签好友或者不想发送的标签好友。\n\n3.群发完公众号后，您还可以追加一条留言文字。\n\n4.此功能是会员用户专享");
                return;
            case 2131297425:
                if (this.jumpPersons == null || this.jumpPersons.size() <= 0) {
                    if (this.sendCardType == 1) {
                        ToastUtils.showToast(this, "请设置您要群发的标签");
                        return;
                    } else if (this.sendCardType == 2) {
                        ToastUtils.showToast(this, "请设置您要屏蔽的标签");
                        return;
                    }
                }
                startCheck("29", true, new BaseActivity.OnStartCheckListener() {
                    public void checkEnd() {
                        String obj = GroupSendPublicNumberToFriendsActivity.this.editLeavingMessage.getText().toString();
                        if (obj != null && !"".equals(obj)) {
                            SPUtils.put(MyApplication.getConText(), "public_number_friend_say_content", obj);
                        }
                        OperationParameterModel operationParameterModel = new OperationParameterModel();
                        operationParameterModel.setTaskNum("29");
                        operationParameterModel.setSendCardType(GroupSendPublicNumberToFriendsActivity.this.sendCardType);
                        operationParameterModel.setStartIndex(GroupSendPublicNumberToFriendsActivity.this.startIndex);
                        operationParameterModel.setSayContent(obj + GroupSendPublicNumberToFriendsActivity.this.getAppendSign());
                        operationParameterModel.setOtherSendType(0);
                        operationParameterModel.setSpaceTime(GroupSendPublicNumberToFriendsActivity.this.spaceTime);
                        operationParameterModel.setTagListPeoples(GroupSendPublicNumberToFriendsActivity.this.jumpPersons);
                        MyApplication.instance.setOperationParameterModel(operationParameterModel);
                        GroupSendPublicNumberToFriendsActivity.this.startWX(operationParameterModel);
                    }
                });
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "群发公众号到好友视频教程", QBangApis.VIDEO_GROUP_SEND_PUBLIC_NUM_FRIENDS);
                return;
            default:
                return;
        }
    }
}
