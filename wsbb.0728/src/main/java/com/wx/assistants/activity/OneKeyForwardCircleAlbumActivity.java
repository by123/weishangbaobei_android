package com.wx.assistants.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.CircleSettingEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PermissionUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.utils.fileutil.ListUtils;
import com.wx.assistants.view.CircleDeleteLayout;
import com.wx.assistants.view.CircleSelectSettingLayout;
import com.wx.assistants.webview.WebViewActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import permission.PermissionListener;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class OneKeyForwardCircleAlbumActivity extends BaseActivity {
    @BindView(2131296368)
    LinearLayout backStartLayout;
    @BindView(2131296490)
    CircleSelectSettingLayout circleSelectSettingLayout;
    /* access modifiers changed from: private */
    public int deleteMode = 0;
    @BindView(2131296646)
    CircleDeleteLayout executeRemarkLayout;
    /* access modifiers changed from: private */
    public boolean isBackStartPos = false;
    /* access modifiers changed from: private */
    public boolean isFastSpeed = true;
    /* access modifiers changed from: private */
    public boolean isSend = false;
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
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297445)
    Switch switchBackStartButton;
    @BindView(2131297447)
    Switch switchButton;
    @BindView(2131297449)
    Switch switchSpeedButton;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6750);
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("一键转发朋友圈/相册");
        this.startWx.setText("启动微信开始转发");
        this.switchButton.setChecked(false);
        this.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    boolean unused = OneKeyForwardCircleAlbumActivity.this.isSend = true;
                    OneKeyForwardCircleAlbumActivity.this.backStartLayout.setVisibility(0);
                    return;
                }
                boolean unused2 = OneKeyForwardCircleAlbumActivity.this.isSend = false;
                OneKeyForwardCircleAlbumActivity.this.backStartLayout.setVisibility(8);
            }
        });
        this.switchBackStartButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    boolean unused = OneKeyForwardCircleAlbumActivity.this.isBackStartPos = true;
                } else {
                    boolean unused2 = OneKeyForwardCircleAlbumActivity.this.isBackStartPos = false;
                }
            }
        });
        this.executeRemarkLayout.setCirculateModelListener(new CircleDeleteLayout.CirculateModelListener() {
            public void circulateMode(int i) {
                int unused = OneKeyForwardCircleAlbumActivity.this.deleteMode = i;
            }
        });
        this.switchSpeedButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                boolean unused = OneKeyForwardCircleAlbumActivity.this.isFastSpeed = z;
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardCircleAlbumActivity] */
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

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.OneKeyForwardCircleAlbumActivity] */
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

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardCircleAlbumActivity] */
    @OnClick({2131297049, 2131297425, 2131297636, 2131297052})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "第一步，点击上方启动微信开始转发按钮，如果提示开启辅助服务，请按照提示开启辅助服务\n\n第二步，进入朋友圈或者好友相册，将转发图标与你要转发的图文或者小视频冲在一条线上，点击转发即可。\n\n一键转发朋友圈/相册功能简介\n\n1.一键转发朋友圈/好友相册图文\n\n2.一键转发朋友圈/好友相册小视频\n\n3.其他转发功能敬请期待\n");
        } else if (id == 2131297425) {
            startCheck("3", true, new BaseActivity.OnStartCheckListener() {
                public void checkEnd() {
                    OperationParameterModel operationParameterModel = new OperationParameterModel();
                    operationParameterModel.setTaskNum("3");
                    operationParameterModel.setFastSpeed(OneKeyForwardCircleAlbumActivity.this.isFastSpeed);
                    operationParameterModel.setDeleteMode(OneKeyForwardCircleAlbumActivity.this.deleteMode);
                    operationParameterModel.setAutoSend(OneKeyForwardCircleAlbumActivity.this.isSend);
                    if (!OneKeyForwardCircleAlbumActivity.this.isSend || !OneKeyForwardCircleAlbumActivity.this.isBackStartPos) {
                        operationParameterModel.setBackStart(false);
                    } else {
                        operationParameterModel.setBackStart(true);
                    }
                    operationParameterModel.setSelectCircleModel(OneKeyForwardCircleAlbumActivity.this.selectModel);
                    operationParameterModel.setSelectCircleTags(OneKeyForwardCircleAlbumActivity.this.selectTags);
                    operationParameterModel.setSelectCircleGroups(OneKeyForwardCircleAlbumActivity.this.selectGroups);
                    MyApplication.instance.setOperationParameterModel(operationParameterModel);
                    OneKeyForwardCircleAlbumActivity.this.startWX(operationParameterModel);
                }
            });
        } else if (id == 2131297636) {
            WebViewActivity.startActivity(this, "一键转发朋友圈/相册视频教程", QBangApis.VIDEO_FORWARD_CIRCLE);
        }
    }
}
