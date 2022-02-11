package com.wx.assistants.activity;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.meiqia.core.MQManager;
import com.meiqia.core.callback.OnInitCallback;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.meiqia.meiqiasdk.util.MQIntentBuilder;
import com.stub.StubApp;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.glide.GlideCircleTransform;
import com.wx.assistants.webview.WebViewActivity;

public class SuggestServerActivity extends BaseActivity {
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;
    @BindView(2131296726)
    Button getActivation;
    @BindView(2131296981)
    ImageView logoImg;
    @BindView(2131297043)
    Button must;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;

    static {
        StubApp.interface11(9382);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    @TargetApi(23)
    public native void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr);

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.SuggestServerActivity] */
    public void initView() {
        this.navTitle.setText("功能建议与客服");
        Glide.with(MyApplication.getConText()).load(2131558404).transform(new BitmapTransformation[]{new GlideCircleTransform(this)}).into(this.logoImg);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.SuggestServerActivity] */
    private void initMeiqiaSDK() {
        MQManager.setDebugMode(true);
        MQConfig.init(this, MyApplication.MeiQiaKey, new OnInitCallback() {
            public void onFailure(int i, String str) {
            }

            public void onSuccess(String str) {
            }
        });
        customMeiqiaSDK();
    }

    private void customMeiqiaSDK() {
        MQConfig.ui.titleGravity = MQConfig.ui.MQTitleGravity.LEFT;
        MQConfig.ui.backArrowIconResId = 2131558407;
    }

    public void conversation(View view) {
        conversationWrapper();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.SuggestServerActivity] */
    @OnClick({2131297049, 2131296726, 2131297043})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131296726) {
            startActivity(ActivationActivity.class, true, true);
        } else if (id == 2131297043) {
            WebViewActivity.startActivity(this, "使用帮助", QBangApis.FAQ_URL, false);
        } else if (id == 2131297049) {
            back();
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.SuggestServerActivity, android.app.Activity] */
    private void conversationWrapper() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        } else {
            conversation();
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.SuggestServerActivity] */
    private void conversation() {
        startActivity(new MQIntentBuilder(this).build());
    }
}
