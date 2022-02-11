package com.wx.assistants.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.wx.assistants.Enity.GroupMemberBeanAll;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class GroupMemberBeanAllDao extends AbstractDao<GroupMemberBeanAll, Long> {
    public static final String TABLENAME = "GROUP_MEMBER_BEAN_ALL";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property WxGroups = new Property(3, String.class, "wxGroups", false, "WX_GROUPS");
        public static final Property WxName = new Property(1, String.class, "wxName", false, "WX_NAME");
        public static final Property WxNum = new Property(2, String.class, "wxNum", false, "WX_NUM");
    }

    /* access modifiers changed from: protected */
    public final boolean isEntityUpdateable() {
        return true;
    }

    public GroupMemberBeanAllDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public GroupMemberBeanAllDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"GROUP_MEMBER_BEAN_ALL\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"WX_NAME\" TEXT,\"WX_NUM\" TEXT,\"WX_GROUPS\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"GROUP_MEMBER_BEAN_ALL\"");
        database.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public final void bindValues(DatabaseStatement databaseStatement, GroupMemberBeanAll groupMemberBeanAll) {
        databaseStatement.clearBindings();
        Long id = groupMemberBeanAll.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String wxName = groupMemberBeanAll.getWxName();
        if (wxName != null) {
            databaseStatement.bindString(2, wxName);
        }
        String wxNum = groupMemberBeanAll.getWxNum();
        if (wxNum != null) {
            databaseStatement.bindString(3, wxNum);
        }
        String wxGroups = groupMemberBeanAll.getWxGroups();
        if (wxGroups != null) {
            databaseStatement.bindString(4, wxGroups);
        }
    }

    /* access modifiers changed from: protected */
    public final void bindValues(SQLiteStatement sQLiteStatement, GroupMemberBeanAll groupMemberBeanAll) {
        sQLiteStatement.clearBindings();
        Long id = groupMemberBeanAll.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String wxName = groupMemberBeanAll.getWxName();
        if (wxName != null) {
            sQLiteStatement.bindString(2, wxName);
        }
        String wxNum = groupMemberBeanAll.getWxNum();
        if (wxNum != null) {
            sQLiteStatement.bindString(3, wxNum);
        }
        String wxGroups = groupMemberBeanAll.getWxGroups();
        if (wxGroups != null) {
            sQLiteStatement.bindString(4, wxGroups);
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public GroupMemberBeanAll readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        String str = null;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            str = cursor.getString(i5);
        }
        return new GroupMemberBeanAll(valueOf, string, string2, str);
    }

    public void readEntity(Cursor cursor, GroupMemberBeanAll groupMemberBeanAll, int i) {
        int i2 = i + 0;
        String str = null;
        groupMemberBeanAll.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        groupMemberBeanAll.setWxName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        groupMemberBeanAll.setWxNum(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            str = cursor.getString(i5);
        }
        groupMemberBeanAll.setWxGroups(str);
    }

    /* access modifiers changed from: protected */
    public final Long updateKeyAfterInsert(GroupMemberBeanAll groupMemberBeanAll, long j) {
        groupMemberBeanAll.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(GroupMemberBeanAll groupMemberBeanAll) {
        if (groupMemberBeanAll != null) {
            return groupMemberBeanAll.getId();
        }
        return null;
    }

    public boolean hasKey(GroupMemberBeanAll groupMemberBeanAll) {
        return groupMemberBeanAll.getId() != null;
    }
}
