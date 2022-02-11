package com.ilike.voicerecorder.core;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import com.ilike.voicerecorder.utils.EMError;
import com.ilike.voicerecorder.utils.PathUtil;
import com.umeng.socialize.common.SocializeConstants;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class VoiceRecorder {
    static final String EXTENSION = ".amr";
    static final String PREFIX = "voice";
    private File file;
    /* access modifiers changed from: private */
    public Handler handler;
    private boolean isCustomNamingFile = false;
    /* access modifiers changed from: private */
    public boolean isRecording = false;
    MediaRecorder recorder;
    private long startTime;
    private String voiceFileName = null;
    private String voiceFilePath = null;

    public VoiceRecorder(Handler handler2) {
        this.handler = handler2;
    }

    public String startRecording(Context context) {
        this.file = null;
        try {
            if (this.recorder != null) {
                this.recorder.release();
                this.recorder = null;
            }
            this.recorder = new MediaRecorder();
            this.recorder.setAudioSource(1);
            this.recorder.setOutputFormat(3);
            this.recorder.setAudioEncoder(1);
            this.recorder.setAudioChannels(1);
            this.recorder.setAudioSamplingRate(8000);
            this.recorder.setAudioEncodingBitRate(64);
            if (!this.isCustomNamingFile) {
                this.voiceFileName = getVoiceFileName(System.currentTimeMillis() + "");
            }
            if (!isDirExist()) {
                PathUtil.getInstance().createDirs("chat", PREFIX, context);
            }
            this.voiceFilePath = PathUtil.getInstance().getVoicePath() + "/" + this.voiceFileName;
            this.file = new File(this.voiceFilePath);
            this.recorder.setOutputFile(this.file.getAbsolutePath());
            this.recorder.prepare();
            this.isRecording = true;
            this.recorder.start();
        } catch (IOException unused) {
            Log.e(PREFIX, "prepare() failed");
        }
        new Thread(new Runnable() {
            public void run() {
                while (VoiceRecorder.this.isRecording) {
                    try {
                        Message message = new Message();
                        message.what = (VoiceRecorder.this.recorder.getMaxAmplitude() * 13) / 32767;
                        VoiceRecorder.this.handler.sendMessage(message);
                        SystemClock.sleep(100);
                    } catch (Exception e) {
                        Log.e(VoiceRecorder.PREFIX, e.toString());
                        return;
                    }
                }
            }
        }).start();
        this.startTime = new Date().getTime();
        Log.d(PREFIX, "start voice recording to file:" + this.file.getAbsolutePath());
        if (this.file == null) {
            return null;
        }
        return this.file.getAbsolutePath();
    }

    public void discardRecording() {
        if (this.recorder != null) {
            try {
                this.recorder.stop();
                this.recorder.release();
                this.recorder = null;
                if (this.file != null && this.file.exists() && !this.file.isDirectory()) {
                    this.file.delete();
                }
            } catch (IllegalStateException | RuntimeException unused) {
            }
            this.isRecording = false;
        }
    }

    public int stopRecoding() {
        if (this.recorder == null) {
            return 0;
        }
        this.isRecording = false;
        this.recorder.stop();
        this.recorder.release();
        this.recorder = null;
        if (this.file == null || !this.file.exists() || !this.file.isFile()) {
            return EMError.FILE_INVALID;
        }
        if (this.file.length() == 0) {
            this.file.delete();
            return EMError.FILE_INVALID;
        }
        int time = ((int) (new Date().getTime() - this.startTime)) / SocializeConstants.CANCLE_RESULTCODE;
        Log.d(PREFIX, "voice recording finished. seconds:" + time + " file length:" + this.file.length());
        return time;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        if (this.recorder != null) {
            this.recorder.release();
        }
    }

    private String getVoiceFileName(String str) {
        Time time = new Time();
        time.setToNow();
        return str + time.toString().substring(0, 15) + EXTENSION;
    }

    public boolean isRecording() {
        return this.isRecording;
    }

    public String getVoiceFilePath() {
        return this.voiceFilePath;
    }

    public String getVoiceFileName() {
        return this.voiceFileName;
    }

    public boolean isDirExist() {
        return !TextUtils.isEmpty(PathUtil.pathPrefix);
    }

    public void isCustomNamingFile(boolean z, String str) {
        if (z) {
            this.isCustomNamingFile = z;
            setVoiceFileName(str);
        }
    }

    public void setVoiceFileName(String str) {
        this.voiceFileName = str + EXTENSION;
    }
}
