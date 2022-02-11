package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.accessibility.AccessibilityNodeInfo;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.yalantis.ucrop.view.CropImageView;
import com.youth.banner.BannerConfig;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class LikeCommentSingleFriendUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static LikeCommentSingleFriendUtils instance;
    /* access modifiers changed from: private */
    public int commentCount = 0;
    /* access modifiers changed from: private */
    public String endTime;
    private int executeLikeComment = 0;
    /* access modifiers changed from: private */
    public int firstListSize = 0;
    /* access modifiers changed from: private */
    public boolean isHasSpread = false;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    /* access modifiers changed from: private */
    public int lastListSize = 0;
    /* access modifiers changed from: private */
    public int likeCommentType = 0;
    /* access modifiers changed from: private */
    public int likeCount = 0;
    /* access modifiers changed from: private */
    public OperationParameterModel model;
    /* access modifiers changed from: private */
    public int realCloneCount = 0;
    /* access modifiers changed from: private */
    public int recordExecuteIndex = 0;
    /* access modifiers changed from: private */
    public List<String> recordTextList = new ArrayList();
    /* access modifiers changed from: private */
    public String sayContent = "";
    /* access modifiers changed from: private */
    public int scrollPage = 0;
    private int spaceTime = 0;
    /* access modifiers changed from: private */
    public String startTime;

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
    }

    static /* synthetic */ int access$1308(LikeCommentSingleFriendUtils likeCommentSingleFriendUtils) {
        int i = likeCommentSingleFriendUtils.commentCount;
        likeCommentSingleFriendUtils.commentCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$308(LikeCommentSingleFriendUtils likeCommentSingleFriendUtils) {
        int i = likeCommentSingleFriendUtils.scrollPage;
        likeCommentSingleFriendUtils.scrollPage = i + 1;
        return i;
    }

    static /* synthetic */ int access$808(LikeCommentSingleFriendUtils likeCommentSingleFriendUtils) {
        int i = likeCommentSingleFriendUtils.realCloneCount;
        likeCommentSingleFriendUtils.realCloneCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$908(LikeCommentSingleFriendUtils likeCommentSingleFriendUtils) {
        int i = likeCommentSingleFriendUtils.likeCount;
        likeCommentSingleFriendUtils.likeCount = i + 1;
        return i;
    }

    private LikeCommentSingleFriendUtils() {
    }

    public static LikeCommentSingleFriendUtils getInstance() {
        instance = new LikeCommentSingleFriendUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        System.out.println("WS_BABY_initData");
        this.model = operationParameterModel;
        this.likeCount = 0;
        this.commentCount = 0;
        this.isHasSpread = false;
        this.realCloneCount = 0;
        this.executeLikeComment = 0;
        this.firstListSize = 0;
        this.lastListSize = 0;
        this.scrollPage = 0;
        this.recordTextList = new ArrayList();
        this.recordExecuteIndex = 0;
        this.isScrollBottom = false;
        this.likeCommentType = operationParameterModel.getLikeCommentType();
        this.startTime = operationParameterModel.getStartDate();
        this.endTime = operationParameterModel.getEndDate();
        this.sayContent = operationParameterModel.getSayContent();
        this.spaceTime = operationParameterModel.getSpaceTime();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initSnsGalleryUI() {
        try {
            System.out.println("WS_BABY_initSnsGalleryUI.0");
            new PerformClickUtils2().checkNodeId(this.autoService, "android:id/text1", executeSpeed, 50, 200, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    System.out.println("WS_BABY_initSnsGalleryUI.1");
                    String findViewIdAndGetText = PerformClickUtils.findViewIdAndGetText(LikeCommentSingleFriendUtils.this.autoService, "android:id/text1");
                    String findViewIdAndGetText2 = PerformClickUtils.findViewIdAndGetText(LikeCommentSingleFriendUtils.this.autoService, "android:id/text2");
                    if (DateUtils.isSatisfactory(LikeCommentSingleFriendUtils.this.startTime, LikeCommentSingleFriendUtils.this.endTime, findViewIdAndGetText)) {
                        PrintStream printStream = System.out;
                        printStream.println("WS_BABY_initSnsGalleryUI.2" + LikeCommentSingleFriendUtils.this.recordTextList);
                        try {
                            String findViewIdAndGetText3 = PerformClickUtils.findViewIdAndGetText(LikeCommentSingleFriendUtils.this.autoService, BaseServiceUtils.desc_text_id);
                            PrintStream printStream2 = System.out;
                            printStream2.println("WS_BABY_initSnsGalleryUI.3" + findViewIdAndGetText + findViewIdAndGetText2 + findViewIdAndGetText3);
                            LogUtils.log("WS_BABY_initSnsGalleryUI.4.scrollPage = " + LikeCommentSingleFriendUtils.this.scrollPage + ",recordExecuteIndex = " + LikeCommentSingleFriendUtils.this.recordExecuteIndex + ",firstListSize = " + LikeCommentSingleFriendUtils.this.firstListSize);
                            if ((LikeCommentSingleFriendUtils.this.scrollPage == 1 && LikeCommentSingleFriendUtils.this.recordExecuteIndex <= LikeCommentSingleFriendUtils.this.firstListSize) || ((LikeCommentSingleFriendUtils.this.isScrollBottom && LikeCommentSingleFriendUtils.this.recordExecuteIndex <= LikeCommentSingleFriendUtils.this.lastListSize) || (LikeCommentSingleFriendUtils.this.scrollPage > 1 && LikeCommentSingleFriendUtils.this.recordExecuteIndex <= 3))) {
                                System.out.println("WS_BABY_initSnsGalleryUI.5");
                                List access$200 = LikeCommentSingleFriendUtils.this.recordTextList;
                                if (access$200.contains(findViewIdAndGetText + findViewIdAndGetText2 + findViewIdAndGetText3)) {
                                    LogUtils.log("WS_BABY.SnsGalleryUI.6");
                                    PerformClickUtils.performBack(LikeCommentSingleFriendUtils.this.autoService);
                                    new PerformClickUtils2().checkNodeId(LikeCommentSingleFriendUtils.this.autoService, BaseServiceUtils.list_circle_layout_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            LikeCommentSingleFriendUtils.this.jumpExecuted(false);
                                        }
                                    });
                                    return;
                                }
                                System.out.println("WS_BABY_initSnsGalleryUI.7");
                                if (LikeCommentSingleFriendUtils.this.recordTextList.size() == 15) {
                                    LikeCommentSingleFriendUtils.this.recordTextList.remove(0);
                                }
                                List access$2002 = LikeCommentSingleFriendUtils.this.recordTextList;
                                access$2002.add(findViewIdAndGetText + findViewIdAndGetText2 + findViewIdAndGetText3);
                                LikeCommentSingleFriendUtils.this.executeImgClone();
                            } else if (LikeCommentSingleFriendUtils.this.recordTextList.size() > 0) {
                                if (((String) LikeCommentSingleFriendUtils.this.recordTextList.get(LikeCommentSingleFriendUtils.this.recordTextList.size() - 1)).equals(findViewIdAndGetText + findViewIdAndGetText2 + findViewIdAndGetText3)) {
                                    LogUtils.log("WS_BABY.SnsGalleryUI.8");
                                    PerformClickUtils.performBack(LikeCommentSingleFriendUtils.this.autoService);
                                    new PerformClickUtils2().checkNodeId(LikeCommentSingleFriendUtils.this.autoService, BaseServiceUtils.list_circle_layout_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            LikeCommentSingleFriendUtils.this.jumpExecuted(false);
                                        }
                                    });
                                    return;
                                }
                                System.out.println("WS_BABY_initSnsGalleryUI.9");
                                if (LikeCommentSingleFriendUtils.this.recordTextList.size() == 15) {
                                    LikeCommentSingleFriendUtils.this.recordTextList.remove(0);
                                }
                                List access$2003 = LikeCommentSingleFriendUtils.this.recordTextList;
                                access$2003.add(findViewIdAndGetText + findViewIdAndGetText2 + findViewIdAndGetText3);
                                LikeCommentSingleFriendUtils.this.executeImgClone();
                            } else {
                                System.out.println("WS_BABY_initSnsGalleryUI.10");
                                if (LikeCommentSingleFriendUtils.this.recordTextList.size() == 15) {
                                    LikeCommentSingleFriendUtils.this.recordTextList.remove(0);
                                }
                                List access$2004 = LikeCommentSingleFriendUtils.this.recordTextList;
                                access$2004.add(findViewIdAndGetText + findViewIdAndGetText2 + findViewIdAndGetText3);
                                LikeCommentSingleFriendUtils.this.executeImgClone();
                            }
                        } catch (Exception unused) {
                            LikeCommentSingleFriendUtils.this.executeImgClone();
                        }
                    } else if (DateUtils.endJump(LikeCommentSingleFriendUtils.this.startTime, findViewIdAndGetText)) {
                        LogUtils.log("WS_BABY.SnsGalleryUI.11");
                        BaseServiceUtils.removeEndView(LikeCommentSingleFriendUtils.this.mMyManager);
                        BaseServiceUtils.getMainHandler().post(new Runnable() {
                            public void run() {
                                String str;
                                LikeCommentSingleFriendUtils.this.mMyManager.showMiddleView();
                                MyWindowManager myWindowManager = LikeCommentSingleFriendUtils.this.mMyManager;
                                if (LikeCommentSingleFriendUtils.this.realCloneCount == 0) {
                                    str = "当前时间小于您设置的起始时间，点赞/评论结束。";
                                } else {
                                    str = "当前时间小于您设置的起始时间，点赞/评论结束。已为您点赞/评论 " + LikeCommentSingleFriendUtils.this.realCloneCount + " 条朋友圈。";
                                }
                                BaseServiceUtils.setMiddleText(myWindowManager, "点赞/评论朋友圈结束", str);
                            }
                        });
                    } else {
                        LogUtils.log("WS_BABY.SnsGalleryUI.12");
                        PerformClickUtils.performBack(LikeCommentSingleFriendUtils.this.autoService);
                        new PerformClickUtils2().checkNodeId(LikeCommentSingleFriendUtils.this.autoService, BaseServiceUtils.list_circle_layout_id, BaseServiceUtils.executeSpeed, 100, 600, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                LikeCommentSingleFriendUtils.this.jumpExecuted(false);
                            }
                        });
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeImgClone() {
        new PerformClickUtils2().checkNodeId(this.autoService, desc_right_down_id, executeSpeedSetting, 100, 20, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                LogUtils.log("WS_BABY.SnsGalleryUI");
                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LikeCommentSingleFriendUtils.this.autoService, BaseServiceUtils.desc_right_down_id);
                if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                    PerformClickUtils.performClick(findViewIdList.get(0));
                    LikeCommentSingleFriendUtils.this.initSnsCommentDetailUI();
                }
            }
        });
    }

    public void initSnsCommentDetailUI() {
        try {
            this.isHasSpread = false;
            LogUtils.log("WS_BABY.initSnsCommentDetailUI.likeCommentType = " + this.likeCommentType);
            if (this.likeCommentType != 2) {
                executePraiseMain();
            } else {
                executeCommentMain(executeSpeed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executePraiseMain() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, expand_more_id, executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LikeCommentSingleFriendUtils.this.autoService, BaseServiceUtils.expand_more_id);
                    if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                        if (LikeCommentSingleFriendUtils.this.likeCommentType == 0) {
                            LikeCommentSingleFriendUtils.this.executeCommentMain(BaseServiceUtils.executeSpeed);
                        } else {
                            LikeCommentSingleFriendUtils.this.backExecute();
                        }
                    } else if (MyApplication.enforceable) {
                        AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(0);
                        if (accessibilityNodeInfo != null && MyApplication.enforceable) {
                            accessibilityNodeInfo.performAction(16);
                            LikeCommentSingleFriendUtils.this.sleep(200);
                            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) LikeCommentSingleFriendUtils.this.autoService, BaseServiceUtils.praise_text_id);
                            LikeCommentSingleFriendUtils.this.sleep(200);
                            if (findViewIdList2 == null || findViewIdList2.size() <= 0 || !MyApplication.enforceable) {
                                if (LikeCommentSingleFriendUtils.this.likeCommentType == 0) {
                                    LikeCommentSingleFriendUtils.this.executeCommentMain(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                } else {
                                    LikeCommentSingleFriendUtils.this.backExecute();
                                }
                            } else if ("赞".equals(findViewIdList2.get(0).getText().toString())) {
                                PerformClickUtils.performClick(findViewIdList2.get(0));
                                LikeCommentSingleFriendUtils.this.sleep(300);
                                LikeCommentSingleFriendUtils.access$908(LikeCommentSingleFriendUtils.this);
                                LikeCommentSingleFriendUtils.access$808(LikeCommentSingleFriendUtils.this);
                                LikeCommentSingleFriendUtils.this.statisticsData();
                                if (LikeCommentSingleFriendUtils.this.likeCommentType == 0) {
                                    LikeCommentSingleFriendUtils.this.executeCommentMain(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                } else {
                                    LikeCommentSingleFriendUtils.this.backExecute();
                                }
                            } else {
                                LikeCommentSingleFriendUtils.access$808(LikeCommentSingleFriendUtils.this);
                                LikeCommentSingleFriendUtils.access$908(LikeCommentSingleFriendUtils.this);
                                boolean unused = LikeCommentSingleFriendUtils.this.isHasSpread = true;
                                LikeCommentSingleFriendUtils.this.statisticsData();
                                if (LikeCommentSingleFriendUtils.this.likeCommentType == 0) {
                                    LikeCommentSingleFriendUtils.this.executeCommentMain(100);
                                    return;
                                }
                                boolean unused2 = LikeCommentSingleFriendUtils.this.isHasSpread = false;
                                LikeCommentSingleFriendUtils.this.backExecute();
                            }
                        } else if (LikeCommentSingleFriendUtils.this.likeCommentType == 0) {
                            LikeCommentSingleFriendUtils.this.executeCommentMain(BaseServiceUtils.executeSpeed);
                        } else {
                            LikeCommentSingleFriendUtils.this.backExecute();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeCommentMain(int i) {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, expand_more_id, (executeSpeedSetting / 2) + i, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    if (MyApplication.enforceable) {
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LikeCommentSingleFriendUtils.this.autoService, BaseServiceUtils.desc_comment_list_text);
                        if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                            LikeCommentSingleFriendUtils.this.executeComment();
                            return;
                        }
                        ArrayList arrayList = new ArrayList();
                        for (int i2 = 0; i2 < findViewIdList.size(); i2++) {
                            CharSequence text = findViewIdList.get(i2).getText();
                            if (text != null) {
                                arrayList.add(text + "");
                            }
                        }
                        if (arrayList.contains(LikeCommentSingleFriendUtils.this.sayContent)) {
                            LikeCommentSingleFriendUtils.access$1308(LikeCommentSingleFriendUtils.this);
                            LikeCommentSingleFriendUtils.this.statisticsData();
                            LikeCommentSingleFriendUtils.this.backExecute();
                        } else {
                            LikeCommentSingleFriendUtils.this.executeComment();
                        }
                        LikeCommentSingleFriendUtils.access$808(LikeCommentSingleFriendUtils.this);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeComment() {
        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, expand_more_id);
        if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
            LogUtils.log("WS_BABY_executeComment.6");
            backExecute();
            return;
        }
        LogUtils.log("WS_BABY_executeComment.0");
        AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(0);
        if (accessibilityNodeInfo != null && MyApplication.enforceable) {
            if (!this.isHasSpread) {
                accessibilityNodeInfo.performAction(16);
                sleep(200);
            }
            this.isHasSpread = false;
            sleep(300);
            LogUtils.log("WS_BABY_executeComment.1");
            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, desc_comment_text_id);
            if (findViewIdList2 == null || findViewIdList2.size() <= 0 || !MyApplication.enforceable) {
                LogUtils.log("WS_BABY_executeComment.5");
                return;
            }
            LogUtils.log("WS_BABY_executeComment.2");
            if ("评论".equals(findViewIdList2.get(0).getText().toString())) {
                LogUtils.log("WS_BABY_executeComment.3");
                PerformClickUtils.performClick(findViewIdList2.get(0));
                sleep(100);
                inputText(this.sayContent);
                new PerformClickUtils2().checkString(this.autoService, "发送", CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 50, 20, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.findTextAndClick(LikeCommentSingleFriendUtils.this.autoService, "发送");
                        new PerformClickUtils2().checkString(LikeCommentSingleFriendUtils.this.autoService, LikeCommentSingleFriendUtils.this.sayContent, 300, 100, 7, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                LikeCommentSingleFriendUtils.access$1308(LikeCommentSingleFriendUtils.this);
                                LikeCommentSingleFriendUtils.this.statisticsData();
                                LikeCommentSingleFriendUtils.this.backExecute();
                            }

                            public void nuFind() {
                                LikeCommentSingleFriendUtils.access$1308(LikeCommentSingleFriendUtils.this);
                                LikeCommentSingleFriendUtils.this.statisticsData();
                                LikeCommentSingleFriendUtils.this.backExecute();
                            }
                        });
                    }

                    public void nuFind() {
                        LikeCommentSingleFriendUtils.access$1308(LikeCommentSingleFriendUtils.this);
                        LikeCommentSingleFriendUtils.this.statisticsData();
                        LikeCommentSingleFriendUtils.this.backExecute();
                    }
                });
                return;
            }
            LogUtils.log("WS_BABY_executeComment.4");
        }
    }

    public void statisticsData() {
        if (this.likeCommentType == 0) {
            MyWindowManager myWindowManager = this.mMyManager;
            showBottomView(myWindowManager, "已为您点赞 " + this.likeCount + "个，评论 " + this.commentCount + "个。\n正在自动点赞/评论朋友圈，请务操作微信。");
        } else if (this.likeCommentType == 1) {
            MyWindowManager myWindowManager2 = this.mMyManager;
            showBottomView(myWindowManager2, "已为您点赞 " + this.likeCount + "个。\n正在自动点赞朋友圈，请务操作微信。");
        } else {
            MyWindowManager myWindowManager3 = this.mMyManager;
            showBottomView(myWindowManager3, "已为您评论 " + this.commentCount + "个。\n正在自动评论朋友圈，请务操作微信。");
        }
    }

    public void backExecute() {
        if (MyApplication.enforceable) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        LikeCommentSingleFriendUtils.this.sleep(BannerConfig.DURATION);
                        if (PerformClickUtils.findNodeIsExistById((AccessibilityService) LikeCommentSingleFriendUtils.this.autoService, BaseServiceUtils.list_layout_child_id)) {
                            System.out.println("WWW");
                            LikeCommentSingleFriendUtils.this.jumpExecuted(false);
                            return;
                        }
                        PerformClickUtils.performBack(LikeCommentSingleFriendUtils.this.autoService);
                        LikeCommentSingleFriendUtils.this.backExecute();
                    } catch (Exception unused) {
                    }
                }
            }).start();
        }
    }

    public void executeMain() {
        try {
            System.out.println("isExecuteMain.x");
            new PerformClickUtils2().checkCloneCircle(this.autoService, "朋友圈", "发消息", album_head_img, list_layout_child_id, (executeSpeedSetting / 10) + 300, 100, 5, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    System.out.println("isExecuteMain.0");
                    if (i == 0) {
                        System.out.println("isExecuteMain.1");
                        PerformClickUtils.findTextAndClick(LikeCommentSingleFriendUtils.this.autoService, "朋友圈");
                        LikeCommentSingleFriendUtils.this.executeClickCircle();
                    } else if (i == 1) {
                        System.out.println("isExecuteMain.2");
                        LikeCommentSingleFriendUtils.this.executeClickCircle();
                    }
                }

                public void nuFind() {
                    System.out.println("isExecuteMain.5");
                    new PerformClickUtils2().checkNodeId(LikeCommentSingleFriendUtils.this.autoService, BaseServiceUtils.album_head_img, 10, 100, 2, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            System.out.println("isExecuteMain.6");
                            new PerformClickUtils2().checkNodeId(LikeCommentSingleFriendUtils.this.autoService, BaseServiceUtils.friends_circle_head_img_id, 10, 100, 2, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    System.out.println("isExecuteMain.7");
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        public void run() {
                                            BaseServiceUtils.removeEndView(LikeCommentSingleFriendUtils.this.mMyManager);
                                            LikeCommentSingleFriendUtils.this.mMyManager.toastToUserError("请前往好友详情页");
                                            LikeCommentSingleFriendUtils.this.initData(LikeCommentSingleFriendUtils.this.model);
                                        }
                                    });
                                }

                                public void nuFind() {
                                    new PerformClickUtils2().checkNodeId(LikeCommentSingleFriendUtils.this.autoService, BaseServiceUtils.back_contact_id, 10, 100, 2, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            System.out.println("isExecuteMain.8");
                                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                                public void run() {
                                                    BaseServiceUtils.removeEndView(LikeCommentSingleFriendUtils.this.mMyManager);
                                                    LikeCommentSingleFriendUtils.this.mMyManager.toastToUserError("您的好友，貌似没有朋友圈动态哦");
                                                    LikeCommentSingleFriendUtils.this.initData(LikeCommentSingleFriendUtils.this.model);
                                                }
                                            });
                                        }

                                        public void nuFind() {
                                            System.out.println("isExecuteMain.10");
                                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                                public void run() {
                                                    BaseServiceUtils.removeEndView(LikeCommentSingleFriendUtils.this.mMyManager);
                                                    LikeCommentSingleFriendUtils.this.mMyManager.toastToUserError("请前往好友详情页");
                                                    LikeCommentSingleFriendUtils.this.initData(LikeCommentSingleFriendUtils.this.model);
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }

                        public void nuFind() {
                            System.out.println("isExecuteMain.9");
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    BaseServiceUtils.removeEndView(LikeCommentSingleFriendUtils.this.mMyManager);
                                    LikeCommentSingleFriendUtils.this.mMyManager.toastToUserError("请前往好友详情页");
                                    LikeCommentSingleFriendUtils.this.initData(LikeCommentSingleFriendUtils.this.model);
                                }
                            });
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("WS_BABY_SnsUserUI.Exception");
        }
    }

    public void executeClickCircle() {
        new PerformClickUtils2().checkNodeId(this.autoService, album_head_img, executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                new PerformClickUtils2().checkNilString(LikeCommentSingleFriendUtils.this.autoService, "正在加载", (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 200, 70, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        new PerformClickUtils2().checkNodeAllIds(LikeCommentSingleFriendUtils.this.autoService, BaseServiceUtils.list_circle_layout_id, BaseServiceUtils.album_head_img, BaseServiceUtils.executeSpeed, 100, 5, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                LikeCommentSingleFriendUtils.this.jumpExecuted(false);
                            }

                            public void nuFind() {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    public void run() {
                                        BaseServiceUtils.removeEndView(LikeCommentSingleFriendUtils.this.mMyManager);
                                        LikeCommentSingleFriendUtils.this.mMyManager.toastToUserError("您的好友，貌似没有朋友圈动态哦");
                                        LikeCommentSingleFriendUtils.this.initData(LikeCommentSingleFriendUtils.this.model);
                                    }
                                });
                            }
                        });
                    }

                    public void nuFind() {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                BaseServiceUtils.removeEndView(LikeCommentSingleFriendUtils.this.mMyManager);
                                LikeCommentSingleFriendUtils.this.mMyManager.toastToUserError("检测到一直正在加载，请确定网络是否可用后，在重试！");
                                LikeCommentSingleFriendUtils.this.initData(LikeCommentSingleFriendUtils.this.model);
                            }
                        });
                    }
                });
            }
        });
    }

    public void jumpExecuted(boolean z) {
        if (z) {
            for (int i = 0; i < this.scrollPage; i++) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = this.autoService.getRootInActiveWindow().findAccessibilityNodeInfosByViewId(list_circle_id);
                if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                    findAccessibilityNodeInfosByViewId.get(0).performAction(4096);
                    sleep(MyApplication.SCROLL_SPEED * 5);
                }
            }
        }
        this.executeLikeComment++;
        if (this.spaceTime <= 0 || this.executeLikeComment <= 1) {
            LogUtils.log("WS_BABY.FTSMainUI.into4");
            joinAlbumListPage();
            return;
        }
        new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
            public void surplus(int i) {
                if (LikeCommentSingleFriendUtils.this.likeCommentType == 0) {
                    MyWindowManager myWindowManager = LikeCommentSingleFriendUtils.this.mMyManager;
                    BaseServiceUtils.showBottomView(myWindowManager, "已为您点赞 " + LikeCommentSingleFriendUtils.this.likeCount + "个，评论 " + LikeCommentSingleFriendUtils.this.commentCount + "个。\n正在延时等待，倒计时 " + i + " 秒");
                } else if (LikeCommentSingleFriendUtils.this.likeCommentType == 1) {
                    MyWindowManager myWindowManager2 = LikeCommentSingleFriendUtils.this.mMyManager;
                    BaseServiceUtils.showBottomView(myWindowManager2, "已为您点赞 " + LikeCommentSingleFriendUtils.this.likeCount + "个。\n正在延时等待，倒计时 " + i + " 秒");
                } else {
                    MyWindowManager myWindowManager3 = LikeCommentSingleFriendUtils.this.mMyManager;
                    BaseServiceUtils.showBottomView(myWindowManager3, "已为您评论 " + LikeCommentSingleFriendUtils.this.commentCount + "个。\n正在延时等待，倒计时 " + i + " 秒");
                }
            }

            public void end() {
                if (LikeCommentSingleFriendUtils.this.likeCommentType == 0) {
                    MyWindowManager myWindowManager = LikeCommentSingleFriendUtils.this.mMyManager;
                    BaseServiceUtils.showBottomView(myWindowManager, "已为您点赞 " + LikeCommentSingleFriendUtils.this.likeCount + "个，评论 " + LikeCommentSingleFriendUtils.this.commentCount + "个。\n正在自动点赞/评论朋友圈，请务操作微信。");
                } else if (LikeCommentSingleFriendUtils.this.likeCommentType == 1) {
                    MyWindowManager myWindowManager2 = LikeCommentSingleFriendUtils.this.mMyManager;
                    BaseServiceUtils.showBottomView(myWindowManager2, "已为您点赞 " + LikeCommentSingleFriendUtils.this.likeCount + "个。\n正在自动点赞朋友圈，请务操作微信。");
                } else {
                    MyWindowManager myWindowManager3 = LikeCommentSingleFriendUtils.this.mMyManager;
                    BaseServiceUtils.showBottomView(myWindowManager3, "已为您评论 " + LikeCommentSingleFriendUtils.this.commentCount + "个。\n正在自动评论朋友圈，请务操作微信。");
                }
                LogUtils.log("WS_BABY.FTSMainUI.into3");
                LikeCommentSingleFriendUtils.this.joinAlbumListPage();
            }
        });
    }

    public void joinAlbumListPage() {
        if (MyApplication.enforceable) {
            System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.00 = " + this.recordExecuteIndex);
            AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
            System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.0");
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(list_circle_layout_id);
            System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.1");
            if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || !MyApplication.enforceable) {
                System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.16");
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        BaseServiceUtils.removeEndView(LikeCommentSingleFriendUtils.this.mMyManager);
                        LikeCommentSingleFriendUtils.this.mMyManager.toastToUserError("请前往好友详情页");
                        LikeCommentSingleFriendUtils.this.initData(LikeCommentSingleFriendUtils.this.model);
                    }
                });
                return;
            }
            if (this.scrollPage == 0) {
                this.firstListSize = findAccessibilityNodeInfosByViewId.size();
                this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(this.autoService, "朋友仅展示最近");
                if (!this.isScrollBottom) {
                    this.isScrollBottom = PerformClickUtils.findNodeIsExistById((AccessibilityService) this.autoService, bottom_line);
                }
                if (PerformClickUtils.findNodeIsExistByText(this.autoService, "拍照分享")) {
                    getMainHandler().post(new Runnable() {
                        public void run() {
                            BaseServiceUtils.removeEndView(LikeCommentSingleFriendUtils.this.mMyManager);
                            LikeCommentSingleFriendUtils.this.mMyManager.toastToUserError("不能点赞/评论自己的朋友圈");
                            LikeCommentSingleFriendUtils.this.initData(LikeCommentSingleFriendUtils.this.model);
                        }
                    });
                    return;
                }
            }
            if (this.isScrollBottom) {
                this.lastListSize = findAccessibilityNodeInfosByViewId.size();
            }
            System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.2");
            if (this.recordExecuteIndex < findAccessibilityNodeInfosByViewId.size()) {
                System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.3.size = " + findAccessibilityNodeInfosByViewId.size());
                AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(this.recordExecuteIndex);
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(list_album_video_img);
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(list_layout_2_id);
                System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.5");
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId4 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(list_layout_only_text_id);
                if (findAccessibilityNodeInfosByViewId3 == null || findAccessibilityNodeInfosByViewId3.size() <= 0 || !MyApplication.enforceable) {
                    System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.13");
                    this.recordExecuteIndex++;
                    if (this.model.getCloneCircleType() == 4 || this.model.getCloneCircleType() == 1) {
                        System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.14");
                        if (findAccessibilityNodeInfosByViewId4 == null || findAccessibilityNodeInfosByViewId4.size() <= 0 || !MyApplication.enforceable) {
                            System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.16");
                            joinAlbumListPage();
                            return;
                        }
                        System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.15");
                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId4.get(0));
                        new PerformClickUtils2().checkNodeId(this.autoService, desc_text_time_id, executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                String findViewIdAndGetText = PerformClickUtils.findViewIdAndGetText(LikeCommentSingleFriendUtils.this.autoService, BaseServiceUtils.desc_text_time_id);
                                if (DateUtils.isSatisfactory(LikeCommentSingleFriendUtils.this.startTime, LikeCommentSingleFriendUtils.this.endTime, findViewIdAndGetText)) {
                                    LikeCommentSingleFriendUtils.this.initSnsCommentDetailUI();
                                } else if (DateUtils.endJump(LikeCommentSingleFriendUtils.this.startTime, findViewIdAndGetText)) {
                                    LogUtils.log("WS_BABY.SnsGalleryUI.11");
                                    BaseServiceUtils.removeEndView(LikeCommentSingleFriendUtils.this.mMyManager);
                                    BaseServiceUtils.getMainHandler().post(new Runnable() {
                                        public void run() {
                                            String str;
                                            LikeCommentSingleFriendUtils.this.mMyManager.showMiddleView();
                                            MyWindowManager myWindowManager = LikeCommentSingleFriendUtils.this.mMyManager;
                                            if (LikeCommentSingleFriendUtils.this.realCloneCount == 0) {
                                                str = "当前时间小于您设置的起始时间，点赞/评论结束。";
                                            } else {
                                                str = "当前时间小于您设置的起始时间，点赞/评论结束。已为您点赞/评论 " + LikeCommentSingleFriendUtils.this.realCloneCount + " 条朋友圈。";
                                            }
                                            BaseServiceUtils.setMiddleText(myWindowManager, "点赞/评论朋友圈结束", str);
                                        }
                                    });
                                } else {
                                    LogUtils.log("WS_BABY.SnsGalleryUI.12");
                                    PerformClickUtils.performBack(LikeCommentSingleFriendUtils.this.autoService);
                                    new PerformClickUtils2().checkNodeId(LikeCommentSingleFriendUtils.this.autoService, BaseServiceUtils.list_circle_layout_id, BaseServiceUtils.executeSpeed, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            LikeCommentSingleFriendUtils.this.jumpExecuted(false);
                                        }
                                    });
                                }
                            }
                        });
                        return;
                    }
                    System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.26");
                    joinAlbumListPage();
                    return;
                }
                System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.6");
                if (this.model.getCloneCircleType() == 0 || this.model.getCloneCircleType() == 1 || ((this.model.getCloneCircleType() == 2 && (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() == 0)) || (this.model.getCloneCircleType() == 3 && findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0))) {
                    System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.7");
                    this.recordExecuteIndex++;
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId5 = findAccessibilityNodeInfosByViewId3.get(0).findAccessibilityNodeInfosByViewId(list_circle_img_video_id);
                    System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.8");
                    if (findAccessibilityNodeInfosByViewId5 == null || findAccessibilityNodeInfosByViewId5.size() <= 0) {
                        System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.10");
                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId3.get(0));
                    } else {
                        System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.9");
                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId5.get(0));
                    }
                    System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.11");
                    initSnsGalleryUI();
                    return;
                }
                System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.12");
                this.recordExecuteIndex++;
                joinAlbumListPage();
            } else if (this.recordExecuteIndex >= findAccessibilityNodeInfosByViewId.size()) {
                if (this.isScrollBottom) {
                    getMainHandler().post(new Runnable() {
                        public void run() {
                            String str;
                            BaseServiceUtils.removeEndView(LikeCommentSingleFriendUtils.this.mMyManager);
                            LikeCommentSingleFriendUtils.this.mMyManager.showMiddleView();
                            MyWindowManager myWindowManager = LikeCommentSingleFriendUtils.this.mMyManager;
                            if (LikeCommentSingleFriendUtils.this.realCloneCount == 0) {
                                str = "已经检测到滚动底部了，点赞/评论结束。";
                            } else {
                                str = "已经检测到滚动底部了，点赞/评论结束。已为您点赞/评论 " + LikeCommentSingleFriendUtils.this.realCloneCount + " 条朋友圈。";
                            }
                            BaseServiceUtils.setMiddleText(myWindowManager, "点赞/评论朋友圈结束", str);
                        }
                    });
                }
                System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.14");
                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_circle_id);
                if (findViewIdList != null && findViewIdList.size() != 0) {
                    findViewIdList.get(0).performAction(4096);
                    sleep(MyApplication.SCROLL_SPEED * 5);
                    new PerformClickUtils2().checkNilString(this.autoService, "正在加载", (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, (int) SocializeConstants.CANCLE_RESULTCODE, 300, (PerformClickUtils2.OnCheckListener5) new PerformClickUtils2.OnCheckListener5() {
                        public void find(int i) {
                            LikeCommentSingleFriendUtils.this.statisticsData();
                            boolean unused = LikeCommentSingleFriendUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(LikeCommentSingleFriendUtils.this.autoService, "朋友仅展示最近");
                            if (!LikeCommentSingleFriendUtils.this.isScrollBottom) {
                                boolean unused2 = LikeCommentSingleFriendUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistById((AccessibilityService) LikeCommentSingleFriendUtils.this.autoService, BaseServiceUtils.bottom_line);
                            }
                            PrintStream printStream = System.out;
                            printStream.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.15.isScrollBottom = " + LikeCommentSingleFriendUtils.this.isScrollBottom);
                            int unused3 = LikeCommentSingleFriendUtils.this.recordExecuteIndex = 1;
                            LikeCommentSingleFriendUtils.access$308(LikeCommentSingleFriendUtils.this);
                            LikeCommentSingleFriendUtils.this.joinAlbumListPage();
                        }

                        public void execute(int i) {
                            if (i > 1) {
                                if (LikeCommentSingleFriendUtils.this.likeCommentType == 0) {
                                    MyWindowManager myWindowManager = LikeCommentSingleFriendUtils.this.mMyManager;
                                    BaseServiceUtils.showBottomView(myWindowManager, "已为您点赞 " + LikeCommentSingleFriendUtils.this.likeCount + "个，评论 " + LikeCommentSingleFriendUtils.this.commentCount + "个。\n检测到加载失败，请手动上拉刷新！倒计时（" + (300 - i) + "）秒");
                                } else if (LikeCommentSingleFriendUtils.this.likeCommentType == 1) {
                                    MyWindowManager myWindowManager2 = LikeCommentSingleFriendUtils.this.mMyManager;
                                    BaseServiceUtils.showBottomView(myWindowManager2, "已为您点赞 " + LikeCommentSingleFriendUtils.this.likeCount + "个。\n检测到加载失败，请手动上拉刷新！倒计时（" + (300 - i) + "）秒");
                                } else {
                                    MyWindowManager myWindowManager3 = LikeCommentSingleFriendUtils.this.mMyManager;
                                    BaseServiceUtils.showBottomView(myWindowManager3, "已为您评论 " + LikeCommentSingleFriendUtils.this.commentCount + "个。\n检测到加载失败，请手动上拉刷新！倒计时（" + (300 - i) + "）秒");
                                }
                                if ((i == 2 || i == 7) && Build.VERSION.SDK_INT >= 24) {
                                    LogUtils.log("WS_BABY_DATE..........1");
                                    PerformClickUtils.upPull();
                                }
                            }
                        }

                        public void nuFind() {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    BaseServiceUtils.removeEndView(LikeCommentSingleFriendUtils.this.mMyManager);
                                    LikeCommentSingleFriendUtils.this.mMyManager.toastToUserError("检测到一直正在加载，请确定网络是否可用后，在重试！");
                                }
                            });
                        }
                    });
                }
            }
        }
    }

    public void endViewShow() {
        try {
            if (this.likeCommentType == 0) {
                showBottomView(this.mMyManager, "正在一键点赞/评论朋友圈，请不要操作微信");
            } else if (this.likeCommentType == 1) {
                showBottomView(this.mMyManager, "正在一键点赞朋友圈，请不要操作微信");
            } else {
                showBottomView(this.mMyManager, "正在一键评论朋友圈，请不要操作微信");
            }
            executeMain();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewDismiss() {
        try {
            LogUtils.log("WS_BABY_endViewDismiss");
            this.mMyManager.stopAccessibilityService();
            MyApplication.enforceable = false;
            this.mMyManager.removeBottomView();
            this.mMyManager.showStartView();
            this.mMyManager.showBackView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
