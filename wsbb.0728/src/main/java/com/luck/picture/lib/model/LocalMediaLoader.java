package com.luck.picture.lib.model;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import com.luck.picture.lib.R;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.entity.LocalMediaFolder;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class LocalMediaLoader {
    private static final int AUDIO_DURATION = 500;
    private static final String DURATION = "duration";
    private static final String NOT_GIF = "!='image/gif'";
    private static final String ORDER_BY = "_id DESC";
    /* access modifiers changed from: private */
    public static final String[] PROJECTION = {"_id", "_data", "mime_type", SocializeProtocolConstants.WIDTH, SocializeProtocolConstants.HEIGHT, "duration"};
    /* access modifiers changed from: private */
    public static final Uri QUERY_URI = MediaStore.Files.getContentUri("external");
    private static final String SELECTION = "media_type=? AND _size>0";
    /* access modifiers changed from: private */
    public static final String[] SELECTION_ALL_ARGS = {String.valueOf(1), String.valueOf(3)};
    private static final String SELECTION_NOT_GIF = "media_type=? AND _size>0 AND mime_type!='image/gif'";
    /* access modifiers changed from: private */
    public FragmentActivity activity;
    /* access modifiers changed from: private */
    public boolean isGif;
    private int type = 1;
    private long videoMaxS = 0;
    private long videoMinS = 0;

    public interface LocalMediaLoadListener {
        void loadComplete(List<LocalMediaFolder> list);
    }

    public LocalMediaLoader(FragmentActivity fragmentActivity, int i, boolean z, long j, long j2) {
        this.activity = fragmentActivity;
        this.type = i;
        this.isGif = z;
        this.videoMaxS = j;
        this.videoMinS = j2;
    }

    /* access modifiers changed from: private */
    public String getDurationCondition(long j, long j2) {
        long j3 = this.videoMaxS == 0 ? Long.MAX_VALUE : this.videoMaxS;
        if (j != 0) {
            j3 = Math.min(j3, j);
        }
        return String.format(Locale.CHINA, "%d <%s duration and duration <= %d", new Object[]{Long.valueOf(Math.max(j2, this.videoMinS)), Math.max(j2, this.videoMinS) == 0 ? "" : "=", Long.valueOf(j3)});
    }

    /* access modifiers changed from: private */
    public LocalMediaFolder getImageFolder(String str, List<LocalMediaFolder> list) {
        File parentFile = new File(str).getParentFile();
        for (LocalMediaFolder next : list) {
            if (next.getName().equals(parentFile.getName())) {
                return next;
            }
        }
        LocalMediaFolder localMediaFolder = new LocalMediaFolder();
        localMediaFolder.setName(parentFile.getName());
        localMediaFolder.setPath(parentFile.getAbsolutePath());
        localMediaFolder.setFirstImagePath(str);
        list.add(localMediaFolder);
        return localMediaFolder;
    }

    /* access modifiers changed from: private */
    public static String getSelectionArgsForAllMediaCondition(String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("(media_type=?");
        sb.append(z ? "" : " AND mime_type!='image/gif'");
        sb.append(" OR ");
        sb.append("media_type=? AND ");
        sb.append(str);
        sb.append(") AND ");
        sb.append("_size");
        sb.append(">0");
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public static String getSelectionArgsForSingleMediaCondition(String str) {
        return "media_type=? AND _size>0 AND " + str;
    }

    /* access modifiers changed from: private */
    public static String[] getSelectionArgsForSingleMediaType(int i) {
        return new String[]{String.valueOf(i)};
    }

    /* access modifiers changed from: private */
    public void sortFolder(List<LocalMediaFolder> list) {
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
    }

    public void loadAllMedia(final int i, final LocalMediaLoadListener localMediaLoadListener) {
        this.activity.getSupportLoaderManager().initLoader(i, (Bundle) null, new LoaderManager.LoaderCallbacks<Cursor>() {
            public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
                switch (i) {
                    case 0:
                        return new CursorLoader(LocalMediaLoader.this.activity, LocalMediaLoader.QUERY_URI, LocalMediaLoader.PROJECTION, LocalMediaLoader.getSelectionArgsForAllMediaCondition(LocalMediaLoader.this.getDurationCondition(0, 0), LocalMediaLoader.this.isGif), LocalMediaLoader.SELECTION_ALL_ARGS, LocalMediaLoader.ORDER_BY);
                    case 1:
                        return new CursorLoader(LocalMediaLoader.this.activity, LocalMediaLoader.QUERY_URI, LocalMediaLoader.PROJECTION, LocalMediaLoader.this.isGif ? LocalMediaLoader.SELECTION : LocalMediaLoader.SELECTION_NOT_GIF, LocalMediaLoader.getSelectionArgsForSingleMediaType(1), LocalMediaLoader.ORDER_BY);
                    case 2:
                        return new CursorLoader(LocalMediaLoader.this.activity, LocalMediaLoader.QUERY_URI, LocalMediaLoader.PROJECTION, LocalMediaLoader.getSelectionArgsForSingleMediaCondition(LocalMediaLoader.this.getDurationCondition(0, 0)), LocalMediaLoader.getSelectionArgsForSingleMediaType(3), LocalMediaLoader.ORDER_BY);
                    case 3:
                        return new CursorLoader(LocalMediaLoader.this.activity, LocalMediaLoader.QUERY_URI, LocalMediaLoader.PROJECTION, LocalMediaLoader.getSelectionArgsForSingleMediaCondition(LocalMediaLoader.this.getDurationCondition(0, 500)), LocalMediaLoader.getSelectionArgsForSingleMediaType(2), LocalMediaLoader.ORDER_BY);
                    default:
                        return null;
                }
            }

            public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                try {
                    ArrayList arrayList = new ArrayList();
                    LocalMediaFolder localMediaFolder = new LocalMediaFolder();
                    ArrayList arrayList2 = new ArrayList();
                    if (cursor != null) {
                        int count = cursor.getCount();
                        Log.e("wq", "count == " + count);
                        if (count > 0) {
                            cursor.moveToFirst();
                            do {
                                String string = cursor.getString(cursor.getColumnIndexOrThrow(LocalMediaLoader.PROJECTION[1]));
                                LocalMedia localMedia = new LocalMedia(string, (long) cursor.getInt(cursor.getColumnIndexOrThrow(LocalMediaLoader.PROJECTION[5])), i, cursor.getString(cursor.getColumnIndexOrThrow(LocalMediaLoader.PROJECTION[2])), cursor.getInt(cursor.getColumnIndexOrThrow(LocalMediaLoader.PROJECTION[3])), cursor.getInt(cursor.getColumnIndexOrThrow(LocalMediaLoader.PROJECTION[4])));
                                LocalMediaFolder access$900 = LocalMediaLoader.this.getImageFolder(string, arrayList);
                                access$900.getImages().add(localMedia);
                                access$900.setImageNum(access$900.getImageNum() + 1);
                                arrayList2.add(localMedia);
                                localMediaFolder.setImageNum(localMediaFolder.getImageNum() + 1);
                            } while (cursor.moveToNext());
                            if (arrayList2.size() > 0) {
                                LocalMediaLoader.this.sortFolder(arrayList);
                                arrayList.add(0, localMediaFolder);
                                localMediaFolder.setFirstImagePath(((LocalMedia) arrayList2.get(0)).getPath());
                                localMediaFolder.setName(i == PictureMimeType.ofAudio() ? LocalMediaLoader.this.activity.getString(R.string.picture_all_audio) : LocalMediaLoader.this.activity.getString(R.string.picture_camera_roll));
                                localMediaFolder.setImages(arrayList2);
                            }
                            Log.e("wq", "imageFolders size == " + arrayList.size());
                            localMediaLoadListener.loadComplete(arrayList);
                            return;
                        }
                        localMediaLoadListener.loadComplete(arrayList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onLoaderReset(Loader<Cursor> loader) {
            }
        });
    }
}
