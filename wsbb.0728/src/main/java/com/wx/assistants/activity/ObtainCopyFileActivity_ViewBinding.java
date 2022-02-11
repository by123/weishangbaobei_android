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

public class ObtainCopyFileActivity_ViewBinding implements Unbinder {
    private ObtainCopyFileActivity target;
    private View view2131296529;
    private View view2131297049;

    @UiThread
    public ObtainCopyFileActivity_ViewBinding(ObtainCopyFileActivity obtainCopyFileActivity) {
        this(obtainCopyFileActivity, obtainCopyFileActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.ObtainCopyFileActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v12, types: [android.view.View$OnClickListener, com.wx.assistants.activity.ObtainCopyFileActivity_ViewBinding$2] */
    @UiThread
    public ObtainCopyFileActivity_ViewBinding(final ObtainCopyFileActivity obtainCopyFileActivity, View view) {
        this.target = obtainCopyFileActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        obtainCopyFileActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                obtainCopyFileActivity.onViewClicked(view);
            }
        });
        obtainCopyFileActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131296529, "field 'confirmSelect' and method 'onViewClicked'");
        obtainCopyFileActivity.confirmSelect = (Button) Utils.castView(findRequiredView2, 2131296529, "field 'confirmSelect'", Button.class);
        this.view2131296529 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                obtainCopyFileActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        ObtainCopyFileActivity obtainCopyFileActivity = this.target;
        if (obtainCopyFileActivity != null) {
            this.target = null;
            obtainCopyFileActivity.navBack = null;
            obtainCopyFileActivity.navTitle = null;
            obtainCopyFileActivity.confirmSelect = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131296529.setOnClickListener((View.OnClickListener) null);
            this.view2131296529 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
