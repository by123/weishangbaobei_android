package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class ActivationActivity_ViewBinding implements Unbinder {
    private ActivationActivity target;
    private View view2131297049;
    private View view2131297425;
    private View view2131297426;

    @UiThread
    public ActivationActivity_ViewBinding(ActivationActivity activationActivity) {
        this(activationActivity, activationActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.ActivationActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v9, types: [android.view.View$OnClickListener, com.wx.assistants.activity.ActivationActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v20, types: [android.view.View$OnClickListener, com.wx.assistants.activity.ActivationActivity_ViewBinding$3] */
    @UiThread
    public ActivationActivity_ViewBinding(final ActivationActivity activationActivity, View view) {
        this.target = activationActivity;
        activationActivity.activateEd = (EditText) Utils.findRequiredViewAsType(view, 2131296312, "field 'activateEd'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        activationActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                activationActivity.onViewClicked(view);
            }
        });
        activationActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        activationActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                activationActivity.onViewClicked(view);
            }
        });
        activationActivity.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297225, "field 'recyclerView'", RecyclerView.class);
        activationActivity.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        activationActivity.topLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297531, "field 'topLayout'", LinearLayout.class);
        activationActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        activationActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        activationActivity.navRightLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        activationActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        View findRequiredView3 = Utils.findRequiredView(view, 2131297426, "field 'startWx2' and method 'onViewClicked'");
        activationActivity.startWx2 = (Button) Utils.castView(findRequiredView3, 2131297426, "field 'startWx2'", Button.class);
        this.view2131297426 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                activationActivity.onViewClicked(view);
            }
        });
        activationActivity.setExpireTime = (TextView) Utils.findRequiredViewAsType(view, 2131297349, "field 'setExpireTime'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        ActivationActivity activationActivity = this.target;
        if (activationActivity != null) {
            this.target = null;
            activationActivity.activateEd = null;
            activationActivity.navBack = null;
            activationActivity.navTitle = null;
            activationActivity.startWx = null;
            activationActivity.recyclerView = null;
            activationActivity.refreshLayout = null;
            activationActivity.topLayout = null;
            activationActivity.navRightText = null;
            activationActivity.navRightImg = null;
            activationActivity.navRightLayout = null;
            activationActivity.shadowLinearLayout = null;
            activationActivity.startWx2 = null;
            activationActivity.setExpireTime = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297426.setOnClickListener((View.OnClickListener) null);
            this.view2131297426 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
