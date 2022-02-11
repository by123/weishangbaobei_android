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
import com.wx.assistants.bean.WxGroupEvent;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.GroupSendModeLayoutH;
import com.wx.assistants.webview.WebViewActivity;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class GroupSendMiniProgramsToGroupActivity extends BaseActivity {
    @BindView(2131296616)
    EditText editLeavingMessage;
    @BindView(2131296647)
    ExecuteTimeSpaceLayout executeTimeSpaceLayout;
    /* access modifiers changed from: private */
    public String groupNames = "";
    @BindView(2131296736)
    GroupSendModeLayoutH groupSendModeLayout;
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
        StubApp.interface11(6729);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendMiniProgramsToGroupActivity] */
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
        this.navTitle.setText("群发小程序到微信群");
        this.startWx.setText("启动微信开始群发小程序");
        this.executeTimeSpaceLayout.setExecuteTimeTitle("群发间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = GroupSendMiniProgramsToGroupActivity.this.spaceTime = i;
            }
        });
        this.groupSendModeLayout.setGroupSendTypeListener(new GroupSendModeLayoutH.GroupSendTypeListener() {
            public void sendType(int i) {
                int unused = GroupSendMiniProgramsToGroupActivity.this.sendCardType = i;
            }

            public void sendStartIndex(int i) {
                int unused = GroupSendMiniProgramsToGroupActivity.this.startIndex = i;
            }

            public void sendMembers(String str) {
                String unused = GroupSendMiniProgramsToGroupActivity.this.groupNames = str;
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "mini_programs_to_group_num_all", 1)).intValue();
            int intValue2 = ((Integer) SPUtils.get(MyApplication.getConText(), "mini_programs_to_group_num_part", 1)).intValue();
            int intValue3 = ((Integer) SPUtils.get(MyApplication.getConText(), "mini_programs_to_group_num_shield", 1)).intValue();
            this.groupSendModeLayout.initSendIndexStrAll(true, "mini_programs_to_group_num_all", intValue);
            this.groupSendModeLayout.initSendIndexStrPart(true, "mini_programs_to_group_num_part", intValue2);
            this.groupSendModeLayout.initSendIndexStrShield(true, "mini_programs_to_group_num_shield", intValue3);
            this.groupSendModeLayout.initSendLabelStrAll(true, "mini_programs_to_group_label_all", (String) SPUtils.get(MyApplication.getConText(), "mini_programs_to_group_label_all", ""));
            this.groupSendModeLayout.initSendLabelStrPart(true, "mini_programs_to_group_label_part", (String) SPUtils.get(MyApplication.getConText(), "mini_programs_to_group_label_part", ""));
            this.groupSendModeLayout.initSendLabelStrShield(true, "mini_programs_to_group_label_shield", (String) SPUtils.get(MyApplication.getConText(), "mini_programs_to_group_label_shield", ""));
            String str = (String) SPUtils.get(MyApplication.getConText(), "mini_programs_group_say_content", "");
            if (str != null && !"".equals(str)) {
                this.editLeavingMessage.setText(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    @Subscribe
    public void onEventMainThread(final HotMaterialTextBean hotMaterialTextBean) {
        runOnUiThread(new Runnable() {
            public void run() {
                String materialStr = hotMaterialTextBean.getMaterialStr();
                if (materialStr != null && !"".equals(materialStr)) {
                    GroupSendMiniProgramsToGroupActivity.this.editLeavingMessage.setText(materialStr);
                    if (materialStr != null && !"".equals(materialStr)) {
                        SPUtils.put(MyApplication.getConText(), "mini_programs_group_say_content", materialStr);
                    }
                }
            }
        });
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendMiniProgramsToGroupActivity] */
    @OnClick({2131296950, 2131297049, 2131297425, 2131297636, 2131297052})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296950:
                WebViewActivity.startActivity(this, "热门群发模版", QBangApis.HOT_MASTER_PLATE_URL, false);
                return;
            case 2131297049:
                back();
                return;
            case 2131297052:
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.请将要转发的小程序，提前放在会话聊天页的最后一条。\n\n2.群发小程序，系统默认发送微信的中的所有群，包含没有保存在通讯录中的群，若想只发送保存在通讯录中的群，您可以选择发送模式第二项，把需要将要操作的群，选择即可\n\n3.为了防止漏群情况，使用此功能前，群名必须保证唯一\n\n4.群发完小程序后，您还可以追加一条留言文字。\n\n5.该功能是会员用户专享。");
                return;
            case 2131297425:
                if (this.groupNames == null || "".equals(this.groupNames)) {
                    if (this.sendCardType == 1) {
                        ToastUtils.showToast(MyApplication.getConText(), "请设置您要群发的群");
                        return;
                    } else if (this.sendCardType == 2) {
                        ToastUtils.showToast(MyApplication.getConText(), "请设置您要屏蔽的群");
                        return;
                    }
                }
                startCheck("30", true, new BaseActivity.OnStartCheckListener() {
                    public void checkEnd() {
                        OperationParameterModel operationParameterModel = new OperationParameterModel();
                        operationParameterModel.setTaskNum("30");
                        operationParameterModel.setSendCardType(GroupSendMiniProgramsToGroupActivity.this.sendCardType);
                        operationParameterModel.setStartIndex(GroupSendMiniProgramsToGroupActivity.this.startIndex);
                        operationParameterModel.setJumpGroupNames(GroupSendMiniProgramsToGroupActivity.this.groupNames);
                        String obj = GroupSendMiniProgramsToGroupActivity.this.editLeavingMessage.getText().toString();
                        if (obj != null && !"".equals(obj)) {
                            SPUtils.put(MyApplication.getConText(), "mini_programs_group_say_content", obj);
                        }
                        operationParameterModel.setOtherSendType(1);
                        operationParameterModel.setSayContent(obj + GroupSendMiniProgramsToGroupActivity.this.getAppendSign());
                        operationParameterModel.setSpaceTime(GroupSendMiniProgramsToGroupActivity.this.spaceTime);
                        MyApplication.instance.setOperationParameterModel(operationParameterModel);
                        GroupSendMiniProgramsToGroupActivity.this.startWX(operationParameterModel);
                    }
                });
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "群发小程序到群视频教程", QBangApis.VIDEO_GROUP_SEND_MINI_PROGRAM_GROUPS);
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
