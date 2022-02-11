package com.wx.assistants.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.wx.assistants.Enity.RecoverFriendBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class RecoverFriendBeanDao extends AbstractDao<RecoverFriendBean, Long> {
    public static final String TABLENAME = "RECOVER_FRIEND_BEAN";

    public static class Properties {
        public static final Property Checked = new Property(5, Boolean.TYPE, "checked", false, "CHECKED");
        public static final Property DeleteTime = new Property(4, String.class, "deleteTime", false, "DELETE_TIME");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property IsAdded = new Property(1, Boolean.TYPE, "isAdded", false, "IS_ADDED");
        public static final Property WxNickName = new Property(3, String.class, "wxNickName", false, "WX_NICK_NAME");
        public static final Property WxNum = new Property(2, String.class, "wxNum", false, "WX_NUM");
    }

    /* access modifiers changed from: protected */
    public final boolean isEntityUpdateable() {
        return true;
    }

    public RecoverFriendBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public RecoverFriendBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"RECOVER_FRIEND_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"IS_ADDED\" INTEGER NOT NULL ,\"WX_NUM\" TEXT,\"WX_NICK_NAME\" TEXT,\"DELETE_TIME\" TEXT,\"CHECKED\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"RECOVER_FRIEND_BEAN\"");
        database.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public final void bindValues(DatabaseStatement databaseStatement, RecoverFriendBean recoverFriendBean) {
        databaseStatement.clearBindings();
        Long id = recoverFriendBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        long j = 0;
        databaseStatement.bindLong(2, recoverFriendBean.getIsAdded() ? 1 : 0);
        String wxNum = recoverFriendBean.getWxNum();
        if (wxNum != null) {
            databaseStatement.bindString(3, wxNum);
        }
        String wxNickName = recoverFriendBean.getWxNickName();
        if (wxNickName != null) {
            databaseStatement.bindString(4, wxNickName);
        }
        String deleteTime = recoverFriendBean.getDeleteTime();
        if (deleteTime != null) {
            databaseStatement.bindString(5, deleteTime);
        }
        if (recoverFriendBean.getChecked()) {
            j = 1;
        }
        databaseStatement.bindLong(6, j);
    }

    /* access modifiers changed from: protected */
    public final void bindValues(SQLiteStatement sQLiteStatement, RecoverFriendBean recoverFriendBean) {
        sQLiteStatement.clearBindings();
        Long id = recoverFriendBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        long j = 0;
        sQLiteStatement.bindLong(2, recoverFriendBean.getIsAdded() ? 1 : 0);
        String wxNum = recoverFriendBean.getWxNum();
        if (wxNum != null) {
            sQLiteStatement.bindString(3, wxNum);
        }
        String wxNickName = recoverFriendBean.getWxNickName();
        if (wxNickName != null) {
            sQLiteStatement.bindString(4, wxNickName);
        }
        String deleteTime = recoverFriendBean.getDeleteTime();
        if (deleteTime != null) {
            sQLiteStatement.bindString(5, deleteTime);
        }
        if (recoverFriendBean.getChecked()) {
            j = 1;
        }
        sQLiteStatement.bindLong(6, j);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public RecoverFriendBean readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        boolean z = cursor.getShort(i + 1) != 0;
        int i3 = i + 2;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 3;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 4;
        return new RecoverFriendBean(valueOf, z, string, string2, cursor.isNull(i5) ? null : cursor.getString(i5), cursor.getShort(i + 5) != 0);
    }

    public void readEntity(Cursor cursor, RecoverFriendBean recoverFriendBean, int i) {
        int i2 = i + 0;
        String str = null;
        recoverFriendBean.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        boolean z = true;
        recoverFriendBean.setIsAdded(cursor.getShort(i + 1) != 0);
        int i3 = i + 2;
        recoverFriendBean.setWxNum(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        recoverFriendBean.setWxNickName(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 4;
        if (!cursor.isNull(i5)) {
            str = cursor.getString(i5);
        }
        recoverFriendBean.setDeleteTime(str);
        if (cursor.getShort(i + 5) == 0) {
            z = false;
        }
        recoverFriendBean.setChecked(z);
    }

    /* access modifiers changed from: protected */
    public final Long updateKeyAfterInsert(RecoverFriendBean recoverFriendBean, long j) {
        recoverFriendBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(RecoverFriendBean recoverFriendBean) {
        if (recoverFriendBean != null) {
            return recoverFriendBean.getId();
        }
        return null;
    }

    public boolean hasKey(RecoverFriendBean recoverFriendBean) {
        return recoverFriendBean.getId() != null;
    }
}
