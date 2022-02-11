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
import com.google.gson.Gson;
import com.stub.StubApp;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.HotMaterialTextBean;
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
import com.wx.assistants.view.CirculateNumLayout;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FriendSendModeLayout;
import com.wx.assistants.view.IntimateModelLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class GroupSendVoiceToFriendsActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public int circulateMode = 0;
    @BindView(2131296493)
    CirculateNumLayout circulateNum;
    /* access modifiers changed from: private */
    public int circulateSize = 1;
    @BindView(2131296507)
    LinearLayout cleanEditText;
    /* access modifiers changed from: private */
    public String desc = "";
    @BindView(2131296614)
    EditText editDesc;
    @BindView(2131296616)
    EditText editLeavingMessage;
    @BindView(2131296619)
    EditText editNickName;
    @BindView(2131296624)
    EditText editTitle;
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
    @BindView(2131297427)
    Button startWxTag;
    /* access modifiers changed from: private */
    public String title = "";
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;
    /* access modifiers changed from: private */
    public String voice_path = "";

    static {
        StubApp.interface11(6735);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendVoiceToFriendsActivity] */
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
        try {
            this.voice_path = getIntent().getStringExtra("voice_path");
        } catch (Exception unused) {
        }
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("群发语音到好友");
        this.startWx.setText("启动微信自动群发语音");
        this.startWxTag.setText("启动微信手动转发语音给一个好友");
        this.executeTimeSpaceLayout.setExecuteTimeTitle("群发间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = GroupSendVoiceToFriendsActivity.this.spaceTime = i;
            }
        });
        this.friendSendModeLayout.setFriendSendTypeListener(new FriendSendModeLayout.FriendSendTypeListener() {
            public void sendType(int i) {
                int unused = GroupSendVoiceToFriendsActivity.this.sendCardType = i;
            }

            public void sendStartIndex(int i) {
                int unused = GroupSendVoiceToFriendsActivity.this.startIndex = i;
            }

            public void sendMembers(LinkedHashSet<String> linkedHashSet) {
                LinkedHashSet unused = GroupSendVoiceToFriendsActivity.this.jumpPersons = linkedHashSet;
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
                    GroupSendVoiceToFriendsActivity.this.editLeavingMessage.setText(materialStr);
                    if (materialStr != null && !"".equals(materialStr)) {
                        SPUtils.put(MyApplication.getConText(), "voice_friend_say_content", materialStr);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            getUser();
            String str = (String) SPUtils.get(MyApplication.getConText(), "voice_friend_say_content", "");
            if (str != null && !"".equals(str)) {
                this.editLeavingMessage.setText(str);
            }
            String str2 = (String) SPUtils.get(MyApplication.getConText(), "voice_to_friend_title", "");
            if (str2 != null && !"".equals(str2)) {
                this.editTitle.setText(str2);
            }
            String str3 = (String) SPUtils.get(MyApplication.getConText(), "voice_to_friend_desc", "");
            if (str3 != null && !"".equals(str3)) {
                this.editDesc.setText(str3);
            }
            String str4 = (String) SPUtils.get(MyApplication.getConText(), "voice_to_friend_nickname", "");
            if (str4 != null && !"".equals(str4)) {
                this.editNickName.setText(str4);
            }
            int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "voice_to_friends_num_all", 1)).intValue();
            int intValue2 = ((Integer) SPUtils.get(MyApplication.getConText(), "voice_to_friends_num_part", 1)).intValue();
            int intValue3 = ((Integer) SPUtils.get(MyApplication.getConText(), "voice_to_friends_num_shield", 1)).intValue();
            this.friendSendModeLayout.initSendIndexStrAll(true, "voice_to_friends_num_all", intValue);
            this.friendSendModeLayout.initSendIndexStrPart(true, "voice_to_friends_num_part", intValue2);
            this.friendSendModeLayout.initSendIndexStrShield(true, "voice_to_friends_num_shield", intValue3);
            this.friendSendModeLayout.initSendLabelStrAll(true, "voice_to_friends_label_all", (String) SPUtils.get(MyApplication.getConText(), "voice_to_friends_label_all", ""));
            this.friendSendModeLayout.initSendLabelStrPart(true, "voice_to_friends_label_part", (String) SPUtils.get(MyApplication.getConText(), "voice_to_friends_label_part", ""));
            this.friendSendModeLayout.initSendLabelStrShield(true, "voice_to_friends_label_shield", (String) SPUtils.get(MyApplication.getConText(), "voice_to_friends_label_shield", ""));
            this.friendSendModeLayout.initSendLabelMemberPart(true, "voice_to_friends_label_member_part", (String) SPUtils.get(MyApplication.getConText(), "voice_to_friends_label_member_part", ""));
            this.friendSendModeLayout.initSendLabelMemberShield(true, "voice_to_friends_label_member_shield", (String) SPUtils.get(MyApplication.getConText(), "voice_to_friends_label_member_shield", ""));
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("WS_BABY_Catch_12");
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendVoiceToFriendsActivity] */
    @OnClick({2131297427, 2131296507, 2131297049, 2131297425, 2131297636, 2131297052, 2131296950})
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
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.群发语音给所有好友，可选择设置只想发送的标签好友或者不想发送的标签好友。\n\n2.此功能是会员用户专享");
                return;
            case 2131297425:
                final String obj = this.editLeavingMessage.getText().toString();
                this.desc = this.editDesc.getText().toString();
                if (this.desc == null || "".equals(this.desc)) {
                    this.desc = "语音转发服务";
                }
                this.title = this.editTitle.getText().toString();
                if (this.title == null || "".equals(this.title)) {
                    this.title = "语音转发";
                }
                final String obj2 = this.editNickName.getText().toString();
                if (this.jumpPersons == null || this.jumpPersons.size() <= 0) {
                    if (this.sendCardType == 1) {
                        ToastUtils.showToast(this, "请设置您要群发的标签");
                        return;
                    } else if (this.sendCardType == 2) {
                        ToastUtils.showToast(this, "请设置您要屏蔽的标签");
                        return;
                    }
                }
                startCheck("49", true, new BaseActivity.OnStartCheckListener() {
                    public void checkEnd() {
                        OperationParameterModel operationParameterModel = new OperationParameterModel();
                        operationParameterModel.setTaskNum("49");
                        operationParameterModel.setVoiceNickName(obj2);
                        operationParameterModel.setVoiceTitle(GroupSendVoiceToFriendsActivity.this.title);
                        operationParameterModel.setVoiceDesc(GroupSendVoiceToFriendsActivity.this.desc);
                        operationParameterModel.setVoicePath(GroupSendVoiceToFriendsActivity.this.voice_path);
                        operationParameterModel.setSendCardType(GroupSendVoiceToFriendsActivity.this.sendCardType);
                        operationParameterModel.setStartIndex(GroupSendVoiceToFriendsActivity.this.startIndex);
                        operationParameterModel.setSayContent(obj + GroupSendVoiceToFriendsActivity.this.getAppendSign());
                        operationParameterModel.setSpaceTime(GroupSendVoiceToFriendsActivity.this.spaceTime);
                        operationParameterModel.setCirculateMode(GroupSendVoiceToFriendsActivity.this.circulateMode);
                        operationParameterModel.setOtherSendType(0);
                        if (GroupSendVoiceToFriendsActivity.this.circulateMode == 0) {
                            operationParameterModel.setCirculateOutSize(GroupSendVoiceToFriendsActivity.this.circulateSize);
                            operationParameterModel.setCirculateInnerSize(1);
                        } else {
                            operationParameterModel.setCirculateOutSize(1);
                            operationParameterModel.setCirculateInnerSize(GroupSendVoiceToFriendsActivity.this.circulateSize);
                        }
                        operationParameterModel.setTagListPeoples(GroupSendVoiceToFriendsActivity.this.jumpPersons);
                        operationParameterModel.setIntimateMode(GroupSendVoiceToFriendsActivity.this.intimateMode);
                        MyApplication.instance.setOperationParameterModel(operationParameterModel);
                        GroupSendVoiceToFriendsActivity.this.startWX(operationParameterModel);
                    }
                });
                return;
            case 2131297427:
                startCheck("49", true, new BaseActivity.OnStartCheckListener() {
                    public void checkEnd() {
                        String obj = GroupSendVoiceToFriendsActivity.this.editDesc.getText().toString();
                        if (obj == null || "".equals(obj)) {
                            obj = "语音转发服务";
                        }
                        String obj2 = GroupSendVoiceToFriendsActivity.this.editTitle.getText().toString();
                        if (obj2 == null || "".equals(obj2)) {
                            obj2 = "语音转发";
                        }
                        OperationParameterModel operationParameterModel = new OperationParameterModel();
                        operationParameterModel.setTaskNum("49");
                        operationParameterModel.setVoiceTitle(obj2);
                        operationParameterModel.setVoiceDesc(obj);
                        operationParameterModel.setVoicePath(GroupSendVoiceToFriendsActivity.this.voice_path);
                        operationParameterModel.setOtherSendType(1);
                        MyApplication.instance.setOperationParameterModel(operationParameterModel);
                        GroupSendVoiceToFriendsActivity.this.startWX(operationParameterModel);
                    }
                });
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "语音转发给好友", "https://material.abcvabc.com/20190329/48.mp4");
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

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.GroupSendVoiceToFriendsActivity] */
    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        try {
            String str = (String) SPUtils.get(this, "ws_baby_user_info", "");
            if (str != null && !"".equals(str)) {
                setUserInfo(str);
            }
        } catch (Exception unused) {
        }
    }

    public void setUserInfo(String str) {
        UserInfoBean.UserBean userBean = (UserInfoBean.UserBean) new Gson().fromJson(str, UserInfoBean.UserBean.class);
        if (userBean != null) {
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
                    com.wx.assistants.activity.GroupSendVoiceToFriendsActivity r0 = com.wx.assistants.activity.GroupSendVoiceToFriendsActivity.this     // Catch:{ Exception -> 0x0032 }
                    r0.setUserInfo(r3)     // Catch:{ Exception -> 0x0032 }
                    goto L_0x0036
                L_0x0032:
                    r3 = move-exception
                    r3.printStackTrace()
                L_0x0036:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.GroupSendVoiceToFriendsActivity.AnonymousClass12.onFinish(com.wx.assistants.bean.ConmdBean):void");
            }
        });
    }
}
