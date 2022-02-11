package com.wx.assistants.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.codbking.widget.OnSureLisener;
import com.stub.StubApp;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxTagEvent;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.CustomRadioLayoutBig;
import com.wx.assistants.view.EditTextWithScrollView;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FriendLikeCommentLayout;
import com.wx.assistants.view.TagCloudLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class OneKeyLikeCommentToFriendsActivity extends BaseActivity {
    @BindView(2131296507)
    LinearLayout cleanEditText;
    /* access modifiers changed from: private */
    public int cloneType = 0;
    @BindView(2131296568)
    CustomRadioLayoutBig customRadioLayoutBig;
    @BindView(2131296615)
    FrameLayout editLayout;
    @BindView(2131296616)
    EditTextWithScrollView editLeavingMessage;
    @BindView(2131296634)
    TextView endDate;
    /* access modifiers changed from: private */
    public String endDateStr = "";
    @BindView(2131296647)
    ExecuteTimeSpaceLayout executeTimeSpaceLayout;
    @BindView(2131296680)
    TagCloudLayout flowViewCategory;
    @BindView(2131296681)
    TagCloudLayout flowViewGroup;
    @BindView(2131296717)
    FriendLikeCommentLayout friendSendModeLayout;
    /* access modifiers changed from: private */
    public int intimateMode = 0;
    @BindView(2131296838)
    LinearLayout ivFinishDate;
    @BindView(2131296841)
    LinearLayout ivStartDate;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> jumpPersons = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public int likeCommentType = 0;
    @BindView(2131297028)
    LinearLayout modelLayout2;
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
    private String noSendStr = "";
    /* access modifiers changed from: private */
    public String sayContent = "";
    /* access modifiers changed from: private */
    public int sendType = 0;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    @BindView(2131297410)
    TextView startDate;
    /* access modifiers changed from: private */
    public String startDateStr = "";
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    private LinkedHashSet<String> tags = new LinkedHashSet<>();
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6755);
    }

    public void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("好友点赞/评论");
        this.startWx.setText("启动微信开始点赞/评论");
        this.executeTimeSpaceLayout.setExecuteTimeTitle("设置点赞/评论间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = OneKeyLikeCommentToFriendsActivity.this.spaceTime = i;
            }
        });
        this.friendSendModeLayout.setFriendSendTypeListener(new FriendLikeCommentLayout.FriendSendTypeListener() {
            public void sendMembers(LinkedHashSet<String> linkedHashSet) {
                LogUtils.log("WS_BABY_PEOPLES.sendMembers=" + linkedHashSet.toString());
                LinkedHashSet unused = OneKeyLikeCommentToFriendsActivity.this.jumpPersons = linkedHashSet;
            }

            public void sendStartIndex(int i) {
                LogUtils.log("WS_BABY_PEOPLES.sendStartIndex=" + i);
                int unused = OneKeyLikeCommentToFriendsActivity.this.startIndex = i;
            }

            public void sendType(int i) {
                LogUtils.log("WS_BABY_PEOPLES.sendType=" + i);
                int unused = OneKeyLikeCommentToFriendsActivity.this.sendType = i;
            }
        });
        this.editLeavingMessage.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                SPUtils.put(MyApplication.getConText(), "like_comment_content", editable.toString());
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyLikeCommentToFriendsActivity] */
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
    public void onEventMainThread(WxTagEvent wxTagEvent) {
        ArrayList<String> selectedTagList = wxTagEvent.getSelectedTagList();
        this.tags = new LinkedHashSet<>();
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
                this.tags.add(selectedTagList.get(i2));
                i = i2 + 1;
            }
            this.noSendStr = sb.toString();
            this.friendSendModeLayout.setSendStr(this.noSendStr);
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
            String str = (String) SPUtils.get(MyApplication.getConText(), "like_comment_content", "");
            if (str != null && !"".equals(str)) {
                this.editLeavingMessage.setText(str);
            }
            this.friendSendModeLayout.setSendTypeMode(this.sendType);
            int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "like_comment_num_all", 1)).intValue();
            int intValue2 = ((Integer) SPUtils.get(MyApplication.getConText(), "like_comment_num_part", 1)).intValue();
            int intValue3 = ((Integer) SPUtils.get(MyApplication.getConText(), "like_comment_num_shield", 1)).intValue();
            this.friendSendModeLayout.initSendIndexStrAll(true, "like_comment_num_all", intValue);
            this.friendSendModeLayout.initSendIndexStrPart(true, "like_comment_num_part", intValue2);
            this.friendSendModeLayout.initSendIndexStrShield(true, "like_comment_num_shield", intValue3);
            this.friendSendModeLayout.initSendLabelStrAll(true, "like_comment_label_all", (String) SPUtils.get(MyApplication.getConText(), "like_comment_label_all", ""));
            this.friendSendModeLayout.initSendLabelStrPart(true, "like_comment_label_part", (String) SPUtils.get(MyApplication.getConText(), "like_comment_label_part", ""));
            this.friendSendModeLayout.initSendLabelStrShield(true, "like_comment_label_shield", (String) SPUtils.get(MyApplication.getConText(), "like_comment_label_shield", ""));
            this.friendSendModeLayout.initSendLabelMemberPart(true, "like_comment_label_member_part", (String) SPUtils.get(MyApplication.getConText(), "like_comment_label_member_part", ""));
            this.friendSendModeLayout.initSendLabelMemberShield(true, "like_comment_label_member_shield", (String) SPUtils.get(MyApplication.getConText(), "like_comment_label_member_shield", ""));
        } catch (Exception e) {
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyLikeCommentToFriendsActivity] */
    @OnClick({2131296507, 2131297049, 2131297425, 2131297636, 2131297052, 2131296841, 2131296838})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296507:
                this.editLeavingMessage.setText("");
                return;
            case 2131296838:
                DialogUIUtils.selectDate(this, "选择结束点赞/评论日期", DateUtils.convertString2Date(this.endDate.getText().toString()), new OnSureLisener() {
                    public void onSure(Date date) {
                        String unused = OneKeyLikeCommentToFriendsActivity.this.endDateStr = DateUtils.convertDate2String(date);
                        OneKeyLikeCommentToFriendsActivity.this.endDate.setText(OneKeyLikeCommentToFriendsActivity.this.endDateStr);
                    }
                });
                return;
            case 2131296841:
                DialogUIUtils.selectDate(this, "选择开始点赞/评论日期", DateUtils.convertString2Date(this.startDate.getText().toString()), new OnSureLisener() {
                    public void onSure(Date date) {
                        String unused = OneKeyLikeCommentToFriendsActivity.this.startDateStr = DateUtils.convertDate2String(date);
                        OneKeyLikeCommentToFriendsActivity.this.startDate.setText(OneKeyLikeCommentToFriendsActivity.this.startDateStr);
                    }
                });
                return;
            case 2131297049:
                back();
                return;
            case 2131297052:
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.多人：是逐个向通讯录中好友，最新的一条朋友圈进行点赞/评论，支持先点赞后评论、只点赞、只评论三种类型。\n\n 2.点赞/评论停止时，系统会自动记录本次的位置，再次操作时，将接着上次位置，继续点赞/评论。\n\n3.一人：只向一个好友的朋友圈进行点赞/评论，支持先点赞后评论、只点赞、只评论三种类型。\n\n2.可设置点赞/评论的起止时间，默认只点赞此好友今天的朋友圈。\n\n3.您还可以设置点赞/评论的类别，比如只点赞/评论图片或只点赞/评论视频等等");
                return;
            case 2131297425:
                if (this.likeCommentType == 0 || this.likeCommentType == 2) {
                    this.sayContent = this.editLeavingMessage.getText().toString();
                    if (this.sayContent == null || "".equals(this.sayContent)) {
                        ToastUtils.showToast(this, "请设置评论内容");
                        return;
                    }
                } else {
                    this.sayContent = "";
                }
                if (this.intimateMode == 0) {
                    if (this.sendType == 1 && (this.jumpPersons == null || this.jumpPersons.size() <= 0)) {
                        ToastUtils.showToast(this, "请设置您要点赞/评论的标签");
                        return;
                    } else if (this.sendType != 2 || (this.jumpPersons != null && this.jumpPersons.size() > 0)) {
                        startCheck("51", true, new BaseActivity.OnStartCheckListener() {
                            public void checkEnd() {
                                LogUtils.log("WS_BABY_checkend");
                                OperationParameterModel operationParameterModel = new OperationParameterModel();
                                operationParameterModel.setTaskNum("51");
                                operationParameterModel.setTagListPeoples(OneKeyLikeCommentToFriendsActivity.this.jumpPersons);
                                operationParameterModel.setTagFriendType(OneKeyLikeCommentToFriendsActivity.this.sendType);
                                operationParameterModel.setStartIndex(OneKeyLikeCommentToFriendsActivity.this.startIndex);
                                operationParameterModel.setSayContent(OneKeyLikeCommentToFriendsActivity.this.sayContent + OneKeyLikeCommentToFriendsActivity.this.getAppendSign());
                                operationParameterModel.setSpaceTime(OneKeyLikeCommentToFriendsActivity.this.spaceTime);
                                operationParameterModel.setLikeCommentType(OneKeyLikeCommentToFriendsActivity.this.likeCommentType);
                                operationParameterModel.setIntimateMode(OneKeyLikeCommentToFriendsActivity.this.intimateMode);
                                MyApplication.instance.setOperationParameterModel(operationParameterModel);
                                OneKeyLikeCommentToFriendsActivity.this.startWX(operationParameterModel);
                            }
                        });
                        return;
                    } else {
                        ToastUtils.showToast(this, "请设置您要屏蔽的标签");
                        return;
                    }
                } else if (DateUtils.diff(DateUtils.convertString2Date(this.startDateStr), DateUtils.convertString2Date(this.endDateStr), 5) < 0) {
                    ToastUtils.showToast(this, "开始时间不能大于结束时间");
                    return;
                } else {
                    startCheck("52", true, new BaseActivity.OnStartCheckListener() {
                        public void checkEnd() {
                            OperationParameterModel operationParameterModel = new OperationParameterModel();
                            operationParameterModel.setTaskNum("52");
                            operationParameterModel.setCloneCircleType(OneKeyLikeCommentToFriendsActivity.this.cloneType);
                            operationParameterModel.setIntimateMode(OneKeyLikeCommentToFriendsActivity.this.intimateMode);
                            operationParameterModel.setSayContent(OneKeyLikeCommentToFriendsActivity.this.sayContent + OneKeyLikeCommentToFriendsActivity.this.getAppendSign());
                            operationParameterModel.setStartDate(OneKeyLikeCommentToFriendsActivity.this.startDateStr);
                            operationParameterModel.setEndDate(OneKeyLikeCommentToFriendsActivity.this.endDateStr);
                            operationParameterModel.setSpaceTime(OneKeyLikeCommentToFriendsActivity.this.spaceTime);
                            operationParameterModel.setLikeCommentType(OneKeyLikeCommentToFriendsActivity.this.likeCommentType);
                            MyApplication.instance.setOperationParameterModel(operationParameterModel);
                            OneKeyLikeCommentToFriendsActivity.this.startWX(operationParameterModel);
                        }
                    });
                    return;
                }
            case 2131297636:
                WebViewActivity.startActivity(this, "好友点赞/评论", QBangApis.VIDEO_FRIEND_LIKE_COMMENT);
                return;
            default:
                return;
        }
    }
}
