package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.Enity.TagBean;
import com.wx.assistants.Enity.TagMemberBean;
import com.wx.assistants.Enity.TagPeoplesBean;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.service.AutoService;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.SQLiteUtils;
import com.youth.banner.BannerConfig;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class ObtainTagUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static ObtainTagUtils instance;
    /* access modifiers changed from: private */
    public boolean isContactLabelManagerUI = true;
    /* access modifiers changed from: private */
    public boolean isFirstExecuted = true;
    /* access modifiers changed from: private */
    public String lastName = "";
    /* access modifiers changed from: private */
    public String lastNameNum = "0";
    /* access modifiers changed from: private */
    public String lastPeopleName = "";
    /* access modifiers changed from: private */
    public String nowName = "";
    List<AccessibilityNodeInfo> scrollTags = null;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startIndex2 = 0;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> tagJumpList = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public LinkedHashSet<String> tagList = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public LinkedHashSet<String> tagPeopleList = new LinkedHashSet<>();
    List<AccessibilityNodeInfo> tags = null;
    List<AccessibilityNodeInfo> tagsNum = null;
    /* access modifiers changed from: private */
    public int waitTime = BannerConfig.DURATION;
    /* access modifiers changed from: private */
    public String wxName = "";
    /* access modifiers changed from: private */
    public String wxNumber = "";

    public void middleViewDismiss() {
    }

    private ObtainTagUtils() {
    }

    public static ObtainTagUtils getInstance() {
        instance = new ObtainTagUtils();
        return instance;
    }

    public void initData() {
        this.wxName = "";
        this.wxNumber = "";
        this.startIndex = 0;
        this.startIndex2 = 0;
        this.tagList = new LinkedHashSet<>();
        this.tagJumpList = new LinkedHashSet<>();
        this.tagPeopleList = new LinkedHashSet<>();
        this.lastName = "";
        this.nowName = "";
        this.lastPeopleName = "";
        this.isContactLabelManagerUI = true;
        this.isFirstExecuted = true;
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        this.waitTime = Build.VERSION.SDK_INT > 23 ? BannerConfig.DURATION : 1100;
    }

    public void executeTagMain() {
        try {
            this.isContactLabelManagerUI = true;
            new PerformClickUtils2().checkString(this.autoService, "我", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findTextAndClick(ObtainTagUtils.this.autoService, "我");
                    new PerformClickUtils2().check(ObtainTagUtils.this.autoService, BaseServiceUtils.wx_num_id, BaseServiceUtils.executeSpeedSetting + 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            AccessibilityNodeInfo accessibilityNodeInfo;
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) ObtainTagUtils.this.autoService, BaseServiceUtils.wx_num_id);
                            if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                                String unused = ObtainTagUtils.this.wxName = "宝贝";
                                String unused2 = ObtainTagUtils.this.wxNumber = System.currentTimeMillis() + "";
                                TagBean tagBean = new TagBean();
                                tagBean.setWxName("");
                                tagBean.setWxNum("");
                                SQLiteUtils.getInstance().addTagBean(tagBean);
                                SPUtils.put(ObtainTagUtils.this.autoService, "wx_user", "" + "#" + "");
                            } else {
                                String str = "";
                                String str2 = "";
                                AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList.get(0);
                                if (accessibilityNodeInfo2 != null) {
                                    if (accessibilityNodeInfo2.getText() != null) {
                                        str = accessibilityNodeInfo2.getText() + "";
                                    }
                                    List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) ObtainTagUtils.this.autoService, BaseServiceUtils.wx_nick_name_id);
                                    if (findViewIdList2 != null && findViewIdList2.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = findViewIdList2.get(0)) != null && accessibilityNodeInfo.getText() != null && MyApplication.enforceable) {
                                        str2 = accessibilityNodeInfo.getText() + "";
                                    }
                                    String unused3 = ObtainTagUtils.this.wxName = str2;
                                    String unused4 = ObtainTagUtils.this.wxNumber = str;
                                    TagBean tagBean2 = new TagBean();
                                    tagBean2.setWxName(str2);
                                    tagBean2.setWxNum(str);
                                    SQLiteUtils.getInstance().addTagBean(tagBean2);
                                    SPUtils.put(ObtainTagUtils.this.autoService, "wx_user", str2 + "#" + str);
                                }
                            }
                            PerformClickUtils.findTextAndClick(ObtainTagUtils.this.autoService, "通讯录");
                            PerformClickUtils.findTextAndClick(ObtainTagUtils.this.autoService, "通讯录");
                            PerformClickUtils.findTextAndClick(ObtainTagUtils.this.autoService, "通讯录");
                            new PerformClickUtils2().checkString(ObtainTagUtils.this.autoService, "标签", BaseServiceUtils.executeSpeedSetting + 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(ObtainTagUtils.this.autoService, "标签");
                                    LogUtils.log("WS_BABY_WAIT_TIME_" + ObtainTagUtils.this.waitTime);
                                    ObtainTagUtils.this.sleep(ObtainTagUtils.this.waitTime);
                                    ObtainTagUtils.this.initContactLabelManagerUI();
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

    public void initContactLabelManagerUI() {
        if (this.isContactLabelManagerUI && MyApplication.enforceable) {
            LogUtils.log("WS_BABY_ContactLabelManagerUI_0");
            this.isContactLabelManagerUI = false;
            new PerformClickUtils2().checkNodeIdOrName(this.autoService, tag_list_item_name_id, "新建标签", 10, 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    AccessibilityNodeInfo accessibilityNodeInfo;
                    AccessibilityNodeInfo accessibilityNodeInfo2;
                    if (i == 0) {
                        if (ObtainTagUtils.this.isFirstExecuted) {
                            boolean unused = ObtainTagUtils.this.isFirstExecuted = false;
                            AccessibilityNodeInfo rootInActiveWindow = ObtainTagUtils.this.autoService.getRootInActiveWindow();
                            if (rootInActiveWindow != null && MyApplication.enforceable) {
                                boolean z = true;
                                int i2 = 0;
                                while (z) {
                                    LogUtils.log("WS_BABY_ContactLabelManagerUI_0000");
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.tag_list_id);
                                    if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId.get(0)) != null && MyApplication.enforceable) {
                                        PerformClickUtils.scroll(accessibilityNodeInfo2);
                                        ObtainTagUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                        i2++;
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.tag_list_item_name_id);
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.tag_list_item_num_id);
                                        if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && findAccessibilityNodeInfosByViewId3 != null && findAccessibilityNodeInfosByViewId3.size() > 0) {
                                            String str = findAccessibilityNodeInfosByViewId2.get(findAccessibilityNodeInfosByViewId2.size() - 1).getText() + "";
                                            if (!str.equals(ObtainTagUtils.this.lastName)) {
                                                String unused2 = ObtainTagUtils.this.lastName = str;
                                            } else {
                                                z = false;
                                            }
                                        }
                                    }
                                }
                                while (i2 != 0) {
                                    LogUtils.log("WS_BABY_ContactLabelManagerUI_1111");
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId4 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.tag_list_id);
                                    if (findAccessibilityNodeInfosByViewId4 != null && findAccessibilityNodeInfosByViewId4.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId4.get(0)) != null && MyApplication.enforceable) {
                                        PerformClickUtils.scrollTop(accessibilityNodeInfo);
                                        ObtainTagUtils.this.sleep(100);
                                        i2--;
                                    }
                                }
                            }
                        }
                        LogUtils.log("WS_BABY_ContactLabelManagerUI_1_lastname" + ObtainTagUtils.this.lastName);
                        ObtainTagUtils.this.scrollTags = null;
                        ObtainTagUtils.this.tagsNum = null;
                        ObtainTagUtils.this.scrollTag();
                    } else if (i == 1) {
                        BaseServiceUtils.getMainHandler().post(new Runnable() {
                            public void run() {
                                BaseServiceUtils.updateBottomText(ObtainTagUtils.this.mMyManager, "没有找到标签哦");
                                BaseServiceUtils.removeEndView(ObtainTagUtils.this.mMyManager);
                            }
                        });
                    }
                }

                public void nuFind() {
                    BaseServiceUtils.getMainHandler().post(new Runnable() {
                        public void run() {
                            BaseServiceUtils.updateBottomText(ObtainTagUtils.this.mMyManager, "没有找到标签哦");
                            BaseServiceUtils.removeEndView(ObtainTagUtils.this.mMyManager);
                        }
                    });
                }
            });
        }
    }

    public void scrollTag() {
        try {
            if (MyApplication.enforceable) {
                if (this.scrollTags == null) {
                    this.scrollTags = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, tag_list_item_name_id);
                }
                if (this.scrollTags == null || this.scrollTags.isEmpty() || !MyApplication.enforceable) {
                    LogUtils.log("WS_BABY.ContactLabelManagerUI.tags.null");
                    removeEndView(this.mMyManager);
                    return;
                }
                LogUtils.log("WS_BABY_ContactLabelManagerUI_3");
                AccessibilityNodeInfo accessibilityNodeInfo = null;
                if (this.startIndex < this.scrollTags.size() && MyApplication.enforceable) {
                    LogUtils.log("WS_BABY_ContactLabelManagerUI_4_index_" + this.startIndex);
                    AccessibilityNodeInfo accessibilityNodeInfo2 = this.scrollTags.get(this.startIndex);
                    if (accessibilityNodeInfo2 != null) {
                        if (this.tagsNum == null) {
                            this.tagsNum = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, tag_list_item_num_id);
                        }
                        if (this.tagsNum != null && this.tagsNum.size() > this.startIndex) {
                            accessibilityNodeInfo = this.tagsNum.get(this.startIndex);
                        }
                        String str = "";
                        if (accessibilityNodeInfo2.getText() != null) {
                            str = accessibilityNodeInfo2.getText() + "";
                        }
                        if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                            this.lastNameNum = accessibilityNodeInfo.getText().toString();
                        }
                        this.tagJumpList.add(str);
                        if (!this.tagList.contains(str)) {
                            if (!"(0)".equals(this.lastNameNum)) {
                                LogUtils.log("WS_BABY_ContactLabelManagerUI_6");
                                this.tagList.add(str);
                                PerformClickUtils.performClick(this.scrollTags.get(this.startIndex));
                                this.startIndex++;
                                initContactLabelEditUI();
                                return;
                            }
                        }
                        LogUtils.log("WS_BABY_ContactLabelManagerUI_5");
                        this.startIndex++;
                        scrollTag();
                    }
                } else if (this.startIndex >= this.scrollTags.size() && MyApplication.enforceable) {
                    this.scrollTags = null;
                    this.tagsNum = null;
                    PerformClickUtils.scroll(this.autoService, tag_list_id);
                    new PerformClickUtils2().check(this.autoService, tag_list_item_name_id, 200, 10, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            AccessibilityNodeInfo accessibilityNodeInfo;
                            ObtainTagUtils.this.scrollTags = PerformClickUtils.findViewIdList((AccessibilityService) ObtainTagUtils.this.autoService, BaseServiceUtils.tag_list_item_name_id);
                            ObtainTagUtils.this.tagsNum = PerformClickUtils.findViewIdList((AccessibilityService) ObtainTagUtils.this.autoService, BaseServiceUtils.tag_list_item_num_id);
                            if (ObtainTagUtils.this.scrollTags != null && ObtainTagUtils.this.scrollTags.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = ObtainTagUtils.this.scrollTags.get(ObtainTagUtils.this.scrollTags.size() - 1)) != null && MyApplication.enforceable) {
                                String str = accessibilityNodeInfo.getText() + "";
                                int unused = ObtainTagUtils.this.startIndex = 0;
                                if (!str.equals(ObtainTagUtils.this.lastName) || !ObtainTagUtils.this.tagJumpList.contains(str)) {
                                    ObtainTagUtils.this.scrollTag();
                                    return;
                                }
                                LogUtils.log("WS_BABY_ContactLabelManagerUI_11111");
                                String str2 = (String) SPUtils.get(ObtainTagUtils.this.autoService, "wx_user", "wx_user");
                                StringBuilder sb = new StringBuilder();
                                if (ObtainTagUtils.this.tagList != null && ObtainTagUtils.this.tagList.size() > 0 && MyApplication.enforceable) {
                                    Iterator it = ObtainTagUtils.this.tagList.iterator();
                                    while (it.hasNext()) {
                                        sb.append(((String) it.next()) + ";");
                                    }
                                }
                                TagMemberBean tagMemberBean = new TagMemberBean();
                                tagMemberBean.setWxNum(ObtainTagUtils.this.wxNumber);
                                tagMemberBean.setWxName(ObtainTagUtils.this.wxName);
                                tagMemberBean.setWxTags(sb.toString());
                                SQLiteUtils.getInstance().addTagsMemberBean(tagMemberBean);
                                SPUtils.put(ObtainTagUtils.this.autoService, str2 + "_tags", sb.toString());
                                BaseServiceUtils.removeEndView(ObtainTagUtils.this.mMyManager);
                            }
                        }
                    });
                }
            }
        } catch (Exception unused) {
        }
    }

    public void initContactLabelEditUI() {
        try {
            new PerformClickUtils2().checkNodeAllIds(this.autoService, search_id, single_contact_nick_name_id, executeSpeed + executeSpeedSetting, 100, 30, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY.ContactLabelEditUI");
                    LinkedHashSet unused = ObtainTagUtils.this.tagPeopleList = new LinkedHashSet();
                    String unused2 = ObtainTagUtils.this.lastPeopleName = "";
                    int unused3 = ObtainTagUtils.this.startIndex2 = 0;
                    String unused4 = ObtainTagUtils.this.nowName = "";
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) ObtainTagUtils.this.autoService, BaseServiceUtils.search_id);
                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                        String charSequence = findViewIdList.get(0).getText().toString();
                        MyWindowManager myWindowManager = ObtainTagUtils.this.mMyManager;
                        BaseServiceUtils.updateBottomText(myWindowManager, "正在获取【 " + charSequence + " 】，标签好友 " + ObtainTagUtils.this.lastNameNum + " 人");
                        ObtainTagUtils.this.executeSaveTag(charSequence);
                    }
                }

                public void nuFind() {
                    try {
                        ObtainTagUtils.this.tagList.remove(Integer.valueOf(ObtainTagUtils.this.tagList.size() - 1));
                        boolean unused = ObtainTagUtils.this.isContactLabelManagerUI = true;
                        PerformClickUtils.performBack(ObtainTagUtils.this.autoService);
                        PrintStream printStream = System.out;
                        printStream.println("WS_BABY_TIME9=" + System.currentTimeMillis());
                        new PerformClickUtils2().checkNilString(ObtainTagUtils.this.autoService, "请稍候", ObtainTagUtils.this.waitTime, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                ObtainTagUtils.this.initContactLabelManagerUI();
                            }
                        });
                    } catch (Exception unused2) {
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            this.isContactLabelManagerUI = true;
        }
    }

    public void executeSaveTag(final String str) {
        List<AccessibilityNodeInfo> findViewIdList;
        AccessibilityNodeInfo accessibilityNodeInfo;
        if (this.tags == null) {
            this.tags = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, single_contact_nick_name_id);
            System.out.println("WS_BABY_TIME222=" + System.currentTimeMillis());
        }
        if (this.tags == null || this.tags.isEmpty() || this.tags.size() <= 0 || !MyApplication.enforceable) {
            System.out.println("WS_BABY_TIME8=" + System.currentTimeMillis());
            if (this.tagList != null && this.tagList.size() > 0 && this.tagList.contains(str)) {
                this.tagList.remove(str);
            }
            this.isContactLabelManagerUI = true;
            PerformClickUtils.performBack(this.autoService);
            System.out.println("WS_BABY_TIME9=" + System.currentTimeMillis());
            new PerformClickUtils2().checkNilString(this.autoService, "请稍候", this.waitTime, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    ObtainTagUtils.this.initContactLabelManagerUI();
                }
            });
        } else if (this.startIndex2 < this.tags.size() && MyApplication.enforceable) {
            System.out.println("WS_BABY_TIME3=" + System.currentTimeMillis());
            AccessibilityNodeInfo accessibilityNodeInfo2 = this.tags.get(this.startIndex2);
            if (accessibilityNodeInfo2 != null && MyApplication.enforceable) {
                this.nowName = accessibilityNodeInfo2.getText() + "";
                LogUtils.log("WS_BABY.ContactLabelEditUI.##" + this.startIndex2 + "(" + this.tags.size() + "),nowName" + this.nowName + ",lastPeopleName" + this.lastPeopleName);
                if (this.tagPeopleList.contains(this.nowName)) {
                    this.startIndex2++;
                } else {
                    this.tagPeopleList.add(this.nowName);
                    this.startIndex2++;
                }
                executeSaveTag(str);
            }
        } else if (this.startIndex2 >= this.tags.size() && MyApplication.enforceable && (findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, default_list_id)) != null && findViewIdList.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = findViewIdList.get(0)) != null && MyApplication.enforceable) {
            this.tags = null;
            PerformClickUtils.scroll(accessibilityNodeInfo);
            new PerformClickUtils2().check(this.autoService, single_contact_nick_name_id, 10, 5, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    AccessibilityNodeInfo accessibilityNodeInfo;
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) ObtainTagUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = findViewIdList.get(findViewIdList.size() - 1)) != null && MyApplication.enforceable) {
                        ObtainTagUtils obtainTagUtils = ObtainTagUtils.this;
                        String unused = obtainTagUtils.lastPeopleName = accessibilityNodeInfo.getText() + "";
                        LogUtils.log("WS_BABY.ContactLabelEditUI.@@@[" + ObtainTagUtils.this.lastPeopleName);
                        int unused2 = ObtainTagUtils.this.startIndex2 = 0;
                        if (ObtainTagUtils.this.nowName.equals(ObtainTagUtils.this.lastPeopleName)) {
                            PrintStream printStream = System.out;
                            printStream.println("WS_BABY_TIME7=" + System.currentTimeMillis());
                            ObtainTagUtils.this.endCurrentTag(str);
                            return;
                        }
                        ObtainTagUtils.this.executeSaveTag(str);
                    }
                }

                public void nuFind() {
                    PrintStream printStream = System.out;
                    printStream.println("WS_BABY_TIME8=" + System.currentTimeMillis());
                }
            });
        }
    }

    public void endCurrentTag(String str) {
        LogUtils.log("WS_BABY_ContactLabelEditUI_2");
        String str2 = (String) SPUtils.get(this.autoService, "wx_user", "wx_user");
        StringBuilder sb = new StringBuilder();
        if (this.tagPeopleList != null && this.tagPeopleList.size() > 0 && MyApplication.enforceable) {
            Iterator it = this.tagPeopleList.iterator();
            while (it.hasNext()) {
                sb.append(((String) it.next()) + ";");
            }
        }
        this.isContactLabelManagerUI = true;
        MyWindowManager myWindowManager = this.mMyManager;
        updateBottomText(myWindowManager, "【 " + str + " 】获取完成，标签好友 " + this.lastNameNum + " 人");
        AutoService autoService = this.autoService;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str2);
        sb2.append("_");
        sb2.append(str);
        SPUtils.put(autoService, sb2.toString(), sb.toString());
        TagPeoplesBean tagPeoplesBean = new TagPeoplesBean();
        tagPeoplesBean.setWxNum(this.wxNumber);
        tagPeoplesBean.setWxName(this.wxName);
        tagPeoplesBean.setWxTagName(str);
        tagPeoplesBean.setWxPeoples(sb.toString());
        SQLiteUtils.getInstance().addTagPeoplesBean(tagPeoplesBean);
        PerformClickUtils.performBack(this.autoService);
        LogUtils.log("WS_BABY_NAMES_" + sb.toString());
        LogUtils.log("WS_BABY_NAMES_NUM=" + this.lastNameNum);
        new PerformClickUtils2().checkNilString(this.autoService, "请稍候", this.waitTime, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                ObtainTagUtils.this.initContactLabelManagerUI();
            }
        });
    }

    public void middleViewShow() {
        try {
            MyWindowManager myWindowManager = this.mMyManager;
            setMiddleText(myWindowManager, "好友标签获取完成", "共获取" + this.tagList.size() + "个好友标签");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewShow() {
        try {
            showBottomView(this.mMyManager, "正在获取好友标签，请不要操作微信\n并默认会过滤掉，好友人数为 0 的标签");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        ObtainTagUtils.this.executeTagMain();
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
