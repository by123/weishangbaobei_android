package com.wx.assistants.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.wx.assistants.Enity.ContactBean;
import com.wx.assistants.Enity.ContactScanBean;
import com.wx.assistants.Enity.CopyFriendBean;
import com.wx.assistants.Enity.RecoverFriendBean;
import com.wx.assistants.service_utils.Gender;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class OperationParameterModel implements Parcelable {
    public static final Parcelable.Creator<OperationParameterModel> CREATOR = new Parcelable.Creator<OperationParameterModel>() {
        public OperationParameterModel createFromParcel(Parcel parcel) {
            return new OperationParameterModel(parcel);
        }

        public OperationParameterModel[] newArray(int i) {
            return new OperationParameterModel[i];
        }
    };
    private ArrayList<ContactBean> addressBookPhones = new ArrayList<>();
    private int autoPullType = 0;
    private String cardName;
    private int circulateInnerSize = 1;
    private int circulateMode = 0;
    private int circulateOutSize = 1;
    private int cleanCircleType = 0;
    private int cloneCircleType;
    private LinkedHashSet<CopyFriendBean> copyFriends = new LinkedHashSet<>();
    private int deleteFriendsType = 0;
    private int deleteMode;
    private int deleteOrder;
    private int distance;
    private int dndMode;
    private String endDate;
    private int ffModel = 0;
    private int ffModelEndTime = 0;
    private int ffModelStartTime = 0;
    private LinkedHashSet<String> filterNickNames = new LinkedHashSet<>();
    private int friendSendTag = 0;
    private LinkedHashSet<String> friendsNameSet;
    private String friendsNameStr;
    private String groupNames;
    private int groupNumber;
    private int intimateMode;
    private boolean isAutoGroup;
    private boolean isAutoRemarkContactsName = false;
    private boolean isAutoSend;
    private boolean isBackStart;
    private int isBreakAdd = 0;
    private boolean isCanRepeat;
    private int isDND = 0;
    private boolean isDeleteNoFriends;
    private boolean isDeleteShieldFriends;
    private boolean isFastSpeed;
    private boolean isJumpTag;
    private int isOriginPic;
    private String jumpFriendNames;
    private String jumpGroupNames;
    private int likeCommentType;
    private String localImgUrl = "";
    private String materialForwardId;
    private int materialPicCount;
    private String materialText;
    private int maxCommentNum;
    private int maxOperaNum;
    private int maxPraiseNum;
    private int messageSendType = 0;
    private int minimumStep = 1;
    private int otherSendType = 0;
    private List<PassiveCardBean> passiveCardBeans = new ArrayList();
    private int praiseCommentType;
    private LinkedHashSet<RecoverFriendBean> recoverFriends = new LinkedHashSet<>();
    private String remarkPrefix;
    private String sayContent;
    private List<ContactScanBean> scanPhones = new ArrayList();
    private int scrollSpeed = 150;
    private String selectCircleGroups;
    private String selectCircleModel;
    private String selectCircleTags;
    private int sendCardType = 0;
    private int sendGroupMethod;
    private int sendOrder = 0;
    private Gender sex;
    private String singLabelStr;
    private int spaceTime;
    private String startDate;
    private int startIndex;
    private int tagFriendType = 0;
    private LinkedHashSet<String> tagListNames = new LinkedHashSet<>();
    private LinkedHashSet<String> tagListPeoples = new LinkedHashSet<>();
    private String tagNamesStr;
    private String tagPeoplesStr;
    private String taskNum;
    private int verifyCode;
    private String voiceDesc;
    private String voiceNickName;
    private String voicePath;
    private String voiceTitle;
    private int wechatSportsType = 0;

    public OperationParameterModel() {
    }

    protected OperationParameterModel(Parcel parcel) {
        boolean z = true;
        this.verifyCode = parcel.readInt();
        this.isOriginPic = parcel.readInt();
        this.groupNumber = parcel.readInt();
        this.messageSendType = parcel.readInt();
        this.friendSendTag = parcel.readInt();
        this.sendCardType = parcel.readInt();
        this.dndMode = parcel.readInt();
        this.sendGroupMethod = parcel.readInt();
        this.likeCommentType = parcel.readInt();
        this.ffModel = parcel.readInt();
        this.ffModelStartTime = parcel.readInt();
        this.ffModelEndTime = parcel.readInt();
        this.cleanCircleType = parcel.readInt();
        this.wechatSportsType = parcel.readInt();
        this.minimumStep = parcel.readInt();
        this.autoPullType = parcel.readInt();
        this.deleteMode = parcel.readInt();
        this.materialPicCount = parcel.readInt();
        this.maxPraiseNum = parcel.readInt();
        this.sendOrder = parcel.readInt();
        this.maxCommentNum = parcel.readInt();
        this.spaceTime = parcel.readInt();
        this.circulateInnerSize = parcel.readInt();
        this.circulateMode = parcel.readInt();
        this.deleteOrder = parcel.readInt();
        this.intimateMode = parcel.readInt();
        this.praiseCommentType = parcel.readInt();
        this.taskNum = parcel.readString();
        this.otherSendType = parcel.readInt();
        this.tagFriendType = parcel.readInt();
        this.startIndex = parcel.readInt();
        this.maxOperaNum = parcel.readInt();
        this.materialForwardId = parcel.readString();
        this.singLabelStr = parcel.readString();
        this.deleteFriendsType = parcel.readInt();
        this.isDND = parcel.readInt();
        this.cloneCircleType = parcel.readInt();
        this.distance = parcel.readInt();
        this.circulateOutSize = parcel.readInt();
        this.isDeleteNoFriends = parcel.readByte() != 0;
        this.isDeleteShieldFriends = parcel.readByte() != 0;
        this.isCanRepeat = parcel.readByte() != 0;
        this.isBackStart = parcel.readByte() != 0;
        this.isFastSpeed = parcel.readByte() != 0;
        this.isJumpTag = parcel.readByte() != 0;
        this.isAutoGroup = parcel.readByte() != 0;
        this.tagNamesStr = parcel.readString();
        this.scrollSpeed = parcel.readInt();
        this.localImgUrl = parcel.readString();
        parcel.readList(this.scanPhones, ContactScanBean.class.getClassLoader());
        parcel.readList(this.passiveCardBeans, PassiveCardBean.class.getClassLoader());
        this.tagListNames = (LinkedHashSet) parcel.readSerializable();
        this.recoverFriends = (LinkedHashSet) parcel.readSerializable();
        this.copyFriends = (LinkedHashSet) parcel.readSerializable();
        this.tagPeoplesStr = parcel.readString();
        this.tagListPeoples = (LinkedHashSet) parcel.readSerializable();
        this.filterNickNames = (LinkedHashSet) parcel.readSerializable();
        this.sayContent = parcel.readString();
        int readInt = parcel.readInt();
        this.sex = readInt == -1 ? null : Gender.values()[readInt];
        this.isAutoSend = parcel.readByte() == 0 ? false : z;
        this.cardName = parcel.readString();
        this.jumpGroupNames = parcel.readString();
        this.groupNames = parcel.readString();
        this.jumpFriendNames = parcel.readString();
        this.friendsNameSet = (LinkedHashSet) parcel.readSerializable();
        this.friendsNameStr = parcel.readString();
        this.startDate = parcel.readString();
        this.endDate = parcel.readString();
        this.remarkPrefix = parcel.readString();
        this.materialText = parcel.readString();
        this.voiceDesc = parcel.readString();
        this.voiceNickName = parcel.readString();
        this.voicePath = parcel.readString();
        this.voiceTitle = parcel.readString();
        this.addressBookPhones = (ArrayList) parcel.readSerializable();
        this.isBreakAdd = parcel.readInt();
        this.selectCircleModel = parcel.readString();
        this.selectCircleTags = parcel.readString();
        this.selectCircleGroups = parcel.readString();
    }

    public static Parcelable.Creator<OperationParameterModel> getCREATOR() {
        return CREATOR;
    }

    public int describeContents() {
        return 0;
    }

    public ArrayList<ContactBean> getAddressBookPhones() {
        return this.addressBookPhones;
    }

    public int getAutoPullType() {
        return this.autoPullType;
    }

    public String getCardName() {
        return this.cardName;
    }

    public int getCirculateInnerSize() {
        return this.circulateInnerSize;
    }

    public int getCirculateMode() {
        return this.circulateMode;
    }

    public int getCirculateOutSize() {
        return this.circulateOutSize;
    }

    public int getCleanCircleType() {
        return this.cleanCircleType;
    }

    public int getCloneCircleType() {
        return this.cloneCircleType;
    }

    public LinkedHashSet<CopyFriendBean> getCopyFriends() {
        return this.copyFriends;
    }

    public int getDeleteFriendsType() {
        return this.deleteFriendsType;
    }

    public int getDeleteMode() {
        return this.deleteMode;
    }

    public int getDeleteOrder() {
        return this.deleteOrder;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getDndMode() {
        return this.dndMode;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public int getFfModel() {
        return this.ffModel;
    }

    public int getFfModelEndTime() {
        return this.ffModelEndTime;
    }

    public int getFfModelStartTime() {
        return this.ffModelStartTime;
    }

    public LinkedHashSet<String> getFilterNickNames() {
        return this.filterNickNames;
    }

    public int getFriendSendTag() {
        return this.friendSendTag;
    }

    public LinkedHashSet<String> getFriendsNameSet() {
        return this.friendsNameSet;
    }

    public String getFriendsNameStr() {
        return this.friendsNameStr;
    }

    public String getGroupNames() {
        return this.groupNames;
    }

    public int getGroupNumber() {
        return this.groupNumber;
    }

    public int getIntimateMode() {
        return this.intimateMode;
    }

    public int getIsBreakAdd() {
        return this.isBreakAdd;
    }

    public int getIsDND() {
        return this.isDND;
    }

    public int getIsOriginPic() {
        return this.isOriginPic;
    }

    public String getJumpFriendNames() {
        return this.jumpFriendNames;
    }

    public String getJumpGroupNames() {
        return this.jumpGroupNames;
    }

    public int getLikeCommentType() {
        return this.likeCommentType;
    }

    public String getLocalImgUrl() {
        return this.localImgUrl;
    }

    public String getMaterialForwardId() {
        return this.materialForwardId;
    }

    public int getMaterialPicCount() {
        return this.materialPicCount;
    }

    public String getMaterialText() {
        return this.materialText;
    }

    public int getMaxCommentNum() {
        return this.maxCommentNum;
    }

    public int getMaxOperaNum() {
        return this.maxOperaNum;
    }

    public int getMaxPraiseNum() {
        return this.maxPraiseNum;
    }

    public int getMessageSendType() {
        return this.messageSendType;
    }

    public int getMinimumStep() {
        return this.minimumStep;
    }

    public int getOtherSendType() {
        return this.otherSendType;
    }

    public List<PassiveCardBean> getPassiveCardBeans() {
        return this.passiveCardBeans;
    }

    public int getPraiseCommentType() {
        return this.praiseCommentType;
    }

    public LinkedHashSet<RecoverFriendBean> getRecoverFriends() {
        return this.recoverFriends;
    }

    public String getRemarkPrefix() {
        return this.remarkPrefix;
    }

    public String getSayContent() {
        return this.sayContent;
    }

    public List<ContactScanBean> getScanPhones() {
        return this.scanPhones;
    }

    public int getScrollSpeed() {
        return this.scrollSpeed;
    }

    public String getSelectCircleGroups() {
        return this.selectCircleGroups;
    }

    public String getSelectCircleModel() {
        return this.selectCircleModel;
    }

    public String getSelectCircleTags() {
        return this.selectCircleTags;
    }

    public int getSendCardType() {
        return this.sendCardType;
    }

    public int getSendGroupMethod() {
        return this.sendGroupMethod;
    }

    public int getSendOrder() {
        return this.sendOrder;
    }

    public Gender getSex() {
        return this.sex;
    }

    public String getSingLabelStr() {
        return this.singLabelStr;
    }

    public int getSpaceTime() {
        return this.spaceTime;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public int getTagFriendType() {
        return this.tagFriendType;
    }

    public LinkedHashSet<String> getTagListNames() {
        return this.tagListNames;
    }

    public LinkedHashSet<String> getTagListPeoples() {
        return this.tagListPeoples;
    }

    public String getTagNamesStr() {
        return this.tagNamesStr;
    }

    public String getTagPeoplesStr() {
        return this.tagPeoplesStr;
    }

    public String getTaskNum() {
        return this.taskNum;
    }

    public int getVerifyCode() {
        return this.verifyCode;
    }

    public String getVoiceDesc() {
        return this.voiceDesc;
    }

    public String getVoiceNickName() {
        return this.voiceNickName;
    }

    public String getVoicePath() {
        return this.voicePath;
    }

    public String getVoiceTitle() {
        return this.voiceTitle;
    }

    public int getWechatSportsType() {
        return this.wechatSportsType;
    }

    public boolean isAutoGroup() {
        return this.isAutoGroup;
    }

    public boolean isAutoRemarkContactsName() {
        return this.isAutoRemarkContactsName;
    }

    public boolean isAutoSend() {
        return this.isAutoSend;
    }

    public boolean isBackStart() {
        return this.isBackStart;
    }

    public int isBreakAdd() {
        return this.isBreakAdd;
    }

    public boolean isCanRepeat() {
        return this.isCanRepeat;
    }

    public boolean isDeleteNoFriends() {
        return this.isDeleteNoFriends;
    }

    public boolean isDeleteShieldFriends() {
        return this.isDeleteShieldFriends;
    }

    public boolean isFastSpeed() {
        return this.isFastSpeed;
    }

    public boolean isJumpTag() {
        return this.isJumpTag;
    }

    public void setAddressBookPhones(ArrayList<ContactBean> arrayList) {
        this.addressBookPhones = arrayList;
    }

    public void setAutoGroup(boolean z) {
        this.isAutoGroup = z;
    }

    public void setAutoPullType(int i) {
        this.autoPullType = i;
    }

    public void setAutoRemarkContactsName(boolean z) {
        this.isAutoRemarkContactsName = z;
    }

    public void setAutoSend(boolean z) {
        this.isAutoSend = z;
    }

    public void setBackStart(boolean z) {
        this.isBackStart = z;
    }

    public void setBreakAdd(int i) {
        this.isBreakAdd = i;
    }

    public void setCanRepeat(boolean z) {
        this.isCanRepeat = z;
    }

    public void setCardName(String str) {
        this.cardName = str;
    }

    public void setCirculateInnerSize(int i) {
        this.circulateInnerSize = i;
    }

    public void setCirculateMode(int i) {
        this.circulateMode = i;
    }

    public void setCirculateOutSize(int i) {
        this.circulateOutSize = i;
    }

    public void setCleanCircleType(int i) {
        this.cleanCircleType = i;
    }

    public void setCloneCircleType(int i) {
        this.cloneCircleType = i;
    }

    public void setCopyFriends(LinkedHashSet<CopyFriendBean> linkedHashSet) {
        this.copyFriends = linkedHashSet;
    }

    public void setDeleteFriendsType(int i) {
        this.deleteFriendsType = i;
    }

    public void setDeleteMode(int i) {
        this.deleteMode = i;
    }

    public void setDeleteNoFriends(boolean z) {
        this.isDeleteNoFriends = z;
    }

    public void setDeleteOrder(int i) {
        this.deleteOrder = i;
    }

    public void setDeleteShieldFriends(boolean z) {
        this.isDeleteShieldFriends = z;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setDndMode(int i) {
        this.dndMode = i;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public void setFastSpeed(boolean z) {
        this.isFastSpeed = z;
    }

    public void setFfModel(int i) {
        this.ffModel = i;
    }

    public void setFfModelEndTime(int i) {
        this.ffModelEndTime = i;
    }

    public void setFfModelStartTime(int i) {
        this.ffModelStartTime = i;
    }

    public void setFilterNickNames(LinkedHashSet<String> linkedHashSet) {
        this.filterNickNames = linkedHashSet;
    }

    public void setFriendSendTag(int i) {
        this.friendSendTag = i;
    }

    public void setFriendsNameSet(LinkedHashSet<String> linkedHashSet) {
        this.friendsNameSet = linkedHashSet;
    }

    public void setFriendsNameStr(String str) {
        this.friendsNameStr = str;
    }

    public void setGroupNames(String str) {
        this.groupNames = str;
    }

    public void setGroupNumber(int i) {
        this.groupNumber = i;
    }

    public void setIntimateMode(int i) {
        this.intimateMode = i;
    }

    public void setIsBreakAdd(int i) {
        this.isBreakAdd = i;
    }

    public void setIsDND(int i) {
        this.isDND = i;
    }

    public void setIsOriginPic(int i) {
        this.isOriginPic = i;
    }

    public void setJumpFriendNames(String str) {
        this.jumpFriendNames = str;
    }

    public void setJumpGroupNames(String str) {
        this.jumpGroupNames = str;
    }

    public void setJumpTag(boolean z) {
        this.isJumpTag = z;
    }

    public void setLikeCommentType(int i) {
        this.likeCommentType = i;
    }

    public void setLocalImgUrl(String str) {
        this.localImgUrl = str;
    }

    public void setMaterialForwardId(String str) {
        this.materialForwardId = str;
    }

    public void setMaterialPicCount(int i) {
        this.materialPicCount = i;
    }

    public void setMaterialText(String str) {
        this.materialText = str;
    }

    public void setMaxCommentNum(int i) {
        this.maxCommentNum = i;
    }

    public void setMaxOperaNum(int i) {
        this.maxOperaNum = i;
    }

    public void setMaxPraiseNum(int i) {
        this.maxPraiseNum = i;
    }

    public void setMessageSendType(int i) {
        this.messageSendType = i;
    }

    public void setMinimumStep(int i) {
        this.minimumStep = i;
    }

    public void setOtherSendType(int i) {
        this.otherSendType = i;
    }

    public void setPassiveCardBeans(List<PassiveCardBean> list) {
        this.passiveCardBeans = list;
    }

    public void setPraiseCommentType(int i) {
        this.praiseCommentType = i;
    }

    public void setRecoverFriends(LinkedHashSet<RecoverFriendBean> linkedHashSet) {
        this.recoverFriends = linkedHashSet;
    }

    public void setRemarkPrefix(String str) {
        this.remarkPrefix = str;
    }

    public void setSayContent(String str) {
        this.sayContent = str;
    }

    public void setScanPhones(List<ContactScanBean> list) {
        this.scanPhones = list;
    }

    public void setScrollSpeed(int i) {
        this.scrollSpeed = i;
    }

    public void setSelectCircleGroups(String str) {
        this.selectCircleGroups = str;
    }

    public void setSelectCircleModel(String str) {
        this.selectCircleModel = str;
    }

    public void setSelectCircleTags(String str) {
        this.selectCircleTags = str;
    }

    public void setSendCardType(int i) {
        this.sendCardType = i;
    }

    public void setSendGroupMethod(int i) {
        this.sendGroupMethod = i;
    }

    public void setSendOrder(int i) {
        this.sendOrder = i;
    }

    public void setSex(Gender gender) {
        this.sex = gender;
    }

    public void setSingLabelStr(String str) {
        this.singLabelStr = str;
    }

    public void setSpaceTime(int i) {
        this.spaceTime = i;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public void setStartIndex(int i) {
        this.startIndex = i;
    }

    public void setTagFriendType(int i) {
        this.tagFriendType = i;
    }

    public void setTagListNames(LinkedHashSet<String> linkedHashSet) {
        this.tagListNames = linkedHashSet;
    }

    public void setTagListPeoples(LinkedHashSet<String> linkedHashSet) {
        this.tagListPeoples = linkedHashSet;
    }

    public void setTagNamesStr(String str) {
        this.tagNamesStr = str;
    }

    public void setTagPeoplesStr(String str) {
        this.tagPeoplesStr = str;
    }

    public void setTaskNum(String str) {
        this.taskNum = str;
    }

    public void setVerifyCode(int i) {
        this.verifyCode = i;
    }

    public void setVoiceDesc(String str) {
        this.voiceDesc = str;
    }

    public void setVoiceNickName(String str) {
        this.voiceNickName = str;
    }

    public void setVoicePath(String str) {
        this.voicePath = str;
    }

    public void setVoiceTitle(String str) {
        this.voiceTitle = str;
    }

    public void setWechatSportsType(int i) {
        this.wechatSportsType = i;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    public void writeToParcel(Parcel parcel, int i) {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
}
