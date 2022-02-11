package com.wx.assistants.utils;

import com.wx.assistants.Enity.ContactBean;
import com.wx.assistants.Enity.ContactScanBean;
import com.wx.assistants.Enity.CopyFriendBean;
import com.wx.assistants.Enity.GroupBean;
import com.wx.assistants.Enity.GroupBeanAll;
import com.wx.assistants.Enity.GroupMemberBean;
import com.wx.assistants.Enity.GroupMemberBeanAll;
import com.wx.assistants.Enity.MusicBean;
import com.wx.assistants.Enity.RecordBean;
import com.wx.assistants.Enity.RecoverFriendBean;
import com.wx.assistants.Enity.TagBean;
import com.wx.assistants.Enity.TagBeanCompay;
import com.wx.assistants.Enity.TagMemberBean;
import com.wx.assistants.Enity.TagMemberBeanCompany;
import com.wx.assistants.Enity.TagPeoplesBean;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.greendao.gen.ContactBeanDao;
import com.wx.assistants.greendao.gen.ContactScanBeanDao;
import com.wx.assistants.greendao.gen.CopyFriendBeanDao;
import com.wx.assistants.greendao.gen.DaoSession;
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
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.WhereCondition;

public class SQLiteUtils {
    private static SQLiteUtils instance;
    ContactBeanDao contactBeanDao = this.daoSession.getContactBeanDao();
    ContactScanBeanDao contactScanBeanDao = this.daoSession.getContactScanBeanDao();
    CopyFriendBeanDao copyFriendBeanDao = this.daoSession.getCopyFriendBeanDao();
    DaoSession daoSession = MyApplication.instance.getDaoSession();
    GroupBeanDao groupBeanDao = this.daoSession.getGroupBeanDao();
    GroupBeanAllDao groupBeanDaoAll = this.daoSession.getGroupBeanAllDao();
    GroupMemberBeanDao groupMemberBeanDao = this.daoSession.getGroupMemberBeanDao();
    GroupMemberBeanAllDao groupMemberBeanDaoAll = this.daoSession.getGroupMemberBeanAllDao();
    MusicBeanDao musicBeanDao = this.daoSession.getMusicBeanDao();
    RecordBeanDao recordBeanDao = this.daoSession.getRecordBeanDao();
    RecoverFriendBeanDao recoverFriendBeanDao = this.daoSession.getRecoverFriendBeanDao();
    TagBeanCompayDao tagBeanCompayDao = this.daoSession.getTagBeanCompayDao();
    TagBeanDao tagBeanDao = this.daoSession.getTagBeanDao();
    TagMemberBeanCompanyDao tagMemberBeanCompanyDao = this.daoSession.getTagMemberBeanCompanyDao();
    TagMemberBeanDao tagMemberBeanDao = this.daoSession.getTagMemberBeanDao();
    TagPeoplesBeanDao tagPeoplesBeanDao = this.daoSession.getTagPeoplesBeanDao();

    private SQLiteUtils() {
    }

    public static SQLiteUtils getInstance() {
        if (instance == null) {
            synchronized (SQLiteUtils.class) {
                try {
                    if (instance == null) {
                        instance = new SQLiteUtils();
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<SQLiteUtils> cls = SQLiteUtils.class;
                        throw th;
                    }
                }
            }
        }
        return instance;
    }

    public void addContact(ContactBean contactBean) {
        this.contactBeanDao.insert(contactBean);
    }

    public void addContactScan(ContactScanBean contactScanBean) {
        List list = this.contactScanBeanDao.queryBuilder().where(ContactScanBeanDao.Properties.Number.eq(contactScanBean.getNumber()), new WhereCondition[0]).build().list();
        if (list == null || list.size() == 0) {
            this.contactScanBeanDao.insert(contactScanBean);
        }
    }

    public void addCopyFriend(CopyFriendBean copyFriendBean) {
        this.copyFriendBeanDao.insert(copyFriendBean);
    }

    public void addFriend(RecoverFriendBean recoverFriendBean) {
        this.recoverFriendBeanDao.insert(recoverFriendBean);
    }

    public void addGroupBean(GroupBean groupBean) {
        List groupBeans = getInstance().getGroupBeans(groupBean.getWxNum());
        if (groupBeans != null && groupBeans.size() > 0) {
            for (int i = 0; i < groupBeans.size(); i++) {
                this.groupBeanDao.delete(groupBeans.get(i));
            }
        }
        this.groupBeanDao.insert(groupBean);
    }

    public void addGroupBeanAll(GroupBeanAll groupBeanAll) {
        List groupBeansAll = getInstance().getGroupBeansAll(groupBeanAll.getWxNum());
        if (groupBeansAll != null && groupBeansAll.size() > 0) {
            for (int i = 0; i < groupBeansAll.size(); i++) {
                this.groupBeanDaoAll.delete(groupBeansAll.get(i));
            }
        }
        this.groupBeanDaoAll.insert(groupBeanAll);
    }

    public void addGroupMemberBean(GroupMemberBean groupMemberBean) {
        List groupMemberBeans = getInstance().getGroupMemberBeans(groupMemberBean.getWxNum());
        if (groupMemberBeans != null && groupMemberBeans.size() > 0) {
            for (int i = 0; i < groupMemberBeans.size(); i++) {
                this.groupMemberBeanDao.delete(groupMemberBeans.get(i));
            }
        }
        this.groupMemberBeanDao.insert(groupMemberBean);
    }

    public void addGroupMemberBeanAll(GroupMemberBeanAll groupMemberBeanAll) {
        List groupMemberBeansAll = getInstance().getGroupMemberBeansAll(groupMemberBeanAll.getWxNum());
        if (groupMemberBeansAll != null && groupMemberBeansAll.size() > 0) {
            for (int i = 0; i < groupMemberBeansAll.size(); i++) {
                this.groupMemberBeanDaoAll.delete(groupMemberBeansAll.get(i));
            }
        }
        this.groupMemberBeanDaoAll.insert(groupMemberBeanAll);
    }

    public void addMusic(MusicBean musicBean) {
        List list = this.musicBeanDao.queryBuilder().where(MusicBeanDao.Properties.Title.eq(musicBean.getTitle()), new WhereCondition[0]).build().list();
        if (list == null || list.size() == 0) {
            this.musicBeanDao.insert(musicBean);
        } else {
            this.musicBeanDao.update(musicBean);
        }
    }

    public void addRecord(RecordBean recordBean) {
        List list = this.recordBeanDao.queryBuilder().where(RecordBeanDao.Properties.Path.eq(recordBean.getPath()), new WhereCondition[0]).build().list();
        if (list == null || list.size() == 0) {
            this.recordBeanDao.insert(recordBean);
        } else {
            this.recordBeanDao.update(recordBean);
        }
    }

    public void addTagBean(TagBean tagBean) {
        List tagBeans = getInstance().getTagBeans(tagBean.getWxNum());
        if (tagBeans != null && tagBeans.size() > 0) {
            for (int i = 0; i < tagBeans.size(); i++) {
                this.tagBeanDao.delete(tagBeans.get(i));
            }
        }
        this.tagBeanDao.insert(tagBean);
    }

    public void addTagBeanCompany(TagBeanCompay tagBeanCompay) {
        List tagBeansCompany = getInstance().getTagBeansCompany(tagBeanCompay.getWxCompany());
        if (tagBeansCompany != null && tagBeansCompany.size() > 0) {
            for (int i = 0; i < tagBeansCompany.size(); i++) {
                this.tagBeanCompayDao.delete(tagBeansCompany.get(i));
            }
        }
        this.tagBeanCompayDao.insert(tagBeanCompay);
    }

    public void addTagMemberBeanCompany(TagMemberBeanCompany tagMemberBeanCompany) {
        List tagMemberBeansCompany = getInstance().getTagMemberBeansCompany(tagMemberBeanCompany.getWxCompany());
        if (tagMemberBeansCompany != null && tagMemberBeansCompany.size() > 0) {
            for (int i = 0; i < tagMemberBeansCompany.size(); i++) {
                this.tagMemberBeanCompanyDao.delete(tagMemberBeansCompany.get(i));
            }
        }
        this.tagMemberBeanCompanyDao.insert(tagMemberBeanCompany);
    }

    public void addTagPeoplesBean(TagPeoplesBean tagPeoplesBean) {
        List tagPeoplesBean2 = getInstance().getTagPeoplesBean(tagPeoplesBean.getWxTagName(), tagPeoplesBean.getWxNum());
        if (tagPeoplesBean2 != null && tagPeoplesBean2.size() > 0) {
            for (int i = 0; i < tagPeoplesBean2.size(); i++) {
                this.tagPeoplesBeanDao.delete(tagPeoplesBean2.get(i));
            }
        }
        this.tagPeoplesBeanDao.insert(tagPeoplesBean);
    }

    public void addTagsMemberBean(TagMemberBean tagMemberBean) {
        List tagMemberBeans = getInstance().getTagMemberBeans(tagMemberBean.getWxNum());
        if (tagMemberBeans != null && tagMemberBeans.size() > 0) {
            for (int i = 0; i < tagMemberBeans.size(); i++) {
                this.tagMemberBeanDao.delete(tagMemberBeans.get(i));
            }
        }
        this.tagMemberBeanDao.insert(tagMemberBean);
    }

    public void deleteAddedCopyFriends(List<CopyFriendBean> list) {
        this.copyFriendBeanDao.deleteInTx(list);
    }

    public void deleteAllContacts() {
        this.contactBeanDao.deleteAll();
    }

    public void deleteAllContactsScan() {
        this.contactScanBeanDao.deleteAll();
    }

    public void deleteAllCopyFriends() {
        this.copyFriendBeanDao.deleteAll();
    }

    public void deleteAllFriends() {
        this.recoverFriendBeanDao.deleteAll();
    }

    public void deleteAllGroupBean() {
        this.groupBeanDao.deleteAll();
    }

    public void deleteAllGroupBeanAll() {
        this.groupBeanDaoAll.deleteAll();
    }

    public void deleteAllGroupMemberBean() {
        this.groupMemberBeanDao.deleteAll();
    }

    public void deleteAllGroupMemberBeanAll() {
        this.groupMemberBeanDaoAll.deleteAll();
    }

    public void deleteAllTagBean() {
        this.tagBeanDao.deleteAll();
    }

    public void deleteAllTagMemberBean() {
        this.tagMemberBeanDao.deleteAll();
    }

    public void deleteAllTagPeopleBean() {
        this.tagPeoplesBeanDao.deleteAll();
    }

    public void deleteContact(ContactBean contactBean) {
        this.contactBeanDao.delete(contactBean);
    }

    public void deleteContactScan(ContactScanBean contactScanBean) {
        this.contactScanBeanDao.delete(contactScanBean);
    }

    public void deleteCopyFriend(CopyFriendBean copyFriendBean) {
        this.copyFriendBeanDao.delete(copyFriendBean);
    }

    public void deleteFriend(RecoverFriendBean recoverFriendBean) {
        this.recoverFriendBeanDao.delete(recoverFriendBean);
    }

    public void deleteGroupBean(GroupBean groupBean) {
        this.groupBeanDao.delete(groupBean);
    }

    public void deleteGroupBeanAll(GroupBeanAll groupBeanAll) {
        this.groupBeanDaoAll.delete(groupBeanAll);
    }

    public void deleteGroupMemberBean(GroupMemberBean groupMemberBean) {
        this.groupMemberBeanDao.delete(groupMemberBean);
    }

    public void deleteGroupMemberBeanAll(GroupMemberBeanAll groupMemberBeanAll) {
        this.groupMemberBeanDaoAll.delete(groupMemberBeanAll);
    }

    public void deleteMusic(MusicBean musicBean) {
        this.musicBeanDao.delete(musicBean);
    }

    public void deleteRecord(RecordBean recordBean) {
        this.recordBeanDao.delete(recordBean);
    }

    public void deleteTagBean(TagBean tagBean) {
        this.tagBeanDao.delete(tagBean);
    }

    public void deleteTagMemberBean(TagMemberBean tagMemberBean) {
        this.tagMemberBeanDao.delete(tagMemberBean);
    }

    public void deleteTagPeopleBean(TagPeoplesBean tagPeoplesBean) {
        this.tagPeoplesBeanDao.delete(tagPeoplesBean);
    }

    public List getAllAddedCopyFriends() {
        this.copyFriendBeanDao.detachAll();
        List list = this.copyFriendBeanDao.queryBuilder().where(CopyFriendBeanDao.Properties.IsAdded.eq(true), new WhereCondition[0]).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getAllCollectionRecord() {
        this.recordBeanDao.detachAll();
        List list = this.recordBeanDao.queryBuilder().orderDesc(new Property[]{RecordBeanDao.Properties.CreateTimeLong}).where(RecordBeanDao.Properties.IsCollection.eq(true), new WhereCondition[0]).offset(0).limit(50).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getAllContact() {
        this.contactBeanDao.detachAll();
        List list = this.contactBeanDao.queryBuilder().orderAsc(new Property[]{ContactBeanDao.Properties.NickName}).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getAllContactScan() {
        this.contactBeanDao.detachAll();
        List list = this.contactScanBeanDao.queryBuilder().orderAsc(new Property[]{ContactScanBeanDao.Properties.NickName}).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getAllContactScanFromAdded() {
        this.contactScanBeanDao.detachAll();
        List list = this.contactScanBeanDao.queryBuilder().orderDesc(new Property[]{ContactScanBeanDao.Properties.CreateTime}).where(ContactScanBeanDao.Properties.IsAdded.eq(true), new WhereCondition[0]).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getAllContactScanFromUnAdd() {
        this.contactScanBeanDao.detachAll();
        List list = this.contactScanBeanDao.queryBuilder().orderDesc(new Property[]{ContactScanBeanDao.Properties.CreateTime}).where(ContactScanBeanDao.Properties.IsAdded.eq(false), new WhereCondition[0]).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getAllCopyFriends() {
        this.copyFriendBeanDao.detachAll();
        List list = this.copyFriendBeanDao.queryBuilder().orderAsc(new Property[]{CopyFriendBeanDao.Properties.DeleteTime}).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getAllCopyFriends(int i, int i2) {
        this.copyFriendBeanDao.detachAll();
        List list = this.copyFriendBeanDao.queryBuilder().orderAsc(new Property[]{CopyFriendBeanDao.Properties.DeleteTime}).offset(i * i2).limit(i2).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getAllFriends() {
        this.recoverFriendBeanDao.detachAll();
        List list = this.recoverFriendBeanDao.queryBuilder().orderDesc(new Property[]{RecoverFriendBeanDao.Properties.DeleteTime}).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getAllGroupBean() {
        this.groupBeanDao.detachAll();
        List list = this.groupBeanDao.queryBuilder().build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getAllGroupBeanAll() {
        this.groupBeanDaoAll.detachAll();
        List list = this.groupBeanDaoAll.queryBuilder().build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getAllGroupMemberBean() {
        this.groupMemberBeanDao.detachAll();
        List list = this.groupMemberBeanDao.queryBuilder().build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getAllGroupMemberBeanAll() {
        this.groupMemberBeanDaoAll.detachAll();
        List list = this.groupMemberBeanDaoAll.queryBuilder().build().list();
        return list == null ? new ArrayList() : list;
    }

    public List<MusicBean> getAllMusic() {
        try {
            this.musicBeanDao.detachAll();
            List<MusicBean> list = this.musicBeanDao.queryBuilder().build().list();
            return list == null ? new ArrayList() : list;
        } catch (Exception e) {
            return null;
        }
    }

    public List getAllMyVoiceRecord() {
        this.recordBeanDao.detachAll();
        List list = this.recordBeanDao.queryBuilder().orderDesc(new Property[]{RecordBeanDao.Properties.CreateTimeLong}).where(RecordBeanDao.Properties.VoiceTag.eq(0), new WhereCondition[0]).offset(0).limit(50).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getAllRecord() {
        this.recordBeanDao.detachAll();
        List list = this.recordBeanDao.queryBuilder().orderDesc(new Property[]{RecordBeanDao.Properties.CreateTimeLong}).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getAllTagBean() {
        this.tagBeanDao.detachAll();
        List list = this.tagBeanDao.queryBuilder().build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getAllTagMemberBean() {
        this.tagMemberBeanDao.detachAll();
        List list = this.tagMemberBeanDao.queryBuilder().build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getAllTagPeopleBean() {
        this.tagPeoplesBeanDao.detachAll();
        List list = this.tagPeoplesBeanDao.queryBuilder().build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getAllWXRecord() {
        this.recordBeanDao.detachAll();
        List list = this.recordBeanDao.queryBuilder().orderDesc(new Property[]{RecordBeanDao.Properties.CreateTimeLong}).where(RecordBeanDao.Properties.VoiceTag.eq(1), new WhereCondition[0]).offset(0).limit(50).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List<ContactBean> getContact(String str) {
        try {
            this.contactBeanDao.detachAll();
            List<ContactBean> list = this.contactBeanDao.queryBuilder().where(ContactBeanDao.Properties.Number.eq(str), new WhereCondition[0]).build().list();
            return list == null ? new ArrayList() : list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ContactScanBean> getContactScan(String str) {
        try {
            this.contactScanBeanDao.detachAll();
            List<ContactScanBean> list = this.contactScanBeanDao.queryBuilder().where(ContactScanBeanDao.Properties.Number.eq(str), new WhereCondition[0]).build().list();
            return list == null ? new ArrayList() : list;
        } catch (Exception e) {
            return null;
        }
    }

    public List getGroupBeans(String str) {
        this.groupBeanDao.detachAll();
        List list = this.groupBeanDao.queryBuilder().where(GroupBeanDao.Properties.WxNum.eq(str), new WhereCondition[0]).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getGroupBeansAll(String str) {
        this.groupBeanDaoAll.detachAll();
        List list = this.groupBeanDaoAll.queryBuilder().where(GroupBeanAllDao.Properties.WxNum.eq(str), new WhereCondition[0]).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getGroupMemberBeans(String str) {
        PrintStream printStream = System.out;
        printStream.println("WS_BABY_XGX" + str);
        this.groupMemberBeanDao.detachAll();
        List list = this.groupMemberBeanDao.queryBuilder().where(GroupMemberBeanDao.Properties.WxNum.eq(str), new WhereCondition[0]).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getGroupMemberBeansAll(String str) {
        PrintStream printStream = System.out;
        printStream.println("WS_BABY_XGX" + str);
        this.groupMemberBeanDaoAll.detachAll();
        List list = this.groupMemberBeanDaoAll.queryBuilder().where(GroupMemberBeanAllDao.Properties.WxNum.eq(str), new WhereCondition[0]).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List<MusicBean> getMusic(String str) {
        try {
            this.musicBeanDao.detachAll();
            List<MusicBean> list = this.musicBeanDao.queryBuilder().where(MusicBeanDao.Properties.Title.eq(str), new WhereCondition[0]).build().list();
            return list == null ? new ArrayList() : list;
        } catch (Exception e) {
            return null;
        }
    }

    public RecordBean getRecord(File file) {
        List list = this.recordBeanDao.queryBuilder().where(RecordBeanDao.Properties.Path.eq(file.getAbsolutePath()), new WhereCondition[0]).build().list();
        if (list == null || list.size() == 0) {
            return null;
        }
        return (RecordBean) list.get(0);
    }

    public List<RecordBean> getRecord(String str) {
        try {
            this.recordBeanDao.detachAll();
            List<RecordBean> list = this.recordBeanDao.queryBuilder().where(RecordBeanDao.Properties.Path.eq(str), new WhereCondition[0]).build().list();
            return list == null ? new ArrayList() : list;
        } catch (Exception e) {
            return null;
        }
    }

    public List getTagBeanCompany() {
        this.tagBeanCompayDao.detachAll();
        List list = this.tagBeanCompayDao.queryBuilder().build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getTagBeans(String str) {
        this.tagBeanDao.detachAll();
        List list = this.tagBeanDao.queryBuilder().where(TagBeanDao.Properties.WxNum.eq(str), new WhereCondition[0]).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getTagBeansCompany(String str) {
        this.tagBeanCompayDao.detachAll();
        List list = this.tagBeanCompayDao.queryBuilder().where(TagBeanCompayDao.Properties.WxCompany.eq(str), new WhereCondition[0]).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getTagMemberBeans(String str) {
        PrintStream printStream = System.out;
        printStream.println("WS_BABY_XGX" + str);
        this.tagMemberBeanDao.detachAll();
        List list = this.tagMemberBeanDao.queryBuilder().where(TagMemberBeanDao.Properties.WxNum.eq(str), new WhereCondition[0]).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getTagMemberBeansCompany(String str) {
        PrintStream printStream = System.out;
        printStream.println("WS_BABY_XGX" + str);
        this.tagMemberBeanCompanyDao.detachAll();
        List list = this.tagMemberBeanCompanyDao.queryBuilder().where(TagMemberBeanCompanyDao.Properties.WxCompany.eq(str), new WhereCondition[0]).build().list();
        return list == null ? new ArrayList() : list;
    }

    public List getTagPeoplesBean(String str, String str2) {
        PrintStream printStream = System.out;
        printStream.println("WS_BABY_XGX" + str);
        this.tagPeoplesBeanDao.detachAll();
        List list = this.tagPeoplesBeanDao.queryBuilder().where(TagPeoplesBeanDao.Properties.WxTagName.eq(str), new WhereCondition[]{TagPeoplesBeanDao.Properties.WxNum.eq(str2)}).build().list();
        return list == null ? new ArrayList() : list;
    }

    public void updateContact(ContactBean contactBean) {
        this.contactBeanDao.update(contactBean);
    }

    public void updateContact(String str, String str2) {
        List list = this.contactBeanDao.queryBuilder().where(ContactBeanDao.Properties.Number.eq(str), new WhereCondition[0]).build().list();
        if (list != null && list.size() > 0) {
            ContactBean contactBean = (ContactBean) list.get(0);
            contactBean.setExtra(str2);
            this.contactBeanDao.update(contactBean);
        }
    }

    public void updateContactScan(ContactScanBean contactScanBean) {
        this.contactScanBeanDao.update(contactScanBean);
    }

    public void updateContactScan(String str, int i, String str2) {
        List list = this.contactScanBeanDao.queryBuilder().where(ContactScanBeanDao.Properties.Number.eq(str), new WhereCondition[0]).build().list();
        if (list != null && list.size() > 0) {
            ContactScanBean contactScanBean = (ContactScanBean) list.get(0);
            contactScanBean.setExtra(str2);
            contactScanBean.setIsAdded(true);
            contactScanBean.checked = false;
            contactScanBean.setAddResultType(i);
            this.contactScanBeanDao.update(contactScanBean);
        }
    }

    public void updateCopyFriend(CopyFriendBean copyFriendBean) {
        this.copyFriendBeanDao.update(copyFriendBean);
    }

    public void updateFriend(RecoverFriendBean recoverFriendBean) {
        this.recoverFriendBeanDao.update(recoverFriendBean);
    }

    public void updateGroupBean(GroupBean groupBean) {
        this.groupBeanDao.update(groupBean);
    }

    public void updateGroupBeanAll(GroupBeanAll groupBeanAll) {
        this.groupBeanDaoAll.update(groupBeanAll);
    }

    public void updateGroupMemberBean(GroupMemberBean groupMemberBean) {
        this.groupMemberBeanDao.update(groupMemberBean);
    }

    public void updateGroupMemberBeanAll(GroupMemberBeanAll groupMemberBeanAll) {
        this.groupMemberBeanDaoAll.update(groupMemberBeanAll);
    }

    public void updateMusic(MusicBean musicBean) {
        this.musicBeanDao.update(musicBean);
    }

    public void updateRecord(RecordBean recordBean) {
        this.recordBeanDao.update(recordBean);
    }

    public void updateTagBean(TagBean tagBean) {
        this.tagBeanDao.update(tagBean);
    }

    public void updateTagMemberBean(TagMemberBean tagMemberBean) {
        this.tagMemberBeanDao.update(tagMemberBean);
    }

    public void updateTagPeopleBean(TagPeoplesBean tagPeoplesBean) {
        this.tagPeoplesBeanDao.update(tagPeoplesBean);
    }
}
