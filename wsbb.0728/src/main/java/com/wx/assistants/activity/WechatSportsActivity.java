package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxTagEvent;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.NumSettingOnlyLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class WechatSportsActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    @BindView(2131296339)
    LinearLayout allTagLayout;
    /* access modifiers changed from: private */
    public int defaultNum = 1;
    @BindView(2131296722)
    TextView friendsTags;
    /* access modifiers changed from: private */
    public String jumpPersons = new String();
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297063)
    TextView noFriendsTags;
    @BindView(2131297064)
    LinearLayout noPraiseTagLayout;
    @BindView(2131297078)
    NumSettingOnlyLayout numSettingOnlyLayout;
    private int operationIndex = 0;
    /* access modifiers changed from: private */
    public int operationType = 0;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> persons = new LinkedHashSet<>();
    @BindView(2131297154)
    LinearLayout praiseTagLayout;
    @BindView(2131297209)
    RadioButton rdoBtnAll;
    @BindView(2131297215)
    RadioButton rdoBtnNo;
    @BindView(2131297216)
    RadioButton rdoBtnYes;
    private int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    private LinkedHashSet<String> tagList = new LinkedHashSet<>();
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6759);
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("微信运动点赞");
        this.startWx.setText("启动微信开始点赞");
        this.rdoBtnAll.setOnCheckedChangeListener(this);
        this.rdoBtnYes.setOnCheckedChangeListener(this);
        this.rdoBtnNo.setOnCheckedChangeListener(this);
        this.numSettingOnlyLayout.setNum(this.defaultNum, 10000000, "多少步以上点赞");
        this.numSettingOnlyLayout.setOnResultListener(new NumSettingOnlyLayout.OnResultListener() {
            public void result(int i) {
                int unused = WechatSportsActivity.this.defaultNum = i;
            }
        });
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int id = compoundButton.getId();
        if (id != 2131297209) {
            switch (id) {
                case 2131297215:
                    if (z) {
                        this.rdoBtnYes.setChecked(false);
                        this.rdoBtnAll.setChecked(false);
                        this.operationType = 2;
                        return;
                    }
                    return;
                case 2131297216:
                    if (z) {
                        this.rdoBtnAll.setChecked(false);
                        this.rdoBtnNo.setChecked(false);
                        this.operationType = 1;
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else if (z) {
            this.rdoBtnYes.setChecked(false);
            this.rdoBtnNo.setChecked(false);
            this.operationType = 0;
        }
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.WechatSportsActivity] */
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
        this.tagList = new LinkedHashSet<>();
        this.tagList.addAll(selectedTagList);
        if (selectedTagList == null || selectedTagList.size() <= 0) {
            this.persons = new LinkedHashSet<>();
            if (this.operationIndex == 1) {
                this.friendsTags.setText("选中的标签好友发送");
            } else if (this.operationIndex == 2) {
                this.noFriendsTags.setText("选中的标签好友不发送");
            }
        } else {
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
            if (wxTagEvent.getSelectedPeopleList() != null) {
                this.persons = new LinkedHashSet<>();
                this.persons.addAll(wxTagEvent.getSelectedPeopleList());
            }
            if (wxTagEvent.getSelectedPeoples() != null) {
                this.jumpPersons = wxTagEvent.getSelectedPeoples();
            }
            if (this.operationIndex == 1) {
                this.friendsTags.setText(sb.toString());
            } else if (this.operationIndex == 2) {
                this.noFriendsTags.setText(sb.toString());
            }
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.WechatSportsActivity] */
    @OnClick({2131297049, 2131297425, 2131297636, 2131297052, 2131296339, 2131297154, 2131297064})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296339:
                this.operationIndex = 0;
                this.operationType = 0;
                this.rdoBtnAll.setChecked(true);
                this.rdoBtnYes.setChecked(false);
                this.rdoBtnNo.setChecked(false);
                return;
            case 2131297049:
                back();
                return;
            case 2131297052:
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.要使用微信运动点赞功能，请您在进入微信首页后，点导航栏中的搜索，输入微信运动，进入微信运动公众号后，点步数排行榜后在点开始按钮，微商宝贝就会自动为您执行点赞功能了。\n\n2.可设置不点赞的好友所在的标签，如果选择不点赞好友标签，并且所选择标签为空，表示点赞所以好友\n\n3.可仅点赞某些标签下的好友\n\n4.可设置点赞的步数下限，超过该设置的步数才点赞");
                return;
            case 2131297064:
                this.operationIndex = 2;
                this.operationType = 2;
                this.rdoBtnNo.setChecked(true);
                this.rdoBtnAll.setChecked(false);
                this.rdoBtnYes.setChecked(false);
                Intent intent = new Intent(this, ObtainTagActivity.class);
                intent.putExtra("selects", this.noFriendsTags.getText().toString());
                startActivity(intent);
                return;
            case 2131297154:
                this.operationIndex = 1;
                this.operationType = 1;
                this.rdoBtnYes.setChecked(true);
                this.rdoBtnAll.setChecked(false);
                this.rdoBtnNo.setChecked(false);
                Intent intent2 = new Intent(this, ObtainTagActivity.class);
                intent2.putExtra("selects", this.friendsTags.getText().toString());
                startActivity(intent2);
                return;
            case 2131297425:
                startCheck("24", true, new BaseActivity.OnStartCheckListener() {
                    public void checkEnd() {
                        OperationParameterModel operationParameterModel = new OperationParameterModel();
                        operationParameterModel.setTaskNum("24");
                        operationParameterModel.setMinimumStep(WechatSportsActivity.this.defaultNum);
                        operationParameterModel.setWechatSportsType(WechatSportsActivity.this.operationType);
                        operationParameterModel.setJumpFriendNames(WechatSportsActivity.this.jumpPersons);
                        operationParameterModel.setFriendsNameSet(WechatSportsActivity.this.persons);
                        MyApplication.instance.setOperationParameterModel(operationParameterModel);
                        WechatSportsActivity.this.startWX(operationParameterModel);
                    }
                });
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "微信运动点赞视频教程", QBangApis.VIDEO_SPORTS);
                return;
            default:
                return;
        }
    }
}
