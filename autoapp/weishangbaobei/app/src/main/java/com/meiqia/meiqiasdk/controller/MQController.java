package com.meiqia.meiqiasdk.controller;

import com.meiqia.core.bean.MQEnterpriseConfig;
import com.meiqia.core.callback.OnClientPositionInQueueCallback;
import com.meiqia.meiqiasdk.callback.OnClientOnlineCallback;
import com.meiqia.meiqiasdk.callback.OnDownloadFileCallback;
import com.meiqia.meiqiasdk.callback.OnEvaluateRobotAnswerCallback;
import com.meiqia.meiqiasdk.callback.OnGetMessageListCallBack;
import com.meiqia.meiqiasdk.callback.OnMessageSendCallback;
import com.meiqia.meiqiasdk.callback.SimpleCallback;
import com.meiqia.meiqiasdk.model.Agent;
import com.meiqia.meiqiasdk.model.BaseMessage;
import java.util.List;
import java.util.Map;

public interface MQController {
    public static final String ACTION_AGENT_INPUTTING = "agent_inputting_action";
    public static final String ACTION_AGENT_SEND_CLUE_CARD = "agent_send_card";
    public static final String ACTION_AGENT_STATUS_UPDATE_EVENT = "action_agent_status_update_event";
    public static final String ACTION_BLACK_ADD = "action_black_add";
    public static final String ACTION_BLACK_DEL = "action_black_del";
    public static final String ACTION_CLIENT_IS_REDIRECTED_EVENT = "agent_change_action";
    public static final String ACTION_INVITE_EVALUATION = "invite_evaluation";
    public static final String ACTION_NEW_MESSAGE_RECEIVED = "new_msg_received_action";
    public static final String ACTION_QUEUEING_INIT_CONV = "action_queueing_init_conv";
    public static final String ACTION_QUEUEING_REMOVE = "action_queueing_remove";

    void cancelDownload(String str);

    void closeService();

    void downloadFile(BaseMessage baseMessage, OnDownloadFileCallback onDownloadFileCallback);

    void evaluateRobotAnswer(long j, long j2, int i, OnEvaluateRobotAnswerCallback onEvaluateRobotAnswerCallback);

    void executeEvaluate(String str, int i, String str2, SimpleCallback simpleCallback);

    void getClientPositionInQueue(OnClientPositionInQueueCallback onClientPositionInQueueCallback);

    Agent getCurrentAgent();

    String getCurrentClientId();

    MQEnterpriseConfig getEnterpriseConfig();

    boolean getIsWaitingInQueue();

    void getMessageFromService(long j, int i, OnGetMessageListCallBack onGetMessageListCallBack);

    void getMessagesFromDatabase(long j, int i, OnGetMessageListCallBack onGetMessageListCallBack);

    void onConversationClose();

    void onConversationOpen();

    void openService();

    void refreshEnterpriseConfig(SimpleCallback simpleCallback);

    void resendMessage(BaseMessage baseMessage, OnMessageSendCallback onMessageSendCallback);

    void saveConversationLastMessageTime(long j);

    void saveConversationOnStopTime(long j);

    void sendClientInputtingWithContent(String str);

    void sendMessage(BaseMessage baseMessage, OnMessageSendCallback onMessageSendCallback);

    void setClientInfo(Map<String, String> map, SimpleCallback simpleCallback);

    void setCurrentClientOnline(String str, String str2, OnClientOnlineCallback onClientOnlineCallback);

    void setForceRedirectHuman(boolean z);

    void submitMessageForm(String str, List<String> list, Map<String, String> map, SimpleCallback simpleCallback);

    void updateClientInfo(Map<String, String> map, SimpleCallback simpleCallback);

    void updateMessage(long j, boolean z);
}
