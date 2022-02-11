package com.wx.assistants.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wx.assistants.view.DragFloatActionButton;
import com.wx.assistants.view.HomeBottomSendLayout;
import www.linwg.org.lib.LCardView;

public class TabHomeFragment2_ViewBinding implements Unbinder {
    private TabHomeFragment2 target;
    private View view2131296373;
    private View view2131296415;
    private View view2131296475;
    private View view2131297031;
    private View view2131297052;

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.fragment.TabHomeFragment2_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v9, types: [com.wx.assistants.fragment.TabHomeFragment2_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v65, types: [com.wx.assistants.fragment.TabHomeFragment2_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v76, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.TabHomeFragment2_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r0v81, types: [com.wx.assistants.fragment.TabHomeFragment2_ViewBinding$5, android.view.View$OnClickListener] */
    @UiThread
    public TabHomeFragment2_ViewBinding(final TabHomeFragment2 tabHomeFragment2, View view) {
        this.target = tabHomeFragment2;
        View findRequiredView = Utils.findRequiredView(view, 2131296373, "field 'banner' and method 'onViewClicked'");
        tabHomeFragment2.banner = (ViewPager) Utils.castView(findRequiredView, 2131296373, "field 'banner'", ViewPager.class);
        this.view2131296373 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                tabHomeFragment2.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131296415, "field 'broad' and method 'onViewClicked'");
        tabHomeFragment2.broad = (TextView) Utils.castView(findRequiredView2, 2131296415, "field 'broad'", TextView.class);
        this.view2131296415 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                tabHomeFragment2.onViewClicked(view);
            }
        });
        tabHomeFragment2.singleVDImage = (ImageView) Utils.findRequiredViewAsType(view, 2131297388, "field 'singleVDImage'", ImageView.class);
        tabHomeFragment2.renmaiLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297239, "field 'renmaiLayout'", LinearLayout.class);
        tabHomeFragment2.companyLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296524, "field 'companyLayout'", LinearLayout.class);
        tabHomeFragment2.groupSendLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296735, "field 'groupSendLayout'", LinearLayout.class);
        tabHomeFragment2.otherLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297098, "field 'otherLayout'", LinearLayout.class);
        tabHomeFragment2.cleanLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296509, "field 'cleanLayout'", LinearLayout.class);
        tabHomeFragment2.toolLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297525, "field 'toolLayout'", LinearLayout.class);
        tabHomeFragment2.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        tabHomeFragment2.commonRV = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296523, "field 'commonRV'", RecyclerView.class);
        tabHomeFragment2.renmaiRV = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297240, "field 'renmaiRV'", RecyclerView.class);
        tabHomeFragment2.groupSendRV = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296737, "field 'groupSendRV'", RecyclerView.class);
        tabHomeFragment2.otherRV = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297099, "field 'otherRV'", RecyclerView.class);
        tabHomeFragment2.cleanRV = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296510, "field 'cleanRV'", RecyclerView.class);
        tabHomeFragment2.toolRV = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297526, "field 'toolRV'", RecyclerView.class);
        tabHomeFragment2.companyRV = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296525, "field 'companyRV'", RecyclerView.class);
        tabHomeFragment2.homeBottomSendLayout = (HomeBottomSendLayout) Utils.findRequiredViewAsType(view, 2131296745, "field 'homeBottomSendLayout'", HomeBottomSendLayout.class);
        tabHomeFragment2.navTitle = (Button) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", Button.class);
        View findRequiredView3 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        tabHomeFragment2.navRightLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                tabHomeFragment2.onViewClicked(view);
            }
        });
        tabHomeFragment2.cardView = (LCardView) Utils.findRequiredViewAsType(view, 2131296462, "field 'cardView'", LCardView.class);
        tabHomeFragment2.outCommonUse = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297100, "field 'outCommonUse'", LinearLayout.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131296475, "field 'changeGroupLayout' and method 'onViewClicked'");
        tabHomeFragment2.changeGroupLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131296475, "field 'changeGroupLayout'", LinearLayout.class);
        this.view2131296475 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                tabHomeFragment2.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297031, "field 'moveFriendLayout' and method 'onViewClicked'");
        tabHomeFragment2.moveFriendLayout = (LinearLayout) Utils.castView(findRequiredView5, 2131297031, "field 'moveFriendLayout'", LinearLayout.class);
        this.view2131297031 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                tabHomeFragment2.onViewClicked(view);
            }
        });
        tabHomeFragment2.floatBtn = (DragFloatActionButton) Utils.findRequiredViewAsType(view, 2131296679, "field 'floatBtn'", DragFloatActionButton.class);
    }

    @CallSuper
    public void unbind() {
        TabHomeFragment2 tabHomeFragment2 = this.target;
        if (tabHomeFragment2 != null) {
            this.target = null;
            tabHomeFragment2.banner = null;
            tabHomeFragment2.broad = null;
            tabHomeFragment2.singleVDImage = null;
            tabHomeFragment2.renmaiLayout = null;
            tabHomeFragment2.companyLayout = null;
            tabHomeFragment2.groupSendLayout = null;
            tabHomeFragment2.otherLayout = null;
            tabHomeFragment2.cleanLayout = null;
            tabHomeFragment2.toolLayout = null;
            tabHomeFragment2.refreshLayout = null;
            tabHomeFragment2.commonRV = null;
            tabHomeFragment2.renmaiRV = null;
            tabHomeFragment2.groupSendRV = null;
            tabHomeFragment2.otherRV = null;
            tabHomeFragment2.cleanRV = null;
            tabHomeFragment2.toolRV = null;
            tabHomeFragment2.companyRV = null;
            tabHomeFragment2.homeBottomSendLayout = null;
            tabHomeFragment2.navTitle = null;
            tabHomeFragment2.navRightLayout = null;
            tabHomeFragment2.cardView = null;
            tabHomeFragment2.outCommonUse = null;
            tabHomeFragment2.changeGroupLayout = null;
            tabHomeFragment2.moveFriendLayout = null;
            tabHomeFragment2.floatBtn = null;
            this.view2131296373.setOnClickListener((View.OnClickListener) null);
            this.view2131296373 = null;
            this.view2131296415.setOnClickListener((View.OnClickListener) null);
            this.view2131296415 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            this.view2131296475.setOnClickListener((View.OnClickListener) null);
            this.view2131296475 = null;
            this.view2131297031.setOnClickListener((View.OnClickListener) null);
            this.view2131297031 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
