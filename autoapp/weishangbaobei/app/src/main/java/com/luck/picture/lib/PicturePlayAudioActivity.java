package com.luck.picture.lib;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import com.luck.picture.lib.tools.DateUtils;
import com.stub.StubApp;

public class PicturePlayAudioActivity extends PictureBaseActivity implements View.OnClickListener {
    /* access modifiers changed from: private */
    public String audio_path;
    public Handler handler = new Handler();
    private boolean isPlayAudio = false;
    /* access modifiers changed from: private */
    public MediaPlayer mediaPlayer;
    /* access modifiers changed from: private */
    public SeekBar musicSeekBar;
    public Runnable runnable = new Runnable() {
        public void run() {
            try {
                if (PicturePlayAudioActivity.this.mediaPlayer != null) {
                    PicturePlayAudioActivity.this.tv_musicTime.setText(DateUtils.timeParse((long) PicturePlayAudioActivity.this.mediaPlayer.getCurrentPosition()));
                    PicturePlayAudioActivity.this.musicSeekBar.setProgress(PicturePlayAudioActivity.this.mediaPlayer.getCurrentPosition());
                    PicturePlayAudioActivity.this.musicSeekBar.setMax(PicturePlayAudioActivity.this.mediaPlayer.getDuration());
                    PicturePlayAudioActivity.this.tv_musicTotal.setText(DateUtils.timeParse((long) PicturePlayAudioActivity.this.mediaPlayer.getDuration()));
                    PicturePlayAudioActivity.this.handler.postDelayed(PicturePlayAudioActivity.this.runnable, 200);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private TextView tv_PlayPause;
    private TextView tv_Quit;
    private TextView tv_Stop;
    private TextView tv_musicStatus;
    /* access modifiers changed from: private */
    public TextView tv_musicTime;
    /* access modifiers changed from: private */
    public TextView tv_musicTotal;

    static {
        StubApp.interface11(5585);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: private */
    public void initPlayer(String str) {
        this.mediaPlayer = new MediaPlayer();
        try {
            this.mediaPlayer.setDataSource(str);
            this.mediaPlayer.prepare();
            this.mediaPlayer.setLooping(true);
            playAudio();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_PlayPause) {
            playAudio();
        }
        if (id == R.id.tv_Stop) {
            this.tv_musicStatus.setText(getString(R.string.picture_stop_audio));
            this.tv_PlayPause.setText(getString(R.string.picture_play_audio));
            stop(this.audio_path);
        }
        if (id == R.id.tv_Quit) {
            this.handler.removeCallbacks(this.runnable);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    PicturePlayAudioActivity.this.stop(PicturePlayAudioActivity.this.audio_path);
                }
            }, 30);
            try {
                closeActivity();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void playAudio() {
        if (this.mediaPlayer != null) {
            this.musicSeekBar.setProgress(this.mediaPlayer.getCurrentPosition());
            this.musicSeekBar.setMax(this.mediaPlayer.getDuration());
        }
        if (this.tv_PlayPause.getText().toString().equals(getString(R.string.picture_play_audio))) {
            this.tv_PlayPause.setText(getString(R.string.picture_pause_audio));
            this.tv_musicStatus.setText(getString(R.string.picture_play_audio));
            playOrPause();
        } else {
            this.tv_PlayPause.setText(getString(R.string.picture_play_audio));
            this.tv_musicStatus.setText(getString(R.string.picture_pause_audio));
            playOrPause();
        }
        if (!this.isPlayAudio) {
            this.handler.post(this.runnable);
            this.isPlayAudio = true;
        }
    }

    public void stop(String str) {
        if (this.mediaPlayer != null) {
            try {
                this.mediaPlayer.stop();
                this.mediaPlayer.reset();
                this.mediaPlayer.setDataSource(str);
                this.mediaPlayer.prepare();
                this.mediaPlayer.seekTo(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void playOrPause() {
        try {
            if (this.mediaPlayer == null) {
                return;
            }
            if (this.mediaPlayer.isPlaying()) {
                this.mediaPlayer.pause();
            } else {
                this.mediaPlayer.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        closeActivity();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.mediaPlayer != null && this.handler != null) {
            this.handler.removeCallbacks(this.runnable);
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
    }
}
