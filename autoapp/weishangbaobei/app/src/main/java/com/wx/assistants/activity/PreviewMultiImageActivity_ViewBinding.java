package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class PreviewMultiImageActivity_ViewBinding implements Unbinder {
    private PreviewMultiImageActivity target;
    private View view2131297049;

    @UiThread
    public PreviewMultiImageActivity_ViewBinding(PreviewMultiImageActivity previewMultiImageActivity) {
        this(previewMultiImageActivity, previewMultiImageActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [com.wx.assistants.activity.PreviewMultiImageActivity_ViewBinding$1, android.view.View$OnClickListener] */
    @UiThread
    public PreviewMultiImageActivity_ViewBinding(final PreviewMultiImageActivity previewMultiImageActivity, View view) {
        this.target = previewMultiImageActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        previewMultiImageActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                previewMultiImageActivity.onViewClicked();
            }
        });
        previewMultiImageActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        PreviewMultiImageActivity previewMultiImageActivity = this.target;
        if (previewMultiImageActivity != null) {
            this.target = null;
            previewMultiImageActivity.navBack = null;
            previewMultiImageActivity.navTitle = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
