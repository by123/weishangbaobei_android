package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

public class AutoAddContactsNewFriendsUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static AutoAddContactsNewFriendsUtils instance;
    /* access modifiers changed from: private */
    public int ffModel = 0;
    /* access modifiers changed from: private */
    public int ffModelEndTime = 10;
    /* access modifiers changed from: private */
    public int ffModelStartTime = 0;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    private List<String> jumpStartLists = new ArrayList();
    /* access modifiers changed from: private */
    public String lastScrollNickName;
    private int maxNum = 50;
    /* access modifiers changed from: private */
    public String nowName = "";
    /* access modifiers changed from: private */
    public int recordSendNum = 0;
    private int scrollCount = 0;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    private int startPosition = 1;

    public void middleViewDismiss() {
    }

    private AutoAddContactsNewFriendsUtils() {
    }

    public static AutoAddContactsNewFriendsUtils getInstance() {
        instance = new AutoAddContactsNewFriendsUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.recordSendNum = 0;
        this.scrollCount = 0;
        this.startIndex = 0;
        this.lastScrollNickName = "";
        this.nowName = "";
        this.isScrollBottom = false;
        this.jumpStartLists = new ArrayList();
        this.maxNum = operationParameterModel.getMaxOperaNum();
        this.spaceTime = operationParameterModel.getSpaceTime();
        this.ffModel = operationParameterModel.getFfModel();
        this.startPosition = operationParameterModel.getStartIndex();
        this.ffModelStartTime = operationParameterModel.getFfModelStartTime();
        this.ffModelEndTime = operationParameterModel.getFfModelEndTime();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void executeAdd() {
        try {
            if (this.maxNum <= this.recordSendNum) {
                removeEndView(this.mMyManager);
            }
            LogUtils.log("WS_BABY_ADD@@4444");
            new PerformClickUtils2().check(this.autoService, list_layout_name, executeSpeed, 100, 10, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_ADD@@5555");
                    new PerformClickUtils2().check(AutoAddContactsNewFriendsUtils.this.autoService, BaseServiceUtils.list_layout_add_id, 100, 100, 10, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            if (AutoAddContactsNewFriendsUtils.this.ffModel == 1) {
                                int unused = AutoAddContactsNewFriendsUtils.this.spaceTime = PerformClickUtils.getRandomTime(AutoAddContactsNewFriendsUtils.this.ffModel, AutoAddContactsNewFriendsUtils.this.ffModelStartTime, AutoAddContactsNewFriendsUtils.this.ffModelEndTime);
                                LogUtils.log("WS_BABY.small_spaceTime=" + AutoAddContactsNewFriendsUtils.this.spaceTime);
                            }
                            if (AutoAddContactsNewFriendsUtils.this.spaceTime <= 0 || AutoAddContactsNewFriendsUtils.this.recordSendNum <= 0) {
                                LogUtils.log("WS_BABY_ADD@@66666");
                                AutoAddContactsNewFriendsUtils.this.executedAddOperation();
                                return;
                            }
                            new PerformClickUtils2().countDown2(AutoAddContactsNewFriendsUtils.this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                                public void surplus(int i) {
                                    MyWindowManager myWindowManager = AutoAddContactsNewFriendsUtils.this.mMyManager;
                                    BaseServiceUtils.updateBottomText(myWindowManager, "自动加新朋友推荐，已添加 " + AutoAddContactsNewFriendsUtils.this.recordSendNum + " 人 ,\n正在延时等待，倒计时 " + i + " 秒");
                                }

                                public void end() {
                                    AutoAddContactsNewFriendsUtils.this.executedAddOperation();
                                }
                            });
                        }

                        public void nuFind() {
                            AccessibilityNodeInfo accessibilityNodeInfo;
                            PerformClickUtils.scroll(AutoAddContactsNewFriendsUtils.this.autoService, BaseServiceUtils.new_friends_list_id);
                            AutoAddContactsNewFriendsUtils.this.sleep(MyApplication.SCROLL_SPEED);
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddContactsNewFriendsUtils.this.autoService, BaseServiceUtils.list_layout_name);
                            if (findViewIdList != null && findViewIdList.size() > 0 && (accessibilityNodeInfo = findViewIdList.get(findViewIdList.size() - 1)) != null) {
                                String str = "";
                                CharSequence text = accessibilityNodeInfo.getText();
                                if (text != null && !"".equals(text)) {
                                    str = text.toString();
                                }
                                LogUtils.log("WS_BABY_ADD@@lastName2 = " + str);
                                LogUtils.log("WS_BABY_ADD@@lastScrollNickName2 = " + AutoAddContactsNewFriendsUtils.this.lastScrollNickName);
                                if (AutoAddContactsNewFriendsUtils.this.lastScrollNickName.equals(str)) {
                                    LogUtils.log("WS_BABY_ADD@@END2");
                                    BaseServiceUtils.removeEndView(AutoAddContactsNewFriendsUtils.this.mMyManager);
                                    return;
                                }
                                LogUtils.log("WS_BABY_ADD@@6");
                                String unused = AutoAddContactsNewFriendsUtils.this.lastScrollNickName = str;
                                AutoAddContactsNewFriendsUtils.this.executeAdd();
                            }
                        }
                    });
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY_ADD@@END10");
                    boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(AutoAddContactsNewFriendsUtils.this.autoService, "发消息");
                    boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(AutoAddContactsNewFriendsUtils.this.autoService, "音视频通话");
                    if (!findNodeIsExistByText || !findNodeIsExistByText2) {
                        LogUtils.log("WS_BABY_ADD@@12");
                        BaseServiceUtils.removeEndView(AutoAddContactsNewFriendsUtils.this.mMyManager);
                        return;
                    }
                    LogUtils.log("WS_BABY_ADD@@11");
                    PerformClickUtils.performBack(AutoAddContactsNewFriendsUtils.this.autoService);
                    AutoAddContactsNewFriendsUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                    AutoAddContactsNewFriendsUtils.this.executeAdd();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executedAddOperation() {
        AccessibilityNodeInfo accessibilityNodeInfo;
        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_layout_add_id);
        if (findViewIdList != null && findViewIdList.size() > 0) {
            boolean z = false;
            int i = this.startIndex;
            while (true) {
                if (i >= findViewIdList.size()) {
                    break;
                }
                AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList.get(i);
                if (!(accessibilityNodeInfo2 == null || accessibilityNodeInfo2.getText() == null)) {
                    if ("添加".equals(accessibilityNodeInfo2.getText() + "")) {
                        this.recordSendNum++;
                        PerformClickUtils.performClick(accessibilityNodeInfo2);
                        z = true;
                        break;
                    }
                }
                i++;
            }
            if (z) {
                LogUtils.log("WS_BABY_ADD@@00000");
                new PerformClickUtils2().checkNilString(this.autoService, "正在添加", 2500, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 80, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        AutoAddContactsNewFriendsUtils.this.sleep(300);
                        LogUtils.log("WS_BABY_ADD@@1");
                        MyWindowManager myWindowManager = AutoAddContactsNewFriendsUtils.this.mMyManager;
                        BaseServiceUtils.updateBottomText(myWindowManager, "已向 " + AutoAddContactsNewFriendsUtils.this.recordSendNum + " 个通讯录朋友，发起添加请求。");
                        boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(AutoAddContactsNewFriendsUtils.this.autoService, "发消息");
                        boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(AutoAddContactsNewFriendsUtils.this.autoService, "音视频通话");
                        if (!findNodeIsExistByText || !findNodeIsExistByText2) {
                            LogUtils.log("WS_BABY_ADD@@3");
                            AutoAddContactsNewFriendsUtils.this.executeAdd();
                            return;
                        }
                        LogUtils.log("WS_BABY_ADD@@2");
                        PerformClickUtils.performBack(AutoAddContactsNewFriendsUtils.this.autoService);
                        AutoAddContactsNewFriendsUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                        AutoAddContactsNewFriendsUtils.this.executeAdd();
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY_ADD@@4");
                    }
                });
                return;
            }
            PerformClickUtils.scroll(this.autoService, new_friends_list_id);
            sleep(MyApplication.SCROLL_SPEED);
            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_layout_name);
            if (findViewIdList2 != null && findViewIdList2.size() > 0 && (accessibilityNodeInfo = findViewIdList2.get(findViewIdList2.size() - 1)) != null) {
                String str = "";
                if (accessibilityNodeInfo.getText() != null) {
                    str = accessibilityNodeInfo.getText().toString();
                }
                if (this.lastScrollNickName.equals(str)) {
                    LogUtils.log("WS_BABY_ADD@@END1");
                    removeEndView(this.mMyManager);
                    return;
                }
                LogUtils.log("WS_BABY_ADD@@5");
                this.lastScrollNickName = str;
                executeAdd();
            }
        }
    }

    public void executeMain() {
        try {
            if (this.startPosition > 1) {
                jumpStartIndex();
            } else {
                executeAdd();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jumpStartIndex() {
        LogUtils.log("WS_BABY_LauncherUI.start...");
        AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
        if (rootInActiveWindow != null && MyApplication.enforceable) {
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(list_layout_name);
            if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || !MyApplication.enforceable) {
                removeEndView(this.mMyManager);
            } else if (this.startIndex < findAccessibilityNodeInfosByViewId.size() && MyApplication.enforceable) {
                AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(this.startIndex);
                if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                    this.nowName = accessibilityNodeInfo.getText().toString();
                    Rect rect = new Rect();
                    accessibilityNodeInfo.getBoundsInScreen(rect);
                    this.nowName += rect.toString();
                    LogUtils.log("WS_BABY_nowName@" + this.nowName);
                }
                if (!this.isScrollBottom || !this.jumpStartLists.contains(this.nowName)) {
                    this.scrollCount++;
                }
                LogUtils.log("WS_BABY_JUMP_NUM@" + this.scrollCount + "@" + this.startPosition);
                if (this.scrollCount == this.startPosition) {
                    executeAdd();
                    return;
                }
                this.jumpStartLists.add(this.nowName);
                this.startIndex++;
                updateBottomText(this.mMyManager, "从第 " + this.startPosition + " 个开始,已跳过 " + this.scrollCount + " 个。");
                jumpStartIndex();
            } else if (this.startIndex >= findAccessibilityNodeInfosByViewId.size() && MyApplication.enforceable) {
                new PerformClickUtils2().check(this.autoService, new_friends_list_id, 10, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        AccessibilityNodeInfo accessibilityNodeInfo;
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddContactsNewFriendsUtils.this.autoService, BaseServiceUtils.new_friends_list_id);
                        if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                            findViewIdList.get(0).performAction(4096);
                            int unused = AutoAddContactsNewFriendsUtils.this.startIndex = 1;
                            AutoAddContactsNewFriendsUtils.this.sleep(MyApplication.SCROLL_SPEED);
                            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddContactsNewFriendsUtils.this.autoService, BaseServiceUtils.list_layout_name);
                            if (!(findViewIdList2 == null || findViewIdList2.size() <= 0 || (accessibilityNodeInfo = findViewIdList2.get(findViewIdList2.size() - 1)) == null || accessibilityNodeInfo.getText() == null)) {
                                String charSequence = accessibilityNodeInfo.getText().toString();
                                Rect rect = new Rect();
                                accessibilityNodeInfo.getBoundsInScreen(rect);
                                if ((charSequence + rect.toString()).equals(AutoAddContactsNewFriendsUtils.this.nowName)) {
                                    boolean unused2 = AutoAddContactsNewFriendsUtils.this.isScrollBottom = true;
                                }
                            }
                            if (AutoAddContactsNewFriendsUtils.this.isScrollBottom) {
                                LogUtils.log("WS_BABY_LauncherUI.END");
                                BaseServiceUtils.removeEndView(AutoAddContactsNewFriendsUtils.this.mMyManager);
                                return;
                            }
                            boolean unused3 = AutoAddContactsNewFriendsUtils.this.isScrollBottom = false;
                            AutoAddContactsNewFriendsUtils.this.jumpStartIndex();
                        }
                    }
                });
            }
        }
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        updateMiddleText(myWindowManager, "自动加新朋友推荐", "已向 " + this.recordSendNum + " 个通讯录朋友，发起添加请求。");
    }

    public void endViewShow() {
        showBottomView(this.mMyManager, "自动加新朋友推荐，请不要操作微信");
        new Thread(new Runnable() {
            public void run() {
                BaseServiceUtils.getMainHandler().postDelayed(new Runnable() {
                    public void run() {
                        AutoAddContactsNewFriendsUtils.this.executeMain();
                    }
                }, 10);
            }
        }).start();
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }
}
