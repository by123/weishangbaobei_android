package com.meiqia.meiqiasdk.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meiqia.core.MQMessageManager;
import com.meiqia.core.bean.MQAgent;
import com.meiqia.core.bean.MQMessage;
import com.meiqia.meiqiasdk.model.Agent;
import com.meiqia.meiqiasdk.model.BaseMessage;
import com.meiqia.meiqiasdk.util.MQUtils;

public abstract class MessageReceiver extends BroadcastReceiver {
    private String mConversationId;

    public abstract void addDirectAgentMessageTip(String str);

    public abstract void blackAdd();

    public abstract void blackDel();

    public abstract void changeTitleToInputting();

    public abstract void inviteEvaluation();

    public abstract void queueingInitConv();

    public abstract void recallMessage(long j, String str);

    public abstract void receiveNewMsg(BaseMessage baseMessage);

    public abstract void removeQueue();

    public abstract void setCurrentAgent(Agent agent);

    public abstract void setNewConversationId(String str);

    public abstract void socketOpen();

    public abstract void updateAgentOnlineOfflineStatus();

    public void setConversationId(String str) {
        this.mConversationId = str;
    }

    public void onReceive(Context context, Intent intent) {
        BaseMessage parseMQMessageToBaseMessage;
        String action = intent.getAction();
        MQMessageManager instance = MQMessageManager.getInstance(context);
        if ("new_msg_received_action".equals(action)) {
            MQMessage mQMessage = instance.getMQMessage(intent.getStringExtra("msgId"));
            if (mQMessage != null && (parseMQMessageToBaseMessage = MQUtils.parseMQMessageToBaseMessage(mQMessage)) != null) {
                receiveNewMsg(parseMQMessageToBaseMessage);
            }
        } else if (MQMessageManager.ACTION_RECALL_MESSAGE.equals(action)) {
            recallMessage(intent.getLongExtra("id", -1), intent.getStringExtra("nickname"));
        } else if ("agent_send_card".equals(action)) {
            receiveNewMsg(MQUtils.parseMQMessageToClueCardMessage(instance.getAgentClueCardMessage(intent.getStringExtra("clueCardMessageId"))));
        } else if ("agent_inputting_action".equals(action)) {
            changeTitleToInputting();
        } else if ("agent_change_action".equals(action)) {
            MQAgent currentAgent = instance.getCurrentAgent();
            if (intent.getBooleanExtra("client_is_redirected", false)) {
                addDirectAgentMessageTip(currentAgent.getNickname());
            }
            setCurrentAgent(MQUtils.parseMQAgentToAgent(currentAgent));
            String stringExtra = intent.getStringExtra("conversation_id");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.mConversationId = stringExtra;
                setNewConversationId(stringExtra);
            }
        } else if ("invite_evaluation".equals(action)) {
            if (intent.getStringExtra("conversation_id").equals(this.mConversationId)) {
                inviteEvaluation();
            }
        } else if ("action_agent_status_update_event".equals(action)) {
            updateAgentOnlineOfflineStatus();
        } else if ("action_black_add".equals(action)) {
            blackAdd();
        } else if ("action_black_del".equals(action)) {
            blackDel();
        } else if (TextUtils.equals("action_queueing_remove", action)) {
            removeQueue();
        } else if (TextUtils.equals("action_queueing_init_conv", action)) {
            queueingInitConv();
        } else if (TextUtils.equals(MQMessageManager.ACTION_SOCKET_OPEN, action)) {
            socketOpen();
        }
    }
}
