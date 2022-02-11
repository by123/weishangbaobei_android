package com.meiqia.meiqiasdk.callback;

import android.os.Bundle;
import com.meiqia.meiqiasdk.activity.MQConversationActivity;

public interface MQActivityLifecycleCallback {
    void onActivityCreated(MQConversationActivity mQConversationActivity, Bundle bundle);

    void onActivityDestroyed(MQConversationActivity mQConversationActivity);

    void onActivityPaused(MQConversationActivity mQConversationActivity);

    void onActivityResumed(MQConversationActivity mQConversationActivity);

    void onActivitySaveInstanceState(MQConversationActivity mQConversationActivity, Bundle bundle);

    void onActivityStarted(MQConversationActivity mQConversationActivity);

    void onActivityStopped(MQConversationActivity mQConversationActivity);
}
