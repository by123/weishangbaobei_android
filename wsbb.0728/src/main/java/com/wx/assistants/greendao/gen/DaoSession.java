package com.wx.assistants.greendao.gen;

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
import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

public class DaoSession extends AbstractDaoSession {
    private final ContactBeanDao contactBeanDao = new ContactBeanDao(this.contactBeanDaoConfig, this);
    private final DaoConfig contactBeanDaoConfig;
    private final ContactScanBeanDao contactScanBeanDao = new ContactScanBeanDao(this.contactScanBeanDaoConfig, this);
    private final DaoConfig contactScanBeanDaoConfig;
    private final CopyFriendBeanDao copyFriendBeanDao = new CopyFriendBeanDao(this.copyFriendBeanDaoConfig, this);
    private final DaoConfig copyFriendBeanDaoConfig;
    private final GroupBeanAllDao groupBeanAllDao = new GroupBeanAllDao(this.groupBeanAllDaoConfig, this);
    private final DaoConfig groupBeanAllDaoConfig;
    private final GroupBeanDao groupBeanDao = new GroupBeanDao(this.groupBeanDaoConfig, this);
    private final DaoConfig groupBeanDaoConfig;
    private final GroupMemberBeanAllDao groupMemberBeanAllDao = new GroupMemberBeanAllDao(this.groupMemberBeanAllDaoConfig, this);
    private final DaoConfig groupMemberBeanAllDaoConfig;
    private final GroupMemberBeanDao groupMemberBeanDao = new GroupMemberBeanDao(this.groupMemberBeanDaoConfig, this);
    private final DaoConfig groupMemberBeanDaoConfig;
    private final MusicBeanDao musicBeanDao = new MusicBeanDao(this.musicBeanDaoConfig, this);
    private final DaoConfig musicBeanDaoConfig;
    private final RecordBeanDao recordBeanDao = new RecordBeanDao(this.recordBeanDaoConfig, this);
    private final DaoConfig recordBeanDaoConfig;
    private final RecoverFriendBeanDao recoverFriendBeanDao = new RecoverFriendBeanDao(this.recoverFriendBeanDaoConfig, this);
    private final DaoConfig recoverFriendBeanDaoConfig;
    private final TagBeanCompayDao tagBeanCompayDao = new TagBeanCompayDao(this.tagBeanCompayDaoConfig, this);
    private final DaoConfig tagBeanCompayDaoConfig;
    private final TagBeanDao tagBeanDao = new TagBeanDao(this.tagBeanDaoConfig, this);
    private final DaoConfig tagBeanDaoConfig;
    private final TagMemberBeanCompanyDao tagMemberBeanCompanyDao = new TagMemberBeanCompanyDao(this.tagMemberBeanCompanyDaoConfig, this);
    private final DaoConfig tagMemberBeanCompanyDaoConfig;
    private final TagMemberBeanDao tagMemberBeanDao = new TagMemberBeanDao(this.tagMemberBeanDaoConfig, this);
    private final DaoConfig tagMemberBeanDaoConfig;
    private final TagPeoplesBeanDao tagPeoplesBeanDao = new TagPeoplesBeanDao(this.tagPeoplesBeanDaoConfig, this);
    private final DaoConfig tagPeoplesBeanDaoConfig;

    public DaoSession(Database database, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(database);
        this.tagBeanDaoConfig = map.get(TagBeanDao.class).clone();
        this.tagBeanDaoConfig.initIdentityScope(identityScopeType);
        this.contactBeanDaoConfig = map.get(ContactBeanDao.class).clone();
        this.contactBeanDaoConfig.initIdentityScope(identityScopeType);
        this.copyFriendBeanDaoConfig = map.get(CopyFriendBeanDao.class).clone();
        this.copyFriendBeanDaoConfig.initIdentityScope(identityScopeType);
        this.groupBeanDaoConfig = map.get(GroupBeanDao.class).clone();
        this.groupBeanDaoConfig.initIdentityScope(identityScopeType);
        this.groupMemberBeanAllDaoConfig = map.get(GroupMemberBeanAllDao.class).clone();
        this.groupMemberBeanAllDaoConfig.initIdentityScope(identityScopeType);
        this.groupMemberBeanDaoConfig = map.get(GroupMemberBeanDao.class).clone();
        this.groupMemberBeanDaoConfig.initIdentityScope(identityScopeType);
        this.musicBeanDaoConfig = map.get(MusicBeanDao.class).clone();
        this.musicBeanDaoConfig.initIdentityScope(identityScopeType);
        this.recoverFriendBeanDaoConfig = map.get(RecoverFriendBeanDao.class).clone();
        this.recoverFriendBeanDaoConfig.initIdentityScope(identityScopeType);
        this.tagPeoplesBeanDaoConfig = map.get(TagPeoplesBeanDao.class).clone();
        this.tagPeoplesBeanDaoConfig.initIdentityScope(identityScopeType);
        this.tagMemberBeanDaoConfig = map.get(TagMemberBeanDao.class).clone();
        this.tagMemberBeanDaoConfig.initIdentityScope(identityScopeType);
        this.tagMemberBeanCompanyDaoConfig = map.get(TagMemberBeanCompanyDao.class).clone();
        this.tagMemberBeanCompanyDaoConfig.initIdentityScope(identityScopeType);
        this.recordBeanDaoConfig = map.get(RecordBeanDao.class).clone();
        this.recordBeanDaoConfig.initIdentityScope(identityScopeType);
        this.contactScanBeanDaoConfig = map.get(ContactScanBeanDao.class).clone();
        this.contactScanBeanDaoConfig.initIdentityScope(identityScopeType);
        this.groupBeanAllDaoConfig = map.get(GroupBeanAllDao.class).clone();
        this.groupBeanAllDaoConfig.initIdentityScope(identityScopeType);
        this.tagBeanCompayDaoConfig = map.get(TagBeanCompayDao.class).clone();
        this.tagBeanCompayDaoConfig.initIdentityScope(identityScopeType);
        registerDao(TagBean.class, this.tagBeanDao);
        registerDao(ContactBean.class, this.contactBeanDao);
        registerDao(CopyFriendBean.class, this.copyFriendBeanDao);
        registerDao(GroupBean.class, this.groupBeanDao);
        registerDao(GroupMemberBeanAll.class, this.groupMemberBeanAllDao);
        registerDao(GroupMemberBean.class, this.groupMemberBeanDao);
        registerDao(MusicBean.class, this.musicBeanDao);
        registerDao(RecoverFriendBean.class, this.recoverFriendBeanDao);
        registerDao(TagPeoplesBean.class, this.tagPeoplesBeanDao);
        registerDao(TagMemberBean.class, this.tagMemberBeanDao);
        registerDao(TagMemberBeanCompany.class, this.tagMemberBeanCompanyDao);
        registerDao(RecordBean.class, this.recordBeanDao);
        registerDao(ContactScanBean.class, this.contactScanBeanDao);
        registerDao(GroupBeanAll.class, this.groupBeanAllDao);
        registerDao(TagBeanCompay.class, this.tagBeanCompayDao);
    }

    public void clear() {
        this.tagBeanDaoConfig.clearIdentityScope();
        this.contactBeanDaoConfig.clearIdentityScope();
        this.copyFriendBeanDaoConfig.clearIdentityScope();
        this.groupBeanDaoConfig.clearIdentityScope();
        this.groupMemberBeanAllDaoConfig.clearIdentityScope();
        this.groupMemberBeanDaoConfig.clearIdentityScope();
        this.musicBeanDaoConfig.clearIdentityScope();
        this.recoverFriendBeanDaoConfig.clearIdentityScope();
        this.tagPeoplesBeanDaoConfig.clearIdentityScope();
        this.tagMemberBeanDaoConfig.clearIdentityScope();
        this.tagMemberBeanCompanyDaoConfig.clearIdentityScope();
        this.recordBeanDaoConfig.clearIdentityScope();
        this.contactScanBeanDaoConfig.clearIdentityScope();
        this.groupBeanAllDaoConfig.clearIdentityScope();
        this.tagBeanCompayDaoConfig.clearIdentityScope();
    }

    public ContactBeanDao getContactBeanDao() {
        return this.contactBeanDao;
    }

    public ContactScanBeanDao getContactScanBeanDao() {
        return this.contactScanBeanDao;
    }

    public CopyFriendBeanDao getCopyFriendBeanDao() {
        return this.copyFriendBeanDao;
    }

    public GroupBeanAllDao getGroupBeanAllDao() {
        return this.groupBeanAllDao;
    }

    public GroupBeanDao getGroupBeanDao() {
        return this.groupBeanDao;
    }

    public GroupMemberBeanAllDao getGroupMemberBeanAllDao() {
        return this.groupMemberBeanAllDao;
    }

    public GroupMemberBeanDao getGroupMemberBeanDao() {
        return this.groupMemberBeanDao;
    }

    public MusicBeanDao getMusicBeanDao() {
        return this.musicBeanDao;
    }

    public RecordBeanDao getRecordBeanDao() {
        return this.recordBeanDao;
    }

    public RecoverFriendBeanDao getRecoverFriendBeanDao() {
        return this.recoverFriendBeanDao;
    }

    public TagBeanCompayDao getTagBeanCompayDao() {
        return this.tagBeanCompayDao;
    }

    public TagBeanDao getTagBeanDao() {
        return this.tagBeanDao;
    }

    public TagMemberBeanCompanyDao getTagMemberBeanCompanyDao() {
        return this.tagMemberBeanCompanyDao;
    }

    public TagMemberBeanDao getTagMemberBeanDao() {
        return this.tagMemberBeanDao;
    }

    public TagPeoplesBeanDao getTagPeoplesBeanDao() {
        return this.tagPeoplesBeanDao;
    }
}
