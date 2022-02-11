package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.sevenheaven.segmentcontrol.SegmentControl;

public class MyIndentActivity_ViewBinding implements Unbinder {
    private MyIndentActivity target;
    private View view2131296353;

    @UiThread
    public MyIndentActivity_ViewBinding(MyIndentActivity myIndentActivity) {
        this(myIndentActivity, myIndentActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [com.wx.assistants.activity.MyIndentActivity_ViewBinding$1, android.view.View$OnClickListener] */
    @UiThread
    public MyIndentActivity_ViewBinding(final MyIndentActivity myIndentActivity, View view) {
        this.target = myIndentActivity;
        myIndentActivity.myFragment = (FrameLayout) Utils.findRequiredViewAsType(view, 2131297045, "field 'myFragment'", FrameLayout.class);
        View findRequiredView = Utils.findRequiredView(view, 2131296353, "field 'arrowBack' and method 'onViewClicked'");
        myIndentActivity.arrowBack = (LinearLayout) Utils.castView(findRequiredView, 2131296353, "field 'arrowBack'", LinearLayout.class);
        this.view2131296353 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myIndentActivity.onViewClicked(view);
            }
        });
        myIndentActivity.segmentControl = (SegmentControl) Utils.findRequiredViewAsType(view, 2131297306, "field 'segmentControl'", SegmentControl.class);
    }

    @CallSuper
    public void unbind() {
        MyIndentActivity myIndentActivity = this.target;
        if (myIndentActivity != null) {
            this.target = null;
            myIndentActivity.myFragment = null;
            myIndentActivity.arrowBack = null;
            myIndentActivity.segmentControl = null;
            this.view2131296353.setOnClickListener((View.OnClickListener) null);
            this.view2131296353 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
