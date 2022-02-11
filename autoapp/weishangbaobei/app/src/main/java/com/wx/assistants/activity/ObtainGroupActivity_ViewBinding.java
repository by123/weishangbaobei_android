package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wx.assistants.view.TagCloudLayout;

public class ObtainGroupActivity_ViewBinding implements Unbinder {
    private ObtainGroupActivity target;
    private View view2131296529;
    private View view2131296728;
    private View view2131297049;

    @UiThread
    public ObtainGroupActivity_ViewBinding(ObtainGroupActivity obtainGroupActivity) {
        this(obtainGroupActivity, obtainGroupActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View$OnClickListener, com.wx.assistants.activity.ObtainGroupActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v8, types: [android.view.View$OnClickListener, com.wx.assistants.activity.ObtainGroupActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v12, types: [android.view.View$OnClickListener, com.wx.assistants.activity.ObtainGroupActivity_ViewBinding$3] */
    @UiThread
    public ObtainGroupActivity_ViewBinding(final ObtainGroupActivity obtainGroupActivity, View view) {
        this.target = obtainGroupActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        obtainGroupActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                obtainGroupActivity.onViewClicked(view);
            }
        });
        obtainGroupActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131296728, "field 'getLable' and method 'onViewClicked'");
        obtainGroupActivity.getLable = (Button) Utils.castView(findRequiredView2, 2131296728, "field 'getLable'", Button.class);
        this.view2131296728 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                obtainGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131296529, "field 'confirmSelect' and method 'onViewClicked'");
        obtainGroupActivity.confirmSelect = (Button) Utils.castView(findRequiredView3, 2131296529, "field 'confirmSelect'", Button.class);
        this.view2131296529 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                obtainGroupActivity.onViewClicked(view);
            }
        });
        obtainGroupActivity.radioBtnAll = (CheckBox) Utils.findRequiredViewAsType(view, 2131297182, "field 'radioBtnAll'", CheckBox.class);
        obtainGroupActivity.flowViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        obtainGroupActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        obtainGroupActivity.navRightLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        obtainGroupActivity.selectWXLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297322, "field 'selectWXLayout'", LinearLayout.class);
        obtainGroupActivity.labelList = (ListView) Utils.findRequiredViewAsType(view, 2131296886, "field 'labelList'", ListView.class);
    }

    @CallSuper
    public void unbind() {
        ObtainGroupActivity obtainGroupActivity = this.target;
        if (obtainGroupActivity != null) {
            this.target = null;
            obtainGroupActivity.navBack = null;
            obtainGroupActivity.navTitle = null;
            obtainGroupActivity.getLable = null;
            obtainGroupActivity.confirmSelect = null;
            obtainGroupActivity.radioBtnAll = null;
            obtainGroupActivity.flowViewGroup = null;
            obtainGroupActivity.navRightText = null;
            obtainGroupActivity.navRightLayout = null;
            obtainGroupActivity.selectWXLayout = null;
            obtainGroupActivity.labelList = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131296728.setOnClickListener((View.OnClickListener) null);
            this.view2131296728 = null;
            this.view2131296529.setOnClickListener((View.OnClickListener) null);
            this.view2131296529 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
