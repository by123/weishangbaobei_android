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

public class CleanCircleActivity_ViewBinding implements Unbinder {
    private CleanCircleActivity target;
    private View view2131296426;
    private View view2131296430;
    private View view2131296635;
    private View view2131297049;
    private View view2131297052;
    private View view2131297411;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public CleanCircleActivity_ViewBinding(CleanCircleActivity cleanCircleActivity) {
        this(cleanCircleActivity, cleanCircleActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanCircleActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v7, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanCircleActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v12, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanCircleActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r1v16, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanCircleActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r1v20, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanCircleActivity_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r1v24, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanCircleActivity_ViewBinding$6] */
    /* JADX WARNING: type inference failed for: r1v30, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanCircleActivity_ViewBinding$7] */
    /* JADX WARNING: type inference failed for: r0v27, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanCircleActivity_ViewBinding$8] */
    @UiThread
    public CleanCircleActivity_ViewBinding(final CleanCircleActivity cleanCircleActivity, View view) {
        this.target = cleanCircleActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131296426, "field 'btnComplete' and method 'onViewClicked'");
        cleanCircleActivity.btnComplete = (Button) Utils.castView(findRequiredView, 2131296426, "field 'btnComplete'", Button.class);
        this.view2131296426 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanCircleActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131296430, "field 'btnTime' and method 'onViewClicked'");
        cleanCircleActivity.btnTime = (Button) Utils.castView(findRequiredView2, 2131296430, "field 'btnTime'", Button.class);
        this.view2131296430 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanCircleActivity.onViewClicked(view);
            }
        });
        cleanCircleActivity.layoutTimeFrame = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296897, "field 'layoutTimeFrame'", LinearLayout.class);
        View findRequiredView3 = Utils.findRequiredView(view, 2131297411, "field 'startDateText' and method 'onViewClicked'");
        cleanCircleActivity.startDateText = (TextView) Utils.castView(findRequiredView3, 2131297411, "field 'startDateText'", TextView.class);
        this.view2131297411 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanCircleActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131296635, "field 'endDateText' and method 'onViewClicked'");
        cleanCircleActivity.endDateText = (TextView) Utils.castView(findRequiredView4, 2131296635, "field 'endDateText'", TextView.class);
        this.view2131296635 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanCircleActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        cleanCircleActivity.navBack = (LinearLayout) Utils.castView(findRequiredView5, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanCircleActivity.onViewClicked(view);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        cleanCircleActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView6, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanCircleActivity.onViewClicked(view);
            }
        });
        cleanCircleActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        cleanCircleActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView7 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        cleanCircleActivity.startWx = (Button) Utils.castView(findRequiredView7, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanCircleActivity.onViewClicked(view);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        cleanCircleActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView8, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanCircleActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        CleanCircleActivity cleanCircleActivity = this.target;
        if (cleanCircleActivity != null) {
            this.target = null;
            cleanCircleActivity.btnComplete = null;
            cleanCircleActivity.btnTime = null;
            cleanCircleActivity.layoutTimeFrame = null;
            cleanCircleActivity.startDateText = null;
            cleanCircleActivity.endDateText = null;
            cleanCircleActivity.navBack = null;
            cleanCircleActivity.navRightLayout = null;
            cleanCircleActivity.navRightImg = null;
            cleanCircleActivity.navTitle = null;
            cleanCircleActivity.startWx = null;
            cleanCircleActivity.videoIntroduceLayout = null;
            this.view2131296426.setOnClickListener((View.OnClickListener) null);
            this.view2131296426 = null;
            this.view2131296430.setOnClickListener((View.OnClickListener) null);
            this.view2131296430 = null;
            this.view2131297411.setOnClickListener((View.OnClickListener) null);
            this.view2131297411 = null;
            this.view2131296635.setOnClickListener((View.OnClickListener) null);
            this.view2131296635 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
