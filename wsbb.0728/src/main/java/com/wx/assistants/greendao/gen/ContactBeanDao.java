package com.wx.assistants.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.wx.assistants.Enity.ContactBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class ContactBeanDao extends AbstractDao<ContactBean, Long> {
    public static final String TABLENAME = "CONTACT_BEAN";

    public static class Properties {
        public static final Property Checked = new Property(3, Boolean.TYPE, "checked", false, "CHECKED");
        public static final Property Extra = new Property(5, String.class, "extra", false, "EXTRA");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property IsAdded = new Property(4, Boolean.TYPE, "isAdded", false, "IS_ADDED");
        public static final Property NickName = new Property(2, String.class, "nickName", false, "NICK_NAME");
        public static final Property Number = new Property(1, String.class, "number", false, "NUMBER");
    }

    public ContactBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ContactBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"CONTACT_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"NUMBER\" TEXT,\"NICK_NAME\" TEXT,\"CHECKED\" INTEGER NOT NULL ,\"IS_ADDED\" INTEGER NOT NULL ,\"EXTRA\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"CONTACT_BEAN\"");
        database.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public final void bindValues(SQLiteStatement sQLiteStatement, ContactBean contactBean) {
        long j = 1;
        sQLiteStatement.clearBindings();
        Long id = contactBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String number = contactBean.getNumber();
        if (number != null) {
            sQLiteStatement.bindString(2, number);
        }
        String nickName = contactBean.getNickName();
        if (nickName != null) {
            sQLiteStatement.bindString(3, nickName);
        }
        sQLiteStatement.bindLong(4, contactBean.getChecked() ? 1 : 0);
        if (!contactBean.getIsAdded()) {
            j = 0;
        }
        sQLiteStatement.bindLong(5, j);
        String extra = contactBean.getExtra();
        if (extra != null) {
            sQLiteStatement.bindString(6, extra);
        }
    }

    /* access modifiers changed from: protected */
    public final void bindValues(DatabaseStatement databaseStatement, ContactBean contactBean) {
        long j = 1;
        databaseStatement.clearBindings();
        Long id = contactBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String number = contactBean.getNumber();
        if (number != null) {
            databaseStatement.bindString(2, number);
        }
        String nickName = contactBean.getNickName();
        if (nickName != null) {
            databaseStatement.bindString(3, nickName);
        }
        databaseStatement.bindLong(4, contactBean.getChecked() ? 1 : 0);
        if (!contactBean.getIsAdded()) {
            j = 0;
        }
        databaseStatement.bindLong(5, j);
        String extra = contactBean.getExtra();
        if (extra != null) {
            databaseStatement.bindString(6, extra);
        }
    }

    public Long getKey(ContactBean contactBean) {
        if (contactBean != null) {
            return contactBean.getId();
        }
        return null;
    }

    public boolean hasKey(ContactBean contactBean) {
        return contactBean.getId() != null;
    }

    /* access modifiers changed from: protected */
    public final boolean isEntityUpdateable() {
        return true;
    }

    public ContactBean readEntity(Cursor cursor, int i) {
        boolean z = true;
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        boolean z2 = cursor.getShort(i + 3) != 0;
        if (cursor.getShort(i + 4) == 0) {
            z = false;
        }
        int i5 = i + 5;
        return new ContactBean(valueOf, string, string2, z2, z, cursor.isNull(i5) ? null : cursor.getString(i5));
    }

    public void readEntity(Cursor cursor, ContactBean contactBean, int i) {
        boolean z = true;
        String str = null;
        int i2 = i + 0;
        contactBean.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        contactBean.setNumber(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        contactBean.setNickName(cursor.isNull(i4) ? null : cursor.getString(i4));
        contactBean.setChecked(cursor.getShort(i + 3) != 0);
        if (cursor.getShort(i + 4) == 0) {
            z = false;
        }
        contactBean.setIsAdded(z);
        int i5 = i + 5;
        if (!cursor.isNull(i5)) {
            str = cursor.getString(i5);
        }
        contactBean.setExtra(str);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    /* access modifiers changed from: protected */
    public final Long updateKeyAfterInsert(ContactBean contactBean, long j) {
        contactBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
