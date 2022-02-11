package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.AutoService;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class GroupSendGroupSearchUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static GroupSendGroupSearchUtils instance;
    /* access modifiers changed from: private */
    public int circulateInnerSize = 1;
    /* access modifiers changed from: private */
    public int circulateMode = 0;
    /* access modifiers changed from: private */
    public int circulateOuterSize = 1;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> groupList = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public String groupNames = "";
    /* access modifiers changed from: private */
    public boolean isEnd;
    private boolean isFirstMaxSendExecuteNum = true;
    /* access modifiers changed from: private */
    public boolean isFirstSelectImg = true;
    /* access modifiers changed from: private */
    public boolean isRemoveIndex = false;
    /* access modifiers changed from: private */
    public boolean isRemoveRepeat = false;
    /* access modifiers changed from: private */
    public boolean isWhile = true;
    /* access modifiers changed from: private */
    public String jumpGroupName;
    /* access modifiers changed from: private */
    public int jumpTime = SocializeConstants.CANCLE_RESULTCODE;
    /* access modifiers changed from: private */
    public String lastName = "";
    private String localImgUrl;
    private int maxSendExecuteNum = 0;
    /* access modifiers changed from: private */
    public OperationParameterModel operationParameterModel;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> originList = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public int picCount = 1;
    /* access modifiers changed from: private */
    public String sayContent;
    /* access modifiers changed from: private */
    public int sendGroupNum = 0;
    /* access modifiers changed from: private */
    public int sendOrder = 0;
    /* access modifiers changed from: private */
    public String sendResult = "";
    private int sendTextCount = 10;
    private int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startIndexFromUser = 1;

    private GroupSendGroupSearchUtils() {
    }

    static /* synthetic */ int access$1208(GroupSendGroupSearchUtils groupSendGroupSearchUtils) {
        int i = groupSendGroupSearchUtils.startIndex;
        groupSendGroupSearchUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$2010(GroupSendGroupSearchUtils groupSendGroupSearchUtils) {
        int i = groupSendGroupSearchUtils.circulateInnerSize;
        groupSendGroupSearchUtils.circulateInnerSize = i - 1;
        return i;
    }

    static /* synthetic */ int access$2210(GroupSendGroupSearchUtils groupSendGroupSearchUtils) {
        int i = groupSendGroupSearchUtils.sendTextCount;
        groupSendGroupSearchUtils.sendTextCount = i - 1;
        return i;
    }

    static /* synthetic */ int access$710(GroupSendGroupSearchUtils groupSendGroupSearchUtils) {
        int i = groupSendGroupSearchUtils.circulateOuterSize;
        groupSendGroupSearchUtils.circulateOuterSize = i - 1;
        return i;
    }

    static /* synthetic */ int access$908(GroupSendGroupSearchUtils groupSendGroupSearchUtils) {
        int i = groupSendGroupSearchUtils.sendGroupNum;
        groupSendGroupSearchUtils.sendGroupNum = i + 1;
        return i;
    }

    public static GroupSendGroupSearchUtils getInstance() {
        instance = new GroupSendGroupSearchUtils();
        return instance;
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        try {
            if (this.operationParameterModel != null) {
                int circulateOutSize = this.operationParameterModel.getCirculateOutSize();
                if (this.circulateMode != 0 || this.circulateOuterSize <= 1) {
                    if (this.operationParameterModel.getSendCardType() == 0) {
                        MyWindowManager myWindowManager = this.mMyManager;
                        showBottomView(myWindowManager, "从第 " + this.startIndexFromUser + " 个群开始群发消息");
                    } else if (this.operationParameterModel.getSendCardType() == 1) {
                        MyWindowManager myWindowManager2 = this.mMyManager;
                        showBottomView(myWindowManager2, "从部分群中第 " + this.startIndexFromUser + " 个群开始群发消息");
                    } else if (this.operationParameterModel.getSendCardType() == 2) {
                        MyWindowManager myWindowManager3 = this.mMyManager;
                        showBottomView(myWindowManager3, "从部分群中第 " + this.startIndexFromUser + " 个群开始群发消息");
                    }
                } else if (this.operationParameterModel.getSendCardType() == 0) {
                    MyWindowManager myWindowManager4 = this.mMyManager;
                    StringBuilder sb = new StringBuilder();
                    sb.append("正在向[全部群]发送消息\n从第");
                    sb.append(this.startIndexFromUser);
                    sb.append("个微信群开始群发\n正在执行外循环第");
                    if ((circulateOutSize - this.circulateOuterSize) + 1 <= circulateOutSize) {
                        circulateOutSize = (circulateOutSize - this.circulateOuterSize) + 1;
                    }
                    sb.append(circulateOutSize);
                    sb.append("轮群发");
                    showBottomView(myWindowManager4, sb.toString());
                } else if (this.operationParameterModel.getSendCardType() == 1) {
                    MyWindowManager myWindowManager5 = this.mMyManager;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("正在向[部分群]发送消息\n从部分群中第");
                    sb2.append(this.startIndexFromUser);
                    sb2.append("个微信群开始群发\n正在执行外循环第");
                    if ((circulateOutSize - this.circulateOuterSize) + 1 <= circulateOutSize) {
                        circulateOutSize = (circulateOutSize - this.circulateOuterSize) + 1;
                    }
                    sb2.append(circulateOutSize);
                    sb2.append("轮群发");
                    showBottomView(myWindowManager5, sb2.toString());
                } else if (this.operationParameterModel.getSendCardType() == 2) {
                    MyWindowManager myWindowManager6 = this.mMyManager;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("正在向[部分群]发送消息\n从部分群中第");
                    sb3.append(this.startIndexFromUser);
                    sb3.append("个微信群开始群发\n正在执行外循环第");
                    if ((circulateOutSize - this.circulateOuterSize) + 1 <= circulateOutSize) {
                        circulateOutSize = (circulateOutSize - this.circulateOuterSize) + 1;
                    }
                    sb3.append(circulateOutSize);
                    sb3.append("轮群发");
                    showBottomView(myWindowManager6, sb3.toString());
                }
            } else {
                showBottomView(this.mMyManager, "正在向微信群发送消息\n请不要操作微信");
            }
            new Thread(new Runnable() {
                public void run() {
                    try {
                        GroupSendGroupSearchUtils.this.executeMain();
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeMain() {
        try {
            new PerformClickUtils2().checkString(this.autoService, "通讯录", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findTextAndClick(GroupSendGroupSearchUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(GroupSendGroupSearchUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(GroupSendGroupSearchUtils.this.autoService, "通讯录");
                    new PerformClickUtils2().checkString(GroupSendGroupSearchUtils.this.autoService, "群聊", BaseServiceUtils.executeSpeedSetting + 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(GroupSendGroupSearchUtils.this.autoService, "群聊");
                            GroupSendGroupSearchUtils.this.initChatRoomContactUI();
                        }

                        public void nuFind() {
                        }
                    });
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initAlbumPreviewUI() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, img_first_check_layout_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 300, 200, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY.AlbumPreviewUI_2");
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.img_first_check_layout_id);
                    if (findViewIdList == null || findViewIdList.size() <= 0) {
                        GroupSendGroupSearchUtils.this.initAlbumPreviewUI();
                        return;
                    }
                    int access$000 = GroupSendGroupSearchUtils.this.picCount - 1;
                    while (true) {
                        int i2 = access$000;
                        if (i2 <= -1 || !MyApplication.enforceable) {
                            GroupSendGroupSearchUtils.this.sleep(100);
                            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.complete_id);
                        } else {
                            GroupSendGroupSearchUtils.this.sleep(5);
                            PerformClickUtils.performClick(findViewIdList.get(i2));
                            access$000 = i2 - 1;
                        }
                    }
                    GroupSendGroupSearchUtils.this.sleep(100);
                    List<AccessibilityNodeInfo> findViewIdList22 = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.complete_id);
                    if (findViewIdList22 == null || findViewIdList22.size() <= 0 || !findViewIdList22.get(0).isEnabled()) {
                        GroupSendGroupSearchUtils.this.initAlbumPreviewUI();
                        return;
                    }
                    findViewIdList22.get(0).performAction(16);
                    if (GroupSendGroupSearchUtils.this.operationParameterModel.getMessageSendType() != 2) {
                        GroupSendGroupSearchUtils.this.initChattingBackUI();
                    } else if (GroupSendGroupSearchUtils.this.sendOrder == 0) {
                        GroupSendGroupSearchUtils.this.sendText();
                    } else {
                        GroupSendGroupSearchUtils.this.initChattingBackUI();
                    }
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initChatRoomContactUI() {
        try {
            this.circulateMode = this.operationParameterModel.getCirculateMode();
            this.circulateInnerSize = this.operationParameterModel.getCirculateInnerSize();
            if (this.groupList == null || this.groupList.size() <= 0 || !MyApplication.enforceable) {
                LogUtils.log("WS_BABY.ChatroomContactUI_1");
                if (this.isEnd || !MyApplication.enforceable) {
                    removeEndView(this.mMyManager);
                } else if (this.operationParameterModel.getSendCardType() != 1 || !MyApplication.enforceable) {
                    new PerformClickUtils2().checkNodeAllIds(this.autoService, grout_friend_list_id, group_list_item_name_id, 300, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                            String str;
                            while (GroupSendGroupSearchUtils.this.isWhile && MyApplication.enforceable) {
                                AccessibilityNodeInfo rootInActiveWindow = GroupSendGroupSearchUtils.this.autoService.getRootInActiveWindow();
                                if (rootInActiveWindow == null) {
                                    boolean unused = GroupSendGroupSearchUtils.this.isWhile = false;
                                    return;
                                }
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.grout_friend_list_id);
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
                                if (findAccessibilityNodeInfosByViewId3 != null && !findAccessibilityNodeInfosByViewId3.isEmpty() && MyApplication.enforceable) {
                                    if (GroupSendGroupSearchUtils.this.startIndex < findAccessibilityNodeInfosByViewId3.size()) {
                                        AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId3.get(GroupSendGroupSearchUtils.this.startIndex);
                                        if (accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null) {
                                            str = "";
                                        } else {
                                            str = accessibilityNodeInfo.getText() + "";
                                        }
                                        if (GroupSendGroupSearchUtils.this.groupList.contains(str)) {
                                            GroupSendGroupSearchUtils.access$1208(GroupSendGroupSearchUtils.this);
                                        } else {
                                            GroupSendGroupSearchUtils.this.groupList.add(str);
                                            GroupSendGroupSearchUtils.access$1208(GroupSendGroupSearchUtils.this);
                                        }
                                    } else if (GroupSendGroupSearchUtils.this.startIndex >= findAccessibilityNodeInfosByViewId3.size() && MyApplication.enforceable && findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                                        PerformClickUtils.scroll(findAccessibilityNodeInfosByViewId2.get(0));
                                        new PerformClickUtils2().checkSyn2(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.grout_friend_list_id, BaseServiceUtils.group_list_item_name_id, 0, 10, 100, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                GroupSendGroupSearchUtils.this.sleep(100);
                                                PrintStream printStream = System.out;
                                                printStream.println("WS_BABY_TIME.end2 = " + System.currentTimeMillis());
                                            }

                                            public void nuFind() {
                                            }
                                        });
                                        AccessibilityNodeInfo rootInActiveWindow2 = GroupSendGroupSearchUtils.this.autoService.getRootInActiveWindow();
                                        if (rootInActiveWindow2 != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id)) != null && findAccessibilityNodeInfosByViewId.size() != 0) {
                                            String str2 = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1).getText() + "";
                                            if (str2.equals(GroupSendGroupSearchUtils.this.lastName)) {
                                                boolean unused2 = GroupSendGroupSearchUtils.this.isWhile = false;
                                            } else {
                                                String unused3 = GroupSendGroupSearchUtils.this.lastName = str2;
                                            }
                                            int unused4 = GroupSendGroupSearchUtils.this.startIndex = 0;
                                        } else {
                                            return;
                                        }
                                    }
                                }
                            }
                            if (!GroupSendGroupSearchUtils.this.isWhile && MyApplication.enforceable) {
                                if (GroupSendGroupSearchUtils.this.groupList != null && GroupSendGroupSearchUtils.this.groupList.size() > 0 && GroupSendGroupSearchUtils.this.operationParameterModel.getSendCardType() == 2 && MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY.ChatroomContactUI_555");
                                    if (!GroupSendGroupSearchUtils.this.isRemoveRepeat) {
                                        if (GroupSendGroupSearchUtils.this.jumpGroupName.contains(";")) {
                                            String[] split = GroupSendGroupSearchUtils.this.jumpGroupName.split(";");
                                            for (String remove : split) {
                                                GroupSendGroupSearchUtils.this.groupList.remove(remove);
                                            }
                                        } else {
                                            GroupSendGroupSearchUtils.this.groupList.remove(GroupSendGroupSearchUtils.this.jumpGroupName);
                                        }
                                        boolean unused5 = GroupSendGroupSearchUtils.this.isRemoveRepeat = true;
                                    }
                                    LogUtils.log("WS_BABY.ChatroomContactUI_666");
                                }
                                if (!GroupSendGroupSearchUtils.this.isRemoveIndex && GroupSendGroupSearchUtils.this.startIndexFromUser > 1 && MyApplication.enforceable) {
                                    if (GroupSendGroupSearchUtils.this.groupList.size() >= GroupSendGroupSearchUtils.this.startIndexFromUser) {
                                        for (int i2 = 1; i2 < GroupSendGroupSearchUtils.this.startIndexFromUser; i2++) {
                                            Iterator it = GroupSendGroupSearchUtils.this.groupList.iterator();
                                            if (it.hasNext()) {
                                                GroupSendGroupSearchUtils.this.groupList.remove(it.next());
                                            }
                                        }
                                        boolean unused6 = GroupSendGroupSearchUtils.this.isRemoveIndex = true;
                                    } else {
                                        BaseServiceUtils.removeEndView(GroupSendGroupSearchUtils.this.mMyManager);
                                        GroupSendGroupSearchUtils.this.groupList.clear();
                                        String unused7 = GroupSendGroupSearchUtils.this.sendResult = "群发消息失败,您设置群发的起点位置是从第[" + GroupSendGroupSearchUtils.this.startIndexFromUser + "]个开始，高于群数量";
                                    }
                                }
                            }
                            if (GroupSendGroupSearchUtils.this.groupList != null && GroupSendGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                                if (GroupSendGroupSearchUtils.this.circulateMode == 0 && MyApplication.enforceable) {
                                    GroupSendGroupSearchUtils.this.originList.clear();
                                    GroupSendGroupSearchUtils.this.originList.addAll(GroupSendGroupSearchUtils.this.groupList);
                                }
                                new PerformClickUtils2().check(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.nav_search_id, GroupSendGroupSearchUtils.this.jumpTime, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        PerformClickUtils.findNodeByIdFirstAndClick(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.nav_search_id);
                                        GroupSendGroupSearchUtils.this.sleep(GroupSendGroupSearchUtils.this.jumpTime / 2);
                                        String unused = GroupSendGroupSearchUtils.this.groupNames = "";
                                        Iterator it = GroupSendGroupSearchUtils.this.groupList.iterator();
                                        if (it != null && it.hasNext()) {
                                            String unused2 = GroupSendGroupSearchUtils.this.groupNames = (String) it.next();
                                        }
                                        String substring = GroupSendGroupSearchUtils.this.groupNames.length() > 26 ? GroupSendGroupSearchUtils.this.groupNames.substring(0, 25) : GroupSendGroupSearchUtils.this.groupNames;
                                        for (int i2 = 0; i2 < 10 && substring.startsWith("@"); i2++) {
                                            substring = substring.substring(1);
                                        }
                                        PerformClickUtils.inputText(GroupSendGroupSearchUtils.this.autoService, substring);
                                        LogUtils.log("WS_BABY.ChatroomContactUI_777");
                                        new PerformClickUtils2().check(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.group_list_item_name_id, GroupSendGroupSearchUtils.this.groupNames, 1200, 100, 25, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                            /* JADX WARNING: Removed duplicated region for block: B:71:0x028b A[LOOP:0: B:22:0x0096->B:71:0x028b, LOOP_END] */
                                            /* JADX WARNING: Removed duplicated region for block: B:75:0x0101 A[SYNTHETIC] */
                                            public void find(int i) {
                                                boolean z;
                                                String str;
                                                AccessibilityNodeInfo rootInActiveWindow = GroupSendGroupSearchUtils.this.autoService.getRootInActiveWindow();
                                                if (rootInActiveWindow != null) {
                                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
                                                    if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                                        try {
                                                            Iterator it = GroupSendGroupSearchUtils.this.groupList.iterator();
                                                            if (it.hasNext()) {
                                                                GroupSendGroupSearchUtils.this.groupList.remove(it.next());
                                                                if (GroupSendGroupSearchUtils.this.groupList.size() > 0) {
                                                                    boolean unused = GroupSendGroupSearchUtils.this.isEnd = false;
                                                                } else if (GroupSendGroupSearchUtils.this.circulateMode == 0) {
                                                                    GroupSendGroupSearchUtils.access$710(GroupSendGroupSearchUtils.this);
                                                                    if (GroupSendGroupSearchUtils.this.circulateOuterSize <= 0) {
                                                                        boolean unused2 = GroupSendGroupSearchUtils.this.isEnd = true;
                                                                    } else {
                                                                        int unused3 = GroupSendGroupSearchUtils.this.sendGroupNum = -1;
                                                                        GroupSendGroupSearchUtils.this.groupList.addAll(GroupSendGroupSearchUtils.this.originList);
                                                                    }
                                                                } else {
                                                                    boolean unused4 = GroupSendGroupSearchUtils.this.isEnd = true;
                                                                }
                                                                if (findAccessibilityNodeInfosByViewId != null) {
                                                                    LogUtils.log("WS_BABY.ChatroomContactUI_3");
                                                                    if (findAccessibilityNodeInfosByViewId.size() > 1) {
                                                                        LogUtils.log("WS_BABY.ChatroomContactUI_4");
                                                                        int i2 = 0;
                                                                        while (true) {
                                                                            if (i2 >= findAccessibilityNodeInfosByViewId.size()) {
                                                                                z = false;
                                                                                break;
                                                                            }
                                                                            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i2);
                                                                            if (accessibilityNodeInfo != null) {
                                                                                if (accessibilityNodeInfo.getText() != null) {
                                                                                    str = accessibilityNodeInfo.getText() + "";
                                                                                    LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendGroupSearchUtils.this.groupNames);
                                                                                    if (str == null && "".equals(str) && !str.equals(GroupSendGroupSearchUtils.this.groupNames)) {
                                                                                        LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendGroupSearchUtils.this.groupNames + "@@@@");
                                                                                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(i2));
                                                                                        GroupSendGroupSearchUtils.this.initFirstChattingUI();
                                                                                        z = true;
                                                                                        break;
                                                                                    }
                                                                                    i2++;
                                                                                }
                                                                            }
                                                                            str = "";
                                                                            LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendGroupSearchUtils.this.groupNames);
                                                                            if (str == null && "".equals(str) && !str.equals(GroupSendGroupSearchUtils.this.groupNames)) {
                                                                            }
                                                                        }
                                                                        if (!z) {
                                                                            LogUtils.log("WS_BABY.ChatroomContactUI_55");
                                                                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                                            GroupSendGroupSearchUtils.this.initFirstChattingUI();
                                                                        }
                                                                    } else if (findAccessibilityNodeInfosByViewId.size() == 1) {
                                                                        LogUtils.log("WS_BABY.ChatroomContactUI_6");
                                                                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                                        GroupSendGroupSearchUtils.this.initFirstChattingUI();
                                                                    }
                                                                }
                                                            }
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    } else if (GroupSendGroupSearchUtils.this.groupList != null && GroupSendGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                                                        try {
                                                            Iterator it2 = GroupSendGroupSearchUtils.this.groupList.iterator();
                                                            if (it2.hasNext()) {
                                                                GroupSendGroupSearchUtils.this.groupList.remove(it2.next());
                                                                if (GroupSendGroupSearchUtils.this.groupList.size() > 0) {
                                                                    boolean unused5 = GroupSendGroupSearchUtils.this.isEnd = false;
                                                                } else if (GroupSendGroupSearchUtils.this.circulateMode == 0) {
                                                                    GroupSendGroupSearchUtils.access$710(GroupSendGroupSearchUtils.this);
                                                                    if (GroupSendGroupSearchUtils.this.circulateOuterSize <= 0) {
                                                                        boolean unused6 = GroupSendGroupSearchUtils.this.isEnd = true;
                                                                    } else {
                                                                        int unused7 = GroupSendGroupSearchUtils.this.sendGroupNum = -1;
                                                                        GroupSendGroupSearchUtils.this.groupList.addAll(GroupSendGroupSearchUtils.this.originList);
                                                                    }
                                                                } else {
                                                                    boolean unused8 = GroupSendGroupSearchUtils.this.isEnd = true;
                                                                }
                                                                PerformClickUtils.executedBackHome(GroupSendGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                                    public void find() {
                                                                        GroupSendGroupSearchUtils.this.executeMain();
                                                                    }
                                                                });
                                                            }
                                                        } catch (Exception e2) {
                                                        }
                                                    }
                                                }
                                            }

                                            public void nuFind() {
                                                GroupSendGroupSearchUtils.this.saveIndex(GroupSendGroupSearchUtils.this.operationParameterModel.getMessageSendType());
                                                if (GroupSendGroupSearchUtils.this.groupList != null && GroupSendGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                                                    try {
                                                        Iterator it = GroupSendGroupSearchUtils.this.groupList.iterator();
                                                        if (it.hasNext()) {
                                                            GroupSendGroupSearchUtils.this.groupList.remove(it.next());
                                                            if (GroupSendGroupSearchUtils.this.groupList.size() > 0) {
                                                                boolean unused = GroupSendGroupSearchUtils.this.isEnd = false;
                                                            } else if (GroupSendGroupSearchUtils.this.circulateMode == 0) {
                                                                GroupSendGroupSearchUtils.access$710(GroupSendGroupSearchUtils.this);
                                                                if (GroupSendGroupSearchUtils.this.circulateOuterSize <= 0) {
                                                                    boolean unused2 = GroupSendGroupSearchUtils.this.isEnd = true;
                                                                } else {
                                                                    int unused3 = GroupSendGroupSearchUtils.this.sendGroupNum = -1;
                                                                    GroupSendGroupSearchUtils.this.groupList.addAll(GroupSendGroupSearchUtils.this.originList);
                                                                }
                                                            } else {
                                                                boolean unused4 = GroupSendGroupSearchUtils.this.isEnd = true;
                                                            }
                                                            PerformClickUtils.executedBackHome(GroupSendGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                                public void find() {
                                                                    GroupSendGroupSearchUtils.this.executeMain();
                                                                }
                                                            });
                                                        }
                                                    } catch (Exception e) {
                                                    }
                                                }
                                            }
                                        });
                                    }

                                    public void nuFind() {
                                    }
                                });
                            }
                        }

                        public void nuFind() {
                        }
                    });
                } else {
                    this.groupList = new LinkedHashSet<>();
                    if (this.jumpGroupName.contains(";")) {
                        String[] split = this.jumpGroupName.split(";");
                        for (int i = 0; i < split.length; i++) {
                            if (split[i] != null) {
                                if (!split[i].equals("")) {
                                    this.groupList.add(split[i]);
                                }
                            }
                        }
                    } else {
                        this.groupList.add(this.jumpGroupName);
                    }
                    try {
                        if (this.startIndexFromUser > 1) {
                            LogUtils.log("WS_BABY.st.1");
                            for (int i2 = 0; i2 < this.startIndexFromUser - 1; i2++) {
                                LogUtils.log("WS_BABY.st.st" + i2);
                                if (this.groupList.size() > 0) {
                                    this.groupList.remove(this.groupList.iterator().next());
                                }
                            }
                        } else {
                            LogUtils.log("WS_BABY.st.2");
                        }
                    } catch (Exception e) {
                        LogUtils.log("WS_BABY.st.3");
                    }
                    LogUtils.log("WS_BABY.ChatroomContactUI_111");
                    new PerformClickUtils2().check(this.autoService, nav_search_id, 300, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findNodeByIdFirstAndClick(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.nav_search_id);
                            GroupSendGroupSearchUtils.this.sleep(GroupSendGroupSearchUtils.this.jumpTime / 2);
                            String unused = GroupSendGroupSearchUtils.this.groupNames = "";
                            Iterator it = GroupSendGroupSearchUtils.this.groupList.iterator();
                            if (it != null && it.hasNext()) {
                                String unused2 = GroupSendGroupSearchUtils.this.groupNames = (String) it.next();
                            }
                            String substring = GroupSendGroupSearchUtils.this.groupNames.length() > 26 ? GroupSendGroupSearchUtils.this.groupNames.substring(0, 25) : GroupSendGroupSearchUtils.this.groupNames;
                            for (int i2 = 0; i2 < 10 && substring.startsWith("@"); i2++) {
                                substring = substring.substring(1);
                            }
                            PerformClickUtils.inputText(GroupSendGroupSearchUtils.this.autoService, substring);
                            new PerformClickUtils2().check(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.group_list_item_name_id, GroupSendGroupSearchUtils.this.groupNames, 1200, 100, 25, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                /* JADX WARNING: Removed duplicated region for block: B:64:0x0239 A[LOOP:0: B:20:0x0082->B:64:0x0239, LOOP_END] */
                                /* JADX WARNING: Removed duplicated region for block: B:68:0x00e9 A[SYNTHETIC] */
                                public void find(int i) {
                                    boolean z;
                                    String str;
                                    AccessibilityNodeInfo rootInActiveWindow = GroupSendGroupSearchUtils.this.autoService.getRootInActiveWindow();
                                    if (rootInActiveWindow != null) {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
                                        if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                                            try {
                                                Iterator it = GroupSendGroupSearchUtils.this.groupList.iterator();
                                                if (it.hasNext()) {
                                                    GroupSendGroupSearchUtils.this.groupList.remove(it.next());
                                                    if (GroupSendGroupSearchUtils.this.groupList.size() > 0) {
                                                        boolean unused = GroupSendGroupSearchUtils.this.isEnd = false;
                                                    } else if (GroupSendGroupSearchUtils.this.circulateMode == 0) {
                                                        GroupSendGroupSearchUtils.access$710(GroupSendGroupSearchUtils.this);
                                                        if (GroupSendGroupSearchUtils.this.circulateOuterSize <= 0) {
                                                            boolean unused2 = GroupSendGroupSearchUtils.this.isEnd = true;
                                                        } else {
                                                            int unused3 = GroupSendGroupSearchUtils.this.sendGroupNum = -1;
                                                            GroupSendGroupSearchUtils.this.groupList.addAll(GroupSendGroupSearchUtils.this.originList);
                                                        }
                                                    } else {
                                                        boolean unused4 = GroupSendGroupSearchUtils.this.isEnd = true;
                                                    }
                                                    if (findAccessibilityNodeInfosByViewId != null) {
                                                        LogUtils.log("WS_BABY.ChatroomContactUI_3");
                                                        if (findAccessibilityNodeInfosByViewId.size() > 1) {
                                                            LogUtils.log("WS_BABY.ChatroomContactUI_4");
                                                            int i2 = 0;
                                                            while (true) {
                                                                if (i2 >= findAccessibilityNodeInfosByViewId.size()) {
                                                                    z = false;
                                                                    break;
                                                                }
                                                                AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i2);
                                                                if (accessibilityNodeInfo != null) {
                                                                    if (accessibilityNodeInfo.getText() != null) {
                                                                        str = accessibilityNodeInfo.getText() + "";
                                                                        LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendGroupSearchUtils.this.groupNames);
                                                                        if (str == null && "".equals(str) && !str.equals(GroupSendGroupSearchUtils.this.groupNames)) {
                                                                            LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendGroupSearchUtils.this.groupNames + "@@@@");
                                                                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(i2));
                                                                            GroupSendGroupSearchUtils.this.initFirstChattingUI();
                                                                            z = true;
                                                                            break;
                                                                        }
                                                                        i2++;
                                                                    }
                                                                }
                                                                str = "";
                                                                LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendGroupSearchUtils.this.groupNames);
                                                                if (str == null && "".equals(str) && !str.equals(GroupSendGroupSearchUtils.this.groupNames)) {
                                                                }
                                                            }
                                                            if (!z) {
                                                                LogUtils.log("WS_BABY.ChatroomContactUI_55");
                                                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                                GroupSendGroupSearchUtils.this.initFirstChattingUI();
                                                            }
                                                        } else if (findAccessibilityNodeInfosByViewId.size() == 1) {
                                                            LogUtils.log("WS_BABY.ChatroomContactUI_6");
                                                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                            GroupSendGroupSearchUtils.this.initFirstChattingUI();
                                                        }
                                                    }
                                                }
                                            } catch (Exception e) {
                                            }
                                        } else if (GroupSendGroupSearchUtils.this.groupList != null && GroupSendGroupSearchUtils.this.groupList.size() > 0) {
                                            Iterator it2 = GroupSendGroupSearchUtils.this.groupList.iterator();
                                            if (it2.hasNext()) {
                                                GroupSendGroupSearchUtils.this.groupList.remove(it2.next());
                                                if (GroupSendGroupSearchUtils.this.groupList.size() > 0) {
                                                    boolean unused5 = GroupSendGroupSearchUtils.this.isEnd = false;
                                                } else if (GroupSendGroupSearchUtils.this.circulateMode == 0) {
                                                    GroupSendGroupSearchUtils.access$710(GroupSendGroupSearchUtils.this);
                                                    if (GroupSendGroupSearchUtils.this.circulateOuterSize <= 0) {
                                                        boolean unused6 = GroupSendGroupSearchUtils.this.isEnd = true;
                                                    } else {
                                                        int unused7 = GroupSendGroupSearchUtils.this.sendGroupNum = -1;
                                                        GroupSendGroupSearchUtils.this.groupList.addAll(GroupSendGroupSearchUtils.this.originList);
                                                    }
                                                } else {
                                                    boolean unused8 = GroupSendGroupSearchUtils.this.isEnd = true;
                                                }
                                                PerformClickUtils.executedBackHome(GroupSendGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                    public void find() {
                                                        GroupSendGroupSearchUtils.this.executeMain();
                                                    }
                                                });
                                            }
                                        }
                                    }
                                }

                                public void nuFind() {
                                    GroupSendGroupSearchUtils.this.saveIndex(GroupSendGroupSearchUtils.this.operationParameterModel.getMessageSendType());
                                    if (GroupSendGroupSearchUtils.this.groupList != null && GroupSendGroupSearchUtils.this.groupList.size() > 0) {
                                        try {
                                            Iterator it = GroupSendGroupSearchUtils.this.groupList.iterator();
                                            if (it.hasNext()) {
                                                GroupSendGroupSearchUtils.this.groupList.remove(it.next());
                                                if (GroupSendGroupSearchUtils.this.groupList.size() > 0) {
                                                    boolean unused = GroupSendGroupSearchUtils.this.isEnd = false;
                                                } else if (GroupSendGroupSearchUtils.this.circulateMode == 0) {
                                                    GroupSendGroupSearchUtils.access$710(GroupSendGroupSearchUtils.this);
                                                    if (GroupSendGroupSearchUtils.this.circulateOuterSize <= 0) {
                                                        boolean unused2 = GroupSendGroupSearchUtils.this.isEnd = true;
                                                    } else {
                                                        int unused3 = GroupSendGroupSearchUtils.this.sendGroupNum = -1;
                                                        GroupSendGroupSearchUtils.this.groupList.addAll(GroupSendGroupSearchUtils.this.originList);
                                                    }
                                                } else {
                                                    boolean unused4 = GroupSendGroupSearchUtils.this.isEnd = true;
                                                }
                                                PerformClickUtils.executedBackHome(GroupSendGroupSearchUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                                    public void find() {
                                                        GroupSendGroupSearchUtils.this.executeMain();
                                                    }
                                                });
                                            }
                                        } catch (Exception e) {
                                        }
                                    }
                                }
                            });
                        }

                        public void nuFind() {
                        }
                    });
                }
            } else if (this.spaceTime > 0) {
                new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                    public void end() {
                        GroupSendGroupSearchUtils.this.initRoomContact();
                    }

                    public void surplus(int i) {
                        MyWindowManager myWindowManager = GroupSendGroupSearchUtils.this.mMyManager;
                        BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendGroupSearchUtils.this.sendGroupNum + " 个群,剩余 " + GroupSendGroupSearchUtils.this.groupList.size() + " 个群\n正在延时等待，倒计时 " + i + " 秒");
                    }
                });
            } else {
                initRoomContact();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void initChattingBackUI() {
        PerformClickUtils2 performClickUtils2;
        AutoService autoService;
        AnonymousClass11 r7;
        LogUtils.log("WS_BABY.ChattingUI");
        LogUtils.log("WS_BABY.ChattingUI_2");
        this.circulateInnerSize--;
        try {
            if (this.operationParameterModel.getMessageSendType() != 1 || !MyApplication.enforceable) {
                if (this.operationParameterModel.getMessageSendType() == 2 && MyApplication.enforceable && this.circulateInnerSize <= 0) {
                    this.sendGroupNum++;
                    int i = this.startIndexFromUser == 1 ? this.sendGroupNum + 1 : this.sendGroupNum + this.startIndexFromUser;
                    if (this.operationParameterModel.getSendCardType() == 0) {
                        SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_all", Integer.valueOf(i));
                    } else if (this.operationParameterModel.getSendCardType() == 1) {
                        SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_part", Integer.valueOf(i));
                    } else if (this.operationParameterModel.getSendCardType() == 2) {
                        SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_shield", Integer.valueOf(i));
                    }
                }
            } else if (this.circulateInnerSize <= 0) {
                this.sendGroupNum++;
                int i2 = this.startIndexFromUser == 1 ? this.sendGroupNum + 1 : this.sendGroupNum + this.startIndexFromUser;
                if (this.operationParameterModel.getSendCardType() == 0) {
                    SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_all", Integer.valueOf(i2));
                } else if (this.operationParameterModel.getSendCardType() == 1) {
                    SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_part", Integer.valueOf(i2));
                } else if (this.operationParameterModel.getSendCardType() == 2) {
                    SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_shield", Integer.valueOf(i2));
                }
            }
            if (this.circulateInnerSize > 0 || !MyApplication.enforceable) {
                int circulateInnerSize2 = this.operationParameterModel.getCirculateInnerSize();
                if (this.operationParameterModel.getSendCardType() == 0) {
                    updateBottomText(this.mMyManager, "从[全部群]中第 " + this.startIndexFromUser + " 个开始群发消息\n正在发送第 " + (this.sendGroupNum + 1) + " 个群,剩余 " + this.groupList.size() + " 个群\n已内循环 " + (circulateInnerSize2 - this.circulateInnerSize) + " 次，剩余 " + this.circulateInnerSize + " 次");
                } else {
                    updateBottomText(this.mMyManager, "从[部分群]中第 " + this.startIndexFromUser + " 个开始群发消息\n正在发送第 " + (this.sendGroupNum + 1) + " 个群,剩余 " + this.groupList.size() + " 个群\n已内循环 " + (circulateInnerSize2 - this.circulateInnerSize) + " 次，剩余 " + this.circulateInnerSize + " 次");
                }
            } else {
                int circulateOutSize = this.operationParameterModel.getCirculateOutSize();
                if (this.circulateMode == 0 && circulateOutSize > 1 && MyApplication.enforceable) {
                    if (this.isFirstMaxSendExecuteNum) {
                        this.isFirstMaxSendExecuteNum = false;
                        this.maxSendExecuteNum = this.groupList.size() + 1;
                    }
                    int size = (this.groupList.size() != 0 || this.circulateOuterSize <= 0) ? this.groupList.size() : this.maxSendExecuteNum;
                    if (this.operationParameterModel.getSendCardType() == 0) {
                        MyWindowManager myWindowManager = this.mMyManager;
                        StringBuilder sb = new StringBuilder();
                        sb.append("从[全部群]中第 ");
                        sb.append(this.startIndexFromUser);
                        sb.append(" 个开始群发消息\n已发送 ");
                        sb.append(this.sendGroupNum);
                        sb.append(" 个群,剩余 ");
                        sb.append(size);
                        sb.append(" 个群\n正在执行第 ");
                        if ((circulateOutSize - this.circulateOuterSize) + 1 <= circulateOutSize) {
                            circulateOutSize = (circulateOutSize - this.circulateOuterSize) + 1;
                        }
                        sb.append(circulateOutSize);
                        sb.append(" 轮群发");
                        updateBottomText(myWindowManager, sb.toString());
                    } else {
                        MyWindowManager myWindowManager2 = this.mMyManager;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("从[部分群]中第 ");
                        sb2.append(this.startIndexFromUser);
                        sb2.append(" 个开始群发消息\n已发送 ");
                        sb2.append(this.sendGroupNum);
                        sb2.append(" 个群,剩余 ");
                        sb2.append(size);
                        sb2.append(" 个群\n正在执行第 ");
                        if ((circulateOutSize - this.circulateOuterSize) + 1 <= circulateOutSize) {
                            circulateOutSize = (circulateOutSize - this.circulateOuterSize) + 1;
                        }
                        sb2.append(circulateOutSize);
                        sb2.append(" 轮群发");
                        updateBottomText(myWindowManager2, sb2.toString());
                    }
                } else if (this.operationParameterModel.getSendCardType() == 0) {
                    updateBottomText(this.mMyManager, "从[全部群]中第 " + this.startIndexFromUser + " 个开始群发消息\n已发送 " + this.sendGroupNum + " 个群,剩余 " + this.groupList.size() + " 个群");
                } else {
                    updateBottomText(this.mMyManager, "从[部分群]中第 " + this.startIndexFromUser + " 个开始群发消息\n已发送 " + this.sendGroupNum + " 个群,剩余 " + this.groupList.size() + " 个群");
                }
            }
            try {
                if (this.circulateInnerSize > 0 || !MyApplication.enforceable) {
                    System.out.println("WS_BABY_YYYYYYYYYYYY");
                    initFirstChattingUI();
                    return;
                }
                System.out.println("WS_BABY_XXXXXXXXXXXXXXXXX");
                performClickUtils2 = new PerformClickUtils2();
                autoService = this.autoService;
                r7 = new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.executedBackHome(GroupSendGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                GroupSendGroupSearchUtils.this.executeMain();
                            }
                        });
                    }

                    public void nuFind() {
                    }
                };
                performClickUtils2.checkNilStringTwo(autoService, "请稍候", "正在发送中", SocializeConstants.CANCLE_RESULTCODE, 100, 100, r7);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (this.circulateInnerSize > 0 || !MyApplication.enforceable) {
                System.out.println("WS_BABY_YYYYYYYYYYYY");
            } else {
                System.out.println("WS_BABY_XXXXXXXXXXXXXXXXX");
                performClickUtils2 = new PerformClickUtils2();
                autoService = this.autoService;
                r7 = new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.executedBackHome(GroupSendGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                GroupSendGroupSearchUtils.this.executeMain();
                            }
                        });
                    }

                    public void nuFind() {
                    }
                };
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            if (this.circulateInnerSize > 0 || !MyApplication.enforceable) {
                System.out.println("WS_BABY_YYYYYYYYYYYY");
                initFirstChattingUI();
            } else {
                System.out.println("WS_BABY_XXXXXXXXXXXXXXXXX");
                new PerformClickUtils2().checkNilStringTwo(this.autoService, "请稍候", "正在发送中", SocializeConstants.CANCLE_RESULTCODE, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.executedBackHome(GroupSendGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                GroupSendGroupSearchUtils.this.executeMain();
                            }
                        });
                    }

                    public void nuFind() {
                    }
                });
            }
            throw th2;
        }
    }

    public void initData(OperationParameterModel operationParameterModel2) {
        this.sendTextCount = 10;
        this.sendResult = "";
        this.startIndex = 0;
        this.isWhile = true;
        this.jumpTime = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
        this.isFirstSelectImg = true;
        this.maxSendExecuteNum = 0;
        this.isFirstMaxSendExecuteNum = true;
        this.picCount = operationParameterModel2.getMaterialPicCount();
        this.sendGroupNum = 0;
        this.lastName = "";
        this.groupNames = "";
        this.groupList = new LinkedHashSet<>();
        this.originList = new LinkedHashSet<>();
        this.isEnd = false;
        this.isRemoveIndex = false;
        this.isRemoveRepeat = false;
        this.operationParameterModel = operationParameterModel2;
        this.spaceTime = operationParameterModel2.getSpaceTime();
        this.sayContent = operationParameterModel2.getSayContent();
        this.jumpGroupName = operationParameterModel2.getJumpGroupNames();
        this.localImgUrl = operationParameterModel2.getLocalImgUrl();
        this.startIndexFromUser = operationParameterModel2.getStartIndex();
        this.sendOrder = operationParameterModel2.getSendOrder();
        this.circulateMode = operationParameterModel2.getCirculateMode();
        this.circulateInnerSize = operationParameterModel2.getCirculateInnerSize();
        this.circulateOuterSize = operationParameterModel2.getCirculateOutSize();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initFirstChattingUI() {
        this.sendTextCount = 10;
        LogUtils.log("WS_BABY.ChattingUI_1");
        switch (this.operationParameterModel.getMessageSendType()) {
            case 0:
                initInputSayContent();
                return;
            case 1:
                new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                            PerformClickUtils.inputText(GroupSendGroupSearchUtils.this.autoService, "");
                            GroupSendGroupSearchUtils.this.sleep(200);
                            PerformClickUtils.findViewIdAndClick(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.send_add_id);
                        } else {
                            PerformClickUtils.findViewIdAndClick(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.send_add_id);
                        }
                        new PerformClickUtils2().checkStringsFromPhotos(GroupSendGroupSearchUtils.this.autoService, "相册", "你可能要发送的照片", GroupSendGroupSearchUtils.this.isFirstSelectImg ? SocializeConstants.CANCLE_RESULTCODE : 400, 200, 65, new PerformClickUtils2.OnCheckListener5() {
                            public void execute(int i) {
                                if (i == 15 && !PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                                    GroupSendGroupSearchUtils.this.autoService.performGlobalAction(1);
                                }
                            }

                            public void find(int i) {
                                boolean unused = GroupSendGroupSearchUtils.this.isFirstSelectImg = false;
                                switch (i) {
                                    case 0:
                                        PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendGroupSearchUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                        GroupSendGroupSearchUtils.this.initAlbumPreviewUI();
                                        return;
                                    case 1:
                                        GroupSendGroupSearchUtils.this.autoService.performGlobalAction(1);
                                        GroupSendGroupSearchUtils.this.sleep(350);
                                        if (PerformClickUtils.findNodeIsExistByText(GroupSendGroupSearchUtils.this.autoService, "相册")) {
                                            PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendGroupSearchUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                            GroupSendGroupSearchUtils.this.initAlbumPreviewUI();
                                            return;
                                        }
                                        if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                                            PerformClickUtils.inputText(GroupSendGroupSearchUtils.this.autoService, "");
                                        }
                                        GroupSendGroupSearchUtils.this.sleep(200);
                                        PerformClickUtils.findViewIdAndClick(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.send_add_id);
                                        GroupSendGroupSearchUtils.this.sleep(350);
                                        PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendGroupSearchUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                        GroupSendGroupSearchUtils.this.initAlbumPreviewUI();
                                        return;
                                    default:
                                        return;
                                }
                            }

                            public void nuFind() {
                            }
                        });
                    }

                    public void nuFind() {
                    }
                });
                return;
            case 2:
                if (this.sayContent != null && !this.sayContent.equals("")) {
                    new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            if (GroupSendGroupSearchUtils.this.sendOrder == 0) {
                                GroupSendGroupSearchUtils.this.sendImg();
                            } else {
                                GroupSendGroupSearchUtils.this.sendText();
                            }
                        }

                        public void nuFind() {
                        }
                    });
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void initInputSayContent() {
        new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                GroupSendGroupSearchUtils.this.sleep(50);
                if (PerformClickUtils.findNodeIsExistByText(GroupSendGroupSearchUtils.this.autoService, "按住 说话")) {
                    PerformClickUtils.findViewIdAndClick(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.voice_left_id);
                    GroupSendGroupSearchUtils.this.sleep(100);
                }
                new PerformClickUtils2().checkNodeIdSyn(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.input_edit_text_id, 300, 100, 300, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.findViewByIdAndPasteContent(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.input_edit_text_id, GroupSendGroupSearchUtils.this.sayContent);
                        new PerformClickUtils2().checkString(GroupSendGroupSearchUtils.this.autoService, "发送", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                PerformClickUtils.findTextAndClick(GroupSendGroupSearchUtils.this.autoService, "发送");
                                GroupSendGroupSearchUtils.access$2010(GroupSendGroupSearchUtils.this);
                                int circulateInnerSize = GroupSendGroupSearchUtils.this.operationParameterModel.getCirculateInnerSize();
                                LogUtils.log("WS_BABY_" + GroupSendGroupSearchUtils.this.circulateInnerSize + "@" + circulateInnerSize);
                                if (GroupSendGroupSearchUtils.this.circulateMode == 1 && circulateInnerSize > 1) {
                                    LogUtils.log("WS_BABY1_" + GroupSendGroupSearchUtils.this.circulateInnerSize + "@" + circulateInnerSize);
                                    if (GroupSendGroupSearchUtils.this.operationParameterModel.getSendCardType() == 0) {
                                        LogUtils.log("WS_BABY2_" + GroupSendGroupSearchUtils.this.circulateInnerSize + "@" + circulateInnerSize);
                                        MyWindowManager myWindowManager = GroupSendGroupSearchUtils.this.mMyManager;
                                        BaseServiceUtils.updateBottomText(myWindowManager, "从[全部群]中第 " + GroupSendGroupSearchUtils.this.startIndexFromUser + " 个开始群发消息\n已发送 " + GroupSendGroupSearchUtils.this.sendGroupNum + " 个群,剩余 " + GroupSendGroupSearchUtils.this.groupList.size() + " 个群\n已内循环 " + (circulateInnerSize - GroupSendGroupSearchUtils.this.circulateInnerSize) + "次");
                                    } else {
                                        LogUtils.log("WS_BABY3_" + GroupSendGroupSearchUtils.this.circulateInnerSize + "@" + circulateInnerSize);
                                        MyWindowManager myWindowManager2 = GroupSendGroupSearchUtils.this.mMyManager;
                                        BaseServiceUtils.updateBottomText(myWindowManager2, "从[部分群]中第 " + GroupSendGroupSearchUtils.this.startIndexFromUser + " 个开始群发消息\n已发送 " + GroupSendGroupSearchUtils.this.sendGroupNum + " 个群,剩余 " + GroupSendGroupSearchUtils.this.groupList.size() + " 个群\n已内循环 " + (circulateInnerSize - GroupSendGroupSearchUtils.this.circulateInnerSize) + "次");
                                    }
                                }
                                if (GroupSendGroupSearchUtils.this.circulateInnerSize > 0 || !MyApplication.enforceable) {
                                    GroupSendGroupSearchUtils.this.initInputSayContent();
                                    return;
                                }
                                GroupSendGroupSearchUtils.access$908(GroupSendGroupSearchUtils.this);
                                int access$900 = GroupSendGroupSearchUtils.this.startIndexFromUser == 1 ? GroupSendGroupSearchUtils.this.sendGroupNum + 1 : GroupSendGroupSearchUtils.this.sendGroupNum + GroupSendGroupSearchUtils.this.startIndexFromUser;
                                if (GroupSendGroupSearchUtils.this.operationParameterModel.getSendCardType() == 0) {
                                    SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_all", Integer.valueOf(access$900));
                                } else if (GroupSendGroupSearchUtils.this.operationParameterModel.getSendCardType() == 1) {
                                    SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_part", Integer.valueOf(access$900));
                                } else if (GroupSendGroupSearchUtils.this.operationParameterModel.getSendCardType() == 2) {
                                    SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_shield", Integer.valueOf(access$900));
                                }
                                int circulateOutSize = GroupSendGroupSearchUtils.this.operationParameterModel.getCirculateOutSize();
                                if (GroupSendGroupSearchUtils.this.circulateMode != 0 || circulateOutSize <= 1) {
                                    if (GroupSendGroupSearchUtils.this.operationParameterModel.getSendCardType() == 0) {
                                        MyWindowManager myWindowManager3 = GroupSendGroupSearchUtils.this.mMyManager;
                                        BaseServiceUtils.updateBottomText(myWindowManager3, "从[全部群]中第 " + GroupSendGroupSearchUtils.this.startIndexFromUser + " 个开始群发消息\n已发送 " + GroupSendGroupSearchUtils.this.sendGroupNum + " 个群,剩余 " + GroupSendGroupSearchUtils.this.groupList.size() + " 个群");
                                    } else {
                                        MyWindowManager myWindowManager4 = GroupSendGroupSearchUtils.this.mMyManager;
                                        BaseServiceUtils.updateBottomText(myWindowManager4, "从[部分群]中第 " + GroupSendGroupSearchUtils.this.startIndexFromUser + " 个开始群发消息\n已发送 " + GroupSendGroupSearchUtils.this.sendGroupNum + " 个群,剩余 " + GroupSendGroupSearchUtils.this.groupList.size() + " 个群");
                                    }
                                } else if (GroupSendGroupSearchUtils.this.operationParameterModel.getSendCardType() == 0) {
                                    MyWindowManager myWindowManager5 = GroupSendGroupSearchUtils.this.mMyManager;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("从[全部群]中第 ");
                                    sb.append(GroupSendGroupSearchUtils.this.startIndexFromUser);
                                    sb.append(" 个开始群发消息\n已发送 ");
                                    sb.append(GroupSendGroupSearchUtils.this.sendGroupNum);
                                    sb.append(" 个群,剩余 ");
                                    sb.append(GroupSendGroupSearchUtils.this.groupList.size());
                                    sb.append(" 个群\n正在执行第 ");
                                    if ((circulateOutSize - GroupSendGroupSearchUtils.this.circulateOuterSize) + 1 <= circulateOutSize) {
                                        circulateOutSize = (circulateOutSize - GroupSendGroupSearchUtils.this.circulateOuterSize) + 1;
                                    }
                                    sb.append(circulateOutSize);
                                    sb.append(" 轮群发");
                                    BaseServiceUtils.updateBottomText(myWindowManager5, sb.toString());
                                } else {
                                    MyWindowManager myWindowManager6 = GroupSendGroupSearchUtils.this.mMyManager;
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("从[部分群]中第 ");
                                    sb2.append(GroupSendGroupSearchUtils.this.startIndexFromUser);
                                    sb2.append(" 个开始群发消息\n已发送 ");
                                    sb2.append(GroupSendGroupSearchUtils.this.sendGroupNum);
                                    sb2.append(" 个群,剩余 ");
                                    sb2.append(GroupSendGroupSearchUtils.this.groupList.size());
                                    sb2.append(" 个群\n正在执行第 ");
                                    if ((circulateOutSize - GroupSendGroupSearchUtils.this.circulateOuterSize) + 1 <= circulateOutSize) {
                                        circulateOutSize = (circulateOutSize - GroupSendGroupSearchUtils.this.circulateOuterSize) + 1;
                                    }
                                    sb2.append(circulateOutSize);
                                    sb2.append(" 轮群发");
                                    BaseServiceUtils.updateBottomText(myWindowManager6, sb2.toString());
                                }
                                PerformClickUtils.executedBackHome(GroupSendGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        GroupSendGroupSearchUtils.this.executeMain();
                                    }
                                });
                            }

                            public void nuFind() {
                            }
                        });
                    }

                    public void nuFind() {
                    }
                });
            }

            public void nuFind() {
            }
        });
    }

    public void initRoomContact() {
        MyWindowManager myWindowManager = this.mMyManager;
        updateBottomText(myWindowManager, "正在群发消息,请不要操作微信\n已发送 " + this.sendGroupNum + " 个群,剩余 " + this.groupList.size() + " 个群");
        LogUtils.log("WS_BABY.ChatroomContactUI_2");
        new PerformClickUtils2().check(this.autoService, nav_search_id, this.jumpTime, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findNodeByIdFirstAndClick(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.nav_search_id);
                GroupSendGroupSearchUtils.this.sleep(GroupSendGroupSearchUtils.this.jumpTime / 2);
                String unused = GroupSendGroupSearchUtils.this.groupNames = "";
                Iterator it = GroupSendGroupSearchUtils.this.groupList.iterator();
                if (it != null && it.hasNext()) {
                    String unused2 = GroupSendGroupSearchUtils.this.groupNames = (String) it.next();
                }
                String substring = GroupSendGroupSearchUtils.this.groupNames.length() > 26 ? GroupSendGroupSearchUtils.this.groupNames.substring(0, 25) : GroupSendGroupSearchUtils.this.groupNames;
                for (int i2 = 0; i2 < 10 && substring.startsWith("@"); i2++) {
                    substring = substring.substring(1);
                }
                PerformClickUtils.inputText(GroupSendGroupSearchUtils.this.autoService, substring);
                new PerformClickUtils2().check(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.group_list_item_name_id, GroupSendGroupSearchUtils.this.groupNames, 1200, 100, 25, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    /* JADX WARNING: Removed duplicated region for block: B:87:0x0269 A[LOOP:0: B:32:0x009f->B:87:0x0269, LOOP_END] */
                    /* JADX WARNING: Removed duplicated region for block: B:91:0x0106 A[SYNTHETIC] */
                    public void find(int i) {
                        boolean z;
                        String str;
                        AccessibilityNodeInfo rootInActiveWindow = GroupSendGroupSearchUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow != null && MyApplication.enforceable) {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
                            if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && GroupSendGroupSearchUtils.this.groupList != null && MyApplication.enforceable) {
                                try {
                                    Iterator it = GroupSendGroupSearchUtils.this.groupList.iterator();
                                    if (it.hasNext()) {
                                        GroupSendGroupSearchUtils.this.groupList.remove(it.next());
                                        if (GroupSendGroupSearchUtils.this.groupList.size() != 0 || !MyApplication.enforceable) {
                                            boolean unused = GroupSendGroupSearchUtils.this.isEnd = false;
                                        } else if (GroupSendGroupSearchUtils.this.circulateMode != 0 || !MyApplication.enforceable) {
                                            boolean unused2 = GroupSendGroupSearchUtils.this.isEnd = true;
                                        } else {
                                            GroupSendGroupSearchUtils.access$710(GroupSendGroupSearchUtils.this);
                                            if (GroupSendGroupSearchUtils.this.circulateOuterSize > 0 || !MyApplication.enforceable) {
                                                int unused3 = GroupSendGroupSearchUtils.this.sendGroupNum = -1;
                                                GroupSendGroupSearchUtils.this.groupList.addAll(GroupSendGroupSearchUtils.this.originList);
                                            } else {
                                                boolean unused4 = GroupSendGroupSearchUtils.this.isEnd = true;
                                            }
                                        }
                                        if (findAccessibilityNodeInfosByViewId != null) {
                                            LogUtils.log("WS_BABY.ChatroomContactUI_3");
                                            if (findAccessibilityNodeInfosByViewId.size() > 1) {
                                                LogUtils.log("WS_BABY.ChatroomContactUI_4");
                                                int i2 = 0;
                                                while (true) {
                                                    if (i2 >= findAccessibilityNodeInfosByViewId.size()) {
                                                        z = false;
                                                        break;
                                                    }
                                                    AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i2);
                                                    if (accessibilityNodeInfo != null) {
                                                        if (accessibilityNodeInfo.getText() != null) {
                                                            str = accessibilityNodeInfo.getText() + "";
                                                            LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendGroupSearchUtils.this.groupNames);
                                                            if (str == null && "".equals(str) && !str.equals(GroupSendGroupSearchUtils.this.groupNames)) {
                                                                LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendGroupSearchUtils.this.groupNames + "@@@@");
                                                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(i2));
                                                                GroupSendGroupSearchUtils.this.initFirstChattingUI();
                                                                z = true;
                                                                break;
                                                            }
                                                            i2++;
                                                        }
                                                    }
                                                    str = "";
                                                    LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendGroupSearchUtils.this.groupNames);
                                                    if (str == null && "".equals(str) && !str.equals(GroupSendGroupSearchUtils.this.groupNames)) {
                                                    }
                                                }
                                                if (!z) {
                                                    LogUtils.log("WS_BABY.ChatroomContactUI_55");
                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                    GroupSendGroupSearchUtils.this.initFirstChattingUI();
                                                }
                                            } else if (findAccessibilityNodeInfosByViewId.size() == 1) {
                                                LogUtils.log("WS_BABY.ChatroomContactUI_6");
                                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                GroupSendGroupSearchUtils.this.initFirstChattingUI();
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                }
                            } else if (GroupSendGroupSearchUtils.this.groupList != null && GroupSendGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                                try {
                                    Iterator it2 = GroupSendGroupSearchUtils.this.groupList.iterator();
                                    if (it2.hasNext()) {
                                        GroupSendGroupSearchUtils.this.groupList.remove(it2.next());
                                        if (GroupSendGroupSearchUtils.this.groupList.size() > 0 || !MyApplication.enforceable) {
                                            boolean unused5 = GroupSendGroupSearchUtils.this.isEnd = false;
                                        } else if (GroupSendGroupSearchUtils.this.circulateMode != 0 || !MyApplication.enforceable) {
                                            boolean unused6 = GroupSendGroupSearchUtils.this.isEnd = true;
                                        } else {
                                            GroupSendGroupSearchUtils.access$710(GroupSendGroupSearchUtils.this);
                                            if (GroupSendGroupSearchUtils.this.circulateOuterSize > 0 || !MyApplication.enforceable) {
                                                int unused7 = GroupSendGroupSearchUtils.this.sendGroupNum = -1;
                                                GroupSendGroupSearchUtils.this.groupList.addAll(GroupSendGroupSearchUtils.this.originList);
                                            } else {
                                                boolean unused8 = GroupSendGroupSearchUtils.this.isEnd = true;
                                            }
                                        }
                                        PerformClickUtils.executedBackHome(GroupSendGroupSearchUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                            public void find() {
                                                GroupSendGroupSearchUtils.this.executeMain();
                                            }
                                        });
                                    }
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                    }

                    public void nuFind() {
                        GroupSendGroupSearchUtils.this.saveIndex(GroupSendGroupSearchUtils.this.operationParameterModel.getMessageSendType());
                        if (GroupSendGroupSearchUtils.this.groupList != null && GroupSendGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                            try {
                                Iterator it = GroupSendGroupSearchUtils.this.groupList.iterator();
                                if (it.hasNext()) {
                                    GroupSendGroupSearchUtils.this.groupList.remove(it.next());
                                    if (GroupSendGroupSearchUtils.this.groupList.size() > 0) {
                                        boolean unused = GroupSendGroupSearchUtils.this.isEnd = false;
                                    } else if (GroupSendGroupSearchUtils.this.circulateMode != 0 || !MyApplication.enforceable) {
                                        boolean unused2 = GroupSendGroupSearchUtils.this.isEnd = true;
                                    } else {
                                        GroupSendGroupSearchUtils.access$710(GroupSendGroupSearchUtils.this);
                                        if (GroupSendGroupSearchUtils.this.circulateOuterSize > 0 || !MyApplication.enforceable) {
                                            int unused3 = GroupSendGroupSearchUtils.this.sendGroupNum = -1;
                                            GroupSendGroupSearchUtils.this.groupList.addAll(GroupSendGroupSearchUtils.this.originList);
                                        } else {
                                            boolean unused4 = GroupSendGroupSearchUtils.this.isEnd = true;
                                        }
                                    }
                                    PerformClickUtils.executedBackHome(GroupSendGroupSearchUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            GroupSendGroupSearchUtils.this.executeMain();
                                        }
                                    });
                                }
                            } catch (Exception e) {
                            }
                        }
                    }
                });
            }

            public void nuFind() {
            }
        });
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        if (this.sendResult == null || "".equals(this.sendResult)) {
            MyWindowManager myWindowManager = this.mMyManager;
            updateMiddleText(myWindowManager, "群发消息", "发送了" + this.sendGroupNum + "个群");
            return;
        }
        updateMiddleText(this.mMyManager, "群发结束", this.sendResult);
    }

    public void saveIndex(int i) {
        this.sendGroupNum++;
        int i2 = this.startIndexFromUser == 1 ? this.sendGroupNum + 1 : this.sendGroupNum + this.startIndexFromUser;
        if (i == 0) {
            if (this.operationParameterModel.getSendCardType() == 0) {
                SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_all", Integer.valueOf(i2));
            } else if (this.operationParameterModel.getSendCardType() == 1) {
                SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_part", Integer.valueOf(i2));
            } else if (this.operationParameterModel.getSendCardType() == 2) {
                SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_shield", Integer.valueOf(i2));
            }
        } else if (i == 1) {
            if (this.operationParameterModel.getSendCardType() == 0) {
                SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_all", Integer.valueOf(i2));
            } else if (this.operationParameterModel.getSendCardType() == 1) {
                SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_part", Integer.valueOf(i2));
            } else if (this.operationParameterModel.getSendCardType() == 2) {
                SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_shield", Integer.valueOf(i2));
            }
        } else if (i != 2) {
        } else {
            if (this.operationParameterModel.getSendCardType() == 0) {
                SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_all", Integer.valueOf(i2));
            } else if (this.operationParameterModel.getSendCardType() == 1) {
                SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_part", Integer.valueOf(i2));
            } else if (this.operationParameterModel.getSendCardType() == 2) {
                SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_shield", Integer.valueOf(i2));
            }
        }
    }

    public void sendImg() {
        if (this.localImgUrl != null && !this.localImgUrl.equals("") && MyApplication.enforceable) {
            if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) this.autoService, send_add_id)) {
                PerformClickUtils.inputText(this.autoService, "");
                sleep(200);
                PerformClickUtils.findViewIdAndClick(this.autoService, send_add_id);
            } else {
                PerformClickUtils.findViewIdAndClick(this.autoService, send_add_id);
            }
            new PerformClickUtils2().checkStringsFromPhotos(this.autoService, "相册", "你可能要发送的照片", this.isFirstSelectImg ? SocializeConstants.CANCLE_RESULTCODE : 400, 200, 65, new PerformClickUtils2.OnCheckListener5() {
                public void execute(int i) {
                    if (i == 15 && !PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                        GroupSendGroupSearchUtils.this.autoService.performGlobalAction(1);
                    }
                }

                public void find(int i) {
                    boolean unused = GroupSendGroupSearchUtils.this.isFirstSelectImg = false;
                    switch (i) {
                        case 0:
                            PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendGroupSearchUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                            GroupSendGroupSearchUtils.this.initAlbumPreviewUI();
                            return;
                        case 1:
                            GroupSendGroupSearchUtils.this.autoService.performGlobalAction(1);
                            GroupSendGroupSearchUtils.this.sleep(350);
                            if (PerformClickUtils.findNodeIsExistByText(GroupSendGroupSearchUtils.this.autoService, "相册")) {
                                GroupSendGroupSearchUtils.this.sleep(100);
                                PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendGroupSearchUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                GroupSendGroupSearchUtils.this.initAlbumPreviewUI();
                                return;
                            }
                            if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                                PerformClickUtils.inputText(GroupSendGroupSearchUtils.this.autoService, "");
                            }
                            GroupSendGroupSearchUtils.this.sleep(200);
                            PerformClickUtils.findViewIdAndClick(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.send_add_id);
                            GroupSendGroupSearchUtils.this.sleep(350);
                            PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendGroupSearchUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                            GroupSendGroupSearchUtils.this.initAlbumPreviewUI();
                            return;
                        default:
                            return;
                    }
                }

                public void nuFind() {
                }
            });
        }
    }

    public void sendText() {
        new PerformClickUtils2().check(this.autoService, voice_left_id, this.sendOrder == 0 ? SocializeConstants.CANCLE_RESULTCODE : 100, 100, 1200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                GroupSendGroupSearchUtils.this.sleep(50);
                if (PerformClickUtils.findNodeIsExistByText(GroupSendGroupSearchUtils.this.autoService, "按住 说话")) {
                    PerformClickUtils.findViewIdAndClick(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.voice_left_id);
                    GroupSendGroupSearchUtils.this.sleep(100);
                }
                new PerformClickUtils2().checkNodeIdSyn(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.input_edit_text_id, 300, 100, 10, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.findViewByIdAndPasteContent(GroupSendGroupSearchUtils.this.autoService, BaseServiceUtils.input_edit_text_id, GroupSendGroupSearchUtils.this.sayContent);
                        new PerformClickUtils2().checkString(GroupSendGroupSearchUtils.this.autoService, "发送", 100, 100, 10, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                PerformClickUtils.findTextAndClick(GroupSendGroupSearchUtils.this.autoService, "发送");
                                if (GroupSendGroupSearchUtils.this.operationParameterModel.getMessageSendType() == 0 || GroupSendGroupSearchUtils.this.sendOrder == 0) {
                                    GroupSendGroupSearchUtils.this.sleep(100);
                                    GroupSendGroupSearchUtils.this.initChattingBackUI();
                                    return;
                                }
                                GroupSendGroupSearchUtils.this.sendImg();
                            }

                            public void nuFind() {
                                GroupSendGroupSearchUtils.access$2210(GroupSendGroupSearchUtils.this);
                                GroupSendGroupSearchUtils.this.sendText();
                            }
                        });
                    }

                    public void nuFind() {
                        GroupSendGroupSearchUtils.access$2210(GroupSendGroupSearchUtils.this);
                        GroupSendGroupSearchUtils.this.sendText();
                    }
                });
            }

            public void nuFind() {
            }
        });
    }
}
