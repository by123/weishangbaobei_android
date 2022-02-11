package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.view.accessibility.AccessibilityNodeInfo;
import com.fb.jjyyzjy.watermark.utils.AlbumUtil;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.AutoService;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.fileutil.FileUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.List;

public class OneKeyForwardMaterialUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static OneKeyForwardMaterialUtils instance;
    private int backCount = 25;
    /* access modifiers changed from: private */
    public boolean isAutoSend = false;
    /* access modifiers changed from: private */
    public int num = 0;
    /* access modifiers changed from: private */
    public String text = "";

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
    }

    static /* synthetic */ int access$310(OneKeyForwardMaterialUtils oneKeyForwardMaterialUtils) {
        int i = oneKeyForwardMaterialUtils.backCount;
        oneKeyForwardMaterialUtils.backCount = i - 1;
        return i;
    }

    private OneKeyForwardMaterialUtils() {
    }

    public static OneKeyForwardMaterialUtils getInstance() {
        instance = new OneKeyForwardMaterialUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.backCount = 25;
        this.num = operationParameterModel.getMaterialPicCount();
        this.text = operationParameterModel.getMaterialText();
        this.isAutoSend = operationParameterModel.isAutoSend();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initAlbumPreviewUI() {
        new PerformClickUtils2().check(this.autoService, img_first_check_layout_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY.AlbumPreviewUI.33.num = " + OneKeyForwardMaterialUtils.this.num);
                try {
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardMaterialUtils.this.autoService, BaseServiceUtils.img_first_check_layout_id);
                    if (findViewIdList != null && findViewIdList.size() > 0) {
                        int access$000 = OneKeyForwardMaterialUtils.this.num;
                        while (true) {
                            access$000--;
                            if (access$000 <= -1) {
                                break;
                            } else if (!MyApplication.enforceable) {
                                break;
                            } else {
                                OneKeyForwardMaterialUtils.this.sleep(10);
                                PerformClickUtils.performClick(findViewIdList.get(access$000));
                            }
                        }
                    }
                } catch (Exception unused) {
                }
                OneKeyForwardMaterialUtils.this.sleep(100);
                List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardMaterialUtils.this.autoService, BaseServiceUtils.complete_id);
                if (findViewIdList2 == null || findViewIdList2.size() <= 0 || !findViewIdList2.get(0).isEnabled()) {
                    OneKeyForwardMaterialUtils.this.initAlbumPreviewUI();
                } else if (MyApplication.enforceable) {
                    findViewIdList2.get(0).performAction(16);
                    OneKeyForwardMaterialUtils.this.initSnsUploadUI();
                }
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.AlbumPreviewUI.44");
            }
        });
    }

    public void initSnsUploadUI() {
        new PerformClickUtils2().checkNodeId(this.autoService, input_send_edit_text_id, executeSpeed + executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                Context conText = MyApplication.getConText();
                SPUtils.put(conText, "delete_img", "0#" + OneKeyForwardMaterialUtils.this.num);
                SPUtils.put(MyApplication.getConText(), "delete_model", 0);
                FileUtils.saveForward(0, OneKeyForwardMaterialUtils.this.num);
                LogUtils.log("WS_BABY.SnsUploadUI");
                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardMaterialUtils.this.autoService, BaseServiceUtils.input_send_edit_text_id);
                if (findViewIdList != null && findViewIdList.size() > 0 && OneKeyForwardMaterialUtils.this.text != null && !"".equals(OneKeyForwardMaterialUtils.this.text)) {
                    OneKeyForwardMaterialUtils.this.sleep(10);
                    PerformClickUtils.findViewByIdAndPasteContent(OneKeyForwardMaterialUtils.this.autoService, BaseServiceUtils.input_send_edit_text_id, OneKeyForwardMaterialUtils.this.text);
                }
                if (OneKeyForwardMaterialUtils.this.isAutoSend && MyApplication.enforceable) {
                    PerformClickUtils.findTextAndClick(OneKeyForwardMaterialUtils.this.autoService, "发表");
                    MyApplication.enforceable = false;
                    BaseServiceUtils.removeEndView(OneKeyForwardMaterialUtils.this.mMyManager);
                } else if (MyApplication.enforceable) {
                    MyApplication.enforceable = false;
                    BaseServiceUtils.removeEndView(OneKeyForwardMaterialUtils.this.mMyManager);
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
                    OneKeyForwardMaterialUtils.this.sleep(100);
                    LogUtils.log("WS_BABY.backExecute.1");
                    if (MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.backExecute.2");
                        BaseServiceUtils.getMainHandler().post(new Runnable() {
                            public void run() {
                                try {
                                    AutoService autoService = OneKeyForwardMaterialUtils.this.autoService;
                                    AlbumUtil.insertImageToMediaStore(autoService, FileUtils.getSDPath() + "/tencent/microMsg/WeiXin");
                                } catch (Exception unused) {
                                }
                            }
                        });
                        Boolean valueOf = Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(OneKeyForwardMaterialUtils.this.autoService, "返回"));
                        Boolean valueOf2 = Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(OneKeyForwardMaterialUtils.this.autoService, "拍照分享"));
                        Boolean valueOf3 = Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(OneKeyForwardMaterialUtils.this.autoService, "通讯录"));
                        Boolean valueOf4 = Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(OneKeyForwardMaterialUtils.this.autoService, "发现"));
                        Boolean valueOf5 = Boolean.valueOf(PerformClickUtils.findNodeIsExistById((AccessibilityService) OneKeyForwardMaterialUtils.this.autoService, BaseServiceUtils.home_tab_layout_id));
                        if (valueOf.booleanValue() && valueOf2.booleanValue()) {
                            LogUtils.log("WS_BABY.backExecute.3");
                            LogUtils.log("WS_BABY.backExecute.5");
                            PerformClickUtils.findTextAndClick(OneKeyForwardMaterialUtils.this.autoService, "拍照分享");
                            new PerformClickUtils2().checkString(OneKeyForwardMaterialUtils.this.autoService, "从相册选择", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY.backExecute.6");
                                    PerformClickUtils.findTextAndClick(OneKeyForwardMaterialUtils.this.autoService, "从相册选择");
                                    OneKeyForwardMaterialUtils.this.initAlbumPreviewUI();
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY.backExecute.7");
                                }
                            });
                        } else if (!valueOf3.booleanValue() || !valueOf4.booleanValue() || !valueOf5.booleanValue()) {
                            LogUtils.log("WS_BABY.backExecute.19");
                            PerformClickUtils.performBackNoDeep(OneKeyForwardMaterialUtils.this.autoService);
                            OneKeyForwardMaterialUtils.access$310(OneKeyForwardMaterialUtils.this);
                            OneKeyForwardMaterialUtils.this.backExecute();
                        } else {
                            LogUtils.log("WS_BABY.backExecute.8");
                            PerformClickUtils.findTextAndClick(OneKeyForwardMaterialUtils.this.autoService, "发现");
                            new PerformClickUtils2().checkString(OneKeyForwardMaterialUtils.this.autoService, "朋友圈", 10, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY.backExecute.9");
                                    PerformClickUtils.findTextAndClick(OneKeyForwardMaterialUtils.this.autoService, "朋友圈");
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY.backExecute.10");
                                    PerformClickUtils.scrollTop(OneKeyForwardMaterialUtils.this.autoService, BaseServiceUtils.default_list_id);
                                    new PerformClickUtils2().checkString(OneKeyForwardMaterialUtils.this.autoService, "朋友圈", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            LogUtils.log("WS_BABY.backExecute.11");
                                            PerformClickUtils.findTextAndClick(OneKeyForwardMaterialUtils.this.autoService, "朋友圈");
                                        }

                                        public void nuFind() {
                                            LogUtils.log("WS_BABY.backExecute.12");
                                        }
                                    });
                                }
                            });
                            new PerformClickUtils2().checkString(OneKeyForwardMaterialUtils.this.autoService, "拍照分享", 10, 50, 300, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY.backExecute.13");
                                    LogUtils.log("WS_BABY.backExecute.15");
                                    if (MyApplication.enforceable) {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardMaterialUtils.this.autoService, "拍照分享");
                                        new PerformClickUtils2().checkString(OneKeyForwardMaterialUtils.this.autoService, "从相册选择", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                LogUtils.log("WS_BABY.backExecute.16");
                                                PerformClickUtils.findTextAndClick(OneKeyForwardMaterialUtils.this.autoService, "从相册选择");
                                                OneKeyForwardMaterialUtils.this.initAlbumPreviewUI();
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
            try {
                if (((Integer) SPUtils.get(MyApplication.getConText(), "delete_model", 0)).intValue() == 0) {
                    String str = (String) SPUtils.get(MyApplication.getConText(), "delete_img", "");
                    if (!"".equals(str) && str.contains("#")) {
                        String[] split = str.split("#");
                        FileUtils.deleteForward(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Thread(new Runnable() {
                public void run() {
                    try {
                        OneKeyForwardMaterialUtils.this.executeMain();
                    } catch (Exception unused) {
                    }
                }
            }).start();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showStartView();
        this.mMyManager.showBackView();
    }
}
