package com.wx.assistants.service_utils;

import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.yalantis.ucrop.view.CropImageView;
import java.util.LinkedHashSet;
import java.util.List;

public class CleanFriendsNickNameUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static CleanFriendsNickNameUtils instance;
    private int cleanMaxNum = 200;
    /* access modifiers changed from: private */
    public int deletedNum = 0;
    String inputText = "";
    /* access modifiers changed from: private */
    public int jumpDeleteNum = 0;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> keyWords = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public String nowName = "";

    private CleanFriendsNickNameUtils() {
    }

    static /* synthetic */ int access$208(CleanFriendsNickNameUtils cleanFriendsNickNameUtils) {
        int i = cleanFriendsNickNameUtils.jumpDeleteNum;
        cleanFriendsNickNameUtils.jumpDeleteNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$308(CleanFriendsNickNameUtils cleanFriendsNickNameUtils) {
        int i = cleanFriendsNickNameUtils.deletedNum;
        cleanFriendsNickNameUtils.deletedNum = i + 1;
        return i;
    }

    public static CleanFriendsNickNameUtils getInstance() {
        instance = new CleanFriendsNickNameUtils();
        return instance;
    }

    public void deleteDialog() {
        new PerformClickUtils2().check(this.autoService, dialog_ok_id, 10, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findViewIdAndClick(CleanFriendsNickNameUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                CleanFriendsNickNameUtils.access$308(CleanFriendsNickNameUtils.this);
                MyWindowManager myWindowManager = CleanFriendsNickNameUtils.this.mMyManager;
                BaseServiceUtils.updateBottomText(myWindowManager, "已删除了 " + CleanFriendsNickNameUtils.this.deletedNum + " 个好友");
                CleanFriendsNickNameUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                CleanFriendsNickNameUtils.this.executeKeyWordMain();
            }

            public void nuFind() {
                PerformClickUtils.performBack(CleanFriendsNickNameUtils.this.autoService);
                CleanFriendsNickNameUtils.access$208(CleanFriendsNickNameUtils.this);
                PerformClickUtils.executedBackHome(CleanFriendsNickNameUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        CleanFriendsNickNameUtils.this.executeKeyWordMain();
                    }
                });
            }
        });
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        showBottomView(this.mMyManager, "正在一键删除好友，请不要操作微信！");
        LogUtils.log("WS_BABY.END_SHOW");
        new Thread(new Runnable() {
            public void run() {
                try {
                    CleanFriendsNickNameUtils.this.executeKeyWordMain();
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public void executeKeyWordMain() {
        try {
            LogUtils.log("WS_BABY.executeKeyWordMain");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            if (this.deletedNum >= this.cleanMaxNum) {
                removeEndView(this.mMyManager);
            } else {
                new PerformClickUtils2().check(this.autoService, contact_nav_search_viewgroup_id, executeSpeed, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                        AccessibilityNodeInfo rootInActiveWindow = CleanFriendsNickNameUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_viewgroup_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                            if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                                PerformClickUtils.findTextAndClickFirst(CleanFriendsNickNameUtils.this.autoService, "搜索");
                            } else {
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = findAccessibilityNodeInfosByViewId.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_img_id);
                                if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && findAccessibilityNodeInfosByViewId2.get(0) != null && MyApplication.enforceable) {
                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                                }
                            }
                            CleanFriendsNickNameUtils.this.searchNickName(CleanFriendsNickNameUtils.this.keyWords);
                        }
                    }

                    public void nuFind() {
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initChattingUI() {
        try {
            new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeed + executeSpeedSetting, 100, 80, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findViewIdAndClick(CleanFriendsNickNameUtils.this.autoService, BaseServiceUtils.right_more_id);
                    CleanFriendsNickNameUtils.this.initSingleChatInfo();
                }

                public void nuFind() {
                    PerformClickUtils.executedBackHome(CleanFriendsNickNameUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            CleanFriendsNickNameUtils.this.executeKeyWordMain();
                        }
                    });
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_73");
            e.printStackTrace();
        }
    }

    public void initContactInfoUI() {
        try {
            new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeed + executeSpeedSetting, 100, 30, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    CleanFriendsNickNameUtils.this.saveFriendInfo(CleanFriendsNickNameUtils.this.nowName);
                    PerformClickUtils.findViewIdAndClick(CleanFriendsNickNameUtils.this.autoService, BaseServiceUtils.right_more_id);
                    new PerformClickUtils2().checkString(CleanFriendsNickNameUtils.this.autoService, "黑名单", BaseServiceUtils.executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            System.out.println("WS_BABY_DELETE_1");
                            if (!PerformClickUtils.findNodeIsExistByText(CleanFriendsNickNameUtils.this.autoService, "删除") || !MyApplication.enforceable) {
                                System.out.println("WS_BABY_DELETE_4");
                                PerformClickUtils.scroll(CleanFriendsNickNameUtils.this.autoService, BaseServiceUtils.more_recycle_view_id);
                                CleanFriendsNickNameUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                PerformClickUtils.findTextAndClick(CleanFriendsNickNameUtils.this.autoService, "删除");
                            } else {
                                System.out.println("WS_BABY_DELETE_3");
                                PerformClickUtils.findTextAndClick(CleanFriendsNickNameUtils.this.autoService, "删除");
                            }
                            CleanFriendsNickNameUtils.this.deleteDialog();
                        }

                        public void nuFind() {
                            System.out.println("WS_BABY_DELETE_4");
                        }
                    });
                }

                public void nuFind() {
                    System.out.println("WS_BABY_DELETE_5");
                    PerformClickUtils.executedBackHome(CleanFriendsNickNameUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            CleanFriendsNickNameUtils.access$208(CleanFriendsNickNameUtils.this);
                            CleanFriendsNickNameUtils.this.executeKeyWordMain();
                        }
                    });
                }
            });
        } catch (Exception e) {
            System.out.println("WS_BABY_DELETE_6");
            e.printStackTrace();
        }
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.jumpDeleteNum = 0;
        this.deletedNum = 0;
        this.nowName = "";
        this.cleanMaxNum = operationParameterModel.getMaxOperaNum();
        String friendsNameStr = operationParameterModel.getFriendsNameStr();
        this.keyWords = new LinkedHashSet<>();
        if (friendsNameStr != null && !"".equals(friendsNameStr)) {
            if (friendsNameStr.contains("##")) {
                String[] split = friendsNameStr.split("##");
                for (String str : split) {
                    if (str != null && !"".equals(str)) {
                        this.keyWords.add(str);
                    }
                }
            } else {
                this.keyWords.add(friendsNameStr);
            }
        }
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initSingleChatInfo() {
        try {
            new PerformClickUtils2().check(this.autoService, single_contact_nick_name_id, executeSpeed + executeSpeedSetting, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findViewIdAndClick(CleanFriendsNickNameUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                    CleanFriendsNickNameUtils.this.initContactInfoUI();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_74");
            e.printStackTrace();
        }
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        setMiddleText(myWindowManager, "一键删除好友", "共删除了" + this.deletedNum + "个好友");
    }

    public void searchNickName(final LinkedHashSet<String> linkedHashSet) {
        if (linkedHashSet != null) {
            try {
                if (linkedHashSet.size() > 0) {
                    new PerformClickUtils2().checkNodeId(this.autoService, search_id, 200, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            if (linkedHashSet != null && !"".equals(linkedHashSet) && MyApplication.enforceable) {
                                CleanFriendsNickNameUtils.this.inputText = (String) linkedHashSet.iterator().next();
                                if (CleanFriendsNickNameUtils.this.inputText != null && !CleanFriendsNickNameUtils.this.inputText.equals("") && CleanFriendsNickNameUtils.this.inputText.length() > 20) {
                                    CleanFriendsNickNameUtils.this.inputText = CleanFriendsNickNameUtils.this.inputText.substring(0, 20);
                                }
                                PerformClickUtils.findViewIdAndClick(CleanFriendsNickNameUtils.this.autoService, BaseServiceUtils.search_id);
                                CleanFriendsNickNameUtils.this.sleep(350);
                                PerformClickUtils.inputText(CleanFriendsNickNameUtils.this.autoService, CleanFriendsNickNameUtils.this.inputText);
                            }
                            new PerformClickUtils2().checkNodeStringsHasSomeOne(CleanFriendsNickNameUtils.this.autoService, "联系人", "最常使用", (BaseServiceUtils.executeSpeedSetting / 3) + 600, 100, 30, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    AccessibilityNodeInfo rootInActiveWindow = CleanFriendsNickNameUtils.this.autoService.getRootInActiveWindow();
                                    if (rootInActiveWindow != null && MyApplication.enforceable) {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_name_id);
                                        if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() - CleanFriendsNickNameUtils.this.jumpDeleteNum <= 0 || !MyApplication.enforceable) {
                                            BaseServiceUtils.removeEndView(CleanFriendsNickNameUtils.this.mMyManager);
                                            return;
                                        }
                                        boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(CleanFriendsNickNameUtils.this.autoService, "联系人");
                                        boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(CleanFriendsNickNameUtils.this.autoService, "最常使用");
                                        if ((findNodeIsExistByText || findNodeIsExistByText2) && MyApplication.enforceable) {
                                            LogUtils.log("WS_BABY.search_into");
                                            try {
                                                int access$200 = CleanFriendsNickNameUtils.this.jumpDeleteNum;
                                                while (true) {
                                                    int i2 = access$200;
                                                    if (i2 >= findAccessibilityNodeInfosByViewId.size()) {
                                                        break;
                                                    } else if (findAccessibilityNodeInfosByViewId.get(i2).toString().contains(CleanFriendsNickNameUtils.this.inputText)) {
                                                        int unused = CleanFriendsNickNameUtils.this.jumpDeleteNum = i2;
                                                        break;
                                                    } else {
                                                        access$200 = i2 + 1;
                                                    }
                                                }
                                            } catch (Exception e) {
                                            }
                                            if (CleanFriendsNickNameUtils.this.jumpDeleteNum < findAccessibilityNodeInfosByViewId.size()) {
                                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(CleanFriendsNickNameUtils.this.jumpDeleteNum));
                                            } else {
                                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                            }
                                            CleanFriendsNickNameUtils.this.initChattingUI();
                                            return;
                                        }
                                        linkedHashSet.remove(linkedHashSet.iterator().next());
                                        if (linkedHashSet == null || linkedHashSet.size() - CleanFriendsNickNameUtils.this.jumpDeleteNum <= 0 || !MyApplication.enforceable) {
                                            BaseServiceUtils.removeEndView(CleanFriendsNickNameUtils.this.mMyManager);
                                        } else {
                                            PerformClickUtils.executedBackHome(CleanFriendsNickNameUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                public void find() {
                                                    CleanFriendsNickNameUtils.this.executeKeyWordMain();
                                                }
                                            });
                                        }
                                    }
                                }

                                public void nuFind() {
                                    linkedHashSet.remove(linkedHashSet.iterator().next());
                                    if (linkedHashSet == null || linkedHashSet.size() <= 0) {
                                        BaseServiceUtils.removeEndView(CleanFriendsNickNameUtils.this.mMyManager);
                                    } else {
                                        PerformClickUtils.executedBackHome(CleanFriendsNickNameUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                            public void find() {
                                                CleanFriendsNickNameUtils.this.executeKeyWordMain();
                                            }
                                        });
                                    }
                                }
                            });
                        }

                        public void nuFind() {
                        }
                    });
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        removeEndView(this.mMyManager);
    }
}
