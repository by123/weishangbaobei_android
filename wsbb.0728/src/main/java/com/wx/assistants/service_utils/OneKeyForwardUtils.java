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
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.fileutil.FileUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class OneKeyForwardUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static OneKeyForwardUtils instance;
    /* access modifiers changed from: private */
    public int deleteModel = 0;
    /* access modifiers changed from: private */
    public List<String> groupsList = new ArrayList();
    /* access modifiers changed from: private */
    public boolean isAutoSend = false;
    private boolean isCompleted = false;
    /* access modifiers changed from: private */
    public boolean isExist = false;
    /* access modifiers changed from: private */
    public boolean isFastSpeed = false;
    /* access modifiers changed from: private */
    public boolean isFirstSaveHideText = true;
    /* access modifiers changed from: private */
    public boolean isOnlyText = false;
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
    private int startIndex = 0;
    /* access modifiers changed from: private */
    public int startIndexView = 0;
    private List<String> tagsList = new ArrayList();
    /* access modifiers changed from: private */
    public String text = "";

    interface OnBackDescPageListener {
        void find();
    }

    private OneKeyForwardUtils() {
    }

    static /* synthetic */ int access$808(OneKeyForwardUtils oneKeyForwardUtils) {
        int i = oneKeyForwardUtils.selectGroupNum;
        oneKeyForwardUtils.selectGroupNum = i + 1;
        return i;
    }

    public static OneKeyForwardUtils getInstance() {
        instance = new OneKeyForwardUtils();
        return instance;
    }

    public void backDescPage(final OnBackDescPageListener onBackDescPageListener) {
        new PerformClickUtils2().checkNodeId(this.autoService, view_pager_id, executeSpeed, 100, 30, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                System.out.println("WS_BABY_backDescPage.1");
                OneKeyForwardUtils.this.sleep(470);
                OneKeyForwardUtils.this.autoService.performGlobalAction(1);
                new PerformClickUtils2().checkNodeId(OneKeyForwardUtils.this.autoService, BaseServiceUtils.circle_list_layout_id, 100, 100, 8, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (onBackDescPageListener != null) {
                            onBackDescPageListener.find();
                        }
                    }

                    public void nuFind() {
                        OneKeyForwardUtils.this.autoService.performGlobalAction(1);
                        OneKeyForwardUtils.this.sleep(100);
                        if (onBackDescPageListener != null) {
                            onBackDescPageListener.find();
                        }
                    }
                });
            }

            public void nuFind() {
                System.out.println("WS_BABY_backDescPage.3");
                OneKeyForwardUtils.this.autoService.performGlobalAction(1);
                if (onBackDescPageListener != null) {
                    onBackDescPageListener.find();
                }
            }
        });
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showStartView();
        this.mMyManager.showBackView();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0080, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0081, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    public void endViewShow() {
        try {
            int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "delete_model", 0)).intValue();
            PrintStream printStream = System.out;
            printStream.println("WS_BABY____" + intValue);
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
        showBottomView(this.mMyManager, "正在一键转发朋友圈，请不要操作微信");
        new Thread(new Runnable() {
            public void run() {
                try {
                    OneKeyForwardUtils.this.executeMain(100);
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public void excFroward() {
        try {
            new Thread(new Runnable() {
                /* JADX WARNING: Code restructure failed: missing block: B:24:0x00ab, code lost:
                    r0 = r0.getParent();
                 */
                public void run() {
                    try {
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardUtils.this.autoService, BaseServiceUtils.circle_list_layout_id);
                        LogUtils.log("WS_BABY_SnsTimeLineUI.1");
                        if (findViewIdList == null || findViewIdList.size() <= 0) {
                            OneKeyForwardUtils.this.mMyManager.toastToUserError("检测到的列表未空");
                            return;
                        }
                        LogUtils.log("WS_BABY_SnsTimeLineUI.2.size." + findViewIdList.size());
                        boolean unused = OneKeyForwardUtils.this.isExist = false;
                        int i = 0;
                        while (true) {
                            if (i < findViewIdList.size()) {
                                if (MyApplication.enforceable) {
                                    AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(i);
                                    LogUtils.log("WS_BABY_SnsTimeLineUI.22" + accessibilityNodeInfo.getClassName());
                                    if (accessibilityNodeInfo == null || !"android.widget.FrameLayout".equals(accessibilityNodeInfo.getClassName())) {
                                        break;
                                    }
                                    LogUtils.log("WS_BABY_SnsTimeLineUI.22");
                                    Rect rect = new Rect();
                                    accessibilityNodeInfo.getBoundsInScreen(rect);
                                    if (!rect.contains(MyApplication.startX, MyApplication.startY)) {
                                        i++;
                                    } else {
                                        LogUtils.log("WS_BABY_SnsTimeLineUI.222");
                                        findViewIdList.size();
                                        boolean unused2 = OneKeyForwardUtils.this.isExist = true;
                                        final AccessibilityNodeInfo accessibilityNodeInfo2 = ((BaseServiceUtils.wxVersionCode > 1620 || (BaseServiceUtils.wxVersionCode == 1620 && "7.0.13".equals(BaseServiceUtils.wxVersionName))) && accessibilityNodeInfo == null) ? findViewIdList.get(i) : accessibilityNodeInfo;
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = accessibilityNodeInfo2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.circle_list_layout_text_id);
                                        if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                                            LogUtils.log("WS_BABY_SnsTimeLineUI.333333");
                                            OneKeyForwardUtils.this.initForward(accessibilityNodeInfo2);
                                        } else {
                                            LogUtils.log("WS_BABY_SnsTimeLineUI.222222");
                                            if (OneKeyForwardUtils.this.text == null || "".equals(OneKeyForwardUtils.this.text)) {
                                                String unused3 = OneKeyForwardUtils.this.text = findAccessibilityNodeInfosByViewId.get(0).getText().toString();
                                            }
                                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = accessibilityNodeInfo2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.circle_list_layout_hide_text_id);
                                            if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0 || !OneKeyForwardUtils.this.isFirstSaveHideText) {
                                                LogUtils.log("WS_BABY_SnsTimeLineUI.8");
                                                LogUtils.log("WS_BABY_text=" + OneKeyForwardUtils.this.text);
                                                OneKeyForwardUtils.this.initForward(accessibilityNodeInfo2);
                                            } else {
                                                LogUtils.log("WS_BABY_SnsTimeLineUI.4");
                                                boolean unused4 = OneKeyForwardUtils.this.isFirstSaveHideText = false;
                                                String charSequence = findAccessibilityNodeInfosByViewId2.get(0).getText().toString();
                                                if (charSequence == null || "".equals(charSequence) || !charSequence.endsWith("...")) {
                                                    LogUtils.log("WS_BABY_SnsTimeLineUI.8");
                                                    LogUtils.log("WS_BABY_text=" + OneKeyForwardUtils.this.text);
                                                    OneKeyForwardUtils.this.initForward(accessibilityNodeInfo2);
                                                } else {
                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                                                    LogUtils.log("WS_BABY_SnsTimeLineUI.5");
                                                    new PerformClickUtils2().checkNodeIdSyn(OneKeyForwardUtils.this.autoService, BaseServiceUtils.hide_text_page_text_id, BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 200, new PerformClickUtils2.OnCheckListener() {
                                                        public void find(int i) {
                                                            String unused = OneKeyForwardUtils.this.text = PerformClickUtils.findViewIdAndGetText(OneKeyForwardUtils.this.autoService, BaseServiceUtils.hide_text_page_text_id);
                                                            LogUtils.log("WS_BABY_SnsTimeLineUI.6");
                                                            OneKeyForwardUtils.this.sleep(100);
                                                            PerformClickUtils.performBack(OneKeyForwardUtils.this.autoService);
                                                            new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "拍照分享", 200, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                                public void find(int i) {
                                                                    OneKeyForwardUtils.this.initForward(accessibilityNodeInfo2);
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
                                        }
                                    }
                                } else {
                                    return;
                                }
                            } else {
                                break;
                            }
                        }
                        if (!OneKeyForwardUtils.this.isExist) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    BaseServiceUtils.removeEndView(OneKeyForwardUtils.this.mMyManager);
                                    OneKeyForwardUtils.this.mMyManager.toastToUserError("请将【开始按钮】移动到 待转发消息附近");
                                    OneKeyForwardUtils.this.initData(OneKeyForwardUtils.this.model);
                                }
                            });
                        }
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeMain(int i) {
        try {
            LogUtils.log("WS_BABY_count_执行开始@");
            if (this.isCompleted) {
                publicCircle();
                return;
            }
            new PerformClickUtils2().check(this.autoService, circle_list_layout_id, i, 100, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    OneKeyForwardUtils.this.excFroward();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
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
                PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "从群选择");
                int unused = OneKeyForwardUtils.this.selectGroupNum = 0;
                OneKeyForwardUtils.this.initSelectGroupsName();
            }

            public void nuFind() {
            }
        });
    }

    public void executeRealSelectTags() {
        if (this.selectTags == null || "".equals(this.selectTags)) {
            new PerformClickUtils2().checkString(this.autoService, "完成", executeSpeed + (executeSpeedSetting / 5), 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                    OneKeyForwardUtils.this.sendCircle();
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
                int unused = OneKeyForwardUtils.this.startIndexView = 0;
                OneKeyForwardUtils.this.whileExecuteTask();
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.MassSendSelectContactUI_5_nuFind");
            }
        });
    }

    public void initAlbumPreviewUI() {
        try {
            new PerformClickUtils2().checkNodeIdsHasSomeOne(this.autoService, video_first_id, img_first_check_layout_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (OneKeyForwardUtils.this.isSavedVideo && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.AlbumPreviewUI.1");
                        new PerformClickUtils2().checkNodeId(OneKeyForwardUtils.this.autoService, BaseServiceUtils.video_first_id, 0, 50, 100, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardUtils.this.autoService, BaseServiceUtils.video_first_id);
                                if (findViewIdList == null || findViewIdList.size() <= 0) {
                                    OneKeyForwardUtils.this.initAlbumPreviewUI();
                                    return;
                                }
                                PerformClickUtils.performClick(findViewIdList.get(0));
                                if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                                    new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "编辑视频", SocializeConstants.CANCLE_RESULTCODE, 100, 3, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            PerformClickUtils.findViewIdAndClick(OneKeyForwardUtils.this.autoService, BaseServiceUtils.edit_text_id);
                                            new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "完成", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                public void find(int i) {
                                                    PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                                                    OneKeyForwardUtils.this.sleep(600);
                                                    PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                                                    OneKeyForwardUtils.this.initSnsUploadUI();
                                                }

                                                public void nuFind() {
                                                }
                                            });
                                        }

                                        public void nuFind() {
                                            PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                                            OneKeyForwardUtils.this.initSnsUploadUI();
                                        }
                                    });
                                } else {
                                    new PerformClickUtils2().checkNilString(OneKeyForwardUtils.this.autoService, "图片和视频", 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            OneKeyForwardUtils.this.initVideoMMRecordUI();
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
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardUtils.this.autoService, BaseServiceUtils.img_first_check_layout_id);
                            if (findViewIdList != null && findViewIdList.size() > 0) {
                                int access$600 = OneKeyForwardUtils.this.num - 1;
                                while (true) {
                                    int i2 = access$600;
                                    if (i2 <= -1 || !MyApplication.enforceable) {
                                        break;
                                    }
                                    OneKeyForwardUtils.this.sleep(5);
                                    PerformClickUtils.performClick(findViewIdList.get(i2));
                                    access$600 = i2 - 1;
                                }
                            }
                        } catch (Exception e) {
                        }
                        List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardUtils.this.autoService, BaseServiceUtils.complete_id);
                        if (findViewIdList2 == null || findViewIdList2.size() <= 0 || !findViewIdList2.get(0).isEnabled()) {
                            OneKeyForwardUtils.this.initAlbumPreviewUI();
                            return;
                        }
                        findViewIdList2.get(0).performAction(16);
                        OneKeyForwardUtils.this.initSnsUploadUI();
                    }
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.startIndex = 0;
        this.startIndexView = 0;
        this.num = 0;
        this.text = "";
        this.isOnlyText = false;
        this.isSavedVideo = false;
        this.isExist = false;
        this.isCompleted = false;
        this.isFirstSaveHideText = true;
        this.model = operationParameterModel;
        this.isAutoSend = operationParameterModel.isAutoSend();
        this.deleteModel = operationParameterModel.getDeleteMode();
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

    public void initForward(AccessibilityNodeInfo accessibilityNodeInfo) {
        LogUtils.log("WS_BABY_SnsTimeLineUI.xxxxx" + this.startIndex);
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(list_layout_only_text_id);
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(circle_list_layout_hide_text_id);
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(circle_list_layout_img_id);
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId4 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(circle_list_layout_video_id);
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId5 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(video_layout);
        if (findAccessibilityNodeInfosByViewId3 != null && findAccessibilityNodeInfosByViewId3.size() > 0 && findAccessibilityNodeInfosByViewId3.get(0) != null && findAccessibilityNodeInfosByViewId3.get(0).getChildCount() > 0) {
            this.isOnlyText = false;
            if (findAccessibilityNodeInfosByViewId3 != null && !findAccessibilityNodeInfosByViewId3.isEmpty()) {
                this.isSavedVideo = false;
                this.num = findAccessibilityNodeInfosByViewId3.get(0).getChildCount();
                if (this.startIndex < this.num) {
                    LogUtils.log("WS_BABY_SnsTimeLineUI.图片" + this.startIndex);
                    if (this.startIndex == this.num - 1) {
                        this.isCompleted = true;
                    }
                    PerformClickUtils.performLongClick(findAccessibilityNodeInfosByViewId3.get(0).getChild(this.startIndex));
                    this.startIndex++;
                    new PerformClickUtils2().checkString(this.autoService, "编辑", 50, 10, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "编辑");
                            if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                                OneKeyForwardUtils.this.initMMNewPhotoEditUI();
                            } else {
                                OneKeyForwardUtils.this.initPhotoMMRecordUI();
                            }
                        }

                        public void nuFind() {
                        }
                    });
                }
            }
        } else if ((findAccessibilityNodeInfosByViewId4 != null && findAccessibilityNodeInfosByViewId4.size() > 0) || (findAccessibilityNodeInfosByViewId5 != null && findAccessibilityNodeInfosByViewId5.size() > 0)) {
            this.isOnlyText = false;
            this.num = 1;
            LogUtils.log("WS_BABY_SnsTimeLineUI.视频.num=" + this.num);
            if (this.startIndex < this.num) {
                LogUtils.log("WS_BABY_SnsTimeLineUI.视频" + this.startIndex);
                if (this.startIndex == this.num - 1) {
                    LogUtils.log("WS_BABY_SnsTimeLineUI.视频.true");
                    this.isCompleted = true;
                }
                if (wxVersionCode >= 1420 && !"7.0.4".equals(wxVersionName)) {
                    LogUtils.log("WS_BABY_SnsTimeLineUI.视频.0");
                    if (findAccessibilityNodeInfosByViewId4 == null || findAccessibilityNodeInfosByViewId4.size() <= 0) {
                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId5.get(0));
                        initSnsLineVideoActivity();
                        return;
                    }
                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId4.get(0));
                    initSnsLineVideoActivity();
                } else if (findAccessibilityNodeInfosByViewId4 == null || findAccessibilityNodeInfosByViewId4.size() <= 0) {
                    LogUtils.log("WS_BABY_SnsTimeLineUI.视频.1");
                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId5.get(0));
                    initSnsLineVideoActivity();
                } else {
                    LogUtils.log("WS_BABY_SnsTimeLineUI.视频.1");
                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId4.get(0).getChild(this.startIndex));
                    initSnsLineVideoActivity();
                }
            }
        } else if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0) {
            this.isOnlyText = false;
            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, right_more_id);
            if (findViewIdList != null && findViewIdList.size() != 0) {
                PerformClickUtils.performLongClick(findViewIdList.get(0));
                initSnsUploadUI();
            }
        } else if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0) {
            LogUtils.log("WS_BABY_hide_texts");
            this.isOnlyText = true;
            this.isExist = true;
            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, right_more_id);
            if (findViewIdList2 != null && findViewIdList2.size() != 0) {
                PerformClickUtils.performLongClick(findViewIdList2.get(0));
                initSnsUploadUI();
            }
        }
    }

    public void initMMNewPhotoEditUI() {
        try {
            LogUtils.log("WS_BABY_MMNewPhotoEditUI");
            new PerformClickUtils2().checkString(this.autoService, "完成", (executeSpeedSetting / 5) + 80, 80, 200, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_MMNewPhotoEditUI_has_完成");
                    PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                    new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "保存图片", 80, 80, 100, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "保存图片");
                            new PerformClickUtils2().checkNilString(OneKeyForwardUtils.this.autoService, "正在处理中", (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY_MMNewPhotoEditUI_has_完成2");
                                    OneKeyForwardUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                    PerformClickUtils.hideInputKey(OneKeyForwardUtils.this.autoService);
                                    new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "拍照分享", 100, 100, 8, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            OneKeyForwardUtils.this.executeMain(0);
                                        }

                                        public void nuFind() {
                                            OneKeyForwardUtils.this.autoService.performGlobalAction(1);
                                            OneKeyForwardUtils.this.executeMain(100);
                                        }
                                    });
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY_MMNewPhotoEditUI_has_完成3");
                                    PerformClickUtils.performBack(OneKeyForwardUtils.this.autoService);
                                    OneKeyForwardUtils.this.executeMain(100);
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
            new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "下一步", "完成", (executeSpeedSetting / 5) + 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (i == 0) {
                        PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "下一步");
                        new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "保存图片", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "保存图片");
                                OneKeyForwardUtils.this.backDescPage(new OnBackDescPageListener() {
                                    public void find() {
                                        OneKeyForwardUtils.this.executeMain(0);
                                    }
                                });
                            }

                            public void nuFind() {
                            }
                        });
                    } else if (i != 1) {
                    } else {
                        if (BaseServiceUtils.wxVersionCode >= 1640) {
                            PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                            new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "保存图片", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "保存图片");
                                    new PerformClickUtils2().checkNilString(OneKeyForwardUtils.this.autoService, "完成", 10, 50, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            OneKeyForwardUtils.this.backDescPage(new OnBackDescPageListener() {
                                                public void find() {
                                                    OneKeyForwardUtils.this.executeMain(0);
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
                        new PerformClickUtils2().check(OneKeyForwardUtils.this.autoService, BaseServiceUtils.circle_img_crop_id, 0, 100, 10, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                PerformClickUtils.findViewIdAndClick(OneKeyForwardUtils.this.autoService, BaseServiceUtils.circle_img_crop_id);
                                System.out.println("WS_BABY_......开始");
                                new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "确定", OneKeyForwardUtils.this.isFastSpeed ? 1 : SocializeConstants.CANCLE_RESULTCODE, 5, 200, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        System.out.println("WS_BABY_.....结束");
                                        PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "确定");
                                        new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "完成", 10, 50, 20, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                                                new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "保存图片", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                                    public void find(int i) {
                                                        PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "保存图片");
                                                        new PerformClickUtils2().checkNilString(OneKeyForwardUtils.this.autoService, "完成", 10, 50, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                                            public void find(int i) {
                                                                OneKeyForwardUtils.this.backDescPage(new OnBackDescPageListener() {
                                                                    public void find() {
                                                                        OneKeyForwardUtils.this.executeMain(0);
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
            new PerformClickUtils2().check(this.autoService, list_select_search_id, executeSpeed + (executeSpeedSetting / 5), 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    String str = (String) OneKeyForwardUtils.this.groupsList.get(0);
                    LogUtils.log("WS_BABY_SelectContactUI_2.name = " + str);
                    if (str.contains("、")) {
                        str = str.split("、")[0];
                    }
                    PerformClickUtils.findViewByIdAndPasteContent(OneKeyForwardUtils.this.autoService, BaseServiceUtils.list_select_search_id, str);
                    OneKeyForwardUtils.this.groupsList.remove(0);
                    new PerformClickUtils2().check(OneKeyForwardUtils.this.autoService, BaseServiceUtils.list_item_id, 600, 100, 30, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            LogUtils.log("WS_BABY_SelectContactUI_5");
                            PerformClickUtils.performClick(PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardUtils.this.autoService, BaseServiceUtils.list_item_id).get(0));
                            OneKeyForwardUtils.access$808(OneKeyForwardUtils.this);
                            if (OneKeyForwardUtils.this.groupsList.size() == 0) {
                                if (OneKeyForwardUtils.this.selectGroupNum == 0) {
                                    PerformClickUtils.performBack(OneKeyForwardUtils.this.autoService);
                                } else {
                                    PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "确定");
                                }
                                new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "谁可以看", BaseServiceUtils.executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        OneKeyForwardUtils.this.executeRealSelectTags();
                                    }

                                    public void nuFind() {
                                    }
                                });
                                return;
                            }
                            OneKeyForwardUtils.this.initSelectGroupsName();
                        }

                        public void nuFind() {
                            LogUtils.log("WS_BABY_SelectContactUI_4_nufind");
                            if (OneKeyForwardUtils.this.groupsList.size() == 0) {
                                if (OneKeyForwardUtils.this.selectGroupNum == 0) {
                                    PerformClickUtils.performBack(OneKeyForwardUtils.this.autoService);
                                } else {
                                    PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "确定");
                                }
                                new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "谁可以看", BaseServiceUtils.executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        OneKeyForwardUtils.this.executeRealSelectTags();
                                    }

                                    public void nuFind() {
                                    }
                                });
                                return;
                            }
                            OneKeyForwardUtils.this.initSelectGroupsName();
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

    public void initSnsLineVideoActivity() {
        try {
            LogUtils.log("WS_BABY_SnsOnlineVideoActivity");
            new PerformClickUtils2().check(this.autoService, long_click_id, (executeSpeedSetting / 5) + 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_SnsOnlineVideoActivity0");
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardUtils.this.autoService, BaseServiceUtils.long_click_id);
                    if (findViewIdList != null && findViewIdList.size() > 0) {
                        PerformClickUtils.performLongClick(findViewIdList.get(0));
                        LogUtils.log("WS_BABY_SnsOnlineVideoActivity1");
                        new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "保存视频", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                LogUtils.log("WS_BABY_SnsOnlineVideoActivity2");
                                OneKeyForwardUtils.this.sleep(100);
                                PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "保存视频");
                                LogUtils.log("WS_BABY_SnsOnlineVideoActivity3");
                                new PerformClickUtils2().checkNilNodeId(OneKeyForwardUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 1200, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        LogUtils.log("WS_BABY_SnsOnlineVideoActivity4");
                                        new PerformClickUtils2().checkNilNodeId(OneKeyForwardUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                LogUtils.log("WS_BABY_SnsOnlineVideoActivity44");
                                                new PerformClickUtils2().checkNilNodeId(OneKeyForwardUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                                    public void find(int i) {
                                                        LogUtils.log("WS_BABY_SnsOnlineVideoActivity444");
                                                        new PerformClickUtils2().checkNilNodeId(OneKeyForwardUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                                            public void find(int i) {
                                                                LogUtils.log("WS_BABY_SnsOnlineVideoActivity444");
                                                                new PerformClickUtils2().checkNilNodeId(OneKeyForwardUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                                                    public void find(int i) {
                                                                        LogUtils.log("WS_BABY_SnsOnlineVideoActivity444");
                                                                        new PerformClickUtils2().checkNilNodeId(OneKeyForwardUtils.this.autoService, BaseServiceUtils.video_progress_save_id, 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                                                            public void find(int i) {
                                                                                LogUtils.log("WS_BABY_SnsOnlineVideoActivity444");
                                                                                if (MyApplication.enforceable) {
                                                                                    boolean unused = OneKeyForwardUtils.this.isSavedVideo = true;
                                                                                    PerformClickUtils.performBack(OneKeyForwardUtils.this.autoService);
                                                                                    OneKeyForwardUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                                                                    boolean unused2 = OneKeyForwardUtils.this.isOnlyText = false;
                                                                                    OneKeyForwardUtils.this.executeMain(100);
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
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY_SnsOnlineVideoActivity7");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSnsUploadUI() {
        new PerformClickUtils2().checkNodeId(this.autoService, input_send_edit_text_id, executeSpeed + (executeSpeedSetting / 5), 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_SnsUploadUI" + OneKeyForwardUtils.this.text);
                BaseServiceUtils.getMainHandler().post(new Runnable() {
                    public void run() {
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardUtils.this.autoService, BaseServiceUtils.input_send_edit_text_id);
                        if (findViewIdList != null && findViewIdList.size() > 0) {
                            if (OneKeyForwardUtils.this.text != null && !"".equals(OneKeyForwardUtils.this.text)) {
                                OneKeyForwardUtils.this.sleep(10);
                                PerformClickUtils.findViewByIdAndPasteContent(OneKeyForwardUtils.this.autoService, BaseServiceUtils.input_send_edit_text_id, OneKeyForwardUtils.this.text);
                            }
                            if (OneKeyForwardUtils.this.isSavedVideo) {
                                SPUtils.put(MyApplication.getConText(), "delete_img", "1#1");
                                SPUtils.put(MyApplication.getConText(), "delete_model", Integer.valueOf(OneKeyForwardUtils.this.deleteModel));
                                FileUtils.saveForward(1, 1);
                            } else {
                                Context conText = MyApplication.getConText();
                                SPUtils.put(conText, "delete_img", "0#" + OneKeyForwardUtils.this.num);
                                SPUtils.put(MyApplication.getConText(), "delete_model", Integer.valueOf(OneKeyForwardUtils.this.deleteModel));
                                FileUtils.saveForward(0, OneKeyForwardUtils.this.num);
                            }
                            OneKeyForwardUtils.this.selectFriends();
                        }
                    }
                });
            }

            public void nuFind() {
            }
        });
    }

    public void initVideoMMRecordUI() {
        try {
            System.out.println("WS_BABY_initVideoMMRecordUI");
            new PerformClickUtils2().checkString(this.autoService, "完成", (executeSpeedSetting / 5) + 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    System.out.println("WS_BABY_initVideoMMRecordUI.2");
                    new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "取消", 100, 100, 2, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            System.out.println("WS_BABY_initVideoMMRecordUI.3");
                            LogUtils.log("WS_BABY_0");
                            PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_1");
                            OneKeyForwardUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                            PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_2");
                            new PerformClickUtils2().checkNilString(OneKeyForwardUtils.this.autoService, "正在处理", (int) SocializeConstants.CANCLE_RESULTCODE, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    OneKeyForwardUtils.this.initSnsUploadUI();
                                }

                                public void nuFind() {
                                }
                            });
                        }

                        public void nuFind() {
                            System.out.println("WS_BABY_initVideoMMRecordUI.4");
                            LogUtils.log("WS_BABY_00");
                            PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_11");
                            new PerformClickUtils2().checkNilString(OneKeyForwardUtils.this.autoService, "正在处理", (int) SocializeConstants.CANCLE_RESULTCODE, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    OneKeyForwardUtils.this.initSnsUploadUI();
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

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
    }

    public void publicCircle() {
        new PerformClickUtils2().check(this.autoService, right_more_id, (executeSpeedSetting / 5) + 100, 100, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            AutoService autoService = OneKeyForwardUtils.this.autoService;
                            AlbumUtil.insertImageToMediaStore(autoService, FileUtils.getSDPath() + "/tencent/microMsg/WeiXin");
                        } catch (Exception e) {
                        }
                        try {
                            PerformClickUtils.findViewIdAndClick(OneKeyForwardUtils.this.autoService, BaseServiceUtils.right_more_id);
                            new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "从相册选择", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "从相册选择");
                                    OneKeyForwardUtils.this.initAlbumPreviewUI();
                                }

                                public void nuFind() {
                                }
                            });
                        } catch (Exception e2) {
                        }
                    }
                }).start();
            }

            public void nuFind() {
            }
        });
    }

    public void selectFriends() {
        if (this.selectModel == null || "".equals(this.selectModel) || "公开".equals(this.selectModel)) {
            sendCircle();
        } else {
            new PerformClickUtils2().checkString(this.autoService, "谁可以看", executeSpeed + (executeSpeedSetting / 5), 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "谁可以看");
                    new PerformClickUtils2().checkStringsAll(OneKeyForwardUtils.this.autoService, "私密", "公开", BaseServiceUtils.executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            if ("私密".equals(OneKeyForwardUtils.this.selectModel) || "公开".equals(OneKeyForwardUtils.this.selectModel)) {
                                PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, OneKeyForwardUtils.this.selectModel);
                                PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                                OneKeyForwardUtils.this.sendCircle();
                            } else if ("部分可见".equals(OneKeyForwardUtils.this.selectModel)) {
                                new PerformClickUtils2().checkNodeStringsHasSomeOne(OneKeyForwardUtils.this.autoService, "部分可见", "选中的朋友可见", BaseServiceUtils.executeSpeed, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        if (i == 0) {
                                            PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "部分可见");
                                        } else {
                                            PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "选中的朋友可见");
                                        }
                                        OneKeyForwardUtils.this.executeRealSelect();
                                    }

                                    public void nuFind() {
                                    }
                                });
                            } else if ("不给谁看".equals(OneKeyForwardUtils.this.selectModel)) {
                                new PerformClickUtils2().checkNodeStringsHasSomeOne(OneKeyForwardUtils.this.autoService, "不给谁看", "选中的朋友不可见", BaseServiceUtils.executeSpeed, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        if (i == 0) {
                                            PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "不给谁看");
                                        } else {
                                            PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "选中的朋友不可见");
                                        }
                                        OneKeyForwardUtils.this.executeRealSelect();
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
        new PerformClickUtils2().checkString(this.autoService, "发表", executeSpeed + (executeSpeedSetting / 5), 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_SEND" + OneKeyForwardUtils.this.isAutoSend);
                if (OneKeyForwardUtils.this.isAutoSend) {
                    PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "发表");
                }
                MyApplication.enforceable = false;
                BaseServiceUtils.removeEndView(OneKeyForwardUtils.this.mMyManager);
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
