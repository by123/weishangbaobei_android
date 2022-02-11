package com.wx.assistants.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.PermissionUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.webview.WebViewActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import permission.PermissionListener;

public class OneKeyForwardWindowActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public boolean isSend = false;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6753);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardWindowActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.OneKeyForwardWindowActivity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        PermissionUtils.checkReadAndWriteExternalStorage(this, new PermissionListener() {
            public void permissionDenied(@NonNull String[] strArr) {
            }

            public void permissionGranted(@NonNull String[] strArr) {
            }
        });
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("聊天窗口转发");
        this.startWx.setText("启动微信开始转发");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardWindowActivity] */
    @OnClick({2131297049, 2131297425, 2131297636, 2131297052})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "第一步，点击上方启动微信开始转发按钮，如果提示开启辅助服务，请按照提示开启辅助服务\n\n第二步，微信聊天窗口，将转发图标与你要转发的图文或者小视频冲在一条线上，点击转发即可。\n\n一键转发聊天窗口功能简介\n\n1.一键转发聊天窗口图文\n\n2.一键转发聊天窗口小视频\n\n3.其他转发功能敬请期待");
        } else if (id == 2131297425) {
            startCheck("15", true, new BaseActivity.OnStartCheckListener() {
                public void checkEnd() {
                    OperationParameterModel operationParameterModel = new OperationParameterModel();
                    operationParameterModel.setTaskNum("15");
                    operationParameterModel.setAutoSend(OneKeyForwardWindowActivity.this.isSend);
                    MyApplication.instance.setOperationParameterModel(operationParameterModel);
                    OneKeyForwardWindowActivity.this.startWX(operationParameterModel);
                }
            });
        } else if (id == 2131297636) {
            WebViewActivity.startActivity(this, "聊天窗口转发视频教程", QBangApis.VIDEO_FORWARD_WINDOWS);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
