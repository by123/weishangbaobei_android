package com.meiqia.meiqiasdk.controller;

import android.content.Context;
import android.text.TextUtils;
import com.meiqia.core.MQManager;
import com.meiqia.core.bean.MQAgent;
import com.meiqia.core.bean.MQEnterpriseConfig;
import com.meiqia.core.bean.MQMessage;
import com.meiqia.core.callback.OnClientInfoCallback;
import com.meiqia.core.callback.OnClientPositionInQueueCallback;
import com.meiqia.core.callback.OnGetMessageListCallback;
import com.meiqia.core.callback.OnProgressCallback;
import com.meiqia.meiqiasdk.callback.OnClientOnlineCallback;
import com.meiqia.meiqiasdk.callback.OnDownloadFileCallback;
import com.meiqia.meiqiasdk.callback.OnEvaluateRobotAnswerCallback;
import com.meiqia.meiqiasdk.callback.OnGetMessageListCallBack;
import com.meiqia.meiqiasdk.callback.OnMessageSendCallback;
import com.meiqia.meiqiasdk.callback.SimpleCallback;
import com.meiqia.meiqiasdk.model.Agent;
import com.meiqia.meiqiasdk.model.BaseMessage;
import com.meiqia.meiqiasdk.model.PhotoMessage;
import com.meiqia.meiqiasdk.model.VoiceMessage;
import com.meiqia.meiqiasdk.util.MQUtils;
import java.io.File;
import java.util.List;
import java.util.Map;

public class ControllerImpl implements MQController {
    public Context context;

    public ControllerImpl(Context context2) {
        this.context = context2;
    }

    public void cancelDownload(String str) {
        MQManager.getInstance(this.context).cancelDownload(str);
    }

    public void closeService() {
        MQManager.getInstance(this.context).closeMeiqiaService();
    }

    public void downloadFile(BaseMessage baseMessage, final OnDownloadFileCallback onDownloadFileCallback) {
        MQManager.getInstance(this.context).downloadFile(MQUtils.parseBaseMessageToMQMessage(baseMessage), new OnProgressCallback() {
            public void onFailure(int i, String str) {
                if (onDownloadFileCallback != null) {
                    onDownloadFileCallback.onFailure(i, str);
                }
            }

            public void onProgress(int i) {
                if (onDownloadFileCallback != null) {
                    onDownloadFileCallback.onProgress(i);
                }
            }

            public void onSuccess() {
                if (onDownloadFileCallback != null) {
                    onDownloadFileCallback.onSuccess((File) null);
                }
            }
        });
    }

    public void evaluateRobotAnswer(long j, long j2, int i, final OnEvaluateRobotAnswerCallback onEvaluateRobotAnswerCallback) {
        MQManager.getInstance(this.context).evaluateRobotAnswer(j, j2, i, new com.meiqia.core.callback.OnEvaluateRobotAnswerCallback() {
            public void onFailure(int i, String str) {
                if (onEvaluateRobotAnswerCallback != null) {
                    onEvaluateRobotAnswerCallback.onFailure(i, str);
                }
            }

            public void onSuccess(String str) {
                if (onEvaluateRobotAnswerCallback != null) {
                    onEvaluateRobotAnswerCallback.onSuccess(str);
                }
            }
        });
    }

    public void executeEvaluate(String str, int i, String str2, final SimpleCallback simpleCallback) {
        MQManager.getInstance(this.context).executeEvaluate(str, i, str2, new com.meiqia.core.callback.SimpleCallback() {
            public void onFailure(int i, String str) {
                if (simpleCallback != null) {
                    simpleCallback.onFailure(i, str);
                }
            }

            public void onSuccess() {
                if (simpleCallback != null) {
                    simpleCallback.onSuccess();
                }
            }
        });
    }

    public void getClientPositionInQueue(final OnClientPositionInQueueCallback onClientPositionInQueueCallback) {
        MQManager.getInstance(this.context).getClientPositionInQueue(new OnClientPositionInQueueCallback() {
            public void onFailure(int i, String str) {
                if (onClientPositionInQueueCallback != null) {
                    onClientPositionInQueueCallback.onFailure(i, str);
                }
            }

            public void onSuccess(int i) {
                if (onClientPositionInQueueCallback != null) {
                    onClientPositionInQueueCallback.onSuccess(i);
                }
            }
        });
    }

    public Agent getCurrentAgent() {
        return MQUtils.parseMQAgentToAgent(MQManager.getInstance(this.context).getCurrentAgent());
    }

    public String getCurrentClientId() {
        return MQManager.getInstance(this.context).getCurrentClientId();
    }

    public MQEnterpriseConfig getEnterpriseConfig() {
        return MQManager.getInstance(this.context).getEnterpriseConfig();
    }

    public boolean getIsWaitingInQueue() {
        return MQManager.getInstance(this.context).getIsWaitingInQueue();
    }

    public void getMessageFromService(long j, int i, final OnGetMessageListCallBack onGetMessageListCallBack) {
        MQManager.getInstance(this.context).getMQMessageFromService(j, i, new OnGetMessageListCallback() {
            public void onFailure(int i, String str) {
                if (onGetMessageListCallBack != null) {
                    onGetMessageListCallBack.onFailure(i, str);
                }
            }

            public void onSuccess(List<MQMessage> list) {
                List<BaseMessage> parseMQMessageToChatBaseList = MQUtils.parseMQMessageToChatBaseList(list);
                if (onGetMessageListCallBack != null) {
                    onGetMessageListCallBack.onSuccess(parseMQMessageToChatBaseList);
                }
            }
        });
    }

    public void getMessagesFromDatabase(long j, int i, final OnGetMessageListCallBack onGetMessageListCallBack) {
        MQManager.getInstance(this.context).getMQMessageFromDatabase(j, i, new OnGetMessageListCallback() {
            public void onFailure(int i, String str) {
                if (onGetMessageListCallBack != null) {
                    onGetMessageListCallBack.onFailure(i, str);
                }
            }

            public void onSuccess(List<MQMessage> list) {
                List<BaseMessage> parseMQMessageToChatBaseList = MQUtils.parseMQMessageToChatBaseList(list);
                if (onGetMessageListCallBack != null) {
                    onGetMessageListCallBack.onSuccess(parseMQMessageToChatBaseList);
                }
            }
        });
    }

    public void onConversationClose() {
        MQManager.getInstance(this.context).onConversationClose();
    }

    public void onConversationOpen() {
        MQManager.getInstance(this.context).onConversationOpen();
    }

    public void openService() {
        MQManager.getInstance(this.context).openMeiqiaService();
    }

    public void refreshEnterpriseConfig(final SimpleCallback simpleCallback) {
        MQManager.getInstance(this.context).refreshEnterpriseConfig(new com.meiqia.core.callback.SimpleCallback() {
            public void onFailure(int i, String str) {
                if (simpleCallback != null) {
                    simpleCallback.onFailure(i, str);
                }
            }

            public void onSuccess() {
                if (simpleCallback != null) {
                    simpleCallback.onSuccess();
                }
            }
        });
    }

    public void resendMessage(BaseMessage baseMessage, final OnMessageSendCallback onMessageSendCallback) {
        final long id = baseMessage.getId();
        sendMessage(baseMessage, new OnMessageSendCallback() {
            public void onFailure(BaseMessage baseMessage, int i, String str) {
                if (onMessageSendCallback != null) {
                    onMessageSendCallback.onFailure(baseMessage, i, str);
                }
                MQManager.getInstance(ControllerImpl.this.context).deleteMessage(id);
            }

            public void onSuccess(BaseMessage baseMessage, int i) {
                if (onMessageSendCallback != null) {
                    onMessageSendCallback.onSuccess(baseMessage, i);
                }
                MQManager.getInstance(ControllerImpl.this.context).deleteMessage(id);
            }
        });
    }

    public void saveConversationLastMessageTime(long j) {
        MQManager.getInstance(this.context).saveConversationLastMessageTime(j);
    }

    public void saveConversationOnStopTime(long j) {
        MQManager.getInstance(this.context).saveConversationOnStopTime(j);
    }

    public void sendClientInputtingWithContent(String str) {
        MQManager.getInstance(this.context).sendClientInputtingWithContent(str);
    }

    public void sendMessage(final BaseMessage baseMessage, final OnMessageSendCallback onMessageSendCallback) {
        AnonymousClass1 r0 = new com.meiqia.core.callback.OnMessageSendCallback() {
            public void onFailure(MQMessage mQMessage, int i, String str) {
                MQUtils.parseMQMessageIntoBaseMessage(mQMessage, baseMessage);
                if (onMessageSendCallback != null) {
                    onMessageSendCallback.onFailure(baseMessage, i, str);
                }
            }

            public void onSuccess(MQMessage mQMessage, int i) {
                MQUtils.parseMQMessageIntoBaseMessage(mQMessage, baseMessage);
                if (onMessageSendCallback != null) {
                    onMessageSendCallback.onSuccess(baseMessage, i);
                }
            }
        };
        if ("text".equals(baseMessage.getContentType())) {
            MQManager.getInstance(this.context).sendMQTextMessage(baseMessage.getContent(), r0);
        } else if ("photo".equals(baseMessage.getContentType())) {
            MQManager.getInstance(this.context).sendMQPhotoMessage(((PhotoMessage) baseMessage).getLocalPath(), r0);
        } else if ("audio".equals(baseMessage.getContentType())) {
            MQManager.getInstance(this.context).sendMQVoiceMessage(((VoiceMessage) baseMessage).getLocalPath(), r0);
        }
    }

    public void setClientInfo(Map<String, String> map, final SimpleCallback simpleCallback) {
        MQManager.getInstance(this.context).setClientInfo(map, new OnClientInfoCallback() {
            public void onFailure(int i, String str) {
                if (simpleCallback != null) {
                    simpleCallback.onFailure(i, str);
                }
            }

            public void onSuccess() {
                if (simpleCallback != null) {
                    simpleCallback.onSuccess();
                }
            }
        });
    }

    public void setCurrentClientOnline(String str, String str2, final OnClientOnlineCallback onClientOnlineCallback) {
        AnonymousClass5 r0 = new com.meiqia.core.callback.OnClientOnlineCallback() {
            public void onFailure(int i, String str) {
                if (onClientOnlineCallback != null) {
                    onClientOnlineCallback.onFailure(i, str);
                }
            }

            public void onSuccess(MQAgent mQAgent, String str, List<MQMessage> list) {
                Agent parseMQAgentToAgent = MQUtils.parseMQAgentToAgent(mQAgent);
                List<BaseMessage> parseMQMessageToChatBaseList = MQUtils.parseMQMessageToChatBaseList(list);
                if (onClientOnlineCallback != null) {
                    onClientOnlineCallback.onSuccess(parseMQAgentToAgent, str, parseMQMessageToChatBaseList);
                }
            }
        };
        if (!TextUtils.isEmpty(str)) {
            MQManager.getInstance(this.context).setClientOnlineWithClientId(str, r0);
        } else if (!TextUtils.isEmpty(str2)) {
            MQManager.getInstance(this.context).setClientOnlineWithCustomizedId(str2, r0);
        } else {
            MQManager.getInstance(this.context).setCurrentClientOnline(r0);
        }
    }

    public void setForceRedirectHuman(boolean z) {
        MQManager.getInstance(this.context).setForceRedirectHuman(z);
    }

    public void submitMessageForm(String str, List<String> list, Map<String, String> map, final SimpleCallback simpleCallback) {
        MQManager.getInstance(this.context).submitMessageForm(str, list, map, new com.meiqia.core.callback.SimpleCallback() {
            public void onFailure(int i, String str) {
                if (simpleCallback != null) {
                    simpleCallback.onFailure(i, str);
                }
            }

            public void onSuccess() {
                if (simpleCallback != null) {
                    simpleCallback.onSuccess();
                }
            }
        });
    }

    public void updateClientInfo(Map<String, String> map, final SimpleCallback simpleCallback) {
        MQManager.getInstance(this.context).updateClientInfo(map, new OnClientInfoCallback() {
            public void onFailure(int i, String str) {
                if (simpleCallback != null) {
                    simpleCallback.onFailure(i, str);
                }
            }

            public void onSuccess() {
                if (simpleCallback != null) {
                    simpleCallback.onSuccess();
                }
            }
        });
    }

    public void updateMessage(long j, boolean z) {
        MQManager.getInstance(this.context).updateMessage(j, z);
    }
}
