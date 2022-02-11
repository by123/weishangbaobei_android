package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class CleanVideoWaterMarkctivity_ViewBinding implements Unbinder {
    private CleanVideoWaterMarkctivity target;
    private View view2131296727;
    private View view2131297049;
    private View view2131297052;
    private View view2131297274;

    @UiThread
    public CleanVideoWaterMarkctivity_ViewBinding(CleanVideoWaterMarkctivity cleanVideoWaterMarkctivity) {
        this(cleanVideoWaterMarkctivity, cleanVideoWaterMarkctivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [com.wx.assistants.activity.CleanVideoWaterMarkctivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v10, types: [com.wx.assistants.activity.CleanVideoWaterMarkctivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v14, types: [com.wx.assistants.activity.CleanVideoWaterMarkctivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v28, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanVideoWaterMarkctivity_ViewBinding$4] */
    @UiThread
    public CleanVideoWaterMarkctivity_ViewBinding(final CleanVideoWaterMarkctivity cleanVideoWaterMarkctivity, View view) {
        this.target = cleanVideoWaterMarkctivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        cleanVideoWaterMarkctivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanVideoWaterMarkctivity.onViewClicked(view);
            }
        });
        cleanVideoWaterMarkctivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        cleanVideoWaterMarkctivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        cleanVideoWaterMarkctivity.editLeavingMessage = (EditText) Utils.findRequiredViewAsType(view, 2131296616, "field 'editLeavingMessage'", EditText.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131296727, "field 'getBtn' and method 'onViewClicked'");
        cleanVideoWaterMarkctivity.getBtn = (Button) Utils.castView(findRequiredView2, 2131296727, "field 'getBtn'", Button.class);
        this.view2131296727 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanVideoWaterMarkctivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297274, "field 'saveVideoBtn' and method 'onViewClicked'");
        cleanVideoWaterMarkctivity.saveVideoBtn = (Button) Utils.castView(findRequiredView3, 2131297274, "field 'saveVideoBtn'", Button.class);
        this.view2131297274 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanVideoWaterMarkctivity.onViewClicked(view);
            }
        });
        cleanVideoWaterMarkctivity.jcVideoPlayer = (JCVideoPlayer) Utils.findRequiredViewAsType(view, 2131296867, "field 'jcVideoPlayer'", JCVideoPlayer.class);
        cleanVideoWaterMarkctivity.showVideo = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297377, "field 'showVideo'", LinearLayout.class);
        cleanVideoWaterMarkctivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        cleanVideoWaterMarkctivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanVideoWaterMarkctivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        CleanVideoWaterMarkctivity cleanVideoWaterMarkctivity = this.target;
        if (cleanVideoWaterMarkctivity != null) {
            this.target = null;
            cleanVideoWaterMarkctivity.navBack = null;
            cleanVideoWaterMarkctivity.navTitle = null;
            cleanVideoWaterMarkctivity.shadowLinearLayout = null;
            cleanVideoWaterMarkctivity.editLeavingMessage = null;
            cleanVideoWaterMarkctivity.getBtn = null;
            cleanVideoWaterMarkctivity.saveVideoBtn = null;
            cleanVideoWaterMarkctivity.jcVideoPlayer = null;
            cleanVideoWaterMarkctivity.showVideo = null;
            cleanVideoWaterMarkctivity.navRightText = null;
            cleanVideoWaterMarkctivity.navRightLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131296727.setOnClickListener((View.OnClickListener) null);
            this.view2131296727 = null;
            this.view2131297274.setOnClickListener((View.OnClickListener) null);
            this.view2131297274 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
