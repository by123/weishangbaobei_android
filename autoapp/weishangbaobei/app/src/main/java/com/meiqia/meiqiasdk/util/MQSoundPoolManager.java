package com.meiqia.meiqiasdk.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.RawRes;
import com.stub.StubApp;
import java.util.HashMap;
import java.util.Map;

public class MQSoundPoolManager {
    private static final int SOUND_INTERNAL_TIME = 500;
    private static final int STREAMS_COUNT = 1;
    private AudioManager mAudioManager;
    private Context mContext;
    private long mPrePlayTime = 0;
    private SoundPool mSoundPool;
    /* access modifiers changed from: private */
    public Map<Integer, Integer> mSoundSourceMap;

    public static MQSoundPoolManager getInstance(Context context) {
        return new MQSoundPoolManager(StubApp.getOrigApplicationContext(context.getApplicationContext()));
    }

    private MQSoundPoolManager(Context context) {
        this.mContext = context;
        if (Build.VERSION.SDK_INT >= 21) {
            this.mSoundPool = new SoundPool.Builder().setMaxStreams(1).build();
        } else {
            this.mSoundPool = new SoundPool(1, 3, 0);
        }
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mSoundSourceMap = new HashMap();
    }

    public void playSound(@RawRes final int i) {
        if (this.mSoundSourceMap != null && !isSilentMode()) {
            if (!this.mSoundSourceMap.containsKey(Integer.valueOf(i))) {
                this.mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    public void onLoadComplete(SoundPool soundPool, int i, int i2) {
                        if (i2 == 0) {
                            MQSoundPoolManager.this.mSoundSourceMap.put(Integer.valueOf(i), Integer.valueOf(i));
                            MQSoundPoolManager.this.play(i);
                        }
                    }
                });
                this.mSoundPool.load(StubApp.getOrigApplicationContext(this.mContext.getApplicationContext()), i, 1);
                return;
            }
            play(this.mSoundSourceMap.get(Integer.valueOf(i)).intValue());
        }
    }

    /* access modifiers changed from: private */
    public void play(int i) {
        if (!isPlaying() && this.mAudioManager.getRingerMode() != 0) {
            this.mSoundPool.stop(i);
            this.mSoundPool.play(i, 1.0f, 1.0f, 0, 0, 1.0f);
        }
    }

    public void release() {
        this.mSoundPool.release();
        this.mSoundPool = null;
        this.mAudioManager = null;
        this.mContext = null;
        this.mSoundSourceMap = null;
    }

    private boolean isPlaying() {
        if (System.currentTimeMillis() - this.mPrePlayTime <= 500) {
            return true;
        }
        this.mPrePlayTime = System.currentTimeMillis();
        return false;
    }

    private boolean isSilentMode() {
        return ((AudioManager) this.mContext.getSystemService("audio")).getRingerMode() != 2;
    }
}
