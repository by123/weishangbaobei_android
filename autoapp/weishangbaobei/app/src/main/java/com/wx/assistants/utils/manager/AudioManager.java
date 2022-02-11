package com.wx.assistants.utils.manager;

import android.media.MediaRecorder;
import android.util.Log;
import android.view.Surface;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class AudioManager {
    private static AudioManager mInstance;
    private boolean isPrepared;
    private String mCurrentFilePathString;
    private String mDirString;
    public AudioStageListener mListener;
    private MediaRecorder mRecorder;

    public interface AudioStageListener {
        void wellPrepared();
    }

    private AudioManager(String str) {
        this.mDirString = str;
    }

    public static AudioManager getInstance(String str) {
        if (mInstance == null) {
            synchronized (AudioManager.class) {
                if (mInstance == null) {
                    mInstance = new AudioManager(str);
                }
            }
        }
        return mInstance;
    }

    public void setOnAudioStageListener(AudioStageListener audioStageListener) {
        this.mListener = audioStageListener;
    }

    public void prepareAudio() {
        try {
            this.isPrepared = false;
            File file = new File(this.mDirString);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, generalFileName());
            this.mCurrentFilePathString = file2.getAbsolutePath();
            this.mRecorder = new MediaRecorder();
            this.mRecorder.setOutputFile(file2.getAbsolutePath());
            this.mRecorder.setAudioSource(1);
            this.mRecorder.setOutputFormat(0);
            this.mRecorder.setAudioEncoder(3);
            this.mRecorder.prepare();
            this.mRecorder.start();
            this.isPrepared = true;
            if (this.mListener != null) {
                this.mListener.wellPrepared();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private String generalFileName() {
        return UUID.randomUUID().toString() + ".amr";
    }

    public int getVoiceLevel(int i) {
        if (this.isPrepared) {
            try {
                return ((i * this.mRecorder.getMaxAmplitude()) / 32768) + 1;
            } catch (Exception unused) {
            }
        }
        return 1;
    }

    public void release() {
        if (this.mRecorder != null) {
            try {
                this.mRecorder.setOnErrorListener((MediaRecorder.OnErrorListener) null);
                this.mRecorder.setOnInfoListener((MediaRecorder.OnInfoListener) null);
                this.mRecorder.setPreviewDisplay((Surface) null);
                this.mRecorder.stop();
            } catch (IllegalStateException e) {
                Log.i("Exception", Log.getStackTraceString(e) + "123");
            } catch (RuntimeException e2) {
                Log.i("Exception", Log.getStackTraceString(e2) + "123");
            } catch (Exception e3) {
                Log.i("Exception", Log.getStackTraceString(e3) + "123");
            }
            this.mRecorder.release();
            this.mRecorder = null;
        }
    }

    public void cancel() {
        release();
        if (this.mCurrentFilePathString != null) {
            new File(this.mCurrentFilePathString).delete();
            this.mCurrentFilePathString = null;
        }
    }

    public String getCurrentFilePath() {
        return this.mCurrentFilePathString;
    }
}
