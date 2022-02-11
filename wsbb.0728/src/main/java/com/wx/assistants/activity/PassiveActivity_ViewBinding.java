package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class PassiveActivity_ViewBinding implements Unbinder {
    private PassiveActivity target;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public PassiveActivity_ViewBinding(PassiveActivity passiveActivity) {
        this(passiveActivity, passiveActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.PassiveActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v12, types: [android.view.View$OnClickListener, com.wx.assistants.activity.PassiveActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v17, types: [android.view.View$OnClickListener, com.wx.assistants.activity.PassiveActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v25, types: [android.view.View$OnClickListener, com.wx.assistants.activity.PassiveActivity_ViewBinding$4] */
    @UiThread
    public PassiveActivity_ViewBinding(final PassiveActivity passiveActivity, View view) {
        this.target = passiveActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        passiveActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                passiveActivity.onViewClicked(view);
            }
        });
        passiveActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        passiveActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                passiveActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        passiveActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                passiveActivity.onViewClicked(view);
            }
        });
        passiveActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        passiveActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                passiveActivity.onViewClicked(view);
            }
        });
        passiveActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        passiveActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        passiveActivity.numText = (TextView) Utils.findRequiredViewAsType(view, 2131297079, "field 'numText'", TextView.class);
        passiveActivity.commonRV = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296523, "field 'commonRV'", RecyclerView.class);
        passiveActivity.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        PassiveActivity passiveActivity = this.target;
        if (passiveActivity != null) {
            this.target = null;
            passiveActivity.navBack = null;
            passiveActivity.navTitle = null;
            passiveActivity.startWx = null;
            passiveActivity.videoIntroduceLayout = null;
            passiveActivity.navRightImg = null;
            passiveActivity.navRightLayout = null;
            passiveActivity.navRightText = null;
            passiveActivity.shadowLinearLayout = null;
            passiveActivity.numText = null;
            passiveActivity.commonRV = null;
            passiveActivity.refreshLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
