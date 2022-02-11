package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.Enity.GroupBeanAll;
import com.wx.assistants.Enity.GroupMemberBeanAll;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.service.AutoService;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.SQLiteUtils;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class ObtainGroupAllUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static ObtainGroupAllUtils instance;
    /* access modifiers changed from: private */
    public boolean isWhile = true;
    /* access modifiers changed from: private */
    public String lastName = "";
    /* access modifiers changed from: private */
    public int startIndex = 0;
    private LinkedHashSet<String> tagList = new LinkedHashSet<>();
    List<AccessibilityNodeInfo> tags = null;
    /* access modifiers changed from: private */
    public String wxName = "";
    /* access modifiers changed from: private */
    public String wxNumber = "";

    public void middleViewDismiss() {
    }

    private ObtainGroupAllUtils() {
    }

    public static ObtainGroupAllUtils getInstance() {
        instance = new ObtainGroupAllUtils();
        return instance;
    }

    public void initData() {
        this.wxName = "";
        this.wxNumber = "";
        this.startIndex = 0;
        this.isWhile = true;
        this.tagList = new LinkedHashSet<>();
        this.lastName = "";
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initChatRoomContactUI() {
        try {
            new PerformClickUtils2().checkNodeAllIds(this.autoService, list_select_group_id, list_select_group_name_id, executeSpeedSetting + 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    new Thread(new Runnable() {
                        public void run() {
                            ObtainGroupAllUtils.this.executeGroup();
                        }
                    }).start();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeGroup() {
        AccessibilityNodeInfo accessibilityNodeInfo;
        if (this.tags == null) {
            this.tags = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_select_group_name_id);
        }
        if (this.tags != null && this.tags.size() > 0 && MyApplication.enforceable) {
            if (this.startIndex < this.tags.size() && MyApplication.enforceable) {
                AccessibilityNodeInfo accessibilityNodeInfo2 = this.tags.get(this.startIndex);
                if (accessibilityNodeInfo2 != null) {
                    if (accessibilityNodeInfo2.getText() != null) {
                        this.lastName = accessibilityNodeInfo2.getText() + "";
                    }
                    if (this.tagList.contains(this.lastName)) {
                        this.startIndex++;
                    } else {
                        this.tagList.add(this.lastName);
                        this.startIndex++;
                    }
                    executeGroup();
                }
            } else if (this.startIndex >= this.tags.size()) {
                this.tags = null;
                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_select_group_id);
                if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = findViewIdList.get(0)) != null && MyApplication.enforceable) {
                    PerformClickUtils.scroll(accessibilityNodeInfo);
                    new PerformClickUtils2().checkNodeAllIds(this.autoService, list_select_group_id, list_select_group_name_id, 10, 10, 100, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) ObtainGroupAllUtils.this.autoService, BaseServiceUtils.list_select_group_name_id);
                            if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                                ObtainGroupAllUtils.this.executeGroup();
                                return;
                            }
                            AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(findViewIdList.size() - 1);
                            if (accessibilityNodeInfo == null || !MyApplication.enforceable) {
                                ObtainGroupAllUtils.this.executeGroup();
                                return;
                            }
                            String str = accessibilityNodeInfo.getText() + "";
                            int unused = ObtainGroupAllUtils.this.startIndex = 0;
                            if (str.equals(ObtainGroupAllUtils.this.lastName)) {
                                boolean unused2 = ObtainGroupAllUtils.this.isWhile = false;
                                ObtainGroupAllUtils.this.tjGroup();
                                return;
                            }
                            ObtainGroupAllUtils.this.executeGroup();
                        }
                    });
                }
            }
        }
    }

    public void tjGroup() {
        String str = (String) SPUtils.get(this.autoService, "wx_user_all", "wx_user_all");
        StringBuilder sb = new StringBuilder();
        if (this.tagList != null && this.tagList.size() > 0 && MyApplication.enforceable) {
            Iterator it = this.tagList.iterator();
            while (it.hasNext()) {
                sb.append(((String) it.next()) + ";");
            }
        }
        GroupMemberBeanAll groupMemberBeanAll = new GroupMemberBeanAll();
        groupMemberBeanAll.setWxNum(this.wxNumber);
        groupMemberBeanAll.setWxName(this.wxName);
        groupMemberBeanAll.setWxGroups(sb.toString());
        SQLiteUtils.getInstance().addGroupMemberBeanAll(groupMemberBeanAll);
        AutoService autoService = this.autoService;
        SPUtils.put(autoService, str + "_groups_all", sb.toString());
        removeEndView(this.mMyManager);
    }

    public void executeGroupMain() {
        try {
            new PerformClickUtils2().checkString(this.autoService, "我", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findTextAndClick(ObtainGroupAllUtils.this.autoService, "我");
                    new PerformClickUtils2().check(ObtainGroupAllUtils.this.autoService, BaseServiceUtils.wx_num_id, BaseServiceUtils.executeSpeedSetting + 100, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            String str;
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) ObtainGroupAllUtils.this.autoService, BaseServiceUtils.wx_num_id);
                            if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                                String unused = ObtainGroupAllUtils.this.wxName = "未知昵称";
                                String unused2 = ObtainGroupAllUtils.this.wxNumber = System.currentTimeMillis() + "";
                                GroupBeanAll groupBeanAll = new GroupBeanAll();
                                groupBeanAll.setWxName(ObtainGroupAllUtils.this.wxName);
                                groupBeanAll.setWxNum(ObtainGroupAllUtils.this.wxNumber);
                                SQLiteUtils.getInstance().addGroupBeanAll(groupBeanAll);
                                SPUtils.put(ObtainGroupAllUtils.this.autoService, "wx_user_all", ObtainGroupAllUtils.this.wxName + "#" + ObtainGroupAllUtils.this.wxNumber);
                            } else {
                                String str2 = "";
                                AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(0);
                                if (accessibilityNodeInfo != null) {
                                    if (accessibilityNodeInfo.getText() != null) {
                                        str2 = accessibilityNodeInfo.getText() + "";
                                    }
                                    AccessibilityNodeInfo rootInActiveWindow = ObtainGroupAllUtils.this.autoService.getRootInActiveWindow();
                                    if (rootInActiveWindow != null && MyApplication.enforceable) {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.wx_nick_name_id);
                                        if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                            AccessibilityNodeInfo accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId.get(0);
                                            try {
                                                str = accessibilityNodeInfo2.getText() + "";
                                            } catch (Exception unused3) {
                                            }
                                            String unused4 = ObtainGroupAllUtils.this.wxName = str;
                                            String unused5 = ObtainGroupAllUtils.this.wxNumber = str2;
                                            GroupBeanAll groupBeanAll2 = new GroupBeanAll();
                                            groupBeanAll2.setWxName(str);
                                            groupBeanAll2.setWxNum(str2);
                                            SQLiteUtils.getInstance().addGroupBeanAll(groupBeanAll2);
                                            SPUtils.put(ObtainGroupAllUtils.this.autoService, "wx_user_all", str + "#" + str2);
                                        }
                                        str = "";
                                        String unused6 = ObtainGroupAllUtils.this.wxName = str;
                                        String unused7 = ObtainGroupAllUtils.this.wxNumber = str2;
                                        GroupBeanAll groupBeanAll22 = new GroupBeanAll();
                                        groupBeanAll22.setWxName(str);
                                        groupBeanAll22.setWxNum(str2);
                                        SQLiteUtils.getInstance().addGroupBeanAll(groupBeanAll22);
                                        SPUtils.put(ObtainGroupAllUtils.this.autoService, "wx_user_all", str + "#" + str2);
                                    }
                                }
                            }
                            new PerformClickUtils2().checkString(ObtainGroupAllUtils.this.autoService, "通讯录", 100, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(ObtainGroupAllUtils.this.autoService, "通讯录");
                                    new PerformClickUtils2().checkNodeId(ObtainGroupAllUtils.this.autoService, BaseServiceUtils.contact_nav_search_viewgroup_id, 100, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) ObtainGroupAllUtils.this.autoService, BaseServiceUtils.contact_nav_search_viewgroup_id);
                                            if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                                                PerformClickUtils.findTextAndClickFirst(ObtainGroupAllUtils.this.autoService, "更多功能按钮");
                                                new PerformClickUtils2().checkString(ObtainGroupAllUtils.this.autoService, "发起群聊", 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                                    public void nuFind() {
                                                    }

                                                    public void find(int i) {
                                                        PerformClickUtils.findTextAndClick(ObtainGroupAllUtils.this.autoService, "发起群聊");
                                                        new PerformClickUtils2().checkString(ObtainGroupAllUtils.this.autoService, "选择一个群", 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                                            public void nuFind() {
                                                            }

                                                            public void find(int i) {
                                                                PerformClickUtils.findTextAndClick(ObtainGroupAllUtils.this.autoService, "选择一个群");
                                                                new PerformClickUtils2().checkString(ObtainGroupAllUtils.this.autoService, "选择群聊", 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                                                    public void nuFind() {
                                                                    }

                                                                    public void find(int i) {
                                                                        ObtainGroupAllUtils.this.initChatRoomContactUI();
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
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void middleViewShow() {
        try {
            MyWindowManager myWindowManager = this.mMyManager;
            setMiddleText(myWindowManager, "群组获取完成", "共获取" + this.tagList.size() + "个群组");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewShow() {
        try {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        ObtainGroupAllUtils.this.executeGroupMain();
                    } catch (Exception unused) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }
}
