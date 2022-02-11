package com.wx.assistants.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wx.assistants.view.HomeBottomSendLayout;

public class TabHomeFragment_ViewBinding implements Unbinder {
    private TabHomeFragment target;
    private View view2131296373;
    private View view2131296415;
    private View view2131297052;

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.fragment.TabHomeFragment_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v9, types: [com.wx.assistants.fragment.TabHomeFragment_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v19, types: [com.wx.assistants.fragment.TabHomeFragment_ViewBinding$3, android.view.View$OnClickListener] */
    @UiThread
    public TabHomeFragment_ViewBinding(final TabHomeFragment tabHomeFragment, View view) {
        this.target = tabHomeFragment;
        View findRequiredView = Utils.findRequiredView(view, 2131296373, "field 'banner' and method 'onViewClicked'");
        tabHomeFragment.banner = (ViewPager) Utils.castView(findRequiredView, 2131296373, "field 'banner'", ViewPager.class);
        this.view2131296373 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                tabHomeFragment.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131296415, "field 'broad' and method 'onViewClicked'");
        tabHomeFragment.broad = (TextView) Utils.castView(findRequiredView2, 2131296415, "field 'broad'", TextView.class);
        this.view2131296415 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                tabHomeFragment.onViewClicked(view);
            }
        });
        tabHomeFragment.singleVDImage = (ImageView) Utils.findRequiredViewAsType(view, 2131297388, "field 'singleVDImage'", ImageView.class);
        tabHomeFragment.renmaiLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297239, "field 'renmaiLayout'", LinearLayout.class);
        tabHomeFragment.companyLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296524, "field 'companyLayout'", LinearLayout.class);
        tabHomeFragment.groupSendLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296735, "field 'groupSendLayout'", LinearLayout.class);
        tabHomeFragment.otherLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297098, "field 'otherLayout'", LinearLayout.class);
        tabHomeFragment.cleanLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296509, "field 'cleanLayout'", LinearLayout.class);
        tabHomeFragment.toolLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297525, "field 'toolLayout'", LinearLayout.class);
        tabHomeFragment.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        tabHomeFragment.commonRV = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296523, "field 'commonRV'", RecyclerView.class);
        tabHomeFragment.renmaiRV = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297240, "field 'renmaiRV'", RecyclerView.class);
        tabHomeFragment.groupSendRV = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296737, "field 'groupSendRV'", RecyclerView.class);
        tabHomeFragment.otherRV = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297099, "field 'otherRV'", RecyclerView.class);
        tabHomeFragment.cleanRV = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296510, "field 'cleanRV'", RecyclerView.class);
        tabHomeFragment.toolRV = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297526, "field 'toolRV'", RecyclerView.class);
        tabHomeFragment.companyRV = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296525, "field 'companyRV'", RecyclerView.class);
        tabHomeFragment.homeBottomSendLayout = (HomeBottomSendLayout) Utils.findRequiredViewAsType(view, 2131296745, "field 'homeBottomSendLayout'", HomeBottomSendLayout.class);
        View findRequiredView3 = Utils.findRequiredView(view, 2131297052, "method 'onViewClicked'");
        this.view2131297052 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                tabHomeFragment.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        TabHomeFragment tabHomeFragment = this.target;
        if (tabHomeFragment != null) {
            this.target = null;
            tabHomeFragment.banner = null;
            tabHomeFragment.broad = null;
            tabHomeFragment.singleVDImage = null;
            tabHomeFragment.renmaiLayout = null;
            tabHomeFragment.companyLayout = null;
            tabHomeFragment.groupSendLayout = null;
            tabHomeFragment.otherLayout = null;
            tabHomeFragment.cleanLayout = null;
            tabHomeFragment.toolLayout = null;
            tabHomeFragment.refreshLayout = null;
            tabHomeFragment.commonRV = null;
            tabHomeFragment.renmaiRV = null;
            tabHomeFragment.groupSendRV = null;
            tabHomeFragment.otherRV = null;
            tabHomeFragment.cleanRV = null;
            tabHomeFragment.toolRV = null;
            tabHomeFragment.companyRV = null;
            tabHomeFragment.homeBottomSendLayout = null;
            this.view2131296373.setOnClickListener((View.OnClickListener) null);
            this.view2131296373 = null;
            this.view2131296415.setOnClickListener((View.OnClickListener) null);
            this.view2131296415 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
