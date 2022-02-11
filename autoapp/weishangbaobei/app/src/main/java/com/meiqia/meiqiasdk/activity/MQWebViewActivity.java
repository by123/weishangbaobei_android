package com.meiqia.meiqiasdk.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.callback.OnEvaluateRobotAnswerCallback;
import com.meiqia.meiqiasdk.model.RobotMessage;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.stub.StubApp;

public class MQWebViewActivity extends Activity implements View.OnClickListener {
    public static final String CONTENT = "content";
    public static RobotMessage sRobotMessage;
    private TextView mAlreadyFeedbackTv;
    private ImageView mBackIv;
    private RelativeLayout mBackRl;
    private TextView mBackTv;
    private RelativeLayout mEvaluateRl;
    private RelativeLayout mTitleRl;
    private TextView mTitleTv;
    private TextView mUsefulTv;
    private TextView mUselessTv;
    private WebView mWebView;

    static {
        StubApp.interface11(6122);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    private void findViews() {
        this.mTitleRl = (RelativeLayout) findViewById(R.id.title_rl);
        this.mBackRl = (RelativeLayout) findViewById(R.id.back_rl);
        this.mBackTv = (TextView) findViewById(R.id.back_tv);
        this.mBackIv = (ImageView) findViewById(R.id.back_iv);
        this.mTitleTv = (TextView) findViewById(R.id.title_tv);
        this.mWebView = (WebView) findViewById(R.id.webview);
        this.mEvaluateRl = (RelativeLayout) findViewById(R.id.ll_robot_evaluate);
        this.mUsefulTv = (TextView) findViewById(R.id.tv_robot_useful);
        this.mUselessTv = (TextView) findViewById(R.id.tv_robot_useless);
        this.mAlreadyFeedbackTv = (TextView) findViewById(R.id.tv_robot_already_feedback);
    }

    private void setListeners() {
        this.mBackRl.setOnClickListener(this);
        this.mUsefulTv.setOnClickListener(this);
        this.mUselessTv.setOnClickListener(this);
        this.mAlreadyFeedbackTv.setOnClickListener(this);
    }

    private void applyCustomUIConfig() {
        if (-1 != MQConfig.ui.backArrowIconResId) {
            this.mBackIv.setImageResource(MQConfig.ui.backArrowIconResId);
        }
        MQUtils.applyCustomUITintDrawable(this.mTitleRl, 17170443, R.color.mq_activity_title_bg, MQConfig.ui.titleBackgroundResId);
        MQUtils.applyCustomUITextAndImageColor(R.color.mq_activity_title_textColor, MQConfig.ui.titleTextColorResId, this.mBackIv, this.mBackTv, this.mTitleTv);
        MQUtils.applyCustomUITitleGravity(this.mBackTv, this.mTitleTv);
    }

    private void logic() {
        if (getIntent() != null) {
            handleRobotRichTextMessage();
            this.mWebView.loadDataWithBaseURL((String) null, getIntent().getStringExtra("content"), "text/html", "utf-8", (String) null);
        }
    }

    /* access modifiers changed from: private */
    public void handleRobotRichTextMessage() {
        if (sRobotMessage == null) {
            return;
        }
        if (TextUtils.equals("evaluate", sRobotMessage.getSubType()) || "rich_text".equals(sRobotMessage.getContentType())) {
            this.mEvaluateRl.setVisibility(0);
            if (sRobotMessage.isAlreadyFeedback()) {
                this.mUselessTv.setVisibility(8);
                this.mUsefulTv.setVisibility(8);
                this.mAlreadyFeedbackTv.setVisibility(0);
                return;
            }
            this.mUselessTv.setVisibility(0);
            this.mUsefulTv.setVisibility(0);
            this.mAlreadyFeedbackTv.setVisibility(8);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back_rl) {
            onBackPressed();
        } else if (id == R.id.tv_robot_useful) {
            evaluate(1);
        } else if (id == R.id.tv_robot_useless) {
            evaluate(0);
        } else if (id == R.id.tv_robot_already_feedback) {
            this.mEvaluateRl.setVisibility(8);
        }
    }

    private void evaluate(int i) {
        MQConfig.getController(this).evaluateRobotAnswer(sRobotMessage.getId(), sRobotMessage.getQuestionId(), i, new OnEvaluateRobotAnswerCallback() {
            public void onFailure(int i, String str) {
                MQUtils.show((Context) MQWebViewActivity.this, R.string.mq_evaluate_failure);
            }

            public void onSuccess(String str) {
                MQWebViewActivity.sRobotMessage.setAlreadyFeedback(true);
                MQWebViewActivity.this.handleRobotRichTextMessage();
            }
        });
    }
}
