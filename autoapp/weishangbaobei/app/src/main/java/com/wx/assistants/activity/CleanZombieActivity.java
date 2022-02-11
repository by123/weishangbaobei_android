package com.wx.assistants.activity;

import android.content.Intent;
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
import com.stub.StubApp;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.AppVersionModel;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.HomeBannerBean;
import com.wx.assistants.bean.LabelBean;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxTagEvent;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.NumSettingLayout;
import com.wx.assistants.view.SingleLabelSelectLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CleanZombieActivity extends BaseActivity {
    @BindView(2131296581)
    Switch deleteSwitchBtn;
    @BindView(2131296647)
    ExecuteTimeSpaceLayout executeTimeSpaceLayout;
    private String inviteUrl = "";
    /* access modifiers changed from: private */
    public boolean isDeleted = false;
    @BindView(2131296872)
    TextView jumpLabel;
    private LinkedHashSet<String> jumpPersons = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public int maxNum = 300;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297082)
    NumSettingLayout numberSettingLayout;
    /* access modifiers changed from: private */
    public String selectLabel = "";
    @BindView(2131297386)
    SingleLabelSelectLayout singleSelectLabelLayout;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6717);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.CleanZombieActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.CleanZombieActivity] */
    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("自动检测僵尸粉");
        this.startWx.setText("启动微信开始检测");
        this.jumpLabel.setText("");
        this.jumpLabel.setVisibility(8);
        this.isDeleted = false;
        this.deleteSwitchBtn.setChecked(false);
        this.singleSelectLabelLayout.setOnSelectSingleLabelListener(new SingleLabelSelectLayout.OnSelectSingleLabelListener() {
            public void selectLabel(LabelBean labelBean) {
                LogUtils.log("WS_BABY_selectLabel");
                if (labelBean == null) {
                    String unused = CleanZombieActivity.this.selectLabel = null;
                } else {
                    String unused2 = CleanZombieActivity.this.selectLabel = labelBean.getLabelName();
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
        this.numberSettingLayout.setNum(300, SocializeConstants.CANCLE_RESULTCODE, "检测数量");
        this.numberSettingLayout.setStartPointIndex(((Integer) SPUtils.get(this, "zombie_num", 1)).intValue(), "检测起点");
        this.numberSettingLayout.setOnResultListener(new NumSettingLayout.OnResultListener() {
            public void result(int i, int i2) {
                int unused = CleanZombieActivity.this.maxNum = i;
                int unused2 = CleanZombieActivity.this.startIndex = i2;
                SPUtils.put(MyApplication.getConText(), "zombie_num", Integer.valueOf(CleanZombieActivity.this.startIndex));
            }
        });
        this.executeTimeSpaceLayout.setExecuteTimeTitle("检测时间间隔");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = CleanZombieActivity.this.spaceTime = i;
            }
        });
        this.deleteSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                boolean unused = CleanZombieActivity.this.isDeleted = z;
                if (z) {
                    CleanZombieActivity.this.singleSelectLabelLayout.setVisibility(8);
                } else {
                    CleanZombieActivity.this.singleSelectLabelLayout.setVisibility(0);
                }
            }
        });
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.CleanZombieActivity] */
    public void initData() {
        int intValue = ((Integer) SPUtils.get(this, "zombie_num", 1)).intValue();
        if (this.numberSettingLayout != null) {
            this.startIndex = intValue;
            this.numberSettingLayout.setStartPointIndex(this.startIndex, "检测起点");
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        initData();
        checkText();
    }

    @Subscribe
    public void onEventMainThread(WxTagEvent wxTagEvent) {
        ArrayList<String> selectedTagList = wxTagEvent.getSelectedTagList();
        if (selectedTagList == null || selectedTagList.size() <= 0) {
            this.jumpLabel.setText("");
            this.jumpLabel.setVisibility(8);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < selectedTagList.size(); i++) {
                sb.append(selectedTagList.get(i) + ";");
            }
            this.jumpLabel.setText(sb.toString());
            this.jumpLabel.setVisibility(0);
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.CleanZombieActivity] */
    @OnClick({2131297049, 2131296871, 2131297425, 2131297636, 2131297052})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296871:
                Intent intent = new Intent(this, ObtainTagActivity.class);
                intent.putExtra("selects", this.jumpLabel.getText().toString());
                startActivity(intent);
                return;
            case 2131297049:
                back();
                return;
            case 2131297052:
                DialogUIUtils.dialogFunctionalSpecification(this, "自动检测僵尸粉", "1.清理死粉过程中，非好友被标记为A000非好友_昵称;\n\n2.默认从第一个好友开始检测，您也可以设置检测的起点。\n\n3.检测人数超过500人以上，若发现连续检测三人都是非好友，系统会认为由于微信的限制，导致发送异常，并自动停止。建议间隔6小时在尝试检测。\n\n4.因微信系统限制，如遇到黑屏、卡顿、无响应等问题，请前往微信【设置--通用--关闭照片、视频、文件自动下载】\n\n");
                return;
            case 2131297425:
                startCheck();
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "检测僵尸粉视频教程", QBangApis.VIDEO_CLEAN_ZOMBIE);
                return;
            default:
                return;
        }
    }

    public void startCheck() {
        startCheck("1", true, new BaseActivity.OnStartCheckListener() {
            public void checkEnd() {
                CleanZombieActivity.this.checkText();
                CleanZombieActivity.this.showLoadingDialog("初始化检测数据");
                ApiWrapper.getInstance().getVersion(new ApiWrapper.CallbackListener<ConmdBean<AppVersionModel>>() {
                    /* JADX WARNING: type inference failed for: r3v6, types: [android.content.Context, com.wx.assistants.activity.CleanZombieActivity] */
                    public void onFinish(ConmdBean<AppVersionModel> conmdBean) {
                        CleanZombieActivity.this.dismissLoadingDialog();
                        try {
                            try {
                                MyApplication.SHARE_DOWN_URL = conmdBean.getData().getShareUrl();
                                SPUtils.put(CleanZombieActivity.this, "user_inviteUrl", MyApplication.SHARE_DOWN_URL);
                                CleanZombieActivity.this.startWeChat();
                            } catch (Exception unused) {
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    public void onFailure(FailureModel failureModel) {
                        CleanZombieActivity.this.dismissLoadingDialog();
                        LogUtils.log("#######" + failureModel.toString());
                        CleanZombieActivity.this.startWeChat();
                    }
                });
            }
        });
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.CleanZombieActivity] */
    public void startWeChat() {
        PrintStream printStream = System.out;
        printStream.println("WS_BABY_SHARE_DOWN_URL=" + MyApplication.SHARE_DOWN_URL);
        if (MyApplication.SHARE_DOWN_URL == null || "".equals(MyApplication.SHARE_DOWN_URL)) {
            ToastUtils.showToast(this, "请稍等5秒在点开始，正在初始化检测文本数据！");
            return;
        }
        this.inviteUrl = "" + MyApplication.SHARE_DOWN_URL;
        OperationParameterModel operationParameterModel = new OperationParameterModel();
        operationParameterModel.setTaskNum("1");
        operationParameterModel.setDeleteNoFriends(this.isDeleted);
        if (!this.isDeleted) {
            operationParameterModel.setSingLabelStr(this.selectLabel);
        } else {
            operationParameterModel.setSingLabelStr("");
        }
        operationParameterModel.setSayContent(this.inviteUrl);
        operationParameterModel.setIsDND(0);
        operationParameterModel.setStartIndex(this.startIndex);
        operationParameterModel.setTagListPeoples(this.jumpPersons);
        operationParameterModel.setMaxOperaNum(this.maxNum);
        operationParameterModel.setSpaceTime(this.spaceTime);
        MyApplication.instance.setOperationParameterModel(operationParameterModel);
        startWX(operationParameterModel);
    }

    public void checkText() {
        ApiWrapper.getInstance().getScroll(MacAddressUtils.getMacAddress(MyApplication.mContext), "TEXT", new ApiWrapper.CallbackListener<ConmdBean<List<HomeBannerBean>>>() {
            public void onFailure(FailureModel failureModel) {
            }

            public void onFinish(ConmdBean conmdBean) {
                List list;
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() == 0 && (list = (List) conmdBean.getData()) != null && list.size() > 0) {
                            String[] strArr = new String[list.size()];
                            for (int i = 0; i < list.size(); i++) {
                                String scrollBar = ((HomeBannerBean) list.get(i)).getScrollBar();
                                if (scrollBar != null) {
                                    strArr[i] = scrollBar;
                                } else {
                                    strArr[i] = MyApplication.checkStr[0];
                                }
                            }
                            MyApplication.checkStr = strArr;
                        }
                    } catch (Exception e) {
                        LogUtils.log("WS_BABY_Catch_53");
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
