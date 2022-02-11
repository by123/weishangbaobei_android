package com.ilike.voicerecorder.widget;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.ilike.voicerecorder.R;
import com.ilike.voicerecorder.utils.VoiceProvider;
import java.io.File;

public class VoicePlayClickListener implements View.OnClickListener {
    private static final String TAG = "VoicePlayClickListener";
    public static VoicePlayClickListener currentPlayListener = null;
    public static String getLocalUrl = "";
    public static boolean isPlaying = false;
    public static String playMsgId = "";
    Activity activity;
    private BaseAdapter adapter;
    private boolean isSetingPlayingIcon = false;
    MediaPlayer mediaPlayer = null;
    private int stopPlayingDrawableId = 0;
    private AnimationDrawable voiceAnimation = null;
    ImageView voiceIconView;

    public VoicePlayClickListener(ImageView imageView, Activity activity2) {
        this.activity = activity2;
        this.voiceIconView = imageView;
    }

    private void showAnimation() {
        if (!this.isSetingPlayingIcon) {
            this.voiceIconView.setImageResource(R.drawable.voice_to_icon);
        }
        this.voiceAnimation = (AnimationDrawable) this.voiceIconView.getDrawable();
        this.voiceAnimation.start();
    }

    public void onClick(View view) {
        this.activity.getResources().getString(R.string.Is_download_voice_click_later);
        if (isPlaying) {
            if (playMsgId != null) {
                currentPlayListener.stopPlayVoice();
                return;
            }
            currentPlayListener.stopPlayVoice();
        }
        File file = new File(getLocalUrl);
        if (!file.exists() || !file.isFile()) {
            Log.e(TAG, "file not exist");
        }
    }

    public void playUrlVoice(String str) {
        getLocalUrl = str;
        if (isPlaying && playMsgId != null) {
            currentPlayListener.stopPlayVoice();
        }
        playMsgId = str;
        AudioManager audioManager = (AudioManager) this.activity.getSystemService("audio");
        this.mediaPlayer = new MediaPlayer();
        if (VoiceProvider.getInstance().getSettingsProvider().isSpeakerOpened()) {
            audioManager.setMode(0);
            audioManager.setSpeakerphoneOn(true);
            this.mediaPlayer.setAudioStreamType(2);
        } else {
            audioManager.setSpeakerphoneOn(false);
            audioManager.setMode(2);
            this.mediaPlayer.setAudioStreamType(0);
        }
        try {
            this.mediaPlayer.setDataSource(str);
            this.mediaPlayer.prepare();
            this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    VoicePlayClickListener.this.mediaPlayer.release();
                    VoicePlayClickListener.this.mediaPlayer = null;
                    VoicePlayClickListener.this.stopPlayVoice();
                }
            });
            isPlaying = true;
            currentPlayListener = this;
            this.mediaPlayer.start();
            showAnimation();
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }
    }

    public void playVoice(String str) {
        getLocalUrl = str;
        if (isPlaying && playMsgId != null) {
            currentPlayListener.stopPlayVoice();
        }
        if (new File(str).exists()) {
            playMsgId = str;
            AudioManager audioManager = (AudioManager) this.activity.getSystemService("audio");
            this.mediaPlayer = new MediaPlayer();
            if (VoiceProvider.getInstance().getSettingsProvider().isSpeakerOpened()) {
                audioManager.setMode(0);
                audioManager.setSpeakerphoneOn(true);
                this.mediaPlayer.setAudioStreamType(2);
            } else {
                audioManager.setSpeakerphoneOn(false);
                audioManager.setMode(2);
                this.mediaPlayer.setAudioStreamType(0);
            }
            try {
                this.mediaPlayer.setDataSource(str);
                this.mediaPlayer.prepare();
                this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        VoicePlayClickListener.this.mediaPlayer.release();
                        VoicePlayClickListener.this.mediaPlayer = null;
                        VoicePlayClickListener.this.stopPlayVoice();
                    }
                });
                isPlaying = true;
                currentPlayListener = this;
                this.mediaPlayer.start();
                showAnimation();
            } catch (Exception e) {
                Log.e("Exception", e.getMessage());
            }
        }
    }

    public void setPlayingIconDrawableResoure(int i) {
        this.isSetingPlayingIcon = true;
        this.voiceIconView.setImageResource(R.drawable.voice_to_icon);
    }

    public void setStopPlayIcon(int i) {
        this.stopPlayingDrawableId = i;
    }

    public void stopPlayVoice() {
        this.voiceAnimation.stop();
        if (this.stopPlayingDrawableId != 0) {
            this.voiceIconView.setImageResource(this.stopPlayingDrawableId);
        } else {
            this.voiceIconView.setImageResource(R.drawable.ease_chatto_voice_playing);
        }
        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
            this.mediaPlayer.release();
        }
        isPlaying = false;
        playMsgId = null;
    }
}
