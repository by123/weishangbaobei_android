package com.meiqia.meiqiasdk.callback;

import android.content.Intent;
import com.meiqia.meiqiasdk.activity.MQConversationActivity;

public interface OnLinkClickCallback {
    void onClick(MQConversationActivity mQConversationActivity, Intent intent, String str);
}
