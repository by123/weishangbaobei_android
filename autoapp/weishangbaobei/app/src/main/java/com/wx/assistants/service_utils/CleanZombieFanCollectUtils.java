package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class CleanZombieFanCollectUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static CleanZombieFanCollectUtils instance;
    private List<String> beforeLists = new ArrayList();
    /* access modifiers changed from: private */
    public int beforeReSendNum = 0;
    /* access modifiers changed from: private */
    public int continuityNilFriendCount = 0;
    private int excDelete = 0;
    /* access modifiers changed from: private */
    public int excFriendsNum = 0;
    /* access modifiers changed from: private */
    public boolean isCanDelete;
    private boolean isFirst = true;
    /* access modifiers changed from: private */
    public boolean isFirstFav = true;
    /* access modifiers changed from: private */
    public boolean isJumpedStart = true;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    private boolean isSearchResult = true;
    List<AccessibilityNodeInfo> itemList = null;
    List<AccessibilityNodeInfo> itemList0 = null;
    private List<String> jumpStartLists = new ArrayList();
    /* access modifiers changed from: private */
    public int lastReSendNum = 0;
    private int maxNum = 300;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> nameLists = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public int noFriendsNum = 0;
    /* access modifiers changed from: private */
    public String nowName = "";
    /* access modifiers changed from: private */
    public OperationParameterModel operationParameterModel;
    /* access modifiers changed from: private */
    public String remarkLabel = "";
    /* access modifiers changed from: private */
    public int residenceTime = 0;
    /* access modifiers changed from: private */
    public String sayContent;
    private int scrollCount = 0;
    /* access modifiers changed from: private */
    public String sendResult = "";
    private int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startInt = 0;

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$208(CleanZombieFanCollectUtils cleanZombieFanCollectUtils) {
        int i = cleanZombieFanCollectUtils.excFriendsNum;
        cleanZombieFanCollectUtils.excFriendsNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$408(CleanZombieFanCollectUtils cleanZombieFanCollectUtils) {
        int i = cleanZombieFanCollectUtils.excDelete;
        cleanZombieFanCollectUtils.excDelete = i + 1;
        return i;
    }

    static /* synthetic */ int access$908(CleanZombieFanCollectUtils cleanZombieFanCollectUtils) {
        int i = cleanZombieFanCollectUtils.noFriendsNum;
        cleanZombieFanCollectUtils.noFriendsNum = i + 1;
        return i;
    }

    private CleanZombieFanCollectUtils() {
    }

    public static CleanZombieFanCollectUtils getInstance() {
        instance = new CleanZombieFanCollectUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel2) {
        this.isFirstFav = true;
        this.excFriendsNum = 0;
        this.excDelete = 0;
        this.noFriendsNum = 0;
        this.beforeLists = new ArrayList();
        this.jumpStartLists = new ArrayList();
        this.startIndex = 0;
        this.residenceTime = 100;
        this.continuityNilFriendCount = 0;
        this.sendResult = "";
        this.nowName = "";
        this.isJumpedStart = true;
        this.isScrollBottom = false;
        this.isFirst = true;
        this.scrollCount = 0;
        this.spaceTime = operationParameterModel2.getSpaceTime();
        this.isSearchResult = true;
        this.operationParameterModel = operationParameterModel2;
        this.remarkLabel = operationParameterModel2.getSingLabelStr();
        this.isCanDelete = operationParameterModel2.isDeleteNoFriends();
        this.sayContent = operationParameterModel2.getSayContent();
        this.startInt = operationParameterModel2.getStartIndex();
        this.nameLists = operationParameterModel2.getTagListPeoples();
        this.maxNum = operationParameterModel2.getMaxOperaNum();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        if (operationParameterModel2.getSendCardType() == 1) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("WS_BABY.st.0");
                sb.append(this.startInt > 1);
                LogUtils.log(sb.toString());
                if (this.startInt > 1) {
                    LogUtils.log("WS_BABY.st.1");
                    for (int i = 0; i < this.startInt - 1; i++) {
                        LogUtils.log("WS_BABY.st.st" + i);
                        if (this.nameLists.size() > 0) {
                            this.nameLists.remove(this.nameLists.iterator().next());
                        }
                    }
                    return;
                }
                LogUtils.log("WS_BABY.st.2");
            } catch (Exception unused) {
                LogUtils.log("WS_BABY.st.3");
            }
        }
    }

    public void saveIndex() {
        int i;
        if (this.startInt == 1) {
            i = (this.excFriendsNum + 1) - this.excDelete;
        } else {
            i = (this.excFriendsNum + this.startInt) - this.excDelete;
        }
        if (this.operationParameterModel.getSendCardType() == 0) {
            SPUtils.put(MyApplication.getConText(), "check_zombies_friend_num_all", Integer.valueOf(i));
            Context conText = MyApplication.getConText();
            SPUtils.put(conText, "check_zombies_friend_num_all_text", "上次共执行 " + this.excFriendsNum + " 人 ,非好友 " + this.noFriendsNum + " 人");
        } else if (this.operationParameterModel.getSendCardType() == 1) {
            SPUtils.put(MyApplication.getConText(), "check_zombies_friend_num_part", Integer.valueOf(i));
            Context conText2 = MyApplication.getConText();
            SPUtils.put(conText2, "check_zombies_friend_num_part_text", "上次共执行 " + this.excFriendsNum + " 人 ,非好友 " + this.noFriendsNum + " 人");
        } else if (this.operationParameterModel.getSendCardType() == 2) {
            SPUtils.put(MyApplication.getConText(), "check_zombies_friend_num_shield", Integer.valueOf(i));
            Context conText3 = MyApplication.getConText();
            SPUtils.put(conText3, "check_zombies_num_shield_text", "上次共执行 " + this.excFriendsNum + " 人 ,非好友 " + this.noFriendsNum + " 人");
        }
    }

    public void initExMore() {
        LogUtils.log("WS_BABY_ContactInfoUI.initExMore.1");
        new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeedSetting + executeSpeed + 350, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                LogUtils.log("WS_BABY_ContactInfoUI.initExMore.2");
                CleanZombieFanCollectUtils.this.sleep(CleanZombieFanCollectUtils.this.residenceTime);
                if (CleanZombieFanCollectUtils.this.isCanDelete && CleanZombieFanCollectUtils.this.excFriendsNum <= 1000 && MyApplication.enforceable) {
                    CleanZombieFanCollectUtils.this.saveFriendInfo(CleanZombieFanCollectUtils.this.nowName);
                }
                PerformClickUtils.findViewIdAndClick(CleanZombieFanCollectUtils.this.autoService, BaseServiceUtils.right_more_id);
                new PerformClickUtils2().checkString(CleanZombieFanCollectUtils.this.autoService, "黑名单", BaseServiceUtils.executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        if (!CleanZombieFanCollectUtils.this.isCanDelete || CleanZombieFanCollectUtils.this.excFriendsNum > 1000 || !MyApplication.enforceable) {
                            LogUtils.log("WS_BABY_ContactInfoUI.10.备注");
                            new PerformClickUtils2().checkNodeStringsHasSomeOne(CleanZombieFanCollectUtils.this.autoService, "设置备注和标签", "设置备注及标签", (BaseServiceUtils.executeSpeedSetting / 8) + BaseServiceUtils.executeSpeed, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY_ContactInfoUI.10.备注00");
                                    if (i == 0) {
                                        List<AccessibilityNodeInfo> findViewStringList = PerformClickUtils.findViewStringList(CleanZombieFanCollectUtils.this.autoService, "设置备注和标签");
                                        if (findViewStringList != null && findViewStringList.size() > 0 && MyApplication.enforceable) {
                                            LogUtils.log("WS_BABY_ContactInfoUI.11.设置备注和标签");
                                            PerformClickUtils.performClick(findViewStringList.get(0));
                                            CleanZombieFanCollectUtils.this.initContactRemarkInfoModUI();
                                            return;
                                        }
                                        return;
                                    }
                                    List<AccessibilityNodeInfo> findViewStringList2 = PerformClickUtils.findViewStringList(CleanZombieFanCollectUtils.this.autoService, "设置备注及标签");
                                    if (findViewStringList2 != null && findViewStringList2.size() > 0 && MyApplication.enforceable) {
                                        LogUtils.log("WS_BABY_ContactInfoUI.12.设置备注及标签");
                                        PerformClickUtils.performClick(findViewStringList2.get(0));
                                        CleanZombieFanCollectUtils.this.initContactRemarkInfoModUI();
                                    }
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY_ContactInfoUI.10.备注11");
                                }
                            });
                            return;
                        }
                        LogUtils.log("WS_BABY_ContactInfoUI.6.允许删除");
                        if (!PerformClickUtils.findNodeIsExistByText(CleanZombieFanCollectUtils.this.autoService, "删除") || !MyApplication.enforceable) {
                            PerformClickUtils.scroll(CleanZombieFanCollectUtils.this.autoService, BaseServiceUtils.more_recycle_view_id);
                            CleanZombieFanCollectUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                            PerformClickUtils.findTextAndClick(CleanZombieFanCollectUtils.this.autoService, "删除");
                        } else {
                            PerformClickUtils.findTextAndClick(CleanZombieFanCollectUtils.this.autoService, "删除");
                        }
                        new PerformClickUtils2().check(CleanZombieFanCollectUtils.this.autoService, BaseServiceUtils.dialog_ok_id, 100, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                LogUtils.log("WS_BABY_ContactInfoUI.9.确认删除");
                                PerformClickUtils.findViewIdAndClick(CleanZombieFanCollectUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                CleanZombieFanCollectUtils.access$408(CleanZombieFanCollectUtils.this);
                                if (CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 2) {
                                    CleanZombieFanCollectUtils.this.executeMain(CleanZombieFanCollectUtils.this.nameLists);
                                } else {
                                    CleanZombieFanCollectUtils.this.executePartMain();
                                }
                            }

                            public void nuFind() {
                                PerformClickUtils.executedBackHome(CleanZombieFanCollectUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        CleanZombieFanCollectUtils.access$408(CleanZombieFanCollectUtils.this);
                                        if (CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 2) {
                                            CleanZombieFanCollectUtils.this.executeMain(CleanZombieFanCollectUtils.this.nameLists);
                                        } else {
                                            CleanZombieFanCollectUtils.this.executePartMain();
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    public void initFirstContactInfoMSGUI() {
        new PerformClickUtils2().checkNodeIdOrStringAll(this.autoService, msg_layout, "发消息", executeSpeed + 350 + executeSpeedSetting, 100, 30, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findViewIdAndClick2(CleanZombieFanCollectUtils.this.autoService, BaseServiceUtils.msg_layout);
                PerformClickUtils.findTextAndClick(CleanZombieFanCollectUtils.this.autoService, "发消息");
                LogUtils.log("WS_BABY_ContactInfoUI.22.发消息");
                new PerformClickUtils2().checkNodeId(CleanZombieFanCollectUtils.this.autoService, BaseServiceUtils.voice_left_id, 10, 100, 15, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        CleanZombieFanCollectUtils.this.initChattingFirstUI(0);
                    }

                    public void nuFind() {
                        PerformClickUtils.findViewIdAndClick2(CleanZombieFanCollectUtils.this.autoService, BaseServiceUtils.msg_layout);
                        PerformClickUtils.findTextAndClick(CleanZombieFanCollectUtils.this.autoService, "发消息");
                        new PerformClickUtils2().checkNodeId(CleanZombieFanCollectUtils.this.autoService, BaseServiceUtils.voice_left_id, 10, 100, 10, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                CleanZombieFanCollectUtils.this.initChattingFirstUI(0);
                            }

                            public void nuFind() {
                                LogUtils.log("WS_BABY_ContactInfoUI.12.Back");
                                PerformClickUtils.executedBackHome(CleanZombieFanCollectUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        if (CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 2) {
                                            CleanZombieFanCollectUtils.this.executeMain(CleanZombieFanCollectUtils.this.nameLists);
                                        } else {
                                            CleanZombieFanCollectUtils.this.executePartMain();
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            }

            public void nuFind() {
                LogUtils.log("WS_BABY_ContactInfoUI.12.Back");
                PerformClickUtils.executedBackHome(CleanZombieFanCollectUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        if (CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 2) {
                            CleanZombieFanCollectUtils.this.executeMain(CleanZombieFanCollectUtils.this.nameLists);
                        } else {
                            CleanZombieFanCollectUtils.this.executePartMain();
                        }
                    }
                });
            }
        });
    }

    public void initContactRemarkInfoModUI() {
        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.0");
        try {
            new PerformClickUtils2().check(this.autoService, remark_edit_id, executeSpeedSetting + executeSpeed + 350, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.1");
                    AccessibilityNodeInfo rootInActiveWindow = CleanZombieFanCollectUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.remark_edit_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                        findAccessibilityNodeInfosByViewId.get(0).performAction(16);
                        CleanZombieFanCollectUtils.this.sleep(CleanZombieFanCollectUtils.this.residenceTime);
                        boolean z = !CleanZombieFanCollectUtils.this.nowName.startsWith("A000非好友_");
                        if (z) {
                            CleanZombieFanCollectUtils.this.inputText("A000非好友_");
                        }
                        CleanZombieFanCollectUtils.this.sleep(CleanZombieFanCollectUtils.this.residenceTime);
                        if (CleanZombieFanCollectUtils.this.remarkLabel == null || "".equals(CleanZombieFanCollectUtils.this.remarkLabel)) {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.complete_id);
                            if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                            }
                            PerformClickUtils.executedBackHome(CleanZombieFanCollectUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    if (CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 2) {
                                        CleanZombieFanCollectUtils.this.executeMain(CleanZombieFanCollectUtils.this.nameLists);
                                    } else {
                                        CleanZombieFanCollectUtils.this.executePartMain();
                                    }
                                }
                            });
                        } else if (!PerformClickUtils.findNodeIsExistByText(CleanZombieFanCollectUtils.this.autoService, CleanZombieFanCollectUtils.this.remarkLabel)) {
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.2");
                            new PerformClickUtils2().checkNodeIds(CleanZombieFanCollectUtils.this.autoService, BaseServiceUtils.input_remark_label, BaseServiceUtils.input_remark_label_view_group, 100, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    if (i == 0) {
                                        PerformClickUtils.findViewIdAndClick(CleanZombieFanCollectUtils.this.autoService, BaseServiceUtils.input_remark_label);
                                    } else if (i == 1) {
                                        PerformClickUtils.findViewIdAndClick(CleanZombieFanCollectUtils.this.autoService, BaseServiceUtils.input_remark_label_view_group);
                                    }
                                    new PerformClickUtils2().checkString(CleanZombieFanCollectUtils.this.autoService, CleanZombieFanCollectUtils.this.remarkLabel, BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            PerformClickUtils.findTextAndClick(CleanZombieFanCollectUtils.this.autoService, CleanZombieFanCollectUtils.this.remarkLabel);
                                            CleanZombieFanCollectUtils.this.sleep(100);
                                            PerformClickUtils.findTextAndClick(CleanZombieFanCollectUtils.this.autoService, "保存");
                                            new PerformClickUtils2().checkString(CleanZombieFanCollectUtils.this.autoService, "完成", BaseServiceUtils.executeSpeed + 350 + BaseServiceUtils.executeSpeedSetting, 200, 100, new PerformClickUtils2.OnCheckListener() {
                                                public void nuFind() {
                                                }

                                                public void find(int i) {
                                                    PerformClickUtils.findTextAndClick(CleanZombieFanCollectUtils.this.autoService, "完成");
                                                    PerformClickUtils.executedBackHome(CleanZombieFanCollectUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                                        public void find() {
                                                            if (CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 2) {
                                                                CleanZombieFanCollectUtils.this.executeMain(CleanZombieFanCollectUtils.this.nameLists);
                                                            } else {
                                                                CleanZombieFanCollectUtils.this.executePartMain();
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        } else {
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.3");
                            if (z) {
                                LogUtils.log("WS_BABY_ContactRemarkInfoModUI.4");
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.complete_id);
                                if (findAccessibilityNodeInfosByViewId3 != null && findAccessibilityNodeInfosByViewId3.size() > 0 && MyApplication.enforceable) {
                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId3.get(0));
                                }
                                PerformClickUtils.executedBackHome(CleanZombieFanCollectUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        if (CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 2) {
                                            CleanZombieFanCollectUtils.this.executeMain(CleanZombieFanCollectUtils.this.nameLists);
                                        } else {
                                            CleanZombieFanCollectUtils.this.executePartMain();
                                        }
                                    }
                                });
                                return;
                            }
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.5");
                            PerformClickUtils.executedBackHome(CleanZombieFanCollectUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    if (CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 2) {
                                        CleanZombieFanCollectUtils.this.executeMain(CleanZombieFanCollectUtils.this.nameLists);
                                    } else {
                                        CleanZombieFanCollectUtils.this.executePartMain();
                                    }
                                }
                            });
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initFirstSingleChatInfoUI() {
        LogUtils.log("WS_BABY_SingleChatInfoUI.2");
        new PerformClickUtils2().check(this.autoService, single_contact_nick_name_id, executeSpeedSetting + executeSpeed + 350, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                PerformClickUtils.findViewIdAndClick(CleanZombieFanCollectUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                LogUtils.log("WS_BABY_SingleChatInfoUI.3");
                CleanZombieFanCollectUtils.this.initExMore();
            }
        });
    }

    public void checkZombieFan(final LinkedHashSet<String> linkedHashSet) {
        if (this.excFriendsNum >= this.maxNum) {
            removeEndView(this.mMyManager);
            return;
        }
        this.isJumpedStart = false;
        if (this.itemList == null) {
            this.itemList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_item_name_id);
        }
        if (this.itemList != null && this.itemList.size() > 0 && MyApplication.enforceable) {
            LogUtils.log("WS_BABY_LauncherUI.startIndex" + this.startIndex + "@" + this.itemList.size());
            if (this.startIndex < this.itemList.size() && MyApplication.enforceable) {
                final AccessibilityNodeInfo accessibilityNodeInfo = this.itemList.get(this.startIndex);
                if (accessibilityNodeInfo != null) {
                    if (accessibilityNodeInfo.getText() != null) {
                        this.nowName = accessibilityNodeInfo.getText() + "";
                    }
                    if (this.beforeLists != null && this.beforeLists.size() > 30) {
                        for (int i = 0; i < 5; i++) {
                            this.beforeLists.remove(0);
                        }
                    }
                    if (linkedHashSet != null && linkedHashSet.size() > 0 && linkedHashSet.contains(this.nowName)) {
                        this.startIndex++;
                        this.excFriendsNum++;
                        updateBottomText(this.mMyManager, "已跳过【 " + this.nowName + " 】");
                        checkZombieFan(linkedHashSet);
                    } else if (this.isScrollBottom && this.beforeLists.contains(this.nowName)) {
                        this.startIndex++;
                        LogUtils.log("WS_BABY_LauncherUI.beforeLists.contains." + this.nowName);
                        checkZombieFan(linkedHashSet);
                    } else if ("微信团队".equals(this.nowName) || "文件传输助手".equals(this.nowName)) {
                        this.excFriendsNum++;
                        this.startIndex++;
                        LogUtils.log("WS_BABY_LauncherUI.微信团队.");
                        checkZombieFan(linkedHashSet);
                    } else {
                        LogUtils.log("WS_BABY_LauncherUI.check");
                        this.beforeLists.add(this.nowName);
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_item_layout_id);
                        if (findViewIdList == null || this.startIndex >= findViewIdList.size()) {
                            this.excFriendsNum++;
                            this.startIndex++;
                            LogUtils.log("WS_BABY_LauncherUI.微信团队.");
                            checkZombieFan(linkedHashSet);
                            return;
                        }
                        final AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList.get(this.startIndex);
                        this.startIndex++;
                        if (this.spaceTime <= 0 || this.excFriendsNum <= 0) {
                            PerformClickUtils.performClick(accessibilityNodeInfo2);
                            PerformClickUtils.performClick(accessibilityNodeInfo);
                            initFirstContactInfoMSGUI();
                            return;
                        }
                        new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                            public void surplus(int i) {
                                MyWindowManager myWindowManager = CleanZombieFanCollectUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "从第 " + CleanZombieFanCollectUtils.this.startInt + " 个好友，开始检测僵尸粉\n已执行 " + CleanZombieFanCollectUtils.this.excFriendsNum + " 人\n正在延时等待，倒计时 " + i + " 秒");
                            }

                            public void end() {
                                MyWindowManager myWindowManager = CleanZombieFanCollectUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "从第 " + CleanZombieFanCollectUtils.this.startInt + " 个好友，开始检测僵尸粉\n已执行 " + CleanZombieFanCollectUtils.this.excFriendsNum + " 人 ,非好友 " + CleanZombieFanCollectUtils.this.noFriendsNum + " 人");
                                PerformClickUtils.performClick(accessibilityNodeInfo2);
                                PerformClickUtils.performClick(accessibilityNodeInfo);
                                CleanZombieFanCollectUtils.this.initFirstContactInfoMSGUI();
                            }
                        });
                    }
                }
            } else if (this.startIndex >= this.itemList.size() && MyApplication.enforceable) {
                this.itemList = null;
                PerformClickUtils.scroll(this.autoService, grout_friend_list_id);
                new PerformClickUtils2().check(this.autoService, list_item_name_id, 350, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        int unused = CleanZombieFanCollectUtils.this.startIndex = 1;
                        if (CleanZombieFanCollectUtils.this.isScrollBottom) {
                            LogUtils.log("WS_BABY_LauncherUI.END");
                            BaseServiceUtils.removeEndView(CleanZombieFanCollectUtils.this.mMyManager);
                            return;
                        }
                        boolean unused2 = CleanZombieFanCollectUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(CleanZombieFanCollectUtils.this.autoService, "位联系人");
                        CleanZombieFanCollectUtils.this.checkZombieFan(linkedHashSet);
                    }
                });
            }
        }
    }

    public void executeMain(final LinkedHashSet<String> linkedHashSet) {
        try {
            this.isSearchResult = true;
            LogUtils.log("WS_BABY_LauncherUI");
            if (this.isFirst && MyApplication.enforceable) {
                this.isFirst = false;
                this.isFirst = false;
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                sleep((executeSpeedSetting / 8) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
            }
            new PerformClickUtils2().checkNodeIds(this.autoService, grout_friend_list_id, list_item_name_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    if (CleanZombieFanCollectUtils.this.startInt <= 1 || !CleanZombieFanCollectUtils.this.isJumpedStart || !MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_LauncherUI_从头开始");
                        CleanZombieFanCollectUtils.this.checkZombieFan(linkedHashSet);
                        return;
                    }
                    LogUtils.log("WS_BABY_LauncherUI_从" + CleanZombieFanCollectUtils.this.startInt + "开始");
                    CleanZombieFanCollectUtils.this.initCheck();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initCheck() {
        LogUtils.log("WS_BABY_LauncherUI.start...");
        AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
        if (rootInActiveWindow != null && MyApplication.enforceable) {
            if (this.itemList0 == null) {
                this.itemList0 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(list_item_name_id);
            }
            if (this.itemList0 != null && this.itemList0.size() > 0 && MyApplication.enforceable) {
                if (this.startIndex < this.itemList0.size() && MyApplication.enforceable) {
                    AccessibilityNodeInfo accessibilityNodeInfo = this.itemList0.get(this.startIndex);
                    if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                        this.nowName = accessibilityNodeInfo.getText() + "";
                    }
                    if (!this.isScrollBottom || !this.jumpStartLists.contains(this.nowName)) {
                        this.scrollCount++;
                    }
                    LogUtils.log("WS_BABY_JUMP_NUM@" + this.scrollCount + "@" + this.startInt);
                    if (this.scrollCount == this.startInt) {
                        checkZombieFan(this.nameLists);
                        return;
                    }
                    this.jumpStartLists.add(this.nowName);
                    this.startIndex++;
                    updateBottomText(this.mMyManager, "从第 " + this.startInt + " 个好友，开始检测僵尸粉\n已跳过 " + this.scrollCount + " 个好友，请不要操作微信");
                    initCheck();
                } else if (this.startIndex >= this.itemList0.size() && MyApplication.enforceable) {
                    this.itemList0 = null;
                    PerformClickUtils.scroll(this.autoService, grout_friend_list_id);
                    new PerformClickUtils2().check(this.autoService, list_item_name_id, 100, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            int unused = CleanZombieFanCollectUtils.this.startIndex = 1;
                            if (CleanZombieFanCollectUtils.this.isScrollBottom) {
                                LogUtils.log("WS_BABY_LauncherUI.END");
                                BaseServiceUtils.removeEndView(CleanZombieFanCollectUtils.this.mMyManager);
                                return;
                            }
                            boolean unused2 = CleanZombieFanCollectUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(CleanZombieFanCollectUtils.this.autoService, "位联系人");
                            CleanZombieFanCollectUtils.this.initCheck();
                        }
                    });
                }
            }
        }
    }

    public void initFavSelectUI() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, collection_list_id, this.isFirstFav ? SocializeConstants.CANCLE_RESULTCODE : executeSpeed + (executeSpeedSetting / 2), 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                /* JADX WARNING: Removed duplicated region for block: B:34:0x009e  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void find(int r8) {
                    /*
                        r7 = this;
                        com.wx.assistants.service_utils.CleanZombieFanCollectUtils r8 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this
                        com.wx.assistants.service.AutoService r8 = r8.autoService
                        android.view.accessibility.AccessibilityNodeInfo r8 = r8.getRootInActiveWindow()
                        if (r8 == 0) goto L_0x00ea
                        java.lang.String r0 = com.wx.assistants.service_utils.BaseServiceUtils.collection_list_id
                        java.util.List r8 = r8.findAccessibilityNodeInfosByViewId(r0)
                        if (r8 == 0) goto L_0x00ea
                        int r0 = r8.size()
                        if (r0 <= 0) goto L_0x00ea
                        boolean r0 = com.wx.assistants.application.MyApplication.enforceable
                        if (r0 == 0) goto L_0x00ea
                        java.lang.String r0 = "WS_BABY_FavSelectUI.into3"
                        com.wx.assistants.utils.LogUtils.log(r0)
                        r0 = 0
                        java.lang.Object r1 = r8.get(r0)
                        r2 = 1
                        if (r1 == 0) goto L_0x009b
                        java.lang.String r1 = "android.widget.ListView"
                        java.lang.Object r3 = r8.get(r0)
                        android.view.accessibility.AccessibilityNodeInfo r3 = (android.view.accessibility.AccessibilityNodeInfo) r3
                        java.lang.CharSequence r3 = r3.getClassName()
                        boolean r1 = r1.equals(r3)
                        if (r1 == 0) goto L_0x009b
                        java.lang.String r1 = "WS_BABY_FavSelectUI.into4"
                        com.wx.assistants.utils.LogUtils.log(r1)
                        java.lang.Object r1 = r8.get(r0)
                        android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1
                        int r1 = r1.getChildCount()
                        if (r1 <= 0) goto L_0x009b
                        boolean r1 = com.wx.assistants.application.MyApplication.enforceable
                        if (r1 == 0) goto L_0x009b
                        java.lang.String r1 = "WS_BABY_FavSelectUI.into5"
                        com.wx.assistants.utils.LogUtils.log(r1)
                        r1 = 0
                    L_0x0056:
                        java.lang.Object r3 = r8.get(r0)
                        android.view.accessibility.AccessibilityNodeInfo r3 = (android.view.accessibility.AccessibilityNodeInfo) r3
                        int r3 = r3.getChildCount()
                        if (r1 >= r3) goto L_0x009b
                        boolean r3 = com.wx.assistants.application.MyApplication.enforceable
                        if (r3 != 0) goto L_0x0067
                        return
                    L_0x0067:
                        java.lang.Object r3 = r8.get(r0)
                        android.view.accessibility.AccessibilityNodeInfo r3 = (android.view.accessibility.AccessibilityNodeInfo) r3
                        android.view.accessibility.AccessibilityNodeInfo r3 = r3.getChild(r1)
                        if (r3 == 0) goto L_0x0098
                        java.lang.CharSequence r4 = r3.getClassName()
                        if (r4 == 0) goto L_0x0098
                        java.lang.String r4 = "android.widget.FrameLayout"
                        java.lang.CharSequence r5 = r3.getClassName()
                        boolean r4 = r4.equals(r5)
                        if (r4 == 0) goto L_0x0098
                        boolean r4 = com.wx.assistants.application.MyApplication.enforceable
                        if (r4 == 0) goto L_0x0098
                        java.lang.String r1 = "WS_BABY_FavSelectUI.into6"
                        com.wx.assistants.utils.LogUtils.log(r1)
                        java.lang.String r1 = "WS_BABY_FavSelectUI.into7"
                        com.wx.assistants.utils.LogUtils.log(r1)
                        com.wx.assistants.utils.PerformClickUtils.performClick(r3)
                        r1 = 1
                        goto L_0x009c
                    L_0x0098:
                        int r1 = r1 + 1
                        goto L_0x0056
                    L_0x009b:
                        r1 = 0
                    L_0x009c:
                        if (r1 != 0) goto L_0x00ce
                        java.lang.String r1 = "WS_BABY_FavSelectUI.into77777"
                        com.wx.assistants.utils.LogUtils.log(r1)
                        com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this
                        com.wx.assistants.service.AutoService r1 = r1.autoService
                        java.lang.String r3 = com.wx.assistants.service_utils.BaseServiceUtils.collection_list_id
                        java.util.List r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r3)
                        if (r1 == 0) goto L_0x00ce
                        int r3 = r1.size()
                        if (r3 <= 0) goto L_0x00ce
                        java.lang.Object r1 = r1.get(r0)
                        android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1
                        int r1 = r1.getChildCount()
                        if (r1 <= r2) goto L_0x00ce
                        java.lang.Object r8 = r8.get(r0)
                        android.view.accessibility.AccessibilityNodeInfo r8 = (android.view.accessibility.AccessibilityNodeInfo) r8
                        android.view.accessibility.AccessibilityNodeInfo r8 = r8.getChild(r2)
                        com.wx.assistants.utils.PerformClickUtils.performClick(r8)
                    L_0x00ce:
                        com.wx.assistants.utils.PerformClickUtils2 r0 = new com.wx.assistants.utils.PerformClickUtils2
                        r0.<init>()
                        com.wx.assistants.service_utils.CleanZombieFanCollectUtils r8 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this
                        com.wx.assistants.service.AutoService r1 = r8.autoService
                        java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id
                        int r8 = com.wx.assistants.service_utils.BaseServiceUtils.executeSpeed
                        int r3 = com.wx.assistants.service_utils.BaseServiceUtils.executeSpeedSetting
                        int r3 = r3 + r8
                        r4 = 100
                        r5 = 600(0x258, float:8.41E-43)
                        com.wx.assistants.service_utils.CleanZombieFanCollectUtils$9$1 r6 = new com.wx.assistants.service_utils.CleanZombieFanCollectUtils$9$1
                        r6.<init>()
                        r0.checkNodeId(r1, r2, r3, r4, r5, r6)
                    L_0x00ea:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass9.find(int):void");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initChattingBackUI() {
        new PerformClickUtils2().check(this.autoService, voice_left_id, executeSpeed + (executeSpeedSetting / 10), 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                CleanZombieFanCollectUtils.access$208(CleanZombieFanCollectUtils.this);
                CleanZombieFanCollectUtils.this.saveIndex();
                new PerformClickUtils2().checkNodeIdNilExist(CleanZombieFanCollectUtils.this.autoService, BaseServiceUtils.chatting_progress_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 200, 200, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY_ChattingUI.2.发送1");
                        PerformClickUtils.executedBackHome(CleanZombieFanCollectUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                if (CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() == 2) {
                                    CleanZombieFanCollectUtils.this.executeMain(CleanZombieFanCollectUtils.this.nameLists);
                                } else {
                                    CleanZombieFanCollectUtils.this.executePartMain();
                                }
                            }
                        });
                    }

                    /* JADX WARNING: Can't wrap try/catch for region: R(3:63|64|(1:70)) */
                    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
                        r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r4.this$1.this$0.autoService, com.wx.assistants.service_utils.BaseServiceUtils.right_more_id);
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0208, code lost:
                        com.wx.assistants.utils.LogUtils.log("WS_BABY_ChattingUI.7.右上更多");
                        com.wx.assistants.utils.PerformClickUtils.performClick(r1.get(r1.size() - 1));
                        r4.this$1.this$0.initFirstSingleChatInfoUI();
                     */
                    /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x01f0 */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void nuFind() {
                        /*
                            r4 = this;
                            java.lang.String r0 = "WS_BABY_ChattingUI.2.发送2"
                            com.wx.assistants.utils.LogUtils.log(r0)
                            r0 = 30
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r2 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r2 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service.AutoService r2 = r2.autoService     // Catch:{ Exception -> 0x024c }
                            java.lang.String r3 = "重发"
                            int r2 = com.wx.assistants.utils.PerformClickUtils.findViewStringListSize(r2, r3)     // Catch:{ Exception -> 0x024c }
                            int unused = r1.lastReSendNum = r2     // Catch:{ Exception -> 0x024c }
                            java.io.PrintStream r1 = java.lang.System.out     // Catch:{ Exception -> 0x024c }
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x024c }
                            r2.<init>()     // Catch:{ Exception -> 0x024c }
                            java.lang.String r3 = "WS_BABY_beforeReSendNum"
                            r2.append(r3)     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r3 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r3 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x024c }
                            int r3 = r3.beforeReSendNum     // Catch:{ Exception -> 0x024c }
                            r2.append(r3)     // Catch:{ Exception -> 0x024c }
                            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x024c }
                            r1.println(r2)     // Catch:{ Exception -> 0x024c }
                            java.io.PrintStream r1 = java.lang.System.out     // Catch:{ Exception -> 0x024c }
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x024c }
                            r2.<init>()     // Catch:{ Exception -> 0x024c }
                            java.lang.String r3 = "WS_BABY_lastReSendNum"
                            r2.append(r3)     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r3 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r3 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x024c }
                            int r3 = r3.lastReSendNum     // Catch:{ Exception -> 0x024c }
                            r2.append(r3)     // Catch:{ Exception -> 0x024c }
                            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x024c }
                            r1.println(r2)     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x024c }
                            int r1 = r1.lastReSendNum     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r2 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r2 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x024c }
                            int r2 = r2.beforeReSendNum     // Catch:{ Exception -> 0x024c }
                            r3 = 0
                            if (r1 <= r2) goto L_0x0224
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r2 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r2 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x024c }
                            int r2 = r2.continuityNilFriendCount     // Catch:{ Exception -> 0x024c }
                            int r2 = r2 + 1
                            int unused = r1.continuityNilFriendCount = r2     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils.access$908(r1)     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x024c }
                            int r1 = r1.excFriendsNum     // Catch:{ Exception -> 0x024c }
                            r2 = 300(0x12c, float:4.2E-43)
                            if (r1 < r2) goto L_0x00ac
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x024c }
                            int r1 = r1.continuityNilFriendCount     // Catch:{ Exception -> 0x024c }
                            r2 = 3
                            if (r1 < r2) goto L_0x00ac
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x024c }
                            java.lang.String r2 = "由于您连续检测了300个以上的好友，由于微信限制规则，检测到了连续三条非好友，为了防止检测异常（检测错误），希望您隔3小时后，继续检测。"
                            java.lang.String unused = r1.sendResult = r2     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service.MyWindowManager r1 = r1.mMyManager     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.BaseServiceUtils.removeEndView(r1)     // Catch:{ Exception -> 0x024c }
                            goto L_0x023e
                        L_0x00ac:
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x01f0 }
                            java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.contact_title_id     // Catch:{ Exception -> 0x01f0 }
                            java.util.List r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x01f0 }
                            if (r1 == 0) goto L_0x01bc
                            int r2 = r1.size()     // Catch:{ Exception -> 0x01f0 }
                            if (r2 <= 0) goto L_0x01bc
                            boolean r2 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x01f0 }
                            if (r2 == 0) goto L_0x01bc
                            java.lang.Object r1 = r1.get(r3)     // Catch:{ Exception -> 0x01f0 }
                            android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1     // Catch:{ Exception -> 0x01f0 }
                            java.lang.String r2 = ""
                            java.lang.CharSequence r1 = r1.getText()     // Catch:{ Exception -> 0x00d5 }
                            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00d5 }
                            goto L_0x00d6
                        L_0x00d5:
                            r1 = r2
                        L_0x00d6:
                            if (r1 == 0) goto L_0x0187
                            java.lang.String r2 = "A000非好友_"
                            boolean r1 = r1.startsWith(r2)     // Catch:{ Exception -> 0x01f0 }
                            if (r1 == 0) goto L_0x0187
                            boolean r1 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x01f0 }
                            if (r1 == 0) goto L_0x0187
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x01f0 }
                            boolean r1 = r1.isCanDelete     // Catch:{ Exception -> 0x01f0 }
                            if (r1 != 0) goto L_0x0152
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x01f0 }
                            java.lang.String r1 = r1.remarkLabel     // Catch:{ Exception -> 0x01f0 }
                            if (r1 == 0) goto L_0x013d
                            java.lang.String r1 = ""
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r2 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r2 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x01f0 }
                            java.lang.String r2 = r2.remarkLabel     // Catch:{ Exception -> 0x01f0 }
                            boolean r1 = r1.equals(r2)     // Catch:{ Exception -> 0x01f0 }
                            if (r1 != 0) goto L_0x013d
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x01f0 }
                            java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.right_more_id     // Catch:{ Exception -> 0x01f0 }
                            java.util.List r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x01f0 }
                            if (r1 == 0) goto L_0x023e
                            int r2 = r1.size()     // Catch:{ Exception -> 0x01f0 }
                            if (r2 <= 0) goto L_0x023e
                            boolean r2 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x01f0 }
                            if (r2 == 0) goto L_0x023e
                            java.lang.String r2 = "WS_BABY_ChattingUI.5.右上更多"
                            com.wx.assistants.utils.LogUtils.log(r2)     // Catch:{ Exception -> 0x01f0 }
                            int r2 = r1.size()     // Catch:{ Exception -> 0x01f0 }
                            int r2 = r2 + -1
                            java.lang.Object r1 = r1.get(r2)     // Catch:{ Exception -> 0x01f0 }
                            android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.utils.PerformClickUtils.performClick(r1)     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x01f0 }
                            r1.initFirstSingleChatInfoUI()     // Catch:{ Exception -> 0x01f0 }
                            goto L_0x023e
                        L_0x013d:
                            java.lang.String r1 = "WS_BABY_ChattingUI.3.不可删除"
                            com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10$1$2 r2 = new com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10$1$2     // Catch:{ Exception -> 0x01f0 }
                            r2.<init>()     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.utils.PerformClickUtils.executedBackHome(r1, r0, r2)     // Catch:{ Exception -> 0x01f0 }
                            goto L_0x023e
                        L_0x0152:
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x01f0 }
                            java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.right_more_id     // Catch:{ Exception -> 0x01f0 }
                            java.util.List r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x01f0 }
                            if (r1 == 0) goto L_0x023e
                            int r2 = r1.size()     // Catch:{ Exception -> 0x01f0 }
                            if (r2 <= 0) goto L_0x023e
                            boolean r2 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x01f0 }
                            if (r2 == 0) goto L_0x023e
                            java.lang.String r2 = "WS_BABY_ChattingUI.4.右上更多"
                            com.wx.assistants.utils.LogUtils.log(r2)     // Catch:{ Exception -> 0x01f0 }
                            int r2 = r1.size()     // Catch:{ Exception -> 0x01f0 }
                            int r2 = r2 + -1
                            java.lang.Object r1 = r1.get(r2)     // Catch:{ Exception -> 0x01f0 }
                            android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.utils.PerformClickUtils.performClick(r1)     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x01f0 }
                            r1.initFirstSingleChatInfoUI()     // Catch:{ Exception -> 0x01f0 }
                            goto L_0x023e
                        L_0x0187:
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x01f0 }
                            java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.right_more_id     // Catch:{ Exception -> 0x01f0 }
                            java.util.List r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x01f0 }
                            if (r1 == 0) goto L_0x023e
                            int r2 = r1.size()     // Catch:{ Exception -> 0x01f0 }
                            if (r2 <= 0) goto L_0x023e
                            boolean r2 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x01f0 }
                            if (r2 == 0) goto L_0x023e
                            java.lang.String r2 = "WS_BABY_ChattingUI.5.右上更多"
                            com.wx.assistants.utils.LogUtils.log(r2)     // Catch:{ Exception -> 0x01f0 }
                            int r2 = r1.size()     // Catch:{ Exception -> 0x01f0 }
                            int r2 = r2 + -1
                            java.lang.Object r1 = r1.get(r2)     // Catch:{ Exception -> 0x01f0 }
                            android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.utils.PerformClickUtils.performClick(r1)     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x01f0 }
                            r1.initFirstSingleChatInfoUI()     // Catch:{ Exception -> 0x01f0 }
                            goto L_0x023e
                        L_0x01bc:
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x01f0 }
                            java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.right_more_id     // Catch:{ Exception -> 0x01f0 }
                            java.util.List r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x01f0 }
                            if (r1 == 0) goto L_0x023e
                            int r2 = r1.size()     // Catch:{ Exception -> 0x01f0 }
                            if (r2 <= 0) goto L_0x023e
                            boolean r2 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x01f0 }
                            if (r2 == 0) goto L_0x023e
                            java.lang.String r2 = "WS_BABY_ChattingUI.6.右上更多"
                            com.wx.assistants.utils.LogUtils.log(r2)     // Catch:{ Exception -> 0x01f0 }
                            int r2 = r1.size()     // Catch:{ Exception -> 0x01f0 }
                            int r2 = r2 + -1
                            java.lang.Object r1 = r1.get(r2)     // Catch:{ Exception -> 0x01f0 }
                            android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.utils.PerformClickUtils.performClick(r1)     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x01f0 }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x01f0 }
                            r1.initFirstSingleChatInfoUI()     // Catch:{ Exception -> 0x01f0 }
                            goto L_0x023e
                        L_0x01f0:
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x024c }
                            java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.right_more_id     // Catch:{ Exception -> 0x024c }
                            java.util.List r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x024c }
                            if (r1 == 0) goto L_0x023e
                            int r2 = r1.size()     // Catch:{ Exception -> 0x024c }
                            if (r2 <= 0) goto L_0x023e
                            boolean r2 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x024c }
                            if (r2 == 0) goto L_0x023e
                            java.lang.String r2 = "WS_BABY_ChattingUI.7.右上更多"
                            com.wx.assistants.utils.LogUtils.log(r2)     // Catch:{ Exception -> 0x024c }
                            int r2 = r1.size()     // Catch:{ Exception -> 0x024c }
                            int r2 = r2 + -1
                            java.lang.Object r1 = r1.get(r2)     // Catch:{ Exception -> 0x024c }
                            android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.utils.PerformClickUtils.performClick(r1)     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x024c }
                            r1.initFirstSingleChatInfoUI()     // Catch:{ Exception -> 0x024c }
                            goto L_0x023e
                        L_0x0224:
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x024c }
                            int unused = r1.continuityNilFriendCount = r3     // Catch:{ Exception -> 0x024c }
                            java.lang.String r1 = "WS_BABY_ChattingUI.8.BACK"
                            com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10$1$3 r2 = new com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10$1$3     // Catch:{ Exception -> 0x024c }
                            r2.<init>()     // Catch:{ Exception -> 0x024c }
                            com.wx.assistants.utils.PerformClickUtils.executedBackHome(r1, r0, r2)     // Catch:{ Exception -> 0x024c }
                        L_0x023e:
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r0 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r0 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this
                            com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager
                            java.lang.StringBuilder r1 = new java.lang.StringBuilder
                            r1.<init>()
                            goto L_0x026a
                        L_0x024a:
                            r0 = move-exception
                            goto L_0x02a7
                        L_0x024c:
                            java.lang.String r1 = "WS_BABY_ChattingUI.9.BACK"
                            com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ all -> 0x024a }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this     // Catch:{ all -> 0x024a }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this     // Catch:{ all -> 0x024a }
                            com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ all -> 0x024a }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10$1$4 r2 = new com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10$1$4     // Catch:{ all -> 0x024a }
                            r2.<init>()     // Catch:{ all -> 0x024a }
                            com.wx.assistants.utils.PerformClickUtils.executedBackHome(r1, r0, r2)     // Catch:{ all -> 0x024a }
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r0 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r0 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this
                            com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager
                            java.lang.StringBuilder r1 = new java.lang.StringBuilder
                            r1.<init>()
                        L_0x026a:
                            java.lang.String r2 = "从第 "
                            r1.append(r2)
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r2 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r2 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this
                            int r2 = r2.startInt
                            r1.append(r2)
                            java.lang.String r2 = " 个好友，开始检测僵尸粉\n已执行 "
                            r1.append(r2)
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r2 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r2 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this
                            int r2 = r2.excFriendsNum
                            r1.append(r2)
                            java.lang.String r2 = " 人 ,非好友 "
                            r1.append(r2)
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r2 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r2 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this
                            int r2 = r2.noFriendsNum
                            r1.append(r2)
                            java.lang.String r2 = " 人"
                            r1.append(r2)
                            java.lang.String r1 = r1.toString()
                            com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r0, r1)
                            return
                        L_0x02a7:
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r1 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this
                            com.wx.assistants.service.MyWindowManager r1 = r1.mMyManager
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder
                            r2.<init>()
                            java.lang.String r3 = "从第 "
                            r2.append(r3)
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r3 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r3 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this
                            int r3 = r3.startInt
                            r2.append(r3)
                            java.lang.String r3 = " 个好友，开始检测僵尸粉\n已执行 "
                            r2.append(r3)
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r3 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r3 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this
                            int r3 = r3.excFriendsNum
                            r2.append(r3)
                            java.lang.String r3 = " 人 ,非好友 "
                            r2.append(r3)
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils$10 r3 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.this
                            com.wx.assistants.service_utils.CleanZombieFanCollectUtils r3 = com.wx.assistants.service_utils.CleanZombieFanCollectUtils.this
                            int r3 = r3.noFriendsNum
                            r2.append(r3)
                            java.lang.String r3 = " 人"
                            r2.append(r3)
                            java.lang.String r2 = r2.toString()
                            com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r1, r2)
                            throw r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.CleanZombieFanCollectUtils.AnonymousClass10.AnonymousClass1.nuFind():void");
                    }
                });
            }
        });
    }

    public void initChattingFirstUI(int i) {
        LogUtils.log("WS_BABY.ChattingUI.into");
        new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, i, 50, 600, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                new PerformClickUtils2().checkStringAndIdSomeOne(CleanZombieFanCollectUtils.this.autoService, "发送", BaseServiceUtils.send_add_id, 10, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        if (i == 0) {
                            PerformClickUtils.inputText(CleanZombieFanCollectUtils.this.autoService, "");
                            CleanZombieFanCollectUtils.this.clickCollection();
                            return;
                        }
                        CleanZombieFanCollectUtils.this.clickCollection();
                    }
                });
            }
        });
    }

    public void clickCollection() {
        LogUtils.log("WS_BABY_FavSelectUI.clickCollection.1");
        PerformClickUtils.findViewIdAndClick(this.autoService, send_add_id);
        new PerformClickUtils2().checkCardCollectionName(this.autoService, send_add_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 30, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                LogUtils.log("WS_BABY_FavSelectUI.clickCollection.4");
                PerformClickUtils.findTextAndClick((AccessibilityService) CleanZombieFanCollectUtils.this.autoService, "我的收藏", BaseServiceUtils.album_id);
                LogUtils.log("WS_BABY_FavSelectUI.clickCollection.5");
                CleanZombieFanCollectUtils.this.initFavSelectUI();
            }
        });
    }

    public void inputText(String str) {
        try {
            AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null) {
                boolean z = true;
                AccessibilityNodeInfo findFocus = rootInActiveWindow.findFocus(1);
                if (findFocus != null) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        if (findFocus == null) {
                            z = false;
                        }
                        if (z && findFocus.getClassName().equals("android.widget.EditText")) {
                            Bundle bundle = new Bundle();
                            bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", str + findFocus.getText() + "");
                            findFocus.performAction(2097152, bundle);
                            return;
                        }
                        return;
                    }
                    Object text = findFocus.getText();
                    if (text == null) {
                        text = "";
                    }
                    ((ClipboardManager) this.autoService.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", str + text));
                    if (Build.VERSION.SDK_INT >= 18) {
                        Bundle bundle2 = new Bundle();
                        bundle2.putInt("ACTION_ARGUMENT_SELECTION_START_INT", 0);
                        bundle2.putInt("ACTION_ARGUMENT_SELECTION_END_INT", str.length());
                        findFocus.performAction(1);
                        findFocus.performAction(WXMediaMessage.MINI_PROGRAM__THUMB_LENGHT, bundle2);
                        findFocus.performAction(32768);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executePartMain() {
        try {
            if (this.nameLists != null) {
                if (this.nameLists.size() != 0) {
                    LogUtils.log("WS_BABY.LauncherUI.executePartMain");
                    if (MyApplication.enforceable) {
                        if (this.isFirst && MyApplication.enforceable) {
                            this.isFirst = false;
                            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                            sleep(200);
                        }
                        new PerformClickUtils2().checkNodeAllIds(this.autoService, contact_nav_search_viewgroup_id, contact_nav_search_img_id, 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                                AccessibilityNodeInfo rootInActiveWindow = CleanZombieFanCollectUtils.this.autoService.getRootInActiveWindow();
                                if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_viewgroup_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                    if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                                        PerformClickUtils.findTextAndClickFirst(CleanZombieFanCollectUtils.this.autoService, "搜索");
                                    } else {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = findAccessibilityNodeInfosByViewId.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_img_id);
                                        if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && findAccessibilityNodeInfosByViewId2.get(0) != null && MyApplication.enforceable) {
                                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                                        }
                                    }
                                    CleanZombieFanCollectUtils.this.searchNickName();
                                }
                            }
                        });
                        return;
                    }
                    return;
                }
            }
            removeEndView(this.mMyManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchNickName() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, search_id, 200, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    if (CleanZombieFanCollectUtils.this.nameLists != null && CleanZombieFanCollectUtils.this.nameLists.size() > 0 && MyApplication.enforceable) {
                        String str = (String) CleanZombieFanCollectUtils.this.nameLists.iterator().next();
                        String unused = CleanZombieFanCollectUtils.this.nowName = str;
                        if (str != null && !str.equals("") && str.length() > 20) {
                            str = str.substring(0, 20);
                        }
                        PerformClickUtils.findViewIdAndClick(CleanZombieFanCollectUtils.this.autoService, BaseServiceUtils.search_id);
                        CleanZombieFanCollectUtils.this.sleep(350);
                        PerformClickUtils.inputText(CleanZombieFanCollectUtils.this.autoService, str);
                        CleanZombieFanCollectUtils.this.nameLists.remove(CleanZombieFanCollectUtils.this.nameLists.iterator().next());
                    }
                    new PerformClickUtils2().checkNodeStringsHasSomeOne(CleanZombieFanCollectUtils.this.autoService, "联系人", "最常使用", (BaseServiceUtils.executeSpeedSetting / 2) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 20, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(CleanZombieFanCollectUtils.this.autoService, "联系人");
                            boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(CleanZombieFanCollectUtils.this.autoService, "最常使用");
                            if ((findNodeIsExistByText || findNodeIsExistByText2) && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY.search_into");
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanCollectUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                                if (findViewIdList == null || findViewIdList.size() <= 0) {
                                    CleanZombieFanCollectUtils.access$208(CleanZombieFanCollectUtils.this);
                                    CleanZombieFanCollectUtils.this.saveIndex();
                                    PerformClickUtils.performBack(CleanZombieFanCollectUtils.this.autoService);
                                    LogUtils.log("WS_BABY.search_back1");
                                    CleanZombieFanCollectUtils.this.executePartMain();
                                    return;
                                }
                                PerformClickUtils.performClick(findViewIdList.get(0));
                                CleanZombieFanCollectUtils.this.initChattingFirstUI(0);
                            }
                        }

                        public void nuFind() {
                            CleanZombieFanCollectUtils.access$208(CleanZombieFanCollectUtils.this);
                            CleanZombieFanCollectUtils.this.saveIndex();
                            PerformClickUtils.performBack(CleanZombieFanCollectUtils.this.autoService);
                            LogUtils.log("WS_BABY.search_back1");
                            CleanZombieFanCollectUtils.this.executePartMain();
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
            if (this.sendResult != null && !"".equals(this.sendResult)) {
                setMiddleText(this.mMyManager, "僵尸粉检索结束", this.sendResult);
            } else if (this.isSearchResult) {
                MyWindowManager myWindowManager = this.mMyManager;
                setMiddleText(myWindowManager, "僵尸粉检索结束", "从第 " + this.startInt + " 个好友，开始检测僵尸粉\n本次已执行 " + this.excFriendsNum + " 人 ,非好友 " + this.noFriendsNum + " 人");
            } else {
                setMiddleText(this.mMyManager, "僵尸粉检索结束", "没有找到好友，起始点可能超出检测范围！！！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewShow() {
        try {
            this.isFirst = true;
            MyWindowManager myWindowManager = this.mMyManager;
            showBottomView(myWindowManager, "从第 " + this.startInt + " 个好友，开始检测僵尸粉\n请不要操作微信");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        if (CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() != 0) {
                            if (CleanZombieFanCollectUtils.this.operationParameterModel.getSendCardType() != 2) {
                                CleanZombieFanCollectUtils.this.executePartMain();
                                return;
                            }
                        }
                        CleanZombieFanCollectUtils.this.executeMain(CleanZombieFanCollectUtils.this.nameLists);
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
