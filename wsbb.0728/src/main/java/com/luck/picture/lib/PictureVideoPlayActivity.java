package com.luck.picture.lib;

import android.content.Context;
import android.content.ContextWrapper;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
import com.stub.StubApp;

public class PictureVideoPlayActivity extends PictureBaseActivity implements MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, View.OnClickListener {
    private ImageView iv_play;
    private MediaController mMediaController;
    private int mPositionWhenPaused = -1;
    /* access modifiers changed from: private */
    public VideoView mVideoView;
    private ImageView picture_left_back;
    private String video_path = "";

    static {
        StubApp.interface11(5613);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(new ContextWrapper(context) {
            public Object getSystemService(String str) {
                return "audio".equals(str) ? StubApp.getOrigApplicationContext(getApplicationContext()).getSystemService(str) : super.getSystemService(str);
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.picture_left_back) {
            finish();
        } else if (id == R.id.iv_play) {
            this.mVideoView.start();
            this.iv_play.setVisibility(4);
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if (this.iv_play != null) {
            this.iv_play.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.mMediaController = null;
        this.mVideoView = null;
        this.iv_play = null;
        super.onDestroy();
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        return false;
    }

    public void onPause() {
        this.mPositionWhenPaused = this.mVideoView.getCurrentPosition();
        this.mVideoView.stopPlayback();
        super.onPause();
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                if (i != 3) {
                    return false;
                }
                PictureVideoPlayActivity.this.mVideoView.setBackgroundColor(0);
                return true;
            }
        });
    }

    public void onResume() {
        if (this.mPositionWhenPaused >= 0) {
            this.mVideoView.seekTo(this.mPositionWhenPaused);
            this.mPositionWhenPaused = -1;
        }
        super.onResume();
    }

    public void onStart() {
        this.mVideoView.setVideoPath(this.video_path);
        this.mVideoView.start();
        super.onStart();
    }
}
