package com.wx.assistants.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;
import bottomtabbar.BottomTabBar;
import com.blankj.utilcode.util.SizeUtils;
import com.luck.picture.lib.R;
import com.meiqia.core.MQManager;
import com.meiqia.core.bean.MQMessage;
import com.meiqia.core.callback.OnGetMessageListCallback;
import com.meiqia.meiqiasdk.util.MQIntentBuilder;
import com.stub.StubApp;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.fragment.InComeFragment2;
import com.wx.assistants.fragment.MaterialForwardFragment;
import com.wx.assistants.fragment.MyFragment;
import com.wx.assistants.fragment.TabHomeFragment2;
import com.wx.assistants.utils.ApkDownUtils;
import com.wx.assistants.utils.AppManager;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import java.util.List;

public class MainActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public boolean isBlack = true;
    private BottomTabBar mBottomTabBar;
    /* access modifiers changed from: private */
    public boolean mFlag;

    static {
        StubApp.interface11(8968);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.wx.assistants.activity.MainActivity, android.content.Context] */
    private void clickTwoExit() {
        if (!this.mFlag) {
            this.mFlag = true;
            Toast.makeText(this, "再按一次退出程序", 0).show();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    boolean unused = MainActivity.this.mFlag = false;
                }
            }, 2000);
            return;
        }
        AppManager.getAppManager().finishAllActivity();
        System.exit(0);
        Process.killProcess(Process.myPid());
    }

    private void initView() {
        this.mBottomTabBar = findViewById(2131296746);
        if (!((Boolean) SPUtils.get(MyApplication.getConText(), "version_open", true)).booleanValue()) {
            this.mBottomTabBar.init(getSupportFragmentManager()).setImgSize((float) SizeUtils.dp2px(24.0f), (float) SizeUtils.dp2px(24.0f)).setFontSize(12.0f).setTabPadding((float) SizeUtils.dp2px(8.0f), (float) SizeUtils.dp2px(3.0f), (float) SizeUtils.dp2px(6.0f)).setChangeColor(getResources().getColor(R.color.main_color), getResources().getColor(2131099736)).addTabItem("首页", 2131231400, 2131231399, TabHomeFragment2.class).addTabItem("收益", 2131231406, 2131231405, InComeFragment2.class).addTabItem("个人中心", 2131231404, 2131231403, MyFragment.class).isShowDivider(true).setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                public void onTabChange(int i, String str, View view) {
                    if (i == 0 || i == 2 || i == 3) {
                        boolean unused = MainActivity.this.isBlack = true;
                        MainActivity.this.blackFill();
                        return;
                    }
                    boolean unused2 = MainActivity.this.isBlack = false;
                    MainActivity.this.whiteFill();
                }
            }).setCurrentTab(0);
        } else {
            this.mBottomTabBar.init(getSupportFragmentManager()).setImgSize((float) SizeUtils.dp2px(24.0f), (float) SizeUtils.dp2px(24.0f)).setFontSize(12.0f).setTabPadding((float) SizeUtils.dp2px(8.0f), (float) SizeUtils.dp2px(3.0f), (float) SizeUtils.dp2px(6.0f)).setChangeColor(getResources().getColor(R.color.main_color), getResources().getColor(2131099736)).addTabItem("首页", 2131231400, 2131231399, TabHomeFragment2.class).addTabItem("收益", 2131231406, 2131231405, InComeFragment2.class).addTabItem("素材转发", 2131231402, 2131231401, MaterialForwardFragment.class).addTabItem("个人中心", 2131231404, 2131231403, MyFragment.class).isShowDivider(true).setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                public void onTabChange(int i, String str, View view) {
                    if (i == 0 || i == 2 || i == 3) {
                        boolean unused = MainActivity.this.isBlack = true;
                        MainActivity.this.blackFill();
                    } else {
                        boolean unused2 = MainActivity.this.isBlack = false;
                        MainActivity.this.whiteFill();
                    }
                    if (i == 3) {
                        MQManager.getInstance(MyApplication.getConText()).getUnreadMessages(new OnGetMessageListCallback() {
                            public void onFailure(int i, String str) {
                                LogUtils.log("WS_BABY" + str);
                            }

                            /* JADX WARNING: type inference failed for: r0v5, types: [com.wx.assistants.activity.MainActivity, android.content.Context] */
                            public void onSuccess(List<MQMessage> list) {
                                LogUtils.log("WS_BABY" + list);
                                if (list != null && list.size() > 0) {
                                    DialogUIUtils.dialogDefault(MainActivity.this, "客服提醒", "您有" + list.size() + "条客服消息未查看，是否查看？", "取消", "查看", (View.OnClickListener) null, new View.OnClickListener() {
                                        /* JADX WARNING: type inference failed for: r1v2, types: [com.wx.assistants.activity.MainActivity, android.content.Context] */
                                        public void onClick(View view) {
                                            MainActivity.this.startActivity(new MQIntentBuilder(MainActivity.this).build());
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            }).setCurrentTab(0);
        }
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        clickTwoExit();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.wx.assistants.activity.MainActivity, com.wx.assistants.base.BaseActivity, android.content.Context, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        boolean booleanValue = ((Boolean) SPUtils.get(MyApplication.getConText(), "version_open", true)).booleanValue();
        boolean booleanValue2 = ((Boolean) SPUtils.get(this, "isCanShowVerDialog", true)).booleanValue();
        if (booleanValue && booleanValue2) {
            ApkDownUtils.getInstance(this).checkVersion(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.isBlack) {
            blackFill();
        } else {
            whiteFill();
        }
    }
}
