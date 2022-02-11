package com.wx.assistants.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.wx.assistants.Enity.CopyFriendBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class CopyFriendBeanDao extends AbstractDao<CopyFriendBean, Long> {
    public static final String TABLENAME = "COPY_FRIEND_BEAN";

    public static class Properties {
        public static final Property Checked = new Property(6, Boolean.TYPE, "checked", false, "CHECKED");
        public static final Property DeleteTime = new Property(5, String.class, "deleteTime", false, "DELETE_TIME");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property IsAdded = new Property(1, Boolean.TYPE, "isAdded", false, "IS_ADDED");
        public static final Property WxNickName = new Property(3, String.class, "wxNickName", false, "WX_NICK_NAME");
        public static final Property WxNum = new Property(2, String.class, "wxNum", false, "WX_NUM");
        public static final Property WxRemarkName = new Property(4, String.class, "wxRemarkName", false, "WX_REMARK_NAME");
    }

    /* access modifiers changed from: protected */
    public final boolean isEntityUpdateable() {
        return true;
    }

    public CopyFriendBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public CopyFriendBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"COPY_FRIEND_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"IS_ADDED\" INTEGER NOT NULL ,\"WX_NUM\" TEXT,\"WX_NICK_NAME\" TEXT,\"WX_REMARK_NAME\" TEXT,\"DELETE_TIME\" TEXT,\"CHECKED\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"COPY_FRIEND_BEAN\"");
        database.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public final void bindValues(DatabaseStatement databaseStatement, CopyFriendBean copyFriendBean) {
        databaseStatement.clearBindings();
        Long id = copyFriendBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        long j = 0;
        databaseStatement.bindLong(2, copyFriendBean.getIsAdded() ? 1 : 0);
        String wxNum = copyFriendBean.getWxNum();
        if (wxNum != null) {
            databaseStatement.bindString(3, wxNum);
        }
        String wxNickName = copyFriendBean.getWxNickName();
        if (wxNickName != null) {
            databaseStatement.bindString(4, wxNickName);
        }
        String wxRemarkName = copyFriendBean.getWxRemarkName();
        if (wxRemarkName != null) {
            databaseStatement.bindString(5, wxRemarkName);
        }
        String deleteTime = copyFriendBean.getDeleteTime();
        if (deleteTime != null) {
            databaseStatement.bindString(6, deleteTime);
        }
        if (copyFriendBean.getChecked()) {
            j = 1;
        }
        databaseStatement.bindLong(7, j);
    }

    /* access modifiers changed from: protected */
    public final void bindValues(SQLiteStatement sQLiteStatement, CopyFriendBean copyFriendBean) {
        sQLiteStatement.clearBindings();
        Long id = copyFriendBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        long j = 0;
        sQLiteStatement.bindLong(2, copyFriendBean.getIsAdded() ? 1 : 0);
        String wxNum = copyFriendBean.getWxNum();
        if (wxNum != null) {
            sQLiteStatement.bindString(3, wxNum);
        }
        String wxNickName = copyFriendBean.getWxNickName();
        if (wxNickName != null) {
            sQLiteStatement.bindString(4, wxNickName);
        }
        String wxRemarkName = copyFriendBean.getWxRemarkName();
        if (wxRemarkName != null) {
            sQLiteStatement.bindString(5, wxRemarkName);
        }
        String deleteTime = copyFriendBean.getDeleteTime();
        if (deleteTime != null) {
            sQLiteStatement.bindString(6, deleteTime);
        }
        if (copyFriendBean.getChecked()) {
            j = 1;
        }
        sQLiteStatement.bindLong(7, j);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public CopyFriendBean readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        boolean z = cursor.getShort(i + 1) != 0;
        int i3 = i + 2;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 3;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 4;
        String string3 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 5;
        return new CopyFriendBean(valueOf, z, string, string2, string3, cursor.isNull(i6) ? null : cursor.getString(i6), cursor.getShort(i + 6) != 0);
    }

    public void readEntity(Cursor cursor, CopyFriendBean copyFriendBean, int i) {
        int i2 = i + 0;
        String str = null;
        copyFriendBean.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        boolean z = true;
        copyFriendBean.setIsAdded(cursor.getShort(i + 1) != 0);
        int i3 = i + 2;
        copyFriendBean.setWxNum(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        copyFriendBean.setWxNickName(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 4;
        copyFriendBean.setWxRemarkName(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 5;
        if (!cursor.isNull(i6)) {
            str = cursor.getString(i6);
        }
        copyFriendBean.setDeleteTime(str);
        if (cursor.getShort(i + 6) == 0) {
            z = false;
        }
        copyFriendBean.setChecked(z);
    }

    /* access modifiers changed from: protected */
    public final Long updateKeyAfterInsert(CopyFriendBean copyFriendBean, long j) {
        copyFriendBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(CopyFriendBean copyFriendBean) {
        if (copyFriendBean != null) {
            return copyFriendBean.getId();
        }
        return null;
    }

    public boolean hasKey(CopyFriendBean copyFriendBean) {
        return copyFriendBean.getId() != null;
    }
}
