package com.wx.assistants.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.View;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnChangeLisener;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.wx.assistants.Enity.TagPeoplesBean;
import com.wx.assistants.activity.LoginActivity;
import com.wx.assistants.dialog.AlertActionSheetDialog;
import com.wx.assistants.dialog.AlertEditDefaultDialog;
import com.wx.assistants.dialog.AlertEditDialog;
import com.wx.assistants.dialog.AlertSuccessDialog;
import com.wx.assistants.dialog.AlertTitledDialog;
import com.wx.assistants.dialog.AlertUntitledDialog;
import com.wx.assistants.dialog.ChangeDeviceDialog;
import com.wx.assistants.dialog.FloatWindowDialog;
import com.wx.assistants.dialog.FunctionalSpecificationDialog;
import com.wx.assistants.dialog.TagMembersDialog;
import com.wx.assistants.dialog.VersionUpdateDialog;
import com.wx.assistants.dialog.VersionUpdateDialogNew1;
import com.wx.assistants.dialog.view.AlertEditTextDialog;
import com.wx.assistants.dialog.view.AlertFFModelDialog;
import com.wx.assistants.dialog.view.OpenAncillaryServiceDialog;
import java.util.Date;
import java.util.List;

public class DialogUIUtils {
    public static void dialogInput(Context context, String str, AlertEditDefaultDialog.OnEditTextListener onEditTextListener) {
        new AlertEditDefaultDialog(context).builder().setTitle(str).setPositiveButton(onEditTextListener).setCancelable(true).show();
    }

    public static void setEditText(Context context, String str, String str2, AlertEditTextDialog.OnEditTextListener onEditTextListener) {
        new AlertEditTextDialog(context).builder().setTitle(str).setEditText(str2).setPositiveButton(onEditTextListener).setCancelable(true).show();
    }

    public static void dialogSetStartPoint(Context context, int i, AlertEditDialog.OnEditTextListener onEditTextListener) {
        new AlertEditDialog(context).builder().setTitle("设置起点").setEditText(i).setPositiveButton(onEditTextListener).setCancelable(true).show();
    }

    public static void dialogSetStartPoint(Context context, String str, int i, int i2, int i3, AlertEditDialog.OnEditTextListener onEditTextListener) {
        new AlertEditDialog(context).builder().setTitle(str).setMaxNum(i2).setMinNum(i3).setEditText(i).setPositiveButton(onEditTextListener).setCancelable(true).show();
    }

    public static void dialogFunctionalSpecification(Context context, String str, String str2) {
        new FunctionalSpecificationDialog(context).builder().setTitle(str).setMsg(str2).setCancelable(true).show();
    }

    public static void selectDate(Context context, String str, Date date, OnSureLisener onSureLisener) {
        DatePickDialog datePickDialog = new DatePickDialog(context);
        datePickDialog.setYearLimt(30);
        datePickDialog.setTitle(str);
        datePickDialog.setType(DateType.TYPE_YMD);
        datePickDialog.setMessageFormat("yyyy年MM月dd日");
        datePickDialog.setOnChangeLisener((OnChangeLisener) null);
        datePickDialog.setStartDate(date);
        datePickDialog.setOnSureLisener(onSureLisener);
        datePickDialog.show();
    }

    public static void dialogSetStartPoint(Context context, String str, int i, AlertEditDialog.OnEditTextListener onEditTextListener) {
        new AlertEditDialog(context).builder().setTitle(str).setEditText(i).setPositiveButton(onEditTextListener).setCancelable(true).show();
    }

    public static void dialogSetStartPoint(Context context, String str, int i, int i2, AlertEditDialog.OnEditTextListener onEditTextListener) {
        new AlertEditDialog(context).builder().setTitle(str).setMaxNum(i2).setEditText(i).setPositiveButton(onEditTextListener).setCancelable(true).show();
    }

    public static void fansselect(Context context, int i, int i2, AlertEditDialog.OnEditTextListener onEditTextListener) {
        new AlertEditDialog(context).builder().setTitle("设置加粉数量").setEditText(i).setMaxNum(i2).setPositiveButton(onEditTextListener).setCancelable(true).show();
    }

    public static void setFFModelTime(Context context, int i, int i2, AlertFFModelDialog.OnEditTextListener onEditTextListener) {
        new AlertFFModelDialog(context).builder().setTitle("设置时间间隔区间").setEditText(i, i2).setPositiveButton(onEditTextListener).setCancelable(true).show();
    }

    public static void fansSelect(Context context, int i, int i2, AlertEditDialog.OnEditTextListener onEditTextListener) {
        new AlertEditDialog(context).builder().setTitle("设置加粉数量").setMaxNum(i2).setEditText(i).setPositiveButton(onEditTextListener).setCancelable(true).show();
    }

    public static void circulateNum(Context context, int i, AlertEditDialog.OnEditTextListener onEditTextListener) {
        new AlertEditDialog(context).builder().setTitle("设置循环次数").setEditText(i).setPositiveButton(onEditTextListener).setCancelable(true).show();
    }

    public static void dialogTagMembers(Context context, List<TagPeoplesBean> list) {
        new TagMembersDialog(context).builder().setMemberData(list).setPositiveButton((DialogInterface.OnClickListener) null).setCancelable(true).show();
    }

    public static void dialogPhoneAlreadyRegister(Context context, String str, View.OnClickListener onClickListener) {
        new AlertUntitledDialog(context).builder().setMsg(String.format(context.getResources().getString(2131689733), new Object[]{str})).setPositiveButton(context.getResources().getString(2131689619), AlertUntitledDialog.TitleColor.Green, onClickListener).setCancelable(false).show();
    }

    public static void dialogRegisterForgotPwd(Context context, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        new AlertUntitledDialog(context).builder().setMsg(context.getResources().getString(2131689548)).setNegativeButton(context.getResources().getString(2131689569), AlertUntitledDialog.TitleColor.GRAY, onClickListener).setPositiveButton(context.getResources().getString(2131689552), AlertUntitledDialog.TitleColor.Green, onClickListener2).setCancelable(false).show();
    }

    public static void dialogDefault(Context context, String str, String str2, String str3, String str4, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        new AlertTitledDialog(context).builder().setTitle(str).setMsg(str2).setNegativeButton(str3, AlertTitledDialog.TitleColor.GRAY, onClickListener).setPositiveButton(str4, AlertTitledDialog.TitleColor.Green, onClickListener2).setCancelable(false).show();
    }

    public static void dialogAccountNoExists(Context context, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        new AlertUntitledDialog(context).builder().setMsg(context.getResources().getString(2131689567)).setNegativeButton(context.getResources().getString(2131689566), AlertUntitledDialog.TitleColor.GRAY, onClickListener).setPositiveButton(context.getResources().getString(2131689570), AlertUntitledDialog.TitleColor.Green, onClickListener2).setCancelable(false).show();
    }

    public static void dialogTest(Context context, String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        new AlertUntitledDialog(context).builder().setMsg(str).setNegativeButton("取消", AlertUntitledDialog.TitleColor.GRAY, onClickListener).setPositiveButton("测试", AlertUntitledDialog.TitleColor.Green, onClickListener2).setCancelable(true).show();
    }

    public static void dialogAccountPwdError(Context context, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        new AlertUntitledDialog(context).builder().setMsg(context.getResources().getString(2131689559)).setNegativeButton(context.getResources().getString(2131689557), AlertUntitledDialog.TitleColor.GRAY, onClickListener).setPositiveButton(context.getResources().getString(2131689570), AlertUntitledDialog.TitleColor.Green, onClickListener2).setCancelable(false).show();
    }

    public static void dialogVerificationCodeDelay(Context context, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        new AlertUntitledDialog(context).builder().setMsg(context.getResources().getString(2131689562)).setNegativeButton(context.getResources().getString(2131689549), AlertUntitledDialog.TitleColor.GRAY, onClickListener).setPositiveButton(context.getResources().getString(2131689565), AlertUntitledDialog.TitleColor.Green, onClickListener2).setCancelable(false).show();
    }

    public static void dialogPhoneNumberIllegal(Context context, String str, View.OnClickListener onClickListener) {
        AlertUntitledDialog builder = new AlertUntitledDialog(context).builder();
        if (str == null) {
            str = context.getResources().getString(2131689564);
        }
        builder.setMsg(str).setPositiveButton(context.getResources().getString(2131689565), AlertUntitledDialog.TitleColor.Green, onClickListener).setCancelable(false).show();
    }

    public static void dialogNilTile(Context context, String str, View.OnClickListener onClickListener) {
        new AlertUntitledDialog(context).builder().setMsg(str).setPositiveButton(context.getResources().getString(2131689565), AlertUntitledDialog.TitleColor.Green, onClickListener).setCancelable(true).show();
    }

    public static void dialogAccountExists(Context context, View.OnClickListener onClickListener) {
        new AlertUntitledDialog(context).builder().setMsg(context.getResources().getString(2131689547)).setPositiveButton(context.getResources().getString(2131689558), AlertUntitledDialog.TitleColor.Green, onClickListener).setCancelable(false).show();
    }

    public static void actionSheetLogOut(Context context, AlertActionSheetDialog.OnSheetItemClickListener onSheetItemClickListener) {
        new AlertActionSheetDialog(context).builder().setTitle(context.getResources().getString(2131689561)).setCancelable(false).setCanceledOnTouchOutside(true).addSheetItem(context.getResources().getString(2131689560), AlertActionSheetDialog.SheetItemColor.Red, onSheetItemClickListener).show();
    }

    public static void actionSheetLoginMore(Context context, AlertActionSheetDialog.OnSheetItemClickListener onSheetItemClickListener) {
        new AlertActionSheetDialog(context).builder().setCancelable(false).setCanceledOnTouchOutside(true).addSheetItem(context.getResources().getString(2131689618), AlertActionSheetDialog.SheetItemColor.Black, onSheetItemClickListener).addSheetItem(context.getResources().getString(2131689819), AlertActionSheetDialog.SheetItemColor.Black, onSheetItemClickListener).show();
    }

    public static void actionSheetSelectPhotos(Context context, AlertActionSheetDialog.OnSheetItemClickListener onSheetItemClickListener) {
        new AlertActionSheetDialog(context).builder().setCancelable(false).setCanceledOnTouchOutside(true).addSheetItem(context.getResources().getString(2131689527), AlertActionSheetDialog.SheetItemColor.Black, onSheetItemClickListener).addSheetItem(context.getResources().getString(2131689817), AlertActionSheetDialog.SheetItemColor.Black, onSheetItemClickListener).show();
    }

    public static void actionSheetSavePhotos(Context context, AlertActionSheetDialog.OnSheetItemClickListener onSheetItemClickListener) {
        new AlertActionSheetDialog(context).builder().setCancelable(false).setCanceledOnTouchOutside(true).addSheetItem(context.getResources().getString(2131689814), AlertActionSheetDialog.SheetItemColor.Black, onSheetItemClickListener).show();
    }

    public static void dialogNoNetWork(Context context, View.OnClickListener onClickListener) {
        new AlertUntitledDialog(context).builder().setMsg(context.getResources().getString(2131689732)).setPositiveButton(context.getResources().getString(2131689617), AlertUntitledDialog.TitleColor.Green, onClickListener).setCancelable(false).show();
    }

    public static void openAncillaryServices(Context context, String str, View.OnClickListener onClickListener) {
        new OpenAncillaryServiceDialog(context).builder().setTitle("温馨提示").setMsg(str).setPositiveButton("去开启辅助服务", OpenAncillaryServiceDialog.TitleColor.Red, onClickListener).setCancelable(true).show();
    }

    public static void openFloatWindowServices(Context context, View.OnClickListener onClickListener) {
        new FloatWindowDialog(context).builder().setTitle("温馨提示").setMsg("1.若使用微商宝贝的（群加粉、清理僵尸粉、一键转发朋友圈/相册、自动加附近人、群发图文等）功能服务，您需要开启悬浮窗权限。\n2.点击【去开启悬浮窗】,找到微商宝贝,打开悬浮窗权限即可。\n3.若不开启您将无法使用微商宝贝辅助服务。").setPositiveButton("去开启悬浮窗", FloatWindowDialog.TitleColor.Red, onClickListener).setCancelable(true).show();
    }

    public static void actionSheetNomalNote(Context context, CharSequence charSequence, View.OnClickListener onClickListener) {
        AlertUntitledDialog builder = new AlertUntitledDialog(context).builder();
        if (charSequence == null) {
            charSequence = context.getResources().getString(2131689564);
        }
        builder.setMsg(charSequence).setPositiveButton(context.getResources().getString(2131689565), AlertUntitledDialog.TitleColor.Green, onClickListener).setCancelable(true).show();
    }

    public static void dialogAppVersion(Context context, int i, String str, String str2, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        if (!VersionUpdateDialog.isShowing) {
            VersionUpdateDialogNew1 builder = VersionUpdateDialogNew1.newInstance().init(context).builder();
            VersionUpdateDialogNew1 versionNegativeButton = builder.setTitle("V:" + str).setMsg(str2).setVersionNegativeButton("下次再说", (VersionUpdateDialogNew1.TitleColor) null, onClickListener);
            boolean z = true;
            VersionUpdateDialogNew1 versionPositiveButton = versionNegativeButton.setVersionPositiveButton("立即更新", 1, (VersionUpdateDialogNew1.TitleColor) null, onClickListener2);
            if (i != 0) {
                z = false;
            }
            versionPositiveButton.setCancelable(z).show();
        }
    }

    public static void dialogChangerDevice(Context context, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnClickListener onClickListener3) {
        ChangeDeviceDialog.newInstance().init(context).builder().setTitle("设备更换提醒").setMsg("检测到当前用户已经在其他手机开通了会员；\n原则上以前开通的会员只能在一台设备上使用；\n如果您打算更换到这台设备上使用，需要您支付5元服务费！\n若选择更换，请点击'更换设备'按钮自主更换，更换后原设备将停止使用；\n若想保证原设备也能使用，请单独在为该设备充值会员即可;\n如有其他问题，请在功能建议处，联系客服帮您处理。").setOpenMemeberButton("购买会员", ChangeDeviceDialog.TitleColor.Red, onClickListener).setVersionNegativeButton("更换设备", ChangeDeviceDialog.TitleColor.Red, onClickListener2).setVersionPositiveButton("确定", 1, ChangeDeviceDialog.TitleColor.Black, onClickListener3).setCancelable(true).show();
    }

    public static void GroupTabSelectDialog(Context context, String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        new AlertUntitledDialog(context).builder().setMsg(str).setNegativeButton("放弃选择", AlertUntitledDialog.TitleColor.GRAY, onClickListener).setPositiveButton("继续选择", AlertUntitledDialog.TitleColor.Green, onClickListener2).setCancelable(false).show();
    }

    public static void dialogInviteCopy(Context context, String str, AlertActionSheetDialog.OnSheetItemClickListener onSheetItemClickListener) {
        new AlertActionSheetDialog(context).builder().setCancelable(false).setCanceledOnTouchOutside(true).setTitle(str).addSheetItem("复制手机号", AlertActionSheetDialog.SheetItemColor.Black, onSheetItemClickListener).show();
    }

    public static void goLogin(final Activity activity, View.OnClickListener onClickListener, final boolean z) {
        new AlertTitledDialog(activity).builder().setTitle("温馨提示").setMsg("您尚未登录，请登录后在操作！").setVersionNegativeButton(activity.getResources().getString(2131689550), AlertTitledDialog.TitleColor.GRAY, onClickListener).setCancelable(true).setVersionPositiveButton("去登录", 1, AlertTitledDialog.TitleColor.Green, new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(activity, LoginActivity.class);
                intent.putExtra("isNeedBack", z);
                activity.startActivity(intent);
            }
        }).setCancelable(true).show();
    }

    public static void authorizationDialog(Context context, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        new AlertTitledDialog(context).builder().setTitle("温馨提示").setMsg("必须授权才能安装APK，请设置允许安装").setVersionNegativeButton("取消", AlertTitledDialog.TitleColor.GRAY, onClickListener).setVersionPositiveButton("设置", 1, AlertTitledDialog.TitleColor.Green, onClickListener2).show();
    }

    public static void dialogOpenMember(Context context, String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        AlertTitledDialog title = new AlertTitledDialog(context).builder().setTitle(context.getResources().getString(2131689736));
        if (str == null) {
            str = context.getResources().getString(2131689738);
        }
        title.setMsg(str).setNegativeButton(context.getResources().getString(2131689550), AlertTitledDialog.TitleColor.GRAY, onClickListener).setCancelable(true).setVersionPositiveButton(context.getResources().getString(2131689737), 1, AlertTitledDialog.TitleColor.Green, onClickListener2).setCancelable(true).show();
    }

    public static void dialogOpenMemberMain(Context context, String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnClickListener onClickListener3) {
        ChangeDeviceDialog title = ChangeDeviceDialog.newInstance().init(context).builder().setTitle(context.getResources().getString(2131689740));
        if (str == null) {
            str = context.getResources().getString(2131689739);
        }
        title.setMsg(str).setOpenMemeberButton("取消", ChangeDeviceDialog.TitleColor.GRAY, onClickListener).setCancelable(true).setNegativeButton("免费体验", ChangeDeviceDialog.TitleColor.Red, onClickListener2).setCancelable(true).setVersionPositiveButton(context.getResources().getString(2131689737), 1, ChangeDeviceDialog.TitleColor.Green, onClickListener3).setCancelable(true).show();
    }

    public static void dialogNoPermission(Context context, String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        String str2;
        if (TextUtils.equals("android.permission.CAMERA", str)) {
            str2 = String.format(context.getResources().getString(2131689866), new Object[]{context.getResources().getString(2131689863)});
        } else if (TextUtils.equals("android.permission.RECORD_AUDIO", str)) {
            str2 = String.format(context.getResources().getString(2131689866), new Object[]{context.getResources().getString(2131689867)});
        } else if (TextUtils.equals("android.permission.READ_CONTACTS", str)) {
            str2 = String.format(context.getResources().getString(2131689866), new Object[]{context.getResources().getString(2131689864)});
        } else if (TextUtils.equals("android.permission.READ_EXTERNAL_STORAGE", str) || TextUtils.equals("android.permission.WRITE_EXTERNAL_STORAGE", str)) {
            str2 = String.format(context.getResources().getString(2131689866), new Object[]{context.getResources().getString(2131689865)});
        } else {
            str2 = String.format(context.getResources().getString(2131689866), new Object[]{context.getResources().getString(2131689865)});
        }
        new AlertTitledDialog(context).builder().setTitle(context.getResources().getString(2131689750)).setMsg(str2).setNegativeButton(context.getResources().getString(2131689550), AlertTitledDialog.TitleColor.GRAY, onClickListener).setPositiveButton(context.getResources().getString(2131689611), AlertTitledDialog.TitleColor.Green, onClickListener2).setCancelable(false).show();
    }

    public static void successInfoDialog(Context context, String str, @DrawableRes int i, long j, AlertSuccessDialog.DelayedListener delayedListener) {
        new AlertSuccessDialog(context).builder().setDelayTime(j).setSuccessText(str, AlertSuccessDialog.TitleColor.Withe).setSuccessImg(i).setDelayedListener(delayedListener).show();
    }

    public static void successInfoDialog(Context context) {
        new AlertSuccessDialog(context).builder().setCancelable(false).setDelayTime(2000).setSuccessText(context.getResources().getString(2131689815), AlertSuccessDialog.TitleColor.Withe).setSuccessImg(2131231014).show();
    }

    public static void dialogCrowdWorkSearch(Context context) {
        new AlertTitledDialog(context).builder().setTitle("未搜索到相关内容").setMsg("未搜索到您输入的相关内容，请重新输入搜索内容").setNegativeButton(context.getResources().getString(2131689542), AlertTitledDialog.TitleColor.Green, (View.OnClickListener) null).setCancelable(false).show();
    }
}
