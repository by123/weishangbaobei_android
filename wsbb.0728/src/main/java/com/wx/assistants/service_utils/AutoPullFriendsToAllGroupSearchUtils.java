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

    private AutoPullFriendsToAllGroupSearchUtils() {
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

    public static AutoPullFriendsToAllGroupSearchUtils getInstance() {
        instance = new AutoPullFriendsToAllGroupSearchUtils();
        return instance;
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
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeAddFriend() {
        if (MyApplication.enforceable) {
            new PerformClickUtils2().checkNodeAllIds(this.autoService, default_list_id, list_head_img_id, executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    int i2;
                    LogUtils.log("WS_BABY.ChatroomInfoUI_0");
                    int i3 = 2;
                    for (int i4 = 0; i4 < i3; i4++) {
                        AccessibilityNodeInfo rootInActiveWindow = AutoPullFriendsToAllGroupSearchUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow == null || !MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.ChatroomInfoUI_3");
                            return;
                        }
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.default_list_id);
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_head_img_id);
                        if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.ChatroomInfoUI_1");
                            int i5 = 0;
                            while (true) {
                                if (i5 >= findAccessibilityNodeInfosByViewId2.size()) {
                                    break;
                                } else if (!MyApplication.enforceable) {
                                    i2 = i3;
                                    break;
                                } else {
                                    AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId2.get(i5);
                                    if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getContentDescription() == null)) {
                                        if ((accessibilityNodeInfo.getContentDescription() + "").equals("添加成员") && MyApplication.enforceable) {
                                            PerformClickUtils.performClick(accessibilityNodeInfo);
                                            AutoPullFriendsToAllGroupSearchUtils.this.initSelectNickName();
                                            i2 = 0;
                                            break;
                                        }
                                    }
                                    i5++;
                                }
                            }
                            if (MyApplication.enforceable && findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY.ChatroomInfoUI_2");
                                findAccessibilityNodeInfosByViewId.get(0).performAction(4096);
                                AutoPullFriendsToAllGroupSearchUtils.this.sleep(MyApplication.SCROLL_SPEED);
                            }
                            i3 = i2;
                        }
                        i2 = i3;
                        LogUtils.log("WS_BABY.ChatroomInfoUI_2");
                        findAccessibilityNodeInfosByViewId.get(0).performAction(4096);
                        AutoPullFriendsToAllGroupSearchUtils.this.sleep(MyApplication.SCROLL_SPEED);
                        i3 = i2;
                    }
                }

                public void nuFind() {
                }
            });
            return;
        }
        LogUtils.log("WS_BABY.ChatroomInfoUI_4.END");
        removeEndView(this.mMyManager);
    }

    public void executeMain() {
        try {
            LogUtils.log("WS_BABY.LauncherUI");
            new PerformClickUtils2().checkString(this.autoService, "通讯录", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findTextAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, "通讯录");
                    new PerformClickUtils2().checkString(AutoPullFriendsToAllGroupSearchUtils.this.autoService, "群聊", BaseServiceUtils.executeSpeedSetting + 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, "群聊");
                            AutoPullFriendsToAllGroupSearchUtils.this.initChatroomContactUI();
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

    public void initChatRoomInfoUIBack() {
        new PerformClickUtils2().checkNilString(this.autoService, "正在添加", (int) SocializeConstants.CANCLE_RESULTCODE, 100, 300, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY.ChatroomInfoUI_1");
                new PerformClickUtils2().check(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.dialog_ok_id, 100, (int) SocializeConstants.CANCLE_RESULTCODE, 5, false, (PerformClickUtils2.OnCheckListener2) new PerformClickUtils2.OnCheckListener2() {
                    public void end() {
                        if (MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.end");
                            new PerformClickUtils2().checkLoad(AutoPullFriendsToAllGroupSearchUtils.this.autoService, "正在发送", 0, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.end2");
                                    new PerformClickUtils2().checkDialogToBack(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.back_contact_id, BaseServiceUtils.dialog_ok_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 50, new PerformClickUtils2.OnCheckListener() {
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

                                        public void nuFind() {
                                        }
                                    });
                                }

                                public void nuFind() {
                                }
                            });
                        }
                    }

                    public void executeIndex(int i) {
                        MyWindowManager myWindowManager = AutoPullFriendsToAllGroupSearchUtils.this.mMyManager;
                        BaseServiceUtils.updateBottomText(myWindowManager, "执行下一个群，倒计时 " + i + " 秒");
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
                            } catch (Exception e) {
                            }
                            AutoPullFriendsToAllGroupSearchUtils.this.sleep(200);
                        }
                        PerformClickUtils.findViewIdAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                    }

                    public void nuFind() {
                    }
                });
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.ChatroomInfoUI_ADD");
            }
        });
    }

    public void initChatRoomInfoUIFirst() {
        executeAddFriend();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00dc, code lost:
        com.wx.assistants.utils.LogUtils.log("WS_BABY.st.3");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    public void initChatroomContactUI() {
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
                                            boolean z;
                                            String str;
                                            try {
                                                AccessibilityNodeInfo rootInActiveWindow = AutoPullFriendsToAllGroupSearchUtils.this.autoService.getRootInActiveWindow();
                                                if (rootInActiveWindow != null) {
                                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
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
                                                                        if (accessibilityNodeInfo.getText() != null) {
                                                                            str = accessibilityNodeInfo.getText() + "";
                                                                        } else {
                                                                            str = "";
                                                                        }
                                                                        for (int i2 = 0; i2 < 10 && str.startsWith("@"); i2++) {
                                                                            str = AutoPullFriendsToAllGroupSearchUtils.this.groupNames.substring(1);
                                                                        }
                                                                        LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + AutoPullFriendsToAllGroupSearchUtils.this.groupNames);
                                                                        if (str != null && !"".equals(str) && str.equals(AutoPullFriendsToAllGroupSearchUtils.this.groupNames)) {
                                                                            LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + AutoPullFriendsToAllGroupSearchUtils.this.groupNames + "@@@@");
                                                                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(i));
                                                                            AutoPullFriendsToAllGroupSearchUtils.this.initChattingUIFirst();
                                                                            z = true;
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
                                            } catch (Exception e) {
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
                new PerformClickUtils2().checkNodeAllIds(this.autoService, grout_friend_list_id, group_list_item_name_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        AccessibilityNodeInfo accessibilityNodeInfo;
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                        AccessibilityNodeInfo accessibilityNodeInfo2;
                        while (AutoPullFriendsToAllGroupSearchUtils.this.isWhile && MyApplication.enforceable) {
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
                                    for (int i2 = 1; i2 < AutoPullFriendsToAllGroupSearchUtils.this.startIndexFromUser; i2++) {
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
                                                    boolean z;
                                                    String str;
                                                    try {
                                                        AccessibilityNodeInfo rootInActiveWindow = AutoPullFriendsToAllGroupSearchUtils.this.autoService.getRootInActiveWindow();
                                                        if (rootInActiveWindow != null && MyApplication.enforceable) {
                                                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
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
                                                                                if (accessibilityNodeInfo.getText() != null) {
                                                                                    str = accessibilityNodeInfo.getText() + "";
                                                                                } else {
                                                                                    str = "";
                                                                                }
                                                                                LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + AutoPullFriendsToAllGroupSearchUtils.this.groupNames);
                                                                                if (str != null && !"".equals(str) && str.equals(AutoPullFriendsToAllGroupSearchUtils.this.groupNames)) {
                                                                                    LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + AutoPullFriendsToAllGroupSearchUtils.this.groupNames + "@@@@");
                                                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(i));
                                                                                    AutoPullFriendsToAllGroupSearchUtils.this.initChattingUIFirst();
                                                                                    z = true;
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
                                                    } catch (Exception e) {
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

                                public void nuFind() {
                                }
                            });
                        }
                    }

                    public void nuFind() {
                    }
                });
            }
        } else if (this.spaceTime > 0) {
            new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                public void end() {
                    AutoPullFriendsToAllGroupSearchUtils.this.initRoomContact();
                }

                public void surplus(int i) {
                    MyWindowManager myWindowManager = AutoPullFriendsToAllGroupSearchUtils.this.mMyManager;
                    BaseServiceUtils.updateBottomText(myWindowManager, "已邀请 " + AutoPullFriendsToAllGroupSearchUtils.this.sendGroupNum + " 个群,剩余 " + AutoPullFriendsToAllGroupSearchUtils.this.groupList.size() + " 个群\n正在延时等待，倒计时 " + i + " 秒");
                }
            });
        } else {
            initRoomContact();
        }
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

    public void initChattingUIFirst() {
        new PerformClickUtils2().check(this.autoService, contact_title_id, 0, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                if (PerformClickUtils.getTextByNodeId(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.contact_title_id).contains("(500)")) {
                    AutoPullFriendsToAllGroupSearchUtils.access$1508(AutoPullFriendsToAllGroupSearchUtils.this);
                    int access$400 = AutoPullFriendsToAllGroupSearchUtils.this.startIndexFromUser == 1 ? AutoPullFriendsToAllGroupSearchUtils.this.sendGroupNum + AutoPullFriendsToAllGroupSearchUtils.this.jumpNum + 1 : AutoPullFriendsToAllGroupSearchUtils.this.sendGroupNum + AutoPullFriendsToAllGroupSearchUtils.this.jumpNum + AutoPullFriendsToAllGroupSearchUtils.this.startIndexFromUser;
                    if (AutoPullFriendsToAllGroupSearchUtils.this.operationParameterModel.getSendCardType() == 0) {
                        SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_num_all", Integer.valueOf(access$400));
                    } else if (AutoPullFriendsToAllGroupSearchUtils.this.operationParameterModel.getSendCardType() == 1) {
                        SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_num_part", Integer.valueOf(access$400));
                    } else if (AutoPullFriendsToAllGroupSearchUtils.this.operationParameterModel.getSendCardType() == 2) {
                        SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_num_shield", Integer.valueOf(access$400));
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

            public void nuFind() {
            }
        });
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
                                boolean z;
                                String str;
                                try {
                                    AccessibilityNodeInfo rootInActiveWindow = AutoPullFriendsToAllGroupSearchUtils.this.autoService.getRootInActiveWindow();
                                    if (rootInActiveWindow != null && MyApplication.enforceable) {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
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
                                                            if (accessibilityNodeInfo.getText() != null) {
                                                                str = accessibilityNodeInfo.getText() + "";
                                                            } else {
                                                                str = "";
                                                            }
                                                            LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + AutoPullFriendsToAllGroupSearchUtils.this.groupNames);
                                                            if (str != null && !"".equals(str) && str.equals(AutoPullFriendsToAllGroupSearchUtils.this.groupNames)) {
                                                                LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + AutoPullFriendsToAllGroupSearchUtils.this.groupNames + "@@@@");
                                                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(i));
                                                                AutoPullFriendsToAllGroupSearchUtils.this.initChattingUIFirst();
                                                                z = true;
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
                                } catch (Exception e) {
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

            public void nuFind() {
            }
        });
    }

    public void initSelectNickName() {
        if (MyApplication.enforceable) {
            try {
                new PerformClickUtils2().check(this.autoService, list_select_search_id, this.jumpTime, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY_SelectContactUI_2");
                        PerformClickUtils.findViewByIdAndPasteContent(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.list_select_search_id, AutoPullFriendsToAllGroupSearchUtils.this.cardName);
                        new PerformClickUtils2().checkNilNodeId(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.list_select_letter_slip_id, SocializeConstants.CANCLE_RESULTCODE, 200, 40, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                new PerformClickUtils2().checkNodeIdsHasSomeOne(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.list_select_name_id, BaseServiceUtils.search_card_wxh, 0, 200, 40, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        new Thread(new Runnable() {
                                            public void run() {
                                                boolean z;
                                                try {
                                                    LogUtils.log("WS_BABY_SelectContactUI_3");
                                                    AccessibilityNodeInfo rootInActiveWindow = AutoPullFriendsToAllGroupSearchUtils.this.autoService.getRootInActiveWindow();
                                                    if (rootInActiveWindow != null && MyApplication.enforceable) {
                                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_name_id);
                                                        if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || !MyApplication.enforceable) {
                                                            z = true;
                                                        } else {
                                                            int i = 0;
                                                            boolean z2 = true;
                                                            while (z2 && i < findAccessibilityNodeInfosByViewId.size() && MyApplication.enforceable) {
                                                                AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i);
                                                                int i2 = i + 1;
                                                                if (accessibilityNodeInfo != null && MyApplication.enforceable) {
                                                                    LogUtils.log("WS_BABY_SelectContactUI_4_" + accessibilityNodeInfo.getText() + "");
                                                                    String str = "";
                                                                    try {
                                                                        str = accessibilityNodeInfo.getText() + "";
                                                                        if (str != null) {
                                                                            try {
                                                                                if (!"".equals(str)) {
                                                                                    str = str.replace(" ", "");
                                                                                }
                                                                            } catch (Exception e) {
                                                                            }
                                                                        }
                                                                    } catch (Exception e2) {
                                                                    }
                                                                    if (AutoPullFriendsToAllGroupSearchUtils.this.cardName.trim().equals(str.trim())) {
                                                                        LogUtils.log("WS_BABY_SelectContactUI_5");
                                                                        PerformClickUtils.performClick(accessibilityNodeInfo);
                                                                        i = i2;
                                                                        z2 = false;
                                                                    }
                                                                }
                                                                i = i2;
                                                            }
                                                            z = z2;
                                                        }
                                                        if (z && MyApplication.enforceable) {
                                                            LogUtils.log("WS_BABY_SelectContactUI_5656");
                                                            AccessibilityNodeInfo rootInActiveWindow2 = AutoPullFriendsToAllGroupSearchUtils.this.autoService.getRootInActiveWindow();
                                                            if (rootInActiveWindow2 != null && MyApplication.enforceable) {
                                                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.search_card_wxh);
                                                                if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0 || !MyApplication.enforceable) {
                                                                    LogUtils.log("WS_BABY_SelectContactUI_5656-null");
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
                                                                            LogUtils.log("WS_BABY_SelectContactUI2_" + str2);
                                                                            if (str2 != null && str2.contains(":")) {
                                                                                str2 = str2.split(":")[1];
                                                                            }
                                                                            if (str2 != null && !"".equals(str2)) {
                                                                                str2 = str2.replace(" ", "");
                                                                            }
                                                                            LogUtils.log("WS_BABY_SelectContactUI3_" + str2);
                                                                            if (AutoPullFriendsToAllGroupSearchUtils.this.cardName.trim().equals(str2.trim())) {
                                                                                LogUtils.log("WS_BABY_SelectContactUI4_" + str2);
                                                                                PerformClickUtils.performClick(accessibilityNodeInfo2);
                                                                                z3 = false;
                                                                            } else {
                                                                                LogUtils.log("WS_BABY_SelectContactUI_5");
                                                                            }
                                                                        }
                                                                    }
                                                                    z = z3;
                                                                }
                                                            }
                                                        }
                                                        if (z || !MyApplication.enforceable) {
                                                            BaseServiceUtils.updateBottomText(AutoPullFriendsToAllGroupSearchUtils.this.mMyManager, "没有找到您要拉人的昵称\n核查您检索的内容");
                                                            return;
                                                        }
                                                        AutoPullFriendsToAllGroupSearchUtils.this.sleep(200);
                                                        if (PerformClickUtils.findViewIdIsEnabled(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.complete_id)) {
                                                            AutoPullFriendsToAllGroupSearchUtils.access$408(AutoPullFriendsToAllGroupSearchUtils.this);
                                                            int access$400 = AutoPullFriendsToAllGroupSearchUtils.this.startIndexFromUser == 1 ? AutoPullFriendsToAllGroupSearchUtils.this.sendGroupNum + AutoPullFriendsToAllGroupSearchUtils.this.jumpNum + 1 : AutoPullFriendsToAllGroupSearchUtils.this.sendGroupNum + AutoPullFriendsToAllGroupSearchUtils.this.jumpNum + AutoPullFriendsToAllGroupSearchUtils.this.startIndexFromUser;
                                                            if (AutoPullFriendsToAllGroupSearchUtils.this.operationParameterModel.getSendCardType() == 0) {
                                                                SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_num_all", Integer.valueOf(access$400));
                                                            } else if (AutoPullFriendsToAllGroupSearchUtils.this.operationParameterModel.getSendCardType() == 1) {
                                                                SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_num_part", Integer.valueOf(access$400));
                                                            } else if (AutoPullFriendsToAllGroupSearchUtils.this.operationParameterModel.getSendCardType() == 2) {
                                                                SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_num_shield", Integer.valueOf(access$400));
                                                            }
                                                            PerformClickUtils.findViewIdAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.complete_id);
                                                            AutoPullFriendsToAllGroupSearchUtils.this.initChatRoomInfoUIBack();
                                                            return;
                                                        }
                                                        AutoPullFriendsToAllGroupSearchUtils.access$1508(AutoPullFriendsToAllGroupSearchUtils.this);
                                                        int access$4002 = AutoPullFriendsToAllGroupSearchUtils.this.startIndexFromUser == 1 ? AutoPullFriendsToAllGroupSearchUtils.this.sendGroupNum + AutoPullFriendsToAllGroupSearchUtils.this.jumpNum + 1 : AutoPullFriendsToAllGroupSearchUtils.this.sendGroupNum + AutoPullFriendsToAllGroupSearchUtils.this.jumpNum + AutoPullFriendsToAllGroupSearchUtils.this.startIndexFromUser;
                                                        if (AutoPullFriendsToAllGroupSearchUtils.this.operationParameterModel.getSendCardType() == 0) {
                                                            SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_num_all", Integer.valueOf(access$4002));
                                                        } else if (AutoPullFriendsToAllGroupSearchUtils.this.operationParameterModel.getSendCardType() == 1) {
                                                            SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_num_part", Integer.valueOf(access$4002));
                                                        } else if (AutoPullFriendsToAllGroupSearchUtils.this.operationParameterModel.getSendCardType() == 2) {
                                                            SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_num_shield", Integer.valueOf(access$4002));
                                                        }
                                                        new PerformClickUtils2().check(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.back_contact_id, AutoPullFriendsToAllGroupSearchUtils.this.backTime * 2, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                                            public void find(int i) {
                                                                PerformClickUtils.findViewIdAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.back_contact_id);
                                                                new PerformClickUtils2().check(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.back_contact_id, AutoPullFriendsToAllGroupSearchUtils.this.backTime * 2, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                                                    public void find(int i) {
                                                                        PerformClickUtils.findViewIdAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.back_contact_id);
                                                                        new PerformClickUtils2().check(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.back_id, AutoPullFriendsToAllGroupSearchUtils.this.backTime * 2, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                                                            public void find(int i) {
                                                                                PerformClickUtils.findViewIdAndClick(AutoPullFriendsToAllGroupSearchUtils.this.autoService, BaseServiceUtils.back_id);
                                                                                LogUtils.log("WS_BABY_TYPE_5");
                                                                                AutoPullFriendsToAllGroupSearchUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                                                                AutoPullFriendsToAllGroupSearchUtils.this.executeMain();
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
                                                } catch (Exception e3) {
                                                }
                                            }
                                        }).start();
                                    }

                                    public void nuFind() {
                                        BaseServiceUtils.updateBottomText(AutoPullFriendsToAllGroupSearchUtils.this.mMyManager, "没有找到您要拉人的昵称\n核查您检索的内容");
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
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        if (this.sendResult == null || "".equals(this.sendResult)) {
            MyWindowManager myWindowManager = this.mMyManager;
            updateMiddleText(myWindowManager, "拉人进群", "微商宝贝帮您邀请了" + this.sendGroupNum + "个群");
            return;
        }
        updateMiddleText(this.mMyManager, "拉人进群结束", this.sendResult);
    }
}
