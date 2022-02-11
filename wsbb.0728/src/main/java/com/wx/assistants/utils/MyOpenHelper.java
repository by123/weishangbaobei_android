package com.wx.assistants.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.wx.assistants.greendao.gen.ContactBeanDao;
import com.wx.assistants.greendao.gen.ContactScanBeanDao;
import com.wx.assistants.greendao.gen.CopyFriendBeanDao;
import com.wx.assistants.greendao.gen.DaoMaster;
import com.wx.assistants.greendao.gen.GroupBeanAllDao;
import com.wx.assistants.greendao.gen.GroupBeanDao;
import com.wx.assistants.greendao.gen.GroupMemberBeanAllDao;
import com.wx.assistants.greendao.gen.GroupMemberBeanDao;
import com.wx.assistants.greendao.gen.MusicBeanDao;
import com.wx.assistants.greendao.gen.RecordBeanDao;
import com.wx.assistants.greendao.gen.RecoverFriendBeanDao;
import com.wx.assistants.greendao.gen.TagBeanCompayDao;
import com.wx.assistants.greendao.gen.TagBeanDao;
import com.wx.assistants.greendao.gen.TagMemberBeanCompanyDao;
import com.wx.assistants.greendao.gen.TagMemberBeanDao;
import com.wx.assistants.greendao.gen.TagPeoplesBeanDao;
import org.greenrobot.greendao.database.Database;

public class MyOpenHelper extends DaoMaster.OpenHelper {
    public MyOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
        super(context, str, cursorFactory);
    }

    public void onUpgrade(Database database, int i, int i2) {
        MigrationHelper.migrate(database, new MigrationHelper.ReCreateAllTableListener() {
            public void onCreateAllTables(Database database, boolean z) {
                DaoMaster.createAllTables(database, z);
            }

            public void onDropAllTables(Database database, boolean z) {
                DaoMaster.dropAllTables(database, z);
            }
        }, new Class[]{RecordBeanDao.class, ContactBeanDao.class, ContactScanBeanDao.class, CopyFriendBeanDao.class, GroupBeanDao.class, GroupMemberBeanDao.class, MusicBeanDao.class, RecoverFriendBeanDao.class, TagBeanDao.class, TagMemberBeanDao.class, TagPeoplesBeanDao.class, TagBeanCompayDao.class, TagMemberBeanCompanyDao.class, GroupMemberBeanAllDao.class, GroupBeanAllDao.class});
    }
}
