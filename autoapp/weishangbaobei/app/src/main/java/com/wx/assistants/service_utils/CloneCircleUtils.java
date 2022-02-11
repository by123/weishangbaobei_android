package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
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
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.fileutil.FileUtils;
import com.yalantis.ucrop.view.CropImageView;
import com.youth.banner.BannerConfig;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class CloneCircleUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static CloneCircleUtils instance;
    /* access modifiers changed from: private */
    public int backCount = 25;
    /* access modifiers changed from: private */
    public int deleteModel = 0;
    /* access modifiers changed from: private */
    public String endTime;
    /* access modifiers changed from: private */
    public int firstListSize = 0;
    /* access modifiers changed from: private */
    public List<String> groupsList = new ArrayList();
    /* access modifiers changed from: private */
    public boolean isFastSpeed = false;
    /* access modifiers changed from: private */
    public boolean isFirstDelete = true;
    private boolean isNextIng = false;
    /* access modifiers changed from: private */
    public boolean isOnlyText = false;
    /* access modifiers changed from: private */
    public boolean isSavedCompleted = false;
    /* access modifiers changed from: private */
    public boolean isSavedVideo = false;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    /* access modifiers changed from: private */
    public int jumpTime = 100;
    /* access modifiers changed from: private */
    public int lastListSize = 0;
    private String lastName = "";
    /* access modifiers changed from: private */
    public OperationParameterModel model;
    /* access modifiers changed from: private */
    public int nowScrollNum = 0;
    /* access modifiers changed from: private */
    public String nowTime;
    /* access modifiers changed from: private */
    public int num = 0;
    /* access modifiers changed from: private */
    public int realCloneCount = 0;
    /* access modifiers changed from: private */
    public int recordExecuteIndex = 0;
    /* access modifiers changed from: private */
    public List<String> recordTextList = new ArrayList();
    /* access modifiers changed from: private */
    public int scrollPage = 0;
    /* access modifiers changed from: private */
    public int selectGroupNum = 0;
    private String selectGroups = "";
    /* access modifiers changed from: private */
    public String selectModel = "公开";
    private int selectTagNum = 0;
    private String selectTags = "";
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startIndexView = 0;
    /* access modifiers changed from: private */
    public String startTime;
    private List<String> tagsList = new ArrayList();
    /* access modifiers changed from: private */
    public String text = "";
    /* access modifiers changed from: private */
    public String wxCode = "";

    interface OnBackDescPageListener {
        void find();
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
    }

    static /* synthetic */ int access$1008(CloneCircleUtils cloneCircleUtils) {
        int i = cloneCircleUtils.realCloneCount;
        cloneCircleUtils.realCloneCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$1410(CloneCircleUtils cloneCircleUtils) {
        int i = cloneCircleUtils.backCount;
        cloneCircleUtils.backCount = i - 1;
        return i;
    }

    static /* synthetic */ int access$2408(CloneCircleUtils cloneCircleUtils) {
        int i = cloneCircleUtils.selectGroupNum;
        cloneCircleUtils.selectGroupNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$308(CloneCircleUtils cloneCircleUtils) {
        int i = cloneCircleUtils.scrollPage;
        cloneCircleUtils.scrollPage = i + 1;
        return i;
    }

    static /* synthetic */ int access$808(CloneCircleUtils cloneCircleUtils) {
        int i = cloneCircleUtils.startIndex;
        cloneCircleUtils.startIndex = i + 1;
        return i;
    }

    private CloneCircleUtils() {
    }

    public static CloneCircleUtils getInstance() {
        instance = new CloneCircleUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        System.out.println("WS_BABY_initData");
        this.model = operationParameterModel;
        this.startIndex = 0;
        this.startIndexView = 0;
        this.num = 0;
        this.nowScrollNum = 0;
        this.text = "";
        this.wxCode = "";
        this.nowTime = "";
        this.jumpTime = 200;
        this.isNextIng = false;
        this.realCloneCount = 0;
        this.firstListSize = 0;
        this.lastListSize = 0;
        this.scrollPage = 0;
        this.recordTextList = new ArrayList();
        this.recordExecuteIndex = 0;
        this.isFirstDelete = true;
        this.isOnlyText = false;
        this.isSavedVideo = false;
        this.isSavedCompleted = false;
        this.isScrollBottom = false;
        this.deleteModel = operationParameterModel.getDeleteMode();
        this.startTime = operationParameterModel.getStartDate();
        this.endTime = operationParameterModel.getEndDate();
        this.isFastSpeed = operationParameterModel.isFastSpeed();
        this.selectModel = operationParameterModel.getSelectCircleModel();
        this.selectTags = operationParameterModel.getSelectCircleTags();
        this.selectGroups = operationParameterModel.getSelectCircleGroups();
        this.groupsList = new ArrayList();
        this.tagsList = new ArrayList();
        this.selectGroupNum = 0;
        this.selectTagNum = 0;
        this.lastName = "";
        if (this.selectTags != null && !"".equals(this.selectTags)) {
            if (this.selectTags.contains(";")) {
                String[] split = this.selectTags.split(";");
                for (String add : split) {
                    this.tagsList.add(add);
                }
            } else {
                this.tagsList.add(this.selectTags);
            }
        }
        if (this.selectGroups != null && !"".equals(this.selectGroups)) {
            if (this.selectGroups.contains(";")) {
                String[] split2 = this.selectGroups.split(";");
                for (String add2 : split2) {
                    this.groupsList.add(add2);
                }
            } else {
                this.groupsList.add(this.selectGroups);
            }
        }
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initSnsGalleryUI() {
        try {
            System.out.println("WS_BABY_initSnsGalleryUI.0");
            new PerformClickUtils2().checkNodeId(this.autoService, "android:id/text1", executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    System.out.println("WS_BABY_initSnsGalleryUI.1");
                    String findViewIdAndGetText = PerformClickUtils.findViewIdAndGetText(CloneCircleUtils.this.autoService, "android:id/text1");
                    String findViewIdAndGetText2 = PerformClickUtils.findViewIdAndGetText(CloneCircleUtils.this.autoService, "android:id/text2");
                    if (DateUtils.isSatisfactory(CloneCircleUtils.this.startTime, CloneCircleUtils.this.endTime, findViewIdAndGetText)) {
                        PrintStream printStream = System.out;
                        printStream.println("WS_BABY_initSnsGalleryUI.2" + CloneCircleUtils.this.recordTextList);
                        try {
                            String findViewIdAndGetText3 = PerformClickUtils.findViewIdAndGetText(CloneCircleUtils.this.autoService, BaseServiceUtils.desc_text_id);
                            PrintStream printStream2 = System.out;
                            printStream2.println("WS_BABY_initSnsGalleryUI.3" + findViewIdAndGetText + findViewIdAndGetText2 + findViewIdAndGetText3);
                            LogUtils.log("WS_BABY_initSnsGalleryUI.4.scrollPage = " + CloneCircleUtils.this.scrollPage + ",recordExecuteIndex = " + CloneCircleUtils.this.recordExecuteIndex + ",firstListSize = " + CloneCircleUtils.this.firstListSize);
                            if ((CloneCircleUtils.this.scrollPage == 1 && CloneCircleUtils.this.recordExecuteIndex <= CloneCircleUtils.this.firstListSize) || ((CloneCircleUtils.this.isScrollBottom && CloneCircleUtils.this.recordExecuteIndex <= CloneCircleUtils.this.lastListSize) || (CloneCircleUtils.this.scrollPage > 1 && CloneCircleUtils.this.recordExecuteIndex <= 3))) {
                                System.out.println("WS_BABY_initSnsGalleryUI.5");
                                List access$200 = CloneCircleUtils.this.recordTextList;
                                if (access$200.contains(findViewIdAndGetText + findViewIdAndGetText2 + findViewIdAndGetText3)) {
                                    LogUtils.log("WS_BABY.SnsGalleryUI.6");
                                    PerformClickUtils.performBack(CloneCircleUtils.this.autoService);
                                    int unused = CloneCircleUtils.this.startIndex = 0;
                                    int unused2 = CloneCircleUtils.this.num = 0;
                                    new PerformClickUtils2().checkNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.list_circle_layout_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            CloneCircleUtils.this.jumpExecuted(false);
                                        }
                                    });
                                    return;
                                }
                                System.out.println("WS_BABY_initSnsGalleryUI.7");
                                if (CloneCircleUtils.this.recordTextList.size() == 15) {
                                    CloneCircleUtils.this.recordTextList.remove(0);
                                }
                                List access$2002 = CloneCircleUtils.this.recordTextList;
                                access$2002.add(findViewIdAndGetText + findViewIdAndGetText2 + findViewIdAndGetText3);
                                CloneCircleUtils.this.executeImgClone();
                            } else if (CloneCircleUtils.this.recordTextList.size() > 0) {
                                if (((String) CloneCircleUtils.this.recordTextList.get(CloneCircleUtils.this.recordTextList.size() - 1)).equals(findViewIdAndGetText + findViewIdAndGetText2 + findViewIdAndGetText3)) {
                                    LogUtils.log("WS_BABY.SnsGalleryUI.8");
                                    PerformClickUtils.performBack(CloneCircleUtils.this.autoService);
                                    int unused3 = CloneCircleUtils.this.startIndex = 0;
                                    int unused4 = CloneCircleUtils.this.num = 0;
                                    new PerformClickUtils2().checkNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.list_circle_layout_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            CloneCircleUtils.this.jumpExecuted(false);
                                        }
                                    });
                                    return;
                                }
                                System.out.println("WS_BABY_initSnsGalleryUI.9");
                                if (CloneCircleUtils.this.recordTextList.size() == 15) {
                                    CloneCircleUtils.this.recordTextList.remove(0);
                                }
                                List access$2003 = CloneCircleUtils.this.recordTextList;
                                access$2003.add(findViewIdAndGetText + findViewIdAndGetText2 + findViewIdAndGetText3);
                                CloneCircleUtils.this.executeImgClone();
                            } else {
                                System.out.println("WS_BABY_initSnsGalleryUI.10");
                                if (CloneCircleUtils.this.recordTextList.size() == 15) {
                                    CloneCircleUtils.this.recordTextList.remove(0);
                                }
                                List access$2004 = CloneCircleUtils.this.recordTextList;
                                access$2004.add(findViewIdAndGetText + findViewIdAndGetText2 + findViewIdAndGetText3);
                                CloneCircleUtils.this.executeImgClone();
                            }
                        } catch (Exception unused5) {
                            System.out.println("WS_BABY_initSnsGalleryUI.222ERROR");
                            CloneCircleUtils.this.executeImgClone();
                        }
                    } else if (DateUtils.endJump(CloneCircleUtils.this.startTime, findViewIdAndGetText)) {
                        LogUtils.log("WS_BABY.SnsGalleryUI.11");
                        BaseServiceUtils.removeEndView(CloneCircleUtils.this.mMyManager);
                        BaseServiceUtils.getMainHandler().post(new Runnable() {
                            public void run() {
                                String str;
                                CloneCircleUtils.this.mMyManager.showMiddleView();
                                MyWindowManager myWindowManager = CloneCircleUtils.this.mMyManager;
                                if (CloneCircleUtils.this.realCloneCount == 0) {
                                    str = "当前时间小于您设置的起始时间，克隆结束。";
                                } else {
                                    str = "当前时间小于您设置的起始时间，克隆结束。已为您克隆 " + CloneCircleUtils.this.realCloneCount + " 条朋友圈。";
                                }
                                BaseServiceUtils.setMiddleText(myWindowManager, "克隆朋友圈结束", str);
                            }
                        });
                    } else {
                        LogUtils.log("WS_BABY.SnsGalleryUI.12");
                        PerformClickUtils.performBack(CloneCircleUtils.this.autoService);
                        int unused6 = CloneCircleUtils.this.startIndex = 0;
                        int unused7 = CloneCircleUtils.this.num = 0;
                        new PerformClickUtils2().checkNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.list_circle_layout_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                LogUtils.log("WS_BABY.SnsGalleryUI.13");
                                CloneCircleUtils.this.jumpExecuted(false);
                            }
                        });
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeImgClone() {
        new PerformClickUtils2().checkNodeId(this.autoService, desc_right_down_id, 10, 100, 20, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY.SnsGalleryUI");
                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CloneCircleUtils.this.autoService, BaseServiceUtils.desc_right_down_id);
                if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                    PerformClickUtils.performClick(findViewIdList.get(0));
                    CloneCircleUtils.this.initSnsCommentDetailUI(100);
                }
            }

            public void nuFind() {
                BaseServiceUtils.getMainHandler().post(new Runnable() {
                    public void run() {
                        BaseServiceUtils.removeEndView(CloneCircleUtils.this.mMyManager);
                        CloneCircleUtils.this.mMyManager.toastToUserError("不能克隆非好友的朋友圈！");
                    }
                });
            }
        });
    }

    public void cloneNext() {
        System.out.println("WS_BABY.cloneNext.0");
        this.isNextIng = true;
        new PerformClickUtils2().checkNodeId(this.autoService, friends_circle_head_img_id, (executeSpeedSetting / 8) + SocializeConstants.CANCLE_RESULTCODE, 200, 100, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                System.out.println("WS_BABY.cloneNext.1");
                PerformClickUtils.performBack(CloneCircleUtils.this.autoService);
                CloneCircleUtils.this.findSearchBtn();
            }

            public void nuFind() {
                System.out.println("WS_BABY.cloneNext.2");
            }
        });
    }

    public void findSearchBtn() {
        new PerformClickUtils2().check(this.autoService, contact_nav_search_viewgroup_id, executeSpeedSetting + 100, 50, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                AccessibilityNodeInfo rootInActiveWindow = CloneCircleUtils.this.autoService.getRootInActiveWindow();
                if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_viewgroup_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                    if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                        PerformClickUtils.findTextAndClickFirst(CloneCircleUtils.this.autoService, "搜索");
                    } else {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = findAccessibilityNodeInfosByViewId.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_img_id);
                        if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && findAccessibilityNodeInfosByViewId2.get(0) != null && MyApplication.enforceable) {
                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                        }
                    }
                    CloneCircleUtils.this.initFTSMainUI();
                }
            }
        });
    }

    public void initFTSMainUI() {
        try {
            LogUtils.log("WS_BABY.FTSMainUI.wxCode = " + this.wxCode);
            searchNickName(this.wxCode);
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_72");
            e.printStackTrace();
        }
    }

    public void searchNickName(final String str) {
        try {
            LogUtils.log("WS_BABY_searchNickName=" + str);
            if (str != null && !"".equals(str) && MyApplication.enforceable) {
                new PerformClickUtils2().checkNodeId(this.autoService, search_id, executeSpeedSetting + 50, 100, 200, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY_searchNickName.0");
                        PerformClickUtils.findViewIdAndClick(CloneCircleUtils.this.autoService, BaseServiceUtils.search_id);
                        CloneCircleUtils.this.sleep(350);
                        PerformClickUtils.inputText(CloneCircleUtils.this.autoService, str);
                        new PerformClickUtils2().checkNodeStringsHasSomeOne(CloneCircleUtils.this.autoService, "联系人", "最常使用", CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 30, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                LogUtils.log("WS_BABY_searchNickName.1");
                                new Thread(new Runnable() {
                                    public void run() {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                                        try {
                                            AccessibilityNodeInfo rootInActiveWindow = CloneCircleUtils.this.autoService.getRootInActiveWindow();
                                            if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_name_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                                boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(CloneCircleUtils.this.autoService, "联系人");
                                                boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(CloneCircleUtils.this.autoService, "最常使用");
                                                if ((findNodeIsExistByText || findNodeIsExistByText2) && MyApplication.enforceable) {
                                                    LogUtils.log("WS_BABY.initChattingUI");
                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                    CloneCircleUtils.this.initChattingUI();
                                                }
                                            }
                                        } catch (Exception unused) {
                                        }
                                    }
                                }).start();
                            }

                            public void nuFind() {
                                LogUtils.log("WS_BABY_searchNickName.3");
                            }
                        });
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY_searchNickName.2");
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initChattingUI() {
        try {
            new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeedSetting + 50, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findViewIdAndClick(CloneCircleUtils.this.autoService, BaseServiceUtils.right_more_id);
                    CloneCircleUtils.this.initSingleChatInfo();
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_73");
            e.printStackTrace();
        }
    }

    public void initSingleChatInfo() {
        try {
            new PerformClickUtils2().check(this.autoService, single_contact_nick_name_id, executeSpeedSetting + 50, 50, 60, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findViewIdAndClick(CloneCircleUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                    CloneCircleUtils.this.endViewShow();
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_74");
            e.printStackTrace();
        }
    }

    public void initAlbumPreviewUI() {
        try {
            if (this.isSavedVideo) {
                new PerformClickUtils2().checkNodeId(this.autoService, video_first_id, executeSpeedSetting + BannerConfig.DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        if (MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.AlbumPreviewUI.1");
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CloneCircleUtils.this.autoService, BaseServiceUtils.video_first_id);
                            if (findViewIdList == null || findViewIdList.size() <= 0) {
                                CloneCircleUtils.this.initAlbumPreviewUI();
                                return;
                            }
                            PerformClickUtils.performClick(findViewIdList.get(0));
                            if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                                new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "编辑视频", CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 8, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        PerformClickUtils.findViewIdAndClick(CloneCircleUtils.this.autoService, BaseServiceUtils.edit_text_id);
                                        new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "完成", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                            public void nuFind() {
                                            }

                                            public void find(int i) {
                                                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                                                CloneCircleUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                                                CloneCircleUtils.this.initSnsUploadUI();
                                            }
                                        });
                                    }

                                    public void nuFind() {
                                        PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                                        CloneCircleUtils.this.initSnsUploadUI();
                                    }
                                });
                            } else {
                                new PerformClickUtils2().checkNilString(CloneCircleUtils.this.autoService, "图片和视频", 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        CloneCircleUtils.this.initVideoMMRecordUI();
                                    }
                                });
                            }
                        }
                    }
                });
            } else {
                new PerformClickUtils2().checkNodeId(this.autoService, img_first_check_layout_id, executeSpeedSetting + BannerConfig.DURATION, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        if (MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.AlbumPreviewUI.2");
                            try {
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CloneCircleUtils.this.autoService, BaseServiceUtils.img_first_check_layout_id);
                                if (findViewIdList != null && findViewIdList.size() > 0) {
                                    int access$900 = CloneCircleUtils.this.num;
                                    while (true) {
                                        access$900--;
                                        if (access$900 <= -1) {
                                            break;
                                        } else if (!MyApplication.enforceable) {
                                            break;
                                        } else {
                                            CloneCircleUtils.this.sleep(5);
                                            PerformClickUtils.performClick(findViewIdList.get(access$900));
                                        }
                                    }
                                }
                            } catch (Exception unused) {
                            }
                            CloneCircleUtils.this.sleep(100);
                            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) CloneCircleUtils.this.autoService, BaseServiceUtils.complete_id);
                            if (findViewIdList2 == null || findViewIdList2.size() <= 0 || !findViewIdList2.get(0).isEnabled()) {
                                CloneCircleUtils.this.initAlbumPreviewUI();
                                return;
                            }
                            findViewIdList2.get(0).performAction(16);
                            CloneCircleUtils.this.initSnsUploadUI();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSnslineVideoActivity() {
        try {
            LogUtils.log("WS_BABY_SnsOnlineVideoActivity");
            new PerformClickUtils2().check(this.autoService, long_click_id, (executeSpeedSetting / 8) + this.jumpTime, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_SnsOnlineVideoActivity0");
                    PerformClickUtils.performLongClick(CloneCircleUtils.this.autoService.getRootInActiveWindow().findAccessibilityNodeInfosByViewId(BaseServiceUtils.long_click_id).get(0));
                    LogUtils.log("WS_BABY_SnsOnlineVideoActivity1");
                    new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "保存视频", CloneCircleUtils.this.jumpTime, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            LogUtils.log("WS_BABY_SnsOnlineVideoActivity2");
                            CloneCircleUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "保存视频");
                            LogUtils.log("WS_BABY_SnsOnlineVideoActivity3");
                            new PerformClickUtils2().checkNilNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 1200, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY_SnsOnlineVideoActivity4");
                                    new PerformClickUtils2().checkNilNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            LogUtils.log("WS_BABY_SnsOnlineVideoActivity44");
                                            new PerformClickUtils2().checkNilNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                                public void find(int i) {
                                                    LogUtils.log("WS_BABY_SnsOnlineVideoActivity444");
                                                    new PerformClickUtils2().checkNilNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                                        public void find(int i) {
                                                            LogUtils.log("WS_BABY_SnsOnlineVideoActivity444");
                                                            new PerformClickUtils2().checkNilNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                                                public void find(int i) {
                                                                    LogUtils.log("WS_BABY_SnsOnlineVideoActivity444");
                                                                    if (MyApplication.enforceable) {
                                                                        boolean unused = CloneCircleUtils.this.isSavedVideo = true;
                                                                        PerformClickUtils.performBack(CloneCircleUtils.this.autoService);
                                                                        CloneCircleUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                                                        boolean unused2 = CloneCircleUtils.this.isOnlyText = false;
                                                                        int unused3 = CloneCircleUtils.this.backCount = 25;
                                                                        CloneCircleUtils.this.backExecute();
                                                                    }
                                                                }

                                                                public void nuFind() {
                                                                    LogUtils.log("WS_BABY_SnsOnlineVideoActivity5");
                                                                }
                                                            });
                                                        }

                                                        public void nuFind() {
                                                            LogUtils.log("WS_BABY_SnsOnlineVideoActivity5");
                                                        }
                                                    });
                                                }

                                                public void nuFind() {
                                                    LogUtils.log("WS_BABY_SnsOnlineVideoActivity5");
                                                }
                                            });
                                        }

                                        public void nuFind() {
                                            LogUtils.log("WS_BABY_SnsOnlineVideoActivity5");
                                        }
                                    });
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY_SnsOnlineVideoActivity5");
                                }
                            });
                        }

                        public void nuFind() {
                            LogUtils.log("WS_BABY_SnsOnlineVideoActivity6");
                        }
                    });
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY_SnsOnlineVideoActivity7");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initMMNewPhotoEditUI() {
        try {
            LogUtils.log("WS_BABY_MMNewPhotoEditUI");
            new PerformClickUtils2().checkString(this.autoService, "完成", (executeSpeedSetting / 10) + 100, 50, 400, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    LogUtils.log("WS_BABY_MMNewPhotoEditUI_has_完成");
                    PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                    new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "保存图片", 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "保存图片");
                            new PerformClickUtils2().checkNilString(CloneCircleUtils.this.autoService, "正在处理中", (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 50, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    new Thread(new Runnable() {
                                        public void run() {
                                            LogUtils.log("WS_BABY_MMNewPhotoEditUI_has_完成2");
                                            CloneCircleUtils.this.backDescPage(new OnBackDescPageListener() {
                                                public void find() {
                                                    CloneCircleUtils.this.initSnsCommentDetailUI(0);
                                                }
                                            });
                                        }
                                    }).start();
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY_MMNewPhotoEditUI_has_完成3");
                                    CloneCircleUtils.this.backDescPage(new OnBackDescPageListener() {
                                        public void find() {
                                            CloneCircleUtils.this.initSnsCommentDetailUI(0);
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

    public void initSnsCommentDetailUI(int i) {
        try {
            new PerformClickUtils2().checkNodeIdsHasSomeOne3(this.autoService, circle_list_layout_img_id, circle_list_layout_video_id, list_layout_only_text_id, (executeSpeedSetting / 10) + i, 100, 200, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    CloneCircleUtils.this.executeDetail();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeDetail() {
        try {
            if (this.isSavedCompleted || !MyApplication.enforceable) {
                this.isOnlyText = false;
                this.backCount = 25;
                backExecute();
                return;
            }
            new PerformClickUtils2().checkNodeIdsHasSomeOne3(this.autoService, list_layout_only_text_id, circle_list_layout_img_id, desc_video_relativeLayout_id, 0, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    AccessibilityNodeInfo rootInActiveWindow = CloneCircleUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_layout_only_text_id);
                        final List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.circle_list_layout_img_id);
                        final List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.desc_video_relativeLayout_id);
                        if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0 || findAccessibilityNodeInfosByViewId2.get(0) == null || findAccessibilityNodeInfosByViewId2.get(0).getChildCount() <= 0) {
                            if (findAccessibilityNodeInfosByViewId3 == null || findAccessibilityNodeInfosByViewId3.size() <= 0 || findAccessibilityNodeInfosByViewId3.get(0) == null) {
                                if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                                    int unused = CloneCircleUtils.this.num = 1;
                                    new PerformClickUtils2().checkNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.list_layout_only_text_id, 50, 200, 100, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            String unused = CloneCircleUtils.this.nowTime = PerformClickUtils.findViewIdAndGetText(CloneCircleUtils.this.autoService, BaseServiceUtils.desc_text_time_id);
                                            if (DateUtils.isSatisfactory(CloneCircleUtils.this.startTime, CloneCircleUtils.this.endTime, CloneCircleUtils.this.nowTime)) {
                                                String unused2 = CloneCircleUtils.this.text = PerformClickUtils.findViewIdAndGetText(CloneCircleUtils.this.autoService, BaseServiceUtils.list_layout_only_text_id);
                                                PrintStream printStream = System.out;
                                                printStream.println("WS_BABY_UPLOAD_ONLEYTEXT_" + CloneCircleUtils.this.nowTime + CloneCircleUtils.this.text);
                                                List access$200 = CloneCircleUtils.this.recordTextList;
                                                if (access$200.contains(CloneCircleUtils.this.nowTime + CloneCircleUtils.this.text)) {
                                                    LogUtils.log("WS_BABY.SnsGalleryUI.12");
                                                    PerformClickUtils.performBack(CloneCircleUtils.this.autoService);
                                                    int unused3 = CloneCircleUtils.this.startIndex = 0;
                                                    int unused4 = CloneCircleUtils.this.num = 0;
                                                    new PerformClickUtils2().checkNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.list_circle_layout_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                        public void nuFind() {
                                                        }

                                                        public void find(int i) {
                                                            CloneCircleUtils.this.jumpExecuted(false);
                                                        }
                                                    });
                                                    return;
                                                }
                                                if (CloneCircleUtils.this.recordTextList.size() == 15) {
                                                    CloneCircleUtils.this.recordTextList.remove(0);
                                                }
                                                List access$2002 = CloneCircleUtils.this.recordTextList;
                                                access$2002.add(CloneCircleUtils.this.nowTime + CloneCircleUtils.this.text);
                                                PerformClickUtils.performBack(CloneCircleUtils.this.autoService);
                                                boolean unused5 = CloneCircleUtils.this.isSavedCompleted = true;
                                                boolean unused6 = CloneCircleUtils.this.isOnlyText = true;
                                                int unused7 = CloneCircleUtils.this.backCount = 25;
                                                CloneCircleUtils.this.backExecute();
                                            } else if (DateUtils.endJump(CloneCircleUtils.this.startTime, CloneCircleUtils.this.nowTime)) {
                                                BaseServiceUtils.removeEndView(CloneCircleUtils.this.mMyManager);
                                                BaseServiceUtils.getMainHandler().post(new Runnable() {
                                                    public void run() {
                                                        String str;
                                                        CloneCircleUtils.this.mMyManager.showMiddleView();
                                                        MyWindowManager myWindowManager = CloneCircleUtils.this.mMyManager;
                                                        if (CloneCircleUtils.this.realCloneCount == 0) {
                                                            str = "当前时间小于您设置的起始时间，克隆结束。";
                                                        } else {
                                                            str = "当前时间小于您设置的起始时间，克隆结束。已为您克隆 " + CloneCircleUtils.this.realCloneCount + " 条朋友圈。";
                                                        }
                                                        BaseServiceUtils.setMiddleText(myWindowManager, "克隆朋友圈结束", str);
                                                    }
                                                });
                                            } else {
                                                PerformClickUtils.performBack(CloneCircleUtils.this.autoService);
                                                new PerformClickUtils2().checkNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.list_circle_layout_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                    public void nuFind() {
                                                    }

                                                    public void find(int i) {
                                                        CloneCircleUtils.this.jumpExecuted(false);
                                                    }
                                                });
                                            }
                                        }
                                    });
                                }
                            } else if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                                new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "详情", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        int unused = CloneCircleUtils.this.num = 1;
                                        if (CloneCircleUtils.this.startIndex < CloneCircleUtils.this.num && MyApplication.enforceable) {
                                            boolean unused2 = CloneCircleUtils.this.isSavedCompleted = true;
                                            LogUtils.log("WS_BABY.SnsCommentDetailUI.22");
                                            PerformClickUtils.performClick((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId3.get(0));
                                            CloneCircleUtils.this.initSnslineVideoActivity();
                                        }
                                    }
                                });
                            } else if (CloneCircleUtils.this.text == null || "".equals(CloneCircleUtils.this.text)) {
                                new PerformClickUtils2().checkNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.list_layout_only_text_id, 10, 100, 200, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        LogUtils.log("WS_BABY_SnsSingleTextViewUI0");
                                        String unused = CloneCircleUtils.this.text = PerformClickUtils.findViewIdAndGetText(CloneCircleUtils.this.autoService, BaseServiceUtils.list_layout_only_text_id);
                                        new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "详情", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                            public void nuFind() {
                                            }

                                            public void find(int i) {
                                                int unused = CloneCircleUtils.this.num = 1;
                                                if (CloneCircleUtils.this.startIndex < CloneCircleUtils.this.num && MyApplication.enforceable) {
                                                    boolean unused2 = CloneCircleUtils.this.isSavedCompleted = true;
                                                    LogUtils.log("WS_BABY.SnsCommentDetailUI.22");
                                                    PerformClickUtils.performClick((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId3.get(0));
                                                    CloneCircleUtils.this.initSnslineVideoActivity();
                                                }
                                            }
                                        });
                                    }
                                });
                            } else {
                                new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "详情", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        int unused = CloneCircleUtils.this.num = 1;
                                        if (CloneCircleUtils.this.startIndex < CloneCircleUtils.this.num && MyApplication.enforceable) {
                                            boolean unused2 = CloneCircleUtils.this.isSavedCompleted = true;
                                            LogUtils.log("WS_BABY.SnsCommentDetailUI.22");
                                            PerformClickUtils.performClick((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId3.get(0));
                                            CloneCircleUtils.this.initSnslineVideoActivity();
                                        }
                                    }
                                });
                            }
                        } else if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                            if (findAccessibilityNodeInfosByViewId2 != null && !findAccessibilityNodeInfosByViewId2.isEmpty() && MyApplication.enforceable) {
                                boolean unused2 = CloneCircleUtils.this.isSavedVideo = false;
                                int unused3 = CloneCircleUtils.this.num = findAccessibilityNodeInfosByViewId2.get(0).getChildCount();
                                if (CloneCircleUtils.this.startIndex < CloneCircleUtils.this.num) {
                                    if (CloneCircleUtils.this.startIndex == CloneCircleUtils.this.num - 1) {
                                        boolean unused4 = CloneCircleUtils.this.isSavedCompleted = true;
                                    }
                                    PerformClickUtils.performLongClick(findAccessibilityNodeInfosByViewId2.get(0).getChild(CloneCircleUtils.this.startIndex));
                                    CloneCircleUtils.access$808(CloneCircleUtils.this);
                                    new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "编辑", 10, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "编辑");
                                            if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                                                CloneCircleUtils.this.initMMNewPhotoEditUI();
                                            } else {
                                                CloneCircleUtils.this.initPhotoMMRecordUI();
                                            }
                                        }
                                    });
                                }
                            }
                        } else if (CloneCircleUtils.this.text == null || "".equals(CloneCircleUtils.this.text)) {
                            new PerformClickUtils2().checkNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.list_layout_only_text_id, 10, 100, 200, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    LogUtils.log("WS_BABY_SnsSingleTextViewUI0");
                                    String unused = CloneCircleUtils.this.text = PerformClickUtils.findViewIdAndGetText(CloneCircleUtils.this.autoService, BaseServiceUtils.list_layout_only_text_id);
                                    CloneCircleUtils.this.sleep(100);
                                    if (findAccessibilityNodeInfosByViewId2 != null && !findAccessibilityNodeInfosByViewId2.isEmpty() && MyApplication.enforceable) {
                                        boolean unused2 = CloneCircleUtils.this.isSavedVideo = false;
                                        int unused3 = CloneCircleUtils.this.num = ((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId2.get(0)).getChildCount();
                                        if (CloneCircleUtils.this.startIndex < CloneCircleUtils.this.num) {
                                            if (CloneCircleUtils.this.startIndex == CloneCircleUtils.this.num - 1) {
                                                boolean unused4 = CloneCircleUtils.this.isSavedCompleted = true;
                                            }
                                            PerformClickUtils.performLongClick(((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId2.get(0)).getChild(CloneCircleUtils.this.startIndex));
                                            CloneCircleUtils.access$808(CloneCircleUtils.this);
                                            new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "编辑", 10, 50, 50, new PerformClickUtils2.OnCheckListener() {
                                                public void nuFind() {
                                                }

                                                public void find(int i) {
                                                    PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "编辑");
                                                    if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                                                        CloneCircleUtils.this.initMMNewPhotoEditUI();
                                                    } else {
                                                        CloneCircleUtils.this.initPhotoMMRecordUI();
                                                    }
                                                }
                                            });
                                        }
                                    }
                                }
                            });
                        } else if (findAccessibilityNodeInfosByViewId2 != null && !findAccessibilityNodeInfosByViewId2.isEmpty() && MyApplication.enforceable) {
                            boolean unused5 = CloneCircleUtils.this.isSavedVideo = false;
                            int unused6 = CloneCircleUtils.this.num = findAccessibilityNodeInfosByViewId2.get(0).getChildCount();
                            if (CloneCircleUtils.this.startIndex < CloneCircleUtils.this.num) {
                                if (CloneCircleUtils.this.startIndex == CloneCircleUtils.this.num - 1) {
                                    boolean unused7 = CloneCircleUtils.this.isSavedCompleted = true;
                                }
                                PerformClickUtils.performLongClick(findAccessibilityNodeInfosByViewId2.get(0).getChild(CloneCircleUtils.this.startIndex));
                                CloneCircleUtils.access$808(CloneCircleUtils.this);
                                new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "编辑", 10, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "编辑");
                                        if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                                            CloneCircleUtils.this.initMMNewPhotoEditUI();
                                        } else {
                                            CloneCircleUtils.this.initPhotoMMRecordUI();
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backExecute() {
        if (this.backCount >= 0 && MyApplication.enforceable) {
            new Thread(new Runnable() {
                /* JADX WARNING: Code restructure failed: missing block: B:16:0x009c, code lost:
                    if (r2.booleanValue() != false) goto L_0x009e;
                 */
                /* JADX WARNING: Removed duplicated region for block: B:27:0x00f7 A[Catch:{ Exception -> 0x0164 }] */
                /* JADX WARNING: Removed duplicated region for block: B:28:0x0153 A[Catch:{ Exception -> 0x0164 }] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r17 = this;
                        r0 = r17
                        com.wx.assistants.service_utils.CloneCircleUtils r1 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        r2 = 100
                        r1.sleep(r2)     // Catch:{ Exception -> 0x0164 }
                        java.lang.String r1 = "WS_BABY.SnsCommentDetailUI.2"
                        com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x0164 }
                        boolean r1 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0164 }
                        if (r1 == 0) goto L_0x0164
                        java.lang.String r1 = "WS_BABY.SnsCommentDetailUI.4"
                        com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x0164 }
                        android.os.Handler r1 = com.wx.assistants.service_utils.BaseServiceUtils.getMainHandler()     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils$14$1 r2 = new com.wx.assistants.service_utils.CloneCircleUtils$14$1     // Catch:{ Exception -> 0x0164 }
                        r2.<init>()     // Catch:{ Exception -> 0x0164 }
                        r1.post(r2)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r1 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0164 }
                        java.lang.String r2 = "返回"
                        boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x0164 }
                        java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r2 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service.AutoService r2 = r2.autoService     // Catch:{ Exception -> 0x0164 }
                        java.lang.String r3 = "拍照分享"
                        boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x0164 }
                        java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r3 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service.AutoService r3 = r3.autoService     // Catch:{ Exception -> 0x0164 }
                        java.lang.String r4 = "查看消息"
                        boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r3, r4)     // Catch:{ Exception -> 0x0164 }
                        java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r4 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service.AutoService r4 = r4.autoService     // Catch:{ Exception -> 0x0164 }
                        java.lang.String r5 = "通讯录"
                        boolean r4 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r4, r5)     // Catch:{ Exception -> 0x0164 }
                        java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r5 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service.AutoService r5 = r5.autoService     // Catch:{ Exception -> 0x0164 }
                        java.lang.String r6 = "发现"
                        boolean r5 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r5, r6)     // Catch:{ Exception -> 0x0164 }
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r6 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service.AutoService r6 = r6.autoService     // Catch:{ Exception -> 0x0164 }
                        java.lang.String r7 = com.wx.assistants.service_utils.BaseServiceUtils.home_tab_layout_id     // Catch:{ Exception -> 0x0164 }
                        boolean r6 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r6, (java.lang.String) r7)     // Catch:{ Exception -> 0x0164 }
                        java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r7 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        boolean r7 = r7.isOnlyText     // Catch:{ Exception -> 0x0164 }
                        if (r7 == 0) goto L_0x0092
                        boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x0164 }
                        if (r1 == 0) goto L_0x00e5
                        boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x0164 }
                        if (r1 == 0) goto L_0x00e5
                        boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x0164 }
                        if (r1 != 0) goto L_0x00e5
                        goto L_0x009e
                    L_0x0092:
                        boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x0164 }
                        if (r1 == 0) goto L_0x00e5
                        boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x0164 }
                        if (r1 == 0) goto L_0x00e5
                    L_0x009e:
                        com.wx.assistants.service_utils.CloneCircleUtils r1 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        boolean r1 = r1.isOnlyText     // Catch:{ Exception -> 0x0164 }
                        if (r1 == 0) goto L_0x00c1
                        java.lang.String r1 = "WS_BABY.SnsCommentDetailUI.9898"
                        com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r1 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0164 }
                        java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.right_more_id     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.utils.PerformClickUtils.performLongClick(r1, r2)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r1 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        r2 = 0
                        boolean unused = r1.isSavedCompleted = r2     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r1 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        r1.initSnsUploadUI()     // Catch:{ Exception -> 0x0164 }
                        goto L_0x0164
                    L_0x00c1:
                        com.wx.assistants.service_utils.CloneCircleUtils r1 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0164 }
                        java.lang.String r2 = "拍照分享"
                        com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.utils.PerformClickUtils2 r3 = new com.wx.assistants.utils.PerformClickUtils2     // Catch:{ Exception -> 0x0164 }
                        r3.<init>()     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r1 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service.AutoService r4 = r1.autoService     // Catch:{ Exception -> 0x0164 }
                        java.lang.String r5 = "从相册选择"
                        r6 = 10
                        r7 = 50
                        r8 = 200(0xc8, float:2.8E-43)
                        com.wx.assistants.service_utils.CloneCircleUtils$14$2 r9 = new com.wx.assistants.service_utils.CloneCircleUtils$14$2     // Catch:{ Exception -> 0x0164 }
                        r9.<init>()     // Catch:{ Exception -> 0x0164 }
                        r3.checkString(r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0164 }
                        goto L_0x0164
                    L_0x00e5:
                        boolean r1 = r4.booleanValue()     // Catch:{ Exception -> 0x0164 }
                        if (r1 == 0) goto L_0x0153
                        boolean r1 = r5.booleanValue()     // Catch:{ Exception -> 0x0164 }
                        if (r1 == 0) goto L_0x0153
                        boolean r1 = r6.booleanValue()     // Catch:{ Exception -> 0x0164 }
                        if (r1 == 0) goto L_0x0153
                        java.lang.String r1 = "WS_BABY.SnsCommentDetailUI.7"
                        com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r1 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0164 }
                        java.lang.String r2 = "发现"
                        com.wx.assistants.utils.PerformClickUtils.findTabTextAndClick(r1, r2)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r1 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0164 }
                        java.lang.String r2 = "发现"
                        com.wx.assistants.utils.PerformClickUtils.findTabTextAndClick(r1, r2)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r1 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        r2 = 50
                        r1.sleep(r2)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r1 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0164 }
                        java.lang.String r2 = "发现"
                        com.wx.assistants.utils.PerformClickUtils.findTabTextAndClick(r1, r2)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.utils.PerformClickUtils2 r3 = new com.wx.assistants.utils.PerformClickUtils2     // Catch:{ Exception -> 0x0164 }
                        r3.<init>()     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r1 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service.AutoService r4 = r1.autoService     // Catch:{ Exception -> 0x0164 }
                        java.lang.String r5 = "朋友圈"
                        r6 = 10
                        r7 = 50
                        r8 = 20
                        com.wx.assistants.service_utils.CloneCircleUtils$14$3 r9 = new com.wx.assistants.service_utils.CloneCircleUtils$14$3     // Catch:{ Exception -> 0x0164 }
                        r9.<init>()     // Catch:{ Exception -> 0x0164 }
                        r3.checkString(r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.utils.PerformClickUtils2 r10 = new com.wx.assistants.utils.PerformClickUtils2     // Catch:{ Exception -> 0x0164 }
                        r10.<init>()     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r1 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service.AutoService r11 = r1.autoService     // Catch:{ Exception -> 0x0164 }
                        java.lang.String r12 = "拍照分享"
                        r13 = 10
                        r14 = 50
                        r15 = 200(0xc8, float:2.8E-43)
                        com.wx.assistants.service_utils.CloneCircleUtils$14$4 r1 = new com.wx.assistants.service_utils.CloneCircleUtils$14$4     // Catch:{ Exception -> 0x0164 }
                        r1.<init>()     // Catch:{ Exception -> 0x0164 }
                        r16 = r1
                        r10.checkString(r11, r12, r13, r14, r15, r16)     // Catch:{ Exception -> 0x0164 }
                        goto L_0x0164
                    L_0x0153:
                        com.wx.assistants.service_utils.CloneCircleUtils r1 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils.access$1410(r1)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r1 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.utils.PerformClickUtils.performBackNoDeep(r1)     // Catch:{ Exception -> 0x0164 }
                        com.wx.assistants.service_utils.CloneCircleUtils r1 = com.wx.assistants.service_utils.CloneCircleUtils.this     // Catch:{ Exception -> 0x0164 }
                        r1.backExecute()     // Catch:{ Exception -> 0x0164 }
                    L_0x0164:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.CloneCircleUtils.AnonymousClass14.run():void");
                }
            }).start();
        }
    }

    public void initSnsUploadUI() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, input_send_edit_text_id, (executeSpeedSetting / 8) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    if (!CloneCircleUtils.this.isSavedVideo || !MyApplication.enforceable) {
                        Context conText = MyApplication.getConText();
                        SPUtils.put(conText, "delete_img", "0#" + CloneCircleUtils.this.num);
                        SPUtils.put(MyApplication.getConText(), "delete_model", Integer.valueOf(CloneCircleUtils.this.deleteModel));
                        FileUtils.saveForward(0, CloneCircleUtils.this.num);
                    } else {
                        SPUtils.put(MyApplication.getConText(), "delete_img", "1#1");
                        SPUtils.put(MyApplication.getConText(), "delete_model", Integer.valueOf(CloneCircleUtils.this.deleteModel));
                        FileUtils.saveForward(1, 1);
                    }
                    if (CloneCircleUtils.this.text != null && !"".equals(CloneCircleUtils.this.text)) {
                        CloneCircleUtils.this.sleep(10);
                        PerformClickUtils.findViewByIdAndPasteContent(CloneCircleUtils.this.autoService, BaseServiceUtils.input_send_edit_text_id, CloneCircleUtils.this.text);
                    }
                    CloneCircleUtils.this.selectFriends();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initPhotoMMRecordUI() {
        try {
            new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "下一步", "完成", (executeSpeedSetting / 10) + 10, 50, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    int i2 = i;
                    if (i2 == 0) {
                        PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "下一步");
                        new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "保存图片", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "保存图片");
                                CloneCircleUtils.this.backDescPage(new OnBackDescPageListener() {
                                    public void find() {
                                        CloneCircleUtils.this.initSnsCommentDetailUI(0);
                                    }
                                });
                            }
                        });
                    } else if (i2 != 1) {
                    } else {
                        if (BaseServiceUtils.wxVersionCode >= 1640) {
                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                            new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "保存图片", 10, 20, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "保存图片");
                                    new PerformClickUtils2().checkNilString(CloneCircleUtils.this.autoService, "完成", 10, 20, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            CloneCircleUtils.this.backDescPage(new OnBackDescPageListener() {
                                                public void find() {
                                                    CloneCircleUtils.this.initSnsCommentDetailUI(0);
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                            return;
                        }
                        new PerformClickUtils2().check(CloneCircleUtils.this.autoService, BaseServiceUtils.circle_img_crop_id, 0, 100, 10, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                PerformClickUtils.findViewIdAndClick(CloneCircleUtils.this.autoService, BaseServiceUtils.circle_img_crop_id);
                                new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "确定", CloneCircleUtils.this.isFastSpeed ? 1 : SocializeConstants.CANCLE_RESULTCODE, 5, 200, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "确定");
                                        new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "完成", 10, 20, 50, new PerformClickUtils2.OnCheckListener() {
                                            public void nuFind() {
                                            }

                                            public void find(int i) {
                                                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                                                new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "保存图片", 10, 20, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new PerformClickUtils2.OnCheckListener() {
                                                    public void nuFind() {
                                                    }

                                                    public void find(int i) {
                                                        PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "保存图片");
                                                        new PerformClickUtils2().checkNilString(CloneCircleUtils.this.autoService, "完成", 10, 20, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                                            public void nuFind() {
                                                            }

                                                            public void find(int i) {
                                                                CloneCircleUtils.this.backDescPage(new OnBackDescPageListener() {
                                                                    public void find() {
                                                                        CloneCircleUtils.this.initSnsCommentDetailUI(0);
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
                            }
                        });
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initVideoMMRecordUI() {
        try {
            new PerformClickUtils2().checkString(this.autoService, "完成", (executeSpeedSetting / 10) + 10, 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    System.out.println("WS_BABY_initVideoMMRecordUI.2");
                    new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "取消", 100, 100, 2, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            System.out.println("WS_BABY_initVideoMMRecordUI.3");
                            LogUtils.log("WS_BABY_0");
                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_1");
                            CloneCircleUtils.this.sleep(600);
                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_2");
                            new PerformClickUtils2().checkNilString(CloneCircleUtils.this.autoService, "正在处理", (int) SocializeConstants.CANCLE_RESULTCODE, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    CloneCircleUtils.this.initSnsUploadUI();
                                }
                            });
                        }

                        public void nuFind() {
                            System.out.println("WS_BABY_initVideoMMRecordUI.4");
                            LogUtils.log("WS_BABY_00");
                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_11");
                            new PerformClickUtils2().checkNilString(CloneCircleUtils.this.autoService, "正在处理", (int) SocializeConstants.CANCLE_RESULTCODE, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    CloneCircleUtils.this.initSnsUploadUI();
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
            new PerformClickUtils2().checkCloneCircle(this.autoService, "朋友圈", "发消息", album_head_img, list_layout_child_id, (executeSpeedSetting / 10) + 100, 50, this.isNextIng ? 30 : 6, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    System.out.println("isExecuteMain.0");
                    if (i == 0) {
                        System.out.println("isExecuteMain.1");
                        if (CloneCircleUtils.this.wxCode == null || "".equals(CloneCircleUtils.this.wxCode)) {
                            CloneCircleUtils.this.saveFriendInfo();
                        }
                        CloneCircleUtils.this.executeClickCircle();
                    } else if (i == 1) {
                        System.out.println("isExecuteMain.2");
                        PerformClickUtils.findViewIdAndClick(CloneCircleUtils.this.autoService, BaseServiceUtils.back_contact_id);
                        new PerformClickUtils2().checkStringsAll(CloneCircleUtils.this.autoService, "朋友圈", "发消息", 100, 50, 10, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                System.out.println("isExecuteMain.3");
                                if (CloneCircleUtils.this.wxCode == null || "".equals(CloneCircleUtils.this.wxCode)) {
                                    CloneCircleUtils.this.saveFriendInfo();
                                }
                                CloneCircleUtils.this.executeClickCircle();
                            }

                            public void nuFind() {
                                System.out.println("isExecuteMain.4");
                            }
                        });
                    }
                }

                public void nuFind() {
                    System.out.println("isExecuteMain.5");
                    new PerformClickUtils2().checkNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.album_head_img, 0, 100, 2, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            System.out.println("isExecuteMain.6");
                            new PerformClickUtils2().checkNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.friends_circle_head_img_id, 0, 100, 2, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    System.out.println("isExecuteMain.7");
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        public void run() {
                                            BaseServiceUtils.removeEndView(CloneCircleUtils.this.mMyManager);
                                            CloneCircleUtils.this.mMyManager.toastToUserError("请前往好友详情页");
                                            CloneCircleUtils.this.initData(CloneCircleUtils.this.model);
                                        }
                                    });
                                }

                                public void nuFind() {
                                    new PerformClickUtils2().checkNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.back_contact_id, 0, 100, 2, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            System.out.println("isExecuteMain.8");
                                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                                public void run() {
                                                    BaseServiceUtils.removeEndView(CloneCircleUtils.this.mMyManager);
                                                    CloneCircleUtils.this.mMyManager.toastToUserError("您的好友，貌似没有朋友圈动态哦");
                                                    CloneCircleUtils.this.initData(CloneCircleUtils.this.model);
                                                }
                                            });
                                        }

                                        public void nuFind() {
                                            System.out.println("isExecuteMain.10");
                                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                                public void run() {
                                                    BaseServiceUtils.removeEndView(CloneCircleUtils.this.mMyManager);
                                                    CloneCircleUtils.this.mMyManager.toastToUserError("请前往好友详情页");
                                                    CloneCircleUtils.this.initData(CloneCircleUtils.this.model);
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }

                        public void nuFind() {
                            System.out.println("isExecuteMain.9");
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    BaseServiceUtils.removeEndView(CloneCircleUtils.this.mMyManager);
                                    CloneCircleUtils.this.mMyManager.toastToUserError("请前往好友详情页");
                                    CloneCircleUtils.this.initData(CloneCircleUtils.this.model);
                                }
                            });
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("WS_BABY_SnsUserUI.Exception");
        }
    }

    public void executeClickCircle() {
        new PerformClickUtils2().checkString(this.autoService, "朋友圈", (executeSpeedSetting / 5) + 100, 50, 10, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                LogUtils.log("WS_BABY_executeClickCircle.1");
                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "朋友圈");
                new PerformClickUtils2().checkNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.album_head_img, 100, 50, 200, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        CloneCircleUtils.this.saveFriendInfo2();
                        LogUtils.log("WS_BABY_executeClickCircle.2");
                        new PerformClickUtils2().checkNilString(CloneCircleUtils.this.autoService, "正在加载", (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 80, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                LogUtils.log("WS_BABY_executeClickCircle.3");
                                new PerformClickUtils2().checkNodeAllIds(CloneCircleUtils.this.autoService, BaseServiceUtils.list_circle_layout_id, BaseServiceUtils.album_head_img, 100, 100, 3, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        LogUtils.log("WS_BABY_executeClickCircle.4");
                                        CloneCircleUtils.this.jumpExecuted(true);
                                    }

                                    public void nuFind() {
                                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                                            public void run() {
                                                BaseServiceUtils.removeEndView(CloneCircleUtils.this.mMyManager);
                                                CloneCircleUtils.this.mMyManager.toastToUserError("您的好友，貌似没有朋友圈动态哦");
                                                CloneCircleUtils.this.initData(CloneCircleUtils.this.model);
                                            }
                                        });
                                    }
                                });
                            }

                            public void nuFind() {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    public void run() {
                                        BaseServiceUtils.removeEndView(CloneCircleUtils.this.mMyManager);
                                        CloneCircleUtils.this.mMyManager.toastToUserError("检测到一直正在加载，请确定网络是否可用后，在重试！");
                                        CloneCircleUtils.this.initData(CloneCircleUtils.this.model);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    public void scrollOperation() {
        if (this.nowScrollNum < this.scrollPage) {
            this.nowScrollNum++;
            PerformClickUtils.scroll(this.autoService, list_circle_id);
            sleep(MyApplication.SCROLL_SPEED * 5);
            new PerformClickUtils2().checkNilString(this.autoService, "正在加载", (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, (int) SocializeConstants.CANCLE_RESULTCODE, 300, (PerformClickUtils2.OnCheckListener5) new PerformClickUtils2.OnCheckListener5() {
                public void find(int i) {
                    CloneCircleUtils.this.scrollOperation();
                    MyWindowManager myWindowManager = CloneCircleUtils.this.mMyManager;
                    BaseServiceUtils.updateBottomText(myWindowManager, "已为您克隆 " + CloneCircleUtils.this.realCloneCount + " 条朋友圈。\n正在自动克隆朋友圈，请不要操作微信。");
                }

                public void execute(int i) {
                    if (i > 1) {
                        MyWindowManager myWindowManager = CloneCircleUtils.this.mMyManager;
                        BaseServiceUtils.updateBottomText(myWindowManager, "正在滚动第" + CloneCircleUtils.this.nowScrollNum + "页数据。\n检测到加载失败，请手动上拉刷新！倒计时（" + (300 - i) + "）秒");
                        if ((i == 2 || i == 7) && Build.VERSION.SDK_INT >= 24) {
                            LogUtils.log("WS_BABY_DATE..........1");
                            PerformClickUtils.upPull();
                        }
                    }
                }

                public void nuFind() {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            BaseServiceUtils.removeEndView(CloneCircleUtils.this.mMyManager);
                            CloneCircleUtils.this.mMyManager.toastToUserError("检测到一直正在加载，请确定网络是否可用后，在重试！");
                            CloneCircleUtils.this.initData(CloneCircleUtils.this.model);
                        }
                    });
                }
            });
            return;
        }
        this.nowScrollNum = 0;
        joinAlbumListPage();
    }

    public void jumpExecuted(boolean z) {
        if (z) {
            System.out.println("WS_BABY_jumpExecuted.1");
            scrollOperation();
            return;
        }
        System.out.println("WS_BABY_jumpExecuted.2");
        joinAlbumListPage();
    }

    public void backDescPage(final OnBackDescPageListener onBackDescPageListener) {
        System.out.println("WS_BABY_backDescPage.1");
        sleep(600);
        this.autoService.performGlobalAction(1);
        new PerformClickUtils2().checkString(this.autoService, "详情", 100, 100, 8, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                System.out.println("WS_BABY_backDescPage.2");
                if (onBackDescPageListener != null) {
                    onBackDescPageListener.find();
                }
            }

            public void nuFind() {
                System.out.println("WS_BABY_backDescPage.3");
                CloneCircleUtils.this.autoService.performGlobalAction(1);
                CloneCircleUtils.this.sleep(100);
                if (onBackDescPageListener != null) {
                    onBackDescPageListener.find();
                }
            }
        });
    }

    public void joinAlbumListPage() {
        if (MyApplication.enforceable) {
            System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.00 = " + this.recordExecuteIndex);
            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_circle_layout_id);
            System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.1 ");
            if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.16");
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        BaseServiceUtils.removeEndView(CloneCircleUtils.this.mMyManager);
                        CloneCircleUtils.this.mMyManager.toastToUserError("请前往好友详情页");
                        CloneCircleUtils.this.initData(CloneCircleUtils.this.model);
                    }
                });
                return;
            }
            if (this.scrollPage == 0) {
                this.firstListSize = findViewIdList.size();
                this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(this.autoService, "朋友仅展示最近");
                if (!this.isScrollBottom) {
                    this.isScrollBottom = PerformClickUtils.findNodeIsExistById((AccessibilityService) this.autoService, bottom_line);
                }
                if (PerformClickUtils.findNodeIsExistByText(this.autoService, "拍照分享")) {
                    getMainHandler().post(new Runnable() {
                        public void run() {
                            BaseServiceUtils.removeEndView(CloneCircleUtils.this.mMyManager);
                            CloneCircleUtils.this.mMyManager.toastToUserError("不能克隆自己的朋友圈");
                            CloneCircleUtils.this.initData(CloneCircleUtils.this.model);
                        }
                    });
                    return;
                }
            }
            if (this.isScrollBottom) {
                this.lastListSize = findViewIdList.size();
            }
            if (this.wxCode == null || "".equals(this.wxCode)) {
                this.wxCode = PerformClickUtils.findViewIdAndGetText(this.autoService, home_msg_list_name_id);
            }
            System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.2.size = " + findViewIdList);
            if (this.recordExecuteIndex < findViewIdList.size()) {
                System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.3.size = " + findViewIdList.size());
                AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(this.recordExecuteIndex);
                if (accessibilityNodeInfo != null) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(list_album_video_img);
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(list_layout_2_id);
                    System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.5");
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(list_layout_only_text_id);
                    if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0 || !MyApplication.enforceable) {
                        System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.13");
                        this.recordExecuteIndex++;
                        if (this.model.getCloneCircleType() == 4 || this.model.getCloneCircleType() == 1) {
                            System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.14");
                            if (findAccessibilityNodeInfosByViewId3 == null || findAccessibilityNodeInfosByViewId3.size() <= 0 || !MyApplication.enforceable) {
                                System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.16");
                                joinAlbumListPage();
                                return;
                            }
                            System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.15");
                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId3.get(0));
                            initSnsCommentDetailUI(100);
                            return;
                        }
                        System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.26");
                        joinAlbumListPage();
                        return;
                    }
                    System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.6");
                    if (this.model.getCloneCircleType() == 0 || this.model.getCloneCircleType() == 1 || ((this.model.getCloneCircleType() == 2 && (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() == 0)) || (this.model.getCloneCircleType() == 3 && findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0))) {
                        System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.7");
                        this.recordExecuteIndex++;
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId4 = findAccessibilityNodeInfosByViewId2.get(0).findAccessibilityNodeInfosByViewId(list_circle_img_video_id);
                        System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.8");
                        if (findAccessibilityNodeInfosByViewId4 == null || findAccessibilityNodeInfosByViewId4.size() <= 0) {
                            System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.10");
                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                        } else {
                            System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.9");
                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId4.get(0));
                        }
                        System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.11");
                        initSnsGalleryUI();
                        return;
                    }
                    System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.12");
                    this.recordExecuteIndex++;
                    joinAlbumListPage();
                    return;
                }
                System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.12");
                this.recordExecuteIndex++;
                joinAlbumListPage();
            } else if (this.recordExecuteIndex >= findViewIdList.size()) {
                if (this.isScrollBottom) {
                    getMainHandler().post(new Runnable() {
                        public void run() {
                            String str;
                            BaseServiceUtils.removeEndView(CloneCircleUtils.this.mMyManager);
                            CloneCircleUtils.this.mMyManager.showMiddleView();
                            MyWindowManager myWindowManager = CloneCircleUtils.this.mMyManager;
                            if (CloneCircleUtils.this.realCloneCount == 0) {
                                str = "已经检测到滚动底部了，克隆结束。";
                            } else {
                                str = "已经检测到滚动底部了，克隆结束。已为您克隆 " + CloneCircleUtils.this.realCloneCount + " 条朋友圈。";
                            }
                            BaseServiceUtils.setMiddleText(myWindowManager, "克隆朋友圈结束", str);
                        }
                    });
                }
                System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.14");
                List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_circle_id);
                if (findViewIdList2 != null && findViewIdList2.size() != 0) {
                    findViewIdList2.get(0).performAction(4096);
                    sleep(MyApplication.SCROLL_SPEED * 5);
                    new PerformClickUtils2().checkNilString(this.autoService, "正在加载", (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, (int) SocializeConstants.CANCLE_RESULTCODE, 300, (PerformClickUtils2.OnCheckListener5) new PerformClickUtils2.OnCheckListener5() {
                        public void find(int i) {
                            MyWindowManager myWindowManager = CloneCircleUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager, "已为您克隆 " + CloneCircleUtils.this.realCloneCount + " 条朋友圈。\n正在自动克隆朋友圈，请不要操作微信。");
                            boolean unused = CloneCircleUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(CloneCircleUtils.this.autoService, "朋友仅展示最近");
                            if (!CloneCircleUtils.this.isScrollBottom) {
                                boolean unused2 = CloneCircleUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistById((AccessibilityService) CloneCircleUtils.this.autoService, BaseServiceUtils.bottom_line);
                            }
                            PrintStream printStream = System.out;
                            printStream.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.15.isScrollBottom = " + CloneCircleUtils.this.isScrollBottom);
                            int unused3 = CloneCircleUtils.this.recordExecuteIndex = 1;
                            CloneCircleUtils.access$308(CloneCircleUtils.this);
                            CloneCircleUtils.this.joinAlbumListPage();
                        }

                        public void execute(int i) {
                            if (i > 1) {
                                MyWindowManager myWindowManager = CloneCircleUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "已为您克隆 " + CloneCircleUtils.this.realCloneCount + " 条朋友圈。\n检测到加载失败，请手动上拉刷新！倒计时（" + (300 - i) + "）秒");
                                if ((i == 2 || i == 7) && Build.VERSION.SDK_INT >= 24) {
                                    LogUtils.log("WS_BABY_DATE..........1");
                                    PerformClickUtils.upPull();
                                }
                            }
                        }

                        public void nuFind() {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    BaseServiceUtils.removeEndView(CloneCircleUtils.this.mMyManager);
                                    CloneCircleUtils.this.mMyManager.toastToUserError("检测到一直正在加载，请确定网络是否可用后，在重试！");
                                    CloneCircleUtils.this.initData(CloneCircleUtils.this.model);
                                }
                            });
                        }
                    });
                }
            }
        }
    }

    public void selectFriends() {
        if (this.selectModel == null || "".equals(this.selectModel) || "公开".equals(this.selectModel)) {
            sendCircle();
        } else if (this.realCloneCount <= 0 || "私密".equals(this.selectModel)) {
            new PerformClickUtils2().checkString(this.autoService, "谁可以看", executeSpeed + (executeSpeedSetting / 5), 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "谁可以看");
                    new PerformClickUtils2().checkStringsAll(CloneCircleUtils.this.autoService, "私密", "公开", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            if ("私密".equals(CloneCircleUtils.this.selectModel) || "公开".equals(CloneCircleUtils.this.selectModel)) {
                                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, CloneCircleUtils.this.selectModel);
                                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                                CloneCircleUtils.this.sendCircle();
                            } else if ("部分可见".equals(CloneCircleUtils.this.selectModel)) {
                                new PerformClickUtils2().checkNodeStringsHasSomeOne(CloneCircleUtils.this.autoService, "部分可见", "选中的朋友可见", BaseServiceUtils.executeSpeedSetting + BaseServiceUtils.executeSpeed, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        if (i == 0) {
                                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "部分可见");
                                        } else {
                                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "选中的朋友可见");
                                        }
                                        CloneCircleUtils.this.executeRealSelect();
                                    }
                                });
                            } else if ("不给谁看".equals(CloneCircleUtils.this.selectModel)) {
                                new PerformClickUtils2().checkNodeStringsHasSomeOne(CloneCircleUtils.this.autoService, "不给谁看", "选中的朋友不可见", BaseServiceUtils.executeSpeedSetting + BaseServiceUtils.executeSpeed, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        if (i == 0) {
                                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "不给谁看");
                                        } else {
                                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "选中的朋友不可见");
                                        }
                                        CloneCircleUtils.this.executeRealSelect();
                                    }
                                });
                            }
                        }
                    });
                }
            });
        } else {
            new PerformClickUtils2().checkString(this.autoService, "上次分组", 300, 100, 10, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "上次分组");
                    CloneCircleUtils.this.sleep(100);
                    CloneCircleUtils.this.sendCircle();
                }
            });
        }
    }

    public void executeRealSelect() {
        if (this.selectGroups == null || "".equals(this.selectGroups)) {
            if (this.selectTags != null && !"".equals(this.selectTags)) {
                executeRealSelectTags();
            }
        } else if (wxVersionCode >= 1420 && !"7.0.4".equals(wxVersionName)) {
            executeRealSelectGroups();
        } else if (this.selectTags == null || "".equals(this.selectTags)) {
            PerformClickUtils.performBack(this.autoService);
            sendCircle();
        } else {
            executeRealSelectTags();
        }
    }

    public void executeRealSelectGroups() {
        new PerformClickUtils2().checkString(this.autoService, "从群选择", executeSpeed + (executeSpeedSetting / 5), 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "从群选择");
                int unused = CloneCircleUtils.this.selectGroupNum = 0;
                CloneCircleUtils.this.initSelectGroupsName();
            }
        });
    }

    public void executeRealSelectTags() {
        if (this.selectTags == null || "".equals(this.selectTags)) {
            new PerformClickUtils2().checkString(this.autoService, "完成", executeSpeedSetting + executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                    CloneCircleUtils.this.sendCircle();
                }
            });
            return;
        }
        executeSelectTags();
    }

    public void executeSelectTags() {
        LogUtils.log("WS_BABY.MassSendSelectContactUI_4");
        new PerformClickUtils2().checkNodeId(this.autoService, filter_tags_name, (executeSpeedSetting / 5) + 200, 100, 300, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_START_SELECT_CONTACT");
                int unused = CloneCircleUtils.this.startIndexView = 0;
                CloneCircleUtils.this.whileExecuteTask();
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.MassSendSelectContactUI_5_nuFind");
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0112, code lost:
        if ("".equals(r0) == false) goto L_0x0116;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void whileExecuteTask() {
        /*
            r4 = this;
            com.wx.assistants.service.AutoService r0 = r4.autoService
            java.lang.String r1 = filter_tags_name
            java.util.List r0 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r0, (java.lang.String) r1)
            if (r0 == 0) goto L_0x014e
            int r1 = r0.size()
            if (r1 <= 0) goto L_0x014e
            boolean r1 = com.wx.assistants.application.MyApplication.enforceable
            if (r1 == 0) goto L_0x014e
            int r1 = r4.startIndexView
            int r2 = r0.size()
            if (r1 >= r2) goto L_0x0093
            int r1 = r4.startIndexView
            java.lang.Object r0 = r0.get(r1)
            android.view.accessibility.AccessibilityNodeInfo r0 = (android.view.accessibility.AccessibilityNodeInfo) r0
            java.lang.String r1 = ""
            if (r0 == 0) goto L_0x0043
            java.lang.CharSequence r2 = r0.getText()
            if (r2 == 0) goto L_0x0043
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.CharSequence r2 = r0.getText()
            r1.append(r2)
            java.lang.String r2 = ""
            r1.append(r2)
            java.lang.String r1 = r1.toString()
        L_0x0043:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "WS_BABY_excSelectTask.0.nowName = "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            com.wx.assistants.utils.LogUtils.log(r2)
            java.util.List<java.lang.String> r2 = r4.tagsList
            boolean r2 = r2.contains(r1)
            if (r2 == 0) goto L_0x007d
            int r2 = r4.startIndexView
            int r2 = r2 + 1
            r4.startIndexView = r2
            int r2 = r4.selectTagNum
            int r2 = r2 + 1
            r4.selectTagNum = r2
            r2 = 10
            r4.sleep(r2)
            com.wx.assistants.utils.PerformClickUtils.performClick(r0)
            java.util.List<java.lang.String> r0 = r4.tagsList
            r0.remove(r1)
            r4.whileExecuteTask()
            goto L_0x014e
        L_0x007d:
            java.lang.String r0 = "WS_BABY_excSelectTask.2"
            com.wx.assistants.utils.LogUtils.log(r0)
            int r0 = r4.startIndexView
            int r0 = r0 + 1
            r4.startIndexView = r0
            int r0 = r4.selectTagNum
            int r0 = r0 + 1
            r4.selectTagNum = r0
            r4.whileExecuteTask()
            goto L_0x014e
        L_0x0093:
            int r1 = r4.startIndexView
            int r0 = r0.size()
            if (r1 < r0) goto L_0x014e
            com.wx.assistants.service.AutoService r0 = r4.autoService
            android.view.accessibility.AccessibilityNodeInfo r0 = r0.getRootInActiveWindow()
            if (r0 == 0) goto L_0x014e
            boolean r1 = com.wx.assistants.application.MyApplication.enforceable
            if (r1 == 0) goto L_0x014e
            java.lang.String r1 = "WS_BABY_excSelectTask.10"
            com.wx.assistants.utils.LogUtils.log(r1)
            java.lang.String r1 = filter_tags_list
            java.util.List r0 = r0.findAccessibilityNodeInfosByViewId(r1)
            if (r0 == 0) goto L_0x014d
            int r1 = r0.size()
            if (r1 > 0) goto L_0x00bc
            goto L_0x014d
        L_0x00bc:
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            android.view.accessibility.AccessibilityNodeInfo r0 = (android.view.accessibility.AccessibilityNodeInfo) r0
            r2 = 4096(0x1000, float:5.74E-42)
            r0.performAction(r2)
            r4.startIndexView = r1
            int r0 = com.wx.assistants.application.MyApplication.SCROLL_SPEED
            r4.sleep(r0)
            com.wx.assistants.service.AutoService r0 = r4.autoService
            java.lang.String r1 = filter_tags_name
            java.util.List r0 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r0, (java.lang.String) r1)
            if (r0 == 0) goto L_0x014e
            int r1 = r0.size()
            if (r1 <= 0) goto L_0x014e
            java.lang.String r1 = ""
            int r2 = r0.size()
            int r2 = r2 + -1
            java.lang.Object r0 = r0.get(r2)
            android.view.accessibility.AccessibilityNodeInfo r0 = (android.view.accessibility.AccessibilityNodeInfo) r0
            if (r0 == 0) goto L_0x0115
            java.lang.CharSequence r2 = r0.getText()
            if (r2 == 0) goto L_0x0115
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.CharSequence r0 = r0.getText()
            r2.append(r0)
            java.lang.String r0 = ""
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            if (r0 == 0) goto L_0x0115
            java.lang.String r2 = ""
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0115
            goto L_0x0116
        L_0x0115:
            r0 = r1
        L_0x0116:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "WS_BABY_excSelectTask.11.lastname = "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            com.wx.assistants.utils.LogUtils.log(r1)
            java.lang.String r1 = r4.lastName
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0147
            int r0 = r4.selectTagNum
            if (r0 != 0) goto L_0x013c
            com.wx.assistants.service.AutoService r0 = r4.autoService
            com.wx.assistants.utils.PerformClickUtils.performBack(r0)
            goto L_0x0143
        L_0x013c:
            com.wx.assistants.service.AutoService r0 = r4.autoService
            java.lang.String r1 = "完成"
            com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r0, r1)
        L_0x0143:
            r4.sendCircle()
            goto L_0x014e
        L_0x0147:
            r4.lastName = r0
            r4.whileExecuteTask()
            goto L_0x014e
        L_0x014d:
            return
        L_0x014e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.CloneCircleUtils.whileExecuteTask():void");
    }

    public void initSelectGroupsName() {
        try {
            new PerformClickUtils2().check(this.autoService, list_select_search_id, executeSpeed + executeSpeedSetting, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    String str = (String) CloneCircleUtils.this.groupsList.get(0);
                    LogUtils.log("WS_BABY_SelectContactUI_2.name = " + str);
                    if (str.contains("、")) {
                        str = str.split("、")[0];
                    }
                    PerformClickUtils.findViewByIdAndPasteContent(CloneCircleUtils.this.autoService, BaseServiceUtils.list_select_search_id, str);
                    CloneCircleUtils.this.groupsList.remove(0);
                    new PerformClickUtils2().check(CloneCircleUtils.this.autoService, BaseServiceUtils.list_item_id, 600, 100, 30, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            LogUtils.log("WS_BABY_SelectContactUI_5");
                            PerformClickUtils.performClick(PerformClickUtils.findViewIdList((AccessibilityService) CloneCircleUtils.this.autoService, BaseServiceUtils.list_item_id).get(0));
                            CloneCircleUtils.access$2408(CloneCircleUtils.this);
                            if (CloneCircleUtils.this.groupsList.size() == 0) {
                                if (CloneCircleUtils.this.selectGroupNum == 0) {
                                    PerformClickUtils.performBack(CloneCircleUtils.this.autoService);
                                } else {
                                    PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "确定");
                                }
                                new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "谁可以看", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        CloneCircleUtils.this.executeRealSelectTags();
                                    }
                                });
                                return;
                            }
                            CloneCircleUtils.this.initSelectGroupsName();
                        }

                        public void nuFind() {
                            LogUtils.log("WS_BABY_SelectContactUI_4_nufind");
                            if (CloneCircleUtils.this.groupsList.size() == 0) {
                                if (CloneCircleUtils.this.selectGroupNum == 0) {
                                    PerformClickUtils.performBack(CloneCircleUtils.this.autoService);
                                } else {
                                    PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "确定");
                                }
                                new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "谁可以看", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        CloneCircleUtils.this.executeRealSelectTags();
                                    }
                                });
                                return;
                            }
                            CloneCircleUtils.this.initSelectGroupsName();
                        }
                    });
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY_SelectContactUI_5_nufind");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCircle() {
        new PerformClickUtils2().checkString(this.autoService, "发表", executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "发表");
                boolean unused = CloneCircleUtils.this.isFirstDelete = true;
                boolean unused2 = CloneCircleUtils.this.isSavedCompleted = false;
                boolean unused3 = CloneCircleUtils.this.isOnlyText = false;
                boolean unused4 = CloneCircleUtils.this.isSavedVideo = false;
                int unused5 = CloneCircleUtils.this.startIndex = 0;
                int unused6 = CloneCircleUtils.this.num = 0;
                String unused7 = CloneCircleUtils.this.nowTime = "";
                CloneCircleUtils.access$1008(CloneCircleUtils.this);
                String unused8 = CloneCircleUtils.this.text = "";
                MyWindowManager myWindowManager = CloneCircleUtils.this.mMyManager;
                BaseServiceUtils.updateBottomText(myWindowManager, "已为您克隆 " + CloneCircleUtils.this.realCloneCount + " 条朋友圈。\n正在自动克隆朋友圈，请务操作微信。");
                CloneCircleUtils.this.cloneNext();
            }
        });
    }

    public void endViewShow() {
        try {
            if (this.isFirstDelete && MyApplication.enforceable) {
                this.isFirstDelete = false;
                try {
                    int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "delete_model", 0)).intValue();
                    if (this.model != null && intValue == 0) {
                        String str = (String) SPUtils.get(MyApplication.getConText(), "delete_img", "");
                        if (!"".equals(str) && str.contains("#")) {
                            String[] split = str.split("#");
                            FileUtils.deleteForward(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (this.realCloneCount == 0) {
                showBottomView(this.mMyManager, "正在一键克隆朋友圈，请不要操作微信");
            }
            executeMain();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void endViewDismiss() {
        try {
            LogUtils.log("WS_BABY_endViewDismiss");
            this.mMyManager.stopAccessibilityService();
            MyApplication.enforceable = false;
            this.mMyManager.removeBottomView();
            this.mMyManager.showStartView();
            this.mMyManager.showBackView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFriendInfo2() {
        if (this.wxCode == null || "null".equals(this.wxCode) || "".equals(this.wxCode) || this.wxCode.startsWith("wxid_")) {
            this.wxCode = PerformClickUtils.findViewIdAndGetText(this.autoService, circle_list_layout_nickname_id);
        }
    }

    public void saveFriendInfo() {
        String str;
        String textFirstByNodeId;
        try {
            String textByNodeId = PerformClickUtils.getTextByNodeId(this.autoService, wx_nick_name);
            String textByNodeId2 = PerformClickUtils.getTextByNodeId(this.autoService, wx_number);
            if (textByNodeId == null || "".equals(textByNodeId)) {
                textByNodeId = "";
            } else if (textByNodeId.contains(":")) {
                String str2 = textByNodeId.split(":")[1];
                textByNodeId = (str2 == null || "".equals(str2)) ? "" : str2.trim();
            }
            if ("".equals(textByNodeId) && (textFirstByNodeId = PerformClickUtils.getTextFirstByNodeId(this.autoService, wx_nick_remark_name)) != null && !"".equals(textFirstByNodeId)) {
                textByNodeId = textFirstByNodeId.trim();
            }
            if (textByNodeId2 == null || "".equals(textByNodeId2)) {
                str = "";
            } else {
                str = textByNodeId2.contains(":") ? textByNodeId2.split(":")[1].trim() : "";
            }
            if (!"".equals(str)) {
                if (!str.startsWith("wxid_")) {
                    this.wxCode = str;
                    return;
                }
            }
            this.wxCode = textByNodeId;
        } catch (Exception unused) {
        }
    }
}
