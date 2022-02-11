package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
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

public class GroupSendCardSearchUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static GroupSendCardSearchUtils instance;
    /* access modifiers changed from: private */
    public String cardName = "";
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
    /* access modifiers changed from: private */
    public boolean isFirstMaxSendExecuteNum = true;
    /* access modifiers changed from: private */
    public boolean isRemoveIndex = false;
    /* access modifiers changed from: private */
    public boolean isRemoveRepeat = false;
    /* access modifiers changed from: private */
    public boolean isWhile = true;
    /* access modifiers changed from: private */
    public String jumpGroupName = "";
    /* access modifiers changed from: private */
    public int jumpTime = 300;
    /* access modifiers changed from: private */
    public String lastName;
    /* access modifiers changed from: private */
    public int maxSendExecuteNum = 0;
    /* access modifiers changed from: private */
    public OperationParameterModel operationParameterModel;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> originList = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public String sayContent = "";
    /* access modifiers changed from: private */
    public int sendGroupNum = 0;
    /* access modifiers changed from: private */
    public String sendResult = "";
    private int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startIndexFromUser = 1;

    private GroupSendCardSearchUtils() {
    }

    static /* synthetic */ int access$410(GroupSendCardSearchUtils groupSendCardSearchUtils) {
        int i = groupSendCardSearchUtils.circulateOuterSize;
        groupSendCardSearchUtils.circulateOuterSize = i - 1;
        return i;
    }

    static /* synthetic */ int access$608(GroupSendCardSearchUtils groupSendCardSearchUtils) {
        int i = groupSendCardSearchUtils.sendGroupNum;
        groupSendCardSearchUtils.sendGroupNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$908(GroupSendCardSearchUtils groupSendCardSearchUtils) {
        int i = groupSendCardSearchUtils.startIndex;
        groupSendCardSearchUtils.startIndex = i + 1;
        return i;
    }

    public static GroupSendCardSearchUtils getInstance() {
        instance = new GroupSendCardSearchUtils();
        return instance;
    }

    public void clickCard() {
        PerformClickUtils.findViewIdAndClick(this.autoService, send_add_id);
        new PerformClickUtils2().checkCardCollectionName(this.autoService, send_add_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 30, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY.ChattingUI_8");
                PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendCardSearchUtils.this.autoService, "名片", BaseServiceUtils.album_id);
                GroupSendCardSearchUtils.this.initSelectContactUI();
            }

            public void nuFind() {
            }
        });
    }

    public void endViewDismiss() {
        try {
            this.mMyManager.stopAccessibilityService();
            this.mMyManager.removeBottomView();
            this.mMyManager.showMiddleView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewShow() {
        try {
            if (this.operationParameterModel != null) {
                int circulateOutSize = this.operationParameterModel.getCirculateOutSize();
                if (this.circulateMode != 0 || circulateOutSize <= 1) {
                    if (this.operationParameterModel.getSendCardType() == 0) {
                        MyWindowManager myWindowManager = this.mMyManager;
                        showBottomView(myWindowManager, "正在发送名片[" + this.cardName + "]\n从第 " + this.startIndexFromUser + " 个微信群开始群发");
                    } else if (this.operationParameterModel.getSendCardType() == 1) {
                        MyWindowManager myWindowManager2 = this.mMyManager;
                        showBottomView(myWindowManager2, "正在发送名片[" + this.cardName + "]\n从部分群中第 " + this.startIndexFromUser + " 个微信群开始群发");
                    } else if (this.operationParameterModel.getSendCardType() == 2) {
                        MyWindowManager myWindowManager3 = this.mMyManager;
                        showBottomView(myWindowManager3, "正在发送名片[" + this.cardName + "]\n从部分群中第 " + this.startIndexFromUser + " 个微信群开始群发");
                    }
                } else if (this.operationParameterModel.getSendCardType() == 0) {
                    MyWindowManager myWindowManager4 = this.mMyManager;
                    StringBuilder sb = new StringBuilder();
                    sb.append("正在向[全部群]发送名片[");
                    sb.append(this.cardName);
                    sb.append("]\n从第");
                    sb.append(this.startIndexFromUser);
                    sb.append("个微信群开始群发\n正在执行第");
                    if ((circulateOutSize - this.circulateOuterSize) + 1 <= circulateOutSize) {
                        circulateOutSize = (circulateOutSize - this.circulateOuterSize) + 1;
                    }
                    sb.append(circulateOutSize);
                    sb.append("轮群发");
                    showBottomView(myWindowManager4, sb.toString());
                } else if (this.operationParameterModel.getSendCardType() == 1) {
                    MyWindowManager myWindowManager5 = this.mMyManager;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("正在向[部分群]发送名片[");
                    sb2.append(this.cardName);
                    sb2.append("]\n从部分群中第");
                    sb2.append(this.startIndexFromUser);
                    sb2.append("个微信群开始群发\n正在执行第");
                    if ((circulateOutSize - this.circulateOuterSize) + 1 <= circulateOutSize) {
                        circulateOutSize = (circulateOutSize - this.circulateOuterSize) + 1;
                    }
                    sb2.append(circulateOutSize);
                    sb2.append("轮群发");
                    showBottomView(myWindowManager5, sb2.toString());
                } else if (this.operationParameterModel.getSendCardType() == 2) {
                    MyWindowManager myWindowManager6 = this.mMyManager;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("正在向[部分群]发送名片[");
                    sb3.append(this.cardName);
                    sb3.append("]\n从部分群中第");
                    sb3.append(this.startIndexFromUser);
                    sb3.append("个微信群开始群发\n正在执行第");
                    if ((circulateOutSize - this.circulateOuterSize) + 1 <= circulateOutSize) {
                        circulateOutSize = (circulateOutSize - this.circulateOuterSize) + 1;
                    }
                    sb3.append(circulateOutSize);
                    sb3.append("轮群发");
                    showBottomView(myWindowManager6, sb3.toString());
                }
            } else {
                showBottomView(this.mMyManager, "正在向微信群群发名片\n请不要操作微信");
            }
            new Thread(new Runnable() {
                public void run() {
                    try {
                        GroupSendCardSearchUtils.this.executeMain();
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
            LogUtils.log("WS_BABY.LauncherUI");
            new PerformClickUtils2().checkString(this.autoService, "通讯录", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findTextAndClick(GroupSendCardSearchUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(GroupSendCardSearchUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(GroupSendCardSearchUtils.this.autoService, "通讯录");
                    new PerformClickUtils2().checkString(GroupSendCardSearchUtils.this.autoService, "群聊", BaseServiceUtils.executeSpeedSetting + 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(GroupSendCardSearchUtils.this.autoService, "群聊");
                            GroupSendCardSearchUtils.this.initChatRoomContactUI();
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

    public void initBackChattingUI() {
        this.circulateInnerSize--;
        if (this.circulateInnerSize == 0 || !MyApplication.enforceable) {
            PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                public void find() {
                    LogUtils.log("WS_BABY.ChattingUI_BACK");
                    GroupSendCardSearchUtils.this.executeMain();
                }
            });
            return;
        }
        LogUtils.log("WS_BABY.ChattingUI_10");
        initFirstChattingUI();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ec, code lost:
        com.wx.assistants.utils.LogUtils.log("WS_BABY.st.3");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    public void initChatRoomContactUI() {
        this.circulateMode = this.operationParameterModel.getCirculateMode();
        this.circulateInnerSize = this.operationParameterModel.getCirculateInnerSize();
        if (this.groupList == null || this.groupList.size() <= 0 || !MyApplication.enforceable) {
            LogUtils.log("WS_BABY.ChatroomContactUI_1");
            if (this.isEnd || !MyApplication.enforceable) {
                removeEndView(this.mMyManager);
            } else if (this.operationParameterModel.getSendCardType() == 1) {
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
                    new PerformClickUtils2().check(this.autoService, nav_search_id, this.jumpTime, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findNodeByIdFirstAndClick(GroupSendCardSearchUtils.this.autoService, BaseServiceUtils.nav_search_id);
                            GroupSendCardSearchUtils.this.sleep(GroupSendCardSearchUtils.this.jumpTime);
                            String unused = GroupSendCardSearchUtils.this.groupNames = "";
                            Iterator it = GroupSendCardSearchUtils.this.groupList.iterator();
                            if (it != null && it.hasNext()) {
                                String unused2 = GroupSendCardSearchUtils.this.groupNames = (String) it.next();
                            }
                            String substring = GroupSendCardSearchUtils.this.groupNames.length() > 26 ? GroupSendCardSearchUtils.this.groupNames.substring(0, 25) : GroupSendCardSearchUtils.this.groupNames;
                            for (int i2 = 0; i2 < 10 && substring.startsWith("@"); i2++) {
                                substring = substring.substring(1);
                            }
                            PerformClickUtils.inputText(GroupSendCardSearchUtils.this.autoService, substring);
                            new PerformClickUtils2().check(GroupSendCardSearchUtils.this.autoService, BaseServiceUtils.group_list_item_name_id, GroupSendCardSearchUtils.this.groupNames, 1200, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    new Thread(new Runnable() {
                                        /* JADX WARNING: Removed duplicated region for block: B:69:0x028e A[LOOP:0: B:23:0x0095->B:69:0x028e, LOOP_END] */
                                        /* JADX WARNING: Removed duplicated region for block: B:73:0x0100 A[SYNTHETIC] */
                                        public void run() {
                                            boolean z;
                                            String str;
                                            try {
                                                AccessibilityNodeInfo rootInActiveWindow = GroupSendCardSearchUtils.this.autoService.getRootInActiveWindow();
                                                if (rootInActiveWindow != null) {
                                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
                                                    if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                                        Iterator it = GroupSendCardSearchUtils.this.groupList.iterator();
                                                        if (it.hasNext()) {
                                                            GroupSendCardSearchUtils.this.groupList.remove(it.next());
                                                            if (GroupSendCardSearchUtils.this.groupList.size() != 0) {
                                                                boolean unused = GroupSendCardSearchUtils.this.isEnd = false;
                                                            } else if (GroupSendCardSearchUtils.this.circulateMode == 0) {
                                                                GroupSendCardSearchUtils.access$410(GroupSendCardSearchUtils.this);
                                                                if (GroupSendCardSearchUtils.this.circulateOuterSize <= 0) {
                                                                    boolean unused2 = GroupSendCardSearchUtils.this.isEnd = true;
                                                                } else {
                                                                    int unused3 = GroupSendCardSearchUtils.this.sendGroupNum = -1;
                                                                    GroupSendCardSearchUtils.this.groupList.addAll(GroupSendCardSearchUtils.this.originList);
                                                                }
                                                            } else {
                                                                boolean unused4 = GroupSendCardSearchUtils.this.isEnd = true;
                                                            }
                                                            if (findAccessibilityNodeInfosByViewId != null) {
                                                                LogUtils.log("WS_BABY.ChatroomContactUI_3");
                                                                if (findAccessibilityNodeInfosByViewId.size() > 1) {
                                                                    LogUtils.log("WS_BABY.ChatroomContactUI_4");
                                                                    int i = 0;
                                                                    while (true) {
                                                                        if (i >= findAccessibilityNodeInfosByViewId.size()) {
                                                                            z = false;
                                                                            break;
                                                                        }
                                                                        AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i);
                                                                        if (accessibilityNodeInfo != null) {
                                                                            if (accessibilityNodeInfo.getText() != null) {
                                                                                str = accessibilityNodeInfo.getText() + "";
                                                                                LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendCardSearchUtils.this.groupNames);
                                                                                if (str == null && "".equals(str) && !str.equals(GroupSendCardSearchUtils.this.groupNames)) {
                                                                                    LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendCardSearchUtils.this.groupNames + "@@@@");
                                                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(i));
                                                                                    GroupSendCardSearchUtils.this.initFirstChattingUI();
                                                                                    z = true;
                                                                                    break;
                                                                                }
                                                                                i++;
                                                                            }
                                                                        }
                                                                        str = "";
                                                                        LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendCardSearchUtils.this.groupNames);
                                                                        if (str == null && "".equals(str) && !str.equals(GroupSendCardSearchUtils.this.groupNames)) {
                                                                        }
                                                                    }
                                                                    if (!z) {
                                                                        LogUtils.log("WS_BABY.ChatroomContactUI_55");
                                                                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                                        GroupSendCardSearchUtils.this.initFirstChattingUI();
                                                                    }
                                                                } else if (findAccessibilityNodeInfosByViewId.size() == 1) {
                                                                    LogUtils.log("WS_BABY.ChatroomContactUI_6");
                                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                                    GroupSendCardSearchUtils.this.initFirstChattingUI();
                                                                }
                                                            }
                                                        }
                                                    } else if (GroupSendCardSearchUtils.this.groupList == null || GroupSendCardSearchUtils.this.groupList.size() <= 0 || !MyApplication.enforceable) {
                                                        BaseServiceUtils.removeEndView(GroupSendCardSearchUtils.this.mMyManager);
                                                    } else {
                                                        Iterator it2 = GroupSendCardSearchUtils.this.groupList.iterator();
                                                        if (it2.hasNext()) {
                                                            GroupSendCardSearchUtils.this.groupList.remove(it2.next());
                                                            if (GroupSendCardSearchUtils.this.groupList.size() > 0) {
                                                                boolean unused5 = GroupSendCardSearchUtils.this.isEnd = false;
                                                            } else if (GroupSendCardSearchUtils.this.circulateMode == 0) {
                                                                GroupSendCardSearchUtils.access$410(GroupSendCardSearchUtils.this);
                                                                if (GroupSendCardSearchUtils.this.circulateOuterSize <= 0) {
                                                                    boolean unused6 = GroupSendCardSearchUtils.this.isEnd = true;
                                                                } else {
                                                                    int unused7 = GroupSendCardSearchUtils.this.sendGroupNum = -1;
                                                                    GroupSendCardSearchUtils.this.groupList.addAll(GroupSendCardSearchUtils.this.originList);
                                                                }
                                                            } else {
                                                                boolean unused8 = GroupSendCardSearchUtils.this.isEnd = true;
                                                            }
                                                            PerformClickUtils.executedBackHome(GroupSendCardSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                                public void find() {
                                                                    GroupSendCardSearchUtils.this.executeMain();
                                                                }
                                                            });
                                                        }
                                                    }
                                                }
                                            } catch (Exception e) {
                                            }
                                        }
                                    }).start();
                                }

                                public void nuFind() {
                                    GroupSendCardSearchUtils.this.saveIndex();
                                    if (GroupSendCardSearchUtils.this.groupList == null || GroupSendCardSearchUtils.this.groupList.size() <= 0 || !MyApplication.enforceable) {
                                        BaseServiceUtils.removeEndView(GroupSendCardSearchUtils.this.mMyManager);
                                    } else {
                                        new Thread(new Runnable() {
                                            public void run() {
                                                try {
                                                    Iterator it = GroupSendCardSearchUtils.this.groupList.iterator();
                                                    if (it.hasNext()) {
                                                        GroupSendCardSearchUtils.this.groupList.remove(it.next());
                                                        if (GroupSendCardSearchUtils.this.groupList.size() > 0) {
                                                            boolean unused = GroupSendCardSearchUtils.this.isEnd = false;
                                                        } else if (GroupSendCardSearchUtils.this.circulateMode == 0) {
                                                            GroupSendCardSearchUtils.access$410(GroupSendCardSearchUtils.this);
                                                            if (GroupSendCardSearchUtils.this.circulateOuterSize <= 0) {
                                                                boolean unused2 = GroupSendCardSearchUtils.this.isEnd = true;
                                                            } else {
                                                                int unused3 = GroupSendCardSearchUtils.this.sendGroupNum = -1;
                                                                GroupSendCardSearchUtils.this.groupList.addAll(GroupSendCardSearchUtils.this.originList);
                                                            }
                                                        } else {
                                                            boolean unused4 = GroupSendCardSearchUtils.this.isEnd = true;
                                                        }
                                                        PerformClickUtils.executedBackHome(GroupSendCardSearchUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                                            public void find() {
                                                                GroupSendCardSearchUtils.this.executeMain();
                                                            }
                                                        });
                                                    }
                                                } catch (Exception e) {
                                                }
                                            }
                                        }).start();
                                    }
                                }
                            });
                        }

                        public void nuFind() {
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                new Thread(new Runnable() {
                    public void run() {
                        AccessibilityNodeInfo accessibilityNodeInfo;
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                        AccessibilityNodeInfo accessibilityNodeInfo2;
                        try {
                            GroupSendCardSearchUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                            while (GroupSendCardSearchUtils.this.isWhile && MyApplication.enforceable) {
                                AccessibilityNodeInfo rootInActiveWindow = GroupSendCardSearchUtils.this.autoService.getRootInActiveWindow();
                                if (rootInActiveWindow != null && MyApplication.enforceable) {
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.grout_friend_list_id);
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
                                    if (findAccessibilityNodeInfosByViewId3 != null && !findAccessibilityNodeInfosByViewId3.isEmpty() && MyApplication.enforceable) {
                                        if (GroupSendCardSearchUtils.this.startIndex < findAccessibilityNodeInfosByViewId3.size() && MyApplication.enforceable) {
                                            AccessibilityNodeInfo accessibilityNodeInfo3 = findAccessibilityNodeInfosByViewId3.get(GroupSendCardSearchUtils.this.startIndex);
                                            if (accessibilityNodeInfo3 != null) {
                                                if (accessibilityNodeInfo3.getText() != null) {
                                                    String unused = GroupSendCardSearchUtils.this.lastName = accessibilityNodeInfo3.getText() + "";
                                                }
                                                if (GroupSendCardSearchUtils.this.groupList.contains(GroupSendCardSearchUtils.this.lastName)) {
                                                    GroupSendCardSearchUtils.access$908(GroupSendCardSearchUtils.this);
                                                } else {
                                                    GroupSendCardSearchUtils.this.groupList.add(GroupSendCardSearchUtils.this.lastName);
                                                    GroupSendCardSearchUtils.access$908(GroupSendCardSearchUtils.this);
                                                }
                                            }
                                        } else if (GroupSendCardSearchUtils.this.startIndex >= findAccessibilityNodeInfosByViewId3.size() && MyApplication.enforceable && findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId2.get(0)) != null && MyApplication.enforceable) {
                                            PerformClickUtils.scroll(accessibilityNodeInfo);
                                            new PerformClickUtils2().checkSyn2(GroupSendCardSearchUtils.this.autoService, BaseServiceUtils.grout_friend_list_id, BaseServiceUtils.group_list_item_name_id, 0, 10, 100, new PerformClickUtils2.OnCheckListener() {
                                                public void find(int i) {
                                                    GroupSendCardSearchUtils.this.sleep(100);
                                                    PrintStream printStream = System.out;
                                                    printStream.println("WS_BABY_TIME.end2 = " + System.currentTimeMillis());
                                                }

                                                public void nuFind() {
                                                }
                                            });
                                            AccessibilityNodeInfo rootInActiveWindow2 = GroupSendCardSearchUtils.this.autoService.getRootInActiveWindow();
                                            if (rootInActiveWindow2 != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1)) != null && MyApplication.enforceable) {
                                                if ((accessibilityNodeInfo2.getText() + "").equals(GroupSendCardSearchUtils.this.lastName)) {
                                                    boolean unused2 = GroupSendCardSearchUtils.this.isWhile = false;
                                                }
                                                int unused3 = GroupSendCardSearchUtils.this.startIndex = 0;
                                            }
                                        }
                                    }
                                }
                            }
                            if (!GroupSendCardSearchUtils.this.isWhile && MyApplication.enforceable) {
                                if (GroupSendCardSearchUtils.this.groupList != null && GroupSendCardSearchUtils.this.groupList.size() > 0 && GroupSendCardSearchUtils.this.operationParameterModel.getSendCardType() == 2 && MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY.TYPE#2#jumpGroup#" + GroupSendCardSearchUtils.this.jumpGroupName);
                                    if (!GroupSendCardSearchUtils.this.isRemoveRepeat) {
                                        if (GroupSendCardSearchUtils.this.jumpGroupName.contains(";")) {
                                            String[] split = GroupSendCardSearchUtils.this.jumpGroupName.split(";");
                                            for (String remove : split) {
                                                GroupSendCardSearchUtils.this.groupList.remove(remove);
                                            }
                                        } else {
                                            GroupSendCardSearchUtils.this.groupList.remove(GroupSendCardSearchUtils.this.jumpGroupName);
                                        }
                                        boolean unused4 = GroupSendCardSearchUtils.this.isRemoveRepeat = true;
                                    }
                                }
                                if (!GroupSendCardSearchUtils.this.isRemoveIndex && GroupSendCardSearchUtils.this.startIndexFromUser > 1) {
                                    if (GroupSendCardSearchUtils.this.groupList.size() >= GroupSendCardSearchUtils.this.startIndexFromUser) {
                                        for (int i = 1; i < GroupSendCardSearchUtils.this.startIndexFromUser; i++) {
                                            Iterator it = GroupSendCardSearchUtils.this.groupList.iterator();
                                            if (it.hasNext()) {
                                                GroupSendCardSearchUtils.this.groupList.remove(it.next());
                                            }
                                        }
                                        boolean unused5 = GroupSendCardSearchUtils.this.isRemoveIndex = true;
                                    } else {
                                        BaseServiceUtils.removeEndView(GroupSendCardSearchUtils.this.mMyManager);
                                        GroupSendCardSearchUtils.this.groupList.clear();
                                        String unused6 = GroupSendCardSearchUtils.this.sendResult = "群发名片失败,您设置群发的起点位置是从第[" + GroupSendCardSearchUtils.this.startIndexFromUser + "]个开始群发，高于群数量";
                                    }
                                }
                            }
                            if (GroupSendCardSearchUtils.this.groupList != null && GroupSendCardSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                                if (GroupSendCardSearchUtils.this.circulateMode == 0) {
                                    GroupSendCardSearchUtils.this.originList.clear();
                                    GroupSendCardSearchUtils.this.originList.addAll(GroupSendCardSearchUtils.this.groupList);
                                }
                                new PerformClickUtils2().check(GroupSendCardSearchUtils.this.autoService, BaseServiceUtils.nav_search_id, GroupSendCardSearchUtils.this.jumpTime, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        PerformClickUtils.findNodeByIdFirstAndClick(GroupSendCardSearchUtils.this.autoService, BaseServiceUtils.nav_search_id);
                                        GroupSendCardSearchUtils.this.sleep(GroupSendCardSearchUtils.this.jumpTime);
                                        String unused = GroupSendCardSearchUtils.this.groupNames = "";
                                        Iterator it = GroupSendCardSearchUtils.this.groupList.iterator();
                                        if (it != null && it.hasNext()) {
                                            String unused2 = GroupSendCardSearchUtils.this.groupNames = (String) it.next();
                                        }
                                        String substring = GroupSendCardSearchUtils.this.groupNames.length() > 26 ? GroupSendCardSearchUtils.this.groupNames.substring(0, 25) : GroupSendCardSearchUtils.this.groupNames;
                                        for (int i2 = 0; i2 < 10 && substring.startsWith("@"); i2++) {
                                            substring = substring.substring(1);
                                        }
                                        PerformClickUtils.inputText(GroupSendCardSearchUtils.this.autoService, substring);
                                        new PerformClickUtils2().check(GroupSendCardSearchUtils.this.autoService, BaseServiceUtils.group_list_item_name_id, GroupSendCardSearchUtils.this.groupNames, 1200, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                new Thread(new Runnable() {
                                                    /* JADX WARNING: Removed duplicated region for block: B:70:0x0329 A[LOOP:0: B:25:0x0109->B:70:0x0329, LOOP_END] */
                                                    /* JADX WARNING: Removed duplicated region for block: B:74:0x0178 A[SYNTHETIC] */
                                                    public void run() {
                                                        boolean z;
                                                        String str;
                                                        try {
                                                            AccessibilityNodeInfo rootInActiveWindow = GroupSendCardSearchUtils.this.autoService.getRootInActiveWindow();
                                                            if (rootInActiveWindow != null && MyApplication.enforceable) {
                                                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
                                                                if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                                                    LogUtils.log("WS_BABY.TYPE" + GroupSendCardSearchUtils.this.operationParameterModel.getSendCardType() + "###" + GroupSendCardSearchUtils.this.groupList.size());
                                                                    Iterator it = GroupSendCardSearchUtils.this.groupList.iterator();
                                                                    if (it.hasNext()) {
                                                                        GroupSendCardSearchUtils.this.groupList.remove(it.next());
                                                                        LogUtils.log("WS_BABY.TYPE###end" + GroupSendCardSearchUtils.this.groupList.toString());
                                                                        if (GroupSendCardSearchUtils.this.groupList.size() != 0) {
                                                                            boolean unused = GroupSendCardSearchUtils.this.isEnd = false;
                                                                        } else if (GroupSendCardSearchUtils.this.circulateMode == 0) {
                                                                            GroupSendCardSearchUtils.access$410(GroupSendCardSearchUtils.this);
                                                                            if (GroupSendCardSearchUtils.this.circulateOuterSize <= 0) {
                                                                                boolean unused2 = GroupSendCardSearchUtils.this.isEnd = true;
                                                                            } else {
                                                                                int unused3 = GroupSendCardSearchUtils.this.sendGroupNum = -1;
                                                                                GroupSendCardSearchUtils.this.groupList.addAll(GroupSendCardSearchUtils.this.originList);
                                                                            }
                                                                        } else {
                                                                            boolean unused4 = GroupSendCardSearchUtils.this.isEnd = true;
                                                                        }
                                                                        if (findAccessibilityNodeInfosByViewId != null) {
                                                                            LogUtils.log("WS_BABY.ChatroomContactUI_3");
                                                                            if (findAccessibilityNodeInfosByViewId.size() > 1) {
                                                                                LogUtils.log("WS_BABY.ChatroomContactUI_4");
                                                                                int i = 0;
                                                                                while (true) {
                                                                                    if (i >= findAccessibilityNodeInfosByViewId.size()) {
                                                                                        z = false;
                                                                                        break;
                                                                                    }
                                                                                    AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i);
                                                                                    if (accessibilityNodeInfo != null) {
                                                                                        if (accessibilityNodeInfo.getText() != null) {
                                                                                            str = accessibilityNodeInfo.getText() + "";
                                                                                            LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendCardSearchUtils.this.groupNames);
                                                                                            if (str == null && "".equals(str) && !str.equals(GroupSendCardSearchUtils.this.groupNames)) {
                                                                                                LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendCardSearchUtils.this.groupNames + "@@@@");
                                                                                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(i));
                                                                                                GroupSendCardSearchUtils.this.initFirstChattingUI();
                                                                                                z = true;
                                                                                                break;
                                                                                            }
                                                                                            i++;
                                                                                        }
                                                                                    }
                                                                                    str = "";
                                                                                    LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendCardSearchUtils.this.groupNames);
                                                                                    if (str == null && "".equals(str) && !str.equals(GroupSendCardSearchUtils.this.groupNames)) {
                                                                                    }
                                                                                }
                                                                                if (!z) {
                                                                                    LogUtils.log("WS_BABY.ChatroomContactUI_55");
                                                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                                                    GroupSendCardSearchUtils.this.initFirstChattingUI();
                                                                                }
                                                                            } else if (findAccessibilityNodeInfosByViewId.size() == 1) {
                                                                                LogUtils.log("WS_BABY.ChatroomContactUI_6");
                                                                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                                                GroupSendCardSearchUtils.this.initFirstChattingUI();
                                                                            }
                                                                        }
                                                                    }
                                                                } else if (GroupSendCardSearchUtils.this.groupList != null && GroupSendCardSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                                                                    Iterator it2 = GroupSendCardSearchUtils.this.groupList.iterator();
                                                                    if (it2.hasNext()) {
                                                                        GroupSendCardSearchUtils.this.groupList.remove(it2.next());
                                                                        if (GroupSendCardSearchUtils.this.groupList.size() > 0) {
                                                                            boolean unused5 = GroupSendCardSearchUtils.this.isEnd = false;
                                                                        } else if (GroupSendCardSearchUtils.this.circulateMode == 0) {
                                                                            GroupSendCardSearchUtils.access$410(GroupSendCardSearchUtils.this);
                                                                            if (GroupSendCardSearchUtils.this.circulateOuterSize <= 0) {
                                                                                boolean unused6 = GroupSendCardSearchUtils.this.isEnd = true;
                                                                            } else {
                                                                                int unused7 = GroupSendCardSearchUtils.this.sendGroupNum = -1;
                                                                                GroupSendCardSearchUtils.this.groupList.addAll(GroupSendCardSearchUtils.this.originList);
                                                                            }
                                                                        } else {
                                                                            boolean unused8 = GroupSendCardSearchUtils.this.isEnd = true;
                                                                        }
                                                                        PerformClickUtils.executedBackHome(GroupSendCardSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                                            public void find() {
                                                                                GroupSendCardSearchUtils.this.executeMain();
                                                                            }
                                                                        });
                                                                    }
                                                                }
                                                            }
                                                        } catch (Exception e) {
                                                        }
                                                    }
                                                }).start();
                                            }

                                            public void nuFind() {
                                                GroupSendCardSearchUtils.this.saveIndex();
                                                if (GroupSendCardSearchUtils.this.groupList == null || GroupSendCardSearchUtils.this.groupList.size() <= 0 || !MyApplication.enforceable) {
                                                    BaseServiceUtils.removeEndView(GroupSendCardSearchUtils.this.mMyManager);
                                                } else {
                                                    new Thread(new Runnable() {
                                                        public void run() {
                                                            try {
                                                                Iterator it = GroupSendCardSearchUtils.this.groupList.iterator();
                                                                if (it.hasNext()) {
                                                                    GroupSendCardSearchUtils.this.groupList.remove(it.next());
                                                                    if (GroupSendCardSearchUtils.this.groupList.size() > 0) {
                                                                        boolean unused = GroupSendCardSearchUtils.this.isEnd = false;
                                                                    } else if (GroupSendCardSearchUtils.this.circulateMode == 0) {
                                                                        GroupSendCardSearchUtils.access$410(GroupSendCardSearchUtils.this);
                                                                        if (GroupSendCardSearchUtils.this.circulateOuterSize <= 0) {
                                                                            boolean unused2 = GroupSendCardSearchUtils.this.isEnd = true;
                                                                        } else {
                                                                            int unused3 = GroupSendCardSearchUtils.this.sendGroupNum = -1;
                                                                            GroupSendCardSearchUtils.this.groupList.addAll(GroupSendCardSearchUtils.this.originList);
                                                                        }
                                                                    } else {
                                                                        boolean unused4 = GroupSendCardSearchUtils.this.isEnd = true;
                                                                    }
                                                                    PerformClickUtils.executedBackHome(GroupSendCardSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                                        public void find() {
                                                                            GroupSendCardSearchUtils.this.executeMain();
                                                                        }
                                                                    });
                                                                }
                                                            } catch (Exception e) {
                                                            }
                                                        }
                                                    }).start();
                                                }
                                            }
                                        });
                                    }

                                    public void nuFind() {
                                    }
                                });
                            }
                        } catch (Exception e) {
                        }
                    }
                }).start();
            }
        } else if (this.spaceTime > 0) {
            new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                public void end() {
                    GroupSendCardSearchUtils.this.initRoomContact();
                }

                public void surplus(int i) {
                    MyWindowManager myWindowManager = GroupSendCardSearchUtils.this.mMyManager;
                    BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendCardSearchUtils.this.sendGroupNum + " 个群,剩余 " + GroupSendCardSearchUtils.this.groupList.size() + " 个群\n正在延时等待，倒计时 " + i + " 秒");
                }
            });
        } else {
            initRoomContact();
        }
    }

    public void initData(OperationParameterModel operationParameterModel2) {
        this.sendResult = "";
        this.groupNames = "";
        this.startIndex = 0;
        this.isWhile = true;
        this.jumpTime = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
        this.sendGroupNum = 0;
        this.lastName = "";
        this.groupList = new LinkedHashSet<>();
        this.originList = new LinkedHashSet<>();
        this.isEnd = false;
        this.isRemoveIndex = false;
        this.isRemoveRepeat = false;
        this.operationParameterModel = operationParameterModel2;
        this.cardName = operationParameterModel2.getCardName();
        this.sayContent = operationParameterModel2.getSayContent();
        this.jumpGroupName = operationParameterModel2.getJumpGroupNames();
        this.startIndexFromUser = operationParameterModel2.getStartIndex();
        this.spaceTime = operationParameterModel2.getSpaceTime();
        this.circulateMode = operationParameterModel2.getCirculateMode();
        this.circulateInnerSize = operationParameterModel2.getCirculateInnerSize();
        this.circulateOuterSize = operationParameterModel2.getCirculateOutSize();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        this.maxSendExecuteNum = 0;
        this.isFirstMaxSendExecuteNum = true;
    }

    public void initFirstChattingUI() {
        LogUtils.log("WS_BABY.ChattingUI_1");
        new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, executeSpeed + executeSpeedSetting, 100, 300, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY.ChattingUI_2");
                new PerformClickUtils2().checkStringAndIdSomeOne(GroupSendCardSearchUtils.this.autoService, "发送", BaseServiceUtils.send_add_id, 50, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (i == 0) {
                            LogUtils.log("WS_BABY.ChattingUI_3");
                            PerformClickUtils.inputText(GroupSendCardSearchUtils.this.autoService, "");
                            GroupSendCardSearchUtils.this.clickCard();
                            return;
                        }
                        LogUtils.log("WS_BABY.ChattingUI_4");
                        GroupSendCardSearchUtils.this.clickCard();
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY.ChattingUI_5");
                    }
                });
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.ChattingUI_6");
            }
        });
    }

    public void initRoomContact() {
        new PerformClickUtils2().check(this.autoService, nav_search_id, this.jumpTime, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY.ChatroomContactUI_2");
                PerformClickUtils.findNodeByIdFirstAndClick(GroupSendCardSearchUtils.this.autoService, BaseServiceUtils.nav_search_id);
                GroupSendCardSearchUtils.this.sleep(GroupSendCardSearchUtils.this.jumpTime);
                String unused = GroupSendCardSearchUtils.this.groupNames = "";
                Iterator it = GroupSendCardSearchUtils.this.groupList.iterator();
                if (it != null && it.hasNext()) {
                    String unused2 = GroupSendCardSearchUtils.this.groupNames = (String) it.next();
                }
                String substring = GroupSendCardSearchUtils.this.groupNames.length() > 26 ? GroupSendCardSearchUtils.this.groupNames.substring(0, 25) : GroupSendCardSearchUtils.this.groupNames;
                for (int i2 = 0; i2 < 10 && substring.startsWith("@"); i2++) {
                    substring = substring.substring(1);
                }
                PerformClickUtils.inputText(GroupSendCardSearchUtils.this.autoService, substring);
                new PerformClickUtils2().check(GroupSendCardSearchUtils.this.autoService, BaseServiceUtils.group_list_item_name_id, GroupSendCardSearchUtils.this.groupNames, 1200, 100, 15, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        new Thread(new Runnable() {
                            /* JADX WARNING: Removed duplicated region for block: B:75:0x02bc A[LOOP:0: B:29:0x00c3->B:75:0x02bc, LOOP_END] */
                            /* JADX WARNING: Removed duplicated region for block: B:79:0x012e A[SYNTHETIC] */
                            public void run() {
                                boolean z;
                                String str;
                                try {
                                    AccessibilityNodeInfo rootInActiveWindow = GroupSendCardSearchUtils.this.autoService.getRootInActiveWindow();
                                    if (rootInActiveWindow != null && MyApplication.enforceable) {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
                                        if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                            Iterator it = GroupSendCardSearchUtils.this.groupList.iterator();
                                            if (it.hasNext()) {
                                                GroupSendCardSearchUtils.this.groupList.remove(it.next());
                                                LogUtils.log("WS_BABY.TYPE###end" + GroupSendCardSearchUtils.this.groupList.toString());
                                                if (GroupSendCardSearchUtils.this.groupList.size() != 0 || !MyApplication.enforceable) {
                                                    boolean unused = GroupSendCardSearchUtils.this.isEnd = false;
                                                } else if (GroupSendCardSearchUtils.this.circulateMode != 0 || !MyApplication.enforceable) {
                                                    boolean unused2 = GroupSendCardSearchUtils.this.isEnd = true;
                                                } else {
                                                    GroupSendCardSearchUtils.access$410(GroupSendCardSearchUtils.this);
                                                    if (GroupSendCardSearchUtils.this.circulateOuterSize <= 0) {
                                                        boolean unused3 = GroupSendCardSearchUtils.this.isEnd = true;
                                                    } else {
                                                        int unused4 = GroupSendCardSearchUtils.this.sendGroupNum = -1;
                                                        GroupSendCardSearchUtils.this.groupList.addAll(GroupSendCardSearchUtils.this.originList);
                                                    }
                                                }
                                                if (findAccessibilityNodeInfosByViewId != null) {
                                                    LogUtils.log("WS_BABY.ChatroomContactUI_3");
                                                    if (findAccessibilityNodeInfosByViewId.size() > 1) {
                                                        LogUtils.log("WS_BABY.ChatroomContactUI_4");
                                                        int i = 0;
                                                        while (true) {
                                                            if (i >= findAccessibilityNodeInfosByViewId.size()) {
                                                                z = false;
                                                                break;
                                                            }
                                                            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i);
                                                            if (accessibilityNodeInfo != null) {
                                                                if (accessibilityNodeInfo.getText() != null) {
                                                                    str = accessibilityNodeInfo.getText() + "";
                                                                    LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendCardSearchUtils.this.groupNames);
                                                                    if (str == null && "".equals(str) && !str.equals(GroupSendCardSearchUtils.this.groupNames)) {
                                                                        LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendCardSearchUtils.this.groupNames + "@@@@");
                                                                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(i));
                                                                        GroupSendCardSearchUtils.this.initFirstChattingUI();
                                                                        z = true;
                                                                        break;
                                                                    }
                                                                    i++;
                                                                }
                                                            }
                                                            str = "";
                                                            LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendCardSearchUtils.this.groupNames);
                                                            if (str == null && "".equals(str) && !str.equals(GroupSendCardSearchUtils.this.groupNames)) {
                                                            }
                                                        }
                                                        if (!z) {
                                                            LogUtils.log("WS_BABY.ChatroomContactUI_55");
                                                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                            GroupSendCardSearchUtils.this.initFirstChattingUI();
                                                        }
                                                    } else if (findAccessibilityNodeInfosByViewId.size() == 1) {
                                                        LogUtils.log("WS_BABY.ChatroomContactUI_6");
                                                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                        GroupSendCardSearchUtils.this.initFirstChattingUI();
                                                    }
                                                }
                                            }
                                        } else if (GroupSendCardSearchUtils.this.groupList == null || GroupSendCardSearchUtils.this.groupList.size() <= 0 || !MyApplication.enforceable) {
                                            BaseServiceUtils.removeEndView(GroupSendCardSearchUtils.this.mMyManager);
                                        } else {
                                            Iterator it2 = GroupSendCardSearchUtils.this.groupList.iterator();
                                            if (it2.hasNext()) {
                                                GroupSendCardSearchUtils.this.groupList.remove(it2.next());
                                                if (GroupSendCardSearchUtils.this.groupList.size() > 0) {
                                                    boolean unused5 = GroupSendCardSearchUtils.this.isEnd = false;
                                                } else if (GroupSendCardSearchUtils.this.circulateMode == 0) {
                                                    GroupSendCardSearchUtils.access$410(GroupSendCardSearchUtils.this);
                                                    if (GroupSendCardSearchUtils.this.circulateOuterSize <= 0) {
                                                        boolean unused6 = GroupSendCardSearchUtils.this.isEnd = true;
                                                    } else {
                                                        int unused7 = GroupSendCardSearchUtils.this.sendGroupNum = -1;
                                                        GroupSendCardSearchUtils.this.groupList.addAll(GroupSendCardSearchUtils.this.originList);
                                                    }
                                                } else {
                                                    boolean unused8 = GroupSendCardSearchUtils.this.isEnd = true;
                                                }
                                                PerformClickUtils.executedBackHome(GroupSendCardSearchUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                                    public void find() {
                                                        GroupSendCardSearchUtils.this.executeMain();
                                                    }
                                                });
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                }
                            }
                        }).start();
                    }

                    public void nuFind() {
                        GroupSendCardSearchUtils.this.saveIndex();
                        if (GroupSendCardSearchUtils.this.groupList == null || GroupSendCardSearchUtils.this.groupList.size() <= 0 || !MyApplication.enforceable) {
                            BaseServiceUtils.removeEndView(GroupSendCardSearchUtils.this.mMyManager);
                        } else {
                            new Thread(new Runnable() {
                                public void run() {
                                    try {
                                        Iterator it = GroupSendCardSearchUtils.this.groupList.iterator();
                                        if (it.hasNext()) {
                                            GroupSendCardSearchUtils.this.groupList.remove(it.next());
                                            if (GroupSendCardSearchUtils.this.groupList.size() > 0) {
                                                boolean unused = GroupSendCardSearchUtils.this.isEnd = false;
                                            } else if (GroupSendCardSearchUtils.this.circulateMode == 0) {
                                                GroupSendCardSearchUtils.access$410(GroupSendCardSearchUtils.this);
                                                if (GroupSendCardSearchUtils.this.circulateOuterSize <= 0) {
                                                    boolean unused2 = GroupSendCardSearchUtils.this.isEnd = true;
                                                } else {
                                                    int unused3 = GroupSendCardSearchUtils.this.sendGroupNum = -1;
                                                    GroupSendCardSearchUtils.this.groupList.addAll(GroupSendCardSearchUtils.this.originList);
                                                }
                                            } else {
                                                boolean unused4 = GroupSendCardSearchUtils.this.isEnd = true;
                                            }
                                            PerformClickUtils.executedBackHome(GroupSendCardSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                public void find() {
                                                    GroupSendCardSearchUtils.this.executeMain();
                                                }
                                            });
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                            }).start();
                        }
                    }
                });
            }

            public void nuFind() {
            }
        });
    }

    public void initSelectContactUI() {
        try {
            new PerformClickUtils2().check(this.autoService, list_select_search_id, executeSpeed + executeSpeedSetting, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_SelectContactUI_2");
                    PerformClickUtils.findViewByIdAndPasteContent(GroupSendCardSearchUtils.this.autoService, BaseServiceUtils.list_select_search_id, GroupSendCardSearchUtils.this.cardName);
                    new PerformClickUtils2().checkNilNodeId(GroupSendCardSearchUtils.this.autoService, BaseServiceUtils.list_select_letter_slip_id, 150, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            new PerformClickUtils2().checkNodeHasSomeOne(GroupSendCardSearchUtils.this.autoService, "最常使用", BaseServiceUtils.list_select_name_id, BaseServiceUtils.search_card_wxh, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 200, 40, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    new Thread(new Runnable() {
                                        public void run() {
                                            boolean z = true;
                                            try {
                                                LogUtils.log("WS_BABY_SelectContactUI_3");
                                                AccessibilityNodeInfo rootInActiveWindow = GroupSendCardSearchUtils.this.autoService.getRootInActiveWindow();
                                                if (rootInActiveWindow != null && MyApplication.enforceable) {
                                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_name_id);
                                                    if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                                        int i = 0;
                                                        boolean z2 = true;
                                                        while (z2 && i < findAccessibilityNodeInfosByViewId.size() && MyApplication.enforceable) {
                                                            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i);
                                                            int i2 = i + 1;
                                                            if (accessibilityNodeInfo == null || !MyApplication.enforceable) {
                                                                i = i2;
                                                            } else {
                                                                LogUtils.log("WS_BABY_SelectContactUI_4_" + accessibilityNodeInfo.getText() + "");
                                                                String str = "";
                                                                try {
                                                                    str = accessibilityNodeInfo.getText() + "";
                                                                } catch (Exception e) {
                                                                    LogUtils.log("WS_BABY_SelectContactUI_5");
                                                                }
                                                                if (GroupSendCardSearchUtils.this.cardName.trim().equalsIgnoreCase(str.trim())) {
                                                                    LogUtils.log("WS_BABY_SelectContactUI_6");
                                                                    PerformClickUtils.performClick2(accessibilityNodeInfo);
                                                                    i = i2;
                                                                    z2 = false;
                                                                } else {
                                                                    LogUtils.log("WS_BABY_SelectContactUI_7");
                                                                    i = i2;
                                                                }
                                                            }
                                                        }
                                                        z = z2;
                                                    }
                                                    if (z && MyApplication.enforceable) {
                                                        LogUtils.log("WS_BABY_SelectContactUI_9");
                                                        AccessibilityNodeInfo rootInActiveWindow2 = GroupSendCardSearchUtils.this.autoService.getRootInActiveWindow();
                                                        if (rootInActiveWindow2 != null && MyApplication.enforceable) {
                                                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.search_card_wxh);
                                                            if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0 || !MyApplication.enforceable) {
                                                                LogUtils.log("WS_BABY_SelectContactUI_14-null");
                                                            } else {
                                                                int i3 = 0;
                                                                boolean z3 = z;
                                                                while (z3 && i3 < findAccessibilityNodeInfosByViewId2.size() && MyApplication.enforceable) {
                                                                    AccessibilityNodeInfo accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId2.get(i3);
                                                                    i3++;
                                                                    if (accessibilityNodeInfo2 != null) {
                                                                        String str2 = "";
                                                                        if (accessibilityNodeInfo2.getText() != null) {
                                                                            str2 = accessibilityNodeInfo2.getText() + "";
                                                                        }
                                                                        LogUtils.log("WS_BABY_SelectContactUI10_" + str2);
                                                                        if (str2 != null && str2.contains(":")) {
                                                                            str2 = str2.split(":")[1];
                                                                        }
                                                                        LogUtils.log("WS_BABY_SelectContactUI11_" + str2);
                                                                        if (GroupSendCardSearchUtils.this.cardName.trim().equalsIgnoreCase(str2.trim())) {
                                                                            LogUtils.log("WS_BABY_SelectContactUI12_" + str2);
                                                                            PerformClickUtils.performClick2(accessibilityNodeInfo2);
                                                                            z3 = false;
                                                                        } else {
                                                                            LogUtils.log("WS_BABY_SelectContactUI_13");
                                                                        }
                                                                    }
                                                                }
                                                                z = z3;
                                                            }
                                                        }
                                                    }
                                                    if (z || !MyApplication.enforceable) {
                                                        BaseServiceUtils.updateBottomText(GroupSendCardSearchUtils.this.mMyManager, "没有找到要发送的名片\n请确认名片昵称/微信号是否正确");
                                                        return;
                                                    }
                                                    LogUtils.log("WS_BABY_SelectContactUI_6");
                                                    new PerformClickUtils2().checkNodeAllIds(GroupSendCardSearchUtils.this.autoService, BaseServiceUtils.toast_edit_id, BaseServiceUtils.dialog_ok_id, 20, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                                        public void find(int i) {
                                                            if (GroupSendCardSearchUtils.this.sayContent != null && !GroupSendCardSearchUtils.this.sayContent.equals("")) {
                                                                LogUtils.log("WS_BABY_SelectContactUI_7");
                                                                GroupSendCardSearchUtils.this.sleep(10);
                                                                PerformClickUtils.findViewByIdAndPasteContent(GroupSendCardSearchUtils.this.autoService, BaseServiceUtils.toast_edit_id, GroupSendCardSearchUtils.this.sayContent);
                                                            }
                                                            GroupSendCardSearchUtils.this.sleep(50);
                                                            PerformClickUtils.findViewIdAndClick(GroupSendCardSearchUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                                            LogUtils.log("WS_BABY_SelectContactUI_8");
                                                            if (GroupSendCardSearchUtils.this.circulateInnerSize == 1 && MyApplication.enforceable) {
                                                                GroupSendCardSearchUtils.access$908(GroupSendCardSearchUtils.this);
                                                                GroupSendCardSearchUtils.access$608(GroupSendCardSearchUtils.this);
                                                                int access$600 = GroupSendCardSearchUtils.this.startIndexFromUser == 1 ? GroupSendCardSearchUtils.this.sendGroupNum + 1 : GroupSendCardSearchUtils.this.sendGroupNum + GroupSendCardSearchUtils.this.startIndexFromUser;
                                                                int circulateOutSize = GroupSendCardSearchUtils.this.operationParameterModel.getCirculateOutSize();
                                                                if (GroupSendCardSearchUtils.this.circulateMode == 0 && circulateOutSize > 1 && MyApplication.enforceable) {
                                                                    if (GroupSendCardSearchUtils.this.isFirstMaxSendExecuteNum) {
                                                                        boolean unused = GroupSendCardSearchUtils.this.isFirstMaxSendExecuteNum = false;
                                                                        int unused2 = GroupSendCardSearchUtils.this.maxSendExecuteNum = GroupSendCardSearchUtils.this.groupList.size() + 1;
                                                                    }
                                                                    int size = (GroupSendCardSearchUtils.this.groupList.size() != 0 || GroupSendCardSearchUtils.this.circulateOuterSize <= 0) ? GroupSendCardSearchUtils.this.groupList.size() : GroupSendCardSearchUtils.this.maxSendExecuteNum;
                                                                    if (GroupSendCardSearchUtils.this.operationParameterModel.getSendCardType() == 0) {
                                                                        MyWindowManager myWindowManager = GroupSendCardSearchUtils.this.mMyManager;
                                                                        StringBuilder sb = new StringBuilder();
                                                                        sb.append("从[全部群]中第 ");
                                                                        sb.append(GroupSendCardSearchUtils.this.startIndexFromUser);
                                                                        sb.append(" 个开始群发名片\n已发送 ");
                                                                        sb.append(GroupSendCardSearchUtils.this.sendGroupNum);
                                                                        sb.append(" 个群,剩余");
                                                                        sb.append(size);
                                                                        sb.append(" 个群\n正在执行第 ");
                                                                        sb.append((circulateOutSize - GroupSendCardSearchUtils.this.circulateOuterSize) + 1 > circulateOutSize ? circulateOutSize : (circulateOutSize - GroupSendCardSearchUtils.this.circulateOuterSize) + 1);
                                                                        sb.append(" 轮群发");
                                                                        BaseServiceUtils.updateBottomText(myWindowManager, sb.toString());
                                                                    } else {
                                                                        MyWindowManager myWindowManager2 = GroupSendCardSearchUtils.this.mMyManager;
                                                                        StringBuilder sb2 = new StringBuilder();
                                                                        sb2.append("从[部分群]中第 ");
                                                                        sb2.append(GroupSendCardSearchUtils.this.startIndexFromUser);
                                                                        sb2.append(" 个开始群发名片\n已发送 ");
                                                                        sb2.append(GroupSendCardSearchUtils.this.sendGroupNum);
                                                                        sb2.append(" 个群,剩余");
                                                                        sb2.append(size);
                                                                        sb2.append(" 个群\n正在执行第 ");
                                                                        if ((circulateOutSize - GroupSendCardSearchUtils.this.circulateOuterSize) + 1 <= circulateOutSize) {
                                                                            circulateOutSize = (circulateOutSize - GroupSendCardSearchUtils.this.circulateOuterSize) + 1;
                                                                        }
                                                                        sb2.append(circulateOutSize);
                                                                        sb2.append(" 轮群发");
                                                                        BaseServiceUtils.updateBottomText(myWindowManager2, sb2.toString());
                                                                    }
                                                                } else if (GroupSendCardSearchUtils.this.operationParameterModel.getSendCardType() == 0) {
                                                                    MyWindowManager myWindowManager3 = GroupSendCardSearchUtils.this.mMyManager;
                                                                    BaseServiceUtils.updateBottomText(myWindowManager3, "从[全部群]中第 " + GroupSendCardSearchUtils.this.startIndexFromUser + " 个开始群发名片\n已发送 " + GroupSendCardSearchUtils.this.sendGroupNum + " 个群,剩余 " + GroupSendCardSearchUtils.this.groupList.size() + " 个群");
                                                                } else {
                                                                    MyWindowManager myWindowManager4 = GroupSendCardSearchUtils.this.mMyManager;
                                                                    BaseServiceUtils.updateBottomText(myWindowManager4, "从[部分群]中第 " + GroupSendCardSearchUtils.this.startIndexFromUser + " 个开始群发名片\n已发送 " + GroupSendCardSearchUtils.this.sendGroupNum + " 个群,剩余 " + GroupSendCardSearchUtils.this.groupList.size() + " 个群");
                                                                }
                                                                GroupSendCardSearchUtils.this.saveRecord(access$600);
                                                            }
                                                            GroupSendCardSearchUtils.this.initBackChattingUI();
                                                        }

                                                        public void nuFind() {
                                                            BaseServiceUtils.updateBottomText(GroupSendCardSearchUtils.this.mMyManager, "没有找到要发送的名片\n请确认名片昵称/微信号是否存在");
                                                        }
                                                    });
                                                }
                                            } catch (Exception e2) {
                                            }
                                        }
                                    }).start();
                                }

                                public void nuFind() {
                                    BaseServiceUtils.updateBottomText(GroupSendCardSearchUtils.this.mMyManager, "没有找到要发送的名片\n请确认名片昵称或微信号是否正确");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        if (this.sendResult == null || "".equals(this.sendResult)) {
            MyWindowManager myWindowManager = this.mMyManager;
            updateMiddleText(myWindowManager, "群发名片", "发送了" + this.sendGroupNum + "个群");
            return;
        }
        updateMiddleText(this.mMyManager, "群发名片结束", this.sendResult);
    }

    public void saveIndex() {
        this.sendGroupNum++;
        int i = this.startIndexFromUser == 1 ? this.sendGroupNum + 1 : this.sendGroupNum + this.startIndexFromUser;
        if (this.operationParameterModel.getSendCardType() == 0) {
            SPUtils.put(MyApplication.getConText(), "card_to_group_num_all", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 1) {
            SPUtils.put(MyApplication.getConText(), "card_to_group_num_part", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 2) {
            SPUtils.put(MyApplication.getConText(), "card_to_group_num_shield", Integer.valueOf(i));
        }
    }

    public void saveRecord(int i) {
        if (this.operationParameterModel.getSendCardType() == 0) {
            SPUtils.put(MyApplication.getConText(), "card_to_group_num_all", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 1) {
            SPUtils.put(MyApplication.getConText(), "card_to_group_num_part", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 2) {
            SPUtils.put(MyApplication.getConText(), "card_to_group_num_shield", Integer.valueOf(i));
        }
    }
}
