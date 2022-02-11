package com.luck.picture.lib.tools;

import android.content.Context;
import android.widget.Toast;
import com.stub.StubApp;

public final class ToastManage {
    public static void s(Context context, String str) {
        Toast.makeText(StubApp.getOrigApplicationContext(context.getApplicationContext()), str, 1).show();
    }
}
