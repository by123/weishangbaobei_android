package com.meiqia.meiqiasdk.chatitem;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.chatitem.MQChatFileItem;
import com.meiqia.meiqiasdk.imageloader.MQImage;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;
import com.meiqia.meiqiasdk.model.BaseMessage;
import com.meiqia.meiqiasdk.model.FileMessage;
import com.meiqia.meiqiasdk.model.PhotoMessage;
import com.meiqia.meiqiasdk.model.VoiceMessage;
import com.meiqia.meiqiasdk.util.MQAudioPlayerManager;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.meiqia.meiqiasdk.util.MQDownloadManager;
import com.meiqia.meiqiasdk.util.MQEmotionUtil;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.meiqia.meiqiasdk.widget.MQBaseCustomCompositeView;
import com.meiqia.meiqiasdk.widget.MQImageView;
import com.umeng.commonsdk.proguard.e;
import java.io.File;

public abstract class MQBaseBubbleItem extends MQBaseCustomCompositeView implements MQChatFileItem.Callback {
    protected RelativeLayout chatBox;
    protected MQChatFileItem chatFileItem;
    protected MQImageView contentImage;
    protected TextView contentText;
    protected Callback mCallback;
    protected int mImageHeight;
    protected int mImageWidth;
    protected int mMaxItemWidth;
    protected int mMinItemWidth;
    protected View unreadCircle;
    protected MQImageView usAvatar;
    protected ImageView voiceAnimIv;
    protected View voiceContainerRl;
    protected TextView voiceContentTv;

    public interface Callback {
        int getCurrentDownloadingItemPosition();

        int getCurrentPlayingItemPosition();

        boolean isLastItemAndVisible(int i);

        void notifyDataSetChanged();

        void onClueCardMessageSendSuccess(BaseMessage baseMessage);

        void onFileMessageDownloadFailure(FileMessage fileMessage, int i, String str);

        void onFileMessageExpired(FileMessage fileMessage);

        void photoPreview(String str);

        void resendFailedMessage(BaseMessage baseMessage);

        void scrollContentToBottom();

        void setCurrentDownloadingItemPosition(int i);

        void setVoiceMessageDuration(VoiceMessage voiceMessage, String str);

        void startPlayVoiceAndRefreshList(VoiceMessage voiceMessage, int i);

        void stopPlayVoice();
    }

    public MQBaseBubbleItem(Context context, Callback callback) {
        super(context);
        this.mCallback = callback;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.contentText = (TextView) getViewById(R.id.content_text);
        this.contentImage = (MQImageView) getViewById(R.id.content_pic);
        this.voiceContentTv = (TextView) getViewById(R.id.tv_voice_content);
        this.voiceAnimIv = (ImageView) getViewById(R.id.iv_voice_anim);
        this.voiceContainerRl = getViewById(R.id.rl_voice_container);
        this.chatFileItem = (MQChatFileItem) getViewById(R.id.file_container);
        this.usAvatar = (MQImageView) getViewById(R.id.us_avatar_iv);
        this.chatBox = (RelativeLayout) getViewById(R.id.chat_box);
    }

    /* access modifiers changed from: protected */
    public void processLogic() {
        int screenWidth = MQUtils.getScreenWidth(getContext());
        float f = (float) screenWidth;
        this.mMaxItemWidth = (int) (0.5f * f);
        this.mMinItemWidth = (int) (f * 0.18f);
        this.mImageWidth = screenWidth / 3;
        this.mImageHeight = this.mImageWidth;
    }

    /* access modifiers changed from: protected */
    public void applyConfig(boolean z) {
        configChatBubbleBg(this.contentText, z);
        configChatBubbleTextColor(this.contentText, z);
        configChatBubbleBg(this.voiceContentTv, z);
        configChatBubbleTextColor(this.voiceContentTv, z);
    }

    private void configChatBubbleBg(View view, boolean z) {
        if (z) {
            MQUtils.applyCustomUITintDrawable(view, R.color.mq_chat_left_bubble_final, R.color.mq_chat_left_bubble, MQConfig.ui.leftChatBubbleColorResId);
        } else {
            MQUtils.applyCustomUITintDrawable(view, R.color.mq_chat_right_bubble_final, R.color.mq_chat_right_bubble, MQConfig.ui.rightChatBubbleColorResId);
        }
    }

    private void configChatBubbleTextColor(TextView textView, boolean z) {
        if (z) {
            MQUtils.applyCustomUITextAndImageColor(R.color.mq_chat_left_textColor, MQConfig.ui.leftChatTextColorResId, (ImageView) null, textView);
            return;
        }
        MQUtils.applyCustomUITextAndImageColor(R.color.mq_chat_right_textColor, MQConfig.ui.rightChatTextColorResId, (ImageView) null, textView);
    }

    public void setMessage(BaseMessage baseMessage, int i, Activity activity) {
        handleVisibilityByContentType(baseMessage);
        fillContent(baseMessage, i, activity);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleVisibilityByContentType(com.meiqia.meiqiasdk.model.BaseMessage r4) {
        /*
            r3 = this;
            android.widget.TextView r0 = r3.contentText
            r1 = 8
            r0.setVisibility(r1)
            com.meiqia.meiqiasdk.widget.MQImageView r0 = r3.contentImage
            r0.setVisibility(r1)
            android.view.View r0 = r3.voiceContainerRl
            r0.setVisibility(r1)
            com.meiqia.meiqiasdk.chatitem.MQChatFileItem r0 = r3.chatFileItem
            r0.setVisibility(r1)
            java.lang.String r4 = r4.getContentType()
            int r0 = r4.hashCode()
            r1 = 3143036(0x2ff57c, float:4.404332E-39)
            r2 = 0
            if (r0 == r1) goto L_0x0052
            r1 = 3556653(0x36452d, float:4.983932E-39)
            if (r0 == r1) goto L_0x0048
            r1 = 93166550(0x58d9bd6, float:1.3316821E-35)
            if (r0 == r1) goto L_0x003e
            r1 = 106642994(0x65b3e32, float:4.1235016E-35)
            if (r0 == r1) goto L_0x0034
            goto L_0x005c
        L_0x0034:
            java.lang.String r0 = "photo"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x005c
            r4 = 1
            goto L_0x005d
        L_0x003e:
            java.lang.String r0 = "audio"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x005c
            r4 = 2
            goto L_0x005d
        L_0x0048:
            java.lang.String r0 = "text"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x005c
            r4 = 0
            goto L_0x005d
        L_0x0052:
            java.lang.String r0 = "file"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x005c
            r4 = 3
            goto L_0x005d
        L_0x005c:
            r4 = -1
        L_0x005d:
            switch(r4) {
                case 0: goto L_0x0078;
                case 1: goto L_0x0072;
                case 2: goto L_0x006c;
                case 3: goto L_0x0066;
                default: goto L_0x0060;
            }
        L_0x0060:
            android.widget.TextView r4 = r3.contentText
            r4.setVisibility(r2)
            goto L_0x007d
        L_0x0066:
            com.meiqia.meiqiasdk.chatitem.MQChatFileItem r4 = r3.chatFileItem
            r4.setVisibility(r2)
            goto L_0x007d
        L_0x006c:
            android.view.View r4 = r3.voiceContainerRl
            r4.setVisibility(r2)
            goto L_0x007d
        L_0x0072:
            com.meiqia.meiqiasdk.widget.MQImageView r4 = r3.contentImage
            r4.setVisibility(r2)
            goto L_0x007d
        L_0x0078:
            android.widget.TextView r4 = r3.contentText
            r4.setVisibility(r2)
        L_0x007d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.meiqiasdk.chatitem.MQBaseBubbleItem.handleVisibilityByContentType(com.meiqia.meiqiasdk.model.BaseMessage):void");
    }

    private void fillContent(BaseMessage baseMessage, final int i, Activity activity) {
        String url;
        if (!TextUtils.isEmpty(baseMessage.getAvatar())) {
            MQImage.displayImage(activity, this.usAvatar, baseMessage.getAvatar(), R.drawable.mq_ic_holder_avatar, R.drawable.mq_ic_holder_avatar, 100, 100, (MQImageLoader.MQDisplayImageListener) null);
        }
        String contentType = baseMessage.getContentType();
        char c = 65535;
        int hashCode = contentType.hashCode();
        if (hashCode != 3143036) {
            if (hashCode != 3556653) {
                if (hashCode != 93166550) {
                    if (hashCode == 106642994 && contentType.equals("photo")) {
                        c = 1;
                    }
                } else if (contentType.equals("audio")) {
                    c = 2;
                }
            } else if (contentType.equals("text")) {
                c = 0;
            }
        } else if (contentType.equals("file")) {
            c = 3;
        }
        switch (c) {
            case 0:
                if (!TextUtils.isEmpty(baseMessage.getContent())) {
                    this.contentText.setText(MQEmotionUtil.getEmotionText(getContext(), baseMessage.getContent(), 20));
                    return;
                }
                return;
            case 1:
                PhotoMessage photoMessage = (PhotoMessage) baseMessage;
                if (MQUtils.isFileExist(photoMessage.getLocalPath())) {
                    url = photoMessage.getLocalPath();
                } else {
                    url = photoMessage.getUrl();
                }
                final String str = url;
                MQImage.displayImage(activity, this.contentImage, str, R.drawable.mq_ic_holder_light, R.drawable.mq_ic_holder_light, this.mImageWidth, this.mImageHeight, new MQImageLoader.MQDisplayImageListener() {
                    public void onSuccess(View view, String str) {
                        MQBaseBubbleItem.this.postDelayed(new Runnable() {
                            public void run() {
                                if (MQBaseBubbleItem.this.mCallback.isLastItemAndVisible(i)) {
                                    MQBaseBubbleItem.this.mCallback.scrollContentToBottom();
                                }
                            }
                        }, 500);
                        view.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                MQBaseBubbleItem.this.mCallback.photoPreview(str);
                            }
                        });
                    }
                });
                return;
            case 2:
                handleBindVoiceItem((VoiceMessage) baseMessage, i);
                return;
            case 3:
                handleBindFileItem((FileMessage) baseMessage);
                return;
            default:
                this.contentText.setText(getResources().getString(R.string.mq_unknown_msg_tip));
                return;
        }
    }

    private void handleBindFileItem(FileMessage fileMessage) {
        this.chatFileItem.initFileItem(this, fileMessage);
        switch (fileMessage.getFileState()) {
            case 0:
                this.chatFileItem.downloadSuccessState();
                return;
            case 1:
                this.chatFileItem.downloadingState();
                this.chatFileItem.setProgress(fileMessage.getProgress());
                return;
            case 2:
                this.chatFileItem.downloadInitState();
                return;
            case 3:
                this.chatFileItem.downloadFailedState();
                return;
            default:
                return;
        }
    }

    private void handleBindVoiceItem(final VoiceMessage voiceMessage, final int i) {
        String str;
        this.voiceContainerRl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MQBaseBubbleItem.this.handleClickVoiceBtn(voiceMessage, i);
            }
        });
        if (voiceMessage.getDuration() == -1) {
            str = "";
        } else {
            str = voiceMessage.getDuration() + e.ap;
        }
        this.voiceContentTv.setText(str);
        ViewGroup.LayoutParams layoutParams = this.voiceContainerRl.getLayoutParams();
        if (voiceMessage.getDuration() == -1) {
            this.voiceContentTv.setText("");
            layoutParams.width = this.mMinItemWidth;
        } else {
            this.voiceContentTv.setText(voiceMessage.getDuration() + "\"");
            layoutParams.width = (int) (((float) this.mMinItemWidth) + ((((float) this.mMaxItemWidth) / 60.0f) * ((float) voiceMessage.getDuration())));
        }
        this.voiceContainerRl.setLayoutParams(layoutParams);
        if (this.mCallback.getCurrentPlayingItemPosition() == i) {
            if (voiceMessage.getItemViewType() == 1) {
                this.voiceAnimIv.setImageResource(R.drawable.mq_anim_voice_left_playing);
            } else {
                this.voiceAnimIv.setImageResource(R.drawable.mq_anim_voice_right_playing);
            }
            ((AnimationDrawable) this.voiceAnimIv.getDrawable()).start();
        } else if (voiceMessage.getItemViewType() == 1) {
            this.voiceAnimIv.setImageResource(R.drawable.mq_voice_left_normal);
            this.voiceAnimIv.setColorFilter(getResources().getColor(R.color.mq_chat_left_textColor));
        } else {
            this.voiceAnimIv.setImageResource(R.drawable.mq_voice_right_normal);
            this.voiceAnimIv.setColorFilter(getResources().getColor(R.color.mq_chat_right_textColor));
        }
        if (this.unreadCircle == null) {
            return;
        }
        if (!voiceMessage.isRead()) {
            this.unreadCircle.setVisibility(0);
        } else {
            this.unreadCircle.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void handleClickVoiceBtn(VoiceMessage voiceMessage, int i) {
        if (TextUtils.isEmpty(voiceMessage.getLocalPath())) {
            this.mCallback.stopPlayVoice();
            downloadAndPlayVoice(voiceMessage, i);
        } else if (!MQAudioPlayerManager.isPlaying() || this.mCallback.getCurrentPlayingItemPosition() != i) {
            this.mCallback.startPlayVoiceAndRefreshList(voiceMessage, i);
        } else {
            this.mCallback.stopPlayVoice();
        }
    }

    private void downloadAndPlayVoice(final VoiceMessage voiceMessage, final int i) {
        this.mCallback.setCurrentDownloadingItemPosition(i);
        MQDownloadManager.getInstance(getContext()).downloadVoice(voiceMessage.getUrl(), new MQDownloadManager.Callback() {
            public void onSuccess(File file) {
                MQBaseBubbleItem.this.mCallback.setVoiceMessageDuration(voiceMessage, file.getAbsolutePath());
                MQBaseBubbleItem.this.post(new Runnable() {
                    public void run() {
                        if (MQBaseBubbleItem.this.mCallback.getCurrentDownloadingItemPosition() == i) {
                            MQBaseBubbleItem.this.mCallback.startPlayVoiceAndRefreshList(voiceMessage, i);
                        }
                    }
                });
            }

            public void onFailure() {
                MQUtils.showSafe(MQBaseBubbleItem.this.getContext(), R.string.mq_download_audio_failure);
            }
        });
    }

    public void notifyDataSetChanged() {
        this.mCallback.notifyDataSetChanged();
    }

    public void onFileMessageDownloadFailure(FileMessage fileMessage, int i, String str) {
        this.mCallback.onFileMessageDownloadFailure(fileMessage, i, str);
    }

    public void onFileMessageExpired(FileMessage fileMessage) {
        this.mCallback.onFileMessageExpired(fileMessage);
    }
}
