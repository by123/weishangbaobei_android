package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.androidkun.xtablayout.XTabLayout;

public class AutoAddContactCameraAddressActivity_ViewBinding implements Unbinder {
    private AutoAddContactCameraAddressActivity target;
    private View view2131296731;
    private View view2131297049;
    private View view2131297052;
    private View view2131297636;

    @UiThread
    public AutoAddContactCameraAddressActivity_ViewBinding(AutoAddContactCameraAddressActivity autoAddContactCameraAddressActivity) {
        this(autoAddContactCameraAddressActivity, autoAddContactCameraAddressActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.AutoAddContactCameraAddressActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v12, types: [com.wx.assistants.activity.AutoAddContactCameraAddressActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v17, types: [com.wx.assistants.activity.AutoAddContactCameraAddressActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v28, types: [com.wx.assistants.activity.AutoAddContactCameraAddressActivity_ViewBinding$4, android.view.View$OnClickListener] */
    @UiThread
    public AutoAddContactCameraAddressActivity_ViewBinding(final AutoAddContactCameraAddressActivity autoAddContactCameraAddressActivity, View view) {
        this.target = autoAddContactCameraAddressActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        autoAddContactCameraAddressActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContactCameraAddressActivity.onViewClicked(view);
            }
        });
        autoAddContactCameraAddressActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        autoAddContactCameraAddressActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView2, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContactCameraAddressActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131296731, "field 'graphicExplanationLayout' and method 'onViewClicked'");
        autoAddContactCameraAddressActivity.graphicExplanationLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131296731, "field 'graphicExplanationLayout'", LinearLayout.class);
        this.view2131296731 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContactCameraAddressActivity.onViewClicked(view);
            }
        });
        autoAddContactCameraAddressActivity.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, 2131297639, "field 'viewPager'", ViewPager.class);
        autoAddContactCameraAddressActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        autoAddContactCameraAddressActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContactCameraAddressActivity.onViewClicked();
            }
        });
        autoAddContactCameraAddressActivity.xTabLayout = (XTabLayout) Utils.findRequiredViewAsType(view, 2131297695, "field 'xTabLayout'", XTabLayout.class);
    }

    @CallSuper
    public void unbind() {
        AutoAddContactCameraAddressActivity autoAddContactCameraAddressActivity = this.target;
        if (autoAddContactCameraAddressActivity != null) {
            this.target = null;
            autoAddContactCameraAddressActivity.navBack = null;
            autoAddContactCameraAddressActivity.navTitle = null;
            autoAddContactCameraAddressActivity.videoIntroduceLayout = null;
            autoAddContactCameraAddressActivity.graphicExplanationLayout = null;
            autoAddContactCameraAddressActivity.viewPager = null;
            autoAddContactCameraAddressActivity.navRightText = null;
            autoAddContactCameraAddressActivity.navRightLayout = null;
            autoAddContactCameraAddressActivity.xTabLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131296731.setOnClickListener((View.OnClickListener) null);
            this.view2131296731 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
