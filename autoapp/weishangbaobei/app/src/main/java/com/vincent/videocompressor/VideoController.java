package com.vincent.videocompressor;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaExtractor;
import android.util.Log;
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

    public static native int convertVideoFrame(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i, int i2, int i3, int i4, int i5);

    private static boolean isRecognizedFormat(int i) {
        if (i == 39 || i == 2130706688) {
            return true;
        }
        switch (i) {
            case 19:
            case 20:
            case 21:
                return true;
            default:
                return false;
        }
    }

    public static VideoController getInstance() {
        VideoController videoController = Instance;
        if (videoController == null) {
            synchronized (VideoController.class) {
                videoController = Instance;
                if (videoController == null) {
                    videoController = new VideoController();
                    Instance = videoController;
                }
            }
        }
        return videoController;
    }

    @SuppressLint({"NewApi"})
    public static int selectColorFormat(MediaCodecInfo mediaCodecInfo, String str) {
        MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
        int i = 0;
        for (int i2 : capabilitiesForType.colorFormats) {
            if (isRecognizedFormat(i2)) {
                if (!mediaCodecInfo.getName().equals("OMX.SEC.AVC.Encoder") || i2 != 19) {
                    return i2;
                }
                i = i2;
            }
        }
        return i;
    }

    private void didWriteData(boolean z, boolean z2) {
        if (this.videoConvertFirstWrite) {
            this.videoConvertFirstWrite = false;
        }
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

    public static MediaCodecInfo selectCodec(String str) {
        int codecCount = MediaCodecList.getCodecCount();
        MediaCodecInfo mediaCodecInfo = null;
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                MediaCodecInfo mediaCodecInfo2 = mediaCodecInfo;
                for (String equalsIgnoreCase : codecInfoAt.getSupportedTypes()) {
                    if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                        if (!codecInfoAt.getName().equals("OMX.SEC.avc.enc") || codecInfoAt.getName().equals("OMX.SEC.AVC.Encoder")) {
                            return codecInfoAt;
                        }
                        mediaCodecInfo2 = codecInfoAt;
                    }
                }
                mediaCodecInfo = mediaCodecInfo2;
            }
        }
        return mediaCodecInfo;
    }

    public void scheduleVideoConvert(String str, String str2) {
        startVideoConvertFromQueue(str, str2);
    }

    private void startVideoConvertFromQueue(String str, String str2) {
        VideoConvertRunnable.runConversion(str, str2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0098, code lost:
        if (r12 == -1) goto L_0x009a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a0 A[SYNTHETIC] */
    @android.annotation.TargetApi(16)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long readAndWriteTrack(android.media.MediaExtractor r22, com.vincent.videocompressor.MP4Builder r23, android.media.MediaCodec.BufferInfo r24, long r25, long r27, java.io.File r29, boolean r30) throws java.lang.Exception {
        /*
            r21 = this;
            r0 = r22
            r1 = r23
            r2 = r24
            r3 = r25
            r5 = r21
            r6 = r30
            int r7 = r5.selectTrack(r0, r6)
            if (r7 < 0) goto L_0x00ab
            r0.selectTrack(r7)
            android.media.MediaFormat r10 = r0.getTrackFormat(r7)
            int r11 = r1.addTrack(r10, r6)
            java.lang.String r12 = "max-input-size"
            int r10 = r10.getInteger(r12)
            r12 = 0
            r8 = 0
            int r9 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r9 <= 0) goto L_0x002e
            r0.seekTo(r3, r8)
            goto L_0x0031
        L_0x002e:
            r0.seekTo(r12, r8)
        L_0x0031:
            java.nio.ByteBuffer r9 = java.nio.ByteBuffer.allocateDirect(r10)
            r10 = 0
            r16 = -1
        L_0x0038:
            if (r10 != 0) goto L_0x00a7
            int r12 = r22.getSampleTrackIndex()
            r13 = 1
            if (r12 != r7) goto L_0x0093
            int r12 = r0.readSampleData(r9, r8)
            r2.size = r12
            int r12 = r2.size
            if (r12 >= 0) goto L_0x0053
            r2.size = r8
            r4 = r9
            r3 = 0
            r8 = 1
            r18 = 0
            goto L_0x009d
        L_0x0053:
            r20 = r9
            long r8 = r22.getSampleTime()
            r2.presentationTimeUs = r8
            r8 = 0
            int r12 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r12 <= 0) goto L_0x006a
            r14 = -1
            int r12 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1))
            if (r12 != 0) goto L_0x006a
            long r8 = r2.presentationTimeUs
            goto L_0x006c
        L_0x006a:
            r8 = r16
        L_0x006c:
            r18 = 0
            int r12 = (r27 > r18 ? 1 : (r27 == r18 ? 0 : -1))
            if (r12 < 0) goto L_0x007f
            long r3 = r2.presentationTimeUs
            int r12 = (r3 > r27 ? 1 : (r3 == r27 ? 0 : -1))
            if (r12 >= 0) goto L_0x0079
            goto L_0x007f
        L_0x0079:
            r16 = r8
            r4 = r20
            r3 = 0
            goto L_0x009a
        L_0x007f:
            r3 = 0
            r2.offset = r3
            int r4 = r22.getSampleFlags()
            r2.flags = r4
            r4 = r20
            r1.writeSampleData(r11, r4, r2, r6)
            r22.advance()
            r16 = r8
            goto L_0x009c
        L_0x0093:
            r4 = r9
            r3 = 0
            r18 = 0
            r8 = -1
            if (r12 != r8) goto L_0x009c
        L_0x009a:
            r8 = 1
            goto L_0x009d
        L_0x009c:
            r8 = 0
        L_0x009d:
            if (r8 == 0) goto L_0x00a0
            r10 = 1
        L_0x00a0:
            r9 = r4
            r12 = r18
            r3 = r25
            r8 = 0
            goto L_0x0038
        L_0x00a7:
            r0.unselectTrack(r7)
            return r16
        L_0x00ab:
            r0 = -1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vincent.videocompressor.VideoController.readAndWriteTrack(android.media.MediaExtractor, com.vincent.videocompressor.MP4Builder, android.media.MediaCodec$BufferInfo, long, long, java.io.File, boolean):long");
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

    /* JADX WARNING: type inference failed for: r8v44 */
    /* JADX WARNING: type inference failed for: r8v45 */
    /* JADX WARNING: type inference failed for: r8v46 */
    /* JADX WARNING: type inference failed for: r8v47 */
    /* JADX WARNING: type inference failed for: r8v48 */
    /* JADX WARNING: type inference failed for: r8v49 */
    /* JADX WARNING: type inference failed for: r8v50 */
    /* JADX WARNING: type inference failed for: r8v52 */
    /* JADX WARNING: type inference failed for: r8v55 */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0261, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0262, code lost:
        r47 = r1;
        r5 = r4;
        r54 = r7;
        r42 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x02fc, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x02fd, code lost:
        r47 = r1;
        r5 = r4;
        r54 = r7;
        r42 = r14;
        r55 = r33;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0331, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0332, code lost:
        r47 = r1;
        r5 = r4;
        r54 = r7;
        r55 = r33;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x0372, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x0373, code lost:
        r47 = r1;
        r5 = r4;
        r54 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x03d3, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00a7, code lost:
        if (r1 == 270) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x03f6, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x03f7, code lost:
        r47 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x03f9, code lost:
        r1 = r0;
        r54 = r7;
        r55 = r33;
        r5 = r46;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x044c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x044d, code lost:
        r1 = r0;
        r54 = r7;
        r55 = r33;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:329:0x05ad, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:340:0x05c2, code lost:
        if (r5.size != 0) goto L_0x05c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:342:0x05c6, code lost:
        r6 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:349:0x05d2, code lost:
        if (r5.presentationTimeUs != 0) goto L_0x05c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:356:0x05df, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:?, code lost:
        android.util.Log.e("tmessages", r0.getMessage());
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:406:0x0730, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:407:0x0733, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:408:0x0734, code lost:
        r1 = r0;
        r8 = r56;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:411:0x074d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:412:0x074e, code lost:
        r47 = r1;
        r5 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:415:0x0759, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:416:0x075a, code lost:
        r47 = r1;
        r5 = r4;
        r54 = r7;
        r56 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:417:0x0762, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:418:0x0763, code lost:
        r47 = r1;
        r5 = r4;
        r54 = r7;
        r56 = r8;
        r42 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:419:0x076c, code lost:
        r55 = r33;
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:420:0x0770, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:421:0x0771, code lost:
        r47 = r1;
        r5 = r4;
        r54 = r7;
        r56 = r8;
        r42 = r14;
        r55 = r33;
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:422:0x077e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:423:0x077f, code lost:
        r47 = r1;
        r5 = r4;
        r54 = r7;
        r42 = r14;
        r55 = r33;
        r1 = r0;
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:426:0x0794, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:427:0x0795, code lost:
        r54 = r7;
        r55 = r33;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:511:0x0931, code lost:
        r54.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:514:?, code lost:
        r11.finishMovie(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:515:0x093a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:516:0x093b, code lost:
        android.util.Log.e("tmessages", r0.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0208, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0209, code lost:
        r1 = r0;
        r54 = r7;
        r2 = r11;
        r11 = r33;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0211, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0212, code lost:
        r47 = r1;
        r5 = r4;
        r54 = r7;
        r42 = r14;
        r40 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x021b, code lost:
        r55 = r33;
        r8 = null;
        r9 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x021f, code lost:
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0220, code lost:
        r14 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x02d3  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x02ef A[SYNTHETIC, Splitter:B:146:0x02ef] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0315  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x032b A[SYNTHETIC, Splitter:B:161:0x032b] */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x033b A[SYNTHETIC, Splitter:B:165:0x033b] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0358 A[SYNTHETIC, Splitter:B:171:0x0358] */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x037a  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x0384  */
    /* JADX WARNING: Removed duplicated region for block: B:234:0x044c A[ExcHandler: Exception (r0v55 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:228:0x0439] */
    /* JADX WARNING: Removed duplicated region for block: B:311:0x0546 A[Catch:{ Exception -> 0x06dd, all -> 0x06d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:313:0x0549 A[Catch:{ Exception -> 0x06dd, all -> 0x06d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:329:0x05ad A[ExcHandler: all (th java.lang.Throwable), PHI: r2 r54 
      PHI: (r2v29 com.vincent.videocompressor.MP4Builder) = (r2v30 com.vincent.videocompressor.MP4Builder), (r2v30 com.vincent.videocompressor.MP4Builder), (r2v30 com.vincent.videocompressor.MP4Builder), (r2v30 com.vincent.videocompressor.MP4Builder), (r2v30 com.vincent.videocompressor.MP4Builder), (r2v30 com.vincent.videocompressor.MP4Builder), (r2v30 com.vincent.videocompressor.MP4Builder), (r2v30 com.vincent.videocompressor.MP4Builder), (r2v48 com.vincent.videocompressor.MP4Builder) binds: [B:353:0x05da, B:354:?, B:346:0x05cc, B:347:?, B:338:0x05c0, B:339:?, B:326:0x0587, B:327:?, B:294:0x04fa] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r54v32 android.media.MediaExtractor) = (r54v33 android.media.MediaExtractor), (r54v33 android.media.MediaExtractor), (r54v33 android.media.MediaExtractor), (r54v33 android.media.MediaExtractor), (r54v33 android.media.MediaExtractor), (r54v33 android.media.MediaExtractor), (r54v33 android.media.MediaExtractor), (r54v33 android.media.MediaExtractor), (r54v45 android.media.MediaExtractor) binds: [B:353:0x05da, B:354:?, B:346:0x05cc, B:347:?, B:338:0x05c0, B:339:?, B:326:0x0587, B:327:?, B:294:0x04fa] A[DONT_GENERATE, DONT_INLINE], Splitter:B:294:0x04fa] */
    /* JADX WARNING: Removed duplicated region for block: B:383:0x0674 A[Catch:{ Exception -> 0x0733, all -> 0x0730 }] */
    /* JADX WARNING: Removed duplicated region for block: B:389:0x06a3 A[Catch:{ Exception -> 0x0733, all -> 0x0730 }] */
    /* JADX WARNING: Removed duplicated region for block: B:406:0x0730 A[ExcHandler: all (th java.lang.Throwable), PHI: r54 r55 
      PHI: (r54v25 android.media.MediaExtractor) = (r54v33 android.media.MediaExtractor), (r54v33 android.media.MediaExtractor), (r54v23 android.media.MediaExtractor), (r54v38 android.media.MediaExtractor) binds: [B:365:0x05f9, B:370:0x0613, B:256:0x048e, B:308:0x0540] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r55v20 com.vincent.videocompressor.MP4Builder) = (r55v31 com.vincent.videocompressor.MP4Builder), (r55v31 com.vincent.videocompressor.MP4Builder), (r55v18 com.vincent.videocompressor.MP4Builder), (r55v18 com.vincent.videocompressor.MP4Builder) binds: [B:365:0x05f9, B:370:0x0613, B:256:0x048e, B:308:0x0540] A[DONT_GENERATE, DONT_INLINE], Splitter:B:365:0x05f9] */
    /* JADX WARNING: Removed duplicated region for block: B:426:0x0794 A[ExcHandler: all (th java.lang.Throwable), PHI: r7 r33 
      PHI: (r7v14 android.media.MediaExtractor) = (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v18 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor) binds: [B:57:0x014f, B:58:?, B:103:0x022c, B:104:?, B:106:0x0235, B:107:?, B:131:0x02a0, B:132:?, B:139:0x02e1, B:142:0x02e6, B:143:?, B:155:0x0316, B:157:0x0323, B:158:?, B:167:0x0342, B:168:?, B:221:0x0425, B:165:0x033b, B:166:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r33v1 com.vincent.videocompressor.MP4Builder) = (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v5 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder) binds: [B:57:0x014f, B:58:?, B:103:0x022c, B:104:?, B:106:0x0235, B:107:?, B:131:0x02a0, B:132:?, B:139:0x02e1, B:142:0x02e6, B:143:?, B:155:0x0316, B:157:0x0323, B:158:?, B:167:0x0342, B:168:?, B:221:0x0425, B:165:0x033b, B:166:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:131:0x02a0] */
    /* JADX WARNING: Removed duplicated region for block: B:443:0x07c4 A[Catch:{ Exception -> 0x07e2, all -> 0x07dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:445:0x07c9 A[Catch:{ Exception -> 0x07e2, all -> 0x07dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:447:0x07ce A[Catch:{ Exception -> 0x07e2, all -> 0x07dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:449:0x07d6 A[Catch:{ Exception -> 0x07e2, all -> 0x07dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:461:0x07fd  */
    /* JADX WARNING: Removed duplicated region for block: B:466:0x0817  */
    /* JADX WARNING: Removed duplicated region for block: B:469:0x0820 A[SYNTHETIC, Splitter:B:469:0x0820] */
    /* JADX WARNING: Removed duplicated region for block: B:499:0x08a2  */
    /* JADX WARNING: Removed duplicated region for block: B:501:0x08a7 A[SYNTHETIC, Splitter:B:501:0x08a7] */
    /* JADX WARNING: Removed duplicated region for block: B:511:0x0931  */
    /* JADX WARNING: Removed duplicated region for block: B:513:0x0936 A[SYNTHETIC, Splitter:B:513:0x0936] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0208 A[ExcHandler: all (r0v69 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r7 r11 r33 
      PHI: (r7v40 android.media.MediaExtractor) = (r7v18 android.media.MediaExtractor), (r7v16 android.media.MediaExtractor), (r7v16 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor), (r7v7 android.media.MediaExtractor) binds: [B:228:0x0439, B:185:0x038a, B:189:0x0394, B:171:0x0358, B:161:0x032b, B:162:?, B:146:0x02ef, B:147:?, B:148:0x02f8, B:149:?, B:136:0x02d5, B:137:?, B:109:0x024a, B:62:0x015f] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r11v31 com.vincent.videocompressor.VideoController) = (r11v23 com.vincent.videocompressor.VideoController), (r11v22 com.vincent.videocompressor.VideoController), (r11v22 com.vincent.videocompressor.VideoController), (r11v0 com.vincent.videocompressor.VideoController), (r11v0 com.vincent.videocompressor.VideoController), (r11v0 com.vincent.videocompressor.VideoController), (r11v0 com.vincent.videocompressor.VideoController), (r11v0 com.vincent.videocompressor.VideoController), (r11v0 com.vincent.videocompressor.VideoController), (r11v0 com.vincent.videocompressor.VideoController), (r11v0 com.vincent.videocompressor.VideoController), (r11v0 com.vincent.videocompressor.VideoController), (r11v0 com.vincent.videocompressor.VideoController), (r11v0 com.vincent.videocompressor.VideoController) binds: [B:228:0x0439, B:185:0x038a, B:189:0x0394, B:171:0x0358, B:161:0x032b, B:162:?, B:146:0x02ef, B:147:?, B:148:0x02f8, B:149:?, B:136:0x02d5, B:137:?, B:109:0x024a, B:62:0x015f] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r33v9 com.vincent.videocompressor.MP4Builder) = (r33v5 com.vincent.videocompressor.MP4Builder), (r33v4 com.vincent.videocompressor.MP4Builder), (r33v4 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder), (r33v0 com.vincent.videocompressor.MP4Builder) binds: [B:228:0x0439, B:185:0x038a, B:189:0x0394, B:171:0x0358, B:161:0x032b, B:162:?, B:146:0x02ef, B:147:?, B:148:0x02f8, B:149:?, B:136:0x02d5, B:137:?, B:109:0x024a, B:62:0x015f] A[DONT_GENERATE, DONT_INLINE], Splitter:B:62:0x015f] */
    @android.annotation.TargetApi(16)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean convertVideo(java.lang.String r60, java.lang.String r61, int r62, com.vincent.videocompressor.VideoController.CompressProgressListener r63) {
        /*
            r59 = this;
            r11 = r59
            r1 = r60
            r2 = r63
            r11.path = r1
            android.media.MediaMetadataRetriever r1 = new android.media.MediaMetadataRetriever
            r1.<init>()
            java.lang.String r3 = r11.path
            r1.setDataSource(r3)
            r3 = 18
            java.lang.String r4 = r1.extractMetadata(r3)
            r5 = 19
            java.lang.String r5 = r1.extractMetadata(r5)
            r6 = 24
            java.lang.String r6 = r1.extractMetadata(r6)
            r7 = 9
            java.lang.String r1 = r1.extractMetadata(r7)
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            long r7 = r1.longValue()
            r9 = 1000(0x3e8, double:4.94E-321)
            long r7 = r7 * r9
            java.lang.Integer r1 = java.lang.Integer.valueOf(r6)
            int r1 = r1.intValue()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            int r4 = r4.intValue()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            int r5 = r5.intValue()
            r6 = 10
            r9 = 3
            switch(r62) {
                case 2: goto L_0x006c;
                case 3: goto L_0x005f;
                default: goto L_0x0054;
            }
        L_0x0054:
            int r10 = r4 * 2
            int r10 = r10 / r9
            int r12 = r5 * 2
            int r12 = r12 / r9
            int r13 = r10 * r12
            int r13 = r13 * 30
            goto L_0x0074
        L_0x005f:
            int r10 = r4 / 2
            int r12 = r5 / 2
            int r13 = r10 / 2
            int r14 = r12 / 2
            int r13 = r13 * r14
            int r13 = r13 * 10
            goto L_0x0074
        L_0x006c:
            int r10 = r4 / 2
            int r12 = r5 / 2
            int r13 = r10 * r12
            int r13 = r13 * 10
        L_0x0074:
            java.io.File r14 = new java.io.File
            r15 = r61
            r14.<init>(r15)
            int r15 = android.os.Build.VERSION.SDK_INT
            r6 = 90
            r17 = r7
            r7 = 0
            if (r15 >= r3) goto L_0x0091
            if (r12 <= r10) goto L_0x0091
            if (r10 == r4) goto L_0x0091
            if (r12 == r5) goto L_0x0091
            r1 = 270(0x10e, float:3.78E-43)
            r1 = 90
            r6 = 270(0x10e, float:3.78E-43)
            goto L_0x00b0
        L_0x0091:
            int r8 = android.os.Build.VERSION.SDK_INT
            r15 = 20
            if (r8 <= r15) goto L_0x00aa
            if (r1 != r6) goto L_0x009d
            r6 = 270(0x10e, float:3.78E-43)
        L_0x009b:
            r1 = 0
            goto L_0x00b0
        L_0x009d:
            r8 = 180(0xb4, float:2.52E-43)
            if (r1 != r8) goto L_0x00a5
            r6 = 180(0xb4, float:2.52E-43)
            r1 = 0
            goto L_0x00ab
        L_0x00a5:
            r8 = 270(0x10e, float:3.78E-43)
            if (r1 != r8) goto L_0x00aa
            goto L_0x009b
        L_0x00aa:
            r6 = 0
        L_0x00ab:
            r58 = r12
            r12 = r10
            r10 = r58
        L_0x00b0:
            java.io.File r15 = new java.io.File
            java.lang.String r8 = r11.path
            r15.<init>(r8)
            boolean r8 = r15.canRead()
            r26 = r6
            r6 = 1
            if (r8 != 0) goto L_0x00c4
            r11.didWriteData(r6, r6)
            return r7
        L_0x00c4:
            r11.videoConvertFirstWrite = r6
            long r27 = java.lang.System.currentTimeMillis()
            if (r12 == 0) goto L_0x0962
            if (r10 == 0) goto L_0x0962
            android.media.MediaCodec$BufferInfo r7 = new android.media.MediaCodec$BufferInfo     // Catch:{ Exception -> 0x088e, all -> 0x0884 }
            r7.<init>()     // Catch:{ Exception -> 0x088e, all -> 0x0884 }
            com.vincent.videocompressor.Mp4Movie r6 = new com.vincent.videocompressor.Mp4Movie     // Catch:{ Exception -> 0x088e, all -> 0x0884 }
            r6.<init>()     // Catch:{ Exception -> 0x088e, all -> 0x0884 }
            r6.setCacheFile(r14)     // Catch:{ Exception -> 0x088e, all -> 0x0884 }
            r6.setRotation(r1)     // Catch:{ Exception -> 0x088e, all -> 0x0884 }
            r6.setSize(r12, r10)     // Catch:{ Exception -> 0x088e, all -> 0x0884 }
            com.vincent.videocompressor.MP4Builder r1 = new com.vincent.videocompressor.MP4Builder     // Catch:{ Exception -> 0x088e, all -> 0x0884 }
            r1.<init>()     // Catch:{ Exception -> 0x088e, all -> 0x0884 }
            com.vincent.videocompressor.MP4Builder r6 = r1.createMovie(r6)     // Catch:{ Exception -> 0x088e, all -> 0x0884 }
            android.media.MediaExtractor r1 = new android.media.MediaExtractor     // Catch:{ Exception -> 0x087a, all -> 0x0873 }
            r1.<init>()     // Catch:{ Exception -> 0x087a, all -> 0x0873 }
            java.lang.String r8 = r15.toString()     // Catch:{ Exception -> 0x0868, all -> 0x085d }
            r1.setDataSource(r8)     // Catch:{ Exception -> 0x0868, all -> 0x085d }
            r31 = -1
            if (r12 != r4) goto L_0x0144
            if (r10 == r5) goto L_0x0102
            r33 = r6
            r4 = r7
            r8 = 0
            r7 = r1
            goto L_0x0149
        L_0x0102:
            r10 = 0
            r8 = -1
            r12 = -1
            r5 = r1
            r1 = r59
            r2 = r5
            r3 = r6
            r4 = r7
            r34 = r5
            r33 = r6
            r5 = r8
            r9 = r7
            r7 = r12
            r12 = r9
            r9 = r14
            long r1 = r1.readAndWriteTrack(r2, r3, r4, r5, r7, r9, r10)     // Catch:{ Exception -> 0x0137, all -> 0x012d }
            int r3 = (r1 > r31 ? 1 : (r1 == r31 ? 0 : -1))
            if (r3 == 0) goto L_0x0120
            r31 = r1
        L_0x0120:
            r5 = r12
            r42 = r14
            r40 = r15
            r55 = r33
            r7 = r34
            r35 = 0
            goto L_0x07fb
        L_0x012d:
            r0 = move-exception
            r1 = r0
            r2 = r11
            r11 = r33
            r54 = r34
        L_0x0134:
            r13 = 0
            goto L_0x092f
        L_0x0137:
            r0 = move-exception
            r1 = r0
            r42 = r14
            r40 = r15
            r11 = r33
            r8 = r34
        L_0x0141:
            r13 = 0
            goto L_0x0897
        L_0x0144:
            r33 = r6
            r4 = r7
            r7 = r1
            r8 = 0
        L_0x0149:
            int r1 = r11.selectTrack(r7, r8)     // Catch:{ Exception -> 0x0854, all -> 0x084f }
            if (r1 < 0) goto L_0x07f1
            java.lang.String r6 = android.os.Build.MANUFACTURER     // Catch:{ Exception -> 0x07a0, all -> 0x0794 }
            java.lang.String r6 = r6.toLowerCase()     // Catch:{ Exception -> 0x07a0, all -> 0x0794 }
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x07a0, all -> 0x0794 }
            r29 = 4
            r30 = 2
            if (r5 >= r3) goto L_0x0224
            java.lang.String r5 = "video/avc"
            android.media.MediaCodecInfo r5 = selectCodec(r5)     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            java.lang.String r3 = "video/avc"
            int r3 = selectColorFormat(r5, r3)     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            if (r3 == 0) goto L_0x0200
            java.lang.String r8 = r5.getName()     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            java.lang.String r9 = "OMX.qcom."
            boolean r9 = r8.contains(r9)     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            if (r9 == 0) goto L_0x0197
            int r8 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            r9 = 16
            if (r8 != r9) goto L_0x0192
            java.lang.String r8 = "lge"
            boolean r8 = r6.equals(r8)     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            if (r8 != 0) goto L_0x018d
            java.lang.String r8 = "nokia"
            boolean r8 = r6.equals(r8)     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            if (r8 == 0) goto L_0x0192
        L_0x018d:
            r37 = r3
            r8 = 1
        L_0x0190:
            r9 = 1
            goto L_0x01cb
        L_0x0192:
            r37 = r3
            r8 = 1
        L_0x0195:
            r9 = 0
            goto L_0x01cb
        L_0x0197:
            java.lang.String r9 = "OMX.Intel."
            boolean r9 = r8.contains(r9)     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            if (r9 == 0) goto L_0x01a3
            r37 = r3
            r8 = 2
            goto L_0x0195
        L_0x01a3:
            java.lang.String r9 = "OMX.MTK.VIDEO.ENCODER.AVC"
            boolean r9 = r8.equals(r9)     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            if (r9 == 0) goto L_0x01af
            r37 = r3
            r8 = 3
            goto L_0x0195
        L_0x01af:
            java.lang.String r9 = "OMX.SEC.AVC.Encoder"
            boolean r9 = r8.equals(r9)     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            if (r9 == 0) goto L_0x01bb
            r37 = r3
            r8 = 4
            goto L_0x0190
        L_0x01bb:
            java.lang.String r9 = "OMX.TI.DUCATI1.VIDEO.H264E"
            boolean r8 = r8.equals(r9)     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            if (r8 == 0) goto L_0x01c7
            r37 = r3
            r8 = 5
            goto L_0x0195
        L_0x01c7:
            r37 = r3
            r8 = 0
            goto L_0x0195
        L_0x01cb:
            java.lang.String r3 = "tmessages"
            r38 = r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            r8.<init>()     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            r39 = r9
            java.lang.String r9 = "codec = "
            r8.append(r9)     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            java.lang.String r5 = r5.getName()     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            r8.append(r5)     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            java.lang.String r5 = " manufacturer = "
            r8.append(r5)     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            r8.append(r6)     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            java.lang.String r5 = "device = "
            r8.append(r5)     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            java.lang.String r5 = android.os.Build.MODEL     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            r8.append(r5)     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            java.lang.String r5 = r8.toString()     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            android.util.Log.e(r3, r5)     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            r3 = r37
            r5 = r38
            goto L_0x022a
        L_0x0200:
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            java.lang.String r3 = "no supported color format"
            r2.<init>(r3)     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
            throw r2     // Catch:{ Exception -> 0x0211, all -> 0x0208 }
        L_0x0208:
            r0 = move-exception
            r1 = r0
            r54 = r7
            r2 = r11
            r11 = r33
            goto L_0x0134
        L_0x0211:
            r0 = move-exception
            r47 = r1
            r5 = r4
            r54 = r7
            r42 = r14
            r40 = r15
        L_0x021b:
            r55 = r33
            r8 = 0
            r9 = 0
        L_0x021f:
            r13 = 0
        L_0x0220:
            r14 = 0
        L_0x0221:
            r1 = r0
            goto L_0x07b1
        L_0x0224:
            r3 = 2130708361(0x7f000789, float:1.701803E38)
            r5 = 0
            r39 = 0
        L_0x022a:
            java.lang.String r8 = "tmessages"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x07a0, all -> 0x0794 }
            r9.<init>()     // Catch:{ Exception -> 0x07a0, all -> 0x0794 }
            r40 = r15
            java.lang.String r15 = "colorFormat = "
            r9.append(r15)     // Catch:{ Exception -> 0x078b, all -> 0x0794 }
            r9.append(r3)     // Catch:{ Exception -> 0x078b, all -> 0x0794 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x078b, all -> 0x0794 }
            android.util.Log.e(r8, r9)     // Catch:{ Exception -> 0x078b, all -> 0x0794 }
            int r8 = r12 * r10
            int r9 = r8 * 3
            int r9 = r9 / 2
            if (r5 != 0) goto L_0x026a
            int r5 = r10 % 16
            if (r5 == 0) goto L_0x025f
            int r5 = r10 % 16
            r6 = 16
            int r5 = 16 - r5
            int r5 = r5 + r10
            int r5 = r5 - r10
            int r5 = r5 * r12
            int r6 = r5 * 5
            int r6 = r6 / 4
            int r9 = r9 + r6
            r15 = 1
            goto L_0x02a0
        L_0x025f:
            r15 = 1
            goto L_0x029f
        L_0x0261:
            r0 = move-exception
            r47 = r1
            r5 = r4
            r54 = r7
            r42 = r14
            goto L_0x021b
        L_0x026a:
            r15 = 1
            if (r5 != r15) goto L_0x0280
            java.lang.String r5 = r6.toLowerCase()     // Catch:{ Exception -> 0x0261, all -> 0x0208 }
            java.lang.String r6 = "lge"
            boolean r5 = r5.equals(r6)     // Catch:{ Exception -> 0x0261, all -> 0x0208 }
            if (r5 != 0) goto L_0x029f
            int r5 = r8 + 2047
            r5 = r5 & -2048(0xfffffffffffff800, float:NaN)
            int r5 = r5 - r8
            int r9 = r9 + r5
            goto L_0x02a0
        L_0x0280:
            r8 = 5
            if (r5 != r8) goto L_0x0284
            goto L_0x029f
        L_0x0284:
            r8 = 3
            if (r5 != r8) goto L_0x029f
            java.lang.String r5 = "baidu"
            boolean r5 = r6.equals(r5)     // Catch:{ Exception -> 0x0261, all -> 0x0208 }
            if (r5 == 0) goto L_0x029f
            int r5 = r10 % 16
            r6 = 16
            int r5 = 16 - r5
            int r5 = r5 + r10
            int r5 = r5 - r10
            int r5 = r5 * r12
            int r6 = r5 * 5
            int r6 = r6 / 4
            int r9 = r9 + r6
            goto L_0x02a0
        L_0x029f:
            r5 = 0
        L_0x02a0:
            r7.selectTrack(r1)     // Catch:{ Exception -> 0x078b, all -> 0x0794 }
            r41 = r9
            r8 = 0
            r6 = 0
            r7.seekTo(r8, r6)     // Catch:{ Exception -> 0x078b, all -> 0x0794 }
            android.media.MediaFormat r6 = r7.getTrackFormat(r1)     // Catch:{ Exception -> 0x078b, all -> 0x0794 }
            java.lang.String r8 = "video/avc"
            android.media.MediaFormat r8 = android.media.MediaFormat.createVideoFormat(r8, r12, r10)     // Catch:{ Exception -> 0x078b, all -> 0x0794 }
            java.lang.String r9 = "color-format"
            r8.setInteger(r9, r3)     // Catch:{ Exception -> 0x078b, all -> 0x0794 }
            java.lang.String r9 = "bitrate"
            r8.setInteger(r9, r13)     // Catch:{ Exception -> 0x078b, all -> 0x0794 }
            java.lang.String r9 = "frame-rate"
            r13 = 25
            r8.setInteger(r9, r13)     // Catch:{ Exception -> 0x078b, all -> 0x0794 }
            java.lang.String r9 = "i-frame-interval"
            r13 = 10
            r8.setInteger(r9, r13)     // Catch:{ Exception -> 0x078b, all -> 0x0794 }
            int r9 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x078b, all -> 0x0794 }
            r13 = 18
            if (r9 >= r13) goto L_0x02df
            java.lang.String r9 = "stride"
            int r13 = r12 + 32
            r8.setInteger(r9, r13)     // Catch:{ Exception -> 0x0261, all -> 0x0208 }
            java.lang.String r9 = "slice-height"
            r8.setInteger(r9, r10)     // Catch:{ Exception -> 0x0261, all -> 0x0208 }
        L_0x02df:
            java.lang.String r9 = "video/avc"
            android.media.MediaCodec r9 = android.media.MediaCodec.createEncoderByType(r9)     // Catch:{ Exception -> 0x078b, all -> 0x0794 }
            r13 = 0
            r9.configure(r8, r13, r13, r15)     // Catch:{ Exception -> 0x077e, all -> 0x0794 }
            int r8 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x077e, all -> 0x0794 }
            r13 = 18
            if (r8 < r13) goto L_0x0315
            com.vincent.videocompressor.InputSurface r8 = new com.vincent.videocompressor.InputSurface     // Catch:{ Exception -> 0x0308, all -> 0x0208 }
            android.view.Surface r13 = r9.createInputSurface()     // Catch:{ Exception -> 0x0308, all -> 0x0208 }
            r8.<init>(r13)     // Catch:{ Exception -> 0x0308, all -> 0x0208 }
            r8.makeCurrent()     // Catch:{ Exception -> 0x02fc, all -> 0x0208 }
            goto L_0x0316
        L_0x02fc:
            r0 = move-exception
            r47 = r1
            r5 = r4
            r54 = r7
            r42 = r14
            r55 = r33
            goto L_0x021f
        L_0x0308:
            r0 = move-exception
            r47 = r1
            r5 = r4
            r54 = r7
            r42 = r14
            r55 = r33
            r8 = 0
            goto L_0x021f
        L_0x0315:
            r8 = 0
        L_0x0316:
            r9.start()     // Catch:{ Exception -> 0x0770, all -> 0x0794 }
            java.lang.String r13 = "mime"
            java.lang.String r13 = r6.getString(r13)     // Catch:{ Exception -> 0x0770, all -> 0x0794 }
            android.media.MediaCodec r13 = android.media.MediaCodec.createDecoderByType(r13)     // Catch:{ Exception -> 0x0770, all -> 0x0794 }
            int r15 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0762, all -> 0x0794 }
            r42 = r14
            r14 = 18
            if (r15 < r14) goto L_0x033b
            com.vincent.videocompressor.OutputSurface r14 = new com.vincent.videocompressor.OutputSurface     // Catch:{ Exception -> 0x0331, all -> 0x0208 }
            r14.<init>()     // Catch:{ Exception -> 0x0331, all -> 0x0208 }
            goto L_0x0342
        L_0x0331:
            r0 = move-exception
            r47 = r1
            r5 = r4
            r54 = r7
            r55 = r33
            goto L_0x0220
        L_0x033b:
            com.vincent.videocompressor.OutputSurface r14 = new com.vincent.videocompressor.OutputSurface     // Catch:{ Exception -> 0x0759, all -> 0x0794 }
            r15 = r26
            r14.<init>(r12, r10, r15)     // Catch:{ Exception -> 0x0759, all -> 0x0794 }
        L_0x0342:
            android.view.Surface r15 = r14.getSurface()     // Catch:{ Exception -> 0x074d, all -> 0x0794 }
            r44 = r3
            r43 = r5
            r3 = 0
            r5 = 0
            r13.configure(r6, r15, r3, r5)     // Catch:{ Exception -> 0x074d, all -> 0x0794 }
            r13.start()     // Catch:{ Exception -> 0x074d, all -> 0x0794 }
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x074d, all -> 0x0794 }
            r6 = 21
            if (r5 >= r6) goto L_0x037a
            java.nio.ByteBuffer[] r5 = r13.getInputBuffers()     // Catch:{ Exception -> 0x0372, all -> 0x0208 }
            java.nio.ByteBuffer[] r15 = r9.getOutputBuffers()     // Catch:{ Exception -> 0x0372, all -> 0x0208 }
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0372, all -> 0x0208 }
            r6 = 18
            if (r3 >= r6) goto L_0x036f
            java.nio.ByteBuffer[] r3 = r9.getInputBuffers()     // Catch:{ Exception -> 0x0372, all -> 0x0208 }
            r16 = r3
            r3 = 0
            r6 = 0
            goto L_0x0380
        L_0x036f:
            r3 = 0
            r6 = 0
            goto L_0x037e
        L_0x0372:
            r0 = move-exception
            r47 = r1
            r5 = r4
            r54 = r7
            goto L_0x0755
        L_0x037a:
            r3 = 0
            r5 = 0
            r6 = 0
            r15 = 0
        L_0x037e:
            r16 = 0
        L_0x0380:
            r36 = -5
        L_0x0382:
            if (r3 != 0) goto L_0x073b
            r45 = r3
            r46 = r4
            if (r6 != 0) goto L_0x0402
            int r3 = r7.getSampleTrackIndex()     // Catch:{ Exception -> 0x03f6, all -> 0x0208 }
            if (r3 != r1) goto L_0x03d5
            r47 = r1
            r1 = 2500(0x9c4, double:1.235E-320)
            int r3 = r13.dequeueInputBuffer(r1)     // Catch:{ Exception -> 0x03d3, all -> 0x0208 }
            if (r3 < 0) goto L_0x03dc
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x03d3, all -> 0x0208 }
            r2 = 21
            if (r1 >= r2) goto L_0x03a4
            r1 = r5[r3]     // Catch:{ Exception -> 0x03d3, all -> 0x0208 }
        L_0x03a2:
            r2 = 0
            goto L_0x03a9
        L_0x03a4:
            java.nio.ByteBuffer r1 = r13.getInputBuffer(r3)     // Catch:{ Exception -> 0x03d3, all -> 0x0208 }
            goto L_0x03a2
        L_0x03a9:
            int r22 = r7.readSampleData(r1, r2)     // Catch:{ Exception -> 0x03d3, all -> 0x0208 }
            if (r22 >= 0) goto L_0x03c0
            r21 = 0
            r22 = 0
            r23 = 0
            r25 = 4
            r19 = r13
            r20 = r3
            r19.queueInputBuffer(r20, r21, r22, r23, r25)     // Catch:{ Exception -> 0x03d3, all -> 0x0208 }
            r6 = 1
            goto L_0x03dc
        L_0x03c0:
            r21 = 0
            long r23 = r7.getSampleTime()     // Catch:{ Exception -> 0x03d3, all -> 0x0208 }
            r25 = 0
            r19 = r13
            r20 = r3
            r19.queueInputBuffer(r20, r21, r22, r23, r25)     // Catch:{ Exception -> 0x03d3, all -> 0x0208 }
            r7.advance()     // Catch:{ Exception -> 0x03d3, all -> 0x0208 }
            goto L_0x03dc
        L_0x03d3:
            r0 = move-exception
            goto L_0x03f9
        L_0x03d5:
            r47 = r1
            r1 = -1
            if (r3 != r1) goto L_0x03dc
            r1 = 1
            goto L_0x03dd
        L_0x03dc:
            r1 = 0
        L_0x03dd:
            if (r1 == 0) goto L_0x0404
            r1 = 2500(0x9c4, double:1.235E-320)
            int r20 = r13.dequeueInputBuffer(r1)     // Catch:{ Exception -> 0x03d3, all -> 0x0208 }
            if (r20 < 0) goto L_0x0404
            r21 = 0
            r22 = 0
            r23 = 0
            r25 = 4
            r19 = r13
            r19.queueInputBuffer(r20, r21, r22, r23, r25)     // Catch:{ Exception -> 0x03d3, all -> 0x0208 }
            r6 = 1
            goto L_0x0404
        L_0x03f6:
            r0 = move-exception
            r47 = r1
        L_0x03f9:
            r1 = r0
            r54 = r7
            r55 = r33
            r5 = r46
            goto L_0x07b1
        L_0x0402:
            r47 = r1
        L_0x0404:
            r4 = r36
            r3 = r45
            r1 = 1
            r2 = 1
        L_0x040a:
            if (r1 != 0) goto L_0x0419
            if (r2 == 0) goto L_0x040f
            goto L_0x0419
        L_0x040f:
            r36 = r4
            r4 = r46
            r1 = r47
            r2 = r63
            goto L_0x0382
        L_0x0419:
            r49 = r1
            r50 = r2
            r51 = r3
            r48 = r5
            r5 = r46
            r1 = 2500(0x9c4, double:1.235E-320)
            int r3 = r9.dequeueOutputBuffer(r5, r1)     // Catch:{ Exception -> 0x0739, all -> 0x0794 }
            r1 = -1
            if (r3 != r1) goto L_0x0436
            r52 = r6
            r54 = r7
            r2 = r33
            r50 = 0
            goto L_0x0554
        L_0x0436:
            r1 = -3
            if (r3 != r1) goto L_0x0454
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x044c, all -> 0x0208 }
            r2 = 21
            if (r1 >= r2) goto L_0x0443
            java.nio.ByteBuffer[] r15 = r9.getOutputBuffers()     // Catch:{ Exception -> 0x044c, all -> 0x0208 }
        L_0x0443:
            r52 = r6
            r54 = r7
            r2 = r33
        L_0x0449:
            r1 = -1
            goto L_0x0554
        L_0x044c:
            r0 = move-exception
            r1 = r0
            r54 = r7
            r55 = r33
            goto L_0x07b1
        L_0x0454:
            r1 = -2
            if (r3 != r1) goto L_0x0488
            android.media.MediaFormat r1 = r9.getOutputFormat()     // Catch:{ Exception -> 0x044c, all -> 0x047a }
            r2 = -5
            if (r4 != r2) goto L_0x0473
            r52 = r6
            r2 = r33
            r6 = 0
            int r1 = r2.addTrack(r1, r6)     // Catch:{ Exception -> 0x046b, all -> 0x0469 }
            r4 = r1
            goto L_0x0477
        L_0x0469:
            r0 = move-exception
            goto L_0x047d
        L_0x046b:
            r0 = move-exception
            r1 = r0
            r55 = r2
            r54 = r7
            goto L_0x07b1
        L_0x0473:
            r52 = r6
            r2 = r33
        L_0x0477:
            r54 = r7
            goto L_0x0449
        L_0x047a:
            r0 = move-exception
            r2 = r33
        L_0x047d:
            r1 = r0
            r54 = r7
            r13 = 0
            r58 = r11
            r11 = r2
            r2 = r58
            goto L_0x092f
        L_0x0488:
            r52 = r6
            r2 = r33
            if (r3 < 0) goto L_0x0713
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x070d, all -> 0x0706 }
            r6 = 21
            if (r1 >= r6) goto L_0x0497
            r1 = r15[r3]     // Catch:{ Exception -> 0x046b, all -> 0x0469 }
            goto L_0x049b
        L_0x0497:
            java.nio.ByteBuffer r1 = r9.getOutputBuffer(r3)     // Catch:{ Exception -> 0x070d, all -> 0x0706 }
        L_0x049b:
            if (r1 == 0) goto L_0x06e4
            int r6 = r5.size     // Catch:{ Exception -> 0x070d, all -> 0x0706 }
            r53 = r15
            r15 = 1
            if (r6 <= r15) goto L_0x053e
            int r6 = r5.flags     // Catch:{ Exception -> 0x0539, all -> 0x0534 }
            r6 = r6 & 2
            if (r6 != 0) goto L_0x04b6
            r6 = 0
            boolean r1 = r2.writeSampleData(r4, r1, r5, r6)     // Catch:{ Exception -> 0x046b, all -> 0x0469 }
            if (r1 == 0) goto L_0x053e
            r11.didWriteData(r6, r6)     // Catch:{ Exception -> 0x046b, all -> 0x0469 }
            goto L_0x053e
        L_0x04b6:
            r6 = -5
            if (r4 != r6) goto L_0x053e
            int r4 = r5.size     // Catch:{ Exception -> 0x0539, all -> 0x0534 }
            byte[] r4 = new byte[r4]     // Catch:{ Exception -> 0x0539, all -> 0x0534 }
            int r6 = r5.offset     // Catch:{ Exception -> 0x0539, all -> 0x0534 }
            int r15 = r5.size     // Catch:{ Exception -> 0x0539, all -> 0x0534 }
            int r6 = r6 + r15
            r1.limit(r6)     // Catch:{ Exception -> 0x0539, all -> 0x0534 }
            int r6 = r5.offset     // Catch:{ Exception -> 0x0539, all -> 0x0534 }
            r1.position(r6)     // Catch:{ Exception -> 0x0539, all -> 0x0534 }
            r1.get(r4)     // Catch:{ Exception -> 0x0539, all -> 0x0534 }
            int r1 = r5.size     // Catch:{ Exception -> 0x0539, all -> 0x0534 }
            r6 = 1
            int r1 = r1 - r6
        L_0x04d1:
            if (r1 < 0) goto L_0x0516
            r15 = 3
            if (r1 <= r15) goto L_0x0516
            byte r15 = r4[r1]     // Catch:{ Exception -> 0x0539, all -> 0x0534 }
            if (r15 != r6) goto L_0x050c
            int r6 = r1 + -1
            byte r6 = r4[r6]     // Catch:{ Exception -> 0x0539, all -> 0x0534 }
            if (r6 != 0) goto L_0x050c
            int r6 = r1 + -2
            byte r6 = r4[r6]     // Catch:{ Exception -> 0x0539, all -> 0x0534 }
            if (r6 != 0) goto L_0x050c
            int r6 = r1 + -3
            byte r15 = r4[r6]     // Catch:{ Exception -> 0x0539, all -> 0x0534 }
            if (r15 != 0) goto L_0x050c
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r6)     // Catch:{ Exception -> 0x0539, all -> 0x0534 }
            int r15 = r5.size     // Catch:{ Exception -> 0x0539, all -> 0x0534 }
            int r15 = r15 - r6
            java.nio.ByteBuffer r15 = java.nio.ByteBuffer.allocate(r15)     // Catch:{ Exception -> 0x0539, all -> 0x0534 }
            r54 = r7
            r11 = 0
            java.nio.ByteBuffer r7 = r1.put(r4, r11, r6)     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
            r7.position(r11)     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
            int r7 = r5.size     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
            int r7 = r7 - r6
            java.nio.ByteBuffer r4 = r15.put(r4, r6, r7)     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
            r4.position(r11)     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
            goto L_0x051a
        L_0x050c:
            r54 = r7
            int r1 = r1 + -1
            r7 = r54
            r6 = 1
            r11 = r59
            goto L_0x04d1
        L_0x0516:
            r54 = r7
            r1 = 0
            r15 = 0
        L_0x051a:
            java.lang.String r4 = "video/avc"
            android.media.MediaFormat r4 = android.media.MediaFormat.createVideoFormat(r4, r12, r10)     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
            if (r1 == 0) goto L_0x052e
            if (r15 == 0) goto L_0x052e
            java.lang.String r6 = "csd-0"
            r4.setByteBuffer(r6, r1)     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
            java.lang.String r1 = "csd-1"
            r4.setByteBuffer(r1, r15)     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
        L_0x052e:
            r1 = 0
            int r4 = r2.addTrack(r4, r1)     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
            goto L_0x0540
        L_0x0534:
            r0 = move-exception
            r54 = r7
            goto L_0x05ae
        L_0x0539:
            r0 = move-exception
            r54 = r7
            goto L_0x05b3
        L_0x053e:
            r54 = r7
        L_0x0540:
            int r1 = r5.flags     // Catch:{ Exception -> 0x06dd, all -> 0x06d8 }
            r1 = r1 & 4
            if (r1 == 0) goto L_0x0549
            r1 = 1
        L_0x0547:
            r6 = 0
            goto L_0x054b
        L_0x0549:
            r1 = 0
            goto L_0x0547
        L_0x054b:
            r9.releaseOutputBuffer(r3, r6)     // Catch:{ Exception -> 0x06dd, all -> 0x06d8 }
            r51 = r1
            r15 = r53
            goto L_0x0449
        L_0x0554:
            if (r3 == r1) goto L_0x056a
            r33 = r2
            r46 = r5
            r5 = r48
            r1 = r49
            r2 = r50
            r3 = r51
            r6 = r52
            r7 = r54
        L_0x0566:
            r11 = r59
            goto L_0x040a
        L_0x056a:
            r6 = 2500(0x9c4, double:1.235E-320)
            int r3 = r13.dequeueOutputBuffer(r5, r6)     // Catch:{ Exception -> 0x06dd, all -> 0x06d8 }
            if (r3 != r1) goto L_0x0580
            r55 = r2
            r57 = r4
            r56 = r8
            r7 = r17
            r1 = r63
        L_0x057c:
            r49 = 0
            goto L_0x06a5
        L_0x0580:
            r6 = -3
            if (r3 != r6) goto L_0x0584
            goto L_0x05a1
        L_0x0584:
            r6 = -2
            if (r3 != r6) goto L_0x05b8
            android.media.MediaFormat r3 = r13.getOutputFormat()     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
            java.lang.String r6 = "tmessages"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
            r7.<init>()     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
            java.lang.String r11 = "newFormat = "
            r7.append(r11)     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
            r7.append(r3)     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
            java.lang.String r3 = r7.toString()     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
            android.util.Log.e(r6, r3)     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
        L_0x05a1:
            r55 = r2
            r57 = r4
            r56 = r8
            r7 = r17
            r1 = r63
            goto L_0x06a5
        L_0x05ad:
            r0 = move-exception
        L_0x05ae:
            r1 = r0
            r11 = r2
            goto L_0x079c
        L_0x05b2:
            r0 = move-exception
        L_0x05b3:
            r1 = r0
            r55 = r2
            goto L_0x07b1
        L_0x05b8:
            if (r3 < 0) goto L_0x06bd
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x06dd, all -> 0x06d8 }
            r7 = 18
            if (r6 < r7) goto L_0x05c8
            int r6 = r5.size     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
            if (r6 == 0) goto L_0x05c6
        L_0x05c4:
            r6 = 1
            goto L_0x05d5
        L_0x05c6:
            r6 = 0
            goto L_0x05d5
        L_0x05c8:
            int r6 = r5.size     // Catch:{ Exception -> 0x06dd, all -> 0x06d8 }
            if (r6 != 0) goto L_0x05c4
            long r6 = r5.presentationTimeUs     // Catch:{ Exception -> 0x05b2, all -> 0x05ad }
            r19 = 0
            int r11 = (r6 > r19 ? 1 : (r6 == r19 ? 0 : -1))
            if (r11 == 0) goto L_0x05c6
            goto L_0x05c4
        L_0x05d5:
            r13.releaseOutputBuffer(r3, r6)     // Catch:{ Exception -> 0x06dd, all -> 0x06d8 }
            if (r6 == 0) goto L_0x0666
            r14.awaitNewImage()     // Catch:{ Exception -> 0x05df, all -> 0x05ad }
            r3 = 0
            goto L_0x05eb
        L_0x05df:
            r0 = move-exception
            r3 = r0
            java.lang.String r6 = "tmessages"
            java.lang.String r3 = r3.getMessage()     // Catch:{ Exception -> 0x06dd, all -> 0x06d8 }
            android.util.Log.e(r6, r3)     // Catch:{ Exception -> 0x06dd, all -> 0x06d8 }
            r3 = 1
        L_0x05eb:
            if (r3 != 0) goto L_0x0666
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x06dd, all -> 0x06d8 }
            r6 = 18
            if (r3 < r6) goto L_0x0622
            r7 = 0
            r14.drawImage(r7)     // Catch:{ Exception -> 0x06dd, all -> 0x06d8 }
            r55 = r2
            long r1 = r5.presentationTimeUs     // Catch:{ Exception -> 0x061f, all -> 0x0730 }
            r19 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 * r19
            r8.setPresentationTime(r1)     // Catch:{ Exception -> 0x061f, all -> 0x0730 }
            r1 = r63
            if (r1 == 0) goto L_0x0617
            long r2 = r5.presentationTimeUs     // Catch:{ Exception -> 0x061f, all -> 0x0730 }
            float r2 = (float) r2
            r56 = r8
            r7 = r17
            float r3 = (float) r7
            float r2 = r2 / r3
            r3 = 1120403456(0x42c80000, float:100.0)
            float r2 = r2 * r3
            r1.onProgress(r2)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            goto L_0x061b
        L_0x0617:
            r56 = r8
            r7 = r17
        L_0x061b:
            r56.swapBuffers()     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            goto L_0x066e
        L_0x061f:
            r0 = move-exception
            goto L_0x06e0
        L_0x0622:
            r55 = r2
            r56 = r8
            r7 = r17
            r1 = r63
            r2 = 2500(0x9c4, double:1.235E-320)
            int r6 = r9.dequeueInputBuffer(r2)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            if (r6 < 0) goto L_0x065e
            r2 = 1
            r14.drawImage(r2)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            java.nio.ByteBuffer r19 = r14.getFrame()     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r20 = r16[r6]     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r20.clear()     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r21 = r44
            r22 = r12
            r23 = r10
            r24 = r43
            r25 = r39
            convertVideoFrame(r19, r20, r21, r22, r23, r24, r25)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r21 = 0
            long r2 = r5.presentationTimeUs     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r25 = 0
            r19 = r9
            r20 = r6
            r22 = r41
            r23 = r2
            r19.queueInputBuffer(r20, r21, r22, r23, r25)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            goto L_0x066e
        L_0x065e:
            java.lang.String r2 = "tmessages"
            java.lang.String r3 = "input buffer not available"
            android.util.Log.e(r2, r3)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            goto L_0x066e
        L_0x0666:
            r55 = r2
            r56 = r8
            r7 = r17
            r1 = r63
        L_0x066e:
            int r2 = r5.flags     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r2 = r2 & 4
            if (r2 == 0) goto L_0x06a3
            java.lang.String r2 = "tmessages"
            java.lang.String r3 = "decoder stream end"
            android.util.Log.e(r2, r3)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r3 = 18
            if (r2 < r3) goto L_0x0688
            r9.signalEndOfInputStream()     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r57 = r4
            goto L_0x057c
        L_0x0688:
            r57 = r4
            r3 = 2500(0x9c4, double:1.235E-320)
            int r20 = r9.dequeueInputBuffer(r3)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            if (r20 < 0) goto L_0x057c
            r21 = 0
            r22 = 1
            long r3 = r5.presentationTimeUs     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r25 = 4
            r19 = r9
            r23 = r3
            r19.queueInputBuffer(r20, r21, r22, r23, r25)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            goto L_0x057c
        L_0x06a3:
            r57 = r4
        L_0x06a5:
            r46 = r5
            r17 = r7
            r5 = r48
            r1 = r49
            r2 = r50
            r3 = r51
            r6 = r52
            r7 = r54
            r33 = r55
            r8 = r56
            r4 = r57
            goto L_0x0566
        L_0x06bd:
            r55 = r2
            r56 = r8
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r2.<init>()     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            java.lang.String r4 = "unexpected result from decoder.dequeueOutputBuffer: "
            r2.append(r4)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r2.append(r3)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            throw r1     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
        L_0x06d8:
            r0 = move-exception
            r55 = r2
            goto L_0x0799
        L_0x06dd:
            r0 = move-exception
            r55 = r2
        L_0x06e0:
            r56 = r8
            goto L_0x0221
        L_0x06e4:
            r55 = r2
            r54 = r7
            r56 = r8
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r2.<init>()     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            java.lang.String r4 = "encoderOutputBuffer "
            r2.append(r4)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r2.append(r3)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            java.lang.String r3 = " was null"
            r2.append(r3)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            throw r1     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
        L_0x0706:
            r0 = move-exception
            r55 = r2
            r54 = r7
            goto L_0x0799
        L_0x070d:
            r0 = move-exception
            r55 = r2
            r54 = r7
            goto L_0x06e0
        L_0x0713:
            r55 = r2
            r54 = r7
            r56 = r8
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r2.<init>()     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            java.lang.String r4 = "unexpected result from encoder.dequeueOutputBuffer: "
            r2.append(r4)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r2.append(r3)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
            throw r1     // Catch:{ Exception -> 0x0733, all -> 0x0730 }
        L_0x0730:
            r0 = move-exception
            goto L_0x0799
        L_0x0733:
            r0 = move-exception
            r1 = r0
            r8 = r56
            goto L_0x07b1
        L_0x0739:
            r0 = move-exception
            goto L_0x0751
        L_0x073b:
            r47 = r1
            r5 = r4
            r54 = r7
            r56 = r8
            r55 = r33
            r1 = r47
            r7 = r54
            r8 = r56
            r2 = 0
            goto L_0x07bf
        L_0x074d:
            r0 = move-exception
            r47 = r1
            r5 = r4
        L_0x0751:
            r54 = r7
            r56 = r8
        L_0x0755:
            r55 = r33
            goto L_0x0221
        L_0x0759:
            r0 = move-exception
            r47 = r1
            r5 = r4
            r54 = r7
            r56 = r8
            goto L_0x076c
        L_0x0762:
            r0 = move-exception
            r47 = r1
            r5 = r4
            r54 = r7
            r56 = r8
            r42 = r14
        L_0x076c:
            r55 = r33
            r1 = r0
            goto L_0x07b0
        L_0x0770:
            r0 = move-exception
            r47 = r1
            r5 = r4
            r54 = r7
            r56 = r8
            r42 = r14
            r55 = r33
            r1 = r0
            goto L_0x07af
        L_0x077e:
            r0 = move-exception
            r47 = r1
            r5 = r4
            r54 = r7
            r42 = r14
            r55 = r33
            r1 = r0
            r8 = 0
            goto L_0x07af
        L_0x078b:
            r0 = move-exception
            r47 = r1
            r5 = r4
            r54 = r7
            r42 = r14
            goto L_0x07aa
        L_0x0794:
            r0 = move-exception
            r54 = r7
            r55 = r33
        L_0x0799:
            r1 = r0
        L_0x079a:
            r11 = r55
        L_0x079c:
            r2 = r59
            goto L_0x0134
        L_0x07a0:
            r0 = move-exception
            r47 = r1
            r5 = r4
            r54 = r7
            r42 = r14
            r40 = r15
        L_0x07aa:
            r55 = r33
            r1 = r0
            r8 = 0
            r9 = 0
        L_0x07af:
            r13 = 0
        L_0x07b0:
            r14 = 0
        L_0x07b1:
            java.lang.String r2 = "tmessages"
            java.lang.String r1 = r1.getMessage()     // Catch:{ Exception -> 0x07e8, all -> 0x07e4 }
            android.util.Log.e(r2, r1)     // Catch:{ Exception -> 0x07e8, all -> 0x07e4 }
            r1 = r47
            r7 = r54
            r2 = 1
        L_0x07bf:
            r7.unselectTrack(r1)     // Catch:{ Exception -> 0x07e2, all -> 0x07dd }
            if (r14 == 0) goto L_0x07c7
            r14.release()     // Catch:{ Exception -> 0x07e2, all -> 0x07dd }
        L_0x07c7:
            if (r8 == 0) goto L_0x07cc
            r8.release()     // Catch:{ Exception -> 0x07e2, all -> 0x07dd }
        L_0x07cc:
            if (r13 == 0) goto L_0x07d4
            r13.stop()     // Catch:{ Exception -> 0x07e2, all -> 0x07dd }
            r13.release()     // Catch:{ Exception -> 0x07e2, all -> 0x07dd }
        L_0x07d4:
            if (r9 == 0) goto L_0x07f9
            r9.stop()     // Catch:{ Exception -> 0x07e2, all -> 0x07dd }
            r9.release()     // Catch:{ Exception -> 0x07e2, all -> 0x07dd }
            goto L_0x07f9
        L_0x07dd:
            r0 = move-exception
            r1 = r0
            r54 = r7
            goto L_0x079a
        L_0x07e2:
            r0 = move-exception
            goto L_0x07eb
        L_0x07e4:
            r0 = move-exception
            r7 = r54
            goto L_0x0799
        L_0x07e8:
            r0 = move-exception
            r7 = r54
        L_0x07eb:
            r1 = r0
            r8 = r7
            r11 = r55
            goto L_0x0141
        L_0x07f1:
            r5 = r4
            r42 = r14
            r40 = r15
            r55 = r33
            r2 = 0
        L_0x07f9:
            r35 = r2
        L_0x07fb:
            if (r35 != 0) goto L_0x0817
            r10 = 1
            r8 = -1
            r1 = r59
            r11 = r55
            r2 = r7
            r3 = r11
            r4 = r5
            r5 = r31
            r12 = r7
            r13 = 0
            r7 = r8
            r9 = r42
            r1.readAndWriteTrack(r2, r3, r4, r5, r7, r9, r10)     // Catch:{ Exception -> 0x0814, all -> 0x0812 }
            goto L_0x081b
        L_0x0812:
            r0 = move-exception
            goto L_0x0861
        L_0x0814:
            r0 = move-exception
            goto L_0x0870
        L_0x0817:
            r12 = r7
            r11 = r55
            r13 = 0
        L_0x081b:
            r12.release()
            if (r11 == 0) goto L_0x082f
            r11.finishMovie(r13)     // Catch:{ Exception -> 0x0824 }
            goto L_0x082f
        L_0x0824:
            r0 = move-exception
            r1 = r0
            java.lang.String r2 = "tmessages"
            java.lang.String r1 = r1.getMessage()
            android.util.Log.e(r2, r1)
        L_0x082f:
            java.lang.String r1 = "tmessages"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "time = "
            r2.append(r3)
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r27
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r1, r2)
            r1 = r35
            goto L_0x08d3
        L_0x084f:
            r0 = move-exception
            r12 = r7
            r11 = r33
            goto L_0x0860
        L_0x0854:
            r0 = move-exception
            r12 = r7
            r42 = r14
            r40 = r15
            r11 = r33
            goto L_0x086f
        L_0x085d:
            r0 = move-exception
            r12 = r1
            r11 = r6
        L_0x0860:
            r13 = 0
        L_0x0861:
            r1 = r0
            r54 = r12
            r2 = r59
            goto L_0x092f
        L_0x0868:
            r0 = move-exception
            r12 = r1
            r11 = r6
            r42 = r14
            r40 = r15
        L_0x086f:
            r13 = 0
        L_0x0870:
            r1 = r0
            r8 = r12
            goto L_0x0897
        L_0x0873:
            r0 = move-exception
            r11 = r6
            r13 = 0
            r1 = r0
            r2 = r59
            goto L_0x088a
        L_0x087a:
            r0 = move-exception
            r11 = r6
            r42 = r14
            r40 = r15
            r13 = 0
            r1 = r0
            r8 = 0
            goto L_0x0897
        L_0x0884:
            r0 = move-exception
            r13 = 0
            r1 = r0
            r2 = r59
            r11 = 0
        L_0x088a:
            r54 = 0
            goto L_0x092f
        L_0x088e:
            r0 = move-exception
            r42 = r14
            r40 = r15
            r13 = 0
            r1 = r0
            r8 = 0
            r11 = 0
        L_0x0897:
            java.lang.String r2 = "tmessages"
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0929 }
            android.util.Log.e(r2, r1)     // Catch:{ all -> 0x0929 }
            if (r8 == 0) goto L_0x08a5
            r8.release()
        L_0x08a5:
            if (r11 == 0) goto L_0x08b6
            r11.finishMovie(r13)     // Catch:{ Exception -> 0x08ab }
            goto L_0x08b6
        L_0x08ab:
            r0 = move-exception
            r1 = r0
            java.lang.String r2 = "tmessages"
            java.lang.String r1 = r1.getMessage()
            android.util.Log.e(r2, r1)
        L_0x08b6:
            java.lang.String r1 = "tmessages"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "time = "
            r2.append(r3)
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r27
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r1, r2)
            r1 = 1
        L_0x08d3:
            r2 = r59
            r3 = 1
            r2.didWriteData(r3, r1)
            cachedFile = r42
            java.lang.String r1 = "ViratPath"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r2.path
            r3.append(r4)
            java.lang.String r4 = ""
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r1, r3)
            java.lang.String r1 = "ViratPath"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r42.getPath()
            r3.append(r4)
            java.lang.String r4 = ""
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r1, r3)
            java.lang.String r1 = "ViratPath"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r40.getPath()
            r3.append(r4)
            java.lang.String r4 = ""
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r1, r3)
            r1 = 1
            return r1
        L_0x0929:
            r0 = move-exception
            r2 = r59
            r1 = r0
            r54 = r8
        L_0x092f:
            if (r54 == 0) goto L_0x0934
            r54.release()
        L_0x0934:
            if (r11 == 0) goto L_0x0945
            r11.finishMovie(r13)     // Catch:{ Exception -> 0x093a }
            goto L_0x0945
        L_0x093a:
            r0 = move-exception
            r3 = r0
            java.lang.String r3 = r3.getMessage()
            java.lang.String r4 = "tmessages"
            android.util.Log.e(r4, r3)
        L_0x0945:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "time = "
            r3.append(r4)
            long r4 = java.lang.System.currentTimeMillis()
            long r4 = r4 - r27
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "tmessages"
            android.util.Log.e(r4, r3)
            throw r1
        L_0x0962:
            r2 = r11
            r13 = 0
            r1 = 1
            r2.didWriteData(r1, r1)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vincent.videocompressor.VideoController.convertVideo(java.lang.String, java.lang.String, int, com.vincent.videocompressor.VideoController$CompressProgressListener):boolean");
    }

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
}
