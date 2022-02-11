package com.wx.assistants.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.wx.assistants.Enity.MusicBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class MusicBeanDao extends AbstractDao<MusicBean, Long> {
    public static final String TABLENAME = "MUSIC_BEAN";

    public static class Properties {
        public static final Property Duration = new Property(1, Long.class, "duration", false, "DURATION");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Title = new Property(2, String.class, "title", false, "TITLE");
    }

    /* access modifiers changed from: protected */
    public final boolean isEntityUpdateable() {
        return true;
    }

    public MusicBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public MusicBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"MUSIC_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"DURATION\" INTEGER,\"TITLE\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"MUSIC_BEAN\"");
        database.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public final void bindValues(DatabaseStatement databaseStatement, MusicBean musicBean) {
        databaseStatement.clearBindings();
        Long id = musicBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        Long duration = musicBean.getDuration();
        if (duration != null) {
            databaseStatement.bindLong(2, duration.longValue());
        }
        String title = musicBean.getTitle();
        if (title != null) {
            databaseStatement.bindString(3, title);
        }
    }

    /* access modifiers changed from: protected */
    public final void bindValues(SQLiteStatement sQLiteStatement, MusicBean musicBean) {
        sQLiteStatement.clearBindings();
        Long id = musicBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        Long duration = musicBean.getDuration();
        if (duration != null) {
            sQLiteStatement.bindLong(2, duration.longValue());
        }
        String title = musicBean.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(3, title);
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public MusicBean readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        String str = null;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        Long valueOf2 = cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3));
        int i4 = i + 2;
        if (!cursor.isNull(i4)) {
            str = cursor.getString(i4);
        }
        return new MusicBean(valueOf, valueOf2, str);
    }

    public void readEntity(Cursor cursor, MusicBean musicBean, int i) {
        int i2 = i + 0;
        String str = null;
        musicBean.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        musicBean.setDuration(cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3)));
        int i4 = i + 2;
        if (!cursor.isNull(i4)) {
            str = cursor.getString(i4);
        }
        musicBean.setTitle(str);
    }

    /* access modifiers changed from: protected */
    public final Long updateKeyAfterInsert(MusicBean musicBean, long j) {
        musicBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(MusicBean musicBean) {
        if (musicBean != null) {
            return musicBean.getId();
        }
        return null;
    }

    public boolean hasKey(MusicBean musicBean) {
        return musicBean.getId() != null;
    }
}
