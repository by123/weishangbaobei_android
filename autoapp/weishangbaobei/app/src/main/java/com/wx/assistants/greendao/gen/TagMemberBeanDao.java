package com.wx.assistants.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.wx.assistants.Enity.TagMemberBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class TagMemberBeanDao extends AbstractDao<TagMemberBean, Long> {
    public static final String TABLENAME = "TAG_MEMBER_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property WxName = new Property(1, String.class, "wxName", false, "WX_NAME");
        public static final Property WxNum = new Property(2, String.class, "wxNum", false, "WX_NUM");
        public static final Property WxTags = new Property(3, String.class, "wxTags", false, "WX_TAGS");
    }

    /* access modifiers changed from: protected */
    public final boolean isEntityUpdateable() {
        return true;
    }

    public TagMemberBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public TagMemberBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"TAG_MEMBER_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"WX_NAME\" TEXT,\"WX_NUM\" TEXT,\"WX_TAGS\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"TAG_MEMBER_BEAN\"");
        database.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public final void bindValues(DatabaseStatement databaseStatement, TagMemberBean tagMemberBean) {
        databaseStatement.clearBindings();
        Long id = tagMemberBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String wxName = tagMemberBean.getWxName();
        if (wxName != null) {
            databaseStatement.bindString(2, wxName);
        }
        String wxNum = tagMemberBean.getWxNum();
        if (wxNum != null) {
            databaseStatement.bindString(3, wxNum);
        }
        String wxTags = tagMemberBean.getWxTags();
        if (wxTags != null) {
            databaseStatement.bindString(4, wxTags);
        }
    }

    /* access modifiers changed from: protected */
    public final void bindValues(SQLiteStatement sQLiteStatement, TagMemberBean tagMemberBean) {
        sQLiteStatement.clearBindings();
        Long id = tagMemberBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String wxName = tagMemberBean.getWxName();
        if (wxName != null) {
            sQLiteStatement.bindString(2, wxName);
        }
        String wxNum = tagMemberBean.getWxNum();
        if (wxNum != null) {
            sQLiteStatement.bindString(3, wxNum);
        }
        String wxTags = tagMemberBean.getWxTags();
        if (wxTags != null) {
            sQLiteStatement.bindString(4, wxTags);
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public TagMemberBean readEntity(Cursor cursor, int i) {
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
        return new TagMemberBean(valueOf, string, string2, str);
    }

    public void readEntity(Cursor cursor, TagMemberBean tagMemberBean, int i) {
        int i2 = i + 0;
        String str = null;
        tagMemberBean.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        tagMemberBean.setWxName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        tagMemberBean.setWxNum(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            str = cursor.getString(i5);
        }
        tagMemberBean.setWxTags(str);
    }

    /* access modifiers changed from: protected */
    public final Long updateKeyAfterInsert(TagMemberBean tagMemberBean, long j) {
        tagMemberBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(TagMemberBean tagMemberBean) {
        if (tagMemberBean != null) {
            return tagMemberBean.getId();
        }
        return null;
    }

    public boolean hasKey(TagMemberBean tagMemberBean) {
        return tagMemberBean.getId() != null;
    }
}
