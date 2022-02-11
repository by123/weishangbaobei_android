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

public class SuggestServerActivity_ViewBinding implements Unbinder {
    private SuggestServerActivity target;
    private View view2131296726;
    private View view2131297043;
    private View view2131297049;

    @UiThread
    public SuggestServerActivity_ViewBinding(SuggestServerActivity suggestServerActivity) {
        this(suggestServerActivity, suggestServerActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.SuggestServerActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v9, types: [android.view.View$OnClickListener, com.wx.assistants.activity.SuggestServerActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v14, types: [com.wx.assistants.activity.SuggestServerActivity_ViewBinding$3, android.view.View$OnClickListener] */
    @UiThread
    public SuggestServerActivity_ViewBinding(final SuggestServerActivity suggestServerActivity, View view) {
        this.target = suggestServerActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131296726, "field 'getActivation' and method 'onViewClicked'");
        suggestServerActivity.getActivation = (Button) Utils.castView(findRequiredView, 2131296726, "field 'getActivation'", Button.class);
        this.view2131296726 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                suggestServerActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297043, "field 'must' and method 'onViewClicked'");
        suggestServerActivity.must = (Button) Utils.castView(findRequiredView2, 2131297043, "field 'must'", Button.class);
        this.view2131297043 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                suggestServerActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        suggestServerActivity.navBack = (LinearLayout) Utils.castView(findRequiredView3, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                suggestServerActivity.onViewClicked(view);
            }
        });
        suggestServerActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        suggestServerActivity.logoImg = (ImageView) Utils.findRequiredViewAsType(view, 2131296981, "field 'logoImg'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        SuggestServerActivity suggestServerActivity = this.target;
        if (suggestServerActivity != null) {
            this.target = null;
            suggestServerActivity.getActivation = null;
            suggestServerActivity.must = null;
            suggestServerActivity.navBack = null;
            suggestServerActivity.navTitle = null;
            suggestServerActivity.logoImg = null;
            this.view2131296726.setOnClickListener((View.OnClickListener) null);
            this.view2131296726 = null;
            this.view2131297043.setOnClickListener((View.OnClickListener) null);
            this.view2131297043 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
