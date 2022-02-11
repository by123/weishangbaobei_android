package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.fb.jjyyzjy.watermark.utils.AlbumUtil;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.AutoService;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.fileutil.FileUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.List;

public class OneKeyForwardBigVideoUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static OneKeyForwardBigVideoUtils instance;
    private int backCount = 25;
    private int executeCount = 10;
    /* access modifiers changed from: private */
    public boolean isAutoSend = false;
    private int num = 0;
    /* access modifiers changed from: private */
    public String text = "";

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
    }

    static /* synthetic */ int access$010(OneKeyForwardBigVideoUtils oneKeyForwardBigVideoUtils) {
        int i = oneKeyForwardBigVideoUtils.executeCount;
        oneKeyForwardBigVideoUtils.executeCount = i - 1;
        return i;
    }

    static /* synthetic */ int access$310(OneKeyForwardBigVideoUtils oneKeyForwardBigVideoUtils) {
        int i = oneKeyForwardBigVideoUtils.backCount;
        oneKeyForwardBigVideoUtils.backCount = i - 1;
        return i;
    }

    private OneKeyForwardBigVideoUtils() {
    }

    public static OneKeyForwardBigVideoUtils getInstance() {
        instance = new OneKeyForwardBigVideoUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.backCount = 25;
        this.executeCount = 10;
        this.num = operationParameterModel.getMaterialPicCount();
        this.text = operationParameterModel.getMaterialText();
        this.isAutoSend = operationParameterModel.isAutoSend();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initAlbumPreviewUI() {
        if (this.executeCount >= 0) {
            new PerformClickUtils2().check(this.autoService, video_first_id, (executeSpeedSetting / 5) + SocializeConstants.CANCLE_RESULTCODE, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardBigVideoUtils.this.autoService, BaseServiceUtils.video_first_id);
                    if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                        OneKeyForwardBigVideoUtils.access$010(OneKeyForwardBigVideoUtils.this);
                        OneKeyForwardBigVideoUtils.this.initAlbumPreviewUI();
                        return;
                    }
                    PerformClickUtils.performClick(findViewIdList.get(0));
                    if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                        new PerformClickUtils2().checkString(OneKeyForwardBigVideoUtils.this.autoService, "编辑视频", CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 8, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                PerformClickUtils.findViewIdAndClick(OneKeyForwardBigVideoUtils.this.autoService, BaseServiceUtils.edit_text_id);
                                new PerformClickUtils2().checkString(OneKeyForwardBigVideoUtils.this.autoService, "完成", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardBigVideoUtils.this.autoService, "完成");
                                        OneKeyForwardBigVideoUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                        PerformClickUtils.findTextAndClick(OneKeyForwardBigVideoUtils.this.autoService, "完成");
                                        OneKeyForwardBigVideoUtils.this.initSnsUploadUI();
                                    }
                                });
                            }

                            public void nuFind() {
                                PerformClickUtils.findTextAndClick(OneKeyForwardBigVideoUtils.this.autoService, "完成");
                                OneKeyForwardBigVideoUtils.this.initSnsUploadUI();
                            }
                        });
                    } else {
                        new PerformClickUtils2().checkNilString(OneKeyForwardBigVideoUtils.this.autoService, "图片和视频", 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                OneKeyForwardBigVideoUtils.this.initVideoMMRecordUI();
                            }
                        });
                    }
                }
            });
        }
    }

    public void initVideoMMRecordUI() {
        try {
            LogUtils.log("WS_BABY.AlbumPreviewUI.444");
            new PerformClickUtils2().checkString(this.autoService, "完成", 10, 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    System.out.println("WS_BABY_initVideoMMRecordUI.2");
                    new PerformClickUtils2().checkString(OneKeyForwardBigVideoUtils.this.autoService, "取消", BaseServiceUtils.executeSpeed, 100, 2, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            System.out.println("WS_BABY_initVideoMMRecordUI.3");
                            LogUtils.log("WS_BABY_0");
                            PerformClickUtils.findTextAndClick(OneKeyForwardBigVideoUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_1");
                            OneKeyForwardBigVideoUtils.this.sleep(600);
                            PerformClickUtils.findTextAndClick(OneKeyForwardBigVideoUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_2");
                            new PerformClickUtils2().checkNilString(OneKeyForwardBigVideoUtils.this.autoService, "正在处理", (int) SocializeConstants.CANCLE_RESULTCODE, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    OneKeyForwardBigVideoUtils.this.initSnsUploadUI();
                                }
                            });
                        }

                        public void nuFind() {
                            System.out.println("WS_BABY_initVideoMMRecordUI.4");
                            LogUtils.log("WS_BABY_00");
                            PerformClickUtils.findTextAndClick(OneKeyForwardBigVideoUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_11");
                            new PerformClickUtils2().checkNilString(OneKeyForwardBigVideoUtils.this.autoService, "正在处理", (int) SocializeConstants.CANCLE_RESULTCODE, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    OneKeyForwardBigVideoUtils.this.initSnsUploadUI();
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

    public void initSnsUploadUI() {
        new PerformClickUtils2().checkNodeId(this.autoService, input_send_edit_text_id, executeSpeed + executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                LogUtils.log("WS_BABY.SnsUploadUI");
                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardBigVideoUtils.this.autoService, BaseServiceUtils.input_send_edit_text_id);
                if (findViewIdList != null && findViewIdList.size() > 0 && OneKeyForwardBigVideoUtils.this.text != null && !"".equals(OneKeyForwardBigVideoUtils.this.text)) {
                    OneKeyForwardBigVideoUtils.this.sleep(10);
                    PerformClickUtils.findViewByIdAndPasteContent(OneKeyForwardBigVideoUtils.this.autoService, BaseServiceUtils.input_send_edit_text_id, OneKeyForwardBigVideoUtils.this.text);
                }
                if (OneKeyForwardBigVideoUtils.this.isAutoSend && MyApplication.enforceable) {
                    PerformClickUtils.findTextAndClick(OneKeyForwardBigVideoUtils.this.autoService, "发表");
                    MyApplication.enforceable = false;
                    BaseServiceUtils.removeEndView(OneKeyForwardBigVideoUtils.this.mMyManager);
                } else if (MyApplication.enforceable) {
                    MyApplication.enforceable = false;
                    BaseServiceUtils.removeEndView(OneKeyForwardBigVideoUtils.this.mMyManager);
                }
            }
        });
    }

    public void executeMain() {
        try {
            this.backCount = 20;
            backExecute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backExecute() {
        if (this.backCount >= 0 && MyApplication.enforceable) {
            new Thread(new Runnable() {
                public void run() {
                    OneKeyForwardBigVideoUtils.this.sleep(100);
                    LogUtils.log("WS_BABY.backExecute.1");
                    if (MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.backExecute.2");
                        BaseServiceUtils.getMainHandler().post(new Runnable() {
                            public void run() {
                                try {
                                    AutoService autoService = OneKeyForwardBigVideoUtils.this.autoService;
                                    AlbumUtil.insertImageToMediaStore(autoService, FileUtils.getSDPath() + "/tencent/microMsg/WeiXin");
                                } catch (Exception unused) {
                                }
                            }
                        });
                        Boolean valueOf = Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(OneKeyForwardBigVideoUtils.this.autoService, "返回"));
                        Boolean valueOf2 = Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(OneKeyForwardBigVideoUtils.this.autoService, "拍照分享"));
                        Boolean valueOf3 = Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(OneKeyForwardBigVideoUtils.this.autoService, "通讯录"));
                        Boolean valueOf4 = Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(OneKeyForwardBigVideoUtils.this.autoService, "发现"));
                        Boolean valueOf5 = Boolean.valueOf(PerformClickUtils.findNodeIsExistById((AccessibilityService) OneKeyForwardBigVideoUtils.this.autoService, BaseServiceUtils.home_tab_layout_id));
                        if (valueOf.booleanValue() && valueOf2.booleanValue()) {
                            LogUtils.log("WS_BABY.backExecute.3");
                            LogUtils.log("WS_BABY.backExecute.5");
                            PerformClickUtils.findTextAndClick(OneKeyForwardBigVideoUtils.this.autoService, "拍照分享");
                            new PerformClickUtils2().checkString(OneKeyForwardBigVideoUtils.this.autoService, "从相册选择", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY.backExecute.6");
                                    PerformClickUtils.findTextAndClick(OneKeyForwardBigVideoUtils.this.autoService, "从相册选择");
                                    OneKeyForwardBigVideoUtils.this.initAlbumPreviewUI();
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY.backExecute.7");
                                }
                            });
                        } else if (!valueOf3.booleanValue() || !valueOf4.booleanValue() || !valueOf5.booleanValue()) {
                            LogUtils.log("WS_BABY.backExecute.19");
                            PerformClickUtils.performBackNoDeep(OneKeyForwardBigVideoUtils.this.autoService);
                            OneKeyForwardBigVideoUtils.access$310(OneKeyForwardBigVideoUtils.this);
                            OneKeyForwardBigVideoUtils.this.backExecute();
                        } else {
                            LogUtils.log("WS_BABY.backExecute.8");
                            PerformClickUtils.findTextAndClick(OneKeyForwardBigVideoUtils.this.autoService, "发现");
                            new PerformClickUtils2().checkString(OneKeyForwardBigVideoUtils.this.autoService, "朋友圈", 10, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY.backExecute.9");
                                    PerformClickUtils.findTextAndClick(OneKeyForwardBigVideoUtils.this.autoService, "朋友圈");
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY.backExecute.10");
                                    PerformClickUtils.scrollTop(OneKeyForwardBigVideoUtils.this.autoService, BaseServiceUtils.default_list_id);
                                    new PerformClickUtils2().checkString(OneKeyForwardBigVideoUtils.this.autoService, "朋友圈", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            LogUtils.log("WS_BABY.backExecute.11");
                                            PerformClickUtils.findTextAndClick(OneKeyForwardBigVideoUtils.this.autoService, "朋友圈");
                                        }

                                        public void nuFind() {
                                            LogUtils.log("WS_BABY.backExecute.12");
                                        }
                                    });
                                }
                            });
                            new PerformClickUtils2().checkString(OneKeyForwardBigVideoUtils.this.autoService, "拍照分享", 10, 50, 300, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY.backExecute.13");
                                    LogUtils.log("WS_BABY.backExecute.15");
                                    if (MyApplication.enforceable) {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardBigVideoUtils.this.autoService, "拍照分享");
                                        new PerformClickUtils2().checkString(OneKeyForwardBigVideoUtils.this.autoService, "从相册选择", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                LogUtils.log("WS_BABY.backExecute.16");
                                                PerformClickUtils.findTextAndClick(OneKeyForwardBigVideoUtils.this.autoService, "从相册选择");
                                                OneKeyForwardBigVideoUtils.this.initAlbumPreviewUI();
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
                }
            }).start();
        }
    }

    public void endViewShow() {
        try {
            showBottomView(this.mMyManager, "正在自动转发朋友圈素材，请不要操作微信");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        OneKeyForwardBigVideoUtils.this.executeMain();
                    } catch (Exception unused) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showStartView();
        this.mMyManager.showBackView();
    }
}
