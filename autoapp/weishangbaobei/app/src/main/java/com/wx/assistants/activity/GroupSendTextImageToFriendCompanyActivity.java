package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
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
import com.wx.assistants.bean.WxTagCompanyEvent;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.EditTextWithScrollView;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FriendSendModeLayoutCompany;
import com.wx.assistants.view.IntimateModelLayout;
import com.wx.assistants.view.SelectPicVideoCompanyLayout;
import com.wx.assistants.view.SendContentLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class GroupSendTextImageToFriendCompanyActivity extends BaseActivity {
    @BindView(2131296507)
    LinearLayout cleanEditText;
    @BindView(2131296616)
    EditTextWithScrollView editLeavingMessage;
    @BindView(2131296647)
    ExecuteTimeSpaceLayout executeTimeSpaceLayout;
    @BindView(2131296717)
    FriendSendModeLayoutCompany friendSendModeLayout;
    /* access modifiers changed from: private */
    public int intimateMode = 1;
    @BindView(2131296813)
    IntimateModelLayout intimateModelLayout;
    /* access modifiers changed from: private */
    public int isOriginPic = 1;
    @BindView(2131296950)
    LinearLayout linearLayoutTemplate;
    /* access modifiers changed from: private */
    public ArrayList<String> mPicList = new ArrayList<>();
    /* access modifiers changed from: private */
    public int msgType = 2;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297054)
    TextView navTitle;
    private String noSendStr = "";
    @BindView(2131297131)
    LinearLayout picLayout;
    private String sayContent = "";
    @BindView(2131297315)
    SelectPicVideoCompanyLayout selectPicVideoLayout;
    @BindView(2131297333)
    SendContentLayout sendContentLayout;
    /* access modifiers changed from: private */
    public int sendOrder = 0;
    /* access modifiers changed from: private */
    public int sendPicVideoCode = 0;
    /* access modifiers changed from: private */
    public int sendType = 0;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> tags = new LinkedHashSet<>();
    @BindView(2131297464)
    LinearLayout textLayout;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6733);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendTextImageToFriendCompanyActivity] */
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
        try {
            this.sayContent = (String) SPUtils.get(MyApplication.getConText(), "company_text_img_friend_content", "");
            if (this.sayContent != null && !"".equals(this.sayContent)) {
                this.editLeavingMessage.setText(this.sayContent);
            }
            this.friendSendModeLayout.setSendTypeMode(this.sendType);
            int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "company_one_bye_one_text_img_num_all", 1)).intValue();
            int intValue2 = ((Integer) SPUtils.get(MyApplication.getConText(), "company_one_bye_one_text_img_num_part", 1)).intValue();
            int intValue3 = ((Integer) SPUtils.get(MyApplication.getConText(), "company_one_bye_one_text_img_num_shield", 1)).intValue();
            this.friendSendModeLayout.initSendIndexStrAll(true, "company_one_bye_one_text_img_num_all", intValue);
            this.friendSendModeLayout.initSendIndexStrPart(true, "company_one_bye_one_text_img_num_part", intValue2);
            this.friendSendModeLayout.initSendIndexStrShield(true, "company_one_bye_one_text_img_num_shield", intValue3);
            this.friendSendModeLayout.initSendLabelStrAll(true, "company_one_bye_one_text_img_label_all", (String) SPUtils.get(MyApplication.getConText(), "company_one_bye_one_text_img_label_all", ""));
            this.friendSendModeLayout.initSendLabelStrPart(true, "company_one_bye_one_text_img_label_part", (String) SPUtils.get(MyApplication.getConText(), "company_one_bye_one_text_img_label_part", ""));
            this.friendSendModeLayout.initSendLabelStrShield(true, "company_one_bye_one_text_img_label_shield", (String) SPUtils.get(MyApplication.getConText(), "company_one_bye_one_text_img_label_shield", ""));
        } catch (Exception unused) {
        }
    }

    public void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("群发图文给好友");
        this.startWx.setText("启动微信开始群发");
        this.executeTimeSpaceLayout.setExecuteTimeTitle("群发间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = GroupSendTextImageToFriendCompanyActivity.this.spaceTime = i;
            }
        });
        this.friendSendModeLayout.setFriendSendTypeListener(new FriendSendModeLayoutCompany.FriendSendTypeListener() {
            public void sendType(int i) {
                LogUtils.log("WS_BABY_PEOPLES.sendType=" + i);
                int unused = GroupSendTextImageToFriendCompanyActivity.this.sendType = i;
            }

            public void sendStartIndex(int i) {
                LogUtils.log("WS_BABY_PEOPLES.sendStartIndex=" + i);
                int unused = GroupSendTextImageToFriendCompanyActivity.this.startIndex = i;
            }

            public void sendTags(LinkedHashSet<String> linkedHashSet) {
                LinkedHashSet unused = GroupSendTextImageToFriendCompanyActivity.this.tags = linkedHashSet;
            }
        });
        this.sendContentLayout.setCirculateModelListener(new SendContentLayout.CirculateModelListener() {
            public void circulateMode(int i) {
                int unused = GroupSendTextImageToFriendCompanyActivity.this.msgType = i;
                GroupSendTextImageToFriendCompanyActivity.this.updateLayout();
            }
        });
        this.editLeavingMessage.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "company_text_img_friend_content", editable.toString());
                }
            }
        });
        this.intimateModelLayout.setIntimateModelListener(new IntimateModelLayout.IntimateModelListener() {
            public void intimateMode(int i) {
                int unused = GroupSendTextImageToFriendCompanyActivity.this.intimateMode = i;
            }
        });
        this.intimateModelLayout.setSelectMode(this.intimateMode);
        this.selectPicVideoLayout.setFromFriend(this, true);
        this.selectPicVideoLayout.setOnPicVideoSelectListener(new SelectPicVideoCompanyLayout.OnPicVideoSelectListener() {
            public void mode(int i, int i2, int i3, ArrayList<String> arrayList) {
                int unused = GroupSendTextImageToFriendCompanyActivity.this.isOriginPic = i;
                int unused2 = GroupSendTextImageToFriendCompanyActivity.this.sendOrder = i2;
                int unused3 = GroupSendTextImageToFriendCompanyActivity.this.sendPicVideoCode = i3;
                ArrayList unused4 = GroupSendTextImageToFriendCompanyActivity.this.mPicList = arrayList;
            }
        });
    }

    public void updateLayout() {
        if (this.msgType == 0) {
            this.textLayout.setVisibility(0);
            this.picLayout.setVisibility(8);
            this.selectPicVideoLayout.setSendMsgType(this, 0);
        } else if (this.msgType == 1) {
            this.textLayout.setVisibility(8);
            this.picLayout.setVisibility(0);
            this.selectPicVideoLayout.setSendMsgType(this, 1);
        } else if (this.msgType == 2) {
            this.textLayout.setVisibility(0);
            this.picLayout.setVisibility(0);
            this.selectPicVideoLayout.setSendMsgType(this, 2);
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendTextImageToFriendCompanyActivity] */
    @OnClick({2131296507, 2131297049, 2131297425, 2131297636, 2131296616, 2131296950, 2131297052})
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
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.此功能是向我的企业客户中的好友逐个发送消息，支持文字、图片或视频同时发送。\n\n2.群发停止时，若群发的内容不改变，则默认记录上次群发的位置，再次群发时，将接着上次没有群发完的好友，继续群发，以保证不重复发送\n");
                return;
            case 2131297065:
                Intent intent = new Intent(this, ObtainTagActivity.class);
                intent.putExtra("selects", this.noSendStr);
                startActivity(intent);
                return;
            case 2131297425:
                if (!PerformClickUtils.isFastClick()) {
                    if (!checkCompanyWxApp(this)) {
                        ToastUtils.showToast(this, "请先安装企业微信！");
                        return;
                    }
                    this.sayContent = this.editLeavingMessage.getText().toString();
                    if (this.msgType == 0) {
                        if (this.sayContent == null || "".equals(this.sayContent)) {
                            ToastUtils.showToast(this, "请输入文字内容！");
                            return;
                        }
                        SPUtils.put(MyApplication.getConText(), "company_text_img_friend_content", this.sayContent);
                    } else if (this.msgType == 1) {
                        if (this.mPicList == null || this.mPicList.size() == 0) {
                            if (this.sendPicVideoCode == 0) {
                                ToastUtils.showToast(this, "请选择图片！");
                                return;
                            } else if (this.sendPicVideoCode == 1) {
                                ToastUtils.showToast(this, "请选择视频！");
                                return;
                            } else {
                                return;
                            }
                        }
                    } else if (this.msgType == 2) {
                        if (this.sayContent == null || "".equals(this.sayContent)) {
                            ToastUtils.showToast(this, "请输入文字内容！");
                            return;
                        } else if (this.mPicList != null && this.mPicList.size() != 0) {
                            SPUtils.put(MyApplication.getConText(), "company_text_img_friend_content", this.sayContent);
                        } else if (this.sendPicVideoCode == 0) {
                            ToastUtils.showToast(this, "请选择图片！");
                            return;
                        } else if (this.sendPicVideoCode == 1) {
                            ToastUtils.showToast(this, "请选择视频！");
                            return;
                        } else {
                            return;
                        }
                    }
                    if (this.tags != null && this.tags.size() > 0) {
                        LogUtils.log("WS_BABY_PEOPLES=" + this.tags.toString());
                    } else if (this.sendType == 1) {
                        ToastUtils.showToast(this, "请设置您要群发的标签");
                        return;
                    } else if (this.sendType == 2) {
                        ToastUtils.showToast(this, "请设置您要屏蔽的标签");
                        return;
                    }
                    startCheck("1005", true, new BaseActivity.OnStartCheckListener() {
                        public void checkEnd() {
                            LogUtils.log("WS_BABY_checkend");
                            if (GroupSendTextImageToFriendCompanyActivity.this.msgType == 1 || GroupSendTextImageToFriendCompanyActivity.this.msgType == 2) {
                                GroupSendTextImageToFriendCompanyActivity.this.showLoadingDialog("正在处理", false);
                                GroupSendTextImageToFriendCompanyActivity.this.savePicImages(GroupSendTextImageToFriendCompanyActivity.this.mPicList, new BaseActivity.OnLoadingDownListener() {
                                    public void downEnd() {
                                        GroupSendTextImageToFriendCompanyActivity.this.dismissLoadingDialog();
                                        GroupSendTextImageToFriendCompanyActivity.this.startExecute();
                                    }

                                    public void progress(int i) {
                                        GroupSendTextImageToFriendCompanyActivity groupSendTextImageToFriendCompanyActivity = GroupSendTextImageToFriendCompanyActivity.this;
                                        groupSendTextImageToFriendCompanyActivity.updateLoadingDialog("正在处理 " + i + "/" + GroupSendTextImageToFriendCompanyActivity.this.mPicList.size());
                                    }
                                });
                                return;
                            }
                            GroupSendTextImageToFriendCompanyActivity.this.startExecute();
                        }
                    });
                    return;
                }
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "群发图文到我的客户", QBangApis.VIDEO_COMPANY_IMG_TEXT_CUSTOM);
                return;
            default:
                return;
        }
    }

    public void startExecute() {
        OperationParameterModel operationParameterModel = new OperationParameterModel();
        operationParameterModel.setTaskNum("1005");
        operationParameterModel.setIsOriginPic(this.isOriginPic);
        operationParameterModel.setTagListNames(this.tags);
        operationParameterModel.setFriendSendTag(0);
        operationParameterModel.setTagFriendType(this.sendType);
        operationParameterModel.setStartIndex(this.startIndex);
        operationParameterModel.setSendOrder(this.sendOrder);
        operationParameterModel.setMessageSendType(this.msgType);
        operationParameterModel.setSayContent(this.sayContent + getAppendSign());
        operationParameterModel.setLocalImgUrl("xxx.png");
        operationParameterModel.setMaterialPicCount(this.mPicList.size());
        operationParameterModel.setSpaceTime(this.spaceTime);
        operationParameterModel.setSendCardType(this.sendPicVideoCode);
        operationParameterModel.setIntimateMode(this.intimateMode);
        MyApplication.instance.setOperationParameterModel(operationParameterModel);
        startWXCompany(operationParameterModel);
    }

    @Subscribe
    public void onEventMainThread(final HotMaterialTextBean hotMaterialTextBean) {
        runOnUiThread(new Runnable() {
            public void run() {
                final String materialStr = hotMaterialTextBean.getMaterialStr();
                if (materialStr != null && !"".equals(materialStr)) {
                    GroupSendTextImageToFriendCompanyActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            GroupSendTextImageToFriendCompanyActivity.this.editLeavingMessage.setText(materialStr);
                            if (materialStr != null && !"".equals(materialStr)) {
                                SPUtils.put(MyApplication.getConText(), "company_text_img_friend_content", materialStr);
                            }
                        }
                    });
                }
            }
        });
    }

    @Subscribe
    public void onEventMainThread(WxTagCompanyEvent wxTagCompanyEvent) {
        PrintStream printStream = System.out;
        printStream.println("WS_BABY_TAGS = " + this.tags);
        ArrayList<String> selectedTagList = wxTagCompanyEvent.getSelectedTagList();
        this.tags = new LinkedHashSet<>();
        if (selectedTagList == null || selectedTagList.size() <= 0) {
            this.friendSendModeLayout.setSendStr("");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < selectedTagList.size(); i++) {
            sb.append(selectedTagList.get(i) + ";");
            this.tags.add(selectedTagList.get(i));
        }
        PrintStream printStream2 = System.out;
        printStream2.println("WS_BABY_TAGS = " + this.tags);
        this.noSendStr = sb.toString();
        this.friendSendModeLayout.setSendStr(this.noSendStr);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10 && i2 == 11) {
            this.selectPicVideoLayout.setOnActivityResult(intent);
        }
    }
}
