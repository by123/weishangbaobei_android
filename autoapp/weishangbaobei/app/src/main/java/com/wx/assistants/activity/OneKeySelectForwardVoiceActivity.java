package com.wx.assistants.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.athbk.ultimatetablayout.OnClickTabListener;
import com.athbk.ultimatetablayout.UltimateTabLayout;
import com.stub.StubApp;
import com.wx.assistants.adapter.FragmentSelectVoiceAdapter;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class OneKeySelectForwardVoiceActivity extends BaseActivity {
    private FragmentSelectVoiceAdapter adapter;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297363)
    ShadowLinearLayout shadowLinearLayout;
    @BindView(2131297452)
    UltimateTabLayout tabLayout;
    @BindView(2131297639)
    ViewPager viewPager;

    static {
        StubApp.interface11(6756);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.OneKeySelectForwardVoiceActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    public void initView() {
        this.navTitle.setText("选择语音");
        ArrayList arrayList = new ArrayList();
        arrayList.add("我的语音");
        arrayList.add("微信语音");
        arrayList.add("我的收藏");
        this.adapter = new FragmentSelectVoiceAdapter(getSupportFragmentManager(), arrayList);
        this.viewPager.setAdapter(this.adapter);
        this.tabLayout.setOnClickTabListener(new OnClickTabListener() {
            public void onClickTab(int i) {
                OneKeySelectForwardVoiceActivity.this.viewPager.setCurrentItem(i);
                if (i == 1) {
                    OneKeySelectForwardVoiceActivity.this.tabLayout.setNumberBadge(i, 0);
                } else {
                    OneKeySelectForwardVoiceActivity.this.tabLayout.setNumberBadge(i, 1);
                }
            }
        });
        this.tabLayout.setViewPager(this.viewPager, this.adapter);
        this.viewPager.setOffscreenPageLimit(1);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.OneKeySelectForwardVoiceActivity] */
    @OnClick({2131297049, 2131297052})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            WebViewActivity.startActivity(this, "语音转发视频", "https://material.abcvabc.com/20190329/48.mp4");
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
