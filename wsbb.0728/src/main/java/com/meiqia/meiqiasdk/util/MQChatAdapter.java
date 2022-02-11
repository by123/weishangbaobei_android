package com.meiqia.meiqiasdk.util;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.activity.MQConversationActivity;
import com.meiqia.meiqiasdk.activity.MQPhotoPreviewActivity;
import com.meiqia.meiqiasdk.chatitem.MQAgentItem;
import com.meiqia.meiqiasdk.chatitem.MQBaseBubbleItem;
import com.meiqia.meiqiasdk.chatitem.MQClientItem;
import com.meiqia.meiqiasdk.chatitem.MQClueCardItem;
import com.meiqia.meiqiasdk.chatitem.MQEvaluateItem;
import com.meiqia.meiqiasdk.chatitem.MQHybridItem;
import com.meiqia.meiqiasdk.chatitem.MQInitiativeRedirectItem;
import com.meiqia.meiqiasdk.chatitem.MQNoAgentItem;
import com.meiqia.meiqiasdk.chatitem.MQRobotItem;
import com.meiqia.meiqiasdk.chatitem.MQTimeItem;
import com.meiqia.meiqiasdk.chatitem.MQTipItem;
import com.meiqia.meiqiasdk.model.BaseMessage;
import com.meiqia.meiqiasdk.model.ClueCardMessage;
import com.meiqia.meiqiasdk.model.EvaluateMessage;
import com.meiqia.meiqiasdk.model.FileMessage;
import com.meiqia.meiqiasdk.model.HybridMessage;
import com.meiqia.meiqiasdk.model.InitiativeRedirectMessage;
import com.meiqia.meiqiasdk.model.RedirectQueueMessage;
import com.meiqia.meiqiasdk.model.RobotMessage;
import com.meiqia.meiqiasdk.model.TipMessage;
import com.meiqia.meiqiasdk.model.VoiceMessage;
import com.meiqia.meiqiasdk.util.MQAudioPlayerManager;
import com.meiqia.meiqiasdk.util.MQDownloadManager;
import com.meiqia.meiqiasdk.widget.MQRedirectQueueItem;
import java.io.File;
import java.util.List;

public class MQChatAdapter extends BaseAdapter implements MQBaseBubbleItem.Callback {
    private static final int NO_POSITION = -1;
    private static final String TAG = "MQChatAdapter";
    private MQConversationActivity mConversationActivity;
    private int mCurrentDownloadingItemPosition = -1;
    /* access modifiers changed from: private */
    public int mCurrentPlayingItemPosition = -1;
    /* access modifiers changed from: private */
    public ListView mListView;
    private List<BaseMessage> mMessageList;
    /* access modifiers changed from: private */
    public Runnable mNotifyDataSetChangedRunnable = new Runnable() {
        public void run() {
            MQChatAdapter.this.notifyDataSetChanged();
        }
    };

    public MQChatAdapter(MQConversationActivity mQConversationActivity, List<BaseMessage> list, ListView listView) {
        this.mConversationActivity = mQConversationActivity;
        this.mMessageList = list;
        this.mListView = listView;
    }

    public void addMQMessage(BaseMessage baseMessage) {
        this.mMessageList.add(baseMessage);
        notifyDataSetChanged();
    }

    public void addMQMessage(BaseMessage baseMessage, int i) {
        this.mMessageList.add(i, baseMessage);
        notifyDataSetChanged();
    }

    public void downloadAndNotifyDataSetChanged(List<BaseMessage> list) {
        for (BaseMessage next : list) {
            if (next instanceof VoiceMessage) {
                final VoiceMessage voiceMessage = (VoiceMessage) next;
                File file = null;
                if (!TextUtils.isEmpty(voiceMessage.getLocalPath())) {
                    file = new File(voiceMessage.getLocalPath());
                }
                if (file == null || !file.exists()) {
                    file = MQAudioRecorderManager.getCachedVoiceFileByUrl(this.mConversationActivity, voiceMessage.getUrl());
                }
                if (file == null || !file.exists()) {
                    MQDownloadManager.getInstance(this.mConversationActivity).downloadVoice(voiceMessage.getUrl(), new MQDownloadManager.Callback() {
                        public void onFailure() {
                        }

                        public void onSuccess(File file) {
                            MQChatAdapter.this.setVoiceMessageDuration(voiceMessage, file.getAbsolutePath());
                            MQChatAdapter.this.mListView.post(MQChatAdapter.this.mNotifyDataSetChangedRunnable);
                        }
                    });
                } else {
                    setVoiceMessageDuration(voiceMessage, file.getAbsolutePath());
                    notifyDataSetChanged();
                }
            }
        }
    }

    public int getCount() {
        return this.mMessageList.size();
    }

    public int getCurrentDownloadingItemPosition() {
        return this.mCurrentDownloadingItemPosition;
    }

    public int getCurrentPlayingItemPosition() {
        return this.mCurrentPlayingItemPosition;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public int getItemViewType(int i) {
        return this.mMessageList.get(i).getItemViewType();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        MQClueCardItem mQClueCardItem;
        BaseMessage baseMessage = this.mMessageList.get(i);
        if (view == null) {
            switch (getItemViewType(i)) {
                case 0:
                    mQClueCardItem = new MQClientItem(this.mConversationActivity, this);
                    break;
                case 1:
                    mQClueCardItem = new MQAgentItem(this.mConversationActivity, this);
                    break;
                case 2:
                    mQClueCardItem = new MQTimeItem(this.mConversationActivity);
                    break;
                case 3:
                    mQClueCardItem = new MQTipItem(this.mConversationActivity);
                    break;
                case 4:
                    mQClueCardItem = new MQEvaluateItem(this.mConversationActivity);
                    break;
                case 5:
                    mQClueCardItem = new MQRobotItem(this.mConversationActivity, this.mConversationActivity);
                    break;
                case 6:
                    mQClueCardItem = new MQNoAgentItem(this.mConversationActivity);
                    break;
                case 7:
                    mQClueCardItem = new MQInitiativeRedirectItem(this.mConversationActivity);
                    break;
                case 8:
                    mQClueCardItem = new MQRedirectQueueItem(this.mConversationActivity, this.mConversationActivity);
                    break;
                case 9:
                    mQClueCardItem = new MQHybridItem(this.mConversationActivity, (MQRobotItem.Callback) null);
                    break;
                case 10:
                    mQClueCardItem = new MQHybridItem(this.mConversationActivity, this.mConversationActivity);
                    break;
                case 11:
                    mQClueCardItem = new MQClueCardItem(this.mConversationActivity, this);
                    break;
                default:
                    mQClueCardItem = view;
                    break;
            }
        } else {
            mQClueCardItem = view;
        }
        if (getItemViewType(i) == 1) {
            ((MQAgentItem) mQClueCardItem).setMessage(baseMessage, i, this.mConversationActivity);
        } else if (getItemViewType(i) == 0) {
            ((MQClientItem) mQClueCardItem).setMessage(baseMessage, i, this.mConversationActivity);
        } else if (getItemViewType(i) == 6) {
            ((MQNoAgentItem) mQClueCardItem).setCallback(this.mConversationActivity);
        } else if (getItemViewType(i) == 5) {
            ((MQRobotItem) mQClueCardItem).setMessage((RobotMessage) baseMessage, this.mConversationActivity);
        } else if (getItemViewType(i) == 10) {
            ((MQHybridItem) mQClueCardItem).setMessage((HybridMessage) baseMessage, this.mConversationActivity);
        } else if (getItemViewType(i) == 7) {
            ((MQInitiativeRedirectItem) mQClueCardItem).setMessage((InitiativeRedirectMessage) baseMessage, this.mConversationActivity);
        } else if (getItemViewType(i) == 2) {
            ((MQTimeItem) mQClueCardItem).setMessage(baseMessage);
        } else if (getItemViewType(i) == 3) {
            ((MQTipItem) mQClueCardItem).setMessage(baseMessage);
        } else if (getItemViewType(i) == 4) {
            ((MQEvaluateItem) mQClueCardItem).setMessage((EvaluateMessage) baseMessage);
        } else if (getItemViewType(i) == 8) {
            ((MQRedirectQueueItem) mQClueCardItem).setMessage((RedirectQueueMessage) baseMessage);
        } else if (getItemViewType(i) == 9) {
            ((MQHybridItem) mQClueCardItem).setMessage((HybridMessage) baseMessage, this.mConversationActivity);
        } else if (getItemViewType(i) == 11) {
            ((MQClueCardItem) mQClueCardItem).setMessage((ClueCardMessage) baseMessage, this.mConversationActivity);
        }
        return mQClueCardItem;
    }

    public int getViewTypeCount() {
        return 12;
    }

    public boolean isLastItemAndVisible(int i) {
        return i == this.mListView.getLastVisiblePosition() && this.mListView.getLastVisiblePosition() == getCount() + -1;
    }

    public void loadMoreMessage(List<BaseMessage> list) {
        this.mMessageList.addAll(0, list);
        notifyDataSetChanged();
        downloadAndNotifyDataSetChanged(list);
    }

    public void onClueCardMessageSendSuccess(BaseMessage baseMessage) {
        this.mMessageList.remove(baseMessage);
        TipMessage tipMessage = new TipMessage();
        tipMessage.setContent(this.mConversationActivity.getString(R.string.mq_submit_success));
        this.mMessageList.add(tipMessage);
        notifyDataSetChanged();
    }

    public void onFileMessageDownloadFailure(FileMessage fileMessage, int i, String str) {
        this.mConversationActivity.onFileMessageDownloadFailure(fileMessage, i, str);
    }

    public void onFileMessageExpired(FileMessage fileMessage) {
        this.mConversationActivity.onFileMessageExpired(fileMessage);
    }

    public void photoPreview(String str) {
        this.mConversationActivity.startActivity(MQPhotoPreviewActivity.newIntent(this.mConversationActivity, MQUtils.getImageDir(this.mConversationActivity), str));
    }

    public void resendFailedMessage(BaseMessage baseMessage) {
        notifyDataSetInvalidated();
        this.mConversationActivity.resendMessage(baseMessage);
    }

    public void scrollContentToBottom() {
        this.mConversationActivity.scrollContentToBottom();
    }

    public void setCurrentDownloadingItemPosition(int i) {
        this.mCurrentPlayingItemPosition = i;
    }

    public void setVoiceMessageDuration(VoiceMessage voiceMessage, String str) {
        voiceMessage.setLocalPath(str);
        voiceMessage.setDuration(MQAudioPlayerManager.getDurationByFilePath(this.mConversationActivity, str));
    }

    public void startPlayVoiceAndRefreshList(VoiceMessage voiceMessage, int i) {
        MQAudioPlayerManager.playSound(voiceMessage.getLocalPath(), new MQAudioPlayerManager.Callback() {
            public void onCompletion() {
                int unused = MQChatAdapter.this.mCurrentPlayingItemPosition = -1;
                MQChatAdapter.this.notifyDataSetChanged();
            }

            public void onError() {
                int unused = MQChatAdapter.this.mCurrentPlayingItemPosition = -1;
                MQChatAdapter.this.notifyDataSetChanged();
            }
        });
        voiceMessage.setIsRead(true);
        MQConfig.getController(this.mConversationActivity).updateMessage(voiceMessage.getId(), true);
        this.mCurrentPlayingItemPosition = i;
        notifyDataSetChanged();
    }

    public void stopPlayVoice() {
        MQAudioPlayerManager.stop();
        this.mCurrentPlayingItemPosition = -1;
        notifyDataSetChanged();
    }
}
