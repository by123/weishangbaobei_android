package com.wx.assistants.activity;

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
import com.codbking.widget.OnSureLisener;
import com.stub.StubApp;
import com.wx.assistants.adapter.TagSelectAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.CircleSettingEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.utils.fileutil.ListUtils;
import com.wx.assistants.view.CircleDeleteLayout;
import com.wx.assistants.view.CircleSelectSettingLayout;
import com.wx.assistants.view.TagCloudLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class CloneCircleActivity extends BaseActivity {
    @BindView(2131296490)
    CircleSelectSettingLayout circleSelectSettingLayout;
    /* access modifiers changed from: private */
    public int cloneType = 0;
    /* access modifiers changed from: private */
    public int deleteMode = 0;
    @BindView(2131296634)
    TextView endDate;
    /* access modifiers changed from: private */
    public String endDateStr = "";
    @BindView(2131296646)
    CircleDeleteLayout executeRemarkLayout;
    @BindView(2131296681)
    TagCloudLayout flowViewGroup;
    /* access modifiers changed from: private */
    public boolean isFastSpeed = true;
    @BindView(2131296838)
    LinearLayout ivFinishDate;
    @BindView(2131296841)
    LinearLayout ivStartDate;
    private ArrayList<String> mList;
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
    public String selectGroups = "";
    /* access modifiers changed from: private */
    public String selectModel = "公开";
    /* access modifiers changed from: private */
    public String selectTags = "";
    @BindView(2131297363)
    ShadowLinearLayout shadowLinearLayout;
    @BindView(2131297410)
    TextView startDate;
    /* access modifiers changed from: private */
    public String startDateStr = "";
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297449)
    Switch switchSpeedButton;
    private TagSelectAdapter tagSelectAdapter;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6720);
    }

    private void initView() {
        this.navTitle.setText("克隆朋友圈");
        this.startWx.setText("启动微信开始克隆");
        this.navRightImg.setVisibility(0);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.CloneCircleActivity] */
    public void initData() {
        this.startDateStr = DateUtils.convertDate2String(new Date());
        this.endDateStr = DateUtils.convertDate2String(new Date());
        this.startDate.setText(this.startDateStr);
        this.endDate.setText(this.endDateStr);
        this.mList = new ArrayList<>();
        this.mList.addAll(Arrays.asList(getResources().getStringArray(2130903041)));
        this.tagSelectAdapter = new TagSelectAdapter(this, this.mList);
        this.flowViewGroup.setAdapter(this.tagSelectAdapter, 0);
        this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                int unused = CloneCircleActivity.this.cloneType = i;
                PrintStream printStream = System.out;
                printStream.println("WS_BABY_CLONE-TYPE." + CloneCircleActivity.this.cloneType);
            }
        });
        this.executeRemarkLayout.setCirculateModelListener(new CircleDeleteLayout.CirculateModelListener() {
            public void circulateMode(int i) {
                int unused = CloneCircleActivity.this.deleteMode = i;
            }
        });
        this.switchSpeedButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                boolean unused = CloneCircleActivity.this.isFastSpeed = z;
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.CloneCircleActivity] */
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
    public void onEventMainThread(CircleSettingEvent circleSettingEvent) {
        this.selectModel = circleSettingEvent.getCircleModel();
        this.selectTags = circleSettingEvent.getSelectTags();
        this.selectGroups = circleSettingEvent.getSelectGroups();
        LogUtils.log("WS_BABY_event " + this.selectModel + ListUtils.DEFAULT_JOIN_SEPARATOR + this.selectTags + ListUtils.DEFAULT_JOIN_SEPARATOR + this.selectGroups);
        this.circleSelectSettingLayout.setResult(this.selectModel, this.selectTags, this.selectGroups);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.CloneCircleActivity] */
    @OnClick({2131297049, 2131297636, 2131297052, 2131296841, 2131296838, 2131297425})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296838:
                DialogUIUtils.selectDate(this, "选择结束克隆日期", DateUtils.convertString2Date(this.endDate.getText().toString()), new OnSureLisener() {
                    public void onSure(Date date) {
                        String unused = CloneCircleActivity.this.endDateStr = DateUtils.convertDate2String(date);
                        CloneCircleActivity.this.endDate.setText(CloneCircleActivity.this.endDateStr);
                    }
                });
                return;
            case 2131296841:
                DialogUIUtils.selectDate(this, "选择开始克隆日期", DateUtils.convertString2Date(this.startDate.getText().toString()), new OnSureLisener() {
                    public void onSure(Date date) {
                        String unused = CloneCircleActivity.this.startDateStr = DateUtils.convertDate2String(date);
                        CloneCircleActivity.this.startDate.setText(CloneCircleActivity.this.startDateStr);
                    }
                });
                return;
            case 2131297049:
                back();
                return;
            case 2131297052:
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.选择任意好友朋友圈，可克隆所有好友状态，还可设置开始和结束的日期，筛选克隆内容。\n\n2.开始克隆前，请先检查您克隆的好友是否同名。\n\n3.此功能仅对半年以上会员用户授权使用。");
                return;
            case 2131297425:
                if (DateUtils.diff(DateUtils.convertString2Date(this.startDateStr), DateUtils.convertString2Date(this.endDateStr), 5) < 0) {
                    ToastUtils.showToast(this, "开始时间不能大于结束时间");
                    return;
                } else {
                    startCheck("13", true, new BaseActivity.OnStartCheckListener() {
                        public void checkEnd() {
                            OperationParameterModel operationParameterModel = new OperationParameterModel();
                            operationParameterModel.setTaskNum("13");
                            operationParameterModel.setFastSpeed(CloneCircleActivity.this.isFastSpeed);
                            operationParameterModel.setCloneCircleType(CloneCircleActivity.this.cloneType);
                            operationParameterModel.setDeleteMode(CloneCircleActivity.this.deleteMode);
                            operationParameterModel.setStartDate(CloneCircleActivity.this.startDateStr);
                            operationParameterModel.setEndDate(CloneCircleActivity.this.endDateStr);
                            operationParameterModel.setSelectCircleModel(CloneCircleActivity.this.selectModel);
                            operationParameterModel.setSelectCircleTags(CloneCircleActivity.this.selectTags);
                            operationParameterModel.setSelectCircleGroups(CloneCircleActivity.this.selectGroups);
                            MyApplication.instance.setOperationParameterModel(operationParameterModel);
                            CloneCircleActivity.this.startWX(operationParameterModel);
                        }
                    });
                    return;
                }
            case 2131297636:
                WebViewActivity.startActivity(this, "一键克隆朋友圈", QBangApis.VIDEO_CLONE_CIRCLE);
                return;
            default:
                return;
        }
    }
}
