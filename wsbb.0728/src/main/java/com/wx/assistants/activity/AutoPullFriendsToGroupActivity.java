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
import com.wx.assistants.bean.LabelBean;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxSingleTagEvent;
import com.wx.assistants.bean.WxTagEvent;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.CustomSpeedLayout;
import com.wx.assistants.view.FriendSendModeLayout2;
import com.wx.assistants.view.NumSettingOnlyLayout;
import com.wx.assistants.webview.WebViewActivity;
import com.yalantis.ucrop.view.CropImageView;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AutoPullFriendsToGroupActivity extends BaseActivity {
    private int canMaxNum = 5000;
    @BindView(2131296572)
    CustomSpeedLayout customSpeedLayout;
    /* access modifiers changed from: private */
    public int defaultNum = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
    @BindView(2131296616)
    EditText editLeavingMessage;
    @BindView(2131296618)
    LinearLayout editLeavingMessageLayout;
    @BindView(2131296718)
    FriendSendModeLayout2 friendSendModeLayout;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> jumpPersons = new LinkedHashSet<>();
    private LabelBean labelBean = new LabelBean();
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297078)
    NumSettingOnlyLayout numSettingOnlyLayout;
    /* access modifiers changed from: private */
    public int pullType = 0;
    /* access modifiers changed from: private */
    public int scrollSpeed = CustomSpeedLayout.DEFAULT_SPEED;
    @BindView(2131297379)
    ImageView showImg;
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> tagList = new LinkedHashSet<>();
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6711);
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("自动拉好友进群");
        this.startWx.setText("启动微信拉好友进群");
        this.friendSendModeLayout.setFriendSendTypeListener(new FriendSendModeLayout2.FriendSendTypeListener() {
            public void sendMembers(LinkedHashSet<String> linkedHashSet) {
                LogUtils.log("WS_BABY_PEOPLES.sendMembers=" + linkedHashSet.toString());
                LinkedHashSet unused = AutoPullFriendsToGroupActivity.this.jumpPersons = linkedHashSet;
            }

            public void sendStartIndex(int i) {
                LogUtils.log("WS_BABY_PEOPLES.sendStartIndex=" + i);
                int unused = AutoPullFriendsToGroupActivity.this.startIndex = i;
            }

            public void sendTag(LinkedHashSet<String> linkedHashSet) {
                LinkedHashSet unused = AutoPullFriendsToGroupActivity.this.tagList = linkedHashSet;
            }

            public void sendType(int i) {
                LogUtils.log("WS_BABY_PEOPLES.sendType=" + i);
                int unused = AutoPullFriendsToGroupActivity.this.pullType = i;
            }
        });
        this.numSettingOnlyLayout.setNum(this.defaultNum, this.canMaxNum, "拉人数量");
        this.numSettingOnlyLayout.setOnResultListener(new NumSettingOnlyLayout.OnResultListener() {
            public void result(int i) {
                int unused = AutoPullFriendsToGroupActivity.this.defaultNum = i;
            }
        });
        this.customSpeedLayout.setFunction(CustomSpeedLayout.FUNCTION.PULL_FRIEND_TO_GROUP);
        this.customSpeedLayout.setOnSignSeekBarListener(new CustomSpeedLayout.OnSignSeekBarListener() {
            public void progress(int i) {
                int unused = AutoPullFriendsToGroupActivity.this.scrollSpeed = i;
                PrintStream printStream = System.out;
                printStream.println("WS_BABY_PROGRESS_" + i);
            }
        });
        this.editLeavingMessage.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "pull_friend_to_group_say_content", editable.toString());
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoPullFriendsToGroupActivity] */
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
    public void onEventMainThread(WxSingleTagEvent wxSingleTagEvent) {
        this.labelBean = wxSingleTagEvent.getSelectedLabelBean();
        this.tagList = new LinkedHashSet<>();
        String labelName = this.labelBean.getLabelName();
        this.tagList.add(labelName);
        if (labelName == null || "".equals(labelName)) {
            this.friendSendModeLayout.setSendStr("");
        } else {
            this.friendSendModeLayout.setSendStr(labelName);
        }
        if (wxSingleTagEvent.getSelectedPeopleList() != null) {
            this.jumpPersons = new LinkedHashSet<>();
            this.jumpPersons.addAll(wxSingleTagEvent.getSelectedPeopleList());
        } else {
            this.jumpPersons = new LinkedHashSet<>();
        }
        this.friendSendModeLayout.setSendMember(this.jumpPersons);
    }

    @Subscribe
    public void onEventMainThread(WxTagEvent wxTagEvent) {
        ArrayList<String> selectedTagList = wxTagEvent.getSelectedTagList();
        this.tagList = new LinkedHashSet<>();
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
                this.tagList.add(selectedTagList.get(i2));
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
        this.friendSendModeLayout.setSendTypeMode(this.pullType);
        int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "pull_fri_to_group_num_all", 1)).intValue();
        int intValue2 = ((Integer) SPUtils.get(MyApplication.getConText(), "pull_fri_to_group_num_part", 1)).intValue();
        int intValue3 = ((Integer) SPUtils.get(MyApplication.getConText(), "pull_fri_to_group_num_shield", 1)).intValue();
        this.friendSendModeLayout.initSendIndexStrAll(true, "pull_fri_to_group_num_all", intValue);
        this.friendSendModeLayout.initSendIndexStrPart(true, "pull_fri_to_group_num_part", intValue2);
        this.friendSendModeLayout.initSendIndexStrShield(true, "pull_fri_to_group_num_shield", intValue3);
        this.friendSendModeLayout.initSendLabelStrAll(true, "pull_fri_to_group_label_all", (String) SPUtils.get(MyApplication.getConText(), "pull_fri_to_group_label_all", ""));
        this.friendSendModeLayout.initSendLabelStrPart(true, "pull_fri_to_group_label_part", (String) SPUtils.get(MyApplication.getConText(), "pull_fri_to_group_label_part", ""));
        this.friendSendModeLayout.initSendLabelStrShield(true, "pull_fri_to_group_label_shield", (String) SPUtils.get(MyApplication.getConText(), "pull_fri_to_group_label_shield", ""));
        this.friendSendModeLayout.initSendLabelMemberPart(true, "pull_fri_to_group_label_member_part", (String) SPUtils.get(MyApplication.getConText(), "pull_fri_to_group_label_member_part", ""));
        this.friendSendModeLayout.initSendLabelMemberShield(true, "pull_fri_to_group_label_member_shield", (String) SPUtils.get(MyApplication.getConText(), "pull_fri_to_group_label_member_shield", ""));
        String str = (String) SPUtils.get(MyApplication.getConText(), "pull_friend_to_group_say_content", "");
        if (str != null && !"".equals(str)) {
            this.editLeavingMessage.setText(str);
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.AutoPullFriendsToGroupActivity] */
    @OnClick({2131297425, 2131297049, 2131297636, 2131297052})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.自动拉所有好友进群功能，可设置拉人进群的起点，比如你有2000好友，第一次拉了1000人，停止时，后续可以设置从1001人开始继续拉好友进群\n\n2.自动拉部分好友进群功能，目前只能一次拉一个标签好友进群.您还可以选择要过滤的标签好友，支持多标签屏蔽\n");
        } else if (id != 2131297425) {
            if (id == 2131297636) {
                WebViewActivity.startActivity(this, "自动拉人进群视频教程", QBangApis.VIDEO_PULL_TO_GROUP);
            }
        } else if ((this.pullType == 1 || this.pullType == 2) && (this.tagList == null || this.tagList.size() == 0)) {
            ToastUtils.showToast(this, "请设置标签");
        } else {
            startCheck("8", true, new BaseActivity.OnStartCheckListener() {
                public void checkEnd() {
                    String obj = AutoPullFriendsToGroupActivity.this.editLeavingMessage.getText().toString();
                    OperationParameterModel operationParameterModel = new OperationParameterModel();
                    operationParameterModel.setTaskNum("8");
                    operationParameterModel.setStartIndex(AutoPullFriendsToGroupActivity.this.startIndex);
                    operationParameterModel.setAutoPullType(AutoPullFriendsToGroupActivity.this.pullType);
                    operationParameterModel.setMaxOperaNum(AutoPullFriendsToGroupActivity.this.defaultNum);
                    operationParameterModel.setScrollSpeed(AutoPullFriendsToGroupActivity.this.scrollSpeed);
                    operationParameterModel.setSayContent(obj);
                    operationParameterModel.setTagListNames(AutoPullFriendsToGroupActivity.this.tagList);
                    operationParameterModel.setTagListPeoples(AutoPullFriendsToGroupActivity.this.jumpPersons);
                    MyApplication.instance.setOperationParameterModel(operationParameterModel);
                    AutoPullFriendsToGroupActivity.this.startWX(operationParameterModel);
                }
            });
        }
    }
}
