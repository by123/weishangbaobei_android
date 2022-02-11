package com.sina.weibo.sdk.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import com.stub.StubApp;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;

public class WbShareTransActivity extends Activity {
    boolean flag = false;
    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt("_weibo_resp_errcode", 1);
            intent.putExtras(bundle);
            intent.setFlags(WXMediaMessage.MINI_PROGRAM__THUMB_LENGHT);
            intent.setClassName(WbShareTransActivity.this, WbShareTransActivity.this.startActivityName);
            WbShareTransActivity.this.startActivity(intent);
            WbShareTransActivity.this.finish();
        }
    };
    /* access modifiers changed from: private */
    public String startActivityName;

    static {
        StubApp.interface11(6639);
    }

    private void sendSuccess() {
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.handler.sendEmptyMessageDelayed(0, 100);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getIntExtra("startFlag", -1) != 0) {
            this.handler.removeMessages(0);
            Bundle extras = intent.getExtras();
            Intent intent2 = new Intent();
            intent2.putExtras(extras);
            intent2.setFlags(WXMediaMessage.MINI_PROGRAM__THUMB_LENGHT);
            intent2.setClassName(this, this.startActivityName);
            startActivity(intent2);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.remove("startFlag");
        bundle.putBoolean("resultDataFlag", true);
        bundle.putString("startActivity", this.startActivityName);
    }
}
