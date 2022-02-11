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
import com.wx.assistants.bean.WxGroupEvent;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.CirculateModelLayout;
import com.wx.assistants.view.CirculateNumLayout;
import com.wx.assistants.view.CustomRadioLayout;
import com.wx.assistants.view.EditTextWithScrollView;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.GroupSendModeLayoutH;
import com.wx.assistants.view.SelectPicVideoLayout;
import com.wx.assistants.view.SendContentLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class GroupSendTextImageToGroupActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public int circulateMode = 0;
    @BindView(2131296492)
    CirculateModelLayout circulateModeLayout;
    @BindView(2131296493)
    CirculateNumLayout circulateNum;
    /* access modifiers changed from: private */
    public int circulateSize = 1;
    @BindView(2131296507)
    LinearLayout cleanEditText;
    @BindView(2131296616)
    EditTextWithScrollView editLeavingMessage;
    @BindView(2131296647)
    ExecuteTimeSpaceLayout executeTimeSpaceLayout;
    /* access modifiers changed from: private */
    public String groupNames = "";
    @BindView(2131296736)
    GroupSendModeLayoutH groupSendModeLayout;
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
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297131)
    LinearLayout picLayout;
    private String sayContent = "";
    @BindView(2131297315)
    SelectPicVideoLayout selectPicVideoLayout;
    /* access modifiers changed from: private */
    public int sendCardType = 0;
    @BindView(2131297333)
    SendContentLayout sendContentLayout;
    /* access modifiers changed from: private */
    public int sendGroupMethod = 0;
    @BindView(2131297334)
    CustomRadioLayout sendGroupMethodLayout;
    /* access modifiers changed from: private */
    public int sendOrder = 0;
    /* access modifiers changed from: private */
    public int sendPicVideoCode = 0;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297464)
    LinearLayout textLayout;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6734);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendTextImageToGroupActivity] */
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

    public void initData() {
        try {
            String str = (String) SPUtils.get(MyApplication.getConText(), "group_send_text_img_group_content", "");
            if (str != null && !"".equals(str)) {
                this.editLeavingMessage.setText(str);
            }
            int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "img_text_to_group_num_all", 1)).intValue();
            int intValue2 = ((Integer) SPUtils.get(MyApplication.getConText(), "img_text_to_group_num_part", 1)).intValue();
            int intValue3 = ((Integer) SPUtils.get(MyApplication.getConText(), "img_text_to_group_num_shield", 1)).intValue();
            this.groupSendModeLayout.initSendIndexStrAll(true, "img_text_to_group_num_all", intValue);
            this.groupSendModeLayout.initSendIndexStrPart(true, "img_text_to_group_num_part", intValue2);
            this.groupSendModeLayout.initSendIndexStrShield(true, "img_text_to_group_num_shield", intValue3);
            this.groupSendModeLayout.initSendLabelStrAll(true, "img_text_to_group_label_all", (String) SPUtils.get(MyApplication.getConText(), "img_text_to_group_label_all", ""));
            this.groupSendModeLayout.initSendLabelStrPart(true, "img_text_to_group_label_part", (String) SPUtils.get(MyApplication.getConText(), "img_text_to_group_label_part", ""));
            this.groupSendModeLayout.initSendLabelStrShield(true, "img_text_to_group_label_shield", (String) SPUtils.get(MyApplication.getConText(), "img_text_to_group_label_shield", ""));
        } catch (Exception unused) {
        }
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("群发图文给微信群");
        this.startWx.setText("启动微信开始群发");
        this.selectPicVideoLayout.setFromFriend(this, false);
        this.selectPicVideoLayout.setOnPicVideoSelectListener(new SelectPicVideoLayout.OnPicVideoSelectListener() {
            public void mode(int i, int i2, int i3, ArrayList<String> arrayList) {
                int unused = GroupSendTextImageToGroupActivity.this.isOriginPic = i;
                int unused2 = GroupSendTextImageToGroupActivity.this.sendOrder = i2;
                int unused3 = GroupSendTextImageToGroupActivity.this.sendPicVideoCode = i3;
                ArrayList unused4 = GroupSendTextImageToGroupActivity.this.mPicList = arrayList;
            }
        });
        this.executeTimeSpaceLayout.setExecuteTimeTitle("间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = GroupSendTextImageToGroupActivity.this.spaceTime = i;
            }
        });
        this.editLeavingMessage.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "group_send_text_img_group_content", editable.toString());
                }
            }
        });
        this.circulateNum.setCirculateNumListener(new CirculateNumLayout.CirculateNumListener() {
            public void circulateNum(int i) {
                int unused = GroupSendTextImageToGroupActivity.this.circulateSize = i;
            }
        });
        this.circulateModeLayout.setCirculateModelListener(new CirculateModelLayout.CirculateModelListener() {
            public void circulateMode(int i) {
                int unused = GroupSendTextImageToGroupActivity.this.circulateMode = i;
            }
        });
        this.groupSendModeLayout.setGroupSendTypeListener(new GroupSendModeLayoutH.GroupSendTypeListener() {
            public void sendType(int i) {
                int unused = GroupSendTextImageToGroupActivity.this.sendCardType = i;
            }

            public void sendStartIndex(int i) {
                int unused = GroupSendTextImageToGroupActivity.this.startIndex = i;
            }

            public void sendMembers(String str) {
                String unused = GroupSendTextImageToGroupActivity.this.groupNames = str;
            }
        });
        this.sendGroupMethodLayout.setText("找群方式", "翻页找", 60, "搜索找", 60);
        this.sendGroupMethodLayout.setDefaultSelect(0);
        this.sendGroupMethodLayout.setOnModelSelectListener(new CustomRadioLayout.OnModelSelectListener() {
            public void mode(int i) {
                int unused = GroupSendTextImageToGroupActivity.this.sendGroupMethod = i;
            }
        });
        this.sendContentLayout.setCirculateModelListener(new SendContentLayout.CirculateModelListener() {
            public void circulateMode(int i) {
                int unused = GroupSendTextImageToGroupActivity.this.msgType = i;
                GroupSendTextImageToGroupActivity.this.updateLayout();
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

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendTextImageToGroupActivity] */
    @OnClick({2131296507, 2131297049, 2131297425, 2131297636, 2131297052, 2131296616, 2131296950})
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
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.此功能是向通讯录保存的群逐个发送消息。消息类型支持文字、图片或视频。若发送的内容包含视频，根据微信的规则，视频时长不能超过5分钟。\n\n2.为了防止漏群情况，使用此功能前，请确保群名必须保证唯一\n\n3.可设置循环方式和循环次数。假如选择A,B两个群发送，循环次数2次为例。外循环：AB两群发完一遍，AB两群在发一遍。内循环：A群内连发2遍后，B群内也连发两遍。\n\n4.该功能是会员用户专享。\n");
                return;
            case 2131297425:
                if (!PerformClickUtils.isFastClick()) {
                    this.sayContent = this.editLeavingMessage.getText().toString();
                    if (this.msgType == 0) {
                        if (this.sayContent == null || "".equals(this.sayContent)) {
                            ToastUtils.showToast(this, "请输入文字内容！");
                            return;
                        }
                    } else if (this.msgType == 1) {
                        if (this.mPicList == null || this.mPicList.size() == 0) {
                            if (this.sendPicVideoCode == 0) {
                                ToastUtils.showToast(this, "请选择图片！");
                                return;
                            } else if (this.sendPicVideoCode == 1) {
                                ToastUtils.showToast(this, "请选择视频！");
                                return;
                            } else {
                                ToastUtils.showToast(this, "请选择图片/视频！");
                                return;
                            }
                        }
                    } else if (this.msgType == 2) {
                        if (this.sayContent == null || "".equals(this.sayContent)) {
                            ToastUtils.showToast(this, "请输入文字内容！");
                            return;
                        } else if (this.mPicList == null || this.mPicList.size() == 0) {
                            if (this.sendPicVideoCode == 0) {
                                ToastUtils.showToast(this, "请选择图片！");
                                return;
                            } else if (this.sendPicVideoCode == 1) {
                                ToastUtils.showToast(this, "请选择视频！");
                                return;
                            } else {
                                ToastUtils.showToast(this, "请选择图片/视频！");
                                return;
                            }
                        }
                    }
                    if (this.groupNames == null || "".equals(this.groupNames)) {
                        if (this.sendCardType == 1) {
                            ToastUtils.showToast(MyApplication.getConText(), "请设置您要群发的群");
                            return;
                        } else if (this.sendCardType == 2) {
                            ToastUtils.showToast(MyApplication.getConText(), "请设置您要屏蔽的群");
                            return;
                        }
                    }
                    startCheck("6", true, new BaseActivity.OnStartCheckListener() {
                        public void checkEnd() {
                            if (GroupSendTextImageToGroupActivity.this.msgType == 2 || GroupSendTextImageToGroupActivity.this.msgType == 1) {
                                GroupSendTextImageToGroupActivity.this.showLoadingDialog("正在处理", false);
                                GroupSendTextImageToGroupActivity.this.savePicImages(GroupSendTextImageToGroupActivity.this.mPicList, new BaseActivity.OnLoadingDownListener() {
                                    public void downEnd() {
                                        GroupSendTextImageToGroupActivity.this.dismissLoadingDialog();
                                        GroupSendTextImageToGroupActivity.this.executeStartWX();
                                    }

                                    public void progress(int i) {
                                        GroupSendTextImageToGroupActivity groupSendTextImageToGroupActivity = GroupSendTextImageToGroupActivity.this;
                                        groupSendTextImageToGroupActivity.updateLoadingDialog("正在处理 " + i + "/" + GroupSendTextImageToGroupActivity.this.mPicList.size());
                                    }
                                });
                                return;
                            }
                            GroupSendTextImageToGroupActivity.this.executeStartWX();
                        }
                    });
                    return;
                }
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "群发文字加图片到微信群", QBangApis.VIDEO_GROUP_SEND_IMG_TEXT_TO_GROUP);
                return;
            default:
                return;
        }
    }

    public void executeStartWX() {
        OperationParameterModel operationParameterModel = new OperationParameterModel();
        operationParameterModel.setTaskNum("6");
        operationParameterModel.setIsOriginPic(this.isOriginPic);
        operationParameterModel.setStartIndex(this.startIndex);
        operationParameterModel.setSendOrder(this.sendOrder);
        operationParameterModel.setSendGroupMethod(this.sendGroupMethod);
        operationParameterModel.setJumpGroupNames(this.groupNames);
        operationParameterModel.setCirculateMode(this.circulateMode);
        if (this.circulateMode == 0) {
            operationParameterModel.setCirculateOutSize(this.circulateSize);
            operationParameterModel.setCirculateInnerSize(1);
        } else {
            operationParameterModel.setCirculateOutSize(1);
            operationParameterModel.setCirculateInnerSize(this.circulateSize);
        }
        operationParameterModel.setMaterialPicCount(this.mPicList.size());
        operationParameterModel.setSpaceTime(this.spaceTime);
        operationParameterModel.setMessageSendType(this.msgType);
        operationParameterModel.setSendCardType(this.sendCardType);
        operationParameterModel.setSayContent(this.sayContent + getAppendSign());
        operationParameterModel.setLocalImgUrl("xxx.png");
        MyApplication.instance.setOperationParameterModel(operationParameterModel);
        startWX(operationParameterModel);
    }

    @Subscribe
    public void onEventMainThread(final HotMaterialTextBean hotMaterialTextBean) {
        runOnUiThread(new Runnable() {
            public void run() {
                String materialStr = hotMaterialTextBean.getMaterialStr();
                if (materialStr != null && !"".equals(materialStr)) {
                    GroupSendTextImageToGroupActivity.this.editLeavingMessage.setText(materialStr);
                    if (materialStr != null && !"".equals(materialStr)) {
                        SPUtils.put(MyApplication.getConText(), "group_send_text_img_group_content", materialStr);
                    }
                }
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10 && i2 == 11) {
            this.selectPicVideoLayout.setOnActivityResult(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
