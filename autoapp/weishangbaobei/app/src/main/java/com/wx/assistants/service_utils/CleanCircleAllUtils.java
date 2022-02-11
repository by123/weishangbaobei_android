package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.accessibility.AccessibilityNodeInfo;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import java.util.Date;
import java.util.List;

public class CleanCircleAllUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static CleanCircleAllUtils instance;
    private String currentDay = "";
    private String currentMonth = "";
    private String currentYear = "";
    /* access modifiers changed from: private */
    public String endTime;
    /* access modifiers changed from: private */
    public int exeNum = 0;
    private boolean isCanEnd = false;
    /* access modifiers changed from: private */
    public boolean isExeTasking = true;
    private boolean isFindExecuteStartIndex = false;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    /* access modifiers changed from: private */
    public OperationParameterModel model;
    /* access modifiers changed from: private */
    public int scrollCount = 0;
    private int startIndex = 0;
    /* access modifiers changed from: private */
    public String startTime;

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$308(CleanCircleAllUtils cleanCircleAllUtils) {
        int i = cleanCircleAllUtils.startIndex;
        cleanCircleAllUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$508(CleanCircleAllUtils cleanCircleAllUtils) {
        int i = cleanCircleAllUtils.exeNum;
        cleanCircleAllUtils.exeNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$708(CleanCircleAllUtils cleanCircleAllUtils) {
        int i = cleanCircleAllUtils.scrollCount;
        cleanCircleAllUtils.scrollCount = i + 1;
        return i;
    }

    private CleanCircleAllUtils() {
    }

    public static CleanCircleAllUtils getInstance() {
        instance = new CleanCircleAllUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.model = operationParameterModel;
        this.startIndex = 0;
        this.exeNum = 0;
        this.scrollCount = 0;
        this.isCanEnd = false;
        this.isScrollBottom = false;
        this.isExeTasking = true;
        this.currentYear = "";
        this.currentMonth = "";
        this.currentDay = "";
        this.isFindExecuteStartIndex = false;
        this.startTime = operationParameterModel.getStartDate();
        this.endTime = operationParameterModel.getEndDate();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initSnsUserUIForAll() {
        if (this.isExeTasking) {
            LogUtils.log("WS_BABY.SnsUserUI.0....." + executeSpeedSetting);
            try {
                new PerformClickUtils2().checkNodeId(this.autoService, list_circle_layout_id, executeSpeedSetting + executeSpeed, 100, 30, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY.SnsUserUI.1");
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanCircleAllUtils.this.autoService, BaseServiceUtils.list_circle_layout_id);
                        if (findViewIdList == null || findViewIdList.size() <= 0) {
                            LogUtils.log("WS_BABY.SnsUserUI.9");
                            BaseServiceUtils.removeEndView(CleanCircleAllUtils.this.mMyManager);
                            return;
                        }
                        LogUtils.log("WS_BABY.SnsUserUI.2");
                        if (findViewIdList == null || findViewIdList.size() != 1) {
                            LogUtils.log("WS_BABY.SnsUserUI.3");
                            final AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(1);
                            if (accessibilityNodeInfo != null) {
                                final List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_layout_only_text_id);
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_circle_img_video_id);
                                final List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_layout_child_url);
                                if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                                    CleanCircleAllUtils.this.sleep(100);
                                    LogUtils.log("WS_BABY.SnsUserUI.4");
                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1));
                                    new PerformClickUtils2().check(CleanCircleAllUtils.this.autoService, BaseServiceUtils.desc_text_time_id, BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 600, (PerformClickUtils2.OnCheckListener5) new PerformClickUtils2.OnCheckListener5() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            CleanCircleAllUtils.this.initSnsCommentDetailUI();
                                        }

                                        public void execute(int i) {
                                            if (i == 10 || i == 20) {
                                                System.out.println("WS_BABY_NNNNNNN");
                                                PerformClickUtils.performClick((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1));
                                            }
                                        }
                                    });
                                } else if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0) {
                                    LogUtils.log("WS_BABY.SnsUserUI.5");
                                    CleanCircleAllUtils.this.sleep(100);
                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(findAccessibilityNodeInfosByViewId2.size() - 1));
                                    new PerformClickUtils2().checkNodeAllIds(CleanCircleAllUtils.this.autoService, BaseServiceUtils.desc_img_time_id, BaseServiceUtils.right_more_id, BaseServiceUtils.executeSpeedSetting + BaseServiceUtils.executeSpeed, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            LogUtils.log("WS_BABY.SnsUserUI.55");
                                            CleanCircleAllUtils.this.initSnsGalleryUI();
                                        }
                                    });
                                } else if (findAccessibilityNodeInfosByViewId3 == null || findAccessibilityNodeInfosByViewId3.size() <= 0) {
                                    CleanCircleAllUtils.this.sleep(100);
                                    LogUtils.log("WS_BABY.SnsUserUI.7");
                                    PerformClickUtils.performClick(accessibilityNodeInfo);
                                    new PerformClickUtils2().check(CleanCircleAllUtils.this.autoService, BaseServiceUtils.desc_text_time_id, BaseServiceUtils.executeSpeedSetting + BaseServiceUtils.executeSpeed, 100, 600, (PerformClickUtils2.OnCheckListener5) new PerformClickUtils2.OnCheckListener5() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            CleanCircleAllUtils.this.initSnsCommentDetailUI();
                                        }

                                        public void execute(int i) {
                                            if (i == 10 || i == 20) {
                                                System.out.println("WS_BABY_NNNNNNN");
                                                PerformClickUtils.performClick(accessibilityNodeInfo);
                                            }
                                        }
                                    });
                                } else {
                                    LogUtils.log("WS_BABY.SnsUserUI.6");
                                    CleanCircleAllUtils.this.sleep(100);
                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId3.get(findAccessibilityNodeInfosByViewId3.size() - 1));
                                    new PerformClickUtils2().check(CleanCircleAllUtils.this.autoService, BaseServiceUtils.desc_text_time_id, BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 600, (PerformClickUtils2.OnCheckListener5) new PerformClickUtils2.OnCheckListener5() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            CleanCircleAllUtils.this.initSnsCommentDetailUI();
                                        }

                                        public void execute(int i) {
                                            if (i == 10 || i == 20) {
                                                System.out.println("WS_BABY_NNNNNNN");
                                                PerformClickUtils.performClick((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId3.get(findAccessibilityNodeInfosByViewId3.size() - 1));
                                            }
                                        }
                                    });
                                }
                            } else {
                                LogUtils.log("WS_BABY.SnsUserUI.8");
                                BaseServiceUtils.removeEndView(CleanCircleAllUtils.this.mMyManager);
                            }
                        } else {
                            BaseServiceUtils.removeEndView(CleanCircleAllUtils.this.mMyManager);
                        }
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY.SnsUserUI.10");
                        CleanCircleAllUtils.this.initAlbumUI();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void initSnsCommentDetailUI() {
        LogUtils.log("WS_BABY.SnsCommentDetailUI");
        try {
            new PerformClickUtils2().check(this.autoService, desc_text_time_id, executeSpeedSetting + executeSpeed, 100, 300, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                    LogUtils.log("WS_BABY.SnsCommentDetailUI.1");
                    if (CleanCircleAllUtils.this.model.getCleanCircleType() == 1) {
                        LogUtils.log("WS_BABY.SnsCommentDetailUI.2");
                        AccessibilityNodeInfo rootInActiveWindow = CleanCircleAllUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.desc_text_time_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                            String charSequence = findAccessibilityNodeInfosByViewId.get(0).getText().toString();
                            if (DateUtils.isSatisfactory(CleanCircleAllUtils.this.startTime, CleanCircleAllUtils.this.endTime, charSequence)) {
                                LogUtils.log("WS_BABY.SnsCommentDetailUI.4");
                                CleanCircleAllUtils.this.exeTextTask();
                            } else if (DateUtils.endJump(CleanCircleAllUtils.this.startTime, charSequence)) {
                                LogUtils.log("WS_BABY.SnsCommentDetailUI.6");
                                BaseServiceUtils.removeEndView(CleanCircleAllUtils.this.mMyManager);
                            } else {
                                LogUtils.log("WS_BABY.SnsCommentDetailUI.5");
                                PerformClickUtils.performBack(CleanCircleAllUtils.this.autoService);
                                CleanCircleAllUtils.access$308(CleanCircleAllUtils.this);
                                CleanCircleAllUtils.this.initSnsUserUIForTime();
                            }
                        }
                    } else {
                        LogUtils.log("WS_BABY.SnsCommentDetailUI.3");
                        CleanCircleAllUtils.this.exeTextTask();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSnsGalleryUI() {
        LogUtils.log("WS_BABY.SnsGalleryUI");
        try {
            new PerformClickUtils2().check(this.autoService, desc_img_time_id, 100, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    LogUtils.log("WS_BABY.SnsGalleryUI.2");
                    if (CleanCircleAllUtils.this.model.getCleanCircleType() == 1) {
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanCircleAllUtils.this.autoService, BaseServiceUtils.desc_img_time_id);
                        if (findViewIdList == null || findViewIdList.size() <= 0) {
                            boolean unused = CleanCircleAllUtils.this.isExeTasking = true;
                            PerformClickUtils.performBack(CleanCircleAllUtils.this.autoService);
                            CleanCircleAllUtils.access$308(CleanCircleAllUtils.this);
                            CleanCircleAllUtils.this.initSnsUserUIForTime();
                            return;
                        }
                        LogUtils.log("WS_BABY.SnsGalleryUI.3");
                        String charSequence = findViewIdList.get(0).getText().toString();
                        if (DateUtils.isSatisfactory(CleanCircleAllUtils.this.startTime, CleanCircleAllUtils.this.endTime, charSequence)) {
                            LogUtils.log("WS_BABY.SnsGalleryUI.4");
                            CleanCircleAllUtils.this.exeImgTask();
                        } else if (DateUtils.endJump(CleanCircleAllUtils.this.startTime, charSequence)) {
                            LogUtils.log("WS_BABY.SnsGalleryUI.5");
                            boolean unused2 = CleanCircleAllUtils.this.isExeTasking = false;
                            BaseServiceUtils.removeEndView(CleanCircleAllUtils.this.mMyManager);
                        } else {
                            LogUtils.log("WS_BABY.SnsGalleryUI.6");
                            boolean unused3 = CleanCircleAllUtils.this.isExeTasking = true;
                            PerformClickUtils.performBack(CleanCircleAllUtils.this.autoService);
                            CleanCircleAllUtils.access$308(CleanCircleAllUtils.this);
                            CleanCircleAllUtils.this.initSnsUserUIForTime();
                        }
                    } else {
                        LogUtils.log("WS_BABY.SnsGalleryUI.8");
                        CleanCircleAllUtils.this.exeImgTask();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSnsUserUIForTime() {
        LogUtils.log("WS_BABY.SnsUserUI");
        if (!this.isFindExecuteStartIndex) {
            LogUtils.log("WS_BABY.SnsUserUI.0");
            try {
                Thread.sleep(2000);
            } catch (Exception unused) {
            }
            executeStartIndexSnsUserUI();
            return;
        }
        LogUtils.log("WS_BABY.SnsUserUI.18");
        new PerformClickUtils2().checkNodeId(this.autoService, list_circle_layout_id, executeSpeedSetting + executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                CleanCircleAllUtils.this.executeSnsUserUI();
            }
        });
    }

    public void initAlbumUI() {
        LogUtils.log("WS_BABY.AlbumUI");
        new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "我的朋友圈", "前往朋友圈", executeSpeedSetting + executeSpeed, 50, 600, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                if (i == 0) {
                    try {
                        PerformClickUtils.findTextAndClick(CleanCircleAllUtils.this.autoService, "我的朋友圈");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (i == 1) {
                    try {
                        PerformClickUtils.findTextAndClick(CleanCircleAllUtils.this.autoService, "前往朋友圈");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                CleanCircleAllUtils.this.sleep(200);
                if (CleanCircleAllUtils.this.model.getCleanCircleType() == 0) {
                    CleanCircleAllUtils.this.initSnsUserUIForAll();
                } else {
                    CleanCircleAllUtils.this.initSnsUserUIForTime();
                }
            }
        });
    }

    public void executeSnsUserUI() {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2;
        try {
            LogUtils.log("WS_BABY.SnsUserUI.0");
            AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(list_circle_layout_id);
                if (findAccessibilityNodeInfosByViewId3 != null && findAccessibilityNodeInfosByViewId3.size() > 0) {
                    findAccessibilityNodeInfosByViewId3.remove(0);
                }
                LogUtils.log("WS_BABY.SnsUserUI.startIndex=" + this.startIndex + ",itemList.size()=" + findAccessibilityNodeInfosByViewId3.size());
                if (this.startIndex < findAccessibilityNodeInfosByViewId3.size()) {
                    sleep(SocializeConstants.CANCLE_RESULTCODE);
                    AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId3.get(this.startIndex);
                    if (accessibilityNodeInfo != null) {
                        LogUtils.log("WS_BABY.SnsUserUI.1");
                        final List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId4 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(list_layout_only_text_id);
                        final List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId5 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(list_circle_img_video_id);
                        final List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId6 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(list_layout_child_url);
                        if (findAccessibilityNodeInfosByViewId4 != null && findAccessibilityNodeInfosByViewId4.size() > 0) {
                            LogUtils.log("WS_BABY.SnsUserUI.2");
                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId4.get(findAccessibilityNodeInfosByViewId4.size() - 1));
                            new PerformClickUtils2().check(this.autoService, desc_text_time_id, executeSpeed + executeSpeedSetting, 100, 600, (PerformClickUtils2.OnCheckListener5) new PerformClickUtils2.OnCheckListener5() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    CleanCircleAllUtils.this.initSnsCommentDetailUI();
                                }

                                public void execute(int i) {
                                    if (i == 10 || i == 20) {
                                        System.out.println("WS_BABY_NNNNNNN");
                                        PerformClickUtils.performClick((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId4.get(findAccessibilityNodeInfosByViewId4.size() - 1));
                                    }
                                }
                            });
                        } else if (findAccessibilityNodeInfosByViewId5 != null && findAccessibilityNodeInfosByViewId5.size() > 0) {
                            LogUtils.log("WS_BABY.SnsUserUI.3");
                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId5.get(findAccessibilityNodeInfosByViewId5.size() - 1));
                            new PerformClickUtils2().check(this.autoService, desc_img_time_id, executeSpeed + executeSpeedSetting, 100, 600, (PerformClickUtils2.OnCheckListener5) new PerformClickUtils2.OnCheckListener5() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    CleanCircleAllUtils.this.initSnsGalleryUI();
                                }

                                public void execute(int i) {
                                    if (i == 10 || i == 20) {
                                        System.out.println("WS_BABY_NNNNNNN");
                                        PerformClickUtils.performClick((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId5.get(findAccessibilityNodeInfosByViewId5.size() - 1));
                                    }
                                }
                            });
                        } else if (findAccessibilityNodeInfosByViewId6 == null || findAccessibilityNodeInfosByViewId6.size() <= 0) {
                            LogUtils.log("WS_BABY.SnsUserUI.55555555");
                            if (rootInActiveWindow != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(list_circle_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                findAccessibilityNodeInfosByViewId.get(0).performAction(4096);
                                sleep(MyApplication.SCROLL_SPEED);
                                this.startIndex = 0;
                                LogUtils.log("WS_BABY.SnsUserUI.6");
                                AccessibilityNodeInfo rootInActiveWindow2 = this.autoService.getRootInActiveWindow();
                                if (rootInActiveWindow2 != null && (findAccessibilityNodeInfosByViewId2 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(list_layout_child_id)) != null && findAccessibilityNodeInfosByViewId2.size() > 0) {
                                    if (findAccessibilityNodeInfosByViewId2.get(0).getChild(1) != null) {
                                        if ("拍照分享".equals(findAccessibilityNodeInfosByViewId2.get(0).getChild(1).getContentDescription() + "")) {
                                            LogUtils.log("WS_BABY.SnsUserUI.7");
                                            this.startIndex++;
                                        }
                                    }
                                    if (findAccessibilityNodeInfosByViewId2 != null && !findAccessibilityNodeInfosByViewId2.isEmpty()) {
                                        LogUtils.log("WS_BABY.SnsUserUI.8");
                                        AccessibilityNodeInfo accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId2.get(1);
                                        if (accessibilityNodeInfo2 != null) {
                                            accessibilityNodeInfo2.performAction(16);
                                            accessibilityNodeInfo2.getParent().performAction(16);
                                            this.startIndex++;
                                        }
                                    }
                                }
                            }
                        } else {
                            LogUtils.log("WS_BABY.SnsUserUI.4");
                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId6.get(findAccessibilityNodeInfosByViewId6.size() - 1));
                            new PerformClickUtils2().check(this.autoService, desc_text_time_id, executeSpeedSetting + executeSpeed, 100, 600, (PerformClickUtils2.OnCheckListener5) new PerformClickUtils2.OnCheckListener5() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    CleanCircleAllUtils.this.initSnsCommentDetailUI();
                                }

                                public void execute(int i) {
                                    if (i == 10 || i == 20) {
                                        System.out.println("WS_BABY_NNNNNNN");
                                        PerformClickUtils.performClick((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId6.get(findAccessibilityNodeInfosByViewId6.size() - 1));
                                    }
                                }
                            });
                        }
                    }
                } else if (this.startIndex >= findAccessibilityNodeInfosByViewId3.size()) {
                    this.isScrollBottom = PerformClickUtils.findNodeIsExistById((AccessibilityService) this.autoService, bottom_line);
                    if (this.isScrollBottom) {
                        LogUtils.log("WS_BABY.SnsUserUI.9");
                        this.isCanEnd = true;
                    }
                    LogUtils.log("WS_BABY.SnsUserUI.10");
                    if (!this.isCanEnd) {
                        if (findAccessibilityNodeInfosByViewId3.size() != 0) {
                            if (rootInActiveWindow != null) {
                                LogUtils.log("WS_BABY.SnsUserUI.12");
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId7 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(list_circle_id);
                                if (findAccessibilityNodeInfosByViewId7 != null && findAccessibilityNodeInfosByViewId7.size() > 0 && MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY.SnsUserUI.13");
                                    findAccessibilityNodeInfosByViewId7.get(0).performAction(4096);
                                    this.startIndex = 1;
                                    LogUtils.log("WS_BABY.SnsUserUI.14");
                                    new PerformClickUtils2().checkNilString(this.autoService, "正在加载", (int) SocializeConstants.CANCLE_RESULTCODE, (int) SocializeConstants.CANCLE_RESULTCODE, 303, (PerformClickUtils2.OnCheckListener5) new PerformClickUtils2.OnCheckListener5() {
                                        public void find(int i) {
                                            new PerformClickUtils2().checkNodeId(CleanCircleAllUtils.this.autoService, BaseServiceUtils.list_circle_layout_id, BaseServiceUtils.executeSpeed, 100, 50, new PerformClickUtils2.OnCheckListener() {
                                                public void nuFind() {
                                                }

                                                public void find(int i) {
                                                    CleanCircleAllUtils.this.executeSnsUserUI();
                                                }
                                            });
                                        }

                                        public void execute(int i) {
                                            if (i > 3) {
                                                MyWindowManager myWindowManager = CleanCircleAllUtils.this.mMyManager;
                                                BaseServiceUtils.updateBottomText(myWindowManager, "已为您清理了 " + CleanCircleAllUtils.this.exeNum + " 条朋友圈。\n检测到加载失败，请手动上拉刷新！倒计时（" + (303 - i) + "）秒");
                                                if ((i == 4 || i == 9) && Build.VERSION.SDK_INT >= 24) {
                                                    LogUtils.log("WS_BABY_DATE..........1");
                                                    PerformClickUtils.upPull();
                                                }
                                            }
                                        }

                                        public void nuFind() {
                                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                                public void run() {
                                                    BaseServiceUtils.removeEndView(CleanCircleAllUtils.this.mMyManager);
                                                    CleanCircleAllUtils.this.mMyManager.toastToUserError("检测到一直正在加载，请确定网络是否可用后，在重试！");
                                                }
                                            });
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                    }
                    LogUtils.log("WS_BABY.SnsUserUI.11");
                    removeEndView(this.mMyManager);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeStartIndexSnsUserUI() {
        String findViewIdAndGetText;
        try {
            LogUtils.log("WS_BABY_DATE_0");
            String findViewIdAndGetText2 = PerformClickUtils.findViewIdAndGetText(this.autoService, line_year_big_id);
            if (findViewIdAndGetText2 != null && !"".equals(findViewIdAndGetText2)) {
                if (!findViewIdAndGetText2.contains("年")) {
                    findViewIdAndGetText2 = findViewIdAndGetText2 + "年";
                }
                this.currentYear = findViewIdAndGetText2;
            }
            LogUtils.log("WS_BABY_DATE_1_" + this.currentYear);
            if ((this.currentYear == null || "".equals(this.currentYear)) && (findViewIdAndGetText = PerformClickUtils.findViewIdAndGetText(this.autoService, line_year_small_id)) != null && !"".equals(findViewIdAndGetText)) {
                if (!findViewIdAndGetText.contains("年")) {
                    findViewIdAndGetText = findViewIdAndGetText + "年";
                }
                this.currentYear = findViewIdAndGetText;
                LogUtils.log("WS_BABY_DATE_2_" + this.currentYear);
            }
            if (this.currentYear == null || "".equals(this.currentYear)) {
                this.currentYear = DateUtils.nowYear();
            }
            String findViewIdAndGetText3 = PerformClickUtils.findViewIdAndGetText(this.autoService, line_month_id);
            if (findViewIdAndGetText3 != null && !"".equals(findViewIdAndGetText3)) {
                if (!findViewIdAndGetText3.contains("月")) {
                    findViewIdAndGetText3 = findViewIdAndGetText3 + "月";
                }
                this.currentMonth = findViewIdAndGetText3;
                LogUtils.log("WS_BABY_DATE_3_" + this.currentMonth);
            }
            String findViewIdAndGetText4 = PerformClickUtils.findViewIdAndGetText(this.autoService, line_day_id);
            LogUtils.log("WS_BABY_DATE_4_" + findViewIdAndGetText4);
            if (findViewIdAndGetText4 != null && !"".equals(findViewIdAndGetText4)) {
                if (!"今天".equals(findViewIdAndGetText4)) {
                    if (!"今日".equals(findViewIdAndGetText4)) {
                        if (!"昨天".equals(findViewIdAndGetText4)) {
                            if (!"昨日".equals(findViewIdAndGetText4)) {
                                if (!findViewIdAndGetText4.contains("日")) {
                                    findViewIdAndGetText4 = findViewIdAndGetText4 + "日";
                                }
                                this.currentDay = findViewIdAndGetText4;
                                LogUtils.log("WS_BABY_DATE_7_" + this.currentMonth + "_" + this.currentDay);
                            }
                        }
                        String str = DateUtils.get_yestoday();
                        this.currentMonth = str.split("#")[0];
                        this.currentDay = str.split("#")[1];
                        LogUtils.log("WS_BABY_DATE_6_" + this.currentMonth + "_" + this.currentDay);
                    }
                }
                String convertDate2String = DateUtils.convertDate2String(new Date(), "MM月#dd日");
                this.currentMonth = convertDate2String.split("#")[0];
                this.currentDay = convertDate2String.split("#")[1];
                LogUtils.log("WS_BABY_DATE_5_" + this.currentMonth + "_" + this.currentDay);
            }
            String str2 = this.currentYear + "" + this.currentMonth + "" + this.currentDay;
            LogUtils.log("WS_BABY_DATE_SET0." + str2);
            LogUtils.log("WS_BABY_DATE_SET1." + this.currentYear + "" + this.currentMonth + "" + this.currentDay);
            StringBuilder sb = new StringBuilder();
            sb.append("WS_BABY_DATE_SET2.");
            sb.append(this.endTime);
            LogUtils.log(sb.toString());
            boolean isScroll = DateUtils.isScroll(str2, this.endTime);
            LogUtils.log("WS_BABY_DATE" + isScroll);
            if (isScroll) {
                PerformClickUtils.scroll(this.autoService, list_circle_id);
                new PerformClickUtils2().checkNilString(this.autoService, "正在加载", (int) SocializeConstants.CANCLE_RESULTCODE, (int) SocializeConstants.CANCLE_RESULTCODE, 303, (PerformClickUtils2.OnCheckListener5) new PerformClickUtils2.OnCheckListener5() {
                    public void find(int i) {
                        MyWindowManager myWindowManager = CleanCircleAllUtils.this.mMyManager;
                        BaseServiceUtils.updateBottomText(myWindowManager, "已为您清理了 " + CleanCircleAllUtils.this.exeNum + " 条朋友圈。\n正在自动清理朋友圈，请不要操作微信。");
                        boolean unused = CleanCircleAllUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistById((AccessibilityService) CleanCircleAllUtils.this.autoService, BaseServiceUtils.bottom_line);
                        if (!CleanCircleAllUtils.this.isScrollBottom || CleanCircleAllUtils.this.scrollCount <= 0) {
                            CleanCircleAllUtils.access$708(CleanCircleAllUtils.this);
                            CleanCircleAllUtils.this.executeStartIndexSnsUserUI();
                            return;
                        }
                        BaseServiceUtils.removeEndView(CleanCircleAllUtils.this.mMyManager);
                    }

                    public void execute(int i) {
                        if (i > 3) {
                            MyWindowManager myWindowManager = CleanCircleAllUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager, "已为您清理了 " + CleanCircleAllUtils.this.exeNum + " 条朋友圈。\n检测到加载失败，请手动上拉刷新！倒计时（" + (303 - i) + "）秒");
                            if ((i == 4 || i == 9) && Build.VERSION.SDK_INT >= 24) {
                                LogUtils.log("WS_BABY_DATE..........1");
                                PerformClickUtils.upPull();
                            }
                        }
                    }

                    public void nuFind() {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                BaseServiceUtils.removeEndView(CleanCircleAllUtils.this.mMyManager);
                                CleanCircleAllUtils.this.mMyManager.toastToUserError("检测到一直正在加载，请确定网络是否可用后，在重试！");
                            }
                        });
                    }
                });
                return;
            }
            this.isFindExecuteStartIndex = true;
            executeSnsUserUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exeTextTask() {
        try {
            LogUtils.log("WS_BABY_exeTextTask_0");
            new PerformClickUtils2().check(this.autoService, desc_delete_id, 50, 50, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanCircleAllUtils.this.autoService, BaseServiceUtils.desc_delete_id);
                    if (findViewIdList != null && findViewIdList.size() > 0) {
                        PerformClickUtils.performClick(findViewIdList.get(0));
                        new PerformClickUtils2().check(CleanCircleAllUtils.this.autoService, BaseServiceUtils.dialog_ok_id, 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanCircleAllUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                if (findViewIdList != null && findViewIdList.size() > 0) {
                                    findViewIdList.get(0).performAction(16);
                                }
                                new PerformClickUtils2().checkNilString(CleanCircleAllUtils.this.autoService, "正在删除", (BaseServiceUtils.executeSpeedSetting / 10) + 600, 100, 300, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        LogUtils.log("WS_BABY_正在删除文本_0000000000000");
                                        CleanCircleAllUtils.access$508(CleanCircleAllUtils.this);
                                        CleanCircleAllUtils.this.updateBottomText();
                                        CleanCircleAllUtils.this.sleep(200);
                                        if (CleanCircleAllUtils.this.model.getCleanCircleType() == 0) {
                                            CleanCircleAllUtils.this.initSnsUserUIForAll();
                                        } else {
                                            CleanCircleAllUtils.this.initSnsUserUIForTime();
                                        }
                                    }
                                });
                            }
                        });
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exeImgTask() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, desc_right_down_id, 100, 100, 200, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findViewIdAndClick(CleanCircleAllUtils.this.autoService, BaseServiceUtils.desc_right_down_id);
                    new PerformClickUtils2().checkNodeId(CleanCircleAllUtils.this.autoService, BaseServiceUtils.desc_delete_id, BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 200, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            PerformClickUtils.findViewIdAndClick(CleanCircleAllUtils.this.autoService, BaseServiceUtils.desc_delete_id);
                            new PerformClickUtils2().check(CleanCircleAllUtils.this.autoService, BaseServiceUtils.dialog_ok_id, 100, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    PerformClickUtils.findViewIdAndClick(CleanCircleAllUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                    LogUtils.log("WS_BABY_正在删除图片_00000000000");
                                    CleanCircleAllUtils.access$508(CleanCircleAllUtils.this);
                                    CleanCircleAllUtils.this.updateBottomText();
                                    new PerformClickUtils2().checkNilString(CleanCircleAllUtils.this.autoService, "正在删除", (BaseServiceUtils.executeSpeedSetting / 10) + 600, 100, 300, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            LogUtils.log("WS_BABY_正在删除图片_0");
                                            new PerformClickUtils2().checkNodeAllIds(CleanCircleAllUtils.this.autoService, BaseServiceUtils.desc_img_time_id, BaseServiceUtils.right_more_id, 50, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                public void nuFind() {
                                                }

                                                public void find(int i) {
                                                    LogUtils.log("WS_BABY_到视频预页");
                                                    PerformClickUtils.performBack(CleanCircleAllUtils.this.autoService);
                                                    LogUtils.log("WS_BABY_到列表页");
                                                    if (CleanCircleAllUtils.this.model.getCleanCircleType() == 0) {
                                                        CleanCircleAllUtils.this.sleep(600);
                                                        CleanCircleAllUtils.this.initSnsUserUIForAll();
                                                        return;
                                                    }
                                                    CleanCircleAllUtils.this.sleep(600);
                                                    CleanCircleAllUtils.this.initSnsUserUIForTime();
                                                }
                                            });
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

    public void executeMain() {
        try {
            new PerformClickUtils2().checkString(this.autoService, "我", 100, 50, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findTextAndClick(CleanCircleAllUtils.this.autoService, "我");
                    new PerformClickUtils2().checkString(CleanCircleAllUtils.this.autoService, "相册", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 50, 600, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(CleanCircleAllUtils.this.autoService, "相册");
                            CleanCircleAllUtils.this.initAlbumUI();
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
        setMiddleText(myWindowManager, "清理朋友圈", "清理了" + this.exeNum + "条");
    }

    public void endViewShow() {
        if (this.model.getCleanCircleType() == 0) {
            showBottomView(this.mMyManager, "正在清理朋友圈\n请您不要操作微信");
        } else {
            MyWindowManager myWindowManager = this.mMyManager;
            showBottomView(myWindowManager, "正在清理朋友圈\n[" + this.startTime + "-" + this.endTime + "]\n请您不要操作微信");
        }
        new Thread(new Runnable() {
            public void run() {
                try {
                    CleanCircleAllUtils.this.executeMain();
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

    public void updateBottomText() {
        MyWindowManager myWindowManager = this.mMyManager;
        updateBottomText(myWindowManager, "已为您清理了 " + this.exeNum + " 条朋友圈。\n正在自动清理朋友圈，请不要操作微信。");
    }
}
