package com.wx.assistants.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.wx.assistants.Enity.ContactScanBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class ContactScanBeanDao extends AbstractDao<ContactScanBean, Long> {
    public static final String TABLENAME = "CONTACT_SCAN_BEAN";

    public static class Properties {
        public static final Property AddResultType = new Property(5, Integer.TYPE, "addResultType", false, "ADD_RESULT_TYPE");
        public static final Property Checked = new Property(3, Boolean.TYPE, "checked", false, "CHECKED");
        public static final Property CreateTime = new Property(7, Long.TYPE, "createTime", false, "CREATE_TIME");
        public static final Property Extra = new Property(6, String.class, "extra", false, "EXTRA");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property IsAdded = new Property(4, Boolean.TYPE, "isAdded", false, "IS_ADDED");
        public static final Property NickName = new Property(2, String.class, "nickName", false, "NICK_NAME");
        public static final Property Number = new Property(1, String.class, "number", false, "NUMBER");
    }

    /* access modifiers changed from: protected */
    public final boolean isEntityUpdateable() {
        return true;
    }

    public ContactScanBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ContactScanBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"CONTACT_SCAN_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"NUMBER\" TEXT,\"NICK_NAME\" TEXT,\"CHECKED\" INTEGER NOT NULL ,\"IS_ADDED\" INTEGER NOT NULL ,\"ADD_RESULT_TYPE\" INTEGER NOT NULL ,\"EXTRA\" TEXT,\"CREATE_TIME\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"CONTACT_SCAN_BEAN\"");
        database.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public final void bindValues(DatabaseStatement databaseStatement, ContactScanBean contactScanBean) {
        databaseStatement.clearBindings();
        Long id = contactScanBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String number = contactScanBean.getNumber();
        if (number != null) {
            databaseStatement.bindString(2, number);
        }
        String nickName = contactScanBean.getNickName();
        if (nickName != null) {
            databaseStatement.bindString(3, nickName);
        }
        long j = 0;
        databaseStatement.bindLong(4, contactScanBean.getChecked() ? 1 : 0);
        if (contactScanBean.getIsAdded()) {
            j = 1;
        }
        databaseStatement.bindLong(5, j);
        databaseStatement.bindLong(6, (long) contactScanBean.getAddResultType());
        String extra = contactScanBean.getExtra();
        if (extra != null) {
            databaseStatement.bindString(7, extra);
        }
        databaseStatement.bindLong(8, contactScanBean.getCreateTime());
    }

    /* access modifiers changed from: protected */
    public final void bindValues(SQLiteStatement sQLiteStatement, ContactScanBean contactScanBean) {
        sQLiteStatement.clearBindings();
        Long id = contactScanBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String number = contactScanBean.getNumber();
        if (number != null) {
            sQLiteStatement.bindString(2, number);
        }
        String nickName = contactScanBean.getNickName();
        if (nickName != null) {
            sQLiteStatement.bindString(3, nickName);
        }
        long j = 0;
        sQLiteStatement.bindLong(4, contactScanBean.getChecked() ? 1 : 0);
        if (contactScanBean.getIsAdded()) {
            j = 1;
        }
        sQLiteStatement.bindLong(5, j);
        sQLiteStatement.bindLong(6, (long) contactScanBean.getAddResultType());
        String extra = contactScanBean.getExtra();
        if (extra != null) {
            sQLiteStatement.bindString(7, extra);
        }
        sQLiteStatement.bindLong(8, contactScanBean.getCreateTime());
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public ContactScanBean readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        boolean z = true;
        boolean z2 = cursor.getShort(i + 3) != 0;
        if (cursor.getShort(i + 4) == 0) {
            z = false;
        }
        int i5 = i + 6;
        return new ContactScanBean(valueOf, string, string2, z2, z, cursor.getInt(i + 5), cursor.isNull(i5) ? null : cursor.getString(i5), cursor.getLong(i + 7));
    }

    public void readEntity(Cursor cursor, ContactScanBean contactScanBean, int i) {
        int i2 = i + 0;
        String str = null;
        contactScanBean.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        contactScanBean.setNumber(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        contactScanBean.setNickName(cursor.isNull(i4) ? null : cursor.getString(i4));
        boolean z = true;
        contactScanBean.setChecked(cursor.getShort(i + 3) != 0);
        if (cursor.getShort(i + 4) == 0) {
            z = false;
        }
        contactScanBean.setIsAdded(z);
        contactScanBean.setAddResultType(cursor.getInt(i + 5));
        int i5 = i + 6;
        if (!cursor.isNull(i5)) {
            str = cursor.getString(i5);
        }
        contactScanBean.setExtra(str);
        contactScanBean.setCreateTime(cursor.getLong(i + 7));
    }

    /* access modifiers changed from: protected */
    public final Long updateKeyAfterInsert(ContactScanBean contactScanBean, long j) {
        contactScanBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(ContactScanBean contactScanBean) {
        if (contactScanBean != null) {
            return contactScanBean.getId();
        }
        return null;
    }

    public boolean hasKey(ContactScanBean contactScanBean) {
        return contactScanBean.getId() != null;
    }
}
