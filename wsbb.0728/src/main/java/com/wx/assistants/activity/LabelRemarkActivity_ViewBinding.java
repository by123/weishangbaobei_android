package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.LabelAutoBatchLayout;
import com.wx.assistants.view.LabelRemarkSelectLayout;

public class LabelRemarkActivity_ViewBinding implements Unbinder {
    private LabelRemarkActivity target;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public LabelRemarkActivity_ViewBinding(LabelRemarkActivity labelRemarkActivity) {
        this(labelRemarkActivity, labelRemarkActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v10, types: [com.wx.assistants.activity.LabelRemarkActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v18, types: [com.wx.assistants.activity.LabelRemarkActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v23, types: [com.wx.assistants.activity.LabelRemarkActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v28, types: [com.wx.assistants.activity.LabelRemarkActivity_ViewBinding$4, android.view.View$OnClickListener] */
    @UiThread
    public LabelRemarkActivity_ViewBinding(final LabelRemarkActivity labelRemarkActivity, View view) {
        this.target = labelRemarkActivity;
        labelRemarkActivity.remarkName = (EditText) Utils.findRequiredViewAsType(view, 2131297231, "field 'remarkName'", EditText.class);
        labelRemarkActivity.labelName = (EditText) Utils.findRequiredViewAsType(view, 2131296885, "field 'labelName'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        labelRemarkActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                labelRemarkActivity.onViewClicked(view);
            }
        });
        labelRemarkActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        labelRemarkActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                labelRemarkActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        labelRemarkActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                labelRemarkActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        labelRemarkActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                labelRemarkActivity.onViewClicked(view);
            }
        });
        labelRemarkActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        labelRemarkActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        labelRemarkActivity.friendSendModeLayout = (LabelRemarkSelectLayout) Utils.findRequiredViewAsType(view, 2131296717, "field 'friendSendModeLayout'", LabelRemarkSelectLayout.class);
        labelRemarkActivity.labelLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296884, "field 'labelLayout'", LinearLayout.class);
        labelRemarkActivity.labelAutoBatchLayout = (LabelAutoBatchLayout) Utils.findRequiredViewAsType(view, 2131296883, "field 'labelAutoBatchLayout'", LabelAutoBatchLayout.class);
        labelRemarkActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        labelRemarkActivity.showImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297379, "field 'showImg'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        LabelRemarkActivity labelRemarkActivity = this.target;
        if (labelRemarkActivity != null) {
            this.target = null;
            labelRemarkActivity.remarkName = null;
            labelRemarkActivity.labelName = null;
            labelRemarkActivity.navBack = null;
            labelRemarkActivity.navTitle = null;
            labelRemarkActivity.startWx = null;
            labelRemarkActivity.videoIntroduceLayout = null;
            labelRemarkActivity.navRightLayout = null;
            labelRemarkActivity.navRightText = null;
            labelRemarkActivity.executeTimeSpaceLayout = null;
            labelRemarkActivity.friendSendModeLayout = null;
            labelRemarkActivity.labelLayout = null;
            labelRemarkActivity.labelAutoBatchLayout = null;
            labelRemarkActivity.navRightImg = null;
            labelRemarkActivity.showImg = null;
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
