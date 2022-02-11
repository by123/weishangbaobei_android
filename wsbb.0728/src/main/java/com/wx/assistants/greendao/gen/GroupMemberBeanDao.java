package com.wx.assistants.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.wx.assistants.Enity.GroupMemberBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class GroupMemberBeanDao extends AbstractDao<GroupMemberBean, Long> {
    public static final String TABLENAME = "GROUP_MEMBER_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property WxGroups = new Property(3, String.class, "wxGroups", false, "WX_GROUPS");
        public static final Property WxName = new Property(1, String.class, "wxName", false, "WX_NAME");
        public static final Property WxNum = new Property(2, String.class, "wxNum", false, "WX_NUM");
    }

    public GroupMemberBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public GroupMemberBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"GROUP_MEMBER_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"WX_NAME\" TEXT,\"WX_NUM\" TEXT,\"WX_GROUPS\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"GROUP_MEMBER_BEAN\"");
        database.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public final void bindValues(SQLiteStatement sQLiteStatement, GroupMemberBean groupMemberBean) {
        sQLiteStatement.clearBindings();
        Long id = groupMemberBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String wxName = groupMemberBean.getWxName();
        if (wxName != null) {
            sQLiteStatement.bindString(2, wxName);
        }
        String wxNum = groupMemberBean.getWxNum();
        if (wxNum != null) {
            sQLiteStatement.bindString(3, wxNum);
        }
        String wxGroups = groupMemberBean.getWxGroups();
        if (wxGroups != null) {
            sQLiteStatement.bindString(4, wxGroups);
        }
    }

    /* access modifiers changed from: protected */
    public final void bindValues(DatabaseStatement databaseStatement, GroupMemberBean groupMemberBean) {
        databaseStatement.clearBindings();
        Long id = groupMemberBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String wxName = groupMemberBean.getWxName();
        if (wxName != null) {
            databaseStatement.bindString(2, wxName);
        }
        String wxNum = groupMemberBean.getWxNum();
        if (wxNum != null) {
            databaseStatement.bindString(3, wxNum);
        }
        String wxGroups = groupMemberBean.getWxGroups();
        if (wxGroups != null) {
            databaseStatement.bindString(4, wxGroups);
        }
    }

    public Long getKey(GroupMemberBean groupMemberBean) {
        if (groupMemberBean != null) {
            return groupMemberBean.getId();
        }
        return null;
    }

    public boolean hasKey(GroupMemberBean groupMemberBean) {
        return groupMemberBean.getId() != null;
    }

    /* access modifiers changed from: protected */
    public final boolean isEntityUpdateable() {
        return true;
    }

    public GroupMemberBean readEntity(Cursor cursor, int i) {
        String str = null;
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            str = cursor.getString(i5);
        }
        return new GroupMemberBean(valueOf, string, string2, str);
    }

    public void readEntity(Cursor cursor, GroupMemberBean groupMemberBean, int i) {
        String str = null;
        int i2 = i + 0;
        groupMemberBean.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        groupMemberBean.setWxName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        groupMemberBean.setWxNum(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            str = cursor.getString(i5);
        }
        groupMemberBean.setWxGroups(str);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    /* access modifiers changed from: protected */
    public final Long updateKeyAfterInsert(GroupMemberBean groupMemberBean, long j) {
        groupMemberBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
