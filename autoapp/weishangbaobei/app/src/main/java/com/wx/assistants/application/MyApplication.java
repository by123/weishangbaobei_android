package com.wx.assistants.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Process;
import android.support.multidex.MultiDex;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import com.google.gson.Gson;
import com.meiqia.core.MQManager;
import com.meiqia.core.callback.OnInitCallback;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.stub.StubApp;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.wx.assistants.Constants.Constants;
import com.wx.assistants.bean.HomeBannerBean;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.greendao.gen.DaoMaster;
import com.wx.assistants.greendao.gen.DaoSession;
import com.wx.assistants.service.AutoService;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.AppManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MyOpenHelper;
import com.wx.assistants.utils.SPUtils;
import io.microshow.rxffmpeg.RxFFmpegInvoke;
import java.util.List;

public class MyApplication extends Application {
    public static final String BD_OCR_KEY = "aewYpQG5Dv3lZYo7P8hCATLW";
    public static final String BD_OCR_SK = "ePnveddPZpSAyDWAGVAhPXKD0f9fgwb2";
    public static final String BuglyKey = "1ddbfbc018";
    public static final int INVITE_IMG_NUM = 1;
    public static final int INVITE_URL_CODE = 5;
    public static final String MESSAGE_RECEIVED_ACTION = "com.ws.baby.push.MESSAGE_RECEIVED_ACTION";
    public static final String MeiQiaKey = "3fb047fbeb390704f27804f8d97907be";
    public static int SCROLL_SPEED = 400;
    public static String SHARE_DOWN_URL = "";
    public static final String WS = "com.example.wx.assistant";
    public static final String WS_MainActivity = "com.wx.assistants.activity.MainActivity";
    public static final String WX_APP_ID = "wx5f30d18ee6c0c547";
    public static final String WX_Merchant_NUMBER = "1520755691";
    public static final String WX_SECRET = "fd23863ea28a89d01ce739ba29648e6e";
    public static HomeBannerBean bannerPayBean = null;
    public static String[] checkStr = {"我正清删除或拉黑我的人，免费用.", "我正清删除或拉黑我的人，免费使用."};
    public static int countSend = 0;
    public static boolean enforceable = false;
    public static boolean hasBDGotToken = false;
    public static MyApplication instance = null;
    public static String inviteUrlStr = "";
    public static boolean isComment = false;
    public static boolean isCommentSign = false;
    public static boolean isNeedSign = false;
    public static boolean isOpenLog = true;
    public static boolean isValidationService = false;
    public static String lastGroupName = "";
    public static Context mContext;
    public static int startX;
    public static int startY;
    private AutoService autoService;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private MyOpenHelper mHelper;
    private OperationParameterModel model;
    private MyWindowManager myWindowManager;

    public MyApplication() {
        PlatformConfig.setWeixin(WX_APP_ID, WX_SECRET);
        PlatformConfig.setQQZone("101549981", "62204cb86a0dd9faf6d888f723bd6fad");
    }

    public void onCreate() {
        super.onCreate();
        mContext = StubApp.getOrigApplicationContext(getApplicationContext());
        Constants.mainId = Process.myTid();
        Constants.thread = Thread.currentThread();
        Constants.handler = new Handler();
        instance = this;
        UMShareAPI.get(this);
        UMConfigure.init(this, 1, (String) null);
        UMConfigure.setLogEnabled(true);
        UMConfigure.setEncryptEnabled(true);
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        CrashReport.initCrashReport(StubApp.getOrigApplicationContext(getApplicationContext()), BuglyKey, false);
        initMeiqiaSDK();
        BGASwipeBackHelper.init(this, (List) null);
        setDatabase();
        try {
            RxFFmpegInvoke.getInstance().setDebug(false);
        } catch (Exception unused) {
        }
    }

    private void initMeiqiaSDK() {
        MQManager.setDebugMode(true);
        MQConfig.init(this, MeiQiaKey, (OnInitCallback) null);
        customMeiqiaSDK();
    }

    private void customMeiqiaSDK() {
        MQConfig.ui.titleGravity = MQConfig.ui.MQTitleGravity.LEFT;
        MQConfig.ui.backArrowIconResId = 2131558407;
    }

    public static Context getConText() {
        return mContext;
    }

    public MyWindowManager getMyWindowManager() {
        this.myWindowManager = MyWindowManager.newInstance();
        return this.myWindowManager;
    }

    public static int getMainId() {
        return Constants.mainId;
    }

    public static Thread getMainThread() {
        return Constants.thread;
    }

    public static Handler getHandler() {
        return Constants.handler;
    }

    public Activity getBaseActivity() {
        return AppManager.getAppManager().currentActivity();
    }

    public void setOperationParameterModel(OperationParameterModel operationParameterModel) {
        if (this.model != null) {
            this.model = null;
        }
        if (operationParameterModel != null) {
            this.model = operationParameterModel;
            String json = new Gson().toJson(this.model);
            SPUtils.put(getConText(), "model_json", json);
            LogUtils.log("WS_BABY_JSON" + json);
        }
    }

    public OperationParameterModel getOperationParameterModel() {
        if (this.model != null) {
            return this.model;
        }
        String str = (String) SPUtils.get(this, "model_json", "");
        if (str == null || "".equals(str)) {
            return new OperationParameterModel();
        }
        return (OperationParameterModel) new Gson().fromJson(str, OperationParameterModel.class);
    }

    public void setAutoService(AutoService autoService2) {
        this.autoService = autoService2;
    }

    public AutoService getAutoService() {
        if (this.autoService != null) {
            return this.autoService;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(context);
    }

    private void setDatabase() {
        this.mHelper = new MyOpenHelper(this, "ws_baby_db", (SQLiteDatabase.CursorFactory) null);
        this.db = this.mHelper.getWritableDatabase();
        this.mDaoMaster = new DaoMaster(this.db);
        this.mDaoSession = this.mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return this.mDaoSession;
    }
}
