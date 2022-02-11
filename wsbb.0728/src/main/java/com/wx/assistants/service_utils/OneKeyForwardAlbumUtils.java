package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.accessibility.AccessibilityNodeInfo;
import com.fb.jjyyzjy.watermark.utils.AlbumUtil;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.AutoService;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.service_utils.OneKeyForwardUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.fileutil.FileUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class OneKeyForwardAlbumUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static OneKeyForwardAlbumUtils instance;
    int backCount = 25;
    /* access modifiers changed from: private */
    public int deleteModel = 0;
    /* access modifiers changed from: private */
    public String friendName = "";
    /* access modifiers changed from: private */
    public List<String> groupsList = new ArrayList();
    /* access modifiers changed from: private */
    public boolean isAutoSend;
    /* access modifiers changed from: private */
    public boolean isBackStart = false;
    /* access modifiers changed from: private */
    public boolean isExist = false;
    /* access modifiers changed from: private */
    public boolean isFastSpeed = false;
    private boolean isFirstDelete = true;
    /* access modifiers changed from: private */
    public boolean isOnlyText = false;
    /* access modifiers changed from: private */
    public boolean isSavedCompleted = false;
    /* access modifiers changed from: private */
    public boolean isSavedVideo = false;
    private String lastName = "";
    /* access modifiers changed from: private */
    public OperationParameterModel model;
    /* access modifiers changed from: private */
    public int num = 0;
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
    private List<String> tagsList = new ArrayList();
    /* access modifiers changed from: private */
    public String text = "";

    interface OnBackDescPageListener {
        void find();
    }

    private OneKeyForwardAlbumUtils() {
    }

    static /* synthetic */ int access$1108(OneKeyForwardAlbumUtils oneKeyForwardAlbumUtils) {
        int i = oneKeyForwardAlbumUtils.selectGroupNum;
        oneKeyForwardAlbumUtils.selectGroupNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$508(OneKeyForwardAlbumUtils oneKeyForwardAlbumUtils) {
        int i = oneKeyForwardAlbumUtils.startIndex;
        oneKeyForwardAlbumUtils.startIndex = i + 1;
        return i;
    }

    public static OneKeyForwardAlbumUtils getInstance() {
        instance = new OneKeyForwardAlbumUtils();
        return instance;
    }

    public void backDescPage(final OneKeyForwardUtils.OnBackDescPageListener onBackDescPageListener) {
        new PerformClickUtils2().checkNodeId(this.autoService, view_pager_id, 100, 100, 30, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                System.out.println("WS_BABY_backDescPage.1");
                OneKeyForwardAlbumUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                OneKeyForwardAlbumUtils.this.autoService.performGlobalAction(1);
                new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "详情", 100, 100, 8, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (onBackDescPageListener != null) {
                            onBackDescPageListener.find();
                        }
                    }

                    public void nuFind() {
                        OneKeyForwardAlbumUtils.this.autoService.performGlobalAction(1);
                        OneKeyForwardAlbumUtils.this.sleep(100);
                        if (onBackDescPageListener != null) {
                            onBackDescPageListener.find();
                        }
                    }
                });
            }

            public void nuFind() {
                System.out.println("WS_BABY_backDescPage.3");
                OneKeyForwardAlbumUtils.this.autoService.performGlobalAction(1);
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
                        OneKeyForwardAlbumUtils.this.sleep(100);
                        LogUtils.log("WS_BABY.backExecute.1");
                        if (MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.backExecute.2");
                            Boolean valueOf = Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(OneKeyForwardAlbumUtils.this.autoService, "返回"));
                            Boolean valueOf2 = Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(OneKeyForwardAlbumUtils.this.autoService, "拍照分享"));
                            boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(OneKeyForwardAlbumUtils.this.autoService, "查看消息");
                            boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(OneKeyForwardAlbumUtils.this.autoService, "通讯录");
                            boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(OneKeyForwardAlbumUtils.this.autoService, "发现");
                            boolean findNodeIsExistById = PerformClickUtils.findNodeIsExistById((AccessibilityService) OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.home_tab_layout_id);
                            if (!OneKeyForwardAlbumUtils.this.isOnlyText ? !(!valueOf.booleanValue() || !valueOf2.booleanValue()) : !(!valueOf.booleanValue() || !valueOf2.booleanValue() || Boolean.valueOf(findNodeIsExistByText).booleanValue())) {
                                LogUtils.log("WS_BABY.backExecute.3");
                                if (OneKeyForwardAlbumUtils.this.isOnlyText) {
                                    LogUtils.log("WS_BABY.backExecute.4");
                                    PerformClickUtils.performLongClick(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.right_more_id);
                                    boolean unused = OneKeyForwardAlbumUtils.this.isSavedCompleted = false;
                                    OneKeyForwardAlbumUtils.this.initSnsUploadUI();
                                    return;
                                }
                                LogUtils.log("WS_BABY.backExecute.5");
                                PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "拍照分享");
                                new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "从相册选择", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        LogUtils.log("WS_BABY.backExecute.6");
                                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "从相册选择");
                                        OneKeyForwardAlbumUtils.this.initAlbumPreviewUI();
                                    }

                                    public void nuFind() {
                                        LogUtils.log("WS_BABY.backExecute.7");
                                    }
                                });
                            } else if (!Boolean.valueOf(findNodeIsExistByText2).booleanValue() || !Boolean.valueOf(findNodeIsExistByText3).booleanValue() || !Boolean.valueOf(findNodeIsExistById).booleanValue()) {
                                LogUtils.log("WS_BABY.backExecute.19");
                                PerformClickUtils.performBackNoDeep(OneKeyForwardAlbumUtils.this.autoService);
                                BaseServiceUtils.getMainHandler().post(new Runnable() {
                                    public void run() {
                                        try {
                                            AutoService autoService = OneKeyForwardAlbumUtils.this.autoService;
                                            AlbumUtil.insertImageToMediaStore(autoService, FileUtils.getSDPath() + "/tencent/microMsg/WeiXin");
                                        } catch (Exception e) {
                                        }
                                    }
                                });
                                OneKeyForwardAlbumUtils oneKeyForwardAlbumUtils = OneKeyForwardAlbumUtils.this;
                                oneKeyForwardAlbumUtils.backCount--;
                                OneKeyForwardAlbumUtils.this.backExecute();
                            } else {
                                LogUtils.log("WS_BABY.backExecute.8");
                                PerformClickUtils.findTabTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "发现");
                                PerformClickUtils.findTabTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "发现");
                                OneKeyForwardAlbumUtils.this.sleep(50);
                                PerformClickUtils.findTabTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "发现");
                                new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "朋友圈", 10, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        LogUtils.log("WS_BABY.backExecute.9");
                                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "朋友圈");
                                    }

                                    public void nuFind() {
                                        LogUtils.log("WS_BABY.backExecute.10");
                                        PerformClickUtils.scrollTop(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.default_list_id);
                                        new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "朋友圈", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                LogUtils.log("WS_BABY.backExecute.11");
                                                PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "朋友圈");
                                            }

                                            public void nuFind() {
                                                LogUtils.log("WS_BABY.backExecute.12");
                                            }
                                        });
                                    }
                                });
                                new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "拍照分享", 10, 50, 300, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        PrintStream printStream = System.out;
                                        printStream.println("WS_BABY_isOnlyText =..... " + OneKeyForwardAlbumUtils.this.isOnlyText);
                                        if (OneKeyForwardAlbumUtils.this.isOnlyText) {
                                            LogUtils.log("WS_BABY.backExecute.14");
                                            PerformClickUtils.performLongClick(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.right_more_id);
                                            boolean unused = OneKeyForwardAlbumUtils.this.isSavedCompleted = false;
                                            OneKeyForwardAlbumUtils.this.initSnsUploadUI();
                                            return;
                                        }
                                        LogUtils.log("WS_BABY.backExecute.15");
                                        if (MyApplication.enforceable) {
                                            PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "拍照分享");
                                            new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "从相册选择", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                                public void find(int i) {
                                                    LogUtils.log("WS_BABY.backExecute.16");
                                                    PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "从相册选择");
                                                    OneKeyForwardAlbumUtils.this.initAlbumPreviewUI();
                                                }

                                                public void nuFind() {
                                                    LogUtils.log("WS_BABY.backExecute.17");
                                                }
                                            });
                                        }
                                    }

                                    public void nuFind() {
                                        LogUtils.log("WS_BABY.backExecute.18");
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
        new PerformClickUtils2().checkStringAndIdSomeOne(this.autoService, "查看消息", friends_circle_head_img_id, (executeSpeedSetting / 8) + SocializeConstants.CANCLE_RESULTCODE, 200, 100, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                System.out.println("WS_BABY.cloneNext.1");
                if (i == 0) {
                    BaseServiceUtils.getMainHandler().post(new Runnable() {
                        public void run() {
                            OneKeyForwardAlbumUtils.this.mMyManager.removeEndView();
                            OneKeyForwardAlbumUtils.this.mMyManager.removeBottomView();
                            OneKeyForwardAlbumUtils.this.mMyManager.stopAccessibilityService();
                            OneKeyForwardAlbumUtils.this.mMyManager.showStartView();
                            OneKeyForwardAlbumUtils.this.mMyManager.showBackView();
                        }
                    });
                } else {
                    PerformClickUtils.executedBackHome(OneKeyForwardAlbumUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            if ("我的相册".equals(OneKeyForwardAlbumUtils.this.friendName)) {
                                OneKeyForwardAlbumUtils.this.jumpMyAlbum();
                            } else {
                                OneKeyForwardAlbumUtils.this.findSearchBtn();
                            }
                        }
                    });
                }
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
            showBottomView(this.mMyManager, "正在一键转发相册图文，请不要操作微信");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        OneKeyForwardAlbumUtils.this.executeMain();
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void executeClickCircle() {
        new PerformClickUtils2().checkString(this.autoService, "朋友圈", (executeSpeedSetting / 5) + 100, 50, 10, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_executeClickCircle.1");
                PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "朋友圈");
                BaseServiceUtils.getMainHandler().post(new Runnable() {
                    public void run() {
                        OneKeyForwardAlbumUtils.this.mMyManager.removeEndView();
                        OneKeyForwardAlbumUtils.this.mMyManager.removeBottomView();
                        OneKeyForwardAlbumUtils.this.mMyManager.stopAccessibilityService();
                        OneKeyForwardAlbumUtils.this.mMyManager.showStartView();
                        OneKeyForwardAlbumUtils.this.mMyManager.showBackView();
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
                System.out.println("WS_BABY_isOnlyText = false");
                getMainHandler().post(new Runnable() {
                    public void run() {
                        try {
                            AutoService autoService = OneKeyForwardAlbumUtils.this.autoService;
                            AlbumUtil.insertImageToMediaStore(autoService, FileUtils.getSDPath() + "/tencent/microMsg/WeiXin");
                        } catch (Exception e) {
                        }
                    }
                });
                this.backCount = 25;
                backExecute();
                return;
            }
            new PerformClickUtils2().checkNodeIdsHasSomeOne3(this.autoService, list_layout_only_text_id, circle_list_layout_img_id, desc_video_relativeLayout_id, 10, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY.SnsCommentDetailUI.1");
                    AccessibilityNodeInfo rootInActiveWindow = OneKeyForwardAlbumUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow == null) {
                        LogUtils.log("WS_BABY.SnsCommentDetailUI.null");
                        return;
                    }
                    LogUtils.log("WS_BABY.SnsCommentDetailUI.121212");
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_layout_only_text_id);
                    final List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.circle_list_layout_img_id);
                    final List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.desc_video_relativeLayout_id);
                    if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0 || findAccessibilityNodeInfosByViewId2.get(0) == null || findAccessibilityNodeInfosByViewId2.get(0).getChildCount() <= 0) {
                        if (findAccessibilityNodeInfosByViewId3 == null || findAccessibilityNodeInfosByViewId3.size() <= 0 || findAccessibilityNodeInfosByViewId3.get(0) == null) {
                            if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                                int unused = OneKeyForwardAlbumUtils.this.num = 1;
                                LogUtils.log("WS_BABY_SnsSingleTextViewUI");
                                new PerformClickUtils2().checkNodeId(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.list_layout_only_text_id, 10, 100, 200, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        LogUtils.log("WS_BABY_SnsSingleTextViewUI0");
                                        String unused = OneKeyForwardAlbumUtils.this.text = PerformClickUtils.findViewIdAndGetText(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.list_layout_only_text_id);
                                        OneKeyForwardAlbumUtils.this.sleep(100);
                                        PerformClickUtils.performBack(OneKeyForwardAlbumUtils.this.autoService);
                                        boolean unused2 = OneKeyForwardAlbumUtils.this.isSavedCompleted = true;
                                        boolean unused3 = OneKeyForwardAlbumUtils.this.isOnlyText = true;
                                        System.out.println("WS_BABY_isOnlyText = true");
                                        OneKeyForwardAlbumUtils.this.backCount = 25;
                                        OneKeyForwardAlbumUtils.this.backExecute();
                                    }

                                    public void nuFind() {
                                    }
                                });
                            }
                        } else if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                            OneKeyForwardAlbumUtils.this.sleep(100);
                            int unused2 = OneKeyForwardAlbumUtils.this.num = 1;
                            if (OneKeyForwardAlbumUtils.this.startIndex < OneKeyForwardAlbumUtils.this.num && MyApplication.enforceable) {
                                boolean unused3 = OneKeyForwardAlbumUtils.this.isSavedCompleted = true;
                                LogUtils.log("WS_BABY.SnsCommentDetailUI.22");
                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId3.get(0));
                                OneKeyForwardAlbumUtils.this.initSnslineVideoActivity();
                            }
                        } else if (OneKeyForwardAlbumUtils.this.text == null || "".equals(OneKeyForwardAlbumUtils.this.text)) {
                            new PerformClickUtils2().checkNodeId(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.list_layout_only_text_id, 10, 100, 200, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY_SnsSingleTextViewUI0");
                                    String unused = OneKeyForwardAlbumUtils.this.text = PerformClickUtils.findViewIdAndGetText(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.list_layout_only_text_id);
                                    OneKeyForwardAlbumUtils.this.sleep(100);
                                    int unused2 = OneKeyForwardAlbumUtils.this.num = 1;
                                    if (OneKeyForwardAlbumUtils.this.startIndex < OneKeyForwardAlbumUtils.this.num && MyApplication.enforceable) {
                                        boolean unused3 = OneKeyForwardAlbumUtils.this.isSavedCompleted = true;
                                        LogUtils.log("WS_BABY.SnsCommentDetailUI.22");
                                        PerformClickUtils.performClick((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId3.get(0));
                                        OneKeyForwardAlbumUtils.this.initSnslineVideoActivity();
                                    }
                                }

                                public void nuFind() {
                                }
                            });
                        } else {
                            OneKeyForwardAlbumUtils.this.sleep(100);
                            int unused4 = OneKeyForwardAlbumUtils.this.num = 1;
                            if (OneKeyForwardAlbumUtils.this.startIndex < OneKeyForwardAlbumUtils.this.num && MyApplication.enforceable) {
                                boolean unused5 = OneKeyForwardAlbumUtils.this.isSavedCompleted = true;
                                LogUtils.log("WS_BABY.SnsCommentDetailUI.22");
                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId3.get(0));
                                OneKeyForwardAlbumUtils.this.initSnslineVideoActivity();
                            }
                        }
                    } else if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                        if (findAccessibilityNodeInfosByViewId2 != null && !findAccessibilityNodeInfosByViewId2.isEmpty() && MyApplication.enforceable) {
                            boolean unused6 = OneKeyForwardAlbumUtils.this.isSavedVideo = false;
                            int unused7 = OneKeyForwardAlbumUtils.this.num = findAccessibilityNodeInfosByViewId2.get(0).getChildCount();
                            if (OneKeyForwardAlbumUtils.this.startIndex < OneKeyForwardAlbumUtils.this.num) {
                                if (OneKeyForwardAlbumUtils.this.startIndex == OneKeyForwardAlbumUtils.this.num - 1) {
                                    boolean unused8 = OneKeyForwardAlbumUtils.this.isSavedCompleted = true;
                                }
                                PerformClickUtils.performLongClick(findAccessibilityNodeInfosByViewId2.get(0).getChild(OneKeyForwardAlbumUtils.this.startIndex));
                                OneKeyForwardAlbumUtils.access$508(OneKeyForwardAlbumUtils.this);
                                new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "编辑", 10, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "编辑");
                                        if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                                            OneKeyForwardAlbumUtils.this.initMMNewPhotoEditUI();
                                        } else {
                                            OneKeyForwardAlbumUtils.this.initPhotoMMRecordUI();
                                        }
                                    }

                                    public void nuFind() {
                                    }
                                });
                            }
                        }
                    } else if (OneKeyForwardAlbumUtils.this.text == null || "".equals(OneKeyForwardAlbumUtils.this.text)) {
                        new PerformClickUtils2().checkNodeId(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.list_layout_only_text_id, 10, 100, 200, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                LogUtils.log("WS_BABY_SnsSingleTextViewUI0");
                                String unused = OneKeyForwardAlbumUtils.this.text = PerformClickUtils.findViewIdAndGetText(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.list_layout_only_text_id);
                                OneKeyForwardAlbumUtils.this.sleep(100);
                                if (findAccessibilityNodeInfosByViewId2 != null && !findAccessibilityNodeInfosByViewId2.isEmpty() && MyApplication.enforceable) {
                                    boolean unused2 = OneKeyForwardAlbumUtils.this.isSavedVideo = false;
                                    int unused3 = OneKeyForwardAlbumUtils.this.num = ((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId2.get(0)).getChildCount();
                                    if (OneKeyForwardAlbumUtils.this.startIndex < OneKeyForwardAlbumUtils.this.num) {
                                        if (OneKeyForwardAlbumUtils.this.startIndex == OneKeyForwardAlbumUtils.this.num - 1) {
                                            boolean unused4 = OneKeyForwardAlbumUtils.this.isSavedCompleted = true;
                                        }
                                        PerformClickUtils.performLongClick(((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId2.get(0)).getChild(OneKeyForwardAlbumUtils.this.startIndex));
                                        OneKeyForwardAlbumUtils.access$508(OneKeyForwardAlbumUtils.this);
                                        new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "编辑", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "编辑");
                                                if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                                                    OneKeyForwardAlbumUtils.this.initMMNewPhotoEditUI();
                                                } else {
                                                    OneKeyForwardAlbumUtils.this.initPhotoMMRecordUI();
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
                        boolean unused9 = OneKeyForwardAlbumUtils.this.isSavedVideo = false;
                        int unused10 = OneKeyForwardAlbumUtils.this.num = findAccessibilityNodeInfosByViewId2.get(0).getChildCount();
                        if (OneKeyForwardAlbumUtils.this.startIndex < OneKeyForwardAlbumUtils.this.num) {
                            if (OneKeyForwardAlbumUtils.this.startIndex == OneKeyForwardAlbumUtils.this.num - 1) {
                                boolean unused11 = OneKeyForwardAlbumUtils.this.isSavedCompleted = true;
                            }
                            PerformClickUtils.performLongClick(findAccessibilityNodeInfosByViewId2.get(0).getChild(OneKeyForwardAlbumUtils.this.startIndex));
                            OneKeyForwardAlbumUtils.access$508(OneKeyForwardAlbumUtils.this);
                            new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "编辑", 10, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "编辑");
                                    if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                                        OneKeyForwardAlbumUtils.this.initMMNewPhotoEditUI();
                                    } else {
                                        OneKeyForwardAlbumUtils.this.initPhotoMMRecordUI();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeMain() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, list_circle_layout_id, 100, 200, 50, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    OneKeyForwardAlbumUtils.this.getFriendName();
                    try {
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.list_circle_layout_id);
                        if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                            LogUtils.log("WS_BABY_SnsUserUI.8");
                            return;
                        }
                        int i2 = 0;
                        while (true) {
                            if (i2 >= findViewIdList.size() || !MyApplication.enforceable) {
                                break;
                            }
                            AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(i2);
                            if (accessibilityNodeInfo != null) {
                                try {
                                    Rect rect = new Rect();
                                    accessibilityNodeInfo.getBoundsInScreen(rect);
                                    if (rect.contains(MyApplication.startX, MyApplication.startY)) {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_layout_2_id);
                                        if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_layout_only_text_id);
                                            if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                                                boolean unused = OneKeyForwardAlbumUtils.this.isExist = true;
                                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                                                OneKeyForwardAlbumUtils.this.initSnsCommentDetailUI(100);
                                            }
                                        } else {
                                            boolean unused2 = OneKeyForwardAlbumUtils.this.isExist = true;
                                            if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = findAccessibilityNodeInfosByViewId.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_circle_img_video_id);
                                                if (findAccessibilityNodeInfosByViewId3 == null || findAccessibilityNodeInfosByViewId3.size() <= 0) {
                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                } else {
                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId3.get(0));
                                                }
                                                OneKeyForwardAlbumUtils.this.initSnsGalleryUI();
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                }
                            }
                            i2++;
                        }
                        if (!OneKeyForwardAlbumUtils.this.isExist && MyApplication.enforceable) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    BaseServiceUtils.removeEndView(OneKeyForwardAlbumUtils.this.mMyManager);
                                    OneKeyForwardAlbumUtils.this.mMyManager.toastToUserError("请将【开始按钮】移动到 待转发消息附近");
                                    OneKeyForwardAlbumUtils.this.initData(OneKeyForwardAlbumUtils.this.model);
                                }
                            });
                        }
                    } catch (Exception e2) {
                    }
                }

                public void nuFind() {
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
                PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "从群选择");
                int unused = OneKeyForwardAlbumUtils.this.selectGroupNum = 0;
                OneKeyForwardAlbumUtils.this.initSelectGroupsName();
            }

            public void nuFind() {
            }
        });
    }

    public void executeRealSelectTags() {
        if (this.selectTags == null || "".equals(this.selectTags)) {
            new PerformClickUtils2().checkString(this.autoService, "完成", executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "完成");
                    OneKeyForwardAlbumUtils.this.sendCircle();
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
                int unused = OneKeyForwardAlbumUtils.this.startIndexView = 0;
                OneKeyForwardAlbumUtils.this.whileExecuteTask();
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
                AccessibilityNodeInfo rootInActiveWindow = OneKeyForwardAlbumUtils.this.autoService.getRootInActiveWindow();
                if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_viewgroup_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                    if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                        PerformClickUtils.findTextAndClickFirst(OneKeyForwardAlbumUtils.this.autoService, "搜索");
                    } else {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = findAccessibilityNodeInfosByViewId.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_img_id);
                        if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && findAccessibilityNodeInfosByViewId2.get(0) != null && MyApplication.enforceable) {
                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                        }
                    }
                    OneKeyForwardAlbumUtils.this.searchNickName(OneKeyForwardAlbumUtils.this.friendName);
                }
            }

            public void nuFind() {
            }
        });
    }

    public void getFriendName() {
        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, circle_list_layout_nickname_id);
        List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, "android:id/text1");
        if (findViewIdList == null || findViewIdList.size() <= 0) {
            if (findViewIdList2 != null && findViewIdList2.size() > 0 && findViewIdList2.get(0).getText() != null) {
                this.friendName = findViewIdList2.get(0).getText() + "";
            }
        } else if (findViewIdList.get(0).getText() != null) {
            this.friendName = findViewIdList.get(0).getText() + "";
        }
    }

    public void initAlbumPreviewUI() {
        try {
            new PerformClickUtils2().checkNodeIdsHasSomeOne(this.autoService, video_first_id, img_first_check_layout_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (OneKeyForwardAlbumUtils.this.isSavedVideo && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.AlbumPreviewUI.1");
                        new PerformClickUtils2().checkNodeId(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.video_first_id, 0, 50, 100, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.video_first_id);
                                if (findViewIdList == null || findViewIdList.size() <= 0) {
                                    OneKeyForwardAlbumUtils.this.initAlbumPreviewUI();
                                    return;
                                }
                                PerformClickUtils.performClick(findViewIdList.get(0));
                                if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                                    new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "编辑视频", SocializeConstants.CANCLE_RESULTCODE, 100, 3, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            PerformClickUtils.findViewIdAndClick(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.edit_text_id);
                                            new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "完成", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                public void find(int i) {
                                                    PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "完成");
                                                    OneKeyForwardAlbumUtils.this.sleep(600);
                                                    PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "完成");
                                                    OneKeyForwardAlbumUtils.this.initSnsUploadUI();
                                                }

                                                public void nuFind() {
                                                }
                                            });
                                        }

                                        public void nuFind() {
                                            PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "完成");
                                            OneKeyForwardAlbumUtils.this.initSnsUploadUI();
                                        }
                                    });
                                } else {
                                    new PerformClickUtils2().checkNilString(OneKeyForwardAlbumUtils.this.autoService, "图片和视频", 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            OneKeyForwardAlbumUtils.this.initVideoMMRecordUI();
                                        }

                                        public void nuFind() {
                                        }
                                    });
                                }
                            }

                            public void nuFind() {
                            }
                        });
                    } else if (MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.AlbumPreviewUI.2");
                        try {
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.img_first_check_layout_id);
                            if (findViewIdList != null && findViewIdList.size() > 0) {
                                int access$200 = OneKeyForwardAlbumUtils.this.num - 1;
                                while (true) {
                                    int i2 = access$200;
                                    if (i2 > -1 && MyApplication.enforceable) {
                                        OneKeyForwardAlbumUtils.this.sleep(5);
                                        PerformClickUtils.performClick(findViewIdList.get(i2));
                                        access$200 = i2 - 1;
                                    }
                                }
                            }
                        } catch (Exception e) {
                        }
                        try {
                            OneKeyForwardAlbumUtils.this.sleep(100);
                            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.complete_id);
                            if (findViewIdList2 == null || findViewIdList2.size() <= 0 || !findViewIdList2.get(0).isEnabled()) {
                                OneKeyForwardAlbumUtils.this.initAlbumPreviewUI();
                                return;
                            }
                            findViewIdList2.get(0).performAction(16);
                            OneKeyForwardAlbumUtils.this.initSnsUploadUI();
                        } catch (Exception e2) {
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

    public void initChattingUI() {
        try {
            new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeedSetting + 50, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findViewIdAndClick(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.right_more_id);
                    OneKeyForwardAlbumUtils.this.initSingleChatInfo();
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
        this.model = operationParameterModel;
        this.startIndex = 0;
        this.startIndexView = 0;
        this.isExist = false;
        this.num = 0;
        this.text = "";
        this.friendName = "";
        this.isFirstDelete = true;
        this.isOnlyText = false;
        this.isSavedVideo = false;
        this.isSavedCompleted = false;
        this.isAutoSend = operationParameterModel.isAutoSend();
        this.deleteModel = operationParameterModel.getDeleteMode();
        this.isFastSpeed = operationParameterModel.isFastSpeed();
        this.selectModel = operationParameterModel.getSelectCircleModel();
        this.selectTags = operationParameterModel.getSelectCircleTags();
        this.selectGroups = operationParameterModel.getSelectCircleGroups();
        this.isBackStart = operationParameterModel.isBackStart();
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

    public void initMMNewPhotoEditUI() {
        try {
            LogUtils.log("WS_BABY_MMNewPhotoEditUI");
            new PerformClickUtils2().checkString(this.autoService, "完成", (executeSpeedSetting / 10) + 80, 80, 200, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_MMNewPhotoEditUI_has_完成");
                    PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "完成");
                    new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "保存图片", 80, 80, 100, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "保存图片");
                            new PerformClickUtils2().checkNilString(OneKeyForwardAlbumUtils.this.autoService, "正在处理中", (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 50, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY_MMNewPhotoEditUI_has_完成2");
                                    OneKeyForwardAlbumUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                    PerformClickUtils.hideInputKey(OneKeyForwardAlbumUtils.this.autoService);
                                    new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "详情", 100, 100, 8, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            OneKeyForwardAlbumUtils.this.initSnsCommentDetailUI(100);
                                        }

                                        public void nuFind() {
                                            OneKeyForwardAlbumUtils.this.autoService.performGlobalAction(1);
                                            OneKeyForwardAlbumUtils.this.initSnsCommentDetailUI(100);
                                        }
                                    });
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY_MMNewPhotoEditUI_has_完成3");
                                    PerformClickUtils.performBack(OneKeyForwardAlbumUtils.this.autoService);
                                    OneKeyForwardAlbumUtils.this.initSnsCommentDetailUI(100);
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
            new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "下一步", "完成", 10, 50, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (i == 0) {
                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "下一步");
                        new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "保存图片", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "保存图片");
                                OneKeyForwardAlbumUtils.this.backDescPage(new OneKeyForwardUtils.OnBackDescPageListener() {
                                    public void find() {
                                        OneKeyForwardAlbumUtils.this.initSnsCommentDetailUI(0);
                                    }
                                });
                            }

                            public void nuFind() {
                            }
                        });
                    } else if (i != 1) {
                    } else {
                        if (BaseServiceUtils.wxVersionCode >= 1640) {
                            PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "完成");
                            new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "保存图片", 10, 20, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "保存图片");
                                    new PerformClickUtils2().checkNilString(OneKeyForwardAlbumUtils.this.autoService, "完成", 10, 20, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            OneKeyForwardAlbumUtils.this.backDescPage(new OneKeyForwardUtils.OnBackDescPageListener() {
                                                public void find() {
                                                    OneKeyForwardAlbumUtils.this.initSnsCommentDetailUI(0);
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
                        new PerformClickUtils2().check(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.circle_img_crop_id, 0, 100, 10, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                PerformClickUtils.findViewIdAndClick(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.circle_img_crop_id);
                                new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "确定", OneKeyForwardAlbumUtils.this.isFastSpeed ? 1 : SocializeConstants.CANCLE_RESULTCODE, 5, 200, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "确定");
                                        new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "完成", 10, 20, 50, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "完成");
                                                new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "保存图片", 10, 20, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new PerformClickUtils2.OnCheckListener() {
                                                    public void find(int i) {
                                                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "保存图片");
                                                        new PerformClickUtils2().checkNilString(OneKeyForwardAlbumUtils.this.autoService, "完成", 10, 20, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                                            public void find(int i) {
                                                                OneKeyForwardAlbumUtils.this.backDescPage(new OneKeyForwardUtils.OnBackDescPageListener() {
                                                                    public void find() {
                                                                        OneKeyForwardAlbumUtils.this.initSnsCommentDetailUI(0);
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
                    String str = (String) OneKeyForwardAlbumUtils.this.groupsList.get(0);
                    LogUtils.log("WS_BABY_SelectContactUI_2.name = " + str);
                    if (str.contains("、")) {
                        str = str.split("、")[0];
                    }
                    PerformClickUtils.findViewByIdAndPasteContent(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.list_select_search_id, str);
                    OneKeyForwardAlbumUtils.this.groupsList.remove(0);
                    new PerformClickUtils2().check(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.list_item_id, 600, 100, 30, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            LogUtils.log("WS_BABY_SelectContactUI_5");
                            PerformClickUtils.performClick(PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.list_item_id).get(0));
                            OneKeyForwardAlbumUtils.access$1108(OneKeyForwardAlbumUtils.this);
                            if (OneKeyForwardAlbumUtils.this.groupsList.size() == 0) {
                                if (OneKeyForwardAlbumUtils.this.selectGroupNum == 0) {
                                    PerformClickUtils.performBack(OneKeyForwardAlbumUtils.this.autoService);
                                } else {
                                    PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "确定");
                                }
                                new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "谁可以看", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        OneKeyForwardAlbumUtils.this.executeRealSelectTags();
                                    }

                                    public void nuFind() {
                                    }
                                });
                                return;
                            }
                            OneKeyForwardAlbumUtils.this.initSelectGroupsName();
                        }

                        public void nuFind() {
                            LogUtils.log("WS_BABY_SelectContactUI_4_nufind");
                            if (OneKeyForwardAlbumUtils.this.groupsList.size() == 0) {
                                if (OneKeyForwardAlbumUtils.this.selectGroupNum == 0) {
                                    PerformClickUtils.performBack(OneKeyForwardAlbumUtils.this.autoService);
                                } else {
                                    PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "确定");
                                }
                                new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "谁可以看", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        OneKeyForwardAlbumUtils.this.executeRealSelectTags();
                                    }

                                    public void nuFind() {
                                    }
                                });
                                return;
                            }
                            OneKeyForwardAlbumUtils.this.initSelectGroupsName();
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
                    PerformClickUtils.findViewIdAndClick(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                    OneKeyForwardAlbumUtils.this.executeClickCircle();
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
                    LogUtils.log("WS_BABY_SnsCommentDetailUI2");
                    OneKeyForwardAlbumUtils.this.executeDetail();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSnsGalleryUI() {
        new PerformClickUtils2().checkNodeId(this.autoService, desc_right_down_id, executeSpeed + executeSpeedSetting, 50, 40, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.desc_right_down_id);
                if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                    PerformClickUtils.performClick(findViewIdList.get(0));
                    OneKeyForwardAlbumUtils.this.initSnsCommentDetailUI(100);
                }
            }

            public void nuFind() {
                BaseServiceUtils.getMainHandler().post(new Runnable() {
                    public void run() {
                        BaseServiceUtils.removeEndView(OneKeyForwardAlbumUtils.this.mMyManager);
                        OneKeyForwardAlbumUtils.this.mMyManager.toastToUserError("不能转发非好友的朋友圈！");
                    }
                });
            }
        });
    }

    public void initSnsUploadUI() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, input_send_edit_text_id, executeSpeed + executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (!OneKeyForwardAlbumUtils.this.isSavedVideo || !MyApplication.enforceable) {
                        Context conText = MyApplication.getConText();
                        SPUtils.put(conText, "delete_img", "0#" + OneKeyForwardAlbumUtils.this.num);
                        SPUtils.put(MyApplication.getConText(), "delete_model", Integer.valueOf(OneKeyForwardAlbumUtils.this.deleteModel));
                        FileUtils.saveForward(0, OneKeyForwardAlbumUtils.this.num);
                    } else {
                        SPUtils.put(MyApplication.getConText(), "delete_img", "1#1");
                        SPUtils.put(MyApplication.getConText(), "delete_model", Integer.valueOf(OneKeyForwardAlbumUtils.this.deleteModel));
                        FileUtils.saveForward(1, 1);
                    }
                    if (OneKeyForwardAlbumUtils.this.text != null && !"".equals(OneKeyForwardAlbumUtils.this.text)) {
                        OneKeyForwardAlbumUtils.this.sleep(10);
                        PerformClickUtils.findViewByIdAndPasteContent(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.input_send_edit_text_id, OneKeyForwardAlbumUtils.this.text);
                    }
                    LogUtils.log("WS_BABY.SnsUploadUI");
                    OneKeyForwardAlbumUtils.this.selectFriends();
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
            if (!this.isSavedVideo && MyApplication.enforceable) {
                new PerformClickUtils2().check(this.autoService, long_click_id, (executeSpeedSetting / 10) + 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY_SnsOnlineVideoActivity0");
                        PerformClickUtils.performLongClick(OneKeyForwardAlbumUtils.this.autoService.getRootInActiveWindow().findAccessibilityNodeInfosByViewId(BaseServiceUtils.long_click_id).get(0));
                        LogUtils.log("WS_BABY_SnsOnlineVideoActivity1");
                        new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "保存视频", CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                LogUtils.log("WS_BABY_SnsOnlineVideoActivity2");
                                OneKeyForwardAlbumUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "保存视频");
                                LogUtils.log("WS_BABY_SnsOnlineVideoActivity3");
                                new PerformClickUtils2().checkNilNodeId(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 1200, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        LogUtils.log("WS_BABY_SnsOnlineVideoActivity4");
                                        new PerformClickUtils2().checkNilNodeId(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                LogUtils.log("WS_BABY_SnsOnlineVideoActivity44");
                                                new PerformClickUtils2().checkNilNodeId(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                                    public void find(int i) {
                                                        LogUtils.log("WS_BABY_SnsOnlineVideoActivity444");
                                                        new PerformClickUtils2().checkNilNodeId(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                                            public void find(int i) {
                                                                LogUtils.log("WS_BABY_SnsOnlineVideoActivity4444");
                                                                new PerformClickUtils2().checkNilNodeId(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                                                    public void find(int i) {
                                                                        LogUtils.log("WS_BABY_SnsOnlineVideoActivity44444");
                                                                        new PerformClickUtils2().checkNilNodeId(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                                                            public void find(int i) {
                                                                                LogUtils.log("WS_BABY_SnsOnlineVideoActivity44444");
                                                                                if (MyApplication.enforceable) {
                                                                                    boolean unused = OneKeyForwardAlbumUtils.this.isSavedVideo = true;
                                                                                    PerformClickUtils.performBack(OneKeyForwardAlbumUtils.this.autoService);
                                                                                    OneKeyForwardAlbumUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                                                                    boolean unused2 = OneKeyForwardAlbumUtils.this.isOnlyText = false;
                                                                                    BaseServiceUtils.getMainHandler().post(new Runnable() {
                                                                                        public void run() {
                                                                                            try {
                                                                                                AutoService autoService = OneKeyForwardAlbumUtils.this.autoService;
                                                                                                AlbumUtil.insertImageToMediaStore(autoService, FileUtils.getSDPath() + "/tencent/microMsg/WeiXin");
                                                                                            } catch (Exception e) {
                                                                                            }
                                                                                        }
                                                                                    });
                                                                                    OneKeyForwardAlbumUtils.this.backCount = 25;
                                                                                    OneKeyForwardAlbumUtils.this.backExecute();
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
            } else if (MyApplication.enforceable) {
                LogUtils.log("WS_BABY_SnsOnlineVideoActivity8");
                this.startIndex++;
                PerformClickUtils.performBack(this.autoService);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initVideoMMRecordUI() {
        try {
            System.out.println("WS_BABY_initVideoMMRecordUI");
            new PerformClickUtils2().checkString(this.autoService, "完成", (executeSpeedSetting / 5) + 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    System.out.println("WS_BABY_initVideoMMRecordUI.2");
                    new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "取消", 100, 100, 2, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            System.out.println("WS_BABY_initVideoMMRecordUI.3");
                            LogUtils.log("WS_BABY_0");
                            PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_1");
                            OneKeyForwardAlbumUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                            PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_2");
                            new PerformClickUtils2().checkNilString(OneKeyForwardAlbumUtils.this.autoService, "正在处理", 100, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    OneKeyForwardAlbumUtils.this.initSnsUploadUI();
                                }

                                public void nuFind() {
                                }
                            });
                        }

                        public void nuFind() {
                            System.out.println("WS_BABY_initVideoMMRecordUI.4");
                            LogUtils.log("WS_BABY_00");
                            PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_11");
                            new PerformClickUtils2().checkNilString(OneKeyForwardAlbumUtils.this.autoService, "正在处理", (int) SocializeConstants.CANCLE_RESULTCODE, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    OneKeyForwardAlbumUtils.this.initSnsUploadUI();
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

    public void jumpMyAlbum() {
        new PerformClickUtils2().checkString(this.autoService, "我", 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "我");
                new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "相册", 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "相册");
                        new PerformClickUtils2().checkNodeStringsHasSomeOne(OneKeyForwardAlbumUtils.this.autoService, "我的朋友圈", "前往朋友圈", 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                if (i == 0) {
                                    try {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "我的朋友圈");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (i == 1) {
                                    try {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "前往朋友圈");
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                BaseServiceUtils.getMainHandler().post(new Runnable() {
                                    public void run() {
                                        OneKeyForwardAlbumUtils.this.mMyManager.removeEndView();
                                        OneKeyForwardAlbumUtils.this.mMyManager.removeBottomView();
                                        OneKeyForwardAlbumUtils.this.mMyManager.stopAccessibilityService();
                                        OneKeyForwardAlbumUtils.this.mMyManager.showStartView();
                                        OneKeyForwardAlbumUtils.this.mMyManager.showBackView();
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

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
    }

    public void searchNickName(final String str) {
        try {
            LogUtils.log("WS_BABY_searchNickName=" + str);
            if (str != null && !"".equals(str) && MyApplication.enforceable) {
                new PerformClickUtils2().checkNodeId(this.autoService, search_id, executeSpeedSetting + 50, 100, 200, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY_searchNickName.0");
                        PerformClickUtils.findViewIdAndClick(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.search_id);
                        OneKeyForwardAlbumUtils.this.sleep(350);
                        PerformClickUtils.inputText(OneKeyForwardAlbumUtils.this.autoService, str);
                        new PerformClickUtils2().checkNodeStringsHasSomeOne(OneKeyForwardAlbumUtils.this.autoService, "联系人", "最常使用", CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 30, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                                boolean z;
                                LogUtils.log("WS_BABY_searchNickName.1");
                                AccessibilityNodeInfo rootInActiveWindow = OneKeyForwardAlbumUtils.this.autoService.getRootInActiveWindow();
                                if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_name_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                    boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(OneKeyForwardAlbumUtils.this.autoService, "联系人");
                                    boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(OneKeyForwardAlbumUtils.this.autoService, "最常使用");
                                    if ((findNodeIsExistByText || findNodeIsExistByText2) && MyApplication.enforceable) {
                                        int i2 = 0;
                                        while (true) {
                                            try {
                                                if (i2 >= findAccessibilityNodeInfosByViewId.size()) {
                                                    z = false;
                                                    break;
                                                }
                                                AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i2);
                                                if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                                                    if (str.equals(accessibilityNodeInfo.getText() + "")) {
                                                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(i2));
                                                        OneKeyForwardAlbumUtils.this.initChattingUI();
                                                        z = true;
                                                        break;
                                                    }
                                                }
                                                i2++;
                                            } catch (Exception e) {
                                                LogUtils.log("WS_BABY.initChattingUI");
                                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                                OneKeyForwardAlbumUtils.this.initChattingUI();
                                                return;
                                            }
                                        }
                                        if (!z) {
                                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                            OneKeyForwardAlbumUtils.this.initChattingUI();
                                        }
                                    }
                                }
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
        } else {
            new PerformClickUtils2().checkString(this.autoService, "谁可以看", executeSpeed + (executeSpeedSetting / 5), 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "谁可以看");
                    new PerformClickUtils2().checkStringsAll(OneKeyForwardAlbumUtils.this.autoService, "私密", "公开", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            if ("私密".equals(OneKeyForwardAlbumUtils.this.selectModel) || "公开".equals(OneKeyForwardAlbumUtils.this.selectModel)) {
                                PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, OneKeyForwardAlbumUtils.this.selectModel);
                                PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "完成");
                                OneKeyForwardAlbumUtils.this.sendCircle();
                            } else if ("部分可见".equals(OneKeyForwardAlbumUtils.this.selectModel)) {
                                new PerformClickUtils2().checkNodeStringsHasSomeOne(OneKeyForwardAlbumUtils.this.autoService, "部分可见", "选中的朋友可见", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        if (i == 0) {
                                            PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "部分可见");
                                        } else {
                                            PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "选中的朋友可见");
                                        }
                                        OneKeyForwardAlbumUtils.this.executeRealSelect();
                                    }

                                    public void nuFind() {
                                    }
                                });
                            } else if ("不给谁看".equals(OneKeyForwardAlbumUtils.this.selectModel)) {
                                new PerformClickUtils2().checkNodeStringsHasSomeOne(OneKeyForwardAlbumUtils.this.autoService, "不给谁看", "选中的朋友不可见", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        if (i == 0) {
                                            PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "不给谁看");
                                        } else {
                                            PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "选中的朋友不可见");
                                        }
                                        OneKeyForwardAlbumUtils.this.executeRealSelect();
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
        }
    }

    public void sendCircle() {
        new PerformClickUtils2().checkString(this.autoService, "发表", executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_SEND" + OneKeyForwardAlbumUtils.this.isAutoSend);
                if (OneKeyForwardAlbumUtils.this.isAutoSend && MyApplication.enforceable) {
                    PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "发表");
                    LogUtils.log("WS_BABY_SEND_friendName" + OneKeyForwardAlbumUtils.this.friendName);
                    if (!OneKeyForwardAlbumUtils.this.isBackStart) {
                        MyApplication.enforceable = false;
                        BaseServiceUtils.removeEndView(OneKeyForwardAlbumUtils.this.mMyManager);
                    } else if (OneKeyForwardAlbumUtils.this.friendName == null || "".equals(OneKeyForwardAlbumUtils.this.friendName)) {
                        MyApplication.enforceable = false;
                        BaseServiceUtils.removeEndView(OneKeyForwardAlbumUtils.this.mMyManager);
                    } else {
                        MyApplication.enforceable = true;
                        OneKeyForwardAlbumUtils.this.cloneNext();
                    }
                } else if (MyApplication.enforceable) {
                    MyApplication.enforceable = false;
                    BaseServiceUtils.removeEndView(OneKeyForwardAlbumUtils.this.mMyManager);
                }
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
