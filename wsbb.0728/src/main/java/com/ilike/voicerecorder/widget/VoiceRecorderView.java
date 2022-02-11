package com.ilike.voicerecorder.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ilike.voicerecorder.R;
import com.ilike.voicerecorder.core.VoiceRecorder;
import com.ilike.voicerecorder.utils.CommonUtils;
import com.yalantis.ucrop.view.CropImageView;

public class VoiceRecorderView extends RelativeLayout {
    protected Context context;
    protected LayoutInflater inflater;
    protected boolean isImagesCustom = false;
    protected ImageView micImage;
    protected Handler micImageHandler = new Handler() {
        public void handleMessage(Message message) {
            VoiceRecorderView.this.micImage.setImageDrawable(VoiceRecorderView.this.micImages[message.what]);
        }
    };
    protected Drawable[] micImages;
    protected String move_up_to_cancel = "";
    protected TextView recordingHint;
    protected String release_to_cancel = "";
    protected VoiceRecorder voiceRecorder;
    protected PowerManager.WakeLock wakeLock;

    public interface EaseVoiceRecorderCallback {
        void onVoiceRecordComplete(String str, int i);
    }

    public VoiceRecorderView(Context context2) {
        super(context2);
        init(context2);
    }

    public VoiceRecorderView(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        init(context2);
    }

    public VoiceRecorderView(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i);
        init(context2);
    }

    @SuppressLint({"InvalidWakeLockTag"})
    private void init(Context context2) {
        this.context = context2;
        LayoutInflater.from(context2).inflate(R.layout.ease_widget_voice_recorder, this);
        this.micImage = (ImageView) findViewById(R.id.mic_image);
        this.recordingHint = (TextView) findViewById(R.id.recording_hint);
        this.voiceRecorder = new VoiceRecorder(this.micImageHandler);
        this.micImages = new Drawable[]{getResources().getDrawable(R.drawable.ease_record_animate_01), getResources().getDrawable(R.drawable.ease_record_animate_02), getResources().getDrawable(R.drawable.ease_record_animate_03), getResources().getDrawable(R.drawable.ease_record_animate_04), getResources().getDrawable(R.drawable.ease_record_animate_05), getResources().getDrawable(R.drawable.ease_record_animate_06), getResources().getDrawable(R.drawable.ease_record_animate_07), getResources().getDrawable(R.drawable.ease_record_animate_08), getResources().getDrawable(R.drawable.ease_record_animate_09), getResources().getDrawable(R.drawable.ease_record_animate_10), getResources().getDrawable(R.drawable.ease_record_animate_11), getResources().getDrawable(R.drawable.ease_record_animate_12), getResources().getDrawable(R.drawable.ease_record_animate_13), getResources().getDrawable(R.drawable.ease_record_animate_14)};
        this.wakeLock = ((PowerManager) context2.getSystemService("power")).newWakeLock(6, "voice");
        this.release_to_cancel = context2.getString(R.string.release_to_cancel);
        this.move_up_to_cancel = context2.getString(R.string.move_up_to_cancel);
    }

    public void discardRecording() {
        if (this.wakeLock.isHeld()) {
            this.wakeLock.release();
        }
        try {
            if (this.voiceRecorder.isRecording()) {
                this.voiceRecorder.discardRecording();
                setVisibility(4);
            }
        } catch (Exception e) {
        }
    }

    public String getVoiceFileName() {
        return this.voiceRecorder.getVoiceFileName();
    }

    public String getVoiceFilePath() {
        return this.voiceRecorder.getVoiceFilePath();
    }

    public boolean isRecording() {
        return this.voiceRecorder.isRecording();
    }

    public boolean onPressToSpeakBtnTouch(View view, MotionEvent motionEvent, EaseVoiceRecorderCallback easeVoiceRecorderCallback) {
        switch (motionEvent.getAction()) {
            case 0:
                try {
                    view.setPressed(true);
                    startRecording();
                    return true;
                } catch (Exception e) {
                    view.setPressed(false);
                    return true;
                }
            case 1:
                view.setPressed(false);
                if (motionEvent.getY() < CropImageView.DEFAULT_ASPECT_RATIO) {
                    discardRecording();
                    return true;
                }
                try {
                    int stopRecoding = stopRecoding();
                    if (stopRecoding > 0) {
                        if (easeVoiceRecorderCallback != null) {
                            easeVoiceRecorderCallback.onVoiceRecordComplete(getVoiceFilePath(), stopRecoding);
                            return true;
                        }
                        return true;
                    } else if (stopRecoding == 401) {
                        Toast.makeText(this.context, R.string.Recording_without_permission, 0).show();
                        return true;
                    } else {
                        Toast.makeText(this.context, R.string.The_recording_time_is_too_short, 0).show();
                        return true;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            case 2:
                if (motionEvent.getY() < CropImageView.DEFAULT_ASPECT_RATIO) {
                    showReleaseToCancelHint();
                    return true;
                }
                showMoveUpToCancelHint();
                return true;
            default:
                discardRecording();
                return false;
        }
    }

    public void setCustomNamingFile(boolean z, String str) {
        this.voiceRecorder.isCustomNamingFile(z, str);
    }

    public void setDrawableAnimation(Drawable[] drawableArr) {
        this.micImages = null;
        this.micImages = drawableArr;
    }

    public void setShowMoveUpToCancelHint(String str) {
        this.move_up_to_cancel = str;
    }

    public void setShowReleaseToCancelHint(String str) {
        this.release_to_cancel = str;
    }

    public void showMoveUpToCancelHint() {
        this.recordingHint.setText(this.move_up_to_cancel);
        this.recordingHint.setBackgroundColor(0);
    }

    public void showReleaseToCancelHint() {
        this.recordingHint.setText(this.release_to_cancel);
        this.recordingHint.setBackgroundResource(R.drawable.ease_recording_text_hint_bg);
    }

    public void startRecording() {
        if (!CommonUtils.isSdcardExist()) {
            Toast.makeText(this.context, R.string.Send_voice_need_sdcard_support, 0).show();
            return;
        }
        try {
            this.wakeLock.acquire();
            setVisibility(0);
            this.recordingHint.setText(this.context.getString(R.string.move_up_to_cancel));
            this.recordingHint.setBackgroundColor(0);
            this.voiceRecorder.startRecording(this.context);
        } catch (Exception e) {
            e.printStackTrace();
            if (this.wakeLock.isHeld()) {
                this.wakeLock.release();
            }
            if (this.voiceRecorder != null) {
                this.voiceRecorder.discardRecording();
            }
            setVisibility(4);
            Toast.makeText(this.context, R.string.recoding_fail, 0).show();
        }
    }

    public int stopRecoding() {
        setVisibility(4);
        if (this.wakeLock.isHeld()) {
            this.wakeLock.release();
        }
        return this.voiceRecorder.stopRecoding();
    }
}
