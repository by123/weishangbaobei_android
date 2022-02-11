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

    public void endViewDismiss() {
    }

    public void endViewShow() {
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
    }

    private OneKeyWxImagesSlicerUtils() {
    }

    public static OneKeyWxImagesSlicerUtils getInstance() {
        instance = new OneKeyWxImagesSlicerUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.isAutoSend = operationParameterModel.isAutoSend();
        this.num = operationParameterModel.getMaterialPicCount();
        this.text = operationParameterModel.getMaterialText();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        LogUtils.log("WS_BABY_initData");
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
                public void nuFind() {
                }

                public void find(int i) {
                    new Thread(new Runnable() {
                        /* JADX WARNING: Removed duplicated region for block: B:27:0x0066 A[Catch:{ Exception -> 0x0093 }] */
                        /* JADX WARNING: Removed duplicated region for block: B:30:0x008c A[Catch:{ Exception -> 0x0093 }] */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public void run() {
                            /*
                                r4 = this;
                                com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils$1 r0 = com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils.AnonymousClass1.this     // Catch:{ Exception -> 0x0093 }
                                com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils r0 = com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils.this     // Catch:{ Exception -> 0x0093 }
                                com.wx.assistants.service.AutoService r0 = r0.autoService     // Catch:{ Exception -> 0x0093 }
                                android.view.accessibility.AccessibilityNodeInfo r0 = r0.getRootInActiveWindow()     // Catch:{ Exception -> 0x0093 }
                                if (r0 == 0) goto L_0x0093
                                boolean r1 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0093 }
                                if (r1 == 0) goto L_0x0093
                                java.lang.String r1 = com.wx.assistants.service_utils.BaseServiceUtils.img_first_check_layout_id     // Catch:{ Exception -> 0x0046 }
                                java.util.List r1 = r0.findAccessibilityNodeInfosByViewId(r1)     // Catch:{ Exception -> 0x0046 }
                                if (r1 == 0) goto L_0x0041
                                int r2 = r1.size()     // Catch:{ Exception -> 0x0046 }
                                if (r2 <= 0) goto L_0x0041
                                java.lang.String r2 = "WS_BABY.AlbumPreviewUI.17"
                                com.wx.assistants.utils.LogUtils.log(r2)     // Catch:{ Exception -> 0x0046 }
                                com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils$1 r2 = com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils.AnonymousClass1.this     // Catch:{ Exception -> 0x0046 }
                                com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils r2 = com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils.this     // Catch:{ Exception -> 0x0046 }
                                int r2 = r2.num     // Catch:{ Exception -> 0x0046 }
                                int r2 = r2 + -1
                            L_0x002d:
                                r3 = -1
                                if (r2 <= r3) goto L_0x0046
                                boolean r3 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0046 }
                                if (r3 != 0) goto L_0x0035
                                return
                            L_0x0035:
                                java.lang.Object r3 = r1.get(r2)     // Catch:{ Exception -> 0x0046 }
                                android.view.accessibility.AccessibilityNodeInfo r3 = (android.view.accessibility.AccessibilityNodeInfo) r3     // Catch:{ Exception -> 0x0046 }
                                com.wx.assistants.utils.PerformClickUtils.performClick(r3)     // Catch:{ Exception -> 0x0046 }
                                int r2 = r2 + -1
                                goto L_0x002d
                            L_0x0041:
                                java.lang.String r1 = "WS_BABY.AlbumPreviewUI.18"
                                com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x0046 }
                            L_0x0046:
                                java.lang.String r1 = "WS_BABY.AlbumPreviewUI.19"
                                com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x0093 }
                                java.lang.String r1 = com.wx.assistants.service_utils.BaseServiceUtils.complete_id     // Catch:{ Exception -> 0x0093 }
                                java.util.List r0 = r0.findAccessibilityNodeInfosByViewId(r1)     // Catch:{ Exception -> 0x0093 }
                                if (r0 == 0) goto L_0x008c
                                int r1 = r0.size()     // Catch:{ Exception -> 0x0093 }
                                if (r1 <= 0) goto L_0x008c
                                r1 = 0
                                java.lang.Object r2 = r0.get(r1)     // Catch:{ Exception -> 0x0093 }
                                android.view.accessibility.AccessibilityNodeInfo r2 = (android.view.accessibility.AccessibilityNodeInfo) r2     // Catch:{ Exception -> 0x0093 }
                                boolean r2 = r2.isEnabled()     // Catch:{ Exception -> 0x0093 }
                                if (r2 == 0) goto L_0x008c
                                boolean r2 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0093 }
                                if (r2 == 0) goto L_0x0093
                                java.lang.Object r0 = r0.get(r1)     // Catch:{ Exception -> 0x0093 }
                                android.view.accessibility.AccessibilityNodeInfo r0 = (android.view.accessibility.AccessibilityNodeInfo) r0     // Catch:{ Exception -> 0x0093 }
                                r1 = 16
                                r0.performAction(r1)     // Catch:{ Exception -> 0x0093 }
                                com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils$1 r0 = com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils.AnonymousClass1.this     // Catch:{ Exception -> 0x0093 }
                                com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils r0 = com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils.this     // Catch:{ Exception -> 0x0093 }
                                com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils$1 r1 = com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils.AnonymousClass1.this     // Catch:{ Exception -> 0x0093 }
                                com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils r1 = com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils.this     // Catch:{ Exception -> 0x0093 }
                                int r1 = r1.residenceTime     // Catch:{ Exception -> 0x0093 }
                                r0.sleep(r1)     // Catch:{ Exception -> 0x0093 }
                                com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils$1 r0 = com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils.AnonymousClass1.this     // Catch:{ Exception -> 0x0093 }
                                com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils r0 = com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils.this     // Catch:{ Exception -> 0x0093 }
                                r0.initSnsUploadUI()     // Catch:{ Exception -> 0x0093 }
                                goto L_0x0093
                            L_0x008c:
                                com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils$1 r0 = com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils.AnonymousClass1.this     // Catch:{ Exception -> 0x0093 }
                                com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils r0 = com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils.this     // Catch:{ Exception -> 0x0093 }
                                r0.initAlbumPreviewUI()     // Catch:{ Exception -> 0x0093 }
                            L_0x0093:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils.AnonymousClass1.AnonymousClass1.run():void");
                        }
                    }).start();
                }
            });
        }
    }

    public void initSnsUploadUI() {
        LogUtils.log("WS_BABY_AUTO_SEND.0" + this.isAutoSend + ListUtils.DEFAULT_JOIN_SEPARATOR + MyApplication.enforceable);
        if (this.autoService == null || !MyApplication.enforceable) {
            LogUtils.log("WS_BABY_AUTO_SEND.autoService.null");
            return;
        }
        new PerformClickUtils2().checkNodeId(this.autoService, input_send_edit_text_id, executeSpeedSetting + executeSpeed, 100, 600, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

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
        });
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

    public void mainExecute() {
        LogUtils.log("WS_BABY.SnsCommentDetailUI.2");
        if (MyApplication.enforceable) {
            LogUtils.log("WS_BABY.SnsCommentDetailUI.4");
            new PerformClickUtils2().check(this.autoService, send_second_img, executeSpeedSetting + executeSpeed, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.performClick(PerformClickUtils.findViewIdList((AccessibilityService) OneKeyWxImagesSlicerUtils.this.autoService, BaseServiceUtils.send_second_img).get(1));
                    new PerformClickUtils2().checkString(OneKeyWxImagesSlicerUtils.this.autoService, "从相册选择", 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(OneKeyWxImagesSlicerUtils.this.autoService, "从相册选择");
                            OneKeyWxImagesSlicerUtils.this.initAlbumPreviewUI();
                        }
                    });
                }
            });
        }
    }
}
