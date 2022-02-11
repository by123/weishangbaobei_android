package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import java.util.ArrayList;
import java.util.List;

public class CleanGroupsOtherUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static CleanGroupsOtherUtils instance;
    /* access modifiers changed from: private */
    public int deleteType = 1;
    /* access modifiers changed from: private */
    public int deletedNum = 0;
    private List<String> groupList = new ArrayList();
    List<AccessibilityNodeInfo> groupNames = null;
    private boolean isScrollBottom = false;
    /* access modifiers changed from: private */
    public int jumpNum = 0;
    private List<String> jumpStartLists = new ArrayList();
    /* access modifiers changed from: private */
    public String lastName = "";
    private String nowName = "";
    /* access modifiers changed from: private */
    public int scrollCount = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;

    private CleanGroupsOtherUtils() {
    }

    static /* synthetic */ int access$008(CleanGroupsOtherUtils cleanGroupsOtherUtils) {
        int i = cleanGroupsOtherUtils.jumpNum;
        cleanGroupsOtherUtils.jumpNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$508(CleanGroupsOtherUtils cleanGroupsOtherUtils) {
        int i = cleanGroupsOtherUtils.deletedNum;
        cleanGroupsOtherUtils.deletedNum = i + 1;
        return i;
    }

    public static CleanGroupsOtherUtils getInstance() {
        instance = new CleanGroupsOtherUtils();
        return instance;
    }

    public void checkContactGroup() {
        new PerformClickUtils2().checkExistString(this.autoService, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                System.out.println("WS_BABY_checkContactGroup_0");
                if (PerformClickUtils.findNodeIsExistByText(CleanGroupsOtherUtils.this.autoService, "保存到通讯录")) {
                    System.out.println("WS_BABY_checkContactGroup_1");
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanGroupsOtherUtils.this.autoService, BaseServiceUtils.switch_id);
                    if (findViewIdList != null && findViewIdList.size() >= 2) {
                        System.out.println("WS_BABY_checkContactGroup_2");
                        AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(findViewIdList.size() - 2);
                        if (accessibilityNodeInfo != null) {
                            System.out.println("WS_BABY_checkContactGroup_3");
                            CharSequence contentDescription = accessibilityNodeInfo.getContentDescription();
                            if (contentDescription != null) {
                                System.out.println("WS_BABY_checkContactGroup_4");
                                String charSequence = contentDescription.toString();
                                if (charSequence != null) {
                                    System.out.println("WS_BABY_checkContactGroup_5");
                                    if ("已开启".equals(charSequence)) {
                                        System.out.println("WS_BABY_checkContactGroup_6");
                                        PerformClickUtils.executedBackHome(CleanGroupsOtherUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                            public void find() {
                                                CleanGroupsOtherUtils.access$008(CleanGroupsOtherUtils.this);
                                                CleanGroupsOtherUtils.this.executeAllMain();
                                            }
                                        });
                                        return;
                                    }
                                    System.out.println("WS_BABY_checkContactGroup_7");
                                    CleanGroupsOtherUtils.this.deleteGroup();
                                }
                            }
                        }
                    }
                }
            }

            public void nuFind() {
            }
        });
    }

    public void deleteGroup() {
        new PerformClickUtils2().checkExistString(this.autoService, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findTextAndClick(CleanGroupsOtherUtils.this.autoService, "删除并退出");
                new PerformClickUtils2().checkNodeIdOrName(CleanGroupsOtherUtils.this.autoService, BaseServiceUtils.dialog_ok_id, "离开群聊", 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (i == 0) {
                            PerformClickUtils.findViewIdAndClick(CleanGroupsOtherUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                        } else {
                            PerformClickUtils.findTextAndClick(CleanGroupsOtherUtils.this.autoService, "离开群聊");
                        }
                        new PerformClickUtils2().checkNilString(CleanGroupsOtherUtils.this.autoService, "请稍候", (int) SocializeConstants.CANCLE_RESULTCODE, 50, (int) SocializeConstants.CANCLE_RESULTCODE, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                CleanGroupsOtherUtils.access$508(CleanGroupsOtherUtils.this);
                                MyWindowManager myWindowManager = CleanGroupsOtherUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "正在删除微信群，已删除 " + CleanGroupsOtherUtils.this.deletedNum + " 个群");
                                CleanGroupsOtherUtils.this.executeAllMain();
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

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        showBottomView(this.mMyManager, "正在删除微信群，请不要操作微信！");
        LogUtils.log("WS_BABY.END_SHOW");
        new Thread(new Runnable() {
            public void run() {
                BaseServiceUtils.getMainHandler().post(new Runnable() {
                    public void run() {
                        CleanGroupsOtherUtils.this.executeAllMain();
                    }
                });
            }
        }).start();
    }

    public void executeAllMain() {
        try {
            new PerformClickUtils2().checkString(this.autoService, "通讯录", 100, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findTextAndClick(CleanGroupsOtherUtils.this.autoService, "通讯录");
                    new PerformClickUtils2().checkNodeId(CleanGroupsOtherUtils.this.autoService, BaseServiceUtils.contact_nav_search_viewgroup_id, 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanGroupsOtherUtils.this.autoService, BaseServiceUtils.contact_nav_search_viewgroup_id);
                            if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                                PerformClickUtils.findTextAndClickFirst(CleanGroupsOtherUtils.this.autoService, "更多功能按钮");
                                new PerformClickUtils2().checkString(CleanGroupsOtherUtils.this.autoService, "发起群聊", 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        PerformClickUtils.findTextAndClick(CleanGroupsOtherUtils.this.autoService, "发起群聊");
                                        new PerformClickUtils2().checkString(CleanGroupsOtherUtils.this.autoService, "选择一个群", 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                PerformClickUtils.findTextAndClick(CleanGroupsOtherUtils.this.autoService, "选择一个群");
                                                new PerformClickUtils2().checkString(CleanGroupsOtherUtils.this.autoService, "选择群聊", 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                                    public void find(int i) {
                                                        CleanGroupsOtherUtils.this.initRoomContact();
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

    public void findDeleteGroup() {
        if (this.groupNames == null) {
            this.groupNames = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_select_group_name_id);
        }
        if (this.groupNames != null && this.groupNames.size() > 0 && MyApplication.enforceable) {
            if (this.startIndex < this.groupNames.size() && MyApplication.enforceable) {
                AccessibilityNodeInfo accessibilityNodeInfo = this.groupNames.get(this.startIndex);
                String str = "";
                if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                    str = accessibilityNodeInfo.getText() + "";
                    Rect rect = new Rect();
                    accessibilityNodeInfo.getBoundsInScreen(rect);
                    this.nowName = str + rect.toString();
                }
                LogUtils.log("WS_BABY_initRoomContact.isScrollBottom." + this.isScrollBottom + ",name=" + this.nowName);
                StringBuilder sb = new StringBuilder();
                sb.append("WS_BABY_initRoomContact.contains.");
                sb.append(this.jumpStartLists.contains(this.nowName));
                LogUtils.log(sb.toString());
                this.scrollCount = this.scrollCount + 1;
                if (this.jumpStartLists.contains(this.nowName)) {
                    LogUtils.log("WS_BABY_initRoomContact.1");
                    this.startIndex++;
                    findDeleteGroup();
                    return;
                }
                LogUtils.log("WS_BABY_initRoomContact.2");
                LogUtils.log("WS_BABY_initRoomContact.@" + this.scrollCount + "@" + this.jumpNum);
                if (this.scrollCount == this.jumpNum + 1) {
                    this.lastName = "";
                    LogUtils.log("WS_BABY_initRoomContact.3");
                    if (this.deleteType == 1 || this.deleteType == 2 || this.deleteType == 3) {
                        LogUtils.log("WS_BABY_initRoomContact.4");
                        this.jumpStartLists.add(this.nowName);
                        PerformClickUtils.performClick(accessibilityNodeInfo);
                        initFirstChattingUI();
                        return;
                    }
                    LogUtils.log("WS_BABY_initRoomContact.5");
                    if (this.deleteType != 4 || this.groupList == null || this.groupList.size() <= 0 || !this.groupList.contains(str)) {
                        LogUtils.log("WS_BABY_initRoomContact.7");
                        this.startIndex++;
                        this.scrollCount++;
                        findDeleteGroup();
                        return;
                    }
                    LogUtils.log("WS_BABY_initRoomContact.6");
                    this.jumpStartLists.add(this.nowName);
                    PerformClickUtils.performClick(accessibilityNodeInfo);
                    initFirstChattingUI();
                    return;
                }
                LogUtils.log("WS_BABY_initRoomContact.8");
                this.startIndex++;
                findDeleteGroup();
            } else if (this.startIndex >= this.groupNames.size() && MyApplication.enforceable) {
                LogUtils.log("WS_BABY_initRoomContact.9");
                this.groupNames = null;
                this.startIndex = 1;
                PerformClickUtils.scroll(this.autoService, list_select_group_id);
                new PerformClickUtils2().check(this.autoService, list_select_group_name_id, 200, 10, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        CleanGroupsOtherUtils.this.groupNames = PerformClickUtils.findViewIdList((AccessibilityService) CleanGroupsOtherUtils.this.autoService, BaseServiceUtils.list_select_group_name_id);
                        if (CleanGroupsOtherUtils.this.groupNames == null || CleanGroupsOtherUtils.this.groupNames.size() <= 0) {
                            CleanGroupsOtherUtils.this.findDeleteGroup();
                            return;
                        }
                        AccessibilityNodeInfo accessibilityNodeInfo = CleanGroupsOtherUtils.this.groupNames.get(CleanGroupsOtherUtils.this.groupNames.size() - 1);
                        if (accessibilityNodeInfo != null) {
                            try {
                                if (accessibilityNodeInfo.getText() != null) {
                                    Rect rect = new Rect();
                                    accessibilityNodeInfo.getBoundsInScreen(rect);
                                    String str = (accessibilityNodeInfo.getText() + "") + rect.toString();
                                    LogUtils.log("WS_BABY_initRoomContact.11");
                                    if (str.equals(CleanGroupsOtherUtils.this.lastName)) {
                                        LogUtils.log("WS_BABY_initRoomContact.end");
                                        BaseServiceUtils.removeEndView(CleanGroupsOtherUtils.this.mMyManager);
                                        return;
                                    }
                                    String unused = CleanGroupsOtherUtils.this.lastName = str;
                                    CleanGroupsOtherUtils.this.findDeleteGroup();
                                    return;
                                }
                            } catch (Exception e) {
                                CleanGroupsOtherUtils.this.findDeleteGroup();
                                return;
                            }
                        }
                        CleanGroupsOtherUtils.this.findDeleteGroup();
                    }

                    public void nuFind() {
                        CleanGroupsOtherUtils.this.findDeleteGroup();
                    }
                });
            }
        }
    }

    public void initChatRoomInfoUI() {
        try {
            LogUtils.log("WS_BABY.isFirstChatroomInfoUI");
            new PerformClickUtils2().checkNodeAllIds(this.autoService, default_list_id, list_head_img_id, 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (CleanGroupsOtherUtils.this.deleteType == 1) {
                        CleanGroupsOtherUtils.this.checkContactGroup();
                    } else if (CleanGroupsOtherUtils.this.deleteType == 2) {
                        if (PerformClickUtils.findNodeIsExistByText(CleanGroupsOtherUtils.this.autoService, "删除成员")) {
                            System.out.println("WS_BABY_EXIST_0");
                            PerformClickUtils.executedBackHome(CleanGroupsOtherUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    CleanGroupsOtherUtils.access$008(CleanGroupsOtherUtils.this);
                                    CleanGroupsOtherUtils.this.executeAllMain();
                                }
                            });
                        } else if (PerformClickUtils.findNodeIsExistByText(CleanGroupsOtherUtils.this.autoService, "群聊名称")) {
                            System.out.println("WS_BABY_DELETE_0");
                            CleanGroupsOtherUtils.this.deleteGroup();
                        } else {
                            PerformClickUtils.scroll(CleanGroupsOtherUtils.this.autoService, BaseServiceUtils.default_list_id);
                            System.out.println("WS_BABY_SCROLL_0");
                            new PerformClickUtils2().checkStringsSyn(CleanGroupsOtherUtils.this.autoService, "群聊名称", "群二维码", 30, 20, 50, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    System.out.println("WS_BABY_群聊名称_0");
                                    CleanGroupsOtherUtils.this.sleep(20);
                                    if (PerformClickUtils.findNodeIsExistByText(CleanGroupsOtherUtils.this.autoService, "删除成员")) {
                                        System.out.println("WS_BABY_删除成员_0");
                                        PerformClickUtils.executedBackHome(CleanGroupsOtherUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                            public void find() {
                                                CleanGroupsOtherUtils.access$008(CleanGroupsOtherUtils.this);
                                                CleanGroupsOtherUtils.this.executeAllMain();
                                            }
                                        });
                                        return;
                                    }
                                    System.out.println("WS_BABY_DELETE_1");
                                    CleanGroupsOtherUtils.this.deleteGroup();
                                }

                                public void nuFind() {
                                }
                            });
                        }
                    } else if (CleanGroupsOtherUtils.this.deleteType != 3) {
                        System.out.println("WS_BABY_DELETE_22");
                        CleanGroupsOtherUtils.this.deleteGroup();
                    } else if (PerformClickUtils.findNodeIsExistByText(CleanGroupsOtherUtils.this.autoService, "删除成员")) {
                        System.out.println("WS_BABY_EXIST_00");
                        PerformClickUtils.executedBackHome(CleanGroupsOtherUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                CleanGroupsOtherUtils.access$008(CleanGroupsOtherUtils.this);
                                CleanGroupsOtherUtils.this.executeAllMain();
                            }
                        });
                    } else if (PerformClickUtils.findNodeIsExistByText(CleanGroupsOtherUtils.this.autoService, "群聊名称")) {
                        System.out.println("WS_BABY_DELETE_00");
                        CleanGroupsOtherUtils.this.checkContactGroup();
                    } else {
                        PerformClickUtils.scroll(CleanGroupsOtherUtils.this.autoService, BaseServiceUtils.default_list_id);
                        System.out.println("WS_BABY_SCROLL_00");
                        new PerformClickUtils2().checkStringsSyn(CleanGroupsOtherUtils.this.autoService, "群聊名称", "群二维码", 30, 20, 50, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                System.out.println("WS_BABY_群聊名称_00");
                                CleanGroupsOtherUtils.this.sleep(20);
                                if (PerformClickUtils.findNodeIsExistByText(CleanGroupsOtherUtils.this.autoService, "删除成员")) {
                                    System.out.println("WS_BABY_删除成员_00");
                                    PerformClickUtils.executedBackHome(CleanGroupsOtherUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            CleanGroupsOtherUtils.access$008(CleanGroupsOtherUtils.this);
                                            CleanGroupsOtherUtils.this.executeAllMain();
                                        }
                                    });
                                    return;
                                }
                                System.out.println("WS_BABY_DELETE_11");
                                CleanGroupsOtherUtils.this.checkContactGroup();
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

    public void initData(OperationParameterModel operationParameterModel) {
        this.deletedNum = 0;
        this.deleteType = operationParameterModel.getDeleteFriendsType();
        if (operationParameterModel.getGroupNames() != null && !"".equals(operationParameterModel.getGroupNames())) {
            String groupNames2 = operationParameterModel.getGroupNames();
            if (groupNames2.contains(";")) {
                String[] split = groupNames2.split(";");
                for (String add : split) {
                    this.groupList.add(add);
                }
            } else {
                this.groupList.add(groupNames2);
            }
        }
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initFirstChattingUI() {
        LogUtils.log("WS_BABY.ChattingUI_1");
        new PerformClickUtils2().checkNodeId(this.autoService, right_more_id, executeSpeedSetting + 100, 50, 50, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY.ChattingUI_2");
                PerformClickUtils.findViewIdAndClick(CleanGroupsOtherUtils.this.autoService, BaseServiceUtils.right_more_id);
                CleanGroupsOtherUtils.this.initChatRoomInfoUI();
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.ChattingUI_6");
                PerformClickUtils.executedBackHome(CleanGroupsOtherUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        CleanGroupsOtherUtils.access$008(CleanGroupsOtherUtils.this);
                        CleanGroupsOtherUtils.this.executeAllMain();
                    }
                });
            }
        });
    }

    public void initRoomContact() {
        new PerformClickUtils2().checkNodeAllIds(this.autoService, list_select_group_id, list_select_group_name_id, executeSpeedSetting + 100, 100, 25, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_initRoomContact_从" + CleanGroupsOtherUtils.this.jumpNum + "开始");
                int unused = CleanGroupsOtherUtils.this.scrollCount = 0;
                int unused2 = CleanGroupsOtherUtils.this.startIndex = 0;
                CleanGroupsOtherUtils.this.groupNames = null;
                CleanGroupsOtherUtils.this.findDeleteGroup();
            }

            public void nuFind() {
                BaseServiceUtils.removeEndView(CleanGroupsOtherUtils.this.mMyManager);
            }
        });
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        setMiddleText(myWindowManager, "一键删除微信群", "已删除了" + this.deletedNum + "个微信群");
    }
}
