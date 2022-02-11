package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.NumSettingLayout;
import com.wx.assistants.view.SingleLabelSelectLayout;

public class CleanZombieActivity_ViewBinding implements Unbinder {
    private CleanZombieActivity target;
    private View view2131296871;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public CleanZombieActivity_ViewBinding(CleanZombieActivity cleanZombieActivity) {
        this(cleanZombieActivity, cleanZombieActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanZombieActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v8, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanZombieActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v12, types: [com.wx.assistants.activity.CleanZombieActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v21, types: [com.wx.assistants.activity.CleanZombieActivity_ViewBinding$4, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v33, types: [com.wx.assistants.activity.CleanZombieActivity_ViewBinding$5, android.view.View$OnClickListener] */
    @UiThread
    public CleanZombieActivity_ViewBinding(final CleanZombieActivity cleanZombieActivity, View view) {
        this.target = cleanZombieActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        cleanZombieActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanZombieActivity.onViewClicked(view);
            }
        });
        cleanZombieActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        cleanZombieActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanZombieActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        cleanZombieActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanZombieActivity.onViewClicked(view);
            }
        });
        cleanZombieActivity.jumpLabel = (TextView) Utils.findRequiredViewAsType(view, 2131296872, "field 'jumpLabel'", TextView.class);
        cleanZombieActivity.numberSettingLayout = (NumSettingLayout) Utils.findRequiredViewAsType(view, 2131297082, "field 'numberSettingLayout'", NumSettingLayout.class);
        cleanZombieActivity.deleteSwitchBtn = (Switch) Utils.findRequiredViewAsType(view, 2131296581, "field 'deleteSwitchBtn'", Switch.class);
        cleanZombieActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        cleanZombieActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        cleanZombieActivity.singleSelectLabelLayout = (SingleLabelSelectLayout) Utils.findRequiredViewAsType(view, 2131297386, "field 'singleSelectLabelLayout'", SingleLabelSelectLayout.class);
        cleanZombieActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131296871, "method 'onViewClicked'");
        this.view2131296871 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanZombieActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297052, "method 'onViewClicked'");
        this.view2131297052 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanZombieActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        CleanZombieActivity cleanZombieActivity = this.target;
        if (cleanZombieActivity != null) {
            this.target = null;
            cleanZombieActivity.navBack = null;
            cleanZombieActivity.navTitle = null;
            cleanZombieActivity.startWx = null;
            cleanZombieActivity.videoIntroduceLayout = null;
            cleanZombieActivity.jumpLabel = null;
            cleanZombieActivity.numberSettingLayout = null;
            cleanZombieActivity.deleteSwitchBtn = null;
            cleanZombieActivity.navRightText = null;
            cleanZombieActivity.navRightImg = null;
            cleanZombieActivity.singleSelectLabelLayout = null;
            cleanZombieActivity.executeTimeSpaceLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131296871.setOnClickListener((View.OnClickListener) null);
            this.view2131296871 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
