package com.umeng.socialize.sina.message;

import android.content.Context;
import android.os.Bundle;

public class SendMessageToWeiboResponse extends BaseResponse {
    public SendMessageToWeiboResponse() {
    }

    public SendMessageToWeiboResponse(Bundle bundle) {
        fromBundle(bundle);
    }

    /* access modifiers changed from: package-private */
    public final boolean check(Context context) {
        return true;
    }

    public int getType() {
        return 1;
    }
}
