package com.wx.assistants.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.wx.assistants.Enity.TagMemberBeanCompany;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class TagMemberBeanCompanyDao extends AbstractDao<TagMemberBeanCompany, Long> {
    public static final String TABLENAME = "TAG_MEMBER_BEAN_COMPANY";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property WxCompany = new Property(2, String.class, "wxCompany", false, "WX_COMPANY");
        public static final Property WxName = new Property(1, String.class, "wxName", false, "WX_NAME");
        public static final Property WxTags = new Property(3, String.class, "wxTags", false, "WX_TAGS");
    }

    /* access modifiers changed from: protected */
    public final boolean isEntityUpdateable() {
        return true;
    }

    public TagMemberBeanCompanyDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public TagMemberBeanCompanyDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"TAG_MEMBER_BEAN_COMPANY\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"WX_NAME\" TEXT,\"WX_COMPANY\" TEXT,\"WX_TAGS\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"TAG_MEMBER_BEAN_COMPANY\"");
        database.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public final void bindValues(DatabaseStatement databaseStatement, TagMemberBeanCompany tagMemberBeanCompany) {
        databaseStatement.clearBindings();
        Long id = tagMemberBeanCompany.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String wxName = tagMemberBeanCompany.getWxName();
        if (wxName != null) {
            databaseStatement.bindString(2, wxName);
        }
        String wxCompany = tagMemberBeanCompany.getWxCompany();
        if (wxCompany != null) {
            databaseStatement.bindString(3, wxCompany);
        }
        String wxTags = tagMemberBeanCompany.getWxTags();
        if (wxTags != null) {
            databaseStatement.bindString(4, wxTags);
        }
    }

    /* access modifiers changed from: protected */
    public final void bindValues(SQLiteStatement sQLiteStatement, TagMemberBeanCompany tagMemberBeanCompany) {
        sQLiteStatement.clearBindings();
        Long id = tagMemberBeanCompany.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String wxName = tagMemberBeanCompany.getWxName();
        if (wxName != null) {
            sQLiteStatement.bindString(2, wxName);
        }
        String wxCompany = tagMemberBeanCompany.getWxCompany();
        if (wxCompany != null) {
            sQLiteStatement.bindString(3, wxCompany);
        }
        String wxTags = tagMemberBeanCompany.getWxTags();
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

    public TagMemberBeanCompany readEntity(Cursor cursor, int i) {
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
        return new TagMemberBeanCompany(valueOf, string, string2, str);
    }

    public void readEntity(Cursor cursor, TagMemberBeanCompany tagMemberBeanCompany, int i) {
        int i2 = i + 0;
        String str = null;
        tagMemberBeanCompany.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        tagMemberBeanCompany.setWxName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        tagMemberBeanCompany.setWxCompany(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            str = cursor.getString(i5);
        }
        tagMemberBeanCompany.setWxTags(str);
    }

    /* access modifiers changed from: protected */
    public final Long updateKeyAfterInsert(TagMemberBeanCompany tagMemberBeanCompany, long j) {
        tagMemberBeanCompany.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(TagMemberBeanCompany tagMemberBeanCompany) {
        if (tagMemberBeanCompany != null) {
            return tagMemberBeanCompany.getId();
        }
        return null;
    }

    public boolean hasKey(TagMemberBeanCompany tagMemberBeanCompany) {
        return tagMemberBeanCompany.getId() != null;
    }
}
