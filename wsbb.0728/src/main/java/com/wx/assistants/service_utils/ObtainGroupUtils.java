package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.Enity.GroupBean;
import com.wx.assistants.Enity.GroupMemberBean;
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

public class ObtainGroupUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static ObtainGroupUtils instance;
    private boolean isWhile = true;
    /* access modifiers changed from: private */
    public String lastName = "";
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> tagList = new LinkedHashSet<>();
    List<AccessibilityNodeInfo> tags = null;
    /* access modifiers changed from: private */
    public String wxName = "";
    /* access modifiers changed from: private */
    public String wxNumber = "";

    private ObtainGroupUtils() {
    }

    static /* synthetic */ int access$008(ObtainGroupUtils obtainGroupUtils) {
        int i = obtainGroupUtils.startIndex;
        obtainGroupUtils.startIndex = i + 1;
        return i;
    }

    public static ObtainGroupUtils getInstance() {
        instance = new ObtainGroupUtils();
        return instance;
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        try {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        ObtainGroupUtils.this.executeGroupMain();
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeGroup() {
        new Thread(new Runnable() {
            public void run() {
                AccessibilityNodeInfo accessibilityNodeInfo;
                try {
                    if (ObtainGroupUtils.this.tags == null) {
                        ObtainGroupUtils.this.tags = PerformClickUtils.findViewIdList((AccessibilityService) ObtainGroupUtils.this.autoService, BaseServiceUtils.group_list_item_name_id);
                    }
                    if (ObtainGroupUtils.this.tags != null && !ObtainGroupUtils.this.tags.isEmpty() && MyApplication.enforceable) {
                        if (ObtainGroupUtils.this.startIndex < ObtainGroupUtils.this.tags.size() && MyApplication.enforceable) {
                            AccessibilityNodeInfo accessibilityNodeInfo2 = ObtainGroupUtils.this.tags.get(ObtainGroupUtils.this.startIndex);
                            if (accessibilityNodeInfo2 != null && MyApplication.enforceable) {
                                ObtainGroupUtils obtainGroupUtils = ObtainGroupUtils.this;
                                String unused = obtainGroupUtils.lastName = accessibilityNodeInfo2.getText() + "";
                                if (ObtainGroupUtils.this.tagList.contains(ObtainGroupUtils.this.lastName)) {
                                    ObtainGroupUtils.access$008(ObtainGroupUtils.this);
                                } else {
                                    ObtainGroupUtils.this.tagList.add(ObtainGroupUtils.this.lastName);
                                    ObtainGroupUtils.access$008(ObtainGroupUtils.this);
                                }
                                ObtainGroupUtils.this.executeGroup();
                            }
                        } else if (ObtainGroupUtils.this.startIndex >= ObtainGroupUtils.this.tags.size()) {
                            ObtainGroupUtils.this.tags = null;
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) ObtainGroupUtils.this.autoService, BaseServiceUtils.grout_friend_list_id);
                            if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = findViewIdList.get(0)) != null && MyApplication.enforceable) {
                                PerformClickUtils.scroll(accessibilityNodeInfo);
                                new PerformClickUtils2().checkNodeAllIds(ObtainGroupUtils.this.autoService, BaseServiceUtils.grout_friend_list_id, BaseServiceUtils.group_list_item_name_id, 10, 10, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                                        AccessibilityNodeInfo accessibilityNodeInfo;
                                        ObtainGroupUtils.this.sleep(10);
                                        System.out.println("WS_BABY_TIME.end2 = " + System.currentTimeMillis());
                                        AccessibilityNodeInfo rootInActiveWindow = ObtainGroupUtils.this.autoService.getRootInActiveWindow();
                                        if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_list_item_name_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1)) != null && MyApplication.enforceable) {
                                            String str = accessibilityNodeInfo.getText() + "";
                                            int unused = ObtainGroupUtils.this.startIndex = 0;
                                            if (str.equals(ObtainGroupUtils.this.lastName)) {
                                                ObtainGroupUtils.this.tjGroup();
                                            } else {
                                                ObtainGroupUtils.this.executeGroup();
                                            }
                                        }
                                    }

                                    public void nuFind() {
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

    public void executeGroupMain() {
        try {
            new PerformClickUtils2().checkString(this.autoService, "我", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findTextAndClick(ObtainGroupUtils.this.autoService, "我");
                    new PerformClickUtils2().check(ObtainGroupUtils.this.autoService, BaseServiceUtils.wx_num_id, BaseServiceUtils.executeSpeedSetting + 100, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            String str;
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) ObtainGroupUtils.this.autoService, BaseServiceUtils.wx_num_id);
                            if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                                String unused = ObtainGroupUtils.this.wxName = "宝贝";
                                String unused2 = ObtainGroupUtils.this.wxNumber = System.currentTimeMillis() + "";
                                GroupBean groupBean = new GroupBean();
                                groupBean.setWxName(ObtainGroupUtils.this.wxName);
                                groupBean.setWxNum(ObtainGroupUtils.this.wxNumber);
                                SQLiteUtils.getInstance().addGroupBean(groupBean);
                                SPUtils.put(ObtainGroupUtils.this.autoService, "wx_user", ObtainGroupUtils.this.wxName + "#" + ObtainGroupUtils.this.wxNumber);
                            } else {
                                String str2 = "";
                                AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(0);
                                if (accessibilityNodeInfo != null && MyApplication.enforceable) {
                                    try {
                                        if (accessibilityNodeInfo.getText() != null) {
                                            str2 = accessibilityNodeInfo.getText() + "";
                                        }
                                    } catch (Exception e) {
                                    }
                                    List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) ObtainGroupUtils.this.autoService, BaseServiceUtils.wx_nick_name_id);
                                    if (findViewIdList2 != null && findViewIdList2.size() > 0 && MyApplication.enforceable) {
                                        AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList2.get(0);
                                        try {
                                            if (accessibilityNodeInfo2.getText() != null) {
                                                str = accessibilityNodeInfo2.getText() + "";
                                                String unused3 = ObtainGroupUtils.this.wxName = str;
                                                String unused4 = ObtainGroupUtils.this.wxNumber = str2;
                                                GroupBean groupBean2 = new GroupBean();
                                                groupBean2.setWxName(str);
                                                groupBean2.setWxNum(str2);
                                                SQLiteUtils.getInstance().addGroupBean(groupBean2);
                                                SPUtils.put(ObtainGroupUtils.this.autoService, "wx_user", str + "#" + str2);
                                            }
                                        } catch (Exception e2) {
                                            str = "";
                                        }
                                    }
                                    str = "";
                                    String unused5 = ObtainGroupUtils.this.wxName = str;
                                    String unused6 = ObtainGroupUtils.this.wxNumber = str2;
                                    GroupBean groupBean22 = new GroupBean();
                                    groupBean22.setWxName(str);
                                    groupBean22.setWxNum(str2);
                                    SQLiteUtils.getInstance().addGroupBean(groupBean22);
                                    SPUtils.put(ObtainGroupUtils.this.autoService, "wx_user", str + "#" + str2);
                                }
                            }
                            PerformClickUtils.findTextAndClick(ObtainGroupUtils.this.autoService, "通讯录");
                            PerformClickUtils.findTextAndClick(ObtainGroupUtils.this.autoService, "通讯录");
                            PerformClickUtils.findTextAndClick(ObtainGroupUtils.this.autoService, "通讯录");
                            new PerformClickUtils2().checkString(ObtainGroupUtils.this.autoService, "群聊", BaseServiceUtils.executeSpeedSetting + 50, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(ObtainGroupUtils.this.autoService, "群聊");
                                    ObtainGroupUtils.this.initChatRoomContactUI();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initChatRoomContactUI() {
        try {
            new PerformClickUtils2().checkNodeAllIds(this.autoService, grout_friend_list_id, group_list_item_name_id, executeSpeedSetting + 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    ObtainGroupUtils.this.executeGroup();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        try {
            MyWindowManager myWindowManager = this.mMyManager;
            setMiddleText(myWindowManager, "群组获取完成", "共获取" + this.tagList.size() + "个群组");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tjGroup() {
        if (MyApplication.enforceable) {
            String str = (String) SPUtils.get(this.autoService, "wx_user", "wx_user");
            StringBuilder sb = new StringBuilder();
            if (this.tagList != null && this.tagList.size() > 0 && MyApplication.enforceable) {
                Iterator it = this.tagList.iterator();
                while (it.hasNext()) {
                    sb.append(((String) it.next()) + ";");
                }
            }
            AutoService autoService = this.autoService;
            SPUtils.put(autoService, str + "_groups", sb.toString());
            GroupMemberBean groupMemberBean = new GroupMemberBean();
            groupMemberBean.setWxNum(this.wxNumber);
            groupMemberBean.setWxName(this.wxName);
            groupMemberBean.setWxGroups(sb.toString());
            SQLiteUtils.getInstance().addGroupMemberBean(groupMemberBean);
            removeEndView(this.mMyManager);
        }
    }
}
