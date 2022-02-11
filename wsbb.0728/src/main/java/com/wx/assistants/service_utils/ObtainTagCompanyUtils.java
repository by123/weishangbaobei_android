package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.Enity.TagBeanCompay;
import com.wx.assistants.Enity.TagMemberBeanCompany;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SQLiteUtils;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class ObtainTagCompanyUtils extends BaseServiceCompanyUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static ObtainTagCompanyUtils instance;
    private boolean isWhile = true;
    /* access modifiers changed from: private */
    public String lastName = "";
    /* access modifiers changed from: private */
    public String result;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> tagList = new LinkedHashSet<>();
    List<AccessibilityNodeInfo> tags = null;
    /* access modifiers changed from: private */
    public String wxName = "";
    /* access modifiers changed from: private */
    public String wxNumber = "";

    private ObtainTagCompanyUtils() {
    }

    static /* synthetic */ int access$108(ObtainTagCompanyUtils obtainTagCompanyUtils) {
        int i = obtainTagCompanyUtils.startIndex;
        obtainTagCompanyUtils.startIndex = i + 1;
        return i;
    }

    public static ObtainTagCompanyUtils getInstance() {
        instance = new ObtainTagCompanyUtils();
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
                        ObtainTagCompanyUtils.this.executeGroupMain();
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
                    if (ObtainTagCompanyUtils.this.tags == null) {
                        ObtainTagCompanyUtils.this.tags = PerformClickUtils.findViewIdList((AccessibilityService) ObtainTagCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_tag_list_name_id);
                    }
                    if (ObtainTagCompanyUtils.this.tags != null && !ObtainTagCompanyUtils.this.tags.isEmpty() && MyApplication.enforceable) {
                        if (ObtainTagCompanyUtils.this.startIndex < ObtainTagCompanyUtils.this.tags.size() && MyApplication.enforceable) {
                            AccessibilityNodeInfo accessibilityNodeInfo2 = ObtainTagCompanyUtils.this.tags.get(ObtainTagCompanyUtils.this.startIndex);
                            if (accessibilityNodeInfo2 != null && MyApplication.enforceable) {
                                ObtainTagCompanyUtils obtainTagCompanyUtils = ObtainTagCompanyUtils.this;
                                String unused = obtainTagCompanyUtils.lastName = accessibilityNodeInfo2.getText() + "";
                                if (ObtainTagCompanyUtils.this.tagList.contains(ObtainTagCompanyUtils.this.lastName)) {
                                    ObtainTagCompanyUtils.access$108(ObtainTagCompanyUtils.this);
                                } else {
                                    ObtainTagCompanyUtils.this.tagList.add(ObtainTagCompanyUtils.this.lastName);
                                    ObtainTagCompanyUtils.access$108(ObtainTagCompanyUtils.this);
                                }
                                ObtainTagCompanyUtils.this.executeGroup();
                            }
                        } else if (ObtainTagCompanyUtils.this.startIndex >= ObtainTagCompanyUtils.this.tags.size()) {
                            ObtainTagCompanyUtils.this.tags = null;
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) ObtainTagCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_tag_list_id);
                            if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = findViewIdList.get(0)) != null && MyApplication.enforceable) {
                                PerformClickUtils.scroll(accessibilityNodeInfo);
                                new PerformClickUtils2().checkNodeAllIds(ObtainTagCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_tag_list_id, BaseServiceCompanyUtils.company_tag_list_name_id, 10, 10, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                                        AccessibilityNodeInfo accessibilityNodeInfo;
                                        ObtainTagCompanyUtils.this.sleep(10);
                                        System.out.println("WS_BABY_TIME.end2 = " + System.currentTimeMillis());
                                        AccessibilityNodeInfo rootInActiveWindow = ObtainTagCompanyUtils.this.autoService.getRootInActiveWindow();
                                        if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceCompanyUtils.company_tag_list_name_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1)) != null && MyApplication.enforceable) {
                                            String str = accessibilityNodeInfo.getText() + "";
                                            int unused = ObtainTagCompanyUtils.this.startIndex = 0;
                                            if (str.equals(ObtainTagCompanyUtils.this.lastName)) {
                                                ObtainTagCompanyUtils.this.tjGroup();
                                            } else {
                                                ObtainTagCompanyUtils.this.executeGroup();
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
                    PerformClickUtils.findTextAndClick(ObtainTagCompanyUtils.this.autoService, "我");
                    new PerformClickUtils2().checkNodeAllIds(ObtainTagCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_wx_company_id, BaseServiceCompanyUtils.company_wx_nick_name_id, BaseServiceCompanyUtils.executeSpeedSetting + 100, 100, 600, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            AccessibilityNodeInfo accessibilityNodeInfo;
                            String str;
                            String str2;
                            AccessibilityNodeInfo rootInActiveWindow = ObtainTagCompanyUtils.this.autoService.getRootInActiveWindow();
                            if (rootInActiveWindow != null && MyApplication.enforceable) {
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceCompanyUtils.company_wx_company_id);
                                if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(0)) != null && MyApplication.enforceable) {
                                    try {
                                        str = accessibilityNodeInfo.getText() + "";
                                    } catch (Exception e) {
                                        str = "";
                                    }
                                    AccessibilityNodeInfo rootInActiveWindow2 = ObtainTagCompanyUtils.this.autoService.getRootInActiveWindow();
                                    if (rootInActiveWindow2 != null && MyApplication.enforceable) {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceCompanyUtils.company_wx_nick_name_id);
                                        if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                                            AccessibilityNodeInfo accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId2.get(0);
                                            try {
                                                str2 = accessibilityNodeInfo2.getText() + "";
                                            } catch (Exception e2) {
                                            }
                                            String unused = ObtainTagCompanyUtils.this.wxName = str2;
                                            String unused2 = ObtainTagCompanyUtils.this.wxNumber = str + "_" + str2;
                                            TagBeanCompay tagBeanCompay = new TagBeanCompay();
                                            tagBeanCompay.setWxName(ObtainTagCompanyUtils.this.wxName);
                                            tagBeanCompay.setWxCompany(ObtainTagCompanyUtils.this.wxNumber);
                                            SQLiteUtils.getInstance().addTagBeanCompany(tagBeanCompay);
                                            System.out.println("WS_BABY_" + ObtainTagCompanyUtils.this.wxName + ListUtils.DEFAULT_JOIN_SEPARATOR + ObtainTagCompanyUtils.this.wxNumber);
                                        }
                                        str2 = "";
                                        String unused3 = ObtainTagCompanyUtils.this.wxName = str2;
                                        String unused4 = ObtainTagCompanyUtils.this.wxNumber = str + "_" + str2;
                                        TagBeanCompay tagBeanCompay2 = new TagBeanCompay();
                                        tagBeanCompay2.setWxName(ObtainTagCompanyUtils.this.wxName);
                                        tagBeanCompay2.setWxCompany(ObtainTagCompanyUtils.this.wxNumber);
                                        SQLiteUtils.getInstance().addTagBeanCompany(tagBeanCompay2);
                                        System.out.println("WS_BABY_" + ObtainTagCompanyUtils.this.wxName + ListUtils.DEFAULT_JOIN_SEPARATOR + ObtainTagCompanyUtils.this.wxNumber);
                                    }
                                }
                                PerformClickUtils.findTextAndClick(ObtainTagCompanyUtils.this.autoService, "通讯录");
                                PerformClickUtils.findTextAndClick(ObtainTagCompanyUtils.this.autoService, "通讯录");
                                PerformClickUtils.findTextAndClick(ObtainTagCompanyUtils.this.autoService, "通讯录");
                                new PerformClickUtils2().checkString(ObtainTagCompanyUtils.this.autoService, "我的客户", BaseServiceCompanyUtils.executeSpeedSetting + 50, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        PerformClickUtils.findTextAndClick(ObtainTagCompanyUtils.this.autoService, "我的客户");
                                        new PerformClickUtils2().checkString(ObtainTagCompanyUtils.this.autoService, "标签", 100, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                PerformClickUtils.findTextAndClick(ObtainTagCompanyUtils.this.autoService, "标签");
                                                ObtainTagCompanyUtils.this.initChatRoomContactUI();
                                            }

                                            public void nuFind() {
                                                BaseServiceCompanyUtils.removeEndView(ObtainTagCompanyUtils.this.mMyManager);
                                            }
                                        });
                                    }

                                    public void nuFind() {
                                        BaseServiceCompanyUtils.removeEndView(ObtainTagCompanyUtils.this.mMyManager);
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

    public void initChatRoomContactUI() {
        try {
            new PerformClickUtils2().checkNodeAllIds(this.autoService, company_tag_list_id, company_tag_list_name_id, executeSpeedSetting + 100, 100, 20, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    ObtainTagCompanyUtils.this.executeGroup();
                }

                public void nuFind() {
                    String unused = ObtainTagCompanyUtils.this.result = "您还没有建立客户标签";
                    BaseServiceCompanyUtils.removeEndView(ObtainTagCompanyUtils.this.mMyManager);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initData() {
        this.wxName = "";
        this.wxNumber = "";
        this.result = "";
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
            if (this.result == null || "".equals(this.result)) {
                MyWindowManager myWindowManager = this.mMyManager;
                setMiddleText(myWindowManager, "标签获取完成", "共获取" + this.tagList.size() + "个");
                return;
            }
            setMiddleText(this.mMyManager, "标签获取完成", this.result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tjGroup() {
        if (MyApplication.enforceable) {
            StringBuilder sb = new StringBuilder();
            if (this.tagList != null && this.tagList.size() > 0 && MyApplication.enforceable) {
                Iterator it = this.tagList.iterator();
                while (it.hasNext()) {
                    sb.append(((String) it.next()) + ";");
                }
            }
            TagMemberBeanCompany tagMemberBeanCompany = new TagMemberBeanCompany();
            tagMemberBeanCompany.setWxCompany(this.wxNumber);
            tagMemberBeanCompany.setWxName(this.wxName);
            tagMemberBeanCompany.setWxTags(sb.toString());
            SQLiteUtils.getInstance().addTagMemberBeanCompany(tagMemberBeanCompany);
            PrintStream printStream = System.out;
            printStream.println("WS_BABY_" + sb.toString());
            removeEndView(this.mMyManager);
        }
    }
}
