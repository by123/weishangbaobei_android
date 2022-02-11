package com.wx.assistants.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.wx.assistants.Enity.RecordBean;
import com.yongchun.library.view.ImagePreviewFragment;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class RecordBeanDao extends AbstractDao<RecordBean, Long> {
    public static final String TABLENAME = "RECORD_BEAN";

    public static class Properties {
        public static final Property CreateTimeLong = new Property(9, Long.TYPE, "createTimeLong", false, "CREATE_TIME_LONG");
        public static final Property CreateTimeString = new Property(10, String.class, "createTimeString", false, "CREATE_TIME_STRING");
        public static final Property H5Url = new Property(3, String.class, "h5Url", false, "H5_URL");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property IsCollection = new Property(8, Boolean.TYPE, "isCollection", false, "IS_COLLECTION");
        public static final Property IsFromWx = new Property(12, Boolean.TYPE, "isFromWx", false, "IS_FROM_WX");
        public static final Property IsPlayed = new Property(6, Boolean.TYPE, "isPlayed", false, "IS_PLAYED");
        public static final Property IsPlaying = new Property(7, Boolean.TYPE, "isPlaying", false, "IS_PLAYING");
        public static final Property Mp3LocPath = new Property(2, String.class, "mp3LocPath", false, "MP3_LOC_PATH");
        public static final Property Mp3SharedPath = new Property(4, String.class, "mp3SharedPath", false, "MP3_SHARED_PATH");
        public static final Property Path = new Property(1, String.class, ImagePreviewFragment.PATH, false, "PATH");
        public static final Property Second = new Property(5, Integer.TYPE, "second", false, "SECOND");
        public static final Property VoiceTag = new Property(11, Integer.TYPE, "voiceTag", false, "VOICE_TAG");
    }

    public RecordBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public RecordBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"RECORD_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"PATH\" TEXT,\"MP3_LOC_PATH\" TEXT,\"H5_URL\" TEXT,\"MP3_SHARED_PATH\" TEXT,\"SECOND\" INTEGER NOT NULL ,\"IS_PLAYED\" INTEGER NOT NULL ,\"IS_PLAYING\" INTEGER NOT NULL ,\"IS_COLLECTION\" INTEGER NOT NULL ,\"CREATE_TIME_LONG\" INTEGER NOT NULL ,\"CREATE_TIME_STRING\" TEXT,\"VOICE_TAG\" INTEGER NOT NULL ,\"IS_FROM_WX\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"RECORD_BEAN\"");
        database.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public final void bindValues(SQLiteStatement sQLiteStatement, RecordBean recordBean) {
        long j = 1;
        sQLiteStatement.clearBindings();
        Long id = recordBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String path = recordBean.getPath();
        if (path != null) {
            sQLiteStatement.bindString(2, path);
        }
        String mp3LocPath = recordBean.getMp3LocPath();
        if (mp3LocPath != null) {
            sQLiteStatement.bindString(3, mp3LocPath);
        }
        String h5Url = recordBean.getH5Url();
        if (h5Url != null) {
            sQLiteStatement.bindString(4, h5Url);
        }
        String mp3SharedPath = recordBean.getMp3SharedPath();
        if (mp3SharedPath != null) {
            sQLiteStatement.bindString(5, mp3SharedPath);
        }
        sQLiteStatement.bindLong(6, (long) recordBean.getSecond());
        sQLiteStatement.bindLong(7, recordBean.getIsPlayed() ? 1 : 0);
        sQLiteStatement.bindLong(8, recordBean.getIsPlaying() ? 1 : 0);
        sQLiteStatement.bindLong(9, recordBean.getIsCollection() ? 1 : 0);
        sQLiteStatement.bindLong(10, recordBean.getCreateTimeLong());
        String createTimeString = recordBean.getCreateTimeString();
        if (createTimeString != null) {
            sQLiteStatement.bindString(11, createTimeString);
        }
        sQLiteStatement.bindLong(12, (long) recordBean.getVoiceTag());
        if (!recordBean.getIsFromWx()) {
            j = 0;
        }
        sQLiteStatement.bindLong(13, j);
    }

    /* access modifiers changed from: protected */
    public final void bindValues(DatabaseStatement databaseStatement, RecordBean recordBean) {
        long j = 1;
        databaseStatement.clearBindings();
        Long id = recordBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String path = recordBean.getPath();
        if (path != null) {
            databaseStatement.bindString(2, path);
        }
        String mp3LocPath = recordBean.getMp3LocPath();
        if (mp3LocPath != null) {
            databaseStatement.bindString(3, mp3LocPath);
        }
        String h5Url = recordBean.getH5Url();
        if (h5Url != null) {
            databaseStatement.bindString(4, h5Url);
        }
        String mp3SharedPath = recordBean.getMp3SharedPath();
        if (mp3SharedPath != null) {
            databaseStatement.bindString(5, mp3SharedPath);
        }
        databaseStatement.bindLong(6, (long) recordBean.getSecond());
        databaseStatement.bindLong(7, recordBean.getIsPlayed() ? 1 : 0);
        databaseStatement.bindLong(8, recordBean.getIsPlaying() ? 1 : 0);
        databaseStatement.bindLong(9, recordBean.getIsCollection() ? 1 : 0);
        databaseStatement.bindLong(10, recordBean.getCreateTimeLong());
        String createTimeString = recordBean.getCreateTimeString();
        if (createTimeString != null) {
            databaseStatement.bindString(11, createTimeString);
        }
        databaseStatement.bindLong(12, (long) recordBean.getVoiceTag());
        if (!recordBean.getIsFromWx()) {
            j = 0;
        }
        databaseStatement.bindLong(13, j);
    }

    public Long getKey(RecordBean recordBean) {
        if (recordBean != null) {
            return recordBean.getId();
        }
        return null;
    }

    public boolean hasKey(RecordBean recordBean) {
        return recordBean.getId() != null;
    }

    /* access modifiers changed from: protected */
    public final boolean isEntityUpdateable() {
        return true;
    }

    public RecordBean readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        String str = null;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        String string3 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        String string4 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = cursor.getInt(i + 5);
        boolean z = cursor.getShort(i + 6) != 0;
        boolean z2 = cursor.getShort(i + 7) != 0;
        boolean z3 = cursor.getShort(i + 8) != 0;
        long j = cursor.getLong(i + 9);
        int i8 = i + 10;
        if (!cursor.isNull(i8)) {
            str = cursor.getString(i8);
        }
        return new RecordBean(valueOf, string, string2, string3, string4, i7, z, z2, z3, j, str, cursor.getInt(i + 11), cursor.getShort(i + 12) != 0);
    }

    public void readEntity(Cursor cursor, RecordBean recordBean, int i) {
        boolean z = true;
        String str = null;
        int i2 = i + 0;
        recordBean.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        recordBean.setPath(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        recordBean.setMp3LocPath(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        recordBean.setH5Url(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        recordBean.setMp3SharedPath(cursor.isNull(i6) ? null : cursor.getString(i6));
        recordBean.setSecond(cursor.getInt(i + 5));
        recordBean.setIsPlayed(cursor.getShort(i + 6) != 0);
        recordBean.setIsPlaying(cursor.getShort(i + 7) != 0);
        recordBean.setIsCollection(cursor.getShort(i + 8) != 0);
        recordBean.setCreateTimeLong(cursor.getLong(i + 9));
        int i7 = i + 10;
        if (!cursor.isNull(i7)) {
            str = cursor.getString(i7);
        }
        recordBean.setCreateTimeString(str);
        recordBean.setVoiceTag(cursor.getInt(i + 11));
        if (cursor.getShort(i + 12) == 0) {
            z = false;
        }
        recordBean.setIsFromWx(z);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    /* access modifiers changed from: protected */
    public final Long updateKeyAfterInsert(RecordBean recordBean, long j) {
        recordBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
