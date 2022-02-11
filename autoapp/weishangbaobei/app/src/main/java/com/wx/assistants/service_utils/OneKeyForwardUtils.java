package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
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

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
    }

    static /* synthetic */ int access$808(OneKeyForwardUtils oneKeyForwardUtils) {
        int i = oneKeyForwardUtils.selectGroupNum;
        oneKeyForwardUtils.selectGroupNum = i + 1;
        return i;
    }

    private OneKeyForwardUtils() {
    }

    public static OneKeyForwardUtils getInstance() {
        instance = new OneKeyForwardUtils();
        return instance;
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

    public void excFroward() {
        try {
            new Thread(new Runnable() {
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
                                    final AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(i);
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
                                        if ((BaseServiceUtils.wxVersionCode > 1620 || (BaseServiceUtils.wxVersionCode == 1620 && "7.0.13".equals(BaseServiceUtils.wxVersionName))) && (accessibilityNodeInfo = accessibilityNodeInfo.getParent()) == null) {
                                            accessibilityNodeInfo = findViewIdList.get(i);
                                        }
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(BaseServiceUtils.circle_list_layout_text_id);
                                        if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                                            LogUtils.log("WS_BABY_SnsTimeLineUI.333333");
                                            OneKeyForwardUtils.this.initForward(accessibilityNodeInfo);
                                        } else {
                                            LogUtils.log("WS_BABY_SnsTimeLineUI.222222");
                                            if (OneKeyForwardUtils.this.text == null || "".equals(OneKeyForwardUtils.this.text)) {
                                                String unused3 = OneKeyForwardUtils.this.text = findAccessibilityNodeInfosByViewId.get(0).getText().toString();
                                            }
                                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(BaseServiceUtils.circle_list_layout_hide_text_id);
                                            if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0 || !OneKeyForwardUtils.this.isFirstSaveHideText) {
                                                LogUtils.log("WS_BABY_SnsTimeLineUI.8");
                                                LogUtils.log("WS_BABY_text=" + OneKeyForwardUtils.this.text);
                                                OneKeyForwardUtils.this.initForward(accessibilityNodeInfo);
                                            } else {
                                                LogUtils.log("WS_BABY_SnsTimeLineUI.4");
                                                boolean unused4 = OneKeyForwardUtils.this.isFirstSaveHideText = false;
                                                String charSequence = findAccessibilityNodeInfosByViewId2.get(0).getText().toString();
                                                if (charSequence == null || "".equals(charSequence) || !charSequence.endsWith("...")) {
                                                    LogUtils.log("WS_BABY_SnsTimeLineUI.8");
                                                    LogUtils.log("WS_BABY_text=" + OneKeyForwardUtils.this.text);
                                                    OneKeyForwardUtils.this.initForward(accessibilityNodeInfo);
                                                } else {
                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                                                    LogUtils.log("WS_BABY_SnsTimeLineUI.5");
                                                    new PerformClickUtils2().checkNodeIdSyn(OneKeyForwardUtils.this.autoService, BaseServiceUtils.hide_text_page_text_id, BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 200, new PerformClickUtils2.OnCheckListener() {
                                                        public void nuFind() {
                                                        }

                                                        public void find(int i) {
                                                            String unused = OneKeyForwardUtils.this.text = PerformClickUtils.findViewIdAndGetText(OneKeyForwardUtils.this.autoService, BaseServiceUtils.hide_text_page_text_id);
                                                            LogUtils.log("WS_BABY_SnsTimeLineUI.6");
                                                            OneKeyForwardUtils.this.sleep(100);
                                                            PerformClickUtils.performBack(OneKeyForwardUtils.this.autoService);
                                                            new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "拍照分享", 200, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                                public void nuFind() {
                                                                }

                                                                public void find(int i) {
                                                                    OneKeyForwardUtils.this.initForward(accessibilityNodeInfo);
                                                                }
                                                            });
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
                    } catch (Exception unused5) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                        public void nuFind() {
                        }

                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "编辑");
                            if (BaseServiceUtils.wxVersionCode < 1420 || "7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                                OneKeyForwardUtils.this.initMMNewPhotoEditUI();
                            } else {
                                OneKeyForwardUtils.this.initPhotoMMRecordUI();
                            }
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
                public void nuFind() {
                }

                public void find(int i) {
                    LogUtils.log("WS_BABY_MMNewPhotoEditUI_has_完成");
                    PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                    new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "保存图片", 80, 80, 100, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

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
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSnsUploadUI() {
        new PerformClickUtils2().checkNodeId(this.autoService, input_send_edit_text_id, executeSpeed + (executeSpeedSetting / 5), 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

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
        });
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
                PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "谁可以看");
                new PerformClickUtils2().checkStringsAll(OneKeyForwardUtils.this.autoService, "私密", "公开", BaseServiceUtils.executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        if ("私密".equals(OneKeyForwardUtils.this.selectModel) || "公开".equals(OneKeyForwardUtils.this.selectModel)) {
                            PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, OneKeyForwardUtils.this.selectModel);
                            PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                            OneKeyForwardUtils.this.sendCircle();
                        } else if ("部分可见".equals(OneKeyForwardUtils.this.selectModel)) {
                            new PerformClickUtils2().checkNodeStringsHasSomeOne(OneKeyForwardUtils.this.autoService, "部分可见", "选中的朋友可见", BaseServiceUtils.executeSpeed, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    if (i == 0) {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "部分可见");
                                    } else {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "选中的朋友可见");
                                    }
                                    OneKeyForwardUtils.this.executeRealSelect();
                                }
                            });
                        } else if ("不给谁看".equals(OneKeyForwardUtils.this.selectModel)) {
                            new PerformClickUtils2().checkNodeStringsHasSomeOne(OneKeyForwardUtils.this.autoService, "不给谁看", "选中的朋友不可见", BaseServiceUtils.executeSpeed, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    if (i == 0) {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "不给谁看");
                                    } else {
                                        PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "选中的朋友不可见");
                                    }
                                    OneKeyForwardUtils.this.executeRealSelect();
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
                PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "从群选择");
                int unused = OneKeyForwardUtils.this.selectGroupNum = 0;
                OneKeyForwardUtils.this.initSelectGroupsName();
            }
        });
    }

    public void sendCircle() {
        new PerformClickUtils2().checkString(this.autoService, "发表", executeSpeed + (executeSpeedSetting / 5), 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                LogUtils.log("WS_BABY_SEND" + OneKeyForwardUtils.this.isAutoSend);
                if (OneKeyForwardUtils.this.isAutoSend) {
                    PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "发表");
                }
                MyApplication.enforceable = false;
                BaseServiceUtils.removeEndView(OneKeyForwardUtils.this.mMyManager);
            }
        });
    }

    public void executeRealSelectTags() {
        if (this.selectTags == null || "".equals(this.selectTags)) {
            new PerformClickUtils2().checkString(this.autoService, "完成", (executeSpeedSetting / 5) + executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                    OneKeyForwardUtils.this.sendCircle();
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
                int unused = OneKeyForwardUtils.this.startIndexView = 0;
                OneKeyForwardUtils.this.whileExecuteTask();
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
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.OneKeyForwardUtils.whileExecuteTask():void");
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
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        OneKeyForwardUtils.this.executeRealSelectTags();
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
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        OneKeyForwardUtils.this.executeRealSelectTags();
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

    public void initPhotoMMRecordUI() {
        try {
            new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "下一步", "完成", (executeSpeedSetting / 5) + 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    int i2 = i;
                    if (i2 == 0) {
                        PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "下一步");
                        new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "保存图片", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "保存图片");
                                OneKeyForwardUtils.this.backDescPage(new OnBackDescPageListener() {
                                    public void find() {
                                        OneKeyForwardUtils.this.executeMain(0);
                                    }
                                });
                            }
                        });
                    } else if (i2 != 1) {
                    } else {
                        if (BaseServiceUtils.wxVersionCode >= 1640) {
                            PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                            new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "保存图片", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "保存图片");
                                    new PerformClickUtils2().checkNilString(OneKeyForwardUtils.this.autoService, "完成", 10, 50, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            OneKeyForwardUtils.this.backDescPage(new OnBackDescPageListener() {
                                                public void find() {
                                                    OneKeyForwardUtils.this.executeMain(0);
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                            return;
                        }
                        new PerformClickUtils2().check(OneKeyForwardUtils.this.autoService, BaseServiceUtils.circle_img_crop_id, 0, 100, 10, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                PerformClickUtils.findViewIdAndClick(OneKeyForwardUtils.this.autoService, BaseServiceUtils.circle_img_crop_id);
                                System.out.println("WS_BABY_......开始");
                                new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "确定", OneKeyForwardUtils.this.isFastSpeed ? 1 : SocializeConstants.CANCLE_RESULTCODE, 5, 200, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        System.out.println("WS_BABY_.....结束");
                                        PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "确定");
                                        new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "完成", 10, 50, 20, new PerformClickUtils2.OnCheckListener() {
                                            public void nuFind() {
                                            }

                                            public void find(int i) {
                                                PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                                                new PerformClickUtils2().checkString(OneKeyForwardUtils.this.autoService, "保存图片", 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                                    public void nuFind() {
                                                    }

                                                    public void find(int i) {
                                                        PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "保存图片");
                                                        new PerformClickUtils2().checkNilString(OneKeyForwardUtils.this.autoService, "完成", 10, 50, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                                            public void nuFind() {
                                                            }

                                                            public void find(int i) {
                                                                OneKeyForwardUtils.this.backDescPage(new OnBackDescPageListener() {
                                                                    public void find() {
                                                                        OneKeyForwardUtils.this.executeMain(0);
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

    public void initVideoMMRecordUI() {
        try {
            System.out.println("WS_BABY_initVideoMMRecordUI");
            new PerformClickUtils2().checkString(this.autoService, "完成", (executeSpeedSetting / 5) + 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

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
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    OneKeyForwardUtils.this.initSnsUploadUI();
                                }
                            });
                        }

                        public void nuFind() {
                            System.out.println("WS_BABY_initVideoMMRecordUI.4");
                            LogUtils.log("WS_BABY_00");
                            PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                            LogUtils.log("WS_BABY_11");
                            new PerformClickUtils2().checkNilString(OneKeyForwardUtils.this.autoService, "正在处理", (int) SocializeConstants.CANCLE_RESULTCODE, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    OneKeyForwardUtils.this.initSnsUploadUI();
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

    public void initAlbumPreviewUI() {
        try {
            new PerformClickUtils2().checkNodeIdsHasSomeOne(this.autoService, video_first_id, img_first_check_layout_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    if (OneKeyForwardUtils.this.isSavedVideo && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.AlbumPreviewUI.1");
                        new PerformClickUtils2().checkNodeId(OneKeyForwardUtils.this.autoService, BaseServiceUtils.video_first_id, 0, 50, 100, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

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
                                                public void nuFind() {
                                                }

                                                public void find(int i) {
                                                    PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                                                    OneKeyForwardUtils.this.sleep(600);
                                                    PerformClickUtils.findTextAndClick(OneKeyForwardUtils.this.autoService, "完成");
                                                    OneKeyForwardUtils.this.initSnsUploadUI();
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
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            OneKeyForwardUtils.this.initVideoMMRecordUI();
                                        }
                                    });
                                }
                            }
                        });
                    } else if (MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.AlbumPreviewUI.2");
                        try {
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) OneKeyForwardUtils.this.autoService, BaseServiceUtils.img_first_check_layout_id);
                            if (findViewIdList != null && findViewIdList.size() > 0) {
                                int access$600 = OneKeyForwardUtils.this.num;
                                while (true) {
                                    access$600--;
                                    if (access$600 <= -1) {
                                        break;
                                    } else if (!MyApplication.enforceable) {
                                        break;
                                    } else {
                                        OneKeyForwardUtils.this.sleep(5);
                                        PerformClickUtils.performClick(findViewIdList.get(access$600));
                                    }
                                }
                            }
                        } catch (Exception unused) {
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

    public void publicCircle() {
        new PerformClickUtils2().check(this.autoService, right_more_id, (executeSpeedSetting / 5) + 100, 100, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                new Thread(new Runnable() {
                    /* JADX WARNING: Failed to process nested try/catch */
                    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x001e */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                            r9 = this;
                            com.wx.assistants.service_utils.OneKeyForwardUtils$16 r0 = com.wx.assistants.service_utils.OneKeyForwardUtils.AnonymousClass16.this     // Catch:{ Exception -> 0x001e }
                            com.wx.assistants.service_utils.OneKeyForwardUtils r0 = com.wx.assistants.service_utils.OneKeyForwardUtils.this     // Catch:{ Exception -> 0x001e }
                            com.wx.assistants.service.AutoService r0 = r0.autoService     // Catch:{ Exception -> 0x001e }
                            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x001e }
                            r1.<init>()     // Catch:{ Exception -> 0x001e }
                            java.lang.String r2 = com.wx.assistants.utils.fileutil.FileUtils.getSDPath()     // Catch:{ Exception -> 0x001e }
                            r1.append(r2)     // Catch:{ Exception -> 0x001e }
                            java.lang.String r2 = "/tencent/microMsg/WeiXin"
                            r1.append(r2)     // Catch:{ Exception -> 0x001e }
                            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x001e }
                            com.fb.jjyyzjy.watermark.utils.AlbumUtil.insertImageToMediaStore(r0, r1)     // Catch:{ Exception -> 0x001e }
                        L_0x001e:
                            com.wx.assistants.service_utils.OneKeyForwardUtils$16 r0 = com.wx.assistants.service_utils.OneKeyForwardUtils.AnonymousClass16.this     // Catch:{ Exception -> 0x0044 }
                            com.wx.assistants.service_utils.OneKeyForwardUtils r0 = com.wx.assistants.service_utils.OneKeyForwardUtils.this     // Catch:{ Exception -> 0x0044 }
                            com.wx.assistants.service.AutoService r0 = r0.autoService     // Catch:{ Exception -> 0x0044 }
                            java.lang.String r1 = com.wx.assistants.service_utils.BaseServiceUtils.right_more_id     // Catch:{ Exception -> 0x0044 }
                            com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r0, r1)     // Catch:{ Exception -> 0x0044 }
                            com.wx.assistants.utils.PerformClickUtils2 r2 = new com.wx.assistants.utils.PerformClickUtils2     // Catch:{ Exception -> 0x0044 }
                            r2.<init>()     // Catch:{ Exception -> 0x0044 }
                            com.wx.assistants.service_utils.OneKeyForwardUtils$16 r0 = com.wx.assistants.service_utils.OneKeyForwardUtils.AnonymousClass16.this     // Catch:{ Exception -> 0x0044 }
                            com.wx.assistants.service_utils.OneKeyForwardUtils r0 = com.wx.assistants.service_utils.OneKeyForwardUtils.this     // Catch:{ Exception -> 0x0044 }
                            com.wx.assistants.service.AutoService r3 = r0.autoService     // Catch:{ Exception -> 0x0044 }
                            java.lang.String r4 = "从相册选择"
                            r5 = 100
                            r6 = 100
                            r7 = 100
                            com.wx.assistants.service_utils.OneKeyForwardUtils$16$1$1 r8 = new com.wx.assistants.service_utils.OneKeyForwardUtils$16$1$1     // Catch:{ Exception -> 0x0044 }
                            r8.<init>()     // Catch:{ Exception -> 0x0044 }
                            r2.checkString(r3, r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0044 }
                        L_0x0044:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.OneKeyForwardUtils.AnonymousClass16.AnonymousClass1.run():void");
                    }
                }).start();
            }
        });
    }

    public void executeMain(int i) {
        try {
            LogUtils.log("WS_BABY_count_执行开始@");
            if (this.isCompleted) {
                publicCircle();
                return;
            }
            new PerformClickUtils2().check(this.autoService, circle_list_layout_id, i, 100, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    OneKeyForwardUtils.this.excFroward();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
            try {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        showBottomView(this.mMyManager, "正在一键转发朋友圈，请不要操作微信");
        new Thread(new Runnable() {
            public void run() {
                try {
                    OneKeyForwardUtils.this.executeMain(100);
                } catch (Exception unused) {
                }
            }
        }).start();
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showStartView();
        this.mMyManager.showBackView();
    }
}
