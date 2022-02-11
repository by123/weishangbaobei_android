package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wx.assistants.view.CircleDeleteLayout;
import com.wx.assistants.view.CircleSelectSettingLayout;
import com.wx.assistants.view.TagCloudLayout;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class CloneCircleActivity_ViewBinding implements Unbinder {
    private CloneCircleActivity target;
    private View view2131296838;
    private View view2131296841;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public CloneCircleActivity_ViewBinding(CloneCircleActivity cloneCircleActivity) {
        this(cloneCircleActivity, cloneCircleActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CloneCircleActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v9, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CloneCircleActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v14, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CloneCircleActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r1v18, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CloneCircleActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r1v22, types: [com.wx.assistants.activity.CloneCircleActivity_ViewBinding$5, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v27, types: [com.wx.assistants.activity.CloneCircleActivity_ViewBinding$6, android.view.View$OnClickListener] */
    @UiThread
    public CloneCircleActivity_ViewBinding(final CloneCircleActivity cloneCircleActivity, View view) {
        this.target = cloneCircleActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        cloneCircleActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cloneCircleActivity.onViewClicked(view);
            }
        });
        cloneCircleActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        cloneCircleActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        cloneCircleActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView2, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cloneCircleActivity.onViewClicked(view);
            }
        });
        cloneCircleActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        cloneCircleActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cloneCircleActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131296841, "field 'ivStartDate' and method 'onViewClicked'");
        cloneCircleActivity.ivStartDate = (LinearLayout) Utils.castView(findRequiredView4, 2131296841, "field 'ivStartDate'", LinearLayout.class);
        this.view2131296841 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cloneCircleActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131296838, "field 'ivFinishDate' and method 'onViewClicked'");
        cloneCircleActivity.ivFinishDate = (LinearLayout) Utils.castView(findRequiredView5, 2131296838, "field 'ivFinishDate'", LinearLayout.class);
        this.view2131296838 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cloneCircleActivity.onViewClicked(view);
            }
        });
        cloneCircleActivity.flowViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        cloneCircleActivity.startWx = (Button) Utils.castView(findRequiredView6, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cloneCircleActivity.onViewClicked(view);
            }
        });
        cloneCircleActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        cloneCircleActivity.startDate = (TextView) Utils.findRequiredViewAsType(view, 2131297410, "field 'startDate'", TextView.class);
        cloneCircleActivity.endDate = (TextView) Utils.findRequiredViewAsType(view, 2131296634, "field 'endDate'", TextView.class);
        cloneCircleActivity.executeRemarkLayout = (CircleDeleteLayout) Utils.findRequiredViewAsType(view, 2131296646, "field 'executeRemarkLayout'", CircleDeleteLayout.class);
        cloneCircleActivity.circleSelectSettingLayout = (CircleSelectSettingLayout) Utils.findRequiredViewAsType(view, 2131296490, "field 'circleSelectSettingLayout'", CircleSelectSettingLayout.class);
        cloneCircleActivity.switchSpeedButton = (Switch) Utils.findRequiredViewAsType(view, 2131297449, "field 'switchSpeedButton'", Switch.class);
    }

    @CallSuper
    public void unbind() {
        CloneCircleActivity cloneCircleActivity = this.target;
        if (cloneCircleActivity != null) {
            this.target = null;
            cloneCircleActivity.navBack = null;
            cloneCircleActivity.navTitle = null;
            cloneCircleActivity.navRightText = null;
            cloneCircleActivity.navRightLayout = null;
            cloneCircleActivity.shadowLinearLayout = null;
            cloneCircleActivity.videoIntroduceLayout = null;
            cloneCircleActivity.ivStartDate = null;
            cloneCircleActivity.ivFinishDate = null;
            cloneCircleActivity.flowViewGroup = null;
            cloneCircleActivity.startWx = null;
            cloneCircleActivity.navRightImg = null;
            cloneCircleActivity.startDate = null;
            cloneCircleActivity.endDate = null;
            cloneCircleActivity.executeRemarkLayout = null;
            cloneCircleActivity.circleSelectSettingLayout = null;
            cloneCircleActivity.switchSpeedButton = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131296841.setOnClickListener((View.OnClickListener) null);
            this.view2131296841 = null;
            this.view2131296838.setOnClickListener((View.OnClickListener) null);
            this.view2131296838 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
