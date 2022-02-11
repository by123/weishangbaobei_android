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
import com.yongchun.library.model.MediaInfo;
import io.microshow.rxffmpeg.RxFFmpegInvoke;
import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintStream;
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

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        java.lang.System.out.println("BIG_VIDEO_INFO_ERROR0");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    public long getDuration(File file) {
        long j;
        synchronized (this) {
            try {
                String substring = file.getName().substring(0, file.getName().lastIndexOf("."));
                long longValue = ((Long) SPUtils.get(this.activity, substring, -1L)).longValue();
                if (longValue > 0) {
                    System.out.println("BIG_VIDEO_INFO_duration0_" + longValue);
                    return longValue;
                }
                Cursor query = this.activity.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, VIDEO_PROJECTION, "_data = '" + file.getAbsolutePath() + "'", (String[]) null, (String) null);
                if (query.getCount() >= 1) {
                    query.moveToNext();
                    j = query.getLong(query.getColumnIndex("duration"));
                    System.out.println("BIG_VIDEO_INFO_duration_" + j);
                } else {
                    j = 0;
                }
                if (j < 999) {
                    try {
                        int videoDuration = getVideoDuration(file.getAbsolutePath());
                        if (videoDuration > 999) {
                            System.out.println("BIG_VIDEO_INFO_6_" + videoDuration);
                            SPUtils.put(this.activity, substring, Integer.valueOf(videoDuration));
                            long j2 = (long) videoDuration;
                            return j2;
                        }
                        RxFFmpegInvoke.getInstance().setDebug(false);
                        String mediaInfo = RxFFmpegInvoke.getInstance().getMediaInfo(file.getAbsolutePath());
                        System.out.println("BIG_VIDEO_INFO_1_" + mediaInfo);
                        if (mediaInfo == null || mediaInfo.length() <= 50) {
                            System.out.println("BIG_VIDEO_INFO_22_");
                            SPUtils.put(this.activity, substring, Long.valueOf(j));
                            return 15000;
                        }
                        System.out.println("BIG_VIDEO_INFO_2_");
                        MediaInfo parse = MediaInfo.parse(mediaInfo);
                        if (parse != null) {
                            j = parse.duration;
                        }
                    } catch (Exception e) {
                        System.out.println("BIG_VIDEO_INFO_ERROR1");
                    }
                }
                query.close();
                SPUtils.put(this.activity, substring, Long.valueOf(j));
                return j;
            } catch (Exception e2) {
                return 0;
            }
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

    private int getVideoDuration(String str) {
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            return Integer.parseInt(mediaMetadataRetriever.extractMetadata(9));
        } catch (Exception e) {
            return 0;
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
        } catch (Exception e) {
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
        } catch (Exception e) {
        }
    }

    public void loadAllImage(final LocalMediaLoadListener localMediaLoadListener) {
        this.activity.getSupportLoaderManager().initLoader(this.type, (Bundle) null, new LoaderManager.LoaderCallbacks<Cursor>() {
            public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
                if (i == 1) {
                    FragmentActivity access$000 = LocalMediaLoader.this.activity;
                    Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    String[] access$100 = LocalMediaLoader.IMAGE_PROJECTION;
                    return new CursorLoader(access$000, uri, access$100, (String) null, (String[]) null, LocalMediaLoader.IMAGE_PROJECTION[2] + " DESC");
                } else if (i == 2) {
                    FragmentActivity access$0002 = LocalMediaLoader.this.activity;
                    Uri uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    String[] access$200 = LocalMediaLoader.VIDEO_PROJECTION;
                    return new CursorLoader(access$0002, uri2, access$200, (String) null, (String[]) null, LocalMediaLoader.VIDEO_PROJECTION[2] + " DESC");
                } else if (i != 3) {
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
                ArrayList arrayList = new ArrayList();
                LocalMediaFolder localMediaFolder = new LocalMediaFolder();
                ArrayList arrayList2 = new ArrayList();
                while (true) {
                    int i = 0;
                    if (cursor == null || !cursor.moveToNext()) {
                        try {
                            LocalMediaLoader.this.sortImages(arrayList2);
                        } catch (Exception e) {
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
                        } catch (Exception e2) {
                        }
                        localMediaLoadListener.loadComplete(arrayList);
                        if (Integer.parseInt(Build.VERSION.SDK) < 14 && cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    }
                    String string = cursor.getString(cursor.getColumnIndex("_data"));
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
                                while (true) {
                                    int i2 = i;
                                    if (i2 < listFiles.length) {
                                        File file2 = listFiles[i2];
                                        long j = 0;
                                        if (file2.getName().endsWith(PictureFileUtils.POST_VIDEO)) {
                                            try {
                                                j = (long) Math.round((float) LocalMediaLoader.this.getDuration(file2));
                                                PrintStream printStream = System.out;
                                                printStream.println("WS_BABY_" + j);
                                            } catch (Exception e3) {
                                                j = 1500;
                                            }
                                        }
                                        LocalMedia localMedia = new LocalMedia(file2.getAbsolutePath(), file2.lastModified(), j);
                                        arrayList2.add(localMedia);
                                        arrayList3.add(localMedia);
                                        i = i2 + 1;
                                    } else {
                                        try {
                                            break;
                                        } catch (Exception e4) {
                                        }
                                    }
                                }
                                LocalMediaLoader.this.sortImages(arrayList3);
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

            public void onLoaderReset(Loader<Cursor> loader) {
            }
        });
    }
}
