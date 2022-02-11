package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.fileutil.ListUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.List;

public class OneKeyWxImagesSlicerUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static OneKeyWxImagesSlicerUtils instance;
    /* access modifiers changed from: private */
    public boolean isAutoSend = false;
    /* access modifiers changed from: private */
    public int num = 0;
    /* access modifiers changed from: private */
    public int residenceTime = 300;
    /* access modifiers changed from: private */
    public String text = "";

    private OneKeyWxImagesSlicerUtils() {
    }

    public static OneKeyWxImagesSlicerUtils getInstance() {
        instance = new OneKeyWxImagesSlicerUtils();
        return instance;
    }

    public void endViewDismiss() {
    }

    public void endViewShow() {
    }

    public void executeMain() {
        try {
            if (this.num == 0) {
                initSnsUploadUI();
            } else {
                mainExecute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void froward(OperationParameterModel operationParameterModel) {
        try {
            this.isAutoSend = operationParameterModel.isAutoSend();
            this.num = operationParameterModel.getMaterialPicCount();
            this.text = operationParameterModel.getMaterialText();
            this.mMyManager.setMiddleViewListener(this);
            this.mMyManager.setEndViewListener(this);
            LogUtils.log("WS_BABY_endViewShow");
            executeMain();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initAlbumPreviewUI() {
        if (MyApplication.enforceable) {
            LogUtils.log("WS_BABY.AlbumPreviewUI.1");
            new PerformClickUtils2().checkNodeId(this.autoService, img_first_check_layout_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    new Thread(new Runnable() {
                        /* JADX WARNING: Removed duplicated region for block: B:25:0x0068 A[Catch:{ Exception -> 0x008f }] */
                        /* JADX WARNING: Removed duplicated region for block: B:30:0x0091 A[Catch:{ Exception -> 0x008f }] */
                        public void run() {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                            try {
                                AccessibilityNodeInfo rootInActiveWindow = OneKeyWxImagesSlicerUtils.this.autoService.getRootInActiveWindow();
                                if (rootInActiveWindow != null && MyApplication.enforceable) {
                                    try {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.img_first_check_layout_id);
                                        if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0) {
                                            LogUtils.log("WS_BABY.AlbumPreviewUI.18");
                                            LogUtils.log("WS_BABY.AlbumPreviewUI.19");
                                            findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.complete_id);
                                            if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || !findAccessibilityNodeInfosByViewId.get(0).isEnabled()) {
                                                OneKeyWxImagesSlicerUtils.this.initAlbumPreviewUI();
                                            } else if (MyApplication.enforceable) {
                                                findAccessibilityNodeInfosByViewId.get(0).performAction(16);
                                                OneKeyWxImagesSlicerUtils.this.sleep(OneKeyWxImagesSlicerUtils.this.residenceTime);
                                                OneKeyWxImagesSlicerUtils.this.initSnsUploadUI();
                                            }
                                        } else {
                                            LogUtils.log("WS_BABY.AlbumPreviewUI.17");
                                            int access$000 = OneKeyWxImagesSlicerUtils.this.num - 1;
                                            while (access$000 > -1) {
                                                if (MyApplication.enforceable) {
                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(access$000));
                                                    access$000--;
                                                } else {
                                                    return;
                                                }
                                            }
                                            LogUtils.log("WS_BABY.AlbumPreviewUI.19");
                                            findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.complete_id);
                                            if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || !findAccessibilityNodeInfosByViewId.get(0).isEnabled()) {
                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                            } catch (Exception e2) {
                            }
                        }
                    }).start();
                }

                public void nuFind() {
                }
            });
        }
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.isAutoSend = operationParameterModel.isAutoSend();
        this.num = operationParameterModel.getMaterialPicCount();
        this.text = operationParameterModel.getMaterialText();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        LogUtils.log("WS_BABY_initData");
    }

    public void initSnsUploadUI() {
        LogUtils.log("WS_BABY_AUTO_SEND.0" + this.isAutoSend + ListUtils.DEFAULT_JOIN_SEPARATOR + MyApplication.enforceable);
        if (this.autoService == null || !MyApplication.enforceable) {
            LogUtils.log("WS_BABY_AUTO_SEND.autoService.null");
        } else {
            new PerformClickUtils2().checkNodeId(this.autoService, input_send_edit_text_id, executeSpeed + executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyWxImagesSlicerUtils.this.autoService, BaseServiceUtils.input_send_edit_text_id);
                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                        BaseServiceUtils.getMainHandler().post(new Runnable() {
                            public void run() {
                                if (OneKeyWxImagesSlicerUtils.this.text != null && !"".equals(OneKeyWxImagesSlicerUtils.this.text)) {
                                    OneKeyWxImagesSlicerUtils.this.sleep(10);
                                    PerformClickUtils.findViewByIdAndPasteContent(OneKeyWxImagesSlicerUtils.this.autoService, BaseServiceUtils.input_send_edit_text_id, OneKeyWxImagesSlicerUtils.this.text);
                                }
                                LogUtils.log("WS_BABY_AUTO_SEND" + OneKeyWxImagesSlicerUtils.this.isAutoSend);
                                if (OneKeyWxImagesSlicerUtils.this.isAutoSend) {
                                    LogUtils.log("WS_BABY_AUTO_SEND");
                                    PerformClickUtils.findTextAndClick(OneKeyWxImagesSlicerUtils.this.autoService, "发表");
                                } else {
                                    LogUtils.log("WS_BABY_NO_AUTO_SEND");
                                }
                                MyApplication.enforceable = false;
                                OneKeyWxImagesSlicerUtils.this.mMyManager.stopAccessibilityService();
                                BaseServiceUtils.removeEndView(OneKeyWxImagesSlicerUtils.this.mMyManager);
                                OneKeyWxImagesSlicerUtils.this.mMyManager.showBackView();
                            }
                        });
                    }
                }

                public void nuFind() {
                }
            });
        }
    }

    public void mainExecute() {
        LogUtils.log("WS_BABY.SnsCommentDetailUI.2");
        if (MyApplication.enforceable) {
            LogUtils.log("WS_BABY.SnsCommentDetailUI.4");
            new PerformClickUtils2().check(this.autoService, send_second_img, executeSpeed + executeSpeedSetting, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.performClick(PerformClickUtils.findViewIdList((AccessibilityService) OneKeyWxImagesSlicerUtils.this.autoService, BaseServiceUtils.send_second_img).get(1));
                    new PerformClickUtils2().checkString(OneKeyWxImagesSlicerUtils.this.autoService, "从相册选择", 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(OneKeyWxImagesSlicerUtils.this.autoService, "从相册选择");
                            OneKeyWxImagesSlicerUtils.this.initAlbumPreviewUI();
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

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
    }
}
