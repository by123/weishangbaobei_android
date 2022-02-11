package com.wx.assistants.wxapi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.stub.StubApp;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.wx.assistants.bean.PassiveCardEvent;
import com.wx.assistants.bean.UpdateMemberEvent;
import org.greenrobot.eventbus.EventBus;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    private IWXAPI api;

    static {
        StubApp.interface11(12452);
    }

    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.api.handleIntent(intent, this);
    }

    public void onReq(BaseReq baseReq) {
    }

    @SuppressLint({"LongLogTag"})
    public void onResp(BaseResp baseResp) {
        Log.d(TAG, "onPayFinish, errCode = " + baseResp.errCode);
        Log.d("onPayFinish", "onPayFinish, errCode = " + baseResp.errCode);
        if (baseResp.errCode == 0) {
            Toast.makeText(this, "支付成功!", 0).show();
            EventBus.getDefault().post(new PassiveCardEvent());
            EventBus.getDefault().post(new UpdateMemberEvent(0));
            finish();
        } else if (baseResp.errCode == -1) {
            Toast.makeText(this, "支付失败!", 0).show();
            finish();
        } else if (baseResp.errCode == -2) {
            Toast.makeText(this, "取消支付!", 0).show();
            finish();
        }
    }
}
