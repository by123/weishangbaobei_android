package com.wx.assistants.service_utils;

import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.service.AutoService;
import com.wx.assistants.service.FloatMiddleView;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.service_utils.node_company.Node3014;
import com.wx.assistants.service_utils.node_company.Node3016;
import com.wx.assistants.utils.PackageUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.SPUtils;

public class BaseServiceCompanyUtils {
    public static String company_album_id = "com.tencent.wework:id/x8";
    public static String company_back = "com.tencent.wework:id/ghk";
    public static String company_complete_id = "com.tencent.wework:id/gi2";
    public static String company_contact_input_edit_id = "com.tencent.wework:id/doo";
    public static String company_contact_list_id = "com.tencent.wework:id/awd";
    public static String company_contact_title_id = "com.tencent.wework:id/ghl";
    public static String company_customer_list_id = "com.tencent.wework:id/ewa";
    public static String company_customer_remark_name = "com.tencent.wework:id/g3i";
    public static String company_edit_img = "com.tencent.wework:id/a9r";
    public static String company_edit_text = "com.tencent.wework:id/a9p";
    public static String company_group_member_list_id = "com.tencent.wework:id/dmz";
    public static String company_group_member_name_id = "com.tencent.wework:id/go0";
    public static String company_img_first_check_layout_id = "com.tencent.wework:id/fcn";
    public static String company_remark_edit_id = "com.tencent.wework:id/awz";
    public static String company_right_more_id = "com.tencent.wework:id/ghv";
    public static String company_select_video_id = "com.tencent.wework:id/ix";
    public static String company_send_add_id = "com.tencent.wework:id/dob";
    public static String company_tag_group_id = "com.tencent.wework:id/b6z";
    public static String company_tag_list_id = "com.tencent.wework:id/g81";
    public static String company_tag_list_name_id = "com.tencent.wework:id/g87";
    public static String company_voice_left_id = "com.tencent.wework:id/cxq";
    public static String company_wx_company_id = "com.tencent.wework:id/g3c";
    public static String company_wx_nick_name_id = "com.tencent.wework:id/g3i";
    public static int executeSpeed = 300;
    public static int executeSpeedSetting = 0;
    private static Handler mainHandler = null;
    public static String remarkName = "";
    public static int wxVersionCode = 0;
    public static String wxVersionName = "";
    public AutoService autoService;
    public MyWindowManager mMyManager;

    interface IRunnable {
        void run();
    }

    public void initSetting() {
        this.autoService = MyApplication.instance.getAutoService();
        this.mMyManager = MyApplication.instance.getMyWindowManager();
        initWXVersion();
        getMainHandler();
        initVersion();
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

    public void sleep(int i) {
        try {
            Thread.sleep((long) i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void inputText(String str) {
        PerformClickUtils.inputText(this.autoService, str);
    }

    public static void setMiddleText(final MyWindowManager myWindowManager, final String str, final String str2) {
        getMainHandler().post(new Runnable() {
            public void run() {
                myWindowManager.getMiddleView().setTitle(str).setContent(str2);
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

    public static void updateBottomText(final MyWindowManager myWindowManager, final String str) {
        getMainHandler().post(new Runnable() {
            public void run() {
                myWindowManager.getBottomView().setBottomText(str);
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

    public static void showBottomView(final MyWindowManager myWindowManager, final String str) {
        getMainHandler().post(new Runnable() {
            public void run() {
                myWindowManager.showBottomView();
                BaseServiceCompanyUtils.updateBottomText(myWindowManager, str);
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

    public static void removeEndView(final MyWindowManager myWindowManager) {
        getMainHandler().post(new Runnable() {
            public void run() {
                System.out.println("WS_BABY_EDN.....了");
                myWindowManager.removeEndView();
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

    public static void removeMiddleView(final MyWindowManager myWindowManager) {
        getMainHandler().post(new Runnable() {
            public void run() {
                myWindowManager.removeMiddleView();
            }
        });
    }

    public static void removeBottomOperationView(final MyWindowManager myWindowManager) {
        getMainHandler().post(new Runnable() {
            public void run() {
                myWindowManager.removeBottomOperationView();
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
                String textByNodeId = PerformClickUtils.getTextByNodeId(this.autoService, company_customer_remark_name);
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
        } catch (Exception unused) {
            remarkName = "";
        }
    }

    public BaseServiceCompanyUtils() {
        initSetting();
        int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "seek_bar_speed", 0)).intValue();
        if (intValue >= executeSpeed) {
            executeSpeedSetting = intValue - executeSpeed;
        } else {
            executeSpeedSetting = 0;
        }
    }

    public static void initVersion() {
        if ("3.0.14".equals(wxVersionName)) {
            Node3014.node();
        } else if ("3.0.16".equals(wxVersionName)) {
            Node3016.node();
        } else {
            Node3016.node();
        }
    }
}
