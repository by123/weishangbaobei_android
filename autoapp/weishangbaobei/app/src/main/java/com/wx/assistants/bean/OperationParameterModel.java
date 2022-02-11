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
    private ArrayList<ContactBean> addressBookPhones;
    private int autoPullType;
    private String cardName;
    private int circulateInnerSize;
    private int circulateMode;
    private int circulateOutSize;
    private int cleanCircleType;
    private int cloneCircleType;
    private LinkedHashSet<CopyFriendBean> copyFriends;
    private int deleteFriendsType;
    private int deleteMode;
    private int deleteOrder;
    private int distance;
    private int dndMode;
    private String endDate;
    private int ffModel;
    private int ffModelEndTime;
    private int ffModelStartTime;
    private LinkedHashSet<String> filterNickNames;
    private int friendSendTag;
    private LinkedHashSet<String> friendsNameSet;
    private String friendsNameStr;
    private String groupNames;
    private int groupNumber;
    private int intimateMode;
    private boolean isAutoGroup;
    private boolean isAutoRemarkContactsName;
    private boolean isAutoSend;
    private boolean isBackStart;
    private int isBreakAdd;
    private boolean isCanRepeat;
    private int isDND;
    private boolean isDeleteNoFriends;
    private boolean isDeleteShieldFriends;
    private boolean isFastSpeed;
    private boolean isJumpTag;
    private int isOriginPic;
    private String jumpFriendNames;
    private String jumpGroupNames;
    private int likeCommentType;
    private String localImgUrl;
    private String materialForwardId;
    private int materialPicCount;
    private String materialText;
    private int maxCommentNum;
    private int maxOperaNum;
    private int maxPraiseNum;
    private int messageSendType;
    private int minimumStep;
    private int otherSendType;
    private List<PassiveCardBean> passiveCardBeans;
    private int praiseCommentType;
    private LinkedHashSet<RecoverFriendBean> recoverFriends;
    private String remarkPrefix;
    private String sayContent;
    private List<ContactScanBean> scanPhones;
    private int scrollSpeed;
    private String selectCircleGroups;
    private String selectCircleModel;
    private String selectCircleTags;
    private int sendCardType;
    private int sendGroupMethod;
    private int sendOrder;
    private Gender sex;
    private String singLabelStr;
    private int spaceTime;
    private String startDate;
    private int startIndex;
    private int tagFriendType;
    private LinkedHashSet<String> tagListNames;
    private LinkedHashSet<String> tagListPeoples;
    private String tagNamesStr;
    private String tagPeoplesStr;
    private String taskNum;
    private int verifyCode;
    private String voiceDesc;
    private String voiceNickName;
    private String voicePath;
    private String voiceTitle;
    private int wechatSportsType;

    public int describeContents() {
        return 0;
    }

    public int getScrollSpeed() {
        return this.scrollSpeed;
    }

    public void setScrollSpeed(int i) {
        this.scrollSpeed = i;
    }

    public int getGroupNumber() {
        return this.groupNumber;
    }

    public void setGroupNumber(int i) {
        this.groupNumber = i;
    }

    public boolean isAutoGroup() {
        return this.isAutoGroup;
    }

    public void setAutoGroup(boolean z) {
        this.isAutoGroup = z;
    }

    public int getSendGroupMethod() {
        return this.sendGroupMethod;
    }

    public void setSendGroupMethod(int i) {
        this.sendGroupMethod = i;
    }

    public int getIsOriginPic() {
        return this.isOriginPic;
    }

    public void setIsOriginPic(int i) {
        this.isOriginPic = i;
    }

    public String getSelectCircleModel() {
        return this.selectCircleModel;
    }

    public void setSelectCircleModel(String str) {
        this.selectCircleModel = str;
    }

    public String getSelectCircleTags() {
        return this.selectCircleTags;
    }

    public void setSelectCircleTags(String str) {
        this.selectCircleTags = str;
    }

    public String getSelectCircleGroups() {
        return this.selectCircleGroups;
    }

    public void setSelectCircleGroups(String str) {
        this.selectCircleGroups = str;
    }

    public List<ContactScanBean> getScanPhones() {
        return this.scanPhones;
    }

    public void setScanPhones(List<ContactScanBean> list) {
        this.scanPhones = list;
    }

    public List<PassiveCardBean> getPassiveCardBeans() {
        return this.passiveCardBeans;
    }

    public void setPassiveCardBeans(List<PassiveCardBean> list) {
        this.passiveCardBeans = list;
    }

    public boolean isCanRepeat() {
        return this.isCanRepeat;
    }

    public void setCanRepeat(boolean z) {
        this.isCanRepeat = z;
    }

    public boolean isFastSpeed() {
        return this.isFastSpeed;
    }

    public void setFastSpeed(boolean z) {
        this.isFastSpeed = z;
    }

    public boolean isBackStart() {
        return this.isBackStart;
    }

    public void setBackStart(boolean z) {
        this.isBackStart = z;
    }

    public int getCloneCircleType() {
        return this.cloneCircleType;
    }

    public void setCloneCircleType(int i) {
        this.cloneCircleType = i;
    }

    public int getLikeCommentType() {
        return this.likeCommentType;
    }

    public void setLikeCommentType(int i) {
        this.likeCommentType = i;
    }

    public int getVerifyCode() {
        return this.verifyCode;
    }

    public void setVerifyCode(int i) {
        this.verifyCode = i;
    }

    public int getFfModel() {
        return this.ffModel;
    }

    public void setFfModel(int i) {
        this.ffModel = i;
    }

    public int getFfModelStartTime() {
        return this.ffModelStartTime;
    }

    public void setFfModelStartTime(int i) {
        this.ffModelStartTime = i;
    }

    public int getFfModelEndTime() {
        return this.ffModelEndTime;
    }

    public void setFfModelEndTime(int i) {
        this.ffModelEndTime = i;
    }

    public String getVoiceNickName() {
        return this.voiceNickName;
    }

    public void setVoiceNickName(String str) {
        this.voiceNickName = str;
    }

    public String getVoiceTitle() {
        return this.voiceTitle;
    }

    public void setVoiceTitle(String str) {
        this.voiceTitle = str;
    }

    public String getVoiceDesc() {
        return this.voiceDesc;
    }

    public void setVoiceDesc(String str) {
        this.voiceDesc = str;
    }

    public String getVoicePath() {
        return this.voicePath;
    }

    public void setVoicePath(String str) {
        this.voicePath = str;
    }

    public int getDeleteOrder() {
        return this.deleteOrder;
    }

    public void setDeleteOrder(int i) {
        this.deleteOrder = i;
    }

    public int getDeleteMode() {
        return this.deleteMode;
    }

    public void setDeleteMode(int i) {
        this.deleteMode = i;
    }

    public int getDndMode() {
        return this.dndMode;
    }

    public void setDndMode(int i) {
        this.dndMode = i;
    }

    public String getSingLabelStr() {
        return this.singLabelStr;
    }

    public void setSingLabelStr(String str) {
        this.singLabelStr = str;
    }

    public int getIntimateMode() {
        return this.intimateMode;
    }

    public void setIntimateMode(int i) {
        this.intimateMode = i;
    }

    public int getIsBreakAdd() {
        return this.isBreakAdd;
    }

    public void setIsBreakAdd(int i) {
        this.isBreakAdd = i;
    }

    public int getIsDND() {
        return this.isDND;
    }

    public void setIsDND(int i) {
        this.isDND = i;
    }

    public int getCirculateOutSize() {
        return this.circulateOutSize;
    }

    public void setCirculateOutSize(int i) {
        this.circulateOutSize = i;
    }

    public int getCirculateMode() {
        return this.circulateMode;
    }

    public void setCirculateMode(int i) {
        this.circulateMode = i;
    }

    public int getCirculateInnerSize() {
        return this.circulateInnerSize;
    }

    public void setCirculateInnerSize(int i) {
        this.circulateInnerSize = i;
    }

    public int getSendOrder() {
        return this.sendOrder;
    }

    public void setSendOrder(int i) {
        this.sendOrder = i;
    }

    public int getOtherSendType() {
        return this.otherSendType;
    }

    public void setOtherSendType(int i) {
        this.otherSendType = i;
    }

    public int getTagFriendType() {
        return this.tagFriendType;
    }

    public void setTagFriendType(int i) {
        this.tagFriendType = i;
    }

    public boolean isDeleteShieldFriends() {
        return this.isDeleteShieldFriends;
    }

    public void setDeleteShieldFriends(boolean z) {
        this.isDeleteShieldFriends = z;
    }

    public LinkedHashSet<RecoverFriendBean> getRecoverFriends() {
        return this.recoverFriends;
    }

    public void setRecoverFriends(LinkedHashSet<RecoverFriendBean> linkedHashSet) {
        this.recoverFriends = linkedHashSet;
    }

    public LinkedHashSet<CopyFriendBean> getCopyFriends() {
        return this.copyFriends;
    }

    public void setCopyFriends(LinkedHashSet<CopyFriendBean> linkedHashSet) {
        this.copyFriends = linkedHashSet;
    }

    public LinkedHashSet<String> getFilterNickNames() {
        return this.filterNickNames;
    }

    public void setFilterNickNames(LinkedHashSet<String> linkedHashSet) {
        this.filterNickNames = linkedHashSet;
    }

    public int getSpaceTime() {
        return this.spaceTime;
    }

    public void setSpaceTime(int i) {
        this.spaceTime = i;
    }

    public String getMaterialForwardId() {
        return this.materialForwardId;
    }

    public void setMaterialForwardId(String str) {
        this.materialForwardId = str;
    }

    public int isBreakAdd() {
        return this.isBreakAdd;
    }

    public void setBreakAdd(int i) {
        this.isBreakAdd = i;
    }

    public boolean isAutoRemarkContactsName() {
        return this.isAutoRemarkContactsName;
    }

    public void setAutoRemarkContactsName(boolean z) {
        this.isAutoRemarkContactsName = z;
    }

    public String getMaterialText() {
        return this.materialText;
    }

    public void setMaterialText(String str) {
        this.materialText = str;
    }

    public int getMaterialPicCount() {
        return this.materialPicCount;
    }

    public void setMaterialPicCount(int i) {
        this.materialPicCount = i;
    }

    public ArrayList<ContactBean> getAddressBookPhones() {
        return this.addressBookPhones;
    }

    public void setAddressBookPhones(ArrayList<ContactBean> arrayList) {
        this.addressBookPhones = arrayList;
    }

    public String getLocalImgUrl() {
        return this.localImgUrl;
    }

    public void setLocalImgUrl(String str) {
        this.localImgUrl = str;
    }

    public int getFriendSendTag() {
        return this.friendSendTag;
    }

    public void setFriendSendTag(int i) {
        this.friendSendTag = i;
    }

    public int getMessageSendType() {
        return this.messageSendType;
    }

    public void setMessageSendType(int i) {
        this.messageSendType = i;
    }

    public int getSendCardType() {
        return this.sendCardType;
    }

    public void setSendCardType(int i) {
        this.sendCardType = i;
    }

    public int getCleanCircleType() {
        return this.cleanCircleType;
    }

    public void setCleanCircleType(int i) {
        this.cleanCircleType = i;
    }

    public int getWechatSportsType() {
        return this.wechatSportsType;
    }

    public void setWechatSportsType(int i) {
        this.wechatSportsType = i;
    }

    public int getMinimumStep() {
        return this.minimumStep;
    }

    public void setMinimumStep(int i) {
        this.minimumStep = i;
    }

    public int getAutoPullType() {
        return this.autoPullType;
    }

    public void setAutoPullType(int i) {
        this.autoPullType = i;
    }

    public int getMaxPraiseNum() {
        return this.maxPraiseNum;
    }

    public void setMaxPraiseNum(int i) {
        this.maxPraiseNum = i;
    }

    public int getMaxCommentNum() {
        return this.maxCommentNum;
    }

    public void setMaxCommentNum(int i) {
        this.maxCommentNum = i;
    }

    public int getPraiseCommentType() {
        return this.praiseCommentType;
    }

    public void setPraiseCommentType(int i) {
        this.praiseCommentType = i;
    }

    public int getDeleteFriendsType() {
        return this.deleteFriendsType;
    }

    public void setDeleteFriendsType(int i) {
        this.deleteFriendsType = i;
    }

    public static Parcelable.Creator<OperationParameterModel> getCREATOR() {
        return CREATOR;
    }

    public LinkedHashSet<String> getFriendsNameSet() {
        return this.friendsNameSet;
    }

    public void setFriendsNameSet(LinkedHashSet<String> linkedHashSet) {
        this.friendsNameSet = linkedHashSet;
    }

    public String getFriendsNameStr() {
        return this.friendsNameStr;
    }

    public void setFriendsNameStr(String str) {
        this.friendsNameStr = str;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public String getTaskNum() {
        return this.taskNum;
    }

    public void setTaskNum(String str) {
        this.taskNum = str;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public void setStartIndex(int i) {
        this.startIndex = i;
    }

    public int getMaxOperaNum() {
        return this.maxOperaNum;
    }

    public void setMaxOperaNum(int i) {
        this.maxOperaNum = i;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public boolean isDeleteNoFriends() {
        return this.isDeleteNoFriends;
    }

    public void setDeleteNoFriends(boolean z) {
        this.isDeleteNoFriends = z;
    }

    public boolean isJumpTag() {
        return this.isJumpTag;
    }

    public void setJumpTag(boolean z) {
        this.isJumpTag = z;
    }

    public String getTagNamesStr() {
        return this.tagNamesStr;
    }

    public void setTagNamesStr(String str) {
        this.tagNamesStr = str;
    }

    public LinkedHashSet<String> getTagListNames() {
        return this.tagListNames;
    }

    public void setTagListNames(LinkedHashSet<String> linkedHashSet) {
        this.tagListNames = linkedHashSet;
    }

    public String getTagPeoplesStr() {
        return this.tagPeoplesStr;
    }

    public void setTagPeoplesStr(String str) {
        this.tagPeoplesStr = str;
    }

    public LinkedHashSet<String> getTagListPeoples() {
        return this.tagListPeoples;
    }

    public void setTagListPeoples(LinkedHashSet<String> linkedHashSet) {
        this.tagListPeoples = linkedHashSet;
    }

    public String getSayContent() {
        return this.sayContent;
    }

    public void setSayContent(String str) {
        this.sayContent = str;
    }

    public Gender getSex() {
        return this.sex;
    }

    public void setSex(Gender gender) {
        this.sex = gender;
    }

    public boolean isAutoSend() {
        return this.isAutoSend;
    }

    public void setAutoSend(boolean z) {
        this.isAutoSend = z;
    }

    public String getCardName() {
        return this.cardName;
    }

    public void setCardName(String str) {
        this.cardName = str;
    }

    public String getJumpGroupNames() {
        return this.jumpGroupNames;
    }

    public void setJumpGroupNames(String str) {
        this.jumpGroupNames = str;
    }

    public String getGroupNames() {
        return this.groupNames;
    }

    public void setGroupNames(String str) {
        this.groupNames = str;
    }

    public String getJumpFriendNames() {
        return this.jumpFriendNames;
    }

    public void setJumpFriendNames(String str) {
        this.jumpFriendNames = str;
    }

    public String getRemarkPrefix() {
        return this.remarkPrefix;
    }

    public void setRemarkPrefix(String str) {
        this.remarkPrefix = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.friendSendTag);
        parcel.writeInt(this.messageSendType);
        parcel.writeInt(this.sendCardType);
        parcel.writeInt(this.cleanCircleType);
        parcel.writeInt(this.wechatSportsType);
        parcel.writeInt(this.minimumStep);
        parcel.writeInt(this.autoPullType);
        parcel.writeInt(this.maxPraiseNum);
        parcel.writeInt(this.likeCommentType);
        parcel.writeInt(this.sendGroupMethod);
        parcel.writeInt(this.maxCommentNum);
        parcel.writeInt(this.praiseCommentType);
        parcel.writeString(this.localImgUrl);
        parcel.writeString(this.taskNum);
        parcel.writeInt(this.deleteOrder);
        parcel.writeString(this.taskNum);
        parcel.writeString(this.taskNum);
        parcel.writeString(this.singLabelStr);
        parcel.writeInt(this.startIndex);
        parcel.writeInt(this.tagFriendType);
        parcel.writeInt(this.maxOperaNum);
        parcel.writeInt(this.isBreakAdd);
        parcel.writeInt(this.otherSendType);
        parcel.writeInt(this.deleteMode);
        parcel.writeInt(this.materialPicCount);
        parcel.writeInt(this.deleteFriendsType);
        parcel.writeInt(this.distance);
        parcel.writeInt(this.sendOrder);
        parcel.writeInt(this.dndMode);
        parcel.writeInt(this.intimateMode);
        parcel.writeInt(this.isDND);
        parcel.writeInt(this.scrollSpeed);
        parcel.writeInt(this.circulateOutSize);
        parcel.writeInt(this.circulateInnerSize);
        parcel.writeInt(this.circulateMode);
        parcel.writeInt(this.spaceTime);
        parcel.writeInt(this.ffModel);
        parcel.writeInt(this.ffModelStartTime);
        parcel.writeInt(this.ffModelEndTime);
        parcel.writeByte(this.isDeleteNoFriends ? (byte) 1 : 0);
        parcel.writeByte(this.isDeleteShieldFriends ? (byte) 1 : 0);
        parcel.writeByte(this.isJumpTag ? (byte) 1 : 0);
        parcel.writeByte(this.isCanRepeat ? (byte) 1 : 0);
        parcel.writeByte(this.isBackStart ? (byte) 1 : 0);
        parcel.writeByte(this.isFastSpeed ? (byte) 1 : 0);
        parcel.writeByte(this.isAutoGroup ? (byte) 1 : 0);
        parcel.writeString(this.tagNamesStr);
        parcel.writeList(this.scanPhones);
        parcel.writeList(this.passiveCardBeans);
        parcel.writeSerializable(this.tagListNames);
        parcel.writeSerializable(this.recoverFriends);
        parcel.writeSerializable(this.copyFriends);
        parcel.writeSerializable(this.addressBookPhones);
        parcel.writeString(this.tagPeoplesStr);
        parcel.writeSerializable(this.tagListPeoples);
        parcel.writeSerializable(this.filterNickNames);
        parcel.writeString(this.sayContent);
        parcel.writeInt(this.sex == null ? -1 : this.sex.ordinal());
        parcel.writeByte(this.isAutoSend ? (byte) 1 : 0);
        parcel.writeString(this.cardName);
        parcel.writeString(this.jumpGroupNames);
        parcel.writeString(this.groupNames);
        parcel.writeString(this.jumpFriendNames);
        parcel.writeSerializable(this.friendsNameSet);
        parcel.writeString(this.friendsNameStr);
        parcel.writeString(this.startDate);
        parcel.writeString(this.endDate);
        parcel.writeString(this.remarkPrefix);
        parcel.writeString(this.materialText);
        parcel.writeString(this.materialForwardId);
        parcel.writeString(this.voiceDesc);
        parcel.writeString(this.voiceNickName);
        parcel.writeString(this.voicePath);
        parcel.writeString(this.voiceTitle);
        parcel.writeInt(this.verifyCode);
        parcel.writeInt(this.isOriginPic);
        parcel.writeInt(this.cloneCircleType);
        parcel.writeString(this.selectCircleModel);
        parcel.writeString(this.selectCircleTags);
        parcel.writeString(this.selectCircleGroups);
        parcel.writeInt(this.groupNumber);
    }

    public OperationParameterModel() {
        this.scrollSpeed = 150;
        this.scanPhones = new ArrayList();
        this.passiveCardBeans = new ArrayList();
        this.ffModel = 0;
        this.ffModelStartTime = 0;
        this.ffModelEndTime = 0;
        this.sendOrder = 0;
        this.circulateMode = 0;
        this.circulateInnerSize = 1;
        this.circulateOutSize = 1;
        this.isDND = 0;
        this.otherSendType = 0;
        this.tagFriendType = 0;
        this.tagListNames = new LinkedHashSet<>();
        this.recoverFriends = new LinkedHashSet<>();
        this.copyFriends = new LinkedHashSet<>();
        this.tagListPeoples = new LinkedHashSet<>();
        this.filterNickNames = new LinkedHashSet<>();
        this.deleteFriendsType = 0;
        this.isBreakAdd = 0;
        this.cleanCircleType = 0;
        this.sendCardType = 0;
        this.wechatSportsType = 0;
        this.minimumStep = 1;
        this.autoPullType = 0;
        this.messageSendType = 0;
        this.friendSendTag = 0;
        this.isAutoRemarkContactsName = false;
        this.localImgUrl = "";
        this.addressBookPhones = new ArrayList<>();
    }

    protected OperationParameterModel(Parcel parcel) {
        Gender gender;
        this.scrollSpeed = 150;
        this.scanPhones = new ArrayList();
        this.passiveCardBeans = new ArrayList();
        boolean z = false;
        this.ffModel = 0;
        this.ffModelStartTime = 0;
        this.ffModelEndTime = 0;
        this.sendOrder = 0;
        this.circulateMode = 0;
        this.circulateInnerSize = 1;
        this.circulateOutSize = 1;
        this.isDND = 0;
        this.otherSendType = 0;
        this.tagFriendType = 0;
        this.tagListNames = new LinkedHashSet<>();
        this.recoverFriends = new LinkedHashSet<>();
        this.copyFriends = new LinkedHashSet<>();
        this.tagListPeoples = new LinkedHashSet<>();
        this.filterNickNames = new LinkedHashSet<>();
        this.deleteFriendsType = 0;
        this.isBreakAdd = 0;
        this.cleanCircleType = 0;
        this.sendCardType = 0;
        this.wechatSportsType = 0;
        this.minimumStep = 1;
        this.autoPullType = 0;
        this.messageSendType = 0;
        this.friendSendTag = 0;
        this.isAutoRemarkContactsName = false;
        this.localImgUrl = "";
        this.addressBookPhones = new ArrayList<>();
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
        if (readInt == -1) {
            gender = null;
        } else {
            gender = Gender.values()[readInt];
        }
        this.sex = gender;
        this.isAutoSend = parcel.readByte() != 0 ? true : z;
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
}
