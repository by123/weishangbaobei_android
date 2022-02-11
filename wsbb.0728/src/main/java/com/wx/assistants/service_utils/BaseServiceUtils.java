package com.wx.assistants.service_utils;

import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.Enity.CopyFriendBean;
import com.wx.assistants.Enity.RecoverFriendBean;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.service.AutoService;
import com.wx.assistants.service.FloatMiddleView;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.service_utils.node.Node673;
import com.wx.assistants.service_utils.node.Node700;
import com.wx.assistants.service_utils.node.Node7010;
import com.wx.assistants.service_utils.node.Node7011;
import com.wx.assistants.service_utils.node.Node7012;
import com.wx.assistants.service_utils.node.Node7013;
import com.wx.assistants.service_utils.node.Node7014;
import com.wx.assistants.service_utils.node.Node703;
import com.wx.assistants.service_utils.node.Node704;
import com.wx.assistants.service_utils.node.Node705;
import com.wx.assistants.service_utils.node.Node706;
import com.wx.assistants.service_utils.node.Node707;
import com.wx.assistants.service_utils.node.Node708;
import com.wx.assistants.service_utils.node.Node709;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.PackageUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.SQLiteUtils;
import java.util.List;

public class BaseServiceUtils {
    public static String album_head_img = "com.tencent.mm:id/rb";
    public static String album_id = "com.tencent.mm:id/ym";
    public static String back_contact_id = "com.tencent.mm:id/lb";
    public static String back_id = "com.tencent.mm:id/jv";
    public static String bottom_line = "com.tencent.mm:id/ae7";
    public static String chatting_progress_id = "com.tencent.mm:id/asr";
    public static String circle_img_crop_id = "com.tencent.mm:id/f8d";
    public static String circle_list_layout_hide_text_id = "com.tencent.mm:id/aww";
    public static String circle_list_layout_id = "com.tencent.mm:id/efo";
    public static String circle_list_layout_img_id = "com.tencent.mm:id/efe";
    public static String circle_list_layout_nickname_id = "com.tencent.mm:id/b5o";
    public static String circle_list_layout_text_id = "com.tencent.mm:id/efs";
    public static String circle_list_layout_video_id = "com.tencent.mm:id/ed1";
    public static String collection_list_id = "com.tencent.mm:id/bp3";
    public static String comment_text_id = "com.tencent.mm:id/eah";
    public static String complete_id = "com.tencent.mm:id/jq";
    public static String contact_bottom_layout_id = "com.tencent.mm:id/alp";
    public static String contact_layout_id = "com.tencent.mm:id/a_";
    public static String contact_list_img_id = "com.tencent.mm:id/ao4";
    public static String contact_nav_search_img_id = "com.tencent.mm:id/ij";
    public static String contact_nav_search_viewgroup_id = "com.tencent.mm:id/jf";
    public static String contact_title_id = "com.tencent.mm:id/k3";
    public static String cover_the_cover_id = "com.tencent.mm:id/bji";
    public static String default_list_id = "android:id/list";
    public static String desc_comment_list_text = "com.tencent.mm:id/eyr";
    public static String desc_comment_text_id = "com.tencent.mm:id/exq";
    public static String desc_delete_id = "com.tencent.mm:id/eb4";
    public static String desc_img_time_id = "android:id/text1";
    public static String desc_right_down_id = "com.tencent.mm:id/ec1";
    public static String desc_text_id = "com.tencent.mm:id/ebt";
    public static String desc_text_time_id = "com.tencent.mm:id/eay";
    public static String desc_video_relativeLayout_id = "com.tencent.mm:id/li";
    public static String dialog_ok_id = "com.tencent.mm:id/ayb";
    public static String down_img_id = "com.tencent.mm:id/ce9";
    public static String edit_text_id = "com.tencent.mm:id/cf9";
    public static int executeSpeed = 300;
    public static int executeSpeedSetting = 0;
    public static String expand_more_id = "com.tencent.mm:id/eb6";
    public static String filter_tags_list = "com.tencent.mm:id/f0z";
    public static String filter_tags_name = "com.tencent.mm:id/f13";
    public static String friends_circle_head_img_id = "";
    public static String group_list_item_name_id = "com.tencent.mm:id/my";
    public static String group_public_card_name = "com.tencent.mm:id/alw";
    public static String grout_friend_list_id = "com.tencent.mm:id/m_";
    public static String hide_text_page_text_id = "com.tencent.mm:id/ehv";
    public static String home_msg_list_desc_id = "com.tencent.mm:id/b4q";
    public static String home_msg_list_id = "com.tencent.mm:id/cwp";
    public static String home_msg_list_item_id = "com.tencent.mm:id/b4m";
    public static String home_msg_list_name_id = "com.tencent.mm:id/b4o";
    public static String home_tab_layout_id = "com.tencent.mm:id/d7_";
    public static String img_first_check_layout_id = "com.tencent.mm:id/bmv";
    public static String input_edit_text_id = "com.tencent.mm:id/alm";
    public static String input_remark = "com.tencent.mm:id/dx_";
    public static String input_remark_label = "com.tencent.mm:id/e4f";
    public static String input_remark_label_view_group = "com.tencent.mm:id/b51";
    public static String input_say_content = "com.tencent.mm:id/dx6";
    public static String input_send_edit_text_id = "com.tencent.mm:id/cox";
    public static String left_read_remark_id = "com.tencent.mm:id/ar0";
    public static String line_day_id = "com.tencent.mm:id/eo2";
    public static String line_month_id = "com.tencent.mm:id/eo3";
    public static String line_year_big_id = "com.tencent.mm:id/eo4";
    public static String line_year_small_id = "com.tencent.mm:id/el8";
    public static String list_album_video_img = "com.tencent.mm:id/f1q";
    public static String list_circle_id = "com.tencent.mm:id/ebi";
    public static String list_circle_img_video_id = "com.tencent.mm:id/edi";
    public static String list_circle_layout_id = "com.tencent.mm:id/lc";
    public static String list_head_img_id = "com.tencent.mm:id/dwu";
    public static String list_id = "com.tencent.mm:id/brl";
    public static String list_item_id = "com.tencent.mm:id/d8a";
    public static String list_item_layout_id = "com.tencent.mm:id/nf";
    public static String list_item_name_id = "com.tencent.mm:id/n8";
    public static String list_item_nickname_id = "com.tencent.mm:id/bht";
    public static String list_item_ranking_id = "com.tencent.mm:id/bjo";
    public static String list_item_step_id = "com.tencent.mm:id/bgg";
    public static String list_item_zan_img_id = "com.tencent.mm:id/bg8";
    public static String list_layout_2_id = "com.tencent.mm:id/edo";
    public static String list_layout_accept_id = "com.tencent.mm:id/brs";
    public static String list_layout_add_id = "com.tencent.mm:id/buw";
    public static String list_layout_child_id = "com.tencent.mm:id/ea2";
    public static String list_layout_child_url = "com.tencent.mm:id/ea2";
    public static String list_layout_id = "com.tencent.mm:id/brl";
    public static String list_layout_name = "com.tencent.mm:id/bro";
    public static String list_layout_name1 = "com.tencent.mm:id/bvu";
    public static String list_layout_name2 = "com.tencent.mm:id/bvv";
    public static String list_layout_only_text_id = "com.tencent.mm:id/km";
    public static String list_select_checkbox = "com.tencent.mm:id/zk";
    public static String list_select_group_id = "com.tencent.mm:id/i2";
    public static String list_select_group_name_id = "com.tencent.mm:id/cad";
    public static String list_select_id = "com.tencent.mm:id/i2";
    public static String list_select_letter_slip_id = "com.tencent.mm:id/jb";
    public static String list_select_name_id = "com.tencent.mm:id/pp";
    public static String list_select_search_id = "com.tencent.mm:id/b79";
    public static String list_text_id = "com.tencent.mm:id/nl";
    public static String list_video_img_id = "com.tencent.mm:id/ar1";
    public static String long_click_id = "com.tencent.mm:id/ur";
    private static Handler mainHandler = null;
    public static String more_recycle_view_id = "com.tencent.mm:id/d3p";
    public static String msg_layout = "com.tencent.mm:id/b4i";
    public static String nav_search_id = "com.tencent.mm:id/by";
    public static String nealy_list_id = "com.tencent.mm:id/d_j";
    public static String nealy_list_name_id = "com.tencent.mm:id/bex";
    public static String nearly_0 = "com.android.packageinstaller:id/do_not_ask_checkbox";
    public static String nearly_1 = "com.tencent.mm:id/clr";
    public static String new_friends_list_id = "com.tencent.mm:id/brv";
    public static String new_group_send_big_id = "com.tencent.mm:id/d1u";
    public static String new_group_send_id = "com.tencent.mm:id/d5e";
    public static String news_friend_id = "com.tencent.mm:id/brd";
    public static String praise_text_id = "com.tencent.mm:id/eae";
    public static String read_remark_1_id = "com.tencent.mm:id/mm";
    public static String read_remark_2_id = "com.tencent.mm:id/b4n";
    public static String remarkContactName = "com.tencent.mm:id/e0u";
    public static String remarkName = "";
    public static String remark_edit_id = "com.tencent.mm:id/b3v";
    public static String right_more_id = "com.tencent.mm:id/jr";
    public static String room_list_id = "com.tencent.mm:id/akn";
    public static String scan_img_select_layout = "com.tencent.mm:id/f2z";
    public static String scan_web_layout = "com.tencent.mm:id/bho";
    public static String search_card_wxh = "com.tencent.mm:id/km";
    public static String search_id = "com.tencent.mm:id/kh";
    public static String search_list_id = "com.tencent.mm:id/e0r";
    public static String search_list_item_name = "com.tencent.mm:id/ats";
    public static String search_page_back = "com.tencent.mm:id/kf";
    public static String send_add_id = "com.tencent.mm:id/alr";
    public static String send_second_img = "com.tencent.mm:id/nw";
    public static String single_contact_nick_name_id = "com.tencent.mm:id/dwz";
    public static String sport_list_id = "com.tencent.mm:id/bhh";
    public static String switch_id = "com.tencent.mm:id/kv";
    public static String tag_edit_friends_head_img_id = "com.tencent.mm:id/dwu";
    public static String tag_list_id = "com.tencent.mm:id/b2f";
    public static String tag_list_item_name_id = "com.tencent.mm:id/b2b";
    public static String tag_list_item_num_id = "com.tencent.mm:id/b2c";
    public static String tag_view_group_id = "com.tencent.mm:id/i7";
    public static String toast_edit_id = "com.tencent.mm:id/ayk";
    public static String user_label = "com.tencent.mm:id/e5e";
    public static String user_sex = "com.tencent.mm:id/b2y";
    public static String video_first_id = "com.tencent.mm:id/ak1";
    public static String video_layout = "com.tencent.mm:id/f0c";
    public static String video_progress_save_id = "com.tencent.mm:id/aex";
    public static String video_viewpager_imgview = "com.tencent.mm:id/add";
    public static String view_pager_id = "com.tencent.mm:id/acp";
    public static String voice_left_id = "com.tencent.mm:id/am_";
    public static String window_video_more = "com.tencent.mm:id/gig";
    public static int wxVersionCode = 0;
    public static String wxVersionName = "";
    public static String wx_nick_name = "com.tencent.mm:id/b4t";
    public static String wx_nick_name_id = "com.tencent.mm:id/a5b";
    public static String wx_nick_remark_name = "com.tencent.mm:id/b4n";
    public static String wx_num_id = "com.tencent.mm:id/d7y";
    public static String wx_number = "com.tencent.mm:id/b4v";
    public static String wx_sports_title_id = "android:id/text1";
    public AutoService autoService;
    public MyWindowManager mMyManager;

    interface IRunnable {
        void run();
    }

    public BaseServiceUtils() {
        initSetting();
        int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "seek_bar_speed", 0)).intValue();
        if (intValue >= executeSpeed) {
            executeSpeedSetting = intValue - executeSpeed;
        } else {
            executeSpeedSetting = 0;
        }
    }

    public static void formWindowManagerSetting() {
        initWXVersion();
        initVersion();
    }

    public static Handler getMainHandler() {
        if (mainHandler != null) {
            return mainHandler;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        mainHandler = handler;
        return handler;
    }

    public static void initVersion() {
        if ("6.7.3".equals(wxVersionName)) {
            Node673.node();
        } else if ("7.0.0".equals(wxVersionName)) {
            Node700.node();
        } else if ("7.0.3".equals(wxVersionName)) {
            Node703.node();
        } else if ("7.0.4".equals(wxVersionName)) {
            Node704.node();
        } else if ("7.0.5".equals(wxVersionName)) {
            Node705.node();
        } else if ("7.0.6".equals(wxVersionName)) {
            Node706.node();
        } else if ("7.0.7".equals(wxVersionName)) {
            Node707.node();
        } else if ("7.0.8".equals(wxVersionName)) {
            Node708.node();
        } else if ("7.0.9".equals(wxVersionName)) {
            Node709.node();
        } else if ("7.0.10".equals(wxVersionName)) {
            Node7010.node();
        } else if ("7.0.11".equals(wxVersionName)) {
            Node7011.node();
        } else if ("7.0.12".equals(wxVersionName)) {
            Node7012.node();
        } else if ("7.0.13".equals(wxVersionName)) {
            Node7013.node();
        } else if ("7.0.14".equals(wxVersionName)) {
            Node7014.node();
        } else {
            Node7014.node();
        }
    }

    public static void initWXVersion() {
        try {
            wxVersionName = PackageUtils.getWXVersionName(MyApplication.getConText());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        try {
            wxVersionCode = PackageUtils.getWXVersionCode(MyApplication.getConText());
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    public static void removeBottomOperationView(final MyWindowManager myWindowManager) {
        getMainHandler().post(new Runnable() {
            public void run() {
                myWindowManager.removeBottomOperationView();
            }
        });
    }

    public static void removeBottomView(final MyWindowManager myWindowManager) {
        getMainHandler().post(new Runnable() {
            public void run() {
                myWindowManager.removeBottomView();
            }
        });
    }

    public static void removeEndView(final MyWindowManager myWindowManager) {
        getMainHandler().post(new Runnable() {
            public void run() {
                System.out.println("WS_BABY_EDN.....了");
                myWindowManager.removeEndView();
            }
        });
    }

    public static void removeMiddleView(final MyWindowManager myWindowManager) {
        getMainHandler().post(new Runnable() {
            public void run() {
                myWindowManager.removeMiddleView();
            }
        });
    }

    public static void setMiddleText(final MyWindowManager myWindowManager, final String str, final String str2) {
        getMainHandler().post(new Runnable() {
            public void run() {
                myWindowManager.getMiddleView().setTitle(str).setContent(str2);
            }
        });
    }

    public static void showBottomView(final MyWindowManager myWindowManager, final String str) {
        getMainHandler().post(new Runnable() {
            public void run() {
                myWindowManager.showBottomView();
                BaseServiceUtils.updateBottomText(myWindowManager, str);
            }
        });
    }

    public static void showEndView(final MyWindowManager myWindowManager) {
        getMainHandler().post(new Runnable() {
            public void run() {
                myWindowManager.showEndView();
            }
        });
    }

    public static void updateBottomOperationLayout(MyWindowManager myWindowManager, String str, String str2, View.OnClickListener onClickListener, String str3, View.OnClickListener onClickListener2) {
        final MyWindowManager myWindowManager2 = myWindowManager;
        final String str4 = str;
        final String str5 = str2;
        final View.OnClickListener onClickListener3 = onClickListener;
        final String str6 = str3;
        final View.OnClickListener onClickListener4 = onClickListener2;
        getMainHandler().post(new Runnable() {
            public void run() {
                myWindowManager2.showBottomOperationView();
                myWindowManager2.getmBottomOperationView().setBottomText(str4);
                myWindowManager2.getmBottomOperationView().setBottomLayoutVisibility(0);
                myWindowManager2.getmBottomOperationView().setLeftBtnText(str5, onClickListener3);
                myWindowManager2.getmBottomOperationView().setRightBtnText(str6, onClickListener4);
            }
        });
    }

    public static void updateBottomText(final MyWindowManager myWindowManager, final String str) {
        getMainHandler().post(new Runnable() {
            public void run() {
                myWindowManager.getBottomView().setBottomText(str);
            }
        });
    }

    public static void updateMiddleText(final MyWindowManager myWindowManager, final String str, final String str2) {
        getMainHandler().post(new Runnable() {
            public void run() {
                myWindowManager.getMiddleView().setTitle(str).setContent(str2);
            }
        });
    }

    public static void updateMiddleText(final MyWindowManager myWindowManager, final String str, final String str2, final boolean z) {
        getMainHandler().post(new Runnable() {
            public void run() {
                myWindowManager.getMiddleView().setOnlyOk(z).setTitle(str).setContent(str2);
            }
        });
    }

    public static void updatePullGroupMiddleText(MyWindowManager myWindowManager, String str, String str2, String str3, FloatMiddleView.OnLeftBtnListener onLeftBtnListener, String str4, FloatMiddleView.OnRightBtnListener onRightBtnListener) {
        final MyWindowManager myWindowManager2 = myWindowManager;
        final String str5 = str;
        final String str6 = str2;
        final String str7 = str3;
        final FloatMiddleView.OnLeftBtnListener onLeftBtnListener2 = onLeftBtnListener;
        final String str8 = str4;
        final FloatMiddleView.OnRightBtnListener onRightBtnListener2 = onRightBtnListener;
        getMainHandler().post(new Runnable() {
            public void run() {
                myWindowManager2.getMiddleView().setTitle(str5).setContent(str6).setLeftText(str7, onLeftBtnListener2).setRightText(str8, onRightBtnListener2);
            }
        });
    }

    public void getRemarkFriendInfo(int i, String str) {
        try {
            remarkName = "";
            if (i == 1) {
                return;
            }
            if (i == 0) {
                String textByNodeId = PerformClickUtils.getTextByNodeId(this.autoService, wx_nick_name);
                if (textByNodeId == null || "".equals(textByNodeId)) {
                    remarkName = "";
                } else if (str == null || "".equals(str)) {
                    remarkName = "";
                } else {
                    remarkName = str + "，";
                }
            } else if (str == null || "".equals(str)) {
                remarkName = "";
            } else if (str.contains("@")) {
                String[] split = str.trim().split("@");
                remarkName = split[split.length - 1] + "，";
            } else {
                remarkName = "";
            }
        } catch (Exception e) {
            remarkName = "";
        }
    }

    public void initSetting() {
        this.autoService = MyApplication.instance.getAutoService();
        this.mMyManager = MyApplication.instance.getMyWindowManager();
        initWXVersion();
        getMainHandler();
        initVersion();
    }

    public void inputText(String str) {
        PerformClickUtils.inputText(this.autoService, str);
    }

    public boolean isWXGroup() {
        try {
            if (this.autoService == null) {
                return false;
            }
            AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(contact_bottom_layout_id);
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(contact_title_id);
                return findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && findAccessibilityNodeInfosByViewId2.get(0).getText().toString() != null && findAccessibilityNodeInfosByViewId2.get(0).getText().toString().contains("(");
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0062 A[Catch:{ Exception -> 0x00a9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a6  */
    public void saveCopyFriendInfo(String str) {
        String str2;
        try {
            String textByNodeId = PerformClickUtils.getTextByNodeId(this.autoService, wx_nick_name);
            String textByNodeId2 = PerformClickUtils.getTextByNodeId(this.autoService, wx_number);
            if (textByNodeId == null || "".equals(textByNodeId)) {
                textByNodeId = "";
            } else if (textByNodeId.contains(":")) {
                String str3 = textByNodeId.split(":")[1];
                textByNodeId = (str3 == null || "".equals(str3)) ? "" : str3.trim();
            }
            if ("".equals(textByNodeId)) {
                String textFirstByNodeId = PerformClickUtils.getTextFirstByNodeId(this.autoService, wx_nick_remark_name);
                if (textFirstByNodeId != null && !"".equals(textFirstByNodeId)) {
                    textByNodeId = textFirstByNodeId.trim();
                } else if (str == null || "".equals(str)) {
                    textByNodeId = "";
                } else {
                    str2 = str;
                    SQLiteUtils.getInstance().addCopyFriend(new CopyFriendBean((textByNodeId2 == null || "".equals(textByNodeId2)) ? "" : textByNodeId2.contains(":") ? textByNodeId2.split(":")[1].trim() : "", str2, str, DateUtils.timeToString(System.currentTimeMillis()), false));
                }
            }
            str2 = textByNodeId;
            SQLiteUtils.getInstance().addCopyFriend(new CopyFriendBean((textByNodeId2 == null || "".equals(textByNodeId2)) ? "" : textByNodeId2.contains(":") ? textByNodeId2.split(":")[1].trim() : "", str2, str, DateUtils.timeToString(System.currentTimeMillis()), false));
        } catch (Exception e) {
        }
    }

    public void saveFriendInfo(String str) {
        try {
            String textByNodeId = PerformClickUtils.getTextByNodeId(this.autoService, wx_nick_name);
            String textByNodeId2 = PerformClickUtils.getTextByNodeId(this.autoService, wx_number);
            if (textByNodeId == null || "".equals(textByNodeId)) {
                textByNodeId = "";
            } else if (textByNodeId.contains(":")) {
                String str2 = textByNodeId.split(":")[1];
                textByNodeId = (str2 == null || "".equals(str2)) ? "" : str2.trim();
            }
            if ("".equals(textByNodeId)) {
                String textFirstByNodeId = PerformClickUtils.getTextFirstByNodeId(this.autoService, wx_nick_remark_name);
                if (textFirstByNodeId != null && !"".equals(textFirstByNodeId)) {
                    str = textFirstByNodeId.trim();
                } else if (str == null || "".equals(str)) {
                    str = "";
                }
            } else {
                str = textByNodeId;
            }
            SQLiteUtils.getInstance().addFriend(new RecoverFriendBean((textByNodeId2 == null || "".equals(textByNodeId2)) ? "" : textByNodeId2.contains(":") ? textByNodeId2.split(":")[1].trim() : "", str, DateUtils.timeToString(System.currentTimeMillis()), false));
        } catch (Exception e) {
        }
    }

    public void sleep(int i) {
        try {
            Thread.sleep((long) i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateCopyFriendInfoAdded(CopyFriendBean copyFriendBean) {
        try {
            copyFriendBean.setIsAdded(true);
            SQLiteUtils.getInstance().updateCopyFriend(copyFriendBean);
        } catch (Exception e) {
        }
    }

    public void updateFriendInfoAdded(RecoverFriendBean recoverFriendBean) {
        try {
            recoverFriendBean.setIsAdded(true);
            SQLiteUtils.getInstance().updateFriend(recoverFriendBean);
        } catch (Exception e) {
        }
    }
}
