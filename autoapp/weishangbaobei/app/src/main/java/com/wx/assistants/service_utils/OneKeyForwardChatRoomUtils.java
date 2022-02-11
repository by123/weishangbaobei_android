package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityNodeInfo;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.fileutil.FileUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.List;

public class OneKeyForwardChatRoomUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static OneKeyForwardChatRoomUtils instance;
    /* access modifiers changed from: private */
    public boolean isAutoSend;
    private boolean isExecutedSaveVideo = false;
    private boolean isFirstDelete = true;
    /* access modifiers changed from: private */
    public boolean isSavedCompleted = false;
    /* access modifiers changed from: private */
    public boolean isSavedVideo = false;

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
    }

    private OneKeyForwardChatRoomUtils() {
    }

    public static OneKeyForwardChatRoomUtils getInstance() {
        instance = new OneKeyForwardChatRoomUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.isSavedVideo = false;
        this.isSavedCompleted = false;
        this.isFirstDelete = true;
        this.isExecutedSaveVideo = false;
        this.isAutoSend = operationParameterModel.isAutoSend();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initImageGalleryUI() {
        if (!this.isSavedCompleted && MyApplication.enforceable) {
            if (!this.isExecutedSaveVideo) {
                LogUtils.log("WS_BABY.ImageGalleryUI.2");
                new PerformClickUtils2().check(this.autoService, down_img_id, executeSpeedSetting + executeSpeed, 100, 300, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        if (MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.ImageGalleryUI.3");
                            boolean unused = OneKeyForwardChatRoomUtils.this.isSavedCompleted = true;
                            boolean unused2 = OneKeyForwardChatRoomUtils.this.isSavedVideo = false;
                            PerformClickUtils.findViewIdAndClick(OneKeyForwardChatRoomUtils.this.autoService, BaseServiceUtils.down_img_id);
                            OneKeyForwardChatRoomUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                            PerformClickUtils.hideInputKey(OneKeyForwardChatRoomUtils.this.autoService);
                            OneKeyForwardChatRoomUtils.this.backExecute();
                        }
                    }
                });
            } else if (wxVersionCode > 1560 || (wxVersionCode == 1560 && "7.0.10".equals(wxVersionName))) {
                new PerformClickUtils2().check(this.autoService, window_video_more, 0, 20, (int) SocializeConstants.CANCLE_RESULTCODE, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        PerformClickUtils.findViewIdAndClick(OneKeyForwardChatRoomUtils.this.autoService, BaseServiceUtils.window_video_more);
                        new PerformClickUtils2().checkString(OneKeyForwardChatRoomUtils.this.autoService, "保存视频", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                OneKeyForwardChatRoomUtils.this.executeSaveVideo();
                            }
                        });
                    }
                });
            } else {
                LogUtils.log("WS_BABY.ImageGalleryUI.4");
                new PerformClickUtils2().checkNodeId(this.autoService, video_viewpager_imgview, 0, 300, 50, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                        AccessibilityNodeInfo rootInActiveWindow = OneKeyForwardChatRoomUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.view_pager_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.ImageGalleryUI.5");
                            if (findAccessibilityNodeInfosByViewId.get(0) != null && MyApplication.enforceable) {
                                if (Build.VERSION.SDK_INT >= 24) {
                                    LogUtils.log("WS_BABY.ImageGalleryUI.5555");
                                    BaseServiceUtils.getMainHandler().post(new Runnable() {
                                        @RequiresApi(api = 24)
                                        public void run() {
                                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                                            AccessibilityNodeInfo rootInActiveWindow = OneKeyForwardChatRoomUtils.this.autoService.getRootInActiveWindow();
                                            if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.video_viewpager_imgview)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                                Rect rect = new Rect();
                                                findAccessibilityNodeInfosByViewId.get(0).getBoundsInScreen(rect);
                                                GestureDescription.Builder builder = new GestureDescription.Builder();
                                                Path path = new Path();
                                                path.moveTo((float) rect.centerX(), (float) rect.centerY());
                                                MyApplication.instance.getMyWindowManager().getAutoService().dispatchGesture(builder.addStroke(new GestureDescription.StrokeDescription(path, 0, 1000)).build(), new AccessibilityService.GestureResultCallback() {
                                                    public void onCompleted(GestureDescription gestureDescription) {
                                                        super.onCompleted(gestureDescription);
                                                        if (!PerformClickUtils.findNodeIsExistByText(OneKeyForwardChatRoomUtils.this.autoService, "保存视频") || !MyApplication.enforceable) {
                                                            LogUtils.log("WS_BABY.ImageGalleryUI.8");
                                                            return;
                                                        }
                                                        OneKeyForwardChatRoomUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                                        OneKeyForwardChatRoomUtils.this.executeSaveVideo();
                                                    }

                                                    public void onCancelled(GestureDescription gestureDescription) {
                                                        super.onCancelled(gestureDescription);
                                                    }
                                                }, new Handler());
                                            }
                                        }
                                    });
                                    return;
                                }
                                LogUtils.log("WS_BABY.ImageGalleryUI.66666");
                                BaseServiceUtils.updateBottomText(OneKeyForwardChatRoomUtils.this.mMyManager, "仅支持安卓7.0以上的系统使用");
                            }
                        }
                    }
                });
            }
        }
    }

    public void executeSaveVideo() {
        PerformClickUtils.findTextAndClick(this.autoService, "保存视频");
        LogUtils.log("WS_BABY.ImageGalleryUI.6");
        this.isSavedCompleted = true;
        this.isSavedVideo = true;
        new PerformClickUtils2().checkNilNodeId(this.autoService, video_progress_save_id, 1200, 200, 300, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_SnsOnlineVideoActivity4");
                new PerformClickUtils2().checkNilNodeId(OneKeyForwardChatRoomUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 200, 100, 300, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY_SnsOnlineVideoActivity44");
                        new PerformClickUtils2().checkNilNodeId(OneKeyForwardChatRoomUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                LogUtils.log("WS_BABY_SnsOnlineVideoActivity444");
                                if (MyApplication.enforceable) {
                                    boolean unused = OneKeyForwardChatRoomUtils.this.isSavedVideo = true;
                                    OneKeyForwardChatRoomUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                    PerformClickUtils.hideInputKey(OneKeyForwardChatRoomUtils.this.autoService);
                                    OneKeyForwardChatRoomUtils.this.backExecute();
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

    public void initSnsUploadUI() {
        if (this.isSavedVideo && MyApplication.enforceable) {
            SPUtils.put(MyApplication.getConText(), "delete_window_img", "1#1");
            FileUtils.saveForward(1, 1);
        }
        new PerformClickUtils2().checkString(this.autoService, "发表", (executeSpeedSetting / 5) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                if (OneKeyForwardChatRoomUtils.this.isAutoSend) {
                    PerformClickUtils.findTextAndClick(OneKeyForwardChatRoomUtils.this.autoService, "发表");
                }
                MyApplication.enforceable = false;
                BaseServiceUtils.removeEndView(OneKeyForwardChatRoomUtils.this.mMyManager);
            }
        });
    }

    public void albumPreviewUI() {
        try {
            if (!this.isSavedVideo || !MyApplication.enforceable) {
                LogUtils.log("WS_BABY.AlbumPreviewUI.3");
                new PerformClickUtils2().check(this.autoService, img_first_check_layout_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 10, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY.AlbumPreviewUI.33");
                        AccessibilityNodeInfo rootInActiveWindow = OneKeyForwardChatRoomUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow == null || !MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.AlbumPreviewUI.55");
                            return;
                        }
                        LogUtils.log("WS_BABY.AlbumPreviewUI.44");
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.img_first_check_layout_id);
                        if (findAccessibilityNodeInfosByViewId != null && !findAccessibilityNodeInfosByViewId.isEmpty() && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.AlbumPreviewUI.66");
                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                            OneKeyForwardChatRoomUtils.this.sleep(100);
                            AccessibilityNodeInfo rootInActiveWindow2 = OneKeyForwardChatRoomUtils.this.autoService.getRootInActiveWindow();
                            if (rootInActiveWindow2 != null && MyApplication.enforceable) {
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.complete_id);
                                if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0 || !findAccessibilityNodeInfosByViewId2.get(0).isEnabled()) {
                                    OneKeyForwardChatRoomUtils.this.albumPreviewUI();
                                    return;
                                }
                                findAccessibilityNodeInfosByViewId2.get(0).performAction(16);
                                OneKeyForwardChatRoomUtils.this.initSnsUploadUI();
                            }
                        }
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY.AlbumPreviewUI.44");
                    }
                });
                return;
            }
            LogUtils.log("WS_BABY.AlbumPreviewUI.2");
            new PerformClickUtils2().check(this.autoService, video_first_id, (executeSpeedSetting / 5) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 10, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                    AccessibilityNodeInfo rootInActiveWindow = OneKeyForwardChatRoomUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.video_first_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                        if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                            new PerformClickUtils2().checkString(OneKeyForwardChatRoomUtils.this.autoService, "编辑视频", CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 8, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    PerformClickUtils.findViewIdAndClick(OneKeyForwardChatRoomUtils.this.autoService, BaseServiceUtils.edit_text_id);
                                    new PerformClickUtils2().checkString(OneKeyForwardChatRoomUtils.this.autoService, "完成", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            PerformClickUtils.findTextAndClick(OneKeyForwardChatRoomUtils.this.autoService, "完成");
                                            OneKeyForwardChatRoomUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                            PerformClickUtils.findTextAndClick(OneKeyForwardChatRoomUtils.this.autoService, "完成");
                                            OneKeyForwardChatRoomUtils.this.initSnsUploadUI();
                                        }
                                    });
                                }

                                public void nuFind() {
                                    PerformClickUtils.findTextAndClick(OneKeyForwardChatRoomUtils.this.autoService, "完成");
                                    OneKeyForwardChatRoomUtils.this.initSnsUploadUI();
                                }
                            });
                        } else {
                            new PerformClickUtils2().checkNilString(OneKeyForwardChatRoomUtils.this.autoService, "图片和视频", 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    OneKeyForwardChatRoomUtils.this.initVideoMMRecordUI();
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
        PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
            public void find() {
                PerformClickUtils.findTextAndClick(OneKeyForwardChatRoomUtils.this.autoService, "发现");
                new PerformClickUtils2().checkString(OneKeyForwardChatRoomUtils.this.autoService, "朋友圈", 100, 50, 600, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        PerformClickUtils.findTextAndClick(OneKeyForwardChatRoomUtils.this.autoService, "朋友圈");
                        new PerformClickUtils2().checkString(OneKeyForwardChatRoomUtils.this.autoService, "拍照分享", 100, 50, 600, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                PerformClickUtils.findTextAndClick(OneKeyForwardChatRoomUtils.this.autoService, "拍照分享");
                                new PerformClickUtils2().checkString(OneKeyForwardChatRoomUtils.this.autoService, "从相册选择", 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardChatRoomUtils.this.autoService, "从相册选择");
                                        OneKeyForwardChatRoomUtils.this.albumPreviewUI();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    public void initVideoMMRecordUI() {
        try {
            LogUtils.log("WS_BABY.AlbumPreviewUI.444");
            new PerformClickUtils2().checkString(this.autoService, "完成", 10, 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    System.out.println("WS_BABY_initVideoMMRecordUI.2");
                    new PerformClickUtils2().checkString(OneKeyForwardChatRoomUtils.this.autoService, "取消", BaseServiceUtils.executeSpeed, 100, 2, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            System.out.println("WS_BABY_initVideoMMRecordUI.3");
                            LogUtils.log("WS_BABY_0");
                            PerformClickUtils.findTextAndClick(OneKeyForwardChatRoomUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_1");
                            OneKeyForwardChatRoomUtils.this.sleep(600);
                            PerformClickUtils.findTextAndClick(OneKeyForwardChatRoomUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_2");
                            new PerformClickUtils2().checkNilString(OneKeyForwardChatRoomUtils.this.autoService, "正在处理", (int) SocializeConstants.CANCLE_RESULTCODE, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    OneKeyForwardChatRoomUtils.this.initSnsUploadUI();
                                }
                            });
                        }

                        public void nuFind() {
                            System.out.println("WS_BABY_initVideoMMRecordUI.4");
                            LogUtils.log("WS_BABY_00");
                            PerformClickUtils.findTextAndClick(OneKeyForwardChatRoomUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_11");
                            new PerformClickUtils2().checkNilString(OneKeyForwardChatRoomUtils.this.autoService, "正在处理", (int) SocializeConstants.CANCLE_RESULTCODE, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    OneKeyForwardChatRoomUtils.this.initSnsUploadUI();
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
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        try {
            if (!this.isSavedCompleted && MyApplication.enforceable) {
                LogUtils.log("WS_BABY_MAIN.1");
                AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
                if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(room_list_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                    LogUtils.log("WS_BABY_MAIN.2");
                    AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(0);
                    if (accessibilityNodeInfo != null && accessibilityNodeInfo.getChildCount() > 0 && MyApplication.enforceable) {
                        int i = 0;
                        while (i < accessibilityNodeInfo.getChildCount()) {
                            if (MyApplication.enforceable) {
                                AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i);
                                if (child == null || !MyApplication.enforceable || !"android.widget.ListView".equals(child.getClassName().toString())) {
                                    i++;
                                } else {
                                    int i2 = 0;
                                    while (i2 < child.getChildCount()) {
                                        if (MyApplication.enforceable) {
                                            AccessibilityNodeInfo child2 = child.getChild(i2);
                                            if (child2 != null && MyApplication.enforceable) {
                                                Rect rect = new Rect();
                                                child2.getBoundsInScreen(rect);
                                                if (rect.contains(MyApplication.startX, MyApplication.startY)) {
                                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = child2.findAccessibilityNodeInfosByViewId(contact_list_img_id);
                                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = child2.findAccessibilityNodeInfosByViewId(list_video_img_id);
                                                    child2.findAccessibilityNodeInfosByViewId(list_text_id);
                                                    if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0 || !MyApplication.enforceable) {
                                                        LogUtils.log("WS_BABY_MAIN.6");
                                                        getMainHandler().post(new Runnable() {
                                                            public void run() {
                                                                BaseServiceUtils.removeEndView(OneKeyForwardChatRoomUtils.this.mMyManager);
                                                                OneKeyForwardChatRoomUtils.this.mMyManager.toastToUserError("请将【开始按钮】移动到 图片或视频的位置");
                                                            }
                                                        });
                                                        return;
                                                    }
                                                    LogUtils.log("WS_BABY_MAIN.3");
                                                    if (findAccessibilityNodeInfosByViewId3 == null || findAccessibilityNodeInfosByViewId3.size() <= 0) {
                                                        this.isExecutedSaveVideo = false;
                                                    } else {
                                                        this.isExecutedSaveVideo = true;
                                                    }
                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                                                    initImageGalleryUI();
                                                    return;
                                                }
                                            }
                                            i2++;
                                        } else {
                                            return;
                                        }
                                    }
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewShow() {
        try {
            if (this.isFirstDelete) {
                this.isFirstDelete = false;
                try {
                    String str = (String) SPUtils.get(MyApplication.getConText(), "delete_window_img", "");
                    if (!"".equals(str) && str.contains("#")) {
                        String[] split = str.split("#");
                        FileUtils.deleteForward(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            showBottomView(this.mMyManager, "正在一键转发聊天窗口图文，请不要操作微信");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        OneKeyForwardChatRoomUtils.this.executeMain();
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
            this.mMyManager.stopAccessibilityService();
            this.mMyManager.removeBottomView();
            this.mMyManager.showStartView();
            this.mMyManager.showBackView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
