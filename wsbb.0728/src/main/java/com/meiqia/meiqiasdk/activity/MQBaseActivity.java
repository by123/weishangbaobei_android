package com.meiqia.meiqiasdk.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.meiqia.meiqiasdk.util.MQUtils;

public abstract class MQBaseActivity extends Activity {
    private ImageView mBackIv;
    private RelativeLayout mBackRl;
    private TextView mBackTv;
    private RelativeLayout mTitleRl;
    private TextView mTitleTv;

    private void applyCustomUIConfig() {
        if (-1 != MQConfig.ui.backArrowIconResId) {
            this.mBackIv.setImageResource(MQConfig.ui.backArrowIconResId);
        }
        MQUtils.applyCustomUITintDrawable(this.mTitleRl, 17170443, R.color.mq_activity_title_bg, MQConfig.ui.titleBackgroundResId);
        MQUtils.applyCustomUITextAndImageColor(R.color.mq_activity_title_textColor, MQConfig.ui.titleTextColorResId, this.mBackIv, this.mBackTv, this.mTitleTv);
        MQUtils.applyCustomUITitleGravity(this.mBackTv, this.mTitleTv);
    }

    /* access modifiers changed from: protected */
    public abstract int getLayoutRes();

    /* access modifiers changed from: protected */
    public abstract void initView(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutRes());
        this.mTitleRl = (RelativeLayout) findViewById(R.id.title_rl);
        this.mBackRl = (RelativeLayout) findViewById(R.id.back_rl);
        this.mBackTv = (TextView) findViewById(R.id.back_tv);
        this.mBackIv = (ImageView) findViewById(R.id.back_iv);
        this.mTitleTv = (TextView) findViewById(R.id.title_tv);
        applyCustomUIConfig();
        this.mBackRl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MQBaseActivity.this.onBackPressed();
            }
        });
        initView(bundle);
        setListener();
        processLogic(bundle);
    }

    /* access modifiers changed from: protected */
    public abstract void processLogic(Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract void setListener();

    /* access modifiers changed from: protected */
    public void setTitle(String str) {
        this.mTitleTv.setText(str);
    }
}
