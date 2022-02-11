package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class LikeCommentFriendsUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static LikeCommentFriendsUtils instance;
    private List<String> beforeLists = new ArrayList();
    /* access modifiers changed from: private */
    public int commentCount = 0;
    /* access modifiers changed from: private */
    public int excFriendsNum = 0;
    private boolean isFirst = true;
    /* access modifiers changed from: private */
    public boolean isHasSpread = false;
    /* access modifiers changed from: private */
    public boolean isJumpedStart = true;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> jumpPersons = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public int likeCommentType = 0;
    /* access modifiers changed from: private */
    public int likeCount = 0;
    List<AccessibilityNodeInfo> nameNodes = null;
    List<AccessibilityNodeInfo> nameNodes2 = null;
    /* access modifiers changed from: private */
    public String nowSearchName = "";
    /* access modifiers changed from: private */
    public OperationParameterModel operationParameterModel;
    private String recordNowName = "";
    /* access modifiers changed from: private */
    public String sayContent;
    private int scrollCount = 0;
    private int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startInt = 0;
    /* access modifiers changed from: private */
    public int tagFriendType = 0;

    private LikeCommentFriendsUtils() {
    }

    static /* synthetic */ int access$1008(LikeCommentFriendsUtils likeCommentFriendsUtils) {
        int i = likeCommentFriendsUtils.likeCount;
        likeCommentFriendsUtils.likeCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$1108(LikeCommentFriendsUtils likeCommentFriendsUtils) {
        int i = likeCommentFriendsUtils.commentCount;
        likeCommentFriendsUtils.commentCount = i + 1;
        return i;
    }

    public static LikeCommentFriendsUtils getInstance() {
        instance = new LikeCommentFriendsUtils();
        return instance;
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        try {
            this.isFirst = true;
            MyWindowManager myWindowManager = this.mMyManager;
            showBottomView(myWindowManager, "从第 " + this.startInt + " 个好友开始点赞/评论");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                            LikeCommentFriendsUtils.this.executePartMain();
                        } else {
                            LikeCommentFriendsUtils.this.executeMain();
                        }
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeClickCircle() {
        this.isHasSpread = false;
        System.out.println("WS_BABY_executeClickCircle.0");
        new PerformClickUtils2().checkNodeId(this.autoService, album_head_img, executeSpeed + (executeSpeedSetting / 2), 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                if (PerformClickUtils.findNodeIsExistByText(LikeCommentFriendsUtils.this.autoService, "拍照分享")) {
                    PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                LikeCommentFriendsUtils.this.executePartMain();
                            } else {
                                LikeCommentFriendsUtils.this.executeMain();
                            }
                        }
                    });
                    return;
                }
                System.out.println("WS_BABY_executeClickCircle.1");
                new PerformClickUtils2().checkNodeAllIds(LikeCommentFriendsUtils.this.autoService, BaseServiceUtils.list_circle_layout_id, BaseServiceUtils.album_head_img, 50, 50, 30, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        System.out.println("WS_BABY_executeClickCircle.2");
                        LikeCommentFriendsUtils.this.joinAlbumListPage();
                    }

                    public void nuFind() {
                        System.out.println("WS_BABY_executeClickCircle.3");
                        PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                    LikeCommentFriendsUtils.this.executePartMain();
                                } else {
                                    LikeCommentFriendsUtils.this.executeMain();
                                }
                            }
                        });
                    }
                });
            }

            public void nuFind() {
            }
        });
    }

    public void executeComment() {
        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, expand_more_id);
        if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
            LogUtils.log("WS_BABY_executeComment.6");
            PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                public void find() {
                    if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                        LikeCommentFriendsUtils.this.executePartMain();
                    } else {
                        LikeCommentFriendsUtils.this.executeMain();
                    }
                }
            });
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
            AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null && MyApplication.enforceable) {
                LogUtils.log("WS_BABY_executeComment.1");
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(desc_comment_text_id);
                if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || !MyApplication.enforceable) {
                    LogUtils.log("WS_BABY_executeComment.5");
                    return;
                }
                LogUtils.log("WS_BABY_executeComment.2");
                if ("评论".equals(findAccessibilityNodeInfosByViewId.get(0).getText().toString())) {
                    LogUtils.log("WS_BABY_executeComment.3");
                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                    sleep(100);
                    PerformClickUtils.inputText(this.autoService, this.sayContent);
                    new PerformClickUtils2().checkString(this.autoService, "发送", CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 50, 20, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(LikeCommentFriendsUtils.this.autoService, "发送");
                            new PerformClickUtils2().checkString(LikeCommentFriendsUtils.this.autoService, LikeCommentFriendsUtils.this.sayContent, 300, 100, 7, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LikeCommentFriendsUtils.access$1108(LikeCommentFriendsUtils.this);
                                    LikeCommentFriendsUtils.this.statisticsData();
                                    PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                                LikeCommentFriendsUtils.this.executePartMain();
                                            } else {
                                                LikeCommentFriendsUtils.this.executeMain();
                                            }
                                        }
                                    });
                                }

                                public void nuFind() {
                                    LikeCommentFriendsUtils.access$1108(LikeCommentFriendsUtils.this);
                                    LikeCommentFriendsUtils.this.statisticsData();
                                    PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                                LikeCommentFriendsUtils.this.executePartMain();
                                            } else {
                                                LikeCommentFriendsUtils.this.executeMain();
                                            }
                                        }
                                    });
                                }
                            });
                        }

                        public void nuFind() {
                            LikeCommentFriendsUtils.access$1108(LikeCommentFriendsUtils.this);
                            LikeCommentFriendsUtils.this.statisticsData();
                            PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                        LikeCommentFriendsUtils.this.executePartMain();
                                    } else {
                                        LikeCommentFriendsUtils.this.executeMain();
                                    }
                                }
                            });
                        }
                    });
                    return;
                }
                LogUtils.log("WS_BABY_executeComment.4");
            }
        }
    }

    public void executeCommentMain() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, expand_more_id, this.likeCommentType == 0 ? CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION : this.excFriendsNum + (executeSpeedSetting / 2), 100, 20, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (MyApplication.enforceable) {
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LikeCommentFriendsUtils.this.autoService, BaseServiceUtils.desc_comment_list_text);
                        if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                            LikeCommentFriendsUtils.this.executeComment();
                            return;
                        }
                        ArrayList arrayList = new ArrayList();
                        int i2 = 0;
                        while (true) {
                            int i3 = i2;
                            if (i3 >= findViewIdList.size()) {
                                break;
                            }
                            CharSequence text = findViewIdList.get(i3).getText();
                            if (text != null) {
                                arrayList.add(text + "");
                            }
                            i2 = i3 + 1;
                        }
                        if (arrayList.contains(LikeCommentFriendsUtils.this.sayContent)) {
                            LikeCommentFriendsUtils.access$1108(LikeCommentFriendsUtils.this);
                            LikeCommentFriendsUtils.this.statisticsData();
                            PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                        LikeCommentFriendsUtils.this.executePartMain();
                                    } else {
                                        LikeCommentFriendsUtils.this.executeMain();
                                    }
                                }
                            });
                            return;
                        }
                        LikeCommentFriendsUtils.this.executeComment();
                    }
                }

                public void nuFind() {
                    PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                LikeCommentFriendsUtils.this.executePartMain();
                            } else {
                                LikeCommentFriendsUtils.this.executeMain();
                            }
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeFriends() {
        if (MyApplication.enforceable) {
            LogUtils.log("WS_BABY.LauncherUI.executeFriends_0");
            if (this.nameNodes2 == null) {
                this.nameNodes2 = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_item_name_id);
            }
            LogUtils.log("WS_BABY.LauncherUI.executeFriends_5555_" + this.startIndex);
            if (this.nameNodes2 == null || this.nameNodes2.size() <= 0 || !MyApplication.enforceable) {
                LogUtils.log("WS_BABY.LauncherUI.executeFriends_names_null");
                return;
            }
            LogUtils.log("WS_BABY.LauncherUI.executeFriends_6666_" + this.nameNodes2.size());
            if (this.startIndex < this.nameNodes2.size() && MyApplication.enforceable) {
                final AccessibilityNodeInfo accessibilityNodeInfo = this.nameNodes2.get(this.startIndex);
                if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                    String str = accessibilityNodeInfo.getText() + "";
                    LogUtils.log("WS_BABY.LauncherUI.executeFriends_name_" + str);
                    this.recordNowName = str;
                }
                this.scrollCount++;
                if (this.beforeLists != null && this.beforeLists.size() > 50) {
                    for (int i = 0; i < 5; i++) {
                        this.beforeLists.remove(0);
                    }
                }
                if (this.jumpPersons != null && this.jumpPersons.size() > 0 && this.jumpPersons.contains(this.recordNowName)) {
                    this.excFriendsNum++;
                    this.startIndex++;
                    updateBottomText(this.mMyManager, "已跳过【 " + this.recordNowName + " 】");
                    sleep(10);
                    executeFriends();
                } else if (this.isScrollBottom && this.beforeLists.contains(this.recordNowName)) {
                    LogUtils.log("WS_BABY_LauncherUI.beforeLists.contains." + this.recordNowName);
                    this.startIndex = this.startIndex + 1;
                    executeFriends();
                } else if ("微信团队".equals(this.recordNowName) || "文件传输助手".equals(this.recordNowName)) {
                    this.startIndex++;
                    this.excFriendsNum++;
                    LogUtils.log("WS_BABY_LauncherUI.微信团队.");
                    executeFriends();
                } else {
                    if (this.recordNowName != null && !"".equals(this.recordNowName)) {
                        this.beforeLists.add(this.recordNowName);
                    }
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_item_layout_id);
                    if (findViewIdList == null || findViewIdList.size() <= 0 || this.startIndex >= findViewIdList.size()) {
                        this.startIndex++;
                        executeFriends();
                        return;
                    }
                    final AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList.get(this.startIndex);
                    this.startIndex++;
                    if (this.spaceTime <= 0 || this.excFriendsNum <= 0) {
                        PerformClickUtils.performClick(accessibilityNodeInfo2);
                        PerformClickUtils.performClick(accessibilityNodeInfo);
                        initContactInfoUI();
                        return;
                    }
                    new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                        public void end() {
                            PerformClickUtils.performClick(accessibilityNodeInfo2);
                            LogUtils.log("WS_BABY.FTSMainUI.into3");
                            PerformClickUtils.performClick(accessibilityNodeInfo);
                            LikeCommentFriendsUtils.this.initContactInfoUI();
                        }

                        public void surplus(int i) {
                            if (LikeCommentFriendsUtils.this.likeCommentType == 0) {
                                MyWindowManager myWindowManager = LikeCommentFriendsUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "已执行 " + LikeCommentFriendsUtils.this.excFriendsNum + " 个好友，点赞 " + LikeCommentFriendsUtils.this.likeCount + " 个，评论 " + LikeCommentFriendsUtils.this.commentCount + " 个。\n正在延时等待，倒计时 " + i + " 秒");
                            } else if (LikeCommentFriendsUtils.this.likeCommentType == 1) {
                                MyWindowManager myWindowManager2 = LikeCommentFriendsUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager2, "已执行 " + LikeCommentFriendsUtils.this.excFriendsNum + " 个好友 ，点赞 " + LikeCommentFriendsUtils.this.likeCount + " 个。\n正在延时等待，倒计时 " + i + " 秒");
                            } else if (LikeCommentFriendsUtils.this.likeCommentType == 2) {
                                MyWindowManager myWindowManager3 = LikeCommentFriendsUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager3, "已执行 " + LikeCommentFriendsUtils.this.excFriendsNum + " 个好友 ，评论 " + LikeCommentFriendsUtils.this.commentCount + " 个。\n正在延时等待，倒计时 " + i + " 秒");
                            }
                        }
                    });
                }
            } else if (this.startIndex >= this.nameNodes2.size() && MyApplication.enforceable) {
                LogUtils.log("WS_BABY.LauncherUI.executeFriends_scroll");
                this.nameNodes2 = null;
                PerformClickUtils.scroll(this.autoService, grout_friend_list_id);
                new PerformClickUtils2().check(this.autoService, list_item_name_id, 350, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (LikeCommentFriendsUtils.this.isScrollBottom) {
                            BaseServiceUtils.removeEndView(LikeCommentFriendsUtils.this.mMyManager);
                            return;
                        }
                        boolean unused = LikeCommentFriendsUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(LikeCommentFriendsUtils.this.autoService, "位联系人");
                        int unused2 = LikeCommentFriendsUtils.this.startIndex = 1;
                        LikeCommentFriendsUtils.this.executeFriends();
                    }

                    public void nuFind() {
                    }
                });
            }
        }
    }

    public void executeMain() {
        try {
            if (this.isFirst && MyApplication.enforceable) {
                this.isFirst = false;
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                sleep(400);
            }
            new PerformClickUtils2().checkNodeAllIds(this.autoService, grout_friend_list_id, list_item_name_id, executeSpeed + executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (LikeCommentFriendsUtils.this.startInt <= 1 || !LikeCommentFriendsUtils.this.isJumpedStart || !MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_LauncherUI_从头开始");
                        LikeCommentFriendsUtils.this.executeFriends();
                        return;
                    }
                    LogUtils.log("WS_BABY_LauncherUI_从" + LikeCommentFriendsUtils.this.startInt + "开始");
                    LikeCommentFriendsUtils.this.jumpStartPosition();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executePartMain() {
        try {
            if (MyApplication.enforceable) {
                if (this.isFirst && MyApplication.enforceable) {
                    this.isFirst = false;
                    PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                    sleep(200);
                }
                new PerformClickUtils2().checkNodeAllIds(this.autoService, contact_nav_search_img_id, contact_nav_search_viewgroup_id, executeSpeed, 50, 200, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LikeCommentFriendsUtils.this.autoService, BaseServiceUtils.contact_nav_search_viewgroup_id);
                        if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                            if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                                PerformClickUtils.findTextAndClickFirst(LikeCommentFriendsUtils.this.autoService, "搜索");
                            } else {
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = findViewIdList.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_img_id);
                                if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && findAccessibilityNodeInfosByViewId.get(0) != null && MyApplication.enforceable) {
                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                }
                            }
                            LikeCommentFriendsUtils.this.initFTSMainUI();
                        }
                    }

                    public void nuFind() {
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executePraiseMain() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, expand_more_id, executeSpeed + executeSpeedSetting, 100, 20, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LikeCommentFriendsUtils.this.autoService, BaseServiceUtils.expand_more_id);
                    if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                        if (LikeCommentFriendsUtils.this.likeCommentType == 0) {
                            LikeCommentFriendsUtils.this.executeCommentMain();
                        } else {
                            PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                        LikeCommentFriendsUtils.this.executePartMain();
                                    } else {
                                        LikeCommentFriendsUtils.this.executeMain();
                                    }
                                }
                            });
                        }
                    } else if (MyApplication.enforceable) {
                        AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(0);
                        if (accessibilityNodeInfo != null && MyApplication.enforceable) {
                            accessibilityNodeInfo.performAction(16);
                            LikeCommentFriendsUtils.this.sleep(300);
                            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) LikeCommentFriendsUtils.this.autoService, BaseServiceUtils.praise_text_id);
                            if (findViewIdList2 == null || findViewIdList2.size() <= 0 || !MyApplication.enforceable) {
                                if (LikeCommentFriendsUtils.this.likeCommentType == 0) {
                                    LikeCommentFriendsUtils.this.executeCommentMain();
                                } else {
                                    PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                                LikeCommentFriendsUtils.this.executePartMain();
                                            } else {
                                                LikeCommentFriendsUtils.this.executeMain();
                                            }
                                        }
                                    });
                                }
                            } else if ("赞".equals(findViewIdList2.get(0).getText().toString())) {
                                PerformClickUtils.performClick(findViewIdList2.get(0));
                                LikeCommentFriendsUtils.access$1008(LikeCommentFriendsUtils.this);
                                LikeCommentFriendsUtils.this.statisticsData();
                                if (LikeCommentFriendsUtils.this.likeCommentType == 0) {
                                    LikeCommentFriendsUtils.this.sleep(300);
                                    LikeCommentFriendsUtils.this.executeCommentMain();
                                    return;
                                }
                                PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                            LikeCommentFriendsUtils.this.executePartMain();
                                        } else {
                                            LikeCommentFriendsUtils.this.executeMain();
                                        }
                                    }
                                });
                            } else {
                                LikeCommentFriendsUtils.access$1008(LikeCommentFriendsUtils.this);
                                boolean unused = LikeCommentFriendsUtils.this.isHasSpread = true;
                                LikeCommentFriendsUtils.this.statisticsData();
                                if (LikeCommentFriendsUtils.this.likeCommentType == 0) {
                                    LikeCommentFriendsUtils.this.executeCommentMain();
                                    return;
                                }
                                boolean unused2 = LikeCommentFriendsUtils.this.isHasSpread = false;
                                PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                            LikeCommentFriendsUtils.this.executePartMain();
                                        } else {
                                            LikeCommentFriendsUtils.this.executeMain();
                                        }
                                    }
                                });
                            }
                        } else if (LikeCommentFriendsUtils.this.likeCommentType == 0) {
                            LikeCommentFriendsUtils.this.executeCommentMain();
                        } else {
                            PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                        LikeCommentFriendsUtils.this.executePartMain();
                                    } else {
                                        LikeCommentFriendsUtils.this.executeMain();
                                    }
                                }
                            });
                        }
                    }
                }

                public void nuFind() {
                    PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                LikeCommentFriendsUtils.this.executePartMain();
                            } else {
                                LikeCommentFriendsUtils.this.executeMain();
                            }
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initChattingUI() {
        try {
            new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeed + executeSpeedSetting, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findViewIdAndClick(LikeCommentFriendsUtils.this.autoService, BaseServiceUtils.right_more_id);
                    LikeCommentFriendsUtils.this.initSingleChatInfo();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_73");
            e.printStackTrace();
        }
    }

    public void initContactInfoUI() {
        this.isHasSpread = false;
        this.excFriendsNum++;
        statisticsData();
        new PerformClickUtils2().checkString(this.autoService, "发消息", executeSpeed + executeSpeedSetting, 50, 50, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                new PerformClickUtils2().checkString(LikeCommentFriendsUtils.this.autoService, "朋友圈", 10, 50, 40, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.findTextAndClick(LikeCommentFriendsUtils.this.autoService, "朋友圈");
                        PerformClickUtils.findTextAndClick(LikeCommentFriendsUtils.this.autoService, "朋友圈");
                        System.out.println("WS_BABY_initContactInfoUI.0");
                        LikeCommentFriendsUtils.this.executeClickCircle();
                    }

                    public void nuFind() {
                        System.out.println("WS_BABY_initContactInfoUI.1");
                        PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                    LikeCommentFriendsUtils.this.executePartMain();
                                } else {
                                    LikeCommentFriendsUtils.this.executeMain();
                                }
                            }
                        });
                    }
                });
            }

            public void nuFind() {
                System.out.println("WS_BABY_initContactInfoUI.4");
                PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                            LikeCommentFriendsUtils.this.executePartMain();
                        } else {
                            LikeCommentFriendsUtils.this.executeMain();
                        }
                    }
                });
            }
        });
    }

    public void initData(OperationParameterModel operationParameterModel2) {
        this.excFriendsNum = 0;
        this.likeCount = 0;
        this.commentCount = 0;
        this.isFirst = true;
        this.recordNowName = "";
        this.isHasSpread = false;
        this.beforeLists = new ArrayList();
        this.startIndex = 0;
        this.scrollCount = 0;
        this.isJumpedStart = true;
        this.operationParameterModel = operationParameterModel2;
        this.likeCommentType = operationParameterModel2.getLikeCommentType();
        this.tagFriendType = operationParameterModel2.getTagFriendType();
        this.spaceTime = operationParameterModel2.getSpaceTime();
        this.jumpPersons = operationParameterModel2.getTagListPeoples();
        this.startInt = operationParameterModel2.getStartIndex();
        this.sayContent = operationParameterModel2.getSayContent();
        this.isScrollBottom = false;
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        if (this.tagFriendType == 1) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("WS_BABY.st.0");
                sb.append(this.startInt > 1);
                LogUtils.log(sb.toString());
                if (this.startInt > 1) {
                    LogUtils.log("WS_BABY.st.1");
                    for (int i = 0; i < this.startInt - 1; i++) {
                        LogUtils.log("WS_BABY.st.st" + i);
                        if (this.jumpPersons.size() > 0) {
                            this.jumpPersons.remove(this.jumpPersons.iterator().next());
                        }
                    }
                    return;
                }
                LogUtils.log("WS_BABY.st.2");
            } catch (Exception e) {
                LogUtils.log("WS_BABY.st.3");
            }
        }
    }

    public void initFTSMainUI() {
        try {
            if (this.spaceTime <= 0 || this.excFriendsNum <= 0) {
                LogUtils.log("WS_BABY.FTSMainUI.into2");
                searchNickName();
                return;
            }
            new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                public void end() {
                    LogUtils.log("WS_BABY.FTSMainUI.into1");
                    LikeCommentFriendsUtils.this.searchNickName();
                }

                public void surplus(int i) {
                    MyWindowManager myWindowManager = LikeCommentFriendsUtils.this.mMyManager;
                    BaseServiceUtils.updateBottomText(myWindowManager, "已执行 " + LikeCommentFriendsUtils.this.excFriendsNum + " 个好友\n正在延时等待，倒计时 " + i + " 秒");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSingleChatInfo() {
        try {
            if (MyApplication.enforceable) {
                new PerformClickUtils2().check(this.autoService, single_contact_nick_name_id, executeSpeed + executeSpeedSetting, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.findViewIdAndClick(LikeCommentFriendsUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                        LikeCommentFriendsUtils.this.initContactInfoUI();
                    }

                    public void nuFind() {
                    }
                });
            }
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_74");
            e.printStackTrace();
        }
    }

    public void initSnsCommentDetailUI() {
        LogUtils.log("WS_BABY.initSnsCommentDetailUI.likeCommentType = " + this.likeCommentType);
        if (this.likeCommentType != 2) {
            executePraiseMain();
        } else {
            executeCommentMain();
        }
    }

    public void initSnsGalleryUI() {
        new PerformClickUtils2().checkNodeId(this.autoService, desc_right_down_id, executeSpeed + executeSpeedSetting, 50, 44, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY.SnsGalleryUI");
                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LikeCommentFriendsUtils.this.autoService, BaseServiceUtils.desc_right_down_id);
                if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                    PerformClickUtils.performClick(findViewIdList.get(0));
                    LikeCommentFriendsUtils.this.initSnsCommentDetailUI();
                }
            }

            public void nuFind() {
                PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                            LikeCommentFriendsUtils.this.executePartMain();
                        } else {
                            LikeCommentFriendsUtils.this.executeMain();
                        }
                    }
                });
            }
        });
    }

    public void joinAlbumListPage() {
        try {
            if (MyApplication.enforceable) {
                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_circle_layout_id);
                System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.1");
                if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                    PerformClickUtils.executedBackHome(this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                LikeCommentFriendsUtils.this.executePartMain();
                            } else {
                                LikeCommentFriendsUtils.this.executeMain();
                            }
                        }
                    });
                    return;
                }
                AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(0);
                if (accessibilityNodeInfo != null) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(list_layout_2_id);
                    System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.5");
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(list_layout_only_text_id);
                    if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || !MyApplication.enforceable) {
                        System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.13");
                        System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.14");
                        if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0 || !MyApplication.enforceable) {
                            System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.16");
                            PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                        LikeCommentFriendsUtils.this.executePartMain();
                                    } else {
                                        LikeCommentFriendsUtils.this.executeMain();
                                    }
                                }
                            });
                            return;
                        }
                        System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.15");
                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                        initSnsCommentDetailUI();
                        return;
                    }
                    System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.6");
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = findAccessibilityNodeInfosByViewId.get(0).findAccessibilityNodeInfosByViewId(list_circle_img_video_id);
                    System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.8");
                    if (findAccessibilityNodeInfosByViewId3 == null || findAccessibilityNodeInfosByViewId3.size() <= 0) {
                        System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.10");
                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                    } else {
                        System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.9");
                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId3.get(0));
                    }
                    System.out.println("WS_BABY_joinAlbumListPage.recordExecuteIndex.11");
                    initSnsGalleryUI();
                    return;
                }
                PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                            LikeCommentFriendsUtils.this.executePartMain();
                        } else {
                            LikeCommentFriendsUtils.this.executeMain();
                        }
                    }
                });
            }
        } catch (Exception e) {
        }
    }

    public void jumpStartPosition() {
        LogUtils.log("WS_BABY.LauncherUI.jumpFriends");
        if (this.nameNodes == null) {
            this.nameNodes = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_item_name_id);
        }
        if (this.nameNodes != null && this.nameNodes.size() > 0 && MyApplication.enforceable) {
            if (this.startIndex < this.nameNodes.size() && MyApplication.enforceable) {
                LogUtils.log("WS_BABY.LauncherUI.jumpFriends_c" + this.startIndex);
                AccessibilityNodeInfo accessibilityNodeInfo = this.nameNodes.get(this.startIndex);
                if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                    this.recordNowName = accessibilityNodeInfo.getText() + "";
                }
                LogUtils.log("WS_BABY.LauncherUI.jumpFriends_d" + this.recordNowName);
                if (this.scrollCount >= this.startInt - 1) {
                    executeFriends();
                    return;
                }
                this.startIndex++;
                if (!this.isScrollBottom || !this.beforeLists.contains(this.recordNowName)) {
                    this.scrollCount++;
                    updateBottomText(this.mMyManager, "从第 " + this.startInt + " 个开始，已跳过 " + this.scrollCount + " 个好友");
                    LogUtils.log("WS_BABY.LauncherUI.jumpFriends_add");
                    if (this.recordNowName != null && !"".equals(this.recordNowName)) {
                        this.beforeLists.add(this.recordNowName);
                    }
                    jumpStartPosition();
                    return;
                }
                jumpStartPosition();
            } else if (this.startIndex >= this.nameNodes.size() && MyApplication.enforceable) {
                this.nameNodes = null;
                LogUtils.log("WS_BABY.LauncherUI.executeFriends_scroll");
                PerformClickUtils.scroll(this.autoService, grout_friend_list_id);
                new PerformClickUtils2().check(this.autoService, list_item_name_id, 100, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (LikeCommentFriendsUtils.this.isScrollBottom) {
                            BaseServiceUtils.removeEndView(LikeCommentFriendsUtils.this.mMyManager);
                        }
                        boolean unused = LikeCommentFriendsUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(LikeCommentFriendsUtils.this.autoService, "位联系人");
                        int unused2 = LikeCommentFriendsUtils.this.startIndex = 1;
                        LikeCommentFriendsUtils.this.jumpStartPosition();
                    }

                    public void nuFind() {
                    }
                });
            }
        }
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        setMiddleText(myWindowManager, "执行结果", "从第" + this.startInt + "个好友开始,本次共执行了 " + this.excFriendsNum + " 个好友 ");
    }

    public void searchNickName() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, search_id, (executeSpeedSetting / 10) + 200, 50, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (LikeCommentFriendsUtils.this.jumpPersons == null || LikeCommentFriendsUtils.this.jumpPersons.size() <= 0 || !MyApplication.enforceable) {
                        BaseServiceUtils.removeEndView(LikeCommentFriendsUtils.this.mMyManager);
                    } else {
                        LogUtils.log("WS_BABY.search_into.1");
                        String str = (String) LikeCommentFriendsUtils.this.jumpPersons.iterator().next();
                        String unused = LikeCommentFriendsUtils.this.nowSearchName = str;
                        if (str != null && !str.equals("") && str.length() > 20) {
                            str = str.substring(0, 20);
                        }
                        PerformClickUtils.findViewIdAndClick(LikeCommentFriendsUtils.this.autoService, BaseServiceUtils.search_id);
                        LikeCommentFriendsUtils.this.sleep(350);
                        PerformClickUtils.inputText(LikeCommentFriendsUtils.this.autoService, str);
                        LikeCommentFriendsUtils.this.jumpPersons.remove(LikeCommentFriendsUtils.this.jumpPersons.iterator().next());
                    }
                    LogUtils.log("WS_BABY.search_into.2");
                    new PerformClickUtils2().check(LikeCommentFriendsUtils.this.autoService, BaseServiceUtils.list_select_name_id, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 25, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(LikeCommentFriendsUtils.this.autoService, "联系人");
                            boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(LikeCommentFriendsUtils.this.autoService, "最常使用");
                            if ((findNodeIsExistByText || findNodeIsExistByText2) && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY.search_into");
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LikeCommentFriendsUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                                if (findViewIdList != null && findViewIdList.size() > 0) {
                                    PerformClickUtils.performClick(findViewIdList.get(0));
                                    if (LikeCommentFriendsUtils.this.operationParameterModel.getSendCardType() == 1 && LikeCommentFriendsUtils.this.operationParameterModel.getIntimateMode() == 0) {
                                        BaseServiceUtils.remarkName = LikeCommentFriendsUtils.this.nowSearchName;
                                    }
                                    LikeCommentFriendsUtils.this.initChattingUI();
                                } else if (MyApplication.enforceable) {
                                    PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                                LikeCommentFriendsUtils.this.executePartMain();
                                            } else {
                                                LikeCommentFriendsUtils.this.executeMain();
                                            }
                                        }
                                    });
                                }
                            } else if (MyApplication.enforceable) {
                                PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                            LikeCommentFriendsUtils.this.executePartMain();
                                        } else {
                                            LikeCommentFriendsUtils.this.executeMain();
                                        }
                                    }
                                });
                            }
                        }

                        public void nuFind() {
                            if (MyApplication.enforceable) {
                                PerformClickUtils.executedBackHome(LikeCommentFriendsUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                                            LikeCommentFriendsUtils.this.executePartMain();
                                        } else {
                                            LikeCommentFriendsUtils.this.executeMain();
                                        }
                                    }
                                });
                            }
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

    public void statisticsData() {
        MyApplication.getHandler().post(new Runnable() {
            public void run() {
                if (LikeCommentFriendsUtils.this.tagFriendType == 0) {
                    SPUtils.put(MyApplication.getConText(), "like_comment_num_all", Integer.valueOf(LikeCommentFriendsUtils.this.excFriendsNum + LikeCommentFriendsUtils.this.startInt));
                } else if (LikeCommentFriendsUtils.this.tagFriendType == 1) {
                    SPUtils.put(MyApplication.getConText(), "like_comment_num_part", Integer.valueOf(LikeCommentFriendsUtils.this.excFriendsNum + LikeCommentFriendsUtils.this.startInt));
                } else if (LikeCommentFriendsUtils.this.tagFriendType == 2) {
                    SPUtils.put(MyApplication.getConText(), "like_comment_num_shield", Integer.valueOf(LikeCommentFriendsUtils.this.excFriendsNum + LikeCommentFriendsUtils.this.startInt));
                }
            }
        });
        if (this.likeCommentType == 0) {
            MyWindowManager myWindowManager = this.mMyManager;
            showBottomView(myWindowManager, "从第 " + this.startInt + " 个好友开始\n已执行 " + this.excFriendsNum + " 个好友 , 点赞 " + this.likeCount + "个，评论 " + this.commentCount + "个。");
        } else if (this.likeCommentType == 1) {
            MyWindowManager myWindowManager2 = this.mMyManager;
            showBottomView(myWindowManager2, "从第 " + this.startInt + " 个好友开始\n已执行 " + this.excFriendsNum + " 个好友 , 点赞 " + this.likeCount + "个。");
        } else {
            MyWindowManager myWindowManager3 = this.mMyManager;
            showBottomView(myWindowManager3, "从第 " + this.startInt + " 个好友开始\n已执行 " + this.excFriendsNum + " 个好友 ，评论 " + this.commentCount + "个。");
        }
    }
}
