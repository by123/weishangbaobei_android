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
import com.wx.assistants.view.FriendsCopyTagSetLayout;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class OneKeyCopyWXHActivity_ViewBinding implements Unbinder {
    private OneKeyCopyWXHActivity target;
    private View view2131296877;
    private View view2131297049;
    private View view2131297052;
    private View view2131297422;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public OneKeyCopyWXHActivity_ViewBinding(OneKeyCopyWXHActivity oneKeyCopyWXHActivity) {
        this(oneKeyCopyWXHActivity, oneKeyCopyWXHActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [com.wx.assistants.activity.OneKeyCopyWXHActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v8, types: [com.wx.assistants.activity.OneKeyCopyWXHActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v12, types: [com.wx.assistants.activity.OneKeyCopyWXHActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v18, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyCopyWXHActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r1v24, types: [com.wx.assistants.activity.OneKeyCopyWXHActivity_ViewBinding$5, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v28, types: [com.wx.assistants.activity.OneKeyCopyWXHActivity_ViewBinding$6, android.view.View$OnClickListener] */
    @UiThread
    public OneKeyCopyWXHActivity_ViewBinding(final OneKeyCopyWXHActivity oneKeyCopyWXHActivity, View view) {
        this.target = oneKeyCopyWXHActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        oneKeyCopyWXHActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyCopyWXHActivity.onViewClicked(view);
            }
        });
        oneKeyCopyWXHActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        oneKeyCopyWXHActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyCopyWXHActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        oneKeyCopyWXHActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyCopyWXHActivity.onViewClicked(view);
            }
        });
        oneKeyCopyWXHActivity.descText = (TextView) Utils.findRequiredViewAsType(view, 2131296585, "field 'descText'", TextView.class);
        oneKeyCopyWXHActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        oneKeyCopyWXHActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyCopyWXHActivity.onViewClicked(view);
            }
        });
        oneKeyCopyWXHActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        oneKeyCopyWXHActivity.startPull = (TextView) Utils.findRequiredViewAsType(view, 2131297419, "field 'startPull'", TextView.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131297422, "field 'startPullLayout' and method 'onViewClicked'");
        oneKeyCopyWXHActivity.startPullLayout = (LinearLayout) Utils.castView(findRequiredView5, 2131297422, "field 'startPullLayout'", LinearLayout.class);
        this.view2131297422 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyCopyWXHActivity.onViewClicked(view);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, 2131296877, "field 'jumpLabelLayout' and method 'onViewClicked'");
        oneKeyCopyWXHActivity.jumpLabelLayout = (LinearLayout) Utils.castView(findRequiredView6, 2131296877, "field 'jumpLabelLayout'", LinearLayout.class);
        this.view2131296877 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyCopyWXHActivity.onViewClicked(view);
            }
        });
        oneKeyCopyWXHActivity.jumpLabel = (TextView) Utils.findRequiredViewAsType(view, 2131296873, "field 'jumpLabel'", TextView.class);
        oneKeyCopyWXHActivity.friendsCopyTagSetLayout = (FriendsCopyTagSetLayout) Utils.findRequiredViewAsType(view, 2131296720, "field 'friendsCopyTagSetLayout'", FriendsCopyTagSetLayout.class);
    }

    @CallSuper
    public void unbind() {
        OneKeyCopyWXHActivity oneKeyCopyWXHActivity = this.target;
        if (oneKeyCopyWXHActivity != null) {
            this.target = null;
            oneKeyCopyWXHActivity.navBack = null;
            oneKeyCopyWXHActivity.navTitle = null;
            oneKeyCopyWXHActivity.startWx = null;
            oneKeyCopyWXHActivity.videoIntroduceLayout = null;
            oneKeyCopyWXHActivity.descText = null;
            oneKeyCopyWXHActivity.navRightText = null;
            oneKeyCopyWXHActivity.navRightLayout = null;
            oneKeyCopyWXHActivity.shadowLinearLayout = null;
            oneKeyCopyWXHActivity.startPull = null;
            oneKeyCopyWXHActivity.startPullLayout = null;
            oneKeyCopyWXHActivity.jumpLabelLayout = null;
            oneKeyCopyWXHActivity.jumpLabel = null;
            oneKeyCopyWXHActivity.friendsCopyTagSetLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            this.view2131297422.setOnClickListener((View.OnClickListener) null);
            this.view2131297422 = null;
            this.view2131296877.setOnClickListener((View.OnClickListener) null);
            this.view2131296877 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
