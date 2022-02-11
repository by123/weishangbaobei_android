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

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
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

    private OneKeyForwardAlbumUtils() {
    }

    public static OneKeyForwardAlbumUtils getInstance() {
        instance = new OneKeyForwardAlbumUtils();
        return instance;
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

    public void initSnsUploadUI() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, input_send_edit_text_id, executeSpeed + executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

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

    public void initAlbumPreviewUI() {
        try {
            new PerformClickUtils2().checkNodeIdsHasSomeOne(this.autoService, video_first_id, img_first_check_layout_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                /* JADX WARNING: Can't wrap try/catch for region: R(9:7|8|9|10|11|(2:15|(2:16|(2:18|(2:33|20)(2:21|22))(0)))(0)|23|24|(2:31|34)(2:30|38)) */
                /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0068 */
                /* JADX WARNING: Removed duplicated region for block: B:30:0x008e A[Catch:{ Exception -> 0x00a4 }] */
                /* JADX WARNING: Removed duplicated region for block: B:31:0x009f A[Catch:{ Exception -> 0x00a4 }] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void find(int r8) {
                    /*
                        r7 = this;
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r8 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this
                        boolean r8 = r8.isSavedVideo
                        if (r8 == 0) goto L_0x002b
                        boolean r8 = com.wx.assistants.application.MyApplication.enforceable
                        if (r8 == 0) goto L_0x002b
                        java.lang.String r8 = "WS_BABY.AlbumPreviewUI.1"
                        com.wx.assistants.utils.LogUtils.log(r8)
                        com.wx.assistants.utils.PerformClickUtils2 r0 = new com.wx.assistants.utils.PerformClickUtils2
                        r0.<init>()
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r8 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this
                        com.wx.assistants.service.AutoService r1 = r8.autoService
                        java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.video_first_id
                        r3 = 0
                        r4 = 50
                        r5 = 100
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils$3$1 r6 = new com.wx.assistants.service_utils.OneKeyForwardAlbumUtils$3$1
                        r6.<init>()
                        r0.checkNodeId(r1, r2, r3, r4, r5, r6)
                        goto L_0x00a4
                    L_0x002b:
                        boolean r8 = com.wx.assistants.application.MyApplication.enforceable
                        if (r8 == 0) goto L_0x00a4
                        java.lang.String r8 = "WS_BABY.AlbumPreviewUI.2"
                        com.wx.assistants.utils.LogUtils.log(r8)     // Catch:{ Exception -> 0x00a4 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r8 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0068 }
                        com.wx.assistants.service.AutoService r8 = r8.autoService     // Catch:{ Exception -> 0x0068 }
                        java.lang.String r0 = com.wx.assistants.service_utils.BaseServiceUtils.img_first_check_layout_id     // Catch:{ Exception -> 0x0068 }
                        java.util.List r8 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r8, (java.lang.String) r0)     // Catch:{ Exception -> 0x0068 }
                        if (r8 == 0) goto L_0x0068
                        int r0 = r8.size()     // Catch:{ Exception -> 0x0068 }
                        if (r0 <= 0) goto L_0x0068
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r0 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0068 }
                        int r0 = r0.num     // Catch:{ Exception -> 0x0068 }
                        int r0 = r0 + -1
                    L_0x004e:
                        r1 = -1
                        if (r0 <= r1) goto L_0x0068
                        boolean r1 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0068 }
                        if (r1 != 0) goto L_0x0056
                        goto L_0x0068
                    L_0x0056:
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0068 }
                        r2 = 5
                        r1.sleep(r2)     // Catch:{ Exception -> 0x0068 }
                        java.lang.Object r1 = r8.get(r0)     // Catch:{ Exception -> 0x0068 }
                        android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1     // Catch:{ Exception -> 0x0068 }
                        com.wx.assistants.utils.PerformClickUtils.performClick(r1)     // Catch:{ Exception -> 0x0068 }
                        int r0 = r0 + -1
                        goto L_0x004e
                    L_0x0068:
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r8 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x00a4 }
                        r0 = 100
                        r8.sleep(r0)     // Catch:{ Exception -> 0x00a4 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r8 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x00a4 }
                        com.wx.assistants.service.AutoService r8 = r8.autoService     // Catch:{ Exception -> 0x00a4 }
                        java.lang.String r0 = com.wx.assistants.service_utils.BaseServiceUtils.complete_id     // Catch:{ Exception -> 0x00a4 }
                        java.util.List r8 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r8, (java.lang.String) r0)     // Catch:{ Exception -> 0x00a4 }
                        if (r8 == 0) goto L_0x009f
                        int r0 = r8.size()     // Catch:{ Exception -> 0x00a4 }
                        if (r0 <= 0) goto L_0x009f
                        r0 = 0
                        java.lang.Object r1 = r8.get(r0)     // Catch:{ Exception -> 0x00a4 }
                        android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1     // Catch:{ Exception -> 0x00a4 }
                        boolean r1 = r1.isEnabled()     // Catch:{ Exception -> 0x00a4 }
                        if (r1 == 0) goto L_0x009f
                        java.lang.Object r8 = r8.get(r0)     // Catch:{ Exception -> 0x00a4 }
                        android.view.accessibility.AccessibilityNodeInfo r8 = (android.view.accessibility.AccessibilityNodeInfo) r8     // Catch:{ Exception -> 0x00a4 }
                        r0 = 16
                        r8.performAction(r0)     // Catch:{ Exception -> 0x00a4 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r8 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x00a4 }
                        r8.initSnsUploadUI()     // Catch:{ Exception -> 0x00a4 }
                        goto L_0x00a4
                    L_0x009f:
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r8 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x00a4 }
                        r8.initAlbumPreviewUI()     // Catch:{ Exception -> 0x00a4 }
                    L_0x00a4:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.AnonymousClass3.find(int):void");
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
                                                                                            } catch (Exception unused) {
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

    public void initMMNewPhotoEditUI() {
        try {
            LogUtils.log("WS_BABY_MMNewPhotoEditUI");
            new PerformClickUtils2().checkString(this.autoService, "完成", (executeSpeedSetting / 10) + 80, 80, 200, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    LogUtils.log("WS_BABY_MMNewPhotoEditUI_has_完成");
                    PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "完成");
                    new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "保存图片", 80, 80, 100, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

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
                    LogUtils.log("WS_BABY_SnsCommentDetailUI2");
                    OneKeyForwardAlbumUtils.this.executeDetail();
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
                System.out.println("WS_BABY_isOnlyText = false");
                getMainHandler().post(new Runnable() {
                    public void run() {
                        try {
                            AutoService autoService = OneKeyForwardAlbumUtils.this.autoService;
                            AlbumUtil.insertImageToMediaStore(autoService, FileUtils.getSDPath() + "/tencent/microMsg/WeiXin");
                        } catch (Exception unused) {
                        }
                    }
                });
                this.backCount = 25;
                backExecute();
                return;
            }
            new PerformClickUtils2().checkNodeIdsHasSomeOne3(this.autoService, list_layout_only_text_id, circle_list_layout_img_id, desc_video_relativeLayout_id, 10, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

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
                                    public void nuFind() {
                                    }

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
                                public void nuFind() {
                                }

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
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "编辑");
                                        if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                                            OneKeyForwardAlbumUtils.this.initMMNewPhotoEditUI();
                                        } else {
                                            OneKeyForwardAlbumUtils.this.initPhotoMMRecordUI();
                                        }
                                    }
                                });
                            }
                        }
                    } else if (OneKeyForwardAlbumUtils.this.text == null || "".equals(OneKeyForwardAlbumUtils.this.text)) {
                        new PerformClickUtils2().checkNodeId(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.list_layout_only_text_id, 10, 100, 200, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

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
                                            public void nuFind() {
                                            }

                                            public void find(int i) {
                                                PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "编辑");
                                                if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                                                    OneKeyForwardAlbumUtils.this.initMMNewPhotoEditUI();
                                                } else {
                                                    OneKeyForwardAlbumUtils.this.initPhotoMMRecordUI();
                                                }
                                            }
                                        });
                                    }
                                }
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
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "编辑");
                                    if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                                        OneKeyForwardAlbumUtils.this.initMMNewPhotoEditUI();
                                    } else {
                                        OneKeyForwardAlbumUtils.this.initPhotoMMRecordUI();
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

    public void backExecute() {
        if (this.backCount >= 0 && MyApplication.enforceable) {
            new Thread(new Runnable() {
                /* JADX WARNING: Code restructure failed: missing block: B:16:0x0090, code lost:
                    if (r2.booleanValue() != false) goto L_0x0092;
                 */
                /* JADX WARNING: Removed duplicated region for block: B:27:0x00f5 A[Catch:{ Exception -> 0x0176 }] */
                /* JADX WARNING: Removed duplicated region for block: B:28:0x0151 A[Catch:{ Exception -> 0x0176 }] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r17 = this;
                        r0 = r17
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        r2 = 100
                        r1.sleep(r2)     // Catch:{ Exception -> 0x0176 }
                        java.lang.String r1 = "WS_BABY.backExecute.1"
                        com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x0176 }
                        boolean r1 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0176 }
                        if (r1 == 0) goto L_0x0176
                        java.lang.String r1 = "WS_BABY.backExecute.2"
                        com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0176 }
                        java.lang.String r2 = "返回"
                        boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x0176 }
                        java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r2 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service.AutoService r2 = r2.autoService     // Catch:{ Exception -> 0x0176 }
                        java.lang.String r3 = "拍照分享"
                        boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x0176 }
                        java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r3 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service.AutoService r3 = r3.autoService     // Catch:{ Exception -> 0x0176 }
                        java.lang.String r4 = "查看消息"
                        boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r3, r4)     // Catch:{ Exception -> 0x0176 }
                        java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r4 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service.AutoService r4 = r4.autoService     // Catch:{ Exception -> 0x0176 }
                        java.lang.String r5 = "通讯录"
                        boolean r4 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r4, r5)     // Catch:{ Exception -> 0x0176 }
                        java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r5 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service.AutoService r5 = r5.autoService     // Catch:{ Exception -> 0x0176 }
                        java.lang.String r6 = "发现"
                        boolean r5 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r5, r6)     // Catch:{ Exception -> 0x0176 }
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r6 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service.AutoService r6 = r6.autoService     // Catch:{ Exception -> 0x0176 }
                        java.lang.String r7 = com.wx.assistants.service_utils.BaseServiceUtils.home_tab_layout_id     // Catch:{ Exception -> 0x0176 }
                        boolean r6 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r6, (java.lang.String) r7)     // Catch:{ Exception -> 0x0176 }
                        java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r7 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        boolean r7 = r7.isOnlyText     // Catch:{ Exception -> 0x0176 }
                        if (r7 == 0) goto L_0x0086
                        boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x0176 }
                        if (r1 == 0) goto L_0x00e3
                        boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x0176 }
                        if (r1 == 0) goto L_0x00e3
                        boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x0176 }
                        if (r1 != 0) goto L_0x00e3
                        goto L_0x0092
                    L_0x0086:
                        boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x0176 }
                        if (r1 == 0) goto L_0x00e3
                        boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x0176 }
                        if (r1 == 0) goto L_0x00e3
                    L_0x0092:
                        java.lang.String r1 = "WS_BABY.backExecute.3"
                        com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        boolean r1 = r1.isOnlyText     // Catch:{ Exception -> 0x0176 }
                        if (r1 == 0) goto L_0x00ba
                        java.lang.String r1 = "WS_BABY.backExecute.4"
                        com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0176 }
                        java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.right_more_id     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.utils.PerformClickUtils.performLongClick(r1, r2)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        r2 = 0
                        boolean unused = r1.isSavedCompleted = r2     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        r1.initSnsUploadUI()     // Catch:{ Exception -> 0x0176 }
                        goto L_0x0176
                    L_0x00ba:
                        java.lang.String r1 = "WS_BABY.backExecute.5"
                        com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0176 }
                        java.lang.String r2 = "拍照分享"
                        com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.utils.PerformClickUtils2 r3 = new com.wx.assistants.utils.PerformClickUtils2     // Catch:{ Exception -> 0x0176 }
                        r3.<init>()     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service.AutoService r4 = r1.autoService     // Catch:{ Exception -> 0x0176 }
                        java.lang.String r5 = "从相册选择"
                        r6 = 10
                        r7 = 50
                        r8 = 200(0xc8, float:2.8E-43)
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils$9$1 r9 = new com.wx.assistants.service_utils.OneKeyForwardAlbumUtils$9$1     // Catch:{ Exception -> 0x0176 }
                        r9.<init>()     // Catch:{ Exception -> 0x0176 }
                        r3.checkString(r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0176 }
                        goto L_0x0176
                    L_0x00e3:
                        boolean r1 = r4.booleanValue()     // Catch:{ Exception -> 0x0176 }
                        if (r1 == 0) goto L_0x0151
                        boolean r1 = r5.booleanValue()     // Catch:{ Exception -> 0x0176 }
                        if (r1 == 0) goto L_0x0151
                        boolean r1 = r6.booleanValue()     // Catch:{ Exception -> 0x0176 }
                        if (r1 == 0) goto L_0x0151
                        java.lang.String r1 = "WS_BABY.backExecute.8"
                        com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0176 }
                        java.lang.String r2 = "发现"
                        com.wx.assistants.utils.PerformClickUtils.findTabTextAndClick(r1, r2)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0176 }
                        java.lang.String r2 = "发现"
                        com.wx.assistants.utils.PerformClickUtils.findTabTextAndClick(r1, r2)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        r2 = 50
                        r1.sleep(r2)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0176 }
                        java.lang.String r2 = "发现"
                        com.wx.assistants.utils.PerformClickUtils.findTabTextAndClick(r1, r2)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.utils.PerformClickUtils2 r3 = new com.wx.assistants.utils.PerformClickUtils2     // Catch:{ Exception -> 0x0176 }
                        r3.<init>()     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service.AutoService r4 = r1.autoService     // Catch:{ Exception -> 0x0176 }
                        java.lang.String r5 = "朋友圈"
                        r6 = 10
                        r7 = 50
                        r8 = 100
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils$9$2 r9 = new com.wx.assistants.service_utils.OneKeyForwardAlbumUtils$9$2     // Catch:{ Exception -> 0x0176 }
                        r9.<init>()     // Catch:{ Exception -> 0x0176 }
                        r3.checkString(r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.utils.PerformClickUtils2 r10 = new com.wx.assistants.utils.PerformClickUtils2     // Catch:{ Exception -> 0x0176 }
                        r10.<init>()     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service.AutoService r11 = r1.autoService     // Catch:{ Exception -> 0x0176 }
                        java.lang.String r12 = "拍照分享"
                        r13 = 10
                        r14 = 50
                        r15 = 300(0x12c, float:4.2E-43)
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils$9$3 r1 = new com.wx.assistants.service_utils.OneKeyForwardAlbumUtils$9$3     // Catch:{ Exception -> 0x0176 }
                        r1.<init>()     // Catch:{ Exception -> 0x0176 }
                        r16 = r1
                        r10.checkString(r11, r12, r13, r14, r15, r16)     // Catch:{ Exception -> 0x0176 }
                        goto L_0x0176
                    L_0x0151:
                        java.lang.String r1 = "WS_BABY.backExecute.19"
                        com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.utils.PerformClickUtils.performBackNoDeep(r1)     // Catch:{ Exception -> 0x0176 }
                        android.os.Handler r1 = com.wx.assistants.service_utils.BaseServiceUtils.getMainHandler()     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils$9$4 r2 = new com.wx.assistants.service_utils.OneKeyForwardAlbumUtils$9$4     // Catch:{ Exception -> 0x0176 }
                        r2.<init>()     // Catch:{ Exception -> 0x0176 }
                        r1.post(r2)     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        int r2 = r1.backCount     // Catch:{ Exception -> 0x0176 }
                        int r2 = r2 + -1
                        r1.backCount = r2     // Catch:{ Exception -> 0x0176 }
                        com.wx.assistants.service_utils.OneKeyForwardAlbumUtils r1 = com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.this     // Catch:{ Exception -> 0x0176 }
                        r1.backExecute()     // Catch:{ Exception -> 0x0176 }
                    L_0x0176:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.AnonymousClass9.run():void");
                }
            }).start();
        }
    }

    public void initPhotoMMRecordUI() {
        try {
            new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "下一步", "完成", 10, 50, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    int i2 = i;
                    if (i2 == 0) {
                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "下一步");
                        new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "保存图片", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "保存图片");
                                OneKeyForwardAlbumUtils.this.backDescPage(new OneKeyForwardUtils.OnBackDescPageListener() {
                                    public void find() {
                                        OneKeyForwardAlbumUtils.this.initSnsCommentDetailUI(0);
                                    }
                                });
                            }
                        });
                    } else if (i2 != 1) {
                    } else {
                        if (BaseServiceUtils.wxVersionCode >= 1640) {
                            PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "完成");
                            new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "保存图片", 10, 20, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "保存图片");
                                    new PerformClickUtils2().checkNilString(OneKeyForwardAlbumUtils.this.autoService, "完成", 10, 20, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            OneKeyForwardAlbumUtils.this.backDescPage(new OneKeyForwardUtils.OnBackDescPageListener() {
                                                public void find() {
                                                    OneKeyForwardAlbumUtils.this.initSnsCommentDetailUI(0);
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                            return;
                        }
                        new PerformClickUtils2().check(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.circle_img_crop_id, 0, 100, 10, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                PerformClickUtils.findViewIdAndClick(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.circle_img_crop_id);
                                new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "确定", OneKeyForwardAlbumUtils.this.isFastSpeed ? 1 : SocializeConstants.CANCLE_RESULTCODE, 5, 200, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "确定");
                                        new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "完成", 10, 20, 50, new PerformClickUtils2.OnCheckListener() {
                                            public void nuFind() {
                                            }

                                            public void find(int i) {
                                                PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "完成");
                                                new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "保存图片", 10, 20, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new PerformClickUtils2.OnCheckListener() {
                                                    public void nuFind() {
                                                    }

                                                    public void find(int i) {
                                                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "保存图片");
                                                        new PerformClickUtils2().checkNilString(OneKeyForwardAlbumUtils.this.autoService, "完成", 10, 20, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                                            public void nuFind() {
                                                            }

                                                            public void find(int i) {
                                                                OneKeyForwardAlbumUtils.this.backDescPage(new OneKeyForwardUtils.OnBackDescPageListener() {
                                                                    public void find() {
                                                                        OneKeyForwardAlbumUtils.this.initSnsCommentDetailUI(0);
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

    public void initVideoMMRecordUI() {
        try {
            System.out.println("WS_BABY_initVideoMMRecordUI");
            new PerformClickUtils2().checkString(this.autoService, "完成", (executeSpeedSetting / 5) + 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

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
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    OneKeyForwardAlbumUtils.this.initSnsUploadUI();
                                }
                            });
                        }

                        public void nuFind() {
                            System.out.println("WS_BABY_initVideoMMRecordUI.4");
                            LogUtils.log("WS_BABY_00");
                            PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_11");
                            new PerformClickUtils2().checkNilString(OneKeyForwardAlbumUtils.this.autoService, "正在处理", (int) SocializeConstants.CANCLE_RESULTCODE, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    OneKeyForwardAlbumUtils.this.initSnsUploadUI();
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
            new PerformClickUtils2().checkNodeId(this.autoService, list_circle_layout_id, 100, 200, 50, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

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
                            if (i2 < findViewIdList.size()) {
                                if (!MyApplication.enforceable) {
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
                                    } catch (Exception unused3) {
                                        continue;
                                    }
                                }
                                i2++;
                            }
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
                    } catch (Exception unused4) {
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("WS_BABY_SnsUserUI.Exception");
        }
    }

    public void selectFriends() {
        if (this.selectModel == null || "".equals(this.selectModel) || "公开".equals(this.selectModel)) {
            sendCircle();
            return;
        }
        new PerformClickUtils2().checkString(this.autoService, "谁可以看", (executeSpeedSetting / 5) + executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "谁可以看");
                new PerformClickUtils2().checkStringsAll(OneKeyForwardAlbumUtils.this.autoService, "私密", "公开", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        if ("私密".equals(OneKeyForwardAlbumUtils.this.selectModel) || "公开".equals(OneKeyForwardAlbumUtils.this.selectModel)) {
                            PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, OneKeyForwardAlbumUtils.this.selectModel);
                            PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "完成");
                            OneKeyForwardAlbumUtils.this.sendCircle();
                        } else if ("部分可见".equals(OneKeyForwardAlbumUtils.this.selectModel)) {
                            new PerformClickUtils2().checkNodeStringsHasSomeOne(OneKeyForwardAlbumUtils.this.autoService, "部分可见", "选中的朋友可见", BaseServiceUtils.executeSpeedSetting + BaseServiceUtils.executeSpeed, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    if (i == 0) {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "部分可见");
                                    } else {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "选中的朋友可见");
                                    }
                                    OneKeyForwardAlbumUtils.this.executeRealSelect();
                                }
                            });
                        } else if ("不给谁看".equals(OneKeyForwardAlbumUtils.this.selectModel)) {
                            new PerformClickUtils2().checkNodeStringsHasSomeOne(OneKeyForwardAlbumUtils.this.autoService, "不给谁看", "选中的朋友不可见", BaseServiceUtils.executeSpeedSetting + BaseServiceUtils.executeSpeed, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    if (i == 0) {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "不给谁看");
                                    } else {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "选中的朋友不可见");
                                    }
                                    OneKeyForwardAlbumUtils.this.executeRealSelect();
                                }
                            });
                        }
                    }
                });
            }
        });
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
                PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "从群选择");
                int unused = OneKeyForwardAlbumUtils.this.selectGroupNum = 0;
                OneKeyForwardAlbumUtils.this.initSelectGroupsName();
            }
        });
    }

    public void sendCircle() {
        new PerformClickUtils2().checkString(this.autoService, "发表", executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

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
        });
    }

    public void executeRealSelectTags() {
        if (this.selectTags == null || "".equals(this.selectTags)) {
            new PerformClickUtils2().checkString(this.autoService, "完成", executeSpeedSetting + executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "完成");
                    OneKeyForwardAlbumUtils.this.sendCircle();
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
                int unused = OneKeyForwardAlbumUtils.this.startIndexView = 0;
                OneKeyForwardAlbumUtils.this.whileExecuteTask();
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
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.OneKeyForwardAlbumUtils.whileExecuteTask():void");
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
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        OneKeyForwardAlbumUtils.this.executeRealSelectTags();
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
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        OneKeyForwardAlbumUtils.this.executeRealSelectTags();
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

    public void jumpMyAlbum() {
        new PerformClickUtils2().checkString(this.autoService, "我", 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "我");
                new PerformClickUtils2().checkString(OneKeyForwardAlbumUtils.this.autoService, "相册", 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        PerformClickUtils.findTextAndClick(OneKeyForwardAlbumUtils.this.autoService, "相册");
                        new PerformClickUtils2().checkNodeStringsHasSomeOne(OneKeyForwardAlbumUtils.this.autoService, "我的朋友圈", "前往朋友圈", 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

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
                        });
                    }
                });
            }
        });
    }

    public void findSearchBtn() {
        new PerformClickUtils2().check(this.autoService, contact_nav_search_viewgroup_id, executeSpeedSetting + 100, 50, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

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
        });
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
                                            } catch (Exception unused) {
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

    public void initChattingUI() {
        try {
            new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeedSetting + 50, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findViewIdAndClick(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.right_more_id);
                    OneKeyForwardAlbumUtils.this.initSingleChatInfo();
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
                    PerformClickUtils.findViewIdAndClick(OneKeyForwardAlbumUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                    OneKeyForwardAlbumUtils.this.executeClickCircle();
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_74");
            e.printStackTrace();
        }
    }

    public void executeClickCircle() {
        new PerformClickUtils2().checkString(this.autoService, "朋友圈", (executeSpeedSetting / 5) + 100, 50, 10, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

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
            showBottomView(this.mMyManager, "正在一键转发相册图文，请不要操作微信");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        OneKeyForwardAlbumUtils.this.executeMain();
                    } catch (Exception unused) {
                    }
                }
            }).start();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
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
}
