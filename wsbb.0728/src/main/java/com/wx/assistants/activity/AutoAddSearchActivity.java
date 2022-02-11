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
import com.google.gson.reflect.TypeToken;
import com.stub.StubApp;
import com.wx.assistants.adapter.TagSelectAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.ConmdListBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.PassiveCardBean;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FFModelLayout;
import com.wx.assistants.view.NumSettingOnlyLayout;
import com.wx.assistants.view.TagCloudLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import java.util.Arrays;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AutoAddSearchActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public int canMaxNum = 20;
    /* access modifiers changed from: private */
    public int defaultNum = 10;
    @BindView(2131296647)
    ExecuteTimeSpaceLayout executeTimeSpaceLayout;
    /* access modifiers changed from: private */
    public int ffModel = 0;
    /* access modifiers changed from: private */
    public int ffModelEndTime = 10;
    @BindView(2131296660)
    FFModelLayout ffModelLayout;
    /* access modifiers changed from: private */
    public int ffModelStartTime = 0;
    @BindView(2131296681)
    TagCloudLayout flowViewGroup;
    /* access modifiers changed from: private */
    public int isBreak = 0;
    /* access modifiers changed from: private */
    public boolean isRemark = true;
    /* access modifiers changed from: private */
    public ArrayList<String> mList;
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
    @BindView(2131297278)
    EditText sayContent;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    @BindView(2131297425)
    Button startWx;
    private TagSelectAdapter tagSelectAdapter;
    @BindView(2131297636)
    LinearLayout videoIntroduce;

    static {
        StubApp.interface11(6707);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddSearchActivity] */
    private void initData() {
        this.mList = new ArrayList<>();
        this.mList.addAll(Arrays.asList(getResources().getStringArray(2130903043)));
        this.tagSelectAdapter = new TagSelectAdapter(this, this.mList);
        this.flowViewGroup.setAdapter(this.tagSelectAdapter);
        this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                AutoAddSearchActivity.this.sayContent.setText((CharSequence) AutoAddSearchActivity.this.mList.get(i));
            }
        });
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("搜索加人");
        this.startWx.setText("启动微信开始加人");
        this.numSettingOnlyLayout.setNum(this.defaultNum, this.canMaxNum, "加人数量");
        this.numSettingOnlyLayout.setOnResultListener(new NumSettingOnlyLayout.OnResultListener() {
            public void result(int i) {
                int unused = AutoAddSearchActivity.this.defaultNum = i;
            }
        });
        this.executeTimeSpaceLayout.setExecuteTimeTitle("加人间隔时间");
        this.executeTimeSpaceLayout.setOnTimeSpaceListener(new ExecuteTimeSpaceLayout.OnTimeSpaceListener() {
            public void executeSpace(int i) {
                int unused = AutoAddSearchActivity.this.spaceTime = i;
            }
        });
        this.ffModelLayout.setDefaultTime(this.ffModelStartTime, this.ffModelEndTime);
        this.ffModelLayout.setOnFFModelListener(new FFModelLayout.OnFFModelListener() {
            public void ffMode(int i) {
                int unused = AutoAddSearchActivity.this.ffModel = i;
                if (AutoAddSearchActivity.this.ffModel == 0) {
                    AutoAddSearchActivity.this.executeTimeSpaceLayout.setVisibility(0);
                } else {
                    AutoAddSearchActivity.this.executeTimeSpaceLayout.setVisibility(8);
                }
            }

            public void ffModeTime(int i, int i2) {
                int unused = AutoAddSearchActivity.this.ffModelStartTime = i;
                int unused2 = AutoAddSearchActivity.this.ffModelEndTime = i2;
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddSearchActivity] */
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
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddSearchActivity] */
    @OnClick({2131297049, 2131297425, 2131297052, 2131297636})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.根据微信规则，使用搜索加人一次最多加20个好友。\n\n2.使用搜索加人，不耽误其他加粉儿功能。\n\n3.如果有多个微信号，每个微信号都可以使用搜索加人功能。\n\n4.为了保证不出现微信加粉频繁，同一微信号建议每隔3小时使用一次加粉功能。\n\n5.此功能免费。");
        } else if (id == 2131297425) {
            startCheck("65", true, new BaseActivity.OnStartCheckListener() {
                public void checkEnd() {
                    ApiWrapper.getInstance().getPassiveCardList(1, AutoAddSearchActivity.this.defaultNum, new ApiWrapper.CallbackListener<ConmdListBean>() {
                        /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.AutoAddSearchActivity] */
                        public void onFailure(FailureModel failureModel) {
                            ? r0 = AutoAddSearchActivity.this;
                            ToastUtils.showToast(r0, failureModel.getCode() + ":" + failureModel.getErrorMessage());
                        }

                        /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.AutoAddSearchActivity] */
                        /* JADX WARNING: type inference failed for: r0v5, types: [android.content.Context, com.wx.assistants.activity.AutoAddSearchActivity] */
                        /* JADX WARNING: type inference failed for: r0v10, types: [android.content.Context, com.wx.assistants.activity.AutoAddSearchActivity] */
                        public void onFinish(ConmdListBean conmdListBean) {
                            if (conmdListBean == null || conmdListBean.getCode() != 0) {
                                ? r0 = AutoAddSearchActivity.this;
                                ToastUtils.showToast(r0, conmdListBean.getCode() + ":" + conmdListBean.getMessage());
                                return;
                            }
                            try {
                                Gson gson = new Gson();
                                ArrayList arrayList = (ArrayList) gson.fromJson(gson.toJson(conmdListBean.getData().getList()), new TypeToken<ArrayList<PassiveCardBean>>() {
                                }.getType());
                                if (arrayList == null || arrayList.size() <= 0) {
                                    ToastUtils.showToast(AutoAddSearchActivity.this, "没搜索到数据！");
                                    return;
                                }
                                OperationParameterModel operationParameterModel = new OperationParameterModel();
                                operationParameterModel.setTaskNum("65");
                                operationParameterModel.setSayContent(AutoAddSearchActivity.this.sayContent.getText().toString());
                                operationParameterModel.setStartIndex(1);
                                operationParameterModel.setMaxOperaNum(AutoAddSearchActivity.this.canMaxNum);
                                operationParameterModel.setBreakAdd(AutoAddSearchActivity.this.isBreak);
                                operationParameterModel.setFfModel(AutoAddSearchActivity.this.ffModel);
                                operationParameterModel.setFfModelStartTime(AutoAddSearchActivity.this.ffModelStartTime);
                                operationParameterModel.setFfModelEndTime(AutoAddSearchActivity.this.ffModelEndTime);
                                operationParameterModel.setAutoRemarkContactsName(AutoAddSearchActivity.this.isRemark);
                                operationParameterModel.setSpaceTime(AutoAddSearchActivity.this.spaceTime);
                                operationParameterModel.setPassiveCardBeans(arrayList);
                                MyApplication.instance.setOperationParameterModel(operationParameterModel);
                                AutoAddSearchActivity.this.startWX(operationParameterModel);
                            } catch (Exception e) {
                                ToastUtils.showToast(AutoAddSearchActivity.this, "数据解异常！");
                            }
                        }
                    });
                }
            });
        } else if (id == 2131297636) {
            WebViewActivity.startActivity(this, "搜索加人视频教程", QBangApis.VIDEO_ADD_FANS_SEARCH);
        }
    }
}
