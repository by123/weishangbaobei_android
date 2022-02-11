package com.wx.assistants.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.wx.assistants.Enity.TagPeoplesBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class TagPeoplesBeanDao extends AbstractDao<TagPeoplesBean, Long> {
    public static final String TABLENAME = "TAG_PEOPLES_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property WxName = new Property(1, String.class, "wxName", false, "WX_NAME");
        public static final Property WxNum = new Property(2, String.class, "wxNum", false, "WX_NUM");
        public static final Property WxPeoples = new Property(4, String.class, "wxPeoples", false, "WX_PEOPLES");
        public static final Property WxTagName = new Property(3, String.class, "wxTagName", false, "WX_TAG_NAME");
    }

    /* access modifiers changed from: protected */
    public final boolean isEntityUpdateable() {
        return true;
    }

    public TagPeoplesBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public TagPeoplesBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"TAG_PEOPLES_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"WX_NAME\" TEXT,\"WX_NUM\" TEXT,\"WX_TAG_NAME\" TEXT,\"WX_PEOPLES\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"TAG_PEOPLES_BEAN\"");
        database.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public final void bindValues(DatabaseStatement databaseStatement, TagPeoplesBean tagPeoplesBean) {
        databaseStatement.clearBindings();
        Long id = tagPeoplesBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String wxName = tagPeoplesBean.getWxName();
        if (wxName != null) {
            databaseStatement.bindString(2, wxName);
        }
        String wxNum = tagPeoplesBean.getWxNum();
        if (wxNum != null) {
            databaseStatement.bindString(3, wxNum);
        }
        String wxTagName = tagPeoplesBean.getWxTagName();
        if (wxTagName != null) {
            databaseStatement.bindString(4, wxTagName);
        }
        String wxPeoples = tagPeoplesBean.getWxPeoples();
        if (wxPeoples != null) {
            databaseStatement.bindString(5, wxPeoples);
        }
    }

    /* access modifiers changed from: protected */
    public final void bindValues(SQLiteStatement sQLiteStatement, TagPeoplesBean tagPeoplesBean) {
        sQLiteStatement.clearBindings();
        Long id = tagPeoplesBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String wxName = tagPeoplesBean.getWxName();
        if (wxName != null) {
            sQLiteStatement.bindString(2, wxName);
        }
        String wxNum = tagPeoplesBean.getWxNum();
        if (wxNum != null) {
            sQLiteStatement.bindString(3, wxNum);
        }
        String wxTagName = tagPeoplesBean.getWxTagName();
        if (wxTagName != null) {
            sQLiteStatement.bindString(4, wxTagName);
        }
        String wxPeoples = tagPeoplesBean.getWxPeoples();
        if (wxPeoples != null) {
            sQLiteStatement.bindString(5, wxPeoples);
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public TagPeoplesBean readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        int i6 = i + 4;
        return new TagPeoplesBean(valueOf, string, string2, cursor.isNull(i5) ? null : cursor.getString(i5), cursor.isNull(i6) ? null : cursor.getString(i6));
    }

    public void readEntity(Cursor cursor, TagPeoplesBean tagPeoplesBean, int i) {
        int i2 = i + 0;
        String str = null;
        tagPeoplesBean.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        tagPeoplesBean.setWxName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        tagPeoplesBean.setWxNum(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        tagPeoplesBean.setWxTagName(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        if (!cursor.isNull(i6)) {
            str = cursor.getString(i6);
        }
        tagPeoplesBean.setWxPeoples(str);
    }

    /* access modifiers changed from: protected */
    public final Long updateKeyAfterInsert(TagPeoplesBean tagPeoplesBean, long j) {
        tagPeoplesBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(TagPeoplesBean tagPeoplesBean) {
        if (tagPeoplesBean != null) {
            return tagPeoplesBean.getId();
        }
        return null;
    }

    public boolean hasKey(TagPeoplesBean tagPeoplesBean) {
        return tagPeoplesBean.getId() != null;
    }
}
