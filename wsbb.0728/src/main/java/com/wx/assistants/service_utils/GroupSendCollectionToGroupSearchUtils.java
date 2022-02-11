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
import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class GroupSendCollectionToGroupSearchUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static GroupSendCollectionToGroupSearchUtils instance;
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
    public boolean isFirstFav = true;
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
    public int jumpTime = 200;
    /* access modifiers changed from: private */
    public String lastName;
    /* access modifiers changed from: private */
    public int maxSendExecuteNum = 0;
    /* access modifiers changed from: private */
    public OperationParameterModel operationParameterModel;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> originList = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public String sayContent;
    /* access modifiers changed from: private */
    public int sendGroupNum = 0;
    /* access modifiers changed from: private */
    public String sendResult = "";
    private int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startIndexFromUser = 1;

    private GroupSendCollectionToGroupSearchUtils() {
    }

    static /* synthetic */ int access$310(GroupSendCollectionToGroupSearchUtils groupSendCollectionToGroupSearchUtils) {
        int i = groupSendCollectionToGroupSearchUtils.circulateOuterSize;
        groupSendCollectionToGroupSearchUtils.circulateOuterSize = i - 1;
        return i;
    }

    static /* synthetic */ int access$508(GroupSendCollectionToGroupSearchUtils groupSendCollectionToGroupSearchUtils) {
        int i = groupSendCollectionToGroupSearchUtils.sendGroupNum;
        groupSendCollectionToGroupSearchUtils.sendGroupNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$908(GroupSendCollectionToGroupSearchUtils groupSendCollectionToGroupSearchUtils) {
        int i = groupSendCollectionToGroupSearchUtils.startIndex;
        groupSendCollectionToGroupSearchUtils.startIndex = i + 1;
        return i;
    }

    public static GroupSendCollectionToGroupSearchUtils getInstance() {
        instance = new GroupSendCollectionToGroupSearchUtils();
        return instance;
    }

    public void clickCollection() {
        LogUtils.log("WS_BABY_FavSelectUI.clickCollection.1");
        PerformClickUtils.findViewIdAndClick(this.autoService, send_add_id);
        new PerformClickUtils2().checkCardCollectionName(this.autoService, send_add_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 30, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_FavSelectUI.clickCollection.2");
                PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendCollectionToGroupSearchUtils.this.autoService, "我的收藏", BaseServiceUtils.album_id);
                LogUtils.log("WS_BABY_FavSelectUI.clickCollection.3");
                GroupSendCollectionToGroupSearchUtils.this.initFavSelectUI();
            }

            public void nuFind() {
            }
        });
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
                int circulateInnerSize2 = this.operationParameterModel.getCirculateInnerSize();
                if (this.circulateMode != 0 || this.circulateOuterSize <= 1) {
                    if (this.circulateMode != 1 || circulateInnerSize2 <= 1) {
                        if (this.operationParameterModel.getSendCardType() == 0) {
                            MyWindowManager myWindowManager = this.mMyManager;
                            showBottomView(myWindowManager, "从第 " + this.startIndexFromUser + " 个开始群发收藏");
                        } else if (this.operationParameterModel.getSendCardType() == 1) {
                            MyWindowManager myWindowManager2 = this.mMyManager;
                            showBottomView(myWindowManager2, "从部分群中第 " + this.startIndexFromUser + " 个开始群发收藏");
                        } else if (this.operationParameterModel.getSendCardType() == 2) {
                            MyWindowManager myWindowManager3 = this.mMyManager;
                            showBottomView(myWindowManager3, "从部分群中第 " + this.startIndexFromUser + " 个开始群发收藏");
                        }
                    } else if (this.operationParameterModel.getSendCardType() == 0) {
                        MyWindowManager myWindowManager4 = this.mMyManager;
                        showBottomView(myWindowManager4, "从第 " + this.startIndexFromUser + " 个微信群开始群发\n循环群发 " + circulateInnerSize2 + " 次");
                    } else if (this.operationParameterModel.getSendCardType() == 1) {
                        MyWindowManager myWindowManager5 = this.mMyManager;
                        showBottomView(myWindowManager5, "从部分群中第 " + this.startIndexFromUser + " 个微信群开始群发\n循环群发 " + circulateInnerSize2 + " 次");
                    } else if (this.operationParameterModel.getSendCardType() == 2) {
                        MyWindowManager myWindowManager6 = this.mMyManager;
                        showBottomView(myWindowManager6, "从部分群中第 " + this.startIndexFromUser + " 个微信群开始群发\n循环群发 " + circulateInnerSize2 + " 次");
                    }
                } else if (this.operationParameterModel.getSendCardType() == 0) {
                    MyWindowManager myWindowManager7 = this.mMyManager;
                    StringBuilder sb = new StringBuilder();
                    sb.append("从第 ");
                    sb.append(this.startIndexFromUser);
                    sb.append(" 个微信群开始群发收藏\n正在外循环第 ");
                    if ((circulateOutSize - this.circulateOuterSize) + 1 <= circulateOutSize) {
                        circulateOutSize = (circulateOutSize - this.circulateOuterSize) + 1;
                    }
                    sb.append(circulateOutSize);
                    sb.append(" 轮群发");
                    showBottomView(myWindowManager7, sb.toString());
                } else if (this.operationParameterModel.getSendCardType() == 1) {
                    MyWindowManager myWindowManager8 = this.mMyManager;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("从部分群中第 ");
                    sb2.append(this.startIndexFromUser);
                    sb2.append(" 个微信群开始群发收藏\n正在外循环第 ");
                    if ((circulateOutSize - this.circulateOuterSize) + 1 <= circulateOutSize) {
                        circulateOutSize = (circulateOutSize - this.circulateOuterSize) + 1;
                    }
                    sb2.append(circulateOutSize);
                    sb2.append(" 轮群发");
                    showBottomView(myWindowManager8, sb2.toString());
                } else if (this.operationParameterModel.getSendCardType() == 2) {
                    MyWindowManager myWindowManager9 = this.mMyManager;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("从部分群中第 ");
                    sb3.append(this.startIndexFromUser);
                    sb3.append(" 个微信群开始群发收藏\n正在外循环第 ");
                    if ((circulateOutSize - this.circulateOuterSize) + 1 <= circulateOutSize) {
                        circulateOutSize = (circulateOutSize - this.circulateOuterSize) + 1;
                    }
                    sb3.append(circulateOutSize);
                    sb3.append(" 轮群发");
                    showBottomView(myWindowManager9, sb3.toString());
                }
            } else {
                showBottomView(this.mMyManager, "正在向微信群群发收藏\n请不要操作微信");
            }
            new Thread(new Runnable() {
                public void run() {
                    try {
                        GroupSendCollectionToGroupSearchUtils.this.executeMain();
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
                    PerformClickUtils.findTextAndClick(GroupSendCollectionToGroupSearchUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(GroupSendCollectionToGroupSearchUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(GroupSendCollectionToGroupSearchUtils.this.autoService, "通讯录");
                    new PerformClickUtils2().checkString(GroupSendCollectionToGroupSearchUtils.this.autoService, "群聊", BaseServiceUtils.executeSpeedSetting + 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(GroupSendCollectionToGroupSearchUtils.this.autoService, "群聊");
                            GroupSendCollectionToGroupSearchUtils.this.initChatRoomContactUI();
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
                    GroupSendCollectionToGroupSearchUtils.this.executeMain();
                }
            });
        } else {
            new PerformClickUtils2().check(this.autoService, voice_left_id, 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY.ChattingUI.back1");
                    GroupSendCollectionToGroupSearchUtils.this.initFirstChattingUI(0);
                }

                public void nuFind() {
                }
            });
        }
    }

    public void initChatRoomContactUI() {
        this.circulateMode = this.operationParameterModel.getCirculateMode();
        this.circulateInnerSize = this.operationParameterModel.getCirculateInnerSize();
        if (this.groupList == null || this.groupList.size() <= 0 || !MyApplication.enforceable) {
            LogUtils.log("WS_BABY.ChatroomContactUI_1");
            if (this.isEnd) {
                removeEndView(this.mMyManager);
            } else if (this.operationParameterModel.getSendCardType() != 1 || !MyApplication.enforceable) {
                new PerformClickUtils2().checkNodeAllIds(this.autoService, grout_friend_list_id, group_list_item_name_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        AccessibilityNodeInfo accessibilityNodeInfo;
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                        AccessibilityNodeInfo accessibilityNodeInfo2;
                        while (GroupSendCollectionToGroupSearchUtils.this.isWhile && MyApplication.enforceable) {
                            AccessibilityNodeInfo rootInActiveWindow = GroupSendCollectionToGroupSearchUtils.this.autoService.getRootInActiveWindow();
                            if (rootInActiveWindow != null && MyApplication.enforceable) {
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.grout_friend_list_id);
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
                                if (findAccessibilityNodeInfosByViewId3 != null && !findAccessibilityNodeInfosByViewId3.isEmpty() && MyApplication.enforceable) {
                                    if (GroupSendCollectionToGroupSearchUtils.this.startIndex < findAccessibilityNodeInfosByViewId3.size() && MyApplication.enforceable) {
                                        AccessibilityNodeInfo accessibilityNodeInfo3 = findAccessibilityNodeInfosByViewId3.get(GroupSendCollectionToGroupSearchUtils.this.startIndex);
                                        if (accessibilityNodeInfo3 != null && MyApplication.enforceable) {
                                            CharSequence text = accessibilityNodeInfo3.getText();
                                            String str = "";
                                            if (text != null && !"".equals(text)) {
                                                str = text.toString();
                                            }
                                            if (GroupSendCollectionToGroupSearchUtils.this.groupList.contains(str)) {
                                                GroupSendCollectionToGroupSearchUtils.access$908(GroupSendCollectionToGroupSearchUtils.this);
                                            } else {
                                                GroupSendCollectionToGroupSearchUtils.this.groupList.add(str);
                                                GroupSendCollectionToGroupSearchUtils.access$908(GroupSendCollectionToGroupSearchUtils.this);
                                            }
                                        }
                                    } else if (GroupSendCollectionToGroupSearchUtils.this.startIndex >= findAccessibilityNodeInfosByViewId3.size() && MyApplication.enforceable && findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId2.get(0)) != null && MyApplication.enforceable) {
                                        PerformClickUtils.scroll(accessibilityNodeInfo);
                                        new PerformClickUtils2().checkSyn2(GroupSendCollectionToGroupSearchUtils.this.autoService, BaseServiceUtils.grout_friend_list_id, BaseServiceUtils.group_list_item_name_id, 0, 10, 100, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                GroupSendCollectionToGroupSearchUtils.this.sleep(100);
                                                PrintStream printStream = System.out;
                                                printStream.println("WS_BABY_TIME.end2 = " + System.currentTimeMillis());
                                            }

                                            public void nuFind() {
                                            }
                                        });
                                        AccessibilityNodeInfo rootInActiveWindow2 = GroupSendCollectionToGroupSearchUtils.this.autoService.getRootInActiveWindow();
                                        if (rootInActiveWindow2 != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1)) != null && MyApplication.enforceable) {
                                            String str2 = accessibilityNodeInfo2.getText() + "";
                                            if (GroupSendCollectionToGroupSearchUtils.this.lastName.equals(str2)) {
                                                boolean unused = GroupSendCollectionToGroupSearchUtils.this.isWhile = false;
                                            } else {
                                                String unused2 = GroupSendCollectionToGroupSearchUtils.this.lastName = str2;
                                            }
                                            int unused3 = GroupSendCollectionToGroupSearchUtils.this.startIndex = 0;
                                        }
                                    }
                                }
                            }
                        }
                        if (!GroupSendCollectionToGroupSearchUtils.this.isWhile && MyApplication.enforceable) {
                            if (GroupSendCollectionToGroupSearchUtils.this.groupList != null && GroupSendCollectionToGroupSearchUtils.this.groupList.size() > 0 && GroupSendCollectionToGroupSearchUtils.this.operationParameterModel.getSendCardType() == 2 && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY.TYPE#2#" + GroupSendCollectionToGroupSearchUtils.this.groupList.toString());
                                LogUtils.log("WS_BABY.TYPE#2#jumpGroup#" + GroupSendCollectionToGroupSearchUtils.this.jumpGroupName);
                                if (!GroupSendCollectionToGroupSearchUtils.this.isRemoveRepeat) {
                                    if (GroupSendCollectionToGroupSearchUtils.this.jumpGroupName.contains(";")) {
                                        String[] split = GroupSendCollectionToGroupSearchUtils.this.jumpGroupName.split(";");
                                        for (String remove : split) {
                                            GroupSendCollectionToGroupSearchUtils.this.groupList.remove(remove);
                                        }
                                    } else {
                                        GroupSendCollectionToGroupSearchUtils.this.groupList.remove(GroupSendCollectionToGroupSearchUtils.this.jumpGroupName);
                                    }
                                    boolean unused4 = GroupSendCollectionToGroupSearchUtils.this.isRemoveRepeat = true;
                                }
                                LogUtils.log("WS_BABY.TYPE#2#end#" + GroupSendCollectionToGroupSearchUtils.this.groupList.toString());
                            }
                            if (!GroupSendCollectionToGroupSearchUtils.this.isRemoveIndex && GroupSendCollectionToGroupSearchUtils.this.startIndexFromUser > 1 && MyApplication.enforceable) {
                                if (GroupSendCollectionToGroupSearchUtils.this.groupList.size() >= GroupSendCollectionToGroupSearchUtils.this.startIndexFromUser) {
                                    for (int i2 = 1; i2 < GroupSendCollectionToGroupSearchUtils.this.startIndexFromUser; i2++) {
                                        Iterator it = GroupSendCollectionToGroupSearchUtils.this.groupList.iterator();
                                        if (it.hasNext()) {
                                            GroupSendCollectionToGroupSearchUtils.this.groupList.remove(it.next());
                                        }
                                    }
                                    boolean unused5 = GroupSendCollectionToGroupSearchUtils.this.isRemoveIndex = true;
                                } else {
                                    BaseServiceUtils.removeEndView(GroupSendCollectionToGroupSearchUtils.this.mMyManager);
                                    GroupSendCollectionToGroupSearchUtils.this.groupList.clear();
                                    String unused6 = GroupSendCollectionToGroupSearchUtils.this.sendResult = "群发收藏失败,您设置群发的起点位置是从第[" + GroupSendCollectionToGroupSearchUtils.this.startIndexFromUser + "]个开始，高于群数量";
                                }
                            }
                        }
                        if (GroupSendCollectionToGroupSearchUtils.this.groupList != null && GroupSendCollectionToGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                            if (GroupSendCollectionToGroupSearchUtils.this.circulateMode == 0) {
                                GroupSendCollectionToGroupSearchUtils.this.originList.clear();
                                GroupSendCollectionToGroupSearchUtils.this.originList.addAll(GroupSendCollectionToGroupSearchUtils.this.groupList);
                                int unused7 = GroupSendCollectionToGroupSearchUtils.this.maxSendExecuteNum = GroupSendCollectionToGroupSearchUtils.this.originList.size();
                            }
                            new PerformClickUtils2().check(GroupSendCollectionToGroupSearchUtils.this.autoService, BaseServiceUtils.nav_search_id, GroupSendCollectionToGroupSearchUtils.this.jumpTime, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    PerformClickUtils.findNodeByIdFirstAndClick(GroupSendCollectionToGroupSearchUtils.this.autoService, BaseServiceUtils.nav_search_id);
                                    GroupSendCollectionToGroupSearchUtils.this.sleep(300);
                                    String unused = GroupSendCollectionToGroupSearchUtils.this.groupNames = "";
                                    Iterator it = GroupSendCollectionToGroupSearchUtils.this.groupList.iterator();
                                    if (it != null && it.hasNext()) {
                                        String unused2 = GroupSendCollectionToGroupSearchUtils.this.groupNames = (String) it.next();
                                    }
                                    String substring = GroupSendCollectionToGroupSearchUtils.this.groupNames.length() > 26 ? GroupSendCollectionToGroupSearchUtils.this.groupNames.substring(0, 25) : GroupSendCollectionToGroupSearchUtils.this.groupNames;
                                    for (int i2 = 0; i2 < 10 && substring.startsWith("@"); i2++) {
                                        substring = substring.substring(1);
                                    }
                                    PerformClickUtils.inputText(GroupSendCollectionToGroupSearchUtils.this.autoService, substring);
                                    new PerformClickUtils2().check(GroupSendCollectionToGroupSearchUtils.this.autoService, BaseServiceUtils.group_list_item_name_id, GroupSendCollectionToGroupSearchUtils.this.groupNames, 1200, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            boolean z;
                                            String str;
                                            AccessibilityNodeInfo rootInActiveWindow = GroupSendCollectionToGroupSearchUtils.this.autoService.getRootInActiveWindow();
                                            if (rootInActiveWindow != null && MyApplication.enforceable) {
                                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
                                                if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                                    LogUtils.log("WS_BABY.TYPE" + GroupSendCollectionToGroupSearchUtils.this.operationParameterModel.getSendCardType() + "###" + GroupSendCollectionToGroupSearchUtils.this.groupList.size());
                                                    Iterator it = GroupSendCollectionToGroupSearchUtils.this.groupList.iterator();
                                                    if (it.hasNext()) {
                                                        GroupSendCollectionToGroupSearchUtils.this.groupList.remove(it.next());
                                                        LogUtils.log("WS_BABY.TYPE###end" + GroupSendCollectionToGroupSearchUtils.this.groupList.toString());
                                                        if (GroupSendCollectionToGroupSearchUtils.this.groupList.size() != 0) {
                                                            boolean unused = GroupSendCollectionToGroupSearchUtils.this.isEnd = false;
                                                        } else if (GroupSendCollectionToGroupSearchUtils.this.circulateMode == 0) {
                                                            GroupSendCollectionToGroupSearchUtils.access$310(GroupSendCollectionToGroupSearchUtils.this);
                                                            if (GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize <= 0) {
                                                                boolean unused2 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
                                                            } else {
                                                                int unused3 = GroupSendCollectionToGroupSearchUtils.this.sendGroupNum = -1;
                                                                GroupSendCollectionToGroupSearchUtils.this.groupList.addAll(GroupSendCollectionToGroupSearchUtils.this.originList);
                                                            }
                                                        } else {
                                                            boolean unused4 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
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
                                                                    if (accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null) {
                                                                        str = "";
                                                                    } else {
                                                                        str = accessibilityNodeInfo.getText() + "";
                                                                    }
                                                                    LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendCollectionToGroupSearchUtils.this.groupNames);
                                                                    if (str != null && !"".equals(str) && str.equals(GroupSendCollectionToGroupSearchUtils.this.groupNames)) {
                                                                        LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendCollectionToGroupSearchUtils.this.groupNames + "@@@@");
                                                                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(i2));
                                                                        GroupSendCollectionToGroupSearchUtils.this.initFirstChattingUI(100);
                                                                        z = true;
                                                                        break;
                                                                    }
                                                                    i2++;
                                                                }
                                                                if (!z) {
                                                                    LogUtils.log("WS_BABY.ChatroomContactUI_55");
                                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                                    GroupSendCollectionToGroupSearchUtils.this.initFirstChattingUI(100);
                                                                }
                                                            } else if (findAccessibilityNodeInfosByViewId.size() == 1) {
                                                                LogUtils.log("WS_BABY.ChatroomContactUI_6");
                                                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                                GroupSendCollectionToGroupSearchUtils.this.initFirstChattingUI(100);
                                                            }
                                                        }
                                                    }
                                                } else if (GroupSendCollectionToGroupSearchUtils.this.groupList != null && GroupSendCollectionToGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                                                    Iterator it2 = GroupSendCollectionToGroupSearchUtils.this.groupList.iterator();
                                                    if (it2.hasNext()) {
                                                        GroupSendCollectionToGroupSearchUtils.this.groupList.remove(it2.next());
                                                        if (GroupSendCollectionToGroupSearchUtils.this.groupList.size() > 0) {
                                                            boolean unused5 = GroupSendCollectionToGroupSearchUtils.this.isEnd = false;
                                                        } else if (GroupSendCollectionToGroupSearchUtils.this.circulateMode == 0) {
                                                            GroupSendCollectionToGroupSearchUtils.access$310(GroupSendCollectionToGroupSearchUtils.this);
                                                            if (GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize <= 0) {
                                                                boolean unused6 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
                                                            } else {
                                                                int unused7 = GroupSendCollectionToGroupSearchUtils.this.sendGroupNum = -1;
                                                                GroupSendCollectionToGroupSearchUtils.this.groupList.addAll(GroupSendCollectionToGroupSearchUtils.this.originList);
                                                            }
                                                        } else {
                                                            boolean unused8 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
                                                        }
                                                        PerformClickUtils.executedBackHome(GroupSendCollectionToGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                            public void find() {
                                                                GroupSendCollectionToGroupSearchUtils.this.executeMain();
                                                            }
                                                        });
                                                    }
                                                }
                                            }
                                        }

                                        public void nuFind() {
                                            GroupSendCollectionToGroupSearchUtils.this.saveIndex();
                                            if (GroupSendCollectionToGroupSearchUtils.this.groupList != null && GroupSendCollectionToGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                                                Iterator it = GroupSendCollectionToGroupSearchUtils.this.groupList.iterator();
                                                if (it.hasNext()) {
                                                    GroupSendCollectionToGroupSearchUtils.this.groupList.remove(it.next());
                                                    if (GroupSendCollectionToGroupSearchUtils.this.groupList.size() > 0) {
                                                        boolean unused = GroupSendCollectionToGroupSearchUtils.this.isEnd = false;
                                                    } else if (GroupSendCollectionToGroupSearchUtils.this.circulateMode == 0) {
                                                        GroupSendCollectionToGroupSearchUtils.access$310(GroupSendCollectionToGroupSearchUtils.this);
                                                        if (GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize <= 0) {
                                                            boolean unused2 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
                                                        } else {
                                                            int unused3 = GroupSendCollectionToGroupSearchUtils.this.sendGroupNum = -1;
                                                            GroupSendCollectionToGroupSearchUtils.this.groupList.addAll(GroupSendCollectionToGroupSearchUtils.this.originList);
                                                        }
                                                    } else {
                                                        boolean unused4 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
                                                    }
                                                    PerformClickUtils.executedBackHome(GroupSendCollectionToGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                        public void find() {
                                                            GroupSendCollectionToGroupSearchUtils.this.executeMain();
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
            } else {
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
                } catch (Exception e) {
                    LogUtils.log("WS_BABY.st.3");
                }
                LogUtils.log("WS_BABY.TYPE#1#" + this.groupList.toString());
                new PerformClickUtils2().check(this.autoService, nav_search_id, this.jumpTime, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.findNodeByIdFirstAndClick(GroupSendCollectionToGroupSearchUtils.this.autoService, BaseServiceUtils.nav_search_id);
                        GroupSendCollectionToGroupSearchUtils.this.sleep(300);
                        String unused = GroupSendCollectionToGroupSearchUtils.this.groupNames = "";
                        Iterator it = GroupSendCollectionToGroupSearchUtils.this.groupList.iterator();
                        if (it != null && it.hasNext()) {
                            String unused2 = GroupSendCollectionToGroupSearchUtils.this.groupNames = (String) it.next();
                        }
                        String substring = GroupSendCollectionToGroupSearchUtils.this.groupNames.length() > 26 ? GroupSendCollectionToGroupSearchUtils.this.groupNames.substring(0, 25) : GroupSendCollectionToGroupSearchUtils.this.groupNames;
                        for (int i2 = 0; i2 < 10 && substring.startsWith("@"); i2++) {
                            substring = substring.substring(1);
                        }
                        PerformClickUtils.inputText(GroupSendCollectionToGroupSearchUtils.this.autoService, substring);
                        new PerformClickUtils2().check(GroupSendCollectionToGroupSearchUtils.this.autoService, BaseServiceUtils.group_list_item_name_id, GroupSendCollectionToGroupSearchUtils.this.groupNames, 1200, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                boolean z;
                                String str;
                                AccessibilityNodeInfo rootInActiveWindow = GroupSendCollectionToGroupSearchUtils.this.autoService.getRootInActiveWindow();
                                if (rootInActiveWindow != null && MyApplication.enforceable) {
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
                                    if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                        LogUtils.log("WS_BABY.TYPE" + GroupSendCollectionToGroupSearchUtils.this.operationParameterModel.getSendCardType() + "###" + GroupSendCollectionToGroupSearchUtils.this.groupList.size());
                                        Iterator it = GroupSendCollectionToGroupSearchUtils.this.groupList.iterator();
                                        if (it.hasNext()) {
                                            GroupSendCollectionToGroupSearchUtils.this.groupList.remove(it.next());
                                            LogUtils.log("WS_BABY.TYPE###end" + GroupSendCollectionToGroupSearchUtils.this.groupList.toString());
                                            if (GroupSendCollectionToGroupSearchUtils.this.groupList.size() != 0) {
                                                boolean unused = GroupSendCollectionToGroupSearchUtils.this.isEnd = false;
                                            } else if (GroupSendCollectionToGroupSearchUtils.this.circulateMode == 0) {
                                                GroupSendCollectionToGroupSearchUtils.access$310(GroupSendCollectionToGroupSearchUtils.this);
                                                if (GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize <= 0) {
                                                    boolean unused2 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
                                                } else {
                                                    int unused3 = GroupSendCollectionToGroupSearchUtils.this.sendGroupNum = -1;
                                                    GroupSendCollectionToGroupSearchUtils.this.groupList.addAll(GroupSendCollectionToGroupSearchUtils.this.originList);
                                                }
                                            } else {
                                                boolean unused4 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
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
                                                        if (accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null) {
                                                            str = "";
                                                        } else {
                                                            str = accessibilityNodeInfo.getText() + "";
                                                        }
                                                        LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendCollectionToGroupSearchUtils.this.groupNames);
                                                        if (str != null && !"".equals(str) && str.equals(GroupSendCollectionToGroupSearchUtils.this.groupNames)) {
                                                            LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendCollectionToGroupSearchUtils.this.groupNames + "@@@@");
                                                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(i2));
                                                            GroupSendCollectionToGroupSearchUtils.this.initFirstChattingUI(100);
                                                            z = true;
                                                            break;
                                                        }
                                                        i2++;
                                                    }
                                                    if (!z) {
                                                        LogUtils.log("WS_BABY.ChatroomContactUI_55");
                                                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                        GroupSendCollectionToGroupSearchUtils.this.initFirstChattingUI(100);
                                                    }
                                                } else if (findAccessibilityNodeInfosByViewId.size() == 1) {
                                                    LogUtils.log("WS_BABY.ChatroomContactUI_6");
                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                    GroupSendCollectionToGroupSearchUtils.this.initFirstChattingUI(100);
                                                }
                                            }
                                        }
                                    } else if (GroupSendCollectionToGroupSearchUtils.this.groupList != null && GroupSendCollectionToGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                                        Iterator it2 = GroupSendCollectionToGroupSearchUtils.this.groupList.iterator();
                                        if (it2.hasNext()) {
                                            GroupSendCollectionToGroupSearchUtils.this.groupList.remove(it2.next());
                                            if (GroupSendCollectionToGroupSearchUtils.this.groupList.size() > 0) {
                                                boolean unused5 = GroupSendCollectionToGroupSearchUtils.this.isEnd = false;
                                            } else if (GroupSendCollectionToGroupSearchUtils.this.circulateMode == 0) {
                                                GroupSendCollectionToGroupSearchUtils.access$310(GroupSendCollectionToGroupSearchUtils.this);
                                                if (GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize <= 0) {
                                                    boolean unused6 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
                                                } else {
                                                    int unused7 = GroupSendCollectionToGroupSearchUtils.this.sendGroupNum = -1;
                                                    GroupSendCollectionToGroupSearchUtils.this.groupList.addAll(GroupSendCollectionToGroupSearchUtils.this.originList);
                                                }
                                            } else {
                                                boolean unused8 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
                                            }
                                            PerformClickUtils.executedBackHome(GroupSendCollectionToGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                public void find() {
                                                    GroupSendCollectionToGroupSearchUtils.this.executeMain();
                                                }
                                            });
                                        }
                                    }
                                }
                            }

                            public void nuFind() {
                                GroupSendCollectionToGroupSearchUtils.this.saveIndex();
                                if (GroupSendCollectionToGroupSearchUtils.this.groupList != null && GroupSendCollectionToGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                                    Iterator it = GroupSendCollectionToGroupSearchUtils.this.groupList.iterator();
                                    if (it.hasNext()) {
                                        GroupSendCollectionToGroupSearchUtils.this.groupList.remove(it.next());
                                        if (GroupSendCollectionToGroupSearchUtils.this.groupList.size() > 0) {
                                            boolean unused = GroupSendCollectionToGroupSearchUtils.this.isEnd = false;
                                        } else if (GroupSendCollectionToGroupSearchUtils.this.circulateMode == 0) {
                                            GroupSendCollectionToGroupSearchUtils.access$310(GroupSendCollectionToGroupSearchUtils.this);
                                            if (GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize <= 0) {
                                                boolean unused2 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
                                            } else {
                                                int unused3 = GroupSendCollectionToGroupSearchUtils.this.sendGroupNum = -1;
                                                GroupSendCollectionToGroupSearchUtils.this.groupList.addAll(GroupSendCollectionToGroupSearchUtils.this.originList);
                                            }
                                        } else {
                                            boolean unused4 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
                                        }
                                        PerformClickUtils.executedBackHome(GroupSendCollectionToGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                            public void find() {
                                                GroupSendCollectionToGroupSearchUtils.this.executeMain();
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
        } else if (this.spaceTime > 0) {
            new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                public void end() {
                    GroupSendCollectionToGroupSearchUtils.this.initRoomContact();
                }

                public void surplus(int i) {
                    MyWindowManager myWindowManager = GroupSendCollectionToGroupSearchUtils.this.mMyManager;
                    BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendCollectionToGroupSearchUtils.this.sendGroupNum + " 个群,剩余 " + GroupSendCollectionToGroupSearchUtils.this.groupList.size() + " 个群\n正在延时等待，倒计时 " + i + " 秒");
                }
            });
        } else {
            initRoomContact();
        }
    }

    public void initData(OperationParameterModel operationParameterModel2) {
        this.sendResult = "";
        this.startIndex = 0;
        this.isWhile = true;
        this.jumpTime = 300;
        this.sendGroupNum = 0;
        this.isFirstFav = true;
        this.lastName = "";
        this.groupNames = "";
        this.groupList = new LinkedHashSet<>();
        this.originList = new LinkedHashSet<>();
        this.maxSendExecuteNum = 0;
        this.isFirstMaxSendExecuteNum = true;
        this.isEnd = false;
        this.isRemoveIndex = false;
        this.isRemoveRepeat = false;
        this.operationParameterModel = operationParameterModel2;
        this.sayContent = operationParameterModel2.getSayContent();
        this.jumpGroupName = operationParameterModel2.getJumpGroupNames();
        this.startIndexFromUser = operationParameterModel2.getStartIndex();
        this.spaceTime = operationParameterModel2.getSpaceTime();
        this.circulateMode = operationParameterModel2.getCirculateMode();
        this.circulateInnerSize = operationParameterModel2.getCirculateInnerSize();
        this.circulateOuterSize = operationParameterModel2.getCirculateOutSize();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initFavSelectUI() {
        try {
            LogUtils.log("WS_BABY_FavSelectUI.into");
            new PerformClickUtils2().checkFavListNodeId(this.autoService, collection_list_id, this.isFirstFav ? SocializeConstants.CANCLE_RESULTCODE : executeSpeed + executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
                /* JADX WARNING: Removed duplicated region for block: B:33:0x00a4  */
                public void find(int i) {
                    boolean z;
                    LogUtils.log("WS_BABY_FavSelectUI.into2");
                    AccessibilityNodeInfo rootInActiveWindow = GroupSendCollectionToGroupSearchUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null && MyApplication.enforceable) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.collection_list_id);
                        if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY_FavSelectUI.into3");
                            if (findAccessibilityNodeInfosByViewId.get(0) != null && "android.widget.ListView".equals(findAccessibilityNodeInfosByViewId.get(0).getClassName())) {
                                LogUtils.log("WS_BABY_FavSelectUI.into4");
                                if (findAccessibilityNodeInfosByViewId.get(0).getChildCount() > 0 && MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY_FavSelectUI.into5");
                                    int i2 = 0;
                                    while (true) {
                                        if (i2 < findAccessibilityNodeInfosByViewId.get(0).getChildCount()) {
                                            if (MyApplication.enforceable) {
                                                AccessibilityNodeInfo child = findAccessibilityNodeInfosByViewId.get(0).getChild(i2);
                                                if (child != null && child.getClassName() != null && "android.widget.FrameLayout".equals(child.getClassName()) && MyApplication.enforceable) {
                                                    LogUtils.log("WS_BABY_FavSelectUI.into6");
                                                    LogUtils.log("WS_BABY_FavSelectUI.into7");
                                                    PerformClickUtils.performClick(child);
                                                    z = true;
                                                    break;
                                                }
                                                i2++;
                                            } else {
                                                return;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                    if (!z) {
                                        LogUtils.log("WS_BABY_FavSelectUI.into77777");
                                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendCollectionToGroupSearchUtils.this.autoService, BaseServiceUtils.collection_list_id);
                                        if (findViewIdList != null && findViewIdList.size() > 0 && findViewIdList.get(0).getChildCount() > 1) {
                                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0).getChild(1));
                                        }
                                    }
                                    LogUtils.log("WS_BABY_FavSelectUI.into8");
                                    new PerformClickUtils2().checkNodeId(GroupSendCollectionToGroupSearchUtils.this.autoService, BaseServiceUtils.dialog_ok_id, 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            try {
                                                GroupSendCollectionToGroupSearchUtils.this.sleep(10);
                                                PerformClickUtils.findViewByIdAndPasteContent(GroupSendCollectionToGroupSearchUtils.this.autoService, BaseServiceUtils.toast_edit_id, GroupSendCollectionToGroupSearchUtils.this.sayContent);
                                            } catch (Exception e) {
                                                LogUtils.log("WS_BABY_FavSelectUI.into9");
                                            }
                                            LogUtils.log("WS_BABY_FavSelectUI.into10");
                                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendCollectionToGroupSearchUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                            if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                                                LogUtils.log("WS_BABY_FavSelectUI.into11");
                                                findViewIdList.get(0).performAction(16);
                                                if (GroupSendCollectionToGroupSearchUtils.this.circulateInnerSize == 1 && MyApplication.enforceable) {
                                                    GroupSendCollectionToGroupSearchUtils.access$908(GroupSendCollectionToGroupSearchUtils.this);
                                                    GroupSendCollectionToGroupSearchUtils.access$508(GroupSendCollectionToGroupSearchUtils.this);
                                                    GroupSendCollectionToGroupSearchUtils.this.saveRecord(GroupSendCollectionToGroupSearchUtils.this.startIndexFromUser == 1 ? GroupSendCollectionToGroupSearchUtils.this.sendGroupNum + 1 : GroupSendCollectionToGroupSearchUtils.this.sendGroupNum + GroupSendCollectionToGroupSearchUtils.this.startIndexFromUser);
                                                    int circulateOutSize = GroupSendCollectionToGroupSearchUtils.this.operationParameterModel.getCirculateOutSize();
                                                    if (GroupSendCollectionToGroupSearchUtils.this.circulateMode != 0 || circulateOutSize <= 1 || !MyApplication.enforceable) {
                                                        try {
                                                            if (GroupSendCollectionToGroupSearchUtils.this.operationParameterModel.getSendCardType() == 0) {
                                                                MyWindowManager myWindowManager = GroupSendCollectionToGroupSearchUtils.this.mMyManager;
                                                                BaseServiceUtils.updateBottomText(myWindowManager, "从[全部群]中第 " + GroupSendCollectionToGroupSearchUtils.this.startIndexFromUser + " 个开始群发收藏\n已发送 " + GroupSendCollectionToGroupSearchUtils.this.sendGroupNum + " 个群,剩余 " + GroupSendCollectionToGroupSearchUtils.this.groupList.size() + " 个群");
                                                            } else {
                                                                MyWindowManager myWindowManager2 = GroupSendCollectionToGroupSearchUtils.this.mMyManager;
                                                                BaseServiceUtils.updateBottomText(myWindowManager2, "从[部分群]中第 " + GroupSendCollectionToGroupSearchUtils.this.startIndexFromUser + " 个开始群发收藏\n已发送 " + GroupSendCollectionToGroupSearchUtils.this.sendGroupNum + " 个群,剩余 " + GroupSendCollectionToGroupSearchUtils.this.groupList.size() + " 个群");
                                                            }
                                                        } catch (Exception e2) {
                                                            e2.printStackTrace();
                                                        }
                                                    } else {
                                                        if (GroupSendCollectionToGroupSearchUtils.this.isFirstMaxSendExecuteNum) {
                                                            boolean unused = GroupSendCollectionToGroupSearchUtils.this.isFirstMaxSendExecuteNum = false;
                                                            int unused2 = GroupSendCollectionToGroupSearchUtils.this.maxSendExecuteNum = GroupSendCollectionToGroupSearchUtils.this.groupList.size() + 1;
                                                        }
                                                        int size = (GroupSendCollectionToGroupSearchUtils.this.groupList.size() != 0 || GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize <= 0) ? GroupSendCollectionToGroupSearchUtils.this.groupList.size() : GroupSendCollectionToGroupSearchUtils.this.maxSendExecuteNum;
                                                        try {
                                                            LogUtils.log("WS_BABY_X_" + GroupSendCollectionToGroupSearchUtils.this.originList.size() + "@" + GroupSendCollectionToGroupSearchUtils.this.groupList.size() + "@" + GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize + "@" + GroupSendCollectionToGroupSearchUtils.this.maxSendExecuteNum);
                                                            if (GroupSendCollectionToGroupSearchUtils.this.operationParameterModel.getSendCardType() == 0) {
                                                                MyWindowManager myWindowManager3 = GroupSendCollectionToGroupSearchUtils.this.mMyManager;
                                                                StringBuilder sb = new StringBuilder();
                                                                sb.append("从[全部群]中第 ");
                                                                sb.append(GroupSendCollectionToGroupSearchUtils.this.startIndexFromUser);
                                                                sb.append(" 个开始群发收藏\n已发送 ");
                                                                sb.append(GroupSendCollectionToGroupSearchUtils.this.sendGroupNum);
                                                                sb.append(" 个群,剩余 ");
                                                                sb.append(size);
                                                                sb.append(" 个群\n正在执行外循环第 ");
                                                                if ((circulateOutSize - GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize) + 1 <= circulateOutSize) {
                                                                    circulateOutSize = (circulateOutSize - GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize) + 1;
                                                                }
                                                                sb.append(circulateOutSize);
                                                                sb.append(" 轮群发");
                                                                BaseServiceUtils.updateBottomText(myWindowManager3, sb.toString());
                                                            } else {
                                                                MyWindowManager myWindowManager4 = GroupSendCollectionToGroupSearchUtils.this.mMyManager;
                                                                StringBuilder sb2 = new StringBuilder();
                                                                sb2.append("从[部分群]中第 ");
                                                                sb2.append(GroupSendCollectionToGroupSearchUtils.this.startIndexFromUser);
                                                                sb2.append(" 个开始群发收藏\n已发送 ");
                                                                sb2.append(GroupSendCollectionToGroupSearchUtils.this.sendGroupNum);
                                                                sb2.append(" 个群,剩余 ");
                                                                sb2.append(size);
                                                                sb2.append(" 个群\n正在执行外循环第 ");
                                                                if ((circulateOutSize - GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize) + 1 <= circulateOutSize) {
                                                                    circulateOutSize = (circulateOutSize - GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize) + 1;
                                                                }
                                                                sb2.append(circulateOutSize);
                                                                sb2.append(" 轮群发");
                                                                BaseServiceUtils.updateBottomText(myWindowManager4, sb2.toString());
                                                            }
                                                        } catch (Exception e3) {
                                                        }
                                                    }
                                                }
                                                new PerformClickUtils2().checkNodeId(GroupSendCollectionToGroupSearchUtils.this.autoService, BaseServiceUtils.voice_left_id, 1500, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                                    public void find(int i) {
                                                        boolean unused = GroupSendCollectionToGroupSearchUtils.this.isFirstFav = false;
                                                        GroupSendCollectionToGroupSearchUtils.this.initBackChattingUI();
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
                            }
                        }
                        z = false;
                        if (!z) {
                        }
                        LogUtils.log("WS_BABY_FavSelectUI.into8");
                        new PerformClickUtils2().checkNodeId(GroupSendCollectionToGroupSearchUtils.this.autoService, BaseServiceUtils.dialog_ok_id, 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                try {
                                    GroupSendCollectionToGroupSearchUtils.this.sleep(10);
                                    PerformClickUtils.findViewByIdAndPasteContent(GroupSendCollectionToGroupSearchUtils.this.autoService, BaseServiceUtils.toast_edit_id, GroupSendCollectionToGroupSearchUtils.this.sayContent);
                                } catch (Exception e) {
                                    LogUtils.log("WS_BABY_FavSelectUI.into9");
                                }
                                LogUtils.log("WS_BABY_FavSelectUI.into10");
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendCollectionToGroupSearchUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY_FavSelectUI.into11");
                                    findViewIdList.get(0).performAction(16);
                                    if (GroupSendCollectionToGroupSearchUtils.this.circulateInnerSize == 1 && MyApplication.enforceable) {
                                        GroupSendCollectionToGroupSearchUtils.access$908(GroupSendCollectionToGroupSearchUtils.this);
                                        GroupSendCollectionToGroupSearchUtils.access$508(GroupSendCollectionToGroupSearchUtils.this);
                                        GroupSendCollectionToGroupSearchUtils.this.saveRecord(GroupSendCollectionToGroupSearchUtils.this.startIndexFromUser == 1 ? GroupSendCollectionToGroupSearchUtils.this.sendGroupNum + 1 : GroupSendCollectionToGroupSearchUtils.this.sendGroupNum + GroupSendCollectionToGroupSearchUtils.this.startIndexFromUser);
                                        int circulateOutSize = GroupSendCollectionToGroupSearchUtils.this.operationParameterModel.getCirculateOutSize();
                                        if (GroupSendCollectionToGroupSearchUtils.this.circulateMode != 0 || circulateOutSize <= 1 || !MyApplication.enforceable) {
                                            try {
                                                if (GroupSendCollectionToGroupSearchUtils.this.operationParameterModel.getSendCardType() == 0) {
                                                    MyWindowManager myWindowManager = GroupSendCollectionToGroupSearchUtils.this.mMyManager;
                                                    BaseServiceUtils.updateBottomText(myWindowManager, "从[全部群]中第 " + GroupSendCollectionToGroupSearchUtils.this.startIndexFromUser + " 个开始群发收藏\n已发送 " + GroupSendCollectionToGroupSearchUtils.this.sendGroupNum + " 个群,剩余 " + GroupSendCollectionToGroupSearchUtils.this.groupList.size() + " 个群");
                                                } else {
                                                    MyWindowManager myWindowManager2 = GroupSendCollectionToGroupSearchUtils.this.mMyManager;
                                                    BaseServiceUtils.updateBottomText(myWindowManager2, "从[部分群]中第 " + GroupSendCollectionToGroupSearchUtils.this.startIndexFromUser + " 个开始群发收藏\n已发送 " + GroupSendCollectionToGroupSearchUtils.this.sendGroupNum + " 个群,剩余 " + GroupSendCollectionToGroupSearchUtils.this.groupList.size() + " 个群");
                                                }
                                            } catch (Exception e2) {
                                                e2.printStackTrace();
                                            }
                                        } else {
                                            if (GroupSendCollectionToGroupSearchUtils.this.isFirstMaxSendExecuteNum) {
                                                boolean unused = GroupSendCollectionToGroupSearchUtils.this.isFirstMaxSendExecuteNum = false;
                                                int unused2 = GroupSendCollectionToGroupSearchUtils.this.maxSendExecuteNum = GroupSendCollectionToGroupSearchUtils.this.groupList.size() + 1;
                                            }
                                            int size = (GroupSendCollectionToGroupSearchUtils.this.groupList.size() != 0 || GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize <= 0) ? GroupSendCollectionToGroupSearchUtils.this.groupList.size() : GroupSendCollectionToGroupSearchUtils.this.maxSendExecuteNum;
                                            try {
                                                LogUtils.log("WS_BABY_X_" + GroupSendCollectionToGroupSearchUtils.this.originList.size() + "@" + GroupSendCollectionToGroupSearchUtils.this.groupList.size() + "@" + GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize + "@" + GroupSendCollectionToGroupSearchUtils.this.maxSendExecuteNum);
                                                if (GroupSendCollectionToGroupSearchUtils.this.operationParameterModel.getSendCardType() == 0) {
                                                    MyWindowManager myWindowManager3 = GroupSendCollectionToGroupSearchUtils.this.mMyManager;
                                                    StringBuilder sb = new StringBuilder();
                                                    sb.append("从[全部群]中第 ");
                                                    sb.append(GroupSendCollectionToGroupSearchUtils.this.startIndexFromUser);
                                                    sb.append(" 个开始群发收藏\n已发送 ");
                                                    sb.append(GroupSendCollectionToGroupSearchUtils.this.sendGroupNum);
                                                    sb.append(" 个群,剩余 ");
                                                    sb.append(size);
                                                    sb.append(" 个群\n正在执行外循环第 ");
                                                    if ((circulateOutSize - GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize) + 1 <= circulateOutSize) {
                                                        circulateOutSize = (circulateOutSize - GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize) + 1;
                                                    }
                                                    sb.append(circulateOutSize);
                                                    sb.append(" 轮群发");
                                                    BaseServiceUtils.updateBottomText(myWindowManager3, sb.toString());
                                                } else {
                                                    MyWindowManager myWindowManager4 = GroupSendCollectionToGroupSearchUtils.this.mMyManager;
                                                    StringBuilder sb2 = new StringBuilder();
                                                    sb2.append("从[部分群]中第 ");
                                                    sb2.append(GroupSendCollectionToGroupSearchUtils.this.startIndexFromUser);
                                                    sb2.append(" 个开始群发收藏\n已发送 ");
                                                    sb2.append(GroupSendCollectionToGroupSearchUtils.this.sendGroupNum);
                                                    sb2.append(" 个群,剩余 ");
                                                    sb2.append(size);
                                                    sb2.append(" 个群\n正在执行外循环第 ");
                                                    if ((circulateOutSize - GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize) + 1 <= circulateOutSize) {
                                                        circulateOutSize = (circulateOutSize - GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize) + 1;
                                                    }
                                                    sb2.append(circulateOutSize);
                                                    sb2.append(" 轮群发");
                                                    BaseServiceUtils.updateBottomText(myWindowManager4, sb2.toString());
                                                }
                                            } catch (Exception e3) {
                                            }
                                        }
                                    }
                                    new PerformClickUtils2().checkNodeId(GroupSendCollectionToGroupSearchUtils.this.autoService, BaseServiceUtils.voice_left_id, 1500, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            boolean unused = GroupSendCollectionToGroupSearchUtils.this.isFirstFav = false;
                                            GroupSendCollectionToGroupSearchUtils.this.initBackChattingUI();
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
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initFirstChattingUI(int i) {
        new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, executeSpeedSetting + i, 100, 300, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                new PerformClickUtils2().checkStringAndIdSomeOne(GroupSendCollectionToGroupSearchUtils.this.autoService, "发送", BaseServiceUtils.send_add_id, 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (i == 0) {
                            LogUtils.log("WS_BABY_FavSelectUI.initFirstChattingUI.0");
                            PerformClickUtils.inputText(GroupSendCollectionToGroupSearchUtils.this.autoService, "");
                            GroupSendCollectionToGroupSearchUtils.this.clickCollection();
                            return;
                        }
                        LogUtils.log("WS_BABY_FavSelectUI.initFirstChattingUI.1");
                        GroupSendCollectionToGroupSearchUtils.this.clickCollection();
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
        try {
            MyWindowManager myWindowManager = this.mMyManager;
            updateBottomText(myWindowManager, "正在群发收藏,请不要操作微信\n已发送 " + this.sendGroupNum + " 个群,剩余 " + this.groupList.size() + " 个群");
        } catch (Exception e) {
            LogUtils.log("WS_BABY.ChatroomContactUI_error");
        }
        LogUtils.log("WS_BABY.ChatroomContactUI_2");
        new PerformClickUtils2().check(this.autoService, nav_search_id, executeSpeed, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findNodeByIdFirstAndClick(GroupSendCollectionToGroupSearchUtils.this.autoService, BaseServiceUtils.nav_search_id);
                GroupSendCollectionToGroupSearchUtils.this.sleep(300);
                String unused = GroupSendCollectionToGroupSearchUtils.this.groupNames = "";
                Iterator it = GroupSendCollectionToGroupSearchUtils.this.groupList.iterator();
                if (it != null && it.hasNext()) {
                    String unused2 = GroupSendCollectionToGroupSearchUtils.this.groupNames = (String) it.next();
                }
                String substring = GroupSendCollectionToGroupSearchUtils.this.groupNames.length() > 26 ? GroupSendCollectionToGroupSearchUtils.this.groupNames.substring(0, 25) : GroupSendCollectionToGroupSearchUtils.this.groupNames;
                for (int i2 = 0; i2 < 10 && substring.startsWith("@"); i2++) {
                    substring = substring.substring(1);
                }
                PerformClickUtils.inputText(GroupSendCollectionToGroupSearchUtils.this.autoService, substring);
                new PerformClickUtils2().check(GroupSendCollectionToGroupSearchUtils.this.autoService, BaseServiceUtils.group_list_item_name_id, GroupSendCollectionToGroupSearchUtils.this.groupNames, 1200, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        boolean z;
                        String str;
                        AccessibilityNodeInfo rootInActiveWindow = GroupSendCollectionToGroupSearchUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow != null && MyApplication.enforceable) {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id);
                            if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                Iterator it = GroupSendCollectionToGroupSearchUtils.this.groupList.iterator();
                                if (it.hasNext()) {
                                    GroupSendCollectionToGroupSearchUtils.this.groupList.remove(it.next());
                                    if (GroupSendCollectionToGroupSearchUtils.this.groupList.size() != 0) {
                                        boolean unused = GroupSendCollectionToGroupSearchUtils.this.isEnd = false;
                                    } else if (GroupSendCollectionToGroupSearchUtils.this.circulateMode == 0) {
                                        GroupSendCollectionToGroupSearchUtils.access$310(GroupSendCollectionToGroupSearchUtils.this);
                                        if (GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize <= 0) {
                                            boolean unused2 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
                                        } else {
                                            int unused3 = GroupSendCollectionToGroupSearchUtils.this.sendGroupNum = -1;
                                            GroupSendCollectionToGroupSearchUtils.this.groupList.addAll(GroupSendCollectionToGroupSearchUtils.this.originList);
                                        }
                                    } else {
                                        boolean unused4 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
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
                                                if (accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null) {
                                                    str = "";
                                                } else {
                                                    str = accessibilityNodeInfo.getText() + "";
                                                }
                                                LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendCollectionToGroupSearchUtils.this.groupNames);
                                                if (str != null && !"".equals(str) && str.equals(GroupSendCollectionToGroupSearchUtils.this.groupNames)) {
                                                    LogUtils.log("WS_BABY.ChatroomContactUI_44" + str + "@" + GroupSendCollectionToGroupSearchUtils.this.groupNames + "@@@@");
                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(i2));
                                                    GroupSendCollectionToGroupSearchUtils.this.initFirstChattingUI(100);
                                                    z = true;
                                                    break;
                                                }
                                                i2++;
                                            }
                                            if (!z) {
                                                LogUtils.log("WS_BABY.ChatroomContactUI_55");
                                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                GroupSendCollectionToGroupSearchUtils.this.initFirstChattingUI(100);
                                            }
                                        } else if (findAccessibilityNodeInfosByViewId.size() == 1) {
                                            LogUtils.log("WS_BABY.ChatroomContactUI_6");
                                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                            GroupSendCollectionToGroupSearchUtils.this.initFirstChattingUI(100);
                                        }
                                    }
                                }
                            } else if (GroupSendCollectionToGroupSearchUtils.this.groupList != null && GroupSendCollectionToGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                                Iterator it2 = GroupSendCollectionToGroupSearchUtils.this.groupList.iterator();
                                if (it2.hasNext()) {
                                    GroupSendCollectionToGroupSearchUtils.this.groupList.remove(it2.next());
                                    if (GroupSendCollectionToGroupSearchUtils.this.groupList.size() > 0) {
                                        boolean unused5 = GroupSendCollectionToGroupSearchUtils.this.isEnd = false;
                                    } else if (GroupSendCollectionToGroupSearchUtils.this.circulateMode == 0) {
                                        GroupSendCollectionToGroupSearchUtils.access$310(GroupSendCollectionToGroupSearchUtils.this);
                                        if (GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize <= 0) {
                                            boolean unused6 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
                                        } else {
                                            int unused7 = GroupSendCollectionToGroupSearchUtils.this.sendGroupNum = -1;
                                            GroupSendCollectionToGroupSearchUtils.this.groupList.addAll(GroupSendCollectionToGroupSearchUtils.this.originList);
                                        }
                                    } else {
                                        boolean unused8 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
                                    }
                                    PerformClickUtils.executedBackHome(GroupSendCollectionToGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            GroupSendCollectionToGroupSearchUtils.this.executeMain();
                                        }
                                    });
                                }
                            }
                        }
                    }

                    public void nuFind() {
                        GroupSendCollectionToGroupSearchUtils.this.saveIndex();
                        if (GroupSendCollectionToGroupSearchUtils.this.groupList != null && GroupSendCollectionToGroupSearchUtils.this.groupList.size() > 0 && MyApplication.enforceable) {
                            Iterator it = GroupSendCollectionToGroupSearchUtils.this.groupList.iterator();
                            if (it.hasNext()) {
                                GroupSendCollectionToGroupSearchUtils.this.groupList.remove(it.next());
                                if (GroupSendCollectionToGroupSearchUtils.this.groupList.size() > 0) {
                                    boolean unused = GroupSendCollectionToGroupSearchUtils.this.isEnd = false;
                                } else if (GroupSendCollectionToGroupSearchUtils.this.circulateMode == 0) {
                                    GroupSendCollectionToGroupSearchUtils.access$310(GroupSendCollectionToGroupSearchUtils.this);
                                    if (GroupSendCollectionToGroupSearchUtils.this.circulateOuterSize <= 0) {
                                        boolean unused2 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
                                    } else {
                                        int unused3 = GroupSendCollectionToGroupSearchUtils.this.sendGroupNum = -1;
                                        GroupSendCollectionToGroupSearchUtils.this.groupList.addAll(GroupSendCollectionToGroupSearchUtils.this.originList);
                                    }
                                } else {
                                    boolean unused4 = GroupSendCollectionToGroupSearchUtils.this.isEnd = true;
                                }
                                PerformClickUtils.executedBackHome(GroupSendCollectionToGroupSearchUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        GroupSendCollectionToGroupSearchUtils.this.executeMain();
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

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        if (this.sendResult == null || "".equals(this.sendResult)) {
            MyWindowManager myWindowManager = this.mMyManager;
            updateMiddleText(myWindowManager, "群发收藏", "发送了" + this.sendGroupNum + "个群");
            return;
        }
        updateMiddleText(this.mMyManager, "群发收藏结束", this.sendResult);
    }

    public void saveIndex() {
        this.sendGroupNum++;
        int i = this.startIndexFromUser == 1 ? this.sendGroupNum + 1 : this.sendGroupNum + this.startIndexFromUser;
        if (this.operationParameterModel.getSendCardType() == 0) {
            SPUtils.put(MyApplication.getConText(), "collection_to_group_num_all", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 1) {
            SPUtils.put(MyApplication.getConText(), "collection_to_group_num_part", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 2) {
            SPUtils.put(MyApplication.getConText(), "collection_to_group_num_shield", Integer.valueOf(i));
        }
    }

    public void saveRecord(int i) {
        if (this.operationParameterModel.getSendCardType() == 0) {
            SPUtils.put(MyApplication.getConText(), "collection_to_group_num_all", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 1) {
            SPUtils.put(MyApplication.getConText(), "collection_to_group_num_part", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 2) {
            SPUtils.put(MyApplication.getConText(), "collection_to_group_num_shield", Integer.valueOf(i));
        }
    }
}
