package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.accessibility.AccessibilityNodeInfo;
import com.fb.jjyyzjy.watermark.utils.AlbumUtil;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.AutoService;
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

    private CloneCircleUtils() {
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

    public static CloneCircleUtils getInstance() {
        instance = new CloneCircleUtils();
        return instance;
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

    public void backExecute() {
        if (this.backCount >= 0 && MyApplication.enforceable) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        CloneCircleUtils.this.sleep(100);
                        LogUtils.log("WS_BABY.SnsCommentDetailUI.2");
                        if (MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.SnsCommentDetailUI.4");
                            BaseServiceUtils.getMainHandler().post(new Runnable() {
                                public void run() {
                                    try {
                                        AutoService autoService = CloneCircleUtils.this.autoService;
                                        AlbumUtil.insertImageToMediaStore(autoService, FileUtils.getSDPath() + "/tencent/microMsg/WeiXin");
                                    } catch (Exception e) {
                                    }
                                }
                            });
                            Boolean valueOf = Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(CloneCircleUtils.this.autoService, "返回"));
                            Boolean valueOf2 = Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(CloneCircleUtils.this.autoService, "拍照分享"));
                            boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(CloneCircleUtils.this.autoService, "查看消息");
                            boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(CloneCircleUtils.this.autoService, "通讯录");
                            boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(CloneCircleUtils.this.autoService, "发现");
                            boolean findNodeIsExistById = PerformClickUtils.findNodeIsExistById((AccessibilityService) CloneCircleUtils.this.autoService, BaseServiceUtils.home_tab_layout_id);
                            if (!CloneCircleUtils.this.isOnlyText ? !valueOf.booleanValue() || !valueOf2.booleanValue() : !valueOf.booleanValue() || !valueOf2.booleanValue() || Boolean.valueOf(findNodeIsExistByText).booleanValue()) {
                                if (!Boolean.valueOf(findNodeIsExistByText2).booleanValue() || !Boolean.valueOf(findNodeIsExistByText3).booleanValue() || !Boolean.valueOf(findNodeIsExistById).booleanValue()) {
                                    CloneCircleUtils.access$1410(CloneCircleUtils.this);
                                    PerformClickUtils.performBackNoDeep(CloneCircleUtils.this.autoService);
                                    CloneCircleUtils.this.backExecute();
                                    return;
                                }
                                LogUtils.log("WS_BABY.SnsCommentDetailUI.7");
                                PerformClickUtils.findTabTextAndClick(CloneCircleUtils.this.autoService, "发现");
                                PerformClickUtils.findTabTextAndClick(CloneCircleUtils.this.autoService, "发现");
                                CloneCircleUtils.this.sleep(50);
                                PerformClickUtils.findTabTextAndClick(CloneCircleUtils.this.autoService, "发现");
                                new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "朋友圈", 10, 50, 20, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "朋友圈");
                                    }

                                    public void nuFind() {
                                        PerformClickUtils.scrollTop(CloneCircleUtils.this.autoService, BaseServiceUtils.default_list_id);
                                        new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "朋友圈", 10, 50, 300, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "朋友圈");
                                            }

                                            public void nuFind() {
                                            }
                                        });
                                    }
                                });
                                new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "拍照分享", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        new Thread(new Runnable() {
                                            public void run() {
                                                try {
                                                    PrintStream printStream = System.out;
                                                    printStream.println("WS_BABY_isOnlyText =..... " + CloneCircleUtils.this.isOnlyText);
                                                    if (CloneCircleUtils.this.isOnlyText) {
                                                        LogUtils.log("WS_BABY.SnsCommentDetailUI.9898");
                                                        PerformClickUtils.performLongClick(CloneCircleUtils.this.autoService, BaseServiceUtils.right_more_id);
                                                        boolean unused = CloneCircleUtils.this.isSavedCompleted = false;
                                                        CloneCircleUtils.this.initSnsUploadUI();
                                                    } else if (MyApplication.enforceable) {
                                                        LogUtils.log("WS_BABY.SnsCommentDetailUI.99");
                                                        PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "拍照分享");
                                                        new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "从相册选择", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                                            public void find(int i) {
                                                                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "从相册选择");
                                                                CloneCircleUtils.this.initAlbumPreviewUI();
                                                            }

                                                            public void nuFind() {
                                                            }
                                                        });
                                                    }
                                                } catch (Exception e) {
                                                }
                                            }
                                        }).start();
                                    }

                                    public void nuFind() {
                                    }
                                });
                            } else if (CloneCircleUtils.this.isOnlyText) {
                                LogUtils.log("WS_BABY.SnsCommentDetailUI.9898");
                                PerformClickUtils.performLongClick(CloneCircleUtils.this.autoService, BaseServiceUtils.right_more_id);
                                boolean unused = CloneCircleUtils.this.isSavedCompleted = false;
                                CloneCircleUtils.this.initSnsUploadUI();
                            } else {
                                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "拍照分享");
                                new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "从相册选择", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "从相册选择");
                                        CloneCircleUtils.this.initAlbumPreviewUI();
                                    }

                                    public void nuFind() {
                                    }
                                });
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }).start();
        }
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

    public void executeClickCircle() {
        new PerformClickUtils2().checkString(this.autoService, "朋友圈", (executeSpeedSetting / 5) + 100, 50, 10, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_executeClickCircle.1");
                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "朋友圈");
                new PerformClickUtils2().checkNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.album_head_img, 100, 50, 200, new PerformClickUtils2.OnCheckListener() {
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

                    public void nuFind() {
                    }
                });
            }

            public void nuFind() {
            }
        });
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
                                                        public void find(int i) {
                                                            CloneCircleUtils.this.jumpExecuted(false);
                                                        }

                                                        public void nuFind() {
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
                                                    public void find(int i) {
                                                        CloneCircleUtils.this.jumpExecuted(false);
                                                    }

                                                    public void nuFind() {
                                                    }
                                                });
                                            }
                                        }

                                        public void nuFind() {
                                        }
                                    });
                                }
                            } else if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                                new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "详情", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        int unused = CloneCircleUtils.this.num = 1;
                                        if (CloneCircleUtils.this.startIndex < CloneCircleUtils.this.num && MyApplication.enforceable) {
                                            boolean unused2 = CloneCircleUtils.this.isSavedCompleted = true;
                                            LogUtils.log("WS_BABY.SnsCommentDetailUI.22");
                                            PerformClickUtils.performClick((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId3.get(0));
                                            CloneCircleUtils.this.initSnslineVideoActivity();
                                        }
                                    }

                                    public void nuFind() {
                                    }
                                });
                            } else if (CloneCircleUtils.this.text == null || "".equals(CloneCircleUtils.this.text)) {
                                new PerformClickUtils2().checkNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.list_layout_only_text_id, 10, 100, 200, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        LogUtils.log("WS_BABY_SnsSingleTextViewUI0");
                                        String unused = CloneCircleUtils.this.text = PerformClickUtils.findViewIdAndGetText(CloneCircleUtils.this.autoService, BaseServiceUtils.list_layout_only_text_id);
                                        new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "详情", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                int unused = CloneCircleUtils.this.num = 1;
                                                if (CloneCircleUtils.this.startIndex < CloneCircleUtils.this.num && MyApplication.enforceable) {
                                                    boolean unused2 = CloneCircleUtils.this.isSavedCompleted = true;
                                                    LogUtils.log("WS_BABY.SnsCommentDetailUI.22");
                                                    PerformClickUtils.performClick((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId3.get(0));
                                                    CloneCircleUtils.this.initSnslineVideoActivity();
                                                }
                                            }

                                            public void nuFind() {
                                            }
                                        });
                                    }

                                    public void nuFind() {
                                    }
                                });
                            } else {
                                new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "详情", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        int unused = CloneCircleUtils.this.num = 1;
                                        if (CloneCircleUtils.this.startIndex < CloneCircleUtils.this.num && MyApplication.enforceable) {
                                            boolean unused2 = CloneCircleUtils.this.isSavedCompleted = true;
                                            LogUtils.log("WS_BABY.SnsCommentDetailUI.22");
                                            PerformClickUtils.performClick((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId3.get(0));
                                            CloneCircleUtils.this.initSnslineVideoActivity();
                                        }
                                    }

                                    public void nuFind() {
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
                                        public void find(int i) {
                                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "编辑");
                                            if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                                                CloneCircleUtils.this.initMMNewPhotoEditUI();
                                            } else {
                                                CloneCircleUtils.this.initPhotoMMRecordUI();
                                            }
                                        }

                                        public void nuFind() {
                                        }
                                    });
                                }
                            }
                        } else if (CloneCircleUtils.this.text == null || "".equals(CloneCircleUtils.this.text)) {
                            new PerformClickUtils2().checkNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.list_layout_only_text_id, 10, 100, 200, new PerformClickUtils2.OnCheckListener() {
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
                                                public void find(int i) {
                                                    PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "编辑");
                                                    if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                                                        CloneCircleUtils.this.initMMNewPhotoEditUI();
                                                    } else {
                                                        CloneCircleUtils.this.initPhotoMMRecordUI();
                                                    }
                                                }

                                                public void nuFind() {
                                                }
                                            });
                                        }
                                    }
                                }

                                public void nuFind() {
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
                                    public void find(int i) {
                                        PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "编辑");
                                        if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                                            CloneCircleUtils.this.initMMNewPhotoEditUI();
                                        } else {
                                            CloneCircleUtils.this.initPhotoMMRecordUI();
                                        }
                                    }

                                    public void nuFind() {
                                    }
                                });
                            }
                        }
                    }
                }

                public void nuFind() {
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
            public void find(int i) {
                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "从群选择");
                int unused = CloneCircleUtils.this.selectGroupNum = 0;
                CloneCircleUtils.this.initSelectGroupsName();
            }

            public void nuFind() {
            }
        });
    }

    public void executeRealSelectTags() {
        if (this.selectTags == null || "".equals(this.selectTags)) {
            new PerformClickUtils2().checkString(this.autoService, "完成", executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                    CloneCircleUtils.this.sendCircle();
                }

                public void nuFind() {
                }
            });
        } else {
            executeSelectTags();
        }
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

    public void findSearchBtn() {
        new PerformClickUtils2().check(this.autoService, contact_nav_search_viewgroup_id, executeSpeedSetting + 100, 50, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
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

            public void nuFind() {
            }
        });
    }

    public void initAlbumPreviewUI() {
        try {
            if (this.isSavedVideo) {
                new PerformClickUtils2().checkNodeId(this.autoService, video_first_id, executeSpeedSetting + BannerConfig.DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
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
                                            public void find(int i) {
                                                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                                                CloneCircleUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                                                CloneCircleUtils.this.initSnsUploadUI();
                                            }

                                            public void nuFind() {
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
                                    public void find(int i) {
                                        CloneCircleUtils.this.initVideoMMRecordUI();
                                    }

                                    public void nuFind() {
                                    }
                                });
                            }
                        }
                    }

                    public void nuFind() {
                    }
                });
            } else {
                new PerformClickUtils2().checkNodeId(this.autoService, img_first_check_layout_id, executeSpeedSetting + BannerConfig.DURATION, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.AlbumPreviewUI.2");
                            try {
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CloneCircleUtils.this.autoService, BaseServiceUtils.img_first_check_layout_id);
                                if (findViewIdList != null && findViewIdList.size() > 0) {
                                    int access$900 = CloneCircleUtils.this.num - 1;
                                    while (true) {
                                        int i2 = access$900;
                                        if (i2 <= -1 || !MyApplication.enforceable) {
                                            break;
                                        }
                                        CloneCircleUtils.this.sleep(5);
                                        PerformClickUtils.performClick(findViewIdList.get(i2));
                                        access$900 = i2 - 1;
                                    }
                                }
                            } catch (Exception e) {
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
            new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeedSetting + 50, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findViewIdAndClick(CloneCircleUtils.this.autoService, BaseServiceUtils.right_more_id);
                    CloneCircleUtils.this.initSingleChatInfo();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_73");
            e.printStackTrace();
        }
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

    public void initFTSMainUI() {
        try {
            LogUtils.log("WS_BABY.FTSMainUI.wxCode = " + this.wxCode);
            searchNickName(this.wxCode);
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_72");
            e.printStackTrace();
        }
    }

    public void initMMNewPhotoEditUI() {
        try {
            LogUtils.log("WS_BABY_MMNewPhotoEditUI");
            new PerformClickUtils2().checkString(this.autoService, "完成", (executeSpeedSetting / 10) + 100, 50, 400, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_MMNewPhotoEditUI_has_完成");
                    PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                    new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "保存图片", 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
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

    public void initPhotoMMRecordUI() {
        try {
            new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "下一步", "完成", (executeSpeedSetting / 10) + 10, 50, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (i == 0) {
                        PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "下一步");
                        new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "保存图片", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "保存图片");
                                CloneCircleUtils.this.backDescPage(new OnBackDescPageListener() {
                                    public void find() {
                                        CloneCircleUtils.this.initSnsCommentDetailUI(0);
                                    }
                                });
                            }

                            public void nuFind() {
                            }
                        });
                    } else if (i != 1) {
                    } else {
                        if (BaseServiceUtils.wxVersionCode >= 1640) {
                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                            new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "保存图片", 10, 20, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "保存图片");
                                    new PerformClickUtils2().checkNilString(CloneCircleUtils.this.autoService, "完成", 10, 20, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            CloneCircleUtils.this.backDescPage(new OnBackDescPageListener() {
                                                public void find() {
                                                    CloneCircleUtils.this.initSnsCommentDetailUI(0);
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
                            return;
                        }
                        new PerformClickUtils2().check(CloneCircleUtils.this.autoService, BaseServiceUtils.circle_img_crop_id, 0, 100, 10, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                PerformClickUtils.findViewIdAndClick(CloneCircleUtils.this.autoService, BaseServiceUtils.circle_img_crop_id);
                                new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "确定", CloneCircleUtils.this.isFastSpeed ? 1 : SocializeConstants.CANCLE_RESULTCODE, 5, 200, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "确定");
                                        new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "完成", 10, 20, 50, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                                                new PerformClickUtils2().checkString(CloneCircleUtils.this.autoService, "保存图片", 10, 20, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new PerformClickUtils2.OnCheckListener() {
                                                    public void find(int i) {
                                                        PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "保存图片");
                                                        new PerformClickUtils2().checkNilString(CloneCircleUtils.this.autoService, "完成", 10, 20, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                                            public void find(int i) {
                                                                CloneCircleUtils.this.backDescPage(new OnBackDescPageListener() {
                                                                    public void find() {
                                                                        CloneCircleUtils.this.initSnsCommentDetailUI(0);
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
                    }
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                                    public void find(int i) {
                                        CloneCircleUtils.this.executeRealSelectTags();
                                    }

                                    public void nuFind() {
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
                                    public void find(int i) {
                                        CloneCircleUtils.this.executeRealSelectTags();
                                    }

                                    public void nuFind() {
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

    public void initSingleChatInfo() {
        try {
            new PerformClickUtils2().check(this.autoService, single_contact_nick_name_id, executeSpeedSetting + 50, 50, 60, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findViewIdAndClick(CloneCircleUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                    CloneCircleUtils.this.endViewShow();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_74");
            e.printStackTrace();
        }
    }

    public void initSnsCommentDetailUI(int i) {
        try {
            new PerformClickUtils2().checkNodeIdsHasSomeOne3(this.autoService, circle_list_layout_img_id, circle_list_layout_video_id, list_layout_only_text_id, (executeSpeedSetting / 10) + i, 100, 200, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    CloneCircleUtils.this.executeDetail();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSnsGalleryUI() {
        try {
            System.out.println("WS_BABY_initSnsGalleryUI.0");
            new PerformClickUtils2().checkNodeId(this.autoService, "android:id/text1", executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
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
                                        public void find(int i) {
                                            CloneCircleUtils.this.jumpExecuted(false);
                                        }

                                        public void nuFind() {
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
                                        public void find(int i) {
                                            CloneCircleUtils.this.jumpExecuted(false);
                                        }

                                        public void nuFind() {
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
                        } catch (Exception e) {
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
                        int unused5 = CloneCircleUtils.this.startIndex = 0;
                        int unused6 = CloneCircleUtils.this.num = 0;
                        new PerformClickUtils2().checkNodeId(CloneCircleUtils.this.autoService, BaseServiceUtils.list_circle_layout_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                LogUtils.log("WS_BABY.SnsGalleryUI.13");
                                CloneCircleUtils.this.jumpExecuted(false);
                            }

                            public void nuFind() {
                            }
                        });
                    }
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSnsUploadUI() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, input_send_edit_text_id, (executeSpeedSetting / 8) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
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

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSnslineVideoActivity() {
        try {
            LogUtils.log("WS_BABY_SnsOnlineVideoActivity");
            new PerformClickUtils2().check(this.autoService, long_click_id, this.jumpTime + (executeSpeedSetting / 8), 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
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

    public void initVideoMMRecordUI() {
        try {
            new PerformClickUtils2().checkString(this.autoService, "完成", (executeSpeedSetting / 10) + 10, 100, 300, new PerformClickUtils2.OnCheckListener() {
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
                                public void find(int i) {
                                    CloneCircleUtils.this.initSnsUploadUI();
                                }

                                public void nuFind() {
                                }
                            });
                        }

                        public void nuFind() {
                            System.out.println("WS_BABY_initVideoMMRecordUI.4");
                            LogUtils.log("WS_BABY_00");
                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_11");
                            new PerformClickUtils2().checkNilString(CloneCircleUtils.this.autoService, "正在处理", (int) SocializeConstants.CANCLE_RESULTCODE, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    CloneCircleUtils.this.initSnsUploadUI();
                                }

                                public void nuFind() {
                                }
                            });
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

    public void jumpExecuted(boolean z) {
        if (z) {
            System.out.println("WS_BABY_jumpExecuted.1");
            scrollOperation();
            return;
        }
        System.out.println("WS_BABY_jumpExecuted.2");
        joinAlbumListPage();
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
    }

    public void saveFriendInfo() {
        String textFirstByNodeId;
        try {
            String textByNodeId = PerformClickUtils.getTextByNodeId(this.autoService, wx_nick_name);
            String textByNodeId2 = PerformClickUtils.getTextByNodeId(this.autoService, wx_number);
            if (textByNodeId == null || "".equals(textByNodeId)) {
                textByNodeId = "";
            } else if (textByNodeId.contains(":")) {
                String str = textByNodeId.split(":")[1];
                textByNodeId = (str == null || "".equals(str)) ? "" : str.trim();
            }
            String trim = (!"".equals(textByNodeId) || (textFirstByNodeId = PerformClickUtils.getTextFirstByNodeId(this.autoService, wx_nick_remark_name)) == null || "".equals(textFirstByNodeId)) ? textByNodeId : textFirstByNodeId.trim();
            String trim2 = (textByNodeId2 == null || "".equals(textByNodeId2)) ? "" : textByNodeId2.contains(":") ? textByNodeId2.split(":")[1].trim() : "";
            if ("".equals(trim2) || trim2.startsWith("wxid_")) {
                this.wxCode = trim;
            } else {
                this.wxCode = trim2;
            }
        } catch (Exception e) {
        }
    }

    public void saveFriendInfo2() {
        if (this.wxCode == null || "null".equals(this.wxCode) || "".equals(this.wxCode) || this.wxCode.startsWith("wxid_")) {
            this.wxCode = PerformClickUtils.findViewIdAndGetText(this.autoService, circle_list_layout_nickname_id);
        }
    }

    public void scrollOperation() {
        if (this.nowScrollNum < this.scrollPage) {
            this.nowScrollNum++;
            PerformClickUtils.scroll(this.autoService, list_circle_id);
            sleep(MyApplication.SCROLL_SPEED * 5);
            new PerformClickUtils2().checkNilString(this.autoService, "正在加载", (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, (int) SocializeConstants.CANCLE_RESULTCODE, 300, (PerformClickUtils2.OnCheckListener5) new PerformClickUtils2.OnCheckListener5() {
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

                public void find(int i) {
                    CloneCircleUtils.this.scrollOperation();
                    MyWindowManager myWindowManager = CloneCircleUtils.this.mMyManager;
                    BaseServiceUtils.updateBottomText(myWindowManager, "已为您克隆 " + CloneCircleUtils.this.realCloneCount + " 条朋友圈。\n正在自动克隆朋友圈，请不要操作微信。");
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
                                        } catch (Exception e) {
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

    public void selectFriends() {
        if (this.selectModel == null || "".equals(this.selectModel) || "公开".equals(this.selectModel)) {
            sendCircle();
        } else if (this.realCloneCount <= 0 || "私密".equals(this.selectModel)) {
            new PerformClickUtils2().checkString(this.autoService, "谁可以看", executeSpeed + (executeSpeedSetting / 5), 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "谁可以看");
                    new PerformClickUtils2().checkStringsAll(CloneCircleUtils.this.autoService, "私密", "公开", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            if ("私密".equals(CloneCircleUtils.this.selectModel) || "公开".equals(CloneCircleUtils.this.selectModel)) {
                                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, CloneCircleUtils.this.selectModel);
                                PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "完成");
                                CloneCircleUtils.this.sendCircle();
                            } else if ("部分可见".equals(CloneCircleUtils.this.selectModel)) {
                                new PerformClickUtils2().checkNodeStringsHasSomeOne(CloneCircleUtils.this.autoService, "部分可见", "选中的朋友可见", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        if (i == 0) {
                                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "部分可见");
                                        } else {
                                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "选中的朋友可见");
                                        }
                                        CloneCircleUtils.this.executeRealSelect();
                                    }

                                    public void nuFind() {
                                    }
                                });
                            } else if ("不给谁看".equals(CloneCircleUtils.this.selectModel)) {
                                new PerformClickUtils2().checkNodeStringsHasSomeOne(CloneCircleUtils.this.autoService, "不给谁看", "选中的朋友不可见", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        if (i == 0) {
                                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "不给谁看");
                                        } else {
                                            PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "选中的朋友不可见");
                                        }
                                        CloneCircleUtils.this.executeRealSelect();
                                    }

                                    public void nuFind() {
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
        } else {
            new PerformClickUtils2().checkString(this.autoService, "上次分组", 300, 100, 10, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findTextAndClick(CloneCircleUtils.this.autoService, "上次分组");
                    CloneCircleUtils.this.sleep(100);
                    CloneCircleUtils.this.sendCircle();
                }

                public void nuFind() {
                }
            });
        }
    }

    public void sendCircle() {
        new PerformClickUtils2().checkString(this.autoService, "发表", executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
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

            public void nuFind() {
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x010c, code lost:
        if ("".equals(r0) == false) goto L_0x010e;
     */
    public void whileExecuteTask() {
        AccessibilityNodeInfo rootInActiveWindow;
        String str;
        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, filter_tags_name);
        if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
            if (this.startIndexView < findViewIdList.size()) {
                AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(this.startIndexView);
                String str2 = "";
                if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                    str2 = accessibilityNodeInfo.getText() + "";
                }
                LogUtils.log("WS_BABY_excSelectTask.0.nowName = " + str2);
                if (this.tagsList.contains(str2)) {
                    this.startIndexView++;
                    this.selectTagNum++;
                    sleep(10);
                    PerformClickUtils.performClick(accessibilityNodeInfo);
                    this.tagsList.remove(str2);
                    whileExecuteTask();
                    return;
                }
                LogUtils.log("WS_BABY_excSelectTask.2");
                this.startIndexView++;
                this.selectTagNum++;
                whileExecuteTask();
            } else if (this.startIndexView >= findViewIdList.size() && (rootInActiveWindow = this.autoService.getRootInActiveWindow()) != null && MyApplication.enforceable) {
                LogUtils.log("WS_BABY_excSelectTask.10");
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(filter_tags_list);
                if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                    findAccessibilityNodeInfosByViewId.get(0).performAction(4096);
                    this.startIndexView = 0;
                    sleep(MyApplication.SCROLL_SPEED);
                    List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, filter_tags_name);
                    if (findViewIdList2 != null && findViewIdList2.size() > 0) {
                        AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList2.get(findViewIdList2.size() - 1);
                        if (!(accessibilityNodeInfo2 == null || accessibilityNodeInfo2.getText() == null)) {
                            str = accessibilityNodeInfo2.getText() + "";
                            if (str != null) {
                            }
                        }
                        str = "";
                        LogUtils.log("WS_BABY_excSelectTask.11.lastname = " + str);
                        if (str.equals(this.lastName)) {
                            if (this.selectTagNum == 0) {
                                PerformClickUtils.performBack(this.autoService);
                            } else {
                                PerformClickUtils.findTextAndClick(this.autoService, "完成");
                            }
                            sendCircle();
                            return;
                        }
                        this.lastName = str;
                        whileExecuteTask();
                    }
                }
            }
        }
    }
}
