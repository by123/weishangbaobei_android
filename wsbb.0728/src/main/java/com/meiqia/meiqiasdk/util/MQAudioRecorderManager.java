package com.meiqia.meiqiasdk.util;

import android.content.Context;
import android.media.MediaRecorder;
import android.support.annotation.Nullable;
import com.stub.StubApp;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.util.UUID;

public class MQAudioRecorderManager {
    private Callback mCallback;
    private Context mContext;
    private File mCurrentFile;
    private boolean mIsPrepared;
    private MediaRecorder mMediaRecorder;

    public interface Callback {
        void onAudioRecorderNoPermission();

        void wellPrepared();
    }

    public MQAudioRecorderManager(Context context, Callback callback) {
        this.mContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.mCallback = callback;
    }

    public static File getCachedVoiceFileByUrl(Context context, String str) {
        return new File(getVoiceCacheDir(context), str.substring(str.lastIndexOf("/") + 1));
    }

    public static File getVoiceCacheDir(Context context) {
        File file = new File(context.getExternalCacheDir(), "voice");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static String renameVoiceFilename(Context context, String str, String str2) {
        File file = new File(str);
        File file2 = new File(getVoiceCacheDir(context), str2.replace("audio/", ""));
        file.renameTo(file2);
        return file2.getAbsolutePath();
    }

    public void cancel() {
        release();
        if (this.mCurrentFile != null) {
            this.mCurrentFile.delete();
            this.mCurrentFile = null;
        }
    }

    @Nullable
    public String getCurrenFilePath() {
        if (this.mCurrentFile == null) {
            return null;
        }
        return this.mCurrentFile.getAbsolutePath();
    }

    public int getVoiceLevel(int i) {
        if (!this.mIsPrepared) {
            return 1;
        }
        try {
            return Math.max(Math.min(((int) (Math.log10((double) (this.mMediaRecorder.getMaxAmplitude() / CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION)) * 25.0d)) / 4, i), 1);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public void prepareAudio() {
        try {
            this.mCurrentFile = new File(getVoiceCacheDir(this.mContext), UUID.randomUUID().toString());
            this.mMediaRecorder = new MediaRecorder();
            this.mMediaRecorder.setOutputFile(this.mCurrentFile.getAbsolutePath());
            this.mMediaRecorder.setAudioSource(1);
            this.mMediaRecorder.setOutputFormat(3);
            this.mMediaRecorder.setAudioEncoder(1);
            this.mMediaRecorder.prepare();
            this.mMediaRecorder.start();
            this.mIsPrepared = true;
            if (this.mCallback != null) {
                this.mCallback.wellPrepared();
            }
        } catch (Exception e) {
            if (this.mCallback != null) {
                this.mCallback.onAudioRecorderNoPermission();
            }
        }
    }

    public void release() {
        try {
            if (this.mMediaRecorder != null) {
                this.mMediaRecorder.stop();
                this.mMediaRecorder.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            this.mMediaRecorder = null;
            throw th;
        }
        this.mMediaRecorder = null;
    }
}
