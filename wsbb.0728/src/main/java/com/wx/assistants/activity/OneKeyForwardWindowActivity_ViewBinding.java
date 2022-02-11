package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class OneKeyForwardWindowActivity_ViewBinding implements Unbinder {
    private OneKeyForwardWindowActivity target;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public OneKeyForwardWindowActivity_ViewBinding(OneKeyForwardWindowActivity oneKeyForwardWindowActivity) {
        this(oneKeyForwardWindowActivity, oneKeyForwardWindowActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyForwardWindowActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v12, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyForwardWindowActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v17, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyForwardWindowActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v22, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyForwardWindowActivity_ViewBinding$4] */
    @UiThread
    public OneKeyForwardWindowActivity_ViewBinding(final OneKeyForwardWindowActivity oneKeyForwardWindowActivity, View view) {
        this.target = oneKeyForwardWindowActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        oneKeyForwardWindowActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardWindowActivity.onViewClicked(view);
            }
        });
        oneKeyForwardWindowActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        oneKeyForwardWindowActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardWindowActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        oneKeyForwardWindowActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardWindowActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        oneKeyForwardWindowActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardWindowActivity.onViewClicked(view);
            }
        });
        oneKeyForwardWindowActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        OneKeyForwardWindowActivity oneKeyForwardWindowActivity = this.target;
        if (oneKeyForwardWindowActivity != null) {
            this.target = null;
            oneKeyForwardWindowActivity.navBack = null;
            oneKeyForwardWindowActivity.navTitle = null;
            oneKeyForwardWindowActivity.startWx = null;
            oneKeyForwardWindowActivity.videoIntroduceLayout = null;
            oneKeyForwardWindowActivity.navRightLayout = null;
            oneKeyForwardWindowActivity.navRightImg = null;
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
