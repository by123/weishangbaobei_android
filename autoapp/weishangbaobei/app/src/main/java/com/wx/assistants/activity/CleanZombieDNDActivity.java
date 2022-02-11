package com.wx.assistants.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.google.gson.Gson;
import com.stub.StubApp;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.LabelBean;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.UserInfoBean;
import com.wx.assistants.bean.WxTagEvent;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.CleanZombieDNDLayout;
import com.wx.assistants.view.FriendSendModeLayoutZombies;
import com.wx.assistants.view.SingleLabelSelectLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CleanZombieDNDActivity extends BaseActivity {
    @BindView(2131296512)
    CleanZombieDNDLayout cleanZombieDndLayout;
    @BindView(2131296580)
    Switch deleteShieldSwitchBtn;
    @BindView(2131296581)
    Switch deleteSwitchBtn;
    /* access modifiers changed from: private */
    public int dndModel = 0;
    @BindView(2131296717)
    FriendSendModeLayoutZombies friendSendModeLayout;
    /* access modifiers changed from: private */
    public String inviteUrl = "";
    /* access modifiers changed from: private */
    public boolean isDeleted = false;
    /* access modifiers changed from: private */
    public boolean isDeletedShield = false;
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
    public String selectLabel = "";
    /* access modifiers changed from: private */
    public int sendCardType = 0;
    @BindView(2131297369)
    LinearLayout shieldDeleteLayout;
    @BindView(2131297386)
    SingleLabelSelectLayout singleSelectLabelLayout;
    @BindView(2131297387)
    LinearLayout singleSelectLabelOutLayout;
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6718);
    }

    public void initInviteData() {
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.activity.CleanZombieDNDActivity, android.content.Context] */
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
        this.navTitle.setText("无打扰检测僵尸粉");
        this.startWx.setText("启动微信开始检测");
        this.isDeleted = false;
        this.deleteSwitchBtn.setChecked(false);
        this.isDeletedShield = false;
        this.deleteShieldSwitchBtn.setChecked(false);
        this.shieldDeleteLayout.setVisibility(8);
        this.deleteSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                boolean unused = CleanZombieDNDActivity.this.isDeleted = z;
                if (CleanZombieDNDActivity.this.dndModel == 2) {
                    if (!CleanZombieDNDActivity.this.isDeleted || !CleanZombieDNDActivity.this.isDeletedShield) {
                        CleanZombieDNDActivity.this.singleSelectLabelOutLayout.setVisibility(0);
                    } else {
                        CleanZombieDNDActivity.this.singleSelectLabelOutLayout.setVisibility(8);
                    }
                } else if (z) {
                    CleanZombieDNDActivity.this.singleSelectLabelOutLayout.setVisibility(8);
                } else {
                    CleanZombieDNDActivity.this.singleSelectLabelOutLayout.setVisibility(0);
                }
            }
        });
        this.deleteShieldSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                boolean unused = CleanZombieDNDActivity.this.isDeletedShield = z;
                if (!z || !CleanZombieDNDActivity.this.isDeleted) {
                    CleanZombieDNDActivity.this.singleSelectLabelOutLayout.setVisibility(0);
                } else {
                    CleanZombieDNDActivity.this.singleSelectLabelOutLayout.setVisibility(8);
                }
            }
        });
        this.cleanZombieDndLayout.setDNDModelListener(new CleanZombieDNDLayout.DNDModelListener() {
            public void dndMode(int i) {
                int unused = CleanZombieDNDActivity.this.dndModel = i;
                if (CleanZombieDNDActivity.this.dndModel != 2 || (CleanZombieDNDActivity.this.isDeletedShield && CleanZombieDNDActivity.this.isDeleted)) {
                    CleanZombieDNDActivity.this.shieldDeleteLayout.setVisibility(8);
                } else {
                    CleanZombieDNDActivity.this.shieldDeleteLayout.setVisibility(0);
                }
            }

            public void dndModeString(String str) {
                String unused = CleanZombieDNDActivity.this.inviteUrl = str;
            }
        });
        this.singleSelectLabelLayout.setOnSelectSingleLabelListener(new SingleLabelSelectLayout.OnSelectSingleLabelListener() {
            public void selectLabel(LabelBean labelBean) {
                LogUtils.log("WS_BABY_selectLabel");
                if (labelBean == null) {
                    String unused = CleanZombieDNDActivity.this.selectLabel = null;
                } else {
                    String unused2 = CleanZombieDNDActivity.this.selectLabel = labelBean.getLabelName();
                }
            }

            public void selectedPeopleList(LinkedHashSet<String> linkedHashSet) {
                LogUtils.log("WS_BABY_selectedPeopleList" + linkedHashSet);
            }

            public void selectedPeopleString(String str) {
                LogUtils.log("WS_BABY_selectedPeopleString" + str);
            }
        });
        this.singleSelectLabelLayout.setBigText("保存到微信标签");
        this.singleSelectLabelLayout.setSmallText("");
        this.friendSendModeLayout.setFriendSendTypeListener(new FriendSendModeLayoutZombies.FriendSendTypeListener() {
            public void sendType(int i) {
                int unused = CleanZombieDNDActivity.this.sendCardType = i;
            }

            public void sendStartIndex(int i) {
                int unused = CleanZombieDNDActivity.this.startIndex = i;
            }

            public void sendMembers(LinkedHashSet<String> linkedHashSet) {
                LinkedHashSet unused = CleanZombieDNDActivity.this.jumpPersons = linkedHashSet;
            }
        });
        initInviteData();
    }

    public void initData() {
        try {
            int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "check_zombies_friend_num_all", 1)).intValue();
            int intValue2 = ((Integer) SPUtils.get(MyApplication.getConText(), "check_zombies_friend_num_part", 1)).intValue();
            int intValue3 = ((Integer) SPUtils.get(MyApplication.getConText(), "check_zombies_friend_num_shield", 1)).intValue();
            this.friendSendModeLayout.initSendIndexStrAll(true, "check_zombies_friend_num_all", intValue);
            this.friendSendModeLayout.initSendIndexStrPart(true, "check_zombies_friend_num_part", intValue2);
            this.friendSendModeLayout.initSendIndexStrShield(true, "check_zombies_friend_num_shield", intValue3);
            this.friendSendModeLayout.initSendLabelStrAll(true, "check_zombies_friend_label_all", (String) SPUtils.get(MyApplication.getConText(), "check_zombies_friend_label_all", ""));
            this.friendSendModeLayout.initSendLabelStrPart(true, "check_zombies_friend_label_part", (String) SPUtils.get(MyApplication.getConText(), "check_zombies_friend_label_part", ""));
            this.friendSendModeLayout.initSendLabelStrShield(true, "check_zombies_friend_label_shield", (String) SPUtils.get(MyApplication.getConText(), "check_zombies_friend_label_shield", ""));
            this.friendSendModeLayout.initSendLabelMemberPart(true, "check_zombies_friend_label_member_part", (String) SPUtils.get(MyApplication.getConText(), "check_zombies_friend_label_member_part", ""));
            this.friendSendModeLayout.initSendLabelMemberShield(true, "check_zombies_friend_label_member_shield", (String) SPUtils.get(MyApplication.getConText(), "check_zombies_friend_label_member_shield", ""));
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_37");
            e.printStackTrace();
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.activity.CleanZombieDNDActivity, com.wx.assistants.base.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        try {
            String str = (String) SPUtils.get(this, "ws_baby_user_info", "");
            if (str != null && !"".equals(str)) {
                setUserInfo(str);
            }
            initData();
        } catch (Exception unused) {
        }
    }

    public void setUserInfo(String str) {
        UserInfoBean.UserBean userBean = (UserInfoBean.UserBean) new Gson().fromJson(str, UserInfoBean.UserBean.class);
        if (userBean != null) {
            try {
                String userId = userBean.getUserId();
                if (userId != null && !"".equals(userId)) {
                    SPUtils.put(MyApplication.getConText(), "ws_baby_user_id", userId);
                }
            } catch (Exception unused) {
            }
            String memberStatus = userBean.getMemberStatus();
            if ("1".equals(memberStatus) || "1.0".equals(memberStatus)) {
                String level = userBean.getLevel();
                if ("1".equals(level) || "1.0".equals(level)) {
                    this.isHasAuthority = false;
                } else if ("2".equals(level) || SocializeConstants.PROTOCOL_VERSON.equals(level)) {
                    this.isHasAuthority = true;
                } else if ("3".equals(level) || "3.0".equals(level)) {
                    this.isHasAuthority = true;
                } else if ("4".equals(level) || "4.0".equals(level)) {
                    this.isHasAuthority = true;
                } else if ("5".equals(level) || "5.0".equals(level)) {
                    this.isHasAuthority = true;
                } else if ("10".equals(level) || "10.0".equals(level)) {
                    this.isHasAuthority = true;
                } else if ("11".equals(level) || "11.0".equals(level)) {
                    this.isHasAuthority = true;
                } else {
                    this.isHasAuthority = false;
                }
            } else {
                this.isHasAuthority = false;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        initData();
        getUser();
    }

    public void getUser() {
        ApiWrapper.getInstance().getUser(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFailure(FailureModel failureModel) {
            }

            /* JADX WARNING: Can't wrap try/catch for region: R(6:9|10|11|12|13|20) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x002c */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onFinish(com.wx.assistants.bean.ConmdBean r3) {
                /*
                    r2 = this;
                    if (r3 == 0) goto L_0x0036
                    int r0 = r3.getCode()     // Catch:{ Exception -> 0x0032 }
                    r1 = 1
                    if (r0 != r1) goto L_0x0010
                    java.lang.String r0 = r3.getMessage()     // Catch:{ Exception -> 0x0032 }
                    if (r0 == 0) goto L_0x0010
                    goto L_0x0036
                L_0x0010:
                    int r0 = r3.getCode()     // Catch:{ Exception -> 0x0032 }
                    if (r0 != 0) goto L_0x0036
                    com.google.gson.Gson r0 = new com.google.gson.Gson     // Catch:{ Exception -> 0x0032 }
                    r0.<init>()     // Catch:{ Exception -> 0x0032 }
                    java.lang.Object r3 = r3.getData()     // Catch:{ Exception -> 0x0032 }
                    java.lang.String r3 = r0.toJson(r3)     // Catch:{ Exception -> 0x0032 }
                    android.content.Context r0 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x002c }
                    java.lang.String r1 = "ws_baby_user_info"
                    com.wx.assistants.utils.SPUtils.put(r0, r1, r3)     // Catch:{ Exception -> 0x002c }
                L_0x002c:
                    com.wx.assistants.activity.CleanZombieDNDActivity r0 = com.wx.assistants.activity.CleanZombieDNDActivity.this     // Catch:{ Exception -> 0x0032 }
                    r0.setUserInfo(r3)     // Catch:{ Exception -> 0x0032 }
                    goto L_0x0036
                L_0x0032:
                    r3 = move-exception
                    r3.printStackTrace()
                L_0x0036:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.CleanZombieDNDActivity.AnonymousClass6.onFinish(com.wx.assistants.bean.ConmdBean):void");
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

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.activity.CleanZombieDNDActivity, android.content.Context] */
    @OnClick({2131297049, 2131297425, 2131297636, 2131297052})
    public void onViewClicked(View view) {
        String str;
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.无打扰检测死粉目前有三种模式可选-自定义消息、朋友圈辅助和朋友圈检测；\n2.自定义消息的原理是向好友发送一条消息，这条消息是自己编辑的，可以是节日祝福语，可以是自己的产品广告等，好友不会知道你在检测死粉，不会打扰到你的好友。\n3.朋友圈辅助是先查看好友朋友圈是否对你可见，如果你被屏蔽则进一步发送一条自定义消息，来确定对方是否是你的好友\n4.朋友圈检测直接检测好友朋友圈是否屏蔽你，如果被屏蔽设置为不删除，则标记为A000被屏蔽_昵称，被屏蔽的定义：1）好友的朋友圈对你不可见；2）好友微信号被封，无法登录的无效微信号。\n5.清理死粉过程中，非好友如果设置为不删除。则统一被标记为A000非好友_昵称。当然您还可以把非好友的放在特定的标签内。\n6.检测不存在误判，非好友只能看到朋友圈的前十条，可自行查看非好友朋友圈，辨别是不是您的好友。\n7.此功能为半年及以上会员专享。");
        } else if (id == 2131297425) {
            if ((this.dndModel == 0 || this.dndModel == 1) && (this.inviteUrl == null || "".equals(this.inviteUrl))) {
                this.inviteUrl = this.cleanZombieDndLayout.getContent();
                if (this.inviteUrl == null || "".equals(this.inviteUrl)) {
                    ToastUtils.showToast(this, "请输入自定义消息");
                    return;
                }
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
            if (this.dndModel == 0) {
                str = "27";
            } else {
                str = this.dndModel == 3 ? "58" : "38";
            }
            startCheck(str, true, new BaseActivity.OnStartCheckListener() {
                public void checkEnd() {
                    OperationParameterModel operationParameterModel = new OperationParameterModel();
                    if (CleanZombieDNDActivity.this.dndModel == 0) {
                        operationParameterModel.setTaskNum("27");
                        boolean unused = CleanZombieDNDActivity.this.isDeletedShield = false;
                        operationParameterModel.setSayContent(CleanZombieDNDActivity.this.inviteUrl + CleanZombieDNDActivity.this.getAppendSign());
                    } else if (CleanZombieDNDActivity.this.dndModel == 3) {
                        operationParameterModel.setTaskNum("58");
                        boolean unused2 = CleanZombieDNDActivity.this.isDeletedShield = false;
                        if (CleanZombieDNDActivity.this.inviteUrl != null) {
                            operationParameterModel.setSayContent(CleanZombieDNDActivity.this.inviteUrl + CleanZombieDNDActivity.this.getAppendSign());
                        } else {
                            operationParameterModel.setSayContent("");
                        }
                    } else {
                        if (CleanZombieDNDActivity.this.dndModel == 1) {
                            boolean unused3 = CleanZombieDNDActivity.this.isDeletedShield = false;
                            operationParameterModel.setSayContent(CleanZombieDNDActivity.this.inviteUrl + CleanZombieDNDActivity.this.getAppendSign());
                        } else {
                            operationParameterModel.setSayContent("");
                        }
                        operationParameterModel.setTaskNum("38");
                    }
                    operationParameterModel.setDndMode(CleanZombieDNDActivity.this.dndModel);
                    operationParameterModel.setDeleteNoFriends(CleanZombieDNDActivity.this.isDeleted);
                    operationParameterModel.setDeleteShieldFriends(CleanZombieDNDActivity.this.isDeletedShield);
                    operationParameterModel.setSendCardType(CleanZombieDNDActivity.this.sendCardType);
                    if (!CleanZombieDNDActivity.this.isDeleted || !CleanZombieDNDActivity.this.isDeletedShield) {
                        operationParameterModel.setSingLabelStr(CleanZombieDNDActivity.this.selectLabel);
                    } else {
                        operationParameterModel.setSingLabelStr("");
                    }
                    operationParameterModel.setIsDND(1);
                    operationParameterModel.setStartIndex(CleanZombieDNDActivity.this.startIndex);
                    operationParameterModel.setTagListPeoples(CleanZombieDNDActivity.this.jumpPersons);
                    operationParameterModel.setMaxOperaNum(5000);
                    operationParameterModel.setSpaceTime(0);
                    MyApplication.instance.setOperationParameterModel(operationParameterModel);
                    CleanZombieDNDActivity.this.startWX(operationParameterModel);
                }
            });
        } else if (id == 2131297636) {
            WebViewActivity.startActivity(this, "无打扰检测僵尸粉视频教程", QBangApis.VIDEO_CLEAN_ZOMBIE_DND);
        }
    }
}
