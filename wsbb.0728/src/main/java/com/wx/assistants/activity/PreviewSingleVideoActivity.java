package com.wx.assistants.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.stub.StubApp;
import com.wx.assistants.base.BaseActivity;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class PreviewSingleVideoActivity extends BaseActivity {
    @BindView(2131296867)
    JCVideoPlayer jcVideoPlayer;
    private LinearLayout navBack;
    private TextView navTitle;
    private String videoUrl = "";

    static {
        StubApp.interface11(9361);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }
}
