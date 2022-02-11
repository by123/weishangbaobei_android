package com.wx.assistants.utils.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.fileutil.FileUtils;
import com.wx.assistants.utils.manager.AudioManager;
import com.yalantis.ucrop.view.CropImageView;

public class AudioRecordButton extends AppCompatButton implements AudioManager.AudioStageListener {
    private static final int DISTANCE_Y_CANCEL = 50;
    private static final int MSG_AUDIO_PREPARED = 272;
    private static final int MSG_DIALOG_DIMISS = 274;
    private static final int MSG_VOICE_CHANGE = 273;
    private static final int MSG_VOICE_STOP = 4;
    private static final int STATE_NORMAL = 1;
    private static final int STATE_RECORDING = 2;
    private static final int STATE_WANT_TO_CANCEL = 3;
    /* access modifiers changed from: private */
    public boolean isOverTime;
    /* access modifiers changed from: private */
    public boolean isRecording;
    boolean isShcok;
    /* access modifiers changed from: private */
    public AudioManager mAudioManager;
    Context mContext;
    private int mCurrentState;
    /* access modifiers changed from: private */
    public DialogManager mDialogManager;
    /* access modifiers changed from: private */
    public Runnable mGetVoiceLevelRunnable;
    private boolean mHasRecordPromission;
    /* access modifiers changed from: private */
    public AudioFinishRecorderListener mListener;
    /* access modifiers changed from: private */
    public int mMaxRecordTime;
    private boolean mReady;
    private int mRemainedTime;
    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    public final Handler mStateHandler;
    /* access modifiers changed from: private */
    public float mTime;
    private Vibrator vibrator;

    public interface AudioFinishRecorderListener {
        void onFinished(float f, String str);
    }

    public boolean isInEditMode() {
        return true;
    }

    public boolean onPreDraw() {
        return false;
    }

    public boolean isHasRecordPromission() {
        return this.mHasRecordPromission;
    }

    public void setHasRecordPromission(boolean z) {
        this.mHasRecordPromission = z;
    }

    public AudioRecordButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public AudioRecordButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentState = 1;
        this.isRecording = false;
        this.mTime = CropImageView.DEFAULT_ASPECT_RATIO;
        this.isOverTime = false;
        this.mMaxRecordTime = 90;
        this.mRemainedTime = 10;
        this.mHasRecordPromission = true;
        this.mGetVoiceLevelRunnable = new Runnable() {
            public void run() {
                while (AudioRecordButton.this.isRecording) {
                    try {
                        if (AudioRecordButton.this.mTime > ((float) AudioRecordButton.this.mMaxRecordTime)) {
                            AudioRecordButton.this.mStateHandler.sendEmptyMessage(4);
                            return;
                        }
                        Thread.sleep(100);
                        float unused = AudioRecordButton.this.mTime = AudioRecordButton.this.mTime + 0.1f;
                        AudioRecordButton.this.mStateHandler.sendEmptyMessage(273);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        this.mStateHandler = new Handler() {
            public void handleMessage(Message message) {
                int i = message.what;
                if (i != 4) {
                    switch (i) {
                        case AudioRecordButton.MSG_AUDIO_PREPARED /*272*/:
                            AudioRecordButton.this.mDialogManager.showRecordingDialog();
                            boolean unused = AudioRecordButton.this.isRecording = true;
                            new Thread(AudioRecordButton.this.mGetVoiceLevelRunnable).start();
                            return;
                        case 273:
                            AudioRecordButton.this.showRemainedTime();
                            AudioRecordButton.this.mDialogManager.updateVoiceLevel(AudioRecordButton.this.mAudioManager.getVoiceLevel(7));
                            return;
                        default:
                            return;
                    }
                } else {
                    boolean unused2 = AudioRecordButton.this.isOverTime = true;
                    AudioRecordButton.this.mDialogManager.dimissDialog();
                    AudioRecordButton.this.mAudioManager.release();
                    AudioRecordButton.this.mListener.onFinished(AudioRecordButton.this.mTime, AudioRecordButton.this.mAudioManager.getCurrentFilePath());
                    AudioRecordButton.this.reset();
                }
            }
        };
        this.mContext = context;
        this.mDialogManager = new DialogManager(getContext());
        this.mAudioManager = AudioManager.getInstance(FileUtils.getAppRecordDir(this.mContext).toString());
        this.mAudioManager.setOnAudioStageListener(this);
        setOnLongClickListener(new View.OnLongClickListener() {
            public final boolean onLongClick(View view) {
                return AudioRecordButton.lambda$new$0(AudioRecordButton.this, view);
            }
        });
    }

    public static /* synthetic */ boolean lambda$new$0(AudioRecordButton audioRecordButton, View view) {
        if (!audioRecordButton.isHasRecordPromission()) {
            return true;
        }
        audioRecordButton.mReady = true;
        audioRecordButton.mAudioManager.prepareAudio();
        audioRecordButton.changeState(2);
        return false;
    }

    public void setAudioFinishRecorderListener(AudioFinishRecorderListener audioFinishRecorderListener) {
        this.mListener = audioFinishRecorderListener;
    }

    /* access modifiers changed from: private */
    public void showRemainedTime() {
        int i = (int) (((float) this.mMaxRecordTime) - this.mTime);
        if (i < this.mRemainedTime) {
            if (!this.isShcok) {
                this.isShcok = true;
                doShock();
            }
            TextView tipsTxt = this.mDialogManager.getTipsTxt();
            tipsTxt.setText("还可以说 " + i + " 秒 ");
        }
    }

    private void doShock() {
        this.vibrator = (Vibrator) this.mContext.getSystemService("vibrator");
        this.vibrator.vibrate(new long[]{100, 400, 100, 400}, -1);
    }

    public void wellPrepared() {
        this.mStateHandler.sendEmptyMessage(MSG_AUDIO_PREPARED);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        switch (action) {
            case 1:
                if (this.mReady) {
                    if (!this.isRecording || this.mTime < 0.6f) {
                        this.mDialogManager.tooShort();
                        this.mAudioManager.cancel();
                        this.mStateHandler.sendEmptyMessageDelayed(MSG_DIALOG_DIMISS, 1200);
                    } else if (this.mCurrentState == 2) {
                        if (this.isOverTime) {
                            return AudioRecordButton.super.onTouchEvent(motionEvent);
                        }
                        this.mDialogManager.dimissDialog();
                        this.mAudioManager.release();
                        if (this.mListener != null) {
                            this.mListener.onFinished(this.mTime, this.mAudioManager.getCurrentFilePath());
                        }
                    } else if (this.mCurrentState == 3) {
                        this.mAudioManager.cancel();
                        this.mDialogManager.dimissDialog();
                    }
                    reset();
                    break;
                } else {
                    reset();
                    return AudioRecordButton.super.onTouchEvent(motionEvent);
                }
            case 2:
                if (this.isRecording) {
                    if (!wantToCancel(x, y)) {
                        LogUtils.log("WS_BABY_BBBBBBDDDDD");
                        if (!this.isOverTime) {
                            LogUtils.log("WS_BABY_EEEEEEE");
                        }
                        changeState(2);
                        break;
                    } else {
                        LogUtils.log("WS_BABY_BBBBBBCCCCC");
                        changeState(3);
                        break;
                    }
                }
                break;
        }
        getParent().requestDisallowInterceptTouchEvent(true);
        return AudioRecordButton.super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: private */
    public void reset() {
        this.isRecording = false;
        changeState(1);
        this.mReady = false;
        this.mTime = CropImageView.DEFAULT_ASPECT_RATIO;
        this.isOverTime = false;
        this.isShcok = false;
    }

    private boolean wantToCancel(int i, int i2) {
        if (i < 0 || i > getWidth() || i2 < -50 || i2 > getHeight() + 50) {
            return true;
        }
        return false;
    }

    private void changeState(int i) {
        if (this.mCurrentState != i) {
            this.mCurrentState = i;
            switch (this.mCurrentState) {
                case 1:
                    setText(this.mContext.getString(2131689620));
                    setBackgroundColor(Color.rgb(255, 255, 255));
                    return;
                case 2:
                    setBackgroundColor(Color.rgb(205, 205, 205));
                    setText(2131689615);
                    if (this.isRecording) {
                        this.mDialogManager.recording();
                        return;
                    }
                    return;
                case 3:
                    setText(2131689812);
                    this.mDialogManager.wantToCancel();
                    return;
                default:
                    return;
            }
        }
    }

    public int getMaxRecordTime() {
        return this.mMaxRecordTime;
    }

    public void setMaxRecordTime(int i) {
        this.mMaxRecordTime = i;
    }
}
