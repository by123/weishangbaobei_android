package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxTagEvent;
import com.wx.assistants.dialog.AlertEditDialog;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CleanZombiePullGroupActivity extends BaseActivity {
    @BindView(2131296585)
    TextView descText;
    @BindView(2131296731)
    LinearLayout graphicExplanationLayout;
    private String inviteUrl = "";
    /* access modifiers changed from: private */
    public boolean isDeleted = false;
    @BindView(2131296871)
    LinearLayout jumpFriendLayout;
    @BindView(2131296873)
    TextView jumpLabel;
    @BindView(2131296877)
    LinearLayout jumpLabelLayout;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> jumpPersons = new LinkedHashSet<>();
    @BindView(2131297049)
    LinearLayout navBack;
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
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6719);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.CleanZombiePullGroupActivity] */
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
        this.navTitle.setText("拉人进群检测僵尸粉");
        this.startWx.setText("启动微信开始检测");
        this.descText.setText("1.拉人进群模式，即通过分批拉好友进群后，来验证是否是好友，拉群完毕后，在逐个把好友移除群。\n2.拉人进群后，检测到非好友您可以选择删除，也可选择不删除，对于不删除的非好友被标记为A000非好友_昵称;\n3.拉人进群可设置检测的起点，当然也可以设置不想检测的好友所在的标签，可自动过滤掉您不想检测的好友\n");
        this.startPull.setText("从第1个微信好友开始检测");
        this.jumpLabel.setText("点我设置不想检测的好友");
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.CleanZombiePullGroupActivity] */
    public void initData() {
        int intValue = ((Integer) SPUtils.get(this, "zombie_pull_group_num", 1)).intValue();
        String str = (String) SPUtils.get(this, "zombie_pull_group_text", "");
        if (this.startPull != null) {
            TextView textView = this.startPull;
            textView.setText("从第" + intValue + "个微信好友开始检测");
            this.startIndex = intValue;
        }
        initInviteData();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        initData();
    }

    public void initInviteData() {
        this.inviteUrl = "జ్ఞ‌ా";
    }

    @Subscribe
    public void onEventMainThread(WxTagEvent wxTagEvent) {
        ArrayList<String> selectedTagList = wxTagEvent.getSelectedTagList();
        if (selectedTagList == null || selectedTagList.size() <= 0) {
            this.jumpLabel.setText("点我设置不想检测的好友标签");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < selectedTagList.size(); i++) {
                sb.append(selectedTagList.get(i) + ";");
            }
            this.jumpLabel.setText(sb.toString());
        }
        if (wxTagEvent.getSelectedPeopleList() != null) {
            this.jumpPersons = new LinkedHashSet<>();
            this.jumpPersons.addAll(wxTagEvent.getSelectedPeopleList());
            return;
        }
        this.jumpPersons = new LinkedHashSet<>();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.CleanZombiePullGroupActivity] */
    @OnClick({2131297049, 2131296877, 2131297422, 2131296871, 2131297412, 2131297425, 2131297636})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296877:
                Intent intent = new Intent(this, ObtainTagActivity.class);
                intent.putExtra("selects", this.jumpLabel.getText().toString());
                startActivity(intent);
                return;
            case 2131297049:
                back();
                return;
            case 2131297422:
                try {
                    String charSequence = this.startPull.getText().toString();
                    this.startIndex = Integer.parseInt(charSequence.substring(charSequence.indexOf("第") + 1, charSequence.indexOf("个")));
                } catch (Exception unused) {
                    LogUtils.log("WS_BABY_Catch_30");
                }
                DialogUIUtils.dialogSetStartPoint(this, this.startIndex, new AlertEditDialog.OnEditTextListener() {
                    public void result(int i) {
                        int unused = CleanZombiePullGroupActivity.this.startIndex = i;
                        TextView textView = CleanZombiePullGroupActivity.this.startPull;
                        textView.setText("从第" + i + "个微信好友开始检测");
                        SPUtils.put(MyApplication.getConText(), "zombie_pull_group_num", Integer.valueOf(CleanZombiePullGroupActivity.this.startIndex));
                    }
                });
                return;
            case 2131297425:
                startCheck("28", true, new BaseActivity.OnStartCheckListener() {
                    public void checkEnd() {
                        OperationParameterModel operationParameterModel = new OperationParameterModel();
                        operationParameterModel.setTaskNum("28");
                        operationParameterModel.setDeleteNoFriends(CleanZombiePullGroupActivity.this.isDeleted);
                        operationParameterModel.setStartIndex(CleanZombiePullGroupActivity.this.startIndex);
                        operationParameterModel.setTagListPeoples(CleanZombiePullGroupActivity.this.jumpPersons);
                        MyApplication.instance.setOperationParameterModel(operationParameterModel);
                        CleanZombiePullGroupActivity.this.startWX(operationParameterModel);
                    }
                });
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "无打扰检测僵尸粉视频教程", QBangApis.VIDEO_CLEAN_ZOMBIE);
                return;
            default:
                return;
        }
    }
}
