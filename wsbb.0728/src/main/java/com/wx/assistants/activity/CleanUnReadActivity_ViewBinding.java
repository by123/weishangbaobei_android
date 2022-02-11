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

public class CleanUnReadActivity_ViewBinding implements Unbinder {
    private CleanUnReadActivity target;
    private View view2131297049;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public CleanUnReadActivity_ViewBinding(CleanUnReadActivity cleanUnReadActivity) {
        this(cleanUnReadActivity, cleanUnReadActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.CleanUnReadActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v12, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanUnReadActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v17, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanUnReadActivity_ViewBinding$3] */
    @UiThread
    public CleanUnReadActivity_ViewBinding(final CleanUnReadActivity cleanUnReadActivity, View view) {
        this.target = cleanUnReadActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        cleanUnReadActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanUnReadActivity.onViewClicked(view);
            }
        });
        cleanUnReadActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        cleanUnReadActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanUnReadActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        cleanUnReadActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanUnReadActivity.onViewClicked(view);
            }
        });
        cleanUnReadActivity.descText = (TextView) Utils.findRequiredViewAsType(view, 2131296585, "field 'descText'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        CleanUnReadActivity cleanUnReadActivity = this.target;
        if (cleanUnReadActivity != null) {
            this.target = null;
            cleanUnReadActivity.navBack = null;
            cleanUnReadActivity.navTitle = null;
            cleanUnReadActivity.startWx = null;
            cleanUnReadActivity.videoIntroduceLayout = null;
            cleanUnReadActivity.descText = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
