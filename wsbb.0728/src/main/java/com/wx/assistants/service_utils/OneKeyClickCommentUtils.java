package com.wx.assistants.service_utils;

import android.view.accessibility.AccessibilityNodeInfo;
import com.luck.picture.lib.config.PictureConfig;
import com.umeng.socialize.sina.params.ShareRequestParam;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

public class OneKeyClickCommentUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static OneKeyClickCommentUtils instance;
    private static ArrayList<String> recordClickTags = new ArrayList<>();
    private static ArrayList<String> recordTags = new ArrayList<>();
    private int exeCommentTaskNum = 0;
    private int exeZanTaskNum = 0;
    private boolean isWhile = true;
    private int maxOperaNum;
    private OperationParameterModel model;
    private String sayContent;

    private OneKeyClickCommentUtils() {
    }

    public static OneKeyClickCommentUtils getInstance() {
        instance = new OneKeyClickCommentUtils();
        return instance;
    }

    public void endViewDismiss() {
        try {
            this.mMyManager.stopAccessibilityService();
            this.mMyManager.removeBottomView();
            this.mMyManager.showMiddleView();
            if (this.model != null) {
                int praiseCommentType = this.model.getPraiseCommentType();
                if (praiseCommentType == 0) {
                    this.mMyManager.showPraiseStartView();
                    this.mMyManager.showCommentStartView();
                } else if (praiseCommentType == 1) {
                    this.mMyManager.showPraiseStartView();
                } else {
                    this.mMyManager.showCommentStartView();
                }
            } else {
                this.mMyManager.showPraiseStartView();
                this.mMyManager.showCommentStartView();
            }
            this.mMyManager.showBackView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewShow() {
        try {
            this.mMyManager.showBottomView();
            if (MyApplication.isComment) {
                showBottomView(this.mMyManager, "正在自动一键评论,请不要操作微信");
            } else {
                showBottomView(this.mMyManager, "正在自动一键点赞,请不要操作微信");
            }
            new Thread(new Runnable() {
                public void run() {
                    try {
                        if (MyApplication.isComment) {
                            OneKeyClickCommentUtils.this.executeCommentMain();
                        } else {
                            OneKeyClickCommentUtils.this.executePraiseMain();
                        }
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0093, code lost:
        r0 = r0.getParent();
     */
    public void executeCommentMain() {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        StringBuilder sb;
        AccessibilityNodeInfo accessibilityNodeInfo;
        AccessibilityNodeInfo accessibilityNodeInfo2;
        AccessibilityNodeInfo accessibilityNodeInfo3;
        AccessibilityNodeInfo accessibilityNodeInfo4;
        AccessibilityNodeInfo accessibilityNodeInfo5;
        while (this.isWhile && MyApplication.enforceable) {
            try {
                AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
                StringBuilder sb2 = new StringBuilder();
                if (rootInActiveWindow != null && MyApplication.enforceable) {
                    sleep(100);
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(circle_list_layout_id);
                    if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                        int i = 0;
                        while (true) {
                            if (i >= findAccessibilityNodeInfosByViewId2.size() || !MyApplication.enforceable) {
                                break;
                            }
                            AccessibilityNodeInfo accessibilityNodeInfo6 = findAccessibilityNodeInfosByViewId2.get(i);
                            if (accessibilityNodeInfo6 != null && MyApplication.enforceable) {
                                AccessibilityNodeInfo accessibilityNodeInfo7 = ((wxVersionCode > 1620 || (wxVersionCode == 1620 && "7.0.13".equals(wxVersionName))) && accessibilityNodeInfo6 == null) ? findAccessibilityNodeInfosByViewId2.get(i) : accessibilityNodeInfo6;
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = accessibilityNodeInfo7.findAccessibilityNodeInfosByViewId(circle_list_layout_nickname_id);
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId4 = accessibilityNodeInfo7.findAccessibilityNodeInfosByViewId(circle_list_layout_hide_text_id);
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId5 = accessibilityNodeInfo7.findAccessibilityNodeInfosByViewId(circle_list_layout_text_id);
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId6 = accessibilityNodeInfo7.findAccessibilityNodeInfosByViewId(circle_list_layout_img_id);
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId7 = accessibilityNodeInfo7.findAccessibilityNodeInfosByViewId(circle_list_layout_video_id);
                                if (!(findAccessibilityNodeInfosByViewId3 == null || findAccessibilityNodeInfosByViewId3.size() <= 0 || !MyApplication.enforceable || (accessibilityNodeInfo5 = findAccessibilityNodeInfosByViewId3.get(0)) == null || accessibilityNodeInfo5.getText() == null || accessibilityNodeInfo5.getText().toString() == null)) {
                                    sb2.append(accessibilityNodeInfo5.getText() + "");
                                    accessibilityNodeInfo5.getText() + "";
                                }
                                if (!(findAccessibilityNodeInfosByViewId4 == null || findAccessibilityNodeInfosByViewId4.size() <= 0 || !MyApplication.enforceable || (accessibilityNodeInfo4 = findAccessibilityNodeInfosByViewId4.get(0)) == null || accessibilityNodeInfo4.getText() == null || accessibilityNodeInfo4.getText().toString() == null)) {
                                    sb2.append(accessibilityNodeInfo4.getText() + "");
                                }
                                if (!(findAccessibilityNodeInfosByViewId5 == null || findAccessibilityNodeInfosByViewId5.size() <= 0 || !MyApplication.enforceable || (accessibilityNodeInfo3 = findAccessibilityNodeInfosByViewId5.get(0)) == null || accessibilityNodeInfo3.getText() == null || accessibilityNodeInfo3.getText().toString() == null)) {
                                    sb2.append(accessibilityNodeInfo3.getText() + "");
                                }
                                if (findAccessibilityNodeInfosByViewId6 != null && findAccessibilityNodeInfosByViewId6.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId6.get(0)) != null && accessibilityNodeInfo2.getChildCount() >= 0) {
                                    sb2.append(ShareRequestParam.REQ_UPLOAD_PIC_PARAM_IMG + accessibilityNodeInfo2.getChildCount());
                                }
                                if (findAccessibilityNodeInfosByViewId7 != null && findAccessibilityNodeInfosByViewId7.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId7.get(0)) != null && accessibilityNodeInfo.getChildCount() >= 0) {
                                    sb2.append(PictureConfig.VIDEO + accessibilityNodeInfo.getChildCount());
                                }
                                LogUtils.log("WS_BABY_COMMENT_" + sb2.toString());
                                if (recordTags == null || recordTags.contains(sb2.toString()) || !MyApplication.enforceable) {
                                    sb = new StringBuilder();
                                    LogUtils.log("WS_BABY_COMMENT_1");
                                    sb2 = sb;
                                    i++;
                                } else {
                                    LogUtils.log("WS_BABY_COMMENT_init");
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId8 = accessibilityNodeInfo7.findAccessibilityNodeInfosByViewId(expand_more_id);
                                    if (findAccessibilityNodeInfosByViewId8 == null || findAccessibilityNodeInfosByViewId8.size() <= 0 || !MyApplication.enforceable) {
                                        sb = new StringBuilder();
                                        LogUtils.log("WS_BABY_COMMENT_0");
                                        sb2 = sb;
                                        i++;
                                    } else {
                                        LogUtils.log("WS_BABY_COMMENT_2");
                                        recordTags.add(sb2.toString());
                                        if (recordTags.size() > 5) {
                                            recordTags.remove(0);
                                        }
                                        AccessibilityNodeInfo accessibilityNodeInfo8 = findAccessibilityNodeInfosByViewId8.get(0);
                                        if (accessibilityNodeInfo8 != null && MyApplication.enforceable) {
                                            accessibilityNodeInfo8.performAction(16);
                                            sleep(300);
                                            AccessibilityNodeInfo rootInActiveWindow2 = this.autoService.getRootInActiveWindow();
                                            if (rootInActiveWindow2 != null && MyApplication.enforceable) {
                                                LogUtils.log("WS_BABY_COMMENT_3");
                                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId9 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(comment_text_id);
                                                if (findAccessibilityNodeInfosByViewId9 == null || findAccessibilityNodeInfosByViewId9.size() <= 0 || !MyApplication.enforceable) {
                                                    LogUtils.log("WS_BABY_COMMENT_8");
                                                    sb = sb2;
                                                    sb2 = sb;
                                                    i++;
                                                } else {
                                                    LogUtils.log("WS_BABY_COMMENT_5");
                                                    if ("评论".equals(findAccessibilityNodeInfosByViewId9.get(0).getText().toString())) {
                                                        LogUtils.log("WS_BABY_COMMENT_6");
                                                        this.exeCommentTaskNum++;
                                                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId9.get(0));
                                                        sleep(100);
                                                        inputText(this.sayContent);
                                                        sleep(150);
                                                        PerformClickUtils.findTextAndClick(this.autoService, "发送");
                                                        sleep((executeSpeedSetting / 2) + 600);
                                                        updateBottomText(this.mMyManager, "正在执行一键评论,已评论了 " + this.exeCommentTaskNum + " 条");
                                                        if (this.exeCommentTaskNum >= this.maxOperaNum) {
                                                            this.isWhile = false;
                                                            removeEndView(this.mMyManager);
                                                        } else {
                                                            new StringBuilder();
                                                        }
                                                    } else {
                                                        LogUtils.log("WS_BABY_COMMENT_7");
                                                        sb = sb2;
                                                        sb2 = sb;
                                                        i++;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            sb = sb2;
                            sb2 = sb;
                            i++;
                        }
                        if (this.isWhile && MyApplication.enforceable && rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(list_circle_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                            findAccessibilityNodeInfosByViewId.get(0).performAction(4096);
                            sleep(MyApplication.SCROLL_SPEED);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void executePraiseMain() {
        AccessibilityNodeInfo accessibilityNodeInfo;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        AccessibilityNodeInfo accessibilityNodeInfo2;
        try {
            AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null && MyApplication.enforceable) {
                while (this.isWhile && MyApplication.enforceable && rootInActiveWindow != null) {
                    sleep(100);
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(list_circle_id);
                    if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId2.get(0)) != null && MyApplication.enforceable) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(expand_more_id);
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId4 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(circle_list_layout_nickname_id);
                        if (findAccessibilityNodeInfosByViewId3 != null && findAccessibilityNodeInfosByViewId3.size() > 0 && MyApplication.enforceable) {
                            int i = 0;
                            while (true) {
                                if (i >= findAccessibilityNodeInfosByViewId3.size()) {
                                    break;
                                } else if (MyApplication.enforceable) {
                                    if (!(findAccessibilityNodeInfosByViewId4 == null || findAccessibilityNodeInfosByViewId4.size() <= 0 || !MyApplication.enforceable || (accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId4.get(0)) == null || accessibilityNodeInfo2.getText() == null || accessibilityNodeInfo2.getText().toString() == null)) {
                                        accessibilityNodeInfo2.getText() + "";
                                    }
                                    AccessibilityNodeInfo accessibilityNodeInfo3 = findAccessibilityNodeInfosByViewId3.get(i);
                                    if (accessibilityNodeInfo3 != null && MyApplication.enforceable) {
                                        accessibilityNodeInfo3.performAction(16);
                                        Thread.sleep(300);
                                        AccessibilityNodeInfo rootInActiveWindow2 = this.autoService.getRootInActiveWindow();
                                        if (rootInActiveWindow2 != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(praise_text_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable && "赞".equals(findAccessibilityNodeInfosByViewId.get(0).getText().toString())) {
                                            this.exeZanTaskNum++;
                                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                            sleep((executeSpeedSetting / 2) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                            updateBottomText(this.mMyManager, "正在执行一键点赞,已点赞了 " + this.exeZanTaskNum + " 条");
                                            if (this.exeZanTaskNum >= this.maxOperaNum) {
                                                this.isWhile = false;
                                                removeEndView(this.mMyManager);
                                                break;
                                            }
                                        }
                                    }
                                    i++;
                                } else {
                                    return;
                                }
                            }
                            if (this.isWhile && MyApplication.enforceable) {
                                accessibilityNodeInfo.performAction(4096);
                                sleep(MyApplication.SCROLL_SPEED);
                            }
                        } else if (this.isWhile && MyApplication.enforceable) {
                            accessibilityNodeInfo.performAction(4096);
                            sleep(MyApplication.SCROLL_SPEED);
                        }
                    }
                }
            }
        } catch (Exception e) {
            try {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.exeZanTaskNum = 0;
        this.exeCommentTaskNum = 0;
        this.isWhile = true;
        this.model = operationParameterModel;
        if (MyApplication.isComment) {
            this.maxOperaNum = operationParameterModel.getMaxCommentNum();
        } else {
            this.maxOperaNum = operationParameterModel.getMaxPraiseNum();
        }
        this.sayContent = operationParameterModel.getSayContent();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        try {
            if (MyApplication.isComment) {
                MyWindowManager myWindowManager = this.mMyManager;
                updateMiddleText(myWindowManager, "一键自动评论", "评论了 " + this.exeCommentTaskNum + " 条");
                return;
            }
            MyWindowManager myWindowManager2 = this.mMyManager;
            updateMiddleText(myWindowManager2, "一键自动点赞", "点赞了 " + this.exeZanTaskNum + " 条");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
