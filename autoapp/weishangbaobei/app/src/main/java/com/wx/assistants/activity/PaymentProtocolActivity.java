package com.wx.assistants.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.base.BaseActivity;

public class PaymentProtocolActivity extends BaseActivity {
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;

    static {
        StubApp.interface11(9348);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public void initData() {
        this.navTitle.setText("微商宝贝用户付费协议");
    }

    @OnClick({2131297049})
    public void onViewClicked(View view) {
        if (view.getId() == 2131297049) {
            back();
        }
    }
}
