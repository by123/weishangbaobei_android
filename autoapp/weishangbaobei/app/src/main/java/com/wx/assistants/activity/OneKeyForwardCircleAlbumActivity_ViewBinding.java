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
import com.wx.assistants.view.CircleDeleteLayout;
import com.wx.assistants.view.CircleSelectSettingLayout;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class OneKeyForwardCircleAlbumActivity_ViewBinding implements Unbinder {
    private OneKeyForwardCircleAlbumActivity target;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public OneKeyForwardCircleAlbumActivity_ViewBinding(OneKeyForwardCircleAlbumActivity oneKeyForwardCircleAlbumActivity) {
        this(oneKeyForwardCircleAlbumActivity, oneKeyForwardCircleAlbumActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [com.wx.assistants.activity.OneKeyForwardCircleAlbumActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v9, types: [com.wx.assistants.activity.OneKeyForwardCircleAlbumActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v13, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyForwardCircleAlbumActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r1v19, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyForwardCircleAlbumActivity_ViewBinding$4] */
    @UiThread
    public OneKeyForwardCircleAlbumActivity_ViewBinding(final OneKeyForwardCircleAlbumActivity oneKeyForwardCircleAlbumActivity, View view) {
        this.target = oneKeyForwardCircleAlbumActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        oneKeyForwardCircleAlbumActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardCircleAlbumActivity.onViewClicked(view);
            }
        });
        oneKeyForwardCircleAlbumActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        oneKeyForwardCircleAlbumActivity.switchButton = (Switch) Utils.findRequiredViewAsType(view, 2131297447, "field 'switchButton'", Switch.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        oneKeyForwardCircleAlbumActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardCircleAlbumActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        oneKeyForwardCircleAlbumActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardCircleAlbumActivity.onViewClicked(view);
            }
        });
        oneKeyForwardCircleAlbumActivity.executeRemarkLayout = (CircleDeleteLayout) Utils.findRequiredViewAsType(view, 2131296646, "field 'executeRemarkLayout'", CircleDeleteLayout.class);
        oneKeyForwardCircleAlbumActivity.circleSelectSettingLayout = (CircleSelectSettingLayout) Utils.findRequiredViewAsType(view, 2131296490, "field 'circleSelectSettingLayout'", CircleSelectSettingLayout.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        oneKeyForwardCircleAlbumActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardCircleAlbumActivity.onViewClicked(view);
            }
        });
        oneKeyForwardCircleAlbumActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        oneKeyForwardCircleAlbumActivity.switchSpeedButton = (Switch) Utils.findRequiredViewAsType(view, 2131297449, "field 'switchSpeedButton'", Switch.class);
        oneKeyForwardCircleAlbumActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        oneKeyForwardCircleAlbumActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        oneKeyForwardCircleAlbumActivity.switchBackStartButton = (Switch) Utils.findRequiredViewAsType(view, 2131297445, "field 'switchBackStartButton'", Switch.class);
        oneKeyForwardCircleAlbumActivity.backStartLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296368, "field 'backStartLayout'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        OneKeyForwardCircleAlbumActivity oneKeyForwardCircleAlbumActivity = this.target;
        if (oneKeyForwardCircleAlbumActivity != null) {
            this.target = null;
            oneKeyForwardCircleAlbumActivity.navBack = null;
            oneKeyForwardCircleAlbumActivity.navTitle = null;
            oneKeyForwardCircleAlbumActivity.switchButton = null;
            oneKeyForwardCircleAlbumActivity.startWx = null;
            oneKeyForwardCircleAlbumActivity.videoIntroduceLayout = null;
            oneKeyForwardCircleAlbumActivity.executeRemarkLayout = null;
            oneKeyForwardCircleAlbumActivity.circleSelectSettingLayout = null;
            oneKeyForwardCircleAlbumActivity.navRightLayout = null;
            oneKeyForwardCircleAlbumActivity.navRightImg = null;
            oneKeyForwardCircleAlbumActivity.switchSpeedButton = null;
            oneKeyForwardCircleAlbumActivity.navRightText = null;
            oneKeyForwardCircleAlbumActivity.shadowLinearLayout = null;
            oneKeyForwardCircleAlbumActivity.switchBackStartButton = null;
            oneKeyForwardCircleAlbumActivity.backStartLayout = null;
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
