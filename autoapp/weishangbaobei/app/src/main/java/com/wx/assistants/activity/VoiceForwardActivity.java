package com.wx.assistants.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.utils.ToastUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class VoiceForwardActivity extends BaseActivity {
    @BindView(2131296731)
    LinearLayout graphicExplanationLayout;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6758);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.VoiceForwardActivity] */
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
        this.navTitle.setText("语音转发");
    }

    @OnClick({2131297049, 2131297636, 2131296731})
    public void onViewClicked(View view) {
        if (view.getId() == 2131297049) {
            back();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
