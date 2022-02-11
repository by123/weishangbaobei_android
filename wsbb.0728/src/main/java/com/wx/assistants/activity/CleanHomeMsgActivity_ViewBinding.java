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
import com.wx.assistants.view.CustomRadioLayout;
import com.wx.assistants.view.CustomRadioSwitchLayout;

public class CleanHomeMsgActivity_ViewBinding implements Unbinder {
    private CleanHomeMsgActivity target;
    private View view2131296878;
    private View view2131296879;
    private View view2131297049;
    private View view2131297052;
    private View view2131297412;
    private View view2131297422;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public CleanHomeMsgActivity_ViewBinding(CleanHomeMsgActivity cleanHomeMsgActivity) {
        this(cleanHomeMsgActivity, cleanHomeMsgActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.CleanHomeMsgActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v12, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanHomeMsgActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v20, types: [com.wx.assistants.activity.CleanHomeMsgActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v25, types: [com.wx.assistants.activity.CleanHomeMsgActivity_ViewBinding$4, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v30, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanHomeMsgActivity_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r0v44, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanHomeMsgActivity_ViewBinding$6] */
    /* JADX WARNING: type inference failed for: r0v58, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanHomeMsgActivity_ViewBinding$7] */
    /* JADX WARNING: type inference failed for: r0v69, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanHomeMsgActivity_ViewBinding$8] */
    @UiThread
    public CleanHomeMsgActivity_ViewBinding(final CleanHomeMsgActivity cleanHomeMsgActivity, View view) {
        this.target = cleanHomeMsgActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297412, "field 'startLayout' and method 'onViewClicked'");
        cleanHomeMsgActivity.startLayout = (LinearLayout) Utils.castView(findRequiredView, 2131297412, "field 'startLayout'", LinearLayout.class);
        this.view2131297412 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanHomeMsgActivity.onViewClicked(view);
            }
        });
        cleanHomeMsgActivity.startPull = (TextView) Utils.findRequiredViewAsType(view, 2131297419, "field 'startPull'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        cleanHomeMsgActivity.navBack = (LinearLayout) Utils.castView(findRequiredView2, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanHomeMsgActivity.onViewClicked(view);
            }
        });
        cleanHomeMsgActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView3 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        cleanHomeMsgActivity.startWx = (Button) Utils.castView(findRequiredView3, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanHomeMsgActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        cleanHomeMsgActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanHomeMsgActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297422, "field 'startPullLayout' and method 'onViewClicked'");
        cleanHomeMsgActivity.startPullLayout = (LinearLayout) Utils.castView(findRequiredView5, 2131297422, "field 'startPullLayout'", LinearLayout.class);
        this.view2131297422 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanHomeMsgActivity.onViewClicked(view);
            }
        });
        cleanHomeMsgActivity.customRadioSwitchLayout = (CustomRadioSwitchLayout) Utils.findRequiredViewAsType(view, 2131296569, "field 'customRadioSwitchLayout'", CustomRadioSwitchLayout.class);
        cleanHomeMsgActivity.customRadioLayout2 = (CustomRadioLayout) Utils.findRequiredViewAsType(view, 2131296567, "field 'customRadioLayout2'", CustomRadioLayout.class);
        cleanHomeMsgActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        cleanHomeMsgActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView6, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanHomeMsgActivity.onViewClicked(view);
            }
        });
        cleanHomeMsgActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        cleanHomeMsgActivity.customRadioSwitchLayout2 = (CustomRadioSwitchLayout) Utils.findRequiredViewAsType(view, 2131296571, "field 'customRadioSwitchLayout2'", CustomRadioSwitchLayout.class);
        cleanHomeMsgActivity.jumpLabel2 = (TextView) Utils.findRequiredViewAsType(view, 2131296875, "field 'jumpLabel2'", TextView.class);
        View findRequiredView7 = Utils.findRequiredView(view, 2131296879, "field 'jumpLabelLayout2' and method 'onViewClicked'");
        cleanHomeMsgActivity.jumpLabelLayout2 = (LinearLayout) Utils.castView(findRequiredView7, 2131296879, "field 'jumpLabelLayout2'", LinearLayout.class);
        this.view2131296879 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanHomeMsgActivity.onViewClicked(view);
            }
        });
        cleanHomeMsgActivity.customRadioSwitchLayout1 = (CustomRadioSwitchLayout) Utils.findRequiredViewAsType(view, 2131296570, "field 'customRadioSwitchLayout1'", CustomRadioSwitchLayout.class);
        cleanHomeMsgActivity.jumpLabel1 = (TextView) Utils.findRequiredViewAsType(view, 2131296874, "field 'jumpLabel1'", TextView.class);
        View findRequiredView8 = Utils.findRequiredView(view, 2131296878, "field 'jumpLabelLayout1' and method 'onViewClicked'");
        cleanHomeMsgActivity.jumpLabelLayout1 = (LinearLayout) Utils.castView(findRequiredView8, 2131296878, "field 'jumpLabelLayout1'", LinearLayout.class);
        this.view2131296878 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanHomeMsgActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        CleanHomeMsgActivity cleanHomeMsgActivity = this.target;
        if (cleanHomeMsgActivity != null) {
            this.target = null;
            cleanHomeMsgActivity.startLayout = null;
            cleanHomeMsgActivity.startPull = null;
            cleanHomeMsgActivity.navBack = null;
            cleanHomeMsgActivity.navTitle = null;
            cleanHomeMsgActivity.startWx = null;
            cleanHomeMsgActivity.videoIntroduceLayout = null;
            cleanHomeMsgActivity.startPullLayout = null;
            cleanHomeMsgActivity.customRadioSwitchLayout = null;
            cleanHomeMsgActivity.customRadioLayout2 = null;
            cleanHomeMsgActivity.navRightImg = null;
            cleanHomeMsgActivity.navRightLayout = null;
            cleanHomeMsgActivity.navRightText = null;
            cleanHomeMsgActivity.customRadioSwitchLayout2 = null;
            cleanHomeMsgActivity.jumpLabel2 = null;
            cleanHomeMsgActivity.jumpLabelLayout2 = null;
            cleanHomeMsgActivity.customRadioSwitchLayout1 = null;
            cleanHomeMsgActivity.jumpLabel1 = null;
            cleanHomeMsgActivity.jumpLabelLayout1 = null;
            this.view2131297412.setOnClickListener((View.OnClickListener) null);
            this.view2131297412 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131297422.setOnClickListener((View.OnClickListener) null);
            this.view2131297422 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            this.view2131296879.setOnClickListener((View.OnClickListener) null);
            this.view2131296879 = null;
            this.view2131296878.setOnClickListener((View.OnClickListener) null);
            this.view2131296878 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
