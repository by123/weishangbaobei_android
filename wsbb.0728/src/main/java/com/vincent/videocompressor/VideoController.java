package com.vincent.videocompressor;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.util.Log;
import android.view.Surface;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@SuppressLint({"NewApi"})
public class VideoController {
    static final int COMPRESS_QUALITY_HIGH = 1;
    static final int COMPRESS_QUALITY_LOW = 3;
    static final int COMPRESS_QUALITY_MEDIUM = 2;
    private static volatile VideoController Instance = null;
    public static final String MIME_TYPE = "video/avc";
    private static final int PROCESSOR_TYPE_INTEL = 2;
    private static final int PROCESSOR_TYPE_MTK = 3;
    private static final int PROCESSOR_TYPE_OTHER = 0;
    private static final int PROCESSOR_TYPE_QCOM = 1;
    private static final int PROCESSOR_TYPE_SEC = 4;
    private static final int PROCESSOR_TYPE_TI = 5;
    public static File cachedFile;
    public String path;
    private boolean videoConvertFirstWrite = true;

    interface CompressProgressListener {
        void onProgress(float f);
    }

    public static class VideoConvertRunnable implements Runnable {
        private String destPath;
        private String videoPath;

        private VideoConvertRunnable(String str, String str2) {
            this.videoPath = str;
            this.destPath = str2;
        }

        public static void runConversion(final String str, final String str2) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread thread = new Thread(new VideoConvertRunnable(str, str2), "VideoConvertRunnable");
                        thread.start();
                        thread.join();
                    } catch (Exception e) {
                        Log.e("tmessages", e.getMessage());
                    }
                }
            }).start();
        }

        public void run() {
            VideoController.getInstance().convertVideo(this.videoPath, this.destPath, 0, (CompressProgressListener) null);
        }
    }

    public static native int convertVideoFrame(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i, int i2, int i3, int i4, int i5);

    public static void copyFile(File file, File file2) throws IOException {
        FileChannel channel = new FileInputStream(file).getChannel();
        FileChannel channel2 = new FileOutputStream(file2).getChannel();
        try {
            channel.transferTo(1, channel.size(), channel2);
        } finally {
            if (channel != null) {
                channel.close();
            }
            if (channel2 != null) {
                channel2.close();
            }
        }
    }

    private void didWriteData(boolean z, boolean z2) {
        if (this.videoConvertFirstWrite) {
            this.videoConvertFirstWrite = false;
        }
    }

    public static VideoController getInstance() {
        VideoController videoController = Instance;
        if (videoController == null) {
            synchronized (VideoController.class) {
                try {
                    videoController = Instance;
                    if (videoController == null) {
                        videoController = new VideoController();
                        Instance = videoController;
                    }
                } catch (Throwable th) {
                    Class<VideoController> cls = VideoController.class;
                    throw th;
                }
            }
        }
        return videoController;
    }

    private static boolean isRecognizedFormat(int i) {
        if (!(i == 39 || i == 2130706688)) {
            switch (i) {
                case 19:
                case 20:
                case 21:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0088, code lost:
        if (r5 == -1) goto L_0x008a;
     */
    @TargetApi(16)
    private long readAndWriteTrack(MediaExtractor mediaExtractor, MP4Builder mP4Builder, MediaCodec.BufferInfo bufferInfo, long j, long j2, File file, boolean z) throws Exception {
        boolean z2;
        long j3;
        int selectTrack = selectTrack(mediaExtractor, z);
        if (selectTrack < 0) {
            return -1;
        }
        mediaExtractor.selectTrack(selectTrack);
        MediaFormat trackFormat = mediaExtractor.getTrackFormat(selectTrack);
        int addTrack = mP4Builder.addTrack(trackFormat, z);
        int integer = trackFormat.getInteger("max-input-size");
        if (j > 0) {
            mediaExtractor.seekTo(j, 0);
        } else {
            mediaExtractor.seekTo(0, 0);
        }
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(integer);
        boolean z3 = false;
        long j4 = -1;
        while (!z3) {
            int sampleTrackIndex = mediaExtractor.getSampleTrackIndex();
            if (sampleTrackIndex == selectTrack) {
                bufferInfo.size = mediaExtractor.readSampleData(allocateDirect, 0);
                if (bufferInfo.size < 0) {
                    bufferInfo.size = 0;
                    z2 = true;
                    j3 = j4;
                } else {
                    bufferInfo.presentationTimeUs = mediaExtractor.getSampleTime();
                    if (j > 0 && j4 == -1) {
                        j4 = bufferInfo.presentationTimeUs;
                    }
                    if (j2 < 0 || bufferInfo.presentationTimeUs < j2) {
                        bufferInfo.offset = 0;
                        bufferInfo.flags = mediaExtractor.getSampleFlags();
                        mP4Builder.writeSampleData(addTrack, allocateDirect, bufferInfo, z);
                        mediaExtractor.advance();
                        z2 = false;
                        j3 = j4;
                    }
                    z2 = true;
                    j3 = j4;
                }
            }
            if (z2) {
                z3 = true;
            }
            j4 = j3;
        }
        mediaExtractor.unselectTrack(selectTrack);
        return j4;
    }

    public static MediaCodecInfo selectCodec(String str) {
        int codecCount = MediaCodecList.getCodecCount();
        MediaCodecInfo mediaCodecInfo = null;
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                for (String equalsIgnoreCase : codecInfoAt.getSupportedTypes()) {
                    if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                        if (!codecInfoAt.getName().equals("OMX.SEC.avc.enc") || codecInfoAt.getName().equals("OMX.SEC.AVC.Encoder")) {
                            return codecInfoAt;
                        }
                        mediaCodecInfo = codecInfoAt;
                    }
                }
                continue;
            }
        }
        return mediaCodecInfo;
    }

    @SuppressLint({"NewApi"})
    public static int selectColorFormat(MediaCodecInfo mediaCodecInfo, String str) {
        int i = 0;
        MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
        int i2 = 0;
        while (true) {
            int i3 = i;
            if (i3 >= capabilitiesForType.colorFormats.length) {
                return i2;
            }
            int i4 = capabilitiesForType.colorFormats[i3];
            if (isRecognizedFormat(i4)) {
                if (!mediaCodecInfo.getName().equals("OMX.SEC.AVC.Encoder") || i4 != 19) {
                    return i4;
                }
                i2 = i4;
            }
            i = i3 + 1;
        }
    }

    @TargetApi(16)
    private int selectTrack(MediaExtractor mediaExtractor, boolean z) {
        int trackCount = mediaExtractor.getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            String string = mediaExtractor.getTrackFormat(i).getString("mime");
            if (z) {
                if (string.startsWith("audio/")) {
                    return i;
                }
            } else if (string.startsWith("video/")) {
                return i;
            }
        }
        return -5;
    }

    private void startVideoConvertFromQueue(String str, String str2) {
        VideoConvertRunnable.runConversion(str, str2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:184:0x03d0, code lost:
        r8 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x03d1, code lost:
        r4 = null;
        r22 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x048b, code lost:
        r6 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x04bf, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x04c0, code lost:
        r13 = r6;
        r15 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00c6, code lost:
        if (r8 != 270) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x04e5, code lost:
        r5.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:?, code lost:
        r6.finishMovie(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:340:0x0638, code lost:
        r6 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:342:0x063d, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:351:0x064d, code lost:
        if (r7.size != 0) goto L_0x064f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:377:0x06a2, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:378:0x06a3, code lost:
        r13 = r6;
        r15 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:379:0x06a7, code lost:
        r6 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:386:0x06b5, code lost:
        if (r7.presentationTimeUs != 0) goto L_0x064f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:400:0x06f4, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:401:0x06f5, code lost:
        r6 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:417:0x077a, code lost:
        r9 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:418:0x077b, code lost:
        r10 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:419:0x077d, code lost:
        r9 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:420:0x077e, code lost:
        r8 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:422:0x0783, code lost:
        r9 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:423:0x0784, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:424:0x0787, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:425:0x0788, code lost:
        r11 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:455:0x07f1, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:456:0x07f2, code lost:
        android.util.Log.e("tmessages", r5.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:481:0x0842, code lost:
        r8 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:482:0x0845, code lost:
        r8 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:486:0x0851, code lost:
        r8 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:494:0x0868, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:498:0x0874, code lost:
        r9 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:499:0x0877, code lost:
        r8 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:500:0x087a, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:501:0x087d, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:502:0x087e, code lost:
        r6 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:503:0x0882, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:504:0x0883, code lost:
        r6 = r11;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x02b7 A[Catch:{ Exception -> 0x048b, all -> 0x087d }] */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0315  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0322  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x03f0 A[Catch:{ Exception -> 0x085f, all -> 0x0862 }] */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x03f5 A[Catch:{ Exception -> 0x085f, all -> 0x0862 }] */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x03fa A[Catch:{ Exception -> 0x085f, all -> 0x0862 }] */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x0402 A[Catch:{ Exception -> 0x085f, all -> 0x0862 }] */
    /* JADX WARNING: Removed duplicated region for block: B:248:0x04bf A[ExcHandler: Exception (r6v69 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:242:0x04a7] */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x04e5  */
    /* JADX WARNING: Removed duplicated region for block: B:266:0x04ea  */
    /* JADX WARNING: Removed duplicated region for block: B:342:0x063d A[ExcHandler: all (th java.lang.Throwable), Splitter:B:319:0x05bd] */
    /* JADX WARNING: Removed duplicated region for block: B:400:0x06f4 A[Catch:{ Exception -> 0x06a2, all -> 0x06f4 }, ExcHandler: all (th java.lang.Throwable), Splitter:B:364:0x0665] */
    /* JADX WARNING: Removed duplicated region for block: B:424:0x0787 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:63:0x0191] */
    /* JADX WARNING: Removed duplicated region for block: B:437:0x07a3  */
    /* JADX WARNING: Removed duplicated region for block: B:439:0x07a8  */
    /* JADX WARNING: Removed duplicated region for block: B:500:0x087a A[ExcHandler: all (th java.lang.Throwable), Splitter:B:48:0x011c] */
    /* JADX WARNING: Removed duplicated region for block: B:501:0x087d A[ExcHandler: all (th java.lang.Throwable), Splitter:B:115:0x027a] */
    /* JADX WARNING: Removed duplicated region for block: B:506:0x088a  */
    /* JADX WARNING: Removed duplicated region for block: B:507:0x088e  */
    @TargetApi(16)
    public boolean convertVideo(String str, String str2, int i, CompressProgressListener compressProgressListener) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        long currentTimeMillis;
        MediaExtractor mediaExtractor;
        MP4Builder mP4Builder;
        boolean z;
        boolean z2;
        long j;
        int selectTrack;
        boolean z3;
        MediaCodec mediaCodec;
        Exception e;
        MediaCodec mediaCodec2;
        InputSurface inputSurface;
        OutputSurface outputSurface;
        Exception exc;
        MP4Builder mP4Builder2;
        boolean z4;
        MP4Builder mP4Builder3;
        char c;
        int i9;
        int i10;
        int i11;
        OutputSurface outputSurface2;
        int i12;
        ByteBuffer[] byteBufferArr;
        ByteBuffer[] byteBufferArr2;
        ByteBuffer[] byteBufferArr3;
        boolean z5;
        ByteBuffer byteBuffer;
        ByteBuffer byteBuffer2;
        ByteBuffer[] byteBufferArr4;
        boolean z6;
        boolean z7;
        int i13;
        boolean z8;
        boolean z9;
        boolean z10;
        boolean z11;
        int dequeueInputBuffer;
        int i14;
        char c2;
        this.path = str;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(this.path);
        String extractMetadata = mediaMetadataRetriever.extractMetadata(18);
        String extractMetadata2 = mediaMetadataRetriever.extractMetadata(19);
        String extractMetadata3 = mediaMetadataRetriever.extractMetadata(24);
        long longValue = Long.valueOf(mediaMetadataRetriever.extractMetadata(9)).longValue();
        int intValue = Integer.valueOf(extractMetadata3).intValue();
        int intValue2 = Integer.valueOf(extractMetadata).intValue();
        int intValue3 = Integer.valueOf(extractMetadata2).intValue();
        switch (i) {
            case 2:
                i2 = intValue2 / 2;
                i3 = intValue3 / 2;
                i4 = i2 * i3 * 10;
                break;
            case 3:
                i2 = intValue2 / 2;
                i3 = intValue3 / 2;
                i4 = (i2 / 2) * (i3 / 2) * 10;
                break;
            default:
                i2 = (intValue2 * 2) / 3;
                i3 = (intValue3 * 2) / 3;
                i4 = i2 * i3 * 30;
                break;
        }
        File file = new File(str2);
        int i15 = 90;
        long j2 = longValue * 1000;
        if (Build.VERSION.SDK_INT >= 18 || i3 <= i2 || i2 == intValue2 || i3 == intValue3) {
            if (Build.VERSION.SDK_INT > 20) {
                if (intValue == 90) {
                    i15 = SubsamplingScaleImageView.ORIENTATION_270;
                } else if (intValue == 180) {
                    i5 = SubsamplingScaleImageView.ORIENTATION_180;
                    intValue = 0;
                    i6 = i2;
                    i7 = i3;
                    i8 = i5;
                }
                intValue = 0;
                i6 = i3;
                i7 = i2;
                i8 = i15;
            }
            i5 = 0;
            i6 = i2;
            i7 = i3;
            i8 = i5;
        } else {
            intValue = 90;
            i6 = i3;
            i7 = i2;
            i8 = 270;
        }
        File file2 = new File(this.path);
        if (!file2.canRead()) {
            didWriteData(true, true);
            return false;
        }
        this.videoConvertFirstWrite = true;
        currentTimeMillis = System.currentTimeMillis();
        if (i6 == 0 || i7 == 0) {
            didWriteData(true, true);
            return false;
        }
        try {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            Mp4Movie mp4Movie = new Mp4Movie();
            mp4Movie.setCacheFile(file);
            mp4Movie.setRotation(intValue);
            mp4Movie.setSize(i6, i7);
            mP4Builder = new MP4Builder().createMovie(mp4Movie);
            try {
                mediaExtractor = new MediaExtractor();
                try {
                    mediaExtractor.setDataSource(file2.toString());
                    if (i6 == intValue2 && i7 == intValue3) {
                        try {
                            j = readAndWriteTrack(mediaExtractor, mP4Builder, bufferInfo, -1, -1, file, false);
                            if (j == -1) {
                                j = -1;
                            }
                            z2 = false;
                        } catch (Exception e2) {
                            e = e2;
                            Log.e("tmessages", e.getMessage());
                            if (mediaExtractor != null) {
                            }
                            if (mP4Builder != null) {
                            }
                            Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                            z = true;
                            didWriteData(true, z);
                            cachedFile = file;
                            Log.e("ViratPath", this.path + "");
                            Log.e("ViratPath", file.getPath() + "");
                            Log.e("ViratPath", file2.getPath() + "");
                            return true;
                        } catch (Throwable th) {
                            th = th;
                            if (mediaExtractor != null) {
                            }
                            if (mP4Builder != null) {
                            }
                            Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                            throw th;
                        }
                    } else {
                        try {
                            selectTrack = selectTrack(mediaExtractor, false);
                            if (selectTrack >= 0) {
                                try {
                                    String lowerCase = Build.MANUFACTURER.toLowerCase();
                                    if (Build.VERSION.SDK_INT < 18) {
                                        try {
                                            MediaCodecInfo selectCodec = selectCodec(MIME_TYPE);
                                            int selectColorFormat = selectColorFormat(selectCodec, MIME_TYPE);
                                            if (selectColorFormat != 0) {
                                                String name = selectCodec.getName();
                                                if (!name.contains("OMX.qcom.")) {
                                                    if (name.contains("OMX.Intel.")) {
                                                        c = 2;
                                                    } else if (name.equals("OMX.MTK.VIDEO.ENCODER.AVC")) {
                                                        c = 3;
                                                    } else if (name.equals("OMX.SEC.AVC.Encoder")) {
                                                        c2 = 4;
                                                    } else {
                                                        c = name.equals("OMX.TI.DUCATI1.VIDEO.H264E") ? (char) 5 : 0;
                                                    }
                                                    i14 = 0;
                                                    Log.e("tmessages", "codec = " + selectCodec.getName() + " manufacturer = " + lowerCase + "device = " + Build.MODEL);
                                                    i9 = i14;
                                                    i10 = selectColorFormat;
                                                } else if (Build.VERSION.SDK_INT != 16 || (!lowerCase.equals("lge") && !lowerCase.equals("nokia"))) {
                                                    c = 1;
                                                    i14 = 0;
                                                    Log.e("tmessages", "codec = " + selectCodec.getName() + " manufacturer = " + lowerCase + "device = " + Build.MODEL);
                                                    i9 = i14;
                                                    i10 = selectColorFormat;
                                                } else {
                                                    c2 = 1;
                                                }
                                                i14 = 1;
                                                c = c2;
                                                Log.e("tmessages", "codec = " + selectCodec.getName() + " manufacturer = " + lowerCase + "device = " + Build.MODEL);
                                                i9 = i14;
                                                i10 = selectColorFormat;
                                            } else {
                                                throw new RuntimeException("no supported color format");
                                            }
                                        } catch (Exception e3) {
                                            e = e3;
                                            inputSurface = null;
                                            mediaCodec2 = null;
                                            outputSurface2 = null;
                                            i11 = selectTrack;
                                            exc = e;
                                            outputSurface = outputSurface2;
                                            selectTrack = i11;
                                            mP4Builder2 = mP4Builder;
                                            try {
                                                Log.e("tmessages", exc.getMessage());
                                                z4 = true;
                                                mP4Builder3 = mP4Builder2;
                                                mediaExtractor.unselectTrack(selectTrack);
                                                if (outputSurface != null) {
                                                }
                                                if (inputSurface != null) {
                                                }
                                                if (mediaCodec2 != null) {
                                                }
                                                if (mediaCodec != null) {
                                                }
                                                z2 = z3;
                                                j = -1;
                                                if (!z2) {
                                                }
                                                mediaExtractor.release();
                                                if (mP4Builder != null) {
                                                }
                                                Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                                z = z2;
                                            } catch (Exception e4) {
                                                e = e4;
                                                mP4Builder = mP4Builder2;
                                                try {
                                                    Log.e("tmessages", e.getMessage());
                                                    if (mediaExtractor != null) {
                                                    }
                                                    if (mP4Builder != null) {
                                                    }
                                                    Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                                    z = true;
                                                    didWriteData(true, z);
                                                    cachedFile = file;
                                                    Log.e("ViratPath", this.path + "");
                                                    Log.e("ViratPath", file.getPath() + "");
                                                    Log.e("ViratPath", file2.getPath() + "");
                                                    return true;
                                                } catch (Throwable th2) {
                                                    th = th2;
                                                    if (mediaExtractor != null) {
                                                    }
                                                    if (mP4Builder != null) {
                                                    }
                                                    Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                                    throw th;
                                                }
                                            } catch (Throwable th3) {
                                                th = th3;
                                                mP4Builder = mP4Builder2;
                                                if (mediaExtractor != null) {
                                                }
                                                if (mP4Builder != null) {
                                                }
                                                Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                                throw th;
                                            }
                                            didWriteData(true, z);
                                            cachedFile = file;
                                            Log.e("ViratPath", this.path + "");
                                            Log.e("ViratPath", file.getPath() + "");
                                            Log.e("ViratPath", file2.getPath() + "");
                                            return true;
                                        } catch (Throwable th4) {
                                        }
                                    } else {
                                        c = 0;
                                        i9 = 0;
                                        i10 = 2130708361;
                                    }
                                    try {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("colorFormat = ");
                                        sb.append(i10);
                                        Log.e("tmessages", sb.toString());
                                        int i16 = i6 * i7;
                                        int i17 = (i16 * 3) / 2;
                                        if (c == 0) {
                                            if (i7 % 16 != 0) {
                                                int i18 = (((16 - (i7 % 16)) + i7) - i7) * i6;
                                                i17 += (i18 * 5) / 4;
                                                i12 = i18;
                                            }
                                            i12 = 0;
                                        } else if (c == 1) {
                                            if (!lowerCase.toLowerCase().equals("lge")) {
                                                int i19 = ((i16 + 2047) & -2048) - i16;
                                                i17 += i19;
                                                i12 = i19;
                                            }
                                            i12 = 0;
                                        } else {
                                            if (c != 5 && c == 3 && lowerCase.equals("baidu")) {
                                                int i20 = (((16 - (i7 % 16)) + i7) - i7) * i6;
                                                i17 += (i20 * 5) / 4;
                                                i12 = i20;
                                            }
                                            i12 = 0;
                                        }
                                        mediaExtractor.selectTrack(selectTrack);
                                        mediaExtractor.seekTo(0, 0);
                                        MediaFormat trackFormat = mediaExtractor.getTrackFormat(selectTrack);
                                        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(MIME_TYPE, i6, i7);
                                        createVideoFormat.setInteger("color-format", i10);
                                        createVideoFormat.setInteger("bitrate", i4);
                                        createVideoFormat.setInteger("frame-rate", 25);
                                        createVideoFormat.setInteger("i-frame-interval", 10);
                                        if (Build.VERSION.SDK_INT < 18) {
                                            createVideoFormat.setInteger("stride", i6 + 32);
                                            createVideoFormat.setInteger("slice-height", i7);
                                        }
                                        mediaCodec = MediaCodec.createEncoderByType(MIME_TYPE);
                                        mediaCodec.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
                                        if (Build.VERSION.SDK_INT >= 18) {
                                            inputSurface = new InputSurface(mediaCodec.createInputSurface());
                                            inputSurface.makeCurrent();
                                        } else {
                                            inputSurface = null;
                                        }
                                        mediaCodec.start();
                                        mediaCodec2 = MediaCodec.createDecoderByType(trackFormat.getString("mime"));
                                        outputSurface = Build.VERSION.SDK_INT >= 18 ? new OutputSurface() : new OutputSurface(i6, i7, i8);
                                        mediaCodec2.configure(trackFormat, outputSurface.getSurface(), (MediaCrypto) null, 0);
                                        mediaCodec2.start();
                                        if (Build.VERSION.SDK_INT < 21) {
                                            byteBufferArr = mediaCodec2.getInputBuffers();
                                            byteBufferArr2 = mediaCodec.getOutputBuffers();
                                            if (Build.VERSION.SDK_INT < 18) {
                                                byteBufferArr3 = mediaCodec.getInputBuffers();
                                            }
                                            byteBufferArr3 = null;
                                        } else {
                                            byteBufferArr = null;
                                            byteBufferArr2 = null;
                                            byteBufferArr3 = null;
                                        }
                                        boolean z12 = false;
                                        boolean z13 = false;
                                        int i21 = -5;
                                        ByteBuffer[] byteBufferArr5 = byteBufferArr2;
                                        VideoController videoController = this;
                                        MP4Builder mP4Builder4 = mP4Builder;
                                        while (!z13) {
                                            if (!z12) {
                                                try {
                                                    int sampleTrackIndex = mediaExtractor.getSampleTrackIndex();
                                                    if (sampleTrackIndex == selectTrack) {
                                                        int dequeueInputBuffer2 = mediaCodec2.dequeueInputBuffer(2500);
                                                        if (dequeueInputBuffer2 >= 0) {
                                                            int readSampleData = mediaExtractor.readSampleData(Build.VERSION.SDK_INT < 21 ? byteBufferArr[dequeueInputBuffer2] : mediaCodec2.getInputBuffer(dequeueInputBuffer2), 0);
                                                            if (readSampleData < 0) {
                                                                mediaCodec2.queueInputBuffer(dequeueInputBuffer2, 0, 0, 0, 4);
                                                                z12 = true;
                                                            } else {
                                                                mediaCodec2.queueInputBuffer(dequeueInputBuffer2, 0, readSampleData, mediaExtractor.getSampleTime(), 0);
                                                                mediaExtractor.advance();
                                                            }
                                                        }
                                                    } else if (sampleTrackIndex == -1) {
                                                        z10 = true;
                                                        z11 = z12;
                                                        if (!z10 || (dequeueInputBuffer = mediaCodec2.dequeueInputBuffer(2500)) < 0) {
                                                            z5 = z11;
                                                        } else {
                                                            mediaCodec2.queueInputBuffer(dequeueInputBuffer, 0, 0, 0, 4);
                                                            z5 = true;
                                                        }
                                                    }
                                                    z10 = false;
                                                    z11 = z12;
                                                    if (!z10 || (dequeueInputBuffer = mediaCodec2.dequeueInputBuffer(2500)) < 0) {
                                                    }
                                                } catch (Exception e5) {
                                                    e = e5;
                                                    exc = e;
                                                    mP4Builder2 = mP4Builder4;
                                                    Log.e("tmessages", exc.getMessage());
                                                    z4 = true;
                                                    mP4Builder3 = mP4Builder2;
                                                    mediaExtractor.unselectTrack(selectTrack);
                                                    if (outputSurface != null) {
                                                    }
                                                    if (inputSurface != null) {
                                                    }
                                                    if (mediaCodec2 != null) {
                                                    }
                                                    if (mediaCodec != null) {
                                                    }
                                                    z2 = z3;
                                                    j = -1;
                                                    if (!z2) {
                                                    }
                                                    mediaExtractor.release();
                                                    if (mP4Builder != null) {
                                                    }
                                                    Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                                    z = z2;
                                                    didWriteData(true, z);
                                                    cachedFile = file;
                                                    Log.e("ViratPath", this.path + "");
                                                    Log.e("ViratPath", file.getPath() + "");
                                                    Log.e("ViratPath", file2.getPath() + "");
                                                    return true;
                                                } catch (Throwable th5) {
                                                }
                                            } else {
                                                z5 = z12;
                                            }
                                            boolean z14 = true;
                                            boolean z15 = true;
                                            VideoController videoController2 = videoController;
                                            ByteBuffer[] byteBufferArr6 = byteBufferArr5;
                                            boolean z16 = z13;
                                            int i22 = i21;
                                            MP4Builder mP4Builder5 = mP4Builder4;
                                            while (true) {
                                                if (z14 || z15) {
                                                    try {
                                                        int dequeueOutputBuffer = mediaCodec.dequeueOutputBuffer(bufferInfo, 2500);
                                                        if (dequeueOutputBuffer == -1) {
                                                            z7 = false;
                                                            byteBufferArr4 = byteBufferArr6;
                                                            z6 = z16;
                                                            i13 = i22;
                                                        } else {
                                                            if (dequeueOutputBuffer == -3) {
                                                                try {
                                                                    if (Build.VERSION.SDK_INT < 21) {
                                                                        byteBufferArr6 = mediaCodec.getOutputBuffers();
                                                                    }
                                                                } catch (Exception e6) {
                                                                } catch (Throwable th6) {
                                                                    th = th6;
                                                                    mP4Builder = mP4Builder5;
                                                                    if (mediaExtractor != null) {
                                                                    }
                                                                    if (mP4Builder != null) {
                                                                    }
                                                                    Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                                                    throw th;
                                                                }
                                                            } else if (dequeueOutputBuffer == -2) {
                                                                MediaFormat outputFormat = mediaCodec.getOutputFormat();
                                                                if (i22 == -5) {
                                                                    try {
                                                                        i22 = mP4Builder5.addTrack(outputFormat, false);
                                                                    } catch (Exception e7) {
                                                                        exc = e7;
                                                                        mP4Builder2 = mP4Builder5;
                                                                    } catch (Throwable th7) {
                                                                        th = th7;
                                                                        mP4Builder = mP4Builder5;
                                                                        if (mediaExtractor != null) {
                                                                        }
                                                                        if (mP4Builder != null) {
                                                                        }
                                                                        Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                                                        throw th;
                                                                    }
                                                                }
                                                            } else if (dequeueOutputBuffer >= 0) {
                                                                try {
                                                                    ByteBuffer outputBuffer = Build.VERSION.SDK_INT < 21 ? byteBufferArr6[dequeueOutputBuffer] : mediaCodec.getOutputBuffer(dequeueOutputBuffer);
                                                                    if (outputBuffer != null) {
                                                                        if (bufferInfo.size > 1) {
                                                                            try {
                                                                                if ((bufferInfo.flags & 2) == 0) {
                                                                                    if (mP4Builder5.writeSampleData(i22, outputBuffer, bufferInfo, false)) {
                                                                                        videoController2.didWriteData(false, false);
                                                                                    }
                                                                                } else if (i22 == -5) {
                                                                                    byte[] bArr = new byte[bufferInfo.size];
                                                                                    outputBuffer.limit(bufferInfo.offset + bufferInfo.size);
                                                                                    outputBuffer.position(bufferInfo.offset);
                                                                                    outputBuffer.get(bArr);
                                                                                    int i23 = bufferInfo.size - 1;
                                                                                    while (true) {
                                                                                        if (i23 < 0 || i23 <= 3) {
                                                                                            byteBuffer = null;
                                                                                            byteBuffer2 = null;
                                                                                        } else {
                                                                                            if (bArr[i23] == 1 && bArr[i23 - 1] == 0 && bArr[i23 - 2] == 0) {
                                                                                                int i24 = i23 - 3;
                                                                                                if (bArr[i24] == 0) {
                                                                                                    byteBuffer = ByteBuffer.allocate(i24);
                                                                                                    byteBuffer2 = ByteBuffer.allocate(bufferInfo.size - i24);
                                                                                                    try {
                                                                                                        byteBuffer.put(bArr, 0, i24).position(0);
                                                                                                        byteBuffer2.put(bArr, i24, bufferInfo.size - i24).position(0);
                                                                                                    } catch (Exception e8) {
                                                                                                        Log.e("tmessages", e8.getMessage());
                                                                                                        z9 = true;
                                                                                                    } catch (Throwable th8) {
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            i23--;
                                                                                        }
                                                                                    }
                                                                                    byteBuffer = null;
                                                                                    byteBuffer2 = null;
                                                                                    MediaFormat createVideoFormat2 = MediaFormat.createVideoFormat(MIME_TYPE, i6, i7);
                                                                                    if (!(byteBuffer == null || byteBuffer2 == null)) {
                                                                                        createVideoFormat2.setByteBuffer("csd-0", byteBuffer);
                                                                                        createVideoFormat2.setByteBuffer("csd-1", byteBuffer2);
                                                                                    }
                                                                                    i22 = mP4Builder5.addTrack(createVideoFormat2, false);
                                                                                }
                                                                            } catch (Exception e9) {
                                                                                e = e9;
                                                                                exc = e;
                                                                                mP4Builder2 = mP4Builder5;
                                                                                Log.e("tmessages", exc.getMessage());
                                                                                z4 = true;
                                                                                mP4Builder3 = mP4Builder2;
                                                                                mediaExtractor.unselectTrack(selectTrack);
                                                                                if (outputSurface != null) {
                                                                                }
                                                                                if (inputSurface != null) {
                                                                                }
                                                                                if (mediaCodec2 != null) {
                                                                                }
                                                                                if (mediaCodec != null) {
                                                                                }
                                                                                z2 = z3;
                                                                                j = -1;
                                                                                if (!z2) {
                                                                                }
                                                                                mediaExtractor.release();
                                                                                if (mP4Builder != null) {
                                                                                }
                                                                                Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                                                                z = z2;
                                                                                didWriteData(true, z);
                                                                                cachedFile = file;
                                                                                Log.e("ViratPath", this.path + "");
                                                                                Log.e("ViratPath", file.getPath() + "");
                                                                                Log.e("ViratPath", file2.getPath() + "");
                                                                                return true;
                                                                            } catch (Throwable th9) {
                                                                                th = th9;
                                                                                mP4Builder = mP4Builder5;
                                                                                if (mediaExtractor != null) {
                                                                                }
                                                                                if (mP4Builder != null) {
                                                                                }
                                                                                Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                                                                throw th;
                                                                            }
                                                                        }
                                                                        try {
                                                                            boolean z17 = (bufferInfo.flags & 4) != 0;
                                                                            mediaCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
                                                                            z16 = z17;
                                                                        } catch (Exception e10) {
                                                                            e = e10;
                                                                            mP4Builder = mP4Builder5;
                                                                            outputSurface2 = outputSurface;
                                                                            i11 = selectTrack;
                                                                            exc = e;
                                                                            outputSurface = outputSurface2;
                                                                            selectTrack = i11;
                                                                            mP4Builder2 = mP4Builder;
                                                                            Log.e("tmessages", exc.getMessage());
                                                                            z4 = true;
                                                                            mP4Builder3 = mP4Builder2;
                                                                            mediaExtractor.unselectTrack(selectTrack);
                                                                            if (outputSurface != null) {
                                                                            }
                                                                            if (inputSurface != null) {
                                                                            }
                                                                            if (mediaCodec2 != null) {
                                                                            }
                                                                            if (mediaCodec != null) {
                                                                            }
                                                                            z2 = z3;
                                                                            j = -1;
                                                                            if (!z2) {
                                                                            }
                                                                            mediaExtractor.release();
                                                                            if (mP4Builder != null) {
                                                                            }
                                                                            Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                                                            z = z2;
                                                                            didWriteData(true, z);
                                                                            cachedFile = file;
                                                                            Log.e("ViratPath", this.path + "");
                                                                            Log.e("ViratPath", file.getPath() + "");
                                                                            Log.e("ViratPath", file2.getPath() + "");
                                                                            return true;
                                                                        } catch (Throwable th10) {
                                                                            th = th10;
                                                                            mP4Builder = mP4Builder5;
                                                                            if (mediaExtractor != null) {
                                                                            }
                                                                            if (mP4Builder != null) {
                                                                            }
                                                                            Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                                                            throw th;
                                                                        }
                                                                    } else {
                                                                        throw new RuntimeException("encoderOutputBuffer " + dequeueOutputBuffer + " was null");
                                                                    }
                                                                } catch (Exception e11) {
                                                                    e = e11;
                                                                    mP4Builder = mP4Builder5;
                                                                } catch (Throwable th11) {
                                                                    th = th11;
                                                                    mP4Builder = mP4Builder5;
                                                                    if (mediaExtractor != null) {
                                                                    }
                                                                    if (mP4Builder != null) {
                                                                    }
                                                                    Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                                                    throw th;
                                                                }
                                                            } else {
                                                                throw new RuntimeException("unexpected result from encoder.dequeueOutputBuffer: " + dequeueOutputBuffer);
                                                            }
                                                            byteBufferArr4 = byteBufferArr6;
                                                            z6 = z16;
                                                            z7 = z15;
                                                            i13 = i22;
                                                        }
                                                        if (dequeueOutputBuffer == -1) {
                                                            int dequeueOutputBuffer2 = mediaCodec2.dequeueOutputBuffer(bufferInfo, 2500);
                                                            if (dequeueOutputBuffer2 != -1) {
                                                                if (dequeueOutputBuffer2 != -3) {
                                                                    if (dequeueOutputBuffer2 == -2) {
                                                                        Log.e("tmessages", "newFormat = " + mediaCodec2.getOutputFormat());
                                                                    } else if (dequeueOutputBuffer2 >= 0) {
                                                                        if (Build.VERSION.SDK_INT < 18) {
                                                                            if (bufferInfo.size == 0) {
                                                                            }
                                                                        }
                                                                        boolean z18 = true;
                                                                        mediaCodec2.releaseOutputBuffer(dequeueOutputBuffer2, z18);
                                                                        if (z18) {
                                                                            outputSurface.awaitNewImage();
                                                                            z9 = false;
                                                                            if (!z9) {
                                                                                if (Build.VERSION.SDK_INT >= 18) {
                                                                                    outputSurface.drawImage(false);
                                                                                    try {
                                                                                        inputSurface.setPresentationTime(bufferInfo.presentationTimeUs * 1000);
                                                                                        if (compressProgressListener != null) {
                                                                                            compressProgressListener.onProgress((((float) bufferInfo.presentationTimeUs) / ((float) j2)) * 100.0f);
                                                                                        }
                                                                                        inputSurface.swapBuffers();
                                                                                    } catch (Exception e12) {
                                                                                        e = e12;
                                                                                        mP4Builder = mP4Builder5;
                                                                                        outputSurface2 = outputSurface;
                                                                                        i11 = selectTrack;
                                                                                        exc = e;
                                                                                        outputSurface = outputSurface2;
                                                                                        selectTrack = i11;
                                                                                        mP4Builder2 = mP4Builder;
                                                                                        Log.e("tmessages", exc.getMessage());
                                                                                        z4 = true;
                                                                                        mP4Builder3 = mP4Builder2;
                                                                                        mediaExtractor.unselectTrack(selectTrack);
                                                                                        if (outputSurface != null) {
                                                                                        }
                                                                                        if (inputSurface != null) {
                                                                                        }
                                                                                        if (mediaCodec2 != null) {
                                                                                        }
                                                                                        if (mediaCodec != null) {
                                                                                        }
                                                                                        z2 = z3;
                                                                                        j = -1;
                                                                                        if (!z2) {
                                                                                        }
                                                                                        mediaExtractor.release();
                                                                                        if (mP4Builder != null) {
                                                                                        }
                                                                                        Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                                                                        z = z2;
                                                                                        didWriteData(true, z);
                                                                                        cachedFile = file;
                                                                                        Log.e("ViratPath", this.path + "");
                                                                                        Log.e("ViratPath", file.getPath() + "");
                                                                                        Log.e("ViratPath", file2.getPath() + "");
                                                                                        return true;
                                                                                    } catch (Throwable th12) {
                                                                                    }
                                                                                } else {
                                                                                    int dequeueInputBuffer3 = mediaCodec.dequeueInputBuffer(2500);
                                                                                    if (dequeueInputBuffer3 >= 0) {
                                                                                        outputSurface.drawImage(true);
                                                                                        ByteBuffer frame = outputSurface.getFrame();
                                                                                        ByteBuffer byteBuffer3 = byteBufferArr3[dequeueInputBuffer3];
                                                                                        byteBuffer3.clear();
                                                                                        convertVideoFrame(frame, byteBuffer3, i10, i6, i7, i12, i9);
                                                                                        mediaCodec.queueInputBuffer(dequeueInputBuffer3, 0, i17, bufferInfo.presentationTimeUs, 0);
                                                                                    } else {
                                                                                        Log.e("tmessages", "input buffer not available");
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        if ((bufferInfo.flags & 4) != 0) {
                                                                            Log.e("tmessages", "decoder stream end");
                                                                            if (Build.VERSION.SDK_INT >= 18) {
                                                                                mediaCodec.signalEndOfInputStream();
                                                                            } else {
                                                                                int dequeueInputBuffer4 = mediaCodec.dequeueInputBuffer(2500);
                                                                                if (dequeueInputBuffer4 >= 0) {
                                                                                    mediaCodec.queueInputBuffer(dequeueInputBuffer4, 0, 1, bufferInfo.presentationTimeUs, 4);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            z8 = z14;
                                                                            z14 = z8;
                                                                        }
                                                                    } else {
                                                                        throw new RuntimeException("unexpected result from decoder.dequeueOutputBuffer: " + dequeueOutputBuffer2);
                                                                    }
                                                                }
                                                                z8 = z14;
                                                                z14 = z8;
                                                            }
                                                            z8 = false;
                                                            z14 = z8;
                                                        }
                                                        videoController2 = this;
                                                        byteBufferArr6 = byteBufferArr4;
                                                        z15 = z7;
                                                        z16 = z6;
                                                        i22 = i13;
                                                    } catch (Exception e13) {
                                                        e = e13;
                                                        MP4Builder mP4Builder6 = mP4Builder5;
                                                        mP4Builder = mP4Builder6;
                                                        outputSurface2 = outputSurface;
                                                        i11 = selectTrack;
                                                        exc = e;
                                                        outputSurface = outputSurface2;
                                                        selectTrack = i11;
                                                        mP4Builder2 = mP4Builder;
                                                        Log.e("tmessages", exc.getMessage());
                                                        z4 = true;
                                                        mP4Builder3 = mP4Builder2;
                                                        mediaExtractor.unselectTrack(selectTrack);
                                                        if (outputSurface != null) {
                                                        }
                                                        if (inputSurface != null) {
                                                        }
                                                        if (mediaCodec2 != null) {
                                                        }
                                                        if (mediaCodec != null) {
                                                        }
                                                        z2 = z3;
                                                        j = -1;
                                                        if (!z2) {
                                                        }
                                                        mediaExtractor.release();
                                                        if (mP4Builder != null) {
                                                        }
                                                        Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                                        z = z2;
                                                        didWriteData(true, z);
                                                        cachedFile = file;
                                                        Log.e("ViratPath", this.path + "");
                                                        Log.e("ViratPath", file.getPath() + "");
                                                        Log.e("ViratPath", file2.getPath() + "");
                                                        return true;
                                                    } catch (Throwable th13) {
                                                        th = th13;
                                                        mP4Builder = mP4Builder5;
                                                        if (mediaExtractor != null) {
                                                        }
                                                        if (mP4Builder != null) {
                                                        }
                                                        Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                                        throw th;
                                                    }
                                                } else {
                                                    byteBufferArr5 = byteBufferArr6;
                                                    videoController = videoController2;
                                                    z13 = z16;
                                                    i21 = i22;
                                                    z12 = z5;
                                                    mP4Builder4 = mP4Builder5;
                                                }
                                            }
                                        }
                                        z4 = false;
                                        mP4Builder3 = mP4Builder4;
                                    } catch (Exception e14) {
                                        e = e14;
                                    } catch (Throwable th14) {
                                    }
                                } catch (Exception e15) {
                                    e = e15;
                                    InputSurface inputSurface2 = null;
                                    mediaCodec = null;
                                    e = e;
                                    mediaCodec2 = null;
                                    inputSurface = inputSurface2;
                                    outputSurface = null;
                                    exc = e;
                                    mP4Builder2 = mP4Builder;
                                    Log.e("tmessages", exc.getMessage());
                                    z4 = true;
                                    mP4Builder3 = mP4Builder2;
                                    mediaExtractor.unselectTrack(selectTrack);
                                    if (outputSurface != null) {
                                    }
                                    if (inputSurface != null) {
                                    }
                                    if (mediaCodec2 != null) {
                                    }
                                    if (mediaCodec != null) {
                                    }
                                    z2 = z3;
                                    j = -1;
                                    if (!z2) {
                                    }
                                    mediaExtractor.release();
                                    if (mP4Builder != null) {
                                    }
                                    Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                    z = z2;
                                    didWriteData(true, z);
                                    cachedFile = file;
                                    Log.e("ViratPath", this.path + "");
                                    Log.e("ViratPath", file.getPath() + "");
                                    Log.e("ViratPath", file2.getPath() + "");
                                    return true;
                                } catch (Throwable th142) {
                                }
                                try {
                                    mediaExtractor.unselectTrack(selectTrack);
                                    if (outputSurface != null) {
                                        outputSurface.release();
                                    }
                                    if (inputSurface != null) {
                                        inputSurface.release();
                                    }
                                    if (mediaCodec2 != null) {
                                        mediaCodec2.stop();
                                        mediaCodec2.release();
                                    }
                                    if (mediaCodec != null) {
                                        mediaCodec.stop();
                                        mediaCodec.release();
                                        z3 = z4;
                                    } else {
                                        z3 = z4;
                                    }
                                } catch (Exception e16) {
                                    e = e16;
                                    Log.e("tmessages", e.getMessage());
                                    if (mediaExtractor != null) {
                                    }
                                    if (mP4Builder != null) {
                                    }
                                    Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                    z = true;
                                    didWriteData(true, z);
                                    cachedFile = file;
                                    Log.e("ViratPath", this.path + "");
                                    Log.e("ViratPath", file.getPath() + "");
                                    Log.e("ViratPath", file2.getPath() + "");
                                    return true;
                                } catch (Throwable th15) {
                                    th = th15;
                                    if (mediaExtractor != null) {
                                    }
                                    if (mP4Builder != null) {
                                    }
                                    Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                                    throw th;
                                }
                            } else {
                                z3 = false;
                            }
                            z2 = z3;
                            j = -1;
                        } catch (Exception e17) {
                            e = e17;
                        } catch (Throwable th16) {
                            th = th16;
                            if (mediaExtractor != null) {
                            }
                            if (mP4Builder != null) {
                            }
                            Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                            throw th;
                        }
                    }
                    if (!z2) {
                        try {
                            readAndWriteTrack(mediaExtractor, mP4Builder, bufferInfo, j, -1, file, true);
                        } catch (Exception e18) {
                            e = e18;
                            Log.e("tmessages", e.getMessage());
                            if (mediaExtractor != null) {
                                mediaExtractor.release();
                            }
                            if (mP4Builder != null) {
                                try {
                                    mP4Builder.finishMovie(false);
                                } catch (Exception e19) {
                                    Log.e("tmessages", e19.getMessage());
                                }
                            }
                            Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                            z = true;
                            didWriteData(true, z);
                            cachedFile = file;
                            Log.e("ViratPath", this.path + "");
                            Log.e("ViratPath", file.getPath() + "");
                            Log.e("ViratPath", file2.getPath() + "");
                            return true;
                        } catch (Throwable th17) {
                            th = th17;
                            if (mediaExtractor != null) {
                            }
                            if (mP4Builder != null) {
                            }
                            Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                            throw th;
                        }
                    }
                    mediaExtractor.release();
                    if (mP4Builder != null) {
                        try {
                            mP4Builder.finishMovie(false);
                        } catch (Exception e20) {
                            Log.e("tmessages", e20.getMessage());
                        }
                    }
                    Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                    z = z2;
                } catch (Exception e21) {
                    e = e21;
                    Log.e("tmessages", e.getMessage());
                    if (mediaExtractor != null) {
                    }
                    if (mP4Builder != null) {
                    }
                    Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                    z = true;
                    didWriteData(true, z);
                    cachedFile = file;
                    Log.e("ViratPath", this.path + "");
                    Log.e("ViratPath", file.getPath() + "");
                    Log.e("ViratPath", file2.getPath() + "");
                    return true;
                } catch (Throwable th18) {
                    th = th18;
                    if (mediaExtractor != null) {
                    }
                    if (mP4Builder != null) {
                    }
                    Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                    throw th;
                }
            } catch (Exception e22) {
                e = e22;
                mediaExtractor = null;
                Log.e("tmessages", e.getMessage());
                if (mediaExtractor != null) {
                }
                if (mP4Builder != null) {
                }
                Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                z = true;
                didWriteData(true, z);
                cachedFile = file;
                Log.e("ViratPath", this.path + "");
                Log.e("ViratPath", file.getPath() + "");
                Log.e("ViratPath", file2.getPath() + "");
                return true;
            } catch (Throwable th19) {
                th = th19;
                mediaExtractor = null;
                if (mediaExtractor != null) {
                }
                if (mP4Builder != null) {
                }
                Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
                throw th;
            }
        } catch (Exception e23) {
            e = e23;
            mediaExtractor = null;
            mP4Builder = null;
        } catch (Throwable th20) {
            th = th20;
            mP4Builder = null;
            mediaExtractor = null;
            if (mediaExtractor != null) {
            }
            if (mP4Builder != null) {
            }
            Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
            throw th;
        }
        didWriteData(true, z);
        cachedFile = file;
        Log.e("ViratPath", this.path + "");
        Log.e("ViratPath", file.getPath() + "");
        Log.e("ViratPath", file2.getPath() + "");
        return true;
        e = e;
        outputSurface = null;
        exc = e;
        mP4Builder2 = mP4Builder;
        Log.e("tmessages", exc.getMessage());
        z4 = true;
        mP4Builder3 = mP4Builder2;
        mediaExtractor.unselectTrack(selectTrack);
        if (outputSurface != null) {
        }
        if (inputSurface != null) {
        }
        if (mediaCodec2 != null) {
        }
        if (mediaCodec != null) {
        }
        z2 = z3;
        j = -1;
        if (!z2) {
        }
        mediaExtractor.release();
        if (mP4Builder != null) {
        }
        Log.e("tmessages", "time = " + (System.currentTimeMillis() - currentTimeMillis));
        z = z2;
        didWriteData(true, z);
        cachedFile = file;
        Log.e("ViratPath", this.path + "");
        Log.e("ViratPath", file.getPath() + "");
        Log.e("ViratPath", file2.getPath() + "");
        return true;
    }

    public void scheduleVideoConvert(String str, String str2) {
        startVideoConvertFromQueue(str, str2);
    }
}
