package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wx.assistants.view.TagCloudLayout;

public class ObtainGroupAllActivity_ViewBinding implements Unbinder {
    private ObtainGroupAllActivity target;
    private View view2131296529;
    private View view2131296728;
    private View view2131297049;

    @UiThread
    public ObtainGroupAllActivity_ViewBinding(ObtainGroupAllActivity obtainGroupAllActivity) {
        this(obtainGroupAllActivity, obtainGroupAllActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.ObtainGroupAllActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v12, types: [com.wx.assistants.activity.ObtainGroupAllActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v17, types: [com.wx.assistants.activity.ObtainGroupAllActivity_ViewBinding$3, android.view.View$OnClickListener] */
    @UiThread
    public ObtainGroupAllActivity_ViewBinding(final ObtainGroupAllActivity obtainGroupAllActivity, View view) {
        this.target = obtainGroupAllActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        obtainGroupAllActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                obtainGroupAllActivity.onViewClicked(view);
            }
        });
        obtainGroupAllActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131296728, "field 'getLable' and method 'onViewClicked'");
        obtainGroupAllActivity.getLable = (Button) Utils.castView(findRequiredView2, 2131296728, "field 'getLable'", Button.class);
        this.view2131296728 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                obtainGroupAllActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131296529, "field 'confirmSelect' and method 'onViewClicked'");
        obtainGroupAllActivity.confirmSelect = (Button) Utils.castView(findRequiredView3, 2131296529, "field 'confirmSelect'", Button.class);
        this.view2131296529 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                obtainGroupAllActivity.onViewClicked(view);
            }
        });
        obtainGroupAllActivity.radioBtnAll = (CheckBox) Utils.findRequiredViewAsType(view, 2131297182, "field 'radioBtnAll'", CheckBox.class);
        obtainGroupAllActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        obtainGroupAllActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        obtainGroupAllActivity.navRightLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        obtainGroupAllActivity.shadowLinearLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", LinearLayout.class);
        obtainGroupAllActivity.flowViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        obtainGroupAllActivity.selectWXLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297322, "field 'selectWXLayout'", LinearLayout.class);
        obtainGroupAllActivity.labelList = (ListView) Utils.findRequiredViewAsType(view, 2131296886, "field 'labelList'", ListView.class);
    }

    @CallSuper
    public void unbind() {
        ObtainGroupAllActivity obtainGroupAllActivity = this.target;
        if (obtainGroupAllActivity != null) {
            this.target = null;
            obtainGroupAllActivity.navBack = null;
            obtainGroupAllActivity.navTitle = null;
            obtainGroupAllActivity.getLable = null;
            obtainGroupAllActivity.confirmSelect = null;
            obtainGroupAllActivity.radioBtnAll = null;
            obtainGroupAllActivity.navRightText = null;
            obtainGroupAllActivity.navRightImg = null;
            obtainGroupAllActivity.navRightLayout = null;
            obtainGroupAllActivity.shadowLinearLayout = null;
            obtainGroupAllActivity.flowViewGroup = null;
            obtainGroupAllActivity.selectWXLayout = null;
            obtainGroupAllActivity.labelList = null;
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
