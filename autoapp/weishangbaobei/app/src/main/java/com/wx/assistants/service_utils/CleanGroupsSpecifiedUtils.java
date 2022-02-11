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

public class CleanGroupsSpecifiedUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static CleanGroupsSpecifiedUtils instance;
    private int deleteType = 1;
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

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$008(CleanGroupsSpecifiedUtils cleanGroupsSpecifiedUtils) {
        int i = cleanGroupsSpecifiedUtils.jumpNum;
        cleanGroupsSpecifiedUtils.jumpNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$408(CleanGroupsSpecifiedUtils cleanGroupsSpecifiedUtils) {
        int i = cleanGroupsSpecifiedUtils.deletedNum;
        cleanGroupsSpecifiedUtils.deletedNum = i + 1;
        return i;
    }

    private CleanGroupsSpecifiedUtils() {
    }

    public static CleanGroupsSpecifiedUtils getInstance() {
        instance = new CleanGroupsSpecifiedUtils();
        return instance;
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

    public void executeAllMain() {
        try {
            new PerformClickUtils2().checkString(this.autoService, "通讯录", 100, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findTextAndClick(CleanGroupsSpecifiedUtils.this.autoService, "通讯录");
                    new PerformClickUtils2().checkNodeId(CleanGroupsSpecifiedUtils.this.autoService, BaseServiceUtils.contact_nav_search_viewgroup_id, 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanGroupsSpecifiedUtils.this.autoService, BaseServiceUtils.contact_nav_search_viewgroup_id);
                            if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                                PerformClickUtils.findTextAndClickFirst(CleanGroupsSpecifiedUtils.this.autoService, "更多功能按钮");
                                new PerformClickUtils2().checkString(CleanGroupsSpecifiedUtils.this.autoService, "发起群聊", 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        PerformClickUtils.findTextAndClick(CleanGroupsSpecifiedUtils.this.autoService, "发起群聊");
                                        new PerformClickUtils2().checkString(CleanGroupsSpecifiedUtils.this.autoService, "选择一个群", 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                            public void nuFind() {
                                            }

                                            public void find(int i) {
                                                PerformClickUtils.findTextAndClick(CleanGroupsSpecifiedUtils.this.autoService, "选择一个群");
                                                new PerformClickUtils2().checkString(CleanGroupsSpecifiedUtils.this.autoService, "选择群聊", 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                                    public void nuFind() {
                                                    }

                                                    public void find(int i) {
                                                        CleanGroupsSpecifiedUtils.this.initRoomContact();
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initRoomContact() {
        new PerformClickUtils2().checkNodeAllIds(this.autoService, list_select_group_id, list_select_group_name_id, executeSpeedSetting + 100, 100, 25, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_initRoomContact_从" + CleanGroupsSpecifiedUtils.this.jumpNum + "开始");
                int unused = CleanGroupsSpecifiedUtils.this.scrollCount = 0;
                int unused2 = CleanGroupsSpecifiedUtils.this.startIndex = 0;
                CleanGroupsSpecifiedUtils.this.groupNames = null;
                CleanGroupsSpecifiedUtils.this.findDeleteGroup();
            }

            public void nuFind() {
                BaseServiceUtils.removeEndView(CleanGroupsSpecifiedUtils.this.mMyManager);
            }
        });
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
                    LogUtils.log("WS_BABY_initRoomContact.3");
                    if (this.groupList == null || this.groupList.size() <= 0 || !this.groupList.contains(str)) {
                        LogUtils.log("WS_BABY_initRoomContact.7");
                        this.jumpNum++;
                        this.startIndex++;
                        findDeleteGroup();
                        return;
                    }
                    this.lastName = "";
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
                PerformClickUtils.scroll(this.autoService, list_select_group_id);
                this.startIndex = 1;
                new PerformClickUtils2().check(this.autoService, list_select_group_name_id, 200, 10, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY_initRoomContact.10");
                        CleanGroupsSpecifiedUtils.this.groupNames = PerformClickUtils.findViewIdList((AccessibilityService) CleanGroupsSpecifiedUtils.this.autoService, BaseServiceUtils.list_select_group_name_id);
                        if (CleanGroupsSpecifiedUtils.this.groupNames == null || CleanGroupsSpecifiedUtils.this.groupNames.size() <= 0) {
                            CleanGroupsSpecifiedUtils.this.findDeleteGroup();
                            return;
                        }
                        AccessibilityNodeInfo accessibilityNodeInfo = CleanGroupsSpecifiedUtils.this.groupNames.get(CleanGroupsSpecifiedUtils.this.groupNames.size() - 1);
                        if (accessibilityNodeInfo != null) {
                            try {
                                if (accessibilityNodeInfo.getText() != null) {
                                    Rect rect = new Rect();
                                    accessibilityNodeInfo.getBoundsInScreen(rect);
                                    String str = (accessibilityNodeInfo.getText() + "") + rect.toString();
                                    LogUtils.log("WS_BABY_initRoomContact.11");
                                    if (str.equals(CleanGroupsSpecifiedUtils.this.lastName)) {
                                        LogUtils.log("WS_BABY_initRoomContact.end");
                                        BaseServiceUtils.removeEndView(CleanGroupsSpecifiedUtils.this.mMyManager);
                                        return;
                                    }
                                    String unused = CleanGroupsSpecifiedUtils.this.lastName = str;
                                    CleanGroupsSpecifiedUtils.this.findDeleteGroup();
                                    return;
                                }
                            } catch (Exception unused2) {
                                CleanGroupsSpecifiedUtils.this.findDeleteGroup();
                                return;
                            }
                        }
                        CleanGroupsSpecifiedUtils.this.findDeleteGroup();
                    }

                    public void nuFind() {
                        CleanGroupsSpecifiedUtils.this.findDeleteGroup();
                    }
                });
            }
        }
    }

    public void initFirstChattingUI() {
        LogUtils.log("WS_BABY.ChattingUI_1");
        new PerformClickUtils2().checkNodeId(this.autoService, right_more_id, executeSpeedSetting + 100, 50, 50, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY.ChattingUI_2");
                PerformClickUtils.findViewIdAndClick(CleanGroupsSpecifiedUtils.this.autoService, BaseServiceUtils.right_more_id);
                CleanGroupsSpecifiedUtils.this.initChatRoomInfoUI();
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.ChattingUI_6");
                PerformClickUtils.executedBackHome(CleanGroupsSpecifiedUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        CleanGroupsSpecifiedUtils.access$008(CleanGroupsSpecifiedUtils.this);
                        CleanGroupsSpecifiedUtils.this.executeAllMain();
                    }
                });
            }
        });
    }

    public void initChatRoomInfoUI() {
        try {
            LogUtils.log("WS_BABY.isFirstChatroomInfoUI");
            new PerformClickUtils2().checkNodeAllIds(this.autoService, default_list_id, list_head_img_id, 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    CleanGroupsSpecifiedUtils.this.deleteGroup();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteGroup() {
        new PerformClickUtils2().checkExistString(this.autoService, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                PerformClickUtils.findTextAndClick(CleanGroupsSpecifiedUtils.this.autoService, "删除并退出");
                new PerformClickUtils2().checkNodeIdOrName(CleanGroupsSpecifiedUtils.this.autoService, BaseServiceUtils.dialog_ok_id, "离开群聊", 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        if (i == 0) {
                            PerformClickUtils.findViewIdAndClick(CleanGroupsSpecifiedUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                        } else {
                            PerformClickUtils.findTextAndClick(CleanGroupsSpecifiedUtils.this.autoService, "离开群聊");
                        }
                        new PerformClickUtils2().checkNilString(CleanGroupsSpecifiedUtils.this.autoService, "请稍候", (int) SocializeConstants.CANCLE_RESULTCODE, 50, (int) SocializeConstants.CANCLE_RESULTCODE, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                CleanGroupsSpecifiedUtils.access$408(CleanGroupsSpecifiedUtils.this);
                                MyWindowManager myWindowManager = CleanGroupsSpecifiedUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "正在删除微信群，已删除 " + CleanGroupsSpecifiedUtils.this.deletedNum + " 个群");
                                CleanGroupsSpecifiedUtils.this.executeAllMain();
                            }
                        });
                    }
                });
            }
        });
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        setMiddleText(myWindowManager, "一键删除微信群", "已删除了" + this.deletedNum + "个微信群");
    }

    public void endViewShow() {
        showBottomView(this.mMyManager, "正在删除微信群，请不要操作微信！");
        LogUtils.log("WS_BABY.END_SHOW");
        new Thread(new Runnable() {
            public void run() {
                BaseServiceUtils.getMainHandler().post(new Runnable() {
                    public void run() {
                        CleanGroupsSpecifiedUtils.this.executeAllMain();
                    }
                });
            }
        }).start();
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }
}
