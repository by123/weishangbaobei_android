package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class VoiceForwardActivity_ViewBinding implements Unbinder {
    private VoiceForwardActivity target;
    private View view2131296731;
    private View view2131297049;
    private View view2131297636;

    @UiThread
    public VoiceForwardActivity_ViewBinding(VoiceForwardActivity voiceForwardActivity) {
        this(voiceForwardActivity, voiceForwardActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View$OnClickListener, com.wx.assistants.activity.VoiceForwardActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v8, types: [android.view.View$OnClickListener, com.wx.assistants.activity.VoiceForwardActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v11, types: [com.wx.assistants.activity.VoiceForwardActivity_ViewBinding$3, android.view.View$OnClickListener] */
    @UiThread
    public VoiceForwardActivity_ViewBinding(final VoiceForwardActivity voiceForwardActivity, View view) {
        this.target = voiceForwardActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        voiceForwardActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                voiceForwardActivity.onViewClicked(view);
            }
        });
        voiceForwardActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        voiceForwardActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView2, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                voiceForwardActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131296731, "field 'graphicExplanationLayout' and method 'onViewClicked'");
        voiceForwardActivity.graphicExplanationLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131296731, "field 'graphicExplanationLayout'", LinearLayout.class);
        this.view2131296731 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                voiceForwardActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        VoiceForwardActivity voiceForwardActivity = this.target;
        if (voiceForwardActivity != null) {
            this.target = null;
            voiceForwardActivity.navBack = null;
            voiceForwardActivity.navTitle = null;
            voiceForwardActivity.videoIntroduceLayout = null;
            voiceForwardActivity.graphicExplanationLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131296731.setOnClickListener((View.OnClickListener) null);
            this.view2131296731 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
