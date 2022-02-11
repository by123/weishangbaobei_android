package com.meiqia.meiqiasdk.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.LevelListDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.util.MQAudioPlayerManager;
import com.meiqia.meiqiasdk.util.MQAudioRecorderManager;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;

public class MQRecorderKeyboardLayout extends MQBaseCustomCompositeView implements MQAudioRecorderManager.Callback, View.OnTouchListener {
    private static final int RECORDER_MAX_TIME = 60;
    private static final int STATE_NORMAL = 1;
    private static final int STATE_RECORDING = 2;
    private static final int STATE_WANT_CANCEL = 3;
    private static final int VOICE_LEVEL_COUNT = 9;
    /* access modifiers changed from: private */
    public ImageView mAnimIv;
    /* access modifiers changed from: private */
    public MQAudioRecorderManager mAudioRecorderManager;
    /* access modifiers changed from: private */
    public Callback mCallback;
    /* access modifiers changed from: private */
    public int mCurrentState = 1;
    private int mDistanceCancel;
    private Runnable mGetVoiceLevelRunnable = new Runnable() {
        public void run() {
            while (MQRecorderKeyboardLayout.this.mIsRecording) {
                try {
                    Thread.sleep(100);
                    float unused = MQRecorderKeyboardLayout.this.mTime = MQRecorderKeyboardLayout.this.mTime + 0.1f;
                    if (MQRecorderKeyboardLayout.this.mTime <= 60.0f) {
                        MQRecorderKeyboardLayout.this.refreshVoiceLevel();
                    } else {
                        boolean unused2 = MQRecorderKeyboardLayout.this.mIsOvertime = true;
                        MQRecorderKeyboardLayout.this.handleActionUp();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean mHasPermission = false;
    /* access modifiers changed from: private */
    public boolean mIsOvertime = false;
    /* access modifiers changed from: private */
    public boolean mIsRecording;
    /* access modifiers changed from: private */
    public long mLastTipTooShortTime;
    /* access modifiers changed from: private */
    public TextView mStatusTv;
    /* access modifiers changed from: private */
    public float mTime;

    public interface Callback {
        void onAudioRecorderFinish(int i, String str);

        void onAudioRecorderNoPermission();

        void onAudioRecorderTooShort();
    }

    public MQRecorderKeyboardLayout(Context context) {
        super(context);
    }

    public MQRecorderKeyboardLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MQRecorderKeyboardLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void changeState(int i) {
        if (this.mCurrentState != i) {
            this.mCurrentState = i;
            switch (this.mCurrentState) {
                case 1:
                    this.mStatusTv.setText(R.string.mq_audio_status_normal);
                    this.mAnimIv.setImageLevel(1);
                    return;
                case 2:
                    this.mStatusTv.setText(R.string.mq_audio_status_recording);
                    return;
                case 3:
                    this.mStatusTv.setText(R.string.mq_audio_status_want_cancel);
                    this.mAnimIv.setImageLevel(10);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void endRecorder() {
        this.mAudioRecorderManager.release();
        if (this.mCallback != null) {
            String currenFilePath = this.mAudioRecorderManager.getCurrenFilePath();
            if (!TextUtils.isEmpty(currenFilePath)) {
                File file = new File(currenFilePath);
                if (!file.exists() || file.length() <= 6) {
                    this.mAudioRecorderManager.cancel();
                    this.mCallback.onAudioRecorderNoPermission();
                    return;
                }
                this.mCallback.onAudioRecorderFinish(MQAudioPlayerManager.getDurationByFilePath(getContext(), file.getAbsolutePath()), file.getAbsolutePath());
            }
        }
    }

    /* access modifiers changed from: private */
    public void handleActionUp() {
        post(new Runnable() {
            public void run() {
                if (MQRecorderKeyboardLayout.this.mIsOvertime || !MQRecorderKeyboardLayout.this.mHasPermission) {
                    if (MQRecorderKeyboardLayout.this.mIsRecording) {
                        MQRecorderKeyboardLayout.this.endRecorder();
                    }
                } else if (!MQRecorderKeyboardLayout.this.mIsRecording || MQRecorderKeyboardLayout.this.mTime < 1.0f) {
                    MQRecorderKeyboardLayout.this.mAudioRecorderManager.cancel();
                    if (System.currentTimeMillis() - MQRecorderKeyboardLayout.this.mLastTipTooShortTime > 1000) {
                        long unused = MQRecorderKeyboardLayout.this.mLastTipTooShortTime = System.currentTimeMillis();
                        MQRecorderKeyboardLayout.this.mCallback.onAudioRecorderTooShort();
                    }
                } else if (MQRecorderKeyboardLayout.this.mCurrentState == 2) {
                    MQRecorderKeyboardLayout.this.endRecorder();
                } else if (MQRecorderKeyboardLayout.this.mCurrentState == 3) {
                    MQRecorderKeyboardLayout.this.mAudioRecorderManager.cancel();
                }
                MQRecorderKeyboardLayout.this.reset();
            }
        });
    }

    private void initLevelListDrawable() {
        LevelListDrawable levelListDrawable = new LevelListDrawable();
        int i = 0;
        while (i < 9) {
            Resources resources = getContext().getResources();
            StringBuilder sb = new StringBuilder();
            sb.append("mq_voice_level");
            int i2 = i + 1;
            sb.append(i2);
            try {
                levelListDrawable.addLevel(i, i2, MQUtils.tintDrawable(getContext(), getResources().getDrawable(resources.getIdentifier(sb.toString(), "drawable", getContext().getPackageName())), R.color.mq_chat_audio_recorder_icon));
            } catch (Resources.NotFoundException e) {
            }
            i = i2;
        }
        levelListDrawable.addLevel(9, 10, getResources().getDrawable(R.drawable.mq_voice_want_cancel));
        this.mAnimIv.setImageDrawable(levelListDrawable);
    }

    private boolean isWantCancel(int i, int i2) {
        return i2 < (-this.mDistanceCancel);
    }

    /* access modifiers changed from: private */
    public void refreshVoiceLevel() {
        post(new Runnable() {
            public void run() {
                if (MQRecorderKeyboardLayout.this.mCurrentState == 2) {
                    MQRecorderKeyboardLayout.this.mAnimIv.setImageLevel(MQRecorderKeyboardLayout.this.mAudioRecorderManager.getVoiceLevel(9));
                    int round = Math.round(60.0f - MQRecorderKeyboardLayout.this.mTime);
                    if (round <= 10) {
                        MQRecorderKeyboardLayout.this.mStatusTv.setText(MQRecorderKeyboardLayout.this.getContext().getString(R.string.mq_recorder_remaining_time, new Object[]{Integer.valueOf(round)}));
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void reset() {
        this.mIsRecording = false;
        this.mHasPermission = false;
        this.mTime = CropImageView.DEFAULT_ASPECT_RATIO;
        changeState(1);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.mq_layout_recorder_keyboard;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.mStatusTv = (TextView) getViewById(R.id.tv_recorder_keyboard_status);
        this.mAnimIv = (ImageView) getViewById(R.id.iv_recorder_keyboard_anim);
    }

    public boolean isRecording() {
        return this.mCurrentState != 1;
    }

    public void onAudioRecorderNoPermission() {
        endRecorder();
        reset();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                this.mIsOvertime = false;
                this.mHasPermission = true;
                changeState(2);
                this.mAudioRecorderManager.prepareAudio();
                break;
            case 1:
                handleActionUp();
                break;
            case 2:
                if (!this.mIsOvertime && this.mIsRecording && this.mHasPermission) {
                    if (!isWantCancel(x, y)) {
                        changeState(2);
                        break;
                    } else {
                        changeState(3);
                        break;
                    }
                }
            case 3:
                this.mAudioRecorderManager.cancel();
                reset();
                break;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void processLogic() {
        initLevelListDrawable();
        this.mDistanceCancel = MQUtils.dip2px(getContext(), 10.0f);
        this.mAudioRecorderManager = new MQAudioRecorderManager(getContext(), this);
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    /* access modifiers changed from: protected */
    public void setListener() {
        this.mAnimIv.setOnTouchListener(this);
    }

    public void wellPrepared() {
        this.mIsRecording = true;
        new Thread(this.mGetVoiceLevelRunnable).start();
    }
}
