package com.wx.assistants.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.wx.assistants.Enity.TagBeanCompay;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class TagBeanCompayDao extends AbstractDao<TagBeanCompay, Long> {
    public static final String TABLENAME = "TAG_BEAN_COMPAY";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property WxCompany = new Property(2, String.class, "wxCompany", false, "WX_COMPANY");
        public static final Property WxName = new Property(1, String.class, "wxName", false, "WX_NAME");
    }

    /* access modifiers changed from: protected */
    public final boolean isEntityUpdateable() {
        return true;
    }

    public TagBeanCompayDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public TagBeanCompayDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"TAG_BEAN_COMPAY\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"WX_NAME\" TEXT,\"WX_COMPANY\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"TAG_BEAN_COMPAY\"");
        database.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public final void bindValues(DatabaseStatement databaseStatement, TagBeanCompay tagBeanCompay) {
        databaseStatement.clearBindings();
        Long id = tagBeanCompay.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String wxName = tagBeanCompay.getWxName();
        if (wxName != null) {
            databaseStatement.bindString(2, wxName);
        }
        String wxCompany = tagBeanCompay.getWxCompany();
        if (wxCompany != null) {
            databaseStatement.bindString(3, wxCompany);
        }
    }

    /* access modifiers changed from: protected */
    public final void bindValues(SQLiteStatement sQLiteStatement, TagBeanCompay tagBeanCompay) {
        sQLiteStatement.clearBindings();
        Long id = tagBeanCompay.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String wxName = tagBeanCompay.getWxName();
        if (wxName != null) {
            sQLiteStatement.bindString(2, wxName);
        }
        String wxCompany = tagBeanCompay.getWxCompany();
        if (wxCompany != null) {
            sQLiteStatement.bindString(3, wxCompany);
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public TagBeanCompay readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        String str = null;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        if (!cursor.isNull(i4)) {
            str = cursor.getString(i4);
        }
        return new TagBeanCompay(valueOf, string, str);
    }

    public void readEntity(Cursor cursor, TagBeanCompay tagBeanCompay, int i) {
        int i2 = i + 0;
        String str = null;
        tagBeanCompay.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        tagBeanCompay.setWxName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        if (!cursor.isNull(i4)) {
            str = cursor.getString(i4);
        }
        tagBeanCompay.setWxCompany(str);
    }

    /* access modifiers changed from: protected */
    public final Long updateKeyAfterInsert(TagBeanCompay tagBeanCompay, long j) {
        tagBeanCompay.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(TagBeanCompay tagBeanCompay) {
        if (tagBeanCompay != null) {
            return tagBeanCompay.getId();
        }
        return null;
    }

    public boolean hasKey(TagBeanCompay tagBeanCompay) {
        return tagBeanCompay.getId() != null;
    }
}
