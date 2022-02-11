package com.wx.assistants.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.codbking.widget.OnSureLisener;
import com.luck.picture.lib.R;
import com.stub.StubApp;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.webview.WebViewActivity;
import java.util.Date;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CleanCircleActivity extends BaseActivity {
    @BindView(2131296426)
    Button btnComplete;
    @BindView(2131296430)
    Button btnTime;
    /* access modifiers changed from: private */
    public String endDate = "";
    @BindView(2131296635)
    TextView endDateText;
    @BindView(2131296897)
    LinearLayout layoutTimeFrame;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297054)
    TextView navTitle;
    /* access modifiers changed from: private */
    public String startDate = "";
    @BindView(2131297411)
    TextView startDateText;
    @BindView(2131297425)
    Button startWx;
    /* access modifiers changed from: private */
    public int type = 0;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6712);
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("一键清理朋友圈");
        this.startWx.setText("启动微信清理朋友圈");
    }

    public void initData() {
        this.startDate = DateUtils.convertDate2String(new Date());
        this.endDate = DateUtils.convertDate2String(new Date());
        this.startDateText.setText(this.startDate);
        this.endDateText.setText(this.endDate);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.CleanCircleActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, com.wx.assistants.activity.CleanCircleActivity] */
    @OnClick({2131297049, 2131297425, 2131297636, 2131297052, 2131296426, 2131296430, 2131297411, 2131296635})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296426:
                this.type = 0;
                this.layoutTimeFrame.setVisibility(8);
                this.btnComplete.setBackgroundResource(2131230901);
                this.btnTime.setBackgroundResource(2131230902);
                this.btnComplete.setTextColor(getResources().getColor(2131100063));
                this.btnTime.setTextColor(getResources().getColor(R.color.main_color));
                return;
            case 2131296430:
                this.type = 1;
                this.layoutTimeFrame.setVisibility(0);
                this.btnComplete.setBackgroundResource(2131230900);
                this.btnTime.setBackgroundResource(2131230903);
                this.btnTime.setTextColor(getResources().getColor(2131100063));
                this.btnComplete.setTextColor(getResources().getColor(R.color.main_color));
                return;
            case 2131296635:
                DialogUIUtils.selectDate(this, "选择结束清理日期", DateUtils.convertString2Date(this.endDateText.getText().toString()), new OnSureLisener() {
                    public void onSure(Date date) {
                        String unused = CleanCircleActivity.this.endDate = DateUtils.convertDate2String(date);
                        CleanCircleActivity.this.endDateText.setText(CleanCircleActivity.this.endDate);
                    }
                });
                return;
            case 2131297049:
                back();
                return;
            case 2131297052:
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.清理朋友圈，从最近时间往以前的时间开始清理\n\n2.可自定义清理朋友圈的时间区间\n");
                return;
            case 2131297411:
                DialogUIUtils.selectDate(this, "选择开始清理日期", DateUtils.convertString2Date(this.startDateText.getText().toString()), new OnSureLisener() {
                    public void onSure(Date date) {
                        String unused = CleanCircleActivity.this.startDate = DateUtils.convertDate2String(date);
                        CleanCircleActivity.this.startDateText.setText(CleanCircleActivity.this.startDate);
                    }
                });
                return;
            case 2131297425:
                if (this.type != 1 || DateUtils.diff(DateUtils.convertString2Date(this.startDate), DateUtils.convertString2Date(this.endDate), 5) >= 0) {
                    startCheck("20", true, new BaseActivity.OnStartCheckListener() {
                        public void checkEnd() {
                            OperationParameterModel operationParameterModel = new OperationParameterModel();
                            operationParameterModel.setTaskNum("20");
                            operationParameterModel.setCleanCircleType(CleanCircleActivity.this.type);
                            if (CleanCircleActivity.this.type == 0) {
                                operationParameterModel.setStartDate("");
                                operationParameterModel.setEndDate("");
                            } else {
                                operationParameterModel.setStartDate(CleanCircleActivity.this.startDate);
                                operationParameterModel.setEndDate(CleanCircleActivity.this.endDate);
                            }
                            MyApplication.instance.setOperationParameterModel(operationParameterModel);
                            CleanCircleActivity.this.startWX(operationParameterModel);
                        }
                    });
                    return;
                } else {
                    ToastUtils.showToast(this, "开始时间不能大于结束时间");
                    return;
                }
            case 2131297636:
                WebViewActivity.startActivity(this, "一键清理朋友圈", QBangApis.VIDEO_CLEAN_CIRCLE);
                return;
            default:
                return;
        }
    }
}
