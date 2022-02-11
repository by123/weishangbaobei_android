package com.vincent.videocompressor;

import android.os.AsyncTask;
import com.vincent.videocompressor.VideoController;

public class VideoCompress {
    private static final String TAG = "VideoCompress";

    public interface CompressListener {
        void onFail();

        void onProgress(float f);

        void onStart();

        void onSuccess();
    }

    public static VideoCompressTask compressVideoHigh(String str, String str2, CompressListener compressListener) {
        VideoCompressTask videoCompressTask = new VideoCompressTask(compressListener, 1);
        videoCompressTask.execute(new String[]{str, str2});
        return videoCompressTask;
    }

    public static VideoCompressTask compressVideoMedium(String str, String str2, CompressListener compressListener) {
        VideoCompressTask videoCompressTask = new VideoCompressTask(compressListener, 2);
        videoCompressTask.execute(new String[]{str, str2});
        return videoCompressTask;
    }

    public static VideoCompressTask compressVideoLow(String str, String str2, CompressListener compressListener) {
        VideoCompressTask videoCompressTask = new VideoCompressTask(compressListener, 3);
        videoCompressTask.execute(new String[]{str, str2});
        return videoCompressTask;
    }

    private static class VideoCompressTask extends AsyncTask<String, Float, Boolean> {
        private CompressListener mListener;
        private int mQuality;

        public VideoCompressTask(CompressListener compressListener, int i) {
            this.mListener = compressListener;
            this.mQuality = i;
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            if (this.mListener != null) {
                this.mListener.onStart();
            }
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(String... strArr) {
            return Boolean.valueOf(VideoController.getInstance().convertVideo(strArr[0], strArr[1], this.mQuality, new VideoController.CompressProgressListener() {
                public void onProgress(float f) {
                    VideoCompressTask.this.publishProgress(new Float[]{Float.valueOf(f)});
                }
            }));
        }

        /* access modifiers changed from: protected */
        public void onProgressUpdate(Float... fArr) {
            super.onProgressUpdate(fArr);
            if (this.mListener != null) {
                this.mListener.onProgress(fArr[0].floatValue());
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            if (this.mListener == null) {
                return;
            }
            if (bool.booleanValue()) {
                this.mListener.onSuccess();
            } else {
                this.mListener.onFail();
            }
        }
    }
}
