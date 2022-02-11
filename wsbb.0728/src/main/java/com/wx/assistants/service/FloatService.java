package com.wx.assistants.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.utils.LogUtils;

public class FloatService extends Service {
    private String executeTag;
    private MyWindowManager mMyManager;
    private OperationParameterModel model;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        LogUtils.log("WS_BABY_FloatService.onCreate");
        this.mMyManager = MyWindowManager.newInstance();
    }

    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "onDestroy");
        if (this.mMyManager != null) {
            this.mMyManager.removeStartView();
            this.mMyManager.removeEndView();
            this.mMyManager.removeMiddleView();
            this.mMyManager.removeBackView();
            this.mMyManager.removeBottomView();
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtils.log("WS_BABY_FloatService.onStartCommand");
        try {
            this.executeTag = intent.getStringExtra("executeTag");
        } catch (Exception e) {
            this.executeTag = "-1";
        }
        try {
            this.model = MyApplication.instance.getOperationParameterModel();
            if (this.executeTag == null) {
                this.executeTag = this.model.getTaskNum();
            }
        } catch (Exception e2) {
        }
        this.mMyManager.setExecuteTag(this.executeTag);
        if (!"9".equals(this.executeTag)) {
            this.mMyManager.showStartView();
            this.mMyManager.showBackView();
        } else if (this.model.getPraiseCommentType() == 0) {
            this.mMyManager.showPraiseStartView();
            this.mMyManager.showCommentStartView();
            this.mMyManager.showBackView();
        } else if (this.model.getPraiseCommentType() == 1) {
            this.mMyManager.showPraiseStartView();
            this.mMyManager.showBackView();
        } else {
            this.mMyManager.showCommentStartView();
            this.mMyManager.showBackView();
        }
        return super.onStartCommand(intent, i, i2);
    }

    public void setForceBackground() {
        LogUtils.log("WS_BABY_FloatService.setForceBackground");
        Notification notification = new Notification();
        notification.flags = 2;
        notification.flags |= 32;
        notification.flags |= 64;
        startForeground(1, notification);
    }
}
