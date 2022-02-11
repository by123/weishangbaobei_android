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
import com.wx.assistants.view.RemarkFriendLayout;
import com.wx.assistants.view.SingleLabelSelectLayout;

public class AutoPassValidationActivity_ViewBinding implements Unbinder {
    private AutoPassValidationActivity target;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public AutoPassValidationActivity_ViewBinding(AutoPassValidationActivity autoPassValidationActivity) {
        this(autoPassValidationActivity, autoPassValidationActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.AutoPassValidationActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v12, types: [com.wx.assistants.activity.AutoPassValidationActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v17, types: [com.wx.assistants.activity.AutoPassValidationActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v8, types: [com.wx.assistants.activity.AutoPassValidationActivity_ViewBinding$4, android.view.View$OnClickListener] */
    @UiThread
    public AutoPassValidationActivity_ViewBinding(final AutoPassValidationActivity autoPassValidationActivity, View view) {
        this.target = autoPassValidationActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        autoPassValidationActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoPassValidationActivity.onViewClicked(view);
            }
        });
        autoPassValidationActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        autoPassValidationActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoPassValidationActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        autoPassValidationActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoPassValidationActivity.onViewClicked(view);
            }
        });
        autoPassValidationActivity.executeRemarkLayout = (RemarkFriendLayout) Utils.findRequiredViewAsType(view, 2131296646, "field 'executeRemarkLayout'", RemarkFriendLayout.class);
        autoPassValidationActivity.singleSelectLabelLayout = (SingleLabelSelectLayout) Utils.findRequiredViewAsType(view, 2131297386, "field 'singleSelectLabelLayout'", SingleLabelSelectLayout.class);
        autoPassValidationActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "method 'onViewClicked'");
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoPassValidationActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        AutoPassValidationActivity autoPassValidationActivity = this.target;
        if (autoPassValidationActivity != null) {
            this.target = null;
            autoPassValidationActivity.navBack = null;
            autoPassValidationActivity.navTitle = null;
            autoPassValidationActivity.startWx = null;
            autoPassValidationActivity.videoIntroduceLayout = null;
            autoPassValidationActivity.executeRemarkLayout = null;
            autoPassValidationActivity.singleSelectLabelLayout = null;
            autoPassValidationActivity.navRightImg = null;
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
