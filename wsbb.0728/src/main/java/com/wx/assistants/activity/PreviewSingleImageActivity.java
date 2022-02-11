package com.wx.assistants.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.meiqia.meiqiasdk.third.photoview.PhotoView;
import com.stub.StubApp;
import com.wx.assistants.base.BaseActivity;

public class PreviewSingleImageActivity extends BaseActivity {
    private String imgUrl = "";
    private PhotoView mIntensifyImageView;
    private LinearLayout navBack;
    private TextView navTitle;

    static {
        StubApp.interface11(9359);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);
}
