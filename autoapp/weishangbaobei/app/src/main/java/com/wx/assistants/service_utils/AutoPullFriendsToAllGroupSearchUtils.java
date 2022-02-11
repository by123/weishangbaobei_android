package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class AutoPullFriendsToAllGroupSearchUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static AutoPullFriendsToAllGroupSearchUtils instance;
    /* access modifiers changed from: private */
    public int backTime = 300;
    /* access modifiers changed from: private */
    public String cardName = "";
    /* access modifiers changed from: private */
    public LinkedHashSet<String> groupList = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public String groupNames = "";
    /* access modifiers changed from: private */
    public boolean isEnd;
    /* access modifiers changed from: private */
    public boolean isRemoveIndex = false;
    /* access modifiers changed from: private */
    public boolean isRemoveRepeat = false;
    /* access modifiers changed from: private */
    public boolean isWhile = true;
    /* access modifiers changed from: private */
    public String jumpGroupName = "";
    /* access modifiers changed from: private */
    public int jumpNum = 0;
    /* access modifiers changed from: private */
    public int jumpTime = 300;
    /* access modifiers changed from: private */
    public String lastName;
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

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$1508(AutoPullFriendsToAllGroupSearchUtils autoPullFriendsToAllGroupSearchUtils) {
        int i = autoPullFriendsToAllGroupSearchUtils.jumpNum;
        autoPullFriendsToAllGroupSearchUtils.jumpNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$408(AutoPullFriendsToAllGroupSearchUtils autoPullFriendsToAllGroupSearchUtils) {
        int i = autoPullFriendsToAllGroupSearchUtils.sendGroupNum;
        autoPullFriendsToAllGroupSearchUtils.sendGroupNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$608(AutoPullFriendsToAllGroupSearchUtils autoPullFriendsToAllGroupSearchUtils) {
        int i = autoPullFriendsToAllGroupSearchUtils.startIndex;
        autoPullFriendsToAllGroupSearchUtils.startIndex = i + 1;
        return i;
    }

    private AutoPullFriendsToAllGroupSearchUtils() {
    }

    public static AutoPullFriendsToAllGroupSearchUtils getInstance() {
        instance = new AutoPullFriendsToAllGroupSearchUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel2) {
        this.sendResult = "";
        this.groupNames = "";
        this.startIndex = 0;
        this.isWhile = true;
        this.backTime = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
        this.jumpTime = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
        this.sendGroupNum = 0;
        this.jumpNum = 0;
        this.lastName = "";
        this.groupList = new LinkedHashSet<>();
        this.originList = new LinkedHashSet<>();
        this.isEnd = false;
        this.isRemoveIndex = false;
        this.isRemoveRepeat = false;
        this.operationParameterModel = operationParameterModel2;
        this.cardName = operationParameterModel2.getCardName();
        if (this.cardName != null && !"".equals(this.cardName)) {
            this.cardName = this.cardName.replace(" ", "");
        }
        this.sayContent = operationParameterModel2.getSayContent();
        this.jumpGroupName = operationParameterModel2.getJumpGroupNames();
        this.startIndexFromUser = operationParameterModel2.getStartIndex();
        this.spaceTime = operationParameterModel2.getSpaceTime();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initRoomContact() {
        if (this.groupList == null || this.groupList.size() == 0) {
            removeEndView(this.mMyManager);
            return;
        }
        MyWindowManager myWindowManager = this.mMyManager;
        updateBottomText(myWindowManager, "正在拉人进群,请不要操作微信\n已邀请 " + this.sendGroupNum + " 个群,剩余 " + this.groupList.size() + " 个群");
        LogUtils.log("WS_BABY.ChatroomContactUI_2");
        new PerformClickUtils2().check(this.autoService, nav_search_id, this.jumpTime, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                PerformClickUtils.findNodeByIdFirstAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.nav_search_id);
                AutoPullFriendsToAllGroupSearchUtils.this.sleep(AutoPullFriendsToAllGroupSearchUtils.this.jumpTime);
                String unused = AutoPullFriendsToAllGroupSearchUtils.this.groupNames = "";
                Iterator it = AutoPullFriendsToAllGroupSearchUtils.this.groupList.iterator();
                if (it != null && it.hasNext()) {
                    String unused2 = AutoPullFriendsToAllGroupSearchUtils.this.groupNames = (String) it.next();
                }
                for (int i2 = 0; i2 < 10 && AutoPullFriendsToAllGroupSearchUtils.this.groupNames.startsWith("@"); i2++) {
                    String unused3 = AutoPullFriendsToAllGroupSearchUtils.this.groupNames = AutoPullFriendsToAllGroupSearchUtils.this.groupNames.substring(1);
                }
                PerformClickUtils.inputText(AutoPullFriendsToAllGroupSearchUtils.this.autoService, AutoPullFriendsToAllGroupSearchUtils.this.groupNames);
                new PerformClickUtils2().check(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.group_list_item_name_id, AutoPullFriendsToAllGroupSearchUtils.this.groupNames, 1200, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    AccessibilityNodeInfo rootInActiveWindow = AutoPullFriendsToAllGroupSearchUtils.this.autoService.getRootInActiveWindow();
                                    if (rootInActiveWindow != null && MyApplication.enforceable) {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
                                        boolean z = true;
                                        if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                            Iterator it = AutoPullFriendsToAllGroupSearchUtils.this.groupList.iterator();
                                            if (it.hasNext()) {
                                                AutoPullFriendsToAllGroupSearchUtils.this.groupList.remove(it.next());
                                                LogUtils.log("WS_BABY.TYPE###end" + AutoPullFriendsToAllGroupSearchUtils.this.groupList.toString());
                                                if (AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() != 0 || !MyApplication.enforceable) {
                                                    boolean unused = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = false;
                                                } else {
                                                    boolean unused2 = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = true;
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
                                                            String str = "";
                                                            if (accessibilityNodeInfo.getText() != null) {
                                                                str = accessibilityNodeInfo.getText() + "";
                                                            }
                                                            LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + AutoPullFriendsToAllGroupSearchUtils.this.groupNames);
                                                            if (str != null && !"".equals(str) && str.equals(AutoPullFriendsToAllGroupSearchUtils.this.groupNames)) {
                                                                LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + AutoPullFriendsToAllGroupSearchUtils.this.groupNames + "@@@@");
                                                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(i));
                                                                AutoPullFriendsToAllGroupSearchUtils.this.initChattingUIFirst();
                                                                break;
                                                            }
                                                            i++;
                                                        }
                                                        if (!z) {
                                                            LogUtils.log("WS_BABY.ChatroomContactUI_55");
                                                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                            AutoPullFriendsToAllGroupSearchUtils.this.initChattingUIFirst();
                                                        }
                                                    } else if (findAccessibilityNodeInfosByViewId.size() == 1) {
                                                        LogUtils.log("WS_BABY.ChatroomContactUI_6");
                                                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                        AutoPullFriendsToAllGroupSearchUtils.this.initChattingUIFirst();
                                                    }
                                                }
                                            }
                                        } else if (AutoPullFriendsToAllGroupSearchUtils.this.groupList != null && AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                                            Iterator it2 = AutoPullFriendsToAllGroupSearchUtils.this.groupList.iterator();
                                            if (it2.hasNext()) {
                                                AutoPullFriendsToAllGroupSearchUtils.this.groupList.remove(it2.next());
                                                if (AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() <= 0) {
                                                    boolean unused3 = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = true;
                                                } else {
                                                    boolean unused4 = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = false;
                                                }
                                                LogUtils.log("WS_BABY_TYPE_7");
                                                PerformClickUtils.executedBackHome(AutoPullFriendsToAllGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                    public void find() {
                                                        AutoPullFriendsToAllGroupSearchUtils.this.executeMain();
                                                    }
                                                });
                                            }
                                        }
                                    }
                                } catch (Exception unused5) {
                                }
                            }
                        }).start();
                    }

                    public void nuFind() {
                        if (AutoPullFriendsToAllGroupSearchUtils.this.groupList != null && AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                            Iterator it = AutoPullFriendsToAllGroupSearchUtils.this.groupList.iterator();
                            if (it.hasNext()) {
                                AutoPullFriendsToAllGroupSearchUtils.this.groupList.remove(it.next());
                                if (AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() <= 0) {
                                    boolean unused = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = true;
                                } else {
                                    boolean unused2 = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = false;
                                }
                                LogUtils.log("WS_BABY_TYPE_8");
                                PerformClickUtils.executedBackHome(AutoPullFriendsToAllGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        AutoPullFriendsToAllGroupSearchUtils.this.executeMain();
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });
    }

    public void initChatroomContactUI() {
        try {
            if (this.groupList == null || this.groupList.size() <= 0 || !MyApplication.enforceable) {
                LogUtils.log("WS_BABY.ChatroomContactUI_1");
                if (this.isEnd || !MyApplication.enforceable) {
                    removeEndView(this.mMyManager);
                } else if (this.operationParameterModel.getSendCardType() == 1) {
                    this.groupList = new LinkedHashSet<>();
                    if (this.jumpGroupName.contains(";")) {
                        String[] split = this.jumpGroupName.split(";");
                        for (int i = 0; i < split.length; i++) {
                            if (split[i] != null && !split[i].equals("")) {
                                this.groupList.add(split[i]);
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
                    } catch (Exception unused) {
                        LogUtils.log("WS_BABY.st.3");
                    }
                    new PerformClickUtils2().check(this.autoService, nav_search_id, this.jumpTime, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            PerformClickUtils.findNodeByIdFirstAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.nav_search_id);
                            AutoPullFriendsToAllGroupSearchUtils.this.sleep(AutoPullFriendsToAllGroupSearchUtils.this.jumpTime);
                            String unused = AutoPullFriendsToAllGroupSearchUtils.this.groupNames = "";
                            Iterator it = AutoPullFriendsToAllGroupSearchUtils.this.groupList.iterator();
                            if (it != null && it.hasNext()) {
                                String unused2 = AutoPullFriendsToAllGroupSearchUtils.this.groupNames = (String) it.next();
                            }
                            for (int i2 = 0; i2 < 10 && AutoPullFriendsToAllGroupSearchUtils.this.groupNames.startsWith("@"); i2++) {
                                String unused3 = AutoPullFriendsToAllGroupSearchUtils.this.groupNames = AutoPullFriendsToAllGroupSearchUtils.this.groupNames.substring(1);
                            }
                            PerformClickUtils.inputText(AutoPullFriendsToAllGroupSearchUtils.this.autoService, AutoPullFriendsToAllGroupSearchUtils.this.groupNames);
                            new PerformClickUtils2().check(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.group_list_item_name_id, AutoPullFriendsToAllGroupSearchUtils.this.groupNames, 1200, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    new Thread(new Runnable() {
                                        public void run() {
                                            try {
                                                AccessibilityNodeInfo rootInActiveWindow = AutoPullFriendsToAllGroupSearchUtils.this.autoService.getRootInActiveWindow();
                                                if (rootInActiveWindow != null) {
                                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
                                                    boolean z = true;
                                                    if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                                        Iterator it = AutoPullFriendsToAllGroupSearchUtils.this.groupList.iterator();
                                                        if (it.hasNext()) {
                                                            AutoPullFriendsToAllGroupSearchUtils.this.groupList.remove(it.next());
                                                            if (AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() == 0) {
                                                                boolean unused = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = true;
                                                            } else {
                                                                boolean unused2 = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = false;
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
                                                                        String str = "";
                                                                        if (accessibilityNodeInfo.getText() != null) {
                                                                            str = accessibilityNodeInfo.getText() + "";
                                                                        }
                                                                        for (int i2 = 0; i2 < 10 && str.startsWith("@"); i2++) {
                                                                            str = AutoPullFriendsToAllGroupSearchUtils.this.groupNames.substring(1);
                                                                        }
                                                                        LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + AutoPullFriendsToAllGroupSearchUtils.this.groupNames);
                                                                        if (str != null && !"".equals(str) && str.equals(AutoPullFriendsToAllGroupSearchUtils.this.groupNames)) {
                                                                            LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + AutoPullFriendsToAllGroupSearchUtils.this.groupNames + "@@@@");
                                                                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(i));
                                                                            AutoPullFriendsToAllGroupSearchUtils.this.initChattingUIFirst();
                                                                            break;
                                                                        }
                                                                        i++;
                                                                    }
                                                                    if (!z) {
                                                                        LogUtils.log("WS_BABY.ChatroomContactUI_55");
                                                                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                                        AutoPullFriendsToAllGroupSearchUtils.this.initChattingUIFirst();
                                                                    }
                                                                } else if (findAccessibilityNodeInfosByViewId.size() == 1) {
                                                                    LogUtils.log("WS_BABY.ChatroomContactUI_6");
                                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                                    AutoPullFriendsToAllGroupSearchUtils.this.initChattingUIFirst();
                                                                }
                                                            }
                                                        }
                                                    } else if (AutoPullFriendsToAllGroupSearchUtils.this.groupList != null && AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                                                        Iterator it2 = AutoPullFriendsToAllGroupSearchUtils.this.groupList.iterator();
                                                        if (it2.hasNext()) {
                                                            AutoPullFriendsToAllGroupSearchUtils.this.groupList.remove(it2.next());
                                                            if (AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() <= 0) {
                                                                boolean unused3 = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = true;
                                                            } else {
                                                                boolean unused4 = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = false;
                                                            }
                                                            LogUtils.log("WS_BABY_TYPE_9");
                                                            PerformClickUtils.executedBackHome(AutoPullFriendsToAllGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                                public void find() {
                                                                    AutoPullFriendsToAllGroupSearchUtils.this.executeMain();
                                                                }
                                                            });
                                                        }
                                                    }
                                                }
                                            } catch (Exception unused5) {
                                            }
                                        }
                                    }).start();
                                }

                                public void nuFind() {
                                    if (AutoPullFriendsToAllGroupSearchUtils.this.groupList != null && AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                                        new Thread(new Runnable() {
                                            public void run() {
                                                try {
                                                    Iterator it = AutoPullFriendsToAllGroupSearchUtils.this.groupList.iterator();
                                                    if (it.hasNext()) {
                                                        AutoPullFriendsToAllGroupSearchUtils.this.groupList.remove(it.next());
                                                        if (AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() <= 0) {
                                                            boolean unused = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = true;
                                                        } else {
                                                            boolean unused2 = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = false;
                                                        }
                                                        LogUtils.log("WS_BABY_TYPE_10");
                                                        PerformClickUtils.executedBackHome(AutoPullFriendsToAllGroupSearchUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                                            public void find() {
                                                                AutoPullFriendsToAllGroupSearchUtils.this.executeMain();
                                                            }
                                                        });
                                                    }
                                                } catch (Exception unused3) {
                                                }
                                            }
                                        }).start();
                                    }
                                }
                            });
                        }
                    });
                } else {
                    new PerformClickUtils2().checkNodeAllIds(this.autoService, grout_friend_list_id, group_list_item_name_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            int i2;
                            AccessibilityNodeInfo accessibilityNodeInfo;
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                            AccessibilityNodeInfo accessibilityNodeInfo2;
                            while (true) {
                                if (AutoPullFriendsToAllGroupSearchUtils.this.isWhile && MyApplication.enforceable) {
                                    AccessibilityNodeInfo rootInActiveWindow = AutoPullFriendsToAllGroupSearchUtils.this.autoService.getRootInActiveWindow();
                                    if (rootInActiveWindow != null && MyApplication.enforceable) {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.grout_friend_list_id);
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
                                        if (findAccessibilityNodeInfosByViewId3 != null && !findAccessibilityNodeInfosByViewId3.isEmpty() && MyApplication.enforceable) {
                                            if (AutoPullFriendsToAllGroupSearchUtils.this.startIndex < findAccessibilityNodeInfosByViewId3.size() && MyApplication.enforceable) {
                                                AccessibilityNodeInfo accessibilityNodeInfo3 = findAccessibilityNodeInfosByViewId3.get(AutoPullFriendsToAllGroupSearchUtils.this.startIndex);
                                                if (accessibilityNodeInfo3 != null) {
                                                    if (accessibilityNodeInfo3.getText() != null) {
                                                        String unused = AutoPullFriendsToAllGroupSearchUtils.this.lastName = accessibilityNodeInfo3.getText() + "";
                                                    }
                                                    if (AutoPullFriendsToAllGroupSearchUtils.this.groupList.contains(AutoPullFriendsToAllGroupSearchUtils.this.lastName)) {
                                                        AutoPullFriendsToAllGroupSearchUtils.access$608(AutoPullFriendsToAllGroupSearchUtils.this);
                                                    } else {
                                                        AutoPullFriendsToAllGroupSearchUtils.this.groupList.add(AutoPullFriendsToAllGroupSearchUtils.this.lastName);
                                                        AutoPullFriendsToAllGroupSearchUtils.access$608(AutoPullFriendsToAllGroupSearchUtils.this);
                                                    }
                                                }
                                            } else if (AutoPullFriendsToAllGroupSearchUtils.this.startIndex >= findAccessibilityNodeInfosByViewId3.size() && MyApplication.enforceable && findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId2.get(0)) != null && MyApplication.enforceable) {
                                                PerformClickUtils.scroll(accessibilityNodeInfo);
                                                AutoPullFriendsToAllGroupSearchUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                                AccessibilityNodeInfo rootInActiveWindow2 = AutoPullFriendsToAllGroupSearchUtils.this.autoService.getRootInActiveWindow();
                                                if (rootInActiveWindow2 != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1)) != null && MyApplication.enforceable) {
                                                    if ((accessibilityNodeInfo2.getText() + "").equals(AutoPullFriendsToAllGroupSearchUtils.this.lastName)) {
                                                        boolean unused2 = AutoPullFriendsToAllGroupSearchUtils.this.isWhile = false;
                                                    }
                                                    int unused3 = AutoPullFriendsToAllGroupSearchUtils.this.startIndex = 0;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!AutoPullFriendsToAllGroupSearchUtils.this.isWhile && MyApplication.enforceable) {
                                if (AutoPullFriendsToAllGroupSearchUtils.this.groupList != null && AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() > 0 && AutoPullFriendsToAllGroupSearchUtils.this.operationParameterModel.getSendCardType() == 2 && MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY.TYPE#2#jumpGroup#" + AutoPullFriendsToAllGroupSearchUtils.this.jumpGroupName);
                                    if (!AutoPullFriendsToAllGroupSearchUtils.this.isRemoveRepeat) {
                                        if (AutoPullFriendsToAllGroupSearchUtils.this.jumpGroupName.contains(";")) {
                                            String[] split = AutoPullFriendsToAllGroupSearchUtils.this.jumpGroupName.split(";");
                                            for (String remove : split) {
                                                AutoPullFriendsToAllGroupSearchUtils.this.groupList.remove(remove);
                                            }
                                        } else {
                                            AutoPullFriendsToAllGroupSearchUtils.this.groupList.remove(AutoPullFriendsToAllGroupSearchUtils.this.jumpGroupName);
                                        }
                                        boolean unused4 = AutoPullFriendsToAllGroupSearchUtils.this.isRemoveRepeat = true;
                                    }
                                }
                                if (!AutoPullFriendsToAllGroupSearchUtils.this.isRemoveIndex && AutoPullFriendsToAllGroupSearchUtils.this.startIndexFromUser > 1) {
                                    if (AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() >= AutoPullFriendsToAllGroupSearchUtils.this.startIndexFromUser) {
                                        for (int i3 = 1; i3 < AutoPullFriendsToAllGroupSearchUtils.this.startIndexFromUser; i3++) {
                                            Iterator it = AutoPullFriendsToAllGroupSearchUtils.this.groupList.iterator();
                                            if (it.hasNext()) {
                                                AutoPullFriendsToAllGroupSearchUtils.this.groupList.remove(it.next());
                                            }
                                        }
                                        boolean unused5 = AutoPullFriendsToAllGroupSearchUtils.this.isRemoveIndex = true;
                                    } else {
                                        BaseServiceUtils.removeEndView(AutoPullFriendsToAllGroupSearchUtils.this.mMyManager);
                                        AutoPullFriendsToAllGroupSearchUtils.this.groupList.clear();
                                        String unused6 = AutoPullFriendsToAllGroupSearchUtils.this.sendResult = "拉人进群失败,您设置拉人的起点位置是从第[" + AutoPullFriendsToAllGroupSearchUtils.this.startIndexFromUser + "]个开始拉人，高于群数量";
                                    }
                                }
                            }
                            if (AutoPullFriendsToAllGroupSearchUtils.this.groupList != null && AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                                AutoPullFriendsToAllGroupSearchUtils.this.originList.clear();
                                AutoPullFriendsToAllGroupSearchUtils.this.originList.addAll(AutoPullFriendsToAllGroupSearchUtils.this.groupList);
                                new PerformClickUtils2().check(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.nav_search_id, AutoPullFriendsToAllGroupSearchUtils.this.jumpTime, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        PerformClickUtils.findNodeByIdFirstAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.nav_search_id);
                                        AutoPullFriendsToAllGroupSearchUtils.this.sleep(AutoPullFriendsToAllGroupSearchUtils.this.jumpTime);
                                        String unused = AutoPullFriendsToAllGroupSearchUtils.this.groupNames = "";
                                        Iterator it = AutoPullFriendsToAllGroupSearchUtils.this.groupList.iterator();
                                        if (it != null && it.hasNext()) {
                                            String unused2 = AutoPullFriendsToAllGroupSearchUtils.this.groupNames = (String) it.next();
                                        }
                                        for (int i2 = 0; i2 < 10 && AutoPullFriendsToAllGroupSearchUtils.this.groupNames.startsWith("@"); i2++) {
                                            String unused3 = AutoPullFriendsToAllGroupSearchUtils.this.groupNames = AutoPullFriendsToAllGroupSearchUtils.this.groupNames.substring(1);
                                        }
                                        PerformClickUtils.inputText(AutoPullFriendsToAllGroupSearchUtils.this.autoService, AutoPullFriendsToAllGroupSearchUtils.this.groupNames);
                                        new PerformClickUtils2().check(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.group_list_item_name_id, AutoPullFriendsToAllGroupSearchUtils.this.groupNames, 1200, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                new Thread(new Runnable() {
                                                    public void run() {
                                                        try {
                                                            AccessibilityNodeInfo rootInActiveWindow = AutoPullFriendsToAllGroupSearchUtils.this.autoService.getRootInActiveWindow();
                                                            if (rootInActiveWindow != null && MyApplication.enforceable) {
                                                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
                                                                boolean z = true;
                                                                if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                                                    LogUtils.log("WS_BABY_TYPE_0");
                                                                    Iterator it = AutoPullFriendsToAllGroupSearchUtils.this.groupList.iterator();
                                                                    if (it.hasNext()) {
                                                                        AutoPullFriendsToAllGroupSearchUtils.this.groupList.remove(it.next());
                                                                        LogUtils.log("WS_BABY_TYPE_1");
                                                                        if (AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() == 0) {
                                                                            boolean unused = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = true;
                                                                        } else {
                                                                            boolean unused2 = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = false;
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
                                                                                    String str = "";
                                                                                    if (accessibilityNodeInfo.getText() != null) {
                                                                                        str = accessibilityNodeInfo.getText() + "";
                                                                                    }
                                                                                    LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + AutoPullFriendsToAllGroupSearchUtils.this.groupNames);
                                                                                    if (str != null && !"".equals(str) && str.equals(AutoPullFriendsToAllGroupSearchUtils.this.groupNames)) {
                                                                                        LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + AutoPullFriendsToAllGroupSearchUtils.this.groupNames + "@@@@");
                                                                                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(i));
                                                                                        AutoPullFriendsToAllGroupSearchUtils.this.initChattingUIFirst();
                                                                                        break;
                                                                                    }
                                                                                    i++;
                                                                                }
                                                                                if (!z) {
                                                                                    LogUtils.log("WS_BABY.ChatroomContactUI_55");
                                                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                                                    AutoPullFriendsToAllGroupSearchUtils.this.initChattingUIFirst();
                                                                                }
                                                                            } else if (findAccessibilityNodeInfosByViewId.size() == 1) {
                                                                                LogUtils.log("WS_BABY.ChatroomContactUI_6");
                                                                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                                                AutoPullFriendsToAllGroupSearchUtils.this.initChattingUIFirst();
                                                                            }
                                                                        }
                                                                    }
                                                                } else if (AutoPullFriendsToAllGroupSearchUtils.this.groupList != null && AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                                                                    Iterator it2 = AutoPullFriendsToAllGroupSearchUtils.this.groupList.iterator();
                                                                    if (it2.hasNext()) {
                                                                        AutoPullFriendsToAllGroupSearchUtils.this.groupList.remove(it2.next());
                                                                        if (AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() <= 0) {
                                                                            boolean unused3 = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = true;
                                                                        } else {
                                                                            boolean unused4 = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = false;
                                                                        }
                                                                        LogUtils.log("WS_BABY_TYPE_2");
                                                                        PerformClickUtils.executedBackHome(AutoPullFriendsToAllGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                                            public void find() {
                                                                                AutoPullFriendsToAllGroupSearchUtils.this.executeMain();
                                                                            }
                                                                        });
                                                                    }
                                                                }
                                                            }
                                                        } catch (Exception unused5) {
                                                        }
                                                    }
                                                }).start();
                                            }

                                            public void nuFind() {
                                                if (AutoPullFriendsToAllGroupSearchUtils.this.groupList != null && AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                                                    Iterator it = AutoPullFriendsToAllGroupSearchUtils.this.groupList.iterator();
                                                    if (it.hasNext()) {
                                                        AutoPullFriendsToAllGroupSearchUtils.this.groupList.remove(it.next());
                                                        if (AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() <= 0) {
                                                            boolean unused = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = true;
                                                        } else {
                                                            boolean unused2 = AutoPullFriendsToAllGroupSearchUtils.this.isEnd = false;
                                                        }
                                                        LogUtils.log("WS_BABY_TYPE_3");
                                                        PerformClickUtils.executedBackHome(AutoPullFriendsToAllGroupSearchUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                                            public void find() {
                                                                AutoPullFriendsToAllGroupSearchUtils.this.executeMain();
                                                            }
                                                        });
                                                    }
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    });
                }
            } else if (this.spaceTime > 0) {
                new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                    public void surplus(int i) {
                        MyWindowManager myWindowManager = AutoPullFriendsToAllGroupSearchUtils.this.mMyManager;
                        BaseServiceUtils.updateBottomText(myWindowManager, "已邀请 " + AutoPullFriendsToAllGroupSearchUtils.this.sendGroupNum + " 个群,剩余 " + AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() + " 个群\n正在延时等待，倒计时 " + i + " 秒");
                    }

                    public void end() {
                        AutoPullFriendsToAllGroupSearchUtils.this.initRoomContact();
                    }
                });
            } else {
                initRoomContact();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initChattingUIFirst() {
        new PerformClickUtils2().check(this.autoService, contact_title_id, 0, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                int i2;
                if (PerformClickUtils.getTextByNodeId(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.contact_title_id).contains("(500)")) {
                    AutoPullFriendsToAllGroupSearchUtils.access$1508(AutoPullFriendsToAllGroupSearchUtils.this);
                    if (AutoPullFriendsToAllGroupSearchUtils.this.startIndexFromUser == 1) {
                        i2 = AutoPullFriendsToAllGroupSearchUtils.this.sendGroupNum + AutoPullFriendsToAllGroupSearchUtils.this.jumpNum + 1;
                    } else {
                        i2 = AutoPullFriendsToAllGroupSearchUtils.this.sendGroupNum + AutoPullFriendsToAllGroupSearchUtils.this.jumpNum + AutoPullFriendsToAllGroupSearchUtils.this.startIndexFromUser;
                    }
                    if (AutoPullFriendsToAllGroupSearchUtils.this.operationParameterModel.getSendCardType() == 0) {
                        SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_num_all", Integer.valueOf(i2));
                    } else if (AutoPullFriendsToAllGroupSearchUtils.this.operationParameterModel.getSendCardType() == 1) {
                        SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_num_part", Integer.valueOf(i2));
                    } else if (AutoPullFriendsToAllGroupSearchUtils.this.operationParameterModel.getSendCardType() == 2) {
                        SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_num_shield", Integer.valueOf(i2));
                    }
                    PerformClickUtils.executedBackHome(AutoPullFriendsToAllGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            AutoPullFriendsToAllGroupSearchUtils.this.executeMain();
                        }
                    });
                    return;
                }
                LogUtils.log("WS_BABY.ChattingUI_0");
                new PerformClickUtils2().check(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.right_more_id, BaseServiceUtils.executeSpeed, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.findViewIdAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.right_more_id);
                        AutoPullFriendsToAllGroupSearchUtils.this.initChatRoomInfoUIFirst();
                    }

                    public void nuFind() {
                        PerformClickUtils.executedBackHome(AutoPullFriendsToAllGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                AutoPullFriendsToAllGroupSearchUtils.this.executeMain();
                            }
                        });
                    }
                });
            }
        });
    }

    public void initChattingUIBack() {
        try {
            PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                public void find() {
                    AutoPullFriendsToAllGroupSearchUtils.this.executeMain();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initChatRoomInfoUIFirst() {
        executeAddFriend();
    }

    public void initChatRoomInfoUIBack() {
        new PerformClickUtils2().checkNilString(this.autoService, "正在添加", (int) SocializeConstants.CANCLE_RESULTCODE, 100, 300, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY.ChatroomInfoUI_1");
                new PerformClickUtils2().check(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.dialog_ok_id, 100, (int) SocializeConstants.CANCLE_RESULTCODE, 5, false, (PerformClickUtils2.OnCheckListener2) new PerformClickUtils2.OnCheckListener2() {
                    public void nuFind() {
                    }

                    public void find() {
                        LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.000");
                        if (PerformClickUtils.findNodeIsExistById((AccessibilityService) AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.toast_edit_id)) {
                            try {
                                if (AutoPullFriendsToAllGroupSearchUtils.this.sayContent != null && !AutoPullFriendsToAllGroupSearchUtils.this.sayContent.equals("")) {
                                    LogUtils.log("WS_BABY_SelectContactUI_7");
                                    AutoPullFriendsToAllGroupSearchUtils.this.sleep(100);
                                    PerformClickUtils.findViewByIdAndPasteContent(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.toast_edit_id, AutoPullFriendsToAllGroupSearchUtils.this.sayContent);
                                }
                            } catch (Exception unused) {
                            }
                            AutoPullFriendsToAllGroupSearchUtils.this.sleep(200);
                        }
                        PerformClickUtils.findViewIdAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                    }

                    public void end() {
                        if (MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.end");
                            new PerformClickUtils2().checkLoad(AutoPullFriendsToAllGroupSearchUtils.this.autoService, "正在发送", 0, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.end2");
                                    new PerformClickUtils2().checkDialogToBack(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.back_contact_id, BaseServiceUtils.dialog_ok_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 50, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            PerformClickUtils.findViewIdAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.back_contact_id);
                                            LogUtils.log("WS_BABY_SelectContactUI_8");
                                            AutoPullFriendsToAllGroupSearchUtils.access$608(AutoPullFriendsToAllGroupSearchUtils.this);
                                            if (AutoPullFriendsToAllGroupSearchUtils.this.operationParameterModel.getSendCardType() == 0) {
                                                MyWindowManager myWindowManager = AutoPullFriendsToAllGroupSearchUtils.this.mMyManager;
                                                BaseServiceUtils.updateBottomText(myWindowManager, "已邀请 " + AutoPullFriendsToAllGroupSearchUtils.this.sendGroupNum + " 个群,剩余 " + AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() + " 个群");
                                            } else {
                                                MyWindowManager myWindowManager2 = AutoPullFriendsToAllGroupSearchUtils.this.mMyManager;
                                                BaseServiceUtils.updateBottomText(myWindowManager2, "已邀请 " + AutoPullFriendsToAllGroupSearchUtils.this.sendGroupNum + " 个群,剩余 " + AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() + " 个群");
                                            }
                                            AutoPullFriendsToAllGroupSearchUtils.this.initChattingUIBack();
                                        }
                                    });
                                }
                            });
                        }
                    }

                    public void executeIndex(int i) {
                        MyWindowManager myWindowManager = AutoPullFriendsToAllGroupSearchUtils.this.mMyManager;
                        BaseServiceUtils.updateBottomText(myWindowManager, "执行下一个群，倒计时 " + i + " 秒");
                    }
                });
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.ChatroomInfoUI_ADD");
            }
        });
    }

    public void executeAddFriend() {
        if (MyApplication.enforceable) {
            new PerformClickUtils2().checkNodeAllIds(this.autoService, default_list_id, list_head_img_id, executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    LogUtils.log("WS_BABY.ChatroomInfoUI_0");
                    int i2 = 2;
                    for (int i3 = 0; i3 < i2; i3++) {
                        AccessibilityNodeInfo rootInActiveWindow = AutoPullFriendsToAllGroupSearchUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow == null || !MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.ChatroomInfoUI_3");
                            return;
                        }
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.default_list_id);
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_head_img_id);
                        if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.ChatroomInfoUI_1");
                            int i4 = 0;
                            while (true) {
                                if (i4 >= findAccessibilityNodeInfosByViewId2.size() || !MyApplication.enforceable) {
                                    break;
                                }
                                AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId2.get(i4);
                                if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getContentDescription() == null)) {
                                    if ((accessibilityNodeInfo.getContentDescription() + "").equals("添加成员") && MyApplication.enforceable) {
                                        PerformClickUtils.performClick(accessibilityNodeInfo);
                                        AutoPullFriendsToAllGroupSearchUtils.this.initSelectNickName();
                                        i2 = 0;
                                        break;
                                    }
                                }
                                i4++;
                            }
                        }
                        if (MyApplication.enforceable && findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.ChatroomInfoUI_2");
                            findAccessibilityNodeInfosByViewId.get(0).performAction(4096);
                            AutoPullFriendsToAllGroupSearchUtils.this.sleep(MyApplication.SCROLL_SPEED);
                        }
                    }
                }
            });
            return;
        }
        LogUtils.log("WS_BABY.ChatroomInfoUI_4.END");
        removeEndView(this.mMyManager);
    }

    public void initSelectNickName() {
        if (MyApplication.enforceable) {
            try {
                new PerformClickUtils2().check(this.autoService, list_select_search_id, this.jumpTime, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        LogUtils.log("WS_BABY_SelectContactUI_2");
                        PerformClickUtils.findViewByIdAndPasteContent(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.list_select_search_id, AutoPullFriendsToAllGroupSearchUtils.this.cardName);
                        new PerformClickUtils2().checkNilNodeId(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.list_select_letter_slip_id, SocializeConstants.CANCLE_RESULTCODE, 200, 40, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                new PerformClickUtils2().checkNodeIdsHasSomeOne(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.list_select_name_id, BaseServiceUtils.search_card_wxh, 0, 200, 40, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        new Thread(new Runnable() {
                                            /* JADX WARNING: Can't wrap try/catch for region: R(8:22|23|24|(4:26|27|28|(4:30|33|34|(2:36|118)(1:117)))|31|33|34|(0)(0)) */
                                            /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0091 */
                                            /* JADX WARNING: Removed duplicated region for block: B:117:0x002f A[SYNTHETIC] */
                                            /* JADX WARNING: Removed duplicated region for block: B:36:0x00ab A[Catch:{ Exception -> 0x0397 }] */
                                            /* Code decompiled incorrectly, please refer to instructions dump. */
                                            public void run() {
                                                /*
                                                    r11 = this;
                                                    java.lang.String r0 = "WS_BABY_SelectContactUI_3"
                                                    com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service.AutoService r0 = r0.autoService     // Catch:{ Exception -> 0x0397 }
                                                    android.view.accessibility.AccessibilityNodeInfo r0 = r0.getRootInActiveWindow()     // Catch:{ Exception -> 0x0397 }
                                                    if (r0 == 0) goto L_0x0397
                                                    boolean r1 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0397 }
                                                    if (r1 == 0) goto L_0x0397
                                                    java.lang.String r1 = com.wx.assistants.service_utils.BaseServiceUtils.list_select_name_id     // Catch:{ Exception -> 0x0397 }
                                                    java.util.List r0 = r0.findAccessibilityNodeInfosByViewId(r1)     // Catch:{ Exception -> 0x0397 }
                                                    r1 = 0
                                                    r2 = 1
                                                    if (r0 == 0) goto L_0x00b6
                                                    int r3 = r0.size()     // Catch:{ Exception -> 0x0397 }
                                                    if (r3 <= 0) goto L_0x00b6
                                                    boolean r3 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0397 }
                                                    if (r3 == 0) goto L_0x00b6
                                                    r3 = 1
                                                    r4 = 0
                                                L_0x002f:
                                                    if (r3 == 0) goto L_0x00b7
                                                    int r5 = r0.size()     // Catch:{ Exception -> 0x0397 }
                                                    if (r4 >= r5) goto L_0x00b7
                                                    boolean r5 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0397 }
                                                    if (r5 == 0) goto L_0x00b7
                                                    java.lang.Object r5 = r0.get(r4)     // Catch:{ Exception -> 0x0397 }
                                                    android.view.accessibility.AccessibilityNodeInfo r5 = (android.view.accessibility.AccessibilityNodeInfo) r5     // Catch:{ Exception -> 0x0397 }
                                                    int r4 = r4 + 1
                                                    if (r5 == 0) goto L_0x002f
                                                    boolean r6 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0397 }
                                                    if (r6 == 0) goto L_0x002f
                                                    java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0397 }
                                                    r6.<init>()     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r7 = "WS_BABY_SelectContactUI_4_"
                                                    r6.append(r7)     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.CharSequence r7 = r5.getText()     // Catch:{ Exception -> 0x0397 }
                                                    r6.append(r7)     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r7 = ""
                                                    r6.append(r7)     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.utils.LogUtils.log(r6)     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r6 = ""
                                                    java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0091 }
                                                    r7.<init>()     // Catch:{ Exception -> 0x0091 }
                                                    java.lang.CharSequence r8 = r5.getText()     // Catch:{ Exception -> 0x0091 }
                                                    r7.append(r8)     // Catch:{ Exception -> 0x0091 }
                                                    java.lang.String r8 = ""
                                                    r7.append(r8)     // Catch:{ Exception -> 0x0091 }
                                                    java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0091 }
                                                    if (r7 == 0) goto L_0x0090
                                                    java.lang.String r6 = ""
                                                    boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x0090 }
                                                    if (r6 != 0) goto L_0x0090
                                                    java.lang.String r6 = " "
                                                    java.lang.String r8 = ""
                                                    java.lang.String r6 = r7.replace(r6, r8)     // Catch:{ Exception -> 0x0090 }
                                                    goto L_0x0091
                                                L_0x0090:
                                                    r6 = r7
                                                L_0x0091:
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r7 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r7 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r7 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r7 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r7 = r7.cardName     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r7 = r7.trim()     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r6 = r6.trim()     // Catch:{ Exception -> 0x0397 }
                                                    boolean r6 = r7.equals(r6)     // Catch:{ Exception -> 0x0397 }
                                                    if (r6 == 0) goto L_0x002f
                                                    java.lang.String r3 = "WS_BABY_SelectContactUI_5"
                                                    com.wx.assistants.utils.LogUtils.log(r3)     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.utils.PerformClickUtils.performClick(r5)     // Catch:{ Exception -> 0x0397 }
                                                    r3 = 0
                                                    goto L_0x002f
                                                L_0x00b6:
                                                    r3 = 1
                                                L_0x00b7:
                                                    if (r3 == 0) goto L_0x01a8
                                                    boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0397 }
                                                    if (r0 == 0) goto L_0x01a8
                                                    java.lang.String r0 = "WS_BABY_SelectContactUI_5656"
                                                    com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service.AutoService r0 = r0.autoService     // Catch:{ Exception -> 0x0397 }
                                                    android.view.accessibility.AccessibilityNodeInfo r0 = r0.getRootInActiveWindow()     // Catch:{ Exception -> 0x0397 }
                                                    if (r0 == 0) goto L_0x01a8
                                                    boolean r4 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0397 }
                                                    if (r4 == 0) goto L_0x01a8
                                                    java.lang.String r4 = com.wx.assistants.service_utils.BaseServiceUtils.search_card_wxh     // Catch:{ Exception -> 0x0397 }
                                                    java.util.List r0 = r0.findAccessibilityNodeInfosByViewId(r4)     // Catch:{ Exception -> 0x0397 }
                                                    if (r0 == 0) goto L_0x01a3
                                                    int r4 = r0.size()     // Catch:{ Exception -> 0x0397 }
                                                    if (r4 <= 0) goto L_0x01a3
                                                    boolean r4 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0397 }
                                                    if (r4 == 0) goto L_0x01a3
                                                    r4 = 0
                                                L_0x00e9:
                                                    if (r3 == 0) goto L_0x01a8
                                                    int r5 = r0.size()     // Catch:{ Exception -> 0x0397 }
                                                    if (r4 >= r5) goto L_0x01a8
                                                    boolean r5 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0397 }
                                                    if (r5 == 0) goto L_0x01a8
                                                    java.lang.Object r5 = r0.get(r4)     // Catch:{ Exception -> 0x0397 }
                                                    android.view.accessibility.AccessibilityNodeInfo r5 = (android.view.accessibility.AccessibilityNodeInfo) r5     // Catch:{ Exception -> 0x0397 }
                                                    int r4 = r4 + 1
                                                    if (r5 == 0) goto L_0x00e9
                                                    java.lang.String r6 = ""
                                                    java.lang.CharSequence r7 = r5.getText()     // Catch:{ Exception -> 0x0397 }
                                                    if (r7 == 0) goto L_0x011c
                                                    java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0397 }
                                                    r6.<init>()     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.CharSequence r7 = r5.getText()     // Catch:{ Exception -> 0x0397 }
                                                    r6.append(r7)     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r7 = ""
                                                    r6.append(r7)     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0397 }
                                                L_0x011c:
                                                    java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0397 }
                                                    r7.<init>()     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r8 = "WS_BABY_SelectContactUI2_"
                                                    r7.append(r8)     // Catch:{ Exception -> 0x0397 }
                                                    r7.append(r6)     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.utils.LogUtils.log(r7)     // Catch:{ Exception -> 0x0397 }
                                                    if (r6 == 0) goto L_0x0142
                                                    java.lang.String r7 = ":"
                                                    boolean r7 = r6.contains(r7)     // Catch:{ Exception -> 0x0397 }
                                                    if (r7 == 0) goto L_0x0142
                                                    java.lang.String r7 = ":"
                                                    java.lang.String[] r6 = r6.split(r7)     // Catch:{ Exception -> 0x0397 }
                                                    r6 = r6[r2]     // Catch:{ Exception -> 0x0397 }
                                                L_0x0142:
                                                    if (r6 == 0) goto L_0x0154
                                                    java.lang.String r7 = ""
                                                    boolean r7 = r7.equals(r6)     // Catch:{ Exception -> 0x0397 }
                                                    if (r7 != 0) goto L_0x0154
                                                    java.lang.String r7 = " "
                                                    java.lang.String r8 = ""
                                                    java.lang.String r6 = r6.replace(r7, r8)     // Catch:{ Exception -> 0x0397 }
                                                L_0x0154:
                                                    java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0397 }
                                                    r7.<init>()     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r8 = "WS_BABY_SelectContactUI3_"
                                                    r7.append(r8)     // Catch:{ Exception -> 0x0397 }
                                                    r7.append(r6)     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.utils.LogUtils.log(r7)     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r7 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r7 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r7 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r7 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r7 = r7.cardName     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r7 = r7.trim()     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r8 = r6.trim()     // Catch:{ Exception -> 0x0397 }
                                                    boolean r7 = r7.equals(r8)     // Catch:{ Exception -> 0x0397 }
                                                    if (r7 == 0) goto L_0x019c
                                                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0397 }
                                                    r3.<init>()     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r7 = "WS_BABY_SelectContactUI4_"
                                                    r3.append(r7)     // Catch:{ Exception -> 0x0397 }
                                                    r3.append(r6)     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.utils.LogUtils.log(r3)     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.utils.PerformClickUtils.performClick(r5)     // Catch:{ Exception -> 0x0397 }
                                                    r3 = 0
                                                    goto L_0x00e9
                                                L_0x019c:
                                                    java.lang.String r5 = "WS_BABY_SelectContactUI_5"
                                                    com.wx.assistants.utils.LogUtils.log(r5)     // Catch:{ Exception -> 0x0397 }
                                                    goto L_0x00e9
                                                L_0x01a3:
                                                    java.lang.String r0 = "WS_BABY_SelectContactUI_5656-null"
                                                    com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x0397 }
                                                L_0x01a8:
                                                    if (r3 != 0) goto L_0x0388
                                                    boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0397 }
                                                    if (r0 == 0) goto L_0x0388
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    r1 = 200(0xc8, float:2.8E-43)
                                                    r0.sleep(r1)     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service.AutoService r0 = r0.autoService     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r1 = com.wx.assistants.service_utils.BaseServiceUtils.complete_id     // Catch:{ Exception -> 0x0397 }
                                                    boolean r0 = com.wx.assistants.utils.PerformClickUtils.findViewIdIsEnabled(r0, r1)     // Catch:{ Exception -> 0x0397 }
                                                    r1 = 2
                                                    if (r0 == 0) goto L_0x02a3
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.access$408(r0)     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    int r0 = r0.startIndexFromUser     // Catch:{ Exception -> 0x0397 }
                                                    if (r0 != r2) goto L_0x0202
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    int r0 = r0.sendGroupNum     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    int r3 = r3.jumpNum     // Catch:{ Exception -> 0x0397 }
                                                    int r0 = r0 + r3
                                                    int r0 = r0 + r2
                                                    goto L_0x0228
                                                L_0x0202:
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    int r0 = r0.sendGroupNum     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    int r3 = r3.jumpNum     // Catch:{ Exception -> 0x0397 }
                                                    int r0 = r0 + r3
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    int r3 = r3.startIndexFromUser     // Catch:{ Exception -> 0x0397 }
                                                    int r0 = r0 + r3
                                                L_0x0228:
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.bean.OperationParameterModel r3 = r3.operationParameterModel     // Catch:{ Exception -> 0x0397 }
                                                    int r3 = r3.getSendCardType()     // Catch:{ Exception -> 0x0397 }
                                                    if (r3 != 0) goto L_0x0248
                                                    android.content.Context r1 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r2 = "pull_friend_to_all_group_num_all"
                                                    java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.utils.SPUtils.put(r1, r2, r0)     // Catch:{ Exception -> 0x0397 }
                                                    goto L_0x0287
                                                L_0x0248:
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.bean.OperationParameterModel r3 = r3.operationParameterModel     // Catch:{ Exception -> 0x0397 }
                                                    int r3 = r3.getSendCardType()     // Catch:{ Exception -> 0x0397 }
                                                    if (r3 != r2) goto L_0x0268
                                                    android.content.Context r1 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r2 = "pull_friend_to_all_group_num_part"
                                                    java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.utils.SPUtils.put(r1, r2, r0)     // Catch:{ Exception -> 0x0397 }
                                                    goto L_0x0287
                                                L_0x0268:
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r2 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r2 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r2 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r2 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.bean.OperationParameterModel r2 = r2.operationParameterModel     // Catch:{ Exception -> 0x0397 }
                                                    int r2 = r2.getSendCardType()     // Catch:{ Exception -> 0x0397 }
                                                    if (r2 != r1) goto L_0x0287
                                                    android.content.Context r1 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r2 = "pull_friend_to_all_group_num_shield"
                                                    java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.utils.SPUtils.put(r1, r2, r0)     // Catch:{ Exception -> 0x0397 }
                                                L_0x0287:
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service.AutoService r0 = r0.autoService     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r1 = com.wx.assistants.service_utils.BaseServiceUtils.complete_id     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r0, r1)     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    r0.initChatRoomInfoUIBack()     // Catch:{ Exception -> 0x0397 }
                                                    goto L_0x0397
                                                L_0x02a3:
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.access$1508(r0)     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    int r0 = r0.startIndexFromUser     // Catch:{ Exception -> 0x0397 }
                                                    if (r0 != r2) goto L_0x02d7
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    int r0 = r0.sendGroupNum     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    int r3 = r3.jumpNum     // Catch:{ Exception -> 0x0397 }
                                                    int r0 = r0 + r3
                                                    int r0 = r0 + r2
                                                    goto L_0x02fd
                                                L_0x02d7:
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    int r0 = r0.sendGroupNum     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    int r3 = r3.jumpNum     // Catch:{ Exception -> 0x0397 }
                                                    int r0 = r0 + r3
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    int r3 = r3.startIndexFromUser     // Catch:{ Exception -> 0x0397 }
                                                    int r0 = r0 + r3
                                                L_0x02fd:
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.bean.OperationParameterModel r3 = r3.operationParameterModel     // Catch:{ Exception -> 0x0397 }
                                                    int r3 = r3.getSendCardType()     // Catch:{ Exception -> 0x0397 }
                                                    if (r3 != 0) goto L_0x031d
                                                    android.content.Context r2 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r3 = "pull_friend_to_all_group_num_all"
                                                    java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.utils.SPUtils.put(r2, r3, r0)     // Catch:{ Exception -> 0x0397 }
                                                    goto L_0x035c
                                                L_0x031d:
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r3 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.bean.OperationParameterModel r3 = r3.operationParameterModel     // Catch:{ Exception -> 0x0397 }
                                                    int r3 = r3.getSendCardType()     // Catch:{ Exception -> 0x0397 }
                                                    if (r3 != r2) goto L_0x033d
                                                    android.content.Context r2 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r3 = "pull_friend_to_all_group_num_part"
                                                    java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.utils.SPUtils.put(r2, r3, r0)     // Catch:{ Exception -> 0x0397 }
                                                    goto L_0x035c
                                                L_0x033d:
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r2 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r2 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r2 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r2 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.bean.OperationParameterModel r2 = r2.operationParameterModel     // Catch:{ Exception -> 0x0397 }
                                                    int r2 = r2.getSendCardType()     // Catch:{ Exception -> 0x0397 }
                                                    if (r2 != r1) goto L_0x035c
                                                    android.content.Context r2 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r3 = "pull_friend_to_all_group_num_shield"
                                                    java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.utils.SPUtils.put(r2, r3, r0)     // Catch:{ Exception -> 0x0397 }
                                                L_0x035c:
                                                    com.wx.assistants.utils.PerformClickUtils2 r4 = new com.wx.assistants.utils.PerformClickUtils2     // Catch:{ Exception -> 0x0397 }
                                                    r4.<init>()     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service.AutoService r5 = r0.autoService     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r6 = com.wx.assistants.service_utils.BaseServiceUtils.back_contact_id     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    int r0 = r0.backTime     // Catch:{ Exception -> 0x0397 }
                                                    int r7 = r0 * 2
                                                    r8 = 100
                                                    r9 = 50
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1$1$1 r10 = new com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1$1$1     // Catch:{ Exception -> 0x0397 }
                                                    r10.<init>()     // Catch:{ Exception -> 0x0397 }
                                                    r4.check((com.wx.assistants.service.AutoService) r5, (java.lang.String) r6, (int) r7, (int) r8, (int) r9, (com.wx.assistants.utils.PerformClickUtils2.OnCheckListener) r10)     // Catch:{ Exception -> 0x0397 }
                                                    goto L_0x0397
                                                L_0x0388:
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9$1 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils$9 r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.this     // Catch:{ Exception -> 0x0397 }
                                                    com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager     // Catch:{ Exception -> 0x0397 }
                                                    java.lang.String r1 = "没有找到您要拉人的昵称\n核查您检索的内容"
                                                    com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r0, r1)     // Catch:{ Exception -> 0x0397 }
                                                L_0x0397:
                                                    return
                                                */
                                                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils.AnonymousClass9.AnonymousClass1.AnonymousClass1.AnonymousClass1.run():void");
                                            }
                                        }).start();
                                    }

                                    public void nuFind() {
                                        BaseServiceUtils.updateBottomText(AutoPullFriendsToAllGroupSearchUtils.this.mMyManager, "没有找到您要拉人的昵称\n核查您检索的内容");
                                    }
                                });
                            }
                        });
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void executeMain() {
        try {
            LogUtils.log("WS_BABY.LauncherUI");
            new PerformClickUtils2().checkString(this.autoService, "通讯录", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findTextAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, "通讯录");
                    new PerformClickUtils2().checkString(AutoPullFriendsToAllGroupSearchUtils.this.autoService, "群聊", BaseServiceUtils.executeSpeedSetting + 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, "群聊");
                            AutoPullFriendsToAllGroupSearchUtils.this.initChatroomContactUI();
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void middleViewShow() {
        if (this.sendResult == null || "".equals(this.sendResult)) {
            MyWindowManager myWindowManager = this.mMyManager;
            updateMiddleText(myWindowManager, "拉人进群", "微商宝贝帮您邀请了" + this.sendGroupNum + "个群");
            return;
        }
        updateMiddleText(this.mMyManager, "拉人进群结束", this.sendResult);
    }

    public void endViewShow() {
        try {
            if (this.operationParameterModel == null) {
                showBottomView(this.mMyManager, "正在向微信群拉人进群\n请不要操作微信");
            } else if (this.operationParameterModel.getSendCardType() == 0) {
                MyWindowManager myWindowManager = this.mMyManager;
                showBottomView(myWindowManager, "正在拉[" + this.cardName + "]到[全部群]\n从第 " + this.startIndexFromUser + " 个微信群开始");
            } else if (this.operationParameterModel.getSendCardType() == 1) {
                MyWindowManager myWindowManager2 = this.mMyManager;
                showBottomView(myWindowManager2, "正在拉[" + this.cardName + "]到[部分群]\n从部分群中第 " + this.startIndexFromUser + " 个微信群开始");
            } else if (this.operationParameterModel.getSendCardType() == 2) {
                MyWindowManager myWindowManager3 = this.mMyManager;
                showBottomView(myWindowManager3, "正在拉[" + this.cardName + "]到[部分群]\n从部分群中第 " + this.startIndexFromUser + " 个微信群开始");
            }
            new Thread(new Runnable() {
                public void run() {
                    try {
                        AutoPullFriendsToAllGroupSearchUtils.this.executeMain();
                    } catch (Exception unused) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
