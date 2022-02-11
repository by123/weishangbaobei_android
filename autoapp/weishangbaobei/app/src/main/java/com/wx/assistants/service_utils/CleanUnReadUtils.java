package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.yalantis.ucrop.view.CropImageView;
import java.util.List;

public class CleanUnReadUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static CleanUnReadUtils instance;
    private int executionNum = 0;
    /* access modifiers changed from: private */
    public boolean isEnd = false;
    /* access modifiers changed from: private */
    public String lastScrollNickName;
    /* access modifiers changed from: private */
    public int startIndex = 0;

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$008(CleanUnReadUtils cleanUnReadUtils) {
        int i = cleanUnReadUtils.startIndex;
        cleanUnReadUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$108(CleanUnReadUtils cleanUnReadUtils) {
        int i = cleanUnReadUtils.executionNum;
        cleanUnReadUtils.executionNum = i + 1;
        return i;
    }

    private CleanUnReadUtils() {
    }

    public static CleanUnReadUtils getInstance() {
        instance = new CleanUnReadUtils();
        return instance;
    }

    public void initData() {
        this.startIndex = 0;
        this.executionNum = 0;
        this.lastScrollNickName = "";
        this.isEnd = false;
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void executeMain() {
        try {
            new PerformClickUtils2().check(this.autoService, home_msg_list_item_id, 30, 100, 10, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                    LogUtils.log("@@@@@@@@@@@@@@@@@@@@@3");
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanUnReadUtils.this.autoService, BaseServiceUtils.home_msg_list_item_id);
                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                        if (CleanUnReadUtils.this.startIndex < findViewIdList.size() && MyApplication.enforceable) {
                            LogUtils.log("@@@@@@@@@@@@@@@@@@@@@4");
                            AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(CleanUnReadUtils.this.startIndex);
                            if (accessibilityNodeInfo != null && MyApplication.enforceable) {
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(BaseServiceUtils.read_remark_1_id);
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(BaseServiceUtils.read_remark_2_id);
                                LogUtils.log("@@@@@@@@@@@@@@@@@@@@@5");
                                if ((findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() == 0) && (findAccessibilityNodeInfosByViewId3 == null || findAccessibilityNodeInfosByViewId3.size() == 0)) {
                                    CleanUnReadUtils.access$008(CleanUnReadUtils.this);
                                    CleanUnReadUtils.this.executeMain();
                                    return;
                                }
                                PerformClickUtils.performLongClick(accessibilityNodeInfo);
                                new PerformClickUtils2().checkNodeStringsHasSomeOne(CleanUnReadUtils.this.autoService, "标为已读", "标为未读", 100, 100, 5, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        if (i == 0) {
                                            PerformClickUtils.findTextAndClick(CleanUnReadUtils.this.autoService, "标为已读");
                                            CleanUnReadUtils.access$108(CleanUnReadUtils.this);
                                            CleanUnReadUtils.access$008(CleanUnReadUtils.this);
                                            CleanUnReadUtils.this.sleep((BaseServiceUtils.executeSpeedSetting / 3) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                            CleanUnReadUtils.this.executeMain();
                                            return;
                                        }
                                        PerformClickUtils.executedBackHomeDeep(CleanUnReadUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                            public void find() {
                                                CleanUnReadUtils.access$108(CleanUnReadUtils.this);
                                                CleanUnReadUtils.access$008(CleanUnReadUtils.this);
                                                CleanUnReadUtils.this.sleep((BaseServiceUtils.executeSpeedSetting / 3) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                                CleanUnReadUtils.this.executeMain();
                                            }
                                        });
                                    }

                                    public void nuFind() {
                                        PerformClickUtils.executedBackHomeDeep(CleanUnReadUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                            public void find() {
                                                CleanUnReadUtils.access$108(CleanUnReadUtils.this);
                                                CleanUnReadUtils.access$008(CleanUnReadUtils.this);
                                                CleanUnReadUtils.this.sleep((BaseServiceUtils.executeSpeedSetting / 3) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                                CleanUnReadUtils.this.executeMain();
                                            }
                                        });
                                    }
                                });
                            }
                        } else if (CleanUnReadUtils.this.startIndex >= findViewIdList.size() && MyApplication.enforceable) {
                            if (CleanUnReadUtils.this.isEnd || !MyApplication.enforceable) {
                                BaseServiceUtils.removeEndView(CleanUnReadUtils.this.mMyManager);
                                return;
                            }
                            LogUtils.log("@@@@@@@@@@@@@@@@@@@@@7");
                            AccessibilityNodeInfo rootInActiveWindow = CleanUnReadUtils.this.autoService.getRootInActiveWindow();
                            if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.home_msg_list_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                PerformClickUtils.scroll(findAccessibilityNodeInfosByViewId.get(0));
                                LogUtils.log("@@@@@@@@@@@@@@@@@@@@@8");
                                CleanUnReadUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                AccessibilityNodeInfo rootInActiveWindow2 = CleanUnReadUtils.this.autoService.getRootInActiveWindow();
                                if (rootInActiveWindow2 != null && MyApplication.enforceable) {
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId4 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.home_msg_list_name_id);
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId5 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.home_msg_list_desc_id);
                                    if (findAccessibilityNodeInfosByViewId4 != null && findAccessibilityNodeInfosByViewId4.size() > 0 && MyApplication.enforceable) {
                                        LogUtils.log("@@@@@@@@@@@@@@@@@@@@@9");
                                        try {
                                            String charSequence = findAccessibilityNodeInfosByViewId4.get(findAccessibilityNodeInfosByViewId4.size() - 1).getText().toString();
                                            String charSequence2 = findAccessibilityNodeInfosByViewId5.get(findAccessibilityNodeInfosByViewId4.size() - 1).getText().toString();
                                            if (charSequence != null) {
                                                String access$300 = CleanUnReadUtils.this.lastScrollNickName;
                                                if (access$300.equals(charSequence + charSequence2)) {
                                                    boolean unused = CleanUnReadUtils.this.isEnd = true;
                                                    BaseServiceUtils.removeEndView(CleanUnReadUtils.this.mMyManager);
                                                    LogUtils.log("@@@@@@@@@@@@@@@@@@@@@11" + CleanUnReadUtils.this.lastScrollNickName + "#" + charSequence);
                                                    return;
                                                }
                                            }
                                            CleanUnReadUtils cleanUnReadUtils = CleanUnReadUtils.this;
                                            String unused2 = cleanUnReadUtils.lastScrollNickName = charSequence + charSequence2;
                                            int unused3 = CleanUnReadUtils.this.startIndex = 1;
                                            CleanUnReadUtils.this.executeMain();
                                        } catch (Exception unused4) {
                                            int unused5 = CleanUnReadUtils.this.startIndex = 1;
                                            CleanUnReadUtils.this.executeMain();
                                            LogUtils.log("@@@@@@@@@@@@@@@@@@@@@12");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        setMiddleText(myWindowManager, "清理未读消息", "已标记了" + this.executionNum + "条未读消息");
    }

    public void endViewShow() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    PerformClickUtils.findTextAndClick(CleanUnReadUtils.this.autoService, "微信");
                    CleanUnReadUtils.this.sleep(100);
                    PerformClickUtils.findTextAndClick(CleanUnReadUtils.this.autoService, "微信");
                    CleanUnReadUtils.this.sleep(100);
                    PerformClickUtils.findTextAndClick(CleanUnReadUtils.this.autoService, "微信");
                    CleanUnReadUtils.this.sleep(100);
                    CleanUnReadUtils.this.executeMain();
                } catch (Exception unused) {
                }
            }
        }).start();
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }
}
