package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxGroupEvent;
import com.wx.assistants.bean.WxTagEvent;
import com.wx.assistants.dialog.AlertEditDialog;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.CustomRadioLayout;
import com.wx.assistants.view.CustomRadioSwitchLayout;
import com.wx.assistants.webview.WebViewActivity;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CleanHomeMsgActivity extends BaseActivity {
    @BindView(2131296567)
    CustomRadioLayout customRadioLayout2;
    @BindView(2131296569)
    CustomRadioSwitchLayout customRadioSwitchLayout;
    @BindView(2131296570)
    CustomRadioSwitchLayout customRadioSwitchLayout1;
    @BindView(2131296571)
    CustomRadioSwitchLayout customRadioSwitchLayout2;
    /* access modifiers changed from: private */
    public int defaultNum = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
    /* access modifiers changed from: private */
    public int deleteFriendMode = 1;
    /* access modifiers changed from: private */
    public int deleteGroupMode = 1;
    /* access modifiers changed from: private */
    public int deleteMode = 1;
    /* access modifiers changed from: private */
    public int deleteOrder = 1;
    /* access modifiers changed from: private */
    public String friendNames = "";
    /* access modifiers changed from: private */
    public String groupNames = "";
    @BindView(2131296874)
    TextView jumpLabel1;
    @BindView(2131296875)
    TextView jumpLabel2;
    @BindView(2131296878)
    LinearLayout jumpLabelLayout1;
    @BindView(2131296879)
    LinearLayout jumpLabelLayout2;
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
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297412)
    LinearLayout startLayout;
    @BindView(2131297419)
    TextView startPull;
    @BindView(2131297422)
    LinearLayout startPullLayout;
    @BindView(2131297425)
    Button startWx;
    private String tagNames = "";
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6715);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, com.wx.assistants.activity.CleanHomeMsgActivity] */
    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("自动清理消息");
        this.startWx.setText("启动微信开始清理");
        this.startPull.setText("默认保留最新的1条消息");
        this.customRadioSwitchLayout.setText("仅删除,已读过的消息");
        this.customRadioSwitchLayout.setDefaultSelect(1);
        this.customRadioSwitchLayout.setOnModelSelectListener(new CustomRadioSwitchLayout.OnModelSelectListener() {
            public void mode(int i) {
                int unused = CleanHomeMsgActivity.this.deleteMode = i;
            }
        });
        this.customRadioLayout2.setText("从下往上删除", "是", "否");
        this.customRadioLayout2.setDefaultSelect(1);
        this.customRadioLayout2.setOnModelSelectListener(new CustomRadioLayout.OnModelSelectListener() {
            public void mode(int i) {
                int unused = CleanHomeMsgActivity.this.deleteOrder = i;
            }
        });
        try {
            this.deleteFriendMode = ((Integer) SPUtils.get(this, "friend_msg_shield_model", 1)).intValue();
            if (this.deleteFriendMode == 1) {
                this.jumpLabelLayout1.setVisibility(8);
            } else {
                this.jumpLabelLayout1.setVisibility(0);
            }
        } catch (Exception e) {
        }
        this.customRadioSwitchLayout1.setText("保留哪些标签好友");
        this.customRadioSwitchLayout1.setDefaultSelect(this.deleteFriendMode);
        this.customRadioSwitchLayout1.setOnModelSelectListener(new CustomRadioSwitchLayout.OnModelSelectListener() {
            /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.CleanHomeMsgActivity] */
            public void mode(int i) {
                int unused = CleanHomeMsgActivity.this.deleteFriendMode = i;
                SPUtils.put(CleanHomeMsgActivity.this, "friend_msg_shield_model", Integer.valueOf(CleanHomeMsgActivity.this.deleteFriendMode));
                if (CleanHomeMsgActivity.this.deleteFriendMode == 1) {
                    CleanHomeMsgActivity.this.jumpLabelLayout1.setVisibility(8);
                } else {
                    CleanHomeMsgActivity.this.jumpLabelLayout1.setVisibility(0);
                }
            }
        });
        try {
            this.deleteGroupMode = ((Integer) SPUtils.get(this, "group_msg_shield_model", 1)).intValue();
            if (this.deleteGroupMode == 1) {
                this.jumpLabelLayout2.setVisibility(8);
            } else {
                this.jumpLabelLayout2.setVisibility(0);
            }
        } catch (Exception e2) {
        }
        this.customRadioSwitchLayout2.setText("保留哪些群聊信息");
        this.customRadioSwitchLayout2.setDefaultSelect(this.deleteGroupMode);
        this.customRadioSwitchLayout2.setOnModelSelectListener(new CustomRadioSwitchLayout.OnModelSelectListener() {
            /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.CleanHomeMsgActivity] */
            public void mode(int i) {
                int unused = CleanHomeMsgActivity.this.deleteGroupMode = i;
                SPUtils.put(CleanHomeMsgActivity.this, "group_msg_shield_model", Integer.valueOf(CleanHomeMsgActivity.this.deleteGroupMode));
                if (CleanHomeMsgActivity.this.deleteGroupMode == 1) {
                    CleanHomeMsgActivity.this.jumpLabelLayout2.setVisibility(8);
                } else {
                    CleanHomeMsgActivity.this.jumpLabelLayout2.setVisibility(0);
                }
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.CleanHomeMsgActivity] */
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
    public void onEventMainThread(WxGroupEvent wxGroupEvent) {
        LinkedHashSet<String> selectedGroupList = wxGroupEvent.getSelectedGroupList();
        if (selectedGroupList == null || selectedGroupList.size() <= 0) {
            this.jumpLabel2.setText("请设置要保留的群聊");
            this.groupNames = "";
            SPUtils.put(MyApplication.getConText(), "group_msg_label_shield", "");
            return;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = selectedGroupList.iterator();
        while (it.hasNext()) {
            sb.append(((String) it.next()) + ";");
        }
        this.groupNames = sb.toString();
        this.jumpLabel2.setText(this.groupNames);
        SPUtils.put(MyApplication.getConText(), "group_msg_label_shield", this.groupNames);
    }

    @Subscribe
    public void onEventMainThread(WxTagEvent wxTagEvent) {
        ArrayList<String> selectedTagList = wxTagEvent.getSelectedTagList();
        if (selectedTagList == null || selectedTagList.size() <= 0) {
            this.jumpLabel1.setText("");
            this.friendNames = "";
            this.tagNames = "";
            SPUtils.put(MyApplication.getConText(), "friend_msg_label_shield", "");
            SPUtils.put(MyApplication.getConText(), "friend_msg_shield", "");
            return;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= selectedTagList.size()) {
                break;
            }
            sb.append(selectedTagList.get(i2) + ";");
            i = i2 + 1;
        }
        this.jumpLabel1.setText(sb.toString());
        SPUtils.put(MyApplication.getConText(), "friend_msg_label_shield", sb.toString());
        LinkedHashSet<String> selectedPeopleList = wxTagEvent.getSelectedPeopleList();
        if (selectedPeopleList == null || selectedPeopleList.size() <= 0) {
            this.jumpLabel1.setText("");
            this.friendNames = "";
            this.tagNames = "";
            SPUtils.put(MyApplication.getConText(), "friend_msg_label_shield", "");
            SPUtils.put(MyApplication.getConText(), "friend_msg_shield", "");
            return;
        }
        Iterator it = selectedPeopleList.iterator();
        StringBuilder sb2 = new StringBuilder();
        while (it.hasNext()) {
            sb2.append(((String) it.next()) + ";");
        }
        this.friendNames = sb2.toString();
        SPUtils.put(MyApplication.getConText(), "friend_msg_shield", this.friendNames);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.CleanHomeMsgActivity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        int intValue = ((Integer) SPUtils.get(this, "clean_home_msg_num", 1)).intValue();
        if (intValue <= 0) {
            this.startIndex = 0;
            this.startPull.setText("默认清空全部消息");
        } else {
            this.startIndex = intValue;
            TextView textView = this.startPull;
            textView.setText("默认保留最新的" + this.startIndex + "条消息");
        }
        this.tagNames = (String) SPUtils.get(MyApplication.getConText(), "friend_msg_label_shield", "");
        this.jumpLabel1.setText(this.tagNames);
        if (this.tagNames == null || "".equals(this.tagNames)) {
            this.jumpLabel1.setText("设置要保留的好友标签");
        } else {
            this.jumpLabel1.setText(this.tagNames);
        }
        this.friendNames = (String) SPUtils.get(MyApplication.getConText(), "friend_msg_shield", "");
        this.groupNames = (String) SPUtils.get(MyApplication.getConText(), "group_msg_label_shield", "");
        this.jumpLabel2.setText(this.groupNames);
        if (this.groupNames == null || "".equals(this.groupNames)) {
            this.jumpLabel2.setText("设置要保留的群聊");
        } else {
            this.jumpLabel2.setText(this.groupNames);
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.CleanHomeMsgActivity] */
    @OnClick({2131297425, 2131297422, 2131297049, 2131297412, 2131297636, 2131297052, 2131296879, 2131296878})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296878:
                Intent intent = new Intent(this, ObtainTagActivity.class);
                intent.putExtra("selects", this.jumpLabel1.getText().toString());
                startActivity(intent);
                return;
            case 2131296879:
                Intent intent2 = new Intent(this, ObtainGroupAllActivity.class);
                intent2.putExtra("selects", this.jumpLabel2.getText().toString());
                startActivity(intent2);
                return;
            case 2131297049:
                back();
                return;
            case 2131297052:
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.消息太多，手动点击太慢又浪费时间，请交给它，释放您的双手吧\n\n2.删除后，将清空该聊天的消息记录\n\n3.此功能会员以上用户使用\n");
                return;
            case 2131297422:
                try {
                    String charSequence = this.startPull.getText().toString();
                    this.startIndex = Integer.parseInt(charSequence.substring(charSequence.indexOf("第") + 1, charSequence.indexOf("个")).trim());
                } catch (Exception e) {
                }
                DialogUIUtils.dialogSetStartPoint(this, "设置保留的条数", this.startIndex, 200, 0, new AlertEditDialog.OnEditTextListener() {
                    public void result(int i) {
                        int unused = CleanHomeMsgActivity.this.startIndex = i;
                        if (i == 0) {
                            CleanHomeMsgActivity.this.startPull.setText("默认清空全部消息");
                        } else {
                            TextView textView = CleanHomeMsgActivity.this.startPull;
                            textView.setText("默认保留最新的" + i + "条消息");
                        }
                        SPUtils.put(MyApplication.getConText(), "clean_home_msg_num", Integer.valueOf(CleanHomeMsgActivity.this.startIndex));
                    }
                });
                return;
            case 2131297425:
                if (this.deleteFriendMode == 0 && (this.tagNames == null || "".equals(this.tagNames))) {
                    ToastUtils.showToast(this, "请设置要保留的好友标签");
                    return;
                } else if (this.deleteGroupMode != 0 || (this.groupNames != null && !"".equals(this.groupNames))) {
                    startCheck("39", true, new BaseActivity.OnStartCheckListener() {
                        public void checkEnd() {
                            OperationParameterModel operationParameterModel = new OperationParameterModel();
                            operationParameterModel.setTaskNum("39");
                            if (CleanHomeMsgActivity.this.deleteOrder == 0) {
                                operationParameterModel.setStartIndex(CleanHomeMsgActivity.this.startIndex + 1);
                            } else {
                                operationParameterModel.setStartIndex(CleanHomeMsgActivity.this.startIndex + 1);
                            }
                            operationParameterModel.setMaxOperaNum(CleanHomeMsgActivity.this.defaultNum);
                            operationParameterModel.setDeleteMode(CleanHomeMsgActivity.this.deleteMode);
                            operationParameterModel.setDeleteOrder(CleanHomeMsgActivity.this.deleteOrder);
                            if (CleanHomeMsgActivity.this.deleteGroupMode != 0 && CleanHomeMsgActivity.this.deleteFriendMode != 0) {
                                operationParameterModel.setGroupNames("");
                            } else if (CleanHomeMsgActivity.this.deleteGroupMode == 0 && CleanHomeMsgActivity.this.deleteFriendMode == 0) {
                                operationParameterModel.setGroupNames(CleanHomeMsgActivity.this.groupNames + ";" + CleanHomeMsgActivity.this.friendNames);
                            } else if (CleanHomeMsgActivity.this.deleteGroupMode == 0) {
                                operationParameterModel.setGroupNames(CleanHomeMsgActivity.this.groupNames);
                            } else {
                                operationParameterModel.setGroupNames(CleanHomeMsgActivity.this.friendNames);
                            }
                            MyApplication.instance.setOperationParameterModel(operationParameterModel);
                            CleanHomeMsgActivity.this.startWX(operationParameterModel);
                        }
                    });
                    return;
                } else {
                    ToastUtils.showToast(this, "请设置要保留的群聊");
                    return;
                }
            case 2131297636:
                WebViewActivity.startActivity(this, "自动清理消息", QBangApis.VIDEO_AUTO_CLEAN_MSG);
                return;
            default:
                return;
        }
    }
}
