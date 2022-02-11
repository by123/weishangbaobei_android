package com.wx.assistants.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.wx.assistants.Enity.GroupBeanAll;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class GroupBeanAllDao extends AbstractDao<GroupBeanAll, Long> {
    public static final String TABLENAME = "GROUP_BEAN_ALL";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property WxName = new Property(1, String.class, "wxName", false, "WX_NAME");
        public static final Property WxNum = new Property(2, String.class, "wxNum", false, "WX_NUM");
    }

    /* access modifiers changed from: protected */
    public final boolean isEntityUpdateable() {
        return true;
    }

    public GroupBeanAllDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public GroupBeanAllDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"GROUP_BEAN_ALL\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"WX_NAME\" TEXT,\"WX_NUM\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"GROUP_BEAN_ALL\"");
        database.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public final void bindValues(DatabaseStatement databaseStatement, GroupBeanAll groupBeanAll) {
        databaseStatement.clearBindings();
        Long id = groupBeanAll.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String wxName = groupBeanAll.getWxName();
        if (wxName != null) {
            databaseStatement.bindString(2, wxName);
        }
        String wxNum = groupBeanAll.getWxNum();
        if (wxNum != null) {
            databaseStatement.bindString(3, wxNum);
        }
    }

    /* access modifiers changed from: protected */
    public final void bindValues(SQLiteStatement sQLiteStatement, GroupBeanAll groupBeanAll) {
        sQLiteStatement.clearBindings();
        Long id = groupBeanAll.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String wxName = groupBeanAll.getWxName();
        if (wxName != null) {
            sQLiteStatement.bindString(2, wxName);
        }
        String wxNum = groupBeanAll.getWxNum();
        if (wxNum != null) {
            sQLiteStatement.bindString(3, wxNum);
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public GroupBeanAll readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        String str = null;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        if (!cursor.isNull(i4)) {
            str = cursor.getString(i4);
        }
        return new GroupBeanAll(valueOf, string, str);
    }

    public void readEntity(Cursor cursor, GroupBeanAll groupBeanAll, int i) {
        int i2 = i + 0;
        String str = null;
        groupBeanAll.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        groupBeanAll.setWxName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        if (!cursor.isNull(i4)) {
            str = cursor.getString(i4);
        }
        groupBeanAll.setWxNum(str);
    }

    /* access modifiers changed from: protected */
    public final Long updateKeyAfterInsert(GroupBeanAll groupBeanAll, long j) {
        groupBeanAll.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(GroupBeanAll groupBeanAll) {
        if (groupBeanAll != null) {
            return groupBeanAll.getId();
        }
        return null;
    }

    public boolean hasKey(GroupBeanAll groupBeanAll) {
        return groupBeanAll.getId() != null;
    }
}
