package com.meiqia.meiqiasdk.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import com.umeng.socialize.common.SocializeConstants;
import java.io.IOException;

public class MQAudioPlayerManager {
    private static boolean sIsPause;
    /* access modifiers changed from: private */
    public static MediaPlayer sMediaPlayer;

    public interface Callback {
        void onCompletion();

        void onError();
    }

    public static void playSound(String str, final Callback callback) {
        try {
            if (sMediaPlayer == null) {
                sMediaPlayer = new MediaPlayer();
            } else {
                sMediaPlayer.reset();
            }
            sMediaPlayer.setAudioStreamType(3);
            sMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if (callback != null) {
                        callback.onCompletion();
                    }
                }
            });
            sMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    MQAudioPlayerManager.sMediaPlayer.reset();
                    if (callback == null) {
                        return false;
                    }
                    callback.onError();
                    return false;
                }
            });
            sMediaPlayer.setDataSource(str);
            sMediaPlayer.prepare();
            sMediaPlayer.start();
        } catch (IOException unused) {
            if (callback != null) {
                callback.onError();
            }
        }
    }

    public static void resume() {
        if (sMediaPlayer != null && sIsPause) {
            sMediaPlayer.start();
            sIsPause = false;
        }
    }

    public static void pause() {
        if (isPlaying()) {
            sMediaPlayer.pause();
            sIsPause = true;
        }
    }

    public static void stop() {
        if (isPlaying()) {
            sMediaPlayer.stop();
        }
    }

    public static boolean isPlaying() {
        return sMediaPlayer != null && sMediaPlayer.isPlaying();
    }

    public static void release() {
        stop();
        if (sMediaPlayer != null) {
            sMediaPlayer.release();
            sMediaPlayer = null;
        }
    }

    public static int getCurrentPosition() {
        if (!isPlaying()) {
            return 0;
        }
        int currentPosition = sMediaPlayer.getCurrentPosition();
        if (currentPosition == 0) {
            return 1;
        }
        return currentPosition;
    }

    public static int getCurrentDuration() {
        if (!isPlaying()) {
            return 0;
        }
        int duration = sMediaPlayer.getDuration();
        if (duration == 0) {
            return 1;
        }
        return duration;
    }

    public static int getDurationByFilePath(Context context, String str) {
        try {
            int duration = MediaPlayer.create(context, Uri.parse(str)).getDuration() / SocializeConstants.CANCLE_RESULTCODE;
            if (duration == 0) {
                return 1;
            }
            return duration;
        } catch (Exception unused) {
            return 0;
        }
    }
}
