package com.wx.assistants.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.androidkun.xtablayout.XTabLayout;
import com.stub.StubApp;
import com.wx.assistants.Enity.ContactScanBean;
import com.wx.assistants.adapter.FragmentScanContactAdapter;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.fragment.ContactScanAddedFragment;
import com.wx.assistants.fragment.ContactScanUnAddFragment;
import com.wx.assistants.utils.ToastUtils;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AutoAddContactCameraAddressActivity extends BaseActivity {
    public static List<ContactScanBean> phones = new ArrayList();
    private FragmentScanContactAdapter adapter;
    List<Fragment> fragments = new ArrayList();
    @BindView(2131296731)
    LinearLayout graphicExplanationLayout;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;
    @BindView(2131297639)
    ViewPager viewPager;
    @BindView(2131297695)
    XTabLayout xTabLayout;

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    @OnClick({2131297052})
    public void onViewClicked() {
    }

    static {
        StubApp.interface11(6698);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.activity.AutoAddContactCameraAddressActivity, android.content.Context] */
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
        this.navTitle.setText("通讯录");
        ArrayList arrayList = new ArrayList();
        arrayList.add("未添加");
        arrayList.add("已添加");
        this.fragments.add(new ContactScanUnAddFragment());
        this.fragments.add(new ContactScanAddedFragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), this.fragments, arrayList);
        ViewPager findViewById = findViewById(2131297639);
        findViewById.setAdapter(fragmentAdapter);
        findViewById.setOffscreenPageLimit(2);
        this.xTabLayout.setupWithViewPager(findViewById);
        this.xTabLayout.setupWithViewPager(findViewById);
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

    public class FragmentAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> mFragments;
        private List<String> mTitles;

        public FragmentAdapter(FragmentManager fragmentManager, List<Fragment> list, List<String> list2) {
            super(fragmentManager);
            this.mFragments = list;
            this.mTitles = list2;
        }

        public Fragment getItem(int i) {
            return this.mFragments.get(i);
        }

        public int getCount() {
            return this.mFragments.size();
        }

        public CharSequence getPageTitle(int i) {
            return this.mTitles.get(i);
        }
    }
}
