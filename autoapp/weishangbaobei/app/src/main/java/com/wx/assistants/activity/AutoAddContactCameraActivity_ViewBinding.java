package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class AutoAddContactCameraActivity_ViewBinding implements Unbinder {
    private AutoAddContactCameraActivity target;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297427;

    @UiThread
    public AutoAddContactCameraActivity_ViewBinding(AutoAddContactCameraActivity autoAddContactCameraActivity) {
        this(autoAddContactCameraActivity, autoAddContactCameraActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddContactCameraActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v9, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddContactCameraActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v13, types: [com.wx.assistants.activity.AutoAddContactCameraActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v17, types: [com.wx.assistants.activity.AutoAddContactCameraActivity_ViewBinding$4, android.view.View$OnClickListener] */
    @UiThread
    public AutoAddContactCameraActivity_ViewBinding(final AutoAddContactCameraActivity autoAddContactCameraActivity, View view) {
        this.target = autoAddContactCameraActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        autoAddContactCameraActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContactCameraActivity.onViewClicked(view);
            }
        });
        autoAddContactCameraActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        autoAddContactCameraActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout', method 'onViewClicked', and method 'onViewClicked'");
        autoAddContactCameraActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView2, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContactCameraActivity.onViewClicked(view);
                autoAddContactCameraActivity.onViewClicked();
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        autoAddContactCameraActivity.startWx = (Button) Utils.castView(findRequiredView3, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContactCameraActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297427, "field 'start_wx_tag' and method 'onViewClicked'");
        autoAddContactCameraActivity.start_wx_tag = (Button) Utils.castView(findRequiredView4, 2131297427, "field 'start_wx_tag'", Button.class);
        this.view2131297427 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContactCameraActivity.onViewClicked(view);
            }
        });
        autoAddContactCameraActivity.numText = (TextView) Utils.findRequiredViewAsType(view, 2131297079, "field 'numText'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        AutoAddContactCameraActivity autoAddContactCameraActivity = this.target;
        if (autoAddContactCameraActivity != null) {
            this.target = null;
            autoAddContactCameraActivity.navBack = null;
            autoAddContactCameraActivity.navTitle = null;
            autoAddContactCameraActivity.navRightText = null;
            autoAddContactCameraActivity.navRightLayout = null;
            autoAddContactCameraActivity.startWx = null;
            autoAddContactCameraActivity.start_wx_tag = null;
            autoAddContactCameraActivity.numText = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297427.setOnClickListener((View.OnClickListener) null);
            this.view2131297427 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
