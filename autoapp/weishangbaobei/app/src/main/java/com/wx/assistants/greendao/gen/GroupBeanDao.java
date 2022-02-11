package com.wx.assistants.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.wx.assistants.Enity.GroupBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class GroupBeanDao extends AbstractDao<GroupBean, Long> {
    public static final String TABLENAME = "GROUP_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property WxName = new Property(1, String.class, "wxName", false, "WX_NAME");
        public static final Property WxNum = new Property(2, String.class, "wxNum", false, "WX_NUM");
    }

    /* access modifiers changed from: protected */
    public final boolean isEntityUpdateable() {
        return true;
    }

    public GroupBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public GroupBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"GROUP_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"WX_NAME\" TEXT,\"WX_NUM\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"GROUP_BEAN\"");
        database.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public final void bindValues(DatabaseStatement databaseStatement, GroupBean groupBean) {
        databaseStatement.clearBindings();
        Long id = groupBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String wxName = groupBean.getWxName();
        if (wxName != null) {
            databaseStatement.bindString(2, wxName);
        }
        String wxNum = groupBean.getWxNum();
        if (wxNum != null) {
            databaseStatement.bindString(3, wxNum);
        }
    }

    /* access modifiers changed from: protected */
    public final void bindValues(SQLiteStatement sQLiteStatement, GroupBean groupBean) {
        sQLiteStatement.clearBindings();
        Long id = groupBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String wxName = groupBean.getWxName();
        if (wxName != null) {
            sQLiteStatement.bindString(2, wxName);
        }
        String wxNum = groupBean.getWxNum();
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

    public GroupBean readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        String str = null;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        if (!cursor.isNull(i4)) {
            str = cursor.getString(i4);
        }
        return new GroupBean(valueOf, string, str);
    }

    public void readEntity(Cursor cursor, GroupBean groupBean, int i) {
        int i2 = i + 0;
        String str = null;
        groupBean.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        groupBean.setWxName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        if (!cursor.isNull(i4)) {
            str = cursor.getString(i4);
        }
        groupBean.setWxNum(str);
    }

    /* access modifiers changed from: protected */
    public final Long updateKeyAfterInsert(GroupBean groupBean, long j) {
        groupBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(GroupBean groupBean) {
        if (groupBean != null) {
            return groupBean.getId();
        }
        return null;
    }

    public boolean hasKey(GroupBean groupBean) {
        return groupBean.getId() != null;
    }
}
