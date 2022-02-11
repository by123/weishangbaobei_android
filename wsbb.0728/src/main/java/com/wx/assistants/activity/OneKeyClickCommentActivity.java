package com.wx.assistants.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.NumSettingOnlyLayout;
import com.wx.assistants.webview.WebViewActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class OneKeyClickCommentActivity extends BaseActivity {
    @BindView(2131296507)
    LinearLayout cleanEditText;
    @BindView(2131296514)
    Switch clickSwitchBtn;
    @BindView(2131296520)
    FrameLayout commentLayout;
    @BindView(2131296522)
    Switch commentSwitchBtn;
    /* access modifiers changed from: private */
    public int defaultNum = 100;
    @BindView(2131296612)
    EditText edCommentContent;
    /* access modifiers changed from: private */
    public boolean isComment = false;
    /* access modifiers changed from: private */
    public boolean isPraise = true;
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
    /* access modifiers changed from: private */
    public int praiseCommentType = 0;
    /* access modifiers changed from: private */
    public String sayContent = "";
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6747);
    }

    private void initView() {
        MyApplication.isCommentSign = true;
        this.commentLayout.setVisibility(8);
        this.navTitle.setText("朋友圈一键点赞/评论");
        this.startWx.setText("启动微信开始点赞/评论");
        this.navRightImg.setVisibility(0);
        this.clickSwitchBtn.setChecked(true);
        this.commentSwitchBtn.setChecked(false);
        this.clickSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                boolean unused = OneKeyClickCommentActivity.this.isPraise = z;
            }
        });
        this.commentSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                boolean unused = OneKeyClickCommentActivity.this.isComment = z;
                if (z) {
                    OneKeyClickCommentActivity.this.commentLayout.setVisibility(0);
                } else {
                    OneKeyClickCommentActivity.this.commentLayout.setVisibility(8);
                }
            }
        });
        this.numSettingOnlyLayout.setNum(this.defaultNum, SocializeConstants.CANCLE_RESULTCODE, "点赞评论数量");
        this.numSettingOnlyLayout.setOnResultListener(new NumSettingOnlyLayout.OnResultListener() {
            public void result(int i) {
                int unused = OneKeyClickCommentActivity.this.defaultNum = i;
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyClickCommentActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyClickCommentActivity] */
    @OnClick({2131296507, 2131297425, 2131297636, 2131297052, 2131297049})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296507:
                this.edCommentContent.setText("");
                return;
            case 2131297049:
                back();
                return;
            case 2131297052:
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "第一步，使用一键评论前，先设置评论内容，设置为否，则不启用评论功能\n\n第二步，点击上方启动微信开始评论/点赞按钮，如果提示开启辅助服务，请按照提示开启辅助服务\n\n第三步，进入朋友圈，根据自己的设置，点击开始评论/点赞，即可启动微信进行操作\n");
                return;
            case 2131297425:
                if (validate()) {
                    startCheck("9", true, new BaseActivity.OnStartCheckListener() {
                        public void checkEnd() {
                            OperationParameterModel operationParameterModel = new OperationParameterModel();
                            operationParameterModel.setTaskNum("9");
                            operationParameterModel.setPraiseCommentType(OneKeyClickCommentActivity.this.praiseCommentType);
                            operationParameterModel.setMaxPraiseNum(OneKeyClickCommentActivity.this.defaultNum);
                            operationParameterModel.setMaxCommentNum(OneKeyClickCommentActivity.this.defaultNum);
                            operationParameterModel.setSayContent(OneKeyClickCommentActivity.this.sayContent + OneKeyClickCommentActivity.this.getAppendSign());
                            operationParameterModel.setCanRepeat(true);
                            MyApplication.instance.setOperationParameterModel(operationParameterModel);
                            OneKeyClickCommentActivity.this.startWX(operationParameterModel);
                        }
                    });
                    return;
                }
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "一键点赞评论视频教程", QBangApis.VIDEO_ONE_KEN_CLICK_COMMENT);
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyClickCommentActivity] */
    public boolean validate() {
        if (this.isPraise && this.isComment) {
            this.praiseCommentType = 0;
            MyApplication.isCommentSign = true;
        } else if (this.isPraise) {
            this.praiseCommentType = 1;
            MyApplication.isCommentSign = false;
        } else if (this.isComment) {
            this.praiseCommentType = 2;
            MyApplication.isCommentSign = true;
        }
        this.sayContent = this.edCommentContent.getText().toString();
        if (!this.isComment || (this.sayContent != null && !"".equals(this.sayContent))) {
            return true;
        }
        ToastUtils.showToast(this, "请输入评论语");
        return false;
    }
}
