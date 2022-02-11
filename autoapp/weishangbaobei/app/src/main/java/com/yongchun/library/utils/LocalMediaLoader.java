package com.yongchun.library.utils;

import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.yongchun.library.R;
import com.yongchun.library.model.LocalMedia;
import com.yongchun.library.model.LocalMediaFolder;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class LocalMediaLoader {
    /* access modifiers changed from: private */
    public static final String[] IMAGE_PROJECTION = {"_data", "_display_name", "date_added", "_id"};
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_IMAGE_VIDEO = 3;
    public static final int TYPE_VIDEO = 2;
    /* access modifiers changed from: private */
    public static final String[] VIDEO_PROJECTION = {"_data", "_display_name", "date_added", "_id", "duration"};
    /* access modifiers changed from: private */
    public FragmentActivity activity;
    HashSet<String> mDirPaths = new HashSet<>();
    /* access modifiers changed from: private */
    public int type = 1;

    public interface LocalMediaLoadListener {
        void loadComplete(List<LocalMediaFolder> list);
    }

    public LocalMediaLoader(FragmentActivity fragmentActivity, int i) {
        this.activity = fragmentActivity;
        this.type = i;
    }

    public void loadAllImage(final LocalMediaLoadListener localMediaLoadListener) {
        this.activity.getSupportLoaderManager().initLoader(this.type, (Bundle) null, new LoaderManager.LoaderCallbacks<Cursor>() {
            public void onLoaderReset(Loader<Cursor> loader) {
            }

            public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
                int i2 = i;
                if (i2 == 1) {
                    FragmentActivity access$000 = LocalMediaLoader.this.activity;
                    Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    String[] access$100 = LocalMediaLoader.IMAGE_PROJECTION;
                    return new CursorLoader(access$000, uri, access$100, (String) null, (String[]) null, LocalMediaLoader.IMAGE_PROJECTION[2] + " DESC");
                } else if (i2 == 2) {
                    FragmentActivity access$0002 = LocalMediaLoader.this.activity;
                    Uri uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    String[] access$200 = LocalMediaLoader.VIDEO_PROJECTION;
                    return new CursorLoader(access$0002, uri2, access$200, (String) null, (String[]) null, LocalMediaLoader.VIDEO_PROJECTION[2] + " DESC");
                } else if (i2 != 3) {
                    return null;
                } else {
                    FragmentActivity access$0003 = LocalMediaLoader.this.activity;
                    Uri uri3 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    String[] access$1002 = LocalMediaLoader.IMAGE_PROJECTION;
                    return new CursorLoader(access$0003, uri3, access$1002, (String) null, (String[]) null, LocalMediaLoader.IMAGE_PROJECTION[2] + " DESC");
                }
            }

            public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                File parentFile;
                Cursor cursor2 = cursor;
                ArrayList arrayList = new ArrayList();
                LocalMediaFolder localMediaFolder = new LocalMediaFolder();
                ArrayList arrayList2 = new ArrayList();
                while (true) {
                    if (cursor2 == null || !cursor.moveToNext()) {
                        try {
                            LocalMediaLoader.this.sortImages(arrayList2);
                        } catch (Exception unused) {
                        }
                        localMediaFolder.setImages(arrayList2);
                        localMediaFolder.setImageNum(localMediaFolder.getImages().size());
                        if (arrayList2.size() > 0) {
                            localMediaFolder.setFirstImagePath(((LocalMedia) arrayList2.get(0)).getPath());
                        }
                        if (LocalMediaLoader.this.type == 1) {
                            localMediaFolder.setName(LocalMediaLoader.this.activity.getString(R.string.all_image));
                        } else if (LocalMediaLoader.this.type == 2) {
                            localMediaFolder.setName(LocalMediaLoader.this.activity.getString(R.string.all_videos));
                        } else {
                            localMediaFolder.setName(LocalMediaLoader.this.activity.getString(R.string.all_images_video));
                        }
                        arrayList.add(localMediaFolder);
                        try {
                            LocalMediaLoader.this.sortFolder(arrayList);
                        } catch (Exception unused2) {
                        }
                        localMediaLoadListener.loadComplete(arrayList);
                        if (Integer.parseInt(Build.VERSION.SDK) < 14 && cursor2 != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    }
                    String string = cursor2.getString(cursor2.getColumnIndex("_data"));
                    File file = new File(string);
                    if (file.exists() && (parentFile = file.getParentFile()) != null && parentFile.exists()) {
                        LocalMediaFolder access$300 = LocalMediaLoader.this.getImageFolder(string, arrayList);
                        if (!LocalMediaLoader.this.mDirPaths.contains(access$300.getName())) {
                            LocalMediaLoader.this.mDirPaths.add(access$300.getName());
                            if (parentFile.list() != null) {
                                File[] listFiles = parentFile.listFiles(new FilenameFilter() {
                                    public boolean accept(File file, String str) {
                                        return LocalMediaLoader.this.type == 1 ? str.endsWith(".jpg") || str.endsWith(PictureMimeType.PNG) || str.endsWith(".jpeg") || str.endsWith(".JPEG") || str.endsWith(".bmp") : LocalMediaLoader.this.type == 2 ? str.endsWith(PictureFileUtils.POST_VIDEO) : LocalMediaLoader.this.type == 3 ? str.endsWith(".jpg") || str.endsWith(PictureMimeType.PNG) || str.endsWith(".jpeg") || str.endsWith(PictureFileUtils.POST_VIDEO) || str.endsWith(".JPEG") || str.endsWith(".bmp") : str.endsWith(".jpg") || str.endsWith(PictureMimeType.PNG) || str.endsWith(".jpeg");
                                    }
                                });
                                ArrayList arrayList3 = new ArrayList();
                                for (File file2 : listFiles) {
                                    long j = 0;
                                    if (file2.getName().endsWith(PictureFileUtils.POST_VIDEO)) {
                                        try {
                                            j = (long) Math.round((float) LocalMediaLoader.this.getDuration(file2));
                                            System.out.println("WS_BABY_" + j);
                                        } catch (Exception unused3) {
                                            j = 1500;
                                        }
                                    }
                                    LocalMedia localMedia = new LocalMedia(file2.getAbsolutePath(), file2.lastModified(), j);
                                    arrayList2.add(localMedia);
                                    arrayList3.add(localMedia);
                                }
                                try {
                                    LocalMediaLoader.this.sortImages(arrayList3);
                                } catch (Exception unused4) {
                                }
                                if (arrayList3.size() > 0) {
                                    access$300.setImages(arrayList3);
                                    access$300.setImageNum(access$300.getImages().size());
                                    arrayList.add(access$300);
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        java.lang.System.out.println("BIG_VIDEO_INFO_ERROR0");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        java.lang.System.out.println("BIG_VIDEO_INFO_ERROR1");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:36:0x0126 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:38:0x012e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized long getDuration(java.io.File r12) {
        /*
            r11 = this;
            monitor-enter(r11)
            r0 = 0
            java.lang.String r2 = r12.getName()     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.String r3 = r12.getName()     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.String r4 = "."
            int r3 = r3.lastIndexOf(r4)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            r4 = 0
            java.lang.String r2 = r2.substring(r4, r3)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            android.support.v4.app.FragmentActivity r3 = r11.activity     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            r5 = -1
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.Object r3 = com.yongchun.library.utils.SPUtils.get(r3, r2, r5)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            long r5 = r3.longValue()     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            int r3 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x0044
            java.io.PrintStream r12 = java.lang.System.out     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            r2.<init>()     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.String r3 = "BIG_VIDEO_INFO_duration0_"
            r2.append(r3)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            r2.append(r5)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            r12.println(r2)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            monitor-exit(r11)
            return r5
        L_0x0044:
            android.support.v4.app.FragmentActivity r3 = r11.activity     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            android.content.ContentResolver r5 = r3.getContentResolver()     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            android.net.Uri r6 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.String[] r7 = VIDEO_PROJECTION     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            r3.<init>()     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.String r8 = "_data = '"
            r3.append(r8)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.String r8 = r12.getAbsolutePath()     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            r3.append(r8)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.String r8 = "'"
            r3.append(r8)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.String r8 = r3.toString()     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            r9 = 0
            r10 = 0
            android.database.Cursor r3 = r5.query(r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            int r5 = r3.getCount()     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            r6 = 1
            if (r5 < r6) goto L_0x0099
            r3.moveToNext()     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.String r5 = "duration"
            int r5 = r3.getColumnIndex(r5)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            long r5 = r3.getLong(r5)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.io.PrintStream r7 = java.lang.System.out     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            r8.<init>()     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.String r9 = "BIG_VIDEO_INFO_duration_"
            r8.append(r9)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            r8.append(r5)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            r7.println(r8)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            goto L_0x009a
        L_0x0099:
            r5 = r0
        L_0x009a:
            r7 = 999(0x3e7, double:4.936E-321)
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 >= 0) goto L_0x0135
            java.lang.String r7 = r12.getAbsolutePath()     // Catch:{ Exception -> 0x012e, all -> 0x0143 }
            int r7 = r11.getVideoDuration(r7)     // Catch:{ Exception -> 0x012e, all -> 0x0143 }
            r8 = 999(0x3e7, float:1.4E-42)
            if (r7 <= r8) goto L_0x00ce
            java.io.PrintStream r12 = java.lang.System.out     // Catch:{ Exception -> 0x012e, all -> 0x0143 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x012e, all -> 0x0143 }
            r4.<init>()     // Catch:{ Exception -> 0x012e, all -> 0x0143 }
            java.lang.String r8 = "BIG_VIDEO_INFO_6_"
            r4.append(r8)     // Catch:{ Exception -> 0x012e, all -> 0x0143 }
            r4.append(r7)     // Catch:{ Exception -> 0x012e, all -> 0x0143 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x012e, all -> 0x0143 }
            r12.println(r4)     // Catch:{ Exception -> 0x012e, all -> 0x0143 }
            android.support.v4.app.FragmentActivity r12 = r11.activity     // Catch:{ Exception -> 0x012e, all -> 0x0143 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r7)     // Catch:{ Exception -> 0x012e, all -> 0x0143 }
            com.yongchun.library.utils.SPUtils.put(r12, r2, r4)     // Catch:{ Exception -> 0x012e, all -> 0x0143 }
            long r0 = (long) r7
            monitor-exit(r11)
            return r0
        L_0x00ce:
            io.microshow.rxffmpeg.RxFFmpegInvoke r7 = io.microshow.rxffmpeg.RxFFmpegInvoke.getInstance()     // Catch:{ Exception -> 0x012e, all -> 0x0143 }
            r7.setDebug(r4)     // Catch:{ Exception -> 0x012e, all -> 0x0143 }
            io.microshow.rxffmpeg.RxFFmpegInvoke r4 = io.microshow.rxffmpeg.RxFFmpegInvoke.getInstance()     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            java.lang.String r12 = r12.getAbsolutePath()     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            java.lang.String r12 = r4.getMediaInfo(r12)     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            java.io.PrintStream r4 = java.lang.System.out     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            r7.<init>()     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            java.lang.String r8 = "BIG_VIDEO_INFO_1_"
            r7.append(r8)     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            r7.append(r12)     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            r4.println(r7)     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            if (r12 == 0) goto L_0x0112
            int r4 = r12.length()     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            r7 = 50
            if (r4 <= r7) goto L_0x0112
            java.io.PrintStream r4 = java.lang.System.out     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            java.lang.String r7 = "BIG_VIDEO_INFO_2_"
            r4.println(r7)     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            com.yongchun.library.model.MediaInfo r12 = com.yongchun.library.model.MediaInfo.parse(r12)     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            if (r12 == 0) goto L_0x0135
            long r7 = r12.duration     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            r5 = r7
            goto L_0x0135
        L_0x0112:
            java.io.PrintStream r12 = java.lang.System.out     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            java.lang.String r4 = "BIG_VIDEO_INFO_22_"
            r12.println(r4)     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            android.support.v4.app.FragmentActivity r12 = r11.activity     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            java.lang.Long r4 = java.lang.Long.valueOf(r5)     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            com.yongchun.library.utils.SPUtils.put(r12, r2, r4)     // Catch:{ Exception -> 0x0126, all -> 0x0143 }
            r0 = 15000(0x3a98, double:7.411E-320)
            monitor-exit(r11)
            return r0
        L_0x0126:
            java.io.PrintStream r12 = java.lang.System.out     // Catch:{ Exception -> 0x012e, all -> 0x0143 }
            java.lang.String r4 = "BIG_VIDEO_INFO_ERROR0"
            r12.println(r4)     // Catch:{ Exception -> 0x012e, all -> 0x0143 }
            goto L_0x0135
        L_0x012e:
            java.io.PrintStream r12 = java.lang.System.out     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.String r4 = "BIG_VIDEO_INFO_ERROR1"
            r12.println(r4)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
        L_0x0135:
            r3.close()     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            android.support.v4.app.FragmentActivity r12 = r11.activity     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            java.lang.Long r3 = java.lang.Long.valueOf(r5)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            com.yongchun.library.utils.SPUtils.put(r12, r2, r3)     // Catch:{ Exception -> 0x0146, all -> 0x0143 }
            monitor-exit(r11)
            return r5
        L_0x0143:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        L_0x0146:
            monitor-exit(r11)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yongchun.library.utils.LocalMediaLoader.getDuration(java.io.File):long");
    }

    private int getVideoDuration(String str) {
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            return Integer.parseInt(mediaMetadataRetriever.extractMetadata(9));
        } catch (Exception unused) {
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public void sortImages(List<LocalMedia> list) {
        try {
            Collections.sort(list, new Comparator<LocalMedia>() {
                public int compare(LocalMedia localMedia, LocalMedia localMedia2) {
                    long lastUpdateAt = localMedia.getLastUpdateAt();
                    long lastUpdateAt2 = localMedia2.getLastUpdateAt();
                    if (lastUpdateAt == lastUpdateAt2) {
                        return 0;
                    }
                    return lastUpdateAt < lastUpdateAt2 ? 1 : -1;
                }
            });
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public void sortFolder(List<LocalMediaFolder> list) {
        try {
            Collections.sort(list, new Comparator<LocalMediaFolder>() {
                public int compare(LocalMediaFolder localMediaFolder, LocalMediaFolder localMediaFolder2) {
                    int imageNum;
                    int imageNum2;
                    if (localMediaFolder.getImages() == null || localMediaFolder2.getImages() == null || (imageNum = localMediaFolder.getImageNum()) == (imageNum2 = localMediaFolder2.getImageNum())) {
                        return 0;
                    }
                    return imageNum < imageNum2 ? 1 : -1;
                }
            });
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public LocalMediaFolder getImageFolder(String str, List<LocalMediaFolder> list) {
        File parentFile = new File(str).getParentFile();
        if (parentFile == null) {
            return new LocalMediaFolder();
        }
        for (LocalMediaFolder next : list) {
            if (next.getName().equals(parentFile.getName())) {
                return next;
            }
        }
        LocalMediaFolder localMediaFolder = new LocalMediaFolder();
        localMediaFolder.setName(parentFile.getName());
        localMediaFolder.setPath(parentFile.getAbsolutePath());
        localMediaFolder.setFirstImagePath(str);
        return localMediaFolder;
    }
}
