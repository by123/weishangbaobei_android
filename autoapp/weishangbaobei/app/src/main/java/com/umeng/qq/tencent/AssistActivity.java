package com.umeng.qq.tencent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.stub.StubApp;
import org.json.JSONObject;

public class AssistActivity extends Activity {
    public static final String EXTRA_INTENT = "openSDK_LOG.AssistActivity.ExtraIntent";
    protected static final int FINISH_BY_TIMEOUT = 0;
    private static final String RESTART_FLAG = "RESTART_FLAG";
    private static final String RESUME_FLAG = "RESUME_FLAG";
    private static final String TAG = "openSDK_LOG.AssistActivity";
    protected Handler handler = new a(this);
    private boolean isRestart = false;
    private String mAppId;
    protected boolean mOnResumeIsInited = false;

    static {
        StubApp.interface11(7771);
    }

    public static Intent getAssistActivityIntent(Context context) {
        return new Intent(context, AssistActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 0) {
            if (intent != null) {
                intent.putExtra("key_action", "action_login");
            }
            setResultData(i, intent);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        intent.putExtra("key_action", "action_share");
        setResult(-1, intent);
        if (!isFinishing()) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.handler.removeMessages(0);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (!intent.getBooleanExtra("is_login", false)) {
            if (!intent.getBooleanExtra("is_qq_mobile_share", false) && this.isRestart && !isFinishing()) {
                finish();
            }
            if (this.mOnResumeIsInited) {
                this.handler.sendMessage(this.handler.obtainMessage(0));
                return;
            }
            this.mOnResumeIsInited = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean(RESTART_FLAG, true);
        bundle.putBoolean(RESUME_FLAG, this.mOnResumeIsInited);
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }

    public void setResultData(int i, Intent intent) {
        if (intent == null) {
            setResult(0);
            return;
        }
        try {
            String stringExtra = intent.getStringExtra("key_response");
            if (!TextUtils.isEmpty(stringExtra)) {
                JSONObject jSONObject = new JSONObject(stringExtra);
                String optString = jSONObject.optString("openid");
                String optString2 = jSONObject.optString("access_token");
                if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                    setResult(0, intent);
                    return;
                }
            }
            setResult(-1, intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
