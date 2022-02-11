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
import java.util.List;

public class CleanGroupsAllUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static CleanGroupsAllUtils instance;
    /* access modifiers changed from: private */
    public int deletedNum = 0;
    private int index = 0;

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$008(CleanGroupsAllUtils cleanGroupsAllUtils) {
        int i = cleanGroupsAllUtils.index;
        cleanGroupsAllUtils.index = i + 1;
        return i;
    }

    static /* synthetic */ int access$108(CleanGroupsAllUtils cleanGroupsAllUtils) {
        int i = cleanGroupsAllUtils.deletedNum;
        cleanGroupsAllUtils.deletedNum = i + 1;
        return i;
    }

    private CleanGroupsAllUtils() {
    }

    public static CleanGroupsAllUtils getInstance() {
        instance = new CleanGroupsAllUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.index = 0;
        this.deletedNum = 0;
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void executeAllMain() {
        try {
            new PerformClickUtils2().checkString(this.autoService, "通讯录", 100, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findTextAndClick(CleanGroupsAllUtils.this.autoService, "通讯录");
                    new PerformClickUtils2().checkNodeId(CleanGroupsAllUtils.this.autoService, BaseServiceUtils.contact_nav_search_viewgroup_id, 100, 100, 600, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanGroupsAllUtils.this.autoService, BaseServiceUtils.contact_nav_search_viewgroup_id);
                            if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                                PerformClickUtils.findTextAndClickFirst(CleanGroupsAllUtils.this.autoService, "更多功能按钮");
                                new PerformClickUtils2().checkString(CleanGroupsAllUtils.this.autoService, "发起群聊", 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        PerformClickUtils.findTextAndClick(CleanGroupsAllUtils.this.autoService, "发起群聊");
                                        new PerformClickUtils2().checkString(CleanGroupsAllUtils.this.autoService, "选择一个群", 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                            public void nuFind() {
                                            }

                                            public void find(int i) {
                                                PerformClickUtils.findTextAndClick(CleanGroupsAllUtils.this.autoService, "选择一个群");
                                                new PerformClickUtils2().checkString(CleanGroupsAllUtils.this.autoService, "选择群聊", 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                                    public void nuFind() {
                                                    }

                                                    public void find(int i) {
                                                        CleanGroupsAllUtils.this.findGroup();
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

    public void findGroup() {
        new PerformClickUtils2().checkGroupNodeAllIds(this.autoService, list_select_group_id, list_select_group_name_id, "暂无群聊可选", executeSpeedSetting + 100, 100, 25, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                if (i == 0) {
                    CleanGroupsAllUtils.this.executeDeleteGroup();
                } else if (i == 1) {
                    BaseServiceUtils.removeEndView(CleanGroupsAllUtils.this.mMyManager);
                }
            }

            public void nuFind() {
                BaseServiceUtils.removeEndView(CleanGroupsAllUtils.this.mMyManager);
            }
        });
    }

    public void executeDeleteGroup() {
        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_select_group_name_id);
        if (findViewIdList == null || findViewIdList.size() <= this.index) {
            removeEndView(this.mMyManager);
            return;
        }
        PerformClickUtils.performClick(findViewIdList.get(this.index));
        initFirstChattingUI();
    }

    public void initFirstChattingUI() {
        LogUtils.log("WS_BABY.ChattingUI_1");
        new PerformClickUtils2().checkNodeId(this.autoService, right_more_id, executeSpeedSetting + 150, 50, 50, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY.ChattingUI_2");
                PerformClickUtils.findViewIdAndClick(CleanGroupsAllUtils.this.autoService, BaseServiceUtils.right_more_id);
                CleanGroupsAllUtils.this.initChatRoomInfoUI();
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.ChattingUI_6");
                CleanGroupsAllUtils.access$008(CleanGroupsAllUtils.this);
                PerformClickUtils.executedBackHome(CleanGroupsAllUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        CleanGroupsAllUtils.this.executeAllMain();
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
                    new PerformClickUtils2().checkExistString(CleanGroupsAllUtils.this.autoService, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(CleanGroupsAllUtils.this.autoService, "删除并退出");
                            new PerformClickUtils2().checkNodeIdOrName(CleanGroupsAllUtils.this.autoService, BaseServiceUtils.dialog_ok_id, "离开群聊", 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    if (i == 0) {
                                        PerformClickUtils.findViewIdAndClick(CleanGroupsAllUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                    } else {
                                        PerformClickUtils.findTextAndClick(CleanGroupsAllUtils.this.autoService, "离开群聊");
                                    }
                                    new PerformClickUtils2().checkNilString(CleanGroupsAllUtils.this.autoService, "请稍候", (int) SocializeConstants.CANCLE_RESULTCODE, 50, (int) SocializeConstants.CANCLE_RESULTCODE, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            CleanGroupsAllUtils.access$108(CleanGroupsAllUtils.this);
                                            MyWindowManager myWindowManager = CleanGroupsAllUtils.this.mMyManager;
                                            BaseServiceUtils.updateBottomText(myWindowManager, "正在删除微信群，已删除 " + CleanGroupsAllUtils.this.deletedNum + " 个群");
                                            CleanGroupsAllUtils.this.executeAllMain();
                                        }
                                    });
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
                        CleanGroupsAllUtils.this.executeAllMain();
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
