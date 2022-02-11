package com.wx.assistants.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.base.BaseActivity;

public class PublicTextActivity extends BaseActivity {
    private static String navStr;
    private static String publicContentStr;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297171)
    TextView publicText;

    static {
        StubApp.interface11(9363);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public static void start(Activity activity, String str, String str2) {
        navStr = str;
        publicContentStr = str2;
        activity.startActivity(new Intent(activity, PublicTextActivity.class));
    }

    private void initView() {
        this.navTitle.setText(navStr);
        this.publicText.setText(publicContentStr);
    }

    @OnClick({2131297049})
    public void onViewClicked(View view) {
        if (view.getId() == 2131297049) {
            back();
        }
    }
}
