package com.meiqia.core;

import com.meiqia.core.bean.MQAgent;
import com.meiqia.core.bean.MQMessage;
import com.meiqia.core.callback.OnClientBindingCallback;
import com.meiqia.core.callback.OnClientInfoCallback;
import com.meiqia.core.callback.OnClientOnlineCallback;
import com.meiqia.core.callback.OnClientPositionInQueueCallback;
import com.meiqia.core.callback.OnEndConversationCallback;
import com.meiqia.core.callback.OnEvaluateRobotAnswerCallback;
import com.meiqia.core.callback.OnFailureCallBack;
import com.meiqia.core.callback.OnGetClientCallback;
import com.meiqia.core.callback.OnGetMQClientIdCallBackOn;
import com.meiqia.core.callback.OnGetMessageListCallback;
import com.meiqia.core.callback.OnGetTrackIdCallback;
import com.meiqia.core.callback.OnInitCallback;
import com.meiqia.core.callback.OnMessageSendCallback;
import com.meiqia.core.callback.OnProgressCallback;
import com.meiqia.core.callback.OnRegisterDeviceTokenCallback;
import com.meiqia.core.callback.OnTicketCategoriesCallback;
import com.meiqia.core.callback.SimpleCallback;
import java.util.List;
import org.json.JSONArray;

class b implements OnClientBindingCallback, OnClientInfoCallback, OnClientOnlineCallback, OnClientPositionInQueueCallback, OnEndConversationCallback, OnEvaluateRobotAnswerCallback, OnFailureCallBack, OnGetClientCallback, OnGetMQClientIdCallBackOn, OnGetMessageListCallback, OnGetTrackIdCallback, OnInitCallback, OnMessageSendCallback, OnProgressCallback, OnRegisterDeviceTokenCallback, OnTicketCategoriesCallback, SimpleCallback {
    b() {
    }

    public void onFailure(int i, String str) {
    }

    public void onFailure(MQMessage mQMessage, int i, String str) {
    }

    public void onProgress(int i) {
    }

    public void onSuccess() {
    }

    public void onSuccess(int i) {
    }

    public void onSuccess(MQAgent mQAgent, String str, List<MQMessage> list) {
    }

    public void onSuccess(MQMessage mQMessage, int i) {
    }

    public void onSuccess(String str) {
    }

    public void onSuccess(List<MQMessage> list) {
    }

    public void onSuccess(JSONArray jSONArray) {
    }

    public void onSuccess(boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
    }
}
