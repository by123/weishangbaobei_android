package com.wx.assistants.wxapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wx.assistants.application.MyApplication;

public class AppRegister extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        WXAPIFactory.createWXAPI(context, (String) null).registerApp(MyApplication.WX_APP_ID);
    }
}
