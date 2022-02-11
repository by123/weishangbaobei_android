package com.wx.assistants.utils.manager;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import com.umeng.commonsdk.proguard.e;
import java.io.IOException;

public class MediaCollectionManager {
    private static final String TAG = "MediaManager";
    private static boolean isPause;
    static AudioManager mAudioManager;
    private static Context mContext;
    /* access modifiers changed from: private */
    public static MediaPlayer mPlayer;
    static SensorManager mSensorManager;
    static Sensor sensor;

    public static void playSound(String str, MediaPlayer.OnCompletionListener onCompletionListener) {
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
            mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    MediaCollectionManager.mPlayer.reset();
                    return false;
                }
            });
        } else {
            mPlayer.reset();
        }
        try {
            mPlayer.setAudioStreamType(3);
            mPlayer.setOnCompletionListener(onCompletionListener);
            mPlayer.setDataSource(str);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e2) {
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    public static void playSound(String str, MediaPlayer.OnCompletionListener onCompletionListener, Context context) {
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
            mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    MediaCollectionManager.mPlayer.reset();
                    return false;
                }
            });
        } else {
            mPlayer.reset();
        }
        try {
            mPlayer.setAudioStreamType(3);
            mPlayer.setOnCompletionListener(onCompletionListener);
            mPlayer.setDataSource(str);
            mPlayer.prepare();
            mPlayer.start();
            initSensorListener(context);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e2) {
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    private static void initSensorListener(Context context) {
        mContext = context;
        mSensorManager = (SensorManager) context.getSystemService(e.aa);
        sensor = mSensorManager.getDefaultSensor(8);
        mSensorManager.registerListener(new SensorEventListener() {
            public void onAccuracyChanged(Sensor sensor, int i) {
            }

            public void onSensorChanged(SensorEvent sensorEvent) {
                try {
                    if (sensorEvent.values[0] >= MediaCollectionManager.sensor.getMaximumRange()) {
                        MediaCollectionManager.changeAdapterType(true);
                    } else {
                        MediaCollectionManager.changeAdapterType(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, sensor, 3);
    }

    public static void pause() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
            isPause = true;
        }
    }

    public static void resume() {
        if (mPlayer != null && isPause) {
            mPlayer.start();
            isPause = false;
        }
    }

    public static void release() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    /* access modifiers changed from: private */
    public static void changeAdapterType(boolean z) {
        ((Activity) mContext).setVolumeControlStream(1);
        pause();
        if (z) {
            mAudioManager.setMicrophoneMute(false);
            mAudioManager.setSpeakerphoneOn(true);
            mAudioManager.setMode(0);
            Log.e(TAG, "changeAdapterType: 当前为扩音模式");
        } else {
            mAudioManager.setSpeakerphoneOn(false);
            mAudioManager.setMicrophoneMute(true);
            mAudioManager.setMode(0);
            mAudioManager.setMode(3);
            Log.e(TAG, "changeAdapterType: 当前为耳麦模式");
        }
        resume();
    }
}
