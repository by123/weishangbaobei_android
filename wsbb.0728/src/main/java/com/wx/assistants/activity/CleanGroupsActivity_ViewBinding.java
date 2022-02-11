package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import worker8.com.github.radiogroupplus.RadioGroupPlus;

public class CleanGroupsActivity_ViewBinding implements Unbinder {
    private CleanGroupsActivity target;
    private View view2131296877;
    private View view2131297049;
    private View view2131297052;
    private View view2131297185;
    private View view2131297186;
    private View view2131297187;
    private View view2131297188;
    private View view2131297189;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public CleanGroupsActivity_ViewBinding(CleanGroupsActivity cleanGroupsActivity) {
        this(cleanGroupsActivity, cleanGroupsActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.CleanGroupsActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v12, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanGroupsActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v20, types: [com.wx.assistants.activity.CleanGroupsActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v28, types: [com.wx.assistants.activity.CleanGroupsActivity_ViewBinding$4, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v42, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanGroupsActivity_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r0v50, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanGroupsActivity_ViewBinding$6] */
    /* JADX WARNING: type inference failed for: r0v58, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanGroupsActivity_ViewBinding$7] */
    /* JADX WARNING: type inference failed for: r0v66, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanGroupsActivity_ViewBinding$8] */
    /* JADX WARNING: type inference failed for: r0v74, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanGroupsActivity_ViewBinding$9] */
    /* JADX WARNING: type inference failed for: r0v79, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanGroupsActivity_ViewBinding$10] */
    @UiThread
    public CleanGroupsActivity_ViewBinding(final CleanGroupsActivity cleanGroupsActivity, View view) {
        this.target = cleanGroupsActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        cleanGroupsActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanGroupsActivity.onViewClicked(view);
            }
        });
        cleanGroupsActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        cleanGroupsActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanGroupsActivity.onViewClicked(view);
            }
        });
        cleanGroupsActivity.jumpLabel = (TextView) Utils.findRequiredViewAsType(view, 2131296873, "field 'jumpLabel'", TextView.class);
        View findRequiredView3 = Utils.findRequiredView(view, 2131296877, "field 'jumpLabelLayout' and method 'onViewClicked'");
        cleanGroupsActivity.jumpLabelLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131296877, "field 'jumpLabelLayout'", LinearLayout.class);
        this.view2131296877 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanGroupsActivity.onViewClicked(view);
            }
        });
        cleanGroupsActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        cleanGroupsActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanGroupsActivity.onViewClicked(view);
            }
        });
        cleanGroupsActivity.radioGroupPlus = (RadioGroupPlus) Utils.findRequiredViewAsType(view, 2131297184, "field 'radioGroupPlus'", RadioGroupPlus.class);
        cleanGroupsActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        cleanGroupsActivity.radioBtn01 = (RadioButton) Utils.findRequiredViewAsType(view, 2131297177, "field 'radioBtn01'", RadioButton.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131297185, "field 'radioLayout1' and method 'onViewClicked'");
        cleanGroupsActivity.radioLayout1 = (LinearLayout) Utils.castView(findRequiredView5, 2131297185, "field 'radioLayout1'", LinearLayout.class);
        this.view2131297185 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanGroupsActivity.onViewClicked(view);
            }
        });
        cleanGroupsActivity.radioBtn02 = (RadioButton) Utils.findRequiredViewAsType(view, 2131297178, "field 'radioBtn02'", RadioButton.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131297186, "field 'radioLayout2' and method 'onViewClicked'");
        cleanGroupsActivity.radioLayout2 = (LinearLayout) Utils.castView(findRequiredView6, 2131297186, "field 'radioLayout2'", LinearLayout.class);
        this.view2131297186 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanGroupsActivity.onViewClicked(view);
            }
        });
        cleanGroupsActivity.radioBtn03 = (RadioButton) Utils.findRequiredViewAsType(view, 2131297179, "field 'radioBtn03'", RadioButton.class);
        View findRequiredView7 = Utils.findRequiredView(view, 2131297187, "field 'radioLayout3' and method 'onViewClicked'");
        cleanGroupsActivity.radioLayout3 = (LinearLayout) Utils.castView(findRequiredView7, 2131297187, "field 'radioLayout3'", LinearLayout.class);
        this.view2131297187 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanGroupsActivity.onViewClicked(view);
            }
        });
        cleanGroupsActivity.radioBtn04 = (RadioButton) Utils.findRequiredViewAsType(view, 2131297180, "field 'radioBtn04'", RadioButton.class);
        View findRequiredView8 = Utils.findRequiredView(view, 2131297188, "field 'radioLayout4' and method 'onViewClicked'");
        cleanGroupsActivity.radioLayout4 = (LinearLayout) Utils.castView(findRequiredView8, 2131297188, "field 'radioLayout4'", LinearLayout.class);
        this.view2131297188 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanGroupsActivity.onViewClicked(view);
            }
        });
        cleanGroupsActivity.radioBtn05 = (RadioButton) Utils.findRequiredViewAsType(view, 2131297181, "field 'radioBtn05'", RadioButton.class);
        View findRequiredView9 = Utils.findRequiredView(view, 2131297189, "field 'radioLayout5' and method 'onViewClicked'");
        cleanGroupsActivity.radioLayout5 = (LinearLayout) Utils.castView(findRequiredView9, 2131297189, "field 'radioLayout5'", LinearLayout.class);
        this.view2131297189 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanGroupsActivity.onViewClicked(view);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        cleanGroupsActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView10, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanGroupsActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        CleanGroupsActivity cleanGroupsActivity = this.target;
        if (cleanGroupsActivity != null) {
            this.target = null;
            cleanGroupsActivity.navBack = null;
            cleanGroupsActivity.navTitle = null;
            cleanGroupsActivity.startWx = null;
            cleanGroupsActivity.jumpLabel = null;
            cleanGroupsActivity.jumpLabelLayout = null;
            cleanGroupsActivity.navRightText = null;
            cleanGroupsActivity.navRightLayout = null;
            cleanGroupsActivity.radioGroupPlus = null;
            cleanGroupsActivity.navRightImg = null;
            cleanGroupsActivity.radioBtn01 = null;
            cleanGroupsActivity.radioLayout1 = null;
            cleanGroupsActivity.radioBtn02 = null;
            cleanGroupsActivity.radioLayout2 = null;
            cleanGroupsActivity.radioBtn03 = null;
            cleanGroupsActivity.radioLayout3 = null;
            cleanGroupsActivity.radioBtn04 = null;
            cleanGroupsActivity.radioLayout4 = null;
            cleanGroupsActivity.radioBtn05 = null;
            cleanGroupsActivity.radioLayout5 = null;
            cleanGroupsActivity.videoIntroduceLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131296877.setOnClickListener((View.OnClickListener) null);
            this.view2131296877 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            this.view2131297185.setOnClickListener((View.OnClickListener) null);
            this.view2131297185 = null;
            this.view2131297186.setOnClickListener((View.OnClickListener) null);
            this.view2131297186 = null;
            this.view2131297187.setOnClickListener((View.OnClickListener) null);
            this.view2131297187 = null;
            this.view2131297188.setOnClickListener((View.OnClickListener) null);
            this.view2131297188 = null;
            this.view2131297189.setOnClickListener((View.OnClickListener) null);
            this.view2131297189 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
