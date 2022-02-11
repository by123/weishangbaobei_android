package com.wx.assistants.greendao.gen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 27;

    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String str) {
            super(context, str);
        }

        public DevOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory);
        }

        public void onUpgrade(Database database, int i, int i2) {
            Log.i("greenDAO", "Upgrading schema from version " + i + " to " + i2 + " by dropping all tables");
            DaoMaster.dropAllTables(database, true);
            onCreate(database);
        }
    }

    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String str) {
            super(context, str, 27);
        }

        public OpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory, 27);
        }

        public void onCreate(Database database) {
            Log.i("greenDAO", "Creating tables for schema version 27");
            DaoMaster.createAllTables(database, false);
        }
    }

    public DaoMaster(SQLiteDatabase sQLiteDatabase) {
        this((Database) new StandardDatabase(sQLiteDatabase));
    }

    public DaoMaster(Database database) {
        super(database, 27);
        registerDaoClass(TagBeanDao.class);
        registerDaoClass(ContactBeanDao.class);
        registerDaoClass(CopyFriendBeanDao.class);
        registerDaoClass(GroupBeanDao.class);
        registerDaoClass(GroupMemberBeanAllDao.class);
        registerDaoClass(GroupMemberBeanDao.class);
        registerDaoClass(MusicBeanDao.class);
        registerDaoClass(RecoverFriendBeanDao.class);
        registerDaoClass(TagPeoplesBeanDao.class);
        registerDaoClass(TagMemberBeanDao.class);
        registerDaoClass(TagMemberBeanCompanyDao.class);
        registerDaoClass(RecordBeanDao.class);
        registerDaoClass(ContactScanBeanDao.class);
        registerDaoClass(GroupBeanAllDao.class);
        registerDaoClass(TagBeanCompayDao.class);
    }

    public static void createAllTables(Database database, boolean z) {
        TagBeanDao.createTable(database, z);
        ContactBeanDao.createTable(database, z);
        CopyFriendBeanDao.createTable(database, z);
        GroupBeanDao.createTable(database, z);
        GroupMemberBeanAllDao.createTable(database, z);
        GroupMemberBeanDao.createTable(database, z);
        MusicBeanDao.createTable(database, z);
        RecoverFriendBeanDao.createTable(database, z);
        TagPeoplesBeanDao.createTable(database, z);
        TagMemberBeanDao.createTable(database, z);
        TagMemberBeanCompanyDao.createTable(database, z);
        RecordBeanDao.createTable(database, z);
        ContactScanBeanDao.createTable(database, z);
        GroupBeanAllDao.createTable(database, z);
        TagBeanCompayDao.createTable(database, z);
    }

    public static void dropAllTables(Database database, boolean z) {
        TagBeanDao.dropTable(database, z);
        ContactBeanDao.dropTable(database, z);
        CopyFriendBeanDao.dropTable(database, z);
        GroupBeanDao.dropTable(database, z);
        GroupMemberBeanAllDao.dropTable(database, z);
        GroupMemberBeanDao.dropTable(database, z);
        MusicBeanDao.dropTable(database, z);
        RecoverFriendBeanDao.dropTable(database, z);
        TagPeoplesBeanDao.dropTable(database, z);
        TagMemberBeanDao.dropTable(database, z);
        TagMemberBeanCompanyDao.dropTable(database, z);
        RecordBeanDao.dropTable(database, z);
        ContactScanBeanDao.dropTable(database, z);
        GroupBeanAllDao.dropTable(database, z);
        TagBeanCompayDao.dropTable(database, z);
    }

    public static DaoSession newDevSession(Context context, String str) {
        return new DaoMaster(new DevOpenHelper(context, str).getWritableDb()).newSession();
    }

    public DaoSession newSession() {
        return new DaoSession(this.db, IdentityScopeType.Session, this.daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType identityScopeType) {
        return new DaoSession(this.db, identityScopeType, this.daoConfigMap);
    }
}
