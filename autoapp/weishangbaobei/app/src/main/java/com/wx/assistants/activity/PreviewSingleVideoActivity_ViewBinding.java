package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class PreviewSingleVideoActivity_ViewBinding implements Unbinder {
    private PreviewSingleVideoActivity target;

    @UiThread
    public PreviewSingleVideoActivity_ViewBinding(PreviewSingleVideoActivity previewSingleVideoActivity) {
        this(previewSingleVideoActivity, previewSingleVideoActivity.getWindow().getDecorView());
    }

    @UiThread
    public PreviewSingleVideoActivity_ViewBinding(PreviewSingleVideoActivity previewSingleVideoActivity, View view) {
        this.target = previewSingleVideoActivity;
        previewSingleVideoActivity.jcVideoPlayer = (JCVideoPlayer) Utils.findRequiredViewAsType(view, 2131296867, "field 'jcVideoPlayer'", JCVideoPlayer.class);
    }

    @CallSuper
    public void unbind() {
        PreviewSingleVideoActivity previewSingleVideoActivity = this.target;
        if (previewSingleVideoActivity != null) {
            this.target = null;
            previewSingleVideoActivity.jcVideoPlayer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
