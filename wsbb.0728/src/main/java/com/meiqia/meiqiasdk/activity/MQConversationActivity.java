package com.meiqia.meiqiasdk.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.meiqia.core.MQManager;
import com.meiqia.core.MQMessageManager;
import com.meiqia.core.callback.OnClientPositionInQueueCallback;
import com.meiqia.core.callback.SuccessCallback;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.callback.LeaveMessageCallback;
import com.meiqia.meiqiasdk.callback.OnClientOnlineCallback;
import com.meiqia.meiqiasdk.callback.OnEvaluateRobotAnswerCallback;
import com.meiqia.meiqiasdk.callback.OnFinishCallback;
import com.meiqia.meiqiasdk.callback.OnGetMessageListCallBack;
import com.meiqia.meiqiasdk.callback.OnMessageSendCallback;
import com.meiqia.meiqiasdk.callback.SimpleCallback;
import com.meiqia.meiqiasdk.chatitem.MQInitiativeRedirectItem;
import com.meiqia.meiqiasdk.chatitem.MQRobotItem;
import com.meiqia.meiqiasdk.controller.ControllerImpl;
import com.meiqia.meiqiasdk.controller.MQController;
import com.meiqia.meiqiasdk.dialog.MQEvaluateDialog;
import com.meiqia.meiqiasdk.model.Agent;
import com.meiqia.meiqiasdk.model.AgentChangeMessage;
import com.meiqia.meiqiasdk.model.BaseMessage;
import com.meiqia.meiqiasdk.model.EvaluateMessage;
import com.meiqia.meiqiasdk.model.FileMessage;
import com.meiqia.meiqiasdk.model.InitiativeRedirectMessage;
import com.meiqia.meiqiasdk.model.LeaveTipMessage;
import com.meiqia.meiqiasdk.model.NoAgentLeaveMessage;
import com.meiqia.meiqiasdk.model.PhotoMessage;
import com.meiqia.meiqiasdk.model.RedirectQueueMessage;
import com.meiqia.meiqiasdk.model.RobotMessage;
import com.meiqia.meiqiasdk.model.TextMessage;
import com.meiqia.meiqiasdk.model.TipMessage;
import com.meiqia.meiqiasdk.model.VoiceMessage;
import com.meiqia.meiqiasdk.util.MQAudioPlayerManager;
import com.meiqia.meiqiasdk.util.MQAudioRecorderManager;
import com.meiqia.meiqiasdk.util.MQChatAdapter;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.meiqia.meiqiasdk.util.MQSimpleTextWatcher;
import com.meiqia.meiqiasdk.util.MQSoundPoolManager;
import com.meiqia.meiqiasdk.util.MQTimeUtils;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.meiqia.meiqiasdk.widget.MQCustomKeyboardLayout;
import com.stub.StubApp;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MQConversationActivity extends Activity implements View.OnClickListener, MQEvaluateDialog.Callback, MQCustomKeyboardLayout.Callback, View.OnTouchListener, MQRobotItem.Callback, LeaveMessageCallback, MQInitiativeRedirectItem.Callback {
    private static final long AUTO_DISMISS_TOP_TIP_TIME = 2000;
    public static final String CLIENT_ID = "clientId";
    public static final String CLIENT_INFO = "clientInfo";
    public static final String CUSTOMIZED_ID = "customizedId";
    /* access modifiers changed from: private */
    public static int MESSAGE_PAGE_COUNT = 30;
    public static final String PRE_SEND_IMAGE_PATH = "preSendImagePath";
    public static final String PRE_SEND_TEXT = "preSendText";
    private static final int RECORD_AUDIO_REQUEST_CODE = 2;
    public static final int REQUEST_CODE_CAMERA = 0;
    public static final int REQUEST_CODE_PHOTO = 1;
    private static final String TAG = "MQConversationActivity";
    public static final String UPDATE_CLIENT_INFO = "updateClientInfo";
    private static final int WHAT_GET_CLIENT_POSITION_IN_QUEUE = 1;
    private static final int WRITE_EXTERNAL_STORAGE_AND_CAMERA_REQUEST_CODE = 3;
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;
    private List<BaseMessage> delaySendList = new ArrayList();
    /* access modifiers changed from: private */
    public BaseMessage entWelcomeMsg;
    /* access modifiers changed from: private */
    public boolean hasSetClientOnline = false;
    private TextWatcher inputTextWatcher = new MQSimpleTextWatcher() {
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!TextUtils.isEmpty(charSequence)) {
                MQConversationActivity.this.inputting(charSequence.toString());
                if (Build.VERSION.SDK_INT >= 21) {
                    MQConversationActivity.this.mSendTextBtn.setElevation((float) MQUtils.dip2px(MQConversationActivity.this, 3.0f));
                }
                MQConversationActivity.this.mSendTextBtn.setImageResource(R.drawable.mq_ic_send_icon_white);
                MQConversationActivity.this.mSendTextBtn.setBackgroundResource(R.drawable.mq_shape_send_back_pressed);
                return;
            }
            if (Build.VERSION.SDK_INT >= 21) {
                MQConversationActivity.this.mSendTextBtn.setElevation(CropImageView.DEFAULT_ASPECT_RATIO);
            }
            MQConversationActivity.this.mSendTextBtn.setImageResource(R.drawable.mq_ic_send_icon_grey);
            MQConversationActivity.this.mSendTextBtn.setBackgroundResource(R.drawable.mq_shape_send_back_normal);
        }
    };
    private boolean isAddLeaveTip;
    /* access modifiers changed from: private */
    public boolean isBlackState;
    private boolean isDestroy;
    private boolean isNeedDelayOnline;
    private boolean isPause;
    private Runnable mAutoDismissTopTipRunnable;
    private ImageView mBackIv;
    private RelativeLayout mBackRl;
    private TextView mBackTv;
    private String mCameraPicPath;
    private Uri mCameraPicUri;
    private View mCameraSelectBtn;
    /* access modifiers changed from: private */
    public RelativeLayout mChatBodyRl;
    /* access modifiers changed from: private */
    public List<BaseMessage> mChatMessageList = new ArrayList();
    /* access modifiers changed from: private */
    public MQChatAdapter mChatMsgAdapter;
    /* access modifiers changed from: private */
    public MQController mController;
    /* access modifiers changed from: private */
    public String mConversationId;
    /* access modifiers changed from: private */
    public ListView mConversationListView;
    /* access modifiers changed from: private */
    public Agent mCurrentAgent;
    /* access modifiers changed from: private */
    public MQCustomKeyboardLayout mCustomKeyboardLayout;
    private View mEmojiSelectBtn;
    private ImageView mEmojiSelectImg;
    private View mEmojiSelectIndicator;
    private View mEvaluateBtn;
    private MQEvaluateDialog mEvaluateDialog;
    /* access modifiers changed from: private */
    public Handler mHandler;
    /* access modifiers changed from: private */
    public boolean mHasLoadData = false;
    private EditText mInputEt;
    /* access modifiers changed from: private */
    public boolean mIsAllocatingAgent;
    private boolean mIsShowRedirectHumanButton;
    private long mLastSendRobotMessageTime;
    private ProgressBar mLoadProgressBar;
    /* access modifiers changed from: private */
    public MessageReceiver mMessageReceiver;
    private NetworkChangeReceiver mNetworkChangeReceiver;
    private View mPhotoSelectBtn;
    private TextView mRedirectHumanTv;
    private RedirectQueueMessage mRedirectQueueMessage;
    /* access modifiers changed from: private */
    public ImageButton mSendTextBtn;
    /* access modifiers changed from: private */
    public MQSoundPoolManager mSoundPoolManager;
    /* access modifiers changed from: private */
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private RelativeLayout mTitleRl;
    private TextView mTitleTv;
    /* access modifiers changed from: private */
    public TextView mTopTipViewTv;
    private View mVoiceBtn;
    private ImageView mVoiceSelectImg;
    private View mVoiceSelectIndicator;

    private class MessageReceiver extends com.meiqia.meiqiasdk.controller.MessageReceiver {
        private MessageReceiver() {
        }

        public void addDirectAgentMessageTip(String str) {
            MQConversationActivity.this.addDirectAgentMessageTip(str);
        }

        public void blackAdd() {
            boolean unused = MQConversationActivity.this.isBlackState = true;
            MQConversationActivity.this.changeTitleToNoAgentState();
        }

        public void blackDel() {
            boolean unused = MQConversationActivity.this.isBlackState = false;
        }

        public void changeTitleToInputting() {
            MQConversationActivity.this.changeTitleToInputting();
            MQConversationActivity.this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    MQConversationActivity.this.setCurrentAgent(MQConversationActivity.this.mCurrentAgent);
                }
            }, MQConversationActivity.AUTO_DISMISS_TOP_TIP_TIME);
        }

        public void inviteEvaluation() {
            if (MQConversationActivity.this.checkSendable()) {
                MQConversationActivity.this.showEvaluateDialog();
            }
        }

        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
        }

        public void queueingInitConv() {
            removeQueue();
            setCurrentAgent(MQConversationActivity.this.mController.getCurrentAgent());
        }

        public void recallMessage(long j, String str) {
            BaseMessage baseMessage = new BaseMessage();
            baseMessage.setId(j);
            MQConversationActivity.this.mChatMessageList.remove(baseMessage);
            TipMessage tipMessage = new TipMessage();
            tipMessage.setContent(MQConversationActivity.this.getResources().getString(R.string.mq_recall_msg));
            MQConversationActivity.this.mChatMessageList.add(tipMessage);
            MQConversationActivity.this.mChatMsgAdapter.notifyDataSetChanged();
        }

        public void receiveNewMsg(BaseMessage baseMessage) {
            MQConversationActivity.this.receiveNewMsg(baseMessage);
        }

        public void removeQueue() {
            MQConversationActivity.this.mHandler.removeMessages(1);
            MQConversationActivity.this.removeRedirectQueueLeaveMsg();
            MQConversationActivity.this.sendPreMessage();
        }

        public void setCurrentAgent(Agent agent) {
            MQConversationActivity.this.setCurrentAgent(agent);
        }

        public void setNewConversationId(String str) {
            String unused = MQConversationActivity.this.mConversationId = str;
        }

        public void socketOpen() {
        }

        public void updateAgentOnlineOfflineStatus() {
            MQConversationActivity.this.updateAgentOnlineOfflineStatusAndRedirectHuman();
        }
    }

    private class NetworkChangeReceiver extends BroadcastReceiver {
        private boolean isFirstReceiveBroadcast;

        private NetworkChangeReceiver() {
            this.isFirstReceiveBroadcast = true;
        }

        public void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                return;
            }
            if (this.isFirstReceiveBroadcast) {
                this.isFirstReceiveBroadcast = false;
            } else if (MQUtils.isNetworkAvailable(StubApp.getOrigApplicationContext(MQConversationActivity.this.getApplicationContext()))) {
                MQConversationActivity.this.setCurrentAgent(MQConversationActivity.this.mController.getCurrentAgent());
                MQConversationActivity.this.getClientPositionInQueue();
            } else {
                MQConversationActivity.this.changeTitleToNetErrorState();
                MQConversationActivity.this.mHandler.removeMessages(1);
            }
        }
    }

    static {
        StubApp.interface11(6091);
    }

    /* access modifiers changed from: private */
    public void addInitiativeRedirectMessage(@StringRes int i) {
        if (this.mCurrentAgent != null && !this.mCurrentAgent.isRobot()) {
            return;
        }
        if (this.mChatMessageList == null || this.mChatMessageList.size() <= 0 || !(this.mChatMessageList.get(this.mChatMessageList.size() - 1) instanceof InitiativeRedirectMessage)) {
            removeInitiativeRedirectMessage();
            this.mChatMsgAdapter.addMQMessage(new InitiativeRedirectMessage(i));
            MQUtils.scrollListViewToBottom(this.mConversationListView);
        }
    }

    /* access modifiers changed from: private */
    public void addNoAgentLeaveMsg() {
        if (this.mRedirectQueueMessage == null || this.mCurrentAgent == null) {
            removeRedirectQueueLeaveMsg();
            if (this.mChatMessageList == null || this.mChatMessageList.size() <= 0 || !(this.mChatMessageList.get(this.mChatMessageList.size() - 1) instanceof NoAgentLeaveMessage)) {
                removeNoAgentLeaveMsg();
                if (this.mCurrentAgent == null) {
                    changeTitleToNoAgentState();
                }
                this.mChatMsgAdapter.addMQMessage(new NoAgentLeaveMessage());
                MQUtils.scrollListViewToBottom(this.mConversationListView);
                return;
            }
            return;
        }
        addRedirectQueueLeaveMsg(this.mRedirectQueueMessage.getQueueSize());
    }

    /* access modifiers changed from: private */
    public void addRedirectQueueLeaveMsg(int i) {
        removeNoAgentLeaveMsg();
        changeTitleToQueue();
        removeRedirectQueueLeaveMsg();
        this.mRedirectQueueMessage = new RedirectQueueMessage(i);
        this.mChatMsgAdapter.addMQMessage(this.mRedirectQueueMessage);
        MQUtils.scrollListViewToBottom(this.mConversationListView);
    }

    private void applyCustomUIConfig() {
        if (-1 != MQConfig.ui.backArrowIconResId) {
            this.mBackIv.setImageResource(MQConfig.ui.backArrowIconResId);
        }
        MQUtils.applyCustomUITintDrawable(this.mTitleRl, 17170443, R.color.mq_activity_title_bg, MQConfig.ui.titleBackgroundResId);
        MQUtils.applyCustomUITextAndImageColor(R.color.mq_activity_title_textColor, MQConfig.ui.titleTextColorResId, (ImageView) null, this.mBackTv, this.mTitleTv, this.mRedirectHumanTv);
        MQUtils.applyCustomUITitleGravity(this.mBackTv, this.mTitleTv);
        MQUtils.tintPressedIndicator((ImageView) findViewById(R.id.photo_select_iv), R.drawable.mq_ic_image_normal, R.drawable.mq_ic_image_active);
        MQUtils.tintPressedIndicator((ImageView) findViewById(R.id.camera_select_iv), R.drawable.mq_ic_camera_normal, R.drawable.mq_ic_camera_active);
        MQUtils.tintPressedIndicator((ImageView) findViewById(R.id.evaluate_select_iv), R.drawable.mq_ic_evaluate_normal, R.drawable.mq_ic_evaluate_active);
    }

    private void cancelAllDownload() {
        for (BaseMessage next : this.mChatMessageList) {
            if (next instanceof FileMessage) {
                MQConfig.getController(this).cancelDownload(((FileMessage) next).getUrl());
            }
        }
    }

    private boolean checkAndPreSend(BaseMessage baseMessage) {
        if (this.mChatMsgAdapter == null) {
            return false;
        }
        if (this.mRedirectQueueMessage == null || this.mCurrentAgent != null) {
            baseMessage.setStatus("sending");
            this.mChatMessageList.add(baseMessage);
            this.mInputEt.setText("");
            String currentClientId = this.mController.getCurrentClientId();
            if (!TextUtils.isEmpty(currentClientId)) {
                MQUtils.setUnSendTextMessage(this, currentClientId, "");
            }
            MQTimeUtils.refreshMQTimeItem(this.mChatMessageList);
            this.mChatMsgAdapter.notifyDataSetChanged();
            return true;
        }
        popTopTip(R.string.mq_allocate_queue_tip);
        return false;
    }

    private boolean checkAudioPermission() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.RECORD_AUDIO"}, 2);
        return false;
    }

    private void checkIfNeedUpdateClient(final OnFinishCallback onFinishCallback) {
        String str;
        String str2;
        if (getIntent() != null) {
            str = getIntent().getStringExtra(CLIENT_ID);
            str2 = getIntent().getStringExtra(CUSTOMIZED_ID);
        } else {
            str = null;
            str2 = null;
        }
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
            if (!TextUtils.isEmpty(str)) {
                str2 = str;
            }
            MQManager.getInstance(this).setCurrentClient(str2, new SuccessCallback() {
                public void onFailure(int i, String str) {
                    onFinishCallback.onFinish();
                }

                public void onSuccess() {
                    onFinishCallback.onFinish();
                }
            });
            return;
        }
        onFinishCallback.onFinish();
    }

    /* access modifiers changed from: private */
    public boolean checkSendable() {
        if (this.mIsAllocatingAgent) {
            MQUtils.show((Context) this, R.string.mq_allocate_agent_tip);
            return false;
        } else if (!this.mHasLoadData) {
            MQUtils.show((Context) this, R.string.mq_data_is_loading);
            return false;
        } else if (this.mRedirectQueueMessage == null || this.mCurrentAgent != null) {
            if (this.mCurrentAgent != null && this.mCurrentAgent.isRobot()) {
                if (System.currentTimeMillis() - this.mLastSendRobotMessageTime <= 1000) {
                    MQUtils.show((Context) this, R.string.mq_send_robot_msg_time_limit_tip);
                    return false;
                }
                this.mLastSendRobotMessageTime = System.currentTimeMillis();
            }
            return true;
        } else {
            popTopTip(R.string.mq_allocate_queue_tip);
            return false;
        }
    }

    private boolean checkStorageAndCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"}, 3);
        return false;
    }

    private boolean checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        return false;
    }

    private void chooseFromPhotoPicker() {
        try {
            startActivityForResult(MQPhotoPickerActivity.newIntent(this, (File) null, 3, (ArrayList<String>) null, getString(R.string.mq_send)), 1);
        } catch (Exception e) {
            MQUtils.show((Context) this, R.string.mq_photo_not_support);
        }
    }

    private void choosePhotoFromCamera() {
        Uri fromFile;
        MQUtils.closeKeyboard((Activity) this);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        new File(MQUtils.getPicStorePath(this)).mkdirs();
        String str = MQUtils.getPicStorePath(this) + "/" + System.currentTimeMillis() + ".jpg";
        File file = new File(str);
        this.mCameraPicPath = str;
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                ContentValues contentValues = new ContentValues(1);
                contentValues.put("_data", file.getAbsolutePath());
                fromFile = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            } else {
                fromFile = Uri.fromFile(file);
            }
            this.mCameraPicUri = fromFile;
            intent.putExtra("output", fromFile);
            startActivityForResult(intent, 0);
        } catch (Exception e) {
            MQUtils.show((Context) this, R.string.mq_photo_not_support);
        }
    }

    /* access modifiers changed from: private */
    public List<BaseMessage> cleanDupMessages(List<BaseMessage> list, List<BaseMessage> list2) {
        Iterator<BaseMessage> it = list2.iterator();
        while (it.hasNext()) {
            if (list.contains(it.next())) {
                it.remove();
            }
        }
        return list2;
    }

    /* access modifiers changed from: private */
    public void cleanVoiceMessage(List<BaseMessage> list) {
        if (!MQConfig.isVoiceSwitchOpen && list.size() > 0) {
            Iterator<BaseMessage> it = list.iterator();
            while (it.hasNext()) {
                if ("audio".equals(it.next().getContentType())) {
                    it.remove();
                }
            }
        }
    }

    private void createAndSendImageMessage(File file) {
        if (Build.VERSION.SDK_INT >= 29 || file.exists()) {
            PhotoMessage photoMessage = new PhotoMessage();
            photoMessage.setLocalPath(file.getAbsolutePath());
            sendMessage(photoMessage);
        }
    }

    private void createAndSendTextMessage(String str) {
        if (!TextUtils.isEmpty(str.trim())) {
            sendMessage(new TextMessage(str));
        }
    }

    private void findViews() {
        this.mTitleRl = (RelativeLayout) findViewById(R.id.title_rl);
        this.mBackRl = (RelativeLayout) findViewById(R.id.back_rl);
        this.mBackTv = (TextView) findViewById(R.id.back_tv);
        this.mBackIv = (ImageView) findViewById(R.id.back_iv);
        this.mRedirectHumanTv = (TextView) findViewById(R.id.redirect_human_tv);
        this.mChatBodyRl = (RelativeLayout) findViewById(R.id.chat_body_rl);
        this.mConversationListView = (ListView) findViewById(R.id.messages_lv);
        this.mInputEt = (EditText) findViewById(R.id.input_et);
        this.mEmojiSelectBtn = findViewById(R.id.emoji_select_btn);
        this.mCustomKeyboardLayout = (MQCustomKeyboardLayout) findViewById(R.id.customKeyboardLayout);
        this.mSendTextBtn = (ImageButton) findViewById(R.id.send_text_btn);
        this.mPhotoSelectBtn = findViewById(R.id.photo_select_btn);
        this.mCameraSelectBtn = findViewById(R.id.camera_select_btn);
        this.mVoiceBtn = findViewById(R.id.mic_select_btn);
        this.mEvaluateBtn = findViewById(R.id.evaluate_select_btn);
        this.mLoadProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        this.mTitleTv = (TextView) findViewById(R.id.title_tv);
        this.mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        this.mEmojiSelectIndicator = findViewById(R.id.emoji_select_indicator);
        this.mEmojiSelectImg = (ImageView) findViewById(R.id.emoji_select_img);
        this.mVoiceSelectIndicator = findViewById(R.id.conversation_voice_indicator);
        this.mVoiceSelectImg = (ImageView) findViewById(R.id.conversation_voice_img);
    }

    private void forceRedirectHuman() {
        if (this.mController.getCurrentAgent() != null && this.mController.getCurrentAgent().isRobot()) {
            this.mController.setForceRedirectHuman(true);
            setClientOnline(true);
        }
    }

    private String getClientAvatarUrl() {
        Serializable serializableExtra;
        if (!(getIntent() == null || (serializableExtra = getIntent().getSerializableExtra(CLIENT_INFO)) == null)) {
            HashMap hashMap = (HashMap) serializableExtra;
            if (hashMap.containsKey("avatar")) {
                return (String) hashMap.get("avatar");
            }
        }
        return "";
    }

    /* access modifiers changed from: private */
    public void getClientPositionInQueue() {
        this.mHandler.removeMessages(1);
        if (this.mController.getIsWaitingInQueue() && MQUtils.isNetworkAvailable(StubApp.getOrigApplicationContext(getApplicationContext()))) {
            this.mController.getClientPositionInQueue(new OnClientPositionInQueueCallback() {
                public void onFailure(int i, String str) {
                    MQConversationActivity.this.sendGetClientPositionInQueueMsg();
                }

                public void onSuccess(int i) {
                    if (i > 0) {
                        MQConversationActivity.this.addRedirectQueueLeaveMsg(i);
                        MQConversationActivity.this.sendGetClientPositionInQueueMsg();
                        return;
                    }
                    MQConversationActivity.this.setClientOnline(true);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void getMessageDataFromDatabaseAndLoad() {
        this.mController.getMessagesFromDatabase(System.currentTimeMillis(), MESSAGE_PAGE_COUNT, new OnGetMessageListCallBack() {
            public void onFailure(int i, String str) {
            }

            public void onSuccess(List<BaseMessage> list) {
                MQConversationActivity.this.cleanVoiceMessage(list);
                MQConversationActivity.this.mChatMessageList.addAll(list);
                MQConversationActivity.this.loadData();
                if (MQConversationActivity.this.entWelcomeMsg != null) {
                    MQConversationActivity.this.mChatMessageList.remove(MQConversationActivity.this.entWelcomeMsg);
                }
                if (MQConversationActivity.this.mController.getEnterpriseConfig().scheduler_after_client_send_msg && MQConversationActivity.this.entWelcomeMsg == null && !TextUtils.isEmpty(MQConversationActivity.this.mController.getEnterpriseConfig().ent_welcome_message)) {
                    BaseMessage unused = MQConversationActivity.this.entWelcomeMsg = new TextMessage();
                    MQConversationActivity.this.entWelcomeMsg.setAvatar(MQConversationActivity.this.mController.getEnterpriseConfig().avatar);
                    String str = MQConversationActivity.this.mController.getEnterpriseConfig().public_nickname;
                    if (TextUtils.equals("null", str)) {
                        str = MQConversationActivity.this.getResources().getString(R.string.mq_title_default);
                    }
                    MQConversationActivity.this.entWelcomeMsg.setAgentNickname(str);
                    MQConversationActivity.this.entWelcomeMsg.setContent(MQConversationActivity.this.mController.getEnterpriseConfig().ent_welcome_message);
                    MQConversationActivity.this.entWelcomeMsg.setItemViewType(1);
                    MQConversationActivity.this.entWelcomeMsg.setStatus("arrived");
                    MQConversationActivity.this.entWelcomeMsg.setId(System.currentTimeMillis());
                    MQConversationActivity.this.receiveNewMsg(MQConversationActivity.this.entWelcomeMsg);
                }
            }
        });
    }

    private void getMessageFromServiceAndLoad() {
        checkIfNeedUpdateClient(new OnFinishCallback() {
            public void onFinish() {
                MQConversationActivity.this.mController.getMessageFromService(System.currentTimeMillis(), MQConversationActivity.MESSAGE_PAGE_COUNT, new OnGetMessageListCallBack() {
                    public void onFailure(int i, String str) {
                        MQConversationActivity.this.getMessageDataFromDatabaseAndLoad();
                    }

                    public void onSuccess(List<BaseMessage> list) {
                        MQConversationActivity.this.getMessageDataFromDatabaseAndLoad();
                    }
                });
            }
        });
    }

    private void hiddenAgentStatusAndRedirectHuman() {
        this.mTitleTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        this.mRedirectHumanTv.setVisibility(8);
        this.mEvaluateBtn.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void hideEmojiSelectIndicator() {
        this.mEmojiSelectIndicator.setVisibility(8);
        this.mEmojiSelectImg.setImageResource(R.drawable.mq_ic_emoji_normal);
        this.mEmojiSelectImg.clearColorFilter();
    }

    /* access modifiers changed from: private */
    public void hideVoiceSelectIndicator() {
        this.mVoiceSelectIndicator.setVisibility(8);
        this.mVoiceSelectImg.setImageResource(R.drawable.mq_ic_mic_normal);
        this.mVoiceSelectImg.clearColorFilter();
    }

    private void init() {
        File externalFilesDir;
        int i = 8;
        if (this.mController == null) {
            this.mController = new ControllerImpl(this);
        }
        MQTimeUtils.init(this);
        if (TextUtils.isEmpty(MQUtils.DOWNLOAD_DIR) && (externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)) != null) {
            MQUtils.DOWNLOAD_DIR = externalFilesDir.getAbsolutePath();
        }
        this.mHandler = new Handler() {
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    MQConversationActivity.this.getClientPositionInQueue();
                }
            }
        };
        this.mSoundPoolManager = MQSoundPoolManager.getInstance(this);
        this.mChatMsgAdapter = new MQChatAdapter(this, this.mChatMessageList, this.mConversationListView);
        this.mConversationListView.setAdapter(this.mChatMsgAdapter);
        this.mVoiceBtn.setVisibility(MQConfig.isVoiceSwitchOpen ? 0 : 8);
        View view = this.mEvaluateBtn;
        if (MQConfig.isEvaluateSwitchOpen) {
            i = 0;
        }
        view.setVisibility(i);
        this.mCustomKeyboardLayout.init(this, this.mInputEt, this);
        this.isDestroy = false;
        this.isNeedDelayOnline = this.mController.getEnterpriseConfig().scheduler_after_client_send_msg;
    }

    /* access modifiers changed from: private */
    public void inputting(String str) {
        this.mController.sendClientInputtingWithContent(str);
    }

    private boolean isDupMessage(BaseMessage baseMessage) {
        for (BaseMessage equals : this.mChatMessageList) {
            if (equals.equals(baseMessage)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void loadData() {
        MQTimeUtils.refreshMQTimeItem(this.mChatMessageList);
        this.mLoadProgressBar.setVisibility(8);
        Iterator<BaseMessage> it = this.mChatMessageList.iterator();
        String clientAvatarUrl = getClientAvatarUrl();
        while (it.hasNext()) {
            BaseMessage next = it.next();
            if ("sending".equals(next.getStatus())) {
                next.setStatus("arrived");
            } else if ("ending".equals(next.getType()) && this.isBlackState) {
                it.remove();
            }
            if (MQConfig.isShowClientAvatar && !TextUtils.isEmpty(clientAvatarUrl) && next.getItemViewType() == 0) {
                next.setAvatar(clientAvatarUrl);
            }
        }
        if (this.isBlackState) {
            addBlacklistTip(R.string.mq_blacklist_tips);
        }
        MQUtils.scrollListViewToBottom(this.mConversationListView);
        this.mChatMsgAdapter.downloadAndNotifyDataSetChanged(this.mChatMessageList);
        this.mChatMsgAdapter.notifyDataSetChanged();
        if (!this.mHasLoadData) {
            onLoadDataComplete(this, this.mCurrentAgent);
        }
        this.mHasLoadData = true;
    }

    /* access modifiers changed from: private */
    public void loadMoreDataFromDatabase() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mChatMessageList.size() > 0) {
            currentTimeMillis = this.mChatMessageList.get(0).getCreatedOn();
        }
        this.mController.getMessagesFromDatabase(currentTimeMillis, MESSAGE_PAGE_COUNT, new OnGetMessageListCallBack() {
            public void onFailure(int i, String str) {
                MQConversationActivity.this.mChatMsgAdapter.notifyDataSetChanged();
                MQConversationActivity.this.mSwipeRefreshLayout.setRefreshing(false);
            }

            public void onSuccess(List<BaseMessage> list) {
                MQConversationActivity.this.cleanVoiceMessage(list);
                MQTimeUtils.refreshMQTimeItem(list);
                MQConversationActivity.this.mChatMsgAdapter.loadMoreMessage(MQConversationActivity.this.cleanDupMessages(MQConversationActivity.this.mChatMessageList, list));
                MQConversationActivity.this.mConversationListView.setSelection(list.size());
                MQConversationActivity.this.mSwipeRefreshLayout.setRefreshing(false);
                if (list.size() == 0) {
                    MQConversationActivity.this.mSwipeRefreshLayout.setEnabled(false);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void loadMoreDataFromService() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mChatMessageList.size() > 0) {
            currentTimeMillis = this.mChatMessageList.get(0).getCreatedOn();
        }
        this.mController.getMessageFromService(currentTimeMillis, MESSAGE_PAGE_COUNT, new OnGetMessageListCallBack() {
            public void onFailure(int i, String str) {
                MQConversationActivity.this.mChatMsgAdapter.notifyDataSetChanged();
                MQConversationActivity.this.mSwipeRefreshLayout.setRefreshing(false);
            }

            public void onSuccess(List<BaseMessage> list) {
                MQConversationActivity.this.cleanVoiceMessage(list);
                MQTimeUtils.refreshMQTimeItem(list);
                MQConversationActivity.this.mChatMsgAdapter.loadMoreMessage(MQConversationActivity.this.cleanDupMessages(MQConversationActivity.this.mChatMessageList, list));
                MQConversationActivity.this.mConversationListView.setSelection(list.size());
                MQConversationActivity.this.mSwipeRefreshLayout.setRefreshing(false);
                if (list.size() == 0) {
                    MQConversationActivity.this.mSwipeRefreshLayout.setEnabled(false);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void receiveNewMsg(BaseMessage baseMessage) {
        if (this.mChatMsgAdapter != null && !isDupMessage(baseMessage)) {
            if (!MQConfig.isVoiceSwitchOpen && "audio".equals(baseMessage.getContentType())) {
                return;
            }
            if (!"ending".equals(baseMessage.getType()) || !this.isBlackState) {
                this.mChatMessageList.add(baseMessage);
                MQTimeUtils.refreshMQTimeItem(this.mChatMessageList);
                if (baseMessage instanceof VoiceMessage) {
                    this.mChatMsgAdapter.downloadAndNotifyDataSetChanged(Arrays.asList(new BaseMessage[]{baseMessage}));
                } else if (baseMessage instanceof RobotMessage) {
                    RobotMessage robotMessage = (RobotMessage) baseMessage;
                    if ("redirect".equals(robotMessage.getSubType())) {
                        forceRedirectHuman();
                    } else if ("reply".equals(robotMessage.getSubType())) {
                        this.mChatMessageList.remove(baseMessage);
                        addNoAgentLeaveMsg();
                    } else if ("queueing".equals(robotMessage.getSubType())) {
                        forceRedirectHuman();
                    } else if ("manual_redirect".equals(robotMessage.getSubType())) {
                        this.mChatMessageList.remove(baseMessage);
                        addInitiativeRedirectMessage(R.string.mq_manual_redirect_tip);
                    } else {
                        this.mChatMsgAdapter.notifyDataSetChanged();
                    }
                } else {
                    this.mChatMsgAdapter.notifyDataSetChanged();
                }
                if (this.mConversationListView.getLastVisiblePosition() == this.mChatMsgAdapter.getCount() - 2) {
                    MQUtils.scrollListViewToBottom(this.mConversationListView);
                }
                if (!this.isPause && MQConfig.isSoundSwitchOpen) {
                    this.mSoundPoolManager.playSound(R.raw.mq_new_message);
                }
                this.mController.saveConversationLastMessageTime(baseMessage.getCreatedOn());
            }
        }
    }

    private void refreshEnterpriseConfig() {
        refreshRedirectHumanBtn();
        MQConfig.getController(this).refreshEnterpriseConfig(new SimpleCallback() {
            public void onFailure(int i, String str) {
            }

            public void onSuccess() {
                MQConversationActivity.this.refreshRedirectHumanBtn();
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshRedirectHumanBtn() {
        this.mIsShowRedirectHumanButton = MQConfig.getController(this).getEnterpriseConfig().robotSettings.isShow_switch();
        if (this.mCurrentAgent != null) {
            setCurrentAgent(this.mCurrentAgent);
        }
    }

    private void registerReceiver() {
        this.mMessageReceiver = new MessageReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("agent_inputting_action");
        intentFilter.addAction("new_msg_received_action");
        intentFilter.addAction("agent_send_card");
        intentFilter.addAction("agent_change_action");
        intentFilter.addAction("invite_evaluation");
        intentFilter.addAction("action_agent_status_update_event");
        intentFilter.addAction("action_black_add");
        intentFilter.addAction("action_black_del");
        intentFilter.addAction("action_queueing_remove");
        intentFilter.addAction("action_queueing_init_conv");
        intentFilter.addAction(MQMessageManager.ACTION_END_CONV_AGENT);
        intentFilter.addAction(MQMessageManager.ACTION_END_CONV_TIMEOUT);
        intentFilter.addAction(MQMessageManager.ACTION_SOCKET_OPEN);
        intentFilter.addAction(MQMessageManager.ACTION_RECALL_MESSAGE);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mMessageReceiver, intentFilter);
        this.mNetworkChangeReceiver = new NetworkChangeReceiver();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.mNetworkChangeReceiver, intentFilter2);
    }

    private void removeInitiativeRedirectMessage() {
        Iterator<BaseMessage> it = this.mChatMessageList.iterator();
        while (it.hasNext()) {
            if (it.next() instanceof InitiativeRedirectMessage) {
                it.remove();
                this.mChatMsgAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void removeNoAgentLeaveMsg() {
        Iterator<BaseMessage> it = this.mChatMessageList.iterator();
        while (it.hasNext()) {
            if (it.next() instanceof NoAgentLeaveMessage) {
                it.remove();
                this.mChatMsgAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void removeRedirectQueueLeaveMsg() {
        Iterator<BaseMessage> it = this.mChatMessageList.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next() instanceof RedirectQueueMessage) {
                    it.remove();
                    this.mChatMsgAdapter.notifyDataSetChanged();
                    break;
                }
            } else {
                break;
            }
        }
        this.mRedirectQueueMessage = null;
    }

    /* access modifiers changed from: private */
    public void renameVoiceFilename(BaseMessage baseMessage) {
        if (baseMessage instanceof VoiceMessage) {
            VoiceMessage voiceMessage = (VoiceMessage) baseMessage;
            MQAudioRecorderManager.renameVoiceFilename(this, voiceMessage.getLocalPath(), voiceMessage.getContent());
            this.mChatMsgAdapter.downloadAndNotifyDataSetChanged(Arrays.asList(new BaseMessage[]{baseMessage}));
        }
    }

    /* access modifiers changed from: private */
    public void sendDelayMessages() {
        if (this.delaySendList.size() != 0) {
            for (BaseMessage next : this.delaySendList) {
                next.setCreatedOn(System.currentTimeMillis());
                sendMessage(next);
            }
            this.delaySendList.clear();
        }
    }

    /* access modifiers changed from: private */
    public void sendGetClientPositionInQueueMsg() {
        this.mHandler.removeMessages(1);
        if (this.mController.getIsWaitingInQueue() && MQUtils.isNetworkAvailable(StubApp.getOrigApplicationContext(getApplicationContext()))) {
            changeTitleToQueue();
            this.mHandler.sendEmptyMessageDelayed(1, 15000);
        }
    }

    /* access modifiers changed from: private */
    public void sendPreMessage() {
        if (getIntent() != null && !this.mController.getIsWaitingInQueue()) {
            String stringExtra = getIntent().getStringExtra(PRE_SEND_TEXT);
            String stringExtra2 = getIntent().getStringExtra(PRE_SEND_IMAGE_PATH);
            if (!TextUtils.isEmpty(stringExtra)) {
                this.delaySendList.add(new TextMessage(stringExtra));
            }
            if (!TextUtils.isEmpty(stringExtra2)) {
                createAndSendImageMessage(new File(stringExtra2));
            }
            getIntent().putExtra(PRE_SEND_TEXT, "");
            getIntent().putExtra(PRE_SEND_IMAGE_PATH, "");
        }
    }

    /* access modifiers changed from: private */
    public void setClientOnline(final boolean z) {
        String str;
        String str2;
        if (z || (!z && this.mCurrentAgent == null)) {
            this.mIsAllocatingAgent = true;
            this.isNeedDelayOnline = false;
            changeTitleToAllocatingAgent();
            if (getIntent() != null) {
                str = getIntent().getStringExtra(CLIENT_ID);
                str2 = getIntent().getStringExtra(CUSTOMIZED_ID);
            } else {
                str = null;
                str2 = null;
            }
            this.mController.setCurrentClientOnline(str, str2, new OnClientOnlineCallback() {
                public void onFailure(int i, String str) {
                    boolean unused = MQConversationActivity.this.mIsAllocatingAgent = false;
                    if (19999 == i) {
                        MQConversationActivity.this.changeTitleToNetErrorState();
                    } else if (19998 == i) {
                        if (z) {
                            MQConversationActivity.this.setCurrentAgent(MQConversationActivity.this.mCurrentAgent);
                            MQConversationActivity.this.addNoAgentLeaveMsg();
                        } else {
                            MQConversationActivity.this.setCurrentAgent((Agent) null);
                            MQConversationActivity.this.setOrUpdateClientInfo();
                        }
                    } else if (20004 == i) {
                        MQConversationActivity.this.setCurrentAgent((Agent) null);
                        boolean unused2 = MQConversationActivity.this.isBlackState = true;
                    } else if (20010 != i) {
                        MQConversationActivity.this.changeTitleToUnknownErrorState();
                        MQConversationActivity mQConversationActivity = MQConversationActivity.this;
                        Toast.makeText(mQConversationActivity, "code = " + i + "\nmessage = " + str, 0).show();
                    }
                    if (!MQConversationActivity.this.mHasLoadData) {
                        MQConversationActivity.this.getMessageDataFromDatabaseAndLoad();
                    }
                    if (19998 == i) {
                        MQConversationActivity.this.sendDelayMessages();
                    }
                    boolean unused3 = MQConversationActivity.this.hasSetClientOnline = true;
                }

                public void onSuccess(Agent agent, String str, List<BaseMessage> list) {
                    boolean unused = MQConversationActivity.this.mIsAllocatingAgent = false;
                    MQConversationActivity.this.setCurrentAgent(agent);
                    String unused2 = MQConversationActivity.this.mConversationId = str;
                    MQConversationActivity.this.mMessageReceiver.setConversationId(str);
                    MQConversationActivity.this.cleanVoiceMessage(list);
                    MQConversationActivity.this.mChatMessageList.clear();
                    MQConversationActivity.this.mChatMessageList.addAll(list);
                    if (z && MQConversationActivity.this.mChatMessageList.size() > 0 && TextUtils.equals("welcome", ((BaseMessage) MQConversationActivity.this.mChatMessageList.get(MQConversationActivity.this.mChatMessageList.size() - 1)).getType())) {
                        AgentChangeMessage agentChangeMessage = new AgentChangeMessage();
                        agentChangeMessage.setAgentNickname(agent.getNickname());
                        MQConversationActivity.this.mChatMessageList.add(list.size() - 1, agentChangeMessage);
                    }
                    MQConversationActivity.this.setOrUpdateClientInfo();
                    MQConversationActivity.this.loadData();
                    if (MQConversationActivity.this.mController.getIsWaitingInQueue()) {
                        MQConversationActivity.this.getClientPositionInQueue();
                        MQConversationActivity.this.removeNoAgentLeaveMsg();
                        MQConversationActivity.this.changeTitleToQueue();
                    } else {
                        MQConversationActivity.this.removeRedirectQueueLeaveMsg();
                    }
                    MQConversationActivity.this.sendDelayMessages();
                    boolean unused3 = MQConversationActivity.this.hasSetClientOnline = true;
                }
            });
            return;
        }
        setCurrentAgent(this.mCurrentAgent);
    }

    /* access modifiers changed from: private */
    public void setCurrentAgent(Agent agent) {
        if (this.mRedirectQueueMessage == null || this.mCurrentAgent == null) {
            Agent agent2 = this.mCurrentAgent;
            this.mCurrentAgent = agent;
            if (this.mController.getIsWaitingInQueue()) {
                return;
            }
            if (this.mCurrentAgent == null) {
                changeTitleToNoAgentState();
                return;
            }
            this.mTitleTv.setText(agent.getNickname());
            updateAgentOnlineOfflineStatusAndRedirectHuman();
            if (agent2 != this.mCurrentAgent) {
                removeLeaveMessageTip();
                if (!this.mCurrentAgent.isRobot()) {
                    removeNoAgentLeaveMsg();
                    removeInitiativeRedirectMessage();
                    removeRedirectQueueLeaveMsg();
                }
            }
        }
    }

    private void setListeners() {
        this.mBackRl.setOnClickListener(this);
        this.mRedirectHumanTv.setOnClickListener(this);
        this.mSendTextBtn.setOnClickListener(this);
        this.mPhotoSelectBtn.setOnClickListener(this);
        this.mCameraSelectBtn.setOnClickListener(this);
        this.mVoiceBtn.setOnClickListener(this);
        this.mEvaluateBtn.setOnClickListener(this);
        this.mInputEt.addTextChangedListener(this.inputTextWatcher);
        this.mInputEt.setOnTouchListener(this);
        this.mInputEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                MQConversationActivity.this.mSendTextBtn.performClick();
                MQUtils.closeKeyboard((Activity) MQConversationActivity.this);
                return true;
            }
        });
        this.mEmojiSelectBtn.setOnClickListener(this);
        this.mConversationListView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0) {
                    return false;
                }
                MQConversationActivity.this.mCustomKeyboardLayout.closeAllKeyboard();
                MQConversationActivity.this.hideEmojiSelectIndicator();
                MQConversationActivity.this.hideVoiceSelectIndicator();
                return false;
            }
        });
        this.mConversationListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                String content = ((BaseMessage) MQConversationActivity.this.mChatMessageList.get(i)).getContent();
                if (TextUtils.isEmpty(content)) {
                    return false;
                }
                MQUtils.clip(MQConversationActivity.this, content);
                MQUtils.show((Context) MQConversationActivity.this, R.string.mq_copy_success);
                return true;
            }
        });
        this.mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                if (MQConfig.isLoadMessagesFromNativeOpen) {
                    MQConversationActivity.this.loadMoreDataFromDatabase();
                } else {
                    MQConversationActivity.this.loadMoreDataFromService();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void setOrUpdateClientInfo() {
        if (getIntent() != null) {
            Serializable serializableExtra = getIntent().getSerializableExtra(CLIENT_INFO);
            if (serializableExtra != null) {
                this.mController.setClientInfo((HashMap) serializableExtra, (SimpleCallback) null);
            }
            Serializable serializableExtra2 = getIntent().getSerializableExtra(UPDATE_CLIENT_INFO);
            if (serializableExtra2 != null) {
                this.mController.updateClientInfo((HashMap) serializableExtra2, (SimpleCallback) null);
            }
        }
    }

    private void showEmojiSelectIndicator() {
        this.mEmojiSelectIndicator.setVisibility(0);
        this.mEmojiSelectImg.setImageResource(R.drawable.mq_ic_emoji_active);
        this.mEmojiSelectImg.setColorFilter(getResources().getColor(R.color.mq_indicator_selected));
    }

    /* access modifiers changed from: private */
    public void showEvaluateDialog() {
        if (!this.mCustomKeyboardLayout.isRecording()) {
            this.mCustomKeyboardLayout.closeAllKeyboard();
            if (!TextUtils.isEmpty(this.mConversationId)) {
                if (this.mEvaluateDialog == null) {
                    this.mEvaluateDialog = new MQEvaluateDialog(this, this.mController.getEnterpriseConfig().serviceEvaluationConfig.getPrompt_text());
                    this.mEvaluateDialog.setCallback(this);
                }
                this.mEvaluateDialog.show();
            }
        }
    }

    private void showVoiceSelectIndicator() {
        this.mVoiceSelectIndicator.setVisibility(0);
        this.mVoiceSelectImg.setImageResource(R.drawable.mq_ic_mic_active);
        this.mVoiceSelectImg.setColorFilter(getResources().getColor(R.color.mq_indicator_selected));
    }

    /* access modifiers changed from: private */
    public void updateAgentOnlineOfflineStatusAndRedirectHuman() {
        int i = 0;
        Agent currentAgent = this.mController.getCurrentAgent();
        if (currentAgent != null) {
            if (!currentAgent.isOnline()) {
                this.mTitleTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.mq_shape_agent_status_offline, 0);
            } else if (currentAgent.isOffDuty()) {
                this.mTitleTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.mq_shape_agent_status_off_duty, 0);
            } else {
                this.mTitleTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.mq_shape_agent_status_online, 0);
            }
            if (currentAgent.isRobot()) {
                TextView textView = this.mRedirectHumanTv;
                if (!this.mIsShowRedirectHumanButton) {
                    i = 8;
                }
                textView.setVisibility(i);
                this.mEvaluateBtn.setVisibility(8);
                return;
            }
            this.mRedirectHumanTv.setVisibility(8);
            View view = this.mEvaluateBtn;
            if (!MQConfig.isEvaluateSwitchOpen) {
                i = 8;
            }
            view.setVisibility(i);
            return;
        }
        hiddenAgentStatusAndRedirectHuman();
    }

    /* access modifiers changed from: private */
    public void updateResendMessage(BaseMessage baseMessage, int i) {
        int indexOf = this.mChatMessageList.indexOf(baseMessage);
        this.mChatMessageList.remove(baseMessage);
        if (this.isBlackState && this.mChatMessageList.size() > indexOf && this.mChatMessageList.get(indexOf).getItemViewType() == 3) {
            this.mChatMessageList.remove(indexOf);
        }
        MQTimeUtils.refreshMQTimeItem(this.mChatMessageList);
        this.mChatMsgAdapter.addMQMessage(baseMessage);
        if (i == 20004) {
            addBlacklistTip(R.string.mq_blacklist_tips);
        }
        scrollContentToBottom();
    }

    /* access modifiers changed from: protected */
    public void addBlacklistTip(int i) {
        this.isBlackState = true;
        changeTitleToNoAgentState();
        BaseMessage baseMessage = new BaseMessage();
        baseMessage.setItemViewType(3);
        baseMessage.setContent(getResources().getString(i));
        this.mChatMsgAdapter.addMQMessage(baseMessage);
    }

    /* access modifiers changed from: protected */
    public void addDirectAgentMessageTip(String str) {
        AgentChangeMessage agentChangeMessage = new AgentChangeMessage();
        agentChangeMessage.setAgentNickname(str);
        this.mChatMessageList.add(this.mChatMessageList.size(), agentChangeMessage);
        this.mChatMsgAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void addEvaluateMessageTip(int i, String str) {
        this.mChatMsgAdapter.addMQMessage(new EvaluateMessage(i, str));
    }

    /* access modifiers changed from: protected */
    public void addLeaveMessageTip() {
        changeTitleToNoAgentState();
        if (!this.isAddLeaveTip) {
            LeaveTipMessage leaveTipMessage = new LeaveTipMessage();
            String string = getResources().getString(R.string.mq_leave_msg_tips);
            if (!TextUtils.isEmpty(this.mController.getEnterpriseConfig().ticketConfig.getIntro())) {
                string = this.mController.getEnterpriseConfig().ticketConfig.getIntro();
            }
            leaveTipMessage.setContent(string);
            int size = this.mChatMessageList.size();
            if (size != 0) {
                size--;
            }
            this.mChatMsgAdapter.addMQMessage(leaveTipMessage, size);
            this.isAddLeaveTip = true;
        }
    }

    /* access modifiers changed from: protected */
    public void changeTitleToAllocatingAgent() {
        this.mTitleTv.setText(getResources().getString(R.string.mq_allocate_agent));
        hiddenAgentStatusAndRedirectHuman();
    }

    /* access modifiers changed from: protected */
    public void changeTitleToInputting() {
        this.mTitleTv.setText(getResources().getString(R.string.mq_title_inputting));
        updateAgentOnlineOfflineStatusAndRedirectHuman();
    }

    /* access modifiers changed from: protected */
    public void changeTitleToNetErrorState() {
        this.mTitleTv.setText(getResources().getString(R.string.mq_title_net_not_work));
        this.mHandler.removeMessages(1);
        hiddenAgentStatusAndRedirectHuman();
    }

    /* access modifiers changed from: protected */
    public void changeTitleToNoAgentState() {
        this.mTitleTv.setText(getResources().getString(R.string.mq_title_leave_msg));
        hiddenAgentStatusAndRedirectHuman();
    }

    /* access modifiers changed from: protected */
    public void changeTitleToQueue() {
        this.mTitleTv.setText(getResources().getString(R.string.mq_allocate_queue_title));
        hiddenAgentStatusAndRedirectHuman();
    }

    /* access modifiers changed from: protected */
    public void changeTitleToUnknownErrorState() {
        this.mTitleTv.setText(getResources().getString(R.string.mq_title_unknown_error));
        hiddenAgentStatusAndRedirectHuman();
    }

    public void executeEvaluate(final int i, final String str) {
        if (checkSendable()) {
            this.mController.executeEvaluate(this.mConversationId, i, str, new SimpleCallback() {
                public void onFailure(int i, String str) {
                    MQUtils.show((Context) MQConversationActivity.this, R.string.mq_evaluate_failure);
                }

                public void onSuccess() {
                    MQConversationActivity.this.addEvaluateMessageTip(i, str);
                }
            });
        }
    }

    public File getCameraPicFile() {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        File file = new File(this.mCameraPicPath);
        if (Build.VERSION.SDK_INT >= 29 && this.mCameraPicUri != null) {
            String realFilePath = MQUtils.getRealFilePath(this, this.mCameraPicUri);
            if (!TextUtils.isEmpty(realFilePath)) {
                return new File(realFilePath);
            }
        }
        if (file.exists()) {
            return file;
        }
        return null;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        if (i == 0) {
            File cameraPicFile = getCameraPicFile();
            if (cameraPicFile != null) {
                createAndSendImageMessage(cameraPicFile);
            }
        } else if (i == 1) {
            Iterator<String> it = MQPhotoPickerActivity.getSelectedImages(intent).iterator();
            while (it.hasNext()) {
                createAndSendImageMessage(new File(it.next()));
            }
        }
    }

    public void onAudioRecorderFinish(int i, String str) {
        if (checkSendable()) {
            VoiceMessage voiceMessage = new VoiceMessage();
            voiceMessage.setDuration(i);
            voiceMessage.setLocalPath(str);
            sendMessage(voiceMessage);
        }
    }

    public void onAudioRecorderNoPermission() {
        MQUtils.show((Context) this, R.string.mq_recorder_no_permission);
    }

    public void onAudioRecorderTooShort() {
        MQUtils.show((Context) this, R.string.mq_record_record_time_is_short);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back_rl) {
            MQUtils.closeKeyboard((Activity) this);
            onBackPressed();
        } else if (id == R.id.emoji_select_btn) {
            if (this.mCustomKeyboardLayout.isEmotionKeyboardVisible()) {
                hideEmojiSelectIndicator();
            } else {
                showEmojiSelectIndicator();
            }
            hideVoiceSelectIndicator();
            this.mCustomKeyboardLayout.toggleEmotionOriginKeyboard();
        } else if (id == R.id.send_text_btn) {
            if (checkSendable()) {
                createAndSendTextMessage(this.mInputEt.getText().toString());
            }
        } else if (id == R.id.photo_select_btn) {
            if (checkSendable() && checkStoragePermission()) {
                hideEmojiSelectIndicator();
                hideVoiceSelectIndicator();
                chooseFromPhotoPicker();
            }
        } else if (id == R.id.camera_select_btn) {
            if (checkSendable() && checkStorageAndCameraPermission()) {
                hideEmojiSelectIndicator();
                hideVoiceSelectIndicator();
                choosePhotoFromCamera();
            }
        } else if (id == R.id.mic_select_btn) {
            if (checkSendable() && checkAudioPermission()) {
                if (this.mCustomKeyboardLayout.isVoiceKeyboardVisible()) {
                    hideVoiceSelectIndicator();
                } else {
                    showVoiceSelectIndicator();
                }
                hideEmojiSelectIndicator();
                this.mCustomKeyboardLayout.toggleVoiceOriginKeyboard();
            }
        } else if (id == R.id.evaluate_select_btn) {
            hideEmojiSelectIndicator();
            hideVoiceSelectIndicator();
            showEvaluateDialog();
        } else if (id == R.id.redirect_human_tv) {
            forceRedirectHuman();
        }
    }

    public void onClickForceRedirectHuman() {
        forceRedirectHuman();
    }

    public void onClickLeaveMessage() {
        startActivity(new Intent(this, MQMessageFormActivity.class));
    }

    public void onClickRobotMenuItem(String str) {
        sendMessage(new TextMessage(str));
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        MQUtils.closeKeyboard((Activity) this);
        try {
            this.mSoundPoolManager.release();
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mMessageReceiver);
            unregisterReceiver(this.mNetworkChangeReceiver);
        } catch (Exception e) {
        }
        this.isDestroy = true;
        cancelAllDownload();
        this.mController.onConversationClose();
        String currentClientId = this.mController.getCurrentClientId();
        if (!TextUtils.isEmpty(currentClientId)) {
            MQUtils.setUnSendTextMessage(this, currentClientId, this.mInputEt.getText().toString().trim());
        }
        MQConfig.getActivityLifecycleCallback().onActivityDestroyed(this);
        super.onDestroy();
    }

    public void onEvaluateRobotAnswer(final RobotMessage robotMessage, final int i) {
        this.mController.evaluateRobotAnswer(robotMessage.getId(), robotMessage.getQuestionId(), i, new OnEvaluateRobotAnswerCallback() {
            public void onFailure(int i, String str) {
                MQUtils.show((Context) MQConversationActivity.this, R.string.mq_evaluate_failure);
            }

            public void onSuccess(String str) {
                robotMessage.setAlreadyFeedback(true);
                MQConversationActivity.this.mChatMsgAdapter.notifyDataSetChanged();
                if (i == 0) {
                    MQConversationActivity.this.addInitiativeRedirectMessage(R.string.mq_useless_redirect_tip);
                }
                if (!TextUtils.isEmpty(str)) {
                    String str2 = null;
                    if (MQConversationActivity.this.mCurrentAgent != null) {
                        str2 = MQConversationActivity.this.mCurrentAgent.getAvatar();
                    }
                    MQConversationActivity.this.mChatMsgAdapter.addMQMessage(new TextMessage(str, str2));
                }
            }
        });
    }

    public void onFileMessageDownloadFailure(FileMessage fileMessage, int i, String str) {
        if (!this.isDestroy) {
            popTopTip(R.string.mq_download_error);
        }
    }

    public void onFileMessageExpired(FileMessage fileMessage) {
        if (!this.isDestroy) {
            popTopTip(R.string.mq_expired_top_tip);
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4 || !this.mCustomKeyboardLayout.isEmotionKeyboardVisible()) {
            return super.onKeyUp(i, keyEvent);
        }
        this.mCustomKeyboardLayout.closeEmotionKeyboard();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onLoadDataComplete(MQConversationActivity mQConversationActivity, Agent agent) {
        sendPreMessage();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.isPause = true;
        MQConfig.getActivityLifecycleCallback().onActivityPaused(this);
    }

    @TargetApi(23)
    public native void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr);

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!this.isNeedDelayOnline || this.mController.getCurrentAgent() != null) {
            setClientOnline(false);
        } else if (!this.mHasLoadData) {
            String str = this.mController.getEnterpriseConfig().public_nickname;
            if (TextUtils.equals("null", str)) {
                str = getResources().getString(R.string.mq_title_default);
            }
            this.mTitleTv.setText(str);
            this.mLoadProgressBar.setVisibility(0);
            getMessageFromServiceAndLoad();
        }
        this.isPause = false;
        MQConfig.getActivityLifecycleCallback().onActivityResumed(this);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("mCameraPicPath", this.mCameraPicPath);
        MQConfig.getActivityLifecycleCallback().onActivitySaveInstanceState(this, bundle);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (this.mHasLoadData) {
            this.mController.openService();
            sendGetClientPositionInQueueMsg();
        }
        MQConfig.getActivityLifecycleCallback().onActivityStarted(this);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.mHandler.removeMessages(1);
        if (this.mChatMsgAdapter != null) {
            this.mChatMsgAdapter.stopPlayVoice();
            MQAudioPlayerManager.release();
        }
        if (this.mChatMessageList == null || this.mChatMessageList.size() <= 0) {
            this.mController.saveConversationOnStopTime(System.currentTimeMillis());
        } else {
            this.mController.saveConversationOnStopTime(this.mChatMessageList.get(this.mChatMessageList.size() - 1).getCreatedOn());
        }
        MQConfig.getActivityLifecycleCallback().onActivityStopped(this);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        hideEmojiSelectIndicator();
        hideVoiceSelectIndicator();
        return false;
    }

    public void popTopTip(final int i) {
        if (this.mTopTipViewTv == null) {
            this.mTopTipViewTv = (TextView) getLayoutInflater().inflate(R.layout.mq_top_pop_tip, (ViewGroup) null);
            this.mTopTipViewTv.setText(i);
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.mq_top_tip_height);
            this.mChatBodyRl.addView(this.mTopTipViewTv, -1, dimensionPixelOffset);
            ViewCompat.setTranslationY(this.mTopTipViewTv, (float) (-dimensionPixelOffset));
            ViewCompat.animate(this.mTopTipViewTv).translationY(CropImageView.DEFAULT_ASPECT_RATIO).setDuration(300).start();
            if (this.mAutoDismissTopTipRunnable == null) {
                this.mAutoDismissTopTipRunnable = new Runnable() {
                    public void run() {
                        MQConversationActivity.this.popTopTip(i);
                    }
                };
            }
            this.mHandler.postDelayed(this.mAutoDismissTopTipRunnable, AUTO_DISMISS_TOP_TIP_TIME);
            return;
        }
        this.mHandler.removeCallbacks(this.mAutoDismissTopTipRunnable);
        ViewCompat.animate(this.mTopTipViewTv).translationY((float) (-this.mTopTipViewTv.getHeight())).setListener(new ViewPropertyAnimatorListenerAdapter() {
            public void onAnimationEnd(View view) {
                MQConversationActivity.this.mChatBodyRl.removeView(MQConversationActivity.this.mTopTipViewTv);
                TextView unused = MQConversationActivity.this.mTopTipViewTv = null;
            }
        }).setDuration(300).start();
    }

    /* access modifiers changed from: protected */
    public void removeLeaveMessageTip() {
        Iterator<BaseMessage> it = this.mChatMessageList.iterator();
        while (it.hasNext()) {
            if (it.next() instanceof LeaveTipMessage) {
                it.remove();
                this.mChatMsgAdapter.notifyDataSetChanged();
                return;
            }
        }
        this.isAddLeaveTip = false;
    }

    public void resendMessage(BaseMessage baseMessage) {
        if (this.mRedirectQueueMessage == null || this.mCurrentAgent != null) {
            baseMessage.setStatus("sending");
            this.mController.resendMessage(baseMessage, new OnMessageSendCallback() {
                public void onFailure(BaseMessage baseMessage, int i, String str) {
                    MQConversationActivity.this.updateResendMessage(baseMessage, i);
                }

                public void onSuccess(BaseMessage baseMessage, int i) {
                    MQConversationActivity.this.renameVoiceFilename(baseMessage);
                    MQConversationActivity.this.updateResendMessage(baseMessage, 0);
                    if (19998 == i) {
                        MQConversationActivity.this.addLeaveMessageTip();
                    }
                }
            });
            return;
        }
        popTopTip(R.string.mq_allocate_queue_tip);
    }

    public void scrollContentToBottom() {
        MQUtils.scrollListViewToBottom(this.mConversationListView);
    }

    public void sendMessage(BaseMessage baseMessage) {
        if (this.mController.getEnterpriseConfig().scheduler_after_client_send_msg && this.isNeedDelayOnline) {
            this.isNeedDelayOnline = false;
            this.mHasLoadData = false;
            this.mChatMessageList.clear();
            if (this.mChatMsgAdapter != null) {
                this.mChatMsgAdapter.notifyDataSetChanged();
            }
            MQUtils.closeKeyboard((Activity) this);
            this.mLoadProgressBar.setVisibility(0);
            baseMessage.setStatus("sending");
            this.delaySendList.add(baseMessage);
            if (baseMessage instanceof TextMessage) {
                this.mInputEt.setText("");
            }
            setClientOnline(false);
        } else if (checkAndPreSend(baseMessage)) {
            this.mController.sendMessage(baseMessage, new OnMessageSendCallback() {
                public void onFailure(BaseMessage baseMessage, int i, String str) {
                    if (i == 20004) {
                        MQConversationActivity.this.addBlacklistTip(R.string.mq_blacklist_tips);
                    } else if (i == 20008) {
                        if (MQConversationActivity.this.mCurrentAgent != null && !MQConversationActivity.this.mCurrentAgent.isRobot()) {
                            Agent unused = MQConversationActivity.this.mCurrentAgent = null;
                        }
                        MQConversationActivity.this.popTopTip(R.string.mq_allocate_queue_tip);
                        MQConversationActivity.this.getClientPositionInQueue();
                        MQConversationActivity.this.removeNoAgentLeaveMsg();
                        MQConversationActivity.this.changeTitleToQueue();
                    }
                    MQConversationActivity.this.mChatMsgAdapter.notifyDataSetChanged();
                }

                public void onSuccess(BaseMessage baseMessage, int i) {
                    MQConversationActivity.this.renameVoiceFilename(baseMessage);
                    MQConversationActivity.this.mChatMsgAdapter.notifyDataSetChanged();
                    if (19998 == i) {
                        MQConversationActivity.this.addLeaveMessageTip();
                    }
                    if (MQConfig.isSoundSwitchOpen) {
                        MQConversationActivity.this.mSoundPoolManager.playSound(R.raw.mq_send_message);
                    }
                }
            });
            MQUtils.scrollListViewToBottom(this.mConversationListView);
        }
    }

    public void startActivity(Intent intent) {
        List<ResolveInfo> queryIntentActivities;
        if (!intent.toString().contains("mailto") || !((queryIntentActivities = getPackageManager().queryIntentActivities(intent, 0)) == null || queryIntentActivities.size() == 0)) {
            if ("android.intent.action.VIEW".equals(intent.getAction())) {
                String scheme = intent.getData().getScheme();
                if (!TextUtils.isEmpty(scheme) && scheme.startsWith("http") && MQConfig.getOnLinkClickCallback() != null) {
                    MQConfig.getOnLinkClickCallback().onClick(this, intent, intent.getDataString());
                    return;
                }
            }
            super.startActivity(intent);
        }
    }

    public void superStartActivity(Intent intent) {
        super.startActivity(intent);
    }
}
